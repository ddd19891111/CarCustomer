package com.bear.qqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.bear.qqq.MyApplication;
import com.bear.qqq.R;
import com.bear.qqq.adapter.OfferAdapter;
import com.bear.qqq.model.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderActivity extends AppCompatActivity {


    @BindView(R.id.tabbar_home)
    RelativeLayout tabbarHome;
    @BindView(R.id.tabbar_dig)
    RelativeLayout tabbarDig;
    @BindView(R.id.tabbar_my)
    RelativeLayout tabbarMy;
    @BindView(R.id.lv_order_offer)
    ListView lvOrderOffer;

    private MyApplication app;
    private List<Item> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        app = MyApplication.getInstance();
        init();


    }

    private void init() {
        for (int i = 0; i < 3; i++) {
            Item item = new Item();
            item.setNickname(i + "维修厂");
            item.setOffer(i + "10");
            item.setContent(i + "多快好省");
            itemList.add(item);
        }
        OfferAdapter offerAdapter = new OfferAdapter(this, itemList);
        lvOrderOffer.setAdapter(offerAdapter);


    }


    @OnClick(R.id.tabbar_home)
    public void onTabbarHomeClicked() {
        Intent intent = new Intent(OrderActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_dig)
    public void onTabbarDigClicked() {
        Intent intent = new Intent(OrderActivity.this, OrderActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.tabbar_my)
    public void onTabbarMyClicked() {
        Intent intent = new Intent(OrderActivity.this, MyActivity.class);
        startActivity(intent);
        finish();
    }


}
