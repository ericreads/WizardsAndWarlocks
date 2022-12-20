import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.event.MouseEvent;
public abstract class Button {
    private int x;
    private int y;
    private String text;
    private Font font;
    //The color the button is normally
    private Color defaultColor;
    //The color the button is while being hovered over
    private Color hoverColor;
    private Color currentColor;
    public Button(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
    {
        this.x = x;
        this.y = y;
        this.text = text;
        this.font = font;
        this.defaultColor = defaultColor;
        this.hoverColor = hoverColor;
        this.currentColor = defaultColor;
    }
    public void update()
    {
        AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
        if(GameJPanel.getMouseX() > this.x && GameJPanel.getMouseX() < this.x+font.getStringBounds(text, frc).getWidth() && GameJPanel.getMouseY() < this.y && GameJPanel.getMouseY() > this.y - font.getStringBounds(text, frc).getHeight())
        {
            currentColor = hoverColor;
        }
        else
        {
            currentColor = defaultColor;
        }
    }
    public void mouseClicked(MouseEvent e)
    {
        if(currentColor == hoverColor)
        {
            onClick();
        }
    }
    public void draw(Graphics2D g)
    {
        g.setColor(currentColor);
        g.setFont(font);
        g.drawString(text, x, y);
    }
    //Method to be overriden with function of the button
    public abstract void onClick();
}
