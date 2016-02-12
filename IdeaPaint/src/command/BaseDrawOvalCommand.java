/*
 * 基本楕円描画クラス
 * 
 * - 変数 -
 * drawable_ : 描画対象
 * x_ : 左上の x 座標
 * y_ : 左上の y 座標
 * width_ : 楕円の幅
 * height_ : 楕円の高さ
 * 
 * - メソッド -
 * execute() : 実行(描画関数の呼び出し)
 * 
 */

package command;

public abstract class BaseDrawOvalCommand implements Command {
	protected Drawable drawable_;
	protected int x_, y_, width_, height_;

	public BaseDrawOvalCommand(Drawable drawable, int x, int y, int width, int height){
		this.drawable_ = drawable;
		this.x_ = x;
		this.y_ = y;
		this.width_ = width;
		this.height_ = height;
	}
}
