package bap.user.dao;

import bap.user.api.DotaId;
import bap.user.api.UserDto;
import bap.user.api.Username;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    private Connection connection;

    public UserDaoImpl(String url, String user, String pw) throws SQLException {
        connection = DriverManager.getConnection(url, user, pw);
    }

    @Override
    public UserDto getUser(User user) throws SQLException {
        PreparedStatement getUserStmt = connection.prepareStatement(
                "SELECT id, dota_id, username, password, showDotaMatches " +
                "FROM users WHERE " +
                "id = ? AND" +
                "dota_id = ? AND " +
                "username = ? AND " +
                "password = ? AND " +
                "showDotaMatches = ?");

        getUserStmt.setString(1, user.getId());
        getUserStmt.setString(2, user.getDotaId());
        getUserStmt.setString(3, user.getUsername());
        getUserStmt.setString(4, user.getPassword());
        getUserStmt.setBoolean(5, user.isShowDotaMatches());

        ResultSet result = getUserStmt.executeQuery();
        if (!result.next()) {
            return null;
        }

        UserDto userDto = new UserDto(new DotaId(result.getString("dota_id")),
                new Username(result.getString("username")));

        return userDto;
    }

    @Override
    public void createUser(User user) throws SQLException {
        PreparedStatement createUserStmt = connection.prepareStatement(
                "INSERT INTO user(id, dota_id, username, password, showDotaMatches) " +
                "VALUES (?, ?, ?)");

        createUserStmt.setString(1, user.getId());
        createUserStmt.setString(2, user.getDotaId());
        createUserStmt.setString(3, user.getUsername());
        createUserStmt.setString(4, user.getPassword());
        createUserStmt.setBoolean(5, user.isShowDotaMatches());

        createUserStmt.executeUpdate();
    }

    @Override
    public void updateUser(User user) throws SQLException {
        PreparedStatement createUserStmt = connection.prepareStatement(
                "UPDATE user SET dota_id = ?, username = ?, password = ?, showDotaMatches = ? " +
                        "WHERE id = ?");

        createUserStmt.setString(1, user.getDotaId());
        createUserStmt.setString(2, user.getUsername());
        createUserStmt.setString(3, user.getPassword());
        createUserStmt.setBoolean(4, user.isShowDotaMatches());
        createUserStmt.setString(5, user.getId());

        createUserStmt.executeUpdate();
    }

    @Override
    public void deleteUser(User user) throws SQLException {
        PreparedStatement createUserStmt = connection.prepareStatement(
                "DELETE FROM user WHERE id = ?, dota_id = ?, username = ?, password = ?, showDotaMatches = ?");

        createUserStmt.setString(1, user.getId());
        createUserStmt.setString(2, user.getDotaId());
        createUserStmt.setString(3, user.getUsername());
        createUserStmt.setString(4, user.getPassword());
        createUserStmt.setBoolean(5, user.isShowDotaMatches());

        createUserStmt.executeUpdate();
    }
}
