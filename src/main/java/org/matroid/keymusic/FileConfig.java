package org.matroid.keymusic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;

public class FileConfig {

	public static List<File> files = new ArrayList<File>();
	public static ImageIcon background = new ImageIcon("image/piano.jpg");

	static {
		File file1 = new File("piano/1C.mp3");
		File file2 = new File("piano/2D.mp3");
		File file3 = new File("piano/3E.mp3");
		File file4 = new File("piano/4F.mp3");
		File file5 = new File("piano/5G.mp3");
		File file6 = new File("piano/6A.mp3");
		File file7 = new File("piano/7B.mp3");
		files.add(file1);
		files.add(file2);
		files.add(file3);
		files.add(file4);
		files.add(file5);
		files.add(file6);
		files.add(file7);
	}

}
