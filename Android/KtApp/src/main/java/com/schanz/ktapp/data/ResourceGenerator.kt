package com.schanz.ktapp.data

import java.util.Random

import android.graphics.Color
import android.util.Log

import com.schanz.ktapp.Constants
import com.schanz.ktapp.MainApp
import com.schanz.ktapp.R

class ResourceGenerator private constructor() {

    // Declare "static" methods and variables in the companion object.
    companion object {
        private val TAG: String = ResourceGenerator::class.java.simpleName

        // Special prefixes are not idiomatic in Kotlin. Refer to the official style guide:
        // https://developer.android.com/kotlin/style-guide#naming_2
        private val sSingleton: ResourceGenerator by lazy(LazyThreadSafetyMode.PUBLICATION) {
            ResourceGenerator()
        }

        @JvmStatic // For interoperability with existing Java code.
        fun get(): ResourceGenerator {
            return sSingleton
        }
    }

    init {
        initResources()
    }

    private fun initResources() {
        val res = MainApp.getInstance().resources
        mDialogResponses = res.getStringArray(R.array.common_dialog_confirmations)
        mToastMessages = res.getStringArray(R.array.common_toast_messages)
        mItemRarities = res.getStringArray(R.array.attr_default_rarities)
        mItemCurrencies = res.getStringArray(R.array.attr_default_currencies)
        mItemRarityColors = res.getStringArray(R.array.attr_rarity_colors)
        mAchievementResponses = res.getStringArray(R.array.achievement_dialog_confirmations)

        mNameHeroes = res.getStringArray(R.array.name_heroes)
        mNameMonsters = res.getStringArray(R.array.name_monsters)
        mNameItems = res.getStringArray(R.array.name_items)
        mNameModifiers = res.getStringArray(R.array.name_modifiers)
        mItemNameSuffixes = res.getStringArray(R.array.item_name_suffixes)

        mStoryNouns = res.getStringArray(R.array.story_nouns)
        mStoryBeginnings = res.getStringArray(R.array.story_beginnings)
        mStoryMiddles = res.getStringArray(R.array.story_middles)
        mStoryEndings = res.getStringArray(R.array.story_endings)
    }

    private lateinit var mDialogResponses: Array<String>
    private lateinit var mToastMessages: Array<String>
    private lateinit var mItemRarities: Array<String>
    private lateinit var mItemCurrencies: Array<String>
    private lateinit var mItemRarityColors: Array<String>
    private lateinit var mAchievementResponses: Array<String>

    private lateinit var mNameModifiers: Array<String>
    private lateinit var mItemNameSuffixes: Array<String>

    // Creation Names
    private lateinit var mNameHeroes: Array<String>
    private lateinit var mNameItems: Array<String>
    private lateinit var mNameMonsters: Array<String>

    // Creation Stories
    private lateinit var mStoryNouns: Array<String>
    private lateinit var mStoryBeginnings: Array<String>
    private lateinit var mStoryMiddles: Array<String>
    private lateinit var mStoryEndings: Array<String>

    var rand: Random = Random()

    /**
     * @return A random String response from the dialog responses array.
     */
    fun getRandomDialogResponse(): String =
            mDialogResponses[rand.nextInt(mDialogResponses.size)]

    /**
     * @return A random String from the toast messages array.
     */
    fun getRandomToastMessage(): String =
            mToastMessages[rand.nextInt(mToastMessages.size)]

    /**
     * @return A random String rarity from the default rarities array.
     */
    fun getRandomItemRarity(): String =
            mItemRarities[rand.nextInt(mItemRarities.size)]

    /**
     * @return A random item rarity color.
     */
    fun getRandomItemColor(): Int {
        val randIndex = rand.nextInt(mItemRarityColors.size)
        return Color.parseColor(mItemRarityColors[randIndex])
    }

    /**
     * @return A random String currency from the default currencies array.
     */
    fun getRandomItemCurrency(): String =
            mItemCurrencies[rand.nextInt(mItemCurrencies.size)]

    /**
     * @return A random achievement from the default achievements array.
     */
    fun getRandomAchievementResponse(): String =
            mAchievementResponses[rand.nextInt(mAchievementResponses.size)]

    fun generateRandomCreationName(type: Constants.CreationType?): String {
        when (type) {
            Constants.CreationType.HERO -> return getRandomHeroName()
            Constants.CreationType.ITEM -> return getRandomItemName()
            Constants.CreationType.MONSTER -> return getRandomMonsterName()
            else -> Log.w(TAG, "Invalid CreationType")
        }
        return ""
    }

    private fun getRandomHeroName(): String {
        val name = mNameHeroes[rand.nextInt(mNameHeroes.size)]
        val modifier = mNameModifiers[rand.nextInt(mNameModifiers.size)]
        return "$name the $modifier"
    }

    private fun getRandomItemName(): String {
        val name = mNameItems[rand.nextInt(mNameItems.size)]
        val prefix = mNameModifiers[rand.nextInt(mNameModifiers.size)]
        val suffix = mItemNameSuffixes[rand.nextInt(mItemNameSuffixes.size)]
        return "$prefix $name $suffix"
    }

    private fun getRandomMonsterName(): String {
        val monster = mNameMonsters[rand.nextInt(mNameMonsters.size)]
        val modifier = mNameModifiers[rand.nextInt(mNameModifiers.size)]
        return "The $modifier $monster"
    }

    fun generateRandomStory(): String {
        val builder = StringBuilder()
        builder.append(getRandomStoryBeginning())
        builder.append(" ")
        builder.append(getRandomStoryMiddle())
        builder.append(" ")
        builder.append(getRandomStoryEnding())
        return builder.toString()
    }

    private fun getRandomStoryBeginning(): String {
        var randIndex = rand.nextInt(mStoryNouns.size)
        val randomNoun = mStoryNouns[randIndex]
        randIndex = rand.nextInt(mStoryBeginnings.size)
        return String.format(mStoryBeginnings[randIndex], randomNoun)
    }

    private fun getRandomStoryMiddle(): String {
        var randIndex = rand.nextInt(mStoryNouns.size)
        val randomNoun = mStoryNouns[randIndex]
        randIndex = rand.nextInt(mStoryMiddles.size)
        return String.format(mStoryMiddles[randIndex], randomNoun)
    }

    private fun getRandomStoryEnding(): String {
        var randIndex = rand.nextInt(mStoryNouns.size)
        val randomNoun = mStoryNouns[randIndex]
        randIndex = rand.nextInt(mStoryEndings.size)
        return String.format(mStoryEndings[randIndex], randomNoun)
    }
}
