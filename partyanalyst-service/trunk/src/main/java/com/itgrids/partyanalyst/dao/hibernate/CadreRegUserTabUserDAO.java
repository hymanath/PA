package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegUserTabUserDAO;
import com.itgrids.partyanalyst.model.CadreRegUserTabUser;

public class CadreRegUserTabUserDAO extends GenericDaoHibernate<CadreRegUserTabUser, Long> implements ICadreRegUserTabUserDAO{

	public CadreRegUserTabUserDAO() {
		super(CadreRegUserTabUser.class);
		
	}

	public List<Object[]> getUserAssignedConstituencies(Long cadreRegUserId){
		Query query = getSession().createQuery("select distinct model1.constituency.constituencyId," +
								" model1.constituency.name" +
								" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
								" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
								" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
								" and model.cadreSurveyUser.isDeleted = 'N'");
		query.setParameter("cadreRegUserId", cadreRegUserId);
		return query.list();
	}
	
	public List<Object[]> getUserAssignedUsers(Long cadreRegUserId,Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.cadreSurveyUser.cadreSurveyUserId," +
								" model.cadreSurveyUser.userName" +
								" from CadreRegUserTabUser model");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(",CadreSurveyUserAssignDetails model1");
		sb.append(" where model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
								" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model1.constituency.constituencyId = :constituencyId and model1.isDeleted = 'N'");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("cadreRegUserId", cadreRegUserId);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
}
