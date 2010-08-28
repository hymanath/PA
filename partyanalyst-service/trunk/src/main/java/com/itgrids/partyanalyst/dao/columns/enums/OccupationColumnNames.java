/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dao.columns.enums;

public enum OccupationColumnNames {
	
	OCCUPATION_ID("occupationId"),
	OCCUPATION("occupation");
	
	private final String value;

    public String getValue() {
                return value;
    }

    private OccupationColumnNames(String value) {
                   this.value=value;
    }

}
