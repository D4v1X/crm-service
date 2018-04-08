package models.customers;

import java.time.LocalDateTime;

public class CustomerWithDetails {

    private Integer id;
    private String name;
    private String surname;
    private String photo;
    private String createdByUser;
    private LocalDateTime created;
    private String modifiedByUser;
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
