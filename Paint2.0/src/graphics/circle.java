package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class circle extends shape
{

	public circle(int x, int y, int w, Color c) {
		super(x, y, w, w, c);
	}

	public Shape copy() 
	{
		return null;
	}

	// draw circle starting from the center
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x - width/2, y - width/2, width, width);
		
	}
	
	// check if the coordinates of the place where mouse is pressed are on the circle
	public boolean isOn(int x, int y) 
	{
		if (Math.sqrt(Math.pow(this.x-x, 2) + Math.pow(this.y-y, 2)) <= width/2)
		{
			return true;
		}
		return false;
	}

	// resize the circle when mouse is dragged
	public void resize(int x1, int y1, int x2, int y2) 
	{
		int dist = (int) Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
		width = (int) (dist*2);	
	}

}




