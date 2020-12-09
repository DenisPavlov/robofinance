package my.denispavlov.robofinance.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import my.denispavlov.robofinance.util.SexConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Customer {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @ApiModelProperty(name = "Имя", example = "Иван", position = 1)
    private String firstName;

    @Size(max = 255)
    @ApiModelProperty(name = "Фамилия", example = "Иванов", position = 2)
    private String lastName;

    @Size(max = 255)
    @ApiModelProperty(name = "Отчество", example = "Иванович", position = 3)
    private String middleName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "registered_address_id", referencedColumnName = "id")
    @JsonIgnore
    private Address registeredAddress;

    @OneToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Address actualAddress;

    @NotNull
    @Convert(converter = SexConverter.class)
    @ApiModelProperty(name = "Пол", example = "MALE / FEMALE", position = 4)
    private Sex sex;

    @ApiModelProperty(name = "ID адреса регистрации", example = "1", position = 5)
    public long getRegisteredAddressId() {
        return registeredAddress == null ? 0 : registeredAddress.getId();
    }

    @ApiModelProperty(name = "ID адреса проживания", example = "1", position = 6)
    public long getActualAddressId() {
        return actualAddress == null ? 0 : actualAddress.getId();
    }

    public void setRegisteredAddressId(Long id) {
        if (registeredAddress == null) {
            Address registeredAddress = new Address();
            registeredAddress.setId(id);
            this.registeredAddress = registeredAddress;
        } else {
            this.registeredAddress.setId(id);
        }

    }

    public void setActualAddressId(Long id) {
        if (actualAddress == null) {
            Address actualAddress = new Address();
            actualAddress.setId(id);
            this.actualAddress = actualAddress;
        } else {
            this.actualAddress.setId(id);
        }
    }
}