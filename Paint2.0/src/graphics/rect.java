package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class rect extends shape{

	public rect(int x, int y, int w, int h, Color c) 
	{
		super(x, y , w, h, c);
	}

	public Shape copy() 
	{
		return null;
	}

	// draw rectangle
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.fillRect(x, y, width, height);
		
	}

	// check if coordinates of the mouse are on the rectangle
	public boolean isOn(int x, int y) 
	{
		if (x <= this.x + width && y <= this.y + height && x > this.x && y > this.y)
		{
			return true;
		}
		return false;
	}
	
	public void resize (int x1, int y1, int x2, int y2) 
	{
		// dragging in x direction
		// dragging to the left
		if (x1 < x2)
		{
			x = x1;
			width = x2 - x;
		}
		// to the right 
		else if (x1 > x2)
		{
			width = x1 - x;		
		}
		
		// dragging in y direction
		if (y1 < y2)
		{
			y = y1;
			height = y2 - y;
		}	
		else if (y1 > y2)
		{
			height = y1 - y;
		}
	}

}
