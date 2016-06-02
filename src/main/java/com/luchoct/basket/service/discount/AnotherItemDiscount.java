/**
 * 
 */
package com.luchoct.basket.service.discount;

import java.util.List;
import java.util.stream.Collectors;

import com.luchoct.basket.dto.CatalogItem;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.log4j.Logger;

/**
 * A discount that is applied only when a specific number of containers of an
 * item are bought, and it's applied with a percentage of discount over the
 * price of another container of another item.
 * Every n-containers of one item you buy, you have right to have a discount over
 * 1 container of another item.
 * @author Luis
 * 
 */
@Data
public class AnotherItemDiscount implements IDiscount {

	private static final Logger logger = Logger.getLogger(AnotherItemDiscount.class);

	/**
	 * The description
	 */
	private String description;

	/**
	 * The description to use in the ticket.
	 */
	private String ticketDescription;

	/**
	 * The item required to apply the discount.
	 */
	private CatalogItem itemRequired;

	/**
	 * The number of items required to apply the discount.
	 */
	private int numItemsRequired;

	/**
	 * The item to which applying the discount over.
	 */
	private CatalogItem itemDiscounted;

	/**
	 * The percentage of discount to apply.
	 */
	@Setter(AccessLevel.NONE)
	private double percentage;

	/**
	 * It sets the percentage of discount to apply.
	 * @param percentage The percentage to set
	 */
	public void setPercentage(double percentage) {
		this.percentage = percentage;
		assert ((percentage >= 0) && (percentage <= 100));
	}

	private long getTimesToApply(List<CatalogItem> basket) {
		long numItemsFound = basket.stream().filter(item -> item.equals(itemRequired)).count();
		return numItemsFound / numItemsRequired;
	}

	@Override
	/**
	 * {@inheritDoc}
	 */
	public float getValue(List<CatalogItem> basket) {
		long numTimesDiscount = getTimesToApply(basket);

		List<CatalogItem> itemsToDiscount = basket.stream()
				.filter(item -> item.equals(itemDiscounted))
				.limit(numTimesDiscount).collect(Collectors.toList());

		float discount = (float) itemsToDiscount.stream()
				.mapToDouble(item -> item.getPrice() * percentage / 100)
				.sum();

		if (logger.isDebugEnabled()) {
			logger.debug("Discount over items " + itemDiscounted.getId() + " is "
					+ discount);
		}
		return discount;
	}

}
