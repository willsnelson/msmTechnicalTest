package com.moneysupermarkettest.technical.services;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.moneysupermarkettest.technical.constants.FileLocation;
import com.moneysupermarkettest.technical.exceptions.FileSearchException;
import com.moneysupermarkettest.technical.framework.CustomerAccount;
import com.moneysupermarkettest.technical.framework.CustomerAccountRequest;
import com.moneysupermarkettest.technical.framework.OrderingByOrdersFilter;
import com.moneysupermarkettest.technical.framework.SortingOrder;

public class CustomerAccountSearchServiceTest {

	CustomerAccountSearchService customerAccountSearchService;

	String name = "Will Snelson";

	String postcode = "SK103QF";

	@BeforeClass
	public static void setup() {
		FileLocation.CUSTOMERACCOUNTSFILE = "testcustomeraccounts.txt";
	}

	@Before
	public void before() {
		customerAccountSearchService = new CustomerAccountSearchService();
	}

	@Test
	public void testFilterByName() throws FileSearchException {
		List<CustomerAccount> customerAccounts = customerAccountSearchService
				.customerAccountSearch(buildCustomerAccountRequest(true, false, false, false, false, false));
		for (CustomerAccount customerAccount : customerAccounts)
			if (!customerAccount.getName().equalsIgnoreCase(name))
				Assert.fail();
	}

	@Test
	public void testFilterByPostcode() throws FileSearchException {
		List<CustomerAccount> customerAccounts = customerAccountSearchService
				.customerAccountSearch(buildCustomerAccountRequest(false, true, false, false, false, false));
		for (CustomerAccount customerAccount : customerAccounts)
			if (!customerAccount.getPostcode().equalsIgnoreCase(postcode))
				Assert.fail();
	}

	@Test
	public void testOrderAlphabetically() throws FileSearchException {
		List<CustomerAccount> customerAccounts = customerAccountSearchService
				.customerAccountSearch(buildCustomerAccountRequest(false, false, true, false, false, false));
		for (int i = 1; i < customerAccounts.size(); i++) {
			if (customerAccounts.get(i - 1).getName().compareTo(customerAccounts.get(i).getName()) > 0)
				Assert.fail();
		}
	}

	@Test
	public void testOrderByAscendingNumberOfOrder() throws FileSearchException {
		List<CustomerAccount> customerAccounts = customerAccountSearchService
				.customerAccountSearch(buildCustomerAccountRequest(false, false, false, true, true, false));
		for (int i = 1; i < customerAccounts.size(); i++) {
			if (customerAccounts.get(i - 1).getNumberOfOrders() > customerAccounts.get(i).getNumberOfOrders())
				Assert.fail();
		}
	}

	@Test
	public void testOrderByDescendingNumberOfOrder() throws FileSearchException {
		List<CustomerAccount> customerAccounts = customerAccountSearchService
				.customerAccountSearch(buildCustomerAccountRequest(false, false, false, true, false, true));
		for (int i = 1; i < customerAccounts.size(); i++) {
			if (customerAccounts.get(i - 1).getNumberOfOrders() < customerAccounts.get(i).getNumberOfOrders())
				Assert.fail();
		}
	}

	private CustomerAccountRequest buildCustomerAccountRequest(boolean setName, boolean setPostcode,
			boolean orderAlphabetically, boolean orderByOrders, boolean ascending, boolean descending) {
		CustomerAccountRequest customerAccountRequest = new CustomerAccountRequest();
		if (setName)
			customerAccountRequest.setName(name);
		if (setPostcode)
			customerAccountRequest.setPostcode(postcode);
		if (orderAlphabetically)
			customerAccountRequest.setAlphabeticalOrdering(true);
		if (orderByOrders) {
			OrderingByOrdersFilter orderingByOrdersFilter = new OrderingByOrdersFilter();
			SortingOrder sortingOrder = new SortingOrder();
			if (ascending)
				sortingOrder.setAscending(true);
			if (descending)
				sortingOrder.setDescending(true);
			orderingByOrdersFilter.setSortingOrder(sortingOrder);
			customerAccountRequest.setOrderingByOrdersFilter(orderingByOrdersFilter);
		}

		return customerAccountRequest;
	}

}
