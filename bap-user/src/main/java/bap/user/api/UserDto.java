package bap.user.api;

import java.util.Optional;

public class UserDto {
    private DotaId dotaId;

    private Username username;

    public UserDto(DotaId dotaId) {
        this(dotaId, null);
    }

    public UserDto(Username username) {
        this(null, username);
    }

    public UserDto(DotaId dotaId, Username username) {
        this.dotaId = dotaId;
        this.username = username;
    }

    public Optional<String> getDotaId() {
        return Optional.ofNullable(dotaId.getDotaId());
    }

    public Optional<String> getUsername() {
        return Optional.ofNullable(username.getUsername());
    }

    public void setDotaId(DotaId dotaId) {
        this.dotaId = dotaId;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "{dotaId=" + dotaId.getDotaId() +
                ", username=" + username.getUsername() +
                '}';
    }
}
