/* order_test_1Test.java

	Purpose:
		
	Description:
		
	History:
		Sep, 7, 2010 17:30:59 PM

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

This program is distributed under Apache License Version 2.0 in the hope that
it will be useful, but WITHOUT ANY WARRANTY.
*/

//sort ==> Ascending B13~B17
public class SS_135_Test extends SSAbstractTestCase {
	@Override
	protected void executeTest() {
		rightClickCells(1,12,1,16);
	
		mouseOver(jq("a.z-menu-cnt:eq(3)"));		
		waitResponse();
		click(jq("$sortAscending a.z-menu-item-cnt"));
		waitResponse();
		
		//verify
		String b13text = getSpecifiedCell(1, 12).text();
		verifyEquals(b13text, "Average\u00A0total\u00A0assets");
		String b15text = getSpecifiedCell(1, 14).text();
		verifyEquals(b15text, "Current\u00A0assets");
		String b17text = getSpecifiedCell(1, 16).text();
		verifyEquals(b17text, "Total\u00A0assets");
	}
}



