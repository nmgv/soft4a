/*
 * 多角形(塗りつぶし)描画クラス
 */

package command;

public class DrawFillPolygonCommand extends BaseDrawPolygonCommand {
	public DrawFillPolygonCommand(Drawable drawable, int xPoints[], int yPoints[], int nPoints){
		super(drawable, xPoints, yPoints, nPoints);
	}

	public void execute() {
		drawable_.drawFillPolygon(xPoints_, yPoints_, nPoints_);
	}
}
