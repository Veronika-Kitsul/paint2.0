package graphics;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;



public class Paint2_0 
{
	
	public final int width = 800, height = 800;
	private Image img;
	boolean isrect = false;
	boolean iscircle = false;
	ArrayList<shape> shapes = new ArrayList<shape>(); 

	public Paint2_0()
	{
		//setting up new frame default options
		JFrame frame = new JFrame();
			
		
			
			
		//creating panels for holding buttons, drawarea, and one overall panel
		JPanel panel = new JPanel();
		JPanel buttons = new JPanel();
		JPanel drawarea = new JPanel()
				{
					public void paint(Graphics g)
					{
						g.setColor(Color.white);
						g.fillRect(0, 0, width, height);
						for (int i = 0; i < shapes.size(); i++)
						{
							shapes.get(i).draw(g);
						}
					}
				};
		
		
		
		//this is nooot working yet
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(BorderFactory.createTitledBorder("Paint"));
		
		//adding panels to the frame
		frame.add(panel);
		panel.add(buttons);
		panel.add(drawarea);
		buttons.setPreferredSize(new Dimension(width, height/5));
		drawarea.setPreferredSize(new Dimension(width, height - (height/5)));
		
		
		//creating and adding buttons to the screen
		JButton rectangle = new JButton("Rectangle");
		JButton line = new JButton("Line");
		JButton circle = new JButton("Circle");
		buttons.add(rectangle);
		buttons.add(line);
		buttons.add(circle);
		
		
		//is button clicked and if yes - change varible rect to true
		rectangle.addActionListener (new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isrect = true;
				iscircle = false;
			}
		});
		
		//is button clicked and if yes - change varible circle to true
		circle.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				iscircle = true;
				isrect = false;
			}
			
		});
		
		
		
		
		drawarea.addMouseListener(new MouseListener()
		{

					@Override
					public void mouseClicked(MouseEvent e) 
					{
						
					}

					public void mousePressed(MouseEvent e) 
					{
						if (isrect == true)
						{
							shapes.add(new rect(e.getX(), e.getY(), 40, 40, Color.black));
						}
						else if (iscircle == true)
						{
							
							shapes.add(new circle(e.getX(), e.getY(), 40, 40, Color.blue));
						}
						frame.getContentPane().repaint();
					}

					public void mouseReleased(MouseEvent e) 
					{
						
					}

					public void mouseEntered(MouseEvent e) 
					{
						
					}

					public void mouseExited(MouseEvent e) 
					{
						
					}
			
		});		
		
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setFocusable(true);
	}		
	

	
	public static void main (String args[])
	{
		new Paint2_0();
	}
}
