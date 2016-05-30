package main;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Frame extends JPanel {

	private static Frame instance;
	
	public static Frame getInstance() {
		if (instance == null) {
			instance = new Frame();
		}
		return instance;
	}
	
	JFrame map = new JFrame("Robot Mapper - Map");
	JFrame set = new JFrame("Robot Mapper - Settings");
	JFrame raw = new JFrame("Robot Mapper - Raw Data");
	
	JLabel lblUPS = new JLabel("Updates Per Second: ");
	
	public void initFrame() {
		
		raw.setSize(500, 300);
		raw.setLocation(700, 400);
		raw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raw.setVisible(true);
		raw.setResizable(false);
		raw.setLayout(null);
		
		set.setSize(500, 400);
		set.setLocation(700, 0);
		set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		set.setVisible(true);
		set.setResizable(false);
		set.setLayout(null);
		
		lblUPS.setBounds(5, 5, 300, 10);
		set.add(lblUPS);
		
		map.setSize(700, 700);
		map.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		map.setVisible(true);
		map.add(new Paint());
		
		map.addComponentListener(new ComponentListener() {
			
			public void componentShown(ComponentEvent e) {}
		
			public void componentResized(ComponentEvent e) {
				width = map.getContentPane().getWidth();
				height = map.getContentPane().getHeight();	
			}
			
			public void componentMoved(ComponentEvent e) {}
	
			public void componentHidden(ComponentEvent e) {}
			
		});
		
	}
	
	int width, height, ups = 0;
	
	public void update() {
		
		map.repaint();
		lblUPS.setText("Updates Per Second: " + ups);

	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setUPS(int i) {
		ups = i;
	}
	
}
