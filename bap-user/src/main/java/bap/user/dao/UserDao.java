package bap.user.dao;

import bap.user.api.UserDto;

import java.sql.SQLException;

public interface UserDao {
    UserDto getUser(User user) throws SQLException;

    void createUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;
}
