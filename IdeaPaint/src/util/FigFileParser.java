package util;

import java.awt.Color;
import java.util.ArrayList;

import command.Command;
import command.DrawTextCommand;
import command.DrawTriangleCommand;
import command.Drawable;
import command.MacroCommand;

public class FigFileParser {
	Drawable drawable_;
	ArrayList<ArrayList<String>> data;

	public FigFileParser(Drawable drawable_, ArrayList<ArrayList<String>> data) {
		this.drawable_ = drawable_;
		this.data = data;
	}

	public MacroCommand parse() {
		MacroCommand drawFigCmd = new MacroCommand();
		
		for(ArrayList<String> list : data) {
			String figType = list.get(0);
			list.remove(0);
			Command cmd = null;

			switch(figType) {
			case "triangle":
				cmd = parseTriangle(list);
				break;
			case "text":
				cmd = parseText(list);
				break;
			}

			if(cmd != null) drawFigCmd.append(cmd);
		}

		return drawFigCmd;
	}

	private Command parseTriangle(ArrayList<String> list) {
		try {
			int x1 = Validator.validatePositiveInt(list.get(0));
			int y1 = Validator.validatePositiveInt(list.get(1));
			int x2 = Validator.validatePositiveInt(list.get(2));
			int y2 = Validator.validatePositiveInt(list.get(3));
			int x3 = Validator.validatePositiveInt(list.get(4));
			int y3 = Validator.validatePositiveInt(list.get(5));
			Color fillColor = Validator.validateColor(list.get(6));
			Color strokeColor = Validator.validateColor(list.get(7));
			Float strokeWeight = Validator.validateStrokeWeight(list.get(8));

			return new DrawTriangleCommand(drawable_, x1, y1, x2, y2, x3, y3, fillColor, strokeColor, strokeWeight);
		} catch(Exception e) {
			return null;
		}
	}

	private Command parseText(ArrayList<String> list) {
		try {
			int x = Validator.validatePositiveInt(list.get(0));
			int y = Validator.validatePositiveInt(list.get(1));
			Color fillColor = Validator.validateColor(list.get(2));
			String text = list.get(3);
			int fontStyle = Validator.validatePositiveInt(list.get(4));
			int fontSize = Validator.validatePositiveInt(list.get(5));

			return new DrawTextCommand(drawable_, x, y, fillColor, text, fontStyle, fontSize);
		} catch(Exception e) {
			return null;
		}
	}
}
