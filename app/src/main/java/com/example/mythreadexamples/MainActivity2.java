package com.example.mythreadexamples;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.UiThread;
import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }//onCreate

    Thread thread1;
    Thread thread2;

    public void startThread1(View v) {
        //スレッドオブジェクトの生成
        thread1 = new Thread(new MyRunnable(
                1,
                findViewById(R.id.count1EditText),
                findViewById(R.id.output1EditText)));
        //スレッドの開始
        thread1.start();
    }//startThread1

    public void submitThread1(View v) {
        //スレッドオブジェクトの生成とスレッドの開始をまとめてやってくれるのがExecutor。
        //教科書第11章のやり方がこれ。
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new MyRunnable(
                1,
                findViewById(R.id.count1EditText),
                findViewById(R.id.output1EditText)));
    }//submitThread1

    @UiThread
    private class MyRunnable implements Runnable {
        int counter = 0;
        String output = "";
        Boolean enabled = true;
        Random random = new Random();
        int threadNumber;
        Handler handler = new Handler();
        EditText counterEditText;
        EditText outputEditText;

        @UiThread
        MyRunnable(int threadNumber, EditText counterEditText, EditText outputEditText) {
            this.threadNumber = threadNumber;
            this.counterEditText = counterEditText;
            this.outputEditText = outputEditText;
        }//MyRunnable constructor

        @WorkerThread
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
    }//MyRunnable class

    @UiThread
    public void startThread2(View v) {
        thread2 = new Thread(new MyRunnable(
                2,
                findViewById(R.id.count2EditText),
                findViewById(R.id.output2EditText)
        ));
        thread2.start();
    }

    @UiThread
    public void interruptThread1(View v) {

    }

    @UiThread
    public void interruptThread2(View v) {

    }
}//MainActivity