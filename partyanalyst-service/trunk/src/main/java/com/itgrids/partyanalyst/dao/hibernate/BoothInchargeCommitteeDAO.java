package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeCommitteeDAO;
import com.itgrids.partyanalyst.model.BoothInchargeCommittee;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothInchargeCommitteeDAO extends GenericDaoHibernate<BoothInchargeCommittee,Long> implements IBoothInchargeCommitteeDAO {

	public BoothInchargeCommitteeDAO() {
		super(BoothInchargeCommittee.class);

	}
	
	public Long getElectionBoothDetails(Date startDate, Date endDate,Long locationTypeId, Long locationValue, String type,
			List<Long> committeeEnrollmentYearsIdsLst) {

		StringBuilder queryStr = new StringBuilder();

		queryStr.append(" select  count(distinct model.boothInchargeCommitteeId) "
				+ " from BoothInchargeCommittee model where "
				+ " model.isDeleted = 'N' and model.booth.publicationDate.publicationDateId = :publicationDate");

		if (locationTypeId != null && locationTypeId.longValue() == 1l) {
			queryStr.append(" and model.address.state.stateId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 2l) {
			queryStr.append(" and model.address.district.districtId =:locationValue ");
		}
		else if (locationTypeId != null && locationTypeId.longValue() == 4l) {
			queryStr.append(" and model.address.constituency.constituencyId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 5l) {
			queryStr.append(" and model.address.tehsil.tehsilId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 6l) { // town/division
			queryStr.append(" and model.address.localBody.localElectionBodyId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 7l) {
			queryStr.append(" and model.address.panchayat.panchayatId =:locationValue ");
		} else if (locationTypeId != null && locationTypeId.longValue() == 8l) {
			queryStr.append(" and model.address.ward.wardId =:locationValue ");
		}

		if (type != null && type.trim().length() > 0L) {
			if (type.trim().equalsIgnoreCase("started")) {
				queryStr.append(" and model.isConfirmed ='N' and model.startDate is not null and model.completedDate is null");
				if (startDate != null && endDate != null) {
					queryStr.append(" and date(model.startDate) between :startDate and :endDate ");
				}
			} else if (type.trim().equalsIgnoreCase("completed")) {
				queryStr.append(" and model.isConfirmed ='Y' and model.startDate is not null and model.completedDate is not null");
				if (startDate != null && endDate != null) {
					queryStr.append(" and date(model.completedDate) between :startDate and :endDate ");
				}
			}
		}

		if (committeeEnrollmentYearsIdsLst != null && committeeEnrollmentYearsIdsLst.size() > 0) {
			queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in(:committeeEnrollmentYearsIdsLst) ");
		}
		Query qry = getSession().createQuery(queryStr.toString());

		qry.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		if (locationValue != null && locationValue.longValue() > 0) {
			qry.setParameter("locationValue", locationValue);
		}
		if (type != null && type.trim().length() > 0L && !type.trim().equalsIgnoreCase("total")) {
			if (startDate != null && endDate != null) {
				qry.setDate("startDate", startDate);
				qry.setDate("endDate", endDate);
			}
		}

		if (committeeEnrollmentYearsIdsLst != null 	&& committeeEnrollmentYearsIdsLst.size() > 0) {
			qry.setParameterList("committeeEnrollmentYearsIdsLst",committeeEnrollmentYearsIdsLst);
		}

		return (Long) qry.uniqueResult();

	}

	@Override
	public List<Object[]> getBoothInchargeCountDetails(Long locationId,Long locationValue, List<Long> boothCommEnrollYrIds,
			Date startDate, Date endDate) {
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.boothId,model.tdpCadre.tdpCadreId,model.tdpCadre.gender  from BoothIncharge model " +
				" where  model.isActive ='Y' and model.isDeleted='N' " +
				" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.publicationDate.publicationDateId = :publicationDate "); 
	
		
		if(locationId != null && locationId.longValue()==1l){
			   queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.state.stateId =:locationValue");  
			 }else if(locationId != null && locationId.longValue()==2l){
			   queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.district.districtId =:locationValue");  
			 }/*else if(locationId != null && locationId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId =:locationValue ");  
			 }*/else if(locationId != null && locationId.longValue()==4l){
		        queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId =:locationValue ");  
			 }else if(locationId != null && locationId.longValue()==5l){
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId =:locationValue");  
			 }else if(locationId != null && locationId.longValue()==6l){ //  town/division
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId =:locationValue"); 
			 }else if(locationId != null && locationId.longValue()==7l){ 
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId =:locationValue"); 
			 }else if(locationId != null && locationId.longValue()==8l){ 
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.ward.constituencyId =:locationValue"); 
			 }
		
		if(boothCommEnrollYrIds !=null && boothCommEnrollYrIds.size() > 0){
			 queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in (:boothCommEnrollYrIds) " );
		}
		
		if(startDate != null && endDate != null){
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		}
		Query qry = getSession().createQuery(queryStr.toString());
		
		if(locationValue != null && locationValue.longValue() > 0l){
			qry.setParameter("locationValue", locationValue);
		 }
		qry.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		if(boothCommEnrollYrIds !=null && boothCommEnrollYrIds.size() > 0){
			qry.setParameterList("boothCommEnrollYrIds", boothCommEnrollYrIds);
		}
		
		if(startDate != null && endDate != null){
			qry.setDate("startDate", startDate);
			qry.setDate("endDate", endDate);
		}
		return qry.list();
	}

}
