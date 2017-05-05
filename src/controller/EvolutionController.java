package controller;

import java.util.ArrayList;
import java.util.Random;


import com.sun.javafx.geom.Point2D;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mazeHandling.AutomaticMover;
import mazeHandling.Directions;

public class EvolutionController {

    @FXML private TextField seqLen;
    @FXML private TextField amount;
    @FXML private TextField mutation;
    @FXML private Label itLabel;
    @FXML private Label bestLabel;
   
    private int size;
    private Random r=new Random();
    private double mutateRatio;
    private int iterations=0;
	private int bestScore=0;
	private double bestfit;
	private int moversAmount;
	private AutomaticMover bestMover;
	private String orgBestLabel;
	private String orgItLabel;
	
	@FXML 
	public void initialize()
	{
		orgBestLabel=bestLabel.getText();
		orgItLabel=itLabel.getText();
	}
	
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
    private void updateIt()
    {
    	itLabel.setText(orgItLabel+iterations);
    }
    private void updateBest()
    {
    	bestLabel.setText(orgBestLabel+bestScore);
    }
    public ArrayList<AutomaticMover> getRandomPopulation()throws NumberFormatException,BadDataException
    {
    	int lenght=Integer.valueOf(seqLen.getText());
    	if(lenght<0)throw new BadDataException(lenght);
    	moversAmount=Integer.valueOf(amount.getText());
    	if(moversAmount<2)throw new BadDataException(moversAmount);
    	bestScore=moversAmount;
    	mutateRatio=Double.valueOf(mutation.getText())/1000.0;
    	if(mutateRatio<0)throw new BadDataException(mutateRatio);
    	ArrayList<AutomaticMover> m=new ArrayList<>();
    	for(int i=0;i<moversAmount;i++)
    	{
    		m.add(new AutomaticMover(lenght));
    	}
    	bestfit=0;
    	iterations=0;
    	updateIt();
    	return m;
    }
    public ArrayList<AutomaticMover> Evolve(ArrayList<AutomaticMover> oldPopulation)
    {
    	iterations++;
    	bestScore=moversAmount;
    	bestfit=0;
    	ArrayList<Integer> evaluations=Evaluate(oldPopulation);
    	ArrayList<AutomaticMover> newPopulation=CrossOver(oldPopulation, evaluations);
    	updateBest();
    	updateIt();
		return newPopulation;	
    }
    
    private ArrayList<Integer> Evaluate(ArrayList<AutomaticMover> oldPopulation)
    {
    	ArrayList<Double> fitness=new ArrayList<>();
    	for(AutomaticMover mover:oldPopulation)
    	{
    		fitness.add(calculateFitness(mover));
    	}
    	double min=Double.MAX_VALUE, max=Double.MIN_VALUE;
    	for(Double d:fitness)
    	{
    		if(min>d)min=d;
    		if(max<d)max=d;
    	}
    	ArrayList<Integer>normalizedFitness=new ArrayList<>();
    	for(Double d:fitness)
    	{
    		normalizedFitness.add(normalize(d, min, max, 1.0, 100.0));
    	}
    	return normalizedFitness;
    	
    }
    private double calculateFitness(AutomaticMover m)
    {
    	Point2D p=m.getPos();
    	int distance=1+(int) (Math.sqrt((p.x-(size-1))*(p.x-(size-1))+(p.y-(size-1))*(p.y-(size-1))));
    	double fitness=1.0/(distance*m.getNumberOfMoves());
    	if(fitness>bestfit)
    	{
    		bestScore=m.getHowManyMoves();
    		bestfit=fitness;
    		bestMover=m;
    	}
    	return fitness;
    }
    private int normalize(double value, double min, double max,double newMin,double newMax)
    {
    	double norm=(int) ((((value-min)/(max-min))*(newMax-newMin))+newMin);
    	if(norm==0)norm=1;
    	return (int)norm;
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
    		}
    	}
    	mover=new AutomaticMover(genes);
    }
    
	public AutomaticMover getBest() {
		return bestMover;
	}
}
