package com.larten.sep_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ListAdapterAct extends ArrayAdapter<MonHoc> {

    public ListAdapterAct(Context context, int resource, List<MonHoc> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.activity_item_mon_hoc, null);
        }
        MonHoc p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            TextView txt = (TextView) view.findViewById(R.id.textViewTenMonHoc);
            txt.setText(p.Name);
            TextView txt1 = (TextView) view.findViewById(R.id.textViewMaMonHoc);
            txt1.setText(String.valueOf(p.id));


        }
        return view;
    }

}