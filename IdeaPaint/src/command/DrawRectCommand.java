/*
 * 矩形描画クラス
 * 
 * - 変数 -
 * x_ : 左上の x 座標
 * y_ : 左上の y 座標
 * width_ : 矩形の幅
 * height_ : 矩形の高さ
 * 
 */

package command;

import java.awt.Color;

public class DrawRectCommand extends AbstractDrawShapeCommand {
	protected int x_, y_, width_, height_;

	public DrawRectCommand(Drawable drawable, int x, int y, int width, int height, Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable, fillColor, strokeColor, strokeWeight);

		this.x_ = x;
		this.y_ = y;
		this.width_ = width;
		this.height_ = height;
	}

	public Command getDrawFillShapeCommand() {
		Command cmd = new DrawFillRectCommand(drawable_, x_, y_, width_, height_);
		return cmd;
	}

	public Command getDrawStrokeShapeCommand() {
		Command cmd = new DrawStrokeRectCommand(drawable_, x_, y_, width_, height_);
		return cmd;
	}
}
