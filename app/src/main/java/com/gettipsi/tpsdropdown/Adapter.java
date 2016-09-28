package com.gettipsi.tpsdropdown;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter<T> extends ArrayAdapter<T> {

    private DropdownStyle dropdownStyle;

    public Adapter(Context context, int resource, List<T> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dropdown_line, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (dropdownStyle != null) {
            holder.text.setTextColor(Color.parseColor(dropdownStyle.getTextColor().replace("0x", "#")));
            holder.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, dropdownStyle.getFontSize());
            holder.text.setGravity(dropdownStyle.getGravity());
        }
        holder.text.setText(getItem(position).toString());
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolderItem holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.dropdown_line_item, parent, false);
            holder = new ViewHolderItem(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolderItem) convertView.getTag();
        }
        if (dropdownStyle != null) {
            holder.text.setTextColor(Color.parseColor(dropdownStyle.getTextColor().replace("0x", "#")));
            holder.text.setTextSize(TypedValue.COMPLEX_UNIT_SP, dropdownStyle.getFontSize());
            holder.text.setGravity(dropdownStyle.getGravity());
            holder.divider.setBackgroundColor(Color.parseColor(dropdownStyle.getSeparatorColor().replace("0x", "#")));
            ViewGroup.LayoutParams params = holder.divider.getLayoutParams();
            params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    dropdownStyle.getSeparatorHeight(),
                    getContext().getResources().getDisplayMetrics());
            holder.divider.setLayoutParams(params);
        }
        holder.text.setText(getItem(position).toString());
        return convertView;
    }

    public void setDropdownStyle(DropdownStyle dropdownStyle) {
        this.dropdownStyle = dropdownStyle;
        notifyDataSetChanged();
    }

    private class ViewHolder {
        TextView text;

        public ViewHolder(View view) {
            text = (TextView) view.findViewById(R.id.dropdownTextItemId);
        }
    }

    private class ViewHolderItem {
        TextView text;
        View divider;

        public ViewHolderItem(View view) {
            text = (TextView) view.findViewById(R.id.dropdownTextItemId);
            divider = view.findViewById(R.id.divider);
        }
    }
}