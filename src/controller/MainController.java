package controller;

import MazeGenerator.Generator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class MainController {

    @FXML    private Button btn1;
    @FXML    private TextField tf_size;    
    @FXML    private Canvas canvas;

    
    private int size;
    private Generator maze;
    @FXML
    void Generate(ActionEvent event) {
    	try
    	{
    		size=Integer.valueOf(tf_size.getText());
    	}catch(Exception e)
    	{
    		System.out.println(e.getMessage());
    	}
    	maze=new Generator(size);
    	GraphicsContext gc=canvas.getGraphicsContext2D();
    	maze.DrawMaze(gc, canvas.getWidth(), canvas.getHeight());
    }

    @FXML
    void SizeTyped(ActionEvent event) {
    	  
    	  
    }

}
