package comp3111.popnames;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.*;

import com.sun.javafx.collections.MappingChange.Map;

import edu.duke.FileResource;
import javafx.scene.control.TextField;


public class Task2_KthPopularNames {
	
	/**
     *  Task2_KthPopularNames
     *  Constructor
     *  
     */
	Task2_KthPopularNames() {
		
	}
	
	/**
     *  dataChecker
     *  get the data from ui component and valid the data for task2
     *  
     */
	public boolean dataChecker(TextField t2_textfield_yoi1,TextField t2_textfield_yoi2,TextField t2_textfield_k_th){
		int iYear1 = -1;
    	int iYear2 = -1;
    	int k = -1;
    	String summary = "";
    	String gender = "";
    	String tmp_year1 = t2_textfield_yoi1.getText();
    	String tmp_year2 = t2_textfield_yoi2.getText();
		PopupWindow pw = new PopupWindow();
    	
    	if(tmp_year1.trim().isEmpty() || tmp_year2.trim().isEmpty())
    	{
    		pw.displayErrorMsg("Error", "Year should not be empty.");
    		return false;
    	}
    	iYear1 = Integer.parseInt(tmp_year1);
		iYear2 = Integer.parseInt(tmp_year2);
		
		if(iYear1<1880 || iYear1>2019 || iYear2<1880 || iYear2>2019) {
			pw.displayErrorMsg("Error","Year should be between 1880 and 2019");
			return false;
		}
		if(iYear1 >= iYear2) {
			pw.displayErrorMsg("Error","Year1 should be greater than or equals to Year2.");
			return false;
		}
		
		if(t2_textfield_k_th.getText().trim().isEmpty())
		{
			pw.displayErrorMsg("Error", "k-th should not be empty.");
			return false;
		}
		k = Integer.parseInt(t2_textfield_k_th.getText());
		if(k<1 || k>1000)
		{
			pw.displayErrorMsg("Error", "k-th should not be between 1 and 1000");
			return false;
		}
		return true;
	}
	
	/**
     *  CSVParser
     *  read the data from csv
     *  
     */
	public static CSVParser getFileParser(int year) {
	     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
	     return fr.getCSVParser(false);
	}
	
	/**
     *  checkExisting
     * 	check the name whether existing
     *  
     */
	public boolean checkExisting(List<ArrayList<String>> result,String name) {
		for(int i=0;i<result.size();i++)
		{
			if(result.get(i).get(0).equals(name))
			{
				return true;
			}
		}
		return false; // not existing
	}
	
	
	/**
     *  searchByName
     * search the name and return the index
     *  
     */
	public int searchByName(List<ArrayList<String>> result,String name) {
		for(int i=0;i<result.size();i++) {
			if(result.get(i).get(0).equals(name))
				return i;
		}
		return -1;
	}
	
	/**
     *  sort
     *  sort the data by the column(number)
     *  
     */
	public List<ArrayList<String>> sort(List<ArrayList<String>> result,int number){
		
		int n = result.size();
		for(int i=0; i < n; i++){  
            for(int j=1; j < (n-i); j++){  
                 if(Integer.parseInt(result.get(j-1).get(number)) < Integer.parseInt(result.get(j).get(number))){   
                	 	ArrayList<String> temp = result.get(j-1);
                	 	result.set(j-1,result.get(j)); 
                	 	result.set(j,temp);
                }      
            }  
		}  
		return result;
		
	}
	
	/**
     *  report
     *  process the data and find the k-th popular names
     *  
     */
	public List<ArrayList<String>> report(int year1,int year2,int k,char gender) {
		
		List<ArrayList<String>> csvList = new ArrayList<ArrayList<String>>();
		//group all the record to a list between year1 and year2
		for(int i=year1;i<=year2;i++)
		{
			Iterator<CSVRecord> iterator = getFileParser(i).iterator();
			int counter = 0;
			while(iterator.hasNext()) {
				CSVRecord csvRow = iterator.next();
				if(csvRow.get(1).toString().trim().charAt(0) == gender)
				{
					if(counter == k-1) {
						ArrayList<String> tmp = new ArrayList<String>();
						tmp.add(csvRow.get(0).toString());
						tmp.add(csvRow.get(1).toString());
						tmp.add(csvRow.get(2).toString());
						csvList.add(tmp);
						break;
					}
					counter++;
				}
			}
		}
		
		List<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		for(int i =0;i<csvList.size();i++)
		{
			if(!checkExisting(result,csvList.get(i).get(0)))
			{
				for(int j =0;j<csvList.size();j++)
				{
					if(csvList.get(i).get(0) == csvList.get(j).get(0)) {
						ArrayList<String> tmp = new ArrayList<String>();
						tmp.add(csvList.get(i).get(0));
						tmp.add("1");
						tmp.add(csvList.get(i).get(2));
						result.add(tmp);
					}
				}
			}
			else {
				int index = searchByName(result,csvList.get(i).get(0));
				int freq = Integer.parseInt(result.get(index).get(1))+1;
				result.get(index).set(1, String.valueOf(freq));
				int occur = Integer.parseInt(result.get(index).get(2))+Integer.parseInt(csvList.get(i).get(2));
				result.get(index).set(2, String.valueOf(occur));
				
			}
			
		}
		List<ArrayList<String>> sorted = sort(sort(result,2),1);
		int total = 0;
		for(int i=0;i<sorted.size();i++)
		{
			total += Integer.parseInt(sorted.get(i).get(2));
		}
		for(int i=0;i<sorted.size();i++) {
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(sorted.get(i).get(0));
			tmp.add(sorted.get(i).get(1));
			tmp.add(sorted.get(i).get(2));
			double x = (double) Integer.parseInt(sorted.get(i).get(2))/total;
			double percentage1 = (double)Math.round((double)(x) * 100) / 100;
			double percentage2 = percentage1*100;
			tmp.add(Double.toString(percentage2));
			sorted.set(i, tmp);
		}
		return sorted;
	}
	
	
	/**
     *  getSummary
     *  return summary of the result
     *  
     */
	public String getSummary(List<ArrayList<String>> result,int year1,int year2,int k,char gender) {
		if(result.size() > 0)
		{
			String genderName =(gender == 'M')?"boys":"girls";
			String gender1 =(gender == 'M')?"male":"female";
			String oReport = result.get(0).get(0)+" has hold the "+String.valueOf(k)+"-th rank most often for a total of "+result.get(0).get(2)+" times among names registered for baby "+genderName+" born in the period from "+String.valueOf(year1)+" to "+String.valueOf(year2)+".\nThe total number of occurrences of Jessica is "+result.get(0).get(2)+", which represents "+result.get(0).get(3)+"% of total "+gender1+" births at the "+String.valueOf(k)+"-th rank in the period from "+String.valueOf(year1)+" to "+String.valueOf(year2)+".";
			return oReport;
		}
		else {
			String oReport = "No data.";
			return oReport;
		}
	}
	
}
