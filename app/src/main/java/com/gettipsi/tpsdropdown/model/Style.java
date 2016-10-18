package com.gettipsi.tpsdropdown.model;

import android.view.Gravity;

public class Style {
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

    private int indicatorImageResId;

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

    public int getIndicatorImageResId() {
        return indicatorImageResId;
    }

    public void setIndicatorImageResId(int indicatorImageResId) {
        this.indicatorImageResId = indicatorImageResId;
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

    public static final class Builder {
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
        private int indicatorImageResId;

        public Builder withBackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder withBorderWidth(int borderWidth) {
            this.borderWidth = borderWidth;
            return this;
        }

        public Builder withBorderColor(String borderColor) {
            this.borderColor = borderColor;
            return this;
        }

        public Builder withCornerRadius(int cornerRadius) {
            this.cornerRadius = cornerRadius;
            return this;
        }

        public Builder withSeparatorHeight(int separatorHeight) {
            this.separatorHeight = separatorHeight;
            return this;
        }

        public Builder withSeparatorColor(String separatorColor) {
            this.separatorColor = separatorColor;
            return this;
        }

        public Builder withFontName(String fontName) {
            this.fontName = fontName;
            return this;
        }

        public Builder withFontSize(int fontSize) {
            this.fontSize = fontSize;
            return this;
        }

        public Builder withTextColor(String textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder withTextAlignment(String textAlignment) {
            this.textAlignment = textAlignment;
            return this;
        }

        public Builder withIndicatorImageName(String indicatorImageName) {
            this.indicatorImageName = indicatorImageName;
            return this;
        }

        public Builder withIndicatorImageResId(int indicatorImageName) {
            this.indicatorImageResId = indicatorImageName;
            return this;
        }

        public Style build() {
            Style style = new Style();
            style.setBackgroundColor(backgroundColor);
            style.setBorderWidth(borderWidth);
            style.setBorderColor(borderColor);
            style.setCornerRadius(cornerRadius);
            style.setSeparatorHeight(separatorHeight);
            style.setSeparatorColor(separatorColor);
            style.setFontName(fontName);
            style.setFontSize(fontSize);
            style.setTextColor(textColor);
            style.setTextAlignment(textAlignment);
            style.setIndicatorImageName(indicatorImageName);
            style.setIndicatorImageResId(indicatorImageResId);
            return style;
        }
    }
}
