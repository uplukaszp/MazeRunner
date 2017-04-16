package MazeGenerator;

import java.awt.Graphics2D;

public class Generator {
	private int size;
	private Cell cells[];
		
	public Generator(int size)
	{
		this.size=size;
		cells=new Cell[size*size];
		for(int y=0;y<size;y++)
		{
			for(int x=0;x<size;x++)
			{
				cells[y*size+x]=new Cell(x,y,size);
			}
		}
	}
	public void DrawMaze(Graphics2D g,int w,int h)
	{
		for(int i=0;i<cells.length;i++)
		{
			cells[i].drawCell(g,w,h);
		}
	}
}
