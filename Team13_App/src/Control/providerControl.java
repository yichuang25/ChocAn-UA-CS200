package Control;

import java.util.ArrayList;
import java.util.Collections;

import Entity.Billing;
import Entity.Member;
import Entity.Provider;
import Entity.Service;

/**
 * This control class can let provider check member status, add bill record and return provider directory
 * @author Yichen Huang
 *
 */
public class providerControl {
	
	/**
	 * Store the Member list
	 */
	private static ArrayList<Member> Mlist = DataIO.GlobalData.getMemberList();
	
	/**
	 * Store the Billing list
	 */
	private static ArrayList<Billing> Blist = DataIO.GlobalData.getBillingList();
	
	
	/**
	 * This function is to check the member status
	 * @param ID is Member ID
	 * @return if Valid return 0, if invalid return 1, if not exist return 2
	 */
	public static int checkStatus(int ID) {
		Member target = Mlist.stream().filter(x -> x.getID() == ID).findAny().orElse(null);
		if(target == null) {
			return 2;
		}
		else {
			if(target.isValidMember()==true) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}
	
	/**
	 * This class is to create a new bill record
	 * @param providerID 
	 * @param serviceCode
	 * @param memberID
	 * @param comment
	 * @param currentDate
	 * @param providedDate
	 * @return True if success
	 */
	public static Billing billMember(int providerID, int serviceCode, int memberID, String comment, String currentDate, String providedDate) {
		int billingID;
		if (Blist == null) {
			billingID = 300000000;
		}
		else {
			int size = Blist.size()-1;
			billingID = Blist.get(size).getBillingID() + 1;
			
		}
		Billing cur = new Billing(billingID, providerID, serviceCode, memberID, comment, currentDate, providedDate);
		return cur;
		
	}
	
	
}