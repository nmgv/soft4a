package command;

public class DrawFillPolygonCommand implements Command {
	//描画対象
	protected Drawable drawable_;
	private int xPoints[], yPoints[], nPoints;

	//コンストラクタ
	public DrawFillPolygonCommand(Drawable drawable, int xPoints[], int yPoints[], int nPoints){
		this.drawable_ = drawable;
		this.xPoints = xPoints;
		this.yPoints = yPoints;
		this.nPoints = nPoints;
	}

	//実行
	public void execute() {
		drawable_.drawFillPolygon(xPoints, yPoints, nPoints);
	}
}
