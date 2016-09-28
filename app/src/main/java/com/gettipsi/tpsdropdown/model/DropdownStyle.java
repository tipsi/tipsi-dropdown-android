package com.gettipsi.tpsdropdown.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DropdownStyle {

    @SerializedName("style")
    @Expose
    private Style style;

    public Style getStyle() {
        return style;
    }
}
