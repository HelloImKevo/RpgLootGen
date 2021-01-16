package com.schanz.ktapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.schanz.ktapp.Constants.CreationType;

public class FragmentChooseType extends FragmentBase implements IActivityActionCallbacks {

    private static final String TAG = FragmentChooseType.class.getSimpleName();

    private ImageView mCreationTypeImage;
    private Button mBtnCreateHero, mBtnCreateMonster, mBtnCreateItem, mBtnProceed;

    private IFragmentActionCallbacks mCallbacks;

    public static FragmentChooseType newInstance(FragmentId fragmentId) {
        FragmentChooseType fragment = new FragmentChooseType();
        Bundle args = new Bundle();
        args.putSerializable(ActivityHome.ARG_FRAGMENT_ID, fragmentId);
        fragment.setArguments(args);

        return fragment;
    }

    public FragmentChooseType() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_type, container, false);

        mCreationTypeImage = (ImageView)rootView.findViewById(R.id.img_creation_type);
        if (MainApp.getInstance().getCurrentCreation().getImageResource() != 0) {
            mCreationTypeImage.setImageResource(
                    MainApp.getInstance().getCurrentCreation().getImageResource());
        }

        mBtnCreateHero = (Button)rootView.findViewById(R.id.btn_create_hero);
        mBtnCreateHero.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateHero();
            }
        });

        mBtnCreateMonster = (Button)rootView.findViewById(R.id.btn_create_monster);
        mBtnCreateMonster.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateMonster();
            }
        });

        mBtnCreateItem = (Button)rootView.findViewById(R.id.btn_create_item);
        mBtnCreateItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickCreateItem();
            }
        });

        mBtnProceed = (Button)rootView.findViewById(R.id.btn_proceed);
        mBtnProceed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickProceed();
            }
        });
        if (MainApp.getInstance().getCurrentCreationType() == null) {
            mBtnProceed.setVisibility(View.GONE);
        }

        return rootView;
    }

    private void onClickCreateHero() {
        mCreationTypeImage.setImageResource(R.drawable.hero_knight_1);
        showToast(getString(R.string.creation_type_toast_hero));
        MainApp.getInstance().setCurrentCreationType(CreationType.HERO);
        mBtnProceed.setVisibility(View.VISIBLE);
    }

    private void onClickCreateMonster() {
        mCreationTypeImage.setImageResource(R.drawable.monster_skeleton_1);
        showToast(getString(R.string.creation_type_toast_monster));
        MainApp.getInstance().setCurrentCreationType(CreationType.MONSTER);
        mBtnProceed.setVisibility(View.VISIBLE);
    }

    private void onClickCreateItem() {
        mCreationTypeImage.setImageResource(R.drawable.item_potion_1);
        showToast(getString(R.string.creation_type_toast_item));
        MainApp.getInstance().setCurrentCreationType(CreationType.ITEM);
        mBtnProceed.setVisibility(View.VISIBLE);
    }

    private void onClickProceed() {
        if (mCallbacks != null) {
            mCallbacks.onClickProceed(
                    (FragmentId)getArguments().getSerializable(ActivityHome.ARG_FRAGMENT_ID));
        }
    }

    @Override
    public void onClickSave() {
        showDebugToast("Received instruction to save");
        Log.d(TAG, "Received instruction to save");
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActivityHome)activity).onSectionAttached(
                (FragmentId)getArguments().getSerializable(ActivityHome.ARG_FRAGMENT_ID));
        try {
            mCallbacks = (IFragmentActionCallbacks)activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement FragmentButtonCallbacks.");
        }
    }
}
