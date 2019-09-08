package my.denispavlov.robofinance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Address {

    public Address() {
        created = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ApiModelProperty(name = "Id адреса", example = "1")
    private Long id;

    @Size(max = 255)
    @ApiModelProperty(name = "Страна", example = "Россия")
    private String country;

    @Size(max = 255)
    @ApiModelProperty(name = "Регион", example = "Новосибирская область")
    private String region;

    @Size(max = 255)
    @ApiModelProperty(name = "Город", example = "Новосибирск")
    private String city;

    @Size(max = 255)
    @ApiModelProperty(name = "Улица", example = "Ленина")
    private String street;

    @Size(max = 255)
    @ApiModelProperty(name = "Дом", example = "5Б")
    private String house;

    @Size(max = 255)
    @ApiModelProperty(name = "Квартира", example = "12")
    private String flat;
    @ApiModelProperty(name = "Дата и время создания адреса", example = "2015-05-30T04:00:00.000+0000")
    private Date created;
    @ApiModelProperty(name = "Дата и время обновления адреса", example = "2015-05-30T04:00:00.000+0000")
    private Date modified;
}
