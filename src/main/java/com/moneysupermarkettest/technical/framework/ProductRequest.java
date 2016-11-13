package com.moneysupermarkettest.technical.framework;

public class ProductRequest {

	private String type;

	private String name;

	private String supplier;

	private boolean alphabeticalOrdering;

	private PriceFiltering priceFiltering;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the supplier
	 */
	public String getSupplier() {
		return supplier;
	}

	/**
	 * @param supplier
	 *            the supplier to set
	 */
	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
	 * @return the priceFiltering
	 */
	public PriceFiltering getPriceFiltering() {
		return priceFiltering;
	}

	/**
	 * @param priceFiltering
	 *            the priceFiltering to set
	 */
	public void setPriceFiltering(PriceFiltering priceFiltering) {
		this.priceFiltering = priceFiltering;
	}

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

}