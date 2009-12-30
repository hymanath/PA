
/**
* Copyright (c) 2009 IT Grids.
* All Rights Reserved.
*
* IT Grids Confidential Information.
* Created on July 11, 2009
*/
package com.itgrids.partyanalyst.dao.columns.enums;
 /**
* Candidate Table Column Name Constants in the form enum. 
* 
* @author <a href="mailto:sai.basetti@gmail.com">Sai Krishna</a>
* */


public enum ElectionColumnNames {

	ELECTION_ID("electionId"),
	END_DATE("endDate"),
	ELECTION_YEAR("electionYear");
	
	private final String value;

    public String getValue() {
                return value;
    }
    private ElectionColumnNames(String value) {
        this.value=value;
    }
}
