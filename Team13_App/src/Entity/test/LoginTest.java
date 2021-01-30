package Entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entity.Login;

/**
 * 
 * @author Yichen Huang
 *
 */
class LoginTest {
	
	Login temp;
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test for failure
	 */
	@Test
	void testGetUserName() {
		temp = new Login("user1","pass123");
		assertNotEquals("user2",temp.getUserName());
	}

	/**
	 * Test for success
	 */
	@Test
	void testSetUserName() {
		temp = new Login("user1","pass123");
		temp.setUserName("user2");
		assertEquals("user2",temp.getUserName());
		
	}

	/**
	 * Test for failure
	 */
	@Test
	void testGetPassworld() {
		temp = new Login("user1","pass123");
		assertNotEquals("password",temp.getPassworld());
	}

	/**
	 * Test for success
	 */
	@Test
	void testSetPassworld() {
		temp = new Login("user1","pass123");
		temp.setPassworld("pass1234");
		assertEquals("pass1234",temp.getPassworld());
	}

}
