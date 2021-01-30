package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.LoginControl;
import DataIO.GlobalData;
import Entity.Provider;
import cs200fall2019team13.Main;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Window;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField passwordField;
	
	public static ManagerFrame managerFr;
	public static ProviderFrame providerFr;
	public static OperatorFrame operatorFr;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Login frame = new Login();
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
	public Login() {
		setResizable(false);
		setTitle("ChocAn Login");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 376, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		
		JLabel lblLogin = new JLabel("Please Login:");
		lblLogin.setBounds(35, 24, 91, 16);
		contentPane.add(lblLogin);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(45, 69, 130, 26);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(45, 117, 130, 26);
		contentPane.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(45, 52, 81, 16);
		contentPane.add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setBounds(45, 100, 68, 16);
		contentPane.add(lblNewLabel);
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton radioButton = new JRadioButton("Manager");
		radioButton.setBounds(45, 175, 94, 23);
		group.add(radioButton);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_1 = new JRadioButton("Provider");
		radioButton_1.setBounds(141, 175, 90, 23);
		group.add(radioButton_1);
		contentPane.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("Operator");
		radioButton_2.setBounds(233, 175, 106, 23);
		group.add(radioButton_2);
		contentPane.add(radioButton_2);
		
		JLabel label = new JLabel("Select your role:");
		label.setBounds(35, 153, 113, 16);
		contentPane.add(label);
		
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String role = "";
				if(radioButton.isSelected()) role = "m"; 
				else if(radioButton_1.isSelected()) role = "p"; 
				else if(radioButton_2.isSelected()) role = "o"; 
				
				String u = txtUsername.getText();
				String pa = passwordField.getText(); 
				
				boolean test = LoginControl.loginAction(u, pa, role);
				
				if(role != "" && test) {
					LaunchFrame.loginPage.dispose();
					if(role == "m") {
						managerFr = new ManagerFrame(new Entity.Login(u,pa,role));
						InvolkePageCloseAction(managerFr); 
						managerFr.setVisible(true);
					}
					else if(role == "p"){
						Provider temp = Control.providerManage.searchID(Integer.parseInt(u));
						if (temp == null) {
							JOptionPane.showMessageDialog(null, "The provider not exist!");
						}
						else {
							providerFr = new ProviderFrame(temp);
							InvolkePageCloseAction(providerFr); 
							providerFr.setVisible(true);
						}
					}
					else if(role == "o") {
						operatorFr = new OperatorFrame(); 
						InvolkePageCloseAction(operatorFr);
						operatorFr.setVisible(true);
					}
				}
				else JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
			}
		});
		btnLogin.setBounds(201, 217, 117, 29);
		contentPane.add(btnLogin);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				LaunchFrame.frmChocanLogin.setVisible(true);
				LaunchFrame.loginPage.dispatchEvent(new WindowEvent(LaunchFrame.loginPage, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnBack.setBounds(35, 217, 117, 29);
		contentPane.add(btnBack);
		
		JLabel logo = new JLabel(new ImageIcon(LaunchFrame.class.getResource("/images/chocanUnlabeled.png")));
		logo.setBounds(233, 68, 75, 75);
		getContentPane().add(logo);
		
	}
	
	public static void InvolkePageCloseAction(JFrame f) {
		f.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(f, 
		            "Are you sure you want to close the program?", "Close Program?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            Main.systemExit();
		        }
		    }
		});
	}
}
