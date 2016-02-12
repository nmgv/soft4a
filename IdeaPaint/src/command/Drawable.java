package command;

import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;

public interface Drawable {
	public abstract void drawFillRect(int x, int y, int length);
	public abstract void drawOval(int x, int y, int width, int height);
	public abstract void drawFillOval(int x, int y, int width, int height);
	public abstract void drawFillPolygon(int xPoints[], int yPoints[], int nPoints);
	public abstract void drawStrokePolygon(int xPoints[], int yPoints[], int nPoints);
	public abstract void baseDrawText(int x, int y, String text, Font font);
	public abstract void setColor(Color color);
	public abstract void setStroke(BasicStroke stroke);

	public abstract void drawFillRect(int x, int y, int width, int height);
	public abstract void drawLineRect(int x, int y, int width, int height);
	public abstract void drawLine(int x1, int y1, int x2, int y2);

	public abstract void update();
	public abstract void save(String filepath);
	public abstract void newOffScreen();

}
