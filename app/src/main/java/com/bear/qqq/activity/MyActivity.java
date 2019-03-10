package com.bear.qqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bear.qqq.MyApplication;
import com.bear.qqq.R;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyActivity extends AppCompatActivity {


    @BindView(R.id.tabbar_home)
    RelativeLayout tabbarHome;
    @BindView(R.id.tabbar_dig)
    RelativeLayout tabbarDig;
    @BindView(R.id.tabbar_my)
    RelativeLayout tabbarMy;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.tvChangePassword)
    TextView tvChangePassword;
    @BindView(R.id.logout)
    Button logout;
    MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        app=MyApplication.getInstance();
        tvNickname.setText(app.itemCurrent.getUsername());

    }


    @OnClick(R.id.tabbar_home)
    public void onTabbarHomeClicked() {
        Intent intent = new Intent(MyActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_dig)
    public void onTabbarDigClicked() {
        Intent intent = new Intent(MyActivity.this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_my)
    public void onTabbarMyClicked() {
        Intent intent = new Intent(MyActivity.this, MyActivity.class);
        startActivity(intent);
        finish();
    }


    @OnClick(R.id.tvChangePassword)
    public void onTvChangePasswordClicked() {
        Intent intent = new Intent(MyActivity.this, PasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.logout)
    public void onLogoutClicked() {
        Intent intent = new Intent(MyActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
