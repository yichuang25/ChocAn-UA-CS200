package DataIO; 

import java.util.ArrayList;

import Entity.Billing;
import Entity.Login;
import Entity.Member;
import Entity.Provider;
import Entity.Service; 

public class GlobalData extends DatabaseIO{

	private static ArrayList<Provider> ProviderList; 
    
    private static ArrayList<Member> MemberList; 
    
    private static ArrayList<Login> LoginList; 
    
    private static ArrayList<Service> ServiceList; 
    
    private static ArrayList<Billing> BillingList; 
    

    /**
     * @apiNote Construct the Global Data class, this will establish the database connection 
     * and parse all data from table to array lists, these array lists will only be accessible 
     * through getters and setters. 
     */
    public GlobalData() {
    	super();
    	
    	ProviderList = PList; 
    	MemberList = MList; 
    	LoginList = LList; 
    	ServiceList = SList; 
    	BillingList = BList; 
    	
    }
    
    /**
     * @apiNote Does nothing but print out all data on all table 
     */
    public void PrintAllTable() {
    	System.out.println("\nAll Table and Data\n");
    	System.out.println("+++++++++++++++++++++++ Login +++++++++++++++++++++++");
    	for(Login l : LList) System.out.println(l.toString());
    	System.out.println("*****************************************************\n");
    	System.out.println("+++++++++++++++++++++++ Member +++++++++++++++++++++++");
    	for(Member m : MList) System.out.println(m.toString());
    	System.out.println("*****************************************************\n");
    	System.out.println("+++++++++++++++++++++++ Provider +++++++++++++++++++++++");
    	for(Provider p : PList) System.out.println(p.toString());
    	System.out.println("*****************************************************\n");
    	System.out.println("+++++++++++++++++++++++ Service +++++++++++++++++++++++");
    	for(Service s : SList) System.out.println(s.toString());
    	System.out.println("*****************************************************\n");
    	System.out.println("+++++++++++++++++++++++ Billing +++++++++++++++++++++++");
    	for(Billing b : BList) System.out.println(b.toString());
    	System.out.println("*****************************************************");
    }

	/**
	 * @return the providerList
	 */
	public static ArrayList<Provider> getProviderList() {
		if(!ProviderList.equals(PList)) {
			PList = selectAllProvider(); 
			ProviderList = PList; 
		}
		return ProviderList;
	}

	/**
	 * @param providerList the providerList to set
	 * @return bool to indicate if data is inserted to the database
	 */
	public static boolean setProviderList(ArrayList<Provider> providerList){
		//ProviderList = providerList;
		
		for(Provider p : providerList) {
			if(!PList.contains(p)) {
				Provider pv = PList.stream().filter(x -> x.getID() == p.getID()).findAny().orElse(null);
				if(pv != null) {
					return false; 
				}
			}
		}
		
		if(UpdateProvidertable(providerList)) {
			ProviderList = PList;
			return true; 
		}
		return false; 
	}

	/**
	 * @return the memberList
	 */
	public static ArrayList<Member> getMemberList() {
		if(!MemberList.equals(MList)) {
			MList = selectAllMember(); 
			MemberList = MList; 
		}
		return MemberList;
	}

	/**
	 * @param memberList the memberList to set
	 * @return bool to indicate if data is inserted to the database
	 */
	public static boolean setMemberList(ArrayList<Member> memberList) {
		for(Member m : memberList) {
			if(!MList.contains(m)) {
				Member mb = MList.stream().filter(x -> x.getID() == m.getID()).findAny().orElse(null);
				if(mb != null) {
					return false; 
				}
			}
		}
		
		if(UpdateMembertable(memberList)) {
			MemberList = MList;
			return true; 
		}
		return false; 
	}

	/**
	 * @return the loginList
	 */
	public static ArrayList<Login> getLoginList() {
		if(!LoginList.equals(LList)) {
			LList = selectAllLogin(); 
			LoginList = LList; 
		}
		return LoginList;
	}

	/**
	 * @param loginList the loginList to set
	 * @return bool to indicate if data is inserted to the database
	 */
	public static boolean setLoginList(ArrayList<Login> loginList) {
		for(Login l : loginList) {
			if(!LList.contains(l)) {
				Login li = LList.stream().filter(x -> x.getUserName() == l.getUserName()).findAny().orElse(null);
				if(li != null) {
					return false; 
				}
			}
		}
		
		if(UpdateLogintable(loginList)) {
			LoginList = LList;
			return true; 
		}
		return false; 
	}

	/**
	 * @return the serviceList
	 */
	public static ArrayList<Service> getServiceList() {
		if(!ServiceList.equals(SList)) {
			SList = selectAllService(); 
			ServiceList = SList; 
		}
		return ServiceList;
	}

	/**
	 * @param serviceList the serviceList to set
	 * @return bool to indicate if data is inserted to the database
	 */
	public static boolean setServiceList(ArrayList<Service> serviceList) {
		for(Service s : serviceList) {
			if(!SList.contains(s)) {
				Service se = SList.stream().filter(x -> x.getServiceCode() == s.getServiceCode()).findAny().orElse(null);
				if(se != null) {
					return false; 
				}
			}
		}
		
		if(UpdateServicetable(serviceList)) {
			ServiceList = SList;
			return true; 
		}
		return false; 
	}

	/**
	 * @return the billingList
	 */
	public static ArrayList<Billing> getBillingList() {
		if(!BillingList.equals(BList)) {
			BList = selectAllBilling(); 
			BillingList = BList; 
		}
		return BillingList;
	}

	/**
	 * @param billingList the billingList to set
	 * @return bool to indicate if data is inserted to the database
	 */
	public static boolean setBillingList(ArrayList<Billing> billingList) {
		for(Billing b : billingList) {
			if(!BList.contains(b)) {
				Billing bi = BList.stream().filter(x -> x.getBillingID() == b.getBillingID()).findAny().orElse(null);
				if(bi != null) {
					return false; 
				}
			}
		}
		
		if(UpdateBillingtable(billingList)) {
			BillingList = BList;
			return true; 
		}
		return false; 
	}
    
	/**
	 * @apiNote This method takes all the array list in this class 
	 * and call all corresponding database table update methods. 
	 * @return bool to indicate if all tables are updated
	 */
	public boolean StoreAllData(){
		boolean Ptable = UpdateProvidertable(ProviderList); 
		boolean Mtable = UpdateMembertable(MemberList); 
		boolean Ltable = UpdateLogintable(LoginList); 
		boolean Stable = UpdateServicetable(ServiceList);
		boolean Btable = UpdateBillingtable(BillingList); 
		
		return (Ptable && Mtable && Ltable && Stable && Btable); 
	}
	
	
	/**
	 * @apiNote This method disconnect the current connection, do not use it unless you are very sure
	 * @return bool to indicate if database is successful disconnected 
	 */
	public static boolean disconnectDatabase() {
		return disconnect();
	}
	
    
}