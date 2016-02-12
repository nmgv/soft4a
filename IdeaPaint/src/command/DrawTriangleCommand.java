/*
 * 三角形描画クラス
 * 
 * - 変数 -
 * xPoints_ : 頂点の x 座標の配列
 * yPoints_ : 頂点の y 座標の配列
 * nPoints_ : 頂点の数
 * 
 */

package command;

import java.awt.Color;

public class DrawTriangleCommand extends AbstractDrawShapeCommand {
	private int xPoints_[], yPoints_[], nPoints_;

	public DrawTriangleCommand(Drawable drawable_, int xPoints_[], int yPoints_[], Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable_, fillColor, strokeColor, strokeWeight);

		this.nPoints_ = 3;
		this.xPoints_ = xPoints_;
		this.yPoints_ = yPoints_;
	}
	
	public DrawTriangleCommand(Drawable drawable_, int x1, int y1, int x2, int y2, int x3, int y3, Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable_, fillColor, strokeColor, strokeWeight);

		this.nPoints_ = 3;
		this.xPoints_ = new int[nPoints_];
		this.yPoints_ = new int[nPoints_];
		this.xPoints_[0] = x1;
		this.xPoints_[1] = x2;
		this.xPoints_[2] = x3;
		this.yPoints_[0] = y1;
		this.yPoints_[1] = y2;
		this.yPoints_[2] = y3;
	}

	public Command getDrawFillShapeCommand() {
		Command cmd = new DrawFillPolygonCommand(drawable_, xPoints_, yPoints_, nPoints_);
		return cmd;
	}

	public Command getDrawStrokeShapeCommand() {
		Command cmd = new DrawStrokePolygonCommand(drawable_, xPoints_, yPoints_, nPoints_);
		return cmd;
	}
}
