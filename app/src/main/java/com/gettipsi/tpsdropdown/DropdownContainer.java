package com.gettipsi.tpsdropdown;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gettipsi.tpsdropdown.model.DropdownStyle;
import com.gettipsi.tpsdropdown.model.Style;

import java.lang.ref.WeakReference;
import java.util.List;

public class DropdownContainer extends FrameLayout {

    private Dropdown dropdown;
    private WeakReference<Adapter> adapter;

    public DropdownContainer(Context context) {
        super(context);
        initDropdown();
    }

    public DropdownContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDropdown();
    }

    public DropdownContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDropdown();
    }

    public Dropdown getDropdown() {
        return dropdown;
    }

    public <T> void setupWithElements(List<T> values) {
        adapter = new WeakReference<Adapter>(new Adapter<>(getContext(), android.R.layout.simple_spinner_item, values));
        adapter.get().setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setAdapter();
        invalidate();
    }

    public void setStyle(String style) {
        DropdownStylist.getInstance().parseStyle(style);
        if (adapter.get() != null) {
            adapter.get().notifyDataSetChanged();
        }
        invalidate();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        DropdownStyle dropdownStyle = DropdownStylist.getInstance().getDropdownStyle();
        if (dropdownStyle != null && dropdownStyle.getStyle() != null) {
            setBackground(getBackground(DropdownStylist.getInstance().getStyle()));
            if (dropdown != null) {
                dropdown.setBackgroundColor(Color.TRANSPARENT);
                ((ImageView) findViewById(R.id.dropdownIcon)).setImageResource(
                        getResourceId(DropdownStylist.getInstance().getStyle().getIndicatorImageName()));
            }
        }
    }

    private void initDropdown() {
        dropdown = new Dropdown(getContext());
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.START);
        dropdown.setId(R.id.dropdownId);
        dropdown.setLayoutParams(params);
        addView(dropdown);
        addView(getIconView());
    }

    private ImageView getIconView() {
        ImageView icon = new ImageView(getContext());
        icon.setId(R.id.dropdownIcon);
        FrameLayout.LayoutParams params = new LayoutParams(getResources().getDimensionPixelSize(R.dimen.default_icon_size),
                getResources().getDimensionPixelSize(R.dimen.default_icon_size));
        params.gravity = Gravity.END | Gravity.CENTER_VERTICAL;
        params.setMargins(0, getResources().getDimensionPixelSize(R.dimen.item_padding),
                0, getResources().getDimensionPixelSize(R.dimen.item_padding));
        icon.setLayoutParams(params);
        icon.setClickable(false);
        return icon;
    }

    private void setAdapter() {
        try {
            getDropdown().setAdapter(adapter.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
        post(new Runnable() {
            @Override
            public void run() {
                getDropdown().setAdapter(adapter.get());
                getDropdown().setSelection(0);
            }
        });
    }

    private int getResourceId(String name) {
        int dot = name.lastIndexOf(".");
        String resourceName = dot >= 0 ? name.substring(0, dot) : name;
        return getContext().getResources().getIdentifier(resourceName, "drawable",
                getContext().getPackageName());
    }

    private GradientDrawable getBackground(Style dropdownStyle) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        if (dropdownStyle.getBackgroundColor() != null) {
            gradientDrawable.setColor(Color.parseColor(dropdownStyle.getBackgroundColor().replace("0x", "#")));
        }
        if (dropdownStyle.getBorderWidth() > 0 && dropdownStyle.getBorderColor() != null) {
            gradientDrawable.setStroke(dropdownStyle.getBorderWidth(),
                    Color.parseColor(dropdownStyle.getBorderColor().replace("0x", "#")));
        }
        if (dropdownStyle.getCornerRadius() > 0) {
            gradientDrawable.setCornerRadius(dropdownStyle.getCornerRadius());
        }
        return gradientDrawable;
    }
}
