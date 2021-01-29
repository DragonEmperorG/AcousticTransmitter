package cn.edu.whu.unsc.audio.transmitter;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

public class TransmittingLoop extends Thread {

    private final String TAG = "TransmittingLoop";

    private boolean transmittingLoopRunningFlag = false;

    private StringBuilder stringBuilder4Log;

    TransmittingLoop() {

    }

    public void startTransmittingLoop() {
        transmittingLoopRunningFlag = true;
        this.start();
    }

    public void stopTransmittingLoop() {
        transmittingLoopRunningFlag = false;
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

        stringBuilder4Log = new StringBuilder();
        stringBuilder4Log.append(String.format(Locale.CHINA, "  sample rate     : %d Hz (configured %d Hz)\n", audioTracker.getSampleRate(), TransmitterParameters.SAMPLE_RATE));
        Log.i(TAG, stringBuilder4Log.toString());

        float[] messageSingleton = new float[minBuffSize];
        audioTracker.play();

        ChirpGenerator chirpGenerator = new ChirpGenerator(TransmitterParameters.SAMPLE_RATE, 19000, 0.45, 19500);
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

        int cycleCounter = 0;
        while (transmittingLoopRunningFlag) {

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
