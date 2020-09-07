package cn.edu.whu.unsc.audio.transmitter;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

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

        AudioTrack audioTracker = new AudioTrack.Builder()
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

        float[] messageSingleton = new float[minBuffSize];
        audioTracker.play();

//        double frequence = 8000.0;
//        for (int j = 0; j < 400; j++) {
//            for (int i = 0; i < minBuffSize; i++) {
//                messageSingleton[i] = (float) Math.cos(2.0 * Math.PI * frequence * TransmitterParameters.TIME_INTERVAL * i);
//            }
//            audioTrack.write(messageSingleton, 0, minBuffSize, AudioTrack.WRITE_BLOCKING);
//
//            frequence += 100;
//        }

        ChirpGenerator chirpGenerator = new ChirpGenerator(TransmitterParameters.SAMPLE_RATE, 15000, 0.2, 21000);
        ArrayList<Float> chirpMessage = chirpGenerator.getChirp();
        int chirpMessageSize = chirpMessage.size();

        ArrayList<Float> chirpMessageCycle = new ArrayList<>();
        int chirpMessageCycleLength = 1 * TransmitterParameters.SAMPLE_RATE;
        for (int i = 0; i < chirpMessageCycleLength; i++) {
            if (i < chirpMessageSize) {
                chirpMessageCycle.add(chirpMessage.get(i));
            } else {
                chirpMessageCycle.add(0.0F);
            }
        }


        int messageSingletonIndex = 0;
        for (int cycleIndex = 0; cycleIndex < 60; cycleIndex++) {
            for (int sampleIndexInCycle = 0; sampleIndexInCycle < chirpMessageCycleLength; sampleIndexInCycle++) {
                int sampleIndex = chirpMessageCycleLength * cycleIndex + sampleIndexInCycle;
                int minBuffSizeMod = sampleIndex % minBuffSize;
                messageSingleton[minBuffSizeMod] = chirpMessageCycle.get(sampleIndexInCycle);
                if (minBuffSizeMod == minBuffSize - 1) {
                    audioTracker.write(messageSingleton, 0, minBuffSize, AudioTrack.WRITE_BLOCKING);
                }
            }
        }

        audioTracker.stop();
        audioTracker.release();
    }

}
