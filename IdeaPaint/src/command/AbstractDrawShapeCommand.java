package command;

import java.awt.Color;

public abstract class AbstractDrawShapeCommand extends MacroCommand {
	protected Drawable drawable_;
	Color fillColor, strokeColor;
	float strokeWeight;
	
	public AbstractDrawShapeCommand(Drawable drawable_, Color fillColor, Color strokeColor, float strokeWeight) {
		this.drawable_ = drawable_;
		this.fillColor = fillColor;
		this.strokeColor = strokeColor;
		this.strokeWeight = strokeWeight;
	}

	private void createCommand() {
		if(fillColor != null) {
			Command drawFillShapeCommand = getDrawFillShapeCommand();
			if(drawFillShapeCommand != null) {
				append(new ColorCommand(drawable_, fillColor));
				append(drawFillShapeCommand);
			}
		}

		if(strokeColor != null) {
			Command drawStrokeShapeCommand = getDrawStrokeShapeCommand();
			if(drawStrokeShapeCommand != null) {
				append(new ColorCommand(drawable_, strokeColor));
				append(new StrokeCommand(drawable_, strokeWeight));
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
