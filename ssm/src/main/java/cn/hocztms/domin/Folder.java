package cn.hocztms.domin;

import java.io.Serializable;

public class Folder implements Serializable {
    private String id;//
    private String fid; //记录上一级 文件夹id  默认一级目录 fid = 0
    private String username;
    private String filename;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "id='" + id + '\'' +
                ", fid='" + fid + '\'' +
                ", username='" + username + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
