//Name: Jonathan Hassel
//Date: 3/6/17
//Class: COP3252 - Thrasher

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class RandomizedPictureFrame extends JPanel
{
	Random rand = new Random();
	
	public void paintComponent(Graphics g)
	{
		int num = rand.nextInt(5) + 1;
		
		for(int i = 0; i < num; ++i)
		{
			g.setColor(new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
			g.fillRect(rand.nextInt(this.getWidth()), rand.nextInt(this.getHeight()), rand.nextInt((int) (.5 *this.getWidth())) + 1, rand.nextInt((int) (.5 *this.getHeight())) + 1);
		}
	}
}
