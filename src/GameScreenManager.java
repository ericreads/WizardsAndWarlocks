//Import required modules
import java.util.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GameScreenManager {
	//The list which will store all of our GameScreens
	private ArrayList<GameScreen> screens;
	//The instance that will be statically returned allowing this class to be called *anywhere*
	private static GameScreenManager instance;
	
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
		screens.get(screens.size()-1).initialize();
	}
	//Removes one screen from the top of the list (making the one below it the one being rendered)
	public void clearScreen()
	{
		//Remove the last element in the list (if it exists)
		if(screens.size() - 1 >= 0)
			screens.remove(screens.size()-1);
	}
	//Remove all the screens from the list
	public void clearScreens()
	{
		for(int i = 0; i < screens.size(); i++)
		{
			screens.remove(i);
			i--;
		}
	}
	//Calls the update method of the screen at the top of the list
	public void update(int deltaTime)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).update(deltaTime);
	}
	//Calls the draw method of the screen at the top of the list
	public void draw(Graphics2D g)
	{
		if(screens.size() > 0)
			screens.get(screens.size()-1).draw(g);
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
}
