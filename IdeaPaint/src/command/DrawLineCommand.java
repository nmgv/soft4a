/*
 * 直線描画クラス
 * 
 * - 変数 -
 * x1_ : 始点の x 座標
 * y1_ : 始点の y 座標
 * x2_ : 終点の x 座標
 * y2_ : 終点の y 座標
 * 
 */

package command;

import java.awt.Color;

public class DrawLineCommand extends AbstractDrawShapeCommand {
	private int x1_, y1_, x2_, y2_;

	public DrawLineCommand(Drawable drawable, int x1, int y1, int x2, int y2, Color strokeColor, float strokeWeight) {
		super(drawable, null, strokeColor, strokeWeight);

		this.x1_ = x1;
		this.y1_ = y1;
		this.x2_ = x2;
		this.y2_ = y2;
	}

	public Command getDrawFillShapeCommand() {
		return null;
	}

	public Command getDrawStrokeShapeCommand() {
		Command cmd = new BaseDrawLineCommand(drawable_, x1_, y1_, x2_, y2_);
		return cmd;
	}
}
