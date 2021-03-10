package cn.hocztms.domin;

import java.time.LocalDate;

public class Picture {
    String id;
    String username;
    LocalDate date;
    String picturename;
    int tag; // 设置一个tag来判定是否已审核

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getPicturename() {
        return picturename;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "uid=" + id +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", picturename='" + picturename + '\'' +
                ", tag=" + tag +
                '}';
    }

    public Picture(String id, String username, LocalDate date, String picturename, int tag) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.picturename = picturename;
        this.tag = tag;
    }
}
