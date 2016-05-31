package main;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

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
	JTextArea rawData = new JTextArea();
	JScrollPane rawDataScroll = new JScrollPane(rawData);
	JCheckBox drawRobot = new JCheckBox("Draw Robot's Track (Red)");
	JCheckBox drawRobotLines = new JCheckBox("Connect With Lines");
	JCheckBox drawWall = new JCheckBox("Draw Wall (Black)");
	JCheckBox drawWallLines = new JCheckBox("Connect With Lines");
	
	public void initFrame() {
		
		raw.setSize(500, 300);
		raw.setLocation(700, 400);
		raw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		raw.setVisible(true);
		raw.setResizable(false);

		rawData.setLineWrap(true);
		rawData.setWrapStyleWord(true);
		rawData.setEditable(false);
		raw.add(rawDataScroll);
		
		set.setSize(500, 400);
		set.setLocation(700, 0);
		set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		set.setVisible(true);
		set.setResizable(false);
		set.setLayout(null);
		
		lblUPS.setBounds(5, 5, 300, 10);
		set.add(lblUPS);
		drawRobot.setSelected(true);
		drawRobot.setBounds(5, 25, 300, 15);
		set.add(drawRobot);
		drawRobotLines.setSelected(true);
		drawRobotLines.setBounds(20, 45, 300, 15);
		set.add(drawRobotLines);
		drawWall.setSelected(true);
		drawWall.setBounds(5, 70, 300, 15);
		set.add(drawWall);
		drawWallLines.setSelected(true);
		drawWallLines.setBounds(20, 90, 300, 15);
		set.add(drawWallLines);
		
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
	
	int width = 500, height = 400, ups = 0;
	
	public void update() {
		
		map.repaint();
		lblUPS.setText("Updates Per Second: " + ups);
		rawData.setText(Main.getInstance().getRawData());
		rawDataScroll.getVerticalScrollBar().setValue(rawDataScroll.getVerticalScrollBar().getMaximum());

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
	
	public boolean isDrawRobotSelected() {
		return drawRobot.isSelected();
	}
	
	public boolean isDrawRobotLinesSelected() {
		return drawRobotLines.isSelected();
	}
	
}
