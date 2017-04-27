package controller;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mazeHandling.AutomaticMover;

public class MutationController {

    @FXML private TextField seqLen;
    @FXML private TextField amount;
    @FXML private TextField mutation;
   
    public void setEditable(boolean editable)
    {
    	seqLen.setEditable(editable);
    	amount.setEditable(editable);
    	mutation.setEditable(editable);
    }
    
    public ArrayList<AutomaticMover> getPopulation()
    {
    	int lenght=Integer.valueOf(seqLen.getText());
    	int moversAmount=Integer.valueOf(amount.getText());
    	ArrayList<AutomaticMover> m=new ArrayList<>();
    	for(int i=0;i<moversAmount;i++)
    	{
    		m.add(new AutomaticMover(lenght));
    	}return m;
    }
}
