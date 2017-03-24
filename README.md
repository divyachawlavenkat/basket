# Basket
## Functional Specification: Pricing a basket
Write a program and associated unit tests that can price a basket of goods taking into account some special offers.

The goods that can be purchased, together with their normal prices are:
* Soup – 65p per tin
* Bread – 80p per loaf
* Milk – £1.30 per bottle
* Apples – £1.00 per bag

Current special offers:
* Apples have a 10% discount off their normal price this week
* Buy 2 tins of soup and get a loaf of bread for half price

The program should accept a list of items in the basket and output the subtotal, the special offer discounts and the final price.

Input should be via the command line in the form *PriceBasket item1 item2 item3 ...*

For example:
```
PriceBasket Apple Milk Bread
```

Output should be to the console, for example:
```
Subtotal: £3.10
Apples 10% off: -10p
Total: £3.00
```

If no special offers are applicable the code should output:
```
Subtotal: £1.30
(No offers available)
Total price: £1.30
```

The code and design should meet these requirements, but be sufficiently flexible to allow future changes to the product list and/or discounts applied.

The code should be well structured, commented, have error handling and be tested.

# Run
run `java -jar PriceBasket.jar --help` or `java -jar PriceBasket.jar PriceBasket` to get more information.
```
Usage PriceBasket lis_of_ids
list_of_ids is a list of ids of items of the catalog, separated by spaces.
Available ids are: Soup, Bread, Milk, Apple
```

# Technical Specification
This is a maven project (tested with Maven 3.0.5).

# Changelog
* 2013-12-10 Version 1.0 Initial Release
* 2014-12-10 Version 1.1
  * Upgraded to Spring 4 from Spring 3.
  * Upgraded to Java 8 from Java 6


# Areas of improvement
* Use slf4j rather than log4j
* Replace tests on ShoppingServiceTest by actual unit tests using Mockito.


