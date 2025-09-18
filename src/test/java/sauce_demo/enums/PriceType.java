package sauce_demo.enums;

/**
 * The PriceType enum represents different types of prices used in the Sauce Demo application.
 * It includes ITEM_TOTAL, TAX, and TOTAL_PRICE types.
 */
public enum PriceType {
    ITEM_TOTAL("itemTotal"),
    TAX("tax"),
    TOTAL_PRICE("totalPrice");

    final String value;

    /**
     * Constructs a PriceType enum with the specified value.
     *
     * @param value The value associated with the price type.
     */
    PriceType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
