package graphics;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public class text {

	public text(String input, int width, int height, Graphics g) 
	{
		g.drawString(input, width, height);
	}

}
