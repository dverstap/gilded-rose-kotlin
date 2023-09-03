package com.gildedrose

class GildedRose(var items: List<Item>) {

    fun updateQuality() {
        for (item in items) {
            // Without the update() extension function, this would look a bit silly:
            //   item.type.update(item)
            item.update()
        }
    }

}
