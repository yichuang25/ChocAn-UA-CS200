package UI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

import Entity.Member;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

/**
 * This is the UI for add member
 * @author Yichen Huang
 *
 */

public class addMember extends JFrame {

	private JPanel contentPane;
	private JTextField Nametext;
	private JTextField Emailtext;
	private JTextField Addresstext;
	private JTextField Citytext;
	private JTextField Statetext;
	private JTextField Zipcodetext;
	private JTextField Balancetext;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					addMember frame = new addMember();
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
	public addMember() {
		setResizable(false);
		setTitle("New Member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 412, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(30, 30, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(30, 58, 61, 16);
		contentPane.add(lblEmail);
		
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(30, 86, 61, 16);
		contentPane.add(lblAddress);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(30, 114, 61, 16);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(30, 142, 61, 16);
		contentPane.add(lblState);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setBounds(30, 170, 61, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(30, 198, 61, 16);
		contentPane.add(lblBalance);
		
		Nametext = new JTextField();
		Nametext.setBounds(191, 25, 192, 26);
		contentPane.add(Nametext);
		Nametext.setColumns(10);
		
		Emailtext = new JTextField();
		Emailtext.setBounds(191, 53, 192, 26);
		contentPane.add(Emailtext);
		Emailtext.setColumns(10);
		
		Addresstext = new JTextField();
		Addresstext.setBounds(191, 81, 192, 26);
		contentPane.add(Addresstext);
		Addresstext.setColumns(10);
		
		Citytext = new JTextField();
		Citytext.setBounds(191, 109, 192, 26);
		contentPane.add(Citytext);
		Citytext.setColumns(10);
		
		Statetext = new JTextField();
		Statetext.setBounds(191, 137, 192, 26);
		contentPane.add(Statetext);
		Statetext.setColumns(10);
		
		Zipcodetext = new JTextField();
		Zipcodetext.setBounds(191, 165, 192, 26);
		contentPane.add(Zipcodetext);
		Zipcodetext.setColumns(10);
		
		Balancetext = new JTextField();
		Balancetext.setBounds(191, 193, 192, 26);
		contentPane.add(Balancetext);
		Balancetext.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperatorFrame.addMem.dispose();
			}
		});
		btnCancel.setBounds(30, 243, 117, 29);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Member> Mlist = DataIO.GlobalData.getMemberList();
				int ID;
				if (Mlist == null) {
					ID = 1000000000;
				}
				else {
					int size = Mlist.size()-1;
					ID = Mlist.get(size).getID()+1;
				}
				if(Nametext.getText().isEmpty() || Emailtext.getText().isEmpty() || Addresstext.getText().isEmpty() || Citytext.getText().isEmpty() || Statetext.getText().isEmpty() || Zipcodetext.getText().isEmpty() || Balancetext.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error, please complete the form!");
				}
				else {
					boolean zipcodeletter = false;
					boolean balanceletter = false;
					
					for(int i = 0;i<Zipcodetext.getText().length();i++) {
						if (Character.isAlphabetic(Zipcodetext.getText().charAt(i))) {
							zipcodeletter = true;
						}
					}
					
					for(int i = 0;i<Balancetext.getText().length();i++) {
						if (Character.isAlphabetic(Balancetext.getText().charAt(i))) {
							balanceletter = true;
						}
					}
					
					if(zipcodeletter) {
						JOptionPane.showMessageDialog(null, "Error, Zipcode can not contain letter!");
					}
					else if(balanceletter) {
						JOptionPane.showMessageDialog(null, "Error, Balance can not contain letter");
					}
					else {
						Member temp = new Member(ID, Nametext.getText(),Emailtext.getText(),Addresstext.getText(),Citytext.getText(),Statetext.getText(),Integer.parseInt(Zipcodetext.getText()),Double.parseDouble(Balancetext.getText()),true);
						if(Double.parseDouble(Balancetext.getText()) <= 0) {
							temp.setValidMember(false);
						}
						Control.memberManage.addMember(temp);
						OperatorFrame.refreashList("M");
						OperatorFrame.addMem.dispose();
					}
					
					
				}
			}
		});
		btnSave.setBounds(266, 243, 117, 29);
		contentPane.add(btnSave);
	}
}
