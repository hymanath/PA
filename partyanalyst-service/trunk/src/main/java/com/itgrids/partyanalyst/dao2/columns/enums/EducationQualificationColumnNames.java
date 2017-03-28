/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dao.columns.enums;

public enum EducationQualificationColumnNames {

	EDUCATION_QUALIFICATION_ID("eduQualificationId"),
	EDUCATION_QUALIFICATION("qualification");
	
	private final String value;

    public String getValue() {
                return value;
    }

    private EducationQualificationColumnNames(String value) {
                   this.value=value;
    }
}
