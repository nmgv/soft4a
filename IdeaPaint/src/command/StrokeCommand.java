package command;

import java.awt.BasicStroke;

public class StrokeCommand implements Command {
	private Drawable drawable_;
	private BasicStroke stroke;

	public StrokeCommand(Drawable drawable_, float width) {
		this.drawable_ = drawable_;
		this.stroke = new BasicStroke(width);
	}

	public void execute() {
		drawable_.setStroke(stroke);
	}
}
