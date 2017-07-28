package com.l24o.template.common.utils;

import android.support.annotation.StringRes;

import com.l24o.template.Constants;
import com.l24o.template.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class AgeUtils {

    @StringRes
    public static int getAgeEnds(int age) {
        int lastDigit = age % 10;
        if ((age < 10 || age > 20) && lastDigit > 0 && lastDigit < 5) {
            return R.string.age_1;
        } else {
            return R.string.age_2;
        }
    }

    public static String formatDate(Date date) {
        return new SimpleDateFormat(Constants.UI_DATE_FORMAT, Locale.getDefault()).format(date);
    }

}
