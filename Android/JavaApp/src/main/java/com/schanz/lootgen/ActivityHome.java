
package com.schanz.lootgen;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class ActivityHome extends AppCompatActivity
        implements FragmentNavigationDrawer.NavigationDrawerCallbacks,
        IFragmentActionCallbacks {

    private static final String TAG = ActivityHome.class.getSimpleName();

    private FragmentNavigationDrawer mNavigationDrawerFragment;

    private CharSequence mTitle;

    public static final String ARG_FRAGMENT_ID = "fragment_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mNavigationDrawerFragment = (FragmentNavigationDrawer)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout)findViewById(R.id.drawer_layout));

        showFragmentChooseType(FragmentId.CHOOSE_TYPE);
    }

    public Context getContext() {
        return this;
    }

    @Override
    public void onNavigationDrawerItemSelected(FragmentId fragmentId) {
        switch (fragmentId) {
            case PREVIEW_CREATION:
                showFragmentPreviewCreation(fragmentId);
                break;
            case CHOOSE_TYPE:
                showFragmentChooseType(fragmentId);
                break;
            case CHOOSE_ATTRIBUTES:
                showFragmentChooseAttributes(fragmentId);
                break;
            case CREATE_STORY:
                showFragmentCreateStory(fragmentId);
                break;
            default:
                showDebugToast("Not implemented!");
                break;
        }

    }

    private void showFragmentPreviewCreation(FragmentId fragmentId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, FragmentPreviewCreation.newInstance(fragmentId))
                .commit();
    }

    private void showFragmentChooseType(FragmentId fragmentId) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, FragmentChooseType.newInstance(fragmentId))
                .commit();
    }

    private void showFragmentChooseAttributes(FragmentId fragmentId) {
        if (MainApp.getInstance().getCurrentCreationType() == null) {
            showDebugToast("Select a creation type first!");
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, FragmentChooseAttributes.newInstance(fragmentId))
                .commit();
    }

    private void showFragmentCreateStory(FragmentId fragmentId) {
        if (MainApp.getInstance().getCurrentCreationType() == null) {
            showDebugToast("Select a creation type first!");
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, FragmentCreateStory.newInstance(fragmentId))
                .commit();
    }

    public void onSectionAttached(FragmentId fragmentId) {
        switch (fragmentId) {
            case PREVIEW_CREATION:
                mTitle = getString(R.string.drawer_preview);
                break;
            case CHOOSE_TYPE:
                mTitle = getString(R.string.drawer_type);
                break;
            case CHOOSE_ATTRIBUTES:
                mTitle = getString(R.string.drawer_attributes);
                break;
            case CREATE_STORY:
                mTitle = getString(R.string.drawer_story);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.home, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Settings can be opened with hardware "..." button
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            saveFragmentData();
            return true;
        } else if (id == R.id.action_settings) {
            showDebugToast("Home -> Settings");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Searches all active fragments for the first instance of a saving fragment that
     * implements {@code IActivityActionCallbacks}, and instructs the fragment to save its data.
     */
    private void saveFragmentData() {
        IActivityActionCallbacks savingFragment = null;
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            if (fragment != null && fragment.isVisible()
                    && fragment instanceof IActivityActionCallbacks) {
                savingFragment = (IActivityActionCallbacks)fragment;
                break;
            }
        }
        if (savingFragment == null) {
            Log.d(TAG, "No saveable fragments found!");
            return;
        }
        savingFragment.onClickSave();
    }

    protected void showDebugToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        TextView v = (TextView)toast.getView().findViewById(android.R.id.message);
        v.setTextColor(Color.GREEN);
        v.setTextSize(20.0f);
        toast.show();
    }

    @Override
    public void onClickProceed(FragmentId fragmentId) {
        switch (fragmentId) {
            case CHOOSE_ATTRIBUTES:
                showFragmentCreateStory(FragmentId.CREATE_STORY);
                break;
            case CHOOSE_TYPE:
                showFragmentChooseAttributes(FragmentId.CHOOSE_ATTRIBUTES);
                break;
            case CREATE_STORY:
                break;
            case PREVIEW_CREATION:
                break;
            default:
                break;
        }
    }
}
