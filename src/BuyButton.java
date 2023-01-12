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
		if(SaveManager.getInstance().getMoney() >= object.getCost())
		{
			if(object.getName().equals("Spell Slinger") && !SaveManager.getInstance().hasSpellSlinger())
			{
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
				SaveManager.getInstance().buySpellSlinger();
				super.text = "ALREADY OWNED";
				super.hoverColor = new Color(197, 119, 82);
				super.defaultColor = super.hoverColor;
			} else if(object.getName().equals("Spell Sprayer") && !SaveManager.getInstance().hasSpellSprayer())
			{
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
				SaveManager.getInstance().buySpellSprayer();
				super.text = "ALREADY OWNED";
				super.hoverColor = new Color(197, 119, 82);
				super.defaultColor = super.hoverColor;
			} else if(object.getName().equals("Health Upgrade") && SaveManager.getInstance().getPlayerHealth() < 175)
			{
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
				SaveManager.getInstance().setPlayerHealth(SaveManager.getInstance().getPlayerHealth()+15);
				if(SaveManager.getInstance().getPlayerHealth() >= 175)
				{
					super.text = "Maximum Health Reached";
					super.hoverColor = new Color(197, 119, 82);
					super.defaultColor = super.hoverColor;
				}
			} else if(object.getName().equals("Potion of Healing"))
			{
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
				SaveManager.getInstance().setHealthPotionNum(SaveManager.getInstance().getHealthPotionNum()+1);
			}
			if(SaveManager.getInstance().getMoney() < object.getCost())
			{
				super.hoverColor = new Color(197, 119, 82);
				super.defaultColor = super.hoverColor;
			}
		}
		SaveManager.getInstance().saveVals();
		SaveManager.getInstance().refreshVals();
	}

}
