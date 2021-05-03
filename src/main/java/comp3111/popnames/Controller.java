/**
 * Building on the sample skeleton for 'ui.fxml' COntroller Class generated by SceneBuilder 
 */
package comp3111.popnames;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;

import javax.swing.*;


public class Controller {

    @FXML
    private Tab tabTaskZero;

    @FXML
    private TextField textfieldNameF;

    @FXML
    private TextField textfieldYear;

    @FXML
    private Button buttonRankM;

    @FXML
    private TextField textfieldNameM;

    @FXML
    private Button buttonRankF;

    @FXML
    private Button buttonTopM;

    @FXML
    private Button buttonTopF;

    @FXML
    private Button buttonSummary;
    
    @FXML
    private Tab tabReport1;

    @FXML
    private ToggleGroup T1;

    @FXML
    private Tab tabReport2;

    @FXML
    private ToggleGroup T11;

    @FXML
    private Tab tabReport3;

    @FXML
    private ToggleGroup T111;

    @FXML
    private Tab tabApp1;

    @FXML
    private Tab tabApp2;

    @FXML
    private Tab tabApp3;

    @FXML
    private TextArea textAreaConsole;
    
    @FXML
    private Button buttonDataTable_T1;

    @FXML
    private Button buttonPieChart_T1;

    @FXML
    private Button buttonBarChart_T1;

    @FXML
    private Button buttonSummary_T1;
    
    @FXML
    private TextField textfieldYear_T1;
    
    @FXML
    private TextField textfieldTopN_T1;
    
    @FXML
    private TextField textfieldDadName_T4;
    
    @FXML
    private TextField textfieldMomName_T4;
    
    @FXML
    private TextField textfieldDadYOB_T4;
    
    @FXML
    private TextField textfieldMomYOB_T4;
    
    @FXML
    private Button buttonT4X1_T4;

    @FXML
    private Button buttonT4X2_T4;
    
    @FXML
    private TextField textfieldYear1_T3;
    
    @FXML
    private TextField textfieldYear2_T3;
    
    @FXML
    private TextField textfieldName_T3;
    
    @FXML
    private TextField textfieldGender_T3;
    
    @FXML
    private Button buttonDataTable_T3;
    
    @FXML
    private Button buttonSummary_T3;
    
    @FXML
    private Button buttonBarChart_T3;
    
    @FXML
    private Button buttonLineChart_T3;
    
    @FXML
    private Button buttonT6X1_T6;
    
    @FXML
    private Button buttonT6X2_T6;
    
    @FXML
    private TextField textfieldiName_T6;
    
    @FXML
    private TextField textfieldiMateName_T6;
    
    @FXML
    private TextField textfieldiGender_T6;
    
    @FXML
    private TextField textfieldiGenderMate_T6;
    
    @FXML
    private TextField textfieldiYOB_T6;
    
    @FXML
    private TextField textfieldiPreference_T6;


    /**
     *  Task Zero
     *  To be triggered by the "Summary" button on the Task Zero Tab 
     *  
     */
    @FXML
    void doSummary() {
    	int year = Integer.parseInt(textfieldYear.getText());
    	String oReport = AnalyzeNames.getSummary(year);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankF() {
    	String oReport = "";
    	String iNameF = textfieldNameF.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameF, "F");
    	if (oRank == -1)
    		oReport = String.format("The name %s (female) has not been ranked in the year %d.\n", iNameF, iYear);
    	else
    		oReport = String.format("Rank of %s (female) in year %d is #%d.\n", iNameF, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }

  
    /**
     *  Task Zero
     *  To be triggered by the "Rank (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doRankM() {
    	String oReport = "";
    	String iNameM = textfieldNameM.getText();
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	int oRank = AnalyzeNames.getRank(iYear, iNameM, "M");
    	if (oRank == -1)
    		oReport = String.format("The name %s (male) has not been ranked in the year %d.\n", iNameM, iYear);
    	else
    		oReport = String.format("Rank of %s (male) in year %d is #%d.\n", iNameM, iYear, oRank);
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (female)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopF() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (female) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "F"));
    	textAreaConsole.setText(oReport);
    }


    /**
     *  Task Zero
     *  To be triggered by the "Top 5 (male)" button on the Task Zero Tab
     *  
     */
    @FXML
    void doTopM() {
    	String oReport = "";
    	final int topN = 5;
    	int iYear = Integer.parseInt(textfieldYear.getText());
    	oReport = String.format("Top %d most popular names (male) in the year %d:\n", topN, iYear);
    	for (int i=1; i<=topN; i++)
    		oReport += String.format("#%d: %s\n", i, AnalyzeNames.getName(iYear, i, "M"));
    	textAreaConsole.setText(oReport);
    }
    
    //***************************
    // Task 1
    @FXML
    void doSummary_T1() {
    	int year = Integer.parseInt(textfieldYear_T1.getText());
    	int topN = Integer.parseInt(textfieldTopN_T1.getText());
    	String oReport = Task1_AnalyzeNames.getSummary(year, topN);
    	textAreaConsole.setText(oReport);
    }
    
    @FXML
    void doDataTable_M_T1() {
    	int year = Integer.parseInt(textfieldYear_T1.getText());
    	int topN = Integer.parseInt(textfieldTopN_T1.getText());
    	JTable dataTable_M = Task1_AnalyzeNames.getDataTable_M(year, topN);
    }
    
    @FXML
    void doDataTable_F_T1() {
    	int year = Integer.parseInt(textfieldYear_T1.getText());
    	int topN = Integer.parseInt(textfieldTopN_T1.getText());
    	JTable dataTable_M = Task1_AnalyzeNames.getDataTable_F(year, topN);
    }

  
    @FXML
    void doPieChart_T1_M() {
    	int year = Integer.parseInt(textfieldYear_T1.getText());
    	int topN = Integer.parseInt(textfieldTopN_T1.getText());
    	Task1_AnalyzeNames.showPieChart_M(year, topN); 
    }
    
    @FXML
    void doPieChart_T1_F() {
    	int year = Integer.parseInt(textfieldYear_T1.getText());
    	int topN = Integer.parseInt(textfieldTopN_T1.getText());
    	Task1_AnalyzeNames.showPieChart_F(year, topN); 

    }


    @FXML
    void doBarChart_T1_M() {
    	int year = Integer.parseInt(textfieldYear_T1.getText());
    	int topN = Integer.parseInt(textfieldTopN_T1.getText());
    	Task1_AnalyzeNames.showBarChart_M(year, topN); 	
    }
    
    @FXML
    void doBarChart_T1_F() {
    	int year = Integer.parseInt(textfieldYear_T1.getText());
    	int topN = Integer.parseInt(textfieldTopN_T1.getText());
    	Task1_AnalyzeNames.showBarChart_F(year, topN); 	
    }
    
    //*******************************
    
    //*******************************
    //Task4
    
    @FXML
    void t4x1_T4() {
    	int dadYOB = Integer.parseInt(textfieldDadYOB_T4.getText());
    	int momYOB = Integer.parseInt(textfieldMomYOB_T4.getText());
    	String dadName = textfieldDadName_T4.getText();
    	String momName = textfieldMomName_T4.getText();
    	String oReport = Task4_nameRecommendation.t4x1_func(dadYOB, momYOB, dadName, momName);
    	textAreaConsole.setText(oReport);
    }
    
    @FXML
    void t4x2_T4() {
    	int dadYOB = Integer.parseInt(textfieldDadYOB_T4.getText());
    	int momYOB = Integer.parseInt(textfieldMomYOB_T4.getText());
    	String dadName = textfieldDadName_T4.getText();
    	String momName = textfieldMomName_T4.getText();
    	String oReport = Task4_nameRecommendation.t4x2_func(dadYOB, momYOB, dadName, momName);
    	textAreaConsole.setText(oReport);
    }
    
    
    //*******************************
    
    //*******************************
    //Task3
    
    @FXML
    void doDataTable_T3() 
    {
    	int year1 = Integer.parseInt(textfieldYear1_T3.getText());
    	int year2 = Integer.parseInt(textfieldYear2_T3.getText());
    	String name = textfieldName_T3.getText();
    	String gender = textfieldGender_T3.getText();
    	JTable finalTable = Task3Name.getFinalTable(year1,year2,name,gender);
    }
    @FXML
    void doSummary_T3() 
    {
    	int year1 = Integer.parseInt(textfieldYear1_T3.getText());
    	int year2 = Integer.parseInt(textfieldYear2_T3.getText());
    	String name = textfieldName_T3.getText();
    	String gender = textfieldGender_T3.getText();
    	String report = Task3Name.getSummary(year1,year2,name,gender);
    	textAreaConsole.setText(report);
    }
    
    @FXML
    void doBarChart_T3() 
    {
    	int year1 = Integer.parseInt(textfieldYear1_T3.getText());
    	int year2 = Integer.parseInt(textfieldYear2_T3.getText());
    	String name = textfieldName_T3.getText();
    	String gender = textfieldGender_T3.getText();
    	Task3Name.showBarChart(year1,year2,name,gender);
    }
    
    @FXML
    void doLineChart_T3() 
    {
    	int year1 = Integer.parseInt(textfieldYear1_T3.getText());
    	int year2 = Integer.parseInt(textfieldYear2_T3.getText());
    	String name = textfieldName_T3.getText();
    	String gender = textfieldGender_T3.getText();
    	Task3Name.showLineChart(year1,year2,name,gender);
    }
    
  //*******************************
    
  //*******************************
    // Task 6:
    
    @FXML
    void doT6X1_T6() 
    {
    	String iMateName =  textfieldiMateName_T6.getText();
    	String iName = textfieldiName_T6.getText();
    	String oScore = Task6Name.t6x1_func(iName,iMateName);
    	textAreaConsole.setText(oScore);
    	
    }
    
    @FXML
    void doT6X2_T6() 
    {
    	String iMateName =  textfieldiMateName_T6.getText();
    	String iName = textfieldiName_T6.getText();
    	String iPreference = textfieldiPreference_T6.getText();
    	String iGender = textfieldiGender_T6.getText();
    	String iGenderMate = textfieldiGenderMate_T6.getText();
    	int iYOB = Integer.parseInt(textfieldiYOB_T6.getText());
    	String oScore = Task6Name.t6x2_func(iName,iMateName,iGender,iGenderMate,iYOB,iPreference);
    	if(iPreference.equals("Y")&&iYOB<2002) 
		{
			
			Task3Name.showLineChart(iYOB,iYOB+10,iMateName,iGenderMate);
			
			
		}
		
		if(iPreference.equals("O")&&iYOB>1890) 
		{
			
			Task3Name.showLineChart(iYOB-10,iYOB,iMateName,iGenderMate);
			
		}
		textAreaConsole.setText(oScore);
    }
   

}

