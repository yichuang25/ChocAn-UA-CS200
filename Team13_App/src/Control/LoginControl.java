package Control;

import java.util.ArrayList;
import DataIO.GlobalData;
import Entity.Login;

public class LoginControl {

	/**
	 * @apiNote This method will check all parameters against the login data stored in the database
	 * @param UserName
	 * @param Password
	 * @param role
	 * @return
	 */
	public static boolean loginAction(String userN, String pass, String role) {
		ArrayList<Login> LList = GlobalData.getLoginList();
		if(LList != null) {
			Login tryLogin = LList.stream().filter(x -> x.getUserName().equals(userN) && x.getPassworld().equals(pass) && x.getRole().equals(role)).findAny().orElse(null); 
			if(tryLogin != null) {
				
				LList.remove(LList.stream().filter(x -> x.getUserName().equals(userN)).findAny().orElse(null)); 
				LList.add(new Login(userN,pass,role));
				
				return GlobalData.setLoginList(LList);
			}
		}else System.out.println("Login table is empty");
		
		return false; 
	}

}
