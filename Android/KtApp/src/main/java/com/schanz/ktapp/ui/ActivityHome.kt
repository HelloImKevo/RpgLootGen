package com.schanz.ktapp.ui

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.schanz.ktapp.MainApp
import com.schanz.ktapp.R

class ActivityHome : AppCompatActivity(),
        FragmentNavigationDrawer.NavigationDrawerCallbacks,
        IFragmentActionCallbacks {

    companion object {
        private val TAG = ActivityHome::class.java.simpleName
        const val ARG_FRAGMENT_ID = "fragment_id"
    }

    private lateinit var mNavigationDrawerFragment: FragmentNavigationDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mNavigationDrawerFragment =
                supportFragmentManager.findFragmentById(R.id.navigation_drawer)
                        as FragmentNavigationDrawer

        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                findViewById(R.id.drawer_layout))
        showFragmentChooseType(FragmentId.CHOOSE_TYPE)
    }

    override fun onNavigationDrawerItemSelected(fragmentId: FragmentId) {
        when (fragmentId) {
            FragmentId.PREVIEW_CREATION -> showFragmentPreviewCreation(fragmentId)
            FragmentId.CHOOSE_TYPE -> showFragmentChooseType(fragmentId)
            FragmentId.CHOOSE_ATTRIBUTES -> showFragmentChooseAttributes(fragmentId)
            FragmentId.CREATE_STORY -> showFragmentCreateStory(fragmentId)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (!mNavigationDrawerFragment.isDrawerOpen) {
            menuInflater.inflate(R.menu.home, menu)
            restoreActionBar()
            return true
        }
        return super.onCreateOptionsMenu(menu)
    }

    /**
     * Settings can be opened with hardware "..." button
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_save) {
            saveFragmentData()
            return true
        } else if (id == R.id.action_settings) {
            showDebugToast("Home -> Settings")
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClickProceed(fragmentId: FragmentId?) {
        when (fragmentId) {
            FragmentId.CHOOSE_ATTRIBUTES -> showFragmentCreateStory(FragmentId.CREATE_STORY)
            FragmentId.CHOOSE_TYPE -> showFragmentChooseAttributes(FragmentId.CHOOSE_ATTRIBUTES)
            else -> showDebugToast("Not implemented!")
        }
    }

    private fun showFragmentPreviewCreation(fragmentId: FragmentId) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentPreviewCreation.newInstance(fragmentId))
                .commit()
    }

    private fun showFragmentChooseType(fragmentId: FragmentId) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentChooseType.newInstance(fragmentId))
                .commit()
    }

    private fun showFragmentChooseAttributes(fragmentId: FragmentId) {
        if (MainApp.getInstance().currentCreationType == null) {
            showDebugToast("Select a creation type first!")
            return
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentChooseAttributes.newInstance(fragmentId))
                .commit()
    }

    private fun showFragmentCreateStory(fragmentId: FragmentId) {
        if (MainApp.getInstance().currentCreationType == null) {
            showDebugToast("Select a creation type first!")
            return
        }
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragmentCreateStory.newInstance(fragmentId))
                .commit()
    }

    fun onSectionAttached(fragmentId: FragmentId?) {
        when (fragmentId) {
            FragmentId.PREVIEW_CREATION -> title = getString(R.string.drawer_preview)
            FragmentId.CHOOSE_TYPE -> title = getString(R.string.drawer_type)
            FragmentId.CHOOSE_ATTRIBUTES -> title = getString(R.string.drawer_attributes)
            FragmentId.CREATE_STORY -> title = getString(R.string.drawer_story)
        }
    }

    private fun restoreActionBar() {
        supportActionBar?.navigationMode = ActionBar.NAVIGATION_MODE_STANDARD
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = this.title
    }

    /**
     * Searches all active fragments for the first instance of a saving fragment that
     * implements `IActivityActionCallbacks`, and instructs the fragment to save its data.
     */
    private fun saveFragmentData() {
        for (fragment in supportFragmentManager.fragments) {
            if (fragment != null && fragment.isVisible
                    && fragment is IActivityActionCallbacks) {
                fragment.onClickSave()
                return
            }
        }
        Log.d(TAG, "No saveable fragments found!")
    }

    private fun showDebugToast(message: String?) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        val v = toast.view.findViewById<View>(android.R.id.message) as TextView
        v.setTextColor(Color.GREEN)
        v.textSize = 20.0f
        toast.show()
    }
}
