package com.itgrids.partyanalyst.dao.columns.enums;


/**
 * Candidate Table Column Name Constants in the form enum. 
 * 
 * @author <a href="mailto:mritlsk@yahoo.com">Inayatullah</a>
 *
 */
public enum ElectionTypeColumnNames {
	
	ELECTION_TYPE_ID("electionTypeId"),
	ELECTION_TYPE("electionType"),
	SCOPE("scope");
	private final String value;
    public String getValue() {
                return value;
    }
    private ElectionTypeColumnNames(String value) {
                   this.value=value;
    }	
}
