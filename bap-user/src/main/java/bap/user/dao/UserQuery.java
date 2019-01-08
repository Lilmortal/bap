package bap.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface UserQuery {
    UserQuery withId(Optional<String> optionalId);

    UserQuery withDotaId(Optional<String> optionalDotaId);

    UserQuery withUsername(Optional<String> optionalUsername);

    UserQuery withPassword(Optional<String> optionalPassword);

    UserQuery withShowDotaMatches(Optional<Boolean> optionalShowDotaMatches);

    ResultSet execute() throws SQLException;
}
