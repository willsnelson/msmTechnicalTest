package com.moneysupermarkettest.technical.framework;

public class SupplierRequest {

	private String name;

	private boolean findProductsStocked;

	private boolean alphabeticalOrdering;

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
	 * @return the findProductsStocked
	 */
	public boolean isFindProductsStocked() {
		return findProductsStocked;
	}

	/**
	 * @param findProductsStocked
	 *            the findProductsStocked to set
	 */
	public void setFindProductsStocked(boolean findProductsStocked) {
		this.findProductsStocked = findProductsStocked;
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

}
