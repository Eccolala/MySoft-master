package me.chunyu.spike.wcl_circle_reveal_demo.detail_activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;

import cn.bmob.v3.listener.SaveListener;
import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.bean.BookInfo;
import me.chunyu.spike.wcl_circle_reveal_demo.db.BookDB;

public class InsertActivity extends AppCompatActivity {
    private BookDB mBookDB;
    private TextView name_tv;
    private TextView author_tv;
    private TextView price_tv;
    private String name;
    private String author;
    private String price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        mBookDB = BookDB.getInstance(this);

        initLayout();


    }

    private void initData() {
        name = name_tv.getText().toString();
        author = author_tv.getText().toString();
        price = price_tv.getText().toString();
    }

    private void initLayout() {
        name_tv = (TextView) findViewById(R.id.id_name_txt);
        author_tv = (TextView) findViewById(R.id.id_author_txt);
        price_tv = (TextView) findViewById(R.id.id_price_txt);


    }

    public void clickInsert(View view) {
        initData();

        BookInfo bookInfo = new BookInfo();
        bookInfo.setName(name);
        bookInfo.setPrice(price);
        bookInfo.setAuthor(author);

        mBookDB.savaBook(bookInfo);

        bookInfo.save(InsertActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                SuperActivityToast.create(InsertActivity.this, "添加图书成功",
                        SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
            }

            @Override
            public void onFailure(int i, String s) {
                SuperActivityToast.create(InsertActivity.this, "失败",
                        SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
            }
        });

    }
}
