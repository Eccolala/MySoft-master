package me.chunyu.spike.wcl_circle_reveal_demo.detail_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.bean.BookInfo;
import me.chunyu.spike.wcl_circle_reveal_demo.db.BookDB;

public class DeleteActivity extends AppCompatActivity {
    private BookDB mBookDB;
    private List<BmobObject> queryBeans = new ArrayList<BmobObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        mBookDB = BookDB.getInstance(this);
        mBookDB.deleteDataBaseTest();

        BmobQuery<BookInfo> query = new BmobQuery<BookInfo>();



    }
}
