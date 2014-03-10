package me.cloyd1815.upimg.window;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import me.cloyd1815.upimg.UpImg;

public class Window extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JPanel panel;
	public static JButton button;
	public static JLabel label;
	public static Window window;
	public static JTextArea text;
	
	public Window() {
		panel = new JPanel();
		button = new JButton("UpImg");
		button.addActionListener(this);
		label = new JLabel();
		
		button.setToolTipText("Click to upload an image");
		panel.add(button);
		panel.add(label);
		this.add(panel);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			window.setState(ICONIFIED);
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = null;
			try {
				capture = new Robot().createScreenCapture(screenRect);
				File file = File.createTempFile("screenshot", ".png");
				ImageIO.write(capture, "PNG", file);
				UpImg.upimg(file);
			} catch (IOException | AWTException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	}
}

