package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertClarification;
import com.itgrids.partyanalyst.model.AlertClarificationComments;

public interface IAlertClarificationCommentsDAO extends GenericDao<AlertClarificationComments, Long>{
	
	public List<Object[]> getClarificationComments(Long alertId);
	public Integer updateCommentStatus(Long commentId);
}
