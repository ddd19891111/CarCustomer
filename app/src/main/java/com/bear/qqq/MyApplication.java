package com.bear.qqq;

import android.app.Application;
import android.os.Handler;
import android.os.Message;
import com.bear.qqq.model.Item;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;


public class MyApplication extends Application {



    public static String remoterip = "192.168.0.2";
    public static  int remoteport = 4567;
    public static final int localport = 18889;
    public static Socket socket = null;
    public Socket getSocket(){
        return socket;
    }
    public void setSocket(Socket socket){
        this.socket = socket;
    }
    public static Handler myHandler = null;
    public static ReceiveThread receiveThread = null;
    public static boolean isReceive = false;
    public static boolean isConnect = false;
    public OutputStream outStream;
    private String strMessage;

    public Item itemCurrent=new Item();

    public MyApplication() {



    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public  boolean connect(String ip){
        remoterip=ip;
        if (!isConnect) {
            new Thread(connectThread).start();
            //showMessage("服务器连接……");
        }
        return true;
    }



    public boolean send(Item item){
        //把对象转为JSON格式的字符串
        Gson gs = new Gson();
        strMessage = gs.toJson(item);
        System.out.println("把对象转为JSON格式的字符串///  " + strMessage);
        new Thread(sendThread).start();
        return true;
    }
    //连接到服务器的接口
    Runnable connectThread = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {

                //初始化Scoket，连接到服务器
                socket = new Socket(remoterip, remoteport);
                isConnect = true;
                //启动接收线程
                isReceive = true;
                receiveThread = new ReceiveThread(socket);
                receiveThread.start();
                System.out.println("----connected success----");
                //showMessage("服务器连接成功");
                Message msg = new Message();
                msg.obj = "{\"success\":\"success\",\"type\":\"connect\"}";
                myHandler.sendMessage(msg);


            } catch (UnknownHostException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("UnknownHostException-->" + e.toString());
                //showMessage("服务器连接失败");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("IOException" + e.toString());
                //showMessage("服务器连接失败");
            }
        }
    };

    //接收线程
    private class ReceiveThread extends Thread {
        private InputStream inStream = null;

        private byte[] buffer;
        private String str = null;

        ReceiveThread(Socket socket) {
            try {
                inStream = socket.getInputStream();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (isReceive) {
                buffer = new byte[512];
                try {
                    inStream.read(buffer);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    str = new String(buffer, "UTF-8").trim();
                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.what=00;
                msg.obj = str;
                myHandler.sendMessage(msg);
            }
        }
    }


    //发送消息的接口
    Runnable sendThread = new Runnable() {

        @Override
        public void run() {
            // TODO Auto-generated method stub
            byte[] sendBuffer = null;
            try {
                sendBuffer = strMessage.getBytes("UTF-8");
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            try {
                outStream = socket.getOutputStream();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                //showMessage("服务器连接中，请稍后");
            }
            try {
                outStream.write(sendBuffer);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    };


    public static MyApplication instance;

    public static MyApplication getInstance() {
        return instance;
    }

    public static void setInstance(MyApplication instance) {
        MyApplication.instance = instance;
    }
}
