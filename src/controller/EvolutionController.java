package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import com.sun.javafx.geom.Point2D;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mazeHandling.AutomaticMover;

public class EvolutionController {

    @FXML private TextField seqLen;
    @FXML private TextField amount;
    @FXML private TextField mutation;
   
    private int size;
    public void setEditable(boolean editable)
    {
    	seqLen.setDisable(!editable);
    	amount.setDisable(!editable);
    	mutation.setDisable(!editable);
    }
    public void setsize(int size)
    {
    	this.size=size;
    }
    public ArrayList<AutomaticMover> getPopulation()
    {
    	int lenght=Integer.valueOf(seqLen.getText());
    	int moversAmount=Integer.valueOf(amount.getText());
    	ArrayList<AutomaticMover> m=new ArrayList<>();
    	for(int i=0;i<moversAmount;i++)
    	{
    		m.add(new AutomaticMover(lenght));
    	}
    	return m;
    }
    public ArrayList<AutomaticMover> Evolve(ArrayList<AutomaticMover> oldPopulation)
    {
    	ArrayList<AutomaticMover> bestFromOld=Evaluate(oldPopulation);
    	
		return bestFromOld;
    	
    }
    
    private ArrayList<AutomaticMover> Evaluate(ArrayList<AutomaticMover> oldPopulation)
    {
    	Map<AutomaticMover, Double> m=new HashMap<>();
    	ArrayList<AutomaticMover> newPopulation=new ArrayList<>();
    	
    	for(AutomaticMover mover:oldPopulation)
    	{
    		m.put(mover,calculateFitness(mover));
    	}
		double maxFit=0;
		AutomaticMover temp = null;
    	for(Map.Entry<AutomaticMover, Double> entry:m.entrySet())
    	{
    		for(AutomaticMover mover:newPopulation)
    		{
    			if(entry.getKey()!=mover)
    			{
		    		if(entry.getValue()>maxFit)
		    		{
		    			temp=entry.getKey();
		    		}
    			}
    		}
    		newPopulation.add(temp);
    	}
		
    	return newPopulation;
    	
    }
    private double calculateFitness(AutomaticMover m)
    {
    	Point2D p=m.getPos();
    	int distance=1+(int) (Math.sqrt((p.x-(size-1))*(p.x-(size-1))+(p.y-(size-1))*(p.y-(size-1))));
    	return m.getNumberOfMoves()/distance;
    }
    private ArrayList<AutomaticMover> CrossOver(ArrayList<AutomaticMover> population)
    {
		return population;
    	
    }
}
