package org.matroid.keymusic;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {

	private static List<File> files = FileConfig.files;
	private static ImageIcon img = FileConfig.background;
	static ExecutorService exec = Executors.newFixedThreadPool(10);

	public static void main(String[] args) {
		showFrame();
	}

	public static void showFrame() {

		JFrame frame = new JFrame();
		frame.setTitle("Key Music");
		frame.setSize(506, 361);
		frame.setLayout(new FlowLayout());
		frame.setVisible(true);
		frame.setResizable(false);
		// frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel imgLabel = new JLabel(img);
		frame.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));
		imgLabel.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		((JComponent) frame.getContentPane()).setOpaque(false);

		frame.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyCode());
				PlayTask task = new PlayTask(files.get(e.getKeyCode() % 7));
				exec.submit(task);
			}
		});
	}

}
