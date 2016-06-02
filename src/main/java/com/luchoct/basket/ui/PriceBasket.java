/**
 * 
 */
package com.luchoct.basket.ui;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.luchoct.basket.service.ItemsException;
import com.luchoct.basket.service.ShoppingService;

/**
 * @author Luis
 * 
 */
public class PriceBasket {

	private static final Logger logger = Logger.getLogger(ShoppingService.class);

	private static ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(
			new String[] {"ApplicationContext.xml"});

	/**
	 * It calculates the price of the basket.
	 * @param args The args are the ids of the items of the catalog.
	 */
	public static void main(String[] args) {
		ShoppingService service = appContext.getBean("service", ShoppingService.class);
		if ((args.length == 0) || "--help".equals(args[0])) {
			System.out.println("Usage PriceBasket lis_of_ids");
			System.out.println("list_of_ids is a list of ids of items of the catalog, separated by spaces.");
			System.out.println("Available ids are: " + String.join(", ",
					service.getCatalog().getAvailableItems().keySet().toArray(new String[0])));
		} else {
			List<String> ids = Arrays.asList(args);
			try {
				service.processShoppingList(ids);
			} catch (ItemsException ie) {
				logger.error("Error in arguments " + ie);
				System.err.println(ie.toString());
			}
		}
	}
}
