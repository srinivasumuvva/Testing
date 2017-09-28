package com.hari.restaurantdigital.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hari.restaurantdigital.R;
import com.hari.restaurantdigital.fragment.ContentFragment;

import static com.hari.restaurantdigital.R.id;
import static com.hari.restaurantdigital.R.layout;
import static com.hari.restaurantdigital.R.string;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    public static int mDeviceWidth, mDeviceHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(id.toolbar);
        setSupportActionBar(toolbar);
        calculateDeviceMetrics();

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(id.navigation_view);

        navigationView.setNavigationItemSelectedListener(this);


        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, string.openDrawer, string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }
    private void initViews(){

    }

    @Override
    protected void onResume() {
        super.onResume();
        navigationView.getMenu().performIdentifierAction(R.id.inbox, 0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(id.action_cart);
        menuItem.setIcon(buildCounterDrawable(2, R.drawable.cart));
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

     /*   switch (item.getItemId()) {
            case android.R.id.home:

                FragmentManager fragmentManager = getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0)
                    onBackPressed();
                else
                    actionBarDrawerToggle.setDrawerIndicatorEnabled(true);

                return true;
        }*/
        return super.onOptionsItemSelected(item);

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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        drawerLayout.closeDrawers();
        Fragment fragment = null;

        //Check to see which item was being clicked and perform appropriate action
        switch (menuItem.getItemId()) {


            //Replacing the main content with ContentFragment Which is our Inbox View;
            case id.inbox:
                Toast.makeText(getApplicationContext(), "Veg Soups Selected", Toast.LENGTH_SHORT).show();
                fragment = new ContentFragment();
                break;

            // For rest of the options we just show a toast on click

            case id.starred:
                Toast.makeText(getApplicationContext(), "Drinks Selected", Toast.LENGTH_SHORT).show();
                break;
            case id.sent_mail:
                Toast.makeText(getApplicationContext(), "Non Veg Soups Selected", Toast.LENGTH_SHORT).show();
                break;
            case id.drafts:
                Toast.makeText(getApplicationContext(), "Starters Selected", Toast.LENGTH_SHORT).show();
                break;
            case id.allmail:
                Toast.makeText(getApplicationContext(), "Beverages Selected", Toast.LENGTH_SHORT).show();
                break;
            case id.trash:
                Toast.makeText(getApplicationContext(), "Main Course Selected", Toast.LENGTH_SHORT).show();
                break;
            case id.spam:
                Toast.makeText(getApplicationContext(), "Dessert Seleccted", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Somethings Wron", Toast.LENGTH_SHORT).show();
                break;

        }
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
            fragmentTransaction.commit();
        }

        return true;
    }


    private void calculateDeviceMetrics() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mDeviceHeight = displayMetrics.heightPixels;
        mDeviceWidth = displayMetrics.widthPixels;
    }
}
