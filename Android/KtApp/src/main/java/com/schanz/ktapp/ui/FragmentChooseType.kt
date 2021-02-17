package com.schanz.ktapp.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.schanz.ktapp.Constants
import com.schanz.ktapp.MainApp
import com.schanz.ktapp.R

class FragmentChooseType : FragmentBase(), IActivityActionCallbacks {

    companion object {
        private val TAG = FragmentChooseType::class.java.simpleName

        /**
         * Creates a new `FragmentChooseType` instance with configured bundle args.
         */
        fun newInstance(fragmentId: FragmentId): FragmentChooseType {
            val fragment = FragmentChooseType()
            val args = Bundle()
            args.putSerializable(ActivityHome.ARG_FRAGMENT_ID, fragmentId)
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var creationTypeImage: ImageView
    private lateinit var btnProceed: Button

    private var callbacks: IFragmentActionCallbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context as ActivityHome).onSectionAttached(requireArguments()
                .getSerializable(ActivityHome.ARG_FRAGMENT_ID) as FragmentId?)
        callbacks = try {
            context
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity must implement IFragmentActionCallbacks.")
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_choose_type, container, false)

        creationTypeImage = rootView.findViewById<View>(R.id.img_creation_type) as ImageView
        if (MainApp.getInstance().currentCreation.imageResource != 0) {
            creationTypeImage.setImageResource(
                    MainApp.getInstance().currentCreation.imageResource)
        }

        // Button: Create Hero
        rootView.findViewById<View>(R.id.btn_create_hero).setOnClickListener {
            onClickCreateHero()
        }

        // Button: Create Monster
        rootView.findViewById<View>(R.id.btn_create_monster).setOnClickListener {
            onClickCreateMonster()
        }

        // Button: Create Item
        rootView.findViewById<View>(R.id.btn_create_item).setOnClickListener {
            onClickCreateItem()
        }

        // Button: Proceed
        btnProceed = rootView.findViewById<View>(R.id.btn_proceed) as Button
        btnProceed.setOnClickListener { onClickProceed() }

        if (MainApp.getInstance().currentCreationType == null) {
            btnProceed.visibility = View.GONE
        }
        return rootView
    }

    private fun onClickCreateHero() {
        creationTypeImage.setImageResource(R.drawable.hero_knight_1)
        showToast(getString(R.string.creation_type_toast_hero))
        MainApp.getInstance().currentCreationType = Constants.CreationType.HERO
        btnProceed.visibility = View.VISIBLE
    }

    private fun onClickCreateMonster() {
        creationTypeImage.setImageResource(R.drawable.monster_skeleton_1)
        showToast(getString(R.string.creation_type_toast_monster))
        MainApp.getInstance().currentCreationType = Constants.CreationType.MONSTER
        btnProceed.visibility = View.VISIBLE
    }

    private fun onClickCreateItem() {
        creationTypeImage.setImageResource(R.drawable.item_potion_1)
        showToast(getString(R.string.creation_type_toast_item))
        MainApp.getInstance().currentCreationType = Constants.CreationType.ITEM
        btnProceed.visibility = View.VISIBLE
    }

    private fun onClickProceed() {
        callbacks?.onClickProceed(requireArguments()
                .getSerializable(ActivityHome.ARG_FRAGMENT_ID) as FragmentId?)
    }

    override fun onClickSave() {
        showDebugToast("Received instruction to save")
        Log.d(TAG, "Received instruction to save")
    }
}
