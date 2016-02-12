/*
 * 楕円(枠線)描画クラス
 */

package command;

public class DrawStrokeOvalCommand extends BaseDrawOvalCommand {
	public DrawStrokeOvalCommand(Drawable drawable, int x, int y, int width, int height){
		super(drawable, x, y, width, height);
	}

	public void execute() {
		drawable_.drawStrokeOval(x_, y_, width_, height_);
	}
}
