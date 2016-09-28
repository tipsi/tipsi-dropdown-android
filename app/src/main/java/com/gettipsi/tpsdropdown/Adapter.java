package com.gettipsi.tpsdropdown;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gettipsi.tpsdropdown.model.Style;

import java.util.List;

public class Adapter<T> extends ArrayAdapter<T> {

    private Style dropdownStyle;

    public Adapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public void notifyDataSetChanged() {
        dropdownStyle = DropdownStylist.getInstance().getDropdownStyle();
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

    private View getItemView(int position, View convertView, ViewGroup parent, boolean showDivider) {
        View view = createView(R.layout.dropdown_line_item, convertView, parent);
        invalidateItem(position, (ViewHolderItem) view.getTag(), showDivider);
        return view;
    }

    private void invalidateItem(int position, ViewHolderItem viewHolder, boolean showDivider) {
        if (dropdownStyle != null) {
            viewHolder.applyStyle(dropdownStyle);
        }
        viewHolder.divider.setVisibility(showDivider ? View.VISIBLE : View.GONE);
        viewHolder.text.setText(getItem(position).toString());
    }

    private View createView(int resourceId, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
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
            text.setTextColor(Color.parseColor(dropdownStyle.getTextColor().replace("0x", "#")));
            text.setTextSize(TypedValue.COMPLEX_UNIT_SP, dropdownStyle.getFontSize());
            text.setGravity(dropdownStyle.getGravity());
            divider.setBackgroundColor(Color.parseColor(dropdownStyle.getSeparatorColor().replace("0x", "#")));
            ViewGroup.LayoutParams params = divider.getLayoutParams();
            params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dropdownStyle.getSeparatorHeight(),
                    getContext().getResources().getDisplayMetrics());
            divider.setLayoutParams(params);
        }
    }
}