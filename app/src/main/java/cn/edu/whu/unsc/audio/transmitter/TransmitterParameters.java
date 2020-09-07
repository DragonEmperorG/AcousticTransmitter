package cn.edu.whu.unsc.audio.transmitter;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.media.MediaRecorder;
import android.os.Build;

import androidx.annotation.RequiresApi;


@RequiresApi(api = Build.VERSION_CODES.O)
public class TransmitterParameters {
    // The source sample rate expressed in Hz.
    static final int SAMPLE_RATE = 48000;
    //
    static final float TIME_INTERVAL = 1.0F / SAMPLE_RATE;
    // The configuration of the audio channels.
    static final int AUDIO_FORMAT_CHANNEL = AudioFormat.CHANNEL_OUT_MONO;
    // The format in which the audio data is represented.
    static final int AUDIO_FORMAT_ENCODING = AudioFormat.ENCODING_PCM_FLOAT;
    // "Why" you are playing a sound, what is this sound used for.
    static final int AUDIO_ATTRIBUTE_USAGE = AudioAttributes.USAGE_GAME;
    // "What" you are playing. The content type expresses the general category of the content.
    static final int AUDIO_ATTRIBUTE_CONTENT_TYPE = AudioAttributes.CONTENT_TYPE_SONIFICATION;
    // "How" is playback to be affected, see the flag definitions for the specific playback behaviors they control.
    static final int AUDIO_ATTRIBUTE_FLAG = AudioAttributes.FLAG_LOW_LATENCY;
    //
    static final int AUDIO_TRACK_PERFORMANCE_MODE = AudioTrack.PERFORMANCE_MODE_LOW_LATENCY;
    //
    static final int AUDIO_TRACK_TRANSFER_MODE = AudioTrack.MODE_STREAM;

    // Audio Recorder
    static final int AUDIO_RECORD_SOURCE = MediaRecorder.AudioSource.UNPROCESSED;
}
