package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.model.MessageToCandidate;

public interface IMessageToCandidateDAO extends GenericDao<MessageToCandidate, Long>{
	

	 public  List getAllOpenedMessages(Date firstDate, Date secondDate,String selectstatus);
	 public Integer controlMessages(Long id,String message, String isApproved);
	 public List<Object[]> getUserMessages(Long candidateId,int startIndex,int resultsCount);
	 public List<Long> getUserMessagesCount(Long candidateId);
}