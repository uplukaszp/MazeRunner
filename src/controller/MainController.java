package controller;



import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mazeHandling.AutomaticMover;
import mazeHandling.Directions;

public class MainController implements Observer{

    @FXML    private Button btn1;
    @FXML    private Button startbtn;
    @FXML    private Button pausebtn;
    @FXML    private Button newbtn;
    @FXML    private TextField tf_size;    
    @FXML    private Canvas canvas;
    @FXML    private AnchorPane GeneticPane;
    @FXML	 private EvolutionController evolutionController;
    private int size;
    private MazeController controller;
    
    @FXML
    public void initialize() 
    {
    	startbtn.setDisable(true);
    	pausebtn.setDisable(true);
    	newbtn.setDisable(true);
    }
    @FXML
    void Generate(ActionEvent event) {
    	
    	try {
    		size=Integer.valueOf(tf_size.getText());
    		evolutionController.setsize(size);
    		GraphicsContext gc=canvas.getGraphicsContext2D();
    		controller=new MazeController(gc,size,canvas.getWidth(), canvas.getHeight());
    		controller.addObserver(this);
    		controller.drawAll();
    		startbtn.setDisable(false);
		} catch (Exception e) {
			alert(e.getMessage());
		}
    		
    		
    }

    @FXML
    void Start(ActionEvent event) {
    	btn1.setDisable(true);
    	tf_size.setDisable(true);
    	pausebtn.setDisable(false);
    	newbtn.setDisable(false);
    	startbtn.setDisable(true);
    	evolutionController.setEditable(false);
    	try
    	{
    	controller.setMovers(evolutionController.getRandomPopulation());  
    	controller.StartSimulation();
    	}
    	catch(NumberFormatException e)
    	{
    		alert(e.getMessage());
    		initialize();
    		btn1.setDisable(false);
    	}
    	
    	
    }
    
    @FXML
    void Pause(ActionEvent event) {
    	if(pausebtn.getText().equals("Pause"))
    	{
    		controller.stopSimulation();
    		pausebtn.setText("Continue");
    	}
    	else
    	{
    		controller.StartSimulation();
    		pausebtn.setText("Pause");
    	}
    }
   
    @FXML
    void New(ActionEvent event) {
    	
    	controller.stopSimulation();
    	controller.clear();
    	startbtn.setDisable(false);
    	pausebtn.setDisable(true);
    	pausebtn.setText("Pause");
    	evolutionController.setEditable(true);
    	btn1.setDisable(false);
    	tf_size.setDisable(false);
    	
    }

	@Override
	public void update(Observable o, Object arg) {
		
		ArrayList<AutomaticMover> newPopulation,oldPopulation;
		oldPopulation=controller.getMovers();
		newPopulation=evolutionController.Evolve(oldPopulation);
		AutomaticMover bestMover=evolutionController.getBest();
		controller.setMovers(newPopulation);
		controller.drawBestPath(bestMover);
		controller.StartSimulation();
		
		
		
	}
	private void alert(String msg)
	{

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText(null);
		alert.setContentText("Problem " +msg);

		alert.showAndWait();
	}
    
}
