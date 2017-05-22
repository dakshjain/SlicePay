package com.example.rajatjain.slicepay;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import java.util.ArrayList;

public class FragmentB extends android.support.v4.app.Fragment {
    private Myadapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        adapter = new Myadapter(getContext(), R.layout.grid, getdata());
        gridview.setAdapter(adapter);
        return view;
    }

    private ArrayList getdata() {
        final ArrayList imageItems = new ArrayList();

        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),
                    imgs.getResourceId(i, -1));

            imageItems.add(new item(bitmap));
        }

        return imageItems;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
