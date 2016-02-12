package command;

public class DrawFillCommand implements Command {
	//描画対象
	protected Drawable drawable_;
	//描画対象
	private int x, y, width, height;

	//コンストラクタ
	public DrawFillCommand(Drawable drawable, int x, int y, int width, int height){
		this.drawable_ = drawable;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	//実行
	public void execute() {
		drawable_.drawFillOval(x, y, width, height);
	}

}
