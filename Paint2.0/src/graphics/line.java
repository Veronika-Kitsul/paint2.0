package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class line extends shape
{
	public line(int x, int y, int w, int h, Color c) 
	{
		super(x, y, w, h, c);
	}
	
	public Shape copy() 
	{
		return null;
	}

	// draw a line
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.drawLine(x, y, width, height);
		
		
	}
	
	public boolean isOn(int x, int y) 
	{	
		return false;
	}

	// resize the line
	public void resize(int x1, int y1, int x2, int y2) 
	{
		width = x1;
		height = y1;
		x = x2;
		y = y2;	
	}

}
