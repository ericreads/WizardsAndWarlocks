import java.awt.*;

public class ShopButton extends Button {

	public ShopButton(int x, int y, String text, Font font, Color defaultColor, Color hoverColor)
	{
		super(x, y, text, font, defaultColor, hoverColor);
	}
	@Override
	public void onClick() {
		//GameScreenManager.getInstance().clearScreens();
		GameScreenManager.getInstance().addScreen(new ShopSelectionScreen());
	}

}
