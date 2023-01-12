import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

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
		}
		SaveManager.getInstance().saveVals();
		SaveManager.getInstance().refreshVals();
	}

}
