//Name: Jonathan Hassel
//Date: 3/6/17
//Class: COP3252 - Thrasher


import javax.swing.JFrame;

public class HW5Main
{
	public static void main(String args[])
	{ 
		
		DesktopFrame dFrame = new DesktopFrame();  
		dFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dFrame.setSize(800, 600); 		//set frame size
		dFrame.setVisible(true);		//display frame		 
	}
}