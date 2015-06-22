package com.chichin.cityTransport.dao.interfaces;

import com.chichin.cityTransport.entity.User;

/**
 * Created by viacheslav on 15.06.15.
 */
public interface UserDao {
    public User getUser(String login, String password);
}
