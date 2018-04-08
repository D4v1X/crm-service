package models.customers;

public class NewCustomerRequest {

    private String name;
    private String surname;
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
