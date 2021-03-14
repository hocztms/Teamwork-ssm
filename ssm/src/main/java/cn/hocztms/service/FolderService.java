package cn.hocztms.service;

import cn.hocztms.domin.Folder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FolderService {
    public void insertfolder(Folder folder);

    public List<Folder> finduserfolder(String username,String fid);

    public void delete (String id);

    public List<String> findfolder_fid(String fid);

    public List<Folder> findfolderlist(String fid);

    public Folder findfolder_id(String id);

    public void deleteFolder(Folder folder);

    public String checkfolder(String username,String fid,String filename);

    public void updatefilename(@Param("id")String id,@Param("filename")String filename);
}
