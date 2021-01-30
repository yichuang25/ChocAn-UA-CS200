package Entity;

public class Member extends User {
	
	private double Balance; 
	private boolean ValidMember;
	
	/**
	 * @param iD
	 * @param name
	 * @param email
	 * @param address
	 * @param city
	 * @param state
	 * @param zipCode
	 * @param balance
	 * @param validMember
	 */
	public Member(int iD, String name, String email, String address, String city, String state, int zipCode,
			double balance, boolean validMember) {
		super(iD, name, email, address, city, state, zipCode);
		Balance = balance;
		ValidMember = validMember;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return Balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		Balance = balance;
	}

	/**
	 * @return the validMember
	 */
	public boolean isValidMember() {
		return ValidMember;
	}

	/**
	 * @param validMember the validMember to set
	 */
	public void setValidMember(boolean validMember) {
		ValidMember = validMember;
	}

	@Override
	public String toString() {
		return "Member [ID=" + getID() + ", Name="
				+ getName() + ", Email=" + getEmail() + ", Address=" + getAddress() + ", City="
				+ getCity() + ", State=" + getState() + ", ZipCode=" + getZipCode() + ", Balance=" 
				+ Balance + ", ValidMember=" + ValidMember + "]";
	}
	
}
