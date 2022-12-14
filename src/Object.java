import javax.swing.*;
import java.awt.image.*;
import java.awt.*;

public abstract class Object {
    
    private String name;
    private String description; 
    
    private BufferedImage sprite; 
    private BufferedImage icon; 
    
    public abstract void draw(Graphics2D g);
}