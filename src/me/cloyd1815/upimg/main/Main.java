package me.cloyd1815.upimg.main;

import javax.swing.JFrame;

import me.cloyd1815.upimg.window.Window;

public class Main {
	public static Window window;
	
	public static void main(String[] args) {
		window = new Window();
		window.setTitle("UpImg");
		window.setSize(200, 100);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}