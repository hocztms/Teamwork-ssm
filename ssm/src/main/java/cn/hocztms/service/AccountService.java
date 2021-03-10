package cn.hocztms.service;

import cn.hocztms.domin.Account;

import java.util.List;

public interface AccountService {
    public List<Account> findAll();

    public void saveAccount(Account account);

    public String findusername(String username);

    public String findpassword(String username);

    public List<String> findallusername();
}
