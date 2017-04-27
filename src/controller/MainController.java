package controller;



import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainController implements Observer{

    @FXML    private Button btn1;
    @FXML    private TextField tf_size;    
    @FXML    private Canvas canvas;
    @FXML    private Button addbtn;
    @FXML    private AnchorPane GeneticPane;
    @FXML	 private MutationController mutationController;
    private int size;
    private MazeController controller;
    
    @FXML
    void Generate(ActionEvent event) {
    		if(controller!=null)
    		{
    			controller.stopSimulation();
    		}
    		size=Integer.valueOf(tf_size.getText());
    		GraphicsContext gc=canvas.getGraphicsContext2D();
    		controller=new MazeController(gc,size,canvas.getWidth(), canvas.getHeight());
    		controller.addObserver(this);
    		controller.drawAll();
    }

    @FXML
    void Start(ActionEvent event) {
    	btn1.setDisable(true);
    	mutationController.setEditable(false);
    	controller.setMovers(mutationController.getPopulation());    	
    	controller.StartSimulation();
    }
    
    @FXML
    void Pause(ActionEvent event) {
    
    }
    @FXML
    void Save(ActionEvent event) {
    
    }
    @FXML
    void New(ActionEvent event) {
    	mutationController.setEditable(true);
    	btn1.setDisable(false);
    }

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("end of cycle");
		
	}
    
}
