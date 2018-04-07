package com.example.drugtracker;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.FloatArrayEvaluator;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
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
    private int positionToBeCentered = 0;

    private View[] customView = new View[5];

    private TextView[] tabTitlesTextView = new TextView[5];

    private ImageView[] tabIconsImageView = new ImageView[5];


    private int[] tabIcons = {R.drawable.pill_box_tab,
            R.drawable.encyclopedia_tab,
            R.drawable.graph_info_tab,
            R.drawable.drug_store_location,
            R.drawable.user_tab
    };

    private int[] selectedTabIcons = {
            R.drawable.pill_box_selected_tab,
            R.drawable.encyclopedia_selected_tab,
            R.drawable.grapg_info_selected_tab,
            R.drawable.drug_store_selected_tab,
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

        String[] tabTitles = {getResources().getString(R.string.title_pill_box),
                getResources().getString(R.string.title_encyclopedia),
                getResources().getString(R.string.title_graph_info),
                getResources().getString(R.string.title_drugstore),
                getResources().getString(R.string.title_user)};

        mTabLayout = (TabLayout) findViewById(R.id.main_tabs);
        mViewPager = (ViewPager) findViewById(R.id.main_viewPager);

        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        //link the tabs with pager
        mTabLayout.setupWithViewPager(mViewPager);

        //hiding the tab bottom line (indicator)
        mTabLayout.setSelectedTabIndicatorColor(000);

        //initialize tab icons
        for (int i = 0; i < 5; i++) {
            mTabLayout.getTabAt(i).setIcon(tabIcons[i]);
        }

        for (int i = 0; i < 5; i++) {
            customView[i] =
                    LayoutInflater.from(getApplication()).inflate(R.layout.custom_tab, null);
            tabIconsImageView[i] = (ImageView) customView[i].findViewById(R.id.icon_img);
            tabTitlesTextView[i] = (TextView) customView[i].findViewById(R.id.title_tv);

            tabTitlesTextView[i].setText(tabTitles[i]);
            tabIconsImageView[i].setImageResource(selectedTabIcons[i]);
        }

        //when a tab is selected the small icon will be changed to bigger one
        //which is a custom layout with title

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                tab.setCustomView(null);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.remove(tabFragments[positionToBeCentered]).commit();

                //position which is selected should be located in the center
                //and here we determine that position by comparing selected icons whit tabIcons
                for (int i = 0; i < 5; i++) {
                    if (mTabLayout.getTabAt(tab.getPosition()).getIcon().getConstantState() ==
                            ContextCompat.getDrawable(getApplicationContext(), tabIcons[i]).getConstantState()) {
                        positionToBeCentered = i;
                        break;
                    }
                }
                //initialize the a array which arrange the icons

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (mTabLayout.getTabAt(i).getIcon().getConstantState() ==
                                ContextCompat.getDrawable(getApplicationContext(), tabIcons[j]).getConstantState()) {
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

                FragmentTransaction transactionn = getSupportFragmentManager().beginTransaction();
                transactionn.replace(R.id.container, tabFragments[positionToBeCentered]);

                transactionn.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transactionn.addToBackStack(null);
                transactionn.commit();

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
                    if (mTabLayout.getTabAt(tab.getPosition()).getIcon().getConstantState() ==
                            ContextCompat.getDrawable(getApplicationContext(), tabIcons[i]).getConstantState()) {
                        positionToBeCentered = i;
                        break;
                    }
                }

                //initialize the a array which arrange the icons

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (mTabLayout.getTabAt(i).getIcon().getConstantState() ==
                                ContextCompat.getDrawable(getApplicationContext(), tabIcons[j]).getConstantState()) {
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
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.container, tabFragments[positionToBeCentered]);

                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.addToBackStack(null);

                transaction.commit();
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

}


