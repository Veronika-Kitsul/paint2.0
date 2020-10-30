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
import javax.swing.JLabel;
import java.awt.*;
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
	boolean isfront = false;
	boolean isback = false;
	
	// initial coordinates of the mouse for any shape
	int initialx;
	int initialy;
	
	// Text area for text input and initial color for any shape that we create
	public JTextArea input;
	Color onColor = Color.black;
	
	// creating an array list of literally everything drawing area holds
	ArrayList<shape> shapes = new ArrayList<shape>(); 

	public Paint2_0()
	{
		//setting up new frame 
		JFrame frame = new JFrame();
			
		//creating panels for holding buttons, drawing area, and one overall panel
		JPanel panel = new JPanel();
		JPanel buttons = new JPanel();
		JPanel drawarea = new JPanel()
				{
					public void paint(Graphics g)
					{
						// a white rectangle for draw area, so it looks nice
						g.setColor(Color.white);
						g.fillRect(0, 0, width, height);
						
						//drawing every shape
						for (int i = 0; i < shapes.size(); i++)
						{
							shapes.get(i).draw(g);
						}
					}
				};
		
		
		//laying out the panel on y-axe, so everything is seen as vertical boxes; setting title and borders
		BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxlayout);
		panel.setBorder(BorderFactory.createTitledBorder("Paint"));
		
		//adding panels to the frame
		frame.add(panel);
		panel.add(buttons);
		panel.add(drawarea);

		//setting sizes for buttons and draw area, so it looks nice and even
		buttons.setPreferredSize(new Dimension(width, height / 5));
		drawarea.setPreferredSize(new Dimension(width, height - (height / 5)));
		
		//setting layout for buttons panel, so every row will be laid out vertically
		BoxLayout buttonslay = new BoxLayout(buttons, BoxLayout.Y_AXIS) ;
		buttons.setLayout(buttonslay);
		
		// I have three rows of buttons
		// Here comes setting up of the first row: creating the panel, adding it to the buttons panel that stores all rows
			JPanel buttonsrow1 = new JPanel();
			buttons.add(buttonsrow1);
			
			// creating three buttons for rectangle, circle, line and adding them to the row
			JButton rectangle = new JButton("Rectangle");
			JButton line = new JButton("Line");
			JButton circle = new JButton("Circle");
			buttonsrow1.add(rectangle);
			buttonsrow1.add(line);
			buttonsrow1.add(circle);	
		
		
		// creating row 2 of buttons, and adding it to the panel 
			JPanel buttonsrow2 = new JPanel();
			buttons.add(buttonsrow2);
			
			// creating text button and adding it to the row
			JButton text = new JButton("Text");
			buttonsrow2.add(text);
			
			// creating label for the input area, so user knows what do with it
			JLabel textinput = new JLabel("Input your text: ");
			buttonsrow2.add(textinput);
			
			// creating input area for the text button, adding it to the row, setting input area size, making it editable (very important)
			input = new JTextArea(" ");
			input.setEditable(true);
			input.setPreferredSize(new Dimension(width/7, height/24));
			buttonsrow2.add(input);
		
		
		
		// creating row 3 of buttons
			// setting up the row panel and adding it to the panel of buttons
			JPanel buttonsrow3 = new JPanel();	
			buttons.add(buttonsrow3);
			
			// creating 7 buttons: for deleting shapes, choosing color, undoing the action, taking some figure to the front or to the back
			// dark mode feature button and light mode button to go back from dark mode, if you wish
			JButton delete = new JButton("Delete");
			JButton colorchooser = new JButton("Choose Color");
			JButton undo = new JButton("Undo");
			JButton front = new JButton("Front");
			JButton back = new JButton("Back");
			JButton darkmode = new JButton ("Dark mode");
			JButton lightmode = new JButton ("Light mode");
			
			//adding every button to the row 3 of buttons
			buttonsrow3.add(delete);
			buttonsrow3.add(colorchooser);
			buttonsrow3.add(undo);
			buttonsrow3.add(front);
			buttonsrow3.add(back);	
			buttonsrow3.add(darkmode);
			buttonsrow3.add(lightmode);
		
			
			
		// getting the original colors of panels to use it in light mode later
		Color panelcolor = panel.getBackground();
		Color buttonsRowsColor = buttonsrow2.getBackground();
		
		// if you click on the dark mode button, it will set backgrounds of buttons panel and overall panel to dark palette
		darkmode.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				buttonsrow1.setBackground(new Color (88, 110, 117));
				buttonsrow2.setBackground(new Color (88, 110, 117));
				buttonsrow3.setBackground(new Color (88, 110, 117));
				panel.setBackground(new Color (7, 54, 56));
			}
		});
		
		// if you click on the light mode button, it will set all backgrounds to their original color set by Java
		lightmode.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				buttonsrow1.setBackground(buttonsRowsColor);
				buttonsrow2.setBackground(buttonsRowsColor);
				buttonsrow3.setBackground(buttonsRowsColor);
				panel.setBackground(panelcolor);
			}
		});
		
		
		
		//implementing action listener for color choosing button using JColorChooser
		colorchooser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Color initialColor = Color.BLACK;
				onColor = JColorChooser.showDialog(buttons, "Choose color", initialColor);
			}
		});
		
		
		
		// if the rectangle button if clicked, set the variable to true, and all other buttons to false because only one button can be clicked at a time
		rectangle.addActionListener (new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				isrect = true;
				iscircle = false;
				isdelete = false;
				isline = false;
				istext = false;
				isundo = false;
				isfront = false;
				isback = false;
			}
		});
		
		// if the circle button is clicked, set it to true, and everything else to false
		circle.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				iscircle = true;
				isrect = false;
				isdelete = false;
				isline = false;
				istext = false;
				isundo = false;
				isfront = false;
				isback = false;
			}
		});
		
		// if delete button is clicked, set isdelete to true, and everything else to false
		delete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				isdelete = true;
				isrect = false;
				iscircle = false;
				isline = false;
				istext = false;
				isundo = false;
				isfront = false;
				isback = false;
			}	
		});

		// if line button is pressed, set isline to true, and everything else to false
		line.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				isline = true;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = false;
				isundo = false;
				isfront = false;
				isback = false;
			}
		});
		
		//if text button is pressed, then istext = true, and everything else = false
		text.addActionListener(new ActionListener ()
		{
			public void actionPerformed(ActionEvent e) 
			{
				isline = false;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = true;
				isundo = false;
				isfront = false;
				isback = false;
			}
		});
		
		// if undo button is pressed, undo = true, everything else = false
		undo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				isundo = true;
				isline = false;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = false;
				isfront = false;
				isback = false;
				
				// if undo is pressed we delete the last element drawn and repaint the panel to depict changes
				shapes.remove(shapes.size() - 1);
				frame.getContentPane().repaint();
			}	
		});
		
		// if front button is pressed, isfront = true, everything else = false
		front.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				isfront = true;
				isundo = false;
				isline = false;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = false;
				isback = false;
			}
		});
		
		// if back button is pressed, isback = true, everything else = false
		back.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				isback = true;	
				isundo = false;
				isline = false;
				isdelete = false;
				isrect = false;
				iscircle = false;
				istext = false;
				isfront = false;	
			}
		});
			

		
		// Mouse listener for the drawarea to draw shapes
		drawarea.addMouseListener(new MouseListener()
		{
			public void mouseClicked(MouseEvent e) 
			{
				
			}	

			public void mousePressed(MouseEvent e) 
			{
				// if rectangle button is clicked, add new rectangle to the shape array list, get it's initial coordinates
				// here we call rect class that has a method of drawing rectangle
				// pass on the color so we can see the rectangle drawn
				if (isrect == true)
				{
					shapes.add(new rect(e.getX(), e.getY(), 0, 0, onColor));
					initialx = e.getX();
					initialy = e.getY();
				}
				// if circle button is clicked, add new circle to the shape array list, get it's initial coordinates
				// call circle class that has defined method of drawing circles
				else if (iscircle == true)
				{
					shapes.add(new circle(e.getX(), e.getY(), 0, onColor));
					initialx = e.getX();
					initialy = e.getY();
				}
				// if line button is clicked, add new line to the shape array list, get it's initial coordinates
				// calling line class with defined method of drawing lines
				else if (isline == true)
				{
					shapes.add(new line(e.getX(), e.getY(), e.getX(), e.getY(), onColor));
					initialx = e.getX();
					initialy = e.getY();	
				}
				// if delete button is pressed, delete the shape that is clicked after
				else if (isdelete == true)
				{
					for (int i = 0; i < shapes.size(); i++)
					{
						// passing coordinates of where it's clicked to check which shape we have to delete
						// checking which shape to delete -- in class shape
						if (shapes.get(i).isOn(e.getX(), e.getY()))
						{
							shapes.remove(i);
							break;
						}
					}
				}
				// if text button is pressed -- get input from the input field and pass to the text class 
				else if (istext == true)
				{
					if (input.getText() != "" && input.getText() != " ")
					{
						// text class drawing text
						shapes.add(new text(input.getText(), e.getX(), e.getY(), 0, 0, onColor));
						initialx = e.getX();
						initialy = e.getY();
					}
				}
				// if front button is pressed, create a temporary variable to hold the shape, delete the shape from where it is now, and add as the last element
				else if (isfront == true)
				{
					for (int i = 0; i < shapes.size(); i++)
					{
						if (shapes.get(i).isOn(e.getX(), e.getY()) == true)
						{
							shape temp = shapes.get(i);
							shapes.remove(i);
							shapes.add(shapes.size(), temp);
							break;
						}
					}
				}
				// if back button is pressed, create a temporary variable to hold the shape, delete it from where it is, and place one step further than the last element
				else if (isback == true)
				{
					for (int i = 0; i < shapes.size(); i++)
					{
						if (shapes.get(i).isOn(e.getX(), e.getY()) == true)
						{
							shape temp = shapes.get(i);
							shapes.remove(i);
							shapes.add(0, temp);
							break;
						}
					}
				}
				// repaint the content
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
		
		drawarea.addMouseMotionListener(new MouseMotionListener()
		{
			// if mouse is dragged, resize the shape
			public void mouseDragged(MouseEvent e) 
			{
				// check if it's rectangle, circle or line, as we resize only these, and the program may act somewhat odd if we don't check it
				if (isrect == true || iscircle == true || isline == true || istext == true)
				{
					shapes.get(shapes.size() - 1).resize(e.getX(), e.getY(), initialx, initialy);
				}
				
				// always need to repaint to see changes
				frame.getContentPane().repaint();
			}

			public void mouseMoved(MouseEvent e) 
			{
				
			}
			
		});
			
		
		// setting up basic frame features: size, what happens if we close the window, location, focus, if it can be resized, and visibility to see objects we added
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
