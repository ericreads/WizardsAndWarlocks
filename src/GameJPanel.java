// Import required modules
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameJPanel extends JPanel 
{
	//Create a long to store the time the last time the loop was run (for calculating deltaTime)
	private long pastTime;
	public GameJPanel()
	{
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e)
			{
				//Pass the input to the current GameScreen through the GameScreenManager
				GameScreenManager.getInstance().keyTyped(e);
			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
				//Pass the input to the current GameScreen through the GameScreenManager
				GameScreenManager.getInstance().keyReleased(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
				//Pass the input to the current GameScreen through the GameScreenManager
				GameScreenManager.getInstance().keyPressed(e);
			}
		});
		setFocusable(true);
	}
	public void update()
	{
		//Get the current time in milliseconds
		long time = System.nanoTime() / 1000000;
		//If the past time hasn't been defined define it as the current time
		if(pastTime == 0)
			pastTime = time;
		//Calculate the change in time since the last run through of the loop 
		int deltaTime = (int)(time-pastTime);
		//Update the current GameScreen through the GameScreenManager
		GameScreenManager.getInstance().update(deltaTime);
		//Set the pastTime to the current time
		pastTime = time;
	}
	
	@Override
	public void paint(Graphics g)
	{
		// Refresh screen each time
		super.paint(g);
		//Cast g to a usable Graphics2D
		Graphics2D g2d = (Graphics2D) g;
		//Draw the current GameScreen
		GameScreenManager.getInstance().draw(g2d);
	}

	public static void main(String[] args) throws InterruptedException{
	{
		//Enable hardware accelerated graphics to improve performance for linux users(eric)
		System.setProperty("sun.java2d.opengl", "true");
		//Initialize a new window
		JFrame frame = new JFrame("Wizards and Warlocks");
		GameJPanel p = new GameJPanel();
		frame.add(p);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		//Put the player screen in the GameScreenManager
		GameScreenManager.getInstance().addScreen(new TestScreen());
		while(true)
		{
			p.update();
			p.repaint();
			//Set refresh rate to around 16.66ms the default refresh rate of most monitors
			Thread.sleep(16);
		}
	}}
}
