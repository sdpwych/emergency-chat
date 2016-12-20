package com.jeroendebusser.aspiemeltdown.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Jeroen De Busser on 19/12/2016.
 */

@Entity
public class SplashScreen {
    @Id
    private Long id;

    private String title;
    private String text;
    @Generated(hash = 2044147371)
    public SplashScreen(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }
    @Generated(hash = 1974975613)
    public SplashScreen() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
