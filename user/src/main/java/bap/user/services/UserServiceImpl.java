package bap.user.services;

import bap.user.api.UserDto;
import bap.user.dao.User;
import bap.user.dao.UserDao;

public class UserServiceImpl implements UserService {
    private static final String VALID_USERS_REGEX = "[a-zA-Z\\d]{5,}";

    private static final String VALID_PASSWORD_REGEX = "[a-zA-Z\\d]{5,}";

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto getUser(UserDto userDto) {
        User user = new User(userDto.getDotaId().get(), userDto.getUsername().get());
        return userDao.getUser(user);
    }

    @Override
    public void createUser(User user) throws InvalidUserException {
        if (isValid(user)) {
            userDao.createUser(user);
        } else {
            throw new InvalidUserException();
        }
    }

    @Override
    public void updateUser(User user) throws InvalidUserException {
        if (isValid(user)) {
            userDao.updateUser(user);
        } else {
            throw new InvalidUserException();
        }
    }

    @Override
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    private boolean isValid(User user) {
        return VALID_USERS_REGEX.matches(user.getUsername()) && VALID_PASSWORD_REGEX.matches(user.getPassword());
    }
}
