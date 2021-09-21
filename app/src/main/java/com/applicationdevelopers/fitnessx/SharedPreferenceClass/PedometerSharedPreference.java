package com.applicationdevelopers.fitnessx.SharedPreferenceClass;

import android.content.Context;
import android.content.SharedPreferences;

public class PedometerSharedPreference {
    Context context;
    private final SharedPreferences sharedPreferences;

    public PedometerSharedPreference(Context mcontext) {
        context = mcontext;

        sharedPreferences = context.getSharedPreferences("pedometer", Context.MODE_PRIVATE);
    }

    public void setValueToPedometer(int value) {
        SharedPreferences.Editor editora;
        editora = sharedPreferences.edit();
        editora.putInt("setPedometer", 0);
        editora.commit();

    }
    public int getValueinTextView(){
        return sharedPreferences.getInt("setPedometer",0);
    }

}
