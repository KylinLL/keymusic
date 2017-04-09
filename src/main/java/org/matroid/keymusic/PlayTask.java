package org.matroid.keymusic;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayTask implements Runnable {

	byte tempBuffer[] = new byte[320];
	File file;
	AudioInputStream audioInputStream;// 文件流
	AudioFormat audioFormat;// 文件格式
	SourceDataLine sourceDataLine;// 输出设备

	public PlayTask(File file) {
		this.file = file;
	}

	public void run() {
		System.out.println("Start to play：" + file.getName());
		try {
			audioInputStream = AudioSystem.getAudioInputStream(file);
			audioFormat = audioInputStream.getFormat();
			if (audioFormat.getEncoding() != AudioFormat.Encoding.PCM_SIGNED) {
				audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, audioFormat.getSampleRate(), 16,
						audioFormat.getChannels(), audioFormat.getChannels() * 2, audioFormat.getSampleRate(), false);
				audioInputStream = AudioSystem.getAudioInputStream(audioFormat, audioInputStream);
			}

			// 打开输出设备
			DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat,
					AudioSystem.NOT_SPECIFIED);
			sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			sourceDataLine.open(audioFormat);
			sourceDataLine.start();

			int cnt;
			while ((cnt = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
				if (cnt > 0) {
					sourceDataLine.write(tempBuffer, 0, cnt);
				}
			}
			sourceDataLine.drain();
			sourceDataLine.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

}
