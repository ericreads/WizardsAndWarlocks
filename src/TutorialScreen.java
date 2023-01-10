
// Import required modules
import java.awt.*;
import java.awt.event.*;

public class TutorialScreen extends GameScreen 
{
	private int frames = 0;
	
	InstructionScreen instructionScreen;
	TutorialGameplayScreen tutorialScreen;

	@Override
	public void initialize() 
	{
		instructionScreen = new InstructionScreen(1);
		tutorialScreen = new TutorialGameplayScreen();
		
		GameScreenManager.getInstance().clearScreens();
		GameScreenManager.getInstance().addScreen(tutorialScreen);
		GameScreenManager.getInstance().addScreen(instructionScreen);		
	}

	@Override
	public void update(int deltaTime) 
	{

	}

	@Override
	public void draw(Graphics2D g) 
	{
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		instructionScreen.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		
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
