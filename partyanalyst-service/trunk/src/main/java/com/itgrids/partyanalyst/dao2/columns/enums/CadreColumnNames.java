/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dao.columns.enums;

public enum CadreColumnNames {

	EDUCATION("education"),
	SOCIAL_CATEGORY("casteCategory"),
	OCCUPATON("occupation");
	
	private final String value;

    public String getValue() {
                return value;
    }

    private CadreColumnNames(String value) {
                   this.value=value;
    }
	
}
