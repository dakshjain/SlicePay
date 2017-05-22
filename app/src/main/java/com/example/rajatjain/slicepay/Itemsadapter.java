package com.example.rajatjain.slicepay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class Itemsadapter extends ArrayAdapter<Items> {

    private ArrayList<Items> animalList = new ArrayList<>();
    MainActivity data= new MainActivity();
    public Itemsadapter(Context context, int textViewResourceId, ArrayList<Items> objects) {
        super(context, textViewResourceId, objects);
        animalList = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.row_item, null);
        TextView textView1 = (TextView) v.findViewById(R.id.text1);
        TextView textView2 = (TextView) v.findViewById(R.id.text2);
        textView1.setText(animalList.get(position).getname());
        textView2.setText(animalList.get(position).getphone());
        return v;

    }

}