package command;

import java.awt.Color;

public abstract class AbstractDrawShapeCommand extends MacroCommand {
	protected Drawable drawable_;
	private Color fillColor_, strokeColor_;
	private float strokeWeight_;
	
	public AbstractDrawShapeCommand(Drawable drawable, Color fillColor, Color strokeColor, float strokeWeight) {
		this.drawable_ = drawable;
		this.fillColor_ = fillColor;
		this.strokeColor_ = strokeColor;
		this.strokeWeight_ = strokeWeight;
	}

	private void createCommand() {
		clear();

		if(fillColor_ != null) {
			Command drawFillShapeCommand = getDrawFillShapeCommand();
			if(drawFillShapeCommand != null) {
				append(new ColorCommand(drawable_, fillColor_));
				append(drawFillShapeCommand);
			}
		}

		if(strokeColor_ != null) {
			Command drawStrokeShapeCommand = getDrawStrokeShapeCommand();
			if(drawStrokeShapeCommand != null) {
				append(new ColorCommand(drawable_, strokeColor_));
				append(new StrokeCommand(drawable_, strokeWeight_));
				append(drawStrokeShapeCommand);
			}
		}
	}

	public void execute() {
		createCommand();
		super.execute();
	}
	
	public abstract Command getDrawFillShapeCommand();
	public abstract Command getDrawStrokeShapeCommand();
}
