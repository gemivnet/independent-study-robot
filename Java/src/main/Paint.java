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
		
		int xO = Frame.getInstance().getXOffset();
		int yO = Frame.getInstance().getYOffset();
		
		if (Frame.getInstance().isDrawRobotSelected()) {
			
			ArrayList<Point> robot = Main.getInstance().getRobot();
			
			int prevX = 0, prevY = 0;
			
			for (int i = 0; i < robot.size(); i++) {
				
				int robotX = robot.get(i).getRobotX() + xO;
				int robotY = robot.get(i).getRobotY() + yO;
				
				g.setColor(Color.RED);
				g.fillRect(robotX, robotY, 3, 3);
				
				if (Frame.getInstance().isDrawRobotLinesSelected()) {
					if (i == 0) {
						prevX = robotX;
						prevY = robotY;
					} else {
						g.drawLine(robotX, robotY, prevX, prevY);
						prevX = robotX;
						prevY = robotY;
					}
					
					
				}
				
			}
			
		}
		
		if (Frame.getInstance().isDrawWallSelected()) {
			
			ArrayList<Point> wall = Main.getInstance().getWall();
			
			int prevX = 0, prevY = 0;
			
			for (int i = 0; i < wall.size(); i++) {
				
				int robotX = wall.get(i).getRobotX();
				int robotY = wall.get(i).getRobotY();
			
				robotX = (int) (xO + robotX + wall.get(i).getDistance() * Math.cos(Math.toRadians(wall.get(i).getDistance())));
				robotY = (int) (yO + robotY + wall.get(i).getDistance() * Math.sin(Math.toRadians(wall.get(i).getDistance())));
			
				g.setColor(Color.BLACK);
				g.fillRect(robotX, robotY, 3, 3);
				
				if (Frame.getInstance().isDrawWallLinesSelected()) {
					if (i == 0) {
						prevX = robotX;
						prevY = robotY;
					} else {
						g.drawLine(robotX, robotY, prevX, prevY);
						prevX = robotX;
						prevY = robotY;
					}
					
					
				}
			
			}
			
		}
		
		
	}
	
}
