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

	public List<Object[]> getUserAssignedConstituencies(Long cadreRegUserId,Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.constituency.constituencyId," +
								" model1.constituency.name" +
								" from CadreRegUserTabUser model");
		sb.append(",CadreSurveyUserAssignDetails model1");
		sb.append(" where model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
								" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
								" and model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model1.isDeleted = 'N'");
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model1.constituency.district.districtId = :districtId");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("cadreRegUserId", cadreRegUserId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		return query.list();
		/*Query query = getSession().createQuery("select distinct model1.constituency.constituencyId," +
								" model1.constituency.name" +
								" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
								" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
								" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
								" and model.cadreSurveyUser.isDeleted = 'N'");
		query.setParameter("cadreRegUserId", cadreRegUserId);
		return query.list();*/
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
	
	public List<Object[]> getAllAssignedConstituencies(Long districtId,String searchType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model1.constituency.constituencyId," +
										" model1.constituency.name" +
								" from CadreRegUserTabUser model");
		sb.append(",CadreSurveyUserAssignDetails model1");
		sb.append(" where (model.cadreRegUser.userType = :searchType or model.cadreRegUser.userType = 'WM')" +
								" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'" +
								" and model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model1.isDeleted = 'N'");
		if(districtId != null && districtId.longValue() > 0l)
			sb.append(" and model1.constituency.district.districtId = :districtId");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("searchType", searchType);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		return query.list();
		/*Query query = getSession().createQuery("select distinct model1.constituency.constituencyId," +
										" model1.constituency.name" +
										" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
										" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
										" and model.cadreRegUser.userType = 'FM'" +
										" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
										" and model.cadreSurveyUser.isDeleted = 'N'");
		return query.list();*/
	}
	
	public List<Object[]> getAllUserAssignedUsers(Long constituencyId,String searchType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.cadreSurveyUser.cadreSurveyUserId," +
								" model.cadreSurveyUser.userName" +
								" from CadreRegUserTabUser model");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(",CadreSurveyUserAssignDetails model1");
		sb.append(" where (model.cadreRegUser.userType = :searchType or model.cadreRegUser.userType = 'WM')" +
								" and model.isDeleted = 'N' and model.cadreSurveyUser.isDeleted = 'N'");
		if(constituencyId != null && constituencyId.longValue() > 0l)
			sb.append(" and model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model1.constituency.constituencyId = :constituencyId and model1.isDeleted = 'N'");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("searchType", searchType);
		if(constituencyId != null && constituencyId.longValue() > 0l)
			query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getUserAssignedDistricts(Long cadreRegUserId){
		Query query = getSession().createQuery("select distinct model1.constituency.district.districtId," +
								" model1.constituency.district.districtName" +
								" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
								" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
								" and model.cadreRegUser.cadreRegUserId = :cadreRegUserId" +
								" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
								" and model.cadreSurveyUser.isDeleted = 'N'");
		query.setParameter("cadreRegUserId", cadreRegUserId);
		return query.list();
	}
	
	public List<Object[]> getAllAssignedDistricts(String searchType){
		Query query = getSession().createQuery("select distinct model1.constituency.district.districtId," +
										" model1.constituency.district.districtName" +
										" from CadreRegUserTabUser model,CadreSurveyUserAssignDetails model1" +
										" where model.cadreSurveyUser.cadreSurveyUserId = model1.cadreSurveyUser.cadreSurveyUserId" +
										" and (model.cadreRegUser.userType = :searchType or model.cadreRegUser.userType = 'WM')" +
										" and model.isDeleted = 'N' and model1.isDeleted = 'N'" +
										" and model.cadreSurveyUser.isDeleted = 'N'");
		query.setParameter("searchType", searchType);
		return query.list();
	}
}
