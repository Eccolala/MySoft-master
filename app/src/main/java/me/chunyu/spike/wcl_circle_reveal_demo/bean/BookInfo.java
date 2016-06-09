package me.chunyu.spike.wcl_circle_reveal_demo.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by woops on 16-6-6.
 */
public class BookInfo extends BmobObject{
    private String name;
    private String author;
    private String price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
