package com.schanz.lootgen.data;

import com.schanz.lootgen.Constants.CreationType;
import com.schanz.lootgen.Constants.DamageType;
import com.schanz.lootgen.Constants.RangeType;

import androidx.annotation.DrawableRes;

public class Creation {

    private CreationType mCreationType;
    private DamageType mDamageType;
    private RangeType mRangeType;

    /**
     * Name of the {@code Creation}. Example: "Bob".
     */
    private String mName;

    /**
     * Rarity of the {@code Creation}. Examples: Legendary, Rare, Common.
     */
    private String mRarity;

    /**
     * Background story (flavor text) for the {@code Creation}.
     */
    private String mStory;

    /**
     * Drawable image resource that visually represents the {@code Creation}.
     */
    @DrawableRes
    private int mImageResource;

    private int mVitality;
    private int mStrength;
    private int mIntelligence;
    private int mDexterity;
    private int mValue;

    private int mDamageMax;
    private int mDamageMin;

    private int mHealth;
    private int mMana;

    public Creation() {

    }

    public CreationType getCreationType() {
        return mCreationType;
    }

    public void setCreationType(CreationType creationType) {
        mCreationType = creationType;
    }

    public DamageType getDamageType() {
        return mDamageType;
    }

    public void setDamageType(DamageType damageType) {
        mDamageType = damageType;
    }

    public RangeType getRangeType() {
        return mRangeType;
    }

    public void setRangeType(RangeType rangeType) {
        mRangeType = rangeType;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRarity() {
        return mRarity;
    }

    public void setRarity(String rarity) {
        mRarity = rarity;
    }

    @DrawableRes
    public int getImageResource() {
        return mImageResource;
    }

    public void setImageResource(@DrawableRes int imageResource) {
        mImageResource = imageResource;
    }

    public int getVitality() {
        return mVitality;
    }

    public void setVitality(int vitality) {
        mVitality = vitality;
    }

    public int getStrength() {
        return mStrength;
    }

    public void setStrength(int strength) {
        mStrength = strength;
    }

    public int getIntelligence() {
        return mIntelligence;
    }

    public void setIntelligence(int intelligence) {
        mIntelligence = intelligence;
    }

    public int getDexterity() {
        return mDexterity;
    }

    public void setDexterity(int dexterity) {
        mDexterity = dexterity;
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getDamageMax() {
        return mDamageMax;
    }

    public void setDamageMax(int damageMax) {
        mDamageMax = damageMax;
    }

    public int getDamageMin() {
        return mDamageMin;
    }

    public void setDamageMin(int damageMin) {
        mDamageMin = damageMin;
    }

    public int getHealth() {
        return mHealth;
    }

    public void setHealth(int health) {
        mHealth = health;
    }

    public int getMana() {
        return mMana;
    }

    public void setMana(int mana) {
        mMana = mana;
    }

    public String getStory() {
        return mStory;
    }

    public void setStory(String story) {
        mStory = story;
    }
}
