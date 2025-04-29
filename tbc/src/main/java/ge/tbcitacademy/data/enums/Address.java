package ge.tbcitacademy.data.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Address {
    SABURTALO("საბურთალო"),
    MARJANISHVILI("მარჯანიშვილი");

    private final String value;
}
