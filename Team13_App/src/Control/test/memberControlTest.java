/**
 * 
 */
package Control.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DataIO.GlobalData;
import Entity.Member;
import Control.memberManage;

/**
 * @author duter
 *
 */
class memberControlTest {
	GlobalData gd; 
	Member m;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		gd = new GlobalData(); 
	}

	/**
	 * Test for failure
	 */
	@Test
	void testSearchExistFailure() {
		m = new Member(123456789, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487, 120, true);
		assertNotEquals(true, memberManage.searchExist(m)); 
	}
	
	/**
	 * Test for failure
	 */
	@Test
	void testSearchIDFailure() {
		m = new Member(123456789, "John Smith", "jd@ua.edu", "100 University Rd", "Tuscaloosa", "Alabama", 35411, 120, true);
		assertNotEquals(m, memberManage.searchID(m.getID())); 
	}
	
	/**
	 * Test for success
	 */
	@Test
	void testSearchIDSuccess() {
		m = new Member(100000001, "John Dow", "jd@ua.edu", "100 University Rd", "Tuscaloosa", "AL", 35411, 0, false);
		assertEquals(m.toString(), memberManage.searchID(m.getID()).toString()); 
	}
	
	/**
	 * Test for success
	 */
	@Test
	void testSearchExistSuccess() {
		m = new Member(100000001, "John Dow", "jd@ua.edu", "100 University Rd", "Tuscaloosa", "AL", 35411, 0, false);
		assertEquals(true, memberManage.searchExist(m)); 
	}

}
