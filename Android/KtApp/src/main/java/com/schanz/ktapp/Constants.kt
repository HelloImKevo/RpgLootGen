package com.schanz.ktapp

class Constants {
    enum class CreationType {
        HERO,
        MONSTER,
        ITEM
    }

    enum class DamageType {
        SLASH, PIERCE, MAGIC
    }

    enum class RangeType {
        MELEE, RANGED
    }

    enum class ItemType {
        WEAPON, AMMO, SPELL, ARMOR, FOOD, POTION
    }
}
