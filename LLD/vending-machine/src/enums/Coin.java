package enums;

public enum Coin {
    ONE_RUPEE(1),
    TWO_RUPEE(2),
    FIVE_RUPEE(5),
    TEN_RUPEE(10);

    int value;

    private Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}