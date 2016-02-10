package command;

import java.awt.Point;

public class DrawCommand implements Command {
	//描画対象
	protected Drawable drawable_;
	//描画対象
	private Point position_;

	//コンストラクタ
	public DrawCommand(Drawable drawable, Point position){
		this.drawable_ = drawable;
		this.position_ = position;
	}

	//実行
	public void execute() {
		drawable_.draw(position_.x, position_.y);
	}

}
