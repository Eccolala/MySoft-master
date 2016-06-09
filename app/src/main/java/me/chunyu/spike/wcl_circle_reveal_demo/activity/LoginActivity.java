package me.chunyu.spike.wcl_circle_reveal_demo.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;
import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.bean.User;
import me.drakeet.materialdialog.MaterialDialog;

public class LoginActivity extends AppCompatActivity {

    private TextView user;
    private TextView password;
    MaterialDialog mMaterialDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //第一：默认初始化
        Bmob.initialize(this, "cf7c58e294efe430b0425ccf35c2823e");

        user = (TextView)findViewById(R.id.id_number_txt);
        password = (TextView)findViewById(R.id.id_password_txt);
        mMaterialDialog = new MaterialDialog(this);


    }

    public void signIn(View view) {
        startActivity(new Intent(this, SignActivity.class));
    }

    public void clickLogin(View view) {
        String userName = user.getText().toString();
        String passWord = password.getText().toString();

        User user = new User();
        user.setUsername(userName);
        user.setPassword(passWord);

        user.login(LoginActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }

            @Override
            public void onFailure(int i, String s) {
                mMaterialDialog.setTitle("提示")
                        .setMessage("请先注册")
                        .setPositiveButton("好的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消",
                                new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        mMaterialDialog.dismiss();
                                    }
                                })
                        .setCanceledOnTouchOutside(true)
                        // You can change the message anytime.
                        // mMaterialDialog.setTitle("提示");
                        .setOnDismissListener(
                                new DialogInterface.OnDismissListener() {
                                    @Override
                                    public void onDismiss(DialogInterface dialog) {

                                    }
                                })
                        .show();
            }
        });



    }


}
