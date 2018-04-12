package models.customers;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel()
public class CustomerWithDetails {

    @ApiModelProperty(example = "4")
    private Integer id;
    @ApiModelProperty(example = "Xema")
    private String name;
    @ApiModelProperty(example = "Lohan")
    private String surname;
    @ApiModelProperty(example = "data:image/png;base64,iVBORw........kAAAAASUVORK5CYII=")
    private String photo;
    @ApiModelProperty(example = "user")
    private String createdByUser;
    @ApiModelProperty(example = "2018-04-08T15:14:24.761535")
    private LocalDateTime created;
    @ApiModelProperty(example = "admin")
    private String modifiedByUser;
    @ApiModelProperty(example = "2018-04-08T19:23:12.551626")
    private LocalDateTime modified;

    public CustomerWithDetails() { }

    public CustomerWithDetails(Integer id, String name, String surname, String photo, String createdByUser, LocalDateTime created, String modifiedByUser, LocalDateTime modified) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.photo = photo;
        this.createdByUser = createdByUser;
        this.created = created;
        this.modifiedByUser = modifiedByUser;
        this.modified = modified;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(String createdByUser) {
        this.createdByUser = createdByUser;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(String modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
