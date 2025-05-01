package ge.tbcitacademy.data.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Offer {
    private String title;
    private String description;
    private String price;
    private String soldQuantity;
}