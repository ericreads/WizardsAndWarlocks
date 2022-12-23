import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Inventory {
    private ArrayList<Weapon> inventory;
    private int index; // # of item equipped
    
    public Inventory(ArrayList<Weapon> inventory)
    {
    	this.inventory = inventory;
        this.index = 0;
    }

    // Returns the weapon that is currently equipped
    public Weapon getWeapon() {
    	return inventory.get(index);
    }
    //Returns the weapon list
    public ArrayList<Weapon> getWeapons() { return inventory; }
    
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
        if(index > inventory.size()-1)
        	index = inventory.size()-1;
    }

    public void draw(Graphics2D g)
    {
        // Draw inventory slots
    	//Had to move inventory up to see it on linux
		for (int i = 0; i < 4; i++)
		{
			g.setColor(new Color(0, 0, 0, 20));
			g.fillRect((i * 60) + 515, 550, 45, 45);

			g.setColor(Color.black);
			g.drawRect((i * 60) + 515, 550, 45, 45);
			if(i < inventory.size())
				g.drawImage(inventory.get(i).getIcon(), (i * 60) + 515, 550, 45, 45, null);
		}

		// Draw inventory cursor (representing item player has equipped)
		g.setStroke(new BasicStroke(4));
		g.drawRect((index * 60) + 515, 550, 45, 45);
    }
}
