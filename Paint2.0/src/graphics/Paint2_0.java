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
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;



public class Paint2_0 
{
	
	public final int width = 800, height = 800;
	private Image img;
	boolean isrect = false;
	boolean iscircle = false;
	boolean isdelete = false;
	int initialx;
	int initialy;
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
		JButton delete = new JButton("Delete");
		buttons.add(rectangle);
		buttons.add(line);
		buttons.add(circle);
		buttons.add(delete);
		
		
		//is button clicked and if yes - change varible rect to true
		rectangle.addActionListener (new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isrect = true;
				iscircle = false;
				isdelete = false;
			}
		});
		
		//is button clicked and if yes - change varible circle to true
		circle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				iscircle = true;
				isrect = false;
				isdelete = false;
			}
			
		});
		
		delete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				isdelete = true;
				isrect = false;
				iscircle = false;
			}	
		});
	
		
		drawarea.addMouseListener(new MouseListener()
		{

					@Override
					public void mouseClicked(MouseEvent e) 
					{
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						if (isrect == true)
						{
							shapes.add(new rect(e.getX(), e.getY(), 0, 0, Color.black));
							initialx = e.getX();
							initialy = e.getY();
						}
						else if (iscircle == true)
						{
							shapes.add(new circle(e.getX(), e.getY(), 0, Color.blue));
							initialx = e.getX();
							initialy = e.getY();
						}
						else if (isdelete == true)
						{
							for (int i = 0; i < shapes.size(); i++)
							{
								if (shapes.get(i).isOn(e.getX(), e.getY()))
								{
									shapes.remove(i);
									break;
								}
					
							}
						}
						
						frame.getContentPane().repaint();
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
			
				});
		
		drawarea.addMouseMotionListener(new MouseMotionListener()
				{

					@Override
					public void mouseDragged(MouseEvent e) {
						
						shapes.get(shapes.size()-1).resize(e.getX(), e.getY(), initialx, initialy);
						frame.getContentPane().repaint();
					}

					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						
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
