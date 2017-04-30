package mazeHandling;

import java.util.Random;

import com.sun.javafx.geom.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class AutomaticMover {

	private double sizeOfMover;
	private Color c;
	private Directions moveSequence [];
	private int whichMove=0;
	private int x=0;
	private int y=0;
	private Directions lastDirection=Directions.up;
	private boolean isStopped=false;
	

	public AutomaticMover(int howManyMoves) {
		x=y=whichMove=0;
		Random r=new Random();
		moveSequence=new Directions[howManyMoves];
		for(int i=0;i<moveSequence.length;i++)
		{
			moveSequence[i]=Directions.getRandomDirection();
		}
		
		sizeOfMover=0.3+r.nextDouble()*0.7;
		c=new Color(r.nextDouble(),r.nextDouble(),r.nextDouble(),r.nextDouble());
	}
	public AutomaticMover(Directions sequence[])
	{
		x=y=whichMove=0;
		Random r=new Random();
		moveSequence=sequence;
		sizeOfMover=0.3+r.nextDouble()*0.7;
		c=new Color(r.nextDouble(),r.nextDouble(),r.nextDouble(),r.nextDouble());
	}
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
	
	public void Draw(GraphicsContext gc, double width, double height, int size) {
		
		double cellWidth=width/size;
		double cellHeight=height/size;
		gc.setFill(c);
		gc.fillRect(x*cellWidth, y*cellHeight, cellWidth*sizeOfMover, cellHeight*sizeOfMover);
	}

	public void stop()
	{
		isStopped=true;
	}
	public void tryMove() {
		if(whichMove<moveSequence.length&&!isStopped)
		{
			lastDirection=moveSequence[whichMove++];
		}else
		{
			lastDirection=Directions.nothing;
			isStopped=true;
		}		
	}
	
	public boolean isMoving()
	{
		return !isStopped;
		//return lastDirection!=Directions.nothing;
	}
	public boolean isAtTheEnd(int size)
	{
		return this.x==(size-1)&&this.y==(size-1);
	}
	public int getNumberOfMoves()
	{
		return whichMove;
	}
	public Directions[] getMoveSequence()
	{
		return moveSequence;
	}
	public int getHowManyMoves()
	{
		return whichMove;
	}
	@Override
	public String toString()
	{
		return "Move nr: "+getNumberOfMoves()+" isMoving: "+isMoving()+"isStoped: "+isStopped+" pos: "+getPos();
	}
}
