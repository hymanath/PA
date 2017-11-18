package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreCasteInfo;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreCasteInfoDAO extends GenericDaoHibernate<TdpCadreCasteInfo,Long > implements ITdpCadreCasteInfoDAO{

	public TdpCadreCasteInfoDAO() {
		super(TdpCadreCasteInfo.class);
	}

	public int deleteTdpCadreCasteInfoTableBeforeUpdate( Long enrollmentYearId)
	{
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_caste_info where tdp_cadre_enrollment_id=:enrollmentYearId ");
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();		
		return c;
	}
	
	public Integer updateTdpCadreCasteInfoTableByScheduler(String locationType, Long enrollmentYearId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("  insert into tdp_cadre_caste_info (location_type,location_id,caste_state_id,count,caste_category_id,tdp_cadre_enrollment_id)  ");
		
		if(locationType != null && !locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" select '"+locationType+"', ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" select 'LocalBody', ");
		}
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" UA.constituency_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" UA.district_id, ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
			queryStr.append(" UA.panchayat_id,");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		{
			queryStr.append(" UA.ward,");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" UA.tehsil_id , ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" UA.local_election_body, ");
		}
		queryStr.append(" CS.caste_state_id,count(CS.caste_state_id),CC.caste_category_id ,"+enrollmentYearId+"  from  tdp_cadre_enrollment_year TC1,tdp_cadre TC, user_address UA, caste_state CS, caste_category_group CCG, caste_category CC " +
				" where TC.address_id = UA.user_address_id and TC.caste_state_id = CS.caste_state_id and CS.caste_category_group_id = CCG.caste_category_group_id and " +
				" CCG.caste_category_id = CC.caste_category_id " +
				" and TC.enrollment_year = 2014 and TC.is_deleted = 'N' and TC1.tdp_cadre_id = TC.tdp_cadre_id and TC1.is_deleted='N' ");
		
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		{
			queryStr.append(" and UA.constituency_id  is not null group by CS.caste_state_id,UA.constituency_id order by UA.constituency_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
		{
			queryStr.append(" and UA.district_id  is not null group by CS.caste_state_id,UA.district_id order by UA.district_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		{
			queryStr.append(" and UA.panchayat_id  is not null group by CS.caste_state_id,UA.panchayat_id order by UA.panchayat_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		{
			queryStr.append(" and UA.ward  is not null group by CS.caste_state_id,UA.ward order by UA.ward,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
		{
			queryStr.append(" and UA.tehsil_id  is not null and UA.local_election_body  is null group by CS.caste_state_id,UA.tehsil_id order by UA.tehsil_id,CC.caste_category_id   ");
		}
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
		{
			queryStr.append(" and UA.local_election_body  is not null group by CS.caste_state_id,UA.local_election_body order by UA.local_election_body,CC.caste_category_id   ");
		}
		queryStr.append(" and TC1.enrollment_year_id=:enrollmentYearId ");
		Query query = getSession().createSQLQuery(queryStr.toString());
		query.setParameter("enrollmentYearId", enrollmentYearId);
		int c = query.executeUpdate();
		return c;
	}
	
	public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId, Long enrollmentYearId)
	{
		StringBuilder queryStr = new StringBuilder();
		boolean isStateWise = false;
		StringBuilder str  = new StringBuilder();
		if(locationType != null)
		{
			str.append(" select distinct model.locationId,model.count ");
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				str.append(" , model2.name from TdpCadreCasteInfo model, Constituency model2 where model2.constituencyId = model.locationId   and model2.district.districtId in (:locationIdsList) and model.locationType like '%Constituency%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				str.append(", model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId and  model.locationId in (:locationIdsList) and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				str.append(" ,model2.tehsilName from TdpCadreCasteInfo model,Tehsil model2,Booth B where model2.tehsilId = model.locationId and model2.tehsilId = B.tehsil.tehsilId and model.locationType like '%Tehsil%' ");
				str.append(" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and B.constituency.constituencyId in (:locationIdsList)  ");
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.tehsilName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				str.append(" ,model2.panchayatName from TdpCadreCasteInfo model,Panchayat model2,Booth B where model2.panchayatId = model.locationId and model2.panchayatId = B.panchayat.panchayatId and model.locationType like '%Panchayat%' ");
				str.append(" and B.publicationDate.publicationDateId = 11 and B.tehsil.tehsilId in (:locationIdsList)  ");
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.panchayatName ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			{
				str.append(" ,model2.name from TdpCadreCasteInfo model,LocalElectionBody model2,Booth B where model2.localElectionBodyId = model.locationId and model2.localElectionBodyId = B.localBody.localElectionBodyId and model.locationType like '%LocalBody%' ");
				str.append(" and B.publicationDate.publicationDateId = 11 and B.localBody.localElectionBodyId in (:locationIdsList)  ");
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.name ");
			}
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			{
				str.append(" ,model2.name from TdpCadreCasteInfo model,Constituency model2 where model2.constituencyId = model.locationId and model.locationType like '%Ward%' ");
				str.append(" and model.locationId in (:locationIdsList)  ");	
				str.append(" and model.casteStateId =:casteStateId ");
				str.append(" order by model2.name ");
			}
			else if(stateId != null && stateId.longValue() == 0L) //AP & TS
			{
				isStateWise = true;
				str.append(" , model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId  and model.locationId between 1 and 23 and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(stateId != null && stateId.longValue() == 1L) //AP
			{
				isStateWise = true;
				str.append(" , model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId  and model.locationId between 11 and 23 and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			else if(stateId != null && stateId.longValue() == 2L) //TS
			{
				isStateWise = true;
				str.append(" , model2.districtName from TdpCadreCasteInfo model, District model2 where model.locationId = model2.districtId  and model.locationId between 1 and 10 and model.locationType like '%District%' ");
				str.append(" and model.casteStateId =:casteStateId ");
			}
			
			
			queryStr.append(str.toString());
			
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("casteStateId", casteStateId);
			
			if(!isStateWise && (locationIdsList != null && locationIdsList.size()>0))
			{
				query.setParameterList("locationIdsList", locationIdsList);
			}
			
			return query.list();
		}
		else
		{
			return null;
		}
	}
	
	public List<Object[]> getVoterCadreCasteDetailsByAgeRange(Long locationTypeId, List<Long> locationValue, Long casteGroupId,Long casteId) {

		StringBuilder sb = new StringBuilder();
		// 0-agerangeId,1-ageRange,-gender,4-cadreCount
		sb.append("select voter_age_range_id as voterAgeRangeid, gender as gender,count as count from tdp_cadre_caste_info where ");
		if (locationTypeId.longValue() > 0l && locationTypeId != null) {
			if (locationTypeId == 3l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 4l || locationTypeId == 10l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 5l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 6l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 7l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 2l) {
				sb.append("location_id in (:locationValue)");
			}
		}
		if (casteGroupId != null && casteGroupId.longValue() > 0l) {
			sb.append(" and caste_category_id=:casteCategoryId");
		}
		if (casteId != null && casteId.longValue() > 0l) {
			sb.append(" and caste_state_id=:casteId");
		}
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("voterAgeRangeid", Hibernate.LONG)
				.addScalar("gender", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if (casteId != null && casteId.longValue() > 0l) {
			query.setParameter("casteId", casteId);
		}

		if (locationValue != null && locationValue.size() > 0) {
			query.setParameterList("locationValue", locationValue);
		}
		if (casteGroupId != null && casteGroupId.longValue() > 0l) {
			query.setParameter("casteCategoryId", casteId);
		}
		return query.list();
	}
	public List<Object[]> getAgeGenerAndCasteGroupWiseCadresCount(Long locationTypeId,List<Long> locationValue,Long enrollmentYearId){
		StringBuilder  queryStr = new StringBuilder();
		
		
		//0-ageRangeId,1- agerRange, 2- gender 3-castecatId, 4-copunt
		
		StringBuilder sb = new StringBuilder();
		sb.append("select voter_age_range_id as voterAgeRangeid, gender as gender,caste_category_id as caste, sum(count) as count from tdp_cadre_caste_info where " +
				" location_type_id = :locationTypeId   and voter_age_range_id is not null  ");
		if (locationTypeId.longValue() > 0l && locationTypeId != null) {
			if (locationTypeId == 3l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 4l || locationTypeId == 10l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 5l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 6l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 7l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 2l) {
				sb.append(" and location_id in (:locationValue)");
			}else if (locationTypeId == 8l) {
				sb.append(" and location_id in (:locationValue)");
			}

		}
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			sb.append(" and tdp_cadre_enrollment_id=:enrollmentYearId ");
		}
		sb.append(" group by  voter_age_range_id, caste_category_id, gender ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("voterAgeRangeid", Hibernate.LONG)
				.addScalar("gender", Hibernate.STRING)
				.addScalar("caste",Hibernate.LONG)
				.addScalar("count", Hibernate.LONG);
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			query.setParameter("enrollmentYearId", enrollmentYearId);
		}
		if(locationTypeId != null && locationTypeId.longValue()>0L)
			query.setParameter("locationTypeId", locationTypeId);
		
		if (locationValue != null && locationValue.size() > 0) {
			query.setParameterList("locationValue", locationValue);
		}
		return query.list();
	}

	@Override
	public List<Object[]> getCasteNGenderWiseCadreCounts(Long locationTypeId,List<Long> locationValue, Long enrollmentYearId, Long casteGroupId) {
		StringBuilder sb = new StringBuilder();
		// o-castegrpId,1-castgrpname,2-casteId,4-castName, 5-gender,6-count
		// o-castegrpId,1-casteId,3-gender,4-count

		sb.append("select tcf.caste_category_id as casteCategoryId, cc.category_name as casteCategoryName,tcf.caste_state_id as casteId,c.caste_name as castname," +
				" tcf.gender as gender, tcf.count as count from tdp_cadre_caste_info  tcf join caste c on c.caste_id=caste_state_id join " +
				" caste_category cc on cc.caste_category_id= tcf.caste_category_id where ");
		
		if (locationTypeId.longValue() > 0l && locationTypeId != null) {
			if (locationTypeId == 3l) {
				sb.append("location_id in(:locationValue)");
			} else if (locationTypeId == 4l || locationTypeId == 10l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 5l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 6l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 7l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 2l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 8l) {
				sb.append("location_id in (:locationValue)");
			}
		}
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			sb.append(" and tdp_cadre_enrollment_id=:enrollmentYearId");
		}
		if(casteGroupId!=null && casteGroupId.longValue() > 0l){
			sb.append(" and tcf.caste_category_id =:casteGroupId");
		}
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("casteCategoryId", Hibernate.LONG)
				.addScalar("casteCategoryName",Hibernate.STRING)
				.addScalar("casteId",Hibernate.LONG)
				.addScalar("castname",Hibernate.STRING)
				.addScalar("gender", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			query.setParameter("enrollmentYearId", enrollmentYearId);
		}

		if (locationValue != null && locationValue.size() > 0l) {
			query.setParameterList("locationValue", locationValue);
		}
		if(casteGroupId!=null && casteGroupId.longValue()> 0l){
			query.setParameter("casteGroupId", casteGroupId);
		}
		return query.list();
	}

	@Override
	public List<Object[]> getCasteGroupWiseCadreCounts(Long locationTypeId,List<Long> locationValue, Long enrollmentYearId) {
		
		StringBuilder sb = new StringBuilder();

		sb.append("select caste_category_id as casteCategoryId,  sum(count) as totalcount from tdp_cadre_caste_info where location_type_id=:locationTypeId and ");
		
		if (locationTypeId.longValue() > 0l && locationTypeId != null) {
			if (locationTypeId == 3l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 4l || locationTypeId == 10l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 5l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 6l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 7l) {
				sb.append("location_id in (:locationValue)");
			} else if (locationTypeId == 2l) {
				sb.append("location_id in (:locationValue)");
			}
		}
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			sb.append(" and tdp_cadre_enrollment_id=:enrollmentYearId");
		}
		sb.append(" group by caste_category_id");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("casteCategoryId", Hibernate.LONG)
				.addScalar("totalcount", Hibernate.LONG);
		
		query.setParameter("locationTypeId", locationTypeId);
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			query.setParameter("enrollmentYearId", enrollmentYearId);
		}

		if (locationValue != null && locationValue.size() > 0l) {
			query.setParameterList("locationValue", locationValue);
		}
		return query.list();
	}

	@Override
	public List<Object[]> getCadresCasteNAgeGroupWiseCounts(Long locationTypeId,List<Long> locationValue, Long casteGroupId,	Long casteId, Long enrollmentYearId) {


		StringBuilder sb = new StringBuilder();
		sb.append("select tcf.voter_age_range_id as voterAgeRangeid,var.age_range as ageRangename, tcf.gender as gender,tcf.count as count from tdp_cadre_caste_info tcf " +
				" join voter_age_range var  on var.voter_age_range_id = tcf.voter_age_range_id  where  " +
				" location_type_id = :locationTypeId   ");
		if (locationTypeId.longValue() > 0l && locationTypeId != null) {
			if (locationTypeId == 3l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 4l || locationTypeId == 10l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 5l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 6l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 7l) {
				sb.append(" and location_id in (:locationValue)");
			} else if (locationTypeId == 2l) {
				sb.append(" and location_id in (:locationValue)");
			}else if (locationTypeId == 8l) {
				sb.append(" and location_id in (:locationValue)");
			}

		}

		if(casteId != null && casteId.longValue()>0l){
			sb.append(" and tcf.caste_state_id=:casteId");
		}
		if(casteGroupId != null && casteGroupId.longValue()>0l){
			sb.append(" and tcf.caste_category_id=:casteGroupId");
		}
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			sb.append(" and tcf.tdp_cadre_enrollment_id=:enrollmentYearId");
		}
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("voterAgeRangeid", Hibernate.LONG)
				.addScalar("ageRangename",Hibernate.STRING)
				.addScalar("gender", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);
		
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0l) {
			query.setParameter("enrollmentYearId", enrollmentYearId);
		}

		if (locationValue != null && locationValue.size() > 0) {
			query.setParameterList("locationValue", locationValue);
		}
		if(casteId != null && casteId.longValue()>0l){
			query.setParameter("casteId",casteId);
		}
		if(casteGroupId != null && casteGroupId.longValue()>0l){
			query.setParameter("casteGroupId",casteGroupId);
		}
		if(locationTypeId != null && locationTypeId.longValue()>0l){
			query.setParameter("locationTypeId",locationTypeId);
		}
		return query.list();
	}
	
	public List<Object[]> getGenderAndAgeGroupWiseCadreCount(Long locationTypeId,List<Long> locationValue,Long enrollmentYearId ){
		StringBuilder sb = new StringBuilder();
		sb.append( "select model.voterAgeRangeId,model.gender, sum(model.count) " +
				" from TdpCadreCasteInfo model where model.tdpCadreEnrollmentId = :enrollmentYearId " );
		if (locationTypeId.longValue() > 0l && locationTypeId != null) {
			if (locationTypeId == 3l) {
				//sb.append(" and model.locationId in (:locationValue) and model.locationType ='District' ");
				sb.append(" and model.locationId in (:locationValue)  and model.locationType ='Constituency'  ");
			} else if (locationTypeId == 4l || locationTypeId == 10l) {
				sb.append(" and model.locationId in (:locationValue)  and model.locationType ='Constituency'  ");
			} else if (locationTypeId == 5l) {
				sb.append(" and model.locationId in (:locationValue)  and model.locationType ='Thesil'  ");
			} else if (locationTypeId == 6l) {
				sb.append(" and model.locationId in (:locationValue)  and model.locationType ='Panchayat'  ");
			} else if (locationTypeId == 7l) {
				sb.append(" and model.locationId in (:locationValue)  and model.locationType ='Localbody'  ");
			} else if (locationTypeId == 2l) {
				sb.append(" and model.locationId in (:locationValue)  and model.locationType ='State'  ");
			}else if (locationTypeId == 8l) {
				sb.append(" and model.locationId in (:locationValue)  and model.locationType ='Ward'  ");
			} 

		}
		sb.append(" group by model.gender,model.voterAgeRangeId " +
				" order by model.voterAgeRangeId ");
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameter("enrollmentYearId", enrollmentYearId);
		
		if(locationValue!= null && locationValue.size()>0l){
			query.setParameterList("locationValue", locationValue);
		}
		
		return query.list();
	}

	@Override
	public List<Object[]> getCasteWiseCadreCounts(Long locationTypeId,List<Long> locationValue, List<Long> enrollmentYearId) {
		StringBuilder sb = new StringBuilder();
		StringBuilder sm= new StringBuilder();
		StringBuilder se= new StringBuilder();
		// o-casteCategoryId,1-casteCategoryName,2-caste_state_id,4-castName, 5-count,6-yearId

		sb.append("select tcf.caste_category_id AS casteCategoryId, cc.category_name AS casteCategoryName, tcf.caste_state_id AS casteId,c.caste_name AS castname, " +
				" SUM(tcf.count) AS count,tcf.tdp_cadre_enrollment_id  as yearId ");
		sm.append(" from tdp_cadre_caste_info tcf, caste c,caste_category cc ");
		se.append(" WHERE c.caste_id = caste_state_id AND cc.caste_category_id = tcf.caste_category_id");
		if (enrollmentYearId != null && enrollmentYearId.size() > 0l) {
			se.append(" and tdp_cadre_enrollment_id in(:enrollmentYearId)");
		}
		if (locationTypeId.longValue() > 0l && locationTypeId != null) {
			
			 if (locationTypeId == 2l) {
				sb.append(" ,d.district_id as locationId, d.district_name as loactionName ");
				sm.append(",district d ");
				se.append(" AND tcf.location_id = d.state_id AND (d.district_id BETWEEN 11 AND 23) and location_id in (:locationValue) and tcf.location_type_id="+locationTypeId);
				se.append(" GROUP BY tcf.caste_state_id , d.district_id,tcf.tdp_cadre_enrollment_id order by count desc");
			} else if (locationTypeId == 3l) {
				sb.append(" ,co.constituency_id as locationId, co.name as loactionName ");
				sm.append(",constituency co ");
				se.append(" AND tcf.location_id = co.constituency_id and " +
						"tcf.location_id in (:select distinct constituency_id from constituency where district_id in(:locationValue)" +
						" and deform_date is null and election_scope_id=2) and tcf.location_type_id=4");
				se.append(" GROUP BY tcf.caste_state_id , co.constituency_id order by count desc");
				
			} else if (locationTypeId == 4l || locationTypeId == 10l) {
				se.append("location_id in (:locationValue)");
			} else if (locationTypeId == 5l) {
				se.append("location_id in (:locationValue)");
			} else if (locationTypeId == 6l) {
				se.append("location_id in (:locationValue)");
			} else if (locationTypeId == 7l) {
				se.append("location_id in (:locationValue)");
			} else if (locationTypeId == 8l) {
				se.append("location_id in (:locationValue)");
			}
		}
		
		sb.append(sm.toString()).append(se.toString());
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("casteCategoryId", Hibernate.LONG)
				.addScalar("casteCategoryName",Hibernate.STRING)
				.addScalar("casteId",Hibernate.LONG)
				.addScalar("castname",Hibernate.STRING)
				.addScalar("count", Hibernate.LONG)
				.addScalar("yearId",Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("loactionName", Hibernate.STRING);
		if (enrollmentYearId != null && enrollmentYearId.size() > 0l) {
			query.setParameterList("enrollmentYearId", enrollmentYearId);
		}

		if (locationValue != null && locationValue.size() > 0l) {
			query.setParameterList("locationValue", locationValue);
		}
		return query.list();
	}
	
	public List<Object[]> enrollmentYearsBasedOnenrollmentYearIds(List<Long> enrollmentYearIds){
		//0-id,1-year2-desc
		Query query =  getSession().createSQLQuery( "select enrollment_year_id as enrollmentYearId,year as year, description as description from enrollment_year where  enrollment_year_id in(:enrollmentYearIds)" )
				.addScalar("enrollmentYearId",Hibernate.LONG)
				.addScalar("year", Hibernate.LONG)
				.addScalar("description", Hibernate.STRING);
		
		if (enrollmentYearIds != null && enrollmentYearIds.size() > 0l) {
			query.setParameterList("enrollmentYearIds", enrollmentYearIds);
		}

		
		return query.list();
		
	}
	public List<Object[]> getCategoryWiseGenderCount(Long locationScopeId,List<Long> locationValuesList,List<Long> enrollmentYearIdsList){
		//0 tdpCadreEnrollmentId, 1 categoryName, 2 casteCategoryId
		//3 gender,4 count, 5 description
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("tcci.tdp_cadre_enrollment_id as tdpCadreEnrollmentId,cc.category_name as categoryName,");
		sb.append("cc.caste_category_id as casteCategoryId,tcci.gender as gender,sum(count) as count,ey.description as description ");
		sb.append("from ");
		sb.append("tdp_cadre_caste_info tcci, tdp_cadre_enrollment_year tcey,");
		sb.append("enrollment_year ey,caste_category cc, caste_category_group ccg  ");
		
		if(locationScopeId != null && locationScopeId.longValue() == 2L)
			sb.append(" , state s ");
		else if(locationScopeId != null && (locationScopeId.longValue() == 3L)) 
			sb.append(" , district d ");
		else if(locationScopeId != null && locationScopeId.longValue() == 4L || locationScopeId.longValue() == 8L || locationScopeId.longValue() == 10L )
			sb.append(" , constituency c ");
		else if(locationScopeId != null && locationScopeId.longValue() == 5L)
			sb.append(" , tehsil t  ");
		else if(locationScopeId != null && locationScopeId.longValue() == 6L)
			sb.append(" , panchayat p ");
		else if(locationScopeId != null && locationScopeId.longValue() == 7L)
			sb.append(" , local_election_body leb ");
		
		sb.append("where ");
		sb.append("tcci.caste_category_id = cc.caste_category_id and ");
		sb.append("tcci.tdp_cadre_enrollment_id = tcey.tdp_cadre_enrollment_year_id and ");
		sb.append("tcey.enrollment_year_id=ey.enrollment_year_id and ");
		sb.append("cc.caste_category_id = ccg.caste_category_id  and ");
		sb.append(" tcci.caste_category_id = ccg.caste_category_id  and tcci.tdp_cadre_enrollment_id in(:enrollmentYearIdsList)  ");
		
		if(locationScopeId != null && locationScopeId.longValue() == 2L)
			sb.append("and tcci.location_Id = s.state_id and s.state_id in (:locationValuesList) ");
		else if(locationScopeId != null && (locationScopeId.longValue() == 3L)) 
			sb.append("and tcci.location_Id = d.district_id and d.district_id in (:locationValuesList) ");
		else if(locationScopeId != null && locationScopeId.longValue() == 4L || locationScopeId.longValue() == 8L || locationScopeId.longValue() == 10L )
			sb.append("and  tcci.location_Id = c.constituency_id and  c.constituency_id in (:locationValuesList) ");
		else if(locationScopeId != null && locationScopeId.longValue() == 5L)
			sb.append("and  tcci.location_Id = t.tehsil_id and  t.tehsil_id in (:locationValuesList) ");
		else if(locationScopeId != null && locationScopeId.longValue() == 6L)
			sb.append("and  tcci.location_Id = p.panchayat_id and  p.panchayat_id in (:locationValuesList) ");
		else if(locationScopeId != null && locationScopeId.longValue() == 7L)
			sb.append("and  tcci.location_Id = leb.local_election_body_id and  leb.local_election_body_id in (:locationValuesList) ");
		
		sb.append("GROUP BY ");
		sb.append("tcci.tdp_cadre_enrollment_id,cc.caste_category_id,tcci.gender");
		  

		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("tdpCadreEnrollmentId", Hibernate.LONG)
				.addScalar("categoryName", Hibernate.STRING)
				.addScalar("casteCategoryId", Hibernate.LONG)
				.addScalar("gender", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG)
		        .addScalar("description", Hibernate.STRING);
		 		
				query.setParameterList("locationValuesList", locationValuesList);
				query.setParameterList("enrollmentYearIdsList", enrollmentYearIdsList);
				return query.list();	
	}
	
	public List<Object[]> getLocationWiseCadreCounts(Long locationTypeId,List<Long> locationValue,Long casteId,Long enrollmentYearId,String type){
		
		StringBuilder sb = new StringBuilder();

		sb.append("SELECT ");
		if (locationTypeId != null && locationTypeId.longValue() == 2L) {
			sb.append("d.district_id as locationId, d.district_name as locationName ");
		} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L)) {
			sb.append("c.constituency_id as locationId, c.name as locationName ");
		} 
		else if (locationTypeId != null && locationTypeId.longValue() == 4L) {
			sb.append("t.tehsil_id as locationId, t.tehsil_name as locationName ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 5L) {
			sb.append("p.panchayat_id as locationId, p.panchayat_name as locationName ");
		}

		sb.append(",model.gender as gender,sum(model.count) as count from tdp_cadre_caste_info model ");
		sb.append(",caste_state cs ");
		if (locationTypeId != null && locationTypeId.longValue() == 2L) {
			sb.append(" , district d ");
		} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L)) {
			sb.append(" , constituency c ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 4L) {
			sb.append(" , tehsil t ,booth b ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 5L ) {
			sb.append(" , panchayat p,booth b  ");
		}

		sb.append("where ");
		sb.append(" model.caste_state_id=cs.caste_state_id and ");
		if (locationTypeId != null && locationTypeId.longValue() == 2L) {
			sb.append(" model.location_type_id =3 and model.location_Id = d.district_id  ");
			sb.append(" and (d.district_id between 11 and 23) ");
		} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L )) {
			sb.append(" model.location_type_id =4 and model.location_Id = c.constituency_id ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 4L && type !=null && type.equalsIgnoreCase("rural")) {
			sb.append(" b.tehsil_id = t.tehsil_id and model.location_type_id =5 and model.location_Id = t.tehsil_id and b.local_election_body_id is null ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 4L && type !=null && type.equalsIgnoreCase("urban")) {
			sb.append(" b.tehsil_id = t.tehsil_id and model.location_type_id =7 and model.location_Id = t.tehsil_id and b.local_election_body_id is not null ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 5L ) {
			sb.append("  b.panchayat_id = p.panchayat_id and model.location_type_id =6 and model.location_Id = p.panchayat_id ");
		}

		sb.append(" and model.tdp_cadre_enrollment_id =:enrollmentYearId and cs.caste_id =:casteId ");

		if (locationTypeId !=null && locationTypeId.longValue() == 10l && locationValue != null && locationValue.size() >0) {
			sb.append(" and c.constituency_id in (:locationValue) ");
		}else if (locationTypeId != null && (locationTypeId.longValue() == 3L)) {
			sb.append(" and c.district_id in (:locationValue) ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 4L) {
			sb.append(" and b.constituency_id in (:locationValue) ");
		}else if (locationTypeId != null && locationTypeId.longValue() == 5L) {
			sb.append(" and model.location_Id in (:locationValue)  ");
		}
		
		if (locationTypeId != null && locationTypeId.longValue() == 2L) {
			sb.append(" GROUP BY d.district_id ");
		} else if (locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L)) {
			sb.append(" GROUP BY c.constituency_id ");
		} else if (locationTypeId != null &&  locationTypeId.longValue() == 4L) {
			sb.append(" GROUP BY t.tehsil_id ");
		}
		else if (locationTypeId != null && locationTypeId.longValue() == 5L) {
			sb.append(" GROUP BY p.panchayat_id  "); 
		}

		sb.append(" , model.gender ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("locationId", Hibernate.LONG)
				.addScalar("locationName", Hibernate.STRING)
				.addScalar("gender", Hibernate.STRING)
				.addScalar("count", Hibernate.LONG);

		if (locationTypeId !=null && locationTypeId.longValue() >2l && locationValue != null && locationValue.size() >0) {
			query.setParameterList("locationValue", locationValue);
		}
		if (casteId != null && casteId.longValue() > 0L) {
			query.setParameter("casteId", casteId);
		}
		if (enrollmentYearId != null && enrollmentYearId.longValue() > 0L) {
			query.setParameter("enrollmentYearId", enrollmentYearId);
		}

		return query.list();
	}
}
