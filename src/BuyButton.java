import java.awt.*;
public class BuyButton extends Button {

	private Object object;
	
	public BuyButton(int x, int y, Object object, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, "Buy ($" + object.getCost() + ")", font, defaultColor, hoverColor);
		this.object = object;
	}
	public BuyButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, "Already Owned", font, defaultColor, hoverColor);
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
