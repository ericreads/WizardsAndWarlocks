import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public abstract class Object {
    
    private String name;
    private String description; 
    private BufferedImage icon; 
    
    public Object(String name, String description, BufferedImage icon)
    {
    	this.name = name;
    	this.description = description;
    	this.icon = icon;
    }
    
    public BufferedImage getIcon() { return icon; }
    public String getName() { return name; }
    public String getDescription() { return name; }
}