package MazeGenerator;



import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;



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
	public void drawCell(GraphicsContext gc,double w,double h)
	{
		
		
		Rectangle bounds=calculateCellBounds(w,h);
		
		for(int i=0;i<walls.length;i++)
		{
			if(walls[i])
			{
				gc.setStroke(Color.BLACK);
			}else 
			{
				gc.setStroke(new Color(0,0,0,0.0));
			}
			switch (i) {
			case 0:
				gc.strokeLine(bounds.getX(), bounds.getY(), bounds.getX()+bounds.getWidth(), bounds.getY());
				break;
			case 1:
				gc.strokeLine(bounds.getX()+bounds.getWidth(), bounds.getY(), bounds.getX()+bounds.getWidth(), bounds.getY()+bounds.getHeight());
				break;

			case 2:
				gc.strokeLine(bounds.getX()+bounds.getWidth(), bounds.getY()+bounds.getHeight(), bounds.getX(), bounds.getY()+bounds.getHeight());
				break;

			case 3:
				gc.strokeLine(bounds.getX(), bounds.getY()+bounds.getHeight(), bounds.getX(), bounds.getY());
				break;

			default:
				break;
			}
		}
		
	}
	private Rectangle calculateCellBounds(double w,double h)
	{
		Rectangle r=new Rectangle();
		r.setX(x*(w/size));
		r.setY(y*(h/size));
		r.setWidth(w/size);
		r.setHeight(h/size);
		return r;
		
	}
}
