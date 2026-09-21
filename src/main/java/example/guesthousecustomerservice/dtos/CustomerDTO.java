package example.guesthousecustomerservice.dtos;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {
    @NotBlank(message = "Name is required")
    private String name;
    private Long id;

    public CustomerDTO() {
    }

    public CustomerDTO(String name, Long id) {
        this.name = name;
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
