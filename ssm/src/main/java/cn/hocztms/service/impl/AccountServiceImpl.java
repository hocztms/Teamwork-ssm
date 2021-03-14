package cn.hocztms.service.impl;

import cn.hocztms.dao.AccountDao;
import cn.hocztms.domin.Account;
import cn.hocztms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDao accountdao;

    @Override
    public List<Account> findAll() { return accountdao.findAll(); }

    @Override
    public void saveAccount(Account account) { accountdao.saveAccount(account); }

    @Override
    public String findusername(String username) { return accountdao.findusername(username); }

    @Override
    public String findpassword(String username) {
        return accountdao.findpassword(username);
    }

    @Override
    public List<String> findallusername() {
        return accountdao.findallusername();
    }
}
