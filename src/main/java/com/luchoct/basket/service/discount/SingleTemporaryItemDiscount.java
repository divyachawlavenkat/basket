/**
 * 
 */
package com.luchoct.basket.service.discount;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.luchoct.basket.dto.CatalogItem;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.apache.log4j.Logger;

/**
 * A discount applicable to all items with the same id, during a period.
 * @author Luis
 * 
 */
@Data
public class SingleTemporaryItemDiscount implements IDiscount {

	private static final Logger logger = Logger.getLogger(SingleTemporaryItemDiscount.class);

	/**
	 * The description.
	 */
	private String description;

	/**
	 * The description to use in tickets.
	 */
	private String ticketDescription;

	/**
	 * The item to discount.
	 */
	private CatalogItem item;

	/**
	 * The init date of the application of the discount
	 */
	private Date initDate;

	/**
	 * The finish date of the application of the discount
	 */
	private Date endDate;

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

	@Override
	/**
	 * {@inheritDoc}
	 */
	public float getValue(List<CatalogItem> basket) {
		Date currentDate = new Date();
		if (initDate.getTime() <= currentDate.getTime()
				&& currentDate.getTime() <= endDate.getTime()) {
			List<CatalogItem> itemsToDiscount = basket.stream()
					.filter(itemToCheck -> itemToCheck.equals(item))
					.collect(Collectors.toList());

			float discount = (float) itemsToDiscount.stream()
					.mapToDouble(item -> item.getPrice() * percentage / 100)
					.sum();

			if (logger.isDebugEnabled()) {
				logger.debug("Discount of item " + item.getId() + " is " + discount);
			}
			return discount;

		} else return (float)0.0;
	}

}
