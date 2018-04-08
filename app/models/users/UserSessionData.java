package models.users;

import java.util.UUID;

public class UserSessionData {

    private UUID uuid;
    private String token;

    public UserSessionData() {
    }

    public UserSessionData(UUID uuid, String token) {
        this.uuid = uuid;
        this.token = token;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
