package ge.tbcitacademy.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NumberOfGuest {
    TWO_TO_FIVE("2-5"),
    SIX_TO_TEN("6-10"),
    ELEVEN_TO_FIFTEEN("11-15"),
    SIXTEEN_TO_TWENTY("16-20"),
    TWENTYONE_TO_THIRTY("21-30"),
    THIRTYONE_TO_FORTY("31-40"),
    FORTYONE_TO_FIFTY("41-50"),
    FIFTYONE_TO_SIXTY("51-60"),
    SIXTYONE_TO_HUNDRED("61-100"),
    HUNDREDONE_TO_HUNDREDFIFTY("101-150");

    private final String value;
}
