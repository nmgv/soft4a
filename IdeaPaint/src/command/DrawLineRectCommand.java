/* 色指定なしの四角（線）のコマンド生成 */

package command;

import java.awt.Point;

public class DrawLineRectCommand implements Command{
	//描画対象
	protected Drawable drawable_;
	//描画対象
	private Point position_;

	private int width_;

	private int height_;

		//コンストラクタ
	public DrawLineRectCommand(Drawable drawable, Point position, int width, int height){
		this.drawable_ = drawable;
		this.position_ = position;
		this.width_ = width;
		this.height_ = height;
	}

	//実行
	public void execute() {
		drawable_.drawLineRect(position_.x, position_.y, width_, height_);
	}
}