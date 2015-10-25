package com.creatingev.fibonacciactivity.activity;

/**
 * ListView Adapter
 *
 * @author hebaotong
 * @version 1.0.0
 * @date 2015.10.25
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.creatingev.fibonacciactivity.R;
import com.creatingev.fibonacciactivity.service.Fibonacci;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Fibonacci> mNumberList = null;

    public ListViewAdapter(Context context, List<Fibonacci> numberList) {
        mContext = context;
        mNumberList = numberList;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mNumberList.size();
    }

    @Override
    public Fibonacci getItem(int position) {
        return mNumberList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.listview_item, null);
            holder.string = (TextView) convertView.findViewById(R.id.string);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.string.setText(mNumberList.get(position).getFibonacci());

        return convertView;
    }

    public class ViewHolder {
        TextView string;
    }
}
