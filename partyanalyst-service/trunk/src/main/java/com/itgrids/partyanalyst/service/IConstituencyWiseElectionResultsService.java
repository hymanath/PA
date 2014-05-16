package com.itgrids.partyanalyst.service;

public interface IConstituencyWiseElectionResultsService {
	public Object constituencyResults(String stateNo,Long level,Long constituencyNo,String description);
	public void bulkDataInsertUsingRemotingWebserviceCall(String insertFor);
    public void bulkDataInsertUsingRemotingWebserviceCallParliament(String insertFor,Long stateId);

}
