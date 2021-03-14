package cn.hocztms.service;

import cn.hocztms.domin.Picture;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

public interface PictureService {
    public  void insert(Picture picture);

    public List<String> getp1(String username);

    public List<String> getp2(String username,int tag);

    public List<String> getp3(String id);

    public void updatetag(String username,int tag);

    public void delete(String picturename);

    public void delete_id(String id);
}
