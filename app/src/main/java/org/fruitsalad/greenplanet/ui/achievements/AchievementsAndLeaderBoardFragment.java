package org.fruitsalad.greenplanet.ui.achievements;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import org.fruitsalad.greenplanet.R;
import org.fruitsalad.greenplanet.ui.achievements.adapters.AchievementsPagerAdapter;

public class AchievementsAndLeaderBoardFragment extends Fragment {

    private ViewPager viewPager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_achievements_leaderboard, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setTitle("");

        final TabLayout tabLayout = root.findViewById(R.id.tabs_achievements_leaderboard);
        viewPager = root.findViewById(R.id.pager_achievement_leaderboard);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.colorAccent));

        AchievementsPagerAdapter pager =
                new AchievementsPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pager);
        tabLayout.setupWithViewPager(this.viewPager);
        tabLayout.getTabAt(0).setText("Achievements");
        tabLayout.getTabAt(1).setText("Leader Board");

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tabLayout.getTabAt(tab.getPosition()).select();
                tabLayout.setScrollPosition(tab.getPosition(), 0f, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return root;
    }
}