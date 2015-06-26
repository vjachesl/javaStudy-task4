package com.chichin.cityTransport.control.Util;

import com.chichin.cityTransport.entity.TransportTypes;
import org.apache.log4j.Logger;

/**
 * Class with utility methods for checking input values send from the user.
 */
public class InputValidation {
    private static final Logger LOG = Logger.getLogger(InputValidation.class);

    private static final String positiveDecimalNumberRegEx = "\\d+";
    private static final String filledRegex = "^\\p{L}[\\p{L}\\s]*\\p{L}$";
    private static final String isLatinWord = "[a-zA-Z ]+";
    private static final String isCyrillicWord = "[а-яА-Я ]+";


    private static <T> boolean checkNull(
            @SuppressWarnings("unchecked") T... values) {
        if (values == null) {
            LOG.debug("checkNull  - was inputed null value");
            return true;
        } else {
            for (T value : values) {
                if (value == null) {
                    LOG.debug("checkNull  - was inputed null value");
                    return true;
                }
            }
            LOG.debug("checkNull  - was inputed not null value");
            return false;
        }
    }

    public static boolean isFilled(String... values) {

        if (checkNull(values)) {
            return false;
        }

        for (String value : values) {
            System.out.println(value + value.matches(filledRegex));
            if (!value.matches(filledRegex)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCyrillicWord(String... values) {

        if (checkNull(values)) {
            return false;
        }

        for (String value : values) {
            if (!value.matches(isCyrillicWord)) {
                LOG.debug("isCyrillicWord  - was not cyrillic");
                return false;
            }
        }
        LOG.debug("isCyrillicWord  - wascyrillic");
        return true;
    }

    public static boolean isLatinWord(String... values) {

        if (checkNull(values)) {
            return false;
        }

        for (String value : values) {
            if (!value.matches(isLatinWord)) {
                LOG.debug("isLatin  - was not latin");
                return false;
            }
        }
        LOG.debug("isLatin  - was latin");
        return true;
    }

    public static boolean isPositiveDecimalNumber(String... values) {

        if (checkNull(values)) {
            return false;
        }

        for (String value : values) {
            if (!value.matches(positiveDecimalNumberRegEx)) {
                LOG.debug("isPositiveDecimalNumber  - was not number");
                return false;
            }
        }
        LOG.debug("isPositiveDecimalNumber  - was number");
        return true;
    }

    public static TransportTypes isTransportType(String value) {
        if (checkNull(value)) {
            LOG.debug("isTransportType  - was not transport type");
            return null;
        }

        for (TransportTypes t : TransportTypes.values()) {
            if (t.getNameEn().equalsIgnoreCase(value)) {
                LOG.debug("isTransportType  - was transport type");
                return t;
            }
        }
        LOG.debug("isTransportType  - was not transport type");
        return null;
    }

}
