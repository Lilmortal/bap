package bap.user;


import bap.user.api.UserApiImpl;
import bap.user.dao.UserDao;
import bap.user.dao.UserDaoImpl;
import bap.user.services.UserService;
import bap.user.services.UserServiceImpl;

public class BapUserApplication {
    public static void main(String[] args) {

        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);
        UserApiImpl userApiImpl = new UserApiImpl(userService);

        new UserController(userApiImpl).start();
    }
}
