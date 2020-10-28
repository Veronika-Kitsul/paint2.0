package graphics;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.colorchooser.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComponent;



public class Paint2_0 
{
	// variables for size of the window
	public final int width = 800, height = 800;
	
	// booleans to hold which button is currently pressed
	boolean isrect = false;
	boolean iscircle = false;
	boolean isdelete = false;
	boolean isline = false;
	boolean istext = false;
	boolean isundo = false;
	
	// initial coordinates of the mouse for any shape
	int initialx;
	int initialy;
	
	
	public JTextArea input;
	public JTextArea fontsize;
	Color onColor = Color.black;
	ArrayList<shape> shapes = new ArrayList<shape>(); 

	public Paint2_0()
	{
		//setting up new frame 
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
		
		
		//laying out on y axe, setting title and borders
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(BorderFactory.createTitledBorder("Paint"));
		
		//adding panels to the frame, setting sizes
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
		JButton colorchooser = new JButton("Choose Color");
		JButton text = new JButton("Text");
		JButton undo = new JButton("Undo");
		buttons.add(rectangle);
		buttons.add(line);
		buttons.add(circle);
		buttons.add(delete);
		buttons.add(colorchooser);
		buttons.add(text);
		buttons.add(undo);
		
		
		//input area for the text button;
		input = new JTextArea(" ");
		input.setEditable(true);
		input.setPreferredSize(new Dimension(width/8, height/20));
		buttons.add(input);
		
		fontsize = new JTextArea();
		fontsize.setEditable(true);
		fontsize.setPreferredSize(new Dimension(width/12, height/20));
		buttons.add(fontsize);
		
		//implementing action listener for color choosing button
		colorchooser.addActionListener(new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e) 
				{
					Color initialColor = Color.BLACK;
					onColor = JColorChooser.showDialog(buttons, "Choose color", initialColor);
				}
			});
		
		
		
		//is button clicked and if yes - change varible rect to true
		rectangle.addActionListener (new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isrect = true;
				iscircle = false;
				isdelete = false;
				isline = false;
				istext = false;
				isundo = false;
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
				isline = false;
				istext = false;
				isundo = false;
			}
			
		});
		
		//if delete button pressed, set isdelete to true, and everything else to false
		delete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				isdelete = true;
				isrect = false;
				iscircle = false;
				isline = false;
				istext = false;
				isundo = false;
			}	
		});

		//if line button is pressed, set isline to true, and everything else to false
		line.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isline = true;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = false;
				isundo = false;
			}
		});
		
		//if text button is pressed, then istext = true, and everything else = false
		text.addActionListener(new ActionListener ()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				isline = false;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = true;
				isundo = false;
			}
		});
		
		// if undo button is pressed, undo = true, everything else = false
		undo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isundo = true;
				isline = false;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = false;
				shapes.remove(shapes.size() - 1);
				frame.getContentPane().repaint();
			}	
		});
		
		// Mouse listener for the drawarea to see things on the screen
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
							shapes.add(new rect(e.getX(), e.getY(), 0, 0, onColor));
							initialx = e.getX();
							initialy = e.getY();
						}
						else if (iscircle == true)
						{
							shapes.add(new circle(e.getX(), e.getY(), 0, onColor));
							initialx = e.getX();
							initialy = e.getY();
						}
						else if (isline == true)
						{
							shapes.add(new line(e.getX(), e.getY(), e.getX(), e.getY(), onColor));
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
						else if (istext == true)
						{
							if (input.getText() != "" && input.getText() != " ")
							{
								// change 40s to width and height of the text - but how
								shapes.add(new text(input.getText(), e.getX(), e.getY(), 40, 40, onColor));
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

					// if mouse is dragged, resize the shape
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
			
		
		// setting up basic frame features
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
