package scut.carson_ho.androidinterview;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


public class MainActivity extends AppCompatActivity {


    Button button1,button2;
    ViewGroup myLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String device_model = Build.MODEL; // 设备型号
        String version_sdk = Build.VERSION.SDK; // 设备SDK版本
        String version_release = Build.VERSION.RELEASE; // 设备的系统版本

        System.out.println(device_model +version_sdk);
        System.out.println(version_sdk);
        System.out.println(version_release);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        myLayout = (LinearLayout)findViewById(R.id.my_layout);

        // 1.为ViewGroup布局设置监听事件
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "点击了ViewGroup");
            }
        });

        // 2. 为按钮1设置监听事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "点击了button1");
            }
        });

        // 3. 为按钮2设置监听事件
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "点击了button2");
            }
        });

    }
}