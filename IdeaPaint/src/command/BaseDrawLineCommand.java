/*
 * 基本直線描画クラス
 * 
 * - 変数 -
 * drawable_ : 描画対象
 * x1_ : 始点の x 座標
 * y1_ : 始点の y 座標
 * x2_ : 終点の x 座標
 * y2_ : 終点の y 座標
 *  
 * - メソッド -
 * execute() : 実行(描画関数の呼び出し)
 * 
 */

package command;

public class BaseDrawLineCommand implements Command {
	protected Drawable drawable_;
	private int x1_, y1_, x2_, y2_;

	public BaseDrawLineCommand(Drawable drawable, int x1, int y1, int x2, int y2){
		this.drawable_ = drawable;
		this.x1_ = x1;
		this.y1_ = y1;
		this.x2_ = x2;
		this.y2_ = y2;
	}

	public void execute() {
		drawable_.baseDrawLine(x1_, y1_, x2_, y2_);
	}
}