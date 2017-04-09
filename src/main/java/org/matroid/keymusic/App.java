package org.matroid.keymusic;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App {

	public static void main(String[] args) {
		System.out.println("Hello World!");
		new App().play();
	}

	private void play() {
		File file = new File("piano/1C.mp3");
		Thread playThread = new Thread(new PlayTask(file));
		playThread.start();
	}

}
