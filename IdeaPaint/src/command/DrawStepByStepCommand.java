package command;

import java.util.Iterator;

public class DrawStepByStepCommand extends MacroCommand {
	private int delay_;

	public DrawStepByStepCommand(int delay) {
		delay_ = delay < 0 ? 0 : delay;
	}
	
	public void execute() {
		Iterator<Command> it = commands_.iterator();
		while(it.hasNext()){
			((Command)it.next()).execute();
			try {
				Thread.sleep(delay_);
			} catch(InterruptedException e) {
				continue;
			}
		}
	}
}
