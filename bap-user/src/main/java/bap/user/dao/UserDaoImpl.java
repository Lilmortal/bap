package bap.user.dao;

import bap.user.api.DotaId;
import bap.user.api.UserDto;
import bap.user.api.Username;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private Connection connection;

    public UserDaoImpl(String url, String user, String pw) throws SQLException {
        connection = DriverManager.getConnection(url, user, pw);
    }

    @Override
    public List<User> getUsers(User user) throws SQLException {
        UserQuery userSelectBuilder = new UserQueryFactory(connection).select(new String[]{"id", "dotaid", "username", "password", "showdotamatches"});
        ResultSet result = userSelectBuilder
                .withId(user.getId())
                .withDotaId(user.getDotaId())
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withShowDotaMatches(user.getShowDotaMatches())
                .execute();

        List<User> users = new ArrayList<>();
        while (result.next()) {
            User retrievedUser = new User(result.getString("id"), result.getString("dotaid"),
                    result.getString("username"), null, result.getBoolean("showdotamatches"));

            users.add(retrievedUser);
        }

        return users;
    }

    @Override
    public void createUser(User user) throws SQLException {
        UserQuery userInsertBuilder = new UserQueryFactory(connection).insert();
        ResultSet result = userInsertBuilder
                .withId(user.getId())
                .withDotaId(user.getDotaId())
                .withUsername(user.getUsername())
                .withPassword(user.getPassword())
                .withShowDotaMatches(user.getShowDotaMatches())
                .execute();

        if (result.next()) {
            LOG.info("User created.");
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
//        PreparedStatement createUserStmt = connection.prepareStatement(
//                "UPDATE user SET dota_id = ?, username = ?, password = ?, showDotaMatches = ? " +
//                        "WHERE id = ?");
//
//        createUserStmt.setString(1, user.getDotaId());
//        createUserStmt.setString(2, user.getUsername());
//        createUserStmt.setString(3, user.getPassword());
//        createUserStmt.setBoolean(4, user.getShowDotaMatches());
//        createUserStmt.setString(5, user.getId());
//
//        createUserStmt.executeUpdate();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
//        PreparedStatement createUserStmt = connection.prepareStatement(
//                "DELETE FROM user WHERE id = ?, dota_id = ?, username = ?, password = ?, showDotaMatches = ?");
//
//        createUserStmt.setString(1, user.getId());
//        createUserStmt.setString(2, user.getDotaId());
//        createUserStmt.setString(3, user.getUsername());
//        createUserStmt.setString(4, user.getPassword());
//        createUserStmt.setBoolean(5, user.getShowDotaMatches());
//
//        createUserStmt.executeUpdate();
    }
}
