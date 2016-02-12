/*
 * 矩形(枠線)描画クラス
 */

package command;

public class DrawStrokeRectCommand extends BaseDrawOvalCommand {
	public DrawStrokeRectCommand(Drawable drawable, int x, int y, int width, int height){
		super(drawable, x, y, width, height);
	}

	public void execute() {
		drawable_.drawStrokeRect(x_, y_, width_, height_);
	}
}
