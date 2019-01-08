package bap.user.dao;

import java.sql.Connection;

public class UserQueryFactory {
    private Connection connection;

    public UserQueryFactory(Connection connection) {
        this.connection = connection;
    }

    public UserQuery select(String[] columns) {
        return new UserSelectBuilder(connection, columns);
    }

    public UserQuery insert() {
        return new UserCreateBuilder(connection);
    }
}

