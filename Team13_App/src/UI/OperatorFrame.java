package UI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Control.memberManage;
import Control.providerManage;
import DataIO.GlobalData;
import Entity.Member;
import Entity.Provider;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTable;

public class OperatorFrame extends JFrame {

	private static JPanel contentPane;
	private static JTable table;
	private static JTable tableP;
	private static ArrayList<Member> MList = GlobalData.getMemberList(); 
	private static ArrayList<Provider> PList = GlobalData.getProviderList(); 
	private static DefaultTableModel modelP; 
	private static DefaultTableModel model;
	
	public static addMember addMem;
	public static editMember editMem;
	public static addProvider addPro;
	public static editProvider editPro;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					OperatorFrame frame = new OperatorFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public OperatorFrame() {
		setResizable(false);
		setTitle("ChocAn Operator");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1000, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(LaunchFrame.loginPage, "Are you sure you want to Logout?", "Logout?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					LaunchFrame.frmChocanLogin.setVisible(true);
					contentPane.setVisible(false);
					Login.operatorFr.dispose();
				}
			}
		});
		btnLogout.setBounds(901, 431, 83, 29);
		contentPane.add(btnLogout);
		
		JLabel logo = new JLabel(new ImageIcon(LaunchFrame.class.getResource("/images/chocanUnlabeled.png")));
		logo.setBounds(909, 345, 75, 75);
		getContentPane().add(logo);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(10, 11, 881, 449);
		contentPane.add(tabbedPane_1);
		
		JLayeredPane layeredPane = new JLayeredPane();
		tabbedPane_1.addTab("Member", null, layeredPane, null);
		
		JButton btnAddMember = new JButton("Add Member");
		btnAddMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addMem = new addMember();
				addMem.setVisible(true);
			}
		});
		btnAddMember.setBounds(10, 11, 111, 23);
		layeredPane.add(btnAddMember);
		
		JButton btnDeleteMember = new JButton("Delete Member");
		btnDeleteMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Member m = memberManage.searchID(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
				if (JOptionPane.showConfirmDialog(Login.operatorFr, 
			            "Are you sure you want to delete Member "+m.getName()+" ?", "Delete Member?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					memberManage.deleteMember(m); 
					refreashList("M");
				}
			}
		});
		btnDeleteMember.setBounds(628, 11, 117, 23);
		layeredPane.add(btnDeleteMember);
		
		JButton btnEditMember = new JButton("Edit Member");
		btnEditMember.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				Member temp = memberManage.searchID(Integer.parseInt(table.getModel().getValueAt(row, 0).toString()));
				editMem = new editMember(temp);
				editMem.setVisible(true);
			}
		});
		btnEditMember.setBounds(755, 11, 111, 23);
		layeredPane.add(btnEditMember);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 43, 856, 367);
		layeredPane.add(scrollPane_1);
		
		String col[] = {"ID", "Name", "Email", "Address", "City", "State", "Zip Code", "Balance", "Is Active"};
		model = new DefaultTableModel(null,col) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		table = new JTable(model);
		scrollPane_1.setViewportView(table);
		for(Member m : MList) model.addRow(new Object[] {m.getID(), m.getName(), m.getEmail(), m.getAddress(), m.getCity(), m.getState(), m.getZipCode(), m.getBalance(), m.isValidMember()});
		
		JLayeredPane layeredPane_1 = new JLayeredPane();
		tabbedPane_1.addTab("Provider", null, layeredPane_1, null);
		
		JButton btnAddProvider = new JButton("Add Provider");
		btnAddProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPro = new addProvider();
				addPro.setVisible(true);
			}
		});
		btnAddProvider.setBounds(10, 11, 103, 23);
		layeredPane_1.add(btnAddProvider);
		
		JButton btnDeleteProvider = new JButton("Delete Provider");
		btnDeleteProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableP.getSelectedRow();
				Provider p = providerManage.searchID(Integer.parseInt(tableP.getModel().getValueAt(row, 0).toString()));
				if (JOptionPane.showConfirmDialog(Login.operatorFr, 
			            "Are you sure you want to delete Provider "+p.getName()+" ?", "Delete Provider?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					providerManage.deleteProvider(p); 
					refreashList("P");
				}
			}
		});
		btnDeleteProvider.setBounds(622, 11, 117, 23);
		layeredPane_1.add(btnDeleteProvider);
		
		JButton btnEditProvider = new JButton("Edit Provider");
		btnEditProvider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = tableP.getSelectedRow();
				Provider p = providerManage.searchID(Integer.parseInt(tableP.getModel().getValueAt(row, 0).toString()));
				editPro = new editProvider(p);
				editPro.setVisible(true);
			}
		});
		btnEditProvider.setBounds(749, 11, 117, 23);
		layeredPane_1.add(btnEditProvider);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 43, 856, 367);
		layeredPane_1.add(scrollPane);
		
		String colP[] = {"ID", "Name", "Email", "Address", "City", "State", "Zip Code"};
		modelP = new DefaultTableModel(null,colP){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tableP = new JTable(modelP);
		scrollPane.setViewportView(tableP);
		for(Provider p : PList) modelP.addRow(new Object[] {p.getID(), p.getName(), p.getEmail(), p.getAddress(), p.getCity(), p.getState(), p.getZipCode()});
	}
	
	/**
	 * @apiNote This method will update table in the UI
	 * @param A string that indicate which table to refresh 
	 */
	public static void refreashList(String MP) {
		switch (MP){
			case "M":
				model.setRowCount(0);
				MList = GlobalData.getMemberList();
				for(Member m : MList) model.addRow(new Object[] {m.getID(), m.getName(), m.getEmail(), m.getAddress(), m.getCity(), m.getState(), m.getZipCode(), m.getBalance(), m.isValidMember()});
				table.revalidate();
				break; 
			case "P":
				modelP.setRowCount(0);
				PList = GlobalData.getProviderList();
				for(Provider p : PList) modelP.addRow(new Object[] {p.getID(), p.getName(), p.getEmail(), p.getAddress(), p.getCity(), p.getState(), p.getZipCode()});
				tableP.revalidate();
				break; 
			default:
				return; 
		}
	}
}
