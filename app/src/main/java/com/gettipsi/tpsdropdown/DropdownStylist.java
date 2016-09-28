package com.gettipsi.tpsdropdown;

import com.gettipsi.tpsdropdown.model.DropdownStyle;
import com.gettipsi.tpsdropdown.model.Style;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class DropdownStylist {

    private static volatile DropdownStylist instance;
    private static final Object LOCK = new Object();

    private DropdownStyle dropdownStyle = null;

    private DropdownStylist() {
    }

    public static DropdownStylist getInstance() {
        if (instance == null ) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new DropdownStylist();
                }
            }
        }
        return instance;
    }

    public void parseStyle(String style) {
        Gson gson = new GsonBuilder().create();
        dropdownStyle = gson.fromJson(style, new TypeToken<DropdownStyle>() {}.getType());
    }

    public Style getDropdownStyle() {
        return dropdownStyle.getStyle();
    }
}
