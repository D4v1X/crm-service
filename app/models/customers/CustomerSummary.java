package models.customers;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel()
public class CustomerSummary {

    @ApiModelProperty(example = "4")
    private Integer id;
    @ApiModelProperty(example = "Xema")
    private String name;

    public CustomerSummary() {
    }

    public CustomerSummary(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
