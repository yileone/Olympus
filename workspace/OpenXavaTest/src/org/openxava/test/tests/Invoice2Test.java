package org.openxava.test.tests;

import org.openxava.tests.*;


/**
 * 
 * @author Javier Paniza
 */

public class Invoice2Test extends ModuleTestBase {
	
	public Invoice2Test(String testName) {
		super(testName, "Invoice2");		
	}
	
	public void testGroupBy() throws Exception { 

		assertAllGroupBys( 
			"No grouping",
			"Group by year",
			"Group by number",
			"Group by date",
			"Group by month of date",
			"Group by year of date",
			"Group by vat %",
			"Group by amounts sum",
			"Group by number of customer",
			"Group by customer"
		);
		
		assertAction("CRUD.deleteSelected");
		assertAction("List.viewDetail");
		assertAction("CRUD.deleteRow");		
		assertTrue(getHtml().contains("There are 9 records in list"));
		assertListRowCount(9);
		assertValuesInList(0, "2002",  "1",  "1/1/02", "16.0",  "2,500.00", "1", "Javi");
		assertValuesInList(1, "2004",  "2",  "1/4/04", "42.0",     "11.00", "2", "Juanillo");
		assertValuesInList(2, "2004",  "9",  "1/4/04", "71.0",  "4,396.00", "1", "Javi");
		assertValuesInList(3, "2004", "10", "12/4/04", "18.0",  "1,189.00", "2", "Juanillo");
		assertValuesInList(4, "2004", "11", "11/4/06", "22.0",      "0.00", "3", "Carmelo");
		assertValuesInList(5, "2004", "12", "11/5/06", "13.0",    "110.00", "4", "Cuatrero");
		assertValuesInList(6, "2007", "14", "5/28/07", "16.0",  "6,059.00", "1", "Javi");
		assertValuesInList(7, "2009",  "1", "6/23/09", "16.0",      "0.00", "1", "Javi");
		assertValuesInList(8, "2011",  "1", "3/14/11", "18.0", "18,207.00", "1", "Javi");
		
		selectGroupBy("Group by date");
		assertNoAction("CRUD.deleteSelected");
		assertNoAction("List.viewDetail");
		assertNoAction("CRUD.deleteRow");
		assertFalse(getHtml().contains("There are 9 records in list"));
		assertListRowCount(8);
		assertListColumnCount(2);
		assertValuesInList(0,  "1/1/02",  "2,500.00");
		assertValuesInList(1,  "1/4/04",  "4,407.00");
		assertValuesInList(2, "12/4/04",  "1,189.00");
		assertValuesInList(3, "11/4/06",      "0.00");
		assertValuesInList(4, "11/5/06",    "110.00");
		assertValuesInList(5, "5/28/07",  "6,059.00");
		assertValuesInList(6, "6/23/09",      "0.00");
		assertValuesInList(7, "3/14/11", "18,207.00"); 
		
		selectGroupBy("Group by year");
		assertNoAction("CRUD.deleteSelected");
		assertNoAction("List.viewDetail");
		assertNoAction("CRUD.deleteRow");		
		assertListRowCount(5);
		assertListColumnCount(2);
		assertValuesInList(0, "2002",  "2,500.00");
		assertValuesInList(1, "2004",  "5,706.00");
		assertValuesInList(2, "2007",  "6,059.00");
		assertValuesInList(3, "2009",      "0.00");
		assertValuesInList(4, "2011", "18,207.00");
		
		selectGroupBy("Group by customer");
		assertNoAction("CRUD.deleteSelected");
		assertNoAction("List.viewDetail");
		assertNoAction("CRUD.deleteRow");		
		assertListRowCount(4);
		assertListColumnCount(2);
		assertValuesInList(0, "31,162.00", "Javi");
		assertValuesInList(1,  "1,200.00", "Juanillo");
		assertValuesInList(2,      "0.00", "Carmelo");
		assertValuesInList(3,    "110.00", "Cuatrero");
		
		selectGroupBy("Group by month of date");
		assertNoAction("CRUD.deleteSelected");
		assertNoAction("List.viewDetail");
		assertNoAction("CRUD.deleteRow");
		assertListRowCount(7);
		assertListColumnCount(2);
		assertValuesInList(0, "2002/1",  "2,500.00");
		assertValuesInList(1, "2004/1",  "4,407.00");
		assertValuesInList(2, "2004/12", "1,189.00");
		assertValuesInList(3, "2006/11",   "110.00");
		assertValuesInList(4, "2007/5",  "6,059.00");
		assertValuesInList(5, "2009/6",      "0.00"); 
		assertValuesInList(6, "2011/3", "18,207.00");  
		
		selectGroupBy("Group by year of date");
		assertNoAction("CRUD.deleteSelected");
		assertNoAction("List.viewDetail");
		assertNoAction("CRUD.deleteRow");
		assertListRowCount(6);
		assertListColumnCount(2);
		assertValuesInList(0, "2002",  "2,500.00");
		assertValuesInList(1, "2004",  "5,596.00");
		assertValuesInList(2, "2006",    "110.00");
		assertValuesInList(3, "2007",  "6,059.00");
		assertValuesInList(4, "2009",      "0.00");
		assertValuesInList(5, "2011", "18,207.00");  
		
		selectGroupBy("No grouping");
		assertAction("CRUD.deleteSelected");
		assertAction("List.viewDetail");
		assertAction("CRUD.deleteRow");
		assertTrue(getHtml().contains("There are 9 records in list"));
		assertListRowCount(9);
		assertValuesInList(0, "2002",  "1",  "1/1/02", "16.0",  "2,500.00", "1", "Javi");
		assertValuesInList(1, "2004",  "2",  "1/4/04", "42.0",     "11.00", "2", "Juanillo");
		assertValuesInList(2, "2004",  "9",  "1/4/04", "71.0",  "4,396.00", "1", "Javi");
		assertValuesInList(3, "2004", "10", "12/4/04", "18.0",  "1,189.00", "2", "Juanillo");
		assertValuesInList(4, "2004", "11", "11/4/06", "22.0",      "0.00", "3", "Carmelo");
		assertValuesInList(5, "2004", "12", "11/5/06", "13.0",    "110.00", "4", "Cuatrero");
		assertValuesInList(6, "2007", "14", "5/28/07", "16.0",  "6,059.00", "1", "Javi");
		assertValuesInList(7, "2009",  "1", "6/23/09", "16.0",      "0.00", "1", "Javi");
		assertValuesInList(8, "2011",  "1", "3/14/11", "18.0", "18,207.00", "1", "Javi");
		
		selectGroupBy("Group by year");
		assertListRowCount(5);
		assertListColumnCount(2);
		assertListSelectedConfiguration("All");
		execute("List.filter");
		assertListSelectedConfiguration("All");
		resetModule();
		assertListSelectedConfiguration("All");
		assertListRowCount(9);
		assertListColumnCount(7);
	}
	
	public void testDependentEditorsForHiddenPropertiesInCollectionElement() throws Exception {
		execute("CRUD.new");
		execute("InvoiceDetail2.new", "viewObject=xava_view_details");
		assertNotExists("familyList");
		assertNotExists("productList");
		execute("InvoiceDetail2.showProductList");
		assertExists("familyList");
		assertExists("productList");
		assertValidValuesCount("productList", 1); 
		setValue("familyList", "1");
		assertValidValuesCount("productList", 7); 
	}
	
	public void testTouchContainerFromCallback() throws Exception { 
		if (!usesAnnotatedPOJO()) return; // This case is only implemented in JPA
		execute("CRUD.new");
		setValue("number", "66");
		setValue("vatPercentage", "16");
		setValue("customer.number", "1");
		assertCollectionRowCount("details", 0);
		
		// Creating a new detail
		execute("InvoiceDetail2.new", "viewObject=xava_view_details");
		assertNotExists("details.invoice.year"); 
		setValue("quantity", "7");
		setValue("unitPrice", "8");
		assertValue("amount", "56.00"); 
		setValue("product.number", "1");
		assertValue("product.description", "MULTAS DE TRAFICO");
		execute("Collection.save");
		assertNoErrors();
		assertCollectionRowCount("details", 1);
		execute("CRUD.refresh");
		assertValue("amountsSum", "56.00");
		
		// Creating another one
		execute("InvoiceDetail2.new", "viewObject=xava_view_details");
		setValue("quantity", "10");
		setValue("unitPrice", "10");
		assertValue("amount", "100.00");
		setValue("product.number", "1");
		assertValue("product.description", "MULTAS DE TRAFICO");
		execute("Collection.save");
		assertNoErrors();
		assertCollectionRowCount("details", 2);
		execute("CRUD.refresh");
		assertValue("amountsSum", "156.00");
		
		// Modifiying
		execute("Collection.edit", "row=1,viewObject=xava_view_details");
		setValue("quantity", "20");
		setValue("unitPrice", "10");
		execute("Collection.save");
		assertNoErrors();
		assertCollectionRowCount("details", 2);
		execute("CRUD.refresh");
		assertValue("amountsSum", "256.00");
		
		// Removing
		execute("Collection.edit", "row=1,viewObject=xava_view_details");
		setValue("quantity", "20");
		setValue("unitPrice", "10");
		execute("Collection.remove");
		assertNoErrors();
		assertCollectionRowCount("details", 1);
		execute("CRUD.refresh");
		assertValue("amountsSum", "56.00");
		
		execute("CRUD.delete");
		assertNoErrors();		
	}
	
	public void testInjectPropertiesOfContainerInOnCreateCalculatorOfAggregate() throws Exception {   
		execute("CRUD.new");
		setValue("number", "66");
		setValue("vatPercentage", "16");
		setValue("customer.number", "1");
		assertCollectionRowCount("details", 0);
		execute("InvoiceDetail2.new", "viewObject=xava_view_details");
		setValue("quantity", "7");
		setValue("unitPrice", "8");
		assertValue("amount", "56.00");
		setValue("product.number", "1"); 
		assertValue("product.description", "MULTAS DE TRAFICO");
		execute("Collection.save");
		assertNoErrors(); 
		assertCollectionRowCount("details", 1);
		
		execute("CRUD.delete");
		assertNoErrors();
	}
	
	public void testCollectionOrderedByAPropertyOfAReference_valueOfNestedRerenceInsideAnEmbeddedCollection() throws Exception {
		execute("CRUD.new");
		setValue("year", "2002"); 
		setValue("number", "1");
		execute("CRUD.refresh");
		assertCollectionRowCount("details", 2);
		assertValueInCollection("details", 0, "product.description", "XAVA");
		assertValueInCollection("details", 1, "product.description", "IBM ESERVER ISERIES 270");
		
		execute("Collection.edit", "row=0,viewObject=xava_view_details");
		assertValue("product.description", "XAVA");
		assertValue("product.family.description", "SOFTWARE");
		closeDialog();
		
		execute("Collection.edit", "row=1,viewObject=xava_view_details");
		assertValue("product.description", "IBM ESERVER ISERIES 270");
		assertValue("product.family.description", "HARDWARE");		
	}
	
	public void testMinSizeForCollections() throws Exception {
		execute("CRUD.new");
		setValue("number", "66");
		setValue("vatPercentage", "18");
		setValue("customer.number", "1");
		execute("CRUD.save");
		assertError("It's required at least 1 element in Details of Invoice 2"); 
	} 							
}
