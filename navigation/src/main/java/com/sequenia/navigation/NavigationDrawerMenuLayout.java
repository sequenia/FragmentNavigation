package com.sequenia.navigation;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.SparseIntArray;
import android.view.MenuItem;

/**
 * Created by chybakut2004 on 10.06.16.
 */

public abstract class NavigationDrawerMenuLayout extends NavigationMenuLayout {

    private int drawerLayoutId;
    private int drawerViewId;
    private int openStrId;
    private int closeStrId;

    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private NavigationView drawerView;

    public NavigationDrawerMenuLayout(int drawerLayoutId, int drawerViewId, int openStrId, int closeStrId) {
        this.drawerLayoutId = drawerLayoutId;
        this.openStrId = openStrId;
        this.closeStrId = closeStrId;
        this.drawerViewId = drawerViewId;
    }

    @Override
    public void setupLayout(NavigationActivity activity, NavigationActivity.NavigationActivitySettings settings) {
        ActionBar actionBar = activity.getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

        drawerView = (NavigationView) activity.findViewById(drawerViewId);
        drawerLayout = (DrawerLayout) activity.findViewById(drawerLayoutId);
        if(drawerLayout != null) {
            toggle = new ActionBarDrawerToggle(
                    activity,
                    drawerLayout,
                    openStrId,
                    closeStrId);

            toggle.setDrawerIndicatorEnabled(false);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }
    }

    @Override
    public boolean hasBackButtonLogic() {
        return true;
    }

    @Override
    public boolean isOpen() {
        return drawerLayout != null && drawerView != null && drawerLayout.isDrawerOpen(drawerView);
    }

    @Override
    public void close() {
        if(drawerLayout != null && drawerView != null) {
            drawerLayout.closeDrawer(drawerView);
        }
    }

    @Override
    public void updateBackButton(NavigationActivity navigationActivity, NavigationFragment fragment) {
        if(toggle != null) {
            toggle.setDrawerIndicatorEnabled(!navigationActivity.hasBackButton(fragment));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return toggle.onOptionsItemSelected(item);
    }

    public NavigationView getDrawerView() {
        return drawerView;
    }
}