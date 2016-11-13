package com.moneysupermarkettest.technical.helpers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.moneysupermarkettest.technical.constants.CustomerAccountBuilderConstants;
import com.moneysupermarkettest.technical.constants.DocTypeConstants;
import com.moneysupermarkettest.technical.constants.FileLocation;
import com.moneysupermarkettest.technical.constants.FilterConstants;
import com.moneysupermarkettest.technical.constants.OperationConstants;
import com.moneysupermarkettest.technical.constants.ProductBuilderConstants;
import com.moneysupermarkettest.technical.constants.SortConstants;
import com.moneysupermarkettest.technical.constants.SupplierBuilderConstants;
import com.moneysupermarkettest.technical.exceptions.RequestBuilderException;
import com.moneysupermarkettest.technical.framework.ArgumentAndRequest;
import com.moneysupermarkettest.technical.framework.CustomerAccount;
import com.moneysupermarkettest.technical.framework.CustomerAccountRequest;
import com.moneysupermarkettest.technical.framework.FindDocumentTypeResponse;
import com.moneysupermarkettest.technical.framework.OperationRequest;
import com.moneysupermarkettest.technical.framework.OrderingByOrdersFilter;
import com.moneysupermarkettest.technical.framework.PriceFiltering;
import com.moneysupermarkettest.technical.framework.PriceRange;
import com.moneysupermarkettest.technical.framework.Product;
import com.moneysupermarkettest.technical.framework.ProductRequest;
import com.moneysupermarkettest.technical.framework.SortingOrder;
import com.moneysupermarkettest.technical.framework.Supplier;
import com.moneysupermarkettest.technical.framework.SupplierRequest;

public class DataHelperImpl implements DataHelper {

	public OperationRequest buildRequest(String[] arguments) throws RequestBuilderException {
		FindDocumentTypeResponse findDocumentTypeResponse = findDocumentType(arguments);
		OperationRequest operationRequest = findDocumentTypeResponse.getOperationRequest();
		for (String arg : findDocumentTypeResponse.getNewArgs()) {
			ArgumentAndRequest argumentAndRequest = getArgAndReq(arg);
			operationRequest = buildRequestForArgument(argumentAndRequest, operationRequest);
		}
		return operationRequest;
	}

	private OperationRequest buildRequestForArgument(ArgumentAndRequest argumentAndRequest,
			OperationRequest opRequest) {
		String argument = argumentAndRequest.getArgument();
		String request = argumentAndRequest.getRequest();
		if (argument.equalsIgnoreCase(OperationConstants.SORT))
			opRequest = findSortType(request, opRequest);
		else if (argument.equalsIgnoreCase(OperationConstants.FILTER))
			opRequest = findFilterType(request, opRequest);
		return opRequest;
	}

	private OperationRequest findFilterType(String requests, OperationRequest opRequest) {
		if (opRequest.isCustomerAccountSearch())
			opRequest = buildFilterForAccountsSearch(requests, opRequest);
		else if (opRequest.isProductSearch())
			opRequest = buildFilterForProductsSearch(requests, opRequest);
		else if (opRequest.isSupplierSearch())
			opRequest = buildFilterForSupplierSearch(requests, opRequest);
		return opRequest;
	}

	private OperationRequest buildFilterForSupplierSearch(String requests, OperationRequest opRequest) {
		SupplierRequest supplierRequest = opRequest.getSupplierRequest();
		if (supplierRequest == null)
			supplierRequest = new SupplierRequest();
		String[] supplierRequests = requests.split(OperationConstants.REQUESTSPLITTER);
		for (String request : supplierRequests) {
			ArgumentAndRequest argAndReq = getArgAndReqFromRequest(request);
			String arg = argAndReq.getArgument();
			arg = arg.replaceAll(" ", "");
			String parameter = argAndReq.getRequest();
			if (arg.equalsIgnoreCase(FilterConstants.NAME))
				supplierRequest.setName(parameter);
			else if (arg.equalsIgnoreCase(FilterConstants.PRODUCTSSTOCKED))
				supplierRequest.setFindProductsStocked(Boolean.parseBoolean(parameter));
		}
		opRequest.setSupplierRequest(supplierRequest);
		return opRequest;
	}

	private OperationRequest buildFilterForProductsSearch(String requests, OperationRequest opRequest) {
		ProductRequest productRequest = opRequest.getProductRequest();
		if (productRequest == null)
			productRequest = new ProductRequest();
		String[] filterRequests = requests.split(OperationConstants.REQUESTSPLITTER);
		for (String request : filterRequests) {
			ArgumentAndRequest argAndReq = getArgAndReqFromRequest(request);
			String arg = argAndReq.getArgument();
			arg = arg.replaceAll(" ", "");
			String parameter = argAndReq.getRequest();
			if (arg.equalsIgnoreCase(FilterConstants.PRICE))
				productRequest.setPriceFiltering(buildPriceFilter(parameter, checkForPriceFiltering(opRequest)));
			else if (arg.equalsIgnoreCase(FilterConstants.NAME))
				productRequest.setName(parameter);
			else if (arg.equalsIgnoreCase(FilterConstants.TYPE))
				productRequest.setType(parameter);
		}
		opRequest.setProductRequest(productRequest);
		return opRequest;
	}

	private PriceFiltering buildPriceFilter(String parameter, PriceFiltering opPriceFiltering) {
		PriceFiltering priceFiltering = opPriceFiltering;
		PriceRange priceRange = new PriceRange();
		if (opPriceFiltering == null)
			priceFiltering = new PriceFiltering();
		String[] range = parameter.split(OperationConstants.RANGESPLITTER);
		priceRange.setFrom(Double.parseDouble(range[0]));
		priceRange.setTo(Double.parseDouble(range[1]));
		priceFiltering.setPriceRange(priceRange);
		return priceFiltering;
	}

	private OperationRequest buildFilterForAccountsSearch(String requests, OperationRequest opRequest) {
		CustomerAccountRequest customerAccountRequest = opRequest.getCustomerAccountRequest();
		if (customerAccountRequest == null)
			customerAccountRequest = new CustomerAccountRequest();
		String[] filterRequests = requests.split(OperationConstants.REQUESTSPLITTER);
		for (String request : filterRequests) {
			ArgumentAndRequest argAndReq = getArgAndReqFromRequest(request);
			String arg = argAndReq.getArgument();
			arg = arg.replaceAll(" ", "");
			String parameter = argAndReq.getRequest();
			if (arg.equalsIgnoreCase(FilterConstants.NAME))
				customerAccountRequest.setName(parameter);
			else if (arg.equalsIgnoreCase(FilterConstants.POSTCODE))
				customerAccountRequest.setPostcode(parameter);
		}
		opRequest.setCustomerAccountRequest(customerAccountRequest);
		return opRequest;
	}

	private OperationRequest findSortType(String requests, OperationRequest opRequest) {
		if (opRequest.isCustomerAccountSearch())
			opRequest = buildSortingOrderForAccountsSearch(requests, opRequest);
		else if (opRequest.isProductSearch())
			opRequest = buildSortingOrderForProductsSearch(requests, opRequest);
		else if (opRequest.isSupplierSearch())
			opRequest = buildSortingOrderForSupplierSearch(requests, opRequest);
		return opRequest;
	}

	private OperationRequest buildSortingOrderForSupplierSearch(String requests, OperationRequest opRequest) {
		SupplierRequest supplierRequest = opRequest.getSupplierRequest();
		if (supplierRequest == null)
			supplierRequest = new SupplierRequest();
		String[] supplierRequests = requests.split(OperationConstants.REQUESTSPLITTER);
		for (String request : supplierRequests) {
			ArgumentAndRequest argAndReq = getArgAndReqFromRequest(request);
			String arg = argAndReq.getArgument();
			arg = arg.replaceAll(" ", "");
			String parameter = argAndReq.getRequest();
			if (arg.equalsIgnoreCase(SortConstants.ALPHABETICALLY))
				supplierRequest.setAlphabeticalOrdering(Boolean.parseBoolean(parameter));
		}
		opRequest.setSupplierRequest(supplierRequest);
		return opRequest;
	}

	private OperationRequest buildSortingOrderForProductsSearch(String requests, OperationRequest opRequest) {
		ProductRequest productRequest = opRequest.getProductRequest();
		if (productRequest == null)
			productRequest = new ProductRequest();
		String[] productRequests = requests.split(OperationConstants.REQUESTSPLITTER);
		for (String request : productRequests) {
			ArgumentAndRequest argAndReq = getArgAndReqFromRequest(request);
			String arg = argAndReq.getArgument();
			arg = arg.replaceAll(" ", "");
			String parameter = argAndReq.getRequest();
			if (arg.equalsIgnoreCase(SortConstants.ALPHABETICALLY))
				productRequest.setAlphabeticalOrdering(Boolean.parseBoolean(parameter));
			else if (arg.equalsIgnoreCase(SortConstants.PRICE)) {
				productRequest.setPriceFiltering(
						buildOrderingForPriceFiltering(parameter, checkForPriceFiltering(opRequest)));
			}
		}
		opRequest.setProductRequest(productRequest);
		return opRequest;
	}

	private PriceFiltering checkForPriceFiltering(OperationRequest opRequest) {
		if (opRequest.getProductRequest() != null)
			return opRequest.getProductRequest().getPriceFiltering();
		return null;
	}

	private PriceFiltering buildOrderingForPriceFiltering(String parameter, PriceFiltering opPriceFiltering) {
		PriceFiltering priceFiltering = opPriceFiltering;
		if (priceFiltering == null)
			priceFiltering = new PriceFiltering();
		SortingOrder sortingOrder = new SortingOrder();
		if (parameter.equalsIgnoreCase(SortConstants.ASCENDING))
			sortingOrder.setAscending(true);
		else if (parameter.equalsIgnoreCase(SortConstants.DESCENDING))
			sortingOrder.setDescending(true);
		priceFiltering.setSortingOrder(sortingOrder);
		return priceFiltering;
	}

	private OperationRequest buildSortingOrderForAccountsSearch(String requests, OperationRequest opRequest) {
		CustomerAccountRequest customerAccountRequest = opRequest.getCustomerAccountRequest();
		if (customerAccountRequest == null)
			customerAccountRequest = new CustomerAccountRequest();
		String[] customerAccountRequests = requests.split(OperationConstants.REQUESTSPLITTER);
		for (String request : customerAccountRequests) {
			ArgumentAndRequest argAndReq = getArgAndReqFromRequest(request);
			String arg = argAndReq.getArgument();
			arg = arg.replaceAll(" ", "");
			String parameter = argAndReq.getRequest();
			if (arg.equalsIgnoreCase(SortConstants.ALPHABETICALLY))
				customerAccountRequest.setAlphabeticalOrdering(Boolean.parseBoolean(parameter));
			else if (arg.equalsIgnoreCase(SortConstants.NUMBEROFORDERS))
				customerAccountRequest.setOrderingByOrdersFilter(buildSortForOrderingByOrders(parameter));
		}
		opRequest.setCustomerAccountRequest(customerAccountRequest);
		return opRequest;
	}

	private OrderingByOrdersFilter buildSortForOrderingByOrders(String parameter) {
		OrderingByOrdersFilter orderingByOrdersFilter = new OrderingByOrdersFilter();
		SortingOrder sortingOrder = new SortingOrder();
		if (parameter.equalsIgnoreCase(SortConstants.ASCENDING))
			sortingOrder.setAscending(true);
		if (parameter.equalsIgnoreCase(SortConstants.DESCENDING))
			sortingOrder.setDescending(true);
		orderingByOrdersFilter.setSortingOrder(sortingOrder);
		return orderingByOrdersFilter;
	}

	private FindDocumentTypeResponse findDocumentType(String[] args) {
		OperationRequest opRequest = new OperationRequest();
		List<String> otherArgs = new ArrayList<String>();
		for (String arg : args) {
			ArgumentAndRequest argumentAndRequest = getArgAndReq(arg);
			if (argumentAndRequest.getArgument().equalsIgnoreCase(OperationConstants.DOCTYPE)) {
				String request = argumentAndRequest.getRequest();
				if (request.equalsIgnoreCase(DocTypeConstants.PRODUCT))
					opRequest.setProductSearch(true);
				else if (request.equalsIgnoreCase(DocTypeConstants.ACCOUNT))
					opRequest.setCustomerAccountSearch(true);
				else if (request.equalsIgnoreCase(DocTypeConstants.SUPPLIER))
					opRequest.setSupplierSearch(true);
			} else
				otherArgs.add(arg);
		}
		FindDocumentTypeResponse findDocumentTypeResponse = new FindDocumentTypeResponse();
		findDocumentTypeResponse.setNewArgs(otherArgs);
		findDocumentTypeResponse.setOperationRequest(opRequest);
		return findDocumentTypeResponse;
	}

	private ArgumentAndRequest getArgAndReq(String arg) {
		ArgumentAndRequest argumentAndRequest = new ArgumentAndRequest();
		String[] argAndReq = arg.split(OperationConstants.ARGUMENTREQUESTSPLITTER);
		argumentAndRequest.setArgument(argAndReq[0]);
		argumentAndRequest.setRequest(argAndReq[1]);
		return argumentAndRequest;
	}

	private ArgumentAndRequest getArgAndReqFromRequest(String arg) {
		ArgumentAndRequest argumentAndRequest = new ArgumentAndRequest();
		String[] argAndReq = arg.split(OperationConstants.REQUESTANDPARAMETERSPLITTER);
		argumentAndRequest.setArgument(argAndReq[0]);
		argumentAndRequest.setRequest(argAndReq[1]);
		return argumentAndRequest;
	}

	public List<Product> getListOfAllProducts() {
		JSONObject jsonObject = getFileContents(FileLocation.PRODUCTSFILE);
		JSONArray jsonArray = jsonObject.getJSONArray(ProductBuilderConstants.PRODUCTS);
		List<Product> products = new ArrayList<Product>();
		for (int i = 0; jsonArray.length() > i; i++) {
			Product product = new Product();
			JSONObject jsonProduct = jsonArray.getJSONObject(i);
			product.setName(jsonProduct.getString(ProductBuilderConstants.NAME));
			product.setDescription(jsonProduct.getString(ProductBuilderConstants.DESCRIPTION));
			product.setPrice(jsonProduct.getDouble(ProductBuilderConstants.PRICE));
			product.setType(jsonProduct.getString(ProductBuilderConstants.TYPE));
			product.setSupplier(jsonProduct.getString(ProductBuilderConstants.SUPPLIER));
			product.setPaymentType(jsonProduct.getString(ProductBuilderConstants.PAYMENTTYPE));
			product.setMonthlyPayments(jsonProduct.getInt(ProductBuilderConstants.MONTHLYPAYMENTS));
			products.add(product);
		}
		return products;
	}

	private JSONObject getFileContents(String fileLocation) {
		JSONParser jsonParser = new JSONParser();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileLocation);
		org.json.simple.JSONObject jsonObject = new org.json.simple.JSONObject();
		try {
			jsonObject = (org.json.simple.JSONObject) jsonParser.parse(new InputStreamReader(inputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Couldn't find file");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Couldn't read file");
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("Unable to parse json");
		}
		JSONObject jSObject = new JSONObject(jsonObject.toJSONString());
		return jSObject;
	}

	public List<CustomerAccount> getListOfAllCustomerAccounts() {
		JSONObject jsonObject = getFileContents(FileLocation.CUSTOMERACCOUNTSFILE);
		JSONArray jsonArray = jsonObject.getJSONArray(CustomerAccountBuilderConstants.CUSTOMERACCOUNTS);
		List<CustomerAccount> customerAccounts = new ArrayList<CustomerAccount>();
		for (int i = 0; jsonArray.length() > i; i++) {
			CustomerAccount customerAccount = new CustomerAccount();
			JSONObject jsonAccount = jsonArray.getJSONObject(i);
			customerAccount.setName(jsonAccount.getString(CustomerAccountBuilderConstants.NAME));
			customerAccount.setEmailAddress(jsonAccount.getString(CustomerAccountBuilderConstants.EMAILADDRESS));
			customerAccount.setAddress(jsonAccount.getString(CustomerAccountBuilderConstants.ADDRESS));
			customerAccount.setPostcode(jsonAccount.getString(CustomerAccountBuilderConstants.POSTCODE));
			customerAccount.setNumberOfOrders(jsonAccount.getInt(CustomerAccountBuilderConstants.ORDERS));
			customerAccount.setPhonenumber(jsonAccount.getString(CustomerAccountBuilderConstants.PHONENUMBER));
			customerAccounts.add(customerAccount);
		}
		return customerAccounts;
	}

	public List<Supplier> getListOfAllSuppliers() {
		JSONObject jsonObject = getFileContents(FileLocation.SUPPLIERS);
		JSONArray jsonArray = jsonObject.getJSONArray(SupplierBuilderConstants.SUPPLIERS);
		List<Supplier> suppliers = new ArrayList<Supplier>();
		for (int i = 0; jsonArray.length() > i; i++) {
			Supplier supplier = new Supplier();
			JSONObject jsonSupplier = jsonArray.getJSONObject(i);
			supplier.setName(jsonSupplier.getString(SupplierBuilderConstants.NAME));
			supplier.setWebsiteUrl(jsonSupplier.getString(SupplierBuilderConstants.WEBSITEURL));
			suppliers.add(supplier);
		}
		return suppliers;
	}

}
