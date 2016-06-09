package me.chunyu.spike.wcl_circle_reveal_demo.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.bmob.v3.listener.SaveListener;
import me.chunyu.spike.wcl_circle_reveal_demo.R;
import me.chunyu.spike.wcl_circle_reveal_demo.bean.User;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * 登陆功能
 */
public class SignActivity extends AppCompatActivity {
    private MaterialDialog mMaterialDialog;
    private TextView userName;
    private TextView passWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userName = (TextView)findViewById(R.id.id_number_txt);
        passWord = (TextView)findViewById(R.id.id_password_txt);
        mMaterialDialog = new MaterialDialog(this);
    }

    public void clickSign(View view) {
        User user = new User();
        user.setUsername(userName.getText().toString());
        user.setPassword(passWord.getText().toString());



        user.signUp(SignActivity.this, new SaveListener() {
            @Override
            public void onSuccess() {
                mMaterialDialog.setTitle("提示")
                        .setMessage("注册成功")
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

            @Override
            public void onFailure(int i, String s) {
                mMaterialDialog.setTitle("提示")
                        .setMessage("注册失败")
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
