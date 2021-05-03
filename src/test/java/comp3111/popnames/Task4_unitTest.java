package comp3111.popnames;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

public class Task4_unitTest extends ApplicationTest {

	@Test
	public void testT4X1_T4() {
		assertEquals(Task4_nameRecommendation.t4x1_func(1999, 2010, "John", "Mary"), Task4_nameRecommendation.t4x1_func(1999, 2010, "John", "Mary"));
	}
	
	@Test
	public void testT4X2_T4() {
		assertNotEquals(Task4_nameRecommendation.t4x2_func(1990, 2000, "Angus", "Candy"), Task4_nameRecommendation.t4x2_func(1990, 2000, "Angus", "Candy"));
	}
}
