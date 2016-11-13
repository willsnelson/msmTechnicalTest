package com.moneysupermarkettest.technical.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.moneysupermarkettest.technical.constants.FileLocation;
import com.moneysupermarkettest.technical.exceptions.FileSearchException;
import com.moneysupermarkettest.technical.framework.Supplier;
import com.moneysupermarkettest.technical.framework.SupplierRequest;

public class SupplierSearchServiceTest {

	SupplierSearchService supplierSearchService;

	String name = "Vodafone";

	@BeforeClass
	public static void setup() {
		FileLocation.SUPPLIERS = "testsuppliers.txt";
		FileLocation.PRODUCTSFILE = "testproducts.txt";
	}

	@Before
	public void before() {
		supplierSearchService = new SupplierSearchService();
	}

	@Test
	public void testFilterByName() throws FileSearchException {
		List<Supplier> suppliers = supplierSearchService.supplierSearch(buildSupplierSearchRequest(true, false, false));
		for (Supplier supplier : suppliers)
			if (!supplier.getName().equalsIgnoreCase(name))
				Assert.fail();
	}

	@Test
	public void testFilterByProductsStocked() throws FileSearchException {
		List<Supplier> suppliers = supplierSearchService.supplierSearch(buildSupplierSearchRequest(true, true, false));
		for (Supplier supplier : suppliers)
			if (supplier.getProducts() == null)
				Assert.fail();
	}

	@Test
	public void testOrderAlphabetically() throws FileSearchException {
		List<Supplier> suppliers = supplierSearchService.supplierSearch(buildSupplierSearchRequest(false, false, true));
		for (int i = 1; i < suppliers.size(); i++) {
			if (suppliers.get(i - 1).getName().compareTo(suppliers.get(i).getName()) > 0)
				Assert.fail();
		}
	}

	private SupplierRequest buildSupplierSearchRequest(boolean nameSearch, boolean productsStocked,
			boolean orderByNameAlphabetically) {
		SupplierRequest supplierRequest = new SupplierRequest();
		if (nameSearch)
			supplierRequest.setName(name);
		if (productsStocked)
			supplierRequest.setFindProductsStocked(true);
		if (orderByNameAlphabetically)
			supplierRequest.setAlphabeticalOrdering(true);
		return supplierRequest;
	}

}
