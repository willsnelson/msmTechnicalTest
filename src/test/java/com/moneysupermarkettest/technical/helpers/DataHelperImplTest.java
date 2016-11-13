package com.moneysupermarkettest.technical.helpers;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.moneysupermarkettest.technical.constants.FileLocation;
import com.moneysupermarkettest.technical.exceptions.RequestBuilderException;
import com.moneysupermarkettest.technical.framework.CustomerAccount;
import com.moneysupermarkettest.technical.framework.OperationRequest;
import com.moneysupermarkettest.technical.framework.Product;
import com.moneysupermarkettest.technical.framework.Supplier;

public class DataHelperImplTest {

	DataHelperImpl dataHelperImpl;

	String supplierName = "Vodafone";

	String productName = "iPhone";

	String customerName = "Will Snelson";

	String postcode = "SK103QF";

	String type = "energy";

	double priceFrom = 15;

	double priceTo = 35;

	@BeforeClass
	public static void setup() {
		FileLocation.CUSTOMERACCOUNTSFILE = "testcustomeraccounts.txt";
		FileLocation.PRODUCTSFILE = "testproducts.txt";
		FileLocation.SUPPLIERS = "testsuppliers.txt";
	}

	@Before
	public void before() {
		dataHelperImpl = new DataHelperImpl();
	}

	@Test
	public void testAllSupplierRequest() throws RequestBuilderException {
		OperationRequest operationRequest = dataHelperImpl.buildRequest(new String[] { "doctype=supplier",
				"sort=alphabetically:true", "filter=name:" + supplierName + ",productsStocked:true" });

		boolean success = true;
		if (!operationRequest.isSupplierSearch())
			success = false;
		if (operationRequest.getSupplierRequest() != null) {
			if (!operationRequest.getSupplierRequest().isFindProductsStocked())
				success = false;
			if (!operationRequest.getSupplierRequest().isAlphabeticalOrdering())
				success = false;
			if (operationRequest.getSupplierRequest().getName() == null)
				success = false;
			else if (operationRequest.getSupplierRequest().getName() == null
					|| !operationRequest.getSupplierRequest().getName().equalsIgnoreCase(supplierName))
				success = false;
		} else {
			success = false;
		}
		Assert.assertEquals(true, success);

	}

	@Test
	public void testAllCustomerAccountRequest() throws RequestBuilderException {
		OperationRequest operationRequest = dataHelperImpl
				.buildRequest(new String[] { "doctype=account", "sort=alphabetically:true, numberOfOrders:ascending",
						"filter=name:" + customerName + ",postcode:" + postcode });

		boolean success = true;
		if (!operationRequest.isCustomerAccountSearch())
			success = false;
		if (operationRequest.getCustomerAccountRequest() != null) {
			if (!operationRequest.getCustomerAccountRequest().isAlphabeticalOrdering())
				success = false;
			if (operationRequest.getCustomerAccountRequest().getName() == null
					|| !operationRequest.getCustomerAccountRequest().getName().equalsIgnoreCase(customerName))
				success = false;
			if (operationRequest.getCustomerAccountRequest().getName() == null
					|| !operationRequest.getCustomerAccountRequest().getPostcode().equalsIgnoreCase(postcode))
				success = false;
			if (operationRequest.getCustomerAccountRequest().getOrderingByOrdersFilter() != null) {
				if (!operationRequest.getCustomerAccountRequest().getOrderingByOrdersFilter().getSortingOrder()
						.isAscending())
					success = false;
			} else {
				success = false;
			}
		} else {
			success = false;
		}
		Assert.assertEquals(true, success);

	}

	@Test
	public void testAllCustomerAccountDescendingRequest() throws RequestBuilderException {
		OperationRequest operationRequest = dataHelperImpl
				.buildRequest(new String[] { "doctype=account", "sort=alphabetically:true, numberOfOrders:descending",
						"filter=name:" + customerName + ",postcode:" + postcode });

		boolean success = true;
		if (!operationRequest.isCustomerAccountSearch())
			success = false;
		if (operationRequest.getCustomerAccountRequest() != null) {
			if (!operationRequest.getCustomerAccountRequest().isAlphabeticalOrdering())
				success = false;
			if (operationRequest.getCustomerAccountRequest().getName() == null
					|| !operationRequest.getCustomerAccountRequest().getName().equalsIgnoreCase(customerName))
				success = false;
			if (operationRequest.getCustomerAccountRequest().getName() == null
					|| !operationRequest.getCustomerAccountRequest().getPostcode().equalsIgnoreCase(postcode))
				success = false;
			if (operationRequest.getCustomerAccountRequest().getOrderingByOrdersFilter() != null) {
				if (!operationRequest.getCustomerAccountRequest().getOrderingByOrdersFilter().getSortingOrder()
						.isDescending())
					success = false;
			} else {
				success = false;
			}
		} else {
			success = false;
		}
		Assert.assertEquals(true, success);

	}

	@Test
	public void testAllProductAndPriceAscendingRequest() throws RequestBuilderException {
		OperationRequest operationRequest = dataHelperImpl
				.buildRequest(new String[] { "doctype=product", "sort=alphabetically:true, price:ascending",
						"filter=name:" + productName + ",type:" + type + ", price:" + priceFrom + "-" + priceTo });

		boolean success = true;
		if (!operationRequest.isProductSearch())
			success = false;
		if (operationRequest.getProductRequest() != null) {
			if (!operationRequest.getProductRequest().isAlphabeticalOrdering())
				success = false;
			if (operationRequest.getProductRequest().getName() == null
					|| !operationRequest.getProductRequest().getName().equalsIgnoreCase(productName))
				success = false;
			if (operationRequest.getProductRequest().getType() == null
					|| !operationRequest.getProductRequest().getType().equalsIgnoreCase(type))
				success = false;
			if (operationRequest.getProductRequest().getPriceFiltering() != null) {
				if (operationRequest.getProductRequest().getPriceFiltering().getPriceRange().getTo() != priceTo
						|| operationRequest.getProductRequest().getPriceFiltering().getPriceRange()
								.getFrom() != priceFrom)
					success = false;
				if (!operationRequest.getProductRequest().getPriceFiltering().getSortingOrder().isAscending())
					success = false;
			}
		} else {
			success = false;
		}
		Assert.assertEquals(true, success);

	}

	@Test
	public void testAllProductAndPriceDescendingRequest() throws RequestBuilderException {
		OperationRequest operationRequest = dataHelperImpl
				.buildRequest(new String[] { "doctype=product", "sort=alphabetically:true, price:descending",
						"filter=name:" + productName + ",type:" + type + ", price:" + priceFrom + "-" + priceTo });

		boolean success = true;
		if (!operationRequest.isProductSearch())
			success = false;
		if (operationRequest.getProductRequest() != null) {
			if (!operationRequest.getProductRequest().isAlphabeticalOrdering())
				success = false;
			if (operationRequest.getProductRequest().getName() == null
					|| !operationRequest.getProductRequest().getName().equalsIgnoreCase(productName))
				success = false;
			if (operationRequest.getProductRequest().getType() == null
					|| !operationRequest.getProductRequest().getType().equalsIgnoreCase(type))
				success = false;
			if (operationRequest.getProductRequest().getPriceFiltering() != null) {
				if (operationRequest.getProductRequest().getPriceFiltering().getPriceRange().getTo() != priceTo
						|| operationRequest.getProductRequest().getPriceFiltering().getPriceRange()
								.getFrom() != priceFrom)
					success = false;
				if (!operationRequest.getProductRequest().getPriceFiltering().getSortingOrder().isDescending())
					success = false;
			}
		} else {
			success = false;
		}
		Assert.assertEquals(true, success);

	}

	@Test
	public void testGetListOfAllProducts() {
		List<Product> listOfProducts = dataHelperImpl.getListOfAllProducts();
		boolean success = true;
		if (listOfProducts == null || listOfProducts.size() == 0)
			success = false;

		Assert.assertEquals(true, success);
	}

	@Test
	public void testGetListOfAllCustomerAccounts() {
		List<CustomerAccount> listOfAccounts = dataHelperImpl.getListOfAllCustomerAccounts();
		boolean success = true;
		if (listOfAccounts == null || listOfAccounts.size() == 0)
			success = false;

		Assert.assertEquals(true, success);
	}

	@Test
	public void testGetListOfAllSuppliers() {
		List<Supplier> listOfSuppliers = dataHelperImpl.getListOfAllSuppliers();
		boolean success = true;
		if (listOfSuppliers == null || listOfSuppliers.size() == 0)
			success = false;

		Assert.assertEquals(true, success);
	}

}
