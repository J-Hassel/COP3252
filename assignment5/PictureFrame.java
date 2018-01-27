//Name: Jonathan Hassel
//Date: 3/6/17
//Class: COP3252 - Thrasher

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class PictureFrame extends JPanel
{	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		//drawing yellow circle
		Ellipse2D.Double circle;
		g2d.setColor(Color.YELLOW);
		
		if(this.getHeight() < this.getWidth())
		{
			circle = new Ellipse2D.Double(.7 * this.getWidth(), .1 * this.getHeight(), .25 * this.getHeight(), .25 * this.getHeight());
		}
		else
			circle = new Ellipse2D.Double(.7 * this.getWidth(), .1 * this.getHeight(), .25 * this.getWidth(), .25 * this.getWidth());
	
		g2d.fill(circle);	
		
		
		//drawing brown rectangle
		g2d.setColor(new Color(139,69,19));
		
		double x = 0;
		double y = (.9 * this.getHeight());
		double w = this.getWidth();
		double h = (.1 * this.getHeight());
		
		Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, w, h);
	
		g2d.fill(rectangle);
	}
}
