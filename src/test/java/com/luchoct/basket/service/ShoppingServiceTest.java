/**
 * 
 */
package com.luchoct.basket.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.luchoct.basket.dto.Catalog;
import com.luchoct.basket.test.SpringTest;

/**
 * @author Luis
 * 
 */
public class ShoppingServiceTest extends SpringTest {

	static final Logger logger = Logger.getLogger(ShoppingService.class);

	/**
	 * Service to test
	 */
	@Autowired
	private ShoppingService service;

	/**
	 * Catalog
	 */
	@Autowired
	private Catalog catalog;
	
	/**
	 * It tests the initialization of the service
	 */
	@Test
	public void testService() {
		assertNotNull("service not initialised", service);
	}

	/**
	 * It tests the process of simple shopping lists.
	 */
	@Test
	public void testSimpleProcess1() throws Exception {
		service.setCatalog(catalog);
		List<String> ids = new LinkedList<String>();
		ids.add("Apple");
		ids.add("Milk");
		ids.add("Bread");
		float price = service.processShoppingList(ids);
		if (logger.isDebugEnabled()) {
			logger.debug("The price is " + price);
		}
		assertEquals("Unexpected Total price", 3.0, (double)price, 0.000001);
	}

	/**
	 * It tests the process of simple shopping lists with discount over the bread.
	 */
	@Test
	public void testSimpleProcess2() throws Exception {
		service.setCatalog(catalog);
		List<String> ids = new LinkedList<String>();
		ids.add("Apple");
		ids.add("Soup");
		ids.add("Milk");
		ids.add("Bread");
		ids.add("Soup");
		float price = service.processShoppingList(ids);
		if (logger.isDebugEnabled()) {
			logger.debug("The price is " + price);
		}
		assertEquals("Unexpected total price", 3.9, (double)price, 0.000001);
	}
}
