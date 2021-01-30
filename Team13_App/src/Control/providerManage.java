package Control;

import java.util.ArrayList;

import DataIO.GlobalData;
import Entity.Login;
import Entity.Provider;

/**
 * The provider management class, a control class connect the Operator.java with the entity class.
 * This class will let operator add, delete and edit the list of providers.
 * @author Yichen Huang
 *
 */

public class providerManage {
	/**
	 *  Store the ArrayList of the providers.
	 */
	private static ArrayList<Provider> Plist = DataIO.GlobalData.getProviderList();
	
	/**
	 *  	Store the ArrayList of the providers.
	 */
	private static ArrayList<Login> Llist = DataIO.GlobalData.getLoginList();
	
	/**
	 *  Search the provider existence
	 *  Check name fist then check others except ID
	 * @param temp The provider to search
	 * @return false if not exist
	 */
	public static boolean searchExist(Provider temp) {
		if (Plist.stream().filter(x -> 
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
	 * Search the provider in the login list
	 * @param ID The ID to be searched
	 * @return The Login from the list
	 */
	public static Login searchLogin(int ID) {
		String username = Integer.toString(ID);
		return Llist.stream().filter(x -> x.getUserName().equals(username)).findAny().orElse(null);
		
	}
	
	/**
	 *  Search provider based on ID in the list.
	 * @param temp The provider to search
	 * @return If find the provider return the provider, otherwise return null.
	 */
	public static Provider searchID(int ID) {
		return Plist.stream().filter(x -> x.getID() == ID).findAny().orElse(null);

	}
	
	/**
	 *  Add new provider to the list
	 * @param temp The provider to created
	 * @param password The setting password
	 * @return true if success
	 */
	public static boolean addProvider(Provider temp, String password) {
		if (password == null) {
			return false;
		}
		if(searchExist(temp) == false){
			if (Plist.add(temp) == true){
				String username = Integer.toString(temp.getID());
				Login cur = new Login(username,password,"p");
				if(Llist.add(cur) == true) {
					if (DataIO.GlobalData.setLoginList(Llist) == true) {
						return DataIO.GlobalData.setProviderList(Plist);
					}
					else {
						return false;
					}
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	/**
	 * Delete the provider from the list
	 * @param temp The provider to delete
	 * @return If delete successfully return true, else return false.
	 */
	public static boolean deleteProvider(Provider temp) {
		Login target = searchLogin(temp.getID());
		if (target == null) {
			return false;
		}
		if (Llist.remove(target) == false) {
			return false;
		}
		if (DataIO.GlobalData.setLoginList(Llist) == false) {
			return false;
		}
		if (Plist.remove(temp) == true){
			return DataIO.GlobalData.setProviderList(Plist);
		}
		return false;
	}
	
	/**
	 * Edit the provider in the list.
	 * @param temp The provider to be edited.
	 * @return If edit successfully return true, otherwise return false.
	 */
	public static boolean editProvider(Provider temp) {
		Provider target = searchID(temp.getID());
		if (target == null){
			return false;
		}
		target.setEmail(temp.getEmail());
		target.setAddress(temp.getAddress());
		target.setCity(temp.getCity());
		target.setState(temp.getState());
		target.setZipCode(temp.getZipCode());

		return DataIO.GlobalData.setProviderList(Plist);
	}
	
	
}