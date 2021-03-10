package cn.hocztms.dao;

import cn.hocztms.domin.Account;
import cn.hocztms.domin.Folder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderDao {

    //保存文件夹
    @Insert("insert into folder (id,fid,username,filename) value (#{id},#{fid},#{username},#{filename})")
    public void insertfolder(Folder folder);

    //根据 fid来获取某一文件夹下的文件夹 或 一级目录
    @Select("select * from folder where username = #{username} and  fid = #{fid}")
    public List<Folder> findfolder(@Param("username") String username, @Param("fid") String fid);

    @Delete("delete from folder where id = #{id}")
    public void delete(String id);
}
