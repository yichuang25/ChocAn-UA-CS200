package UI;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Control.providerControl;
import Entity.Member;
import Entity.Provider;
import cs200fall2019team13.Main;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * This ProviderFrame is the first UI window the provider will see after login. The provider will be able to enter a MemberID here and begin service if the 
 * member is valid.
 * @author Dylan Young
 *
 */
public class ProviderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField Mid;
	
	public static BillMember billFr;
	public static startService dir;
	

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				ProviderFrame frame = new ProviderFrame();
	//				frame.setVisible(true);
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	});
	//}

	/**
	 * Create the frame.
	 */
	public ProviderFrame(Provider p) {
		setResizable(false);
		setTitle("ChocAn Provider");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 622, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelprovider = new JLabel("Welcome, " + p.getName());
		lblWelprovider.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblWelprovider.setBounds(10, 11, 214, 41);
		contentPane.add(lblWelprovider);
		
		/*JButton Bill_M = new JButton("Bill Member");
		Bill_M.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(Mid.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No MemberID entered");
				}
				else {
					int MidNum= (Integer.parseInt(Mid.getText()));
					if(providerControl.checkStatus(MidNum)==0) {
						billFr=new BillMember();
						billFr.setVisible(true);
						}
					
					else if(providerControl.checkStatus(MidNum)==1) {
						JOptionPane.showMessageDialog(null, "Member is suspended due to unpaid funds");
					}

				
					else {
						JOptionPane.showMessageDialog(null, "MemberID is not registered");
					}
					
				}
				
				
				
				
			}
		});
		Bill_M.setBounds(307, 229, 137, 44);
		contentPane.add(Bill_M);*/
		
		JLabel logo = new JLabel(new ImageIcon(LaunchFrame.class.getResource("/images/chocanUnlabeled.png")));
		logo.setBounds(515, 11, 75, 75);
		getContentPane().add(logo);
		
		JLabel lblMemberStatus = new JLabel("");
		lblMemberStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblMemberStatus.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblMemberStatus.setBounds(117, 181, 347, 29);
		contentPane.add(lblMemberStatus);
		
		Mid = new JTextField();
		Mid.setHorizontalAlignment(SwingConstants.CENTER);
		Mid.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkMemberID(); 
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkMemberID(); 
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkMemberID(); 
				
			}
			/**
			 * Check MemberID is Valid
			 */
			public void checkMemberID() {
				if (Mid.getText().length() == 9) {
					int MidNum = Integer.parseInt(Mid.getText());
					if (providerControl.checkStatus(MidNum) == 0) {
						lblMemberStatus.setForeground(Color.green);
						lblMemberStatus.setText("Validated");
					} else if (providerControl.checkStatus(MidNum) == 1) {
						lblMemberStatus.setForeground(Color.red);
						lblMemberStatus.setText("Suspended");
					} else {
						lblMemberStatus.setForeground(Color.red);
						lblMemberStatus.setText("Not Registered");
					}
				}
				else if(Mid.getText().length() > 9) {
					JOptionPane.showMessageDialog(null, "The ID is too long!");
				}
				else {
					lblMemberStatus.setText("");
				}
			}
		});
		Mid.setBounds(117, 118, 347, 52);
		contentPane.add(Mid);
		Mid.setColumns(10);
		
		JLabel lblText = new JLabel("Swipe Member card or key in Member ID:");
		lblText.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblText.setBounds(107, 63, 412, 44);
		contentPane.add(lblText);
		
		/*
		 * show error message if memberID is not valid
		 */
		JButton btnNewButton = new JButton("Start Service");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Mid.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No MemberID entered");
				}
				else {
					int MidNum= (Integer.parseInt(Mid.getText()));
					if(providerControl.checkStatus(MidNum)==0) {
						Member temp = Control.memberManage.searchID(Integer.parseInt(Mid.getText()));
						if (temp == null) {
							JOptionPane.showMessageDialog(null, "The member not exist!");
						}
						else {
							dir = new startService(temp, p);
							dir.setVisible(true);
							Mid.setText(null);
						}
						
					}
					
					else if(providerControl.checkStatus(MidNum)==1) {
						JOptionPane.showMessageDialog(null, "Member is suspended due to unpaid funds");
					}

				
					else {
						JOptionPane.showMessageDialog(null, "MemberID is not registered");
					}
					
				}

				
				
			}
		});
		btnNewButton.setBounds(228, 221, 137, 44);
		contentPane.add(btnNewButton);
		
		/**
		 * Logout function
		 */
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(LaunchFrame.loginPage, "Are you sure you want to Logout?", "Logout?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					LaunchFrame.frmChocanLogin.setVisible(true);
					contentPane.setVisible(false);
					Login.providerFr.dispose();
				}
			}
		});
		btnLogout.setBounds(470, 281, 117, 29);
		contentPane.add(btnLogout);

	}
}
