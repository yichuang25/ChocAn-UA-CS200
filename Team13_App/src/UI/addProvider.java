package UI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Member;
import Entity.Provider;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * This is the UI for add provider
 * @author Yichen Huang
 *
 */

public class addProvider extends JFrame {

	private JPanel contentPane;
	private JTextField Nametext;
	private JTextField Emailtext;
	private JTextField Addresstext;
	private JTextField Citytext;
	private JTextField Statetext;
	private JTextField Zipcodetext;
	private JTextField Passwordtext;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					addProvider frame = new addProvider();
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
	public addProvider() {
		setResizable(false);
		setTitle("Add Provider");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 419, 318);
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
		
		JLabel lblNewLabel = new JLabel("Address:");
		lblNewLabel.setBounds(30, 86, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(30, 114, 61, 16);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State:");
		lblState.setBounds(30, 142, 61, 16);
		contentPane.add(lblState);
		
		JLabel lblZipcode = new JLabel("Zipcode:");
		lblZipcode.setBounds(30, 170, 61, 16);
		contentPane.add(lblZipcode);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(30, 198, 79, 16);
		contentPane.add(lblPassword);
		
		JLabel label = new JLabel("");
		label.setBounds(30, 235, 61, 16);
		contentPane.add(label);
		
		Nametext = new JTextField();
		Nametext.setBounds(250, 25, 130, 26);
		contentPane.add(Nametext);
		Nametext.setColumns(10);
		
		Emailtext = new JTextField();
		Emailtext.setBounds(250, 53, 130, 26);
		contentPane.add(Emailtext);
		Emailtext.setColumns(10);
		
		Addresstext = new JTextField();
		Addresstext.setBounds(250, 81, 130, 26);
		contentPane.add(Addresstext);
		Addresstext.setColumns(10);
		
		Citytext = new JTextField();
		Citytext.setBounds(250, 109, 130, 26);
		contentPane.add(Citytext);
		Citytext.setColumns(10);
		
		Statetext = new JTextField();
		Statetext.setBounds(250, 137, 130, 26);
		contentPane.add(Statetext);
		Statetext.setColumns(10);
		
		Zipcodetext = new JTextField();
		Zipcodetext.setBounds(250, 165, 130, 26);
		contentPane.add(Zipcodetext);
		Zipcodetext.setColumns(10);
		
		Passwordtext = new JTextField();
		Passwordtext.setBounds(250, 193, 130, 26);
		contentPane.add(Passwordtext);
		Passwordtext.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperatorFrame.addPro.dispose();
			}
		});
		btnCancel.setBounds(30, 226, 117, 29);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Provider> Plist = DataIO.GlobalData.getProviderList();
				int ID;
				if (Plist == null) {
					ID = 1000000000;
				}
				else {
					int size = Plist.size()-1;
					ID = Plist.get(size).getID()+1;
				}
				if(Nametext.getText().isEmpty() || Emailtext.getText().isEmpty() || Addresstext.getText().isEmpty() || Citytext.getText().isEmpty() || Statetext.getText().isEmpty() || Zipcodetext.getText().isEmpty() || Passwordtext.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Error, please complete the form!");
				}
				else {
					boolean zipcodeletter = false;
					for(int i = 0;i<Zipcodetext.getText().length();i++) {
						if (Character.isAlphabetic(Zipcodetext.getText().charAt(i))) {
							zipcodeletter = true;
						}
					}
					if(zipcodeletter) {
						JOptionPane.showMessageDialog(null, "Error, Zipcode can not contain letter!");
					}
					else {
						Provider temp = new Provider(ID, Nametext.getText(),Emailtext.getText(),Addresstext.getText(),Citytext.getText(),Statetext.getText(),Integer.parseInt(Zipcodetext.getText()));
						Control.providerManage.addProvider(temp, Passwordtext.getText());
						OperatorFrame.refreashList("P");
						OperatorFrame.addPro.dispose();
					}
					 
				}
			}
		});
		btnSave.setBounds(263, 226, 117, 29);
		contentPane.add(btnSave);
	}
}
