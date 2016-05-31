package main;

public class Point {

	int robotX, robotY, distance, heading;
	
	public Point(int robotX, int robotY) {
		this.robotX = robotX;
		this.robotY = robotY;
	}
	
	public Point(int robotX, int robotY, int heading, int distance) {
		this.robotX = robotX;
		this.robotY = robotY;
		this.heading = heading;
		this.distance = distance;
	}

	public int getRobotX() {
		return robotX;
	}

	public int getRobotY() {
		return robotY;
	}

	public int getDistance() {
		return distance;
	}

	public int getHeading() {
		return heading;
	}

}
