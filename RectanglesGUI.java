package CourseWork;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RectanglesGUI{
	JFrame frame;
	RectangleDrawPanel drawPanel;
	Color color1 = Color.orange;
	Color color2 = Color.blue;
	
	private JButton buttonC;
	RandomColourListener ranColListener;
	
	private JButton buttonR;
	ResetListener resetListener;
	

	public static void main (String[] args)
	{
		RectanglesGUI gui = new RectanglesGUI();
		gui.go();
	}

	//this method sets up the JFrame, adds the button and drawpanel to the frame and adds the ActionListener to the button
	public void go()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		drawPanel = new RectangleDrawPanel();
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		
		buttonC = new JButton("Click me!");
		frame.add(buttonC, BorderLayout.SOUTH);
		ranColListener = new RandomColourListener();
		buttonC.addActionListener(ranColListener);
		
		buttonR = new JButton("Reset Colours to Original State!");
		frame.add(buttonR, BorderLayout.NORTH);
		resetListener = new ResetListener();
		buttonR.addActionListener(resetListener);
		
		frame.setSize(600,600);
		frame.setVisible(true);
	}

	class RectangleDrawPanel extends JPanel{
		public void paintComponent (Graphics g){
			super.paintComponent(g);
			Graphics2D g2=(Graphics2D)g;
			
			//get size of panel
			Dimension panelSize = drawPanel.getSize();
			
			//divide width and height by 5 for each block
			int width = (panelSize.width)/5;
			int height = (panelSize.height)/5;
		
			//create x and y for the positions of blocks
			int y = 0;
			int x = 0;
			
			//create a new variable that will be used for proper colour swap in loop
			Color color3;
			
			//loops to make 5 columns
			for(int k = 0; k<5; k++) {
			
				//creates a column
				for(int i=0; i<5; i++) { 
					
					//creates a block
					g2.setColor(color1);
					g2.fill3DRect(x,y,width,height,true);
					y = y+height;
					
					//colour swap each block
					color3 = color1;
					color1=color2;
					color2=color3;
					
				}
				
				//sets y back to 0 and x is moved to create the next column
				y=0;
				x=x+width;
				
			}
			//colour swap again so the next column starts w the opposite colour
			color3 = color1;
			color1=color2;
			color2=color3;

		}
	}
	
	public class RandomColourListener implements ActionListener{

		int counter = 1;
		
		private Color c;
		@Override
		public void actionPerformed(ActionEvent e) {
			int r = (int) (Math.random()*256);
			int g = (int) (Math.random()*256);
			int b = (int) (Math.random()*256);
			
			if (counter % 2 == 0) {
				color2 = new Color(r, g, b);
			}else {
				color1 = new Color(r, g, b);
			}
			counter++;
			
			frame.repaint();
			
		}
		
	}
	
	public class ResetListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			color1 = Color.orange;
			color2= Color.blue;
			
			frame.repaint();
		
		}
		
	}
}


