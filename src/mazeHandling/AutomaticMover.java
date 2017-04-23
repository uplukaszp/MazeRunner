package mazeHandling;

import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AutomaticMover extends Mover{

	
	public AutomaticMover() {
		//TODO change timer;
		/*Timer t=new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				tryMove();
			}
		},0, 1000);
	*/
		Timeline timeline = new Timeline(new KeyFrame(
		        Duration.millis(1000),
		        ae -> tryMove()));
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.play();
	}
	@Override
	public void Draw(GraphicsContext gc, double width, double height, int size) {
		
		double cellWidth=width/size;
		double cellHeight=height/size;
		gc.setFill(Color.RED);
		gc.fillRect(x*cellWidth, y*cellHeight, cellWidth, cellHeight);
		System.out.println("x="+x+" y="+ y+" cellW="+cellWidth+" cellh="+cellHeight);
		
	}

	@Override
	public void tryMove() {		
		System.out.println("ovveriden tryMove");
		lastDirection=Directions.down;
		setChanged();
		notifyObservers();
		
	}

}
