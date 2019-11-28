package cn.edu.whu.unsc.audio.transmitter;

public class ChirpGenerator {
    static private String TAG = ChirpGenerator.class.getName();

    private int sampleRate = 48000;
    private double duration = 43.0 / 1000;
    private double startFrequence = 16000;
    private double stopFrequence = 21000;

    private double timeInvertal = 1.0 / sampleRate;

    public ChirpGenerator(int _sampleRate, double _f0, double _t1, double _f1) {
        sampleRate = _sampleRate;
        duration = _t1;
        startFrequence = _f0;
        stopFrequence = _f1;
        timeInvertal = 1.0 / sampleRate;
    }


}
