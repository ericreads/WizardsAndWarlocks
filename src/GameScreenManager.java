//Import required modules
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameScreenManager {
	//The list which will store all of our GameScreens
	private ArrayList<GameScreen> screens;
	//The instance that will be statically returned allowing this class to be called *anywhere*
	private static GameScreenManager instance;
	//Boolean that stores whether or not the window should close
	private boolean shouldClose = false;
	//Constructor is private to ensure only our static instance can be accessed 
	private GameScreenManager()
	{
		screens = new ArrayList<GameScreen>();
	}
	
	//Returns the static instance of GameScreenManager
	public static GameScreenManager getInstance()
	{
		//If instance is null define it, then return it
		if(instance == null)
			instance = new GameScreenManager();
		return instance;
	}
	
	//Adds a screen to the top of the list (making it the one currently being drawn) and initializes it
	public void addScreen(GameScreen gameScreen)
	{
		screens.add(gameScreen);
		screens.get(screens.size() - 1).runInitialize();
	}
	
	//Removes one screen from the top of the list (making the one below it the one being rendered)
	public void clearScreen()
	{
		//Remove the last element in the list (if it exists)
		if(screens.size() - 1 >= 0)
			screens.remove(screens.size()-1);
	}
	// Removes the screen at the bottom of the list 
	public void clearFirst()
	{
		if (screens.size() > 0)
		{
			screens.remove(0);
		}
	}
	
	//Remove all the screens from the list
	public void clearScreens()
	{
		screens.clear();
	}
	//Calls the update method of the screen at the top of the list
	public void update(int deltaTime)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).runUpdate(deltaTime);
	}
	//Calls the draw method of each screen in the list
	public void draw(Graphics2D g)
	{
		for (GameScreen screen : screens)
			screen.runDraw(g);
	}
	//Calls the keyPressed method of the screen at the top of the list
	public void keyPressed(KeyEvent e)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).keyPressed(e);
	}
	//Calls the keyReleased method of the screen at the top of the list
	public void keyReleased(KeyEvent e)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).keyReleased(e);
	}
	//Calls the keyTyped method of the screen at the top of the list
	public void keyTyped(KeyEvent e)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).keyTyped(e);
	}
	///Calls the mouseClicked method of the screen at the top of the list
	public void mouseClicked(MouseEvent e)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).mouseClicked(e);
	}
	//Calls the mousePressed method of the screen at the top of the list
	public void mousePressed(MouseEvent e)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).mousePressed(e);
	}
	//Calls the mouseReleased method of the screen at the top of the list
	public void mouseReleased(MouseEvent e)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).mouseReleased(e);
	}
	//Sets it so the window should close
	public void shouldClose()
	{
		shouldClose = true;
	}
	//Returns whether or not the window should close
	public boolean getShouldClose()
	{
		return shouldClose;
	}
	
}
