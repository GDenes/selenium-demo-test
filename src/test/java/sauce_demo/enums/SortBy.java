package sauce_demo.enums;

/**
 * The SortBy enum represents different sorting criteria used in the Sauce Demo application.
 * It includes AZ, ZA, LOW_TO_HIGH, and HIGH_TO_LOW sorting types.
 */
public enum SortBy {
    AZ("az"),
    ZA("za"),
    LOW_TO_HIGH("lohi"),
    HIGH_TO_LOW("hilo");

    final String value;

    /**
     * Constructs a SortBy enum with the specified value.
     *
     * @param value The value associated with the sorting type.
     */
    SortBy(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
