package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.model.CandidateSubscriptions;

public interface ICandidateSubscriptionsDAO extends GenericDao<CandidateSubscriptions,Long>{

	public Long getSubscriptionCount(Long userId,Long candidateId);
	
	public List<CandidateSubscriptions> getSubscriberDetails(Long candidateId ,Long userId);
	
	public Integer unSubscriptionDetails(Long candidateId,Long userId);

}
