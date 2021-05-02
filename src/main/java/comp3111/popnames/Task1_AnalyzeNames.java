package comp3111.popnames;
import java.util.*;


import javax.swing.*;

import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.duke.FileResource; 
import javafx.scene.Group;  
import javafx.scene.Scene;  
import javafx.scene.chart.BarChart;  
import javafx.scene.chart.CategoryAxis;  
import javafx.scene.chart.NumberAxis;  
import javafx.scene.chart.XYChart;  
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;

public class Task1_AnalyzeNames {
	
	public static CSVParser getFileParser(int year) {
	     FileResource fr = new FileResource(String.format("dataset/yob%s.csv", year));
	     return fr.getCSVParser(false);
		}
	 	
	
		public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm)
	    {
	        List<Map.Entry<String, Integer> > list =
	               new LinkedList<Map.Entry<String, Integer> >(hm.entrySet());
	 
	        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
	            public int compare(Map.Entry<String, Integer> o1,
	                               Map.Entry<String, Integer> o2)
	            {
	                return (o1.getValue()).compareTo(o2.getValue());
	            }
	        });
	         
	        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
	        for (Map.Entry<String, Integer> aa : list) {
	            temp.put(aa.getKey(), aa.getValue());
	        }
	        return temp;
	    }
		
		
		public static String[][] getTopNnames(int year, int topN, String gender) {
			String[][] topNPopName_M = new String[topN][3];
			String[][] topNPopName_F = new String[topN][3];
				
			int totalBirths = 0;
			double totalBoys = 0.0;
			double totalGirls = 0.0;

			int counter_M = 0;
			int counter_F = 0;
			for (CSVRecord rec : getFileParser(year)) {
				
				int numBorn = Integer.parseInt(rec.get(2));
				totalBirths += numBorn;
				if (rec.get(1).equals("M")) {
					totalBoys += numBorn;
					if(counter_M < topN) {
						topNPopName_M[counter_M][0] = rec.get(0);
						topNPopName_M[counter_M][1] = rec.get(2);
						topNPopName_M[counter_M][2] = "";
						++counter_M;
					}
					
				} 
				else if(rec.get(1).equals("F")){
					totalGirls += numBorn;
					if(counter_F < topN) {
						topNPopName_F[counter_F][0] = rec.get(0);
						topNPopName_F[counter_F][1] = rec.get(2);
						topNPopName_F[counter_F][2] = "";
						++counter_F;
					}
				}
			}
			
			for(int i = 0; i < topN; ++i) {
				double occurrences_M = Integer.parseInt(topNPopName_M[i][1]) / totalBoys * 100;
				double occurrences_F = Integer.parseInt(topNPopName_F[i][1]) / totalGirls * 100;
				topNPopName_M[i][2] = String.format("%.2f%%", occurrences_M);
				topNPopName_F[i][2] = String.format("%.2f%%", occurrences_F);
			}
			
			if(gender == "M") {
				return topNPopName_M;
			}
			else if (gender == "F") {
				return topNPopName_F;
			}	
			return new String[3][];
			
		}
		
		public static String getSummary(int year, int topN) {
			
			String[][] topN_M = getTopNnames(year, topN, "M");
			String[][] topN_F = getTopNnames(year, topN, "F");		
			String oReport = "";
			
			oReport = String.format("%s is the most popular name with the number of occurrences of ", topN_M[0][0]);
			oReport += String.format("%s, which represents ", topN_M[0][1]);
			oReport += String.format(" %s of total male births in ", topN_M[0][2]);
			oReport += String.format("%d.\n", year);
			
			oReport += String.format("%s is the most popular name with the number of occurrences of ", topN_F[0][0]);
			oReport += String.format("%s, which represents ", topN_F[0][1]);
			oReport += String.format(" %s of total male births in ", topN_F[0][2]);
			oReport += String.format("%d.\n", year);
		
			return oReport;
		}
		
		
		public static JTable getDataTable_M(int year, int topN) {
			
			JFrame f = new JFrame("Top " + topN + "Names (male) in " + year);
			String[][] topN_M = getTopNnames(year, topN, "M");	
			String[] column = {"Name","Occurrences","Percentage"};
					
			JTable table_M = new JTable(topN_M,column);
			table_M.setBounds(30,40,200,300);
			JScrollPane sp_M =new JScrollPane(table_M);
			f.add(sp_M);
			f.setSize(360,400);    
		    f.setVisible(true);
		    return table_M;		
		}
		
		public static JTable getDataTable_F(int year, int topN) {
			
			JFrame f = new JFrame("Top " + topN + "Names (female) in " + year);
			String[][] topN_F = getTopNnames(year, topN, "F");	
			String[] column = {"Name","Occurrences","Percentage"};
			
			JTable table_F = new JTable(topN_F,column);
			table_F.setBounds(30,40,200,300);				
			JScrollPane sp_F =new JScrollPane(table_F);				
			f.add(sp_F);
			f.setSize(360,400);    
		    f.setVisible(true);
		    return table_F;	
		}
		
		public static void showBarChart_M(int year, int topN) {
			
			String[][] topN_M = getTopNnames(year, topN, "M");	
			int max_occurrences = Integer.parseInt(topN_M[0][1]);
			
			CategoryAxis xaxis = new CategoryAxis();
			xaxis.setLabel("Name"); 
		    NumberAxis yaxis = new NumberAxis(1, max_occurrences, max_occurrences / 10);   
		    yaxis.setLabel("Occurrences"); 
		    
		    XYChart.Series<String, Integer> series = new XYChart.Series<>();
			series.setName("Name");
		    
		    BarChart<String, Integer> bar_chart = new BarChart(xaxis, yaxis);  
		    bar_chart.setTitle("Top" + topN + "Names (male) in " + year);
		    
		    for(int i = 0; i < topN; ++i) {
		    	series.getData().add(new XYChart.Data<>(topN_M[i][0], Integer.parseInt(topN_M[i][1])));  
		    }
		    
		    bar_chart.getData().add(series);
		    bar_chart.setVisible(true);
		    
		    VBox vbox = new VBox();
			Scene scene = new Scene(vbox, 500, 500);
			Stage stage = new Stage();
			vbox.getChildren().add(bar_chart);
			stage.setTitle("Top" + topN + "Names (male) in " + year);
			stage.setScene(scene);
			stage.show();

		}
		
		public static void showBarChart_F(int year, int topN) {
			
			String[][] topN_F = getTopNnames(year, topN, "F");	
			int max_occurrences = Integer.parseInt(topN_F[0][1]);
			
			CategoryAxis xaxis = new CategoryAxis();
			xaxis.setLabel("Name"); 
		    NumberAxis yaxis = new NumberAxis(1, max_occurrences, max_occurrences / 10);   
		    yaxis.setLabel("Occurrences"); 
		    
		    XYChart.Series<String, Integer> series = new XYChart.Series<>();
			series.setName("Name");
		    
		    BarChart<String, Integer> bar_chart = new BarChart(xaxis, yaxis);  
		    bar_chart.setTitle("Top" + topN + "Names (male) in " + year);
		    
		    for(int i = 0; i < topN; ++i) {
		    	series.getData().add(new XYChart.Data<>(topN_F[i][0], Integer.parseInt(topN_F[i][1])));  
		    	
		    }
		    
		    bar_chart.getData().add(series);
		    bar_chart.setVisible(true);
		    
		    VBox vbox = new VBox();
			Scene scene = new Scene(vbox, 500, 500);
			Stage stage = new Stage();
			vbox.getChildren().add(bar_chart);
			stage.setTitle("Top" + topN + "Names (female) in " + year);
			stage.setScene(scene);
			stage.show();

		}
		
		public static void showPieChart_M(int year, int topN) {
			
//			String[][] topN_M = getTopNnames(year, topN, "M");	
//			
//			ObservableList<PieChart.Data> pie_chart_data = FXCollections.observableArrayList();
//			
//	        for(int i = 0; i < topN ; ++i){
//	        	pie_chart_data.add(new PieChart.Data(topN_M[i][0], Integer.parseInt(topN_M[i][1])));
//	        }
//	        
//			PieChart pie_chart = new PieChart(pie_chart_data);
//			pie_chart.setTitle("Top" + topN + "Names (male) in " + year);
//	        
//			VBox vbox = new VBox();
//			Scene scene = new Scene(vbox, 500, 500);
//			Stage stage = new Stage();
//			vbox.getChildren().add(pie_chart);
//			stage.setTitle("Top" + topN + "Names (male) in " + year);
//			stage.setScene(scene);
//			stage.show();
		}
		
		public static void showPieChart_F(int year, int topN) {
			
//			String[][] topN_F = getTopNnames(year, topN, "F");	
//			
//			ObservableList<PieChart.Data> pie_chart_data = FXCollections.observableArrayList();
//			
//	        for(int i = 0; i < topN ; ++i){
//	        	pie_chart_data.add(new PieChart.Data(topN_F[i][0], Integer.parseInt(topN_F[i][1])));
//	        }
//	        
//			PieChart pie_chart = new PieChart(pie_chart_data);
//			pie_chart.setTitle("Top" + topN + "Names (female) in " + year);
//	        
//			VBox vbox = new VBox();
//			Scene scene = new Scene(vbox, 500, 500);
//			Stage stage = new Stage();
//			vbox.getChildren().add(pie_chart);
//			stage.setTitle("Top" + topN + "Names (female) in " + year);
//			stage.setScene(scene);
//			stage.show();
		}
		
		
}
