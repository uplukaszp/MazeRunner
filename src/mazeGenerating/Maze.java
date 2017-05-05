package mazeGenerating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import com.sun.javafx.geom.Point2D;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import mazeHandling.Directions;

public class Maze {
	private final double chanceForRemove=0.035;
	private int size;
	private Cell cells[][];
	private Cell current;
	private Cell next;
	private Random random=new Random();
	private Stack<Cell> stack=new Stack<>();
	private Directions bestSequence[];
	public Maze(int size)
	{
		bestSequence=new Directions[0];
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
				tryRemoveRandomly();
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
	private void tryRemoveRandomly()
	{		
		if(random.nextInt(size)<chanceForRemove*size*size)
		{
				if(findNeighbour())
				{
					current.removeWallBetween(next);
				}
		}
	}
	public void drawMaze(GraphicsContext gc,double w,double h)
	{
		gc.clearRect(0, 0, w, h);
		for(int y=0;y<size;y++)
		{
			for(int x=0;x<size;x++)
			{
				cells[y][x].drawCell(gc, w, h);
			}
		}
		drawBestPath(gc, w, h);
	}
	public void setBestSequence(Directions seq[])
	{
		bestSequence=seq;
	}
	public void drawBestPath(GraphicsContext gc,double w,double h)
	{
		double cellWidth=w/(size);
		double cellHeight=h/(size);
		double lineHPos=cellWidth/2.0;
		double lineVPos=cellHeight/2.0;
		Point2D p=new Point2D(0,0);
		
		gc.setStroke(Color.RED);
		gc.beginPath();
		gc.moveTo(lineHPos, lineVPos);
		System.out.println(Arrays.toString(bestSequence));
		for(int i=0;i<bestSequence.length;i++)
		{
			System.out.print("[ "+p.x+", "+p.y+" ]");
			if(p.x==size&&p.y==size)
			{
				break;
			}
			if(isPosibleMove(bestSequence[i], p))
			{
				switch (bestSequence[i]) {
				case up:
					p.y--;		
					break;
				case left:
					p.x--;
					break;
				case down:
					p.y++;
					break;
				case right:
					p.x++;
					break;
				default:
					break;
				}
				gc.lineTo(p.x*cellWidth+lineHPos,p.y*cellHeight+lineVPos);
			}
		}
		System.out.println();
		gc.stroke();
	}
	public boolean isPosibleMove(Directions d,Point2D from)
	{
		return !cells[(int) from.y][(int) from.x].isWall(d);			
	}
}
