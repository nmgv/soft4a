package command;

import java.util.Iterator;
import java.util.Stack;

public class MacroCommand implements Command {
	//命令の集合
	protected Stack<Command> commands_ = new Stack<Command>();

	//実行
	public void execute() {
		Iterator<Command> it = commands_.iterator();
		while(it.hasNext()){
			((Command)it.next()).execute();
		}
	}

	//追加
	public void append(Command cmd) {
		if(cmd != this){
			commands_.push(cmd);
		}
	}

	//最後の命令を削除
	public void undo(){
		if(!commands_.empty()){
			commands_.pop();
		}
	}

	//全部削除
	public void clear(){
		commands_.clear();
	}

}
