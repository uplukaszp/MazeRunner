package mazeHandling;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AutomaticMover extends Mover{

	private double sizeOfMover;
	private Color c;
	
	public AutomaticMover() {
		
		Random r=new Random();
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(100),
		        ae -> tryMove()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
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
		lastDirection=Directions.getRandomDirection();
		if(lastDirection==null)
		{
			System.out.println("null");
		}
		setChanged();
		notifyObservers();
		
	}

}
