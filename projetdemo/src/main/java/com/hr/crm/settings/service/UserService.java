package com.hr.crm.settings.service;

import com.hr.crm.exception.LoginException;
import com.hr.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}
