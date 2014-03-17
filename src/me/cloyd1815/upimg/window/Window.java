package me.cloyd1815.upimg.window;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import me.cloyd1815.upimg.main.Main;

public class Window extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JPanel panel;
	public static JButton button;
	public static JLabel label;
	public static Window window;
	public static JTextArea text;
	public static TrayIcon trayIcon;
	public static SystemTray tray;
	public static PopupMenu popup;

	public Window() {
		panel = new JPanel();
		button = new JButton("UpImg");
		button.addActionListener(this);
		label = new JLabel();
		text = new JTextArea();

		button.setToolTipText("Click to upload an image");
		panel.add(button);
		panel.add(label);
		panel.add(text);
		this.add(panel);

		if (SystemTray.isSupported()) {
			tray = SystemTray.getSystemTray();

			Image image = createImage("images/favicon.png", "tray icon");
			ActionListener exitListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			};
			popup = new PopupMenu();
			MenuItem defaultItem = new MenuItem("Exit");
			defaultItem.addActionListener(exitListener);
			popup.add(defaultItem);
			defaultItem = new MenuItem("Open");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(true);
					setExtendedState(JFrame.NORMAL);
				}
			});
			popup.add(defaultItem);
			defaultItem = new MenuItem("Take ScreenShot");
			defaultItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new SnipIt();
				}
			});
			popup.add(defaultItem);
			trayIcon = new TrayIcon(image, "UpImg", popup);
			trayIcon.setImageAutoSize(true);
		} else {
			System.out.println("system tray not supported");
		}
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				if (e.getNewState() == ICONIFIED) {
					try {
						tray.add(trayIcon);
						setVisible(false);
					} catch (AWTException ex) {
						System.out.println("unable to add to tray");
					}
				}
				if (e.getNewState() == 7) {
					try {
						tray.add(trayIcon);
						setVisible(false);
					} catch (AWTException ex) {
						System.out.println("unable to add to system tray");
					}
				}
				if (e.getNewState() == MAXIMIZED_BOTH) {
					tray.remove(trayIcon);
					setVisible(true);
				}
				if (e.getNewState() == NORMAL) {
					tray.remove(trayIcon);
					setVisible(true);
				}
			}
		});

	}
//8:00
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			new SnipIt();
		}
	}

	public static Image createImage(String path, String description) {
		URL imageURL = Main.class.getResource(path);

		if (imageURL == null) {
			System.err.println("Resource not found: " + path);
			return null;
		} else {
			return (new ImageIcon(imageURL, description)).getImage();
		}
	}
}
