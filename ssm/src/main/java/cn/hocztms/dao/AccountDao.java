package cn.hocztms.dao;

import cn.hocztms.domin.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountDao {

    //获取全部账户信息
    @Select("select * from account")
    public List<Account> findAll();

    //保存账户
    @Insert("insert into account (username,password) value (#{username},#{password})")
    public void saveAccount(Account account);

    //查找用户 判断是否已有用户名
    @Select("select username from account where username = #{username}")
    public String findusername(String username);

    //查找密码 判断是否密码正确
    @Select("select password from account where username = #{username}")
    public String findpassword(String username);

    //获取全部用户名
    @Select("select username from account")
    public List<String> findallusername();
}
