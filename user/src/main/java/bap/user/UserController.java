package bap.user;

import bap.user.api.UserApiImpl;

import static spark.Spark.*;

public class UserController {
    private static String USER_API_ROOT = "/user";

    private static String VERSION_1 = "/v1";

    private UserApiImpl userApiImpl;

    public UserController(UserApiImpl userApiImpl) {
        this.userApiImpl = userApiImpl;
    }

    public void start() {
        path("/api/", () -> {
            path("/v1", () -> {
                get(USER_API_ROOT + "/dotaId/:id", userApiImpl::getUser);

                get(USER_API_ROOT + "/username/:username", userApiImpl::getUser);

                post(USER_API_ROOT, userApiImpl::createUser);

                put(USER_API_ROOT, userApiImpl::updateUser);

                delete(USER_API_ROOT, userApiImpl::deleteUser);
            });
        });

    }
}
