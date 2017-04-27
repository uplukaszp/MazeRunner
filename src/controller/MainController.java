package controller;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mazeHandling.AutomaticMover;

public class MainController {

    @FXML    private Button btn1;
    @FXML    private TextField tf_size;    
    @FXML    private Canvas canvas;
    @FXML    private Button addbtn;
    @FXML    private AnchorPane GeneticPane;
    
    private int size;
    private MazeController controller;
    private int howManyMoves=100;
    
    @FXML
    void Generate(ActionEvent event) {
    		if(controller!=null)
    		{
    			controller.stopAllMovers();
    		}
    		size=Integer.valueOf(tf_size.getText());
    		GraphicsContext gc=canvas.getGraphicsContext2D();
    		controller=new MazeController(gc,size,canvas.getWidth(), canvas.getHeight());
    		controller.drawAll();
    	
    		
    }
    

    
    @FXML
    void add(ActionEvent event) {
    	if(controller!=null)
    	{
    		controller.addMover(new AutomaticMover(howManyMoves));
    	}
    }

}
