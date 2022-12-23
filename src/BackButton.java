import java.awt.Color;
import java.awt.Font;

public class BackButton extends Button {

	public BackButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, text, font, defaultColor, hoverColor);
	}
	@Override
	public void onClick() {
		GameScreenManager.getInstance().clearScreen();
		SaveManager.getInstance().saveVals();
		SaveManager.getInstance().refreshVals();
	}

}
