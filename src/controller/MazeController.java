package controller;

import java.util.ArrayList;
import java.util.Observable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import mazeGenerating.Maze;
import mazeHandling.AutomaticMover;
import mazeHandling.Directions;



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
		boolean stopMoving=true;
		drawAll();
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
				m.stop();
				m.tryMove();
			}
			
		}
		for(AutomaticMover m:movers)
		{
			if(m.isMoving())
			{
				stopMoving=false;
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
		maze.drawMaze(gc, width, height);
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
	public void drawBestPath(AutomaticMover best) {
			maze.setBestSequence(best.getMoveSequence());
	}
	public void clear() {
		movers.clear();
		maze.setBestSequence(new Directions[0]);
		drawAll();
	}
	
}
