package viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import util.PropertiesReader;

public class DirChooserButton extends JButton implements ActionListener {
	/***
	 * 画像指定ボタン
	 * @author ueno
	 */

	private String dialogTitle_;
	private JComponent parentComponent_;
	private String approveButtonText_;

	private StringBuffer targetDir_;

	private JFileChooser refChooser_;
	private JLabel targetLabel_;
	private String targetPropKey_;
	private util.PropertiesReader prop_;

	private CanvasFrame targetFrame_;

	public DirChooserButton(CanvasFrame targetFrame, String buttonText, String dialogTitle, JComponent parentComponent, String approveButtonText, StringBuffer targetDir, JLabel targetLabel, String targetPropKey, PropertiesReader prop){
		super(buttonText);
		initialize(targetFrame, dialogTitle, parentComponent, approveButtonText, targetDir, targetLabel, targetPropKey, prop);
	}

	private void initialize(CanvasFrame targetFrame, String dialogTitle, JComponent parentComponent, String approveButtonText,
			StringBuffer targetDir, JLabel targetLabel, String targetPropKey, PropertiesReader prop) {
		//ボタン
		this.targetFrame_ = targetFrame;
		this.dialogTitle_ = dialogTitle;
		this.parentComponent_ = parentComponent;
		this.approveButtonText_ = approveButtonText;

		this.targetDir_ = targetDir;

		this.targetLabel_ = targetLabel;

		this.addActionListener(this);

		this.targetPropKey_ = targetPropKey;
		this.prop_ = prop;

		//ダイアログについて
		refChooser_ = new JFileChooser();
		refChooser_.setDialogTitle(dialogTitle_);
		refChooser_.setFileSelectionMode(JFileChooser.FILES_ONLY);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		refChooser_.setCurrentDirectory(new File(prop_.getProp().getProperty(targetPropKey_))); //現ディレクトリの取得

		int selected_ = refChooser_.showDialog(parentComponent_, approveButtonText_);
		if (selected_ == JFileChooser.APPROVE_OPTION) {
			File file = refChooser_.getSelectedFile();
			String filePath = file.getPath();
			filePath = filePath.replace(System.getProperty("user.dir"), "."); //相対パスに変更

			targetLabel_.setText(filePath);

			targetDir_.replace(0, targetDir_.length(), filePath);
			prop_.getProp().setProperty(targetPropKey_, filePath);
			prop_.writeProperties();

			targetFrame_.getCanvas().newOffScreen();
//			targetPictPane_.repaint();

		} else if (selected_ == JFileChooser.CANCEL_OPTION) {
			System.out.println("キャンセルされました");
		} else if (selected_ == JFileChooser.ERROR_OPTION) {
			System.out.println("エラー又は取消しがありました");
		}
	}
}
