package com.chichin.cityTransport.dao.interfaces;

import com.chichin.cityTransport.entity.User;

/**
 * Interface for creating contract for user DAO implementation,
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public interface UserDao {
    User getUser(String login, String password);
}
