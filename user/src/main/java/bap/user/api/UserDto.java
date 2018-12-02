package bap.user.api;

public class UserDto {
    private DotaId dotaId;

    private Username username;

    public UserDto(DotaId dotaId, Username username) {
        this.dotaId = dotaId;
        this.username = username;
    }

    public DotaId getDotaId() {
        return dotaId;
    }

    public Username getUsername() {
        return username;
    }
}
