package command;

public class DrawFillPolygonCommand extends BaseDrawPolygonCommand {
	//コンストラクタ
	public DrawFillPolygonCommand(Drawable drawable_, int xPoints[], int yPoints[], int nPoints){
		super(drawable_, xPoints, yPoints, nPoints);
	}

	//実行
	public void execute() {
		drawable_.drawFillPolygon(xPoints, yPoints, nPoints);
	}
}
