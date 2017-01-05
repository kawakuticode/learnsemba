package com.kawakuticode.learnsemba;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;

/**
 * Created by Russelius on 02/02/15.
 */
public class TabsPageAdapter  extends FragmentPagerAdapter {

    public TabsPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Beginners activity
                return new BeginnersLevel();
            case 1:
                // Intermediate fragment activity
                return new IntermediaLevel();
            case 2:
                //Confirmed  fragment activity
                return new ConfirmedLevel();

            case 3:
                // Video fragment activity
                return new VideoFragment();

            case 4:
                // Music fragment activity
                return new MusicPlayFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }

}
