package sauce_demo.enums;

/**
 * The Browser enum represents different types of browsers used in the Sauce Demo application.
 * It includes CHROME, EDGE and FIREFOX types.
 */
public enum Browser {
    CHROME,
    EDGE,
    FIREFOX;

    /**
     * Converts the provided string value to a Browser enum constant.
     * If the provided value is null or empty, it defaults to 'chrome'.
     * If the provided value matches an existing Browser enum constant, it returns the corresponding enum constant.
     * If the provided value does not match any existing enum constant, it returns Browser.CHROME as the default.
     *
     * @param value The string value representing the desired browser.
     * @return The Browser enum constant corresponding to the provided value or Browser.CHROME if the value is not recognized.
     */
    public static Browser customValueOf(String value) {
        String browserName = (value == null || value.isEmpty()) ? "chrome" : value;
        try {
            return Browser.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CHROME;
        }
    }
}
