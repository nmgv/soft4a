/*
 * 楕円描画クラス
 * 
 * - 変数 -
 * x_ : 左上の x 座標
 * y_ : 左上の y 座標
 * width_ : 楕円の幅
 * height_ : 楕円の高さ
 * 
 */

package command;

import java.awt.Color;

public class DrawOvalCommand extends AbstractDrawShapeCommand {
	private int x_, y_, width_, height_;

	public DrawOvalCommand(Drawable drawable, int x, int y, int r, Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable, fillColor, strokeColor, strokeWeight);

		this.x_ = x;
		this.y_ = y;
		this.width_ = r;
		this.height_ = r;
	}

	public DrawOvalCommand(Drawable drawable, int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable, fillColor, strokeColor, strokeWeight);

		this.x_ = x1 > x2 ? x2 : x1;
		this.y_ = y1 > y2 ? y2 : y1;
		this.width_ = Math.abs(x2 - x1);
		this.height_ = Math.abs(y2 - y1);
	}

	public Command getDrawFillShapeCommand() {
		Command cmd = new DrawFillOvalCommand(drawable_, x_, y_, width_, height_);
		return cmd;
	}

	public Command getDrawStrokeShapeCommand() {
		Command cmd = new DrawStrokeOvalCommand(drawable_, x_, y_, width_, height_);
		return cmd;
	}
}
