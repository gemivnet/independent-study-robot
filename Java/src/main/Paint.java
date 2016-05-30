package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Paint extends JPanel {

	int lastTime, currentTime;
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		lastTime = currentTime;
		currentTime = (int) System.currentTimeMillis();
		Frame.getInstance().setUPS(1000 / (currentTime - lastTime));
		
		int width = Frame.getInstance().getWidth(), height = Frame.getInstance().getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		
	}
	
}
