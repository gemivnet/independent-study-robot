package main;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
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
	JLabel lblMoveScale = new JLabel("Move Scale - " + Main.getInstance().getMoveScale());
	JSlider moveScale = new JSlider();
	JLabel lblTurnScale = new JLabel("Turn Scale - " + Main.getInstance().getTurnScale());
	JSlider turnScale = new JSlider();
	JLabel lblXOffset = new JLabel("X Offset - 0");
	JSlider xOffset = new JSlider();
	JLabel lblYOffset = new JLabel("Y Offset - 0");
	JSlider yOffset = new JSlider();
	
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
		lblMoveScale.setBounds(5, 130, 300, 15);
		set.add(lblMoveScale);
		moveScale.setMaximum(100);
		moveScale.setMinimum(1);
		moveScale.setValue(Main.getInstance().getMoveScale());
		moveScale.setPaintTicks(true);
		moveScale.setMajorTickSpacing(10);
		moveScale.setInverted(true);
		moveScale.setBounds(5, 150, 300, 30);
		set.add(moveScale);
		lblTurnScale.setBounds(5, 190, 300, 15);
		set.add(lblTurnScale);
		turnScale.setMaximum(20);
		turnScale.setMinimum(1);
		turnScale.setValue(Main.getInstance().getTurnScale());
		turnScale.setPaintTicks(true);
		turnScale.setMajorTickSpacing(10);
		turnScale.setBounds(5, 210, 300, 30);
		set.add(turnScale);
		lblXOffset.setBounds(5, 255, 300, 15);
		set.add(lblXOffset);
		xOffset.setMaximum(500);
		xOffset.setMinimum(-500);
		xOffset.setValue(0);
		xOffset.setPaintTicks(true);
		xOffset.setMajorTickSpacing(100);
		xOffset.setBounds(5, 275, 300, 30);
		set.add(xOffset);
		lblYOffset.setBounds(5, 310, 300, 15);
		set.add(lblYOffset);
		yOffset.setMaximum(500);
		yOffset.setMinimum(-500);
		yOffset.setValue(0);
		yOffset.setPaintTicks(true);
		yOffset.setMajorTickSpacing(100);
		yOffset.setBounds(5, 325, 300, 40);
		set.add(yOffset);
		
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
	int xOffsetValue = 0, yOffsetValue = 0;
	
	public void update() {
		
		map.repaint();
		lblUPS.setText("Updates Per Second: " + ups);
		rawData.setText(Main.getInstance().getRawData());
		rawDataScroll.getVerticalScrollBar().setValue(rawDataScroll.getVerticalScrollBar().getMaximum());
		
		Main.getInstance().setMoveScale(moveScale.getValue());
		lblMoveScale.setText("Move Scale - " + Main.getInstance().getMoveScale());
		
		Main.getInstance().setTurnScale(turnScale.getValue());
		lblTurnScale.setText("Turn Scale - " + Main.getInstance().getTurnScale());
		
		xOffsetValue = xOffset.getValue();
		yOffsetValue = yOffset.getValue();
		
		lblXOffset.setText("X Offset - " + xOffsetValue);
		lblYOffset.setText("Y Offset - " + yOffsetValue);

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
	
	public boolean isDrawWallSelected() {
		return drawWall.isSelected();
	}
	
	public boolean isDrawWallLinesSelected() {
		return drawWallLines.isSelected();
	}
	
	public int getXOffset() {
		return xOffsetValue;
	}
	
	public int getYOffset() {
		return yOffsetValue;
	}
	
}
