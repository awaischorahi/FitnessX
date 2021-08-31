package com.applicationdevelopers.fitnessx.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.applicationdevelopers.fitnessx.Views.Onboarding.EatWell;
import com.applicationdevelopers.fitnessx.Views.Onboarding.GetBurn;
import com.applicationdevelopers.fitnessx.Views.Onboarding.SleepWell;
import com.applicationdevelopers.fitnessx.Views.Onboarding.TrackGoal;

public class OnboardingAdapter extends FragmentStatePagerAdapter {
    int numb;
    public OnboardingAdapter(FragmentManager fm, int fragmentnum) {
        super(fm);
        numb=fragmentnum;
    }

    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0:
               TrackGoal trackGoal =new TrackGoal();
               return trackGoal;
           case 1:
               GetBurn burn=new GetBurn();
               return burn;
           case 2:
               EatWell eatWell=new EatWell();
               return eatWell;
           case 3:
               SleepWell sleepWell=new SleepWell();
               return sleepWell;
           default:
               return null;
       }
    }

    @Override
    public int getCount() {
        return numb;
    }
}
