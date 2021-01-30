package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataIO.GlobalData;
import Entity.Billing;
import Entity.Member;
import Entity.Service;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.Insets;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

/**
 * This is to confirm the new bill record to the provider
 * @author Yichen Huang
 *
 */

public class BillMember extends JFrame {
	private static ArrayList<Billing> Blist = GlobalData.getBillingList(); 
	private JPanel contentPane;
	private JTextField Cur;
	private JTextField Date;
	private JTextField pID;
	private JTextField mID;
	private JTextField code;
	private JTextField Name;

	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					BillMember frame = new BillMember();
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
	public BillMember(Billing b, Service s, Member m) {
		setResizable(false);
		setTitle("Comfirm Bill");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 479, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(BillMember.class.getResource("/images/chocanUnlabeled.png")));
		lblLogo.setBounds(195, 0, 87, 87);
		contentPane.add(lblLogo);
		
		JButton btnContinue = new JButton("Finish");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				m.setBalance(m.getBalance() - s.getServicePrice());
				if (m.getBalance()<=0) {
					m.setValidMember(false);
					
				}
				if(Control.memberManage.editMember(m) == false) {
					JOptionPane.showMessageDialog(null, "Failed to update memebr infomation!");
				}
				else {
					if(Blist.add(b) == false) {
						JOptionPane.showMessageDialog(null, "Can't add bill to record!");
						startService.BillFr.dispose();
					}
					else {
						if (DataIO.GlobalData.setBillingList(Blist) == false) {
							JOptionPane.showMessageDialog(null, "Update list faied!");
						}
						else {
							JOptionPane.showMessageDialog(null, "Billed successfully!");
							startService.BillFr.dispose();
							ProviderFrame.dir.dispose();
						}
					}
				}
				
			}
		});
		btnContinue.setBounds(346, 572, 117, 29);
		contentPane.add(btnContinue);
		
		JLabel lblNewLabel = new JLabel("Comfirm Billing Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setBounds(100, 87, 264, 29);
		contentPane.add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startService.BillFr.dispose();
			}
		});
		btnBack.setBounds(10, 572, 117, 29);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel_1 = new JLabel("Current Date and Time:");
		lblNewLabel_1.setBounds(10, 140, 157, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date Service Provided:");
		lblNewLabel_2.setBounds(10, 168, 144, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Provider Number:");
		lblNewLabel_3.setBounds(10, 196, 131, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Member Number:");
		lblNewLabel_4.setBounds(10, 224, 110, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Service Code:");
		lblNewLabel_5.setBounds(10, 252, 110, 16);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Service Name:");
		lblNewLabel_6.setBounds(10, 280, 110, 16);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblComments = new JLabel("Comments:");
		lblComments.setBounds(10, 312, 87, 16);
		contentPane.add(lblComments);
		
		Cur = new JTextField();
		Cur.setText(b.getCurrentDateTime());
		Cur.setHorizontalAlignment(SwingConstants.CENTER);
		Cur.setEditable(false);
		Cur.setBounds(202, 135, 261, 26);
		contentPane.add(Cur);
		Cur.setColumns(10);
		
		Date = new JTextField();
		Date.setText(b.getServiceProvideDate());
		Date.setHorizontalAlignment(SwingConstants.CENTER);
		Date.setEditable(false);
		Date.setBounds(202, 163, 261, 26);
		contentPane.add(Date);
		Date.setColumns(10);
		
		pID = new JTextField();
		pID.setText(Integer.toString(b.getProviderID()));
		pID.setHorizontalAlignment(SwingConstants.CENTER);
		pID.setEditable(false);
		pID.setBounds(202, 191, 261, 26);
		contentPane.add(pID);
		pID.setColumns(10);
		
		mID = new JTextField();
		mID.setText(Integer.toString(b.getMemberID()));
		mID.setHorizontalAlignment(SwingConstants.CENTER);
		mID.setEditable(false);
		mID.setBounds(202, 219, 261, 26);
		contentPane.add(mID);
		mID.setColumns(10);
		
		code = new JTextField();
		code.setText(Integer.toString(b.getBillingID()));
		code.setHorizontalAlignment(SwingConstants.CENTER);
		code.setEditable(false);
		code.setBounds(202, 247, 261, 26);
		contentPane.add(code);
		code.setColumns(10);
		
		Name = new JTextField();
		Name.setText(s.getServiceName());
		Name.setHorizontalAlignment(SwingConstants.CENTER);
		Name.setEditable(false);
		Name.setBounds(202, 275, 261, 26);
		contentPane.add(Name);
		Name.setColumns(10);
		
		JTextArea comments = new JTextArea();
		comments.setLineWrap(true);
		comments.setText(b.getComment());
		comments.setEditable(false);
		comments.setBounds(10, 339, 453, 222);
		contentPane.add(comments);
	}
}
