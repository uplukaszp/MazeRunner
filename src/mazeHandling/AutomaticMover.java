package mazeHandling;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AutomaticMover extends Mover{

	private double sizeOfMover;
	private Color c;
	private Directions moveSequence [];
	private int whichMove=0;
	public AutomaticMover(int howManyMoves) {
		
		Random r=new Random();
		moveSequence=new Directions[howManyMoves];
		for(int i=0;i<moveSequence.length;i++)
		{
			moveSequence[i]=Directions.getRandomDirection();
		}
		
		sizeOfMover=0.3+r.nextDouble()*0.7;
		c=new Color(r.nextDouble(),r.nextDouble(),r.nextDouble(),r.nextDouble());
	}
	
	@Override
	public void Draw(GraphicsContext gc, double width, double height, int size) {
		
		double cellWidth=width/size;
		double cellHeight=height/size;
		gc.setFill(c);
		gc.fillRect(x*cellWidth, y*cellHeight, cellWidth*sizeOfMover, cellHeight*sizeOfMover);
	}

	@Override
	public void tryMove() {
		if(whichMove<moveSequence.length)
		{
			lastDirection=moveSequence[whichMove++];
		}else
		{
			lastDirection=Directions.nothing;
		}		
	}
}
