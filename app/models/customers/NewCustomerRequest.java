package models.customers;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class NewCustomerRequest {

    @ApiModelProperty(example = "David", required = true)
    private String name;
    @ApiModelProperty(example = "Brand", required = true)
    private String surname;
    @ApiModelProperty()
    private String photo;

    public NewCustomerRequest() { }

    public NewCustomerRequest(String name, String surname, String photo) {
        this.name = name;
        this.surname = surname;
        this.photo = photo;
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

}
