package com.example.drugtracker;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);

        mViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        //link the tabs with pager
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        // tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //hiding the tab bottom indicator
        mTabLayout.setSelectedTabIndicatorColor(000);

        //setting up icons
        mTabLayout.getTabAt(0).setIcon(R.drawable.pill_box_tab);
        mTabLayout.getTabAt(1).setIcon(R.drawable.encyclopedia_tab);
        mTabLayout.getTabAt(2).setIcon(R.drawable.graph_info_tab);
        mTabLayout.getTabAt(3).setIcon(R.drawable.drug_store_location);
        mTabLayout.getTabAt(4).setIcon(R.drawable.user_tab);


        //when a tab is selected the small icon will be changed to bigger one
        //which is in a custom layout with title
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            TextView tabTitle;
            TextView tabTitle1;
            TextView tabTitle2;
            TextView tabTitle3;
            TextView tabTitle4;

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        tab.setCustomView(null);
                        View customView =
                                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
                        ImageView tabIcon = (ImageView) customView.findViewById(R.id.icon_img);
                        tabTitle = (TextView) customView.findViewById(R.id.title_tv);
                        tabTitle.setText(getString(R.string.title_pill_box));
                        tabIcon.setImageResource(R.drawable.pill_box_selected_tab);
                        tab.setCustomView(customView);
                        break;
                    case 1:
                        tab.setCustomView(null);
                        View customView1 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
                        ImageView tabIcon1 = (ImageView) customView1.findViewById(R.id.icon_img);
                        tabTitle1 = (TextView) customView1.findViewById(R.id.title_tv);
                        tabTitle1.setText(getString(R.string.title_encyclopedia));
                        tabIcon1.setImageResource(R.drawable.encyclopedia_selected_tab);
                        tab.setCustomView(customView1);
                        break;
                    case 2:
                        tab.setCustomView(null);
                        View customView2 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
                        ImageView tabIcon2 = (ImageView) customView2.findViewById(R.id.icon_img);
                        tabTitle2 = (TextView) customView2.findViewById(R.id.title_tv);
                        tabTitle2.setText(getString(R.string.title_graph_info));
                        tabIcon2.setImageResource(R.drawable.grapg_info_selected_tab);
                        tab.setCustomView(customView2);
                        break;
                    case 3:
                        tab.setCustomView(null);
                        View customView3 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
                        ImageView tabIcon3 = (ImageView) customView3.findViewById(R.id.icon_img);
                        tabTitle3 = (TextView) customView3.findViewById(R.id.title_tv);
                        tabTitle3.setText(getString(R.string.title_drugstore));
                        tabIcon3.setImageResource(R.drawable.drug_store_selected_tab);
                        tab.setCustomView(customView3);
                        break;
                    case 4:
                        tab.setCustomView(null);
                        View customView4 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
                        ImageView tabIcon4 = (ImageView) customView4.findViewById(R.id.icon_img);
                        tabTitle4 = (TextView) customView4.findViewById(R.id.title_tv);
                        tabTitle4.setText(getString(R.string.title_user));
                        tabIcon4.setImageResource(R.drawable.user_tab);
                        tab.setCustomView(customView4);
                        break;
                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

                switch (tab.getPosition()) {
                    case 0:
                        tab.setCustomView(null);
                        View customView5 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.small_custom_tab, null);
                        ImageView tabIcon5 = (ImageView) customView5.findViewById(R.id.small_icon_img);
                        tabIcon5.setImageResource(R.drawable.pill_box_tab);
                        tab.setCustomView(customView5);
                        break;
                    case 1:
                        tab.setCustomView(null);
                        View customView6 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.small_custom_tab, null);
                        ImageView tabIcon6 = (ImageView) customView6.findViewById(R.id.small_icon_img);
                        tabIcon6.setImageResource(R.drawable.encyclopedia_tab);
                        tab.setCustomView(customView6);
                        break;
                    case 2:
                        tab.setCustomView(null);
                        View customView7 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.small_custom_tab, null);
                        ImageView tabIcon7 = (ImageView) customView7.findViewById(R.id.small_icon_img);
                        tabIcon7.setImageResource(R.drawable.graph_info_tab);
                        tab.setCustomView(customView7);
                        break;
                    case 3:
                        tab.setCustomView(null);
                        View customView8 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.small_custom_tab, null);
                        ImageView tabIcon8 = (ImageView) customView8.findViewById(R.id.small_icon_img);
                        tabIcon8.setImageResource(R.drawable.drug_store_location);
                        tab.setCustomView(customView8);
                        break;
                    case 4:
                        tab.setCustomView(null);
                        View customView9 =
                                LayoutInflater.from(getApplication()).inflate(R.layout.small_custom_tab, null);
                        ImageView tabIcon4 = (ImageView) customView9.findViewById(R.id.small_icon_img);
                        tabIcon4.setImageResource(R.drawable.user_tab);
                        tab.setCustomView(customView9);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new Fragment();
        }

        @Override
        public int getCount() {
            return 5;
        }

    }

}
