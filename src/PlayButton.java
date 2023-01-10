import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class PlayButton extends Button {

	public PlayButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, text, font, defaultColor, hoverColor);
	}
	@Override
	public void onClick() {
		GameScreenManager.getInstance().clearScreens();
		GameScreenManager.getInstance().addScreen(new TutorialScreen());
	}
}
