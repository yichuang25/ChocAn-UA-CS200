package UI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

import cs200fall2019team13.Main;

public class LaunchFrame {

	public static JFrame frmChocanLogin;
	public static Login loginPage; 
	//private final ButtonGroup buttonGroup = new ButtonGroup();
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		///new GlobalData(); 
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LaunchFrame window = new LaunchFrame();
//					window.frmChocanLogin.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public LaunchFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChocanLogin = new JFrame();
		frmChocanLogin.setResizable(false);
		frmChocanLogin.setTitle("ChocAn Login");
		frmChocanLogin.setBounds(100, 100, 478, 333);
		frmChocanLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frmChocanLogin.getContentPane().setLayout(null);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			/**
			 * @note this will take the user to the login page
			 */
			public void actionPerformed(ActionEvent e) {
				frmChocanLogin.setVisible(false);
				loginPage = new Login();
				
				LaunchFrame.loginPage.addWindowListener(new java.awt.event.WindowAdapter() {
					/**
					 * @note this handles the the window closing action, and call the system exit method in the main class
					 */
				    @Override
				    public void windowClosing(WindowEvent windowEvent) {
				        if (JOptionPane.showConfirmDialog(LaunchFrame.loginPage, 
				            "Are you sure you want to close the program?", "Close Program?", 
				            JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
				            Main.systemExit();
				        }
				    }
				});
				
				loginPage.setVisible(true);
			}
		});
		btnStart.setBounds(327, 243, 117, 29);
		frmChocanLogin.getContentPane().add(btnStart);
		
		JButton btnExit = new JButton("Close");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmChocanLogin.dispatchEvent(new WindowEvent(frmChocanLogin, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnExit.setBounds(10, 243, 117, 29);
		frmChocanLogin.getContentPane().add(btnExit);
		
		JLabel logo = new JLabel(new ImageIcon(LaunchFrame.class.getResource("/images/chocanResized.png")));
		logo.setBounds(147, 43, 150, 181);
		frmChocanLogin.getContentPane().add(logo);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the ChocAn App");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Arial Black", Font.BOLD, 15));
		lblWelcomeToThe.setBounds(10, 11, 422, 29);
		frmChocanLogin.getContentPane().add(lblWelcomeToThe);
	}
}
