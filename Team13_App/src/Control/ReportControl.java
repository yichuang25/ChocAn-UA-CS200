/**
 * 
 */
package Control;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

import DataIO.GlobalData;
import Entity.Billing;
import Entity.Member;
import Entity.Provider;
import Entity.Service;

/**
 * @author duter
 *
 */
public class ReportControl {

	private static ArrayList<Billing> BList = GlobalData.getBillingList();
	private static ArrayList<Provider> PList = GlobalData.getProviderList();
	private static ArrayList<Member> MList = GlobalData.getMemberList();
	private static ArrayList<Service> SList = GlobalData.getServiceList();
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY_MM_dd_hh_mm_ss");
	
	private static void FilterBList() {
	}
	
	/**
	 * @apiNote A driver method that makes report based on parameter 
	 * @param A String variable to indicate which report type to generate (Provider, Member, Manager, All)
	 * @throws IOException
	 */
	public static void MakeReport(String type) throws IOException{
		
		if (BList == null) BList = GlobalData.getBillingList();
		if (PList == null) PList = GlobalData.getProviderList();
		if (MList == null) MList = GlobalData.getMemberList();
		if (SList == null) SList = GlobalData.getServiceList();
		FilterBList();
		
		Path currentRelativePath = Paths.get("");
    	String s = currentRelativePath.toAbsolutePath().toString();
    	String dirString = s+"/Reports";
        Path dirPath = Paths.get(dirString);
        if (Files.notExists(dirPath)) Files.createDirectories(dirPath);
        else {
        	Files.walkFileTree(dirPath, new SimpleFileVisitor<Path>() {
        		   @Override
        		   public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        		       Files.delete(file);
        		       return FileVisitResult.CONTINUE;
        		   }

        		   @Override
        		   public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        		       Files.delete(dir);
        		       return FileVisitResult.CONTINUE;
        		   }
        		});
        	Files.createDirectories(dirPath);
        }
        
        switch (type) {
        	case "All":
        		providerReport(dirString);
        		memberReport(dirString);
        		managerReport(dirString);
        		open(new File(dirString));
        		break; 
        	case "Provider":
        		providerReport(dirString); 
        		open(new File(dirString.concat("/ProviderReports")));
        		break; 
        	case "Member":
        		memberReport(dirString);
        		open(new File(dirString.concat("/MemberReports")));
        		break; 
        	case "Manager": 
        		managerReport(dirString);
        		open(new File(dirString.concat("/ManagerReport")));
        		break; 
        	default: 
        		return; 
        }
	}
	
	/**
	 * @apiNote This method is tailored to print out formated provider reports
	 * @param A directory string that will be used while reports are generated 
	 * @throws IOException
	 */
	private static void providerReport(String dirString) throws IOException {
		dirString = dirString.concat("/ProviderReports");
		Path dirPath = Paths.get(dirString);
        if (Files.notExists(dirPath)) Files.createDirectories(dirPath);
		
		for(Provider p : PList) {
			
			ArrayList<Billing> PBList = new ArrayList<Billing>(); 
			for(Billing b : BList) if(b.getProviderID() == p.getID()) PBList.add(b);
			
			PBList.sort(Comparator.comparing(Billing::getCurrentDateTime));
			
			String fileString = p.getName() + "_" + LocalDateTime.now().format(formatter) +".txt";
	        Path filePath = Paths.get(dirString, fileString);
	        if (Files.notExists(filePath)) Files.createFile(filePath);
	        
	        File reportFile = filePath.toFile();
	        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportFile)));
	        
	        out.println("Provider Name: \t" + p.getName());
	        out.println("Provider Number: \t" + p.getID());
	        out.println();
	        out.println("Provider Street Address: \t" + p.getAddress());
	        out.println("Provider City: \t" + p.getCity());
	        out.println("Provider State: \t" + p.getState());
	        out.println("Provider Zip Code: \t" + p.getZipCode());
	        out.println("===============================================");
	        out.println("Service Provided: \n");
	        
	        double total = 0; 
	        int count = 0; 
	        
	        for(Billing bl : PBList) {
	        	Member m = MList.stream().filter(x -> x.getID() == bl.getMemberID()).findFirst().orElse(null); 
	        	Service s = SList.stream().filter(x -> x.getServiceCode() == bl.getServiceCode()).findFirst().orElse(null); 
	        	if(m == null || s == null) continue; 
	        	out.println("Date of Service: \t" + bl.getServiceProvideDate());
	        	out.println("Record Date Time: \t" + bl.getCurrentDateTime());
	        	out.println("Member Name: \t" + m.getName());
	        	out.println("Member Number: \t" + m.getID());
	        	out.println("Service Code: \t" + s.getServiceCode());
	        	out.println("Service Fee: \t" + s.getServicePrice());
	        	total += s.getServicePrice(); 
	        	out.println();
	        	count++; 
	        }
	        out.println("===============================================\n");
	        out.println("Total Count of Consultation: \t" + count);
	        out.println("Total Service Fee Earned: \t" + total);
	        out.close();
		}
		System.out.println("Provider reports generated");
	}
	
	/**
	 * @apiNote This method is tailored to print out formated member reports
	 * @param A directory string that will be used while reports are generated 
	 * @throws IOException
	 */
	private static void memberReport(String dirString) throws IOException{
		dirString = dirString.concat("/MemberReports");
		Path dirPath = Paths.get(dirString);
        if (Files.notExists(dirPath)) Files.createDirectories(dirPath);
		
		for (Member m : MList) {
			ArrayList<Billing> MBList = new ArrayList<Billing>(); 
			for(Billing b : BList) if(b.getMemberID() == m.getID()) MBList.add(b); 
			MBList.sort(Comparator.comparing(Billing::getServiceProvideDate));
			
			String fileString = m.getName() + "_" + LocalDateTime.now().format(formatter) +".txt";
	        Path filePath = Paths.get(dirString, fileString);
	        if (Files.notExists(filePath)) Files.createFile(filePath);
	        
	        File reportFile = filePath.toFile();
	        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportFile)));
	        
	        out.println("Member Name: \t" + m.getName());
	        out.println("Member Number: \t" + m.getID());
	        out.println();
	        out.println("Member Address: \t" + m.getAddress());
	        out.println("Member City: \t" + m.getCity());
	        out.println("Member State: \t" + m.getState());
	        out.println("Member Zip Code: \t" + m.getZipCode());
	        out.println("===============================================");
	        out.println("Service Received: \n");
	        
	        for(Billing bl : MBList) {
	        	Service s = SList.stream().filter(x -> x.getServiceCode() == bl.getServiceCode()).findFirst().orElse(null); 
	        	Provider p = PList.stream().filter(x -> x.getID() == bl.getProviderID()).findFirst().orElse(null);
	        	if(s == null || p == null) continue; 
	        	out.println("Date of Service: \t" + bl.getServiceProvideDate());
	        	out.println("Provider Name: \t" + p.getName()); 
	        	out.println("Service Name: \t" + s.getServiceName()); 
	        	out.println();
	        }
	        
	        out.println("===============================================");
	        out.close();
		}
		System.out.println("Member reports generated");
	}
	
	/**
	 * @apiNote This method is tailored to print out formated manager report
	 * @param A directory string that will be used while report is generated 
	 * @throws IOException
	 */
	private static void managerReport(String dirString) throws IOException{
		dirString = dirString.concat("/ManagerReport");
		Path dirPath = Paths.get(dirString);
        if (Files.notExists(dirPath)) Files.createDirectories(dirPath);
		
		String fileString = "Manager_Summmary_Report.txt";
        Path filePath = Paths.get(dirString, fileString);
        if (Files.notExists(filePath)) Files.createFile(filePath);
        
        File reportFile = filePath.toFile();
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportFile)));
        
        int providerCount = 0; 
        double totalPayable = 0; 
        
        out.println("SUMMARY REPORT -------- as of " + LocalDateTime.now().toString() + "\n");
        
		for(Provider p : PList) {
			double totalFee = 0; 
			int count = 0; 
			for(Billing bl : BList) {
				if(p.getID() == bl.getProviderID()) {
					Service s = SList.stream().filter(x -> x.getServiceCode() == bl.getServiceCode()).findFirst().orElse(null);
					if(s == null) continue; 
					count++; 
					totalFee += s.getServicePrice();
				}
			}
			if (count == 0) continue; 
			out.println("Provider Name: \t" + p.getName());
	        out.println("Provider Number: \t" + p.getID());
	        out.println("Service Provided Count: \t" + count);
	        out.println("Total Service Fee: \t" + totalFee);
	        out.println();
	        providerCount++; 
	        totalPayable += totalFee; 
		}
		
		out.println("================================================"); 
		out.println("Provider Count: \t" + providerCount);
		out.println("Total Account Payable: \t" + totalPayable);
		out.close();
		System.out.println("Summary report generated");
	}
	
	/**
	 * @apiNote This is a helper method that opens the folder from the given parameter 
	 * @param file directory 
	 * @return
	 */
	private static boolean open(File file) {
	    try{
	        if (OSDetector.isWindows()) {
	            Runtime.getRuntime().exec(new String[] {"rundll32", "url.dll,FileProtocolHandler", file.getAbsolutePath()});
	            return true;
	        } else if (OSDetector.isLinux() || OSDetector.isMac()) {
	            Runtime.getRuntime().exec(new String[]{"/usr/bin/open", file.getAbsolutePath()});
	            return true;
	        } else{
	            if (Desktop.isDesktopSupported()) {
	                Desktop.getDesktop().open(file);
	                return true;
	            }
	            else return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace(System.err);
	        return false;
	    }
	}
}