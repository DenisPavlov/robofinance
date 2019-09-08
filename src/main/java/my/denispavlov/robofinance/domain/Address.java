package my.denispavlov.robofinance.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
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
    @ApiModelProperty(name = "Страна", example = "Россия")
    private String country;
    @ApiModelProperty(name = "Регион", example = "Новосибирская область")
    private String region;
    @ApiModelProperty(name = "Город", example = "Новосибирск")
    private String city;
    @ApiModelProperty(name = "Улица", example = "Ленина")
    private String street;
    @ApiModelProperty(name = "Дом", example = "5Б")
    private String house;
    @ApiModelProperty(name = "Квартира", example = "12")
    private String flat;
    @ApiModelProperty(name = "Дата и время создания адреса", example = "2015-05-30T04:00:00.000+0000")
    private Date created;
    @ApiModelProperty(name = "Дата и время обновления адреса", example = "2015-05-30T04:00:00.000+0000")
    private Date modified;
}
