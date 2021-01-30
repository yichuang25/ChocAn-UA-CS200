/**
 * 
 */
package Entity;

/**
 * @author duter
 *
 */
public class Service {
	private int ServiceCode; 
	private String ServiceName; 
	private double ServicePrice;
	
	/**
	 * @param serviceCode
	 * @param serviceName
	 * @param servicePrice
	 */
	public Service(int serviceCode, String serviceName, double servicePrice) {
		ServiceCode = serviceCode;
		ServiceName = serviceName;
		ServicePrice = servicePrice;
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
	 * @return the serviceName
	 */
	public String getServiceName() {
		return ServiceName;
	}

	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		ServiceName = serviceName;
	}

	/**
	 * @return the servicePrice
	 */
	public double getServicePrice() {
		return ServicePrice;
	}

	/**
	 * @param servicePrice the servicePrice to set
	 */
	public void setServicePrice(double servicePrice) {
		ServicePrice = servicePrice;
	}

	@Override
	public String toString() {
		return "Service [ServiceCode=" + ServiceCode + ", ServiceName=" + ServiceName + ", ServicePrice=" + ServicePrice
				+ "]";
	} 
	
}
