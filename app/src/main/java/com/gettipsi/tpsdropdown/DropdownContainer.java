package com.gettipsi.tpsdropdown;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.gettipsi.tpsdropdown.model.Style;

import java.lang.ref.WeakReference;
import java.util.List;

public class DropdownContainer extends FrameLayout {

    private Dropdown dropdown;
    private WeakReference<Adapter> adapter;
    private WeakReference<ImageView> icon;

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

    public ImageView getIcon() {
        return icon != null ? icon.get() : null;
    }

    public <T> void setupWithElements(List<T> values) {
        setupWithAdapter(new TipsiAdapter(values));
    }

    public void setupWithAdapter(Adapter adapter) {
        this.adapter = new WeakReference<>(adapter);
        setAdapter();
        invalidate();
    }

    public void setStyle(Style style) {
        DropdownStylist.getInstance().setStyle(style);
        if (adapter != null && adapter.get() != null) {
            adapter.get().notifyDataSetChanged();
        }
        invalidate();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        if (DropdownStylist.getInstance().isStyled()) {
            Style style = DropdownStylist.getInstance().getStyle();
            setBackground(getBackground(style));
            if (dropdown != null) {
                int indicatorImageResId = style.getIndicatorImageResId();
                String indicatorImageName = style.getIndicatorImageName();
                if (indicatorImageResId > 0 || indicatorImageName != null) {
                    dropdown.setBackgroundColor(Color.TRANSPARENT);
                    if (icon != null && icon.get() != null) {
                        if (indicatorImageResId > 0) {
                            icon.get().setImageResource(indicatorImageResId);
                        } else if (indicatorImageName != null && !indicatorImageName.isEmpty()) {
                            icon.get().setImageResource(getResourceId(indicatorImageName));
                        }
                    }
                }
            }
        }
    }

    private void initDropdown() {
        dropdown = new Dropdown(getContext());
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.START | Gravity.CENTER_VERTICAL);
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
        this.icon = new WeakReference<>(icon);
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
