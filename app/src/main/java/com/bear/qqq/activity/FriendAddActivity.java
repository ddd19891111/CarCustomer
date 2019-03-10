package com.bear.qqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bear.qqq.MyApplication;
import com.bear.qqq.R;
import com.bear.qqq.model.Item;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FriendAddActivity extends AppCompatActivity {


    @BindView(R.id.tabbar_home)
    RelativeLayout tabbarHome;
    @BindView(R.id.tabbar_dig)
    RelativeLayout tabbarDig;
    @BindView(R.id.tabbar_my)
    RelativeLayout tabbarMy;
    @BindView(R.id.tvNick)
    TextView tvNick;
    @BindView(R.id.tvSignature)
    TextView tvSignature;
    @BindView(R.id.btnAddFriend)
    Button btnAddFriend;
    @BindView(R.id.lv_add_friend)
    LinearLayout lvAddFriend;
    MyApplication app;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.bt_search)
    Button btSearch;
    private String friendId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_add);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        app = MyApplication.getInstance();
        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            try {
                Log.i("---------------------", msg.obj.toString());
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                JsonArray jsonArray = parser.parse(msg.obj.toString()).getAsJsonArray();
                final ArrayList<Item> friendList = new ArrayList<>();
                //加强for循环遍历JsonArray
                for (JsonElement user : jsonArray) {
                    Item item = new Gson().fromJson(user, Item.class);
                    friendList.add(item);
                    //Log.i("---------------------", item.getId() + "");
                }
                tvNick.setText(friendList.get(0).getNickname());
                tvSignature.setText(friendList.get(0).getSignitrue());
                friendId = friendList.get(0).getId();
                //Log.i("---------------------",friendId);
                lvAddFriend.setVisibility(View.VISIBLE);
            }catch (Exception e){
                showMessage("错误");
            }


            }
        };

    }


    private void showMessage(final String msg) {

                Toast.makeText(FriendAddActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.tabbar_home)
    public void onTabbarHomeClicked() {
        Intent intent = new Intent(FriendAddActivity.this, ContactActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_dig)
    public void onTabbarDigClicked() {

    }

    @OnClick(R.id.tabbar_my)
    public void onTabbarMyClicked() {
        Intent intent = new Intent(FriendAddActivity.this, MyActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btnAddFriend)
    public void onViewClicked() {
        Item item =new Item();
        item.setToUsername(etSearch.getText().toString().trim());
        item.setId(app.itemCurrent.getId());
        item.setUsername(app.itemCurrent.getUsername());
        item.setToId(friendId);
        Log.i("---------------------",friendId);
        item.setType("add");
        app.send(item);
        showMessage("增加");
    }

    @OnClick(R.id.bt_search)
    public void onViewClicked2() {
        Item item =new Item();
        item.setToUsername(etSearch.getText().toString().trim());
        item.setId(app.itemCurrent.getId());
        item.setUsername(app.itemCurrent.getUsername());
        item.setType("search");
        app.send(item);
    }
}
