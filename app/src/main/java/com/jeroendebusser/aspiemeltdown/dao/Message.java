package com.jeroendebusser.aspiemeltdown.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by Jeroen De Busser on 18/07/2015.
 */
@Entity
public class Message {
    @Id
    private Long id;

    private String message;
    private boolean user;

    public String getMessage() {
        return message;
    }

    public boolean getUser() {
        return user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(boolean user) {
        this.user = user;
    }

    public Message(String message, boolean user) {
        this.message = message;
        this.user = user;
    }

    @Generated(hash = 472937277)
    public Message(Long id, String message, boolean user) {
        this.id = id;
        this.message = message;
        this.user = user;
    }

    @Generated(hash = 637306882)
    public Message() {
    }
}
