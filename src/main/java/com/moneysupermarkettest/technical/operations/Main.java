package com.moneysupermarkettest.technical.operations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.moneysupermarkettest.technical.exceptions.FileSearchException;
import com.moneysupermarkettest.technical.exceptions.RequestBuilderException;
import com.moneysupermarkettest.technical.framework.OperationRequest;
import com.moneysupermarkettest.technical.helpers.DataHelper;
import com.moneysupermarkettest.technical.helpers.DataHelperImpl;
import com.moneysupermarkettest.technical.services.CustomerAccountSearchService;
import com.moneysupermarkettest.technical.services.ProductSearchService;
import com.moneysupermarkettest.technical.services.SupplierSearchService;

public class Main {

	private static DataHelper dataHelper = new DataHelperImpl();

	private static ProductSearchService productSearchService = new ProductSearchService();

	private static SupplierSearchService supplierSearchService = new SupplierSearchService();

	private static CustomerAccountSearchService customerAccountSearchService = new CustomerAccountSearchService();

	private static Gson gson = new GsonBuilder().serializeNulls().create();

	public static void main(String[] args) {
		try {
			OperationRequest operationRequest = dataHelper.buildRequest(args);

			if (operationRequest.isProductSearch())
				System.out
						.println(gson.toJson(productSearchService.productSearch(operationRequest.getProductRequest())));
			else if (operationRequest.isSupplierSearch())
				System.out.println(
						gson.toJson(supplierSearchService.supplierSearch(operationRequest.getSupplierRequest())));
			else if (operationRequest.isCustomerAccountSearch())
				System.out.println(gson.toJson(customerAccountSearchService
						.customerAccountSearch(operationRequest.getCustomerAccountRequest())));
		} catch (RequestBuilderException rbe) {
			rbe.printStackTrace();
			System.out.println("Error during building request - please check parameters are of valid format.");
		} catch (FileSearchException fse) {
			fse.printStackTrace();
			System.out.println("Error during file search - please check output for error.");
		}
	}

	/**
	 * @param dataHelper
	 *            the dataHelper to set
	 */
	public static void setDataHelper(DataHelper dataHelper) {
		Main.dataHelper = dataHelper;
	}

}
