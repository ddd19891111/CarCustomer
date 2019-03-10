package com.bear.qqq.model;

import java.io.Serializable;

public class Message implements Serializable {

    private String type;
    private String username;
    private String password;
    private String success="fail";
    private String signitrue;
    private String nickname;
    private String content;
    private String toUsername;
    private String id;
    private String toId;


    public Message() {
    }

    public Message(String type, String username, String password) {
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public Message(String type, String username, String password, String nickname, String signature) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.nickname=nickname;
        this.signitrue = signature;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSignitrue() {
        return signitrue;
    }

    public void setSignitrue(String signitrue) {
        this.signitrue = signitrue;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
