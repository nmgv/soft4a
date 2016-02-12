package util;

import java.awt.Color;
import java.util.ArrayList;

import command.Command;
import command.DrawLineCommand;
import command.DrawOvalCommand;
import command.DrawRectCommand;
import command.DrawStepByStepCommand;
import command.DrawTextCommand;
import command.DrawTriangleCommand;
import command.Drawable;
import command.MacroCommand;

public class FigFileParser {
	Drawable drawable_;
	ArrayList<ArrayList<String>> data;
	int delay_;

	public FigFileParser(Drawable drawable_, ArrayList<ArrayList<String>> data, int delay) {
		this.drawable_ = drawable_;
		this.data = data;
		this.delay_ = delay;
	}

	public MacroCommand parse() {
		MacroCommand drawFigCmd = new DrawStepByStepCommand(delay_);

		for(ArrayList<String> list : data) {
			String figType = list.get(0);
			list.remove(0);
			Command cmd = null;

			switch(figType) {
			case "rect":
				cmd = parseRect(list);
				break;
			case "oval":
				cmd = parseOval(list);
				break;
			case "triangle":
				cmd = parseTriangle(list);
				break;
			case "line":
				cmd = parseLine(list);
				break;
			case "text":
				cmd = parseText(list);
				break;
			}

			if(cmd != null) drawFigCmd.append(cmd);
		}

		return drawFigCmd;
	}


	/*
	 * ----- パーズメソッド ----- 
	 * 解析完了 -> Command を返す
	 * 解析失敗 -> null を返す
	 * 
	 */

	// 矩形解析
	private Command parseRect(ArrayList<String> list) {
		int n = 7;
		if(list.size() < n) {
			System.err.println("rect は " + n + " 個のプロパティを必要とします");
			return null;
		}

		try{
			int x = Validator.validatePositiveInt(list.get(0));
			int y = Validator.validatePositiveInt(list.get(1));
			int width = Validator.validatePositiveInt(list.get(2));
			int height = Validator.validatePositiveInt(list.get(3));
			Color fillColor = Validator.validateColor(list.get(4));
			Color strokeColor = Validator.validateColor(list.get(5));
			Float strokeWeight = Validator.validateStrokeWeight(list.get(6));

			return new DrawRectCommand(drawable_, x, y, width, height, fillColor, strokeColor, strokeWeight);
		} catch(Exception e) {
			return null;
		}
	}

	// 楕円解析
	private Command parseOval(ArrayList<String> list) {
		int n = 7;
		if(list.size() < n) {
			System.err.println("oval は " + n + " 個のプロパティを必要とします");
			return null;
		}

		try {
			int x1 = Validator.validatePositiveInt(list.get(0));
			int y1 = Validator.validatePositiveInt(list.get(1));
			int x2 = Validator.validatePositiveInt(list.get(2));
			int y2 = Validator.validatePositiveInt(list.get(3));
			Color fillColor = Validator.validateColor(list.get(4));
			Color strokeColor = Validator.validateColor(list.get(5));
			Float strokeWeight = Validator.validateStrokeWeight(list.get(6));

			return new DrawOvalCommand(drawable_, x1, y1, x2, y2, fillColor, strokeColor, strokeWeight);
		} catch(Exception e) {
			return null;
		}
	}

	// 三角形解析
	private Command parseTriangle(ArrayList<String> list) {
		int n = 9;
		if(list.size() < n) {
			System.err.println("triangle は " + n + " 個のプロパティを必要とします");
			return null;
		}

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

	// 直線解析
	private Command parseLine(ArrayList<String> list) {
		int n = 6;
		if(list.size() < n) {
			System.err.println("line は " + n + " 個のプロパティを必要とします");
			return null;
		}

		try {
			int x1 = Validator.validatePositiveInt(list.get(0));
			int y1 = Validator.validatePositiveInt(list.get(1));
			int x2 = Validator.validatePositiveInt(list.get(2));
			int y2 = Validator.validatePositiveInt(list.get(3));
			Color strokeColor = Validator.validateColor(list.get(4));
			Float strokeWeight = Validator.validateStrokeWeight(list.get(5));

			return new DrawLineCommand(drawable_, x1, y1, x2, y2, strokeColor, strokeWeight);
		} catch(Exception e) {
			return null;
		}
	}

	// 文字列解析
	private Command parseText(ArrayList<String> list) {
		int n = 6;
		if(list.size() < n) {
			System.err.println("text は " + n + " 個のプロパティを必要とします");
			return null;
		}

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
