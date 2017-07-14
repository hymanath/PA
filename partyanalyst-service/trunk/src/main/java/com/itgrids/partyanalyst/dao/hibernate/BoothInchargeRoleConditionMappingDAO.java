package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionMappingDAO;
import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothInchargeRoleConditionMappingDAO extends GenericDaoHibernate<BoothInchargeRoleConditionMapping, Long> implements IBoothInchargeRoleConditionMappingDAO{

	public BoothInchargeRoleConditionMappingDAO() {
		super(BoothInchargeRoleConditionMapping.class);
	}
	
	public List<Object[]> getBoothInchargeRolesWithMinMAxCount(Long boothId,List<Long> enrollmentYrIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append( " select model.boothInchargeRoleConditionMappingId, model.boothInchargeRoleCondition.minMembers," +
				"model.boothInchargeRoleCondition.maxMembers,model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId" +
				",model.boothInchargeRoleCondition.boothInchargeRole.roleName from BoothInchargeRoleConditionMapping model where model.boothInchargeCommittee is not null " ); 
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append( " and model.boothInchargeCommittee.address.booth.boothId = :boothId " );
		
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0)
			sb.append( " and model.boothInchargeCommittee.boothInchargeEnrollmentId in (:enrollmentYrIds) " );
		Query query=getSession().createQuery(sb.toString()+" order by model.boothInchargeRoleCondition.boothInchargeRole.roleName");
		
		if(boothId != null && boothId.longValue() >0l)
			query.setParameter("boothId", boothId);
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0)
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		
		return query.list();
		
	}
	public List<Object[]> getBoothMinMaxRequiredMemberRoleWise(Long boothId,Long boothInchargeEnrollmentId) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		 				 " model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId," +
		 				 " model.boothInchargeRoleCondition.boothInchargeRole.roleName," +
		 				 " model.boothInchargeRoleCondition.minMembers," +
		 				 " model.boothInchargeRoleCondition.maxMembers " +
		 				 " from BoothInchargeRoleConditionMapping model " +
		 				 " where " +
		 				 " model.isDeleted='N' and model.boothId=:boothId " +
		 				 " and model.boothInchargeEnrollmentId=:boothInchargeEnrollmentId " +
		 				 " group by " +
		 				 " model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("boothId", boothId);
		query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		return query.list();
	}
	public int updateBoothStatus(Long boothId,Long boothInchargeEnrollmentId,Date dateTime) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" update BoothInchargeCommittee model set model.isConfirmed='Y',model.completedDate=:completedDate" +
		 				 " where " +
		 				 " model.isDeleted='N' and  model.boothId=:boothId and model.boothInchargeEnrollmentId=:boothInchargeEnrollmentId ");
		 Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("boothId", boothId);
		  query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		  query.setParameter("completedDate", dateTime);
		  return query.executeUpdate();
	}
	
	public List<Object[]> getBoothDetailsForTehsilId(List<Long> tehsilIds,Long constituencyId){
		Query query = getSession().createQuery("select distinct model.boothInchargeCommittee.boothId," +
											" model.boothInchargeCommittee.booth.partNo," +
											" model.boothInchargeCommittee.booth.villagesCovered" +
											" from BoothInchargeRoleConditionMapping model" +
											" where model.boothInchargeCommittee.address.tehsil.tehsilId in (:tehsilIds)" +
											" and model.boothInchargeCommittee.address.constituency.constituencyId = :constituencyId" +
											" and model.boothInchargeCommittee.booth.publicationDate.publicationDateId = :publicationDate");
		
		query.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}

	public List<Object[]> getBoothsDetailsForMuncipality(List<Long> lcalElcBdyId,Long constituencyId){
		Query query = getSession().createQuery("select distinct model.boothInchargeCommittee.boothId," +
				" model.boothInchargeCommittee.booth.partNo," +
				" model.boothInchargeCommittee.booth.villagesCovered" +
				" from BoothInchargeRoleConditionMapping model" +
				" where model.boothInchargeCommittee.address.localElectionBody.localElectionBodyId in (:lcalElcBdyId)" +
				" and model.boothInchargeCommittee.address.constituency.constituencyId = :constituencyId" +
				" and model.boothInchargeCommittee.booth.publicationDate.publicationDateId = :publicationDate");

			query.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
			query.setParameterList("tehsilIds", lcalElcBdyId);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
	}
	
}
