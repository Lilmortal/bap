package bap.user;


import bap.user.api.UserApiImpl;
import bap.user.dao.UserDao;
import bap.user.dao.UserDaoImpl;
import bap.user.services.UserService;
import bap.user.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class BapUserApplication {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static final String USER_DB_PROPERTIES = "/user_db.properties";

    private static final String USER_DB_URL_KEY = "url";

    private static final String USER_DB_USER_KEY = "user";

    private static final String USER_DB_PASSWORD_KEY = "password";

    private void init() {
        UserDao userDao = null;
        Properties prop = new Properties();
        InputStream input = null;
        try {
            InputStream file = getClass().getResourceAsStream(USER_DB_PROPERTIES);

            prop.load(file);
            userDao = new UserDaoImpl(prop.getProperty(USER_DB_URL_KEY), prop.getProperty(USER_DB_USER_KEY), prop.getProperty(USER_DB_PASSWORD_KEY));
        } catch (SQLException e) {
            LOG.error("Failed to connect to database. SQL State: " + e.getSQLState());
        } catch (FileNotFoundException e) {
            LOG.error("User database properties file not found.");
            LOG.error(e.getMessage());
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    LOG.error("Failed to close user database properties file.");
                    LOG.error(e.getMessage());
                }
            }
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
