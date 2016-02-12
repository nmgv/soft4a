package command;

import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;

public interface Drawable {
	/* ----- 図形描画関数 ----- */

	// 矩形描画関数
	public abstract void drawFillRect(int x, int y, int width, int height);
	public abstract void drawStrokeRect(int x, int y, int width, int height);

	// 楕円描画関数
	public abstract void drawFillOval(int x, int y, int width, int height);
	public abstract void drawStrokeOval(int x, int y, int width, int height);

	// 多角形描画関数
	public abstract void drawFillPolygon(int xPoints[], int yPoints[], int nPoints);
	public abstract void drawStrokePolygon(int xPoints[], int yPoints[], int nPoints);

	// 直線描画関数
	public abstract void baseDrawLine(int x1, int y1, int x2, int y2);

	// 文字列描画関数
	public abstract void baseDrawText(int x, int y, String text, Font font);

	// 色指定関数
	public abstract void setColor(Color color);

	// 線幅指定関数
	public abstract void setStroke(BasicStroke stroke);

	/* ----- 標準の関数 ----- */

	public abstract void defaultDrawFillOval(int x, int y);
	public abstract void defaultDrawFillRect(int x, int y, int length);
	public abstract void update();
	public abstract void save(String filepath);
	public abstract void newOffScreen();
}
