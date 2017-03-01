package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.jdbc.object.SqlQuery;

import com.itgrids.partyanalyst.dao.IEventInviteeDAO;
import com.itgrids.partyanalyst.model.EventInvitee;
import com.itgrids.partyanalyst.utils.IConstants;

public class EventInviteeDAO extends GenericDaoHibernate<EventInvitee, Long> implements IEventInviteeDAO{

	public EventInviteeDAO() {
		super(EventInvitee.class);
	}
	
	public List<Object[]> getEventInviteesCountByLocationType(String locationType,Date currentDate)
	{
		
		StringBuilder str = new StringBuilder();
		
		
		str.append("select model.event.eventId,count(model.eventInviteeId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
		str.append(" from EventInvitee model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		return query.list();
	}
	
	
	/*public List<Object[]> getEventInviteesCountForState(String locationType)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.event.eventId,count(model.eventInviteeId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		str.append(" from EventInvitee model");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.constituencyId order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}*/
	
	public List<Object[]> getEventInviteesCountByLocationTypeAndEvent(String locationType,Date currentDate,Long eventId)
	{
		
		StringBuilder str = new StringBuilder();
		
		
		str.append("select count(model.eventInviteeId), ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
				
		str.append(" from EventInvitee model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.event.eventId = :eventId");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT))
			str.append(" group by model.event.eventId,model.tdpCadre.userAddress.constituency.district.districtId order by model.tdpCadre.userAddress.constituency.district.districtId");
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId order by model.tdpCadre.userAddress.constituency.constituencyId");
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		query.setParameter("eventId", eventId);
		return query.list();
	}
	
	public Long getEventInviteesCountByState(Long stateId,Date currentDate,Long eventId)
	{
		
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.eventInviteeId) ");
		str.append(" from EventInvitee model where date(:currentDate) between date(model.event.eventStartTime) and date(model.event.eventEndTime) and model.event.eventId = :eventId" +
				" and model.tdpCadre.userAddress.state.stateId = 1 ");
		if(stateId == 1){
			str.append(" and model.tdpCadre.userAddress.district.districtId <= 10 ");
		}
		else if(stateId == 36){
			str.append(" and model.userAddress.district.districtId >= 11 ");
		}
		Query query = getSession().createQuery(str.toString());
		query.setDate("currentDate", currentDate);
		query.setParameter("eventId", eventId);
		return (Long)query.uniqueResult();
	}

	public Long checkIsExistDetails(Long id,Long eventId,String memberType)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.eventInviteeId from EventInvitee model where model.eventId =:eventId ");
		if(memberType.trim().equalsIgnoreCase("tdpCadre"))
		{
			queryStr.append(" and model.tdpCadreId =:id ");
		}
		else if(memberType.trim().equalsIgnoreCase("publicRepresentative"))
		{
			queryStr.append(" and model.publicRepresentativeId =:id ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameter("id", id);
		return (Long) query.uniqueResult(); 
	}
	
	public List<Object[]> checkInvitees(List<Long> tdpCadreIds,Long eventId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.tdpCadreId,model.eventInviteeId from EventInvitee model where model.eventId =:eventId and model.tdpCadreId in(:tdpCadreIds)");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("eventId", eventId);
		query.setParameterList("tdpCadreIds", tdpCadreIds);
		return  query.list(); 	
	}
	
	public List<Object[]> getInvitationCountforCandidate(Long tdpCadreId)
	{
		Query query = getSession().createQuery(" select model.eventId,model.event.name, count(model.tdpCadreId),model.event.eventType.eventTypeId, model.event.eventType.type,model.absenteeRemark  from EventInvitee model where model.tdpCadreId =:tdpCadreId and " +
				" model.event.isActive ='true'  group by  model.eventId  ");
		query.setParameter("tdpCadreId", tdpCadreId);
		return  query.list(); 	
	}
	
	public List<String> getTdpCadreMemberShipsIdsByEvent(Long eventId){
		 Query query = getSession().createQuery(" select model.tdpCadre.memberShipNo from EventInvitee model" +
		 " where model.eventId =:eventId ");
		 
		 query.setParameter("eventId", eventId);
		 return query.list();
	}
	
	public List<Object[]> getEventInviteesCountByLocation(String locationType,Set<Long> locationIds,Long eventId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId");	
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" model.tdpCadre.userAddress.constituency.constituencyId");
		}
		str.append(",count(distinct model.tdpCadre.tdpCadreId) ");
			
		str.append(" from EventInvitee model where model.event.eventId = :eventId ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" and model.tdpCadre.userAddress.constituency.district.districtId in (:locationIds)");
			str.append(" group by model.tdpCadre.userAddress.constituency.district.districtId");
		}	
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:locationIds)");
			str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId");
		}	
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		query.setParameterList("locationIds",locationIds);
		return query.list();
	}
	
	public List<Object[]> getPublicRepresentiveInvitessForEvent(Long eventId,List<Long> designationIds){

		
		Query query = getSession().createQuery("" +
		" select    pr.publicRepresentativeType.publicRepresentativeTypeId,pr.publicRepresentativeType.type,count(distinct ei.tdpCadreId)" +
		" from      EventInvitee ei,PublicRepresentative pr,TdpCadreCandidate tca" +
		" where     ei.tdpCadreId = tca.tdpCadreId and tca.candidateId = pr.candidateId " +
		"           and ei.eventId = :eventId and pr.publicRepresentativeType.publicRepresentativeTypeId in (:designationIds) " +
		"           and ei.tdpCadre.isDeleted = 'N' and ei.tdpCadre.enrollmentYear = 2014 "+
		" group by  pr.publicRepresentativeType.publicRepresentativeTypeId " +
		" order by  pr.publicRepresentativeType.orderNo ");
		
		query.setParameter("eventId",eventId);
		query.setParameterList("designationIds",designationIds);
		return query.list();
	}
	
	public List<Object[]> dayWisePublicRepInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds){
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append(" select pr.publicRepresentativeType.publicRepresentativeTypeId,pr.publicRepresentativeType.type," +
				"          date(ea.attendedTime),count(distinct ea.tdpCadre.tdpCadreId) "+
		          " from   EventAttendee ea,EventInvitee ei,PublicRepresentative pr,TdpCadreCandidate tca " +
				  " where  ei.tdpCadreId = tca.tdpCadreId and tca.candidateId = pr.candidateId " +
				  "         and ea.event.parentEventId = ei.event.eventId and " +
				  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
				  "        and pr.publicRepresentativeType.publicRepresentativeTypeId in (:designationIds) ");
		
		sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and ea.event.eventId in  (:eventIds)");
		}
		
		sb.append("  and date(ea.attendedTime) between :startDate and :endDate ");
		
		sb.append(" group by pr.publicRepresentativeType.publicRepresentativeTypeId,date(ea.attendedTime) ");
        
        Query query = getSession().createQuery(sb.toString());
		query.setDate("startDate", startDate);
		query.setDate("endDate", endDate);
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameterList("designationIds",designationIds);
		
		return query.list();
	}
	
	
public List<Object[]> totalPublicRepInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds){
		
		StringBuilder sb =  new StringBuilder();
		
		sb.append(" select pr.publicRepresentativeType.publicRepresentativeTypeId,pr.publicRepresentativeType.type," +
				"   count(distinct ea.tdpCadre.tdpCadreId) "+
		          " from   EventAttendee ea,EventInvitee ei,PublicRepresentative pr,TdpCadreCandidate tca " +
				  " where  ei.tdpCadreId = tca.tdpCadreId and tca.candidateId = pr.candidateId " +
				  "         and ea.event.parentEventId = ei.event.eventId and " +
				  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
				  "        and pr.publicRepresentativeType.publicRepresentativeTypeId in (:designationIds) ");
		sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
		if(eventIds != null && eventIds.size() > 0){
			sb.append(" and ea.event.eventId in  (:eventIds)");
		}
		sb.append(" group by pr.publicRepresentativeType.publicRepresentativeTypeId ");
        Query query = getSession().createQuery(sb.toString());
		if(eventIds != null && eventIds.size() > 0){
			query.setParameterList("eventIds", eventIds);
		}
		query.setParameter("isActive", IConstants.TRUE);
		query.setParameterList("designationIds",designationIds);
		
		return query.list();
	}
	
	public List<Long> getCandidateTdpCadreIds(Long eventId,Long designationId){
		Query query = getSession().createQuery(" select model.tdpCadreId " +
				" from EventInvitee model,TdpCadreCandidate tcc,PublicRepresentative pr " +
				"  where model.eventId=:eventId " +
				" and model.tdpCadreId=tcc.tdpCadreId " +
				" and tcc.candidateId=pr.candidateId " +
				" and pr.publicRepresentativeTypeId=:designationId ");
		query.setParameter("eventId", eventId);
		query.setParameter("designationId", designationId);
		return query.list();
	}
	
	public List<Object[]> getCommitteeLevelInvitessForEvent(Long eventId,List<Long> committeeLevelIds){

		
		Query query = getSession().createQuery("" +
		" select    TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel,count(distinct ei.tdpCadreId)" +
		" from      EventInvitee ei,TdpCommitteeMember TCM" +
		" where     ei.tdpCadreId = TCM.tdpCadreId  " +
		"           and ei.eventId = :eventId and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:committeeLevelIds) " +
		"           and ei.tdpCadre.isDeleted = 'N' and ei.tdpCadre.enrollmentYear = 2014 "+
		" group by  TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId");
		query.setParameter("eventId",eventId);
		query.setParameterList("committeeLevelIds",committeeLevelIds);
		return query.list();
	}
	
	
public List<Object[]> getCommitteeRoleInvitessForEvent(Long eventId,List<Long> committeeRoleIds){

		
		Query query = getSession().createQuery("" +
		" select    TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,TCM.tdpCommitteeRole.tdpRoles.role,count(distinct ei.tdpCadreId),TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel" +
		" from      EventInvitee ei,TdpCommitteeMember TCM" +
		" where     ei.tdpCadreId = TCM.tdpCadreId  " +
		"           and ei.eventId = :eventId and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId in (:committeeRoleIds) " +
		"           and ei.tdpCadre.isDeleted = 'N' and ei.tdpCadre.enrollmentYear = 2014 " +
		" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in(5,11)" +
		" and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1"+
		" group by  TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId");
		query.setParameter("eventId",eventId);
		query.setParameterList("committeeRoleIds",committeeRoleIds);
		return query.list();
	}


public List<Object[]> dayWiseCommitteeLevelInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds){
	
	StringBuilder sb =  new StringBuilder();
	
	sb.append(" select TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," +
			"          date(ea.attendedTime),count(distinct ea.tdpCadre.tdpCadreId) "+
	          " from   EventAttendee ea,EventInvitee ei,TdpCommitteeMember TCM" +
	          " where  ei.tdpCadreId = TCM.tdpCadreId  " + 
	          "         and ea.event.parentEventId = ei.event.eventId and " +
			  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
			  "        and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:designationIds) ");
	
	sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
	if(eventIds != null && eventIds.size() > 0){
		sb.append(" and ea.event.eventId in  (:eventIds)");
	}
	sb.append("  and date(ea.attendedTime) between :startDate and :endDate ");
	
	sb.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,date(ea.attendedTime) ");
    
    Query query = getSession().createQuery(sb.toString());
	query.setDate("startDate", startDate);
	query.setDate("endDate", endDate);
	if(eventIds != null && eventIds.size() > 0){
		query.setParameterList("eventIds", eventIds);
	}
	query.setParameter("isActive", IConstants.TRUE);
	query.setParameterList("designationIds",designationIds);
	
	return query.list();
}


public List<Object[]> totalCommitteeLevelInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds){
	
	StringBuilder sb =  new StringBuilder();
	sb.append(" select TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," +
			"          count(distinct ea.tdpCadre.tdpCadreId) "+
	          " from   EventAttendee ea,EventInvitee ei,TdpCommitteeMember TCM" +
	          " where  ei.tdpCadreId = TCM.tdpCadreId  " + 
	          "         and ea.event.parentEventId = ei.event.eventId and " +
			  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
			  "        and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (:designationIds) ");
	
	sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
	if(eventIds != null && eventIds.size() > 0){
		sb.append(" and ea.event.eventId in  (:eventIds)");
	}
	
	sb.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId ");
    
    Query query = getSession().createQuery(sb.toString());
    if(eventIds != null && eventIds.size() > 0){
		query.setParameterList("eventIds", eventIds);
	}
	query.setParameter("isActive", IConstants.TRUE);
	query.setParameterList("designationIds",designationIds);
	
	return query.list();
}



public List<Object[]> dayWiseCommitteeRoleInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds){
	
	StringBuilder sb =  new StringBuilder();
	
	sb.append(" select TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,TCM.tdpCommitteeRole.tdpRoles.role," +
			"          date(ea.attendedTime),count(distinct ea.tdpCadre.tdpCadreId),TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel "+
	          " from   EventAttendee ea,EventInvitee ei,TdpCommitteeMember TCM" +
	          " where  ei.tdpCadreId = TCM.tdpCadreId  " + 
	          "         and ea.event.parentEventId = ei.event.eventId and " +
			  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
			  "        and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (5,11)" +
			  " and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1" +
			  " and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId in(:designationIds) ");
	
	sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
	if(eventIds != null && eventIds.size() > 0){
		sb.append(" and ea.event.eventId in  (:eventIds)");
	}
	sb.append("  and date(ea.attendedTime) between :startDate and :endDate ");
	
	sb.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,date(ea.attendedTime) ");
    
    Query query = getSession().createQuery(sb.toString());
	query.setDate("startDate", startDate);
	query.setDate("endDate", endDate);
	if(eventIds != null && eventIds.size() > 0){
		query.setParameterList("eventIds", eventIds);
	}
	query.setParameter("isActive", IConstants.TRUE);
	query.setParameterList("designationIds",designationIds);
	
	return query.list();
}
public List<Object[]> getDistrictAffliatedCommitteeInvitessForEvent(Long eventId,List<Long> committeeRoleIds){

	
	Query query = getSession().createQuery("" +
	" select    TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,TCM.tdpCommitteeRole.tdpRoles.role,count(distinct ei.tdpCadreId),TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel" +
	" from      EventInvitee ei,TdpCommitteeMember TCM" +
	" where     ei.tdpCadreId = TCM.tdpCadreId  " +
	"           and ei.eventId = :eventId and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId in (:committeeRoleIds) " +
	"           and ei.tdpCadre.isDeleted = 'N' and ei.tdpCadre.enrollmentYear = 2014 " +
	" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in(11)" +
	" and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId != 1 "+
	" group by  TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId");
	query.setParameter("eventId",eventId);
	query.setParameterList("committeeRoleIds",committeeRoleIds);
	return query.list();
}
public List<Object[]> dayWiseDistrictAffliatedCommitteeInviteesAttendedForEvent(Date startDate,Date endDate,List<Long> eventIds,List<Long> designationIds){
	
	StringBuilder sb =  new StringBuilder();
	
	sb.append(" select TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,TCM.tdpCommitteeRole.tdpRoles.role," +
			"          date(ea.attendedTime),count(distinct ea.tdpCadre.tdpCadreId),TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel "+
	          " from   EventAttendee ea,EventInvitee ei,TdpCommitteeMember TCM" +
	          " where  ei.tdpCadreId = TCM.tdpCadreId  " + 
	          "         and ea.event.parentEventId = ei.event.eventId and " +
			  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
			  "        and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (11)" +
			  " and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId in(:designationIds)" +
			  " and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId != 1 ");
	
	sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
	if(eventIds != null && eventIds.size() > 0){
		sb.append(" and ea.event.eventId in  (:eventIds)");
	}
	sb.append("  and date(ea.attendedTime) between :startDate and :endDate ");
	
	sb.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,date(ea.attendedTime) ");
    
    Query query = getSession().createQuery(sb.toString());
	query.setDate("startDate", startDate);
	query.setDate("endDate", endDate);
	if(eventIds != null && eventIds.size() > 0){
		query.setParameterList("eventIds", eventIds);
	}
	query.setParameter("isActive", IConstants.TRUE);
	query.setParameterList("designationIds",designationIds);
	
	return query.list();
}


public List<Long> getCandidateTdpCadreIdsForCommitteeLevel(Long eventId,Long committeeLevelId){
	Query query = getSession().createQuery(" select model.tdpCadreId " +
			" from EventInvitee model,TdpCommitteeMember TCM " +
			"  where model.eventId=:eventId " +
			" and model.tdpCadreId=TCM.tdpCadreId " +
			" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId=:committeeLevelId ");
	query.setParameter("eventId", eventId);
	query.setParameter("committeeLevelId", committeeLevelId);
	return query.list();
}


public List<Long> getCandidateTdpCadreIdsForCommitteeRole(Long eventId,Long committeeRoleId,String committeeLevel){
	StringBuilder str = new StringBuilder();
	str.append(" select model.tdpCadreId " +
			" from EventInvitee model,TdpCommitteeMember TCM " +
			"  where model.eventId=:eventId " +
			" and model.tdpCadreId=TCM.tdpCadreId " +
			" and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=:committeeRoleId " +
			" and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1");
	if(committeeLevel.equalsIgnoreCase("District"))
		str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 11 ");
	if(committeeLevel.equalsIgnoreCase("Mandal"))
		str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 5 ");
	Query query = getSession().createQuery(str.toString());
	query.setParameter("eventId", eventId);
	query.setParameter("committeeRoleId", committeeRoleId);
	return query.list();
}
public List<Long> getCandidateTdpCadreIdsForAffliatedCommitteeRole(Long eventId,Long committeeRoleId,String committeeLevel){
	StringBuilder str = new StringBuilder();
	str.append(" select model.tdpCadreId " +
			" from EventInvitee model,TdpCommitteeMember TCM " +
			"  where model.eventId=:eventId " +
			" and model.tdpCadreId=TCM.tdpCadreId " +
			" and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId=:committeeRoleId " +
			" and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId != 1");
	if(committeeLevel.equalsIgnoreCase("District"))
		str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 11 ");
	if(committeeLevel.equalsIgnoreCase("Mandal"))
		str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = 5 ");
	Query query = getSession().createQuery(str.toString());
	query.setParameter("eventId", eventId);
	query.setParameter("committeeRoleId", committeeRoleId);
	return query.list();
}


public List<Object[]> totalCommitteeRoleInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds){
	
	StringBuilder sb =  new StringBuilder();
	
	sb.append(" select TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,TCM.tdpCommitteeRole.tdpRoles.role," +
			"          count(distinct ea.tdpCadre.tdpCadreId),TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel "+
	          " from   EventAttendee ea,EventInvitee ei,TdpCommitteeMember TCM" +
	          " where  ei.tdpCadreId = TCM.tdpCadreId  " + 
	          "         and ea.event.parentEventId = ei.event.eventId and " +
			  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
			  "        and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (5,11)" +
			  " and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId = 1" +
			  " and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId in(:designationIds) ");
	
	sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
	if(eventIds != null && eventIds.size() > 0){
		sb.append(" and ea.event.eventId in  (:eventIds)");
	}
	
	sb.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId");
    
    Query query = getSession().createQuery(sb.toString());
	
	if(eventIds != null && eventIds.size() > 0){
		query.setParameterList("eventIds", eventIds);
	}
	query.setParameter("isActive", IConstants.TRUE);
	query.setParameterList("designationIds",designationIds);
	
	return query.list();
}
public List<Object[]> totalDistrictAffliatedCommitteeInviteesAttendedForEvent(List<Long> eventIds,List<Long> designationIds){
	
	StringBuilder sb =  new StringBuilder();
	
	sb.append(" select TCM.tdpCommitteeRole.tdpRoles.tdpRolesId,TCM.tdpCommitteeRole.tdpRoles.role," +
			"          count(distinct ea.tdpCadre.tdpCadreId),TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel "+
	          " from   EventAttendee ea,EventInvitee ei,TdpCommitteeMember TCM" +
	          " where  ei.tdpCadreId = TCM.tdpCadreId  " + 
	          "         and ea.event.parentEventId = ei.event.eventId and " +
			  "        ea.tdpCadre.tdpCadreId = ei.tdpCadre.tdpCadreId and ea.event.isInviteeExist = 'Y' " +
			  "        and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId in (11)" +
			  " and TCM.tdpCommitteeRole.tdpRoles.tdpRolesId in(:designationIds)" +
			  " and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId != 1 ");
	
	sb.append(" and ea.event.isActive =:isActive and ea.tdpCadre.isDeleted = 'N' and ea.tdpCadre.enrollmentYear = 2014 ");
	if(eventIds != null && eventIds.size() > 0){
		sb.append(" and ea.event.eventId in  (:eventIds)");
	}
	
	sb.append(" group by TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId,TCM.tdpCommitteeRole.tdpRoles.tdpRolesId");
    
    Query query = getSession().createQuery(sb.toString());
	if(eventIds != null && eventIds.size() > 0){
		query.setParameterList("eventIds", eventIds);
	}
	query.setParameter("isActive", IConstants.TRUE);
	query.setParameterList("designationIds",designationIds);
	
	return query.list();
}
  //caste wise

	public List<Object[]> getEventInviteesCountByCasteIds(Set<Long> casteIds,Long eventId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.tdpCadre.casteState.caste.casteId,count(distinct model.tdpCadre.tdpCadreId)" +
				   " from   EventInvitee model " +
				   " where  model.event.eventId = :eventId and model.tdpCadre.casteState.caste.casteId in (:casteIds) " +
				   "        and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				   " group by model.tdpCadre.casteState.caste.casteId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		query.setParameterList("casteIds",casteIds);
		return query.list();
	}
	public List<Object[]> getEventInviteesCountByageWiseIds(Set<Long> ageRangeIds,Long eventId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select ageRange.voterAgeRangeId,count(distinct model.tdpCadre.tdpCadreId)" +
				   " from   EventInvitee model,VoterAgeRange ageRange " +
				   " where  model.tdpCadre.age>= ageRange.minValue and model.tdpCadre.age<= ageRange.maxValue and model.event.eventId = :eventId and ageRange.voterAgeRangeId in (:ageRangeIds) " +
				   "        and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				   " group by ageRange.voterAgeRangeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		query.setParameterList("ageRangeIds",ageRangeIds);
		return query.list();
	}
	public List<Object[]> getTotalCadresCountByCasteIds(Set<Long> casteIds)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.casteState.caste.casteId,count(distinct model.tdpCadreId)" +
				   " from   TdpCadre model " +
				   " where  model.casteState.caste.casteId in (:casteIds) and model.isDeleted = 'N' and model.enrollmentYear = 2014 " +
				   " group by model.casteState.caste.casteId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("casteIds",casteIds);
		return query.list();
	}
	
	public List<Object[]> getEventInviteesCountByGender(Long eventId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.tdpCadre.gender,count(distinct model.tdpCadre.tdpCadreId)" +
				   " from   EventInvitee model " +
				   " where  model.event.eventId = :eventId and model.tdpCadre.gender is not null and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014" +
				   " group by model.tdpCadre.gender ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		return query.list();
	}
	public List<Object[]> getEventInviteesCountByCasteCategoryIdsExcludingMinorities(Set<Long> casteCategoryIds,Long eventId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId," +
				"           model.tdpCadre.casteState.casteCategoryGroup.casteCategory.categoryName," +
				"           count(distinct model.tdpCadre.tdpCadreId)" +
				   " from   EventInvitee model " +
				   " where  model.event.eventId = :eventId and model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId in (:casteCategoryIds) " +
				   "        and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				   "        and model.tdpCadre.casteState.casteStateId not in("+IConstants.NEW_MINORITY_CASTE_IDS+") " +
				   " group by model.tdpCadre.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		query.setParameterList("casteCategoryIds",casteCategoryIds);
		return query.list();
	}
	public Long getEventInviteesCountForMinorities(Long eventId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(distinct model.tdpCadre.tdpCadreId)" +
				   " from   EventInvitee model " +
				   " where  model.event.eventId = :eventId " +
				   "        and model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014 " +
				   "        and model.tdpCadre.casteState.casteStateId  in("+IConstants.NEW_MINORITY_CASTE_IDS+") " );
				   
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getEventInviteesCountByCadreLocation(String locationType,Long locationId,Long eventId,String searchType,String mandalType)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			if(searchType.equalsIgnoreCase(IConstants.DISTRICT))
			{
			str.append(" model.tdpCadre.userAddress.constituency.district.districtId,model.tdpCadre.userAddress.constituency.district.districtName ");	
			}
			else if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" model.tdpCadre.userAddress.constituency.constituencyId,model.tdpCadre.userAddress.constituency.name ");
			}
		}
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalType != null && mandalType.equalsIgnoreCase(IConstants.MANDAL))
			{
				str.append(" model.tdpCadre.userAddress.tehsil.tehsilId,model.tdpCadre.userAddress.tehsil.tehsilName ");
			} 
			else if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalType != null && mandalType.equalsIgnoreCase(IConstants.MUNCPAL_CORP))
			{
				str.append(" model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,model.tdpCadre.userAddress.localElectionBody.name ");
			}
			}
			else if(locationType.equalsIgnoreCase(IConstants.MANDAL) && searchType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append("  model.tdpCadre.userAddress.panchayat.panchayatId,model.tdpCadre.userAddress.panchayat.panchayatName ");
			}
			str.append(",count(distinct model.tdpCadre.tdpCadreId) ");
			
			str.append(" from EventInvitee model where model.event.eventId = :eventId ");
		if(locationType.equalsIgnoreCase(IConstants.DISTRICT)){
			if(searchType.equalsIgnoreCase(IConstants.DISTRICT)){
				
				str.append(" and model.tdpCadre.userAddress.constituency.district.districtId =:locationId");
				str.append(" group by model.tdpCadre.userAddress.constituency.district.districtId ");
			}else if(searchType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				
				str.append(" and model.tdpCadre.userAddress.constituency.district.districtId =:locationId");
				str.append(" group by model.tdpCadre.userAddress.constituency.constituencyId order by model.tdpCadre.userAddress.constituency.name asc");
			}
		}	
		else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY) && searchType.equalsIgnoreCase(IConstants.MANDAL)){
				str.append(" and model.tdpCadre.userAddress.constituency.constituencyId =:locationId");
			if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalType != null && mandalType.equalsIgnoreCase(IConstants.MANDAL)){
				
				str.append("  and model.tdpCadre.userAddress.localElectionBody is null group by model.tdpCadre.userAddress.tehsil.tehsilId ");
			}else if(searchType.equalsIgnoreCase(IConstants.MANDAL) && mandalType != null && mandalType.equalsIgnoreCase(IConstants.MUNCPAL_CORP)){
				
				str.append(" group by  model.tdpCadre.userAddress.localElectionBody.localElectionBodyId ");
			 }
		}else if(locationType.equalsIgnoreCase(IConstants.MANDAL) && searchType.equalsIgnoreCase(IConstants.PANCHAYAT)){
				
			str.append(" and model.tdpCadre.userAddress.tehsil.tehsilId =:locationId");
				str.append(" and model.tdpCadre.userAddress.localElectionBody is null group by model.tdpCadre.userAddress.panchayat.panchayatId ");
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("eventId",eventId);
		query.setParameter("locationId",locationId);
		return query.list();
	}
	
	public List<Object[]> getInviteedCountByEventsAndLocations(List<Long> eventIds,List<Long> locationValues,Long locationId){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.event.parentEventId , count(distinct model.tdpCadre.tdpCadreId) ,model.event.name ");
		
		str.append(" from EventInvitee model where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
		if(eventIds != null && eventIds.size() >0){
			str.append(" and model.event.parentEventId in (:eventIds) " );	
		}
		if(locationId.longValue() == 2l){
			str.append(" and model.tdpCadre.userAddress.state.stateId  in (:locationValues) ");
		}else if(locationId.longValue() == 3l){
			str.append(" and model.tdpCadre.userAddress.constituency.district.districtId  in (:locationValues) ");
		}else if(locationId.longValue() == 4l){
			str.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId  in (:locationValues) ");
		}else if(locationId.longValue() == 5l){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId  in (:locationValues) ");
		}else if(locationId.longValue() == 6l){
			str.append(" and model.tdpCadre.userAddress.tehsil.tehsilId  in (:locationValues) ");
		}else if(locationId.longValue() == 7l){
			str.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId  in (:locationValues) ");
		}else if(locationId.longValue() == 8l){
			str.append(" and model.tdpCadre.userAddress.panchayat.panchayatId  in (:locationValues) ");
		}else if(locationId.longValue() == 8l){
			str.append(" and model.tdpCadre.userAddress.ward.constituencyId  in (:locationValues) ");
		}
		str.append(" group by model.event.parentEventId ");
		Query query = getSession().createQuery(str.toString());
		
		if(eventIds != null && eventIds.size() >0){
			query.setParameterList("eventIds", eventIds);	
		}
		if(locationValues != null && locationValues.size() >0){
			query.setParameterList("locationValues", locationValues);
		}
		
		return query.list();
	}
public List<Object[]> getInviteeAttendeeCountByEventsAndLocations(List<Long> eventIds,List<Long> locationValues,Long locationId){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.event.parentEventId , count(distinct model.tdpCadre.tdpCadreId),model.event.name  ");
		
		str.append(" from EventInvitee model,EventAttendee model1  where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
		str.append(" and model1.event.eventId = model.event.eventId  and model1.tdpCadre.tdpCadreId = model.tdpCadre.tdpCadreId  ");
		if(eventIds != null && eventIds.size() >0){
			str.append(" and model.event.parentEventId in (:eventIds) " );	
		}
		if(locationId.longValue() == 2l){
			str.append(" and model.tdpCadre.userAddress.state.stateId  in (:locationValues) ");
		}else if(locationId.longValue() == 3l){
			str.append(" and model.tdpCadre.userAddress.constituency.district.districtId  in (:locationValues) ");
		}else if(locationId.longValue() == 4l){
			str.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId  in (:locationValues) ");
		}else if(locationId.longValue() == 5l){
			str.append(" and model.tdpCadre.userAddress.constituency.constituencyId  in (:locationValues) ");
		}else if(locationId.longValue() == 6l){
			str.append(" and model.tdpCadre.userAddress.tehsil.tehsilId  in (:locationValues) ");
		}else if(locationId.longValue() == 7l){
			str.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId  in (:locationValues) ");
		}else if(locationId.longValue() == 8l){
			str.append(" and model.tdpCadre.userAddress.panchayat.panchayatId  in (:locationValues) ");
		}else if(locationId.longValue() == 8l){
			str.append(" and model.tdpCadre.userAddress.ward.constituencyId  in (:locationValues) ");
		}
		str.append(" group by model.event.parentEventId ");
		Query query = getSession().createQuery(str.toString());
		
		if(eventIds != null && eventIds.size() >0){
			query.setParameterList("eventIds", eventIds);	
		}
		if(locationValues != null && locationValues.size() >0){
			query.setParameterList("locationValues", locationValues);
		}
		
		return query.list();
	}
public List<Object[]> getTotAttendeeCountByEventsAndLocations(List<Long> eventIds,List<Long> locationValues,Long locationId){
	
	StringBuilder str = new StringBuilder();
	
	str.append(" select model.event.parentEventId , count(distinct model.tdpCadre.tdpCadreId),model.event.name  ");
	
	str.append(" from EventAttendee model where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
	if(eventIds != null && eventIds.size() >0){
		str.append(" and model.event.parentEventId in (:eventIds) " );	
	}
	if(locationId.longValue() == 2l){
		str.append(" and model.tdpCadre.userAddress.state.stateId  in (:locationValues) ");
	}else if(locationId.longValue() == 3l){
		str.append(" and model.tdpCadre.userAddress.constituency.district.districtId  in (:locationValues) ");
	}else if(locationId.longValue() == 4l){
		str.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId  in (:locationValues) ");
	}else if(locationId.longValue() == 5l){
		str.append(" and model.tdpCadre.userAddress.constituency.constituencyId  in (:locationValues) ");
	}else if(locationId.longValue() == 6l){
		str.append(" and model.tdpCadre.userAddress.tehsil.tehsilId  in (:locationValues) ");
	}else if(locationId.longValue() == 7l){
		str.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId  in (:locationValues) ");
	}else if(locationId.longValue() == 8l){
		str.append(" and model.tdpCadre.userAddress.panchayat.panchayatId  in (:locationValues) ");
	}else if(locationId.longValue() == 8l){
		str.append(" and model.tdpCadre.userAddress.ward.constituencyId  in (:locationValues) ");
	}
	str.append(" group by model.event.parentEventId ");
	Query query = getSession().createQuery(str.toString());
	
	if(eventIds != null && eventIds.size() >0){
		query.setParameterList("eventIds", eventIds);	
	}
	if(locationValues != null && locationValues.size() >0){
		query.setParameterList("locationValues", locationValues);
	}
	
	return query.list();
}


public List<Object[]> getEventInviteedCountByEvent(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds){
	
	StringBuilder queryStr = new StringBuilder();
	
	queryStr.append(" select model.event.eventId,model.event.name, count(distinct model.tdpCadre.tdpCadreId) ");
	
	queryStr.append(" from EventInvitee model where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
	if(eventIds != null && eventIds.size() >0){
		queryStr.append(" and model.event.eventId in (:eventIds) " );	
	}
	 if(stateId != null && stateId.longValue() > 0){
		/*	if(stateId.longValue()==1l){
				queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");	
			}else if(stateId.longValue()==36l){
				queryStr.append(" and model.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}*/
			if(stateId.longValue()==1l){
				  queryStr.append(" and model.tdpCadre.userAddress.district.districtId > 10 and  model.tdpCadre.userAddress.state.stateId = 1 ");	
			}else if(stateId.longValue()==36l){
				 queryStr.append(" and model.tdpCadre.userAddress.district.districtId < 11 ");
		   }
	 }
	
	  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    queryStr.append(" and model.tdpCadre.userAddress.state.stateId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	     queryStr.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	     queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	        queryStr.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	        queryStr.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	        queryStr.append(" and model.tdpCadre.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	        queryStr.append(" and model.tdpCadre.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	 }
	  queryStr.append(" group by model.event.eventId ");
	 
	  Query query = getSession().createQuery(queryStr.toString());
	 
	if(eventIds != null && eventIds.size() >0){
		query.setParameterList("eventIds", eventIds);	
	}
	if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
		   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	 }
	
	return query.list();
}
public List<Object[]> getEventInviteeCntByEventAndLocationBasedOnUserType(Long userType,Long stateId,List<Long> eventIds,Long userAccessLevelId,List<Long> userAccessLevelValues,String levelType){
	
	  StringBuilder queryStr = new StringBuilder();
	
	  queryStr.append(" select model.event.eventId," +
	  		           " model.event.name,");
	  
	  if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	     queryStr.append("model.tdpCadre.userAddress.district.districtId,");
	     queryStr.append("model.tdpCadre.userAddress.district.districtName,"); 
      }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	   || userType.longValue()==IConstants.MP_USER_TYPE_ID){
	 	 queryStr.append("model.tdpCadre.userAddress.constituency.constituencyId,");
    	 queryStr.append("model.tdpCadre.userAddress.constituency.name,"); 
     }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
	     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
	     queryStr.append(" model.tdpCadre.userAddress.tehsil.tehsilId,");
	     queryStr.append(" model.tdpCadre.userAddress.tehsil.tehsilName,");
	     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
	     queryStr.append(" model.tdpCadre.userAddress.localElectionBody.localElectionBodyId,");
	     queryStr.append(" model.tdpCadre.userAddress.localElectionBody.name,");
	     }
    }
	queryStr.append(" count(distinct model.tdpCadre.tdpCadreId) ");
	
  	queryStr.append(" from EventInvitee model where  model.tdpCadre.isDeleted = 'N' and model.tdpCadre.enrollmentYear = 2014  ");
  	
	if(eventIds != null && eventIds.size() >0){
		queryStr.append(" and model.event.eventId in (:eventIds) " );	
	}
	 if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
				  queryStr.append(" and model.tdpCadre.userAddress.district.districtId > 10 and  model.tdpCadre.userAddress.state.stateId = 1 ");	
				}else if(stateId.longValue()==36l){
					queryStr.append(" and model.tdpCadre.userAddress.district.districtId < 11 ");
				}
	 }
	 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	    queryStr.append(" and model.tdpCadre.userAddress.state.stateId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.tdpCadre.userAddress.district.districtId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	     queryStr.append(" and model.tdpCadre.userAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	     queryStr.append(" and model.tdpCadre.userAddress.constituency.constituencyId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	        queryStr.append(" and model.tdpCadre.userAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	        queryStr.append(" and model.tdpCadre.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	        queryStr.append(" and model.tdpCadre.userAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	        queryStr.append(" and model.tdpCadre.userAddress.ward.constituencyId in (:userAccessLevelValues)"); 
	 }
	  queryStr.append(" group by model.event.eventId"); 
	if(userType != null && userType.longValue()==IConstants.COUNTRY_TYPE_USER_ID || userType.longValue()==IConstants.STATE_TYPE_USER_ID || userType.longValue()==IConstants.GENERAL_SECRETARY_USER_TYPE_ID){
	     queryStr.append(" ,model.tdpCadre.userAddress.district.districtId");
    }else if(userType != null && userType.longValue()==IConstants.SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.ORGANIZING_SECRETARY_USER_TYPE_ID || userType.longValue()==IConstants.DISTRICT_PRESIDENT_USER_TYPE_ID
	   || userType.longValue()==IConstants.MP_USER_TYPE_ID){
	 	 queryStr.append(" ,model.tdpCadre.userAddress.constituency.constituencyId");
   }else if(userType != null && userType.longValue()==IConstants.MLA_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_USER_TYPE_ID || userType.longValue()==IConstants.CONSTITUENCY_INCHARGE_USER_TYPE_ID){
	     if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
	     queryStr.append(" ,model.tdpCadre.userAddress.tehsil.tehsilId");
	     }else if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
	     queryStr.append(" ,model.tdpCadre.userAddress.localElectionBody.localElectionBodyId");
	     }
   }
   queryStr.append(" order by model.event.eventId ");
   Query query = getSession().createQuery(queryStr.toString());
   if(eventIds != null && eventIds.size() >0){
		query.setParameterList("eventIds", eventIds);	
	}
	if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
	  query.setParameterList("userAccessLevelValues", userAccessLevelValues);
	 }
	return query.list();
}
public List<Object[]> getLocationWiseEventInviteedCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds){
	
	StringBuilder queryStr = new StringBuilder();
	
	     queryStr.append("select");
	     
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  UA.state_id as stateId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" UA.district_id as districtId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" UA.parliament_constituency_id as parliamentConstituencyId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  UA.constituency_id as assemblyId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" UA.tehsil_id as tehsilId,");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" UA.local_election_body as localElectionBoydId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" UA.panchayat_id as panchayatId,"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" UA.ward as wardId,"); 
		 }
	  
	  queryStr.append(" COUNT(DISTINCT CONCAT(E.event_id,'-',TC.tdp_cadre_id)) as count ");
	
	  queryStr.append(" FROM event_invitee EI," +
	  		         " event E,tdp_cadre TC," +
	  		         " user_address UA "+
	  		         " WHERE EI.event_id = E.event_id AND "+
		             " EI.tdp_cadre_id = TC.tdp_cadre_id AND " +
					 " TC.address_id = UA.user_address_id AND "+
					 " TC.is_deleted = 'N' AND "+
				 	 " TC.enrollment_year = 2014 ");
		if(eventIds != null && eventIds.size() >0){
			queryStr.append(" AND E.event_id in(:eventIds)" );	
		}
	   if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
					queryStr.append(" and UA.district_id > 10 and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id < 11 ");
				}
		 }
	     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	       queryStr.append("  group by UA.state_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by UA.district_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		     queryStr.append(" group by UA.parliament_constituency_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		     queryStr.append("  group by UA.constituency_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		        queryStr.append(" group by UA.tehsil_id");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		        queryStr.append(" group by UA.local_election_body"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		        queryStr.append(" group by UA.panchayat_id"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		        queryStr.append(" group by UA.ward"); 
		 }
        Session session = getSession();
	      SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		    sqlQuery.addScalar("stateId",Hibernate.LONG); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("districtId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("parliamentConstituencyId",Hibernate.LONG); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			 sqlQuery.addScalar("assemblyId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			 sqlQuery.addScalar("tehsilId",Hibernate.LONG);  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			 sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			 sqlQuery.addScalar("panchayatId",Hibernate.LONG);
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			 sqlQuery.addScalar("wardId",Hibernate.LONG);
		 }
		    sqlQuery.addScalar("count",Hibernate.LONG);
		if(eventIds != null && eventIds.size() >0){
			sqlQuery.setParameterList("eventIds", eventIds);	
		}
	return sqlQuery.list();
}
public List<Object[]> getLocationWiseEventInviteedCntBasedOnUserType(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,List<Long> eventIds,String locationType){
	
	           StringBuilder queryStr = new StringBuilder();
	
	            queryStr.append("select");
	 
		        if(locationType != null && locationType.equalsIgnoreCase("District")){
		         queryStr.append(" d.district_id as districtId,"); //1
		         queryStr.append(" d.district_name as districtName,"); //2
		        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
		         queryStr.append(" c.constituency_id as assemblyId,"); //3
		  	     queryStr.append(" c.name as assemblyName,"); //4
		  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
		         queryStr.append(" t.tehsil_id as tehsilId,");
		         queryStr.append(" t.tehsil_name as tehsilName,");
		        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
		        queryStr.append(" p.panchayat_id as panchayatId,");
		        queryStr.append(" p.panchayat_name as panchayatName,");
		        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
	   	        queryStr.append(" c.constituency_id as wardId,");
	   	        queryStr.append(" c.name as wardName,");
	   	       }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
	   	        queryStr.append(" leb.local_election_body_id as localElectionBoydId,");
	   	        queryStr.append(" leb.name as localElectionBoydName,");
	   	       }
   	        
	           queryStr.append(" COUNT(DISTINCT CONCAT(E.event_id,'-',TC.tdp_cadre_id)) as count ");
	
	           queryStr.append(" FROM event_invitee EI," +
	  		                   " event E,tdp_cadre TC," +
	  		                   " user_address UA");
	  
	  		       if(locationType != null && locationType.equalsIgnoreCase("District")){
			         queryStr.append(",district d"); 
			        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
			         queryStr.append(",constituency c"); 
			  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
			         queryStr.append(",tehsil t");
			        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
			        queryStr.append(",panchayat p");
			        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
		   	        queryStr.append(",constituency c");
		   	        }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
		   	        queryStr.append(",local_election_body leb");
		   	        }
	  		        queryStr.append(" WHERE EI.event_id = E.event_id AND "+
		            " EI.tdp_cadre_id = TC.tdp_cadre_id AND " +
					" TC.address_id = UA.user_address_id AND "+
					" TC.is_deleted = 'N' AND "+
					" TC.enrollment_year = 2014 ");
		
	      if(eventIds != null && eventIds.size() >0){
			queryStr.append(" AND E.event_id in(:eventIds)" );	
		  }
	     
	      if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(" and UA.district_id=d.district_id");
	      }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append("  and UA.constituency_id=c.constituency_id ");
	  	  }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	        	queryStr.append(" and UA.tehsil_id=t.tehsil_id ");  
	      }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
        	   queryStr.append(" and UA.panchayat_id=p.panchayat_id "); 
	      }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
        	   queryStr.append(" and UA.ward=c.constituency_id"); 
   	      }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
        	   queryStr.append(" and UA.local_election_body=leb.local_election_body_id "); 
   	      }
	     
	     
	     if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	 	    queryStr.append(" and UA.state_id in (:userAccessLevelValues)");  
	 	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	 	        queryStr.append(" and UA.district_id in (:userAccessLevelValues)");  
	 	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	 	     queryStr.append(" and UA.parliament_constituency_id in (:userAccessLevelValues)");  
	 	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	 	     queryStr.append(" and UA.constituency_id in (:userAccessLevelValues)");  
	 	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	 	        queryStr.append(" and UA.tehsil_id in (:userAccessLevelValues)");  
	 	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	 	        queryStr.append(" and UA.local_election_body in (:userAccessLevelValues)"); 
	 	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	 	        queryStr.append(" and UA.panchayat_id in (:userAccessLevelValues)"); 
	 	 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	 	        queryStr.append(" and UA.ward in (:userAccessLevelValues)"); 
	 	 }
		 if(stateId != null && stateId.longValue() > 0){
			   if(stateId.longValue()==1l){
					queryStr.append(" and UA.district_id > 10 and UA.state_id = 1 ");
				}else if(stateId.longValue()==36l){
					queryStr.append(" and UA.district_id < 11 ");
				}
		 }
		   if(locationType != null && locationType.equalsIgnoreCase("District")){
	         queryStr.append(" group by d.district_id "); 
	        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         queryStr.append(" group by c.constituency_id "); 
	  	    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         queryStr.append(" group by t.tehsil_id ");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
	        queryStr.append(" group by p.panchayat_id ");
	        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
   	        queryStr.append(" group by c.constituency_id ");
   	       }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
   	        queryStr.append(" group by leb.local_election_body_id");
   	       }
	    
          Session session = getSession();
	      SQLQuery sqlQuery = session.createSQLQuery(queryStr.toString());
	    
	      if(locationType != null && locationType.equalsIgnoreCase("District")){
	    	 sqlQuery.addScalar("districtId",Hibernate.LONG); 
	    	 sqlQuery.addScalar("districtName",Hibernate.STRING); 
	        }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
	         sqlQuery.addScalar("assemblyId",Hibernate.LONG); 
		     sqlQuery.addScalar("assemblyName",Hibernate.STRING); 
		    }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
	         sqlQuery.addScalar("tehsilId",Hibernate.LONG); 
			 sqlQuery.addScalar("tehsilName",Hibernate.STRING); 
		    }else if(locationType != null && locationType.equalsIgnoreCase("Village")){
    	     sqlQuery.addScalar("panchayatId",Hibernate.LONG); 
			 sqlQuery.addScalar("panchayatName",Hibernate.STRING); 
	        }else if(locationType != null && locationType.equalsIgnoreCase("Ward")){
        	 sqlQuery.addScalar("wardId",Hibernate.LONG); 
  			 sqlQuery.addScalar("wardName",Hibernate.STRING); 
  	       } else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
        	  sqlQuery.addScalar("localElectionBoydId",Hibernate.LONG); 
      		  sqlQuery.addScalar("localElectionBoydName",Hibernate.STRING); 
           }
		    sqlQuery.addScalar("count",Hibernate.LONG);
			
	     if(eventIds != null && eventIds.size() >0){
		 	sqlQuery.setParameterList("eventIds", eventIds);	
		 }
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			sqlQuery.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
	return sqlQuery.list();
}

	public List<Object[]> getEventInviteeDetails(List<Long> cadreIds,List<Long> eventIds){
		Query query = getSession().createQuery(" select distinct model.eventId,model.tdpCadreId " +
				" from EventInvitee model " +
				" where model.eventId in (:eventIds) " +
				" and model.tdpCadreId in (:cadreIds)  and model.event.isActive ='true' " +
				" group by model.eventId  ");
		
		query.setParameterList("cadreIds", cadreIds);
		query.setParameterList("eventIds", eventIds);
		
		return query.list();
	}
}
