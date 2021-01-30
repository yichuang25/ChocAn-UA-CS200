package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.ReportControl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import Control.ReportControl;

public class ManagerFrame extends JFrame {

	private JPanel contentPane;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ManagerFrame frame = new ManagerFrame();
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
	public ManagerFrame(Entity.Login m) {
		setResizable(false);
		setTitle("ChocAn Manager");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 466, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblWelcomemanager = new JLabel("Welcome, " + m.getUserName());
		lblWelcomemanager.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblWelcomemanager.setBounds(25, 6, 214, 41);
		contentPane.add(lblWelcomemanager);

		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(LaunchFrame.loginPage, "Are you sure you want to Logout?", "Logout?",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					LaunchFrame.frmChocanLogin.setVisible(true);
					contentPane.setVisible(false);
					Login.managerFr.dispose();
				}

			}
		});
		btnLogout.setBounds(255, 240, 187, 29);
		contentPane.add(btnLogout);

		JButton btnRequestProviderReport = new JButton("Generate Provider Report");
		btnRequestProviderReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRequestProviderReport.disable();
				try {
					ReportControl.MakeReport("Provider");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Provider reports generated");
				btnRequestProviderReport.enable();
			}
		});
		btnRequestProviderReport.setBounds(25, 58, 220, 29);
		contentPane.add(btnRequestProviderReport);

		JButton btnRequestMemberReport = new JButton("Generate Member Report");
		btnRequestMemberReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRequestMemberReport.disable();
				try {
					ReportControl.MakeReport("Member");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Member reports generated");
				btnRequestMemberReport.enable();
			}
		});
		btnRequestMemberReport.setBounds(25, 99, 220, 29);
		contentPane.add(btnRequestMemberReport);

		JButton btnRequestSummaryReport = new JButton("Generate Summary Report");
		btnRequestSummaryReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRequestSummaryReport.disable();
				try {
					ReportControl.MakeReport("Manager");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "Summary report generated");
				btnRequestSummaryReport.enable();
			}
		});
		btnRequestSummaryReport.setBounds(25, 140, 220, 29);
		contentPane.add(btnRequestSummaryReport);

		JLabel logo = new JLabel(new ImageIcon(LaunchFrame.class.getResource("/images/chocanUnlabeled.png")));
		logo.setBounds(314, 78, 75, 75);
		getContentPane().add(logo);

		JLabel lblTimeToAuto = new JLabel("Time to auto generate all reports");
		lblTimeToAuto.setBounds(25, 176, 220, 14);
		contentPane.add(lblTimeToAuto);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setValue(0);
		progressBar.setBounds(25, 201, 220, 29);
		contentPane.add(progressBar);
		fill(progressBar);
		
		JButton btnGenerateAllReports = new JButton("Generate All Reports Now");
		btnGenerateAllReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnGenerateAllReports.disable();
				try {
					ReportControl.MakeReport("All");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "All reports generated");
				btnGenerateAllReports.enable();
			}
		});
		btnGenerateAllReports.setBounds(25, 240, 220, 29);
		contentPane.add(btnGenerateAllReports);
		
		JButton btnTestTimer = new JButton("Test Timer");
		btnTestTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				remain = 5;
			}
		});
		btnTestTimer.setBounds(255, 201, 187, 28);
		contentPane.add(btnTestTimer);
	}

	private static long remain; 
	
	/**
	 * 
	 * @param A jave progress bar element
	 * 
	 * @apiNote This is a Swiss made timer
	 */
	private static void fill(JProgressBar pb) {
		try {
			final long DAY7 = 604800;
			
			LocalDateTime reportDatetime = LocalDateTime.of(LocalDate.now(ZoneId.systemDefault()), LocalTime.now(ZoneId.systemDefault()).MAX).with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
			pb.setStringPainted(true);
			remain = reportDatetime.atZone(ZoneId.systemDefault()).toEpochSecond() - LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
			ActionListener al = new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					pb.setString((remain / (60 * 60 * 24)) % 7 + " Days - " + (remain / (60 * 60)) % 24 + " : " + (remain / 60) % 60 + " : " + remain % 60);
					remain --;
					int i = (int)(((double)(DAY7 - remain) / DAY7)*100);
					pb.setValue(i);
					if(remain == 0) {
						try {
							remain = reportDatetime.atZone(ZoneId.systemDefault()).toEpochSecond()  - LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond();
							ReportControl.MakeReport("All");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			};
			new javax.swing.Timer(1000, al).start();
			
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
