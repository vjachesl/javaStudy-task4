package com.chichin.cityTransport.dao.mySqlDao;

import com.chichin.cityTransport.dao.factory.DaoFactory;
import com.chichin.cityTransport.dao.interfaces.UserDao;
import com.chichin.cityTransport.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.chichin.cityTransport.dao.factory.DaoJdbcUtil.close;

/**
 * Class for JDBC User DAO implementation
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */
public class MySqlUserDao implements UserDao {

    private DaoFactory daoFactory;

    private static final String FIND_ALL_USER = "SELECT * FROM city_transport_db.users";

    public MySqlUserDao(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public User getUser(String login, String password) {
        User toBeFinded = new User(login, password);
        User toReturn = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<User>();
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(FIND_ALL_USER);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }

        for (User us : users) if (us.equals(toBeFinded)) return toBeFinded;

        return toReturn;
    }

    private User map(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getString(1), resultSet.getString(3));
    }
}
