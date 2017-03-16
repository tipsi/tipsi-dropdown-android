package com.gettipsi.tpsdropdown;

import com.gettipsi.tpsdropdown.Adapter;
import java.util.List;

public class TipsiAdapter<T> extends Adapter<T> {

    public TipsiAdapter(List<T> objects) {
        super(objects);
    }

    @Override
    public String getLabel(int position) {
        return getItem(position).toString();
    }

}
