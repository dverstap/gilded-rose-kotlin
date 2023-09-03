package com.gildedrose

import kotlin.math.max
import kotlin.math.min

// There are alternatives to an enum, mainly a class hierarchy.
// IntelliJ even has a "Convert to sealed class" intention for this,
// which even generates objects for the leaves of the class hierarchy.
// I choose to stick with an enum because:
// - KISS
// - In a "real" application, with an actual database and an ORM,
//   items probably would have the type as a column, and then an enum is the simpler solution.
enum class ItemType {

    WORSE_WITH_AGE {
        override fun newQuality(sellIn: Int, quality: Int): Int {
            return if (sellIn > 0) quality - 1 else quality - 2;
        }
    },
    BETTER_WITH_AGE {
        override fun newQuality(sellIn: Int, quality: Int): Int {
            // This isn't explicit in the requirements, but it appears that for Aged Brie,
            // quality increases twice as fast past the sellBy date.
            // At least that's what the old implementation did.
            // TODO Consult with my team lead and/or a domain expert about this:
            // On my first day in this business, I'm not qualified to make this business decision
            // (except then for staying compatible with what the old code did).
            return if (sellIn > 0) quality + 1 else quality + 2
        }
    },
    LEGENDARY {
        override fun update(item: Item) {
            // Do nothing: that's literally the requirement.
        }
    },
    BACKSTAGE_PASS {
        override fun newQuality(sellIn: Int, quality: Int): Int {
            return if (sellIn <= 0)
                0
            else if (sellIn <= 5)
                quality + 3
            else if (sellIn <= 10)
                quality + 2
            else
                quality + 1
        }
    },
    CONJURED {
        override fun newQuality(sellIn: Int, quality: Int): Int {
            return if (sellIn > 0) quality - 2 else quality - 4;
        }
    },
    ;

    open fun update(item: Item) {
        val newQuality = newQuality(item.sellIn, item.quality)
        item.quality = max(0, min(newQuality, 50))
        item.sellIn = item.sellIn - 1
    }

    // This method is internal, which it easier to write some tests
    // (important because of the goblin's preferences).
    internal open fun newQuality(sellIn: Int, quality: Int): Int {
        throw UnsupportedOperationException("newQuality is not supported for: $this")
    }

}

// Kotlin's extension properties function are a great workaround
// for the goblin's issues about shared code ownership:

val Item.type: ItemType
    get() {
        // An alternative would be to have a matches method in ItemType,
        // and then do something like this:
        /*
        for (type in ItemType.entries) {
            if (type != ItemType.NORMAL) {
                if (type.matches(this.name)) {
                    return type;
                }
            }
        }
        return ItemType.NORMAL;
        */
        // But keeping it simple for now.

        // I've made determining the type a bit lenient:
        // - I'm ignoring casing, because we wouldn't want the wrong rules to be applied if somebody
        //   accidentally entered 'aged brie' into the items instead of 'Aged Brie'.
        // - Using contains() instead of equality.
        // TODO: Discuss in code review / with a domain expert

        // The spec mentions "Sulfuras", but the original code checked for "Sulfuras, Hand of Ragnaros".
        return if (name.contains("sulfuras", ignoreCase = true))
            ItemType.LEGENDARY
        else if (name.contains("aged brie", ignoreCase = true))
            ItemType.BETTER_WITH_AGE
        // The spec mentions "Backstage passes", but the original code checked for "Backstage passes to a TAFKAL80ETC concert".
        // Following the spec.
        // Also checking for a single pass, not just multiple:
        // certainly a single pass is just as worthless after a concert as a set of passes.
        else if (name.contains("backstage pass", ignoreCase = true))
            ItemType.BACKSTAGE_PASS
        else if (name.contains("conjured", ignoreCase = true))
            ItemType.CONJURED
        else
            ItemType.WORSE_WITH_AGE
    }

fun Item.update() {
    type.update(this)
}
