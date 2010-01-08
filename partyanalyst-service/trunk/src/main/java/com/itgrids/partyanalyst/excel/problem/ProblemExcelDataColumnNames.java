/* 
 * Copyright (c) 2009 IT Grids India LTD.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 2, 2010
 */
package com.itgrids.partyanalyst.excel.problem;

public enum ProblemExcelDataColumnNames {

	MANDAL_NAME("Mandal_Name"),
	CONSTITUENCY_NAME("Constituency_Name"),
	TOWNSHIP_NAME("Township_Name"),
	AREATYPE_DETAILS("AreaType_Details"),
	DATE("Problem_Date"),
	POLLINGBOOTHPARTNO("PollingBoothPartNo"),
	END("END"),
	RURAL("Rural"),
	PARTYANALYST("PartyAnalyst");
		
	private final String value;
	
	public String getValue(){
		return value;
	}
	private ProblemExcelDataColumnNames(String value){
		this.value = value;
	}
	
}
