package cn.edu.whu.unsc.audio.transmitter;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

public class ReceivingLoop extends Thread {
    private final String TAG = "ReceivingLoop";

    ReceivingLoop() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {

        int minBuffSize = AudioRecord.getMinBufferSize(TransmitterParameters.SAMPLE_RATE,
                TransmitterParameters.AUDIO_FORMAT_CHANNEL,
                TransmitterParameters.AUDIO_FORMAT_ENCODING);

        if (minBuffSize == AudioRecord.ERROR_BAD_VALUE) {
            Log.e(TAG, "ReceivingLoop::run(): Invalid AudioRecord parameter.\n");
            return;
        }

        AudioRecord audioRecord = new AudioRecord.Builder()
                .setAudioSource(TransmitterParameters.AUDIO_RECORD_SOURCE)
                .setAudioFormat(new AudioFormat.Builder()
                        .setSampleRate(TransmitterParameters.SAMPLE_RATE)
                        .setEncoding(TransmitterParameters.AUDIO_FORMAT_ENCODING)
                        .setChannelMask(TransmitterParameters.AUDIO_FORMAT_CHANNEL)
                        .build())
                .setBufferSizeInBytes(minBuffSize)
                .build();

        float[] messageSingleton = new float[minBuffSize];

    }
}
