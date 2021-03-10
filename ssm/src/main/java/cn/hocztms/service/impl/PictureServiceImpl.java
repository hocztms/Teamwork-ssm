package cn.hocztms.service.impl;

import cn.hocztms.dao.PictureDao;
import cn.hocztms.domin.Picture;
import cn.hocztms.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("pictureService")
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;
    @Override
    public void insert(Picture picture) {

        pictureDao.insert(picture);
    }

    @Override
    public List<String> getp1(String username) {
        return pictureDao.getp1(username);
    }

    @Override
    public List<String> getp2(String username, int tag) {
        return pictureDao.getp2(username,tag);
    }

    @Override
    public List<String> getp3(String id) {
        return pictureDao.getp3(id);
    }

    @Override
    public void updatetag(String picturename, int tag) {
     pictureDao.updatetag(picturename,tag);
    }


    @Override
    public void delete(String picturename) {
        pictureDao.delete(picturename);
    }



}
