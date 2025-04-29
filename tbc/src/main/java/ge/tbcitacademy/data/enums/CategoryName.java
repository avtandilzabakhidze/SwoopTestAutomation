package ge.tbcitacademy.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CategoryName {
    CINEMA("კინო"),
    VACATION("დასვენება"),
    ENTERTAINMENT("გართობა"),
    FOOD("კვება"),
    CHILDREN("საბავშვო"),
    SPORTS("სპორტი"),
    AESTHETICS("ესთეტიკა"),
    HEALTH("ჯანმრთელობა"),
    COURSES("კურსები"),
    PETS("ცხოველები");

    private final String value;
}
