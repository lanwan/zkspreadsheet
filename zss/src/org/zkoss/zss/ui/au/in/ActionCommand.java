/* MenuitemCommand.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Feb 13, 2012 12:37:46 PM , Created by sam
}}IS_NOTE

Copyright (C) 2012 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
}}IS_RIGHT
*/
package org.zkoss.zss.ui.au.in;

import java.util.Map;

import org.zkoss.lang.Objects;
import org.zkoss.lang.Strings;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zss.model.sys.XBook;
import org.zkoss.zss.model.sys.XRanges;
import org.zkoss.zss.model.sys.XSheet;
import org.zkoss.zss.ui.Action;
import org.zkoss.zss.ui.Spreadsheet;
import org.zkoss.zss.ui.impl.Utils;

/**
 * @author sam
 */
public class ActionCommand implements Command {

	@Override
	public void process(AuRequest request) {
		final Component comp = request.getComponent();
		if (comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, ActionCommand.class);
		
		final Map data = (Map) request.getData();
		if (data == null || data.size() < 2)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA, new Object[] {Objects.toString(data), ActionCommand.class });
		
		Spreadsheet spreadsheet = ((Spreadsheet) comp);
		String tag = (String) data.get("tag");
		String act = (String) data.get("act");
		if ("toolbar".equals(tag)) {
			spreadsheet.getActionHandler().dispatch(act, data);
		} else if ("sheet".equals(tag) && spreadsheet.getXBook() != null) {
			String sheetId = (String) data.get("sheetId");
			XSheet sheet = Utils.getSheetByUuid(spreadsheet.getXBook(), sheetId);
			if (sheet != null) {
				processSheet(act, data, sheet, spreadsheet);
			}
		}
	}
	
	private void processSheet(String action, Map data, XSheet sheet, Spreadsheet spreadsheet) {
		XBook book = spreadsheet.getXBook();
		if ("add".equals(action)) {
			String prefix = Labels.getLabel(Action.SHEET.getLabelKey());
			if (Strings.isEmpty(prefix))
				prefix = "Sheet";
			int numSheet = book.getNumberOfSheets();
			XRanges.range(sheet).createSheet(prefix + " " + (numSheet + 1));
		} else if ("delete".equals(action)) {
			int numSheet = book.getNumberOfSheets();
			if (numSheet > 1) {
				XSheet sel = null;
				int index = book.getSheetIndex(sheet);
				if (index == numSheet - 1) {//delete last sheet, move select sheet left
					sel = book.getWorksheetAt(index - 1);
				} else { //move sheet right
					sel = book.getWorksheetAt(index + 1);
				}
				XRanges.range(sheet).deleteSheet();
				spreadsheet.setSelectedSheet(sel.getSheetName());
			}
		} else if ("rename".equals(action)) {
			String name = (String) data.get("name");
			XRanges.range(sheet).setSheetName(name);
		} else if ("protect".equals(action)) {
			boolean protect = sheet.getProtect();
			XRanges.range(sheet).protectSheet(protect ? null : "");// toggle sheet protect
		} else if ("moveLeft".equals(action)) {
			int index = book.getSheetIndex(sheet);
			if (index > 0) {
				XRanges.range(sheet).setSheetOrder(index - 1);
			}
		} else if ("moveRight".equals(action)) {
			int index = book.getSheetIndex(sheet);
			if (index < book.getNumberOfSheets() - 1) {
				XRanges.range(sheet).setSheetOrder(index + 1);
			}
		}
	}
}
