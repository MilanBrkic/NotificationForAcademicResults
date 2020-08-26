package notification;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class TrayIconDemo {
	static String myself = "milan.brkic1998@gmail.com";
	 public static void main(String[] args) throws AWTException {
	        if (SystemTray.isSupported()) {
	        	
	        	TrayIconDemo td = new TrayIconDemo();
			    td.displayTray();
			    try {
					Gmail.sendMail(myself);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        		            
	        } else {
	            System.err.println("System tray not supported!");
	        }
	    }

	    public void displayTray() throws AWTException {
	        //Obtain only one instance of the SystemTray object
	        SystemTray tray = SystemTray.getSystemTray();

	        //If the icon is a file
	        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
	        //Alternative (if the icon is on the classpath):
	        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

	        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
	        //Let the system resize the image if needed
	        trayIcon.setImageAutoSize(true);
	        //Set tooltip text for the tray icon
	        trayIcon.setToolTip("System tray icon demo");
	        tray.add(trayIcon);

	        trayIcon.displayMessage("is.fon.bg.ac.rs", "Izaslo je nesto novo", MessageType.INFO);
	    }
}
