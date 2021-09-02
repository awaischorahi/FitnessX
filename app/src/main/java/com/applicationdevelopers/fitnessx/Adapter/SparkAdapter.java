package com.applicationdevelopers.fitnessx.Adapter;

import android.content.Context;

public class SparkAdapter extends com.robinhood.spark.SparkAdapter {
    private float[] data;


    Context context;
    public SparkAdapter(Context mcontext,float[] mdata){
        data=mdata;
        context=mcontext;
    }
    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int index) {
        return data[index];
    }

    @Override
    public float getY(int index) {
        return data[index];
    }
}
