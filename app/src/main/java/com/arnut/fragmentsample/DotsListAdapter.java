package com.arnut.fragmentsample;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by arnut on 5/14/2015 AD.
 */
abstract public class DotsListAdapter extends BaseAdapter implements ListAdapter {

    private static final class ViewHolder{
        TextView txtCoordX;
        TextView txtCoordY;
    }

    private Context mContext;

    public DotsListAdapter(Context context){
        mContext = context;
    }


    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            //create
            viewHolder = new ViewHolder();
            convertView =  LayoutInflater.from(mContext).inflate(R.layout.dotsrow,parent,false);
            viewHolder.txtCoordX = (TextView)convertView.findViewById(R.id.txtCoordX);
            viewHolder.txtCoordY = (TextView)convertView.findViewById(R.id.txtCoordY);
            convertView.setTag(viewHolder);
        }else {
            //reuse
            viewHolder = (ViewHolder)convertView.getTag();

        }

        //update row
        Dot dot = (Dot)getItem(position);
        viewHolder.txtCoordX.setText(String.valueOf(dot.getCoordX()));
        viewHolder.txtCoordY.setText(String.valueOf(dot.getCoordY()));

        //set color
        viewHolder.txtCoordX.setTextColor(dot.getColor().getValue());

        //set font size
        viewHolder.txtCoordX.setTextSize(TypedValue.COMPLEX_UNIT_SP,dot.getSize());


        return convertView;
    }
}
