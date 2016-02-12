/* 色指定有りの枠つき四角のコマンド生成
 * インスタンス生成時に
 * Drawable, Point(座標データ), int(横長), int(縦長), Color(塗りつぶし色), Color(線の色)
 * を入力することでコマンドを生成できます. */

package command;

import java.awt.Color;
import java.awt.Point;
import java.awt.BasicStroke;

public class DrawRectangleCommand implements Command{
	//描画対象
	protected Drawable drawable_;
	//描画対象
	private Point position_;

	private int width_;

	private int height_;

	private Color fcolor_;

	private Color scolor_;
	
	private BasicStroke stroke_;

	//コンストラクタ
	public DrawRectangleCommand(Drawable drawable, Point position, int width, int height, Color fcolor, Color scolor, float strokeweight){
		this.drawable_ = drawable;
		this.position_ = position;
		this.width_ = width;
		this.height_ = height;
		this.fcolor_ = fcolor;
		this.scolor_ = scolor;
		this.stroke_ = new BasicStroke(strokeweight);
	}
	
	//実行
	public void execute() {
		drawable_.setColor(fcolor_);
		drawable_.drawFillRect(position_.x, position_.y, width_, height_);
		drawable_.setColor(scolor_);
		drawable_.setStroke(stroke_);
		drawable_.drawLineRect(position_.x, position_.y, width_, height_);
	}
}
