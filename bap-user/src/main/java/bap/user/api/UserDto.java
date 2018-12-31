package bap.user.api;

public class UserDto {
    private DotaId dotaId;

    private Username username;

    public UserDto() {
    }

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

    public void setDotaId(DotaId dotaId) {
        this.dotaId = dotaId;
    }

    public void setUsername(Username username) {
        this.username = username;
    }
}
