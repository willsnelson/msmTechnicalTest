package com.moneysupermarkettest.technical.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.moneysupermarkettest.technical.exceptions.FileSearchException;
import com.moneysupermarkettest.technical.framework.PriceFiltering;
import com.moneysupermarkettest.technical.framework.PriceRange;
import com.moneysupermarkettest.technical.framework.Product;
import com.moneysupermarkettest.technical.framework.ProductRequest;
import com.moneysupermarkettest.technical.framework.SortingOrder;
import com.moneysupermarkettest.technical.helpers.DataHelper;
import com.moneysupermarkettest.technical.helpers.DataHelperImpl;

public class ProductSearchService {

	private DataHelper dataHelper = new DataHelperImpl();

	private List<Product> filteredAndSortedProducts;

	public List<Product> productSearch(ProductRequest productSearchRequest) throws FileSearchException {
		filteredAndSortedProducts = dataHelper.getListOfAllProducts();
		if (productSearchRequest != null) {
			if (productSearchRequest.isAlphabeticalOrdering())
				filteredAndSortedProducts = filterProductsAlphabetically();
			if (productSearchRequest.getName() != null)
				filteredAndSortedProducts = getAllProductsByName(productSearchRequest.getName());
			if (productSearchRequest.getType() != null)
				filteredAndSortedProducts = getAllProductsByTyoe(productSearchRequest.getType());
			if (productSearchRequest.getPriceFiltering() != null)
				filteredAndSortedProducts = getAllProductsByPriceFiltering(productSearchRequest.getPriceFiltering());
		}
		return filteredAndSortedProducts;
	}

	private List<Product> getAllProductsByPriceFiltering(PriceFiltering priceFiltering) {
		List<Product> products = filteredAndSortedProducts;
		if (priceFiltering.getSortingOrder() != null)
			products = sortProductsByPrice(products, priceFiltering.getSortingOrder());
		if (priceFiltering.getPriceRange() != null)
			products = filterByPriceRange(products, priceFiltering.getPriceRange());
		return products;
	}

	private List<Product> filterByPriceRange(List<Product> listOfProducts, PriceRange priceRange) {
		List<Product> products = new ArrayList<Product>();
		for (Product product : listOfProducts)
			if (product.getPrice() >= priceRange.getFrom() && product.getPrice() <= priceRange.getTo())
				products.add(product);
		return products;
	}

	private List<Product> sortProductsByPrice(List<Product> listOfProducts, SortingOrder sortingOrder) {
		List<Product> products = listOfProducts;
		if (sortingOrder.isDescending()) {
			Comparator<Product> decendingByPrice = new Comparator<Product>() {

				@Override
				public int compare(Product product1, Product product2) {
					return product1.getPrice() > product2.getPrice() ? -1
							: product1.getPrice() == product2.getPrice() ? 0 : 1;
				}
			};
			if (products.size() > 1)
				products.sort(decendingByPrice);
			return products;
		} else if (sortingOrder.isAscending()) {
			Comparator<Product> ascendingByPrice = new Comparator<Product>() {

				@Override
				public int compare(Product product1, Product product2) {
					return product1.getPrice() < product2.getPrice() ? -1
							: product1.getPrice() == product2.getPrice() ? 0 : 1;
				}
			};
			if (products.size() > 1)
				products.sort(ascendingByPrice);
			return products;
		}
		return products;
	}

	private List<Product> getAllProductsByTyoe(String type) {
		List<Product> products = new ArrayList<Product>();
		for (Product product : filteredAndSortedProducts)
			if (product.getType().equalsIgnoreCase(type))
				products.add(product);
		return products;
	}

	private List<Product> filterProductsAlphabetically() {
		List<Product> products = filteredAndSortedProducts;
		Comparator<Product> productsCompare = new Comparator<Product>() {

			@Override
			public int compare(Product product1, Product product2) {
				return product1.getName().compareTo(product2.getName());
			}
		};
		if (products.size() > 1)
			products.sort(productsCompare);
		return products;
	}

	private List<Product> getAllProductsByName(String name) {
		List<Product> products = new ArrayList<Product>();
		for (Product product : filteredAndSortedProducts)
			if (product.getName().equalsIgnoreCase(name))
				products.add(product);
		return products;
	}

}
