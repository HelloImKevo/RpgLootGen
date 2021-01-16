package com.schanz.ktapp.data;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;

import com.schanz.ktapp.Constants.CreationType;
import com.schanz.ktapp.MainApp;
import com.schanz.ktapp.R;

import java.util.Random;

public class ResourceGenerator {

    private static final String TAG = ResourceGenerator.class.getSimpleName();

    private static ResourceGenerator sSingleton;

    public static ResourceGenerator get() {
        if (sSingleton != null) {
            return sSingleton;
        } else {
            sSingleton = new ResourceGenerator();
            return sSingleton;
        }
    }

    private ResourceGenerator() {
        initResources();
    }

    private void initResources() {
        Resources res = MainApp.getInstance().getResources();
        mDialogResponses =
                res.getStringArray(R.array.common_dialog_confirmations);
        mToastMessages =
                res.getStringArray(R.array.common_toast_messages);
        mItemRarities =
                res.getStringArray(R.array.attr_default_rarities);
        mItemCurrencies =
                res.getStringArray(R.array.attr_default_currencies);
        mItemRarityColors =
                res.getStringArray(R.array.attr_rarity_colors);
        mAchievementResponses =
                res.getStringArray(R.array.achievement_dialog_confirmations);

        mNameHeroes =
                res.getStringArray(R.array.name_heroes);
        mNameMonsters =
                res.getStringArray(R.array.name_monsters);
        mNameItems =
                res.getStringArray(R.array.name_items);
        mNameModifiers =
                res.getStringArray(R.array.name_modifiers);
        mItemNameSuffixes =
                res.getStringArray(R.array.item_name_suffixes);

        mStoryNouns =
                res.getStringArray(R.array.story_nouns);
        mStoryBeginnings =
                res.getStringArray(R.array.story_beginnings);
        mStoryMiddles =
                res.getStringArray(R.array.story_middles);
        mStoryEndings =
                res.getStringArray(R.array.story_endings);
    }

    private String[] mDialogResponses;
    private String[] mToastMessages;
    private String[] mItemRarities;
    private String[] mItemCurrencies;
    private String[] mItemRarityColors;
    private String[] mAchievementResponses;

    private String[] mNameModifiers;
    private String[] mItemNameSuffixes;

    private String[] mNameHeroes;
    private String[] mNameItems;
    private String[] mNameMonsters;

    private String[] mStoryNouns;
    private String[] mStoryBeginnings;
    private String[] mStoryMiddles;
    private String[] mStoryEndings;

    Random rand = new Random();

    /**
     * @return A random String response from the dialog responses array.
     */
    public String getRandomDialogResponse() {
        int randIndex = rand.nextInt(mDialogResponses.length);
        return mDialogResponses[randIndex];
    }

    /**
     * @return A random String from the toast messages array.
     */
    public String getRandomToastMessage() {
        int randIndex = rand.nextInt(mToastMessages.length);
        return mToastMessages[randIndex];
    }

    /**
     * @return A random String rarity from the default rarities array.
     */
    public String getRandomItemRarity() {
        int randIndex = rand.nextInt(mItemRarities.length);
        return mItemRarities[randIndex];
    }

    /**
     * @return A random item rarity color.
     */
    public int getRandomItemColor() {
        int randIndex = rand.nextInt(mItemRarityColors.length);
        return Color.parseColor(mItemRarityColors[randIndex]);
    }

    /**
     * @return A random String currency from the default currencies array.
     */
    public String getRandomItemCurrency() {
        int randIndex = rand.nextInt(mItemCurrencies.length);
        return mItemCurrencies[randIndex];
    }

    /**
     * @return A random String currency from the default currencies array.
     */
    public String getRandomAchievementResponse() {
        int randIndex = rand.nextInt(mAchievementResponses.length);
        return mAchievementResponses[randIndex];
    }

    public String generateRandomCreationName(CreationType type) {
        switch (type) {
            case HERO:
                return getRandomHeroName();
            case ITEM:
                return getRandomItemName();
            case MONSTER:
                return getRandomMonsterName();
            default:
                Log.w(TAG, "Invalid CreationType");
                break;
        }
        return "";
    }

    public String getRandomHeroName() {
        String name = mNameHeroes[rand.nextInt(mNameHeroes.length)];
        String modifier = mNameModifiers[rand.nextInt(mNameModifiers.length)];
        return name + " the " + modifier;
    }

    public String getRandomItemName() {
        String name = mNameItems[rand.nextInt(mNameItems.length)];
        String prefix = mNameModifiers[rand.nextInt(mNameModifiers.length)];
        String suffix = mItemNameSuffixes[rand.nextInt(mItemNameSuffixes.length)];
        return prefix + " " + name + " " + suffix;
    }

    public String getRandomMonsterName() {
        String monster = mNameMonsters[rand.nextInt(mNameMonsters.length)];
        String modifier = mNameModifiers[rand.nextInt(mNameModifiers.length)];
        return "The " + modifier + " " + monster;
    }

    public String generateRandomStory() {
        StringBuilder builder = new StringBuilder();
        builder.append(getRandomStoryBeginning());
        builder.append(" ");
        builder.append(getRandomStoryMiddle());
        builder.append(" ");
        builder.append(getRandomStoryEnding());
        return builder.toString();
    }

    public String getRandomStoryBeginning() {
        int randIndex = rand.nextInt(mStoryNouns.length);
        String randomNoun = mStoryNouns[randIndex];

        randIndex = rand.nextInt(mStoryBeginnings.length);
        return String.format(mStoryBeginnings[randIndex], randomNoun);
    }

    public String getRandomStoryMiddle() {
        int randIndex = rand.nextInt(mStoryNouns.length);
        String randomNoun = mStoryNouns[randIndex];

        randIndex = rand.nextInt(mStoryMiddles.length);
        return String.format(mStoryMiddles[randIndex], randomNoun);
    }

    public String getRandomStoryEnding() {
        int randIndex = rand.nextInt(mStoryNouns.length);
        String randomNoun = mStoryNouns[randIndex];

        randIndex = rand.nextInt(mStoryEndings.length);
        return String.format(mStoryEndings[randIndex], randomNoun);
    }
}
