/*
 * 基本多角形描画クラス
 * 
 * - 変数 -
 * drawable_ : 描画対象
 * xPoints_ : 頂点の x 座標の配列
 * yPoints_ : 頂点の y 座標の配列
 * nPoints_ : 頂点の数
 * 
 * - メソッド -
 * execute() : 実行(描画関数の呼び出し)
 * 
 */

package command;

public abstract class BaseDrawPolygonCommand implements Command {
	protected Drawable drawable_;
	protected int xPoints_[], yPoints_[], nPoints_;

	public BaseDrawPolygonCommand(Drawable drawable, int xPoints[], int yPoints[], int nPoints){
		this.drawable_ = drawable;
		this.xPoints_ = xPoints;
		this.yPoints_ = yPoints;
		this.nPoints_ = nPoints;
	}
}
