package com.example.mythreadexamples;

import android.util.Log;

import java.util.Random;


/*
   最も基本的なスレッドの使い方。
   Threadクラスの子クラスを実装する。
   run()メソッドにスレッド内で実行させたい処理を書く。
   run()は戻り値を戻すことができない。
   run()はいつ終わるかもわからない。
   終了方法１）自発的に終了するように書く。
   終了方法２）Thread#interrupt()を呼び出す。
   禁止）Thread#stopを呼び出す。（古いやり方。今や安全に終了することは保証されない。）
   スレッドが何らかの理由で強制終了されるとrun()内のコードのどこかでInterruptedExceptionがスローされる。
   run()はInterruptedException の再スローを許可しない。
   したがってrun()内で捕捉出来なかったInterruptedExceptionはメインスレッドに伝搬することに注意。
 */

public class PlainThread extends Thread {

    int counter = 0;
    String output = "";
    Random random = new Random();
    int threadNumber;

    PlainThread(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        //super.run();

        while (counter < 100) {
            counter += 1;
            output += (char) random.nextInt(26) + 65;

            Log.v("PlainThread" + threadNumber, "counter: " + counter);
            Log.v("PlainThread" + threadNumber, "output: " + output);
        }
    }
}
