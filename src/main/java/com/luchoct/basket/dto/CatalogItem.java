/**
 * 
 */
package com.luchoct.basket.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * An item from the catalog.
 * @author Luis
 * 
 */
@Data
@EqualsAndHashCode(of = {"id"})
public class CatalogItem {

	/**
	 * Id of the item.
	 */
	private String id;

	/**
	 * Description of the item.
	 */
	private String description;

	/**
	 * Description used on the ticket.
	 */
	private String ticketDescription;

	/**
	 * Price of the container for the item.
	 */
	private float price;
}
