package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCallCenterComment;


public interface ITdpCadreCallCenterCommentDAO extends GenericDao<TdpCadreCallCenterComment, Long>{ 
	 public Integer updateComments(Long feedbackId);
}