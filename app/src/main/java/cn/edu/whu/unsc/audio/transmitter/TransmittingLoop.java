package cn.edu.whu.unsc.audio.transmitter;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class TransmittingLoop extends Thread {
    private final String TAG = "TransmittingLoop";

    TransmittingLoop() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {

        int minBuffSize = AudioTrack.getMinBufferSize(TransmitterParameters.SAMPLE_RATE,
                TransmitterParameters.AUDIO_FORMAT_CHANNEL,
                TransmitterParameters.AUDIO_FORMAT_ENCODING);

        AudioTrack audioTrack = new AudioTrack.Builder()
                .setAudioAttributes(new AudioAttributes.Builder()
                        .setUsage(TransmitterParameters.AUDIO_ATTRIBUTE_USAGE)
                        .setContentType(TransmitterParameters.AUDIO_ATTRIBUTE_CONTENT_TYPE)
                        .setFlags(TransmitterParameters.AUDIO_ATTRIBUTE_FLAG)
                        .build())
                .setAudioFormat(new AudioFormat.Builder()
                        .setSampleRate(TransmitterParameters.SAMPLE_RATE)
                        .setEncoding(TransmitterParameters.AUDIO_FORMAT_ENCODING)
                        .setChannelMask(TransmitterParameters.AUDIO_FORMAT_CHANNEL)
                        .build())
                .setBufferSizeInBytes(minBuffSize)
                .setPerformanceMode(TransmitterParameters.AUDIO_TRACK_PERFORMANCE_MODE)
                .setTransferMode(TransmitterParameters.AUDIO_TRACK_TRANSFER_MODE)
                .build();

        float[] messages = new float[minBuffSize];
        audioTrack.play();

        double frequence = 8000.0;
        for (int j = 0; j < 400; j++) {
            for (int i = 0; i < minBuffSize; i++) {
                messages[i] = (float) Math.cos(2.0 * Math.PI * frequence * TransmitterParameters.TIME_INTERVAL * i);
            }
            audioTrack.write(messages, 0, minBuffSize, AudioTrack.WRITE_BLOCKING);

            frequence += 100;
        }

        audioTrack.stop();
        audioTrack.release();

    }
}
