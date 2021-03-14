package cn.hocztms.service.impl;

import cn.hocztms.dao.FolderDao;
import cn.hocztms.dao.PictureDao;
import cn.hocztms.domin.Folder;
import cn.hocztms.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("folderService")
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderDao folderDao;

    @Autowired
    private PictureDao pictureDao;

    @Override
    public void insertfolder(Folder folder) {
        folderDao.insertfolder(folder);
    }

    @Override
    public List<Folder> finduserfolder(String username,String fid) {
        return folderDao.finduserfolder(username,fid);
    }

    @Override
    public void delete(String id) {
        folderDao.delete(id);
    }

    @Override
    public List<String> findfolder_fid(String fid) {
        return folderDao.findfolder_fid(fid);
    }

    @Override
    public List<Folder> findfolderlist(String fid) {
        return folderDao.findfolderlist(fid);
    }

    @Override
    public Folder findfolder_id(String id) {
        return folderDao.findfolder_id(id);
    }

    //递归删除文件夹
    @Override
    public void deleteFolder(Folder folder) {
        List<String> list = folderDao.findfolder_fid(folder.getId());

        if(folderDao.findfolder_fid(folder.getId())==null){
            folderDao.delete(folder.getId());
            pictureDao.delete_id(folder.getId());
        }
        else{
            List<Folder> folderlist = folderDao.findfolderlist(folder.getId());

            for(Folder f : folderlist){
                    deleteFolder(f);
                }
            folderDao.delete(folder.getId());
            pictureDao.delete_id(folder.getId());
        }
    }

    @Override
    public String checkfolder(String username, String fid, String filename) {
        return folderDao.checkfolder(username,fid,filename);
    }

    @Override
    public void updatefilename(String id, String filename) {
        folderDao.updatefilename(id,filename);
    }


}

