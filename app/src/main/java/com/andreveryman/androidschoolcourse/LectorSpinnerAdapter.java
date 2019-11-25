package com.andreveryman.androidschoolcourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andrej Russkikh on 21.11.2019.
 */
public class LectorSpinnerAdapter extends BaseAdapter {

    public void setmLectors(List<String> mLectors) {
        this.mLectors = mLectors;
    }

    private List<String> mLectors;

    @Override
    public int getCount() {
        return mLectors == null ? 0 :mLectors.size();
    }

    @Override
    public Object getItem(int position) {
        return mLectors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
           convertView =  LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_spinner_dropdown_item,parent,false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.lector.setText(mLectors.get(position));

        return convertView;
    }


    private static class ViewHolder{
        TextView lector;
        public ViewHolder(View itemView){
            lector = itemView.findViewById(android.R.id.text1);
        }

    }
}
