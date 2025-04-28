package ge.tbcitacademy.data.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Deal {
    private String title;
    private String description;
    private String price;
    private String soldQuantity;
}