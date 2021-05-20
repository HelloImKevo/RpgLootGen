package com.schanz.ktapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import com.schanz.ktapp.Constants
import com.schanz.ktapp.MainApp.Companion.getInstance
import com.schanz.ktapp.R
import com.schanz.ktapp.data.ResourceGenerator.Companion.get

class FragmentCreateStory : FragmentBase(), IActivityActionCallbacks {

    companion object {
        private val TAG = FragmentCreateStory::class.java.simpleName

        /**
         * Creates a new `FragmentCreateStory` instance with configured bundle args.
         */
        fun newInstance(fragmentId: FragmentId?): FragmentCreateStory {
            val fragment = FragmentCreateStory()
            val args = Bundle()
            args.putSerializable(ActivityHome.ARG_FRAGMENT_ID, fragmentId)
            fragment.arguments = args

            return fragment
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_save) {
            showDebugToast("Clicked save")
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private var mImageResources: IntArray = IntArray(0)

    private var mCurrentImagePosition = 0

    private var mImgCreation: ImageView? = null
    private var mTxtName: EditText? = null
    private var mTxtStory: EditText? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_create_story, container, false)

        initImageResources()

        mCurrentImagePosition = 0

        mImgCreation = rootView.findViewById<View>(R.id.img_creation) as ImageView
        mImgCreation!!.setImageResource(mImageResources[mCurrentImagePosition])

        val btnHelpName = rootView.findViewById<View>(R.id.btn_help_name) as Button
        btnHelpName.setOnClickListener { v: View? ->
            showHelpDialog(
                    "Creation Name",
                    "Name your creation, or generate a random name.",
                    get().getRandomDialogResponse())
        }

        val btnHelpImage = rootView.findViewById<View>(R.id.btn_help_image) as Button
        btnHelpImage.setOnClickListener { v: View? ->
            showHelpDialog(
                    "Creation Story", "Choose an image for your creation, and give it a backstory, "
                    + "or generate a random story.",
                    get().getRandomDialogResponse())
        }

        mTxtName = rootView.findViewById<View>(R.id.txt_name) as EditText

        val btnRandomName = rootView.findViewById<View>(R.id.btn_random_name) as Button
        btnRandomName.setOnClickListener { v: View? ->
            val randomName = get().generateRandomCreationName(
                    getInstance().currentCreationType)
            mTxtName!!.setText(randomName)
        }

        val btnPageForward = rootView.findViewById<View>(R.id.btn_page_forward) as ImageButton
        btnPageForward.setOnClickListener { v: View? -> onClickPageForward() }
        val btnPageBack = rootView.findViewById<View>(R.id.btn_page_back) as ImageButton
        btnPageBack.setOnClickListener { v: View? -> onClickPageBack() }

        mTxtStory = rootView.findViewById<View>(R.id.txt_story) as EditText

        val btnRandomStory = rootView.findViewById<View>(R.id.btn_random_story) as Button
        btnRandomStory.setOnClickListener { v: View? ->
            val randomStory = get().generateRandomStory()
            mTxtStory!!.setText(randomStory)
        }
        populateFields()
        return rootView
    }

    private fun onClickPageForward() {
        if (mCurrentImagePosition >= mImageResources.size - 1) {
            mCurrentImagePosition = 0
        } else {
            mCurrentImagePosition++
        }
        mImgCreation!!.setImageResource(mImageResources[mCurrentImagePosition])
    }

    private fun onClickPageBack() {
        if (mCurrentImagePosition <= 0) {
            mCurrentImagePosition = mImageResources.size - 1
        } else {
            mCurrentImagePosition--
        }
        mImgCreation!!.setImageResource(mImageResources[mCurrentImagePosition])
    }

    private fun initImageResources() {
        if (getInstance().currentCreationType == null) {
            return
        }

        when (getInstance().currentCreationType) {
            Constants.CreationType.HERO -> mImageResources = intArrayOf(
                    R.drawable.hero_knight_1,
                    R.drawable.hero_wizard_1,
                    R.drawable.hero_ranger_1
            )

            Constants.CreationType.ITEM -> mImageResources = intArrayOf(
                    R.drawable.item_hourglass,
                    R.drawable.item_potion_1,
                    R.drawable.item_potion_2,
                    R.drawable.item_scroll_1,
                    R.drawable.weapon_sword_1,
                    R.drawable.weapon_sword_2,
                    R.drawable.weapon_sword_3,
                    R.drawable.weapon_sword_4,
                    R.drawable.weapon_sword_5,
                    R.drawable.weapon_sword_6
            )

            Constants.CreationType.MONSTER -> mImageResources = intArrayOf(
                    R.drawable.monster_skeleton_1,
                    R.drawable.monster_skull_1
            )
            else -> {
            }
        }
    }

    private fun populateFields() {
        val current = getInstance().currentCreation
        if (current.name != null) {
            mTxtName!!.setText(current.name)
        }
        if (current.story != null) {
            mTxtStory!!.setText(current.story)
        }
        if (current.imageResource != 0) {
            mImgCreation!!.setImageResource(current.imageResource)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as ActivityHome).onSectionAttached(requireArguments()
                .getSerializable(ActivityHome.ARG_FRAGMENT_ID) as FragmentId?)
    }

    override fun onClickSave() {
        Log.d(TAG, "Saving creation...")
        val current = getInstance().currentCreation
        current.name = mTxtName!!.text.toString()
        current.story = mTxtStory!!.text.toString()
        current.imageResource = mImageResources[mCurrentImagePosition]
        showDebugToast("Saved!")
    }
}
