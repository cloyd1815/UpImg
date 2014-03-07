package me.cloyd1815.upimg.main;

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

import me.cloyd1815.upimg.UpImg;

public class Main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static JPanel panel;
	public static JButton button;
	public static JLabel label;
	public static Main window;
	
	public Main() {
		panel = new JPanel();
		button = new JButton("UpImg");
		button.addActionListener(this);
		label = new JLabel();
		
		panel.add(button);
		panel.add(label);
		this.add(panel);
	}

	public static void main(String[] args) {
		window = new Main();
		window.setTitle("Up Img");
		window.setSize(200, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button) {
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage capture = null;
			try {
				capture = new Robot().createScreenCapture(screenRect);
				File file = File.createTempFile("screenshot", ".png");
				ImageIO.write(capture, "png", file);
				UpImg.upimg(file);
			} catch (IOException | AWTException | URISyntaxException e1) {
				e1.printStackTrace();
			}
		}
	}
}