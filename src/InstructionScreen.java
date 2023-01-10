// Import required modules
import java.awt.*;
import java.awt.event.*;

public class InstructionScreen extends GameScreen 
{

	private int frames = 0;
	private int stage;
	private TextBox textBox;
	
	public InstructionScreen()
	{
		this.stage = 0;
	}
	
	public InstructionScreen(int stage)
	{
		this.stage = stage; 
	}
	
	@Override
	public void initialize() 
	{
		textBox = new TextBox(null);
	}

	@Override
	public void update(int deltaTime) 
	{
		frames++;
	}

	@Override
	public void draw(Graphics2D g) 
	{
		String dialogue[] = {"50 years ago, The Empire was a prosperous land, where those born with magic could freely", 
				 			 "pratice it, until now. A new King takes to the throne, threatening to hunt down and kill",
				 			 "all commoners with magical abilities, including you.", " ",
				 			 "You have been caught by the King's guards, awaiting your invetible punishment in the dungeon", 
							 "palace. Fight your way through the palace using your sorceries and restore peace to the empire!", " "};

		if (stage == 0)
		{
			for (int i = 0; i < dialogue.length; i++) 
			{
				g.drawString(dialogue[i], 100, 100 + (30 * i));
			}
			
			if (frames > 500)
			{
				g.drawString("Click anywhere to begin the tutorial.", 100, 100 + (240));
			}
		}
		else if (stage == 1)
		{
			g.setColor(new Color(35, 35, 35, 100));
			g.fillRect(0, 0, 1280, 680);
			
			String[] text = {"Use WASD or arrow keys to move.", " ", 
					 	 	 "Move your cursor to aim and click the",
					 		 "left mouse button to cast a spell.", " ",
					 		 "Click anywhere to continue."};
			
			textBox.setText(text);
			textBox.draw(g);
		}
		else if (stage == 2)
		{
			g.setColor(new Color(35, 35, 35, 100));
			g.fillRect(0, 0, 1280, 680);
			
			String[] text = {"Change your equipped inventory ", 
							 "item using the number keys.", " ",
							 "You can purchase items from the shop.", " ", 
			 		 		 "Click anywhere to continue."};
	
			textBox.setText(text);
			textBox.draw(g);
		}
		else if (stage == 4)
		{
			String[] text = {"Congrats! You completed the tutorial.", " ", 
							 "Press 'enter' to begin gameplay."};
	
			for (int i = 0; i < text.length; i++)
			{
				g.drawString(text[i], 400, 300 + (25 * i));
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER && stage == 4)
		{
			GameScreenManager.getInstance().clearScreens();
			GameScreenManager.getInstance().addScreen(new TestScreen());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if (stage < 4)
		{
			stage++;
		}
		
		if (stage == 3)
		{
			GameScreenManager.getInstance().clearScreen();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		
	}

}
