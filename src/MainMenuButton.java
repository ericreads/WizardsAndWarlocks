import java.awt.Color;
import java.awt.Font;

public class MainMenuButton extends Button {

	public MainMenuButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor) 
	{
		super(x, y, text, font, defaultColor, hoverColor);
	}
	
	@Override
	public void onClick() {
		GameScreenManager.getInstance().clearScreens();
		GameScreenManager.getInstance().addScreen(new MainMenu());
		SaveManager.getInstance().saveVals();
		SaveManager.getInstance().refreshVals();
	}
}
