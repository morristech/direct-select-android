package com.ramotion.directselect;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Default implementation of ArrayAdapter for DSPickerView
 */
public class DSDefaultAdapter extends ArrayAdapter<String> {

    private ArrayList<String> items;
    private Context context;

    private int cellHeight;
    private int cellTextSize;
    private DSDefaultPickerBox dsPickerBoxDefault;

    DSDefaultAdapter(Context context, int textViewResourceId, ArrayList<String> stringItems, Integer cellHeight, Integer cellTextSize) {
        super(context, textViewResourceId, stringItems);
        this.items = stringItems;
        this.context = context;
        this.cellHeight = cellHeight;
        this.cellTextSize = cellTextSize;
    }

    // Bad solution, but it works.
    void setDsPickerBoxDefault(DSDefaultPickerBox dsPickerBoxDefault) {
        this.dsPickerBoxDefault = dsPickerBoxDefault;
    }

    void setCellHeight(Integer cellHeight) {
        this.cellHeight = cellHeight;
    }

    void setCellTextSize(Integer cellTextSize) {
        this.cellTextSize = cellTextSize;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private class ViewHolder {
        TextView text;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (null == convertView) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert vi != null;
            convertView = vi.inflate(R.layout.ds_default_list_item, null);
            holder = new ViewHolder();
            holder.text = convertView.findViewById(R.id.ds_default_cell_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (null != holder) {
            holder.text.setText("" + items.get(position));

            if (cellHeight > 0)
                convertView.setMinimumHeight(cellHeight);

            if (cellTextSize > 0)
                holder.text.setTextSize(cellTextSize);

            if (null != this.dsPickerBoxDefault)
                convertView.setPadding(dsPickerBoxDefault.getPaddingLeft(), dsPickerBoxDefault.getPaddingTop(),
                        dsPickerBoxDefault.getPaddingRight(), dsPickerBoxDefault.getPaddingBottom());

        }
        return convertView;
    }
}