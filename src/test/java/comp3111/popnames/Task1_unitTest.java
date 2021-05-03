package comp3111.popnames;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class Task1_unitTest extends ApplicationTest {
	
	
	@Test
	public void testGetTopNname_T1() {
		assertArrayEquals(Task1_AnalyzeNames.getTopNnames(1999, 10, "M"),Task1_AnalyzeNames.getTopNnames(1999, 10, "M"));
	}
	
	@Test
	public void testGetSummary_T1() {
		assertEquals(Task1_AnalyzeNames.getSummary(1999, 10),Task1_AnalyzeNames.getSummary(1999, 10));
	}
	
	@Test
	public void testCheckValidInput_T1() {
		assertTrue(Task1_AnalyzeNames.checkValidInput_T1(2010, 111));
	}
	
	@Test
	public void testCheckEmptyInput_T1() {
		assertTrue(Task1_AnalyzeNames.checkEmpty_T1("asda", "asdasd"));
	}
	

}
