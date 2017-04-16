package MazeGenerator;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;

import com.sun.javafx.geom.Rectangle;

public class Cell {
	
	private int x,y;
	private int size;
	private boolean walls[]={true,true,true,true};
	
	public Cell(int x,int y,int size)
	{
		this.x=x;
		this.y=y;
		this.size=size;
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
				//g.drawLine(bounds.x, bounds.y, bounds.x+bounds.width, bounds.y);
				break;
			case 1:
				g.drawLine(bounds.x+bounds.width, bounds.y, bounds.x, bounds.y+bounds.height);
				break;

			case 2:
				//g.drawLine(bounds.x+bounds.width, bounds.y+bounds.height, bounds.x, bounds.y+bounds.height);
				break;

			case 3:
				//g.drawLine(bounds.x, bounds.y+bounds.height, bounds.x, bounds.y);
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
