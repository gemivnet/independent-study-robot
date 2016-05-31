package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

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
		
		// Draw Robot's Track if Selected
		
		if (Frame.getInstance().isDrawRobotSelected()) {
			
			ArrayList<Point> robot = Main.getInstance().getRobot();
			
			int prevX = 0, prevY = 0;
			
			for (int i = 0; i < robot.size(); i++) {
				g.setColor(Color.RED);
				g.fillRect(robot.get(i).getRobotX(), robot.get(i).getRobotY(), 3, 3);
				
				if (Frame.getInstance().isDrawRobotLinesSelected()) {
					if (i == 0) {
						prevX = robot.get(i).getRobotX();
						prevY = robot.get(i).getRobotY();
					} else {
						g.drawLine(robot.get(i).getRobotX(), robot.get(i).getRobotY(), prevX, prevY);
						prevX = robot.get(i).getRobotX();
						prevY = robot.get(i).getRobotY();
					}
					
					
				}
				
			}
			
		}
		
		
	}
	
}
