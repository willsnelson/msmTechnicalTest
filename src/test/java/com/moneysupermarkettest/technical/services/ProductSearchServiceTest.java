package com.moneysupermarkettest.technical.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.moneysupermarkettest.technical.constants.FileLocation;
import com.moneysupermarkettest.technical.exceptions.FileSearchException;
import com.moneysupermarkettest.technical.framework.PriceFiltering;
import com.moneysupermarkettest.technical.framework.PriceRange;
import com.moneysupermarkettest.technical.framework.Product;
import com.moneysupermarkettest.technical.framework.ProductRequest;
import com.moneysupermarkettest.technical.framework.SortingOrder;

public class ProductSearchServiceTest {

	ProductSearchService productSearchService;

	String productName = "Gas";

	String type = "energy";

	double priceFrom = 15;

	double priceTo = 35;

	@BeforeClass
	public static void setup() {
		FileLocation.PRODUCTSFILE = "testproducts.txt";
	}

	@Before
	public void before() {
		productSearchService = new ProductSearchService();
	}

	@Test
	public void testAlphateticalOrdering() throws FileSearchException {
		List<Product> products = productSearchService
				.productSearch(buildProductRequest(true, false, false, false, false, false, false, false));
		for (int i = 1; i < products.size(); i++) {
			if (products.get(i - 1).getName().compareTo(products.get(i).getName()) > 0)
				Assert.fail();
		}
	}

	@Test
	public void testNameFilter() throws FileSearchException {
		List<Product> filteredProducts = productSearchService
				.productSearch(buildProductRequest(false, true, false, false, false, false, false, false));
		for (Product product : filteredProducts)
			if (!product.getName().equalsIgnoreCase(productName))
				Assert.fail();
	}

	@Test
	public void testTypeFilter() throws FileSearchException {
		List<Product> filteredProducts = productSearchService
				.productSearch(buildProductRequest(false, false, false, false, false, false, false, true));
		for (Product product : filteredProducts)
			if (!product.getType().equalsIgnoreCase(type))
				Assert.fail();
	}

	@Test
	public void testPriceFilter() throws FileSearchException {
		List<Product> filteredProducts = productSearchService
				.productSearch(buildProductRequest(false, false, true, false, false, false, true, false));
		for (Product product : filteredProducts)
			if (product.getPrice() < priceFrom && product.getPrice() > priceTo)
				Assert.fail();
	}

	@Test
	public void testPriceAscendingOrder() throws FileSearchException {
		List<Product> filteredProducts = productSearchService
				.productSearch(buildProductRequest(false, false, true, true, true, false, false, false));
		for (int i = 1; i < filteredProducts.size(); i++) {
			if (filteredProducts.get(i - 1).getPrice() > filteredProducts.get(i).getPrice())
				Assert.fail();
		}
	}

	@Test
	public void testPriceDescendingOrder() throws FileSearchException {
		List<Product> filteredProducts = productSearchService
				.productSearch(buildProductRequest(false, false, true, true, false, true, false, false));
		for (int i = 1; i < filteredProducts.size(); i++) {
			if (filteredProducts.get(i - 1).getPrice() < filteredProducts.get(i).getPrice())
				Assert.fail();
		}
	}

	private ProductRequest buildProductRequest(boolean isAlphabetical, boolean setName, boolean addPriceFiltering,
			boolean priceSorting, boolean priceAscending, boolean priceDescending, boolean priceRange,
			boolean setType) {
		ProductRequest productRequest = new ProductRequest();
		if (isAlphabetical)
			productRequest.setAlphabeticalOrdering(true);
		if (setName)
			productRequest.setName(productName);
		if (addPriceFiltering)
			productRequest
					.setPriceFiltering(buildPriceFiltering(priceSorting, priceAscending, priceDescending, priceRange));
		if (setType)
			productRequest.setType(type);
		return productRequest;
	}

	private PriceFiltering buildPriceFiltering(boolean priceSorting, boolean priceAscending, boolean priceDescending,
			boolean priceRange) {
		PriceFiltering priceFiltering = new PriceFiltering();
		if (priceSorting) {
			SortingOrder sortingOrder = new SortingOrder();
			if (priceAscending)
				sortingOrder.setAscending(true);
			if (priceDescending)
				sortingOrder.setDescending(priceDescending);
			priceFiltering.setSortingOrder(sortingOrder);
		}
		if (priceRange) {
			PriceRange priceRangeObject = new PriceRange();
			priceRangeObject.setFrom(priceFrom);
			priceRangeObject.setTo(priceTo);
			priceFiltering.setPriceRange(priceRangeObject);
		}

		return priceFiltering;
	}

}
