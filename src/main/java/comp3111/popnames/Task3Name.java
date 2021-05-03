package comp3111.popnames;
import java.util.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.duke.FileResource;

public class Task3Name {
	public static CSVParser getFileParser(int year) {
	     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
	     return fr.getCSVParser(false);
		}
	public static int getRank(int year, String name, String gender) 
	{
		 boolean found = false;
	     int oRank = 0;
	 	int rank = 1;
	     for (CSVRecord rec : getFileParser(year)) {
	         
	         if (rec.get(1).equals(gender)) {
	             
	             if (rec.get(0).equals(name)) {
	             	found = true;
	             	oRank = rank;
	             	break;
	             }
	             rank++;
	         }
	     }
	     if (found)
	     	return oRank;
	     else
	     	return -1;
	}
	
	public static int getOccurances(int year, String name, String gender) 
	{
		boolean found =false;
		int occ=0;
		for (CSVRecord rec : getFileParser(year)) {
	         
	         if (rec.get(1).equals(gender)) {
	             
	             if (rec.get(0).equals(name)) {
	             	found = true;
	             	occ = Integer.parseInt(rec.get(2));
	             	break;
	             }
	             
	         }
	     }
		if(found){ return occ;}
		else {return 0;}
	}
	
	public static double getPercent(int year,String name,String gender) 
	{
		double n = getOccurances(year,name,gender);
		double total=0;
		for(CSVRecord rec : getFileParser(year)) 
		{
			if(rec.get(1).equals(gender)) 
			{
				total+=Integer.parseInt(rec.get(2));
			}
		}
		return (n/total)*100;
	}
	
	public static int[] getValidYears(int year1,int year2, String name, String gender) 
	{
		List<Integer> myNumbers = new ArrayList<Integer>();
		for(int i=year1;i<year2+1;i++) 
		{
			boolean a = getRank(i,name,gender)>1000;
			if(!a) 
			{
				myNumbers.add(i);
			}
		}
		int[] array = new int[myNumbers.size()];
		for(int i=0;i<myNumbers.size();i++) 
		{
			array[i]=myNumbers.get(i);
		}
		return array;
	}
	
	public static String[][] getTable(int year1,int year2, String name, String gender)
	{
		int size = getValidYears(year1,year2,name,gender).length;
		String[][] table = new String[size][4]; 
		
		for(int i=0;i<size;i++) 
		{
			table[i][0]=Integer.toString(getValidYears(year1,year2,name,gender)[i]);
			table[i][1]=Integer.toString(getRank(getValidYears(year1,year2,name,gender)[i],name,gender));
			table[i][2]=Integer.toString(getOccurances(getValidYears(year1,year2,name,gender)[i],name,gender));
			table[i][3]=String.format("%.2f%%",getPercent(getValidYears(year1,year2,name,gender)[i],name,gender));
			

		}
		return table;
	}
	
	public static JTable getFinalTable(int year1,int year2, String name, String gender) 
	{
		JFrame frame = new JFrame(" ");
		String[][] table = getTable(year1,year2,name,gender);
		String[] column = {"Year","Rank","Occurrences","Percentage"};
		
		JTable Table = new JTable(table,column);
		Table.setBounds(30,40,200,300);
		JScrollPane sp_Table = new JScrollPane(Table);
		frame.add(sp_Table);
		frame.setSize(360,400);    
	    frame.setVisible(true);
	    return Table;
		
	}
}
