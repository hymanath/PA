/* 
 * Copyright (c) 2010 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on August 26,2010
 */
package com.itgrids.partyanalyst.dao.columns.enums;

public enum SocialCategoryColumnNames {
	
	SOCIAL_CATEGORY_ID("socialCategoryId"),
	SOCIAL_CATEGORY("category");

	private final String value;

    public String getValue() {
                return value;
    }

    private SocialCategoryColumnNames(String value) {
                   this.value=value;
    }
}
