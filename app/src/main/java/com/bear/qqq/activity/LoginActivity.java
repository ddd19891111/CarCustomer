package com.bear.qqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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


public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.etServerIp)
    EditText etServerIp;
    @BindView(R.id.btnConnect)
    Button btnConnect;
    private EditText etAccount;
    private EditText etPassword;
    MyApplication app;

    private Button btnNewUser;//报错添加



    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        app = MyApplication.getInstance();
        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Item item=new Gson().fromJson(msg.obj.toString(), Item.class);
                if (item.getSuccess().equals("success")&&item.getType().equals("login")) {
                    app.itemCurrent.setId(item.getId());
                    app.itemCurrent.setUsername(item.getUsername());
                    app.itemCurrent.setNickname(item.getNickname());
                    app.itemCurrent.setSignitrue(item.getSignitrue());
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);//跳转至主界面
                    startActivity(intent);
                    finish();
                } else if (item.getSuccess().equals("success")&&item.getType().equals("connect")) {
                    Toast.makeText(LoginActivity.this, "服务器连接成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        };



        etAccount = findViewById(R.id.etUsername);//获取用户名输入文本编辑框组件
        etPassword = findViewById(R.id.etPassword);//获取密码输入文本编辑框组件
        btnNewUser = findViewById(R.id.btnNewUser);//报错添加
        //获取登录按钮组件
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//设置点击按钮触发事件函数
            //------------------------------------------------------------------------界面测试
//                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                login(etAccount.getText().toString(), etPassword.getText().toString());
            }
        });
        btnNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });
//        app = MyApplication.getInstance();
//        transmission = app.getConnect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Item item=new Gson().fromJson(msg.obj.toString(), Item.class);
                if (item.getSuccess().equals("success")&&item.getType().equals("login")) {
                    app.itemCurrent.setId(item.getId());
                    app.itemCurrent.setUsername(item.getUsername());
                    app.itemCurrent.setNickname(item.getNickname());
                    app.itemCurrent.setSignitrue(item.getSignitrue());
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);//跳转至主界面
                    startActivity(intent);
                    finish();
                } else if (item.getSuccess().equals("success")&&item.getType().equals("connect")) {
                    Toast.makeText(LoginActivity.this, "服务器连接成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void login(String username, String password) {
        Item item = new Item("login", username, password);
        app.send(item);
    }




    @OnClick(R.id.btnConnect)
    public void onViewClicked() {
        app.connect(etServerIp.getText().toString());
    }
}
