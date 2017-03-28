package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TdpCadreCallCenterFeedback;

public interface ITdpCadreCallCenterFeedbackDAO extends GenericDao<TdpCadreCallCenterFeedback, Long>{ 
	 public Long getFeebackIdByTdpCadre(Long tdpCadreId);
}
