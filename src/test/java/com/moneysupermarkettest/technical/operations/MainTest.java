package com.moneysupermarkettest.technical.operations;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.moneysupermarkettest.technical.constants.FileLocation;

public class MainTest {

	@BeforeClass
	public static void setup() {
		FileLocation.PRODUCTSFILE = "testproducts.txt";
		FileLocation.CUSTOMERACCOUNTSFILE = "testcustomeraccounts.txt";
		FileLocation.SUPPLIERS = "testsuppliers.txt";
	}

	@Before
	public void before() {
		System.out.println("");
	}

	@Test
	public void testJustProduct() {
		System.out.println("testJustProduct - START");
		try {
			Main.main(new String[] { "doctype=product" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testJustProduct - END");
	}

	@Test
	public void testProductFilterByName() {
		System.out.println("testProductFilterByName - START");
		try {
			Main.main(new String[] { "doctype=product", "filter=name:test 32gb" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testProductFilterByName - END");
	}

	@Test
	public void testProductFilterByPrice() {
		System.out.println("testProductFilterByPrice - START");
		try {
			Main.main(new String[] { "doctype=product", "filter=price:25-35" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testProductFilterByPrice - END");
	}

	@Test
	public void testProductFilterByType() {
		System.out.println("testProductFilterByType - START");
		try {
			Main.main(new String[] { "doctype=product", "filter=type:energy" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testProductFilterByType - END");
	}

	@Test
	public void testOrderByPriceAscending() {
		System.out.println("testOrderByPriceAscending - START");
		try {
			Main.main(new String[] { "doctype=product", "sort=price:ascending" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testOrderByPriceAscending - END");
	}

	@Test
	public void testOrderByPriceDescending() {
		System.out.println("testOrderByPriceDescending - START");
		try {
			Main.main(new String[] { "doctype=product", "sort=price:descending" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testOrderByPriceDescending - END");
	}

	@Test
	public void testOrderByProductNameAlphabetically() {
		System.out.println("testOrderByProductNameAlphabetically - START");
		try {
			Main.main(new String[] { "doctype=product", "sort=alphabetically:true" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testOrderByProductNameAlphabetically - END");
	}

	@Test
	public void testFilterAccountsByName() {
		System.out.println("testFilterAccountsByName - START");
		try {
			Main.main(new String[] { "doctype=account", "filter=name:Will Snelson" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testFilterAccountsByName - END");
	}

	@Test
	public void testFilterAccountsByPostcode() {
		System.out.println("testFilterAccountsByPostcode - START");
		try {
			Main.main(new String[] { "doctype=account", "filter=postcode:SK103QF" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testFilterAccountsByPostcode - END");
	}

	@Test
	public void testSortAccountsAlphabetically() {
		System.out.println("testSortAccountsAlphabetically - START");
		try {
			Main.main(new String[] { "doctype=account", "sort=alphabetically:true" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testSortAccountsAlphabetically - END");
	}

	@Test
	public void testSortAccountsByNumberOfOrdersAscending() {
		System.out.println("testSortAccountsByNumberOfOrdersAscending - START");
		try {
			Main.main(new String[] { "doctype=account", "sort=numberOfOrders:ascending" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testSortAccountsByNumberOfOrdersAscending - END");
	}

	@Test
	public void testSortAccountsByNumberOfOrdersDescending() {
		System.out.println("testSortAccountsByNumberOfOrdersDescending - START");
		try {
			Main.main(new String[] { "doctype=account", "sort=numberOfOrders:descending" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testSortAccountsByNumberOfOrdersDescending - END");
	}

	@Test
	public void testFilterSuppliersByName() {
		System.out.println("testFilterSuppliersByName - START");
		try {
			Main.main(new String[] { "doctype=supplier", "filter=name:Vodafone" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testFilterSuppliersByName - END");
	}

	@Test
	public void testFilterAllSuppliersAndProductsStocked() {
		System.out.println("testFilterAllSuppliersAndProductsStocked - START");
		try {
			Main.main(new String[] { "doctype=supplier", "filter=productsStocked:true" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testFilterAllSuppliersAndProductsStocked - END");
	}

	@Test
	public void testFilterSupplierAndProductsStocked() {
		System.out.println("testFilterSupplierAndProductsStocked - START");
		try {
			Main.main(new String[] { "doctype=supplier", "filter=productsStocked:true, name:vodafone" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testFilterSupplierAndProductsStocked - END");
	}

	@Test
	public void testOrderByNameAlphabetically() {
		System.out.println("testOrderByNameAlphabetically - START");
		try {
			Main.main(new String[] { "doctype=supplier", "sort=alphabetically:true" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testOrderByNameAlphabetically - END");
	}

	@Test
	public void testJustAccounts() {
		System.out.println("testJustAccounts - START");
		try {
			Main.main(new String[] { "doctype=account" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testJustAccounts - END");
	}

	@Test
	public void testJustSuppliers() {
		System.out.println("testJustSuppliers - START");
		try {
			Main.main(new String[] { "doctype=supplier" });
		} catch (Exception e) {
			Assert.fail();
		}
		System.out.println("testJustSuppliers - END");
	}

	@After
	public void after() {
		System.out.println("");
	}

}
