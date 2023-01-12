import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;
import java.io.*;

public class Inventory {
    private ArrayList<Weapon> inventory;
    private PotionOfHealing potion;
    private int index; // # of item equipped
    private BufferedImage inventorySlot, inventoryCursor;
    private Font dogicaPixel;
    private Player player;
    private boolean fourPressed = false;
    
    public Inventory(ArrayList<Weapon> inventory, PotionOfHealing potion, Player player)
    {
    	this.inventory = inventory;
        this.index = 0;
        this.potion = potion;
        this.player = player;
        
        try
        {
        	inventorySlot = ImageIO.read(getClass().getResourceAsStream("/ui/inventory_slot.png"));
        	inventoryCursor = ImageIO.read(getClass().getResourceAsStream("/ui/inventory_cursor.png"));
        	InputStream is = getClass().getResourceAsStream("/fonts/dogicapixel.ttf");
			dogicaPixel = Font.createFont(Font.TRUETYPE_FONT, is);
        }
        catch (Exception e)
        {
        	System.out.println(e.toString());
        }
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
        if(e.getKeyCode() == KeyEvent.VK_4 && SaveManager.getInstance().getHealthPotionNum() > 0)
        {
        	if(fourPressed == false)
        	{
        		SaveManager.getInstance().setHealthPotionNum(SaveManager.getInstance().getHealthPotionNum()-1);
        		player.takeDamage(-50);
        	}
        	fourPressed = true;
        } else
        	fourPressed = false;
        if(index > inventory.size()-1)
        	index = inventory.size()-1;
    }

    public void draw(Graphics2D g)
    {
        // Draw inventory slots
    	// Had to move inventory up to see it on linux - remove the constants added to y values to return inventory to previous position
    	
    	for (int i = 0; i < 4; i++)
		{		
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.85F)); // Set opacity of image
			g.drawImage(inventorySlot, (i * 60) + 508, 550 + 25, 50, 50, null);
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F)); // Set opacity to 100%
			if(i < inventory.size())
				g.drawImage(inventory.get(i).getIcon(), (i * 60) + 513, 550 + 30, 40, 40, null);
		}
    	g.drawImage(potion.getIcon(), (3 * 60) + 513, 550 + 30, 40, 40, null);
    	g.setFont(dogicaPixel.deriveFont(Font.PLAIN, 18F));
    	g.setColor(Color.white);
    	g.drawString(SaveManager.getInstance().getHealthPotionNum() + "", (3 * 60) + 525, 550 + 90);

		// Draw inventory cursor (representing item player has equipped)
		
		g.setStroke(new BasicStroke(4));
		g.drawImage(inventoryCursor, (index * 60) + 508, 550 + 25, 50, 50, null);
    }
}
