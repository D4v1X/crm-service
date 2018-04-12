package models.users;

import enums.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class UpdateUserRequest {

    @ApiModelProperty(example = "admin")
    private String name;
    @ApiModelProperty(example = "admin@gmail.com")
    private String email;
    @ApiModelProperty(example = "ADMIN", allowableValues = "ADMIN, USER", allowEmptyValue = true)
    private Role role;

    public UpdateUserRequest() { }

    public UpdateUserRequest(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
