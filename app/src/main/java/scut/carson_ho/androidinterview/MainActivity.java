package scut.carson_ho.androidinterview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "carson：";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 通过匿名内部类 实现多线程
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    Log.d(TAG, "执行了多线程");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }
}