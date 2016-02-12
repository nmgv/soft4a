package command;

import java.awt.Point;

public class DefaultDrawFillRectCommand implements Command {
	//描画対象
	protected Drawable drawable_;
	//描画対象
	private Point position_;

	private int length_;

	//コンストラクタ
	public DefaultDrawFillRectCommand(Drawable drawable, Point position, int length){
		this.drawable_ = drawable;
		this.position_ = position;
		this.length_ = length;
	}

	//実行
	public void execute() {
		drawable_.defaultDrawFillRect(position_.x, position_.y, length_);
	}

}