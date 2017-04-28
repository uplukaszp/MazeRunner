package controller;



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

public class MainController implements Observer{

    @FXML    private Button btn1;
    @FXML    private Button startbtn;
    @FXML    private Button pausebtn;
    @FXML    private Button newbtn;
    @FXML    private Button savebtn;
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
    	savebtn.setDisable(true);
    	
    }
    @FXML
    void Generate(ActionEvent event) {
    		if(controller!=null)
    		{
    			controller.stopSimulation();
    		}
    		size=Integer.valueOf(tf_size.getText());
    		evolutionController.setsize(size);
    		GraphicsContext gc=canvas.getGraphicsContext2D();
    		controller=new MazeController(gc,size,canvas.getWidth(), canvas.getHeight());
    		controller.addObserver(this);
    		controller.drawAll();
    		startbtn.setDisable(false);
    }

    @FXML
    void Start(ActionEvent event) {
    	btn1.setDisable(true);
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
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Problem " +e.getLocalizedMessage());

    		alert.showAndWait();
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
    void Save(ActionEvent event) {
    
    }
    @FXML
    void New(ActionEvent event) {
    	controller.stopSimulation();
    	startbtn.setDisable(false);
    	pausebtn.setDisable(true);
    	pausebtn.setText("Pause");
    	evolutionController.setEditable(true);
    	btn1.setDisable(false);
    }

	@Override
	public void update(Observable o, Object arg) {
		
		controller.setMovers(evolutionController.Evolve(controller.getMovers()));
		controller.StartSimulation();
		
	}
    
}
