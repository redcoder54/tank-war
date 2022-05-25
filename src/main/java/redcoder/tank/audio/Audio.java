
package redcoder.tank.audio;

import javax.sound.sampled.*;

public class Audio implements AutoCloseable{

    private byte[] buffer = new byte[1024 * 1024 * 15];
    private AudioFormat audioFormat;
    private SourceDataLine sourceDataLine;
    private DataLine.Info dataLine_info;
    private AudioInputStream audioInputStream;

    public Audio(String fileName) throws Exception {
        audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
        audioFormat = audioInputStream.getFormat();
        dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
    }

    public void play() throws Exception {
        sourceDataLine.open(audioFormat, buffer.length);
        sourceDataLine.start();
        audioInputStream.mark(12358946);
        int len = 0;
        while ((len = audioInputStream.read(buffer)) > 0) {
            sourceDataLine.write(buffer, 0, len);
        }
        sourceDataLine.drain();
        sourceDataLine.close();
    }

    @Override
    public void close() {
        try {
            audioInputStream.close();
        } catch (Exception e) {
            // ignore
        }
    }
}
