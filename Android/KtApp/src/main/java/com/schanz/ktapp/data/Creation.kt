package com.schanz.ktapp.data

import androidx.annotation.DrawableRes
import com.schanz.ktapp.Constants

class Creation {

    var creationType: Constants.CreationType? = null
    var damageType: Constants.DamageType? = null
    var rangeType: Constants.RangeType? = null

    /**
     * Name of the `Creation`. Example: "Bob".
     */
    var name: String? = null

    /**
     * Rarity of the `Creation`. Examples: Legendary, Rare, Common.
     */
    var rarity: String? = null

    /**
     * Background story (flavor text) for the `Creation`.
     */
    var story: String? = null

    /**
     * Drawable image resource that visually represents the `Creation`.
     */
    @DrawableRes
    var imageResource: Int = 0

    var vitality: Int = 0
    var strength: Int = 0
    var intelligence: Int = 0
    var dexterity: Int = 0
    var value: Int = 0

    var damageMax: Int = 0
    var damageMin: Int = 0

    var health: Int = 0
    var mana: Int = 0
}
