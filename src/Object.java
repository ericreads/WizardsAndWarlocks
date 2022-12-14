import javax.swing.*;
import java.awt.*;

public abstract class Object {
    
    private String name;
    private Image sprite; 
    private Image icon; 
    
    public abstract void draw(Graphics2D g);
}
