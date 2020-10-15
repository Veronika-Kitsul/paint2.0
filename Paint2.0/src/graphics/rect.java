package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class rect extends shape{

	public rect(int x, int y, int w, int h, Color c) {
		super(x, y , w, h, c);
	}

	@Override
	public Shape copy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, width, height);
		
	}

	@Override
	public boolean isOn(int x, int y) {
		if (x <= x + width && y <= y + height && x > this.x && y > this.y)
		{
			return true;
		}
		return false;
	}
	

	@Override
	public void resize(int x1, int x2, int y1, int y2) {
		// TODO Auto-generated method stub
		
	}

}
