package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

import util.FrameUtil;

public class WaitFrame extends JFrame {

	public WaitFrame() {
		// set name
		this.setTitle("monopoly");
		// Set default shutdown properties (end of program)
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// set wondow size
		this.setSize(380, 370);
		// user are not allowed to change window size
		this.setResizable(false);
		// center
		FrameUtil.setFrameCenter(this);
		add(new JLabel("loading......please wait",JLabel.CENTER));
		setVisible(true);
	}

}
