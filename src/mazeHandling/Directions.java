package mazeHandling;

import java.util.Random;

public enum Directions {
	up,left,down,right,nothing;
	
	private static Directions[] d=values();
	private static Random r=new Random();
	
	public static Directions getRandomDirection()
	{
		return d[r.nextInt(d.length-1)];
	}
	
}
