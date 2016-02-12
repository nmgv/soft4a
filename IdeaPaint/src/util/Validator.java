package util;

import java.awt.Color;

public class Validator {
	public static int validatePositiveInt(String raw) throws Exception {
		int value;
		try {
			value = Integer.valueOf(raw);
		} catch(Exception e) {
			System.err.println("値をint型に変換できません(検出値 : " + raw + ").");
			throw e;
		}

		if(value < 0) {
			System.err.println("値は0以上でなければなりません(検出値 : " + raw + ").");
			throw new Exception();
		}

		return value;
	}

	public static float validatePositiveFloat(String raw) throws Exception {
		float value;
		try {
			value = Float.valueOf(raw);
		} catch(Exception e) {
			System.err.println("値をfloat型に変換できません(検出値 : " + raw + ").");
			throw e;
		}

		if(value < 0) {
			System.err.println("値は0以上でなければなりません(検出値 : " + raw + ").");
			throw new Exception();
		}

		return value;
	}

	public static float validateStrokeWeight(String raw) throws Exception {
		if(raw.equals("none")) return 0;

		return validatePositiveFloat(raw);
	}

	public static Color validateColor(String raw) throws Exception {
		if(raw.equals("none")) return null;

		Color color;
		String separator = " ";
		try {
			String rgb[] = raw.split(separator);
			int r = Integer.valueOf(rgb[0]) & 255;
			int g = Integer.valueOf(rgb[1]) & 255;
			int b = Integer.valueOf(rgb[2]) & 255;
			color = new Color(r, g, b);
		} catch(Exception e) {
			System.err.println("値は'" + separator + "'で区切られたRGB値でなければなりません(検出値 : " + raw + ")");
			throw e;
		}

		return color;
	}
}
