
package com.schanz.lootgen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class FragmentCreateStory extends FragmentBase implements IActivityActionCallbacks {

    private static final String TAG = FragmentCreateStory.class.getSimpleName();

    public static FragmentCreateStory newInstance(FragmentId fragmentId) {
        FragmentCreateStory fragment = new FragmentCreateStory();
        Bundle args = new Bundle();
        args.putSerializable(ActivityHome.ARG_FRAGMENT_ID, fragmentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save) {
            showDebugToast("Clicked save");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public FragmentCreateStory() {
    }

    private int[] mImageResources;

    private int mCurrentImagePosition;

    private ImageView mImgCreation;
    private EditText mTxtName, mTxtStory;
    private Button mBtnHelpName, mBtnHelpImage;

    private ImageButton mBtnPageForward, mBtnPageBack;

    private Button mBtnRandomName, mBtnRandomStory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_create_story, container, false);

        initImageResources();

        mCurrentImagePosition = 0;

        mImgCreation = (ImageView)rootView.findViewById(R.id.img_creation);
        mImgCreation.setImageResource(mImageResources[mCurrentImagePosition]);

        mBtnHelpName = (Button)rootView.findViewById(R.id.btn_help_name);
        mBtnHelpName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDialog(
                        "Creation Name",
                        "Name your creation, or generate a random name.",
                        ResourceGenerator.get().getRandomDialogResponse());
            }
        });

        mBtnHelpImage = (Button)rootView.findViewById(R.id.btn_help_image);
        mBtnHelpImage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDialog(
                        "Creation Story",
                        "Choose an image for your creation, and give it a backstory, "
                                + "or generate a random story.",
                        ResourceGenerator.get().getRandomDialogResponse());
            }
        });

        mTxtName = (EditText)rootView.findViewById(R.id.txt_name);

        mBtnRandomName = (Button)rootView.findViewById(R.id.btn_random_name);
        mBtnRandomName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String randomName = ResourceGenerator.get().generateRandomCreationName(
                        MainApp.getInstance().getCurrentCreationType());
                mTxtName.setText(randomName);
            }
        });

        mBtnPageForward = (ImageButton)rootView.findViewById(R.id.btn_page_forward);
        mBtnPageForward.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPageForward();
            }
        });

        mBtnPageBack = (ImageButton)rootView.findViewById(R.id.btn_page_back);
        mBtnPageBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickPageBack();
            }
        });

        mTxtStory = (EditText)rootView.findViewById(R.id.txt_story);

        mBtnRandomStory = (Button)rootView.findViewById(R.id.btn_random_story);
        mBtnRandomStory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String randomStory = ResourceGenerator.get().generateRandomStory();
                mTxtStory.setText(randomStory);
            }
        });

        populateFields();

        return rootView;
    }

    private void onClickPageForward() {
        if (mCurrentImagePosition >= mImageResources.length - 1) {
            mCurrentImagePosition = 0;
        } else {
            mCurrentImagePosition++;
        }
        mImgCreation.setImageResource(mImageResources[mCurrentImagePosition]);
    }

    private void onClickPageBack() {
        if (mCurrentImagePosition <= 0) {
            mCurrentImagePosition = mImageResources.length - 1;
        } else {
            mCurrentImagePosition--;
        }
        mImgCreation.setImageResource(mImageResources[mCurrentImagePosition]);
    }

    private void initImageResources() {
        if (MainApp.getInstance().getCurrentCreationType() == null) {
            return;
        }
        switch (MainApp.getInstance().getCurrentCreationType()) {
            case HERO:
                mImageResources = new int[] {
                        R.drawable.hero_knight_1,
                        R.drawable.hero_wizard_1,
                        R.drawable.hero_ranger_1
                };
                break;
            case ITEM:
                mImageResources = new int[] {
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
                };
                break;
            case MONSTER:
                mImageResources = new int[] {
                        R.drawable.monster_skeleton_1,
                        R.drawable.monster_skull_1
                };
                break;
            default:
                break;

        }
    }

    private void populateFields() {
        Creation current = MainApp.getInstance().getCurrentCreation();
        if (current.getName() != null) {
            mTxtName.setText(current.getName());
        }
        if (current.getStory() != null) {
            mTxtStory.setText(current.getStory());
        }
        if (current.getImageResource() != 0) {
            mImgCreation.setImageResource(current.getImageResource());
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActivityHome)activity).onSectionAttached(
                (FragmentId)getArguments().getSerializable(ActivityHome.ARG_FRAGMENT_ID));
    }

    @Override
    public void onClickSave() {
        Log.d(TAG, "Saving creation...");
        Creation current = MainApp.getInstance().getCurrentCreation();
        current.setName(mTxtName.getText().toString());
        current.setStory(mTxtStory.getText().toString());
        current.setImageResource(mImageResources[mCurrentImagePosition]);
        showDebugToast("Saved!");
    }
}
