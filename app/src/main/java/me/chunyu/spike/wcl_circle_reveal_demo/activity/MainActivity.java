package me.chunyu.spike.wcl_circle_reveal_demo.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.SuperActivityToast;
import com.github.johnpersano.supertoasts.SuperToast;
import com.github.johnpersano.supertoasts.util.Style;
import com.xys.libzxing.zxing.activity.CaptureActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.listener.DeleteListener;
import cn.bmob.v3.listener.SaveListener;
import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.bean.BookInfo;
import me.chunyu.spike.wcl_circle_reveal_demo.db.BookDB;
import me.chunyu.spike.wcl_circle_reveal_demo.detail_activity.InsertActivity;
import me.chunyu.spike.wcl_circle_reveal_demo.detail_activity.QuerryActivity;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fab)
    FloatingActionButton mFab;
    private ListView listView;
    private String[] data;
    private TextView result_tv;
    private BookDB mBook;
    private Context mContext;
    private List<BmobObject> queryBeans = new ArrayList<BmobObject>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mContext = this;

        initData();

        initView();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i) {
                    case 0:
                        //初始化数据库
                        mBook = BookDB.getInstance(mContext);
                        SuperActivityToast.create(MainActivity.this, "添加书库成功",
                                SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
                        break;
                    case 1:
                        startActivity(new Intent(mContext, InsertActivity.class));
                        break;
                    case 2:

                        break;
                    case 3:
                        //按条件查找图书信息
                        startActivity(new Intent(mContext, QuerryActivity.class));
                        break;
                    case 4:
//                        startActivity(new Intent(mContext, DeleteActivity.class));

                        mBook = BookDB.getInstance(mContext);
                        mBook.deleteDataBaseTest();
                        SuperActivityToast.create(MainActivity.this, "已删除书库",
                                SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
//                        delete();

                        break;


                    default:
                        startActivityForResult(new Intent(MainActivity.this, CaptureActivity.class), 0);
                        break;
                }

            }
        });


    }

    private void delete() {
        List<BookInfo> books;
        mBook = BookDB.getInstance(mContext);
        books = mBook.loadBook();

//        List<BmobObject> queryBeans = convertUserToObject(books);


        BookInfo userBean = books.get(0);
        userBean.delete(MainActivity.this, new DeleteListener() {
            @Override
            public void onSuccess() {
                SuperActivityToast.create(MainActivity.this, "删除成功",
                        SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
            }

            @Override
            public void onFailure(int i, String s) {
                SuperActivityToast.create(MainActivity.this, "删除失败",
                        SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
            }
        });
    }

    private void createDataBase() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String result = bundle.getString("result");
            mBook = BookDB.getInstance(mContext);
            assert result != null;
            if (result.equals("9787118022070")) {

                BookInfo info = new BookInfo();
                info.setName("编译原理");
                info.setAuthor("陈火旺");
                info.setPrice("31");

                mBook.savaBook(info);
                info.save(MainActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        SuperActivityToast.create(MainActivity.this, "添加图书成功",
                                SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        SuperActivityToast.create(MainActivity.this, "失败",
                                SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
                    }
                });
            } else if (result.equals("9787302302711")) {
                BookInfo info = new BookInfo();
                info.setName("微机接口");
                info.setAuthor("刘彦文");
                info.setPrice("34");

                mBook.savaBook(info);
                info.save(MainActivity.this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        SuperActivityToast.create(MainActivity.this, "添加图书成功",
                                SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        SuperActivityToast.create(MainActivity.this, "失败",
                                SuperToast.Duration.SHORT, Style.getStyle(Style.ORANGE, SuperToast.Animations.FLYIN)).show();
                    }
                });
            }

        }
    }

    private void initView() {
        result_tv = (TextView) findViewById(R.id.result_id);
        listView = (ListView) findViewById(R.id.function_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MainActivity.this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }

    private void initData() {
        data = new String[]{"创建书库", "添加图书", "更新图书", "查询图书",
                "删除图书", "条形码扫描"};
        mBook = BookDB.getInstance(mContext);
        List<BookInfo> books = new ArrayList<>();
        books = mBook.loadBook();

        convertUserToObject(books);
    }


    // Fab的跳转事件
    public void startOtherActivity(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options =
                    ActivityOptions.makeSceneTransitionAnimation(this, mFab, mFab.getTransitionName());
            startActivity(new Intent(this, ShowActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(this, ShowActivity.class));
        }
    }
    /**
     * 将子类集合转换为基类BmobObject集合
     * @param userBeanList
     * @return
     */
    private List<BmobObject> convertUserToObject(List<BookInfo> userBeanList){
        queryBeans.clear();
        for(BookInfo userBean: userBeanList){
            queryBeans.add(userBean);
        }

        return queryBeans;
    }


}
