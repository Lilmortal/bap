package bap.user.dao;

import java.util.Optional;

public class User {
    // TODO: Make this a UUID
    private String id;

    private String dotaId;

    private String username;

    private String password;

    private Boolean showDotaMatches;

    public User() {
    }

    public User(String dotaId, String username) {
        this(null, dotaId, username, null, null);
    }

    public User(String dotaId, String username, String password) {
        this(null, dotaId, username, password, null);
    }

    public User(String id, String dotaId, String username, String password, Boolean showDotaMatches) {
        this.id = id;
        this.dotaId = dotaId;
        this.username = username;
        this.password = password;
        this.showDotaMatches = showDotaMatches;
    }

    public Optional<String> getId() {
        return Optional.ofNullable(id);
    }

    public Optional<String> getDotaId() {
        return Optional.ofNullable(dotaId);
    }

    public Optional<String> getUsername() {
        return Optional.ofNullable(username);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    public Optional<Boolean> getShowDotaMatches() {
        return Optional.ofNullable(showDotaMatches);
    }

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' +
                ", dotaId='" + dotaId + '\'' +
                ", username='" + username + '\'' +
                ", showDotaMatches=" + showDotaMatches +
                '}';
    }
}
