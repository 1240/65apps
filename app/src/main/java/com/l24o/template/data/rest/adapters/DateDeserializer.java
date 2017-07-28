package com.l24o.template.data.rest.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Alexander Popov on 27/07/2017.
 */

public class DateDeserializer implements JsonDeserializer<Date> {

    private static final Map<String, String> masks = new HashMap<>();

    static {
        masks.put("....-..-..", "yyyy-MM-dd");
        masks.put("..-..-....", "dd-MM-yyyy");
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String value = json.getAsString();
            for (Map.Entry<String, String> entry : masks.entrySet()) {
                if (value.matches(entry.getKey())) {
                    return new SimpleDateFormat(entry.getValue(), Locale.getDefault()).parse(value);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
