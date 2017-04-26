package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtSmsActionTypeDAO;
import com.itgrids.partyanalyst.model.GovtSmsActionType;


public class GovtSmsActionTypeDAO extends GenericDaoHibernate<GovtSmsActionType, Long> implements
IGovtSmsActionTypeDAO {

	public GovtSmsActionTypeDAO() {
		super(GovtSmsActionType.class);
	}

	public List<String> getSMSTextforActionTypeId(Long govtAlertActionTypeId,Long govtSmsTypeId,Long languageId,Long govtUserTypeId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.smsText from GovtSmsActionType model where model.languageId =:languageId and model.govtUserTypeId =:govtUserTypeId ");
		queryStr.append(" and model.govtSmsTypeId =:govtSmsTypeId and model.govtAlertActionTypeId =:govtAlertActionTypeId  ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("languageId", languageId);
		query.setParameter("govtUserTypeId", govtUserTypeId);
		query.setParameter("govtSmsTypeId", govtSmsTypeId);
		query.setParameter("govtAlertActionTypeId", govtAlertActionTypeId);
		return query.list();
	}
}
