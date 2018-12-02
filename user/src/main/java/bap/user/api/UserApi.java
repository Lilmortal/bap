package bap.user.api;

import spark.Request;
import spark.Response;

public interface UserApi {
    UserDto getUser(Request req, Response res);

    String createUser(Request req, Response res);

    String updateUser(Request req, Response res);

    String deleteUser(Request req, Response response);
}
