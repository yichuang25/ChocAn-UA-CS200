/**
 * 
 */
package cs200fall2019team13;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import DataIO.*;
import UI.LaunchFrame;

/**
 * @author Team13
 *
 */
public class Main {

	private static GlobalData gb;
	private static Object lock = new Object();

	/**
	 * @apiNote This method initializes the system by creating a new static Global
	 *          Data object and that object will read in (check out) the database.
	 * @return bool to indicate if the initialize process is success.
	 */
	private static boolean initialize() {
		System.out.println("INITIALIZING SYSTEM ... \n");
		gb = new GlobalData();
		gb.PrintAllTable();
		System.out.println("\n<<< SYSTEM INITIALIZED >>> \n");
		return true;
	}

	/**
	 * @apiNote The One and ONLY Entry Point of the ChocAn App
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (!initialize() || gb == null)
			System.exit(-1);

		LaunchFrame window = new LaunchFrame();
		window.frmChocanLogin.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		window.frmChocanLogin.setVisible(true);

		Thread t = new Thread() {
			public void run() {
				synchronized (lock) {
					while (window.frmChocanLogin.isVisible())
						try {
							lock.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					System.out.println("Working now");
				}
			}
		};
		t.start();

		window.frmChocanLogin.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				synchronized (lock) {
					window.frmChocanLogin.setVisible(false);
					lock.notify();
				}
			}

		});

		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		systemExit();
	}

	/**
	 * @apiNote Call this if you want to exit the system. This method will handle
	 *          all the data check in. (write back to database)
	 */
	public static void systemExit() {
		System.out.println("CLOSING SYSTEM ... \n");

		gb.PrintAllTable();

		if (!gb.StoreAllData()) {
			System.out.println("Data failed to store back to database\n");
			return;
		}
		if (!gb.disconnectDatabase()) {
			System.out.println("Failed to close database connection");
			return;
		}

		System.out.println("ALL DATA STORED BACK TO DATABASE\n");
		System.out.println("SYSTEM CLOSED\n");

		System.exit(0);
	}

}
