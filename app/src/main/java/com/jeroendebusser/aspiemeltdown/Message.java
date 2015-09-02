package com.jeroendebusser.aspiemeltdown;

/**
 * Created by Jeroen De Busser on 18/07/2015.
 */
public class Message {
    private String message;
    private boolean user;

    public String getMessage() {
        return message;
    }

    public boolean getUser() {
        return user;
    }

    public Message(String message, boolean user) {
        this.message = message;
        this.user = user;
    }
}
