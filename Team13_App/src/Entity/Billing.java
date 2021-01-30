/**
 * 
 */
package Entity;

import java.util.Date;

/**
 * @author duter
 *
 */
public class Billing {
	private int BillingID; 
	private int ProviderID; 
	private int ServiceCode; 
	private int MemberID; 
	private String Comment; 
	private String CurrentDateTime;
	private String ServiceProvideDate;
	/**
	 * @param billingID
	 * @param providerID
	 * @param serviceCode
	 * @param memberID
	 * @param comment
	 * @param currentDateTime
	 * @param serviceProvideDate
	 */
	public Billing(int billingID, int providerID, int serviceCode, int memberID, String comment, String currentDateTime,
			String serviceProvideDate) {
		BillingID = billingID;
		ProviderID = providerID;
		ServiceCode = serviceCode;
		MemberID = memberID;
		Comment = comment;
		CurrentDateTime = currentDateTime;
		ServiceProvideDate = serviceProvideDate;
	}
	
	/**
	 * @return the billingID
	 */
	public int getBillingID() {
		return BillingID;
	}
	/**
	 * @param billingID the billingID to set
	 */
	public void setBillingID(int billingID) {
		BillingID = billingID;
	}
	/**
	 * @return the providerID
	 */
	public int getProviderID() {
		return ProviderID;
	}
	/**
	 * @param providerID the providerID to set
	 */
	public void setProviderID(int providerID) {
		ProviderID = providerID;
	}
	/**
	 * @return the serviceCode
	 */
	public int getServiceCode() {
		return ServiceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(int serviceCode) {
		ServiceCode = serviceCode;
	}
	/**
	 * @return the memberID
	 */
	public int getMemberID() {
		return MemberID;
	}
	/**
	 * @param memberID the memberID to set
	 */
	public void setMemberID(int memberID) {
		MemberID = memberID;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return Comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		Comment = comment;
	}
	/**
	 * @return the currentDateTime
	 */
	public String getCurrentDateTime() {
		return CurrentDateTime;
	}
	/**
	 * @param currentDateTime the currentDateTime to set
	 */
	public void setCurrentDateTime(String currentDateTime) {
		CurrentDateTime = currentDateTime;
	}
	/**
	 * @return the serviceProvideDate
	 */
	public String getServiceProvideDate() {
		return ServiceProvideDate;
	}
	/**
	 * @param serviceProvideDate the serviceProvideDate to set
	 */
	public void setServiceProvideDate(String serviceProvideDate) {
		ServiceProvideDate = serviceProvideDate;
	}

	@Override
	public String toString() {
		return "Billing [BillingID=" + BillingID + ", ProviderID=" + ProviderID + ", ServiceCode=" + ServiceCode
				+ ", MemberID=" + MemberID + ", Comment=" + Comment + ", CurrentDateTime=" + CurrentDateTime
				+ ", ServiceProvideDate=" + ServiceProvideDate + "]";
	} 
	
	
	
}
