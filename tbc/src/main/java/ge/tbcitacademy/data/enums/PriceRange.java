package ge.tbcitacademy.data.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PriceRange {
    ZERO_TO_HUNDRED("0₾ - 100₾", 0, 100),
    HUNDRED_TO_TWO_HUNDRED("100₾ - 200₾", 100, 200),
    TWO_HUNDRED_TO_THREE_HUNDRED("200₾ - 300₾", 200, 300),
    THREE_HUNDRED_TO_FIVE_HUNDRED("300₾ - 500₾", 300, 500),
    FIVE_HUNDRED_TO_THOUSAND("500₾ - 1000₾", 500, 1000),
    THOUSAND_AND_ABOVE("1000₾ - დან", 1000, Integer.MAX_VALUE);

    private final String value;
    private final int min;
    private final int max;
}