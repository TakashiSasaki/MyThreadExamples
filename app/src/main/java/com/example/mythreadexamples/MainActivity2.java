package com.example.mythreadexamples;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }//onCreate

    Thread thread1;
    Thread thread2;

    public void startThread1(View v) {
        int threadNumber = 1;
        Handler handler = new Handler();
        EditText counterEditText = findViewById(R.id.count1EditText);
        EditText outputEditText = findViewById(R.id.output1EditText);
        thread1 = new Thread(new Runnable() {
            int counter = 0;
            String output = "";
            Boolean enabled = true;
            Random random = new Random();

            @Override
            public void run() {
                while (counter < 10 && enabled == true) {
                    counter += 1;
                    output += (char) (random.nextInt(26) + 65);

                    Log.v("PlainThread" + threadNumber, "counter: " + counter);
                    Log.v("PlainThread" + threadNumber, "output: " + output);


                    handler.post(() -> {
                        counterEditText.setText("" + counter);
                        outputEditText.setText(output);
                    });

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        Log.v("PlainThread" + threadNumber, "finished sleeping");
                    }//try
                }//while
            }//run
        });//thread1
        thread1.start();
    }

    public void startThread2(View v) {

    }

    public void interruptThread1(View v) {

    }

    public void interruptThread2(View v) {

    }
}//MainActivity