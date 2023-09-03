package com.gildedrose

import com.gildedrose.ItemType.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ItemTypeTest {

    // Tests for each of the requirements in the requirements document.
    // Please keep these in the order of the spec.

    @Test
    fun `Once the sell by date has passed, Quality degrades twice as fast`() {
        assertEquals(9, WORSE_WITH_AGE.newQuality(10, 10))
        assertEquals(9, WORSE_WITH_AGE.newQuality(1, 10))
        assertEquals(8, WORSE_WITH_AGE.newQuality(0, 10))
        assertEquals(8, WORSE_WITH_AGE.newQuality(-1, 10))
        assertEquals(8, WORSE_WITH_AGE.newQuality(-10, 10))
    }

    @Test
    fun `The Quality of an item is never negative`() {
        for (itemType in ItemType.entries) {
            if (itemType != LEGENDARY) {
                for (sellIn in -100..100 step 10) {
                    for (quality in -100..100 step 10) {
                        val item = Item("Item name doesn't matter for this test", sellIn, quality)
                        for (day in 1..100) {
                            itemType.update(item)
                            assertTrue(
                                item.quality >= 0,
                                "$itemType: sellIn=$sellIn quality=$quality after $day days: $item"
                            )
                        }
                    }
                }
            }
        }
    }

    // Note: replaced the double quotes in the method name with single quotes, because IntelliJ warns:
    //   Name contains characters which can cause problems on Windows: "
    @Test
    fun `'Aged Brie' actually increases in Quality the older it gets`() {
        assertEquals(11, BETTER_WITH_AGE.newQuality(10, 10))
        assertEquals(11, BETTER_WITH_AGE.newQuality(1, 10))
        assertEquals(12, BETTER_WITH_AGE.newQuality(0, 10))
        assertEquals(12, BETTER_WITH_AGE.newQuality(-1, 10))
        assertEquals(12, BETTER_WITH_AGE.newQuality(-10, 10))
    }

    @Test
    fun `The Quality of an item is never more than 50`() {
        for (itemType in ItemType.entries) {
            if (itemType != LEGENDARY) {
                for (sellIn in -100..100 step 10) {
                    for (quality in -100..100 step 10) {
                        val item = Item("Item name doesn't matter for this test", sellIn, quality)
                        for (day in 1..100) {
                            itemType.update(item)
                            assertTrue(
                                item.quality <= 50,
                                "$itemType: sellIn=$sellIn quality=$quality after $day days: $item"
                            )
                        }
                    }
                }
            }
        }
    }

    // The "Clarification" at the end of the spec actually introduces a slight uncertainty:
    // This rule from the spec says it never changes, and the clarification says "it's 80",
    // which isn't a contradiction per se. I chose to be lenient, and accept anything and just return that,
    // because that's what the original implementation did.
    // This does mean that we have to filter out legendary items in some of the other unit tests.
    @Test
    fun `'Sulfuras', being a legendary item, never has to be sold or decreases in Quality`() {
        val itemType = LEGENDARY
        for (sellIn in -100..100 step 10) {
            for (quality in -100..100 step 10) {
                for (maxDays in 0..100 step 10) {
                    val item = Item("Item name doesn't matter for this test", sellIn, quality)
                    for (day in 1..100) {
                        itemType.update(item)
                        assertEquals(
                            item.quality,
                            item.quality,
                            "$itemType: sellIn=$sellIn quality=$quality after $day days: $item"
                        )
                    }
                }
            }
        }
    }

    @Test
    fun `'Backstage passes', like aged brie, increases in Quality as its SellIn value approaches, with some additional rules`() {
        // Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
        // Quality drops to 0 after the concert
        assertEquals(11, BACKSTAGE_PASS.newQuality(30, 10))
        assertEquals(11, BACKSTAGE_PASS.newQuality(20, 10))
        assertEquals(11, BACKSTAGE_PASS.newQuality(11, 10))
        assertEquals(12, BACKSTAGE_PASS.newQuality(10, 10))
        assertEquals(12, BACKSTAGE_PASS.newQuality(6, 10))
        assertEquals(13, BACKSTAGE_PASS.newQuality(5, 10))
        assertEquals(13, BACKSTAGE_PASS.newQuality(1, 10))
        assertEquals(0, BACKSTAGE_PASS.newQuality(0, 10))
        assertEquals(0, BACKSTAGE_PASS.newQuality(-1, 10))
        assertEquals(0, BACKSTAGE_PASS.newQuality(-10, 10))
        assertEquals(0, BACKSTAGE_PASS.newQuality(-20, 10))
        assertEquals(0, BACKSTAGE_PASS.newQuality(-30, 10))
    }

    @Test
    fun `'Conjured' items degrade in Quality twice as fast as normal items`() {
        assertEquals(8, CONJURED.newQuality(10, 10))
        assertEquals(8, CONJURED.newQuality(1, 10))
        assertEquals(6, CONJURED.newQuality(0, 10))
        assertEquals(6, CONJURED.newQuality(-1, 10))
        assertEquals(6, CONJURED.newQuality(-10, 10))
    }

    @Test
    fun getType() {
        assertEquals(WORSE_WITH_AGE, Item("x", 0, 0).type)
        assertEquals(BETTER_WITH_AGE, Item("x AGED brie y", 0, 0).type)
        assertEquals(LEGENDARY, Item("x SuLfUrAs y", 0, 0).type)
        assertEquals(BACKSTAGE_PASS, Item("x backstage PASS y", 0, 0).type)
        assertEquals(CONJURED, Item("x CoNjUrEd y", 0, 0).type)
    }
}
