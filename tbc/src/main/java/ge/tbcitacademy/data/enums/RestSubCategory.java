package ge.tbcitacademy.data.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RestSubCategory {
    KAKHETI("კახეთი"),
    TOURS_IN_GEORGIA("ტურები საქართველოში"),
    HOTELS_WITH_POOL("სასტუმროები აუზით"),
    MOUNTAIN_RESORTS("მთის კურორტები"),
    SEASIDE("ზღვისპირეთი"),
    TBILISI_AND_SUBURBS("თბილისი და შემოგარენი");

    private final String value;
}
