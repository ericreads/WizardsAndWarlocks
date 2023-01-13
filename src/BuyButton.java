import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.*;

public class BuyButton extends Button {

	private Object object;
	
	public BuyButton(int x, int y, Object object, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, "BUY (" + object.getCost() + ")", font, defaultColor, hoverColor);
		this.object = object;
		
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		this.x = 632 - (int)(this.font.getStringBounds(this.text, frc).getWidth() / 2);
	}
	
	public BuyButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, text, font, defaultColor, hoverColor);
	}
	
	@Override
	public void onClick() {
		AffineTransform at = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(at, true, true);
		 
				
		if(SaveManager.getInstance().getMoney() >= object.getCost())
		{
			if(object.getName().equals("Spell Slinger") && !SaveManager.getInstance().hasSpellSlinger())
			{
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
				SaveManager.getInstance().buySpellSlinger();
				super.text = "ALREADY OWNED";
				super.hoverColor = super.defaultColor;
				//super.defaultColor = super.hoverColor;
			} else if(object.getName().equals("Spell Sprayer") && !SaveManager.getInstance().hasSpellSprayer())
			{
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
				SaveManager.getInstance().buySpellSprayer();
				super.text = "ALREADY OWNED";
				super.hoverColor = super.defaultColor;
				
				//super.defaultColor = super.hoverColor;
			} else if(object.getName().equals("Health Upgrade") && SaveManager.getInstance().getPlayerHealth() < 175)
			{
				SaveManager.getInstance().setMoney(SaveManager.getInstance().getMoney()-object.getCost());
				SaveManager.getInstance().setPlayerHealth(SaveManager.getInstance().getPlayerHealth()+15);
				if(SaveManager.getInstance().getPlayerHealth() >= 175)
				{
					super.text = "MAXIMUM HEALTH REACHED";
					super.hoverColor = super.defaultColor;
				//	super.defaultColor = super.hoverColor;
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
			this.x = 632 - (int)(this.font.getStringBounds(this.text, frc).getWidth() / 2);
		}
		SaveManager.getInstance().saveVals();
		SaveManager.getInstance().refreshVals();
	}

}
