package com.example.mythreadexamples;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }//onCreate

    PlainThread plainThread1 = new PlainThread(1);
    PlainThread plainThread2 = new PlainThread(2);

    public void startPlainThread1(View v) {
        plainThread1.start();
    }

    public void startPlainThread2(View v) {
        plainThread2.start();
    }
}//MainActivity