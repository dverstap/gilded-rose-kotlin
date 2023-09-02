import com.gildedrose.GildedRose
import com.gildedrose.Item
import io.cucumber.java8.En
import org.junit.jupiter.api.Assertions.assertEquals
import java.util.stream.IntStream

@Suppress("unused")
class LambdaStepDefinitions : En {

    private var gildedRose: GildedRose? = null;

    init {

        Given("an item with {string}, {int} and {int}") { name: String, sellIn: Int, quality: Int ->
            gildedRose = GildedRose(listOf(Item(name, sellIn, quality)))
        }

        When("i go forward 1 day") {
            gildedRose!!.updateQuality()
        }

        When("i go forward {int} days") { days: Int ->
            for (i in IntStream.range(0, days)) {
                gildedRose!!.updateQuality()
            }
        }

        Then(
            "the item will have quality {int} and should be sold in {int}"
        ) { expectedQuality: Int, expectedSellIn: Int ->
            val item = gildedRose!!.items[0]
            assertEquals(expectedSellIn, item.sellIn, item.name)
            assertEquals(expectedQuality, item.quality, item.name)
        }

    }
}
