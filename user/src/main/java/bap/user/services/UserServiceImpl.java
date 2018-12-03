package bap.user.services;

import bap.user.api.UserDto;
import bap.user.dao.User;
import bap.user.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String VALID_USERS_REGEX = "[a-zA-Z\\d]{5,}";

    private static final String VALID_PASSWORD_REGEX = "[a-zA-Z\\d]{5,}";

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDto getUser(UserDto userDto) {
        User user = new User(userDto.getDotaId().get(), userDto.getUsername().get());
        try {
            return userDao.getUser(user);
        } catch (SQLException e) {
            LOG.error("Failed to get " + userDto.getUsername() + ".");
            LOG.error(e.getSQLState());
        }

        return null;
    }

    @Override
    public void createUser(User user) throws InvalidUserException {
        if (isValid(user)) {
            try {
                userDao.createUser(user);
            } catch (SQLException e) {
                LOG.error("Failed to create " + user.getUsername() + ".");
                LOG.error(e.getSQLState());
            }
        } else {
            throw new InvalidUserException();
        }
    }

    @Override
    public void updateUser(User user) throws InvalidUserException {
        if (isValid(user)) {
            try {
                userDao.updateUser(user);
            } catch (SQLException e) {
                LOG.error("Failed to update " + user.getUsername() + ".");
                LOG.error(e.getSQLState());
            }
        } else {
            throw new InvalidUserException();
        }
    }

    @Override
    public void deleteUser(User user) {
        try {
            userDao.deleteUser(user);
        } catch (SQLException e) {
            LOG.error("Failed to delete " + user.getUsername() + ".");
            LOG.error(e.getSQLState());
        }
    }

    private boolean isValid(User user) {
        return VALID_USERS_REGEX.matches(user.getUsername()) && VALID_PASSWORD_REGEX.matches(user.getPassword());
    }
}
