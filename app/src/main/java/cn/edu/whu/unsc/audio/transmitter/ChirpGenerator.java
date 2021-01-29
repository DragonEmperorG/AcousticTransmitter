package cn.edu.whu.unsc.audio.transmitter;

import java.util.ArrayList;

public class ChirpGenerator {

    static private String TAG = ChirpGenerator.class.getName();

    private int sampleRate = 48000;
    private double duration = 0.043;
    private double startFrequence = 16000;
    private double stopFrequence = 21000;
    private double slope = 116279.06976744186046511627906977;

    private double timeInvertal = 1.0 / sampleRate;

    public ChirpGenerator() {

    }

    public ChirpGenerator(int _sampleRate, double _f0, double _t1, double _f1) {
        sampleRate = _sampleRate;
        duration = _t1;
        startFrequence = _f0;
        stopFrequence = _f1;
        timeInvertal = 1.0 / sampleRate;
        slope = (stopFrequence - startFrequence) / duration;
    }

    public ArrayList<Float> getChirp() {
        ArrayList<Float> chirp = new ArrayList<>();
        int sampleLength = (int) Math.ceil(sampleRate * duration);
        double time = 0.0;
        for (int i = 0; i <= sampleLength; i++) {
            double sampleValue = Math.cos(2* Math.PI * startFrequence * time + Math.PI * slope * time * time);
            chirp.add((float) sampleValue);
            time += timeInvertal;
        }
        return chirp;
    }

}
