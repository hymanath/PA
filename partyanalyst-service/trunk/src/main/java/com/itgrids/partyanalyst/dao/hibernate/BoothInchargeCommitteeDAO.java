package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
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

	public int unLockTheBoothInchargeCommittee(Long userId, List<Long> boothCommitteesIdsList){
		Query query = getSession().createSQLQuery(" update booth_incharge_committee set is_confirmed='N' , completed_date = null , updated_by = :userId " +
				" where booth_incharge_committee_id in (:boothCommitteesIdsList)");
		query.setParameter("userId", userId);
		query.setParameterList("boothCommitteesIdsList", boothCommitteesIdsList);
		return query.executeUpdate();
	}
	
	public List<Object[]> getCommitteeFinalizedBoothsListforUnlock(List<Long> assemblyIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("  SELECT  ");
		sb.append("  c.booth_incharge_committee_id as committeeId ,b.part_no  as partNo");
		sb.append("  from  ");
		sb.append("  booth_incharge_committee c ");
		sb.append("  LEFT OUTER JOIN   booth b on c.booth_id = b.booth_id ");
		sb.append("  where  ");
		sb.append("  c.is_confirmed='Y' and  ");
		sb.append("  c.start_date is not null and  ");
		sb.append("  c.completed_date is not null and  ");
		sb.append("  b.constituency_id in (:assemblyIdsList) ");		
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("committeeId", Hibernate.LONG)
				.addScalar("partNo", Hibernate.STRING);
		query.setParameterList("assemblyIdsList", assemblyIdsList);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseBoothCommitteeDetails(Long levelId,List<Long> levelValues){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT" +
					" d.district_name as disrictName,pa.name as parlName,a.name as assemName,t.tehsil_name as tehsilName,CONCAT(l.name,' ',et.election_type) as lebName," +
					" p.panchayat_name as pancName,c.booth_id as incharge_booth_id,b1.part_no as incharge_booth_no,bpv.booth_id as own_booth_id,b.part_no as own_booth_no," +
					" bi.tdp_cadre_id as cadreId,tc.image as image,tc.membership_id as membershipId,tc.first_name as firstName,tc.mobile_no as mobileNo," +
					" r.booth_incharge_role_id as roleId,r.role_name as roleName" +
					" from" +
					" booth_incharge_committee c,booth_incharge_role_condition_mapping m,booth_incharge_role_condition rc," +
					" booth_incharge_role r,booth_incharge bi,tdp_cadre tc,booth b,booth_publication_voter bpv,user_address ua" +
					" LEFT JOIN district d on ua.district_id = d.district_id LEFT JOIN constituency a on ua.constituency_id = a.constituency_id" +
					" LEFT JOIN constituency pa on ua.parliament_constituency_id = pa.constituency_id LEFT JOIN tehsil t on ua.tehsil_id = t.tehsil_id" +
					" LEFT JOIN panchayat p on ua.panchayat_id = p.panchayat_id LEFT JOIN booth b1 on ua.booth_id = b1.booth_id" +
					" LEFT JOIN local_election_body l on ua.local_election_body = l.local_election_body_id LEFT JOIN election_type et on l.election_type_id = et.election_type_id" +
					" where" +
					" rc.booth_incharge_role_id = r.booth_incharge_role_id and c.booth_id = b1.booth_id and c.address_id = ua.user_address_id and b.booth_id = bpv.booth_id" +
					" and bpv.voter_id = tc.voter_id and b.publication_date_id = 24 and b1.publication_date_id = 24 and bi.tdp_cadre_id = tc.tdp_cadre_id" +
					" and tc.is_deleted='N' and tc.enrollment_year='2014' and c.booth_incharge_committee_id = m.booth_incharge_committee_id" +
					" and m.booth_incharge_role_condition_id = rc.booth_incharge_role_condition_id and m.booth_incharge_role_condition_mapping_id = bi.booth_incharge_role_condition_mapping_id" +
					" and c.is_deleted ='N' and bi.is_active='Y' and bi.is_deleted='N'");
		if(levelId != null && levelId.longValue() == 2L)
			sb.append(" and ua.state_id in (:levelValues)");
		else if(levelId != null && levelId.longValue() == 3L)
			sb.append(" and ua.district_id in (:levelValues)");
		else if(levelId != null && levelId.longValue() == 4L)
			sb.append(" and ua.constituency_id in (:levelValues)");
		else if(levelId != null && levelId.longValue() == 5L)
			sb.append(" and ua.tehsil_id in (:levelValues)");
		else if(levelId != null && levelId.longValue() == 7L)
			sb.append(" and ua.local_election_body in (:levelValues)");
		else if(levelId != null && levelId.longValue() == 6L)
			sb.append(" and ua.panchayat_id in (:levelValues)");
		else if(levelId != null && levelId.longValue() == 8L)
			sb.append(" and ua.ward in (:levelValues)");
		
		Query query = getSession().createSQLQuery(sb.toString()).addScalar("disrictName", Hibernate.STRING).addScalar("parlName", Hibernate.STRING)
				.addScalar("assemName", Hibernate.STRING).addScalar("tehsilName", Hibernate.STRING).addScalar("lebName", Hibernate.STRING).addScalar("pancName", Hibernate.STRING)
				.addScalar("incharge_booth_id", Hibernate.LONG).addScalar("incharge_booth_no", Hibernate.STRING).addScalar("own_booth_id", Hibernate.LONG)
				.addScalar("own_booth_no", Hibernate.STRING).addScalar("cadreId", Hibernate.LONG).addScalar("image", Hibernate.STRING).addScalar("membershipId", Hibernate.STRING)
				.addScalar("firstName", Hibernate.STRING).addScalar("mobileNo", Hibernate.STRING).addScalar("roleId", Hibernate.LONG).addScalar("roleName", Hibernate.STRING);
		if(levelId != null && levelId.longValue() > 0L && levelValues != null && !levelValues.isEmpty())
			query.setParameterList("levelValues", levelValues);
		
		return query.list();
	}
}
