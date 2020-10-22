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
		g.fillOval(x - width/2, y - width/2, width, width);
		
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
	//y2 and x2 are initial coordinates of the rectangle around the oval
	public void resize(int x1, int x2, int y1, int y2) 
	{
		int dist = (int) Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
		System.out.println(x1 + " " + x2);
		
			width = (int) (dist*2);
		
		
		
		
		
		
	}

}
