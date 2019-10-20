package org.fruitsalad.greenplanet.ui.achievements.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.fruitsalad.ui.achievements.nestedfragments.AchievementFragment;
import org.fruitsalad.ui.achievements.nestedfragments.LeaderBoardFragment;

public class AchievementsPagerAdapter extends FragmentPagerAdapter {

    private int tabCount;

    public AchievementsPagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new AchievementFragment();
            case 1:
                return new LeaderBoardFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
