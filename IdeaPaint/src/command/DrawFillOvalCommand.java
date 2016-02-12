/*
 * 楕円(塗りつぶし)描画クラス
 */

package command;

public class DrawFillOvalCommand extends BaseDrawOvalCommand {
	public DrawFillOvalCommand(Drawable drawable, int x, int y, int width, int height){
		super(drawable, x, y, width, height);
	}

	public void execute() {
		drawable_.drawFillOval(x_, y_, width_, height_);
	}
}
