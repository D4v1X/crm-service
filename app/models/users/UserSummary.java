package models.users;

import enums.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class UserSummary {

    @ApiModelProperty(example = "1")
    private Integer id;
    @ApiModelProperty(example = "admin")
    private String name;
    @ApiModelProperty(example = "admin@gmail.com")
    private String email;
    @ApiModelProperty(example = "ADMIN", allowableValues = "ADMIN, USER")
    private Role role;

    public UserSummary() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
