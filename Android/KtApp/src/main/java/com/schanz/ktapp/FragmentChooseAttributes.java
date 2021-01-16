package com.schanz.ktapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;

import java.util.Random;

public class FragmentChooseAttributes extends FragmentBase implements OnFocusChangeListener,
        IActivityActionCallbacks {

    private static final String TAG = FragmentChooseAttributes.class.getSimpleName();

    private IFragmentActionCallbacks mCallbacks;

    public static FragmentChooseAttributes newInstance(FragmentId fragmentId) {
        FragmentChooseAttributes fragment = new FragmentChooseAttributes();
        Bundle args = new Bundle();
        args.putSerializable(ActivityHome.ARG_FRAGMENT_ID, fragmentId);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentChooseAttributes() {
    }

    private Random mRandom = new Random();

    private Button mBtnHelp, mBtnRandomize, mBtnProceed;
    private ImageView mImgAttribute;
    private TableRow mRowVitality, mRowStrength, mRowIntelligence, mRowDexterity;
    private TableRow mRowValue, mRowRarity, mRowDamage;
    private CheckBox mChkVitality, mChkStrength, mChkIntelligence, mChkDexterity;
    private CheckBox mChkValue, mChkRarity, mChkDamage;
    private EditText mTxtVitalityMin, mTxtVitalityMax;
    private EditText mTxtStrengthMin, mTxtStrengthMax;
    private EditText mTxtDexterityMin, mTxtDexterityMax;
    private EditText mTxtIntelligenceMin, mTxtIntelligenceMax;
    private EditText mTxtDamageMin, mTxtDamageMax;
    private EditText mTxtValue, mTxtRarity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_choose_attributes, container, false);

        mImgAttribute = (ImageView)rootView.findViewById(R.id.img_attribute);

        mBtnHelp = (Button)rootView.findViewById(R.id.btn_help);
        mBtnHelp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showHelpDialog(
                        R.string.attributes_dialog_help_title,
                        R.string.attributes_dialog_help_message,
                        ResourceGenerator.get().getRandomDialogResponse());
            }
        });

        mBtnRandomize = (Button)rootView.findViewById(R.id.btn_randomize_attributes);
        mBtnRandomize.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRandomize();
            }
        });

        mBtnProceed = (Button)rootView.findViewById(R.id.btn_proceed);
        mBtnProceed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickProceed();
            }
        });

        mRowVitality = (TableRow)rootView.findViewById(R.id.row_vitality);
        mChkVitality = (CheckBox)rootView.findViewById(R.id.chk_vitality);
        mChkVitality.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImgAttribute.setImageResource(R.drawable.attr_vitality);
                }
            }
        });
        mTxtVitalityMin = (EditText)rootView.findViewById(R.id.txt_vitality_min);
        mTxtVitalityMin.setOnFocusChangeListener(this);
        mTxtVitalityMax = (EditText)rootView.findViewById(R.id.txt_vitality_max);
        mTxtVitalityMax.setOnFocusChangeListener(this);

        mRowStrength = (TableRow)rootView.findViewById(R.id.row_strength);
        mChkStrength = (CheckBox)rootView.findViewById(R.id.chk_strength);
        mChkStrength.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImgAttribute.setImageResource(R.drawable.attr_strength);
                }
            }
        });
        mTxtStrengthMin = (EditText)rootView.findViewById(R.id.txt_strength_min);
        mTxtStrengthMin.setOnFocusChangeListener(this);
        mTxtStrengthMax = (EditText)rootView.findViewById(R.id.txt_strength_max);
        mTxtStrengthMax.setOnFocusChangeListener(this);

        mRowIntelligence = (TableRow)rootView.findViewById(R.id.row_intelligence);
        mChkIntelligence = (CheckBox)rootView.findViewById(R.id.chk_intelligence);
        mChkIntelligence.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImgAttribute.setImageResource(R.drawable.attr_intelligence);
                }
            }
        });
        mTxtIntelligenceMin = (EditText)rootView.findViewById(R.id.txt_intelligence_min);
        mTxtIntelligenceMin.setOnFocusChangeListener(this);
        mTxtIntelligenceMax = (EditText)rootView.findViewById(R.id.txt_intelligence_max);
        mTxtIntelligenceMax.setOnFocusChangeListener(this);

        mRowDexterity = (TableRow)rootView.findViewById(R.id.row_dexterity);
        mChkDexterity = (CheckBox)rootView.findViewById(R.id.chk_dexterity);
        mChkDexterity.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImgAttribute.setImageResource(R.drawable.attr_dexterity);
                }
            }
        });
        mTxtDexterityMin = (EditText)rootView.findViewById(R.id.txt_dexterity_min);
        mTxtDexterityMin.setOnFocusChangeListener(this);
        mTxtDexterityMax = (EditText)rootView.findViewById(R.id.txt_dexterity_max);
        mTxtDexterityMax.setOnFocusChangeListener(this);

        mRowDamage = (TableRow)rootView.findViewById(R.id.row_damage);
        mChkDamage = (CheckBox)rootView.findViewById(R.id.chk_damage);
        mChkDamage.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImgAttribute.setImageResource(R.drawable.attr_damage);
                }
            }
        });
        mTxtDamageMin = (EditText)rootView.findViewById(R.id.txt_damage_min);
        mTxtDamageMin.setOnFocusChangeListener(this);
        mTxtDamageMax = (EditText)rootView.findViewById(R.id.txt_damage_max);
        mTxtDamageMax.setOnFocusChangeListener(this);

        mRowValue = (TableRow)rootView.findViewById(R.id.row_value);
        mChkValue = (CheckBox)rootView.findViewById(R.id.chk_value);
        mChkValue.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImgAttribute.setImageResource(R.drawable.attr_value);
                }
            }
        });
        mTxtValue = (EditText)rootView.findViewById(R.id.txt_value);
        mTxtValue.setOnFocusChangeListener(this);

        mRowRarity = (TableRow)rootView.findViewById(R.id.row_rarity);
        mChkRarity = (CheckBox)rootView.findViewById(R.id.chk_rarity);
        mChkRarity.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mImgAttribute.setImageResource(R.drawable.attr_rarity);
                }
            }
        });
        mTxtRarity = (EditText)rootView.findViewById(R.id.txt_rarity);
        mTxtRarity.setOnFocusChangeListener(this);

        handleAttributesVisibility();

        return rootView;
    }

    private void onClickRandomize() {
        showToast(ResourceGenerator.get().getRandomToastMessage());
        MainApp.sHitsRandom++;
        if (MainApp.sHitsRandom == MainApp.ACHIEVEMENT_HIT_RANDOM) {
            showAchievementDialog(
                    R.string.achievement_dialog_10hits_title,
                    R.string.achievement_dialog_10hits_message,
                    ResourceGenerator.get().getRandomAchievementResponse());
        }

        int minLow = -20, minHigh = 10;
        int maxLow = 40, maxHigh = 100;

        int minValue = -1200, maxValue = 8500;

        if (mRowVitality.getVisibility() == View.VISIBLE) {
            mTxtVitalityMin.setText(Integer.toString(getRandomInt(minLow, minHigh)));
            mTxtVitalityMax.setText(Integer.toString(getRandomInt(maxLow, maxHigh)));
        }

        if (mRowStrength.getVisibility() == View.VISIBLE) {
            mTxtStrengthMin.setText(Integer.toString(getRandomInt(minLow, minHigh)));
            mTxtStrengthMax.setText(Integer.toString(getRandomInt(maxLow, maxHigh)));
        }

        if (mRowIntelligence.getVisibility() == View.VISIBLE) {
            mTxtIntelligenceMin.setText(Integer.toString(getRandomInt(minLow, minHigh)));
            mTxtIntelligenceMax.setText(Integer.toString(getRandomInt(maxLow, maxHigh)));
        }

        if (mRowDexterity.getVisibility() == View.VISIBLE) {
            mTxtDexterityMin.setText(Integer.toString(getRandomInt(minLow, minHigh)));
            mTxtDexterityMax.setText(Integer.toString(getRandomInt(maxLow, maxHigh)));
        }

        if (mRowValue.getVisibility() == View.VISIBLE) {
            mTxtValue.setText(Integer.toString(getRandomInt(minValue, maxValue)));
        }

        if (mRowRarity.getVisibility() == View.VISIBLE) {
            mTxtRarity.setTextColor(ResourceGenerator.get().getRandomItemColor());
            mTxtRarity.setText(ResourceGenerator.get().getRandomItemRarity());
        }
    }

    private void handleAttributesVisibility() {
        if (MainApp.getInstance().getCurrentCreationType() == null) {
            showItemAttributes();
            return;
        }

        switch (MainApp.getInstance().getCurrentCreationType()) {
            case HERO:
                showHeroAttributes();
                break;
            case MONSTER:
                showMonsterAttributes();
                break;
            case ITEM:
                showItemAttributes();
                break;
        }
    }

    private void showHeroAttributes() {
        mRowVitality.setVisibility(View.VISIBLE);
        mRowStrength.setVisibility(View.VISIBLE);
        mRowIntelligence.setVisibility(View.VISIBLE);
        mRowDexterity.setVisibility(View.VISIBLE);
        mRowDamage.setVisibility(View.VISIBLE);
        mRowValue.setVisibility(View.GONE);
        mRowRarity.setVisibility(View.GONE);
    }

    private void showItemAttributes() {
        mRowVitality.setVisibility(View.VISIBLE);
        mRowStrength.setVisibility(View.VISIBLE);
        mRowIntelligence.setVisibility(View.VISIBLE);
        mRowDexterity.setVisibility(View.VISIBLE);
        mRowDamage.setVisibility(View.VISIBLE);
        mRowValue.setVisibility(View.VISIBLE);
        mRowRarity.setVisibility(View.VISIBLE);
    }

    private void showMonsterAttributes() {
        mRowVitality.setVisibility(View.VISIBLE);
        mRowStrength.setVisibility(View.VISIBLE);
        mRowIntelligence.setVisibility(View.VISIBLE);
        mRowDexterity.setVisibility(View.VISIBLE);
        mRowDamage.setVisibility(View.VISIBLE);
        mRowValue.setVisibility(View.GONE);
        mRowRarity.setVisibility(View.GONE);
    }

    private int getRandomInt(int min, int max) {
        return mRandom.nextInt((max - min) + 1) + min;
    }

    private void onClickProceed() {
        if (mCallbacks != null) {
            mCallbacks.onClickProceed(
                    (FragmentId)getArguments().getSerializable(ActivityHome.ARG_FRAGMENT_ID));
        }
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

    @Override
    public void onClickSave() {
        Log.d(TAG, "Saving creation...");
        Creation current = MainApp.getInstance().getCurrentCreation();
        current.setRarity(mTxtRarity.getText().toString());
        showDebugToast("Saved rarity!");
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        switch (v.getId()) {
            case R.id.txt_vitality_min:
            case R.id.txt_vitality_max:
                mImgAttribute.setImageResource(R.drawable.attr_vitality);
                break;
            case R.id.txt_strength_min:
            case R.id.txt_strength_max:
                mImgAttribute.setImageResource(R.drawable.attr_strength);
                break;
            case R.id.txt_intelligence_min:
            case R.id.txt_intelligence_max:
                mImgAttribute.setImageResource(R.drawable.attr_intelligence);
                break;
            case R.id.txt_dexterity_min:
            case R.id.txt_dexterity_max:
                mImgAttribute.setImageResource(R.drawable.attr_dexterity);
                break;
            case R.id.txt_damage_min:
            case R.id.txt_damage_max:
                mImgAttribute.setImageResource(R.drawable.attr_damage);
                break;
            case R.id.txt_value:
                mImgAttribute.setImageResource(R.drawable.attr_value);
                break;
            case R.id.txt_rarity:
                mImgAttribute.setImageResource(R.drawable.attr_rarity);
                break;
        }
    }
}
