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

public class UserSelectBuilder implements UserQuery {
    public final Logger LOG = LoggerFactory.getLogger(this.getClass());

    private Connection connection;
    private StringBuilder queryBuilder;
    private boolean hasWhereClause;
    private int parameterIndex = 1;
    private List<QueryParameter> queryParameters;

    public UserSelectBuilder(Connection connection, String[] columns) {
        this.connection = connection;
        this.queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT ");
        for (int i = 0; i < columns.length; i++) {
            if (i > 0) {
                queryBuilder.append(", ");
            }
            queryBuilder.append(columns[i]);
        }
        queryBuilder.append(" FROM Users");

        if (columns.length == 0) {
            throw new RuntimeException("Needs to select a column.");
        }

        queryParameters = new ArrayList<>();
    }

    @Override
    public UserQuery withId(Optional<String> optionalId) {
        optionalId.ifPresent(id -> {
            addWhereClauseIfNotExist("id = ?");
            addParameter(id);
        });
        return this;
    }

    @Override
    public UserQuery withDotaId(Optional<String> optionalDotaId) {
        optionalDotaId.ifPresent(dotaId -> {
            addWhereClauseIfNotExist("dotaid = ?");
            addParameter(dotaId);
        });
        return this;
    }

    @Override
    public UserQuery withUsername(Optional<String> optionalUsername) {
        optionalUsername.ifPresent(username -> {
            addWhereClauseIfNotExist("username = ?");
            addParameter(username);
        });
        return this;
    }

    @Override
    public UserQuery withPassword(Optional<String> optionalPassword) {
        optionalPassword.ifPresent(password -> {
            addWhereClauseIfNotExist("password = ?");
            addParameter(password);
        });
        return this;
    }

    @Override
    public UserQuery withShowDotaMatches(Optional<Boolean> optionalShowDotaMatches) {
        optionalShowDotaMatches.ifPresent(showDotaMatches -> {
            addWhereClauseIfNotExist("showdotamatches = ?");
            addParameter(showDotaMatches);
        });
        return this;
    }

    @Override
    public ResultSet execute() throws SQLException {
        LOG.info(queryBuilder.toString());
        PreparedStatement getUserStmt = connection.prepareStatement(queryBuilder.toString());
        for (QueryParameter queryParameter : queryParameters) {
            getUserStmt.setObject(queryParameter.getParameterIndex(), queryParameter.getParameter());
        }

        return getUserStmt.executeQuery();
    }

    private void addWhereClauseIfNotExist(String whereClause) {
        if (!hasWhereClause) {
            queryBuilder.append(" WHERE ");
            queryBuilder.append(whereClause);
            hasWhereClause = true;
        } else {
            queryBuilder.append(" AND " + whereClause);
        }
    }

    private void addParameter(Object parameter) {
        queryParameters.add(new QueryParameter(parameterIndex, parameter.toString()));
        parameterIndex++;
    }
}