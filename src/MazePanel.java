import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


import MazeGenerator.Generator;

public class MazePanel extends JPanel{

	Generator gen=new Generator(10);
	public MazePanel() {
		this.setBackground(new Color(0xff,0x77,0x1d));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2=(Graphics2D)g;
		g2.setColor(new Color(0x20,0x97,0xff));
		g2.setStroke(new BasicStroke(3));
		gen.DrawMaze(g2,getWidth(),getHeight());
	}
}
