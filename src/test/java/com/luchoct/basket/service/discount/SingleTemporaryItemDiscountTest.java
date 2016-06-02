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
public class SingleTemporaryItemDiscountTest extends SpringTest {

	@Autowired
	private SingleTemporaryItemDiscount applesDiscount;

	@Autowired
	private Catalog catalog;

	@Test
	public void getValueReturnsDiscountOnBuyingApples() throws Exception {
		List<CatalogItem> basket = Arrays.asList("Apple").stream()
				.map(idItem -> catalog.getAvailableItems().get(idItem))
				.collect(Collectors.toList());
		assertEquals("Unexpected discount", 0.1, (double)applesDiscount.getValue(basket), 0.000001);
	}

	@Test
	public void getValueReturnsNoDiscountOnBuyingSomethingElse() throws Exception {
		List<CatalogItem> basket = Arrays.asList("Bread").stream()
				.map(idItem -> catalog.getAvailableItems().get(idItem))
				.collect(Collectors.toList());
		assertEquals("Unexpected discount", 0.0, (double)applesDiscount.getValue(basket), 0.000001);
	}
}