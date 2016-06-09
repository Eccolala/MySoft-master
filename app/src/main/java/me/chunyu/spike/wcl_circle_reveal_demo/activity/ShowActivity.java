package me.chunyu.spike.wcl_circle_reveal_demo.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.adapter.BookAdapter;
import me.chunyu.spike.wcl_circle_reveal_demo.bean.BookInfo;
import me.chunyu.spike.wcl_circle_reveal_demo.db.BookDB;
import me.chunyu.spike.wcl_circle_reveal_demo.utils.GuiUtils;

/**
 * 跳转的Activity
 * <p>
 * Created by wangchenlong on 16/2/26.
 */
public class ShowActivity extends AppCompatActivity {

    @Bind(R.id.other_fab_circle) FloatingActionButton mFabCircle;
    @Bind(R.id.other_rl_container) RelativeLayout mRlContainer;
    private RecyclerView recyclerView;
    private BookDB mBookDB;
    private Context mContext;
    private List<BookInfo> books;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimation(); // 入场动画
            setupExitAnimation(); // 退场动画
        } else {
            initViews();
        }


        mContext = this;

        mBookDB = BookDB.getInstance(this);
        mBookDB.loadDataBaseTest();

        initData();

        initView();


    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.show_list_id);

        BookAdapter bookAdapter = new BookAdapter(mContext,books);
        recyclerView.setAdapter(bookAdapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initData() {
//        data = new String[]{"创建书库", "创建书库", "创建书库", "创建书库",
//                "创建书库", "创建书库"};

//        for (int i = 0;i < 10;i++){
//            BookInfo info = new BookInfo();
//            info.setName("编译原理");
//            info.setAuthor("高尚");
//            info.setPrice("72");
//            books.add(info);
//        }
        books = new ArrayList<>();
        books = mBookDB.loadBook();
    }

    // 入场动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.arc_motion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override public void onTransitionStart(Transition transition) {

            }

            @Override public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow();
            }

            @Override public void onTransitionCancel(Transition transition) {

            }

            @Override public void onTransitionPause(Transition transition) {

            }

            @Override public void onTransitionResume(Transition transition) {

            }
        });
    }

    // 动画展示
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void animateRevealShow() {
        GuiUtils.animateRevealShow(
                this, mRlContainer,
                mFabCircle.getWidth() / 2, R.color.colorAccent,
                new GuiUtils.OnRevealAnimationListener() {
                    @Override public void onRevealHide() {

                    }

                    @Override public void onRevealShow() {
                        initViews();
                    }
                });
    }

    // 退出动画
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(300);
    }

    // 初始化视图
    private void initViews() {
        new Handler(Looper.getMainLooper()).post(() -> {
            Animation animation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
            animation.setDuration(300);
            recyclerView.startAnimation(animation);
            recyclerView.setVisibility(View.VISIBLE);
        });
    }



    // 退出事件
    @Override public void onBackPressed() {
        GuiUtils.animateRevealHide(
                this, mRlContainer,
                mFabCircle.getWidth() / 2, R.color.colorAccent,
                new GuiUtils.OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        defaultBackPressed();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }

    // 默认回退
    private void defaultBackPressed() {
        super.onBackPressed();
    }
}
