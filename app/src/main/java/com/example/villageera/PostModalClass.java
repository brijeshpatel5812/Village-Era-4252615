package com.example.villageera;

public class PostModalClass {
    int post_id;
    String name, phone, post_type, post_desc;

    public PostModalClass(int post_id, String name, String phone, String post_type, String post_desc) {
        this.post_id = post_id;
        this.name = name;
        this.phone = phone;
        this.post_type = post_type;
        this.post_desc = post_desc;
    }

    public PostModalClass(String name, String phone, String post_type, String post_desc) {
        this.name = name;
        this.phone = phone;
        this.post_type = post_type;
        this.post_desc = post_desc;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPost_type() {
        return post_type;
    }

    public void setPost_type(String post_type) {
        this.post_type = post_type;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }
}
