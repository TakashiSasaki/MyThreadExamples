package com.example.mythreadexamples;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    PlainThread plainThread1;
    PlainThread plainThread2;

    public void startPlainThread1(View v) {
        plainThread1 = new PlainThread(1,
                findViewById(R.id.count1EditText),
                findViewById(R.id.output1EditText));
        plainThread1.start();
    }

    public void startPlainThread2(View v) {
        plainThread2 = new PlainThread(2,
                findViewById(R.id.count2EditText),
                findViewById(R.id.output2EditText));
        plainThread2.start();
    }

    public void interruptPlainThread1(View v) {
        if (plainThread1 != null) {
            Log.v("MainAcitivity", "interruptPlainThread1");
            //plainThread1.interrupt();
            plainThread1.enabled = false;
        }
        plainThread1 = null;
    }

    public void interruptPlainThread2(View v) {
        if (plainThread2 != null) {
            Log.v("MainActivity", "interruptPlainThread2");
            //plainThread2.interrupt();
            plainThread2.enabled = false;
        }
        plainThread2 = null;
    }
}//MainActivity