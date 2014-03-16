package me.cloyd1815.upimg.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SnipIt {

	public SnipIt() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
				}

				JFrame frame = new JFrame();
				frame.setUndecorated(true);
				// This works differently under Java 6
				frame.setBackground(new Color(0, 0, 0, 0));
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new SnipItPane());
				frame.setBounds(SelectionPane.getVirtualBounds());
				frame.setVisible(true);
			}
		});
	}
}
