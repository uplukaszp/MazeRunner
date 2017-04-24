package mazeHandling;

import java.util.Observable;
import com.sun.javafx.geom.Point2D;

import javafx.scene.canvas.GraphicsContext;



public abstract class Mover extends Observable{
	
	protected int x;
	protected int y;
	protected Directions lastDirection=Directions.up;
	
	
	public Point2D getPos()
	{
		return new Point2D(x,y);
	}
	public Directions getLastDirection() {
		return lastDirection;
	}
	public void updatePos()
	{
		switch (getLastDirection()) {
		case up:
			y--;
			break;
		case left:
			x--;
			break;
		case down:
			y++;
			break;
		case right:
			x++;
			break;
		default:
			break;
		}
	}
	
	public abstract void Draw(GraphicsContext gc,double width,double height,int size);
	public abstract void tryMove();
	
}
