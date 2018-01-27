//Name: Jonathan Hassel
//Date: 3/6/17
//Class: COP3252 - Thrasher

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JPanel;

public class ChangeablePictureFrame extends JPanel
{
	private Ellipse2D.Double circle;
	private double x;
	private double y;
	
	protected Color color = Color.GREEN;		//default color
	protected boolean isToggled = false;
	
	public void paintComponent(Graphics g)
	{		
		//for removing things from previous frames
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
				
		if(!isToggled)
		{	//default(center) position
			x = (.5 * getWidth()) - (.15 * getWidth());
			y = (.5 * getHeight()) - (.15 * getHeight());
		}

		//width and height
		double w = (.3 * getWidth());
		double h = (.3 * getHeight());
		
		circle = new Ellipse2D.Double(x, y, w, h);
		
		//setting color and drawing circle
		g2d.setColor(color);
		g2d.fill(circle);
		


	}
	
	//sets x coordinate
	public void setX(double xCoord)
	{
		x = xCoord - (.15 * getWidth());
	}
	
	//sets y coordinate
	public void setY(double yCoord)
	{
		y = yCoord - (.15 * getHeight());
	}
}
