package bap.user.dao;

import bap.user.api.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    List<User> getUsers(User user) throws SQLException;

    void createUser(User user) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(User user) throws SQLException;
}
