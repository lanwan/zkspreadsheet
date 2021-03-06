/* Sheet.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		2013/5/1 , Created by dennis
}}IS_NOTE

Copyright (C) 2013 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zss.api.model;

import java.util.List;

/**
 * This interface provides the access to a sheet of a {@link Book}.
 * @author dennis
 * @since 3.0.0
 */
public interface Sheet {

	public org.zkoss.poi.ss.usermodel.Sheet getPoiSheet();
	
	public Book getBook();
	
	/**
	 * Gets the object for synchronized a sheet.
	 * Note: you shouldn't synchronize a sheet directly, you have to get the sync object to synchronize it
	 * @return
	 */
	public Object getSync();

	public boolean isProtected();

	public boolean isAutoFilterEnabled();

	public boolean isDisplayGridlines();
	
	public boolean isRowHidden(int row);
	
	public boolean isColumnHidden(int column);

	public String getSheetName();
	
	public List<Chart> getCharts();
	
	public List<Picture> getPictures();
	
	/**
	 * Get number of row freeze of this sheet, 0 means no row freeze
	 * @return
	 */
	public int getRowFreeze();
	
	/**
	 * Get number of column freeze of this sheet, 0 means no column freeze
	 * @return
	 */
	public int getColumnFreeze();

	public boolean isPrintGridlines();
	
	/**
	 * get row height in pixel
	 * @param row
	 * @return row height in pixel
	 */
	public int getRowHeight(int row);
	
	/**
	 * get column width in pixel
	 * @param column
	 * @return column width in pixel
	 */
	public int getColumnWidth(int column);
	
	/**
	 * get the first row which contains data of this sheet
	 * @return
	 */
	public int getFirstRow();
	
	/**
	 * get the last row which contains data of this sheet
	 * @return
	 */
	public int getLastRow();

	/**
	 * Get the first column of row which contains data
	 * @param row
	 * @return -1 if not such column or no no such row
	 */
	public int getFirstColumn(int row);
	
	/**
	 * Get the last column of row which contains data
	 * @param row
	 * @return -1 if not such column or no no such row
	 */
	public int getLastColumn(int row);
	

}
