package cn.hocztms.dao;

import cn.hocztms.domin.Account;
import cn.hocztms.domin.Folder;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderDao {

    //保存文件夹
    @Insert("insert into folder (id,fid,username,filename) value (#{id},#{fid},#{username},#{filename})")
    public void insertfolder(Folder folder);

    //根据 fid来获取某一文件夹下的文件夹 或 一级目录
    @Select("select * from folder where username = #{username} and  fid = #{fid}")
    public List<Folder> finduserfolder(@Param("username") String username, @Param("fid") String fid);

    //查找某一文件夹的子文件
    @Select("select * from folder where fid = #{fid}")
    public List<Folder> findfolderlist(String fid);

    @Select("select * from folder where id = #{id}")
    public Folder findfolder_id(String id);

    //根据id删除某个文件夹
    @Delete("delete from folder where id = #{id}")
    public void delete(String id);

    @Select("select id from folder where fid = #{fid}")
    public List<String> findfolder_fid(String fid);

    //检查是否已经有文件夹
    @Select("select id from folder where username = #{username} and  fid = #{fid} and filename = #{filename}")
    public String checkfolder(@Param("username") String username, @Param("fid") String fid,@Param("filename")String filename);

    //更新文件夹名
    @Update("update folder set filename = #{filename} where id = #{id}")
    public void updatefilename(@Param("id")String id,@Param("filename")String filename);
}
