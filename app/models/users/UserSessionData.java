package models.users;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.UUID;

@ApiModel()
public class UserSessionData {

    @ApiModelProperty(example = "705477ee-f632-4b5c-b038-04296ef21236")
    private UUID uuid;
    @ApiModelProperty(example = "mqjgr0dvhu6tkpi0damnl6uc28")
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
