package com.hr.crm.settings.dao;

import com.hr.crm.settings.domain.User;

import java.util.Map;

public interface UserDao {
    User login(Map<String, String> map);
}
