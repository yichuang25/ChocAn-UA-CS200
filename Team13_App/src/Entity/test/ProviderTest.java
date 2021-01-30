package Entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entity.Provider;

/**
 * This class is a unit test for provider in Entity class. I will test 4 function in the class.
 * @author Yichen Huang
 *
 */

class ProviderTest {
	Provider temp;

	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test for failure
	 */
	@Test
	void testGetWeeklyConsultationCount() {
		temp = new Provider(123456789, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487);
		assertNotEquals(1,temp.getWeeklyConsultationCount());
		
	}

	/**
	 * Test for success
	 */
	@Test
	void testSetWeeklyConsultationCount() {
		temp = new Provider(123456789, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487);
		temp.setWeeklyConsultationCount(3);
		assertEquals(3,temp.getWeeklyConsultationCount());
		
	}
	/**
	 * Test for success
	 */
	@Test
	void testSetID() {
		temp = new Provider(000000000, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487);
		temp.setID(123456789);
		assertEquals(123456789,temp.getID());
	}

}
