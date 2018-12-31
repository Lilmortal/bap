package bap.user.dao;

public class User {
    private String id;

    private String dotaId;

    private String username;

    private String password;

    private boolean showDotaMatches;

    public User(String dotaId, String username) {
        this(null, dotaId, username, null, true);
    }

    public User(String dotaId, String username, String password) {
        this(null, dotaId, username, password, true);
    }

    public User(String id, String dotaId, String username, String password, boolean showDotaMatches) {
        this.id = id;
        this.dotaId = dotaId;
        this.username = username;
        this.password = password;
        this.showDotaMatches = showDotaMatches;
    }

    public String getId() {
        return id;
    }

    public String getDotaId() {
        return dotaId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isShowDotaMatches() {
        return showDotaMatches;
    }
}
