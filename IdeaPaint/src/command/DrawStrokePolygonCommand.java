/*
 * 多角形(枠線)描画クラス
 */

package command;

public class DrawStrokePolygonCommand extends BaseDrawPolygonCommand {
	public DrawStrokePolygonCommand(Drawable drawable, int xPoints[], int yPoints[], int nPoints){
		super(drawable, xPoints, yPoints, nPoints);
	}

	public void execute() {
		drawable_.drawStrokePolygon(xPoints_, yPoints_, nPoints_);
	}
}
