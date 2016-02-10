package util;

import java.awt.Point;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	/***
	 * Utility クラス
	 * @author ueno
	 */

	private static Random r_ = new Random();
	private final static int LENGTH_ = 50; //振れ幅

	//Utility クラスのため，プライベートコンストラクタ
	private Util(){}

	//座標を入力文字とキャンバスの大きさから大体決める
	public static Point makePoint(String keyword, int width, int height){
		boolean isKeywordXMatch = false;
		boolean isKeywordYMatch = false;
		Point p = new Point();
		int x = 0;
		int y = 0;

		if(keyword.contains("真ん中")){
			isKeywordXMatch = true;
			isKeywordYMatch = true;
			x = width / 2 - LENGTH_ + r_.nextInt(LENGTH_ * 2);
			y = height / 2 - LENGTH_ + r_.nextInt(LENGTH_ * 2);
		}
		if(keyword.contains("下")){
			isKeywordYMatch = true;
			y = height / 2 + r_.nextInt(LENGTH_) * 2;
		}
		if(keyword.contains("上")){
			isKeywordYMatch = true;
			y = r_.nextInt(LENGTH_ * 2);
		}
		if(keyword.contains("右"))
			isKeywordXMatch = true;{
			x = width / 2 + r_.nextInt(LENGTH_) * 2;
		}
		if(keyword.contains("左")){
			isKeywordXMatch = true;
			x = r_.nextInt(LENGTH_ * 2);
		}

		//X と Y の keyword がそれぞれあるか調べ，なければキャンバス内でランダムに
		if(!isKeywordXMatch){
			x = r_.nextInt(width);
		}
		if(!isKeywordYMatch){
			y = r_.nextInt(height);
		}

		p.setLocation(x, y);
		return p;
	}

	/***
	 * 指定有り
	 * @param min_x
	 * @param min_y
	 * @param max_x
	 * @param max_y
	 * @return
	 */
	public static Point makePoint(int min_x, int min_y, int max_x, int max_y){
		int x;
		int y;
		Point p = new Point();

		x = r_.nextInt(max_x - min_x) + min_x;
		y = r_.nextInt(max_y - min_y) + min_y;

		p.setLocation(x, y);

		return p;
	}

	//実行回数を決める
	public static int makeCount(String keyword){
		int count = 0;
		Matcher m;
		Pattern p;
		if(keyword.contains("いっぱい")){
			count = 10;
		} else if(keyword.contains("回")) {
			p = Pattern.compile("([2-9])回");
			m = p.matcher(keyword);
			if(m.find()){
				count = Integer.parseInt(m.group(1));
			}
		} else { //マッチしなければ 1 回
			count = 1;
		}
		return count;
	}

}
