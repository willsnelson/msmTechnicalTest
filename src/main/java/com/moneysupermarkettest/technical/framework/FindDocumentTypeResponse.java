package com.moneysupermarkettest.technical.framework;

import java.util.List;

public class FindDocumentTypeResponse {

	private OperationRequest operationRequest;

	private List<String> newArgs;

	/**
	 * @return the operationRequest
	 */
	public OperationRequest getOperationRequest() {
		return operationRequest;
	}

	/**
	 * @param operationRequest
	 *            the operationRequest to set
	 */
	public void setOperationRequest(OperationRequest operationRequest) {
		this.operationRequest = operationRequest;
	}

	/**
	 * @return the newArgs
	 */
	public List<String> getNewArgs() {
		return newArgs;
	}

	/**
	 * @param newArgs
	 *            the newArgs to set
	 */
	public void setNewArgs(List<String> newArgs) {
		this.newArgs = newArgs;
	}

}
