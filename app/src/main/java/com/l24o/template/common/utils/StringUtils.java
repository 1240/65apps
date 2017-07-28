package com.l24o.template.common.utils;

import android.text.TextUtils;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class StringUtils {

    private static final String EMPTY_STRING = "";

    public static String toUppercaseFirstLetter(String value) {
        if (!TextUtils.isEmpty(value)) {
            return value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
        } else if (value.length() == 1) {
            return value.toUpperCase();
        }
        return EMPTY_STRING;
    }

}
