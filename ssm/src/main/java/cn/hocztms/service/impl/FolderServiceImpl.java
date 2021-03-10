package cn.hocztms.service.impl;

import cn.hocztms.dao.FolderDao;
import cn.hocztms.domin.Folder;
import cn.hocztms.service.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("folderService")
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderDao folderDao;

    @Override
    public void insertfolder(Folder folder) {
        folderDao.insertfolder(folder);
    }

    @Override
    public List<Folder> findfolder(String username,String fid) {
        return folderDao.findfolder(username,fid);
    }

    @Override
    public void delete(String id) {
        folderDao.delete(id);
    }
}
