/**
 * 
 */
package Entity;

/**
 * @author duter
 *
 */
public class Provider extends User{

	private int WeeklyConsultationCount;
	private Login l; 

	/**
	 * @param iD
	 * @param name
	 * @param email
	 * @param address
	 * @param city
	 * @param state
	 * @param zipCode
	 */
	public Provider(int iD, String name, String email, String address, String city, String state, int zipCode) {
		super(iD, name, email, address, city, state, zipCode);
	}

	/**
	 * @return the weeklyConsultationCount
	 */
	public int getWeeklyConsultationCount() {
		return WeeklyConsultationCount;
	}

	/**
	 * @param weeklyConsultationCount the weeklyConsultationCount to set
	 */
	public void setWeeklyConsultationCount(int weeklyConsultationCount) {
		WeeklyConsultationCount = weeklyConsultationCount;
	}

	@Override
	public String toString() {
		return "Provider [ID=" + getID() + ", Name=" + getName() + ", Email=" + getEmail()
				+ ", Address=" + getAddress() + ", City=" + getCity() + ", State=" + getState()
				+ ", ZipCode=" + getZipCode() + "]";
	} 
	
}
