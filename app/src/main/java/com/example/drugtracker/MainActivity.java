package com.example.drugtracker;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.FloatArrayEvaluator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //this array indicates the position of each tab after selecting one of them
    //in fact when you select one tab this array rotate 360 :D
    private int[] indicatePositionOfTabIcons = {0, 1, 2, 3, 4};
    private int counter = 0;
    int positionToBeCentered = 0;

    private View[] customView = new View[5];
    private TextView[] tabTitles = new TextView[5];
    private int[] tabIcons = {R.drawable.pill_box_tab,
            R.drawable.encyclopedia_tab,
            R.drawable.graph_info_tab,
            R.drawable.drug_store_location,
            R.drawable.user_tab
    };
    private Fragment[] tabFragments = {
            new PillBoxFragment(), new EncyclopediaFragment(),
            new DiagramFragment(), new Fragment(), new Fragment()};


    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private android.support.v4.view.PagerAdapter mPagerAdapter;
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        //link the tabs with pager
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));


        //hiding the tab bottom line (indicator)
        mTabLayout.setSelectedTabIndicatorColor(000);


        //initialize tab icons
        for (int i = 0; i < 5; i++) {
            mTabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }

        customView[0] =
                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
        ImageView tabIcon = (ImageView) customView[0].findViewById(R.id.icon_img);
        tabTitles[0] = (TextView) customView[0].findViewById(R.id.title_tv);
        tabTitles[0].setText(getString(R.string.title_pill_box));
        tabIcon.setImageResource(R.drawable.pill_box_selected_tab);

        customView[1] =
                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
        ImageView tabIcon1 = (ImageView) customView[1].findViewById(R.id.icon_img);
        tabTitles[1] = (TextView) customView[1].findViewById(R.id.title_tv);
        tabTitles[1].setText(getString(R.string.title_encyclopedia));
        tabIcon1.setImageResource(R.drawable.encyclopedia_selected_tab);

        customView[2] =
                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
        ImageView tabIcon2 = (ImageView) customView[2].findViewById(R.id.icon_img);
        tabTitles[2] = (TextView) customView[2].findViewById(R.id.title_tv);
        tabTitles[2].setText(getString(R.string.title_graph_info));
        tabIcon2.setImageResource(R.drawable.grapg_info_selected_tab);

        customView[3] =
                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
        ImageView tabIcon3 = (ImageView) customView[3].findViewById(R.id.icon_img);
        tabTitles[3] = (TextView) customView[3].findViewById(R.id.title_tv);
        tabTitles[3].setText(getString(R.string.title_drugstore));
        tabIcon3.setImageResource(R.drawable.drug_store_selected_tab);

        customView[4] =
                LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
        ImageView tabIcon4 = (ImageView) customView[4].findViewById(R.id.icon_img);
        tabTitles[4] = (TextView) customView[4].findViewById(R.id.title_tv);
        tabTitles[4].setText(getString(R.string.title_user));
        tabIcon4.setImageResource(R.drawable.user_tab);


        //when a tab is selected the small icon will be changed to bigger one
        //which is a custom layout with title

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                counter++;
                tab.setCustomView(null);
                mTabLayout.setBackgroundColor(000);
                // mTabLayout.setPadding(0, 25, 0, 300);
                //  mTabLayout.setMinimumHeight(130);

                //position which is selected should be located in the center
                //and here we determine that position by comparing selected icons whit tabIcons

                for (int i = 0; i < 5; i++) {
                    if (mTabLayout.getTabAt(tab.getPosition()).getIcon().getConstantState().equals(
                            getResources().getDrawable(tabIcons[i]).getConstantState())) {
                        positionToBeCentered = i;
                        break;
                    }
                }

                //initialize the a array which arrange the icons

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (mTabLayout.getTabAt(i).getIcon().getConstantState().equals(
                                getResources().getDrawable(tabIcons[j]).getConstantState())) {
                            indicatePositionOfTabIcons[i] = j;
                            break;
                        }
                    }
                }

                makeCenter(indicatePositionOfTabIcons, positionToBeCentered);


                //setting up icons after rotating
                for (int i = 0; i < 5; i++) {
                    mTabLayout.getTabAt(i).setIcon(tabIcons[indicatePositionOfTabIcons[i]]);

                    if (i == 2) {
                        mTabLayout.getTabAt(2).setCustomView(null);
                        mTabLayout.getTabAt(2).setCustomView(customView[indicatePositionOfTabIcons[2]]);
                    }
                }

                Log.d("itemselected is", String.valueOf(mViewPager.getCurrentItem()));

                //if (mViewPager.getCurrentItem() == 4) {
                //   mViewPager.setCurrentItem(0);
                // }
                // mViewPager.setCurrentItem(positionToBeCentered);
  /*
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, tabFragments[positionToBeCentered]);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);

                transaction.commit();
                */
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setCustomView(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

                tab.setCustomView(null);

                //position which is selected should be located in the center
                for (int i = 0; i < 5; i++) {
                    if (mTabLayout.getTabAt(tab.getPosition()).getIcon().getConstantState().equals(
                            getResources().getDrawable(tabIcons[i]).getConstantState())) {
                        positionToBeCentered = i;
                        break;
                    }
                }

                //initialize the a array which arrange the icons

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (mTabLayout.getTabAt(i).getIcon().getConstantState().equals(
                                getResources().getDrawable(tabIcons[j]).getConstantState())) {
                            indicatePositionOfTabIcons[i] = j;
                            break;
                        }
                    }
                }

                makeCenter(indicatePositionOfTabIcons, positionToBeCentered);

                for (int i = 0; i < 5; i++) {
                    mTabLayout.getTabAt(i).setIcon(tabIcons[indicatePositionOfTabIcons[i]]);
                    if (i == 2) {
                        mTabLayout.getTabAt(2).setCustomView(null);
                        mTabLayout.getTabAt(2).setCustomView(customView[indicatePositionOfTabIcons[2]]);
                    }
                }

                Log.d("itemReselected is", String.valueOf(mViewPager.getCurrentItem()));
/*
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame, tabFragments[positionToBeCentered]);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);

                transaction.commit();
*/
            }
        });
    }

    class PagerAdapter extends FragmentStatePagerAdapter {

        /*
        @Override
        public int getItemPosition(Object object) {
            if (object instanceof PillBoxFragment ||
                    object instanceof EncyclopediaFragment ||
                    object instanceof DiagramFragment){
               // return pagerAdapterPosChanged;


            }return POSITION_UNCHANGED;
        }*/

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {

            Log.d("position is ", String.valueOf(position));

            for (int i = 0; i < 5; i++) {
                if (position == i) {
                    switch (positionToBeCentered) {
                        case 0:
                            return new PillBoxFragment();
                        case 1:
                            return new EncyclopediaFragment();
                        case 2:
                            return new DiagramFragment();
                        default:
                            return new Fragment();
                    }
                }
            }
            return new Fragment();

            // mViewPager.setCurrentItem(positionToBeCentered);
            // if (counter == 0) {
            //return new Fragment();
            //tabFragments[positionToBeCentered];
            // }
            //return new Fragment();
            //return tabFragments[mViewPager.getCurrentItem()];
            // FragmentManager fragmentManager = getSupportFragmentManager();
            // fragmentManager.beginTransaction().remove()
            //  if(fragmentManager.findFragmentById("P").)
            //for (int i = 0; i < 5; i++) {
            //    if (position == i) {
            //     fragment = tabFragments[i];
            //   break;
            // }
            //}
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return 5;
        }

    }


    @Override
    protected void onStart() {
        super.onStart();

        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        set = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.flip);
        set.setTarget(mTabLayout);
        set.start();
    }

    //given an array and a number and it will place
    //the number in the center of array

    public void makeCenter(int[] array, int numberToBeCentered) {

        while (array[2] != numberToBeCentered) {
            int temp = array[0];
            for (int j = 0; j < 5; j++) {
                if (j == 4) {
                    array[j] = temp;
                } else {
                    array[j] = array[j + 1];
                }
            }
            Log.d("Stuck", "stuck here");
        }
    }

    public int indicatePosition(int[] icons, TabLayout.Tab tab) {
        int positionToBeCentered = 0;
        for (int i = 0; i < 5; i++) {
            if (tab.getIcon().getConstantState().equals(
                    getResources().getDrawable(icons[i]).getConstantState())) {
                positionToBeCentered = i;
                break;
            }
        }
        return positionToBeCentered;
    }


}
