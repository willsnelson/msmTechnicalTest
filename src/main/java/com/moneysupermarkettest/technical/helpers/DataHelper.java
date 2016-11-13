package com.moneysupermarkettest.technical.helpers;

import java.util.List;

import com.moneysupermarkettest.technical.exceptions.RequestBuilderException;
import com.moneysupermarkettest.technical.framework.CustomerAccount;
import com.moneysupermarkettest.technical.framework.OperationRequest;
import com.moneysupermarkettest.technical.framework.Product;
import com.moneysupermarkettest.technical.framework.Supplier;

public interface DataHelper {

	public OperationRequest buildRequest(String[] arguments) throws RequestBuilderException;

	public List<Product> getListOfAllProducts();

	public List<CustomerAccount> getListOfAllCustomerAccounts();

	public List<Supplier> getListOfAllSuppliers();

}
