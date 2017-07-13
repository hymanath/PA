package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionMappingDAO;
import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;

public class BoothInchargeRoleConditionMappingDAO extends GenericDaoHibernate<BoothInchargeRoleConditionMapping, Long> implements IBoothInchargeRoleConditionMappingDAO{

	public BoothInchargeRoleConditionMappingDAO() {
		super(BoothInchargeRoleConditionMapping.class);
	}
	
	public List<Object[]> getBoothInchargeRolesWithMinMAxCount(Long boothId,List<Long> enrollmentYrIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append( " select model.boothInchargeRoleConditionMappingId, model.boothInchargeRoleCondition.minMembers," +
				"model.boothInchargeRoleCondition.maxMembers,model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId" +
				",model.boothInchargeRoleCondition.boothInchargeRole.roleName from BoothInchargeRoleConditionMapping model where " ); 
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append( "  model.booth.boothId = :boothId " );
		
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0)
			sb.append( " and model.boothInchargeEnrollment.boothInchargeEnrollmentId in (:enrollmentYrIds) " );
		Query query=getSession().createQuery(sb.toString());
		
		if(boothId != null && boothId.longValue() >0l)
			query.setParameter("boothId", boothId);
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0)
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		
		return query.list();
		
	}
}
