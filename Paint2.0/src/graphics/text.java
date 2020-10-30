package graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;

public class text extends shape 
{
	String input;

	// get input from the paint class
	public text(String input, int x, int y, int w, int h, Color c) 
	{
		super(x, y, w, h, c);
		this.input = input;
	}

	public Shape copy() {
		return null;
	}

	// draw text
	public void draw(Graphics g) 
	{
		g.setColor(c);
		g.drawString(input, x, y);
	}

	public boolean isOn(int x, int y) {
		return false;
	}

	// text can be moved, as I change coordinates instantly
	public void resize(int x1, int y1, int x2, int y2) 
	{
		x = x1;
		y = y1;
	}

}
