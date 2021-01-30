/**
 * 
 */
package Entity;

/**
 * @author duter
 *
 */
public class User {
	private int ID; 
	private String Name; 
	private String Email; 
	private String Address; 
	private String City; 
	private String State; 
	private int ZipCode;
	/**
	 * @param iD
	 * @param name
	 * @param email
	 * @param address
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	public User(int iD, String name, String email, String address, String city, String state, int zipCode) {
		ID = iD;
		Name = name;
		Email = email;
		Address = address;
		City = city;
		State = state;
		ZipCode = zipCode;
	}
	/**
	 * @return the iD
	 */
	public int getID() {
		return ID;
	}
	/**
	 * @param iD the iD to set
	 */
	public void setID(int iD) {
		ID = iD;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return State;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		State = state;
	}
	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return ZipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		ZipCode = zipCode;
	}
	
}
