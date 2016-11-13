package com.moneysupermarkettest.technical.runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.moneysupermarkettest.technical.helpers.DataHelperImplTest;
import com.moneysupermarkettest.technical.operations.MainTest;
import com.moneysupermarkettest.technical.services.CustomerAccountSearchServiceTest;
import com.moneysupermarkettest.technical.services.ProductSearchServiceTest;
import com.moneysupermarkettest.technical.services.SupplierSearchServiceTest;

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(MainTest.class, DataHelperImplTest.class, SupplierSearchServiceTest.class,
				ProductSearchServiceTest.class, CustomerAccountSearchServiceTest.class);

		int i = 0;
		for (Failure failure : result.getFailures()) {
			i++;
			System.out.println(failure.getClass() + "- Failed");
		}
		if (i == 0)
			System.out.println("All tests successfully passed.");
		else
			System.out.println(i + " test(s) failed. See output to find out which.");
	}
}