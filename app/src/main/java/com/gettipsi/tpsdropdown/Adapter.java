package com.gettipsi.tpsdropdown;

import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gettipsi.tpsdropdown.model.Style;

import java.util.List;

public abstract class Adapter<T> extends BaseAdapter {

    private Style dropdownStyle;
    private List<T> objects;

    public Adapter(List<T> objects) {
        this.objects = objects;
    }

    public abstract String getLabel(int position);

    @Override
    public void notifyDataSetChanged() {
        dropdownStyle = DropdownStylist.getInstance().getStyle();
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent, false);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getItemView(position, convertView, parent, true);
    }

    @Override
    public int getCount() {
        return objects == null ? 0 : objects.size();
    }

    @Override
    public T getItem(int i) {
        return objects != null ? objects.get(i) : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private View getItemView(int position, View convertView, ViewGroup parent, boolean showDivider) {
        View view = createView(R.layout.dropdown_line_item, convertView, parent);
        invalidateItem(position, (ViewHolderItem) view.getTag(), showDivider);
        return view;
    }

    private void invalidateItem(int position, ViewHolderItem viewHolder, boolean showDivider) {
        if (dropdownStyle != null) {
            viewHolder.applyStyle(dropdownStyle);
        }
        viewHolder.divider.setVisibility(showDivider && getCount() - 1 != position ? View.VISIBLE : View.GONE);
        String label = getLabel(position);
        viewHolder.text.setText(label == null ? "" : label);
    }

    private View createView(int resourceId, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(resourceId, parent, false);
            ViewHolderItem holder = new ViewHolderItem(convertView);
            convertView.setTag(holder);
        }
        return convertView;
    }

    private class ViewHolderItem {
        TextView text;
        View divider;

        public ViewHolderItem(View view) {
            text = (TextView) view.findViewById(R.id.dropdownTextItemId);
            divider = view.findViewById(R.id.divider);
        }

        public void applyStyle(Style dropdownStyle) {
            text.setGravity(dropdownStyle.getGravity());
            if (dropdownStyle.getTextColor() != null) {
                text.setTextColor(Color.parseColor(dropdownStyle.getTextColor().replace("0x", "#")));
            }
            if (dropdownStyle.getFontSize() > 0) {
                text.setTextSize(TypedValue.COMPLEX_UNIT_SP, dropdownStyle.getFontSize());
            }
            if (dropdownStyle.getSeparatorColor() != null && dropdownStyle.getSeparatorHeight() > 0) {
                divider.setBackgroundColor(Color.parseColor(dropdownStyle.getSeparatorColor().replace("0x", "#")));
                ViewGroup.LayoutParams params = divider.getLayoutParams();
                params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                        dropdownStyle.getSeparatorHeight(),
                        text.getContext().getResources().getDisplayMetrics());
                divider.setLayoutParams(params);
            }
        }
    }
}