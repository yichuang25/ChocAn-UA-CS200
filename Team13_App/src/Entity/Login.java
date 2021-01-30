/**
 * 
 */
package Entity;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author duter
 *
 */
public class Login {
	private String UserName; 
	private String Passworld; 
	private String LastLogin; 
	private String Role;
	/**
	 * @param userName
	 * @param passworld
	 * @param lastLogin
	 * @param role
	 */
	public Login(String userName, String passworld, String lastLogin, String role) {
		UserName = userName;
		Passworld = passworld;
		LastLogin = lastLogin;
		Role = role;
	}
	
	
	/**
	 * @param userName
	 * @param passworld
	 * @param role
	 */
	public Login(String userName, String passworld, String role) {
		UserName = userName;
		Passworld = passworld;
		Role = role;
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	    LastLogin = sdf.format(timestamp);
	}


	/**
	 * @param userName
	 * @param passworld
	 */
	public Login(String userName, String passworld) {
		UserName = userName;
		Passworld = passworld;
	}


	/**
	 * @return the userName
	 */
	public String getUserName() {
		return UserName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}
	/**
	 * @return the passworld
	 */
	public String getPassworld() {
		return Passworld;
	}
	/**
	 * @param passworld the passworld to set
	 */
	public void setPassworld(String passworld) {
		Passworld = passworld;
	}
	/**
	 * @return the lastLogin
	 */
	public String getLastLogin() {
		return LastLogin;
	}
	/**
	 * @param lastLogin the lastLogin to set
	 */
	public void setLastLogin(String lastLogin) {
		LastLogin = lastLogin;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return Role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		Role = role;
	}
	@Override
	public String toString() {
		return "Login [UserName=" + UserName + ", Passworld=" + Passworld + ", LastLogin=" + LastLogin + ", Role="
				+ Role + "]";
	}
	
	
}
