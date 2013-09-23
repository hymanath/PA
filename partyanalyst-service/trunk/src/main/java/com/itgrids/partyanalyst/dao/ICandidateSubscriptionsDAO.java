package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CandidateSubscriptions;

public interface ICandidateSubscriptionsDAO extends GenericDao<CandidateSubscriptions,Long>{

	public Long getSubscriptionCount(Long userId,Long candidateId);
	
	public List<CandidateSubscriptions> getSubscriberDetails(Long candidateId ,Long userId);
	
	public Integer unSubscriptionDetails(Long candidateId,Long userId);
	
	public List<Object[]> getAllSubscriberDetails();
	
	public List<Object[]> getAllUserSubscribedCandidatePages(Long userId);
	
	public List<Object[]> getAllCandidates();
	
	public List<Long> getAllCandidatesSubscribedByUser(Long userId);
	
	public List<Object[]> getCandidateSubscriptionsForPublicProfileStreeming(Long userId,Date toDate,Date fromDate);
	

}
