import javax.swing.JFrame;

public class Frame extends JFrame{

	Frame()
	{
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		add(new MazePanel());
		repaint();
	}
}
