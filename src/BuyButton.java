import java.awt.*;
import java.awt.image.*;

public class BuyButton extends Button {

	private Object object;
	private BufferedImage coin;
	
	public BuyButton(int x, int y, Object object, Font font, Color defaultColor, Color hoverColor)
	{
		
		super(x, y, "BUY (" + object.getCost() + ")", font, defaultColor, hoverColor);
		this.object = object;
	}
	
	public BuyButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, "ALREADY OWNED", font, defaultColor, hoverColor);
	}
	
	@Override
	public void onClick() {
		if(object.getName().equals("Spell Slinger") && !SaveManager.getInstance().hasSpellSlinger())
		{
			SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
			SaveManager.getInstance().buySpellSlinger();
		} else if(object.getName().equals("Spell Sprayer") && !SaveManager.getInstance().hasSpellSprayer())
		{
			SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
			SaveManager.getInstance().buySpellSprayer();
		} else if(object.getName().equals("Health Upgrade +15") && SaveManager.getInstance().getPlayerHealth() <= 175)
		{
			SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
			SaveManager.getInstance().setPlayerHealth(SaveManager.getInstance().getPlayerHealth()+15);
		}
		SaveManager.getInstance().saveVals();
		SaveManager.getInstance().refreshVals();
	}

}
