package com.gettipsi.tpsdropdown;

import com.gettipsi.tpsdropdown.model.Style;

public class DropdownStylist {

    private static volatile DropdownStylist instance;
    private static final Object LOCK = new Object();

    private Style dropdownStyle = null;

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

    public void setStyle(Style style) {
        this.dropdownStyle = style;
    }

    public boolean isStyled() {
        return getStyle() != null;
    }

    public Style getStyle() {
        return dropdownStyle;
    }
}
