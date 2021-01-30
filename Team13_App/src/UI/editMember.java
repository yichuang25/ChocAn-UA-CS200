package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Member;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This is the UI for edit signle member selected
 * @author Yichen Huang
 *
 */

public class editMember extends JFrame {

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
//					editMember frame = new editMember();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param single member selected
	 */
	public editMember(Member temp) {
		setResizable(false);
		setTitle("Edit Member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 325);
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
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperatorFrame.editMem.dispose();
			}
		});
		btnCancel.setBounds(30, 243, 117, 29);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
						temp.setName(Nametext.getText());
					     temp.setEmail(Emailtext.getText());
					     temp.setAddress(Addresstext.getText());
					     temp.setCity(Citytext.getText());
					     temp.setState(Statetext.getText());
					     temp.setZipCode(Integer.parseInt(Zipcodetext.getText()));
					     temp.setBalance(Double.parseDouble(Balancetext.getText()));
					     if(Double.parseDouble(Balancetext.getText()) <= 0) {
					    	 temp.setValidMember(false);
					     }
					     else {
					    	 temp.setValidMember(true);
					     }
					     Control.memberManage.editMember(temp);
					     OperatorFrame.editMem.dispose();
					     OperatorFrame.refreashList("M");
					}
				}
			}});
		btnSave.setBounds(280, 243, 117, 29);
		contentPane.add(btnSave);
		
		Nametext = new JTextField();
		Nametext.setText(temp.getName());
		Nametext.setBounds(267, 25, 130, 26);
		contentPane.add(Nametext);
		Nametext.setColumns(10);
		
		Emailtext = new JTextField();
		Emailtext.setText(temp.getEmail());
		Emailtext.setBounds(267, 53, 130, 26);
		contentPane.add(Emailtext);
		Emailtext.setColumns(10);
		
		Addresstext = new JTextField();
		Addresstext.setText(temp.getAddress());
		Addresstext.setBounds(267, 81, 130, 26);
		contentPane.add(Addresstext);
		Addresstext.setColumns(10);
		
		Citytext = new JTextField();
		Citytext.setText(temp.getCity());
		Citytext.setBounds(267, 109, 130, 26);
		contentPane.add(Citytext);
		Citytext.setColumns(10);
		
		Statetext = new JTextField();
		Statetext.setText(temp.getState());
		Statetext.setBounds(267, 137, 130, 26);
		contentPane.add(Statetext);
		Statetext.setColumns(10);
		
		Zipcodetext = new JTextField();
		Zipcodetext.setText(Integer.toString(temp.getZipCode()));
		Zipcodetext.setBounds(267, 165, 130, 26);
		contentPane.add(Zipcodetext);
		Zipcodetext.setColumns(10);
		
		Balancetext = new JTextField();
		Balancetext.setText(Double.toString(temp.getBalance()));
		Balancetext.setBounds(267, 193, 130, 26);
		contentPane.add(Balancetext);
		Balancetext.setColumns(10);
	}

}
