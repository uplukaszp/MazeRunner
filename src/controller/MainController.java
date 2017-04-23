package controller;


import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.scene.BoundsAccessor;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.layout.BorderStroke;
import javafx.scene.paint.Color;
import mazeGenerating.Maze;
import mazeHandling.AutomaticMover;
import mazeHandling.MazeController;

public class MainController {

    @FXML    private Button btn1;
    @FXML    private TextField tf_size;    
    @FXML    private Canvas canvas;

    
    private int size;
    private MazeController controller;
    
    @FXML
    void Generate(ActionEvent event) {
    	try
    	{
    		size=Integer.valueOf(tf_size.getText());
    		GraphicsContext gc=canvas.getGraphicsContext2D();
    		controller=new MazeController(gc,size,canvas.getWidth(), canvas.getHeight());
    		controller.addMover(new AutomaticMover());
    		controller.drawAll();
    	}catch(Exception e)
    	{
    		System.out.println(e.getLocalizedMessage());
    	}
    	
    }

    @FXML
    void SizeTyped(ActionEvent event) {
    	  
    	  
    }

}
