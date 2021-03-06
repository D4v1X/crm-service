/*
 * This file is generated by jOOQ.
*/
package jooq.objects.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Customer implements Serializable {

    private static final long serialVersionUID = 1619621029;

    private Integer       id;
    private String        name;
    private String        surname;
    private String        photo;
    private Integer       createdByUser;
    private LocalDateTime created;
    private Integer       modifiedByUser;
    private LocalDateTime modified;

    public Customer() {}

    public Customer(Customer value) {
        this.id = value.id;
        this.name = value.name;
        this.surname = value.surname;
        this.photo = value.photo;
        this.createdByUser = value.createdByUser;
        this.created = value.created;
        this.modifiedByUser = value.modifiedByUser;
        this.modified = value.modified;
    }

    public Customer(
        Integer       id,
        String        name,
        String        surname,
        String        photo,
        Integer       createdByUser,
        LocalDateTime created,
        Integer       modifiedByUser,
        LocalDateTime modified
    ) {
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
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoto() {
        return this.photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getCreatedByUser() {
        return this.createdByUser;
    }

    public void setCreatedByUser(Integer createdByUser) {
        this.createdByUser = createdByUser;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Integer getModifiedByUser() {
        return this.modifiedByUser;
    }

    public void setModifiedByUser(Integer modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }

    public LocalDateTime getModified() {
        return this.modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Customer (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(surname);
        sb.append(", ").append(photo);
        sb.append(", ").append(createdByUser);
        sb.append(", ").append(created);
        sb.append(", ").append(modifiedByUser);
        sb.append(", ").append(modified);

        sb.append(")");
        return sb.toString();
    }
}
