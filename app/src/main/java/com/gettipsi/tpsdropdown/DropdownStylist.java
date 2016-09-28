package com.gettipsi.tpsdropdown;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

public class DropdownStylist {

    private WeakReference<Adapter> adapter;
    private WeakReference<DropdownContainer> dropdown;
    private DropdownStyle dropdownStyle = null;

    public DropdownStylist(Adapter adapter, DropdownContainer dropdown) {
        this.adapter = new WeakReference<>(adapter);
        this.dropdown = new WeakReference<>(dropdown);
    }

    public void setAdapter(Adapter adapter) {
        this.adapter = new WeakReference<>(adapter);
    }

    public void setStyle(String style) {
        try {
            JSONObject object = new JSONObject(style);
            if (object.has("style")) {
                dropdownStyle = DropdownStyle.fromJson(object.getJSONObject("style"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        applyStyle();
    }

    public void applyStyle() {
        if (dropdownStyle != null) {
            if (adapter.get() != null) {
                adapter.get().setDropdownStyle(dropdownStyle);
            }

            if (dropdown.get() != null) {
                DropdownContainer container = dropdown.get();
                container.setBackground(getBackground(dropdownStyle));
                if (dropdownStyle.getIndicatorImageName() != null) {
                    container.getDropdown().setBackgroundColor(Color.TRANSPARENT);
                    ((ImageView) container.findViewById(R.id.dropdownIcon))
                            .setImageResource(getResourceId(dropdownStyle.getIndicatorImageName()));
                }
            }
        }
    }

    private int getResourceId(String name) {
        if (dropdown.get() != null) {
            int dot = name.lastIndexOf(".");
            String resourceName = dot >= 0 ? name.substring(0, dot) : name;
            Context context = dropdown.get().getContext();
            int id = context.getResources().getIdentifier(resourceName, "drawable",
                    context.getPackageName());
            return id;
        }
        return 0;
    }

    private GradientDrawable getBackground(DropdownStyle dropdownStyle) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(Color.parseColor(dropdownStyle.getBackgroundColor().replace("0x", "#")));
        gradientDrawable.setStroke(dropdownStyle.getBorderWidth(),
                Color.parseColor(dropdownStyle.getBorderColor().replace("0x", "#")));
        gradientDrawable.setCornerRadius(dropdownStyle.getCornerRadius());
        return gradientDrawable;
    }

}
