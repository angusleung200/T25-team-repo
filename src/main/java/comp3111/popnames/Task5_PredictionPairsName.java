package comp3111.popnames;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class Task5_PredictionPairsName {

	/**
     *  Task5_PredictionPairsName
     *  Constructor
     *  
     */
	Task5_PredictionPairsName(){
		
	}
	
	
	/**
     *  dataChecker
     *  get the data from ui component and valid the data for task5
     *  
     */
	public boolean dataChecker(TextField t5_textfield_name,TextField t5_textfield_year, ComboBox<String> t5_combobox_gender,ComboBox<String> t5_combobox_gender_mate,ComboBox<String> t5_combobox_preference,ComboBox<String> t5_combobox_algorithm,ComboBox<String> t5_combobox_year_range) {
		int year = -1;
    	int yearRange = -1;
    	String name = "";
    	String gender = "";
    	String genderMate = "";
    	String preference = "";
    	String algorithm = "";
    	PopupWindow pw = new PopupWindow();
		
    	
    	name = t5_textfield_name.getText();
    	String tmp_year = t5_textfield_year.getText();
    	
    	if(name.isEmpty())
    	{
    		pw.displayErrorMsg("Error","Name should not be empty.");
			return false;
    	}
    	
    	if(tmp_year.isEmpty())
    	{
    		pw.displayErrorMsg("Error","Year should not be empty.");
			return false;
    	}
    	year =Integer.parseInt(tmp_year);
    	gender = t5_combobox_gender.getSelectionModel().getSelectedItem().toString();
    	genderMate = t5_combobox_gender_mate.getSelectionModel().getSelectedItem().toString();
    	preference = t5_combobox_preference.getSelectionModel().getSelectedItem().toString();
    	algorithm = t5_combobox_algorithm.getSelectionModel().getSelectedItem().toString();
    	yearRange = Integer.parseInt(t5_combobox_year_range.getSelectionModel().getSelectedItem().toString());
    	
    	if (year < 1880 || year > 2019)
    	{
    		pw.displayErrorMsg("Error","Year should be between 1880 and 2019");
    		return false;
    	}
    	
    	if(algorithm.equals("T5X2"))
    	{
    		if(preference.equals("Younger") && year+yearRange>2019)
    		{
    			pw.displayErrorMsg("Error","Value out of range.Year and Year Range should be less than and equals to 2019. "+String.valueOf(year)+" + "+String.valueOf(yearRange)+" = "+String.valueOf(year+yearRange)+" <= 2019");
    			return false;
    		}
    		else if (preference.equals("Older") && year-yearRange<1880)
    		{
    			pw.displayErrorMsg("Error","Value out of range.Year and Year Range should be greater than and equals to 1880. "+String.valueOf(year)+" - "+String.valueOf(yearRange)+" = "+String.valueOf(year-yearRange)+" >= 1880");
    			return false;
    		}
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
     *  report
     *  process the data and find the pairs of the name with different algorithm
     *  
     */
	public List<String> report(int year,int yearRange,String algorithm,char genderMate,String preference,String name) {
		if(algorithm.equals("T5X1")) { // T5X1
			List<String> csvList = new ArrayList<String>();
			Iterator<CSVRecord> iterator = getFileParser(year).iterator();
			while(iterator.hasNext()) {
				CSVRecord csvRow = iterator.next();
				if(csvRow.get(1).toString().trim().charAt(0) == genderMate)
				{
					csvList.add(csvRow.get(0).toString());
					return csvList;
				}
			}
			
		}
		else if(algorithm.equals("T5X2")) {// T5X2
			List<ArrayList<String>> csvList = new ArrayList<ArrayList<String>>();
			
			if(preference.equals("Younger")) {
				for(int i=year;i<year+yearRange;i++)
				{
					Iterator<CSVRecord> iterator = getFileParser(i).iterator();
					while(iterator.hasNext()) {
						CSVRecord csvRow = iterator.next();
						if(csvRow.get(1).toString().trim().charAt(0) == genderMate && csvRow.get(0).toString().trim().charAt(0) == name.charAt(0))
						{
							ArrayList<String> tmp = new ArrayList<String>();
							tmp.add(csvRow.get(0).toString());
							tmp.add(csvRow.get(1).toString());
							tmp.add(csvRow.get(2).toString());
							csvList.add(tmp);
							break;
						}
					}
				}
			}
			else if(preference.equals("Older")){
				for(int i=year-yearRange+1;i<=year;i++)
				{
					Iterator<CSVRecord> iterator = getFileParser(i).iterator();
					while(iterator.hasNext()) {
						CSVRecord csvRow = iterator.next();
						if(csvRow.get(1).toString().trim().charAt(0) == genderMate && csvRow.get(0).toString().trim().charAt(0) == name.charAt(0))
						{
							ArrayList<String> tmp = new ArrayList<String>();
							tmp.add(csvRow.get(0).toString());
							tmp.add(csvRow.get(1).toString());
							tmp.add(csvRow.get(2).toString());
							csvList.add(tmp);
							break;
						}
					}
					
				}
			}
			
			List<String> result = new ArrayList<String>();
			for(int i=0;i<csvList.size();i++)
	    	{
				boolean checkExisting = true;
				for(int j=0;j<result.size();j++) {
					if(csvList.get(i).get(0).equals(result.get(j)))
						checkExisting = false;
				}
				if(checkExisting)
					result.add(csvList.get(i).get(0));
	    	}
			
			
			return result;
		}
		return null;
	}
	
	
	/**
     *  getSummary
     *  return summary of the result
     *  
     */
	public String getSummary(List<String> result,int year,int yearRange,String preference,String algorithm) {
		String oReport="";
		if(result.size() > 0)
		{
			if(algorithm.equals("T5X1"))
			{
				oReport +="Summary (From "+String.valueOf(year)+"):\n";
			}
			else if(algorithm.equals("T5X2"))
			{
				if(preference.equals("Younger"))
					oReport +="Summary (From "+String.valueOf(year)+" to "+String.valueOf(year+yearRange)+"):\n";
				else if(preference.equals("Older"))
					oReport +="Summary (From "+String.valueOf(year-yearRange)+" to "+String.valueOf(year)+"):\n";
			}
			oReport += "Prediction on Names for Compatible Pairs:\n";
			for(int i=0;i<result.size();i++)
				oReport += String.valueOf(i+1)+". "+ result.get(i)+"\n";
		}
		else {
			oReport += "No data.\n";
			
		}
		return oReport;
	}
}


