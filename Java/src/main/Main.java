package main;

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
		update.schedule(updateTask, 1000 / UPS, 1000 / UPS);
		
	}
	
	TimerTask updateTask = new TimerTask() {
		public void run() {
			Frame.getInstance().update();
		}
	};
	
	
}
