package com.bear.qqq.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bear.qqq.MyApplication;
import com.bear.qqq.R;

import com.bear.qqq.model.Item;
import com.google.gson.Gson;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etVerifyPassword)
    EditText etVerifyPassword;
    @BindView(R.id.etSignature)
    EditText etSignature;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    MyApplication app;
    @BindView(R.id.etNickname)
    EditText etNickname;
    //    Transmission transmission;
    private Gson gson = new Gson();
    private String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        app = MyApplication.getInstance();
        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Item item = new Gson().fromJson(msg.obj.toString(), Item.class);
                if (item.getSuccess().equals("success") && item.getType().equals("regist")) {
                    showMessage("注册成功");

                } else {
                    showMessage("注册失败");
                }
            }
        };


    }

    private void regist(String username, String password, String nickname, String signature) {
        Gson gs = new Gson();

        Item item = new Item("regist", username, password, nickname,signature);
        app.send(item);
    }


    @OnClick(R.id.btnRegister)
    public void onViewClicked() {
        String username = etUsername.getText().toString().trim();//检测两次密码是否相同
        String password = etPassword.getText().toString().trim();
        String password2 = etVerifyPassword.getText().toString().trim();
        String signature = etSignature.getText().toString().trim();
        String nickname = etNickname.getText().toString().trim();

        if (password.compareTo(password2) == 0) {
            regist(username, password, nickname,signature);
        } else {
            Toast.makeText(RegisterActivity.this, "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            etVerifyPassword.setText("");
            etPassword.requestFocus();
        }

    }


    private void showMessage(final String msg) {

                Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();

    }


}
