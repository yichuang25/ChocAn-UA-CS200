package UI;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Entity.Provider;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * UI for editProvider
 * @author Yichen Huang
 *
 */

public class editProvider extends JFrame {

	private JPanel contentPane;
	private JTextField Nametext;
	private JTextField Emailtext;
	private JTextField Addresstext;
	private JTextField Citytext;
	private JTextField Statetext;
	private JTextField Zipcodetext;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					editProvider frame = new editProvider();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @param single provider selected
	 */
	public editProvider(Provider temp) {
		setResizable(false);
		setTitle("Edit Member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 308);
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
		
		Nametext = new JTextField();
		Nametext.setText(temp.getName());
		Nametext.setBounds(270, 25, 130, 26);
		contentPane.add(Nametext);
		Nametext.setColumns(10);
		
		Emailtext = new JTextField();
		Emailtext.setText(temp.getEmail());
		Emailtext.setBounds(270, 53, 130, 26);
		contentPane.add(Emailtext);
		Emailtext.setColumns(10);
		
		Addresstext = new JTextField();
		Addresstext.setText(temp.getAddress());
		Addresstext.setBounds(270, 81, 130, 26);
		contentPane.add(Addresstext);
		Addresstext.setColumns(10);
		
		Citytext = new JTextField();
		Citytext.setText(temp.getCity());
		Citytext.setBounds(270, 109, 130, 26);
		contentPane.add(Citytext);
		Citytext.setColumns(10);
		
		Statetext = new JTextField();
		Statetext.setText(temp.getState());
		Statetext.setBounds(270, 137, 130, 26);
		contentPane.add(Statetext);
		Statetext.setColumns(10);
		
		Zipcodetext = new JTextField();
		Zipcodetext.setText(Integer.toString(temp.getZipCode()));
		Zipcodetext.setBounds(270, 165, 130, 26);
		contentPane.add(Zipcodetext);
		Zipcodetext.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OperatorFrame.editPro.dispose();
			}
		});
		btnCancel.setBounds(30, 220, 117, 29);
		contentPane.add(btnCancel);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Nametext.getText().isEmpty() || Emailtext.getText().isEmpty() || Addresstext.getText().isEmpty() || Citytext.getText().isEmpty() || Statetext.getText().isEmpty() || Zipcodetext.getText().isEmpty()) {
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
						temp.setName(Nametext.getText());
						temp.setEmail(Emailtext.getText());
						temp.setAddress(Addresstext.getText());
						temp.setCity(Citytext.getText());
						temp.setState(Statetext.getText());
						temp.setZipCode(Integer.parseInt(Zipcodetext.getText()));
						Control.providerManage.editProvider(temp);
						OperatorFrame.refreashList("P");
						OperatorFrame.editPro.dispose();
					}
					
				}
				
			}
		});
		btnSave.setBounds(283, 220, 117, 29);
		contentPane.add(btnSave);
	}
}
