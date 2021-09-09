package com.applicationdevelopers.fitnessx.SharedPreferenceClass;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class OnboardingSharedPreference {
    boolean isRun=false;
    Context mcontext;
    private final SharedPreferences sharedPreferences;

    public OnboardingSharedPreference(Context context){
         mcontext=context;
        sharedPreferences = mcontext.getSharedPreferences("Onboadring",MODE_PRIVATE);

    }
    public void checkSharedPreferenceOnboarding(boolean value){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean("isalreadyRun",value);
        editor.apply();
    }
    public boolean getcheckSharedPreferenceOnboarding(){
        return sharedPreferences.getBoolean("isalreadyRun",false);
    }
}
