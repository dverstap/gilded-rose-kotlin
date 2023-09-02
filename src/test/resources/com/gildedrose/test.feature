Feature: Kotlin

  Scenario Outline: Original test fixture
    Given an item with "<name>", <sellIn> and <quality>
    When i go forward 1 day
    Then the item will have quality <newQuality> and should be sold in <newSellIn>
    Examples:
      | name                                      | sellIn | quality | newSellIn | newQuality |
      | +5 Dexterity Vest                         | 10     | 20      | 9         | 19         |
      | Aged Brie                                 | 2      | 0       | 1         | 1          |
      | Elixir of the Mongoose                    | 5      | 7       | 4         | 6          |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      | 0         | 80         |
      | Sulfuras, Hand of Ragnaros                | -1     | 80      | -1        | 80         |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      | 14        | 21         |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 49      | 9         | 50         |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 49      | 4         | 50         |
      #| Conjured Mana Cake                        | 3      | 6       | -100    | -100     | TODO

  Scenario Outline: Five days
    Given an item with "<name>", <sellIn> and <quality>
    When i go forward 5 days
    Then the item will have quality <newQuality> and should be sold in <newSellIn>
    Examples:
      | name                                      | sellIn | quality | newSellIn | newQuality |
      | +5 Dexterity Vest                         | 10     | 20      | 5         | 15         |
      | Aged Brie                                 | 2      | 0       | -3        | 8          |
      | Elixir of the Mongoose                    | 5      | 7       | 0         | 2          |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      | 0         | 80         |
      | Sulfuras, Hand of Ragnaros                | -1     | 80      | -1        | 80         |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      | 10        | 25         |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 49      | 5         | 50         |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 49      | 0         | 50         |
      #| Conjured Mana Cake                        | 3      | 6       | -100    | -100     | TODO

  Scenario Outline: Ten days
    Given an item with "<name>", <sellIn> and <quality>
    When i go forward 10 days
    Then the item will have quality <newQuality> and should be sold in <newSellIn>
    Examples:
      | name                                      | sellIn | quality | newSellIn | newQuality |
      | +5 Dexterity Vest                         | 10     | 20      | 0         | 10         |
      | Aged Brie                                 | 2      | 0       | -8        | 18         |
      | Elixir of the Mongoose                    | 5      | 7       | -5        | 0          |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      | 0         | 80         |
      | Sulfuras, Hand of Ragnaros                | -1     | 80      | -1        | 80         |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      | 5         | 35         |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 49      | 0         | 50         |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 49      | -5        | 0          |
      #| Conjured Mana Cake                        | 3      | 6       | -100    | -100     | TODO

  Scenario Outline: Fifteen days
    Given an item with "<name>", <sellIn> and <quality>
    When i go forward 15 days
    Then the item will have quality <newQuality> and should be sold in <newSellIn>
    Examples:
      | name                                      | sellIn | quality | newSellIn | newQuality |
      | +5 Dexterity Vest                         | 10     | 20      | -5        | 0          |
      | Aged Brie                                 | 2      | 0       | -13       | 28         |
      | Elixir of the Mongoose                    | 5      | 7       | -10       | 0          |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      | 0         | 80         |
      | Sulfuras, Hand of Ragnaros                | -1     | 80      | -1        | 80         |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      | 0         | 50         |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 49      | -5        | 0          |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 49      | -10       | 0          |
      #| Conjured Mana Cake                        | 3      | 6       | -100    | -100     | TODO

  Scenario Outline: Twenty days
    Given an item with "<name>", <sellIn> and <quality>
    When i go forward 20 days
    Then the item will have quality <newQuality> and should be sold in <newSellIn>
    Examples:
      | name                                      | sellIn | quality | newSellIn | newQuality |
      | +5 Dexterity Vest                         | 10     | 20      | -10       | 0          |
      | Aged Brie                                 | 2      | 0       | -18       | 38         |
      | Elixir of the Mongoose                    | 5      | 7       | -15       | 0          |
      | Sulfuras, Hand of Ragnaros                | 0      | 80      | 0         | 80         |
      | Sulfuras, Hand of Ragnaros                | -1     | 80      | -1        | 80         |
      | Backstage passes to a TAFKAL80ETC concert | 15     | 20      | -5        | 0          |
      | Backstage passes to a TAFKAL80ETC concert | 10     | 49      | -10       | 0          |
      | Backstage passes to a TAFKAL80ETC concert | 5      | 49      | -15       | 0          |
      #| Conjured Mana Cake                        | 3      | 6       | -100    | -100     | TODO
