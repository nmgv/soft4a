/* 色指定有りの線描画コマンド
 * インスタンス生成時に
 * Drawable, Point(座標データ1), Point(座標データ2), Color(線の色)
 * を入力することで線描画のコマンドを生成できます. */

package command;

import java.awt.Color;
import java.awt.Point;

public class DrawLineCommand implements Command{
	//描画対象
	protected Drawable drawable_;
	//描画対象
	private Point p1_;

	private Point p2_;

	private int width_;

	private int height_;

	private Color lcolor_;

	//コンストラクタ
	public DrawLineCommand(Drawable drawable, Point p1, Point p2, Color lcolor){
		this.drawable_ = drawable;
		this.p1_ = p1;
		this.p2_ = p2;
		this.lcolor_ = lcolor;
	}

	//実行
	public void execute() {
		drawable_.setColor(lcolor_);
		drawable_.drawLine(p1_.x, p1_.y,p2_.x, p2_.y);
	}
}
