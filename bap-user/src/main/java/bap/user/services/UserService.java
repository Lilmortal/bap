package bap.user.services;

import bap.user.api.UserDto;
import bap.user.dao.User;

public interface UserService {
    String getUsers(UserDto user);

    void createUser(User user) throws InvalidUserException;

    void updateUser(User user) throws InvalidUserException;

    void deleteUser(User user);
}
