import java.awt.*;
import java.awt.event.MouseEvent;
public class ObjectShopButton {
	private Object object;
	private boolean hovered;
	private int dimensions;
	private int x;
	private int y;
	
	public ObjectShopButton(int x, int y, int dimensions, Object object)
	{
		this.x = x;
		this.y = y;
		this.object = object;
		this.dimensions = dimensions;
		this.hovered = false;
	}
	
	public void update()
	{
		if(GameJPanel.getMouseX() > x && GameJPanel.getMouseX() < x+dimensions && GameJPanel.getMouseY() > y && GameJPanel.getMouseY() < y+dimensions)
		{
			hovered = true;
		} else
		{
			hovered = false;
		}
	}
	
	public void draw(Graphics2D g)
	{
		if(object!= null)
		{
			if(hovered)
				g.setColor(Color.green);
			else
				g.setColor(Color.black);
		
			g.drawImage(object.getIcon(), x, y, dimensions, dimensions, null);
		} else
			g.setColor(Color.black);
		g.drawRect(x, y, dimensions, dimensions);
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if(e.getButton() == MouseEvent.BUTTON1 && hovered && object != null)
			GameScreenManager.getInstance().addScreen(new ShopItemScreen(object));
	}
}
