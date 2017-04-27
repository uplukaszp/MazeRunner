package controller;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;
import mazeGenerating.Maze;
import mazeHandling.Mover;

public class MazeController {
	private Maze maze;
	private ArrayList<Mover>movers;
	private GraphicsContext gc;
	private double width;
	private double height;
	private int size;
	private Timeline timeline;
	public MazeController(GraphicsContext gc,int size,double width,double height)
	{
		
		timeline = new Timeline(new KeyFrame(
		        Duration.millis(100),
		        ae -> tryMove()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
		maze=new Maze(size);
		movers=new ArrayList<Mover>();
		this.size=size;
		this.gc=gc;
		this.width=width;
		this.height=height;
		
	}
	private void tryMove()
	{
		drawAll();
		for(Mover m:movers)
		{
			m.tryMove();
			if(maze.isPosibleMove(m.getLastDirection(), m.getPos()))
			m.updatePos();
		}
	}
	public void addMover(Mover m)
	{
		movers.add(m);
		
	}
	
	public void setMovers(ArrayList<Mover>m)
	{
		this.movers=m;
	}
	public ArrayList<Mover> getMovers()
	{
		return  movers;
	}
	public void drawAll()
	{
		maze.DrawMaze(gc, width, height);
		for(Mover m:movers)
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
