package cn.hocztms.service;

import cn.hocztms.domin.Folder;

import java.util.List;

public interface FolderService {
    public void insertfolder(Folder folder);

    public List<Folder> findfolder(String username,String fid);

    public void delete (String id);
}
