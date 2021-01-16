package com.schanz.ktapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.schanz.ktapp.data.Creation;
import com.schanz.ktapp.MainApp;
import com.schanz.ktapp.R;
import com.schanz.ktapp.data.ResourceGenerator;

public class FragmentPreviewCreation extends FragmentBase {

    public static FragmentPreviewCreation newInstance(FragmentId fragmentId) {
        FragmentPreviewCreation fragment = new FragmentPreviewCreation();
        Bundle args = new Bundle();
        args.putSerializable(ActivityHome.ARG_FRAGMENT_ID, fragmentId);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentPreviewCreation() {
    }

    private ImageView mPreviewImage;
    private TextView mPreviewTitle, mStatBoxName, mStatBoxRarity, mStatBoxStory;
    private Button mBtnHelp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_creation_preview, container, false);

        mBtnHelp = (Button)rootView.findViewById(R.id.btn_help);
        mBtnHelp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDialog(
                        R.string.creation_preview_dialog_help_title,
                        R.string.creation_preview_dialog_help_message,
                        ResourceGenerator.get().getRandomDialogResponse());
            }
        });

        Creation current = MainApp.getInstance().getCurrentCreation();

        mStatBoxName = (TextView)rootView.findViewById(R.id.stat_box_name);
        mStatBoxName.setText(current.getName());

        mStatBoxRarity = (TextView)rootView.findViewById(R.id.stat_box_rarity);
        // mStatBoxRarity.setTextColor(ResourceGenerator.get().getRandomItemColor());
        mStatBoxRarity.setText(current.getRarity());

        mStatBoxStory = (TextView)rootView.findViewById(R.id.stat_box_story);
        if (current.getStory() != null) {
            mStatBoxStory.setText("\"" + current.getStory() + "\"");
        }

        mPreviewTitle = (TextView)rootView.findViewById(R.id.creation_name);
        if (MainApp.getInstance().getCurrentCreation().getName() != null) {
            mPreviewTitle.setText(MainApp.getInstance().getCurrentCreation().getName());
        }

        mPreviewImage = (ImageView)rootView.findViewById(R.id.img_preview);
        if (MainApp.getInstance().getCurrentCreation().getImageResource() != 0) {
            mPreviewImage.setImageResource(
                    MainApp.getInstance().getCurrentCreation().getImageResource());
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActivityHome)activity).onSectionAttached(
                (FragmentId)getArguments().getSerializable(ActivityHome.ARG_FRAGMENT_ID));
    }
}
