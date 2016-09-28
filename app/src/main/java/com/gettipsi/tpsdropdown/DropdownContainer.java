package com.gettipsi.tpsdropdown;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.List;

public class DropdownContainer extends FrameLayout {

    private Dropdown dropdown;
    private DropdownStylist dropdownStylist;
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

    public <T> void setupWithElements(List<T> values) {
        adapter = new WeakReference<Adapter>(new Adapter<>(getContext(), android.R.layout.simple_spinner_item, values));
        adapter.get().setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownStylist.setAdapter(adapter.get());
        dropdownStylist.applyStyle();
        setAdapter();
    }

    public void setStyle(String style) {
        dropdownStylist.setAdapter(adapter != null ? adapter.get() : null);
        dropdownStylist.setStyle(style);
        dropdownStylist.applyStyle();
        invalidate();
    }

    public Dropdown getDropdown() {
        return dropdown;
    }

    private void initDropdown() {
        dropdown = new Dropdown(getContext());
        ViewGroup.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.START);
        dropdown.setId(R.id.dropdownId);
        dropdown.setLayoutParams(params);
        dropdownStylist = new DropdownStylist(null, this);
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
}
