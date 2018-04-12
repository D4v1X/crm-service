package models.users;

import enums.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class NewUserRequest {

    @ApiModelProperty(example = "admin")
    private String name;
    @ApiModelProperty(example = "admin@gmail.com", required = true)
    private String email;
    @ApiModelProperty(example = "admin", required = true)
    private String password;
    @ApiModelProperty(example = "ADMIN", allowableValues = "ADMIN, USER", allowEmptyValue = true, required = true)
    private Role role;

    public NewUserRequest() {
    }

    public NewUserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public NewUserRequest(String name, String email, String password, Role role) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
