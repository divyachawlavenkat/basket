package com.luchoct.basket.service.discount;

import com.luchoct.basket.dto.CatalogItem;
import com.luchoct.basket.dto.Catalog;
import com.luchoct.basket.test.SpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by luchoct on 02/06/2016.
 */
public class AnotherItemDiscountTest extends SpringTest {

	@Autowired
	private AnotherItemDiscount breadDiscountOnBuyingSoup;

	@Autowired
	private Catalog catalog;

	@Test
	public void getValueReturnsDiscountOverBread() throws Exception {
		List<CatalogItem> basket = Arrays.asList("Soup", "Bread", "Soup").stream()
				.map(idItem -> catalog.getAvailableItems().get(idItem))
				.collect(Collectors.toList());
		assertEquals("Unexpected discount", 0.4, (double)breadDiscountOnBuyingSoup.getValue(basket), 0.000001);
	}

	@Test
	public void getValueReturnsNoDiscountAsNotBreadPurchased() throws Exception {
		List<CatalogItem> basket = Arrays.asList("Soup", "Apple", "Soup").stream()
				.map(idItem -> catalog.getAvailableItems().get(idItem))
				.collect(Collectors.toList());
		assertEquals("Unexpected discount", 0.0, (double)breadDiscountOnBuyingSoup.getValue(basket), 0.000001);
	}

	@Test
	public void getValueReturnsNoDiscountAsNotEnoughSoupPurchased() throws Exception {
		List<CatalogItem> basket = Arrays.asList("Soup", "Bread", "Apple").stream()
				.map(idItem -> catalog.getAvailableItems().get(idItem))
				.collect(Collectors.toList());
		assertEquals("Unexpected discount", 0.0, (double)breadDiscountOnBuyingSoup.getValue(basket), 0.000001);
	}
}
