package com.hari.restaurantdigital.ui;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.base.BaseActivity;
import com.hari.restaurantdigital.fragment.ContentFragment;

import static com.hari.restaurantdigital.R.id;
import static com.hari.restaurantdigital.R.layout;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getName();
    //Defining Variables
    private Toolbar toolbar;
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    public static int mDeviceWidth, mDeviceHeight;
    public static int mProductDisplayType = 1;
    MenuItem mNavigationMenuItem;
    private int mSelectedItemSize=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNavigationView();
        calculateDeviceMetrics();
        if (savedInstanceState == null) {
            loadFragment();
        }

    }


    private void initNavigationView() {
        // Initializing Drawer Layout and ActionBarToggle
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(id.drawer);
        ActionBarDrawerToggle toggle = getActionBarDrawerToggle(toolbar);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //Initializing NavigationView
        mNavigationView = (NavigationView) findViewById(id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);


    }

    @NonNull
    private ActionBarDrawerToggle getActionBarDrawerToggle(final Toolbar toolbar) {
        return new ActionBarDrawerToggle(MainActivity.this, mDrawerLayout,
                toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mNavigationMenuItem != null) {
                    navigateToMenuItem(mNavigationMenuItem);
                    mNavigationMenuItem = null;
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
    }

    private void navigateToMenuItem(MenuItem pNavigationMenuItem) {
       /* switch (pNavigationMenuItem.getItemId()) {

        }*/
        loadFragment();
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.getMenu().performIdentifierAction(R.id.inbox, 0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem menuItem = menu.findItem(id.action_cart);
        View actionView = MenuItemCompat.getActionView(menuItem);
        TextView txt_bagCount = (TextView) actionView.findViewById(R.id.badge_notification_1);
        txt_bagCount.setText(mSelectedItemSize+"");
        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });
        return true;
    }

    private Drawable buildCounterDrawable(int count, int backgroundImageId) {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View view = inflater.inflate(layout.add_cart_counter, null);
        view.setBackgroundResource(backgroundImageId);

        if (count == 0) {
            View counterTextPanel = view.findViewById(id.counterValuePanel);
            counterTextPanel.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) view.findViewById(id.count);
            textView.setText("" + count);
        }

        view.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        view.setDrawingCacheEnabled(true);
        view.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        Bitmap bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);

        return new BitmapDrawable(getResources(), bitmap);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mNavigationMenuItem = item;
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case id.type1:
                mProductDisplayType = 1;
                loadFragment();
                return true;
            case id.type2:
                mProductDisplayType = 2;
                loadFragment();
                return true;
            case id.type3:
                mProductDisplayType = 3;
                loadFragment();
                return true;
            case id.type4:
                mProductDisplayType = 4;
                loadFragment();
                return true;
            case id.type5:
                mProductDisplayType = 5;
                loadFragment();
                return true;
            case id.type6:
                mProductDisplayType = 6;
                loadFragment();
                return true;

            case id.action_cart:
                startActivity(new Intent(MainActivity.this, SelectedProductListing.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    }

    public void cartSelected(int pSize){
        mSelectedItemSize=pSize;
        invalidateOptionsMenu();
    }

    private void loadFragment() {
        Fragment fragment = new ContentFragment();
        if (fragment != null) {
            Log.e(TAG, "TOOL BAR HEIGHT IS " + toolbar.getHeight());
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commit();
        }
    }


    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public int getToolBarHeight() {

        int[] textSizeAttr = new int[]{android.R.attr.actionBarSize, R.attr.actionBarSize};
        TypedArray a = obtainStyledAttributes(new TypedValue().data, textSizeAttr);
        float heightHolo = a.getDimension(0, -1);
        float heightMaterial = a.getDimension(1, -1);

        Log.e("DEBUG", "Height android.R.attr.: " + heightHolo + "");
        Log.e("DEBUG", "Height R.attr.: " + heightMaterial + "");
        return (int) heightMaterial;
    }


    private void calculateDeviceMetrics() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int toolbarHeight = toolbar.getHeight();
        Log.e(TAG, "tool bar height is " + toolbarHeight);
        Log.e(TAG, "tool bar height is " + getToolBarHeight());
        Log.e(TAG, "margin height is " + dpToPx(10));
        Log.e(TAG, "margin height is " + dp2px(10));

        Log.e(TAG, "action bar height is " + getSupportActionBar().getHeight());
        Log.e(TAG, "mDeviceHeight height is " + displayMetrics.heightPixels);
        mDeviceHeight = (displayMetrics.heightPixels - getToolBarHeight());
        Log.e(TAG, "mDeviceHeight height is " + mDeviceHeight);
        mDeviceWidth = displayMetrics.widthPixels;
    }
}
