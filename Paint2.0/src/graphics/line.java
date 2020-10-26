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
	
	@Override
	public Shape copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
		//should be two other values for the final
		g.setColor(c);
		g.drawLine(x, y, width, height);
		
	}

	@Override
	public boolean isOn(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resize(int x1, int y1, int x2, int y2) {
		width = (int) Math.sqrt(Math.pow(x2-x1, 2)+ Math.pow(y2-y1, 2));
		x = x1;
		x = x2;
	}

}
