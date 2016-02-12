package command;

import java.awt.Color;

public class DrawOvalCommand extends AbstractDrawShapeCommand {
	private int x, y, width, height;

	public DrawOvalCommand(Drawable drawable_, int x, int y, int r,Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable_, fillColor, strokeColor, strokeWeight);

		this.x = x;
		this.y = y;
		width = r;
		height = r;
	}

	public DrawOvalCommand(Drawable drawable_, int x1, int y1, int x2, int y2, Color fillColor, Color strokeColor, float strokeWeight) {
		super(drawable_, fillColor, strokeColor, strokeWeight);

		this.x = x2;
		if( x2 > x1 )
			this.x = x1;
		this.y = y2;
		if( y2 > y1 )
			this.y = y1;
		width = Math.abs( x2 - x1 );
		height = Math.abs( y2 - y1 );
	}

	public Command getDrawFillShapeCommand() {
		Command cmd = new DrawFillCommand(drawable_, x, y, width, height);
		return cmd;
	}

	public Command getDrawStrokeShapeCommand() {
		Command cmd = new DrawStrokeCommand(drawable_, x, y, width, height);
		return cmd;
	}
}
