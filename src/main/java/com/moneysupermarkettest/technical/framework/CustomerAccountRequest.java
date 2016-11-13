package com.moneysupermarkettest.technical.framework;

public class CustomerAccountRequest {

	private String name;

	private String postcode;

	private boolean alphabeticalOrdering;

	private OrderingByOrdersFilter orderingByOrdersFilter;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 * @param postcode
	 *            the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 * @return the alphabeticalOrdering
	 */
	public boolean isAlphabeticalOrdering() {
		return alphabeticalOrdering;
	}

	/**
	 * @param alphabeticalOrdering
	 *            the alphabeticalOrdering to set
	 */
	public void setAlphabeticalOrdering(boolean alphabeticalOrdering) {
		this.alphabeticalOrdering = alphabeticalOrdering;
	}

	/**
	 * @return the orderingByOrdersFilter
	 */
	public OrderingByOrdersFilter getOrderingByOrdersFilter() {
		return orderingByOrdersFilter;
	}

	/**
	 * @param orderingByOrdersFilter
	 *            the orderingByOrdersFilter to set
	 */
	public void setOrderingByOrdersFilter(OrderingByOrdersFilter orderingByOrdersFilter) {
		this.orderingByOrdersFilter = orderingByOrdersFilter;
	}

}
