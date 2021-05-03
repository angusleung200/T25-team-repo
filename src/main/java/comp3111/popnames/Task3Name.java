package comp3111.popnames;
import java.util.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import javafx.scene.chart.LineChart;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.duke.FileResource;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Task3Name {
	public static CSVParser getFileParser(int year) {
	     FileResource fr = new FileResource(String.format("src/main/resources/dataset/yob%s.csv", year));
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
		return n/total*100;
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
		JFrame frame = new JFrame("Table");
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
	public static int getMostPopRank(int year1,int year2, String name, String gender) 
	{
		String[][] tb = getTable(year1,year2,name,gender);
		
		int min = Integer.parseInt(tb[0][1]);
		for(int i=1;i<getValidYears(year1,year2,name,gender).length;i++) 
		{
			if(Integer.parseInt(tb[i][1])<=min) 
			{
				min = Integer.parseInt(tb[i][1]);
			}
		}
		
		return min;
	}
	
	public static int getMostPopYear(int year1,int year2, String name, String gender) 
	{
		
		int answer =0;
		String[][] tb = getTable(year1,year2,name,gender);
		int rank = getMostPopRank(year1,year2,name,gender);
		for(int i =0;i<getValidYears(year1,year2,name,gender).length;i++ ) 
		{
			if(Integer.parseInt(tb[i][1])==rank) 
			{
				answer =i;
				
			}
		}
		return answer+year1;
	}
	
	public static String getSummary(int year1,int year2, String name, String gender) 
	{
		String g; 
		if(gender.equals("M")) 
		{
			g="male";
		}
		else{
				g="female";
			}
		String report;
		int i = getMostPopYear(year1,year2,name,gender);
		report= String.format("The year when the name %s ", name);
		report+= String.format(" was most popular is %s at rank %d ", i, getMostPopRank(year1,year2,name,gender));
		report+= String.format("."+'\n'+"In that year, the number of occurrences is %s ",getOccurances(i,name,gender));
		report+=String.format(", which represents %.2f%% ", getPercent(i,name,gender));
		report +=String.format(" of total "+ g +" births in %s",i);
		
		
		return report;
		
	}
	public static void showBarChart(int year1,int year2, String name, String gender) 
	{
		String[][] tb = getTable(year1,year2,name,gender);
		int max_occ = getOccurances(getMostPopYear(year1,year2,name,gender),name,gender);
		CategoryAxis xaxis = new CategoryAxis();
		xaxis.setLabel("Year"); 
	    NumberAxis yaxis = new NumberAxis(1, max_occ, max_occ/ 10);   
	    yaxis.setLabel("Occurrences"); 
	    
	    XYChart.Series<String, Integer> series = new XYChart.Series<>();
		series.setName("year");
		BarChart<String, Integer> bar_chart = new BarChart(xaxis, yaxis);
		bar_chart.setTitle("Occurrences of "+name+" from "+year1+" to "+year2);
		
		for(int i = 0; i<getValidYears(year1,year2,name,gender).length;i++) 
		{
			series.getData().add(new XYChart.Data<>(tb[i][0],Integer.parseInt(tb[i][2])));
		}
		bar_chart.getData().add(series);
	    bar_chart.setVisible(true);
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox, 500, 500);
		Stage stage = new Stage();
		vbox.getChildren().add(bar_chart);
		stage.setScene(scene);
		stage.show();
	}
	
	public static void showLineChart(int year1,int year2, String name, String gender) 
	{
		String[][] tb = getTable(year1,year2,name,gender);
		int max_occ = getOccurances(getMostPopYear(year1,year2,name,gender),name,gender);
		NumberAxis xaxis = new NumberAxis(year1-2,year2+2,1);
		xaxis.setLabel("Year"); 
	    NumberAxis yaxis = new NumberAxis(1, max_occ*1.2, max_occ/ 10);   
	    yaxis.setLabel("Occurrences");
	    
	    XYChart.Series<Integer, Integer> series = new XYChart.Series<>();
	    series.setName("Year");
	    
	    LineChart<Integer,Integer> lineChart = new LineChart(xaxis,yaxis);
	    lineChart.setTitle("Occurrences of "+name+" from "+year1+" to "+year2);
	    for(int i = 0; i<getValidYears(year1,year2,name,gender).length;i++) 
		{
			series.getData().add(new XYChart.Data<>(Integer.parseInt(tb[i][0]),Integer.parseInt(tb[i][2])));
		}
	    lineChart.getData().add(series);
	    lineChart.setVisible(true);
	    VBox vbox = new VBox();
	    Scene scene = new Scene(vbox, 500, 500);
		Stage stage = new Stage();
		vbox.getChildren().add(lineChart);
		stage.setScene(scene);
		stage.show();
	    
	    
	}
}
