package command;

import java.awt.Color;

public class ColorCommand implements Command {
	private Color color_;
	private Drawable drawable_;

	public ColorCommand(Drawable drawable, Color color){
		this.drawable_ = drawable;
		this.color_ = color;
	}

	public void execute() {
		drawable_.setColor(color_);
	}

}
