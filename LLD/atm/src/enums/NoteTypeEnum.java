package enums;

public enum NoteTypeEnum {
    ONE(1),
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100),
    TWO_HUNDRED(200),
    FIVE_HUNDRED(500),
    TWO_THOUSAND(2000);

    private final int value;

    NoteTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}