/**
 * 
 */
package Control.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Control.providerControl;
import DataIO.GlobalData;
import Entity.Billing;
import Entity.Member;

/**
 * @author dylan
 *
 */
class providerControlTest {
	GlobalData gd;
	Member test;
	

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		gd=new GlobalData();
	}

	/**
	 * Test method for {@link Control.providerControl#checkStatus(int)}. Success
	 */
	@Test
	void testCheckStatusSuccess() {
		assertEquals(0, providerControl.checkStatus(100000002)); 
	}

	/**
	 * Test method for {@link Control.providerControl#checkStatus(int)}. Success
	 */
	@Test
	void testCheckStatusFailure() {
		assertNotEquals(0, providerControl.checkStatus(100000001)); 
	}
	/**
	 * Test method for {@link Control.providerControl#billMember(int, int, int, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	void testBillMemberSuccess() {
		assertNotNull(providerControl.billMember(200000001, 100001, 100000002, "test", "12/6/2019", "12/6/2019")); 
		
	}
	
	void testBillMemberFailure() {
		assertNull(providerControl.billMember(200000001, 100001, 100000001, "test", "12/6/2019", "12/6/2019")); 
	
	}


}
