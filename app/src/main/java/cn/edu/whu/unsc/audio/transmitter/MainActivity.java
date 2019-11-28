package cn.edu.whu.unsc.audio.transmitter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

 public class MainActivity extends AppCompatActivity {

     static private String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button chirpTransmitterButton = findViewById(R.id.button_test);
        chirpTransmitterButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Read Test Audio Sequence Data
                TransmittingLoop transmittingLoop =  new TransmittingLoop();
                transmittingLoop.start();

                Log.d(TAG, "Chirp Transmitter Complete! ");
            }
        });
    }



}
