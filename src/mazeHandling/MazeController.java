package mazeHandling;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.canvas.GraphicsContext;
import mazeGenerating.Maze;

public class MazeController implements Observer{
	private Maze maze;
	private ArrayList<Mover>movers;
	private GraphicsContext gc;
	private double width;
	private double height;
	private int size;
	public MazeController(GraphicsContext gc,int size,double width,double height)
	{
		maze=new Maze(size);
		movers=new ArrayList<Mover>();
		this.size=size;
		this.gc=gc;
		this.width=width;
		this.height=height;
		
	}
	public void addMover(Mover m)
	{
		movers.add(m);
		m.addObserver(this);
	}
	@Override
	public void update(Observable o, Object arg) {
		drawAll();
		for(Mover m:movers)
		{
			m.updatePos();
		}
		
	}
	public void drawAll()
	{
		for(Mover m:movers)
		{
			m.Draw(gc,width,height,size);
		}
		maze.DrawMaze(gc, width, height);
	}
	
}