package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

	private static Main instance;
	
	public static Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		return instance;
	}
	
	Timer update = new Timer();
	final int UPS = 10;
	
	public void init() {
		
		Frame.getInstance().initFrame();
		map(getInput());
		update.schedule(updateTask, 1000 / UPS, 1000 / UPS);
		
	}
	
	String raw = "";
	
	ArrayList<Point> wall = null;
	ArrayList<Point> robot = null;
	
	int moveScale = 15, turnScale = 6;
	
	public void map(ArrayList<Command> c) {
		
		// Make points based on the robot's position
		
		robot = new ArrayList<Point>();
		
		int robotX = Frame.getInstance().getWidth() / 2;
		int robotY = Frame.getInstance().getHeight() / 2;
		
		int heading = 0;
		
		for (int i = 0; i < c.size(); i++) {
			Command com = c.get(i);
			switch (com.getType()) {
			case "m0":
				robotX += com.getAmount() * Math.cos(Math.toRadians(heading)) / moveScale;
				robotY += com.getAmount() * Math.sin(Math.toRadians(heading)) / moveScale;
				robot.add(new Point(robotX, robotY));
				break;
			case "m1":
				robotX -= com.getAmount() * Math.cos(Math.toRadians(heading)) / moveScale;
				robotY -= com.getAmount() * Math.sin(Math.toRadians(heading)) / moveScale;
				robot.add(new Point(robotX, robotY));
				break;
			case "m2":
				heading -= com.getAmount() / turnScale;
				break;
			case "m3":
				heading += com.getAmount() / turnScale;
				break;
			}
		}
		
		// Make points based on the wall
		
		wall = new ArrayList<Point>();
		
		robotX = Frame.getInstance().getWidth() / 2;
		robotY = Frame.getInstance().getHeight() / 2;
		
		heading = 0;
		
		for (int i = 0; i < c.size(); i++) {
			Command com = c.get(i);
			switch (com.getType()) {
			case "s1":
				wall.add(new Point(robotX - 500 / moveScale, robotY - 500 / moveScale, heading - 90, com.getAmount()));
				break;
			case "s3":
				wall.add(new Point(robotX - 500 / moveScale, robotY - 500 / moveScale, heading, com.getAmount()));
				break;
			case "m0":
				robotX += com.getAmount() * Math.cos(Math.toRadians(heading)) / moveScale;
				robotY += com.getAmount() * Math.sin(Math.toRadians(heading)) / moveScale;
				break;
			case "m1":
				robotX -= com.getAmount() * Math.cos(Math.toRadians(heading)) / moveScale;
				robotY -= com.getAmount() * Math.sin(Math.toRadians(heading)) / moveScale;
				break;
			case "m2":
				heading -= com.getAmount() / turnScale;
				break;
			case "m3":
				heading += com.getAmount() / turnScale;
				break;
			}
			
			
		}
		
		
	}
	
	public ArrayList<Command> getInput() {

		ArrayList<Command> commands = new ArrayList<Command>();
		
		raw = "";

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("c.txt")));

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			raw = sb.toString();
			br.close();

		} catch (Exception e) {

		}

		String[] c = raw.split("\\)");

		for (int i = 0; i < c.length; i++) {
			
			String tmp = c[i];
			tmp = tmp.replace(" (", "");
			tmp = tmp.replace("(", "");
			
			if (tmp.contains(",")) {
				
				String[] t = tmp.split(",");

				commands.add(new Command(t[0], Integer.parseInt(t[1])));

			} else {
				commands.add(new Command(tmp));
			}

		}

		return commands;

	}
	
	
	TimerTask updateTask = new TimerTask() {
		public void run() {
			map(getInput());
			Frame.getInstance().update();
		}
	};
	
	public String getRawData() {
		return raw;
	}
	
	public ArrayList<Point> getWall() {
		return wall;
	}
	
	public ArrayList<Point> getRobot() {
		return robot;
	}
	
	public int getMoveScale() {
		return moveScale;
	}
	
	public void setMoveScale(int i) {
		moveScale = i;
	}
	
	public int getTurnScale() {
		return turnScale;
	}
	
	public void setTurnScale(int i) {
		turnScale = i;
	}
	
}
