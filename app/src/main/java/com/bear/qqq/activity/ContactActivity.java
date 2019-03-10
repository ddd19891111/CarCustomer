package com.bear.qqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bear.qqq.MyApplication;
import com.bear.qqq.R;
import com.bear.qqq.adapter.ItemListAdapter;
import com.bear.qqq.model.Item;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactActivity extends AppCompatActivity {


    @BindView(R.id.tabbar_home)
    RelativeLayout tabbarHome;
    @BindView(R.id.tabbar_dig)
    RelativeLayout tabbarDig;
    @BindView(R.id.tabbar_my)
    RelativeLayout tabbarMy;
    @BindView(R.id.lv_contract)
    ListView lvContract;
    private MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        app=MyApplication.getInstance();

        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                Log.i("---------------------",msg.obj.toString());
                JsonArray jsonArray = parser.parse(msg.obj.toString()).getAsJsonArray();
                final ArrayList<Item> friendList = new ArrayList<>();
                //加强for循环遍历JsonArray
                for (JsonElement user : jsonArray) {
                    Item item = new Gson().fromJson(user, Item.class);
                    friendList.add(item);
                    Log.i("---------------------",item.getId()+"");
                }
                ItemListAdapter friendListAdapter=new ItemListAdapter(ContactActivity.this,friendList);
                lvContract.setAdapter(friendListAdapter);
                lvContract.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(ContactActivity.this, ChatActivity.class);
                        intent.putExtra("friend",friendList.get(position));
                        startActivity(intent);
                    }
                });


            }
        };

    }




    private void showMessage(final String msg) {

                Toast.makeText(ContactActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.tabbar_home)
    public void onTabbarHomeClicked() {
    }

    @OnClick(R.id.tabbar_dig)
    public void onTabbarDigClicked() {
        Intent intent = new Intent(ContactActivity.this, FriendAddActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_my)
    public void onTabbarMyClicked() {
        Intent intent = new Intent(ContactActivity.this, MyActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Item item =new Item();
        item.setType("getFriendList");
        item.setId(app.itemCurrent.getId());
        app.send(item);
    }
}
