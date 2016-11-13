package com.moneysupermarkettest.technical.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.moneysupermarkettest.technical.exceptions.FileSearchException;
import com.moneysupermarkettest.technical.framework.CustomerAccount;
import com.moneysupermarkettest.technical.framework.CustomerAccountRequest;
import com.moneysupermarkettest.technical.framework.OrderingByOrdersFilter;
import com.moneysupermarkettest.technical.framework.SortingOrder;
import com.moneysupermarkettest.technical.helpers.DataHelper;
import com.moneysupermarkettest.technical.helpers.DataHelperImpl;

public class CustomerAccountSearchService {

	DataHelper dataHelper = new DataHelperImpl();

	List<CustomerAccount> filteredAndSortedAccounts;

	public List<CustomerAccount> customerAccountSearch(CustomerAccountRequest customerAccountRequest) throws FileSearchException {
		filteredAndSortedAccounts = dataHelper.getListOfAllCustomerAccounts();
		if (customerAccountRequest != null) {
			if (customerAccountRequest.isAlphabeticalOrdering())
				filteredAndSortedAccounts = orderAccountsAlphabetically();
			if (customerAccountRequest.getName() != null)
				filteredAndSortedAccounts = filterByName(customerAccountRequest.getName());
			if (customerAccountRequest.getPostcode() != null)
				filteredAndSortedAccounts = filterByPostCode(customerAccountRequest.getPostcode());
			if (customerAccountRequest.getOrderingByOrdersFilter() != null)
				filteredAndSortedAccounts = orderByNumberOfOrders(customerAccountRequest.getOrderingByOrdersFilter());
		}
		return filteredAndSortedAccounts;
	}

	private List<CustomerAccount> orderByNumberOfOrders(OrderingByOrdersFilter orderingByOrdersFilter) {
		List<CustomerAccount> customerAccounts = filteredAndSortedAccounts;
		SortingOrder sortingOrder = orderingByOrdersFilter.getSortingOrder();
		if (sortingOrder != null) {
			if (sortingOrder.isDescending()) {
				Comparator<CustomerAccount> decendingByNumberOfOrders = new Comparator<CustomerAccount>() {

					@Override
					public int compare(CustomerAccount account1, CustomerAccount account2) {
						return account1.getNumberOfOrders() > account2.getNumberOfOrders() ? -1
								: account1.getNumberOfOrders() == account2.getNumberOfOrders() ? 0 : 1;
					}
				};
				if (customerAccounts.size() > 1)
					customerAccounts.sort(decendingByNumberOfOrders);
				return customerAccounts;
			} else if (sortingOrder.isAscending()) {
				Comparator<CustomerAccount> decendingByNumberOfOrders = new Comparator<CustomerAccount>() {

					@Override
					public int compare(CustomerAccount account1, CustomerAccount account2) {
						return account1.getNumberOfOrders() < account2.getNumberOfOrders() ? -1
								: account1.getNumberOfOrders() == account2.getNumberOfOrders() ? 0 : 1;
					}
				};
				if (customerAccounts.size() > 1)
					customerAccounts.sort(decendingByNumberOfOrders);
				return customerAccounts;
			}
		}
		return customerAccounts;
	}

	private List<CustomerAccount> filterByName(String name) {
		List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
		for (CustomerAccount customerAccount : filteredAndSortedAccounts)
			if (customerAccount.getName().equalsIgnoreCase(name))
				customerAccounts.add(customerAccount);
		return customerAccounts;
	}

	private List<CustomerAccount> filterByPostCode(String postcode) {
		List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
		for (CustomerAccount customerAccount : filteredAndSortedAccounts)
			if (customerAccount.getPostcode().equalsIgnoreCase(postcode))
				customerAccounts.add(customerAccount);
		return customerAccounts;
	}

	private List<CustomerAccount> orderAccountsAlphabetically() {
		List<CustomerAccount> customerAccounts = filteredAndSortedAccounts;
		Comparator<CustomerAccount> accountCompare = new Comparator<CustomerAccount>() {

			@Override
			public int compare(CustomerAccount account1, CustomerAccount account2) {
				return account1.getName().compareTo(account2.getName());
			}
		};
		if (customerAccounts.size() > 1)
			customerAccounts.sort(accountCompare);
		return customerAccounts;
	}

}
