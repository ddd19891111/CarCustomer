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


public class PasswordActivity extends AppCompatActivity {


    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.etVerifyPassword)
    EditText etVerifyPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    private Gson gson = new Gson();
    MyApplication app;

    //服务器连接


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        app=MyApplication.getInstance();
        app.myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Item item = new Gson().fromJson(msg.obj.toString(), Item.class);
                if (item.getSuccess().equals("success") && item.getType().equals("password")) {
                    showMessage("修改成功");

                } else {
                    showMessage("修改失败");
                }
            }
        };





    }

    private void password(String username, String password) {
        Gson gs = new Gson();

        Item item = new Item("password", username, password);
        app.send(item);
    }



    @OnClick(R.id.btnRegister)
    public void onViewClicked() {
        String password = etPassword.getText().toString().trim();
        String password2 = etVerifyPassword.getText().toString().trim();
        if (password.compareTo(password2) == 0) {

            password(app.itemCurrent.getUsername(), password);
        } else {
            Toast.makeText(PasswordActivity.this, "两次输入的密码不一致，请重新输入！", Toast.LENGTH_SHORT).show();
            etPassword.setText("");
            etVerifyPassword.setText("");
            etPassword.requestFocus();
        }

    }





    private void showMessage(final String msg) {
                Toast.makeText(PasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


}
