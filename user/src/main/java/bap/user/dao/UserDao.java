package bap.user.dao;

import bap.user.api.UserDto;

public interface UserDao {
    UserDto getUser(User user);

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
