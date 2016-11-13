package com.moneysupermarkettest.technical.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.moneysupermarkettest.technical.exceptions.FileSearchException;
import com.moneysupermarkettest.technical.framework.Product;
import com.moneysupermarkettest.technical.framework.Supplier;
import com.moneysupermarkettest.technical.framework.SupplierRequest;
import com.moneysupermarkettest.technical.helpers.DataHelper;
import com.moneysupermarkettest.technical.helpers.DataHelperImpl;

public class SupplierSearchService {

	private DataHelper dataHelper = new DataHelperImpl();

	private List<Supplier> filteredAndSortedSupplier;

	public List<Supplier> supplierSearch(SupplierRequest supplierSearchRequest) throws FileSearchException {
		filteredAndSortedSupplier = dataHelper.getListOfAllSuppliers();
		if (supplierSearchRequest != null) {
			if (supplierSearchRequest.isAlphabeticalOrdering())
				filteredAndSortedSupplier = orderSuppliersAlphabetically();
			if (supplierSearchRequest.getName() != null)
				filteredAndSortedSupplier = filterSuppliersByName(supplierSearchRequest.getName());
			if (supplierSearchRequest.isFindProductsStocked())
				filteredAndSortedSupplier = findProductsStocked();
		}
		return filteredAndSortedSupplier;
	}

	private List<Supplier> findProductsStocked() {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		List<Product> products = dataHelper.getListOfAllProducts();
		for (Supplier supplier : filteredAndSortedSupplier) {
			List<Product> suppliersProducts = new ArrayList<Product>();
			for (Product product : products) {
				if (product.getSupplier().equalsIgnoreCase(supplier.getName()))
					suppliersProducts.add(product);
			}
			supplier.setProducts(suppliersProducts);
			suppliers.add(supplier);
		}
		return suppliers;
	}

	private List<Supplier> filterSuppliersByName(String name) {
		List<Supplier> suppliers = new ArrayList<Supplier>();
		for (Supplier supplier : filteredAndSortedSupplier)
			if (supplier.getName().equalsIgnoreCase(name))
				suppliers.add(supplier);
		return suppliers;
	}

	private List<Supplier> orderSuppliersAlphabetically() {
		List<Supplier> supplier = filteredAndSortedSupplier;
		Comparator<Supplier> supplierCompare = new Comparator<Supplier>() {

			@Override
			public int compare(Supplier supplier1, Supplier supplier2) {
				return supplier1.getName().compareTo(supplier2.getName());
			}
		};
		if (supplier.size() > 1)
			supplier.sort(supplierCompare);
		return supplier;
	}

}
