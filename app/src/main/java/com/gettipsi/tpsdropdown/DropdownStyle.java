package com.gettipsi.tpsdropdown;

import android.view.Gravity;

import org.json.JSONException;
import org.json.JSONObject;

public class DropdownStyle {

    private String backgroundColor;
    private int borderWidth;
    private String borderColor;
    private int cornerRadius;
    private int separatorHeight;
    private String separatorColor;
    private String fontName;
    private int fontSize;
    private String textColor;
    private String textAlignment;
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
        if (getTextAlignment() != null) {
            if (getTextAlignment().equalsIgnoreCase("center")) {
                return Gravity.CENTER;
            } else if (getTextAlignment().equalsIgnoreCase("right")) {
                return Gravity.RIGHT | Gravity.CENTER_VERTICAL;
            } else {
                return Gravity.LEFT | Gravity.CENTER_VERTICAL;
            }
        } else {
            return Gravity.LEFT | Gravity.CENTER_VERTICAL;
        }
    }

    public static DropdownStyle fromJson(JSONObject styleJson) throws JSONException {
        DropdownStyle style = new DropdownStyle();

        if (styleJson.has("backgroundColor"))
            style.setBackgroundColor(styleJson.getString("backgroundColor"));

        if (styleJson.has("borderWidth"))
            style.setBorderWidth(styleJson.getInt("borderWidth"));

        if (styleJson.has("borderColor"))
            style.setBorderColor(styleJson.getString("borderColor"));

        if (styleJson.has("cornerRadius"))
            style.setCornerRadius(styleJson.getInt("cornerRadius"));

        if (styleJson.has("separatorHeight"))
            style.setSeparatorHeight(styleJson.getInt("separatorHeight"));

        if (styleJson.has("separatorColor"))
            style.setSeparatorColor(styleJson.getString("separatorColor"));

        if (styleJson.has("fontName"))
            style.setFontName(styleJson.getString("fontName"));

        if (styleJson.has("fontSize"))
            style.setFontSize(styleJson.getInt("fontSize"));

        if (styleJson.has("textColor"))
            style.setTextColor(styleJson.getString("textColor"));

        if (styleJson.has("textAlignment"))
            style.setTextAlignment(styleJson.getString("textAlignment"));

        if (styleJson.has("indicatorImageName"))
            style.setIndicatorImageName(styleJson.getString("indicatorImageName"));

        return style;
    }
}
