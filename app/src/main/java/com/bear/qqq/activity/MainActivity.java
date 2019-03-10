package com.bear.qqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bear.qqq.MyApplication;
import com.bear.qqq.R;
import com.bear.qqq.model.Item;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tabbar_home)
    RelativeLayout tabbarHome;
    @BindView(R.id.tabbar_dig)
    RelativeLayout tabbarDig;
    @BindView(R.id.tabbar_my)
    RelativeLayout tabbarMy;

    @BindView(R.id.bt_main)
    Button btMain;
    @BindView(R.id.et_main_type)
    EditText etMainType;
    @BindView(R.id.et_main_content)
    EditText etMainContent;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        app = MyApplication.getInstance();



    }

    @Override
    protected void onResume() {
        super.onResume();
        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Item item=new Gson().fromJson(msg.obj.toString(), Item.class);
                if (item.getSuccess().equals("success")&&item.getType().equals("newOrder")) {
                    Toast.makeText(MainActivity.this, "新建订单成功", Toast.LENGTH_SHORT).show();
                } else if (item.getSuccess().equals("success")&&item.getType().equals("connect")) {
                    Toast.makeText(MainActivity.this, "服务器连接成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "通讯失败", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @OnClick(R.id.tabbar_home)
    public void onTabbarHomeClicked() {
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_dig)
    public void onTabbarDigClicked() {
        Intent intent = new Intent(MainActivity.this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_my)
    public void onTabbarMyClicked() {
        Intent intent = new Intent(MainActivity.this, MyActivity.class);
        startActivity(intent);
        finish();
    }

    //新建订单
    @OnClick(R.id.bt_main)
    public void onViewClicked() {
        Item item = new Item();
        item.setType("newOrder");
        item.setErrorType(etMainType.getText().toString());
        item.setContent(etMainContent.getText().toString());
        app.send(item);
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        Item item =new Item();
//        item.setType("getFriendList");
//        item.setId(app.itemCurrent.getId());
//        app.send(item);
//    }
}
