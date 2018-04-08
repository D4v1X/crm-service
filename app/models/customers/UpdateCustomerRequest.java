package models.customers;

public class UpdateCustomerRequest {

    private String name;
    private String surname;
    private String photo;

    public UpdateCustomerRequest() { }

    public UpdateCustomerRequest(String name, String surname, String photo) {
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
