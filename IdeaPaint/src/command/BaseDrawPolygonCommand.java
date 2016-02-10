package command;

public abstract class BaseDrawPolygonCommand implements Command {
	//描画対象
	protected Drawable drawable_;
	protected int xPoints[], yPoints[], nPoints;

	//コンストラクタ
	public BaseDrawPolygonCommand(Drawable drawable_, int xPoints[], int yPoints[], int nPoints){
		this.drawable_ = drawable_;
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		this.nPoints = nPoints;
	}

	//実行
	public abstract void execute();
}
