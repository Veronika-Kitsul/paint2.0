package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class circle extends shape
{

	public circle(int x, int y, int w, Color c) {
		super(x, y, w, w, c);
	}

	@Override
	public Shape copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		g.fillOval(x, y, width, height);
		
	}

	@Override
	public boolean isOn(int x, int y) {
		int cx = this.x + width/2;
		int cy = this.y + width/2;
		if (Math.sqrt(Math.pow(cx-x, 2) + Math.pow(cy-y, 2)) <= width/2)
		{
			return true;
		}
		return false;
	}

	@Override
	public void resize(int x1, int x2, int y1, int y2) 
	{
		if (x1 < x2)
		{
			x = x1;
			width = x2 - x;
		}
		else if (x1 > x2)
		{
			width = x1 - x;		
		}
		
		if (y1 < y2)
		{
			y = y1;
			height = y2 - y1;
		}	
		else if (y1 > y2)
		{
			height = y1 - y2;
		}
	}

}
