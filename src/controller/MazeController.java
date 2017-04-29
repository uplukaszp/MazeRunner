package controller;

import java.util.ArrayList;
import java.util.Observable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import mazeGenerating.Maze;
import mazeHandling.AutomaticMover;



public class MazeController extends Observable{
	private Maze maze;
	private ArrayList<AutomaticMover>movers;
	private GraphicsContext gc;
	private double width;
	private double height;
	private int size;
	private Timeline timeline;
	
	public MazeController(GraphicsContext gc,int size,double width,double height)
	{
		
		timeline = new Timeline(new KeyFrame(
		        Duration.millis(50),
		        ae -> tryMove()));
		timeline.setCycleCount(Animation.INDEFINITE);
		maze=new Maze(size);
		movers=new ArrayList<AutomaticMover>();
		this.size=size;
		this.gc=gc;
		this.width=width;
		this.height=height;
		
	}
	private void tryMove()
	{
		boolean stopMoving=false;
		drawAll();
		System.out.println();
		for(AutomaticMover m:movers)
		{
			if(!m.isAtTheEnd(size))
			{
				m.tryMove();
				if(maze.isPosibleMove(m.getLastDirection(), m.getPos()))
				{
					m.updatePos();
				}
				
			}else
			{
				m.tryMove();
			}
			
		}
		for(AutomaticMover m:movers)
		{
			if(!m.isMoving())
			{
				stopMoving=true;
			}
		}
		
		if(stopMoving)
		{
			endCycle();
		}
	}
	
	private void endCycle() 
	{
		stopSimulation();
		setChanged();
		notifyObservers();		
	}
	public void addMover(AutomaticMover m)
	{
		movers.add(m);		
	}
	
	public void setMovers(ArrayList<AutomaticMover>m)
	{
		this.movers=m;
	}
	public ArrayList<AutomaticMover> getMovers()
	{
		return  movers;
	}
	public void drawAll()
	{
		maze.DrawMaze(gc, width, height);
		for(AutomaticMover m:movers)
		{
			m.Draw(gc,width,height,size);
		}
		
	}
	public void StartSimulation()
	{
		timeline.play();
	}
	public void stopSimulation()
	{
		timeline.stop();
	}
	
}
