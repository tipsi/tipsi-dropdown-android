package com.gettipsi.tpsdropdown.model;

import android.view.Gravity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Style {
    @SerializedName("backgroundColor")
    @Expose
    private String backgroundColor;
    @SerializedName("borderWidth")
    @Expose
    private int borderWidth;
    @SerializedName("borderColor")
    @Expose
    private String borderColor;
    @SerializedName("cornerRadius")
    @Expose
    private int cornerRadius;
    @SerializedName("separatorHeight")
    @Expose
    private int separatorHeight;
    @SerializedName("separatorColor")
    @Expose
    private String separatorColor;
    @SerializedName("fontName")
    @Expose
    private String fontName;
    @SerializedName("fontSize")
    @Expose
    private int fontSize;
    @SerializedName("textColor")
    @Expose
    private String textColor;
    @SerializedName("textAlignment")
    @Expose
    private String textAlignment;
    @SerializedName("indicatorImageName")
    @Expose
    private String indicatorImageName;

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public String getBorderColor() {
        return borderColor;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public int getSeparatorHeight() {
        return separatorHeight;
    }

    public String getSeparatorColor() {
        return separatorColor;
    }

    public String getFontName() {
        return fontName;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getTextColor() {
        return textColor;
    }

    public String getTextAlignment() {
        return textAlignment;
    }

    public String getIndicatorImageName() {
        return indicatorImageName;
    }

    public int getGravity() {

        if (textAlignment != null) {
            if (textAlignment.equalsIgnoreCase("Right")) {
                return Gravity.END | Gravity.CENTER_VERTICAL;
            } else if (textAlignment.equalsIgnoreCase("Center")) {
                return Gravity.CENTER;
            }
        }

        return Gravity.START | Gravity.CENTER_VERTICAL;
    }
}
