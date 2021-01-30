package Entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entity.Member;

/**
 * This class is a unit test for member in Entity class. I will test 4 function in the class.
 * @author Yichen Huang
 *
 */
class MemberTest {

	Member temp;
	@BeforeEach
	void setUp() throws Exception {
		
	}

	/**
	 * Test for failure
	 */
	@Test
	void testGetBalance() {
		temp = new Member(123456789, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487, 120, true);
		assertNotEquals(150,temp.getBalance());
	}
	
	/**
	 * Test for success
	 */
	@Test
	void testSetBalance() {
		temp = new Member(123456789, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487, 120, true);
		temp.setBalance(200);
		assertEquals(200,temp.getBalance());
	}

	/**
	 * Test for failure
	 */
	@Test
	void testIsValidMember() {
		temp = new Member(123456789, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487, 120, true);
		assertNotEquals(false, temp.isValidMember());
	}

	/**
	 * Test for success
	 */
	@Test
	void testSetValidMember() {
		temp = new Member(123456789, "John Smith", "123@gmail.com", "1831 University station", "Tuscaloosa", "Alabama", 35487, 120, true);
		temp.setValidMember(false);
		assertEquals(false,temp.isValidMember());
	}

}
