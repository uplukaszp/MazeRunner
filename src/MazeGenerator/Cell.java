package MazeGenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;



public class Cell {
	
	private int x,y;
	private int size;
	private boolean walls[]={true,true,true,true};
	boolean visited=false;
	
	public Cell(int x,int y,int size)
	{
		this.x=x;
		this.y=y;
		this.size=size;
	}
	public void setVisited()
	{
		visited=true;
	}
	public boolean getVisited()
	{
		return visited;
	}
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	public void removeWallBetween(Cell c)
	{
		//TODO throw when cells are not neighbours
		if(y>c.y)//c on top
		{
			walls[0]=false;
			c.walls[2]=false;
		}
		if(x<c.x)//c on right
		{
			walls[1]=false;
			c.walls[3]=false;
		}
		if(y<c.y)//c on bottom
		{
			walls[2]=false;
			c.walls[0]=false;
		}
		if(x>c.x) //c on left
		{
			walls[3]=false;
			c.walls[1]=false;
		}
		
		
		
	}
	public void drawCell(Graphics2D g,int w,int h)
	{
		Color c=g.getColor();
		Rectangle bounds=calculateCellBounds(w,h);
		
		for(int i=0;i<walls.length;i++)
		{
			if(walls[i])
			{
				g.setColor(c);
			}else 
			{
				g.setColor(new Color(0,0,0,0));
			}
			switch (i) {
			case 0:
				g.drawLine(bounds.x, bounds.y, bounds.x+bounds.width, bounds.y);
				break;
			case 1:
				g.drawLine(bounds.x+bounds.width, bounds.y, bounds.x+bounds.width, bounds.y+bounds.height);
				break;

			case 2:
				g.drawLine(bounds.x+bounds.width, bounds.y+bounds.height, bounds.x, bounds.y+bounds.height);
				break;

			case 3:
				g.drawLine(bounds.x, bounds.y+bounds.height, bounds.x, bounds.y);
				break;

			default:
				break;
			}
		}
		g.setColor(c);
	}
	private Rectangle calculateCellBounds(int w,int h)
	{
		Rectangle r=new Rectangle();
		r.x=x*(w/size);
		r.y=y*(h/size);
		r.width=w/size;
		r.height=h/size;
		return r;
		
	}
}
