package com.fbsum.android.llog.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fbsum.android.llog.LLog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().getDecorView().post(new Runnable() {
            @Override
            public void run() {
                test();
            }
        });
    }

    private void test() {
        LLog.init(true);

        LLog.e("test error msg");
        LLog.e("TEST TAG", "test error msg");
        LLog.d("test debug msg");
        LLog.d("TEST TAG", "test debug msg");
        LLog.json("{\"key\" : \"value\"}");
        LLog.json("TEST TAG", "{\"key\" : \"value\"}");
    }
}
