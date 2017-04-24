package com.fcchyd.linkletandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.fcchyd.linkletandroid.R;
import com.fcchyd.linkletandroid.Type.LinkDataType;

import java.util.ArrayList;

public class Custom1Adapter extends ArrayAdapter<LinkDataType> {

    private final Context context;
    private ArrayList<LinkDataType> arrayList;

    public Custom1Adapter(@NonNull Context context, ArrayList<LinkDataType> arrayList) {
        super(context, 0, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = LayoutInflater.from(getContext()).inflate(R.layout.link_view, parent, false);

        TextView title = (TextView) row.findViewById(R.id.title_row_xml);
        TextView desc = (TextView) row.findViewById(R.id.desc_row_xml);

        if (arrayList.get(position).getTitle() != null) {
            title.setText(arrayList.get(position).getTitle());
        } else {
            title.setText("No title");
        }

        if (arrayList.get(position).getDescription() != null) {
            desc.setText(arrayList.get(position).getDescription());
        } else {
            desc.setText("No description Available");
        }
        return row;
    }
}
