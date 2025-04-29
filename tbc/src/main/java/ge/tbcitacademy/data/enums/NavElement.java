package ge.tbcitacademy.data.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NavElement {
    MOVIE("კინო"),
    REST("დასვენება"),
    ENTERTAINMENT("გართობა"),
    FOOD("კვება"),
    KIDS("საბავშვო"),
    SPORTS("სპორტი"),
    BEAUTY("ესთეტიკა"),
    HEALTH("ჯანმრთელობა"),
    COURSES("კურსები"),
    PETS("ცხოველები");

    private final String value;
}
