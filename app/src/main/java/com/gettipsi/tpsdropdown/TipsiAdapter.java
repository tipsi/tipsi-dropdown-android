package com.gettipsi.tpsdropdown;

<<<<<<< 69fee0bdf407ef93d86e1f47754a21a415fcfce3
=======
import com.gettipsi.tpsdropdown.Adapter;

>>>>>>> Added setters to styling. Created base adapter for dropdown. Added checks for null
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
