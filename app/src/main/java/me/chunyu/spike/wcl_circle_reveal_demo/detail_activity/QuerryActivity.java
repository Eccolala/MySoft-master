package me.chunyu.spike.wcl_circle_reveal_demo.detail_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.db.BookDB;

public class QuerryActivity extends AppCompatActivity {
    private TextView querry_tv;
    private BookDB mBookDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querry);

        initData();

        initLayout();

        mBookDB = BookDB.getInstance(this);
        mBookDB.loadDataBaseTest();
    }

    private void initLayout() {
    }

    private void initData() {


    }
}
