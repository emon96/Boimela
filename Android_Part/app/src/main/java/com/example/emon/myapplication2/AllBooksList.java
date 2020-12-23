package com.example.emon.myapplication2;

public class AllBooksList {
    String title,author,price,page,type;
    public AllBooksList()
    {

    }
    public AllBooksList(String title, String type, String author, String page, String price) {
        this.title = title;
        this.type=type;
        this.author=author;
        this.page=page;
        this.price=price;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }






}
