//Name: Jonathan Hassel
//Date: 3/6/17
//Class: COP3252 - Thrasher

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

public class DesktopFrame extends JFrame
{
	private JDesktopPane dPane;
	
	public DesktopFrame()
	{
		super("Homework 5");
		
		JMenuBar menu = new JMenuBar();
		dPane = new JDesktopPane();
		
		add(dPane);
		
//------Create Menu---------------------------------------------------------------------------------
		JMenu createMenu = new JMenu("Create");	
		createMenu.setMnemonic(KeyEvent.VK_C);
																								
		JMenuItem picFrame = new JMenuItem("Picture Frame", KeyEvent.VK_P);
		JMenuItem changeablePicFrame = new JMenuItem("Changeable Picture Frame", KeyEvent.VK_C);
		JMenuItem randomizedPicFrame = new JMenuItem("Randomized Picture", KeyEvent.VK_R);
		
		createMenu.add(picFrame);
		createMenu.add(changeablePicFrame);
		createMenu.add(randomizedPicFrame);

		
		//to create a picture frame
		picFrame.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JInternalFrame frame = new JInternalFrame("Picture Frame", true, true, true, true);	
					
					PictureFrame panel = new PictureFrame();
					
					frame.setBackground(Color.BLUE);
					
					frame.setVisible(true);
					frame.setSize((int)(.5 * dPane.getWidth()), (int)(.5 * dPane.getHeight()));
					frame.setLocation(0, 0);  
					frame.add(panel);
					dPane.add(frame);
				}
			}
		);
		
		
		//to create a changeable picture frame
		changeablePicFrame.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{					
					JInternalFrame frame = new JInternalFrame("Changeable Picture Frame", true, true, true, true);
					
					ChangeablePictureFrame panel = new ChangeablePictureFrame();
					HandlerClass handler = new HandlerClass(panel);
					
					JMenuBar internalMenu = new JMenuBar();
					
					//all the internal menu items
					JCheckBoxMenuItem dragItem = new JCheckBoxMenuItem("Move on drag");
					JRadioButtonMenuItem greenButton = new JRadioButtonMenuItem("Green", true);
					JRadioButtonMenuItem redButton = new JRadioButtonMenuItem("Red");
					JRadioButtonMenuItem blueButton = new JRadioButtonMenuItem("Blue");
					ButtonGroup group = new ButtonGroup();
					
					//adding buttons to the internalMenu
					internalMenu.add(dragItem);
					internalMenu.add(greenButton);
					internalMenu.add(redButton);
					internalMenu.add(blueButton);
					
					//adding the color buttons to a group
					group.add(greenButton);
					group.add(redButton);
					group.add(blueButton);
					
					//dragItem
					dragItem.addActionListener
					(
							new ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
									if(dragItem.isSelected())
									{
										panel.isToggled = true;
										panel.addMouseMotionListener(handler);
									}
									else
									{
										panel.isToggled = false;
										panel.removeMouseMotionListener(handler);
										panel.setX((.5 * panel.getWidth()) - (.15 * panel.getWidth()));
										panel.setY((.5 * panel.getHeight()) - (.15 * panel.getHeight()));
									}
									panel.repaint();
								}
							}
						);
					
					//green button
					greenButton.addActionListener
					(
							new ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
									//change color to green
									panel.color = Color.GREEN;
									panel.repaint();
								}
							}
					);
					
					//red button
					redButton.addActionListener
					(
							new ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
									//change color to red
									panel.color = Color.RED;
									panel.repaint();
								}
							}
					);
					
					//blue button
					blueButton.addActionListener
					(
							new ActionListener()
							{
								public void actionPerformed(ActionEvent event)
								{
									//change color to blue
									panel.color = Color.BLUE;
									panel.repaint();
								}
							}
					);
					
					//setting the menu bar on the frame
					frame.setJMenuBar(internalMenu);
					
					frame.setVisible(true);
					frame.setSize((int)(.5 * dPane.getWidth()), (int)(.5 * dPane.getHeight()));
					frame.setLocation((int)(.5 * dPane.getWidth()), 0);
					frame.add(panel);
					dPane.add(frame);
				}
			}
		);
		
		
		//to create a randomized picture frame
		randomizedPicFrame.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					JInternalFrame frame = new JInternalFrame("Randomized Picture Frame", true, true, true, true);
					
					RandomizedPictureFrame panel = new RandomizedPictureFrame();
					
					frame.setVisible(true);
					frame.setSize(dPane.getWidth(), (int)(.5 * dPane.getHeight()));
					frame.setLocation(0, (int)(.5 * dPane.getHeight()));
					frame.add(panel);
					dPane.add(frame);
				}
			}
		);
		
//------end Create Menu-----------------------------------------------------------------------------
		
		
//------Quit Menu-----------------------------------------------------------------------------------
		JMenu quitMenu = new JMenu("Quit");
		quitMenu.setMnemonic(KeyEvent.VK_Q);
		
		JMenuItem exit = new JMenuItem("Exit Program", KeyEvent.VK_X);
		
		quitMenu.add(exit);
		
		//to exit with the x key
		exit.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					System.exit(0);
				}
			}
		);
		
		//to exit with CTRL-X accelerator
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

//------end Quit Menu-------------------------------------------------------------------------------

		
		menu.add(createMenu);
		menu.add(quitMenu);
		setJMenuBar(menu);
	}


	class HandlerClass extends ChangeablePictureFrame implements MouseMotionListener
	{
		ChangeablePictureFrame frame;
		
		public HandlerClass(ChangeablePictureFrame f)
		{
			frame = f;
		}
		
		public void mouseDragged(MouseEvent e)
		{
			frame.setX(e.getX());
			frame.setY(e.getY());
			frame.repaint();
		}

		public void mouseMoved(MouseEvent e)
		{
			
		}
	}
}