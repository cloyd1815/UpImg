package me.cloyd1815.upimg.window;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import me.cloyd1815.upimg.UpImg;

public class SelectionPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8398916652995091464L;
	private JButton button;
	private JLabel label;

	public SelectionPane() {
		button = new JButton("UpImg");
		setOpaque(false);

		label = new JLabel("Rectangle");
		label.setOpaque(true);
		label.setBorder(new EmptyBorder(4, 4, 4, 4));
		label.setBackground(Color.GRAY);
		label.setForeground(Color.WHITE);
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(label, gbc);

		gbc.gridy++;
		add(button, gbc);

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.getWindowAncestor(SelectionPane.this).dispose();
				BufferedImage capture = null;
				try {
					capture = new Robot().createScreenCapture(getVirtualBounds());
					BufferedImage img = capture.getSubimage(getX(), getY(), getWidth(), getHeight());
					File file = File.createTempFile("screenshot", ".png");
					ImageIO.write(img, "png", file);
					UpImg.upimg(file);

				} catch (IOException | AWTException | URISyntaxException e1) {
					e1.printStackTrace();
				}
			}
		});

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				label.setText("Rectangle " + getX() + "x" + getY() + "x"
						+ getWidth() + "x" + getHeight());
			}
		});

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		// I've chosen NOT to fill this selection rectangle, so that
		// it now appears as if you're "cutting" away the selection
		// g2d.setColor(new Color(128, 128, 128, 64));
		// g2d.fillRect(0, 0, getWidth(), getHeight());

		float dash1[] = { 10.0f };
		BasicStroke dashed = new BasicStroke(3.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(dashed);
		g2d.drawRect(0, 0, getWidth() - 3, getHeight() - 3);
		g2d.dispose();
	}

	public static Rectangle getVirtualBounds() {

		Rectangle bounds = new Rectangle(0, 0, 0, 0);

		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice lstGDs[] = ge.getScreenDevices();
		for (GraphicsDevice gd : lstGDs) {

			bounds.add(gd.getDefaultConfiguration().getBounds());

		}

		return bounds;

	}
}