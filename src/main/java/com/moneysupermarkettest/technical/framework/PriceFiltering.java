package com.moneysupermarkettest.technical.framework;

public class PriceFiltering {

	private PriceRange priceRange;

	private SortingOrder sortingOrder;

	/**
	 * @return the priceRange
	 */
	public PriceRange getPriceRange() {
		return priceRange;
	}

	/**
	 * @param priceRange
	 *            the priceRange to set
	 */
	public void setPriceRange(PriceRange priceRange) {
		this.priceRange = priceRange;
	}

	/**
	 * @return the sortingOrder
	 */
	public SortingOrder getSortingOrder() {
		return sortingOrder;
	}

	/**
	 * @param sortingOrder
	 *            the sortingOrder to set
	 */
	public void setSortingOrder(SortingOrder sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

}
