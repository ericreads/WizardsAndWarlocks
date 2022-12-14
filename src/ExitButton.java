import java.awt.*;

public class ExitButton extends Button {

	public ExitButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, text, font, defaultColor, hoverColor);
	}
	@Override
	public void onClick() {
		GameScreenManager.getInstance().shouldClose();
	}

}
