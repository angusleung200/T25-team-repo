package comp3111.popnames;

import java.util.ArrayList;
import java.util.List;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.*;

public class PopupWindow {
	
	PopupWindow(){
		
	}
	
	public static void displayErrorMsg(String title,String content)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}
	
	public static void displayTableTask2(String title,List<ArrayList<String>> result) {
		TableView<Task2Name> table = new TableView<Task2Name>();
		table.setMaxSize(490, 490);
		
		TableColumn<Task2Name,String> noCol = new TableColumn<>("No");
		noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
		noCol.setPrefWidth(100);
		table.getColumns().add(noCol);
		
		TableColumn<Task2Name,String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.setPrefWidth(100);
		table.getColumns().add(nameCol);
		
		
		TableColumn<Task2Name,String> frequencyCol = new TableColumn<>("Frequency");
		frequencyCol.setCellValueFactory(new PropertyValueFactory<>("frequency"));
		frequencyCol.setPrefWidth(100);
		table.getColumns().add(frequencyCol);
		
		TableColumn<Task2Name,String> occurrencesCol = new TableColumn<>("Occurrences");
		occurrencesCol.setCellValueFactory(new PropertyValueFactory<>("occurrences"));
		occurrencesCol.setPrefWidth(100);
		table.getColumns().add(occurrencesCol);
		
		TableColumn<Task2Name,String> percentageCol = new TableColumn<>("Percentage");
		percentageCol.setCellValueFactory(new PropertyValueFactory<>("percentage"));
		percentageCol.setPrefWidth(100);
		table.getColumns().add(percentageCol);
		int numberOfOccurrences = 0;
		int numberOfFreq = 0;
		for(int i=0;i<result.size();i++) {
			numberOfOccurrences += Integer.parseInt(result.get(i).get(2));
			numberOfFreq += Integer.parseInt(result.get(i).get(1));
			table.getItems().add(new Task2Name(String.valueOf(i+1),result.get(i).get(0),result.get(i).get(1),result.get(i).get(2),result.get(i).get(3)+"%"));
		}
		System.out.println(table.getItems());
		Stage stage = new Stage();
		Label label1 = new Label("Total Frequency: "+String.valueOf(numberOfFreq));
		Label label2 = new Label("Total Occurrences: "+String.valueOf(numberOfOccurrences));
		Label label3 = new Label("Percentage: 100%");
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(table);
		vbox.getChildren().addAll(label1);
		vbox.getChildren().addAll(label2);
		vbox.getChildren().addAll(label3);
		Scene scene = new Scene(vbox, 500, 500);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
	
	
	public static void displayBarChartTask2(String title,List<ArrayList<String>> result) {
		
		CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("K-th Popular Names");
        xAxis.setLabel("Name");       
        yAxis.setLabel("Occurrences");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Name");
        for(int i=0;i<result.size();i++)
        {
        	series1.getData().add(new XYChart.Data(result.get(i).get(0), Integer.parseInt(result.get(i).get(2))));
        }

		
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox, 500, 500);
		Stage stage = new Stage();
		bc.getData().addAll(series1);
		vbox.getChildren().add(bc);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	
	
	public static void displayPieChartTask2(String title,List<ArrayList<String>> result) {
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for(int i=0;i<result.size();i++)
        {
        	pieChartData.add(new PieChart.Data(result.get(i).get(0), Integer.parseInt(result.get(i).get(2))));
        }
		VBox vbox = new VBox();
		Scene scene = new Scene(vbox, 500, 500);
		Stage stage = new Stage();
		PieChart pieChart = new PieChart(pieChartData);
		pieChart.setTitle(title);
		vbox.getChildren().add(pieChart);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
	

	
	public static void displayTableTask5(String title,List<String> result) {
		TableView<Task5Name> table = new TableView<Task5Name>();
		table.setMaxSize(490, 490);
		
		TableColumn<Task5Name,String> noCol = new TableColumn<>("No");
		noCol.setCellValueFactory(new PropertyValueFactory<>("no"));
		noCol.setPrefWidth(100);
		table.getColumns().add(noCol);
		
		TableColumn<Task5Name,String> nameCol = new TableColumn<>("Name");
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		nameCol.setPrefWidth(100);
		table.getColumns().add(nameCol);
		
		
		
		for(int i=0;i<result.size();i++)
			table.getItems().add(new Task5Name(String.valueOf(i+1),result.get(i)));
		Stage stage = new Stage();
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(table);
		Scene scene = new Scene(vbox, 500, 500);
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
}
