/**
 * 
 */
package com.luchoct.basket.service;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

import com.luchoct.basket.dto.CatalogItem;
import com.luchoct.basket.service.discount.IDiscount;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import com.luchoct.basket.dto.Catalog;

/**
 * @author Luis
 * 
 */
public class ShoppingService {

	private static final Logger logger = Logger.getLogger(ShoppingService.class);

	private static final NumberFormat currencyFormat = NumberFormat
			.getCurrencyInstance(Locale.UK);

	/**
	 * The separator of the id of the item and the id of the container in a full
	 * shopping list process.
	 */
	public static final String CONTAINER_SEPARATOR = "-";

	/**
	 * The catalog of items and containers.
	 */
	@Setter
	@Getter
	private Catalog catalog;

	/**
	 * The available discounts.
	 */
	@Setter
	@Getter
	private Set<IDiscount> discounts;

	/**
	 * It processes a shopping list.
	 * @param idsItems The ids of items of the shopping list.
	 */
	public float processShoppingList(List<String> idsItems)
			throws ItemsException {
		checkItems(idsItems);

		// We create a list of basket items.
		List<CatalogItem> basketItems = idsItems.stream()
				.map(idItem -> catalog.getAvailableItems().get(idItem))
				.collect(Collectors.toList());

		// We calculate the subtotal.
		float subtotalPrice = getSubtotalPrice(basketItems);

		// We apply the discounts.
		return getTotalPrice(basketItems, subtotalPrice);
	}

	/**
	 * It returns the subtotal price, before applying discounts.
	 * @param basketItems List of items of the basket.
	 * @return Subtotal price.
	 */
	private float getSubtotalPrice(List<CatalogItem> basketItems) {
		float subTotalPrice = 0;
		for (CatalogItem item : basketItems) {
			System.out.println(item.getTicketDescription() + ": "
					+ item.getId() + ": "
					+ currencyFormat.format(item.getPrice()));
			subTotalPrice = subTotalPrice + item.getPrice();
		}
		System.out.println("Subtotal: " + currencyFormat.format(subTotalPrice));
		return subTotalPrice;
	}

	/**
	 * It returns the total price of the basket, after applying the discounts of the catalog.
	 * @param basketItems The items of the basket.
	 * @param subtotal The subtotal.
	 * @return The total of the purchase.
	 */
	private float getTotalPrice(List<CatalogItem> basketItems, float subtotal) {
		float totalPrice = subtotal;
		for (IDiscount discount : discounts) {
			float amount = discount.getValue(basketItems);
			if (amount > 0.000001) {
				System.out.println(discount.getTicketDescription() + ": "
						+ currencyFormat.format(amount));
				totalPrice = totalPrice - amount;
			}
		}
		if (totalPrice == subtotal) {
			System.out.println("(No offers available)");
		}
		System.out.println("Total price: " + currencyFormat.format(totalPrice));
		return totalPrice;
	}

	/**
	 * It checks if the ids match the catalog.
	 * @param idsItems The ids of the items.
	 * @throws ItemsException Exception if there are ids wrong.
	 */
	private void checkItems(List<String> idsItems) throws ItemsException {
		List<String> wrongIds = idsItems.stream()
				.filter(idItem -> !catalog.getAvailableItems().containsKey(idItem))
				.collect(Collectors.toList());

		if (!wrongIds.isEmpty()) {
			throw new ItemsException(wrongIds, "Ids of items don't found in the catalog. ");
		}
	}
}
