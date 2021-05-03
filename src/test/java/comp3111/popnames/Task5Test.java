package comp3111.popnames;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class Task5Test extends ApplicationTest{
	
	private TextField t5_textfield_name;
	private TextField t5_textfield_year;
	private ComboBox<String> t5_combobox_gender;
	private ComboBox<String> t5_combobox_gender_mate;
	private ComboBox<String> t5_combobox_preference;
	private ComboBox<String> t5_combobox_algorithm;
	private ComboBox<String> t5_combobox_year_range;
	
	@Test
	public void TestDataChecker1() {
		t5_textfield_name = new TextField();
		t5_textfield_year = new TextField();
		t5_combobox_gender = new ComboBox<String>();
		t5_combobox_gender_mate = new ComboBox<String>();
		t5_combobox_preference = new ComboBox<String>();
		t5_combobox_algorithm = new ComboBox<String>();
		t5_combobox_year_range = new ComboBox<String>();
		
		t5_textfield_name.setText("Jeff");
		t5_textfield_year.setText("2010");
		t5_combobox_gender.setValue("Male");
		t5_combobox_gender_mate.setValue("Female");
		t5_combobox_preference.setValue("Younger");
		t5_combobox_algorithm.setValue("T5X2");
		t5_combobox_year_range.setValue("5");
		Task5_PredictionPairsName pnp = new Task5_PredictionPairsName();
		assertTrue(pnp.dataChecker(t5_textfield_name, t5_textfield_year, t5_combobox_gender, t5_combobox_gender_mate, t5_combobox_preference, t5_combobox_algorithm, t5_combobox_year_range));
	}
	
	@Test
	public void TestDataChecker2() {
		t5_textfield_name = new TextField();
		t5_textfield_year = new TextField();
		t5_combobox_gender = new ComboBox<String>();
		t5_combobox_gender_mate = new ComboBox<String>();
		t5_combobox_preference = new ComboBox<String>();
		t5_combobox_algorithm = new ComboBox<String>();
		t5_combobox_year_range = new ComboBox<String>();
		
		t5_textfield_name.setText("Jeff");
		t5_textfield_year.setText("2010");
		t5_combobox_gender.setValue("Male");
		t5_combobox_gender_mate.setValue("Female");
		t5_combobox_preference.setValue("Older");
		t5_combobox_algorithm.setValue("T5X2");
		t5_combobox_year_range.setValue("20");
		Task5_PredictionPairsName pnp = new Task5_PredictionPairsName();
		assertTrue(pnp.dataChecker(t5_textfield_name, t5_textfield_year, t5_combobox_gender, t5_combobox_gender_mate, t5_combobox_preference, t5_combobox_algorithm, t5_combobox_year_range));
	}
	
	@Test
	public void TestReport1() {
		
		List<String> test = new ArrayList<String>();
		test.add("Jessica");
		test.add("Jasmine");
		test.add("Julia");
		Task5_PredictionPairsName pnp = new Task5_PredictionPairsName();
		assertEquals(test, pnp.report(2010,20,"T5X2",'F',"Older","Jeff"));
		
	}
	
	
	@Test
	public void TestReport2() {
		
		List<String> test = new ArrayList<String>();
		test.add("Joshua");
		test.add("Jacob");
		Task5_PredictionPairsName pnp = new Task5_PredictionPairsName();
		assertEquals(test, pnp.report(2010,20,"T5X2",'M',"Older","Jeff"));
		
	}
}
