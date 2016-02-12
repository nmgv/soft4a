/*
 * 矩形(塗りつぶし)描画クラス
 */

package command;

public class DrawFillRectCommand extends BaseDrawOvalCommand {
	public DrawFillRectCommand(Drawable drawable, int x, int y, int width, int height){
		super(drawable, x, y, width, height);
	}

	public void execute() {
		drawable_.drawFillRect(x_, y_, width_, height_);
	}
}
