package UI;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import Control.memberManage;
import DataIO.GlobalData;
import Entity.Billing;
import Entity.Member;
import Entity.Provider;
import Entity.Service;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextArea;
import java.awt.Font;

/**
 * The UI of Service Directory for provider
 * @author Yichen Huang
 *
 */

public class startService extends JFrame {

	private JPanel contentPane;
	private static ArrayList<Service> Slist = GlobalData.getServiceList(); 
	private static DefaultTableModel model;
	private JTable table;
	private JButton btnNewButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblMemberId;
	private JLabel lblProviderId;
	private JLabel lblServiceCode;
	private JLabel lblComments;
	private JTextField CurTimetext;
	private JTextField Datetext;
	private JTextField mtext;
	private JTextField ptext;
	private JTextField codetext;
	private JLabel lblServiceName;
	private JTextField servicetext;
	private JButton btnNewButton_1;
	private JButton btnSelect;
	private JLabel lblNewLabel_1;
	public static BillMember BillFr;
	private JTextArea comments;
	private static Service cur;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				directory frame = new directory();
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
	public startService(Member m,Provider p) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(startService.class.getResource("/images/chocanUnlabeled.png")));
		setTitle("Begin Service");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 739, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 43, 289, 298);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		String col[] = {"Service Code","Service Name","Service Price"};
		model = new DefaultTableModel(null,col) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton("Close");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProviderFrame.dir.dispose();
			}
		});
		btnNewButton.setBounds(6, 352, 117, 29);
		contentPane.add(btnNewButton);
		
		lblNewLabel = new JLabel("Provider Directory");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(49, 12, 202, 21);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_2 = new JLabel("Current Date And Time:");
		lblNewLabel_2.setBounds(305, 49, 154, 16);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Service Date:(MM-DD-YYYY)");
		lblNewLabel_3.setBounds(305, 78, 193, 16);
		contentPane.add(lblNewLabel_3);
		
		lblMemberId = new JLabel("Member ID:");
		lblMemberId.setBounds(305, 105, 92, 16);
		contentPane.add(lblMemberId);
		
		lblProviderId = new JLabel("Provider ID:");
		lblProviderId.setBounds(305, 132, 77, 16);
		contentPane.add(lblProviderId);
		
		lblServiceCode = new JLabel("Service Code:");
		lblServiceCode.setBounds(305, 159, 92, 16);
		contentPane.add(lblServiceCode);
		
		lblComments = new JLabel("Comments: (Optional)");
		lblComments.setBounds(305, 214, 193, 16);
		contentPane.add(lblComments);
		
		CurTimetext = new JTextField();
		CurTimetext.setHorizontalAlignment(SwingConstants.CENTER);
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		CurTimetext.setText(sdf2.format(timestamp));
		CurTimetext.setEditable(false);
		CurTimetext.setBounds(495, 44, 204, 26);
		contentPane.add(CurTimetext);
		CurTimetext.setColumns(10);
		
		Datetext = new JTextField();
		Datetext.setHorizontalAlignment(SwingConstants.CENTER);
		Datetext.setBounds(495, 73, 140, 26);
		contentPane.add(Datetext);
		Datetext.setColumns(10);
		
		mtext = new JTextField();
		mtext.setHorizontalAlignment(SwingConstants.CENTER);
		mtext.setEditable(false);
		mtext.setText(Integer.toString(m.getID()));
		mtext.setBounds(495, 100, 204, 26);
		contentPane.add(mtext);
		mtext.setColumns(10);
		
		ptext = new JTextField();
		ptext.setHorizontalAlignment(SwingConstants.CENTER);
		ptext.setEditable(false);
		ptext.setText(Integer.toString(p.getID()));
		ptext.setBounds(495, 127, 204, 26);
		contentPane.add(ptext);
		ptext.setColumns(10);
		
		codetext = new JTextField();
		codetext.setHorizontalAlignment(SwingConstants.CENTER);
		codetext.setEditable(false);
		codetext.setBounds(495, 154, 204, 26);
		contentPane.add(codetext);
		codetext.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(codetext.getText().isEmpty() || codetext.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Select the service!");
				}
				else if(Datetext.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Enter the date!");
				}
				else {
					if(comments.getText().isEmpty()) {
						Billing b = Control.providerControl.billMember(p.getID(), Integer.parseInt(codetext.getText()), m.getID(), null, CurTimetext.getText(), Datetext.getText());
						BillFr = new BillMember(b, cur,m);
						BillFr.setVisible(true);		
					}
					else {
						Billing b = Control.providerControl.billMember(p.getID(), Integer.parseInt(codetext.getText()), m.getID(), comments.getText(), CurTimetext.getText(), Datetext.getText());
						BillFr = new BillMember(b,cur,m);
						BillFr.setVisible(true);		
					}
				}
			}
		});
		btnNext.setBounds(582, 352, 117, 29);
		contentPane.add(btnNext);
		
		lblServiceName = new JLabel("Service Name:");
		lblServiceName.setBounds(305, 187, 92, 16);
		contentPane.add(lblServiceName);
		
		servicetext = new JTextField();
		servicetext.setHorizontalAlignment(SwingConstants.CENTER);
		servicetext.setEditable(false);
		servicetext.setBounds(495, 182, 204, 26);
		contentPane.add(servicetext);
		servicetext.setColumns(10);
		
		btnNewButton_1 = new JButton("Set");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				Datetext.setText(sdf.format(timestamp));
			}
		});
		btnNewButton_1.setBounds(639, 73, 60, 26);
		contentPane.add(btnNewButton_1);
		
		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				int Code = Integer.parseInt(table.getModel().getValueAt(row, 0).toString());
				Service s = Slist.stream().filter(x -> x.getServiceCode() == Code).findAny().orElse(null);
				if (s == null) {
					JOptionPane.showMessageDialog(null, "Please select the service!");
				}
				else {
					codetext.setText(Integer.toString(s.getServiceCode()));
					servicetext.setText(s.getServiceName());
					cur = s;
				}
			}
		});
		btnSelect.setBounds(178, 352, 117, 29);
		contentPane.add(btnSelect);
		
		lblNewLabel_1 = new JLabel("New Bill");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(410, 12, 147, 20);
		contentPane.add(lblNewLabel_1);
		
		comments = new JTextArea();
		comments.setLineWrap(true);
		comments.setWrapStyleWord(true);
		comments.setBounds(307, 241, 392, 100);
		contentPane.add(comments);
		comments.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkCharLength(); 
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkCharLength(); 
				
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkCharLength(); 
				
			}
			
			public void checkCharLength() {
				if (comments.getText().length() == 100) {
					comments.setEditable(false);
				}
			}
		});
		
		for(Service s : Slist) model.addRow(new Object[] {s.getServiceCode(), s.getServiceName(), s.getServicePrice()});
	}
}
