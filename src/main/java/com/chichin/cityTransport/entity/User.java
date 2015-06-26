package com.chichin.cityTransport.entity;

/**
 * Class for users objects.
 *
 * @author Viacheslav Chichin
 * @version 1.0  June 20, 2015.
 */

public class User {

    /**
     * Stores user login as final String
     */
    private String login;

    /**
     * Stores user password as final String
     */
    private String password;


    /**
     * Constructor
     *
     * @param login    - user login
     * @param password - user password
     */
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    //getters for login and password
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!login.equals(user.login)) return false;
        return password.equals(user.password);

    }

    @Override
    public int hashCode() {
        int result = login.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
