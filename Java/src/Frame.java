import javax.swing.JFrame;

public class Frame {

	public static Frame instance;
	
	public static Frame getInstance() {
		if (instance ==  null) {
			instance = new Frame();
		}
		return instance;
	}
	
	JFrame f = new JFrame("Robot Mapping");
	
	public void initFrame() {
		
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		
		
		
	}
	
}
