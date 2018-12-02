package bap.user.api;

import bap.user.dao.User;
import bap.user.services.InvalidUserException;
import bap.user.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.io.IOException;

public class UserApiImpl implements UserApi {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    public UserApiImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto getUser(Request req, Response res) {
        UserDto user = new UserDto(new DotaId(req.params(":id")), new Username(req.params(":username")));
        return userService.getUser(user);
    }

    @Override
    public String createUser(Request req, Response res) {
        ObjectMapper mapper = new ObjectMapper();

        User user = null;
        try {
            user = mapper.readValue(req.body(), User.class);
            userService.createUser(user);
        } catch (IOException e) {
            LOG.error("Failed to deserialize user when attempting to create a user.");
            LOG.error(e.getMessage());
        } catch (InvalidUserException e) {
            LOG.warn("User " + user.getUsername() + " is invalid.");
            LOG.warn(e.getMessage());
        }
        return "User created.";

    }

    @Override
    public String updateUser(Request req, Response res) {
        ObjectMapper mapper = new ObjectMapper();

        User user = null;
        try {
            user = mapper.readValue(req.body(), User.class);
            userService.updateUser(user);
        } catch (IOException e) {
            LOG.error("Failed to deserialize user when attempting to update a user.");
            LOG.error(e.getMessage());
        } catch (InvalidUserException e) {
            LOG.warn("User " + user.getUsername() + " is invalid.");
            LOG.warn(e.getMessage());
        }
        return "User updated.";
    }

    @Override
    public String deleteUser(Request req, Response response) {
        ObjectMapper mapper = new ObjectMapper();

        User user;
        try {
            user = mapper.readValue(req.body(), User.class);
            userService.deleteUser(user);
        } catch (IOException e) {
            LOG.error("Failed to deserialize user when attempting to delete a user.");
            LOG.error(e.getMessage());
        }
        return "User deleted.";
    }
}
