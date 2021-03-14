package cn.hocztms.dao;

import cn.hocztms.domin.Picture;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PictureDao {

    //保存图片 uid 为用来搜寻再那一文件夹下图片
    @Insert("insert into picture (id,username,date,picturename,tag) value (#{id},#{username},#{date},#{picturename},#{tag})")
    public  void insert(Picture picture);

    //获取该用户全部图片
    @Select("select picturename from picture where username = #{username}")
    public List<String> getp1(String username);

    //获取该用户全部未审核图片
    @Select("select picturename from picture where username = #{username}and tag = #{tag}")
    public List<String> getp2(@Param("username")String username,@Param("tag")int tag);

    //获取某一文件夹下全部图片
    @Select("select picturename from picture where id = #{id}")
    public List<String> getp3(String id);

    //删除图片 根据图片名字删除图片
    @Delete("delete from picture where picturename = #{picturename}")
    public void delete(String picturename);

    //删除图片 根据图片名字删除图片
    @Delete("delete from picture where id = #{id}")
    public void delete_id(String id);

    //更新已审核成功图片
    @Update("update picture set tag = #{tag} where picturename = #{picturename}")
    public void updatetag(@Param("picturename")String picturename,@Param("tag")int tag);

}
