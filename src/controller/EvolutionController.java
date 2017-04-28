package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import com.sun.javafx.geom.Point2D;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import mazeHandling.AutomaticMover;
import mazeHandling.Directions;

public class EvolutionController {

    @FXML private TextField seqLen;
    @FXML private TextField amount;
    @FXML private TextField mutation;
   
    private int size;
    private Random r=new Random();
    private double mutateRatio;
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
    public ArrayList<AutomaticMover> getRandomPopulation()throws NumberFormatException
    {
    	
    	int lenght=Integer.valueOf(seqLen.getText());
    	int moversAmount=Integer.valueOf(amount.getText());
    	mutateRatio=Double.valueOf(mutation.getText())/1000.0;
    	ArrayList<AutomaticMover> m=new ArrayList<>();
    	for(int i=0;i<moversAmount;i++)
    	{
    		m.add(new AutomaticMover(lenght));
    	}
    	return m;
    }
    public ArrayList<AutomaticMover> Evolve(ArrayList<AutomaticMover> oldPopulation)
    {
    	ArrayList<Integer> evaluations=Evaluate(oldPopulation);
    	ArrayList<AutomaticMover> newPopulation=CrossOver(oldPopulation, evaluations);
    	
		return newPopulation;
    	
    }
    
    private ArrayList<Integer> Evaluate(ArrayList<AutomaticMover> oldPopulation)
    {
    	ArrayList<Integer> fitness=new ArrayList<>();
    	
    	
    	for(AutomaticMover mover:oldPopulation)
    	{
    		fitness.add(calculateFitness(mover));
    	}
    	return fitness;
    	
    }
    private int calculateFitness(AutomaticMover m)
    {
    	Point2D p=m.getPos();
    	int distance=1+(int) (Math.sqrt((p.x-(size-1))*(p.x-(size-1))+(p.y-(size-1))*(p.y-(size-1))));
    	double fitness=100.0/(distance*m.getNumberOfMoves());
    	int normalizedfitness=normalize(fitness,0,1,0,100);
    	return normalizedfitness;
    }
   
    private ArrayList<AutomaticMover> CrossOver(ArrayList<AutomaticMover>  population,ArrayList<Integer> evaluations)
    {
    	AutomaticMover parent1,parent2;
    	ArrayList<AutomaticMover> newPopulation=new ArrayList<>();
    	for(int i=0;i<population.size()-1;i++)
    	{
    		parent1=population.get(i);
    		parent2=population.get(i+1);
    		newPopulation.add(Combine(parent1,parent2,evaluations.get(i),evaluations.get(i+1)));
    	}
    	parent1=population.get(0);
    	parent2=population.get(population.size()-1);
    	newPopulation.add(Combine(parent1, parent2, evaluations.get(0), evaluations.get(evaluations.size()-1)));
		return newPopulation;
		
    	
    }
    private AutomaticMover Combine(AutomaticMover m1,AutomaticMover m2,int fitness1,int fitness2)
    {
    	Directions genes1[]=m1.getMoveSequence();
    	Directions genes2[]=m2.getMoveSequence();
    	Directions newGenes[]=new Directions[genes1.length];
    	for(int i=0;i<genes1.length;i++)
    	{
    		int random=r.nextInt(fitness1+fitness2);
    		if(random<=fitness1)
    		{
    			newGenes[i]=genes1[i];
    		}else
    		{
    			newGenes[i]=genes2[i];
    		}
    	}
    	AutomaticMover newMover=new AutomaticMover(newGenes);
    	Mutate(newMover);
		return newMover;
    	
    }
    private int normalize(double value, double min, double max,double newMin,double newMax)
    {
    	return (int) (((value-min)/(max-min))*(newMax-newMin)+newMin);
    }
   
    private void Mutate(AutomaticMover mover)
    {
    	
    	double chance;
    	Directions genes[]=mover.getMoveSequence();
    	for(int i=0;i<genes.length;i++)
    	{
    		chance=r.nextDouble();
    		if(chance<=mutateRatio)
    		{
    			genes[i]=Directions.getRandomDirection();
    			System.out.println("mutated ch: "+chance+" ratio: "+mutateRatio);
    		}
    	}
    	mover=new AutomaticMover(genes);
    }
}
