package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import javax.swing.JTextArea;

public class text extends shape {
	
	
	//passing input
	public text(String input, int x, int y, int w, int h, Color c) 
	{
		super(x, y, w, h, c);
	}

	@Override
	public Shape copy() {
		return null;
	}

	//how do i pass input here?
	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		//g.drawString(input, width, height);
		
	}

	@Override
	public boolean isOn(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resize(int x1, int y1, int x2, int y2) {
		
	}

}
