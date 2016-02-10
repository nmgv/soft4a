package command;

public class DrawStrokePolygonCommand extends BaseDrawPolygonCommand {
	//コンストラクタ
	public DrawStrokePolygonCommand(Drawable drawable_, int xPoints[], int yPoints[], int nPoints){
		super(drawable_, xPoints, yPoints, nPoints);
	}

	//実行
	public void execute() {
		drawable_.drawStrokePolygon(xPoints, yPoints, nPoints);
	}
}
