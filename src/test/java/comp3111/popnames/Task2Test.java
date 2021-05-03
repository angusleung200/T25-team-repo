package comp3111.popnames;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.control.TextField;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Task2Test extends ApplicationTest {
	public TextField t2_textfield_yoi1;
	public TextField t2_textfield_yoi2;
	public TextField t2_textfield_k_th;
	

	@Test
	public void TestDataChecker() {
		t2_textfield_yoi1 = new TextField();
		t2_textfield_yoi2 = new TextField();
		t2_textfield_k_th = new TextField();
		
		t2_textfield_yoi1.setText("2000");
		t2_textfield_yoi2.setText("2010");
		t2_textfield_k_th.setText("8");
		Task2_KthPopularNames kthPopularNames = new Task2_KthPopularNames();
		assertTrue(kthPopularNames.dataChecker(t2_textfield_yoi1, t2_textfield_yoi2, t2_textfield_k_th));
    }
	
	@Test
	public void TestReport() {
		List<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
		test.add(new ArrayList<String>() {{
			   add("Emma");
			   add("3");
			   add("57322");
			   add("50.0");
			}});
		test.add(new ArrayList<String>() {{
			   add("Isabella");
			   add("2");
			   add("37731");
			   add("33.0");
			}});
		test.add(new ArrayList<String>() {{
			   add("Sophia");
			   add("1");
			   add("20612");
			   add("18.0");
			}});
		Task2_KthPopularNames kthPopularNames = new Task2_KthPopularNames();
		assertEquals(test,kthPopularNames.report(2005, 2010, 2, 'F'));
		
    }
	
	@Test
	public void TestCheckExisting() {
		List<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
		test.add(new ArrayList<String>() {{
			   add("Emma");
			   add("3");
			   add("57322");
			   add("50.0");
			}});
		test.add(new ArrayList<String>() {{
			   add("Isabella");
			   add("2");
			   add("37731");
			   add("33.0");
			}});
		test.add(new ArrayList<String>() {{
			   add("Sophia");
			   add("1");
			   add("20612");
			   add("18.0");
			}});
		Task2_KthPopularNames kthPopularNames = new Task2_KthPopularNames();
		assertTrue(kthPopularNames.checkExisting(test, "Isabella"));
    }
	
	@Test
	public void TestSearchByName() {
		List<ArrayList<String>> test = new ArrayList<ArrayList<String>>();
		test.add(new ArrayList<String>() {{
			   add("Emma");
			   add("3");
			   add("57322");
			   add("50.0");
			}});
		test.add(new ArrayList<String>() {{
			   add("Isabella");
			   add("2");
			   add("37731");
			   add("33.0");
			}});
		test.add(new ArrayList<String>() {{
			   add("Sophia");
			   add("1");
			   add("20612");
			   add("18.0");
			}});
		Task2_KthPopularNames kthPopularNames = new Task2_KthPopularNames();
		assertEquals(kthPopularNames.searchByName(test, "Isabella"), 1);
    }
	
	@Test
	public void TestSort() {
		List<ArrayList<String>> test1 = new ArrayList<ArrayList<String>>();
		test1.add(new ArrayList<String>() {{
			   add("Isabella");
			   add("2");
			   add("37731");
			   add("33.0");
			}});
		test1.add(new ArrayList<String>() {{
			   add("Sophia");
			   add("1");
			   add("20612");
			   add("18.0");
			}});
		test1.add(new ArrayList<String>() {{
			   add("Emma");
			   add("3");
			   add("57322");
			   add("50.0");
			}});
		
		List<ArrayList<String>> test2 = new ArrayList<ArrayList<String>>();
		test2.add(new ArrayList<String>() {{
			   add("Emma");
			   add("3");
			   add("57322");
			   add("50.0");
			}});
		test2.add(new ArrayList<String>() {{
			   add("Isabella");
			   add("2");
			   add("37731");
			   add("33.0");
			}});
		test2.add(new ArrayList<String>() {{
			   add("Sophia");
			   add("1");
			   add("20612");
			   add("18.0");
			}});
		
		Task2_KthPopularNames kthPopularNames = new Task2_KthPopularNames();
		assertEquals(kthPopularNames.sort(kthPopularNames.sort(test1, 2),1), test2);
	}
	
	
	
}
