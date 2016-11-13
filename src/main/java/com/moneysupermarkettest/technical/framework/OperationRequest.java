package com.moneysupermarkettest.technical.framework;

public class OperationRequest {

	private boolean productSearch;

	private ProductRequest productRequest;

	private boolean supplierSearch;

	private SupplierRequest supplierRequest;

	private boolean customerAccountSearch;

	private CustomerAccountRequest customerAccountRequest;

	/**
	 * @return the productSearch
	 */
	public boolean isProductSearch() {
		return productSearch;
	}

	/**
	 * @param productSearch
	 *            the productSearch to set
	 */
	public void setProductSearch(boolean productSearch) {
		this.productSearch = productSearch;
	}

	/**
	 * @return the productRequest
	 */
	public ProductRequest getProductRequest() {
		return productRequest;
	}

	/**
	 * @param productRequest
	 *            the productRequest to set
	 */
	public void setProductRequest(ProductRequest productRequest) {
		this.productRequest = productRequest;
	}

	/**
	 * @return the supplierSearch
	 */
	public boolean isSupplierSearch() {
		return supplierSearch;
	}

	/**
	 * @param supplierSearch
	 *            the supplierSearch to set
	 */
	public void setSupplierSearch(boolean supplierSearch) {
		this.supplierSearch = supplierSearch;
	}

	/**
	 * @return the supplierRequest
	 */
	public SupplierRequest getSupplierRequest() {
		return supplierRequest;
	}

	/**
	 * @param supplierRequest
	 *            the supplierRequest to set
	 */
	public void setSupplierRequest(SupplierRequest supplierRequest) {
		this.supplierRequest = supplierRequest;
	}

	/**
	 * @return the customerAccountSearch
	 */
	public boolean isCustomerAccountSearch() {
		return customerAccountSearch;
	}

	/**
	 * @param customerAccountSearch
	 *            the customerAccountSearch to set
	 */
	public void setCustomerAccountSearch(boolean customerAccountSearch) {
		this.customerAccountSearch = customerAccountSearch;
	}

	/**
	 * @return the customerAccountRequest
	 */
	public CustomerAccountRequest getCustomerAccountRequest() {
		return customerAccountRequest;
	}

	/**
	 * @param customerAccountRequest
	 *            the customerAccountRequest to set
	 */
	public void setCustomerAccountRequest(CustomerAccountRequest customerAccountRequest) {
		this.customerAccountRequest = customerAccountRequest;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OperationRequest [productSearch=" + productSearch + ", productRequest=" + productRequest
				+ ", supplierSearch=" + supplierSearch + ", supplierRequest=" + supplierRequest
				+ ", customerAccountSearch=" + customerAccountSearch + ", customerAccountRequest="
				+ customerAccountRequest + "]";
	}

}
