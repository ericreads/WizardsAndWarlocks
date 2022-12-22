import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public abstract class Object {
    
    private String name;
    private String description; 
    private BufferedImage icon; 
    private int cost;
    
    public Object(String name, String description, BufferedImage icon, int cost)
    {
    	this.name = name;
    	this.description = description;
    	this.icon = icon;
    	this.cost = cost;
    }
    
    public BufferedImage getIcon() { return icon; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getCost() { return cost; }
}