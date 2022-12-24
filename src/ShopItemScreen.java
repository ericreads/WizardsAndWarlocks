import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class ShopItemScreen extends GameScreen {
	private Object object;
	private Button buyButton;
	private Button backButton;
	private Font titleFont;
	private Font buttonFont;
	private Font descriptionFont;
	private boolean buyable;
	
	public ShopItemScreen(Object object)
	{
		this.object = object;
	}
	
	@Override
	public void initialize() {
		
		titleFont = new Font(Font.SANS_SERIF, Font.BOLD, 50);
		buttonFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		descriptionFont = new Font(Font.SANS_SERIF, Font.PLAIN, 25);
		backButton = new BackButton(20, 25, "Back", buttonFont, Color.black, Color.red);
		if((object.getName().equals("Spell Slinger") && SaveManager.getInstance().hasSpellSlinger()) || (object.getName().equals("Spell Sprayer") && SaveManager.getInstance().hasSpellSprayer()) || object.getName().equals("Beginner's Wand"))
		{
			buyButton = new BuyButton(1280/2-100, 600, "Already Owned", buttonFont, Color.green, Color.green);
			buyable = false;
		} else if(object.getCost() > SaveManager.getInstance().getMoney())
		{
			buyButton = new BuyButton(1280/2-100, 600, object, buttonFont, Color.gray.darker(), Color.gray.darker());
			buyable = false;
		} else
		{
			buyButton = new BuyButton(1280/2-100, 600, object, buttonFont, Color.black, Color.green);
			buyable = true;
		}
	}

	@Override
	public void update(int deltaTime) {
		backButton.update();
		buyButton.update();
	}

	@Override
	public void draw(Graphics2D g) {
		AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
        g.setFont(buttonFont);
        g.setColor(Color.black);
		g.drawString("$"+SaveManager.getInstance().getMoney(), (int)(1100-buttonFont.getStringBounds("$"+SaveManager.getInstance().getMoney(), frc).getX()), 25);
		g.setFont(titleFont);
		g.drawString(object.getName(), (int)(1280/2-titleFont.getStringBounds(object.getName(), frc).getWidth()/2), 100);
		g.setFont(descriptionFont);
		String[] lines = object.getDescription().split("\n");
		for (int i = 0; i < lines.length; i++)
		{
            g.drawString(lines[i], 1280/12, 300 + g.getFontMetrics().getHeight()*i);
		}
		g.drawImage(object.getIcon(), (1280/3)*2, 150, 300, 300, null);
		g.drawRect((1280/3)*2, 150, 300, 300);
		backButton.draw(g);
		buyButton.draw(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		backButton.mouseClicked(e);
		if(buyable)
			buyButton.mouseClicked(e);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
