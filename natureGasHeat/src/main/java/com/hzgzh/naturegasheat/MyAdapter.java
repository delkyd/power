package com.hzgzh.naturegasheat;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {


    Context mContext;
    private ArrayList<ValueHolder> mValue;


    private LayoutInflater mInflater;
    private String mUnit = "vol%";

    public MyAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        String[] comp_Name = context.getResources().getStringArray(
                R.array.component);
        mValue = new ArrayList<ValueHolder>(comp_Name.length);
        for (int i = 0; i < comp_Name.length; i++) {
            ValueHolder vh = new ValueHolder();
            vh.name = comp_Name[i];
            mValue.add(vh);
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mValue.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return mValue.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {

            holder = new ViewHolder();

            convertView = mInflater.inflate(R.layout.component, null);
            holder.name = (TextView) convertView
                    .findViewById(R.id.tv_comp_name);
            holder.value = (EditText) convertView
                    .findViewById(R.id.et_comp_value);
            holder.unit = (TextView) convertView
                    .findViewById(R.id.tv_comp_unit);
            holder.name.setTag(position);
            holder.value.setTag(position);
            holder.unit.setTag(position);

            convertView.setTag(holder);

            holder.value.addTextChangedListener(new MyTextWatcher(this, holder,
                    mValue));

        } else {

            holder = (ViewHolder) convertView.getTag();
            holder.value.setTag(position);
            holder.name.setTag(position);
            holder.unit.setTag(position);
        }

        ValueHolder vh = mValue.get(position);
        holder.name.setText(vh.name);
        holder.value.setText(vh.value);
        holder.unit.setText(mUnit);

        ((MainActivity) mContext).total.setText(Double.toString(sumValue()));

        return convertView;
    }

    public ArrayList<ValueHolder> getValue() {
        return mValue;
    }

    public void setUnit(String unit) {
        mUnit = unit;
        notifyDataSetChanged();
    }

    public double sumValue() {
        double temp = 0;
        try {
            for (int i = 0; i < mValue.size(); i++) {
                String str = mValue.get(i).value;
                if (str.equals(""))
                    continue;
                temp += Double.parseDouble(mValue.get(i).value);
            }
        } catch (Exception e) {
            return -1;
        }
        return temp;
    }

    public class ViewHolder {
        TextView name;
        EditText value;
        TextView unit;
    }

    class MyTextWatcher implements TextWatcher {
        ViewHolder mHolder;
        ArrayList<ValueHolder> mValue;
        MyAdapter mAdapter;

        public MyTextWatcher(MyAdapter adapter, ViewHolder holder,
                             ArrayList<ValueHolder> value) {
            mAdapter = adapter;
            mHolder = holder;
            mValue = value;
        }

        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
            int position = (Integer) mHolder.value.getTag();
            ValueHolder vh = mValue.get(position);
            vh.value = mHolder.value.getText().toString();
            mValue.set(position, vh);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            // TODO Auto-generated method stub

        }

    }
}
