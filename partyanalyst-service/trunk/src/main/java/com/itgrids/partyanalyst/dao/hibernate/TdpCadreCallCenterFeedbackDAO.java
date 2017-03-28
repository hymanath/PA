package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterFeedbackDAO;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterFeedback;


public class TdpCadreCallCenterFeedbackDAO extends GenericDaoHibernate<TdpCadreCallCenterFeedback, Long> implements ITdpCadreCallCenterFeedbackDAO {
	
	
	public TdpCadreCallCenterFeedbackDAO() {
		super(TdpCadreCallCenterFeedback.class);		
	}
	

	  public Long getFeebackIdByTdpCadre(Long tdpCadreId){
			Query query = getSession().createQuery("select model.tdpCadreCallCenterFeedbackId from TdpCadreCallCenterFeedback model where model.tdpCadreId = :tdpCadreId");			
			query.setParameter("tdpCadreId", tdpCadreId);
			return (Long)query.uniqueResult();
		}
}
