//Import required modules
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameJPanel extends JPanel 
{
	public GameJPanel()
	{
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e)
			{

			}
			
			@Override
			public void keyReleased(KeyEvent e)
			{
			}
			
			@Override
			public void keyPressed(KeyEvent e)
			{
			}
		});
		setFocusable(true);
	}
	
	public void update()
	{
	
	}
	
	@Override
	public void paint(Graphics g)
	{
		//Cast g to a usable Graphics2D
		Graphics2D g2d = (Graphics2D) g;
		//Draw a white background
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, 1280, 720);
		
	}
	
	public static void main(String[] args) throws InterruptedException{
	{
		//Initialize a new window
		JFrame frame = new JFrame("Wizards and Warlocks");
		GameJPanel p = new GameJPanel();
		frame.add(p);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
		while(true)
		{
			p.update();
			p.repaint();
			Thread.sleep(10);
		}
	}}
}
