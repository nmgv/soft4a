package command;

import java.awt.Color;

public class DrawTextCommand extends AbstractDrawShapeCommand {
	private String text;
	private int x, y, fontSize, fontStyle;

	public DrawTextCommand(Drawable drawable_, int x, int y, Color fillColor, String text, int fontStyle, int fontSize) {
		super(drawable_, fillColor, null, 0);

		this.x = x;
		this.y = y;
		this.text = text;
		this.fontStyle = fontStyle;
		this.fontSize = fontSize;
	}

	public DrawTextCommand(Drawable drawable_, int x, int y, Color fillColor, String text, int fontSize) {
		super(drawable_, fillColor, null, 0);

		this.x = x;
		this.y = y;
		this.text = text;
		this.fontStyle = BaseDrawTextCommand.defaultFontStyle;
		this.fontSize = fontSize;
	}

	public DrawTextCommand(Drawable drawable_, int x, int y, Color fillColor, String text) {
		super(drawable_, fillColor, null, 0);

		this.x = x;
		this.y = y;
		this.text = text;
		this.fontStyle = BaseDrawTextCommand.defaultFontStyle;
		this.fontSize = BaseDrawTextCommand.defaultFontSize;
	}

	public Command getDrawFillShapeCommand() {
		Command cmd = new BaseDrawTextCommand(drawable_, x, y, text, fontStyle, fontSize);
		return cmd;
	}

	public Command getDrawStrokeShapeCommand() {
		return null;
	}
}