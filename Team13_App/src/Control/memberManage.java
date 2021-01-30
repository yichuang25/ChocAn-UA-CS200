package Control;

import java.util.ArrayList;
import Entity.Member;

/**
 * This member management class, a control class connect the operator interface with the entity class.
 * This class will let operator add, delete and edit the list of members.
 * @author Yichen Huang
 *
 */
public class memberManage {
	/**
	 * Store the list of the Member
	 */
	private static ArrayList<Member> Mlist = DataIO.GlobalData.getMemberList();
	
	/**
	 *  Search if the member exist
	 * @param temp
	 * @return true if exist and vice versa
	 */
	public static boolean searchExist(Member temp){
		if (Mlist.stream().filter(x -> 
									x.getName().equals(temp.getName())&& 
									x.getEmail().equals(temp.getEmail()) &&
									x.getAddress().equals(temp.getAddress()) &&
									x.getCity().equals(temp.getCity()) &&
									x.getState().equals(temp.getState()) &&
									x.getZipCode() == temp.getZipCode()
								).findAny().orElse(null) != null) {
			return true;
		}
		else 
			return false;
	}
	/**
	 * Search the member based on memberID from the list.
	 * @param temp MemberID to be searched.
	 * @return If find the Member return true else return null.
	 */
	public static Member searchID(int ID) {
		return Mlist.stream().filter(x -> x.getID() == ID).findAny().orElse(null);
	}
	
	/**
	 * Add the member to the list.
	 * @param temo The member to be added to the list.
	 * @return If add successfully return true, else return false.
	 */
	public static boolean addMember(Member temp) {
		if(searchExist(temp) == false){
			if (Mlist.add(temp) == true){
				return DataIO.GlobalData.setMemberList(Mlist);
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Delete the member from the list.
	 * @param temp The member to be deleted from the list.
	 * @return If deleted successfully return true else return false.
	 */
	public static boolean deleteMember(Member temp) {
		if (Mlist.remove(temp) == true){
			return DataIO.GlobalData.setMemberList(Mlist);
		}
		return false;
	}
	
	/**
	 * Edit the member in the list.
	 * @param temp The member to be edited
	 * @return If edit the member successfully return true else return false.
	 */
	public static boolean editMember(Member temp) {
		Member target = searchID(temp.getID());
		if (target == null){
			return false;
		}
		target.setEmail(temp.getEmail());
		target.setAddress(temp.getAddress());
		target.setCity(temp.getCity());
		target.setState(temp.getState());
		target.setZipCode(temp.getZipCode());
		target.setBalance(temp.getBalance());
		target.setValidMember(temp.isValidMember());
		

		return DataIO.GlobalData.setMemberList(Mlist);
	}
	
}