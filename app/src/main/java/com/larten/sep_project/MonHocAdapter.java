package com.larten.sep_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MonHocAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<MonHoc> monhoclist;

    public MonHocAdapter(classActivity classActivity, int activity_item_mon_hoc, ArrayList<MonHoc> arrayMonhoc) {
    }


    @Override
    public int getCount() {
        return monhoclist.size();
    }
    private class ViewHolder{
        TextView txtid, txtname;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.txtid = (TextView) view.findViewById(R.id.textViewMaMonHoc);
            holder.txtname = (TextView) view.findViewById(R.id.textViewTenMonHoc);
            view.setTag(holder);


        }
        MonHoc monHoc = monhoclist.get(i);
        holder.txtid.setText(monHoc.getId());
        holder.txtname.setText(monHoc.getName());

        return view;
    }
}
