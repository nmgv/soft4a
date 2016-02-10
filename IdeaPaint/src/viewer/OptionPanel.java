package viewer;

import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import util.PropertiesReader;

public class OptionPanel extends JPanel {
	/***
	 * オプションパネル
	 * @author ueno
	 */

	private String targetPropKey_ = "canvasPath";

	public OptionPanel(CanvasFrame targetFrame, PropertiesReader prop, String targetPropKeyPre) {
		initialize(targetFrame, prop, targetPropKeyPre);
	}

	private void initialize(CanvasFrame targetFrame, PropertiesReader prop, String targetPropKeyPre) {
		if (targetPropKeyPre == null) {
			targetPropKeyPre = targetPropKey_;
		}

		//prop の読み込み
		prop.readProperities();
		this.setLayout(new FlowLayout());
		this.setBorder(new TitledBorder("Option"));

		StringBuffer imageFilePathBuf = new StringBuffer(prop.getProp().getProperty(targetPropKeyPre));
		JLabel imageFilePathLabel = new JLabel(imageFilePathBuf.toString());

		JButton imageFilePathButton = null;

		imageFilePathButton = new DirChooserButton(targetFrame, "Select Background", "画像を指定", this, "OK", imageFilePathBuf, imageFilePathLabel, targetPropKeyPre, prop);

		Box box1 = Box.createHorizontalBox();

		box1.setAlignmentX(LEFT_ALIGNMENT);
		box1.add(imageFilePathButton);
		box1.add(imageFilePathLabel);

		this.add(box1);
	}
}