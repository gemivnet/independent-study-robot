
public class Main {

	private static Main instance;
	
	public static Main getInstance() {
		if (instance == null) {
			instance = new Main();
		}
		return instance;
	}
	
	
	
}
