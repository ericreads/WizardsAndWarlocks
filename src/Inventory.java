import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Inventory {
    
    private ArrayList<Object> inventory = new ArrayList<>();
    private int index; // # of item equipped
    
    public Inventory()
    {
        this.index = 0;
    }

    // Returns the weapon that is currently equipped
    public Object getWeapon() {
    	return this.inventory.get(index); 
    }
    
    // KeyEvent input is passed from GameScreenManager Class when key is pressed
	public void keyPressed(KeyEvent e) 
	{
        if (e.getKeyCode() == KeyEvent.VK_1) 
        {
            index = 0;
        }
        if (e.getKeyCode() == KeyEvent.VK_2) 
        {
            index = 1;
        }
        if (e.getKeyCode() == KeyEvent.VK_3) 
        {
            index = 2;
        }
        if (e.getKeyCode() == KeyEvent.VK_4) 
        {
            index = 3;
        }
    }

    public void draw(Graphics2D g)
    {
        // Draw inventory slots
		for (int i = 0; i < 4; i++)
		{
			g.setColor(new Color(0, 0, 0, 20));
			g.fillRect((i * 60) + 515, 625, 45, 45);

			g.setColor(Color.black);
			g.drawRect((i * 60) + 515, 625, 45, 45);
		}

		// Draw inventory cursor (representing item player has equipped)
		g.setStroke(new BasicStroke(4));
		g.drawRect((index * 60) + 515, 625, 45, 45);
    }
}
