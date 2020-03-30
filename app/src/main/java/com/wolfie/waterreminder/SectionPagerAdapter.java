package com.wolfie.waterreminder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SectionPagerAdapter extends FragmentPagerAdapter {

    public SectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0: homearea ha= new homearea();
            return  ha;

            case 1: history h = new history();
            return h;

            case 2: setting s = new setting();
            return s;

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    public CharSequence getPageTitle(int Position)
    {
        switch(Position) {
            case 0: return  "Home";
            case 1:
                return "History";

            case 2:
                return "Settings";


            default: return null;
    }
    }
}
