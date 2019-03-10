package com.bear.qqq.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.bear.qqq.MyApplication;
import com.bear.qqq.R;
import com.bear.qqq.adapter.MessageListAdapter;
import com.bear.qqq.model.Item;
import com.google.gson.Gson;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    Gson gson = new Gson();
    @BindView(R.id.tvChatTitle)
    TextView tvChatTitle;
    //    @BindView(R.id.lvChat)
//    ListView lvChat;
    @BindView(R.id.etInput)
    EditText etInput;
    @BindView(R.id.btnSendMsg)
    Button btnSendMsg;
    @BindView(R.id.lvChat)
    ListView lvChat;

    ;
    private MyApplication app;


    Item friend;
    private List messageList;
    private MessageListAdapter messageListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        Intent intent = getIntent();

        app = MyApplication.getInstance();
        friend = (Item) getIntent().getSerializableExtra("friend");
        tvChatTitle.setText(friend.getUsername());
        init();
    }

    //重新定义app中的handle
    private void init() {
        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Item item = gson.fromJson(msg.obj.toString(), Item.class);
                messageList.add(item);
                messageListAdapter = new MessageListAdapter(ChatActivity.this, messageList);
                lvChat.setAdapter(messageListAdapter);
            }
        };
        messageList = new ArrayList<Item>();
        messageListAdapter = new MessageListAdapter(ChatActivity.this, messageList);
        lvChat.setAdapter(messageListAdapter);
    }

    //发送按键
    @OnClick(R.id.btnSendMsg)
    public void onViewClicked() {


        Item item = new Item();
        item.setId(app.itemCurrent.getId());
        item.setUsername(app.itemCurrent.getUsername());
        item.setToId(friend.getId());
        item.setType("send");
        item.setContent(etInput.getText().toString());
        messageList.add(item);
        messageListAdapter = new MessageListAdapter(ChatActivity.this, messageList);
        lvChat.setAdapter(messageListAdapter);
        app.send(item);
    }
}
