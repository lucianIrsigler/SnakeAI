package enums;

public enum Direction {
    NO_DIRECTION(-1),
    UP(0),
    DOWN(1),
    LEFT(2),
    RIGHT(3);
    private final int value;

    Direction(final int value) { this.value = value; }

    public int getValue() { return value; }

    @Override
    public String toString() {
        return String.valueOf(value);
    }


}
