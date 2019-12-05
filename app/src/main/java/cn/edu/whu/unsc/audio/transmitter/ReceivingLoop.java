package cn.edu.whu.unsc.audio.transmitter;

import android.media.AudioRecord;
import android.os.Build;

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

//        AudioRecord audioTrack = new AudioRecord.Builder()
//                .setAudioAttributes(new AudioRecord.Builder()
//                        .setUsage(TransmitterParameters.AUDIO_ATTRIBUTE_USAGE)
//                        .setContentType(TransmitterParameters.AUDIO_ATTRIBUTE_CONTENT_TYPE)
//                        .setFlags(TransmitterParameters.AUDIO_ATTRIBUTE_FLAG)
//                        .build())
//                .setAudioFormat(new AudioFormat.Builder()
//                        .setSampleRate(TransmitterParameters.SAMPLE_RATE)
//                        .setEncoding(TransmitterParameters.AUDIO_FORMAT_ENCODING)
//                        .setChannelMask(TransmitterParameters.AUDIO_FORMAT_CHANNEL)
//                        .build())
//                .setBufferSizeInBytes(minBuffSize)
//                .setPerformanceMode(TransmitterParameters.AUDIO_TRACK_PERFORMANCE_MODE)
//                .setTransferMode(TransmitterParameters.AUDIO_TRACK_TRANSFER_MODE)
//                .build();

        float[] messageSingleton = new float[minBuffSize];

    }
}
