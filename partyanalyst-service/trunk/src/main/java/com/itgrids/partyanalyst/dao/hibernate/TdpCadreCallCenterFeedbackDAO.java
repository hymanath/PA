package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterFeedbackDAO;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterFeedback;


public class TdpCadreCallCenterFeedbackDAO extends GenericDaoHibernate<TdpCadreCallCenterFeedback, Long> implements ITdpCadreCallCenterFeedbackDAO {
	
	
	public TdpCadreCallCenterFeedbackDAO() {
		super(TdpCadreCallCenterFeedback.class);		
	}
}
