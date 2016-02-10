package command;

import java.awt.Color;

public class DrawTriangleCommand extends AbstractDrawShapeCommand {
	private int xPoints[], yPoints[], nPoints;

	public DrawTriangleCommand(Drawable drawable_, int xPoints[], int yPoints[], Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable_, fillColor, strokeColor, strokeWeight);

		this.nPoints = 3;
		this.xPoints = xPoints;
		this.yPoints = yPoints;
	}
	
	public DrawTriangleCommand(Drawable drawable_, int x1, int y1, int x2, int y2, int x3, int y3, Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable_, fillColor, strokeColor, strokeWeight);

		this.nPoints = 3;
		this.xPoints = new int[nPoints];
		this.yPoints = new int[nPoints];
		this.xPoints[0] = x1;
		this.xPoints[1] = x2;
		this.xPoints[2] = x3;
		this.yPoints[0] = y1;
		this.yPoints[1] = y2;
		this.yPoints[2] = y3;
	}

	public Command getDrawFillShapeCommand() {
		Command cmd = new DrawFillPolygonCommand(drawable_, xPoints, yPoints, nPoints);
		return cmd;
	}

	public Command getDrawStrokeShapeCommand() {
		Command cmd = new DrawStrokePolygonCommand(drawable_, xPoints, yPoints, nPoints);
		return cmd;
	}
}
