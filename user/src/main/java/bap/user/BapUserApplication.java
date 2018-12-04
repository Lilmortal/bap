package bap.user;


import bap.user.api.UserApiImpl;
import bap.user.dao.UserDao;
import bap.user.dao.UserDaoImpl;
import bap.user.services.UserService;
import bap.user.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class BapUserApplication {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private void init() {
        UserDao userDao = null;

        // TODO: Put in env
        String url = "url";
        String user = "user";
        String password = "password";

        try {
            userDao = new UserDaoImpl(url, user, password);
        } catch (SQLException e) {
            LOG.error("Failed to connect to database.");
            LOG.error(e.getSQLState());
        }
        UserService userService = new UserServiceImpl(userDao);
        UserApiImpl userApiImpl = new UserApiImpl(userService);

        new UserController(userApiImpl).start();
    }

    public static void main(String[] args) {
        BapUserApplication application = new BapUserApplication();
        application.init();
    }
}
