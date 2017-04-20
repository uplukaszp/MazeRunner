package MazeGenerator;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javafx.scene.canvas.GraphicsContext;

public class Generator {
	private int size;
	private Cell cells[][];
	private Cell current;
	private Cell next;
	private Random random=new Random();
	private Stack<Cell> stack=new Stack<>();
	public Generator(int size)
	{
		this.size=size;
		cells=new Cell[size][];

		for(int y=0;y<size;y++)
		{
			cells[y]=new Cell[size];
			for(int x=0;x<size;x++)
			{
				cells[y][x]=new Cell(x,y,size);
			}
		}
		current=cells[0][0];
		current.setVisited();
		while(true)
		{
			
			if(findNeighbour())
			{
				stack.push(current);
				current.removeWallBetween(next);
				current=next;
				current.setVisited();
				
			}
			else if(!stack.isEmpty())
			{
				current=stack.pop();
			}else 
			{
				break;
			}
		}
		
	}
	
	private boolean findNeighbour()
	{
		ArrayList<Cell> neighbours=new ArrayList<Cell>();
		int curX=current.getX();
		int curY=current.getY();
		if((curY-1)>=0&&(!cells[curY-1][curX].getVisited()))
		{
			neighbours.add(cells[current.getY()-1][curX]);
		}
		if(curX+1<size&&(!cells[curY][curX+1].getVisited()))
		{
			neighbours.add(cells[curY][curX+1]);
		}
		if(current.getY()+1<size&&(!cells[curY+1][curX].getVisited()))
		{
			neighbours.add(cells[curY+1][curX]);
		}
		if(curX-1>=0&&(!cells[curY][curX-1].getVisited()))
		{
			neighbours.add(cells[curY][curX-1]);
		}
		
		if(neighbours.isEmpty())return false;
		else 
			{
				next=neighbours.get(random.nextInt(neighbours.size()));
				return true;
			}
	}
	
	public void DrawMaze(GraphicsContext gc,double w,double h)
	{
		for(int y=0;y<size;y++)
		{
			for(int x=0;x<size;x++)
			{
				cells[y][x].drawCell(gc, w, h);
			}
		}
	}
	
}
