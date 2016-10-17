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

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public void setSeparatorHeight(int separatorHeight) {
        this.separatorHeight = separatorHeight;
    }

    public void setSeparatorColor(String separatorColor) {
        this.separatorColor = separatorColor;
    }

    public void setFontName(String fontName) {
        this.fontName = fontName;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public void setTextAlignment(String textAlignment) {
        this.textAlignment = textAlignment;
    }

    public void setIndicatorImageName(String indicatorImageName) {
        this.indicatorImageName = indicatorImageName;
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
