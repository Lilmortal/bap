package bap.user;

import bap.user.api.UserApiImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class UserController {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private static String USER_API_ROOT = "/user";

    private static String VERSION_1 = "/v1";

    private UserApiImpl userApiImpl;

    public UserController(UserApiImpl userApiImpl) {
        this.userApiImpl = userApiImpl;
    }

    public void start() {
        path("/healthcheck", () -> {
            get("", (req, res) -> "It is working.");
        });

        path("/api/", () -> {
            path(VERSION_1, () -> {
                get(USER_API_ROOT + "/dotaId/:id", userApiImpl::getUsers);

                get(USER_API_ROOT + "/username/:username", userApiImpl::getUsers);

                post(USER_API_ROOT, userApiImpl::createUser);

                put(USER_API_ROOT, userApiImpl::updateUser);

                delete(USER_API_ROOT, userApiImpl::deleteUser);
            });
        });

    }
}
