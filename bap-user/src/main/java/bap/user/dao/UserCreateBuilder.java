package bap.user.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserCreateBuilder implements UserQuery {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private Connection connection;
    private StringBuilder queryBuilder;
    private List<QueryParameter> queryParameters;
    private boolean isFirstValue;
    private int parameterIndex = 1;
    private int numOfValues;

    public UserCreateBuilder(Connection connection) {
        this.connection = connection;

        this.queryBuilder = new StringBuilder();
        queryBuilder.append("INSERT INTO Users (");

        queryParameters = new ArrayList<>();
    }

    @Override
    public UserQuery withId(Optional<String> optionalId) {
        optionalId.ifPresent(id -> {
            insertValue("id");
            addParameter(id);
        });
        return this;
    }

    @Override
    public UserQuery withDotaId(Optional<String> optionalDotaId) {
        optionalDotaId.ifPresent(dotaId -> {
            insertValue("dotaid");
            addParameter(dotaId);
        });
        return this;
    }

    @Override
    public UserQuery withUsername(Optional<String> optionalUsername) {
        optionalUsername.ifPresent(username -> {
            insertValue("username");
            addParameter(username);
        });
        return this;
    }

    @Override
    public UserQuery withPassword(Optional<String> optionalPassword) {
        optionalPassword.ifPresent(password -> {
            insertValue("password");
            addParameter(password);
        });
        return this;
    }

    @Override
    public UserQuery withShowDotaMatches(Optional<Boolean> optionalShowDotaMatches) {
        optionalShowDotaMatches.ifPresent(showDotaMatches -> {
            insertValue("showdotamatches");
            addParameter(showDotaMatches);
        });
        return this;
    }

    @Override
    public ResultSet execute() throws SQLException {
        queryBuilder.append(") VALUES (");
        for (int i = 0; i < numOfValues; i++) {
            if (i > 0) {
                queryBuilder.append(", ?");
            } else {
                queryBuilder.append("?");
            }
        }
        queryBuilder.append(") RETURNING id");

        PreparedStatement createUserStmt = connection.prepareStatement(queryBuilder.toString());
        LOG.info(queryBuilder.toString());
        for (QueryParameter queryParameter : queryParameters) {
            createUserStmt.setObject(queryParameter.getParameterIndex(), queryParameter.getParameter());
        }

        return createUserStmt.executeQuery();
    }

    private void insertValue(String value) {
        if (!isFirstValue) {
            queryBuilder.append(value);
            isFirstValue = true;
        } else {
            queryBuilder.append(", " + value);
        }
        numOfValues++;
    }

    private void addParameter(Object parameter) {
        queryParameters.add(new QueryParameter(parameterIndex, parameter));
        parameterIndex++;
    }
}
