package com.fbsum.android.llog.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fbsum.android.llog.LLog;

import java.util.ArrayList;
import java.util.List;

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

        Item item = new Item(100, "Test Item");
        LLog.e(item.toString());

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new Item(i, "Test Item " + i));
        }
        LLog.e(items.toString());

    }

    private static class Item {
        public long id;
        public String text;

        public Item(long id, String text) {
            this.id = id;
            this.text = text;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id=" + id +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
}
