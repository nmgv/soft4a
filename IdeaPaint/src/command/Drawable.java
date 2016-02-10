package command;

import java.awt.Color;

public interface Drawable {
	public abstract void drawFillRect(int x, int y, int length);
	public abstract void draw(int x, int y);
	public abstract void setColor(Color color);

	public abstract void update();
	public abstract void save(String filepath);
	public abstract void newOffScreen();

}
