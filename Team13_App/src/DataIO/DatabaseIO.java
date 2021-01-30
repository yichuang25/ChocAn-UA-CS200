package DataIO; 

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Entity.Billing;
import Entity.Login;
import Entity.Member;
import Entity.Provider;
import Entity.Service;

/**
 * 
 * @author duter
 *
 */
public class DatabaseIO{
    
	
	protected static ArrayList<Provider> PList; 
    
    protected static ArrayList<Member> MList; 
    
    protected static ArrayList<Login> LList; 
    
    protected static ArrayList<Service> SList; 
    
    protected static ArrayList<Billing> BList; 
	
    protected static Connection conn;
    
    
    /**
     * @apiNote This method will use a relative path to locate the ChocAn database 
     * and create a connection object that connects to the database. 
     * @return a Connection {@link Connection object} that connects to the ChocAn database
     */
    public static Connection connect() {
    	
    	//db parameters
    	Path currentRelativePath = Paths.get("");
    	String s = currentRelativePath.toAbsolutePath().toString();
    	String url = "jdbc:sqlite:"+s+"/src/DataIO/ChocAnDB.db";
    	
    	System.out.println("Database Location: \n"+url);
    	
		Connection conn = null;
		
        try {
            
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to ChocAn database has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
        
        if (conn == null) {
            return null; 
        }
        return conn; 
	}
    
    /**
     * @apiNote this method will close the current established connection 
     * @return boolean that indicate if the connection is closed
     */
    protected static boolean disconnect() {
    	
    	try {
    		if(conn.isClosed()) return true; 
    		else{
    			conn.close();
    			return true; 
    		}
    	}catch(Exception ex) {
    		System.out.println(ex.toString());
    	}
    	
    	return false; 
    }
    
    /**
     * @apiNote the DatabaseIO class constructor that establish a connection to the database and populate 
     * all the entity lists
     */
    public DatabaseIO() {
    	conn = connect(); 
    	
    	LList = selectAllLogin();
    	MList = selectAllMember(); 
    	PList = selectAllProvider(); 
    	SList = selectAllService();
    	BList = selectAllBilling();
    	
    	
    	System.out.println("All Data Loaded"); 
    }
    
    
    
    /**
     * 
     * @return Select all record from Login table and return a {@link ArrayList login array list}
     */
	protected static ArrayList<Login> selectAllLogin(){
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
        String sql = "SELECT * FROM Login;";
        
        ArrayList<Login> temp = new ArrayList<Login>(); 
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) 
            	temp.add(
            			new Login(
            					rs.getString(1), 
            					rs.getString(2), 
            					rs.getString(3), 
            					rs.getString(4)
            			)
            	);
            
            //for(Login l : temp) System.out.println(l.getUserName()+" "+l.getPassworld()+" "+l.getLastLogin()+" "+l.getRole());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp; 
    }
	
	/**
	 * 
	 * @return Select all record from Member table and return a {@link ArrayList member array list}
	 */
	protected static ArrayList<Member> selectAllMember(){
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
        String sql = "SELECT * FROM Member;";
        
        ArrayList<Member> temp = new ArrayList<Member>(); 
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) 
            	temp.add(
            			new Member(
            					rs.getInt(1), 
            					rs.getString(2), 
            					rs.getString(3), 
            					rs.getString(4), 
            					rs.getString(5),
            					rs.getString(6),
            					rs.getInt(7),
            					rs.getDouble(8),
            					rs.getBoolean(9)
            			)
            	);
            
            //for(Member m : temp) System.out.println(m.getID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp; 
    }
	
	/**
	 * 
	 * @return Select all record from Provider table and return a {@link ArrayList provider array list}
	 */
	protected static ArrayList<Provider> selectAllProvider(){
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		String sql = "SELECT * FROM Provider;";
        
        ArrayList<Provider> temp = new ArrayList<Provider>(); 
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) 
            	temp.add(
            			new Provider(
            					rs.getInt(1), 
            					rs.getString(2), 
            					rs.getString(3), 
            					rs.getString(4), 
            					rs.getString(5),
            					rs.getString(6),
            					rs.getInt(7)
            			)
            	);
            
            //for(Provider m : temp) System.out.println(m.getID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp; 
	}
	
	/**
	 * 
	 * @return Select all record from Service table and return a {@link ArrayList service array list}
	 */
	protected static ArrayList<Service> selectAllService(){
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		String sql = "SELECT * FROM Service;";
        
        ArrayList<Service> temp = new ArrayList<Service>(); 
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) 
            	temp.add(
            			new Service(
            					rs.getInt(1), 
            					rs.getString(2), 
            					rs.getDouble(3)
            			)
            	);
            
            //for(Service m : temp) System.out.println(m.getServiceCode());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp; 
	}
	
	/**
	 * 
	 * @return Select all record from Billing table and return a {@link ArrayList billing array list}
	 */
	protected static ArrayList<Billing> selectAllBilling(){
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
		String sql = "SELECT * FROM Billing;";
        
        ArrayList<Billing> temp = new ArrayList<Billing>(); 
        
        try (
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) 
            	temp.add(
            			new Billing(
            					rs.getInt(1), 
            					rs.getInt(2), 
            					rs.getInt(3), 
            					rs.getInt(4), 
            					rs.getString(5),
            					rs.getString(6),
            					rs.getString(7)
            			)
            	);
            
            //for(Billing m : temp) System.out.println(m.getBillingID());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return temp; 
	}
	
	/**
	 * 
	 * @param providerList
	 * @return boolean to indicate if the list had successfully store to the table
	 * @apiNote This method will erase all data from the table and try to insert new data to the table from
	 * an arraylist, if the insertion failed the data will be restored. 
	 */
	protected static boolean UpdateProvidertable(ArrayList<Provider> providerList) {
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		ArrayList<Provider> temp = PList; 
		
		String deleteData = "DELETE FROM PROVIDER WHERE providerid IS NOT NULL;";
		try {
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate(deleteData); 
			for(Provider p : providerList) {
				stmt.executeUpdate("insert into Provider (ProviderID, ProviderName, ProviderEmail, ProviderAddress, ProviderCity, ProviderState, ProviderZipCode) "
						+ "VALUES ("
						+ p.getID() +", '"
						+ p.getName()+"', '"
						+ p.getEmail()+"', '"
						+ p.getAddress()+"', '"
						+ p.getCity()+"', '"
						+ p.getState()+"', "
						+ p.getZipCode()
						+ ")");
			}
			System.out.println("Provider table updated");
			return true; 
			
		}catch(SQLException ex) {
			UpdateProvidertable(temp); 
			System.out.println(ex.toString()); 
		}
		return false;
	}
	
	/**
	 * 
	 * @param memberList
	 * @return boolean to indicate if the list had successfully store to the table
	 * @apiNote This method will erase all data from the table and try to insert new data to the table from
	 * an arraylist, if the insertion failed the data will be restored. 
	 */
	protected static boolean UpdateMembertable(ArrayList<Member> memberList) {
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		ArrayList<Member> temp = MList; 
		
		String deleteData = "DELETE FROM Member WHERE memberid IS NOT NULL;";
		try {
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate(deleteData); 
			for(Member p : memberList) {
				stmt.executeUpdate("INSERT into Member (memberid, MemberName, MemberEmail, MemberAddress, MemberCity, MemberState, MemberZipCode, Memberbalance, ValidMember) "
						+ "VALUES ("
						+ p.getID() +", '"
						+ p.getName()+"', '"
						+ p.getEmail()+"', '"
						+ p.getAddress()+"', '"
						+ p.getCity()+"', '"
						+ p.getState()+"', "
						+ p.getZipCode()+", "
						+ p.getBalance()+", "
						+ p.isValidMember()
						+ ")");
			}
			System.out.println("Member table updated");
			return true; 
			
		}catch(SQLException ex) {
			UpdateMembertable(temp);
			System.out.println(ex.toString()); 
		}
		return false;
	}
	
	/**
	 * 
	 * @param loginList
	 * @return boolean to indicate if the list had successfully store to the table
	 * @apiNote This method will erase all data from the table and try to insert new data to the table from
	 * an arraylist, if the insertion failed the data will be restored. 
	 */
	protected static boolean UpdateLogintable(ArrayList<Login> loginList) {
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		ArrayList<Login> temp = LList; 
		
		String deleteData = "DELETE FROM login WHERE username IS NOT NULL;";
		try {
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate(deleteData); 
			for(Login p : loginList) {
				stmt.executeUpdate("INSERT into Login (username, password, lastlogin, role) "
						+ "VALUES ('"
						+ p.getUserName() +"', '"
						+ p.getPassworld()+"', '"
						+ p.getLastLogin()+"', '"
						+ p.getRole()
						+ "')");
			}
			System.out.println("Login table updated");
			return true; 
			
		}catch(SQLException ex) {
			UpdateLogintable(temp);
			System.out.println(ex.toString()); 
		}
		return false;
	}
	
	/**
	 * 
	 * @param serviceList
	 * @return boolean to indicate if the list had successfully store to the table
	 * @apiNote This method will erase all data from the table and try to insert new data to the table from
	 * an arraylist, if the insertion failed the data will be restored. 
	 */
	protected static boolean UpdateServicetable(ArrayList<Service> serviceList) {
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		ArrayList<Service> temp = SList; 
		
		String deleteData = "DELETE FROM Service WHERE servicecode IS NOT NULL;";
		try {
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate(deleteData); 
			for(Service p : serviceList) {
				stmt.executeUpdate("INSERT into Service (ServiceCode, ServiceName, ServicePrice) "
						+ "VALUES ("
						+ p.getServiceCode() +", '"
						+ p.getServiceName() +"', "
						+ p.getServicePrice()
						+ ")");
			}
			System.out.println("Service table updated");
			return true; 
			
		}catch(SQLException ex) {
			UpdateServicetable(temp);
			System.out.println(ex.toString()); 
		}
		return false;
	}
	
	/**
	 * 
	 * @param billingList
	 * @return boolean to indicate if the list had successfully store to the table
	 * @apiNote This method will erase all data from the table and try to insert new data to the table from
	 * an arraylist, if the insertion failed the data will be restored. 
	 */
	protected static boolean UpdateBillingtable(ArrayList<Billing> billingList) {
		
		try {
			if(conn.isClosed()) conn = connect(); 
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		ArrayList<Billing> temp = BList; 
		
		String deleteData = "DELETE FROM Billing WHERE billingid IS NOT NULL;";
		try {
			Statement stmt = conn.createStatement(); 
			stmt.executeUpdate(deleteData); 
			for(Billing p : billingList) {
				stmt.executeUpdate("INSERT into Billing (billingid, providerid, servicecode, memberid, comment, serviceprividedate, currentdatetime) "
						+ "VALUES ("
						+ p.getBillingID() +", "
						+ p.getProviderID()+", "
						+ p.getServiceCode()+", "
						+ p.getMemberID()+", '"
						+ p.getComment()+"', '"
						+ p.getServiceProvideDate()+"', '"
						+ p.getCurrentDateTime()
						+ "')");
			}
			System.out.println("Billing table updated");
			return true; 
			
		}catch(Exception ex) {
			UpdateBillingtable(temp);
			System.out.println(ex.toString()); 
		}
		return false;
	}
	
}