package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.dto.RtcUnionInputVO;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreDAO extends GenericDaoHibernate<TdpCadre, Long> implements ITdpCadreDAO{

	public TdpCadreDAO() {
		super(TdpCadre.class);
	}

	public List<Object[]> getRegisterCadreInfoBetweenDates(Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId,model.dataSourceType from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
		
		if(fromDate != null){
			queryStr.append(" and date(model.surveyTime) >=:fromDate ");
		}
		
		if(toDate != null){
			queryStr.append(" and date(model.surveyTime) <=:toDate ");
		}
		
		queryStr.append(" group by model.userAddress.district.districtId,model.dataSourceType ");	

		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null){
		   query.setDate("fromDate", fromDate);
		}
		if(toDate != null){
		  query.setDate("toDate", toDate);
		}
		return query.list();
	}
	
	public List<Object[]> getNewlyRegisterCadreInfo(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
		
		queryStr.append(" and model.previousEnrollmentNo is null group by model.userAddress.district.districtId ");	

		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getRegisterCadreInfoDistrictWise(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtName from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1  and model.enrollmentYear = 2014 " +
				" group by model.userAddress.district.districtId ");

		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getRegisterCadreInfoConstituencyWise(){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.constituency.name from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1  and model.enrollmentYear = 2014 " +
				" group by model.userAddress.constituency.constituencyId");

		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public String getLatestMemberNumber()
	{
		Query query = getSession().createQuery("select model.memberShipNo from TdpCadre model where model.isDeleted = 'N' order by model.tdpCadreId desc ");
		query.setMaxResults(1);
		return (String)query.uniqueResult();
	}

	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre,Integer startIndex,Integer maxIndex)
	{
		StringBuilder str = new StringBuilder();
		
		if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
		{
			str.append(" select distinct TC.tdpCadreId, TC.firstname, TC.relativename, TC.dateOfBirth, TC.age, TC.gender, TC.houseNo, UA.userAddressId,TC.voterId,TC.relativeType ");
		}
		else
		{
			str.append(" select distinct TC.tdpCadreId, TC.firstname, TC.relativename, TC.dateOfBirth, TC.age, TC.gender, UA.houseNo, UA.userAddressId,TC.voterId,TC.relativeType ");
		}

		str.append(" ,TC.image from TdpCadre TC, UserAddress UA where "+queryStr+" TC.userAddress.constituency.constituencyId = :constituencyId ");
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				str.append(" and TC.userAddress.panchayat.panchayatId = :id ");
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				str.append(" and TC.userAddress.localElectionBody.localElectionBodyId = :id ");
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			str.append(" and TC.userAddress.booth.boothId = :boothId ");
		}
		
		if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
		{
			str.append(" and TC.enrollmentYear in (:cadreEnrollmentYear)  ");
		}
		else
		{
			str.append(" and TC.enrollmentYear not in (:cadreEnrollmentYear)  ");
		}
		
		str.append(" and TC.userAddress.userAddressId = UA.userAddressId  and TC.isDeleted = 'N'  order by TC.houseNo ");
		
		Query query = getSession().createQuery(str.toString()); 
		
		query.setParameter("constituencyId", constituencyId);
		

		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}		
		
		query.setParameter("cadreEnrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		if(startIndex != null && maxIndex != null){
			  query.setFirstResult(startIndex);
			  query.setMaxResults(maxIndex);
			}
		return query.list();
	}
	public Long getCadreDetailsForCadreRegistratiobByconstituencIdCount(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre)
	{
		StringBuilder str = new StringBuilder();
		
		if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
		{
			str.append(" select count(*) ");
		}
		else
		{
			str.append(" select count(*) ");
		}

		str.append("  from TdpCadre TC, UserAddress UA where "+queryStr+" TC.userAddress.constituency.constituencyId = :constituencyId ");
		
		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				str.append(" and TC.userAddress.panchayat.panchayatId = :id ");
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				str.append(" and TC.userAddress.localElectionBody.localElectionBodyId = :id ");
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			str.append(" and TC.userAddress.booth.boothId = :boothId ");
		}
		
		if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
		{
			str.append(" and TC.enrollmentYear in (:cadreEnrollmentYear)  ");
		}
		else
		{
			str.append(" and TC.enrollmentYear not in (:cadreEnrollmentYear)  ");
		}
		
		str.append(" and TC.userAddress.userAddressId = UA.userAddressId  and TC.isDeleted = 'N'  order by TC.houseNo ");
		
		Query query = getSession().createQuery(str.toString()); 
		
		query.setParameter("constituencyId", constituencyId);
		

		if(panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}		
		
		query.setParameter("cadreEnrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		
		return (Long)query.uniqueResult();
	}
	
	public Long getWorkStartedConstituencyCount(String state){
        StringBuilder str = new StringBuilder();
		
		str.append("select count(distinct model.userAddress.constituency.constituencyId)  " +
				"from TdpCadre model where model.enrollmentYear = 2014 and ");
		if(state.equalsIgnoreCase("TS"))
		{
			str.append("  model.userAddress.district.districtId <= 10 ");
		}
		
		if(state.equalsIgnoreCase("AP"))
		{
			str.append("  model.userAddress.district.districtId >= 11 ");
		}
				
		str.append(" and model.userAddress.state.stateId = 1  and model.isDeleted = 'N'  ");
		
		Query query = getSession().createQuery(str.toString());
		
		return (Long) query.uniqueResult();
	}
	
	
	public Long getWorkStartedConstituencyYearCount(Long year,String state,Date fromDate, Date toDate){
		StringBuilder str = new StringBuilder();
		str.append("select count(*)  " +
				"from TdpCadre model " +
				"where model.enrollmentYear = :year "+
				"and model.isDeleted = 'N' ");
		if(state.equalsIgnoreCase("TS"))
		{
			str.append(" and model.userAddress.district.districtId <= 10 ");
		}
		
		if(state.equalsIgnoreCase("AP"))
		{
			str.append(" and model.userAddress.district.districtId >= 11 ");
		}
		
		if(fromDate != null && toDate != null)
		{
			str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}	
			
		str.append(" and model.userAddress.state.stateId = 1 ");
		
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("year", year);
		
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return (Long) query.uniqueResult();
	}
			
	public List<Object[]> getRecentlyRegisteredCadres(){
		//0 first name ,1 lastname,2 constituency ,3 localArea, 4 image
		Query query = getSession().createQuery("select model.firstname,model.lastname,model.userAddress.constituency.name,model.userAddress.localArea,model.image from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1  and model.enrollmentYear = 2014 " +
				" order by model.surveyTime desc");
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}

	public List<Object[]> getRecentlyRegisteredCadres(Integer startIndex,Integer maxIndex){
		//0 first name ,1 lastname,2 constituency ,3 localArea, 4 image
		Query query = getSession().createQuery("select model.firstname,model.lastname,model.userAddress.constituency.name,model.userAddress.localArea,model.image from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1  and model.enrollmentYear = 2014 " +
				" order by model.surveyTime desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}

	public List<Long> getCadreAvailableConstituencies(Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.userAddress.constituency.constituencyId from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1  and model.enrollmentYear = 2014 ");
		if(stateId != null && stateId.longValue() > 0l){
			if(stateId.longValue() == 1l){
				queryStr.append(" and model.userAddress.district.districtId > 10 ");
			}else{
				queryStr.append(" and model.userAddress.district.districtId < 11 ");
			}
		}
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Long> getCadreAvailableDistricts(Long stateId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct model.userAddress.district.districtId from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1  and model.enrollmentYear = 2014 ");
		if(stateId != null && stateId.longValue() > 0l){
			if(stateId.longValue() == 1l){
				queryStr.append(" and model.userAddress.district.districtId > 10 ");
			}else{
				queryStr.append(" and model.userAddress.district.districtId < 11 ");
			}
		}
		Query query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getCadreInfoConstituencytWise(List<Long> constituencyIds,Date fromDate, Date toDate,Long year){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,model.enrollmentYear ,model.userAddress.constituency.areaType from TdpCadre model where model.isDeleted = 'N'   and model.userAddress.constituency.constituencyId in(:constituencyIds) ");
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}	
		queryStr.append(" and model.enrollmentYear =:year group by model.userAddress.constituency.constituencyId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("year", year);
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	
	public List<Object[]> getCadreInfoDistrictWise(List<Long> districtIds,Date fromDate, Date toDate,Long year){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId,model.userAddress.district.districtName,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N'   and model.userAddress.district.districtId in(:districtIds) ");
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}	
		queryStr.append(" and model.enrollmentYear =:year  group by model.userAddress.district.districtId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("districtIds", districtIds);
		query.setParameter("year", year);
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	
	/*public Long getWorkingMembersCount(Date date){
		Query query = getSession().createQuery("select count(distinct model.insertedBy.cadreSurveyUserId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType='TAB' and date(model.surveyTime) =:date and model.insertedBy.cadreSurveyUserId is not null");
		
		query.setDate("date", date);
		return (Long)query.uniqueResult();
	}*/
	
	@SuppressWarnings("unchecked")
	public Long getWorkingMembersCount(Date date){
		Query query = getSession().createQuery("select model.insertedBy.cadreSurveyUserId from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType='TAB' and " +
				" date(model.surveyTime) =:date and model.insertedBy.cadreSurveyUserId is not null group by model.insertedBy.cadreSurveyUserId ");
		
		query.setDate("date", date);
		List<Long> list = query.list();
		return Long.valueOf(Integer.valueOf(list.size()).toString());
	}
	
	/*public Long getWorkingMembersForWebCount(Date date){
		Query query = getSession().createQuery("select count(distinct  model.insertedWebUser.userId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType='WEB' and date(model.surveyTime) =:date and  model.insertedWebUser.userId is not null");
		
		query.setDate("date", date);
		return (Long)query.uniqueResult();
	}*/
	
	@SuppressWarnings("unchecked")
	public Long getWorkingMembersForWebCount(Date date){
		Query query = getSession().createQuery("select model.insertedWebUser.userId from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' " +
				" and model.dataSourceType='WEB' and date(model.surveyTime) =:date and model.insertedWebUser.userId is not null group by model.insertedWebUser.userId ");
		
		query.setDate("date", date);
		List<Long> list = query.list();
		return Long.valueOf(Integer.valueOf(list.size()).toString());
	}
	
	public List<TdpCadre> getVoterByVoterId(Long voterId,Long memberTypeId)
	{
		if(memberTypeId != null && memberTypeId.longValue()>1L){
			Query query = getSession().createQuery("select model  from TdpCadre model where model.voterId = :voterId  and " +
					" model.isDeleted = 'N' and model.tdpMemberTypeId=:tdpMemberTypeId and model.enrollmentYear = :enrollmentYear ");
			query.setParameter("voterId", voterId);
			query.setParameter("tdpMemberTypeId", memberTypeId);//tdpMemberTypeId
			query.setParameter("enrollmentYear",  IConstants.UNIONS_REGISTRATION_YEAR);
			return query.list();
		}
		else
		{
			Query query = getSession().createQuery("select model  from TdpCadre model where model.voterId = :voterId  and " +
					" model.isDeleted = 'N' and model.enrollmentYear = :enrollmentYear ");
			query.setParameter("voterId", voterId);
			query.setParameter("enrollmentYear",  IConstants.CADRE_ENROLLMENT_NUMBER);
			return query.list();
		}
	}
	public List<TdpCadre> getNormalCadreDetailsByVoterId(Long voterId)
	{
		Query query = getSession().createQuery("select model  from TdpCadre model where model.voterId = :voterId  and model.isDeleted = 'N' and model.tdpMemberTypeId is null ");
		query.setParameter("voterId", voterId);
		return query.list();
	}
	public List<TdpCadre> getAffliatedCadreByVoterId(Long voterId,Long memberTypeId)
	{
		Query query = getSession().createQuery("select model from TdpCadre model where model.voterId = :voterId  and model.isDeleted = 'N' and model.tdpMemberTypeId=:tdpMemberTypeId and model.enrollmentYear = :enrollmentYear ");
		query.setParameter("voterId", voterId);
		query.setParameter("tdpMemberTypeId", memberTypeId);
		query.setParameter("enrollmentYear",  IConstants.RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER);
		return query.list();
	}
	
	public List<TdpCadre> getAffliatedCadreByVoterIdAndMemberType(Long voterId,Long memberTypeId)
	{
		Query query = getSession().createQuery("select model from TdpCadre model where model.voterId = :voterId  and model.isDeleted = 'N' and model.tdpMemberTypeId=:memberTypeId and model.enrollmentYear = :enrollmentYear ");
		query.setParameter("voterId", voterId);
		query.setParameter("memberTypeId", memberTypeId);
		//query.setParameter("tdpMemberTypeId", IConstants.AFFLIATED_TDP_MEMBER_TYPE_ID);
		query.setParameter("enrollmentYear",  IConstants.RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER);
		return query.list();
	}
	
	public List<TdpCadre> getAffliatedCadreByFamilyVoterId(Long voterId, String refNo){
		Query query = getSession().createQuery("select model from TdpCadre model where model.familyVoterId = :voterId " +
				" and model.isDeleted = 'N'" +
				" and model.tdpMemberTypeId= :tdpMemberTypeId " +
				" and model.enrollmentYear = :enrollmentYear" +
				" and model.refNo =:refNo ");
		query.setParameter("voterId", voterId);
		query.setParameter("tdpMemberTypeId", IConstants.AFFLIATED_TDP_MEMBER_TYPE_ID);
		query.setParameter("enrollmentYear",  IConstants.RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER);
		query.setParameter("refNo", refNo);
		return query.list();
	}
	
	public Long checkRandomNoExistsOrNot(String dataSource,String randomNo){
        Query query = getSession().createQuery("select count(*) from TdpCadre model where  model.isDeleted = 'N' and model.dataSourceType=:dataSource and model.refNo =:randomNo ");
		
		query.setParameter("dataSource", dataSource);
		query.setParameter("randomNo", randomNo);
		return (Long)query.uniqueResult();
	}

	public List<Object[]> getexistringCadreInfoByLocation(String candidateName, Long constid, Long panchayatId,Long boothId,String isPresentCadre, String enrollmentNo)
	{
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select model.firstname, model.relativename, model.memberShipNo, model.tdpCadreId from TdpCadre model where ");
		boolean candiNameExist = false;
		if(candidateName.length()>2){
			candiNameExist = true;
			queryStr.append(" model.firstname like '%"+candidateName+"%' ");
		}
		
		if(enrollmentNo.length()>2){
			if(candiNameExist){
				queryStr.append(" and ");
			}
			queryStr.append(" model.memberShipNo = '"+enrollmentNo+"' ");
		}
		
		
		if(constid != null && constid.longValue() != 0L)
		{
			queryStr.append(" and model.userAddress.constituency.constituencyId = :constid ");
		}
		
		if(panchayatId != null && panchayatId.longValue() != 0L)
		{
			//queryStr.append(" and model.userAddress.panchayatId = :panchayatId ");
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				queryStr.append(" and model.userAddress.panchayat.panchayatId = :id ");
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId = :id ");
			}
		}
		/*if(boothId != null && boothId.longValue() != 0L)
		{
			queryStr.append(" and model.userAddress.booth.boothId = :boothId");
		}*/
		if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
		{
			queryStr.append(" and model.enrollmentYear in (:year) ");
		}
		
		else
		{
			queryStr.append(" and model.enrollmentYear not in (:year) ");
		}
		
		queryStr.append(" and model.memberShipNo is not null and model.memberShipNo != '' and model.isDeleted = 'N' order by model.firstname ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(constid != null && constid.longValue() != 0L)
		{
			query.setParameter("constid", constid);
		}
		
		if(panchayatId != null && panchayatId.longValue() != 0L)
		{
			if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
				query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
			}
		}
		/*if(boothId != null && boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}*/
		
			query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);

		return query.list();
		
	}
		
	public List<Object[]> getCandidateDataCollectionInfo(Long locationType,List<Long> locationIds,Date fromDate,Date toDate){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId,model.insertedBy.name  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ");
		/*if (locationType==0) {
			queryStr.append("");
		} else*/ if(locationType.longValue() == 1l){
			if(locationIds.contains(2l) && locationIds.contains(1l)){
			   queryStr.append(" and model.userAddress.state.stateId= 1 ");
			}else if(locationIds.contains(2l)){
				queryStr.append(" and model.userAddress.state.stateId= 1  and model.userAddress.district.districtId < 11 ");
			}else if(locationIds.contains(1l)){
				queryStr.append(" and model.userAddress.state.stateId= 1  and model.userAddress.district.districtId > 10 ");
			}
		}
		else if(locationType.longValue() == 2l){
			queryStr.append(" and model.userAddress.district.districtId in(:locationIds) ");
		}
		else if(locationType.longValue() == 3l){
			queryStr.append(" and model.userAddress.constituency.constituencyId in(:locationIds)  ");
		}
		queryStr.append(" group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName ");
		Query query = getSession().createQuery(queryStr.toString());
		
		/*Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");*/
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if(locationType.longValue() != 0l && locationType.longValue() != 1l){
		   query.setParameterList("locationIds", locationIds);
		}
		return query.list();
	}
	
	public Long checkMemberShipExistsOrNot(String randomNo){
        Query query = getSession().createQuery("select count(*) from TdpCadre model where model.enrollmentYear = 2014 and model.memberShipNo =:randomNo ");
		query.setParameter("randomNo", randomNo);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getCadreDetailsByMemberId(String memberCardNo)
	{
		Query query = getSession().createQuery("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo,model.voter.imagePath from TdpCadre model where model.memberShipNo = :memberCardNo  and model.isDeleted = 'N'");
		query.setParameter("memberCardNo", memberCardNo);
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseCadreDetails(Long panchayatId )
	{
		Query query = getSession().createQuery("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo,model.refNo,model.cardNumber,model.image,model.voter.imagePath from TdpCadre model where model.userAddress.panchayat.panchayatId = :panchayatId  and model.isDeleted = 'N'");
		query.setParameter("panchayatId", panchayatId);
		return query.list();
	}
	
	/*public List<Object[]> getPanchayatWiseCadreDetails(Long panchayatId)
	{
		Query query = getSession().createQuery("select model.refNo , model.firstname from TdpCadre model where model.userAddress.panchayat.panchayatId = :panchayatId and model.enrollmentYear = :year order by model.firstname ");
		query.setParameter("panchayatId", panchayatId);
		query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);
		return query.list();
	}*/
	
	public Integer updateNFCCardNumberByVoterId(Long voterId , String nfcCardNo)
	{
		Query query = getSession().createQuery("update TdpCadre model set model.cardNumber = :nfcCardNo where model.voterId = :voterId and  model.enrollmentYear = :year  and model.isDeleted = 'N' ");
		query.setParameter("nfcCardNo", nfcCardNo);
		query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);
		query.setParameter("voterId", voterId);
		Integer c = query.executeUpdate();
		
		return c;
	}
	
	public Integer updateNFCCardNumberByTdpCadreId(Long tdpCadreId,String nfcCardNo)
	{
		Query query = getSession().createQuery("update TdpCadre model set model.cardNumber = :nfcCardNo where model.tdpCadreId = :tdpCadreId and  model.enrollmentYear = :year  and model.isDeleted = 'N' ");
		query.setParameter("nfcCardNo", nfcCardNo);
		query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);
		query.setParameter("tdpCadreId", tdpCadreId);
		Integer c = query.executeUpdate();
		
		return c;
	}
	
	public List<Object[]> getAgeRangeCadreCount(Long Id, String ageRange,
			String type) {
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}

		if (ageRange.equals("below 18")) {
			queryStr.append(" and model.age <18 ");
		} else if (ageRange.equals("18-25")) {
			queryStr.append(" and model.age >=18 and model.age<=25 ");
		} else if (ageRange.equals("26-35")) {
			queryStr.append(" and model.age >=26 and model.age<=35 ");
		} else if (ageRange.equals("36-45")) {
			queryStr.append(" and model.age >=36 and model.age<=45 ");
		} else if (ageRange.equals("46-60")) {
			queryStr.append(" and model.age >=46 and model.age<=60 ");
		} else if (ageRange.equals("above 60")) {
			queryStr.append(" and model.age >60 ");
		}

		queryStr.append(" group by model.enrollmentYear order by model.enrollmentYear desc ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);
		return query.list();
	}

	public Long getAgeRangeTotalCount(Long Id, Long enrollmentYear, String type) {

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId) "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
		queryStr.append(" and model.enrollmentYear = :enrollmentYear ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);
		query.setParameter("enrollmentYear", enrollmentYear);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getGender(){
		String queryStr="select distinct(model.gender) " +
				" from TdpCadre model where model.isDeleted = 'N' ";
		Query query = getSession().createQuery(queryStr);		
		return query.list();
	}

	public Long getGenderTotalCount(Long Id, Long enrollmentYear, String type) {

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.gender) "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
		queryStr.append(" and model.enrollmentYear = :enrollmentYear ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);
		query.setParameter("enrollmentYear", enrollmentYear);
		return (Long) query.uniqueResult();
	}

	public List<Object[]> getGenderWiseCadreCount(Long Id,String type){		
		
		StringBuilder queryStr=new StringBuilder();
		queryStr.append("select count(model.gender),model.enrollmentYear,model.gender " +
				"from TdpCadre model where model.isDeleted = 'N' ");
		if(type.equalsIgnoreCase("constituency")){
		    	queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		}else if(type.equalsIgnoreCase("district")){
				queryStr.append(" and model.userAddress.district.districtId = :Id  " );
		}		
		queryStr.append(" group by  model.enrollmentYear,model.gender  order by model.enrollmentYear desc," +
				" model.gender desc ");
		Query query = getSession().createQuery(queryStr.toString());		
		query.setParameter("Id", Id);
		
		
		return query.list();
    }

  
  public Long getDistrictWiseCasteCount(Long districtId,Long enrollmentYear){
		
		String queryStr="select count(model.casteStateId) " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.districtId = :districtId " +
				" and model.enrollmentYear = :enrollmentYear ";
		Query query = getSession().createQuery(queryStr);
		query.setParameter("districtId", districtId);
		query.setParameter("enrollmentYear", enrollmentYear);
		return (Long)query.uniqueResult();
  }
  

	
  
  public Long getCasteTotalCount(Long Id,Long enrollmentYear,String type) {

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.casteStateId) "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
		queryStr.append(" and model.enrollmentYear = :enrollmentYear ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);
		query.setParameter("enrollmentYear", enrollmentYear);
		return (Long) query.uniqueResult();
	}
  
   public List<Object[]> getCastWiseCadreCount(Long Id,String type) {
		StringBuilder queryStr=new StringBuilder(); 
		queryStr.append("select count(model.casteStateId),model.enrollmentYear,model.casteState.casteStateId,model.casteState.caste.casteName,model.casteState.casteCategoryGroup.casteCategory.categoryName "+
				" from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
			queryStr.append(" group by  model.casteState.casteStateId,model.enrollmentYear order by model.casteState.caste.casteName ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);

		return query.list();
	}

   public List<Object[]> getCastGroupWiseCadreCount(Long Id,String type) {
		StringBuilder queryStr=new StringBuilder(); 
		queryStr.append("select count(model.casteStateId),model.enrollmentYear,model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName "+
				" from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
			queryStr.append(" group by  model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.enrollmentYear  ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);

		return query.list();
	}
   
   	@SuppressWarnings("unchecked")
	public List<Object[]> getCasteGroupWiseCadreCountExcludingMinorities(Long Id,String type)
   	{
   		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT count(model.casteStateId),model.enrollmentYear,model.casteState.casteCategoryGroup.casteCategory.casteCategoryId, ");
		queryStr.append(" model.casteState.casteCategoryGroup.casteCategory.categoryName from TdpCadre model where model.isDeleted = 'N' ");
		queryStr.append(" and model.casteState.casteStateId not in("+IConstants.MINORITY_CASTE_IDS+") ");
		
		if (type.equalsIgnoreCase("constituency"))
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		else if (type.equalsIgnoreCase("district"))
			queryStr.append(" and model.userAddress.constituency.district.districtId = :Id ");

		queryStr.append(" group by  model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.enrollmentYear  ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCadreCountInMinorities(Long Id,String type)
   	{
   		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT count(model.casteStateId),model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' ");
		queryStr.append(" and model.casteState.casteStateId in("+IConstants.MINORITY_CASTE_IDS+") ");
		
		if (type.equalsIgnoreCase("constituency"))
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		else if (type.equalsIgnoreCase("district"))
			queryStr.append(" and model.userAddress.constituency.district.districtId = :Id ");

		queryStr.append(" group by model.enrollmentYear ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);

		return query.list();
	}

	
	public List<Object[]> getCadreInfoPanchayatWise(List<Long> panchayatIds,Date fromDate, Date toDate,Long year){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' and model.userAddress.panchayat.panchayatId in(:panchayatIds) ");
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}	
		queryStr.append("  and model.enrollmentYear =:year group by model.userAddress.panchayat.panchayatId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("panchayatIds", panchayatIds);
		query.setParameter("year", year);
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getCadreInfoBoothWise(List<Long> boothIds,Date fromDate, Date toDate,Long year){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.booth.boothId,model.userAddress.booth.partNo,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' and model.userAddress.booth.boothId in(:boothIds) " );
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}	
		queryStr.append("  and model.enrollmentYear =:year group by model.userAddress.booth.boothId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("boothIds", boothIds);
		query.setParameter("year", year);
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getCadreInfoMandalWise(List<Long> tehsilIds,Date fromDate, Date toDate,Long year){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName,model.enrollmentYear,'mandal' from TdpCadre model where model.isDeleted = 'N' and model.userAddress.tehsil.tehsilId in(:tehsilIds) " );		
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}				
		queryStr.append("  and model.enrollmentYear =:year " +
				" and model.userAddress.localElectionBody.localElectionBodyId is null " +
				" group by model.userAddress.tehsil.tehsilId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("year", year);
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	public List<Object[]> getCadreInfoLocalBodyWise(List<Long> localBdyIds,Date fromDate, Date toDate,Long year){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name,model.enrollmentYear,'localBody' from TdpCadre model where model.isDeleted = 'N' and model.userAddress.localElectionBody.localElectionBodyId in(:localBdyIds) " );
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}	
		queryStr.append("  and model.enrollmentYear =:year group by model.userAddress.localElectionBody.localElectionBodyId,model.enrollmentYear ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("localBdyIds", localBdyIds);
		query.setParameter("year", year);
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	
	public List<String> chechForCardNumber(String cardNo)
	{
		Query query = getSession().createQuery("select model.cardNumber from TdpCadre model where model.cardNumber = :cardNo");
		query.setParameter("cardNo", cardNo);
		return query.list();
	}
	
	public List<Long> getVoterDetailsByVoterIds(List<Long> voterIdList)
	{
		Query query = getSession().createQuery("select distinct model.voterId  from TdpCadre model where model.voterId in (:voterIdList) and model.isDeleted = 'N' " +
				" and model.enrollmentYear in (:enrollmentYear)  ");
		
		query.setParameterList("voterIdList", voterIdList);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		
		return query.list();
	}
	
	public List<String> getCadreImageByPreviousEnrolId(String previousEnrollmentNo)
	{
		Query query = getSession().createQuery("select distinct model.image  from TdpCadre model where model.memberShipNo =:previousEnrollmentNo and model.isDeleted = 'N' ");
		
		query.setParameter("previousEnrollmentNo",previousEnrollmentNo);
		
		return query.list();
	}
	
	public List<Object[]> getCasteGroupTotalCount(Long Id, String type){

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
		queryStr.append(" and model.casteState.casteStateId is not null group by model.enrollmentYear");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);
		return query.list();
	}
	public List<Object[]> getAgeTotalCount(Long Id, String type){

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
		queryStr.append(" and model.age is not null group by model.enrollmentYear");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);
		return query.list();
	}
	
	public List<Object[]> getGenderTotalCount(Long Id, String type){
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
		if (type.equalsIgnoreCase("constituency")) {
			queryStr.append(" and model.userAddress.constituency.constituencyId = :Id ");
		} else if (type.equalsIgnoreCase("district")) {
			queryStr.append(" and model.userAddress.district.districtId = :Id ");
		}
		queryStr.append(" and model.gender is not null group by model.enrollmentYear");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("Id", Id);
		return query.list();
	}
	public List<Object[]> getBoothWiseCadreInfo(List<Long> boothIds,int startIndex,int maxIndex,String orderBy,String orderType){
		//0 id,1 image,2name,3relative,4mobile,5partNo,6panchayat
        StringBuilder queryStr = new StringBuilder("select model.tdpCadreId,model.image,model.firstname,model.relativename,model.mobileNo,model.userAddress.booth.partNo,CASE " +
        		" WHEN model.userAddress.panchayat.panchayatId is not null THEN model.userAddress.panchayat.panchayatName ELSE '-' end, model.memberShipNo  from TdpCadre model where model.enrollmentYear ='2014' " +
        		" and model.userAddress.booth.boothId in(:boothIds) and model.isDeleted = 'N' " +
        		" and model.cardNumber is not null and model.cardNumber !='' ");
        if(orderBy.equalsIgnoreCase("panchayat")){
        	queryStr.append(" order by CASE WHEN model.userAddress.panchayat.panchayatId is not null THEN model.userAddress.panchayat.panchayatName ELSE '-' end ");
        }else if(orderBy.equalsIgnoreCase("booth")){
        	queryStr.append(" order by cast(model.userAddress.booth.boothId , int) ");
        }else if(orderBy.equalsIgnoreCase("name")){
        	queryStr.append(" order by model.firstname ");
        }else if(orderBy.equalsIgnoreCase("percentStr")){
        	queryStr.append(" order by model.relativename ");
        }else if(orderBy.equalsIgnoreCase("mobileNo")){
        	queryStr.append(" order by model.mobileNo ");
        }else if(orderBy.equalsIgnoreCase("memberShipNo")){
        	queryStr.append(" order by model.memberShipNo ");
        }
        
        if(orderType.equalsIgnoreCase("asc") || orderType.equalsIgnoreCase("desc")){
           queryStr.append(orderType);
        }
        
        Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("boothIds",boothIds);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseCadreInfo(List<Long> panchayatIds,int startIndex,int maxIndex,String orderBy,String orderType){
        StringBuilder queryStr = new StringBuilder("select model.tdpCadreId,model.image,model.firstname,model.relativename,model.mobileNo,model.userAddress.booth.partNo, " +
        		" model.userAddress.panchayat.panchayatName, model.memberShipNo   from TdpCadre model where model.enrollmentYear ='2014' " +
        		" and model.userAddress.panchayat.panchayatId in(:panchayatIds) and model.isDeleted = 'N' " +
        		" and model.cardNumber is null and model.dispatchStatus is null ");
        if(orderBy.equalsIgnoreCase("panchayat")){
        	queryStr.append(" order by model.userAddress.panchayat.panchayatName ");
        }else if(orderBy.equalsIgnoreCase("booth")){
        	queryStr.append(" order by cast(model.userAddress.booth.boothId , int) ");
        }else if(orderBy.equalsIgnoreCase("name")){
        	queryStr.append(" order by model.firstname ");
        }else if(orderBy.equalsIgnoreCase("percentStr")){
        	queryStr.append(" order by model.relativename ");
        }else if(orderBy.equalsIgnoreCase("mobileNo")){
        	queryStr.append(" order by model.mobileNo ");
        }else if(orderBy.equalsIgnoreCase("memberShipNo")){
        	queryStr.append(" order by model.memberShipNo ");
        }
        
        if(orderType.equalsIgnoreCase("asc") || orderType.equalsIgnoreCase("desc")){
           queryStr.append(orderType);
        }
        
        Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("panchayatIds",panchayatIds);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	/*public Long getLastHoursWorkingMemberCount(Date presentDate, Date lastHours)
	{
		Query query = getSession().createQuery("select " +
				" count(distinct model.insertedBy.cadreSurveyUserId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' " +
				" and model.dataSourceType='TAB' and ( model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and model.insertedBy.cadreSurveyUserId is not null");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		return (Long)query.uniqueResult();
	}*/
	
	@SuppressWarnings("unchecked")
	public Long getLastHoursWorkingMemberCount(Date presentDate, Date lastHours)
	{
		Query query = getSession().createQuery("select " +
				" model.insertedBy.cadreSurveyUserId from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' " +
				" and model.dataSourceType='TAB' and ( model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and model.insertedBy.cadreSurveyUserId is not null " +
				" group by model.insertedBy.cadreSurveyUserId ");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		List<Long> list = query.list();
		return Long.valueOf(Integer.valueOf(list.size()).toString());
	}
	
	/*public Long getLastHoursWorkingMemberCountForWeb(Date presentDate, Date lastHours)
	{
		Query query = getSession().createQuery("select " +
				" count(distinct  model.insertedWebUser.userId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' " +
				" and model.dataSourceType='WEB' and ( model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and  model.insertedWebUser.userId is not null");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		return (Long)query.uniqueResult();
	}*/
	
	@SuppressWarnings("unchecked")
	public Long getLastHoursWorkingMemberCountForWeb(Date presentDate, Date lastHours)
	{
		Query query = getSession().createQuery("select " +
				" model.insertedWebUser.userId from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' " +
				" and model.dataSourceType='WEB' and ( model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and  " +
				" model.insertedWebUser.userId is not null group by model.insertedWebUser.userId");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		List<Long> list = query.list();
		return Long.valueOf(Integer.valueOf(list.size()).toString());
	}
	
	public Integer inActiveTdpCadreByCadreIds(List<Long> tdpCadreIdList)
	{
		Query query = getSession().createQuery("update TdpCadre model set model.isDeleted = 'H' where model.tdpCadreId in (:tdpCadreIdList)  and model.isDeleted = 'N' ");
		query.setParameterList("tdpCadreIdList", tdpCadreIdList);
		Integer count = query.executeUpdate();
		
		return count;
	}
	

	public String checkNFCnumberForVoter(Long voterId)
	{
		Query query = getSession().createQuery("select model.cardNumber from TdpCadre model where model.voterId = :voterId");
		query.setParameter("voterId", voterId);
		return (String) query.uniqueResult();
	}
	
	public Long getBoothWiseCadreInfoCount(List<Long> boothIds){
		//0 id,1 image,2name,3relative,4mobile,5partNo,6panchayat
        StringBuilder queryStr = new StringBuilder("select count(distinct model.tdpCadreId) " +
        		" from TdpCadre model where model.enrollmentYear ='2014' " +
        		" and model.userAddress.booth.boothId in(:boothIds) and model.isDeleted = 'N' " +
        		" and model.cardNumber is not null and model.cardNumber != '' ");
       
        Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("boothIds",boothIds);
		return (Long) query.uniqueResult();
	}
	
	public Long getPanchayatWiseCadreInfoCount(List<Long> panchayatIds){
        StringBuilder queryStr = new StringBuilder("select count(distinct model.tdpCadreId) " +
        		" from TdpCadre model where model.enrollmentYear ='2014' " +
        		" and model.userAddress.panchayat.panchayatId in(:panchayatIds) and model.isDeleted = 'N' " +
        		" and model.cardNumber is null and model.dispatchStatus is null ");
        
        Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("panchayatIds",panchayatIds);
		return (Long) query.uniqueResult();
	}

	
	public Integer updateNFCCardNumberByVoterIdForDelink(Long voterId , String nfcCardNo)
	{
		Query query = getSession().createQuery("update TdpCadre model set model.cardNumber = NULL where model.voterId = :voterId and  model.cardNumber =:nfcCardNo and model.enrollmentYear = :year ");
		query.setParameter("nfcCardNo", nfcCardNo);
		query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);
		query.setParameter("voterId", voterId);
		Integer c = query.executeUpdate();
		
		return c;
	}

	public List<Object[]> getEnrollmentYearWiseDetails(Long locationId , Date fromDate,Date toDate,Long enrollmentYear)
	{
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.enrollmentYear, count(model.tdpCadreId) from TdpCadre model where model.isDeleted = 'N' ");
		
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >=:fromDate ");
			queryStr.append(" and date(model.surveyTime) <=:toDate ");
		}
		queryStr.append(" and  model.userAddress.constituency.constituencyId = :locationId ");
		
		queryStr.append(" and model.enrollmentYear = :enrollmentYear group by model.enrollmentYear  ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setParameter("enrollmentYear", enrollmentYear);
		query.setParameter("locationId", locationId);
		
		if(fromDate != null && toDate != null)
		{
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public Integer updateDispatchStatus(List<Long> cadreIds){
		Query query = getSession().createQuery(" update TdpCadre model set model.dispatchStatus = 'dispatched' where model.tdpCadreId in (:cadreIds) ");
		
		query.setParameterList("cadreIds", cadreIds);
		Integer c = query.executeUpdate();
		return c;
	}
	
	public List<Object[]> getPanchayatWiseCadreDetails1(Long panchayatId,String type )
	{
		
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo,model.refNo,model.cardNumber,model.image,model.voter.imagePath from TdpCadre model where model.isDeleted = 'N' " );
				if (type.equalsIgnoreCase("panchayat")){
					queryStr.append(" and model.userAddress.panchayat.panchayatId = :panchayatId");
				}else if(type.equalsIgnoreCase("booth")){
					queryStr.append(" and model.userAddress.booth.boothId = :panchayatId");
				}
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("panchayatId", panchayatId);
		return query.list();
	}
	
	public Long checkCardNoExistsOrNot(String cardNo){
		Query query = getSession().createQuery("select count(*) from TdpCadre model where model.cardNo = :cardNo");
		query.setParameter("cardNo", cardNo);
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getCadreDataByYear(Long constituencyId)
	{
		//0 cadre.memberId,1cadre.memberShipNo,2cadre.firstname,3cadre.relativename,4cadre.gender,5cadre.mobileNo,
		//6 cadre.dateOfBirth,7cadre.educationId,8 cadre.userAddress.panchayat,9 cadre.userAddress.constituency.constituencyId,
		//10 cadre.userAddress().tehsil().tehsilId,11 cadre.userAddress.localElectionBody,12 cadre.occupationId,
		//13 cadre.casteState.casteStateId, 14cadre.enrollmentYear,15cadre.image,16cadre.nameLocal
		Query query = getSession().createQuery("select model.memberId,model.memberShipNo,model.firstname,model.relativename,model.gender,model.mobileNo,model.dateOfBirth,model.educationId,model.userAddress.panchayat.panchayatId," +
				"  model.userAddress.constituency.constituencyId,model.userAddress.tehsil.tehsilId,model.userAddress.localElectionBody.localElectionBodyId,model.occupationId,model.casteState.casteStateId,model.enrollmentYear, " +
				" model.image,model.nameLocal from TdpCadre model where model.enrollmentYear in (2010,2012) and model.userAddress.constituency.constituencyId = :constituencyId");
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	public List<Object[]> getDistrictsByStateWiseAction(Long stateId)
	{		
		Query query=null;
		if(stateId==1)
		{
			query = getSession().createQuery("select model.districtId, model.districtName from District model where model.districtId > 10 and model.state.stateId = 1 order by model.districtName asc ");
		}
		else if(stateId==2)
		{
			query = getSession().createQuery("select model.districtId, model.districtName from District model where model.districtId < 11 and model.state.stateId = 1 order by model.districtName asc  ");
		}
		else
		{
			query = getSession().createQuery("select model.districtId, model.districtName from District model where model.state.stateId = 1 order by model.districtName asc  ");
		}
		
		return query.list();
	}
	public List<Object[]> getConstsByStateWiseAction(Long stateId)
	{		
		StringBuilder str = new StringBuilder();
        str.append("select distinct model.constituencyId, model.name from Constituency model where model.state.stateId =1 and " +
		"  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null  ");
		if(stateId.longValue() == 1){
			str.append(" and model.district.districtId > 10 ");
		}else if(stateId.longValue() == 2){
			str.append(" and model.district.districtId < 11 ");
		}else{
			str.append(" and model.district.districtId between 1 and 23 ");
		}
		str.append(" order by model.name");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}
	
	public List<Object[]> getTdpCadreDetailsBySearchCriteria(String refNo, String mobileNo)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select distinct model.memberShipNo, model.refNo, model.firstname, model.lastname, model.relativename,  ");
		queryStr.append(" model.gender, model.userAddress.constituency.name, model.mobileNo,model.image, model.cardNumber,model.tdpCadreId, model.voterId ");
		queryStr.append(" from TdpCadre model where model.isDeleted = 'N' and model.isDeleted is not null and model.enrollmentYear = 2014 ");
		
		if(mobileNo != null && mobileNo.trim().length()>0)
		{
			queryStr.append(" and model.mobileNo like '%"+mobileNo+"%' ");
		}
		if(refNo != null && refNo.trim().length()>0)
		{
			queryStr.append(" and model.refNo like '%"+refNo+"%' ");
		}
		queryStr.append("order by model.firstname ");
		
		Query query = getSession().createQuery(queryStr.toString());
				
		return query.list();
	}
	
	
	public List<String> getExistingCadreMemberDetails(String preEnrollmentNo)
	{
		Query query = getSession().createQuery("select model.memberShipNo from TdpCadre model where model.memberShipNo = :preEnrollmentNo");
		query.setParameter("preEnrollmentNo", preEnrollmentNo);
		return query.list();
	}
	
	public List<Object[]> getCadreDetailsForSelection(CadrePrintInputVO input){
		
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.memberShipNo ," +
				" model.voterId,model.firstname," +
				" model.relativename," +
				" model.voter.voterId," +
				" model.voter.voterIDCardNo," +
				" model.refNo,model.cardNumber," +
				" model.image," +
				" model.photoType from TdpCadre model where model.isDeleted = 'N' " );
				if(input.getDistrictId()!=null){
					queryStr.append(" and model.userAddress.district.districtId = :districtId");
				}
				if (input.getPanchayatId()!=null){
					queryStr.append(" and model.userAddress.panchayat.panchayatId = :panchayatId");
				}
				if(input.getBoothId()!=null){
					queryStr.append(" and model.userAddress.booth.boothId = :boothId");
				}
				if(input.getConstituencyId()!=null){
					queryStr.append(" and model.userAddress.constituency.constituencyId = :constituencyId");
				}
				if(input.getMandalId()!=null){
					queryStr.append(" and model.userAddress.tehsil.tehsilId = :tehsilId");
				}
				if(input.getLocalBodyId()!=null){
					queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId = :localElectionBodyId");
				}
				if(input.getRegType() != null){
					queryStr.append(" and model.dataSourceType = :dataSourceType ");
				}
				queryStr.append(" and model.enrollmentYear = 2014   and model.cardNumber is null and model.isDeleted = 'N'  order by model.tdpCadreId desc");	
				
		Query query = getSession().createQuery(queryStr.toString());
		if(input.getDistrictId()!=null){
			query.setParameter("districtId", input.getDistrictId());
		}
		if (input.getPanchayatId()!=null){
			query.setParameter("panchayatId", input.getPanchayatId());
		}
		if(input.getBoothId()!=null){
			query.setParameter("boothId", input.getBoothId());
		}
		if(input.getConstituencyId()!=null){
			query.setParameter("constituencyId", input.getConstituencyId());
		}
		if(input.getMandalId()!=null){
			query.setParameter("tehsilId", input.getMandalId());
		}
		if(input.getLocalBodyId()!=null){
			query.setParameter("localElectionBodyId", input.getLocalBodyId());
		}
		if(input.getRegType() != null){
			query.setParameter("dataSourceType", input.getRegType());
		}
		
		query.setFirstResult(0);
		query.setMaxResults(100);
		return query.list();
	}
	
public List<Object[]> getCadreDetailsForSelectionByFamilyVoterId(CadrePrintInputVO input){
		
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.memberShipNo ," +
				" model.familyVoterId,model.firstname," +
				" model.relativename," +
				" model.familyVoterId," +
				" model.voter.voterIDCardNo," +
				" model.refNo,model.cardNumber," +
				" model.image," +
				" model.photoType from TdpCadre model where model.isDeleted = 'N' " );
				if(input.getDistrictId()!=null){
					queryStr.append(" and model.userAddress.district.districtId = :districtId");
				}
				if (input.getPanchayatId()!=null){
					queryStr.append(" and model.userAddress.panchayat.panchayatId = :panchayatId");
				}
				if(input.getBoothId()!=null){
					queryStr.append(" and model.userAddress.booth.boothId = :boothId");
				}
				if(input.getConstituencyId()!=null){
					queryStr.append(" and model.userAddress.constituency.constituencyId = :constituencyId");
				}
				if(input.getMandalId()!=null){
					queryStr.append(" and model.userAddress.tehsil.tehsilId = :tehsilId");
				}
				if(input.getLocalBodyId()!=null){
					queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId = :localElectionBodyId");
				}
				if(input.getRegType() != null){
					queryStr.append(" and model.dataSourceType = :dataSourceType ");
				}
				queryStr.append(" and model.enrollmentYear = 2014   and model.cardNumber is null and model.isDeleted = 'N' and model.familyVoterId is not null  order by model.tdpCadreId desc");	
				
		Query query = getSession().createQuery(queryStr.toString());
		if(input.getDistrictId()!=null){
			query.setParameter("districtId", input.getDistrictId());
		}
		if (input.getPanchayatId()!=null){
			query.setParameter("panchayatId", input.getPanchayatId());
		}
		if(input.getBoothId()!=null){
			query.setParameter("boothId", input.getBoothId());
		}
		if(input.getConstituencyId()!=null){
			query.setParameter("constituencyId", input.getConstituencyId());
		}
		if(input.getMandalId()!=null){
			query.setParameter("tehsilId", input.getMandalId());
		}
		if(input.getLocalBodyId()!=null){
			query.setParameter("localElectionBodyId", input.getLocalBodyId());
		}
		if(input.getRegType() != null){
			query.setParameter("dataSourceType", input.getRegType());
		}
		
		query.setFirstResult(0);
		query.setMaxResults(100);
		return query.list();
	}
	
	/**
	 * DAO Method will fetch Survey Member Details by input Datetime
	 */
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getLastHoursWorkingMembersDetails(Date presentDate, Date lastHours) {
		
		String resultQuery = "select model.tdpCadreId, model.insertedBy.cadreSurveyUserId, model.insertedBy.userName, model.latitude, model.longititude from TdpCadre model right join (select newmodel.insertedBy.cadreSurveyUserId crtUser, max(newmodel.surveyTime) survTime from TdpCadre newmodel where newmodel.enrollmentYear = 2014 and newmodel.isDeleted = 'N' and newmodel.dataSourceType = 'TAB' and (newmodel.surveyTime >= :lastHours and newmodel.surveyTime <= :presentDate) and newmodel.insertedBy.cadreSurveyUserId is not null group by newmodel.insertedBy.cadreSurveyUserId) surRes on model.insertedBy.cadreSurveyUserId = crtUser and model.surveyTime = survTime";
		Query query = getSession().createQuery(resultQuery);
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		return query.list();
	}	*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLastHoursWorkingMembersDetails(Date presentDate, Date lastHours) {
		
		String resultQuery = "select model.tdpCadreId, model.insertedBy.cadreSurveyUserId, model.insertedBy.userName, model.latitude, model.longititude from TdpCadre model  where model.surveyTime >= :lastHours and model.surveyTime <= :presentDate and model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType = 'TAB' group by model.insertedBy.cadreSurveyUserId  having max(model.surveyTime) < :presentDate";
		Query query = getSession().createQuery(resultQuery);
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		return query.list();
	}	
	public List<Object[]> getCadreInfoDetails(Long locationId,String locationType,int startIndex,int maxIndex){
		//0image,1name,2relative,3constituency,4age,5dataSourceType
        StringBuilder queryStr = new StringBuilder("select model.image,model.firstname,model.relativename,model.userAddress.constituency.name,model.age," +
        		" model.dataSourceType from TdpCadre model where model.enrollmentYear ='2014' and model.isDeleted = 'N'  ");
        if(locationType.equalsIgnoreCase("district")){
        	queryStr.append(" and model.userAddress.district.districtId =:locationId ");
        }else if(locationType.equalsIgnoreCase("constituency")){
        	queryStr.append(" and model.userAddress.constituency.constituencyId =:locationId ");
        }
        queryStr.append(" order by model.insertedTime desc ");
        
        Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("locationId",locationId);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	public Long getCadreInfoDetailsCount(Long locationId,String locationType){
        StringBuilder queryStr = new StringBuilder("select count(*)" +
        		"  from TdpCadre model where model.enrollmentYear ='2014' and model.isDeleted = 'N'  ");
        if(locationType.equalsIgnoreCase("district")){
        	queryStr.append(" and model.userAddress.district.districtId =:locationId ");
        }else if(locationType.equalsIgnoreCase("constituency")){
        	queryStr.append(" and model.userAddress.constituency.constituencyId =:locationId ");
        }
        queryStr.append(" order by model.insertedTime desc ");
        
        Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("locationId",locationId);
		return (Long)query.uniqueResult();
	}
	
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getWorkingMembersDetails(Date date){
		
		String resultQuery = "select model.tdpCadreId, model.insertedBy.cadreSurveyUserId, model.insertedBy.userName, model.latitude, model.longititude from TdpCadre model right join (select newmodel.insertedBy.cadreSurveyUserId crtUser, max(newmodel.surveyTime) survTime from TdpCadre newmodel where newmodel.enrollmentYear = 2014 and newmodel.isDeleted = 'N' and newmodel.dataSourceType = 'TAB' and date(model.surveyTime) = :date and newmodel.insertedBy.cadreSurveyUserId is not null group by newmodel.insertedBy.cadreSurveyUserId) surRes on model.insertedBy.cadreSurveyUserId = crtUser and model.surveyTime = survTime";
		Query query = getSession().createQuery(resultQuery);
		query.setDate("date", date);
		
		return query.list();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWorkingMembersDetails(Date presentDate){
		
		String resultQuery = "select model.tdpCadreId, model.insertedBy.cadreSurveyUserId, model.insertedBy.userName, model.latitude, model.longititude from TdpCadre model  where  model.insertedTime = :presentDate and model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType = 'TAB' and  model.insertedBy.cadreSurveyUserId is not null group by model.insertedBy.cadreSurveyUserId  having max(date(model.insertedTime)) = :presentDate";
		Query query = getSession().createQuery(resultQuery);
		query.setDate("presentDate", presentDate);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCadreInfoDistrictConstiWise(List<Long> districtIds,Date fromDate, Date toDate,Long year,List<Long> constiIds){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId,model.userAddress.district.districtName,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N'   " +
				" and model.userAddress.district.districtId in(:districtIds) " +
				" and model.userAddress.constituency.constituencyId in (:constiIds)");
		if(fromDate != null && toDate != null)
		{
			queryStr.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
		}	
		queryStr.append(" and model.enrollmentYear =:year  group by model.userAddress.district.districtId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("districtIds", districtIds);
		query.setParameterList("constiIds", constiIds);
		query.setParameter("year", year);
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	
	public List<TdpCadre> checkOnlineAccountExistsOrNot(String orderId){
		String resultQuery = "select model from TdpCadre model where model.tdpCadreOnline.orderId =:orderId";
		Query query = getSession().createQuery(resultQuery);
		query.setParameter("orderId", orderId);
		return query.list();
	}
	
	public List<TdpCadre> checkAffliatedCadreOnlineAccountExistsOrNot(String orderId){
		String resultQuery = "select model from TdpCadre model where model.tdpCadreOnline.orderId =:orderId and model.tdpMemberTypeId = :tdpMemberTypeId ";
		Query query = getSession().createQuery(resultQuery);
		query.setParameter("orderId", orderId);
		query.setParameter("tdpMemberTypeId", IConstants.AFFLIATED_TDP_MEMBER_TYPE_ID);
		return query.list();
	}
	
	public List<Object[]> getRegisterCadreInfoForUserBetweenDates(Date fromDate,Date toDate,List<Long> constiIds,List<Long> districtIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId,model.dataSourceType from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
		
		if(fromDate != null){
			queryStr.append(" and date(model.surveyTime) >=:fromDate ");
		}
		
		if(toDate != null){
			queryStr.append(" and date(model.surveyTime) <=:toDate ");
		}
		if(constiIds != null && constiIds.size() > 0)
		queryStr.append(" and  model.userAddress.constituency.constituencyId in(:constiIds)");
		if(districtIds != null && districtIds.size() > 0)
		queryStr.append(" and  model.userAddress.district.districtId in(:districtIds)");
		queryStr.append(" and (model.insertedWebUser.userId not in (3930,7394) or model.insertedWebUser.userId is null) ");
		queryStr.append(" group by model.userAddress.district.districtId,model.dataSourceType ");	
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null){
		   query.setDate("fromDate", fromDate);
		}
		if(toDate != null){
		  query.setDate("toDate", toDate);
		}
		if(constiIds != null && constiIds.size() > 0)
			query.setParameterList("constiIds", constiIds);
		if(districtIds != null && districtIds.size() > 0)
			query.setParameterList("districtIds", districtIds);
		return query.list();
	}
	public List<Object[]> getRegisterCadreInfoForUserBetweenDates1(Date fromDate,Date toDate,List<Long> constiIds,List<Long> districtIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId,model.dataSourceType,model.insertedWebUser.userId from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
		
		if(fromDate != null){
			queryStr.append(" and date(model.surveyTime) >=:fromDate ");
		}
		
		if(toDate != null){
			queryStr.append(" and date(model.surveyTime) <=:toDate ");
		}
		if(constiIds != null && constiIds.size() > 0)
		queryStr.append(" and  model.userAddress.constituency.constituencyId in(:constiIds)");
		if(districtIds != null && districtIds.size() > 0)
		queryStr.append(" and  model.userAddress.district.districtId in(:districtIds)");
		queryStr.append(" and model.insertedWebUser.userId in (3930,7394) ");
		queryStr.append(" group by model.userAddress.district.districtId,model.dataSourceType,model.insertedWebUser.userId ");	
		Query query = getSession().createQuery(queryStr.toString());
		if(fromDate != null){
		   query.setDate("fromDate", fromDate);
		}
		if(toDate != null){
		  query.setDate("toDate", toDate);
		}
		if(constiIds != null && constiIds.size() > 0)
			query.setParameterList("constiIds", constiIds);
		if(districtIds != null && districtIds.size() > 0)
			query.setParameterList("districtIds", districtIds);
		return query.list();
	}
	public List<Object[]> getNewlyRegisterCadreInfo1(List<Long> constiIds,List<Long> districtIds){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
		
		queryStr.append(" and model.previousEnrollmentNo is null"); 	
		if(constiIds != null && constiIds.size() > 0)
			queryStr.append(" and  model.userAddress.constituency.constituencyId in (:constiIds)");
			if(districtIds != null && districtIds.size() > 0)
			queryStr.append(" and  model.userAddress.district.districtId in (:districtIds)");
			queryStr.append(" group by model.userAddress.district.districtId ");
		Query query = getSession().createQuery(queryStr.toString());
		if(constiIds != null && constiIds.size() > 0)
			query.setParameterList("constiIds", constiIds);
		if(districtIds != null && districtIds.size() > 0)
			query.setParameterList("districtIds", districtIds);
		return query.list();
	}
	public Long getWorkStartedConstituencyCount1(String state,List<Long> constituencyIds){
        StringBuilder str = new StringBuilder();
		
		str.append("select count(distinct model.userAddress.constituency.constituencyId)  " +
				"from TdpCadre model where model.enrollmentYear = 2014 and ");
		if(state.equalsIgnoreCase("TS"))
		{
			str.append("  model.userAddress.district.districtId <= 10 ");
		}
		
		if(state.equalsIgnoreCase("AP"))
		{
			str.append("  model.userAddress.district.districtId >= 11 ");
		}
				
		str.append(" and model.userAddress.state.stateId = 1  and model.isDeleted = 'N'  ");
		if(constituencyIds != null && constituencyIds.size() > 0)
		str.append(" and  model.userAddress.constituency.constituencyId in(:constituencyIds)  ");
		
		Query query = getSession().createQuery(str.toString());
		if(constituencyIds != null && constituencyIds.size() > 0)
			query.setParameterList("constituencyIds", constituencyIds);
		return (Long) query.uniqueResult();
	}
	
	public Long getWorkStartedConstituencyYearCount1(Long year,String state,Date fromDate, Date toDate,List<Long> constituencyIds){
		StringBuilder str = new StringBuilder();
		str.append("select count(model.tdpCadreId) from TdpCadre model where model.enrollmentYear = :year and model.isDeleted = 'N' ");
		
		if(state.equalsIgnoreCase("TS"))
			str.append(" and model.userAddress.constituency.district.districtId between 1 and 10 ");
		
		if(state.equalsIgnoreCase("AP"))
			str.append(" and model.userAddress.constituency.district.districtId between 11 and 23 ");
		
		if(fromDate != null && toDate != null)
			str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  " );
			
		str.append(" and model.userAddress.state.stateId = 1 ");
		
		if(constituencyIds != null && constituencyIds.size() > 0)
			str.append(" and  model.userAddress.constituency.constituencyId in(:constituencyIds)  ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("year", year);
		
		if(fromDate != null && toDate != null)
		{
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(constituencyIds != null && constituencyIds.size() > 0)
			query.setParameterList("constituencyIds", constituencyIds);
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getRecentlyRegisteredCadresByConstituencies(Integer startIndex,Integer maxIndex,List<Long> constituencyIds){
		//0 first name ,1 lastname,2 constituency ,3 localArea, 4 image
		Query query = getSession().createQuery("select model.firstname,model.lastname,model.userAddress.constituency.name,model.userAddress.localArea,model.image from TdpCadre model where model.isDeleted = 'N' and model.userAddress.constituency.constituencyId in (:constituencyIds) and model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 " +
		" order by model.surveyTime desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
		}
	
	


	public List<Object[]> getCandidateDataCollected(Date fromDate,Date toDate, List<Long> userIds){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.insertedBy.cadreSurveyUserId," +
				" count(model.tdpCadreId)," +
				" model.userAddress.constituency.constituencyId," +
				" model.userAddress.constituency.name" +
				" from TdpCadre model " +
				" where model.enrollmentYear = 2014  " +
				" and model.dataSourceType ='TAB'  " +
				" and model.isDeleted = 'N' " +
				" and date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate  " +
				" and model.insertedBy.cadreSurveyUserId in(:userIds)");
		
		queryStr.append(" group by model.insertedBy.cadreSurveyUserId " +
				" order by date(model.surveyTime),model.insertedBy.userName ");
		Query query = getSession().createQuery(queryStr.toString());
		
		/*Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");*/
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameterList("userIds", userIds);
		
		return query.list();
	}
	
	
	public List<Object[]> getCandidateDataCollectedWeb(Date fromDate,Date toDate, List<Long> userIds){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.insertedWebUser.userId," +
				" count(model.tdpCadreId)," +
				" model.userAddress.constituency.constituencyId," +
				" model.userAddress.constituency.name" +
				" from TdpCadre model " +
				" where model.enrollmentYear = 2014  " +
				" and model.dataSourceType ='WEB'  " +
				" and model.isDeleted = 'N' " +
				" and date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate  " +
				" and model.insertedWebUser.userId in(:userIds) ");
				//"and model.insertedWebUser.userId != 3930");
		
		queryStr.append(" group by model.insertedWebUser.userId " +
				" order by date(model.surveyTime),model.insertedWebUser.firstName ");
		Query query = getSession().createQuery(queryStr.toString());
		
		/*Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");*/
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameterList("userIds", userIds);
		
		return query.list();
	}
	
	public List<Object[]> getCandidateDataCollectedWebParty(Date fromDate,Date toDate, Long userId){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.insertedWebUser.userId," +
				" count(model.tdpCadreId)," +
				" model.userAddress.constituency.constituencyId," +
				" model.userAddress.constituency.name" +
				" from TdpCadre model " +
				" where model.enrollmentYear = 2014  " +
				" and model.dataSourceType ='WEB'  " +
				" and model.isDeleted = 'N' " +
				" and date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate  " +
				" and model.insertedWebUser.userId  = :userId");
		
		queryStr.append(" group by model.insertedWebUser.userId " +
				" order by date(model.surveyTime),model.insertedWebUser.firstName ");
		Query query = getSession().createQuery(queryStr.toString());
		
		/*Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");*/
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Object[]> getCandidateDataCollectedByDate(Date fromDate,Date toDate, List<Long> userIds){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.insertedBy.cadreSurveyUserId," +
				" count(model.tdpCadreId)," +
				" model.userAddress.constituency.constituencyId," +
				" model.userAddress.constituency.name ,date(model.surveyTime) " +
				" from TdpCadre model " +
				" where model.enrollmentYear = 2014  " +
				" and model.dataSourceType ='TAB'  " +
				" and model.isDeleted = 'N' " +
				" and date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate  " +
				" and model.insertedBy.cadreSurveyUserId in(:userIds)");
		
		queryStr.append(" group by model.insertedBy.cadreSurveyUserId,date(model.surveyTime) " +
				" order by date(model.surveyTime),model.insertedBy.userName ");
		Query query = getSession().createQuery(queryStr.toString());
		
		/*Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");*/
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameterList("userIds", userIds);
		
		return query.list();
	}
	
	public List<Object[]> getCandidateDataCollectedByDateWeb(Date fromDate,Date toDate, List<Long> userIds){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.insertedWebUser.userId," +
				" count(model.tdpCadreId)," +
				" model.userAddress.constituency.constituencyId," +
				" model.userAddress.constituency.name ,date(model.surveyTime) " +
				" from TdpCadre model " +
				" where model.enrollmentYear = 2014  " +
				" and model.dataSourceType ='WEB'  " +
				" and model.isDeleted = 'N' " +
				" and date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate  " +
				" and model.insertedWebUser.userId in(:userIds) and model.insertedWebUser.userId != 3930");
		
		queryStr.append(" group by model.insertedWebUser.userId,date(model.surveyTime) " +
				" order by date(model.surveyTime),model.insertedWebUser.firstName ");
		Query query = getSession().createQuery(queryStr.toString());
		
		/*Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");*/
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameterList("userIds", userIds);
		
		return query.list();
	}
	
	
	public List<Object[]> getCandidateDataCollectedByDateWebParty(Date fromDate,Date toDate, Long userId){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select model.insertedWebUser.userId," +
				" count(model.tdpCadreId)," +
				" model.userAddress.constituency.constituencyId," +
				" model.userAddress.constituency.name ,date(model.surveyTime) " +
				" from TdpCadre model " +
				" where model.enrollmentYear = 2014  " +
				" and model.dataSourceType ='WEB'  " +
				" and model.isDeleted = 'N' " +
				" and date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate  " +
				" and model.insertedWebUser.userId  = :userId");
		
		queryStr.append(" group by model.insertedWebUser.userId,date(model.surveyTime) " +
				" order by date(model.surveyTime),model.insertedWebUser.firstName ");
		Query query = getSession().createQuery(queryStr.toString());
		
		/*Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");*/
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		query.setParameter("userId", userId);
		
		return query.list();
	}
	
	public List<Object[]> getUserBetweenDates(Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery("select distinct model.insertedBy.cadreSurveyUserId,model.insertedBy.userName,model.insertedBy.name,model.insertedBy.mobileNo  from TdpCadre model  where date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate and model.insertedBy.cadreSurveyUserId is not null and model.isDeleted = 'N' ");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public List<Object[]> getUserBetweenDatesForWeb(Date fromDate,Date toDate)
	{
		Query query = getSession().createQuery("select distinct model.insertedWebUser.userId,model.insertedWebUser.userName,model.insertedWebUser.firstName,model.insertedWebUser.mobile  from TdpCadre model  where date(model.surveyTime) >=:fromDate " +
				" and date(model.surveyTime) <=:toDate and model.insertedWebUser.userId is not null   and model.isDeleted = 'N'");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}

	public List<Object[]> getLocationWiseUsersDetails(List<Long> locationIdsList,Date fromDate, Date toDate,String queryString)
	{
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("locationIdsList", locationIdsList);
		
		if(fromDate != null && toDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getCandidateListBySearchType(List<Long> locationIdsList,Date fromDate, Date toDate,String queryString)
	{
		Query query = getSession().createQuery(queryString.toString());
		query.setParameterList("locationIdsList", locationIdsList);
		
		if(fromDate != null && toDate != null)
		{
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		
		return query.list();
	}
	
	public List<Object[]> getTotalRecordsDayWise(List<String> sourceTypes){
		Query query = getSession().createQuery(" select count(model.tdpCadreId)," +
				" date(model.surveyTime) from TdpCadre model" +
				" where model.isDeleted = :deleteStatus " +
				" and model.dataSourceType in (:sourceTypes)" +
				" and model.enrollmentYear = 2014 " +
				" group by date(model.surveyTime)" +
				" order by date(model.surveyTime) asc");
		
		query.setParameter("deleteStatus", "N");
		query.setParameterList("sourceTypes", sourceTypes);
		
		return query.list();
	}
	
	public Long getWorkingMembersCountOfAccessLevel(Date date,List<Long> constiIds){
		Query query = getSession().createQuery("select count(distinct model.insertedBy.cadreSurveyUserId) from TdpCadre model " +
				" where model.enrollmentYear = 2014 and " +
				" model.isDeleted = 'N' and" +
				" model.dataSourceType='TAB' and " +
				" date(model.surveyTime) =:date and " +
				" model.insertedBy.cadreSurveyUserId is not null" +
				" and model.userAddress.constituency.constituencyId in (:constiIds)");
		
		query.setDate("date", date);
		query.setParameterList("constiIds", constiIds);
		return (Long)query.uniqueResult();
	}
	public Long getWorkingMembersCountOfAccessLevelForWeb(Date date,List<Long> constiIds){
		Query query = getSession().createQuery("select count(distinct  model.insertedWebUser.userId) from TdpCadre model " +
				" where model.enrollmentYear = 2014 and " +
				" model.isDeleted = 'N' and" +
				" model.dataSourceType='WEB' and " +
				" date(model.surveyTime) =:date and " +
				"  model.insertedWebUser.userId is not null" +
				" and model.userAddress.constituency.constituencyId in (:constiIds)");
		
		query.setDate("date", date);
		query.setParameterList("constiIds", constiIds);
		return (Long)query.uniqueResult();
	}
	public Long getLastHoursWorkingMemberCountOfAccessLevel(Date presentDate, Date lastHours,List<Long> constiIds){
		Query query = getSession().createQuery("select " +
				" count(distinct model.insertedBy.cadreSurveyUserId) from TdpCadre model " +
				" where model.enrollmentYear = 2014 and " +
				" model.isDeleted = 'N' and" +
				" model.dataSourceType='TAB' and" +
				" (model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and" +
				" model.insertedBy.cadreSurveyUserId is not null" +
				" and model.userAddress.constituency.constituencyId in (:constiIds)");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		query.setParameterList("constiIds", constiIds);
		
		return (Long)query.uniqueResult();
	}
	public Long getLastHoursWorkingMemberCountOfAccessLevelForWeb(Date presentDate, Date lastHours,List<Long> constiIds){
		Query query = getSession().createQuery("select " +
				" count(distinct model.insertedWebUser.userId) from TdpCadre model " +
				" where model.enrollmentYear = 2014 and " +
				" model.isDeleted = 'N' and" +
				" model.dataSourceType='WEB' and" +
				" (model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and" +
				" model.insertedWebUser.userId is not null" +
				" and model.userAddress.constituency.constituencyId in (:constiIds)");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		query.setParameterList("constiIds", constiIds);
		
		return (Long)query.uniqueResult();
	}



public List<Long> getCadreSurveyUsersStartedByLocation(List<Long> assignedUsersList,Date date)
{
	/*select distinct csu.cadre_survey_user_id
	from tdp_cadre tc,cadre_survey_user csu
	where tc.created_by=csu.cadre_survey_user_id and 
	created_by  in(1672,1673,1674,1675,1676) and date(survey_time)='2014-11-05' ;#1673,1675,1676*/
	
  Query query=getSession().createQuery("select distinct model.insertedBy.cadreSurveyUserId from   TdpCadre  model " +
  		" where model.insertedBy.cadreSurveyUserId in(:cadreSurveyUserIds) and " +
  		" date(model.surveyTime)=:surveyTime ");	
  query.setParameterList("cadreSurveyUserIds", assignedUsersList);
  query.setParameter("surveyTime", date);
  return query.list();
}

	
	/*public Integer saveRuralConstituencyDataType1(String prevDate,String table,List<Long> distIds,Long limit)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append(" insert into "+table+" (voter_name,voter_id_card_number,panchayat_name,mandal_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path) " );
		queryString.append(" select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no,P.name_local as VILLAGE,T.name_local as MANDAl ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'R' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg' ) ");
		queryString.append(" from   tdp_cadre TDP,   panchayat P,   constituency C,   tehsil T,   district D,   user_address UA,		voter_names V,voter VV  ,booth_publication_voter BPV , booth B	where  TDP.address_id = UA.user_address_id and UA.constituency_id = C.constituency_id  and UA.panchayat_id = P.panchayat_id  and UA.district_id = D.district_id  and UA.tehsil_id = T.tehsil_id and TDP.voter_id = V.voter_id ");
		queryString.append(" and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL'  and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11  ");
		//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
		
		if(distIds != null)
		{
			queryString.append("  and D.district_id in (:distIds) " );
		}
		
		queryString.append(" LIMIT 0, "+limit+" " );
		Query query = getSession().createSQLQuery(queryString.toString());
		//query.setParameter("prevDate", prevDate);
		if(distIds != null)
		{
			query.setParameterList("distIds", distIds);
		}
		Integer c = query.executeUpdate();
		return c;
	}
	
	public Integer saveRuralUrbanConstituencyDataType2(String prevDate,String table,List<Long> distIds,Long limit)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append(" insert into "+table+"  (voter_name,voter_id_card_number,panchayat_name,mandal_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path) " );
		queryString.append(" select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no,P.name_local as VILLAGE,T.name_local as MANDAl ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'R' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg' ) ");
		queryString.append(" from   tdp_cadre TDP,   panchayat P,   constituency C,   tehsil T,   district D, user_address UA,voter_names V,voter VV   ,booth_publication_voter BPV , booth B 	where  TDP.address_id = UA.user_address_id and UA.constituency_id = C.constituency_id  and UA.panchayat_id = P.panchayat_id  and UA.district_id = D.district_id  and UA.tehsil_id = T.tehsil_id and TDP.voter_id = V.voter_id ");
		queryString.append(" and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL-URBAN' and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11   ");
		//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
		
		if(distIds != null)
		{
			queryString.append("  and D.district_id in (:distIds) " );
		}
		
		queryString.append(" LIMIT 0, "+limit+" " );
		Query query = getSession().createSQLQuery(queryString.toString());
		//query.setParameter("prevDate", prevDate);
		if(distIds != null)
		{
			query.setParameterList("distIds", distIds);
		}
		Integer c = query.executeUpdate();
		return c;
	}
	
	public Integer saveRuralUrbanConstituencyDataType(String prevDate,String table,List<Long> distIds,Long limit)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("  insert into "+table+"  (voter_name,voter_id_card_number,muncipality_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path) ");
		queryString.append("  select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no, LEB.name_local as MUNCIPALITYNAME ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'RU' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg') ");
		queryString.append("  from     tdp_cadre TDP, local_election_body LEB,   voter_names V,  voter VV,  constituency C,  district D, user_address UA   ,booth_publication_voter BPV , booth B	where TDP.address_id = UA.user_address_id and UA.local_election_body = LEB.local_election_body_id  and UA.constituency_id = C.constituency_id   and UA.district_id = D.district_id " );
		queryString.append("  and TDP.voter_id = V.voter_id  and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL-URBAN' and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11  ");
		//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
		
		if(distIds != null)
		{
			queryString.append("  and D.district_id in (:distIds) " );
		}
		
		queryString.append(" LIMIT 0, "+limit+" " );
		Query query = getSession().createSQLQuery(queryString.toString());
		//query.setParameter("prevDate", prevDate);
		if(distIds != null)
		{
			query.setParameterList("distIds", distIds);
		}
		
		queryString.append(" LIMIT 0, "+limit+" " );
		Integer c = query.executeUpdate();
		return c;
	}
	
	public Integer saveUrbanConstituencyDataType1(String prevDate,String table,List<Long> distIds,Long limit)
	{
		StringBuffer queryString = new StringBuffer();
		queryString.append("  insert into "+table+"  (voter_name,voter_id_card_number,muncipality_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path) ");
		queryString.append("  select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no, LEB.name_local as MUNCIPALITYNAME ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'U' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg')");
		queryString.append("  from     tdp_cadre TDP, local_election_body LEB,   voter_names V,  voter VV,  constituency C,  district D, user_address UA  ,booth_publication_voter BPV , booth B	where TDP.address_id = UA.user_address_id and UA.local_election_body = LEB.local_election_body_id  and UA.constituency_id = C.constituency_id   and UA.district_id = D.district_id " );
		queryString.append("  and TDP.voter_id = V.voter_id  and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'URBAN'  and   BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11  ");
		//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
		
		if(distIds != null)
		{
			queryString.append("  and D.district_id in (:distIds) " );
		}
		queryString.append(" LIMIT 0, "+limit+" " );
		Query query = getSession().createSQLQuery(queryString.toString());
		//query.setParameter("prevDate", prevDate);
		if(distIds != null)
		{
			query.setParameterList("distIds", distIds);
		}
		Integer c = query.executeUpdate();
		return c;
	}*/

public Integer saveRuralConstituencyDataType1(String prevDate,String table,List<Long> constIds,Long limit)
{
	StringBuffer queryString = new StringBuffer();
	queryString.append(" insert into "+table+" (voter_name,voter_id_card_number,panchayat_name,mandal_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time,part_no,area_covered,house_no,mobile_no) " );
	queryString.append(" select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no,P.name_local as VILLAGE,T.name_local as MANDAl ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,membership_id  AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'R' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg' ),DATE_ADD(now(),INTERVAL 330 MINUTE),B.part_no,B.village_covered,TDP.house_no,TDP.mobile_no ");
	queryString.append(" from   tdp_cadre TDP,   panchayat P,   constituency C,   tehsil T,   district D,   user_address UA,		voter_names V,voter VV  ,booth_publication_voter BPV , booth B	where  TDP.address_id = UA.user_address_id and UA.constituency_id = C.constituency_id  and UA.panchayat_id = P.panchayat_id  and UA.district_id = D.district_id  and UA.tehsil_id = T.tehsil_id and TDP.voter_id = V.voter_id ");
	queryString.append(" and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL'  and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and TDP.constituency_id is null and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and (TDP.inserted_web_user_id != 3930 or TDP.inserted_web_user_id is null ) and TDP.data_source_type != 'ONLINE'  ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.district_id in (:constIds) " );
	}
	queryString.append(" LIMIT 0, "+limit+" " );
	Query query = getSession().createSQLQuery(queryString.toString());
	//query.setParameter("prevDate", prevDate);
	if(constIds != null)
	{
		query.setParameterList("constIds", constIds);
	}
	Integer c = query.executeUpdate();
	return c;
}

public Integer saveRuralUrbanConstituencyDataType2(String prevDate,String table,List<Long> constIds,Long limit)
{
	StringBuffer queryString = new StringBuffer();
	queryString.append(" insert into "+table+"  (voter_name,voter_id_card_number,panchayat_name,mandal_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time,part_no,area_covered,house_no,mobile_no) " );
	queryString.append(" select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no,P.name_local as VILLAGE,T.name_local as MANDAl ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,membership_id AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'R' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg' ) ,DATE_ADD(now(),INTERVAL 330 MINUTE),B.part_no,B.village_covered,TDP.house_no,TDP.mobile_no  ");
	queryString.append(" from   tdp_cadre TDP,   panchayat P,   constituency C,   tehsil T,   district D, user_address UA,voter_names V,voter VV   ,booth_publication_voter BPV , booth B 	where  TDP.address_id = UA.user_address_id and UA.constituency_id = C.constituency_id  and UA.panchayat_id = P.panchayat_id  and UA.district_id = D.district_id  and UA.tehsil_id = T.tehsil_id and TDP.voter_id = V.voter_id ");
	queryString.append(" and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL-URBAN' and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = "+IConstants.VOTER_DATA_PUBLICATION_ID+"   and TDP.constituency_id is null  and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and (TDP.inserted_web_user_id != 3930 or TDP.inserted_web_user_id is null ) and TDP.data_source_type != 'ONLINE'  ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.district_id in (:constIds) " );
	}
	queryString.append(" LIMIT 0, "+limit+" " );
	Query query = getSession().createSQLQuery(queryString.toString());
	//query.setParameter("prevDate", prevDate);
	if(constIds != null)
	{
		query.setParameterList("constIds", constIds);
	}
	Integer c = query.executeUpdate();
	return c;
}

public Integer saveRuralUrbanConstituencyDataType(String prevDate,String table,List<Long> constIds,Long limit)
{
	StringBuffer queryString = new StringBuffer();
	queryString.append("  insert into "+table+"  (voter_name,voter_id_card_number,muncipality_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time,part_no,area_covered,house_no,mobile_no) ");
	queryString.append("  select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no, LEB.name_local as MUNCIPALITYNAME ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,membership_id AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'RU' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg'),DATE_ADD(now(),INTERVAL 330 MINUTE),B.part_no,B.village_covered,TDP.house_no,TDP.mobile_no  ");
	queryString.append("  from     tdp_cadre TDP, local_election_body LEB,   voter_names V,  voter VV,  constituency C,  district D, user_address UA   ,booth_publication_voter BPV , booth B	where TDP.address_id = UA.user_address_id and UA.local_election_body = LEB.local_election_body_id  and UA.constituency_id = C.constituency_id   and UA.district_id = D.district_id " );
	queryString.append("  and TDP.voter_id = V.voter_id  and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL-URBAN' and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and TDP.constituency_id is null and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and (TDP.inserted_web_user_id != 3930 or TDP.inserted_web_user_id is null ) and TDP.data_source_type != 'ONLINE'  ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.district_id  in (:constIds) " );
	}
	queryString.append(" LIMIT 0, "+limit+" " );
	Query query = getSession().createSQLQuery(queryString.toString());
	//query.setParameter("prevDate", prevDate);
	if(constIds != null)
	{
		query.setParameterList("constIds", constIds);
	}

	Integer c = query.executeUpdate();
	return c;
}

public Integer saveUrbanConstituencyDataType1(String prevDate,String table,List<Long> constIds,Long limit)
{
	StringBuffer queryString = new StringBuffer();
	queryString.append("  insert into "+table+"  (voter_name,voter_id_card_number,muncipality_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time,part_no,area_covered,house_no,mobile_no) ");
	queryString.append("  select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no, LEB.name_local as MUNCIPALITYNAME ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,membership_id AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'U' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg'),DATE_ADD(now(),INTERVAL 330 MINUTE) ,B.part_no,B.village_covered,TDP.house_no ,TDP.mobile_no");
	queryString.append("  from     tdp_cadre TDP, local_election_body LEB,   voter_names V,  voter VV,  constituency C,  district D, user_address UA  ,booth_publication_voter BPV , booth B	where TDP.address_id = UA.user_address_id and UA.local_election_body = LEB.local_election_body_id  and UA.constituency_id = C.constituency_id   and UA.district_id = D.district_id " );
	queryString.append("  and TDP.voter_id = V.voter_id  and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'URBAN'  and   BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = "+IConstants.VOTER_DATA_PUBLICATION_ID+" and TDP.constituency_id is null and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and (TDP.inserted_web_user_id != 3930 or TDP.inserted_web_user_id is null ) and TDP.data_source_type != 'ONLINE'  ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.district_id in (:constIds) " );
	}
	queryString.append(" LIMIT 0, "+limit+" " );
	Query query = getSession().createSQLQuery(queryString.toString());
	//query.setParameter("prevDate", prevDate);
	if(constIds != null)
	{
		query.setParameterList("constIds", constIds);
	}
	Integer c = query.executeUpdate();
	return c;
}

public Integer insertPrintCompanyType(Long id,String table)
{
	Query query = getSession().createSQLQuery("update tdp_cadre set constituency_id = "+id+" where tdp_cadre_id in (select tdp_cadre_id from "+table+" where serial_number is null) and constituency_id is null and enrollment_year = 2014 ");
	Integer count = query.executeUpdate();
	return count;
}

public void flushAndclearSession(){
	getSession().flush();
	getSession().clear();
}
	//0 userId,constiId,time
	public List<Object[]> getAnalysisData(String reqDate){
		Query query = getSession().createQuery("select model.insertedBy.cadreSurveyUserId,model.userAddress.constituency.constituencyId,model.surveyTime from TdpCadre model" +
				" where model.enrollmentYear = 2014 and date(model.surveyTime)= '"+reqDate+"' and  model.isDeleted = 'N' and  model.dataSourceType='TAB' order by model.userAddress.constituency.constituencyId,model.updatedBy.cadreSurveyUserId,model.surveyTime ");
		
		return query.list();
	}
	
	public List<Object[]> getUserData(){
		//0 constiId,1constiName,2userId,3name,4userName,5 tab,6 mobile
		Query query = getSession().createQuery("select model.constituency.constituencyId,model.constituency.name,model.cadreSurveyUser.cadreSurveyUserId,model.cadreSurveyUser.name,model.cadreSurveyUser.userName,model.tabNo " +
				",model.cadreSurveyUser.mobileNo from CadreSurveyUserAssignDetails model");
		return query.list();
	}
	public List<Object[]> getCandidateDataCollectionInfo1(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType){
		//0 count,1 name,2 min,3 max,4 date,5 id

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),min(model.surveyTime),max(model.surveyTime),date(model.surveyTime)");
		if(sourceType.equalsIgnoreCase("TAB"))
		queryStr.append(" ,model.insertedBy.cadreSurveyUserId,model.insertedBy.userName,model.insertedBy.name ");
		else if(sourceType.equalsIgnoreCase("WEB"))
		queryStr.append(" ,model.insertedWebUser.userId,model.insertedWebUser.firstName,model.insertedWebUser.lastName,model.insertedWebUser.accessType,model.insertedWebUser.accessValue ");

		queryStr.append(" from TdpCadre model where model.enrollmentYear = 2014  " +
		" and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate  ");
		queryStr.append(" and model.dataSourceType = :sourceType  ");
		if(locationType.longValue() == 1l){
		if(locationIds.contains(2l) && locationIds.contains(1l)){
		queryStr.append(" and model.userAddress.state.stateId= 1 ");
		}else if(locationIds.contains(2l)){
		queryStr.append(" and model.userAddress.state.stateId= 1  and model.userAddress.district.districtId < 11 ");
		}else if(locationIds.contains(1l)){
		queryStr.append(" and model.userAddress.state.stateId= 1  and model.userAddress.district.districtId > 10 ");
		}
		}
		else if(locationType.longValue() == 2l){
		queryStr.append(" and model.userAddress.district.districtId in(:locationIds) ");
		}
		else if(locationType.longValue() == 3l){
		queryStr.append(" and model.userAddress.constituency.constituencyId in(:locationIds)  ");
		}
		if(sourceType.equalsIgnoreCase("TAB"))
		queryStr.append(" group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName ");
		if(sourceType.equalsIgnoreCase("WEB"))
		queryStr.append(" group by date(model.surveyTime),model.insertedWebUser.userId order by date(model.surveyTime),model.insertedWebUser.firstName ");
		if(sourceType.equalsIgnoreCase("ONLINE"))
		queryStr.append(" group by date(model.surveyTime) order by date(model.surveyTime)"); 
		Query query = getSession().createQuery(queryStr.toString());
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		if(locationType.longValue() != 0l && locationType.longValue() != 1l){
		query.setParameterList("locationIds", locationIds);
		}
		query.setParameter("sourceType", sourceType);
		return query.list();
		}
	
	public List<Object[]> getWebUserConstituecny(List<Long> webUserIds)
	{
		Query query = getSession().createQuery("select distinct model.insertedWebUser.userId , model.userAddress.constituency.name from TdpCadre model where model.insertedWebUser.userId in (:webUserIds)");
		query.setParameterList("webUserIds", webUserIds);
		return query.list();
	}
	
	public List<Object[]> getCandidateDataCollectionInfoForOnline(Long locationType,List<Long> locationIds,Date fromDate,Date toDate,String sourceType,String queryStr)
	{
		
		Query query = getSession().createQuery(queryStr.toString());
		
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);		
		query.setParameterList("locationIds", locationIds);
		query.setParameter("sourceType", sourceType);
		
		return query.list();
		}
	public List<Object[]> getTotalRecords(List<Long> districtIds,String type,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY) || type.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId"); 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "); 
		 if(fromDate != null && toDate != null){
		   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
		 }
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) and model.userAddress.localElectionBody.localElectionBodyId is null group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY) || type.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) )
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}
	
	public List<String> getCardNumbers(String query,Long constiId,String mobileNo,String trNo,Date surveyDate){
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.memberShipNo from TdpCadre model " +
				" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is not null ");
		str.append( " and model.insertedWebUser.userId = 3930  and model.cardNumber is  null  and model.insertedWebUser.userId is not null " );
		str.append(query);
		str.append( " order by date(model.surveyTime)" );
		
		Query qry = getSession().createQuery(str.toString());
		
		if(constiId!=null){
			qry.setParameter("constituencyId", constiId);
		}
		if(mobileNo!=null && mobileNo.trim().length()>0){
			qry.setParameter("mobileNo", mobileNo);
		}
		if(trNo!=null && trNo.trim().length()>0){
			qry.setParameter("trNo", trNo);
		}
		if(surveyDate!=null){
			qry.setDate("surveyDate", surveyDate);
		}
		
		return qry.list();
	}
	
	public List<String> getCardNumbersForNonVoters(String query,Long constiId,String mobileNo,String trNo,Date surveyDate){
		StringBuilder str = new StringBuilder();
		
		str.append(" select model.memberShipNo from TdpCadre model " +
				" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is null ");
		str.append( " and model.insertedWebUser.userId = 3930  and model.cardNumber is  null  and model.insertedWebUser.userId is not null " );
		str.append(query);
		str.append( " order by date(model.surveyTime)" );
		
		Query qry = getSession().createQuery(str.toString());
		
		if(constiId!=null){
			qry.setParameter("constituencyId", constiId);
		}
		if(mobileNo!=null && mobileNo.trim().length()>0){
			qry.setParameter("mobileNo", mobileNo);
		}
		if(trNo!=null && trNo.trim().length()>0){
			qry.setParameter("trNo", trNo);
		}
		if(surveyDate!=null){
			qry.setDate("surveyDate", surveyDate);
		}
		
		return qry.list();
	}
	
	public List<Object[]> getCadreDetailsByMemberShipId(List<String> memberCardNos)	{
		Query query = getSession().createQuery("select model.memberShipNo , " +
				" model.voterId," +
				" model.firstname," +
				" model.relativename," +
				" model.voter.voterId," +
				" model.voter.voterIDCardNo,model.dataSourceType,model.tdpCadreId,model.refNo,model.mobileNo,model.photoType,model.image," +
				" model.userAddress.userAddressId,model.cardNumber from TdpCadre model " +
				" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
		query.setParameterList("memberCardNos", memberCardNos);
		return query.list();
	}
	
	public List<Object[]> getOtherStateCadreDetailsByMemberShipId(List<String> memberCardNos)	{
		Query query = getSession().createQuery("select model.memberShipNo , " +
				" model.voterId," +
				" model.firstname," +
				" model.relativename," +
				" model.voter.voterId," +
				" model.voter.voterIDCardNo,model.dataSourceType,model.tdpCadreId,model.refNo,model.mobileNo,model.photoType,model.image," +
				" model.userAddress.userAddressId,model.cardNumber from TdpCadre model " +
				" where model.memberShipNo in (:memberCardNos) and model.isDeleted = 'N'");
		query.setParameterList("memberCardNos", memberCardNos);
		return query.list();
	}
	
	public List<Long> lastHoursActiveUsers(Date presentTime,Date lastHoursTime,List<Long> consituencyIdsList)
	{
		Query query = getSession().createQuery("select " +
				" distinct model.insertedBy.cadreSurveyUserId from TdpCadre model, CadreSurveyUserAssignDetails model2 " +
				" where model.enrollmentYear = 2014 and " +
				" model.isDeleted = 'N' and " +
				" (model.surveyTime >= :lastHoursTime and model.surveyTime <= :presentTime) and model.insertedBy.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId and " +
				" model.insertedBy.cadreSurveyUserId is not null and " +
				" model.dataSourceType='TAB' and model2.constituency.constituencyId in (:consituencyIdsList) " 
				);
		
		query.setParameter("lastHoursTime", lastHoursTime);
		query.setParameter("presentTime", presentTime);
		query.setParameterList("consituencyIdsList", consituencyIdsList);
		return query.list();
		
	 }
	public List<Long> inActiveUsersCountInLastHours(Date surveyTime,List<Long> cadreSurveyUserIdsList,List<Long> consituencyIdsList) 
	{
		Query query=getSession().createQuery("" +
				"select count(distinct model.insertedBy.cadreSurveyUserId) " +
				" from TdpCadre model , CadreSurveyUserAssignDetails model2 " +
				" where model.surveyTime>=:surveyTime and" +
				" model.isDeleted='N' and model.enrollmentYear = 2014 and " +
				" model.insertedBy.cadreSurveyUserId is not null and " +
				" model.insertedBy.cadreSurveyUserId not in(:cadreSurveyUserIdsList)  and model.insertedBy.cadreSurveyUserId = model2.cadreSurveyUser.cadreSurveyUserId and " +
				" model.dataSourceType='TAB' and model2.constituency.constituencyId in (:consituencyIdsList) ");
		query.setDate("surveyTime",surveyTime);	
		query.setParameterList("cadreSurveyUserIdsList", cadreSurveyUserIdsList);
		query.setParameterList("consituencyIdsList", consituencyIdsList);
		return query.list();	
	}
	
	
	 
		public List<Object[]> getDaywiseWebuserDetailsByUserANDType(Long userId, Date fromDate,Date toDate,String type,Long memberTypeId)
		{
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select date(model.surveyTime), count(model.tdpCadreId) from TdpCadre model where model.isDeleted='N'  and ");
			queryStr.append(" model.insertedWebUserId = :userId and model.dataSourceType='"+type+"' ");
			
			if(fromDate != null && toDate != null)
			{
				queryStr.append(" and ( date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  )  ");
			}
			
			if(memberTypeId !=null && memberTypeId.longValue() > 1l)
			{
				queryStr.append(" and model.tdpMemberTypeId=:memberTypeId and model.enrollmentYear = "+IConstants.UNIONS_REGISTRATION_YEAR+" ");
			}
			else{
				queryStr.append(" and model.enrollmentYear = 2014 ");
			}
			queryStr.append(" group by date(model.surveyTime) order by date(model.surveyTime) desc ");
			
			Query query=getSession().createQuery(queryStr.toString());
			
			if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate",fromDate);	
				query.setDate("toDate",toDate);	
			}
			if(memberTypeId !=null && memberTypeId.longValue() > 1l)
			{
				query.setParameter("memberTypeId",memberTypeId);	
			}
			
			query.setParameter("userId",userId);	
			
			return query.list();
		}
		
	public List<Object[]> inActiveUsersInLastHours(Date surveyTime,List<Long> cadreSurveyUserIdsList,List<Long> constituencyIds)
	{
		Query query=getSession().createQuery("" +
				"select distinct model.insertedBy.userName,model.insertedBy.name,model.insertedBy.mobileNo,model1.tabNo ,model1.constituency.constituencyId, model1.constituency.name, model1.constituency.district.districtName " +
				" from TdpCadre model,CadreSurveyUserAssignDetails model1 " +
				" where model.insertedBy.cadreSurveyUserId=model1.cadreSurveyUser.cadreSurveyUserId and" +
				" model.surveyTime>=:surveyTime and" +
				" model.isDeleted='N' and model.enrollmentYear = 2014 and " +
				" model.insertedBy.cadreSurveyUserId is not null and " +
				" model.insertedBy.cadreSurveyUserId not in(:cadreSurveyUserIdsList) and " +
				" model.dataSourceType='TAB' and model1.constituency.constituencyId in (:constituencyIds) ");
		query.setDate("surveyTime",surveyTime);	
		query.setParameterList("cadreSurveyUserIdsList", cadreSurveyUserIdsList);
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();	
	}
   
	public int getUpdateVoterAndMemNo(Long voterId , String memberShipNumber)
	{
		Query query = getSession().createQuery("");
		
		int c = query.executeUpdate();
		return c;
	}
	
	public List<Object[]> getTotalRecords(List<Long> districtIds,String type,Date date){
		StringBuilder str = new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId");
		
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "+
				" and date(model.surveyTime)=:date "); 
		 
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
		 
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("districtIds", districtIds);
		query.setParameter("date", date);
		
		
		return query.list();
	}
	
	public List<Object[]> getTotalRecordsUnderDate(List<Long> districtIds,String type,Date date){
		StringBuilder str = new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId");
		
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "+
				" and date(model.surveyTime)<=:date "); 
		 
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
		 
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("districtIds", districtIds);
		query.setParameter("date", date);
		
		
		return query.list();
	}
	
	
	public List<Object[]> getTotalRecords1(List<Long> ids,String type){
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
		 str.append("select count(model.tdpCadreId),model.userAddress.panchayat.panchayatId,model.userAddress.constituency.constituencyId "); 
		else if(type.equalsIgnoreCase(IConstants.BOOTH))
			 str.append("select count(model.tdpCadreId),model.userAddress.booth.boothId,model.userAddress.constituency.constituencyId "); 
		
		 str.append("  from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 "); 
		 if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
		    str.append(" and model.userAddress.panchayat.panchayatId in(:ids) group by model.userAddress.panchayat.panchayatId ");
		 else if(type.equalsIgnoreCase(IConstants.BOOTH))
			str.append(" and model.userAddress.booth.boothId in(:ids) group by model.userAddress.booth.boothId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("ids", ids);
		
		return query.list();
	}
	
	
	public List<Object[]> getLocationWiseGenderCadreCount(List<Long> Ids,String type){		
		
		StringBuilder queryStr=new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
	    	queryStr.append("select count(model.gender),model.gender,model.userAddress.constituency.constituencyId ");
		else if(type.equalsIgnoreCase(IConstants.DISTRICT))
				queryStr.append("select count(model.gender),model.gender,model.userAddress.district.districtId " );
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			queryStr.append("select count(model.gender),model.gender, model.userAddress.tehsil.tehsilId "); 
	 	else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			queryStr.append("select count(model.gender),model.gender, model.userAddress.localElectionBody.localElectionBodyId "); 
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			queryStr.append("select count(model.gender),model.gender,model.userAddress.panchayat.panchayatId "); 
		else if(type.equalsIgnoreCase(IConstants.BOOTH))
			queryStr.append("select count(model.gender),model.gender, model.userAddress.booth.boothId "); 
		
            queryStr.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.gender is not null");
            
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
		    	queryStr.append(" and model.userAddress.district.districtId in (:Ids) group by model.gender,model.userAddress.constituency.constituencyId ");
		else if(type.equalsIgnoreCase(IConstants.DISTRICT))
				queryStr.append(" and model.userAddress.district.districtId in (:Ids) group by model.gender,model.userAddress.district.districtId  " );
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			queryStr.append(" and model.userAddress.tehsil.tehsilId in (:Ids) and model.userAddress.localElectionBody.localElectionBodyId is null group by model.gender,model.userAddress.tehsil.tehsilId "); 
	 	else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:Ids) group by model.gender,model.userAddress.localElectionBody.localElectionBodyId "); 
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			queryStr.append(" and model.userAddress.panchayat.panchayatId in (:Ids) group by model.gender,model.userAddress.panchayat.panchayatId "); 
		else if(type.equalsIgnoreCase(IConstants.BOOTH))
			queryStr.append(" and model.userAddress.booth.boothId in (:Ids) group by model.gender,model.userAddress.booth.boothId "); 
		
		
		Query query = getSession().createQuery(queryStr.toString());		
		query.setParameterList("Ids", Ids);
				
		return query.list();
    }
	
	public List<Object[]> getLocationWiseAgeRangeCount(List<Long> Ids,String ageRange ,String type){

		StringBuilder queryStr = new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
	    	queryStr.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId ");
		else if(type.equalsIgnoreCase(IConstants.DISTRICT))
				queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId " );
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			queryStr.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId "); 
	 	else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			queryStr.append("select count(model.tdpCadreId), model.userAddress.localElectionBody.localElectionBodyId "); 
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			queryStr.append("select count(model.tdpCadreId),model.userAddress.panchayat.panchayatId "); 
		else if(type.equalsIgnoreCase(IConstants.BOOTH))
			queryStr.append("select count(model.tdpCadreId),model.userAddress.booth.boothId "); 
		
		
		queryStr.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
		
	
		if (ageRange.equals("below 18")) {
			queryStr.append(" and model.age <18 ");
		} else if (ageRange.equals("18-25")) {
			queryStr.append(" and model.age >=18 and model.age<=25 ");
		} else if (ageRange.equals("26-35")) {
			queryStr.append(" and model.age >=26 and model.age<=35 ");
		} else if (ageRange.equals("36-45")) {
			queryStr.append(" and model.age >=36 and model.age<=45 ");
		} else if (ageRange.equals("46-60")) {
			queryStr.append(" and model.age >=46 and model.age<=60 ");
		} else if (ageRange.equals("above 60")) {
			queryStr.append(" and model.age >60 ");
		}
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
	    	queryStr.append(" and model.userAddress.district.districtId in (:Ids) group by model.userAddress.constituency.constituencyId ");
		else if(type.equalsIgnoreCase(IConstants.DISTRICT))
				queryStr.append(" and model.userAddress.district.districtId in (:Ids) group by model.userAddress.district.districtId  " );
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			queryStr.append(" and model.userAddress.tehsil.tehsilId in (:Ids) and model.userAddress.localElectionBody.localElectionBodyId is null group by model.userAddress.tehsil.tehsilId "); 
	 	else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:Ids) group by model.userAddress.localElectionBody.localElectionBodyId "); 
		else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			queryStr.append(" and model.userAddress.panchayat.panchayatId in (:Ids) group by model.userAddress.panchayat.panchayatId "); 
		else if(type.equalsIgnoreCase(IConstants.BOOTH))
			queryStr.append(" and model.userAddress.booth.boothId in (:Ids) group by model.userAddress.booth.boothId "); 
			

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("Ids", Ids);
		return query.list();
	}


	
	
	public List<Object[]> getLocationWiseTotalRecords(List<Long> districtIds,String type){
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId"); 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "); 
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) and model.userAddress.localElectionBody.localElectionBodyId is null group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}
	
	public List<Object[]> getLocationWiseCount(List<Long> ids,String type){
		
		StringBuilder str = new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId "); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId,model.userAddress.constituency.constituencyId "); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.constituency.constituencyId "); 
		 str.append("  from TdpCadre model " +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "); 
		
		 if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.constituencyId in (:ids) group by model.userAddress.constituency.constituencyId ");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:ids) and model.userAddress.localElectionBody.localElectionBodyId is null group by model.userAddress.tehsil.tehsilId ");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in (:ids) group by model.userAddress.localElectionBody.localElectionBodyId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("ids", ids);
		
		return query.list();
		
		
	}
	public List<Object[]> getTdpCadreAgeRangeByConstituency(List<Long> constituencyIds)
	{
	
	 Query query=getSession().createQuery("select model.age,date(model.dateOfBirth),model.userAddress.constituency.name " +
	 		"  from TdpCadre model " +
	 		"  where model.enrollmentYear=2014 and" +
	 		"  model.isDeleted='N' and" +
	 		"  model.userAddress.constituency.constituencyId in(:constituencyIds) " +
	 		"  order by model.userAddress.constituency.name");
	 
	 query.setParameterList("constituencyIds", constituencyIds);
	 return query.list();		 
	
	}
	public List<Object[]> getTdpCadregenderWiseByConstituency(List<Long> constituencyIds)
	{
	 Query query=getSession().createQuery("select model.userAddress.constituency.name,model.gender,count(model.tdpCadreId) " +
	 		"  from TdpCadre model " +
	 		"  where model.enrollmentYear=2014 and" +
	 		"  model.isDeleted='N' and" +
	 		"  model.userAddress.constituency.constituencyId in(:constituencyIds) " +
	 		"  group by model.userAddress.constituency.name,model.gender " +
	 		"  order by model.userAddress.constituency.name");
	 
	 query.setParameterList("constituencyIds", constituencyIds);
	 return query.list();		 
	
	}
	public List<Object[]> getDuplicateUsersInConstituencies(Date startDate,Date endDate,String type)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.userAddress.constituency.constituencyId,model.userAddress.constituency.name");
		if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append(" ,model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		str.append(" ,model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name");
		str.append(" ,count(distinct model.insertedUserId)");
		str.append(" from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "+
				" and date(model.surveyTime)>=:startDate and date(model.surveyTime)<=:endDate");
		  if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId");
		  str.append(" having count(distinct model.insertedUserId) > 1");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();
	}

	
	public List<Object[]> getDuplicateUsersCountInConstituencies(Date startDate,Date endDate,String type,List<Long> Ids)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.userAddress.constituency.constituencyId");
		if(type.equalsIgnoreCase(IConstants.TEHSIL))
		str.append(" ,model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
		str.append(" ,model.userAddress.localElectionBody.localElectionBodyId");
		str.append(" ,count(model.tdpCadreId),model.insertedBy.cadreSurveyUserId,model.insertedBy.userName,model.insertedBy.name,model.insertedBy.mobileNo");
		str.append(" from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "+
				" and date(model.surveyTime)>=:startDate and date(model.surveyTime)<=:endDate");
		  if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:Ids) group by model.userAddress.constituency.constituencyId,model.userAddress.tehsil.tehsilId,model.insertedBy.cadreSurveyUserId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:Ids)  group by model.userAddress.constituency.constituencyId,model.userAddress.localElectionBody.localElectionBodyId,model.insertedBy.cadreSurveyUserId");
		  
		Query query = getSession().createQuery(str.toString());
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameterList("Ids", Ids);
		return query.list();
	}
	
	public List<Object[]> getDuplicateUsersByUserId(Date startDate,Date endDate,Long userId,Long locationId,Long constituencyId,String type)
	{
	
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.tdpCadreId),date(model.surveyTime) from TdpCadre model ");
		str.append( " where model.insertedBy.cadreSurveyUserId = :userId  and model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 ");
		 str.append(" and date(model.surveyTime)>=:startDate and date(model.surveyTime)<=:endDate " );
		if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId =:locationId"); 
		if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.constituency.localElectionBody.localElectionBodyId =:locationId"); 
	
		 str.append(" and model.userAddress.constituency.constituencyId =:constituencyId group by date(model.surveyTime) order by date(model.surveyTime)");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("userId", userId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setParameter("locationId", locationId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}	
	public List<Object[]> getTotalFemaleRecords(List<Long> districtIds,String type,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId"); 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 and (model.gender ='Female' or model.gender ='F') "); 
		 if(fromDate != null && toDate != null){
		   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
		 }
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.constituencyId in(:districtIds) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}
	
	public List<Object[]> getTotalYouthRecords(List<Long> districtIds,String type,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId"); 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 and model.age < 31 "); 
		 if(fromDate != null && toDate != null){
		   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
		 }
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.constituencyId in(:districtIds) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}
	public List<Object[]> gettingRegisteredVotersForConstituencys(List<Long> constituencyIds)
	{
		Query query=getSession().createQuery("select count(model.tdpCadreId),model.userAddress.constituency.name,model.userAddress.constituency.constituencyId " +
				" from TdpCadre model " +
				" where model.userAddress.constituency.constituencyId in(:constituencyIds) and" +
				"  model.enrollmentYear = 2014 and" +
				"  model.isDeleted='N'" +
				"  group by model.userAddress.constituency.constituencyId " +
				"  order by model.userAddress.constituency.name");
        query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	public List<Object[]> gettingRegisteredVotersForDistricts(List<Long> districtsIdsList)
	{
		Query query=getSession().createQuery("select count(model.tdpCadreId), model.userAddress.district.districtName, model.userAddress.district.districtId " +
				" from TdpCadre model " +
				" where model.userAddress.district.districtId in(:districtsIdsList) and" +
				"  model.enrollmentYear = 2014 and" +
				"  model.isDeleted='N'" +
				"  group by model.userAddress.district.districtId " +
				"  order by model.userAddress.district.districtName asc ");
        query.setParameterList("districtsIdsList", districtsIdsList);
		return query.list();
	}
		
	@SuppressWarnings("unchecked")
	public List<Object[]> gettingRegisteredVotersForParliaments(List<Long> parliamentsList)
	{
		Query query=getSession().createQuery("select count(TC.tdpCadreId), DCAD.delimitationConstituency.constituency.name, DCAD.delimitationConstituency.constituency.constituencyId " +
				"  from TdpCadre TC, DelimitationConstituencyAssemblyDetails DCAD " +
				"  where DCAD.delimitationConstituency.year = 2009 and " +
				"  TC.userAddress.constituency.constituencyId = DCAD.constituency.constituencyId and " +
				"  DCAD.delimitationConstituency.constituency.constituencyId in (:parliamentsList) and " +
				"  TC.enrollmentYear = 2014 and " +
				"  TC.isDeleted='N' " +
				"  group by DCAD.delimitationConstituency.constituency.constituencyId " +
				"  order by DCAD.delimitationConstituency.constituency.name asc  ");
        query.setParameterList("parliamentsList", parliamentsList);
		return query.list();
	}
	
	public List<Object[]> getRegisteredCadreCountIn2012(List<Long> constituencyIds){
		
		 Query query=getSession().createQuery("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId from TdpCadre model " +
		 		" where model.isDeleted = 'N'  and model.enrollmentYear = 2012  and model.userAddress.constituency.constituencyId in (:constituencyIds) " +
		 		" group by model.userAddress.constituency.constituencyId");
		
		 query.setParameterList("constituencyIds", constituencyIds);		
		return query.list();
	
	}	
	
	public List<Object[]> getCastGroupWiseCadreCountExcludeminority(List<Long> Ids,String type)
	{
		StringBuilder str=new StringBuilder(); 
		str.append("select count(model.casteStateId),model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.casteState.casteCategoryGroup.casteCategory.categoryName ");
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
			 str.append(",model.userAddress.constituency.district.districtId"); 
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 str.append(",model.userAddress.constituency.constituencyId"); 
			else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(",model.userAddress.tehsil.tehsilId"); 
			else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(",model.userAddress.localElectionBody.localElectionBodyId"); 
		str.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
		
			str.append(" and model.casteStateId not in("+IConstants.MINORITY_CASTE_IDS+")");
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			    str.append(" and model.userAddress.constituency.district.districtId in(:Ids) group by model.userAddress.constituency.district.districtId,model.casteState.casteCategoryGroup.casteCategory.casteCategoryId ");
			 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				str.append(" and model.userAddress.constituency.constituencyId in(:Ids) group by model.userAddress.constituency.constituencyId,model.casteState.casteCategoryGroup.casteCategory.casteCategoryId");
			 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
					str.append(" and model.userAddress.tehsil.tehsilId in(:Ids) group by model.userAddress.tehsil.tehsilId,model.casteState.casteCategoryGroup.casteCategory.casteCategoryId");
			 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
					str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:Ids) group by model.userAddress.localElectionBody.localElectionBodyId,model.casteState.casteCategoryGroup.casteCategory.casteCategoryId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		
		return query.list();
	}
	
	public List<Object[]> getCastGroupWiseCadreCountMinority(List<Long> Ids,String type)
	{
		StringBuilder str=new StringBuilder(); 
		str.append("select count(model.casteStateId) ");
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
			 str.append(",model.userAddress.constituency.district.districtId"); 
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 str.append(",model.userAddress.constituency.constituencyId"); 
			else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(",model.userAddress.tehsil.tehsilId"); 
			else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append("model.userAddress.localElectionBody.localElectionBodyId"); 
		str.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014");
		
			str.append(" and model.casteStateId in("+IConstants.MINORITY_CASTE_IDS+")");
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			    str.append(" and model.userAddress.constituency.district.districtId in(:Ids) group by model.userAddress.constituency.district.districtId");
			 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				str.append(" and model.userAddress.constituency.constituencyId in(:Ids) group by model.userAddress.constituency.constituencyId");
			 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
					str.append(" and model.userAddress.tehsil.tehsilId in(:Ids) group by model.userAddress.tehsil.tehsilId");
			 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
					str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:Ids) group by model.userAddress.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		
		return query.list();
	}
	public List<Object[]> getTotalRecordsByIds(List<Long> Ids,String type,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId"); 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "); 
		 if(fromDate != null && toDate != null){
		   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
		 }
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:Ids) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.constituencyId in(:Ids) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:Ids) group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:Ids) group by model.userAddress.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		query.setParameterList("Ids", Ids);
		
		return query.list();
	}
	
	public List<Object[]> getTotalRecordsForALocation(List<Long> districtIds,String type,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		if(type.equalsIgnoreCase(IConstants.DISTRICT))
		 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
		else if(type.equalsIgnoreCase(IConstants.TEHSIL))
			str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
		else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
			str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId"); 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "); 
		 if(fromDate != null && toDate != null){
		   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
		 }
		 if(type.equalsIgnoreCase(IConstants.DISTRICT))
		    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
		 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			str.append(" and model.userAddress.constituency.constituencyId in(:districtIds) group by model.userAddress.constituency.constituencyId");
		 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) group by model.userAddress.tehsil.tehsilId");
		 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}	
	
	public List<Object[]> getRegisteredCountByHourWise(Date fromDate, Date toDate){
		Query query = getSession().createQuery("select " +
				" count(model.tdpCadreId),model.insertedBy.cadreSurveyUserId,hour(model.surveyTime),date(model.surveyTime) from TdpCadre model " +
				" where model.enrollmentYear = 2014 and " +
				" model.isDeleted = 'N' and" +
				" model.dataSourceType='TAB' and" +
				" date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate and" +
				" model.insertedBy.cadreSurveyUserId is not null" +
				"  group by model.insertedBy.cadreSurveyUserId,date(model.surveyTime),hour(model.surveyTime)");
		
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);		
		return query.list();
	}		
	
	public Long getTotalRegisteredCadreCountByLocation(List<Long> locationIds, String queryStr)
	{
		Query query = getSession().createQuery(queryStr);
		
		query.setParameterList("locationIds", locationIds);		
		return (Long) query.uniqueResult();
	}
	
	
	public List<Object[]> getRegistrationStartedLocations(List<Long> Ids){
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.userAddress.booth.boothId),model.userAddress.constituency.constituencyId"); 
		str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 and model.userAddress.constituency.constituencyId in(:Ids) group by model.userAddress.constituency.constituencyId"); 
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		return query.list();
	}
	public List<Object[]> getBelowCadresBooths(List<Long> Ids){
		StringBuilder str = new StringBuilder();
		str.append("select model.userAddress.booth.boothId,model.userAddress.constituency.constituencyId"); 
		str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 and model.userAddress.constituency.constituencyId in(:Ids) and  model.userAddress.booth.boothId  is not null group by model.userAddress.constituency.constituencyId, model.userAddress.booth.boothId " +
				"  having count(model.tdpCadreId) < 10"); 
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("Ids", Ids);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseGenderCadreCount1(List<Long> Ids,String type){		
		
		StringBuilder queryStr=new StringBuilder();
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
	    	queryStr.append("select count(model.gender),model.gender,model.userAddress.constituency.constituencyId,model.userAddress.booth.boothId ");
		
            queryStr.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.gender is not null and model.userAddress.booth.boothId is not null");
            
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
		    	queryStr.append(" and model.userAddress.constituency.constituencyId in (:Ids) group by model.userAddress.constituency.constituencyId,model.userAddress.booth.boothId,model.gender ");
		Query query = getSession().createQuery(queryStr.toString());		
		query.setParameterList("Ids", Ids);
				
		return query.list();
    }
	

	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMissingDetails(Set<Long> voterIds)
	{
		Query query = getSession().createQuery("select distinct model.voterId , model.uniqueKey from TdpCadre model where model.voterId in (:voterIds) and model.isDeleted = 'N' and model.enrollmentYear = :enrollmentYear ");
		query.setParameterList("voterIds", voterIds);	
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		return query.list();
	}
	
	/*public List<Object[]> checkForExists(String uniqueKey)
	{
		Query query = getSession().createQuery("select model.uniqueKey,model.isDeleted from TdpCadre model where model.uniqueKey = :uniqueKey  and model.enrollmentYear = :enrollmentYear and model.familyVoterId is not null ");
		query.setParameter("uniqueKey", uniqueKey);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		
		return query.list();
	}*/
	
	
	public Long checkForExists(String uniqueKey)
	{
		Query query = getSession().createQuery("select count(model.uniqueKey) from TdpCadre model where model.uniqueKey = :uniqueKey  and model.enrollmentYear = :enrollmentYear and model.familyVoterId is not null and model.isDeleted = 'NA' ");
		query.setParameter("uniqueKey", uniqueKey);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		Long count = (Long)query.uniqueResult();
		return count;
	}
	
	
	public Integer updateFamilyDetailsWithHistory(List<String> uniqueIds,List<Long> usersIds)
	{
		Query query = getSession().createQuery("update TdpCadre model set model.isDeleted = 'H'   where model.uniqueKey in (:uniqueIds) and model.enrollmentYear = :enrollmentYear and model.insertedBy.cadreSurveyUserId in(:usersIds) and model.familyVoterId is not null  ");
		query.setParameterList("uniqueIds", uniqueIds);
		query.setParameterList("usersIds", usersIds);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		Integer count = query.executeUpdate();
		return count;
	}
	
	public List<Object[]> getFamilyDetails(List<String> uniqueIds)
	{
		Query query = getSession().createQuery("select model.familyVoterId,model.firstname,model.relativename,model.houseNo,model.mobileNo,model.uniqueKey from TdpCadre model where model.uniqueKey in (:uniqueIds)  group by model.uniqueKey  ");
		query.setParameterList("uniqueIds", uniqueIds);
		return query.list();
	}
	
	public Integer updateDetailsToDuplicate(List<String> uniqueKeys)
	{
		Query query = getSession().createQuery("update TdpCadre model set model.isDeleted = 'NA'   where model.uniqueKey in (:uniqueKeys) and model.enrollmentYear = :enrollmentYear and model.familyVoterId is not null  ");
		query.setParameterList("uniqueKeys", uniqueKeys);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		Integer count = query.executeUpdate();
		return count;
	}
	
	
	public Long checkForFamilyExists(String uniqueKey)
	{
		Query query = getSession().createQuery("select count(model.uniqueKey) from TdpCadre model where model.uniqueKey = :uniqueKey  and model.enrollmentYear = :enrollmentYear and model.familyVoterId is not null  ");
		query.setParameter("uniqueKey", uniqueKey);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		Long count = (Long)query.uniqueResult();
		return count;
	}
	
	public Integer updateDetails(List<String> uniqueKeys)
	{
		Query query = getSession().createQuery("update TdpCadre model set model.isDeleted = 'H'   where model.uniqueKey in (:uniqueKeys) and model.enrollmentYear = :enrollmentYear and model.familyVoterId is not null and model.isDeleted = 'N' ");
		query.setParameterList("uniqueKeys", uniqueKeys);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
		Integer count = query.executeUpdate();
		return count;
	}
	
	public List<Object[]> getCadreDetailsByMemberShipIdForNonVoters(List<String> memberCardNos)	{
		Query query = getSession().createQuery("select model.memberShipNo , " +
				" model.voterId," +
				" model.firstname," +
				" model.relativename," +
				//" model.voter.voterId," +
				//" model.voter.voterIDCardNo," +
				" model.dataSourceType," +
				" model.tdpCadreId," +
				" model.refNo," +
				" model.mobileNo," +
				" model.photoType," +
				" model.image," +
				
				" model.userAddress.userAddressId,model.cardNumber from TdpCadre model " +
				" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
		query.setParameterList("memberCardNos", memberCardNos);
		return query.list();
	}
	
	public List<Object[]> getOtherStateCadreDetailsByMemberShipIdForNonVoters(List<String> memberCardNos)	{
		Query query = getSession().createQuery("select model.memberShipNo , " +
				" model.voterId," +
				" model.firstname," +
				" model.relativename," +
				//" model.voter.voterId," +
				//" model.voter.voterIDCardNo," +
				" model.dataSourceType," +
				" model.tdpCadreId," +
				" model.refNo," +
				" model.mobileNo," +
				" model.photoType," +
				" model.image," +
				
				" model.userAddress.userAddressId,model.cardNumber from TdpCadre model " +
				" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
		query.setParameterList("memberCardNos", memberCardNos);
		return query.list();
	}
	
public List<Object[]> getBoothWiseGenderCadres(List<Long> Ids,Long constituencyId){		
		
		StringBuilder queryStr=new StringBuilder();
		queryStr.append(" select count(model.tdpCadreId),model.gender,model.userAddress.booth.boothId,model.userAddress.booth.partNo ");
		//queryStr.append(" CASE WHEN model.userAddress.booth.panchayat is null THEN model.userAddress.booth.localBody.name ");
		//queryStr.append(" WHEN model.userAddress.booth.panchayat is not null THEN model.userAddress.booth.tehsil.tehsilName END ");
		queryStr.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.gender is not null and model.userAddress.booth.boothId in(:Ids)");
		
		queryStr.append(" and model.userAddress.constituency.constituencyId =:constituencyId group by model.userAddress.booth.boothId,model.gender ");
		Query query = getSession().createQuery(queryStr.toString());		
		query.setParameterList("Ids", Ids);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
    }


	public List<Object[]> getTotalRecordsByAccessType(List<Long> districtIds,String type,Date fromDate,Date toDate){
		StringBuilder str = new StringBuilder();
		
		 str.append(" select count(model.tdpCadreId),model.userAddress.constituency.district.districtId "); 
		 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "); 
		 if(fromDate != null && toDate != null){
		   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
		 }
		 
		str.append(" and model.userAddress.constituency.constituencyId in (:districtIds) group by model.userAddress.constituency.district.districtId ");
		 
		
		Query query = getSession().createQuery(str.toString());
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate",fromDate);
			query.setParameter("toDate",toDate);
		}
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}
	
	public List<Object[]> getTotalRecordsByAccessTypeByDate(List<Long> districtIds,String type,Date date){
		StringBuilder str = new StringBuilder();
		
		 str.append(" select count(model.tdpCadreId),model.userAddress.constituency.district.districtId "); 
		 
		 str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 " +
				" and date(model.surveyTime)=:date "); 
		 
		str.append(" and model.userAddress.constituency.constituencyId in (:districtIds) group by model.userAddress.constituency.district.districtId ");
		 
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("districtIds", districtIds);
		query.setParameter("date", date);
		return query.list();
	}
	
	public List<Object[]> getTotalRecordsByAccessTypeUnderDate(List<Long> districtIds,String type,Date date){
		StringBuilder str = new StringBuilder();
		
		str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
		
		str.append("  from TdpCadre model" +
				" where model.isDeleted = 'N' " +
				" and model.enrollmentYear = 2014 "+
				" and date(model.surveyTime)<=:date "); 
		 
		str.append(" and model.userAddress.constituency.constituencyId in (:districtIds) group by model.userAddress.constituency.district.districtId ");
		 
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("districtIds", districtIds);
		query.setParameter("date", date);
		
		
		return query.list();
	}
	
	public List<Object[]> getGenderTotalCountByAccessType(Long districtId, List<Long> constituencyId){
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' and model.userAddress.constituency.constituencyId in (:constituencyId) ");
	
		queryStr.append(" and model.userAddress.district.districtId = :districtId ");
	
		queryStr.append(" and model.gender is not null group by model.enrollmentYear");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		query.setParameterList("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getGenderWiseCadreCountByAccessType(Long districtId, List<Long> constituencyId){
			
			StringBuilder queryStr=new StringBuilder();
			queryStr.append("select count(model.gender),model.enrollmentYear,model.gender " +
					"from TdpCadre model where model.isDeleted = 'N' and model.userAddress.constituency.constituencyId in (:constituencyId) ");
			
			queryStr.append(" and model.userAddress.district.districtId = :districtId  " );
			
			queryStr.append(" group by  model.enrollmentYear,model.gender  order by model.enrollmentYear desc," +
					" model.gender desc ");
			Query query = getSession().createQuery(queryStr.toString());		
			query.setParameter("districtId", districtId);
			query.setParameterList("constituencyId", constituencyId);
			return query.list();
	}
	
	
	public List<Object[]> getAgeTotalCountByAccessType(Long districtId, List<Long> constituencyId){

		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' ");
	
			queryStr.append(" and model.userAddress.constituency.constituencyId in (:constituencyId) ");

			queryStr.append(" and model.userAddress.district.districtId = :districtId ");
		
		queryStr.append(" and model.age is not null group by model.enrollmentYear");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		query.setParameterList("constituencyId", constituencyId);
		return query.list();
	}
	
	
	
	public List<Object[]> getAgeRangeCadreCountByAccessType(Long districtId, String ageRange,List<Long> constituencyIds) {
		StringBuilder queryStr = new StringBuilder();

		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' and model.userAddress.constituency.constituencyId in (:constituencyIds)");
		
			queryStr.append(" and model.userAddress.district.districtId = :districtId ");
		

		if (ageRange.equals("below 18")) {
			queryStr.append(" and model.age <18 ");
		} else if (ageRange.equals("18-25")) {
			queryStr.append(" and model.age >=18 and model.age<=25 ");
		} else if (ageRange.equals("26-35")) {
			queryStr.append(" and model.age >=26 and model.age<=35 ");
		} else if (ageRange.equals("36-45")) {
			queryStr.append(" and model.age >=36 and model.age<=45 ");
		} else if (ageRange.equals("46-60")) {
			queryStr.append(" and model.age >=46 and model.age<=60 ");
		} else if (ageRange.equals("above 60")) {
			queryStr.append(" and model.age >60 ");
		}

		queryStr.append(" group by model.enrollmentYear order by model.enrollmentYear desc ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
	}
	
	public List<Object[]> getCasteGroupTotalCountByAccessType(Long districtId, List<Long> constituencyId){
		
		StringBuilder queryStr = new StringBuilder();
	
		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear "
				+ " from TdpCadre model where model.isDeleted = 'N' and model.userAddress.constituency.constituencyId in (:constituencyId) ");
		queryStr.append(" and model.userAddress.district.districtId = :districtId ");
		
		queryStr.append(" and model.casteState.casteStateId is not null group by model.enrollmentYear");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		query.setParameterList("constituencyId", constituencyId);
		return query.list();
		
	}
	
	public List<Object[]> getCasteGroupWiseCadreCountExcludingMinoritiesByAccessType(Long districtId, List<Long> constituencyId){
  
   		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT count(model.casteStateId),model.enrollmentYear,model.casteState.casteCategoryGroup.casteCategory.casteCategoryId, ");
		queryStr.append(" model.casteState.casteCategoryGroup.casteCategory.categoryName from TdpCadre model where model.isDeleted = 'N' ");
		queryStr.append(" and model.casteState.casteStateId not in("+IConstants.MINORITY_CASTE_IDS+") ");
		queryStr.append(" and model.userAddress.constituency.constituencyId in (:constituencyId) ");
		queryStr.append(" and model.userAddress.constituency.district.districtId = :districtId ");
		queryStr.append(" group by  model.casteState.casteCategoryGroup.casteCategory.casteCategoryId,model.enrollmentYear  ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		query.setParameterList("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getCadreCountInMinoritiesByAccessType(Long districtId, List<Long> constituencyId){
   
   		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT count(model.casteStateId),model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' ");
		queryStr.append(" and model.casteState.casteStateId in("+IConstants.MINORITY_CASTE_IDS+") ");
		queryStr.append(" and model.userAddress.constituency.constituencyId in (:constituencyId) ");
		queryStr.append(" and model.userAddress.constituency.district.districtId = :districtId ");

		queryStr.append(" group by model.enrollmentYear ");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		query.setParameterList("constituencyId", constituencyId);
		return query.list();
	}
	
	  public List<Object[]> getCastWiseCadreCountByAccessType(Long districtId, List<Long> constituencyId){
			StringBuilder queryStr=new StringBuilder(); 
			queryStr.append("select count(model.casteStateId),model.enrollmentYear,model.casteState.casteStateId,model.casteState.caste.casteName,model.casteState.casteCategoryGroup.casteCategory.categoryName "+
					" from TdpCadre model where model.isDeleted = 'N' and model.userAddress.constituency.constituencyId in (:constituencyId) ");
		
				queryStr.append(" and model.userAddress.district.districtId = :districtId ");
		
				queryStr.append(" group by  model.casteState.casteStateId,model.enrollmentYear order by model.casteState.caste.casteName ");

			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("districtId", districtId);
			query.setParameterList("constituencyId", constituencyId);
			return query.list();
		}
	  public List<Object[]> getTotalRecordsBoothWise(Long constituencyId,Date fromDate,Date toDate){
			StringBuilder str = new StringBuilder();
			 str.append("select count(model.tdpCadreId),model.userAddress.booth.boothId from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.userAddress.booth.boothId is not null "); 
			 if(fromDate != null && toDate != null){
			   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
			 }
			  str.append(" and model.userAddress.constituency.constituencyId = :constituencyId group by model.userAddress.booth.partNo ");
			 
				Query query = getSession().createQuery(str.toString());
			if(fromDate != null && toDate != null){
				query.setParameter("fromDate",fromDate);
				query.setParameter("toDate",toDate);
			}
			query.setParameter("constituencyId",constituencyId);
			return query.list();
		  }
	  
	  public Long getRegisteredVotersForConstituencys(List<Long> constituencyIds)
		{
			Query query=getSession().createQuery("select count(model.tdpCadreId) from TdpCadre model " +
					"  where model.userAddress.constituency.constituencyId in(:constituencyIds) and model.enrollmentYear = 2014 and " +
					"  model.isDeleted='N'" );			
	        query.setParameterList("constituencyIds", constituencyIds);
			return (Long)query.uniqueResult();
		}
	  
	  public List<Object[]> getTotalRecordsByBoothWise(Long constituencyId){
			StringBuilder str = new StringBuilder();
			 str.append(" select count(model.tdpCadreId), model.userAddress.booth.boothId, model.userAddress.booth.partNo, model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
			 str.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.userAddress.booth.boothId is not null and model.userAddress.tehsil is not null ");
			 str.append(" and model.userAddress.constituency.constituencyId = :constituencyId  group by model.userAddress.booth.boothId ");
			 
			Query query = getSession().createQuery(str.toString());
			query.setParameter("constituencyId",constituencyId);
			return query.list();
	  }
	  
	  public List<Object[]> getTotalRegisterCadreInfo(){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId from TdpCadre model where model.isDeleted = 'N'  and model.enrollmentYear = 2014 ");
			queryStr.append(" and  model.userAddress.state.stateId = 1 group by model.userAddress.district.districtId ");	
			Query query = getSession().createQuery(queryStr.toString());
			return query.list();
		}

	  public List<Object[]> getCadreDetails(String queryStr)
		{
			
			Query query = getSession().createQuery(queryStr.toString()); 
		
			return query.list();
		}
	  
	  public List<Object[]> getTeluguVoterNames(List<Long> tdpCadreId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select tc.tdpCadreId,concat(vn.firstName,' ',vn.lastName) from TdpCadre tc,VoterNames vn where tc.isDeleted = 'N'  and tc.enrollmentYear = 2014 ");
			queryStr.append(" and  tc.tdpCadreId in (:tdpCadreId) and  tc.voterId = vn.voterId and tc.voterId is not null");	
			Query query = getSession().createQuery(queryStr.toString());
		    query.setParameterList("tdpCadreId", tdpCadreId);
			return query.list();
		}
	  
	  public List<Object[]> getTdpCadreDetailsBySearchCriteriaForCallCenter(Long constituencyId,String queryString)
		{
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select distinct model.tdpCadreId, model.firstname, model.relativename,  ");
			queryStr.append(" model.gender ,model.memberShipNo, model.refNo , model.mobileNo, model.image, model.cardNumber,model.age,date(model.dateOfBirth), model.userAddress.constituency.name,model.voterId,model.occupationId ");
			queryStr.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
			queryStr.append(" "+queryString+" ");
			queryStr.append(" order by model.firstname ");
			
			Query query = getSession().createQuery(queryStr.toString());
			if(constituencyId != null && constituencyId != 0L)
			{
				query.setParameter("constituencyId", constituencyId);
			}
			
			return query.list();
		}
	  
	  
	  public List<Object[]> searchTdpCadreDetailsBySearchCriteriaForCommitte(Long constituencyId,Long casteStateId,String queryString,int startIndex,int maxIndex,List<Long> constituencyIds,boolean isRemoved)
		{
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select distinct model.tdpCadreId, model.firstname, model.relativename,  ");
			queryStr.append(" model.gender ,model.memberShipNo, model.refNo , model.mobileNo, model.image, model.cardNumber,model.age,date(model.dateOfBirth), constituency.name,voter.age,occupatn.occupation, ");
			queryStr.append(" tehsil.tehsilName , panc.panchayatName,localElectionBody.name,district.districtName,caste.casteName,voter.voterIDCardNo, electionType.electionType, model.houseNo,  ");
			queryStr.append(" constituency.constituencyId, tehsil.tehsilId, panc.panchayatId, localElectionBody.localElectionBodyId, district.districtId,voter.houseNo,model.aadheerNo, model.dataSourceType , model.isDeleted,cadreDeleteReason.cadreDeleteReasonId," +
					" cadreDeleteReason.reason");//20
			queryStr.append(" from TdpCadre model left join model.userAddress.panchayat panc ");
			queryStr.append(" left join model.userAddress.tehsil tehsil ");
			queryStr.append(" left join model.userAddress.constituency constituency ");
			queryStr.append(" left join model.userAddress.localElectionBody localElectionBody ");
			queryStr.append(" left join model.userAddress.localElectionBody.electionType electionType ");
			queryStr.append(" left join model.userAddress.district district ");
			queryStr.append(" left join model.occupation occupatn ");
			queryStr.append(" left join model.voter voter ");
			queryStr.append(" left join model.casteState.caste caste ");
			queryStr.append(" left join model.familyVoter familyVoter ");
			queryStr.append(" left join model.cadreDeleteReason cadreDeleteReason ");
			
			if(isRemoved){
				queryStr.append(" where  model.isDeleted = 'MD'  and model.enrollmentYear = 2014  ");
			}
			
			else{
				queryStr.append(" where (model.isDeleted = 'N' or model.isDeleted = 'MD')  and model.enrollmentYear = 2014  ");
			}
				
			
			queryStr.append(" "+queryString+" ");
			queryStr.append(" order by model.firstname ");
			
			Query query = getSession().createQuery(queryStr.toString());
			if((constituencyId != null && constituencyId != 0L) && (constituencyIds == null || constituencyIds.size() == 0))
			{
				query.setParameter("locationValue", constituencyId);
			}
			if(constituencyIds != null && constituencyIds.size() > 0) // mp
			{
				query.setParameterList("ids", constituencyIds);	
			}
			if(casteStateId != null && casteStateId != 0L)
			{
				query.setParameter("casteStateId", casteStateId);
			}
			if(startIndex > 0)
			query.setFirstResult(startIndex);
			if(maxIndex > 0)
				query.setMaxResults(maxIndex);
			
			return query.list();
		}
	  public List<Object[]> getRegisteredMemberDetails(Long tdpCadreId){
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append(""+
			  " select model.voter.voterId, "+
			  " model.voter.name, "+
			  " model.voter.relativeName, "+
			  " model.voter.voterIDCardNo, "+
			  " model.voter.gender, "+
			  " model.voter.age, "+
			  " model.voter.dateOfBirth, "+
			  " model.voter.mobileNo, "+
			  " model.voter.houseNo, "+
			  " booth.boothId, "+
			  " booth.partNo, "+
			  " district.districtId, "+
			  " district.districtName, "+
			  " constituency.constituencyId, "+
			  " constituency.name, "+
			  " tehsil.tehsilId, "+
			  " tehsil.tehsilName, "+
			  " localElectionBody.localElectionBodyId,"+
			  " localElectionBody.name,"+
			  " model.image "+
			  " from " +
			  " TdpCadre model " +
			  " left join model.userAddress.constituency.district district "+
			  " left join model.userAddress.constituency constituency "+
			  " left join model.userAddress.booth booth "+
			  " left join model.userAddress.tehsil tehsil "+
			  " left join model.userAddress.localElectionBody localElectionBody "+
			  " where model.tdpCadreId = :tdpCadreId and model.isDeleted='N' and" +
			  " model.tdpMemberType.tdpMemberTypeId = 5 and model.enrollmentYear=:enrollmentYear ");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("tdpCadreId", tdpCadreId);
		  query.setParameter("enrollmentYear", IConstants.UNIONS_REGISTRATION_YEAR);
		  return query.list();
	  }
	  
	  public List<Object[]> tdpCadreCasteCountDetailsBySearchCriteriaForCommitte(Long constituencyId,Long casteStateId,String queryString)
		{
			StringBuilder queryStr = new StringBuilder();
			
			queryStr.append(" select caste.casteName,model.casteState.casteStateId,count(*) from TdpCadre model left join model.userAddress.panchayat panc ");
			queryStr.append(" left join model.userAddress.tehsil tehsil ");
			queryStr.append(" left join model.userAddress.constituency constituency ");
			queryStr.append(" left join model.userAddress.localElectionBody localElectionBody ");
			queryStr.append(" left join model.userAddress.localElectionBody.electionType electionType ");
			queryStr.append(" left join model.userAddress.district district ");
			queryStr.append(" left join model.occupation occupatn ");
			queryStr.append(" left join model.voter voter ");
			queryStr.append(" left join model.casteState.caste caste ");
			queryStr.append(" left join model.familyVoter familyVoter ");
			queryStr.append(" where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
			queryStr.append(" "+queryString+" ");
			queryStr.append(" order by count(*) desc ");
			
			Query query = getSession().createQuery(queryStr.toString());
			if(constituencyId != null && constituencyId != 0L)
			{
				query.setParameter("locationValue", constituencyId);
			}
			if(casteStateId != null && casteStateId != 0L)
			{
				query.setParameter("casteStateId", casteStateId);
			}
			return query.list();
		}
	  
	  public List<String> getCardNumbersForOnlineCadre(String query,Long constiId,String mobileNo,String trNo,Date surveyDate){
			StringBuilder str = new StringBuilder();
			
			str.append(" select model.memberShipNo from TdpCadre model,TdpCadreOnline model1 " +
					" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is not null ");
			str.append( "  and model.cardNumber is  null  and model.tdpCadreOnline.tdpCadreOnlineId = model1.tdpCadreOnlineId and model.dataSourceType = 'ONLINE' " +
					" and model1.deliveryMode = 1 " );
			
			//str.append( " and model.isPrintReady = 'Y' ");
			str.append(query);
			str.append( " order by date(model.surveyTime)" );
			
			Query qry = getSession().createQuery(str.toString());
			
			if(constiId!=null){
				qry.setParameter("constituencyId", constiId);
			}
			if(mobileNo!=null && mobileNo.trim().length()>0){
				qry.setParameter("mobileNo", mobileNo);
			}
			if(trNo!=null && trNo.trim().length()>0){
				qry.setParameter("trNo", trNo);
			}
			if(surveyDate!=null){
				qry.setDate("surveyDate", surveyDate);
			}
			
			return qry.list();
		}
		
		public List<String> getCardNumbersForNonVotersForOnlineCadre(String query,Long constiId,String mobileNo,String trNo,Date surveyDate){
			StringBuilder str = new StringBuilder();
			
			str.append(" select model.memberShipNo from TdpCadre model,TdpCadreOnline model1  " +
					" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is null ");
			str.append( " and model.cardNumber is  null and model.tdpCadreOnline.tdpCadreOnlineId = model1.tdpCadreOnlineId and model.dataSourceType = 'ONLINE' " +
					" and model1.deliveryMode = 1   " );
			//str.append( " and model.isPrintReady = 'Y' ");
			str.append(query);
			str.append( " order by date(model.surveyTime)" );
			
			Query qry = getSession().createQuery(str.toString());
			
			if(constiId!=null){
				qry.setParameter("constituencyId", constiId);
			}
			if(mobileNo!=null && mobileNo.trim().length()>0){
				qry.setParameter("mobileNo", mobileNo);
			}
			if(trNo!=null && trNo.trim().length()>0){
				qry.setParameter("trNo", trNo);
			}
			if(surveyDate!=null){
				qry.setDate("surveyDate", surveyDate);
			}
			
			return qry.list();
		}

		public List<Object[]> getRegisterCadreInfoForVolunteerUserBetweenDates(Date fromDate,Date toDate,List<Long> tabUserIds,List<Long> webUserIds){
			Calendar cal = Calendar.getInstance();
			cal.set(cal.YEAR, 2014);
			cal.set(cal.MONTH, 11);
			cal.set(cal.DATE, 18);
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select count(model.tdpCadreId),model.dataSourceType  from TdpCadre model where " +
					" model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
			
			if(fromDate != null){
				queryStr.append(" and date(model.surveyTime) >=:fromDate ");
			}
			
			if(toDate != null){
				queryStr.append(" and date(model.surveyTime) <=:toDate ");
			}
			
		    if(tabUserIds.size() > 0  &&  webUserIds.size() > 0){
				queryStr.append(" and (model.insertedBy.cadreSurveyUserId in (:tabUserIds) or model.insertedWebUser.userId  in (:webUserIds))");
		    }else if(tabUserIds.size() > 0){
				queryStr.append(" and model.insertedBy.cadreSurveyUserId in (:tabUserIds) ");
		    }else{
				queryStr.append(" and model.insertedWebUser.userId  in (:webUserIds) ");
		    }
			
			
			queryStr.append(" and date(model.surveyTime) >:startDate group by model.dataSourceType ");	
			Query query = getSession().createQuery(queryStr.toString());
			
			if(fromDate != null){
			   query.setDate("fromDate", fromDate);
			}
			if(toDate != null){
			  query.setDate("toDate", toDate);
			}
		
			if( tabUserIds.size() > 0 )
				query.setParameterList("tabUserIds", tabUserIds);
			if(webUserIds.size() > 0 )
				query.setParameterList("webUserIds", webUserIds);
			query.setParameter("startDate",cal.getTime());
			return query.list();
		}
		
		
		public List<String> getExistingRecordsInfo(List<String> uniqueKeys,List<Long> userIds){
			Query query = getSession().createQuery("select distinct model.uniqueKey from TdpCadre model where model.enrollmentYear ='2014' and model.isDeleted='N' and " +
					" model.uniqueKey in(:uniqueKeys) and model.insertedBy.cadreSurveyUserId in(:userIds)");
			query.setParameterList("uniqueKeys", uniqueKeys);
			query.setParameterList("userIds", userIds);
			return query.list();
		}
		public Long getTotalRegisterCadreInfoByState(String state,List<Long> accessLocationIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select count(model.tdpCadreId) from TdpCadre model where model.isDeleted = 'N'  and model.enrollmentYear = 2014 ");
			queryStr.append(" and  model.userAddress.state.stateId = 1  ");	
			if(state.equalsIgnoreCase("AP"))
			queryStr.append(" and model.userAddress.district.districtId > 10");
			else if(state.equalsIgnoreCase("TS"))
			queryStr.append(" and model.userAddress.district.districtId < 11");
			if(accessLocationIds.size() > 0){
				queryStr.append(" and model.userAddress.constituency.constituencyId in(:accessLocationIds)");
			}
			Query query = getSession().createQuery(queryStr.toString());
			if(accessLocationIds.size() > 0){
				query.setParameterList("accessLocationIds", accessLocationIds);
			}
			return (Long) query.uniqueResult();
		}
				
		public List<Object[]> getLocationWiseCadreRegisterInfo(Set<Long> locationIds,String locationType,Long constituencyId){
			StringBuilder queryStr = new StringBuilder();
			//0locationId,1count
			queryStr.append("select "+getLocation(locationType));
			
			queryStr.append(",count(model.tdpCadreId) from TdpCadre model where "+getLocation(locationType)+"" +
					" in(:locationIds) and model.enrollmentYear ='2014' and model.isDeleted = 'N'  and "+getLocation(locationType)+" is not null ");
			if(constituencyId != null){
				queryStr.append(" and model.userAddress.constituency.constituencyId =:constituencyId ");
			}
			queryStr.append(" group by "+getLocation(locationType) );
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("locationIds", locationIds);
			if(constituencyId != null){
			  query.setParameter("constituencyId", constituencyId);
			}
			return query.list();
		}
		
		public String getLocation(String location){
			if(location.equalsIgnoreCase("district")){
				return " model.userAddress.district.districtId ";
			}else if(location.equalsIgnoreCase("constituency")){
				return " model.userAddress.constituency.constituencyId ";
			}else if(location.equalsIgnoreCase("mandal")){
				return " model.userAddress.tehsil.tehsilId ";
			}else if(location.equalsIgnoreCase("localBody")){
				return " model.userAddress.localElectionBody.localElectionBodyId ";
			}else if(location.equalsIgnoreCase("panchayat")){
				return " model.userAddress.panchayat.panchayatId ";
			}else{
				return " model.userAddress.booth.boothId ";
			}
		}
		
		public Long checkMemberExists(String memberShipNo)
		{
			Query query = getSession().createQuery("select distinct model.tdpCadreId from TdpCadre model where model.memberShipNo = '"+memberShipNo.trim()+"' and model.isDeleted = 'N' order by model.tdpCadreId desc ");
		
			query.setMaxResults(1);
			return (Long) query.uniqueResult();
		}
		public String getMobileNoByMemberShipNo(String memberShipNo)
		{
			Query query = getSession().createQuery("select distinct model.mobileNo from TdpCadre model where model.memberShipNo = '"+memberShipNo.trim()+"' and model.isDeleted = 'N' order by model.tdpCadreId desc ");
			
			query.setMaxResults(1);
			return (String) query.uniqueResult();
		}
		public List<Object[]> getMemberDataByMembershipNo(String memberShipNo)
		{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.firstname,model.mobileNo,model.age,model.gender");
		str.append(" from TdpCadre model where model.memberShipNo = '"+memberShipNo.trim()+"' and model.isDeleted = 'N' ");
		Query query = getSession().createQuery(str.toString());
		return query.list();
		}
		public List<Object[]> getMemberAddressByMembershipNo(String memberShipNo)
		{
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.firstname,model.mobileNo,model.age,model.gender");
		str.append(" ,district.districtName");
		str.append(" ,constituency.name");	
		str.append(" ,tehsil.tehsilName");
		str.append(" ,panc.panchayatName");
		str.append(" ,localElectionBody.name");
		str.append(" from TdpCadre model left join model.userAddress.panchayat panc ");
		str.append(" left join model.userAddress.tehsil tehsil ");
	    str.append(" left join model.userAddress.constituency constituency ");
		str.append(" left join model.userAddress.localElectionBody localElectionBody ");
		str.append(" left join model.userAddress.district district ");
		str.append(" where model.memberShipNo = :memberShipNo and model.isDeleted = 'N' ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("memberShipNo", memberShipNo);
		return query.list();
		}
		
		public String getMembershipNoByTdpCadreId(Long tdpCadreId)
		{
			Query query = getSession().createQuery("select model.memberShipNo from TdpCadre model where model.tdpCadreId = :tdpCadreId");
			query.setParameter("tdpCadreId",tdpCadreId);
			return (String)query.uniqueResult();
		}
		  public List<Object[]> getTdpCadreDetailsByVoterCardNoForCallCenter(String queryString)
			{
				StringBuilder queryStr = new StringBuilder();
				
				queryStr.append(" select distinct model.tdpCadreId, model.firstname, model.relativename,  ");
				queryStr.append(" model.gender ,model.memberShipNo, model.refNo , model.mobileNo, model.image, model.cardNumber,model.age,date(model.dateOfBirth), model.userAddress.constituency.name,model.voterId,model.occupationId ");
				queryStr.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
				queryStr.append(" "+queryString+" ");
				queryStr.append(" order by model.firstname ");
				
				Query query = getSession().createQuery(queryStr.toString());
				
				
				return query.list();
			}
		  
		  public List<Object[]> getexistringCadreInfoByLocationForCommittee(String candidateName, Long constid, Long panchayatId,Long boothId,String isPresentCadre, String enrollmentNo,Long areaId)
			{
				
				StringBuilder queryStr = new StringBuilder();
				queryStr.append(" select model.firstname, model.relativename, model.memberShipNo, model.tdpCadreId from TdpCadre model where ");
				boolean candiNameExist = false;
				if(candidateName.length()>2){
					candiNameExist = true;
					queryStr.append(" model.firstname like '%"+candidateName+"%' ");
				}
				
				if(enrollmentNo.length()>2){
					if(candiNameExist){
						queryStr.append(" and ");
					}
					queryStr.append(" model.memberShipNo = '"+enrollmentNo+"' ");
				}
				
				
				if(constid != null && constid.longValue() != 0L)
				{
					queryStr.append(" and model.userAddress.constituency.constituencyId = :constid ");
				}
				
				if(areaId==1){
					if(panchayatId != null && panchayatId.longValue() != 0L)
					{
						//queryStr.append(" and model.userAddress.panchayatId = :panchayatId ");
						if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
							queryStr.append(" and model.userAddress.panchayat.panchayatId = :id ");
						}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
							queryStr.append(" and model.userAddress.ward.constituencyId = :id ");
						}
						else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("3")){
							queryStr.append(" and model.userAddress.ward.constituencyId = :id ");
						}
					}
				}else if(areaId==2){
					if(panchayatId != null && panchayatId.longValue() != 0L)
					{
						//queryStr.append(" and model.userAddress.panchayatId = :panchayatId ");
						if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
							queryStr.append(" and model.userAddress.localElectionBody.localElectionBodyId = :id ");
						}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
							queryStr.append(" and model.userAddress.tehsil.tehsilId = :id ");
						}
						else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("3")){
							queryStr.append(" and model.userAddress.ward.constituencyId = :id ");
						}
					}
					
				}
				
				/*if(boothId != null && boothId.longValue() != 0L)
				{
					queryStr.append(" and model.userAddress.booth.boothId = :boothId");
				}*/
				if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
				{
					queryStr.append(" and model.enrollmentYear in (:year) ");
				}
				
				else
				{
					queryStr.append(" and model.enrollmentYear not in (:year) ");
				}
				
				queryStr.append(" and model.memberShipNo is not null and model.memberShipNo != '' and model.isDeleted = 'N' order by model.firstname ");
				
				Query query = getSession().createQuery(queryStr.toString());
				
				if(constid != null && constid.longValue() != 0L)
				{
					query.setParameter("constid", constid);
				}
				
				if(panchayatId != null && panchayatId.longValue() != 0L)
				{
					//if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
						query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
					//}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					//	query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
					//}
				}
				/*if(boothId != null && boothId.longValue() != 0L)
				{
					query.setParameter("boothId", boothId);
				}*/
				
					query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);

				return query.list();
				
			}
		  
		  public List<TdpCadre> getTdpCadreDetails(String uuid)
		  {
			  Query query = getSession().createQuery("select model from TdpCadre model where model.uniqueKey = :uuid and model.isDeleted = 'N' and model.enrollmentYear= 2014");
			  query.setParameter("uuid",uuid);
			  return query.list();
			  
		  }
		  
		  public List<Object[]> getReqDetailsForIMageChecking(Long districtId,Long constituencyId)
		  {
			  StringBuffer queryString = new StringBuffer();
			  queryString.append("select model.tdpCadreId,model.image,model.firstname from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear= 2014 and model.image is not null and model.photoType = 'NEW' and model.cardNumber is null ");
			  if(districtId != null && districtId > 0)
			  {
				  queryString.append(" and model.userAddress.district.districtId = :districtId");
			  }
			  if(constituencyId != null && constituencyId > 0)
			  {
				  queryString.append(" and model.userAddress.constituency.constituencyId = :constituencyId");
			  }
			  queryString.append(" and model.tdpCadreId not in (select model1.tdpCadreId from TdpCadreImagesValid model1)");
			 // queryString.append(" limit 0 ,100");
			  Query query = getSession().createQuery(queryString.toString());
			  if(districtId != null && districtId > 0)
			  {
				  query.setParameter("districtId", districtId);
			  }
			  if(constituencyId != null && constituencyId > 0)
			  {
				  query.setParameter("constituencyId", constituencyId);
			  }
			  return query.list();
					  
		  }
		  
		
		public Long getMissedCallsCountByState(Date startDate, Date endDate,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT count(distinct model.mobileNumber) from CadreMissedCallCampaign model,TdpCadre tc " +
					" where tc.mobileNo = model.mobileNumber and tc.isDeleted = 'N' and tc.enrollmentYear='2014' "); 
			
			if(startDate != null && endDate != null && !startDate.equals(endDate))
			str.append(" and date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate");
			else if(startDate != null && endDate != null && startDate.equals(endDate))
			str.append(" and date(model.insertedTime) >=:startDate");
			
			if(stateId.longValue() == 1){
				str.append(" and tc.userAddress.district.districtId between 11 and 23 ");
			}else if(stateId.longValue() == 2){
				str.append(" and tc.userAddress.district.districtId between 1 and 10  ");
			}
			Query query = getSession().createQuery(str.toString());
			if(startDate != null && endDate != null && !startDate.equals(endDate)){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
			}
			else if(startDate != null && endDate != null && startDate.equals(endDate))
				query.setDate("startDate", startDate);
			
			return  (Long) query.uniqueResult();
		}
		
		public List<String> getMissedCallMobileNosByState(Date startDate, Date endDate,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT distinct model.mobileNumber from CadreMissedCallCampaign model,TdpCadre tc " +
					" where tc.mobileNo = model.mobileNumber and tc.isDeleted = 'N' and tc.enrollmentYear='2014' "); 
			
			if(startDate != null && endDate != null && !startDate.equals(endDate))
			str.append(" and date(model.insertedTime) >=:startDate and date(model.insertedTime) <=:endDate");
			else if(startDate != null && endDate != null && startDate.equals(endDate))
			str.append(" and date(model.insertedTime) >=:startDate");
			

			if(stateId.longValue() == 1L){
				str.append(" and tc.userAddress.district.districtId between 11 and 23  ");
			}else if(stateId.longValue() == 2L){
				str.append(" and tc.userAddress.district.districtId between 1 and 10  ");
			}
			Query query = getSession().createQuery(str.toString());
			if(startDate != null && endDate != null && !startDate.equals(endDate)){
				query.setDate("startDate", startDate);
				query.setDate("endDate", endDate);
			}
			else if(startDate != null && endDate != null && startDate.equals(endDate))
				query.setDate("startDate", startDate);
			
			return  query.list();
		}
		
		public List<Object[]> getMemberMobileNumbersCount(Date startDate, Date endDate,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT TC.mobileNo,TC.tdpCadreId,COUNT(TC.tdpCadreId) from CadreMissedCallCampaign model,TdpCadre TC " +
					" where TC.mobileNo = model.mobileNumber and TC.isDeleted = 'N' and TC.enrollmentYear='2014' " +
					" and date(model.insertedTime) >=:startDate and date(model.insertedTime) <= :endDate ");
			
			if(stateId.longValue() == 0L)
				str.append(" and TC.userAddress.district.districtId between 1 and 23 ");
			else if(stateId.longValue() == 1L)
				str.append(" and TC.userAddress.district.districtId between 11 and 23 ");
			else if(stateId.longValue() == 2L)
				str.append(" and TC.userAddress.district.districtId between 1 and 10 ");
			
			str.append(" group by TC.mobileNo,TC.tdpCadreId ");
			Query query = getSession().createQuery(str.toString());
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			
			return  query.list();
		}
		
		public List<Object[]> getDistrictWiseMemberMobileNumbersCount(Date startDate, Date endDate,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT TC.userAddress.district.districtId,TC.mobileNo,TC.tdpCadreId,COUNT(TC.tdpCadreId) from CadreMissedCallCampaign model,TdpCadre TC " +
					" where TC.mobileNo = model.mobileNumber and TC.isDeleted = 'N' and TC.enrollmentYear='2014' " +
					" and date(model.insertedTime) >=:startDate and date(model.insertedTime) <= :endDate ");
			
			if(stateId.longValue() == 0L)
				str.append(" and TC.userAddress.district.districtId between 1 and 23 ");
			else if(stateId.longValue() == 1L)
				str.append(" and TC.userAddress.district.districtId between 11 and 23 ");
			else if(stateId.longValue() == 2L)
				str.append(" and TC.userAddress.district.districtId between 1 and 10 ");
			
			str.append(" group by TC.userAddress.district.districtId,TC.mobileNo,TC.tdpCadreId ");
			Query query = getSession().createQuery(str.toString());
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
			
			return  query.list();
		}
		
		/*public List<Long> getSingleMemberMobileNosCount(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT count(tc.tdpCadreId) from TdpCadre tc " +
					" where tc.mobileNo in (:mobileNos) and tc.isDeleted = 'N' and tc.enrollmentYear='2014' "); 
						
			if(stateId.longValue() == 1L){
				str.append(" and tc.userAddress.district.districtId > 10 ");
			}else if(stateId.longValue() == 2L){
				str.append(" and tc.userAddress.district.districtId < 11 ");
			}
			str.append(" group by tc.mobileNo having count(tc.tdpCadreId) = 1 ");
			Query query = getSession().createQuery(str.toString());
			query.setParameterList("mobileNos", mobileNos);
			
			return query.list();
		}*/
		
		public List<Long> getSingleMemberMobileNosCount(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT COUNT(TC.tdp_cadre_id) CNT FROM tdp_cadre TC,user_address UA " +
					" where TC.mobile_no in (:mobileNos) and TC.is_deleted = 'N' and TC.enrollment_year = '2014' and TC.address_id = UA.user_address_id "); 
						
			if(stateId.longValue() == 1L){
				str.append(" and UA.district_id between 11 and 23  ");
			}else if(stateId.longValue() == 2L){
				str.append(" and UA.district_id between 1 and 10  ");
			}
			str.append(" group by TC.mobile_no HAVING CNT = 1 ");
			Query query = getSession().createSQLQuery(str.toString());
			query.setParameterList("mobileNos", mobileNos);
			
			return query.list();
		}
		

		/*public List<Long> getMultipleMemberMobileNosCount(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT count(tc.tdpCadreId) from TdpCadre tc " +
					" where tc.mobileNo in (:mobileNos) and tc.isDeleted = 'N' and tc.enrollmentYear='2014' "); 
			
		
			if(stateId.longValue() == 1L){
				str.append(" and tc.userAddress.district.districtId > 10 ");
			}else if(stateId.longValue() == 2L){
				str.append(" and tc.userAddress.district.districtId < 11 ");
			}else if(stateId.longValue() == 0L){
				str.append(" and tc.userAddress.district.districtId  between 1 and 23 ");
			}
			str.append(" group by tc.mobileNo having count(tc.tdpCadreId) > 1 ");
			Query query = getSession().createQuery(str.toString());
			query.setParameterList("mobileNos", mobileNos);
			return  query.list();
		}*/
		
		public List<Long> getMultipleMemberMobileNosCount(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT COUNT(TC.tdp_cadre_id) CNT FROM tdp_cadre TC,user_address UA " +
					" where TC.mobile_no in (:mobileNos) and TC.is_deleted = 'N' and TC.enrollment_year = '2014' and TC.address_id = UA.user_address_id "); 
			
		
			if(stateId.longValue() == 1L){
				str.append(" and UA.district_id between 11 and 23  ");
			}else if(stateId.longValue() == 2L){
				str.append(" and UA.district_id between 1 and 10 ");
			}else if(stateId.longValue() == 0L){
				str.append(" and UA.district_id  between 1 and 23 ");
			}
			str.append(" group by TC.mobile_no having CNT > 1 ");
			Query query = getSession().createSQLQuery(str.toString());
			query.setParameterList("mobileNos", mobileNos);
			return  query.list();
		}
		
		public Long getMatchedMobileNosByState(List<String> mobileNos)
		{
			StringBuilder str = new StringBuilder();
			str.append("SELECT count(distinct model.mobileNo) from TdpCadre model " +
					" where model.mobileNo in (:mobileNos) and model.isDeleted = 'N' and tc.enrollmentYear='2014' "); 

			Query query = getSession().createQuery(str.toString());
			query.setParameter("mobileNos", mobileNos);
			return  (Long) query.uniqueResult();
		}
		
		
		/*public List<Object[]> getMissedCallsCountByDistrict(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select count(tc.tdpCadreId),tc.userAddress.district.districtId from TdpCadre tc " +
					" where tc.mobileNo in (:mobileNos) and tc.isDeleted = 'N' and tc.enrollmentYear='2014' "); 
			
		
			if(stateId.longValue() == 1){
				str.append(" and tc.userAddress.district.districtId > 10 ");
			}else if(stateId.longValue() == 2){
				str.append(" and tc.userAddress.district.districtId < 11 ");
			}
			str.append(" group by tc.userAddress.district.districtId ");
			Query query = getSession().createQuery(str.toString());
			
			query.setParameterList("mobileNos", mobileNos);
			return query.list();
		}*/
		
		public List<Object[]> getMissedCallsCountByDistrict(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select COUNT(TC.tdp_cadre_id),UA.district_id from tdp_cadre TC,user_address UA " +
					" where TC.mobile_no in (:mobileNos) and TC.is_deleted = 'N' and TC.enrollment_year = '2014' and TC.address_id = UA.user_address_id "); 
			
			if(stateId.longValue() == 1){
				str.append(" and UA.district_id between 11 and 23  ");
			}else if(stateId.longValue() == 2){
				str.append(" and UA.district_id between 1 and 10  ");
			}
			str.append(" group by UA.district_id ");
			Query query = getSession().createSQLQuery(str.toString());
			
			query.setParameterList("mobileNos", mobileNos);
			return query.list();
		}
		
		/*public List<Object[]> getMissedCallsSingleMemberCountByDistrict(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select count(distinct tc.mobileNo),tc.userAddress.district.districtId from TdpCadre tc " +
					" where tc.mobileNo in (:mobileNos) and tc.isDeleted = 'N' and  tc.enrollmentYear='2014' "); 			
		
			if(stateId.longValue() == 1){
				str.append(" and tc.userAddress.district.districtId > 10 ");
			}else if(stateId.longValue() == 2){
				str.append(" and tc.userAddress.district.districtId < 11 ");
			}
			str.append(" group by tc.userAddress.district.districtId ");
			Query query = getSession().createQuery(str.toString());
			
			query.setParameterList("mobileNos", mobileNos);
			return query.list();
		}*/
		
		public List<Object[]> getMissedCallsSingleMemberCountByDistrict(List<String> mobileNos,Long stateId)
		{
			StringBuilder str = new StringBuilder();
			str.append("select COUNT(DISTINCT TC.mobile_no),UA.district_id from tdp_cadre TC,user_address UA " +
					" where TC.mobile_no in (:mobileNos) and TC.is_deleted = 'N' and  TC.enrollment_year = '2014' and TC.address_id = UA.user_address_id "); 			
		
			if(stateId.longValue() == 1){
				str.append(" and UA.district_id between 11 and 23  ");
			}else if(stateId.longValue() == 2){
				str.append(" and UA.district_id between 1 and 10  ");
			}
			str.append(" group by UA.district_id ");
			Query query = getSession().createSQLQuery(str.toString());
			
			query.setParameterList("mobileNos", mobileNos);
			return query.list();
		}
		public List<Object[]> districtWiseRegCountForDistrict(Long stateId){
			
			StringBuilder str = new StringBuilder();
			str.append("select model.userAddress.district.districtId, count(distinct model.tdpCadreId) " +
					" from TdpCadre model where model.enrollmentYear='2014' and  model.isDeleted='N' " );
			
			if(stateId.longValue() == 0L)
				str.append(" and model.userAddress.district.districtId between 1 and 23 ");
			else if(stateId.longValue() == 1L)
				str.append(" and model.userAddress.district.districtId between 11 and 23 ");
			else if(stateId.longValue() == 2L)
				str.append(" and model.userAddress.district.districtId between 1 and 10 ");
			
			str.append(" group by model.userAddress.district.districtId");
			Query query = getSession().createQuery(str.toString());
			
			return  query.list();
	 
	 }
		
		public List<Object[]> constituencyWiseRegCountForDistrict(Long districtId){
			
			Query query=getSession().createQuery("select " +
					" model.userAddress.constituency.constituencyId," +
					" count(distinct model.tdpCadreId)" +
					" from TdpCadre model " +
					" where model.enrollmentYear='2014' and " +
					"       model.isDeleted='N' and " +
					"        model.userAddress.district.districtId=:districtId " +
					" group by model.userAddress.constituency.constituencyId ");
					 
			 query.setParameter("districtId", districtId);
			 return  query.list();
	 
	 }
	public List<Object[]> constituencyWiseRecivingMissedCallsCount(Long districtId,Date startDate,Date endDate){
		
		Query query=getSession().createQuery("select model.userAddress.constituency.constituencyId,count(distinct model1.mobileNumber) " +
				" from TdpCadre model,CadreMissedCallCampaign model1  " +
				" where model.mobileNo=model1.mobileNumber and model.enrollmentYear='2014' and model.isDeleted='N' and " +
				" date(model1.insertedTime) between :startDate and :endDate and " +
				" model.userAddress.district.districtId=:districtId " +
				" group by model.userAddress.constituency.constituencyId "); 
				
		
		query.setParameter("districtId",districtId);
		query.setParameter("startDate",startDate);
		query.setParameter("endDate",endDate);
		return query.list();
	}

	public List<Object[]> multiMemberRegisteredForMobile(Long districtId,Date startDate,Date endDate){

		Query query=getSession().createQuery("select model.userAddress.constituency.constituencyId,model1.mobileNumber,count( model1.mobileNumber) " +
				" from TdpCadre model,CadreMissedCallCampaign model1  " +
				" where model.mobileNo=model1.mobileNumber and model.enrollmentYear='2014' and model.isDeleted='N' and " +
				" date(model1.insertedTime) between :startDate and :endDate and " +
				" model.userAddress.district.districtId=:districtId " +
				" group by model.userAddress.constituency.constituencyId,model1.mobileNumber ");
				
		
		query.setParameter("districtId",districtId);
		query.setParameter("startDate",startDate);
		query.setParameter("endDate",endDate);
		return query.list();

	}
	
	public List<Object[]> getConstituencyWiseMemberMobileNumbersCount(Long districtId,Date startDate,Date endDate)
	{
		StringBuilder str = new StringBuilder();
		str.append("SELECT TC.userAddress.constituency.constituencyId,TC.mobileNo,TC.tdpCadreId,COUNT(TC.tdpCadreId) from CadreMissedCallCampaign model,TdpCadre TC " +
				" where TC.mobileNo = model.mobileNumber and TC.isDeleted = 'N' and TC.enrollmentYear='2014' " +
				" and date(model.insertedTime) >=:startDate and date(model.insertedTime) <= :endDate" +
				"  and TC.userAddress.district.districtId = :districtId");
	
		str.append(" group by TC.userAddress.constituency.constituencyId,TC.mobileNo,TC.tdpCadreId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("districtId",districtId);
		query.setParameter("startDate",startDate);
		query.setParameter("endDate",endDate);
		return  query.list();
	}
	
	
	public List<Object[]> getLocationWiseCadrePrintedCount(Set<Long> locationIds,String locationType,Long constituencyId){
		StringBuilder queryStr = new StringBuilder();
		//0locationId,1count,2jobids
		queryStr.append("select "+getLocation(locationType));
		
		queryStr.append(",count(model.tdpCadreId) from TdpCadre model where "+getLocation(locationType)+"" +
				" in(:locationIds) and model.enrollmentYear ='2014' and  "+ getLocation(locationType)+" is not null and model.isDeleted = 'N' and constituencyId is not null " +
						" and model.cardNumber is not null  ");
		if(constituencyId != null){
			queryStr.append(" and model.userAddress.constituency.constituencyId =:constituencyId ");
		}
		queryStr.append(" group by "+getLocation(locationType)+" ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("locationIds", locationIds);
		if(constituencyId != null){
			  query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	
	public Long getTdpCadreCountInALocation(List<Long> locationValue,String type,Long year)
	{
		if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
			type = "MUNICIPAL-CORP-GMC";
		
		if(type.equalsIgnoreCase("customWard"))
			type ="WARD";
		String str = "select count(model.tdpCadreId) from TdpCadre model where model.isDeleted = 'N' and  ";
		if(year != null && year > 0)
			str +=  " model.enrollmentYear=:year and " ;
			
		if(type.equalsIgnoreCase("constituency"))
			str += " model.userAddress.constituency.constituencyId in (:locationValue) ";
		else if(type.equalsIgnoreCase("mandal"))
			str += " model.userAddress.tehsil.tehsilId in (:locationValue) ";
		else if(type.equalsIgnoreCase("panchayat"))
			str += " model.userAddress.panchayat.panchayatId in (:locationValue) ";
		else if(type.equalsIgnoreCase("MUNICIPAL-CORP-GMC"))
			str += " model.userAddress.localElectionBody.localElectionBodyId in (:locationValue) ";
		else if(type.equalsIgnoreCase("hamlet"))
			str += " model.userAddress.hamlet.hamletId in (:locationValue) ";
		else if(type.equalsIgnoreCase("WARD"))
			str += " model.userAddress.ward.constituencyId in (:locationValue) ";
		else if(type.equalsIgnoreCase("BOOTH"))
			str += " model.userAddress.booth.boothId in (:locationValue) ";
		Query query = getSession().createQuery(str);
		query.setParameterList("locationValue", locationValue);
		if(year != null && year > 0)
			query.setParameter("year", year);
		return (Long)query.uniqueResult();
		
	}
	
	public List<Object[]> getTdpCadreVoterIDs(List<Long> locationValue,String type,Integer startIndex,
			Integer maxRecords,String columnName ,String order,Long year)
	{
	

		if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
			type = "MUNICIPAL-CORP-GMC";
		
		if(type.equalsIgnoreCase("customWard"))
			type ="WARD";
		String str = "";
		str += "select model.firstname,model.lastname,model.gender,model.mobileNo,voter.voterId," +
				" caste.casteName,tehsil.tehsilName,hamlet.hamletName,booth.partNo " ;
		str+=  " from TdpCadre model " +
		 		" left join model.voter voter " +
		 		" left join model.casteState.caste caste " +
		 		" left join model.userAddress.tehsil tehsil"+
				" left join model.userAddress.hamlet hamlet"+
				" left join model.userAddress.booth booth";
		
		str +=  " where model.isDeleted = 'N' and  ";
		if(year != null && year > 0)
		str +=  " model.enrollmentYear=:year and " ;
		
		if(type.equalsIgnoreCase("constituency"))
			str += " model.userAddress.constituency.constituencyId in (:locationValue) ";
		else if(type.equalsIgnoreCase("mandal"))
			str += " model.userAddress.tehsil.tehsilId in (:locationValue) ";
		else if(type.equalsIgnoreCase("panchayat"))
			str += " model.userAddress.panchayat.panchayatId in (:locationValue) ";
		else if(type.equalsIgnoreCase("MUNICIPAL-CORP-GMC"))
			str += " model.userAddress.localElectionBody.localElectionBodyId in (:locationValue) ";
		else if(type.equalsIgnoreCase("hamlet"))
			str += " model.userAddress.hamlet.hamletId in (:locationValue) ";
		else if(type.equalsIgnoreCase("WARD"))
			str += " model.userAddress.ward.constituencyId in (:locationValue) ";
		else if(type.equalsIgnoreCase("BOOTH"))
			str += " model.userAddress.booth.boothId in (:locationValue) ";
		if(columnName.equalsIgnoreCase("voterIDCardNo")||columnName.equalsIgnoreCase("age")||columnName.equalsIgnoreCase("gender")||columnName.equalsIgnoreCase("houseNo")||columnName.equalsIgnoreCase("relativeName"))
			str += "order by model.voter."+columnName+" "+order;
		else
			str += "order by model."+columnName+" "+order;	   
		Query query = getSession().createQuery(str);
		query.setParameterList("locationValue", locationValue);
		if(year != null && year > 0)
		query.setParameter("year", year);
		query.setFirstResult(startIndex);
		query.setMaxResults(maxRecords);
		return query.list();
		
		
	}

	public Long getVoterIdExistCadreInABooth(Long constituencyId,Long publicationDateId,String partNo)
	{
		  Query query = getSession().createQuery("select count(model.tdpCadreId) from TdpCadre model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
		  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
		  		" model2.booth.partNo = :partNo and model.familyVoter is null ");
		  query.setParameter("constituencyId",constituencyId);
		  query.setParameter("publicationDateId",publicationDateId);
		  query.setParameter("partNo",partNo);
		  
		  return (Long)query.uniqueResult();
	}

	public Long getFamilyVoterIdExistCadreInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createQuery("select count(model.tdpCadreId) from TdpCadre model,BoothPublicationVoter model2 where model.familyVoter.voterId = model2.voter.voterId and " +
		  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
		  		" model2.booth.partNo = :partNo");
		  query.setParameter("constituencyId",constituencyId);
		  query.setParameter("publicationDateId",publicationDateId);
		  query.setParameter("partNo",partNo);
		  
		  return (Long)query.uniqueResult();
	  }

	public List<String> getVoterIdExistCadreFamiliesInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createQuery("select distinct(model2.voter.houseNo) from TdpCadre model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
			  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
			  		" model2.booth.partNo = :partNo and model.familyVoter is null ");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
		  return query.list();
	  }

	public List<String> getFamilyVoterIdExistCadreFamiliesInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createQuery("select distinct(model2.voter.houseNo) from TdpCadre model,BoothPublicationVoter model2 where model.familyVoter.voterId = model2.voter.voterId and " +
			  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
			  		" model2.booth.partNo = :partNo");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
		  return query.list();
	  }

	public List<Object[]> getCadreAvailableFamiliesInABooth(Long constituencyId,Long publicationDateId,String partNo,List<String> houseNosList)
	  {
		  Query query = getSession().createSQLQuery("SELECT V.house_no,COUNT(TD1.tdp_cadre_id),COUNT(TD2.tdp_cadre_id) FROM booth B,booth_publication_voter BPV,voter V " +
		  		" LEFT OUTER JOIN tdp_cadre TD1 ON V.voter_id = TD1.voter_id AND TD1.enrollment_year = 2014 AND TD1.is_deleted = 'N' " +
		  		" LEFT OUTER JOIN tdp_cadre TD2 ON V.voter_id = TD2.family_voterId AND TD2.enrollment_year = 2014 AND TD2.is_deleted = 'N' " +
		  		" WHERE B.booth_id = BPV.booth_id and BPV.voter_id = V.voter_id AND B.constituency_id = :constituencyId AND B.publication_date_id = :publicationDateId AND " +
		  		" B.part_no = :partNo AND V.house_no IN (:houseNosList) GROUP BY V.house_no ");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
			  query.setParameterList("houseNosList",houseNosList);
		  
		  return query.list();
	  }
	  
	  public List<Object[]> getVoterAndCadreDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createSQLQuery("SELECT V.voter_id_card_no,V.name,V.age,V.gender,VA.area,V.house_no,V.relative_name,V.relationship_type,B.part_no, " +
		  		" TD1.tdp_cadre_id,TD1.first_name,TD1.mobile_no,CT1.caste_name,TD2.tdp_cadre_id,TD2.first_name,TD2.mobile_no,CT2.caste_name,V.voter_id FROM booth_publication_voter BPV,booth B,voter V " +
		  		" LEFT JOIN voter_area VA ON V.voter_id = VA.voter_id " +
		  		" LEFT JOIN tdp_cadre TD1 ON V.voter_id = TD1.voter_id AND TD1.enrollment_year = 2014 AND TD1.is_deleted = 'N' AND TD1.family_voterId is null " +
		  		" LEFT JOIN tdp_cadre TD2 ON V.voter_id = TD2.family_voterId AND TD2.enrollment_year = 2014 AND TD2.is_deleted = 'N' " +
		  		" LEFT JOIN caste_state CS1 ON TD1.caste_state_id = CS1.caste_state_id LEFT OUTER JOIN caste CT1 ON CS1.caste_id = CT1.caste_id " +
		  		" LEFT JOIN caste_state CS2 ON TD2.caste_state_id = CS2.caste_state_id LEFT OUTER JOIN caste CT2 ON CS2.caste_id = CT2.caste_id " +
		  		" WHERE B.booth_id = BPV.booth_id and BPV.voter_id = V.voter_id AND B.constituency_id = :constituencyId AND B.publication_date_id = :publicationDateId AND B.part_no = :partNo ORDER BY BPV.serial_no ");
		  
		  query.setParameter("constituencyId",constituencyId);
		  query.setParameter("publicationDateId",publicationDateId);
		  query.setParameter("partNo",partNo);
		  return query.list();
	  }

	  public List<Object[]> getCadreBasicDetailsByVoterId(Long constituencyId,Long publicationDateId,String partNo,Long voterId)
	  {
		  Query query = getSession().createQuery("select model.firstname,model.mobileNo,model.casteState.caste.casteName from TdpCadre model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
			  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
			  		" model2.booth.partNo = :partNo and model.familyVoter is null and model2.voter.voterId = :voterId");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
			  query.setParameter("voterId",voterId);
			  return query.list();
	  }
	  
	  public List<Object[]> getFamilyCadreBasicDetailsByVoterId(Long constituencyId,Long publicationDateId,String partNo,Long voterId)
	  {
		  Query query = getSession().createQuery("select model.firstname,model.mobileNo,model.casteState.caste.casteName from TdpCadre model,BoothPublicationVoter model2 where model.familyVoter.voterId = model2.voter.voterId and " +
			  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
			  		" model2.booth.partNo = :partNo and model2.voter.voterId = :voterId");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
			  query.setParameter("voterId",voterId);
			  return query.list();
	  }

	  public List<Object[]> getCadreBasicDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createQuery("select model2.voter.voterIDCardNo,model.firstname,model.mobileNo,model.casteState.caste.casteName from TdpCadre model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and " +
			  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
			  		" model2.booth.partNo = :partNo and model.familyVoter is null");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
			  return query.list();
	  }
	  
	  public List<Object[]> getFamilyCadreBasicDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createQuery("select model2.voter.voterIDCardNo,model.firstname,model.mobileNo,model.casteState.caste.casteName from TdpCadre model,BoothPublicationVoter model2 where model.familyVoter.voterId = model2.voter.voterId and " +
			  		" model.enrollmentYear = 2014 and model.isDeleted = 'N' and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId and " +
			  		" model2.booth.partNo = :partNo");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
			  return query.list();
	  }
	  
	  public List<Object[]> getCadreDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createSQLQuery("SELECT TD.family_voterId,B.part_no,B.location,V.voter_id_card_no,V.name,V.age,V.gender,TD.mobile_no,V.relative_name,V.house_no,CT.caste_name,ED.qualification,O.occupation,TD.cadre_aadher_no " +
		  		" FROM booth_publication_voter BPV,booth B,voter V,tdp_cadre TD LEFT OUTER JOIN caste_state CS ON TD.caste_state_id = CS.caste_state_id LEFT OUTER JOIN caste CT ON CS.caste_id = CT.caste_id " +
		  		" LEFT OUTER JOIN educational_qualifications ED ON TD.education_id = ED.educational_qualification_id LEFT OUTER JOIN occupation O ON TD.occupation_id = O.occupation_id WHERE " +
		  		" BPV.voter_id = TD.voter_id AND BPV.voter_id = V.voter_id AND BPV.booth_id = B.booth_id AND TD.family_voterId IS NULL AND B.constituency_id = :constituencyId AND B.publication_date_id = :publicationDateId AND " +
		  		" TD.enrollment_year = 2014 AND TD.is_deleted = 'N' AND B.part_no = :partNo ORDER BY BPV.serial_no");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
			  return query.list();
	  }

	  public List<Object[]> getFamilyCadreDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createSQLQuery("SELECT TD.family_voterId,B.part_no,B.location,V.voter_id_card_no,TD.first_name,TD.age,TD.gender,TD.mobile_no,TD.relative_name,TD.house_no,CT.caste_name,ED.qualification,O.occupation,TD.cadre_aadher_no " +
		  		" FROM booth_publication_voter BPV,booth B,voter V,tdp_cadre TD LEFT OUTER JOIN caste_state CS ON TD.caste_state_id = CS.caste_state_id LEFT OUTER JOIN caste CT ON CS.caste_id = CT.caste_id " +
		  		" LEFT OUTER JOIN educational_qualifications ED ON TD.education_id = ED.educational_qualification_id LEFT OUTER JOIN occupation O ON TD.occupation_id = O.occupation_id WHERE " +
		  		" BPV.voter_id = TD.family_voterId AND BPV.voter_id = V.voter_id AND BPV.booth_id = B.booth_id AND TD.enrollment_year = 2014 AND TD.is_deleted = 'N' AND " +
		  		" B.constituency_id = :constituencyId AND B.publication_date_id = :publicationDateId AND B.part_no = :partNo ORDER BY BPV.serial_no ");
			  query.setParameter("constituencyId",constituencyId);
			  query.setParameter("publicationDateId",publicationDateId);
			  query.setParameter("partNo",partNo);
			  return query.list();
	  }
	  
	  public List<Object[]> getVoterHouseWiseDetailsInABooth(Long constituencyId,Long publicationDateId,String partNo)
	  {
		  Query query = getSession().createSQLQuery("SELECT V.house_no,VA.area,COUNT(V.voter_id),COUNT(DISTINCT TD.tdp_cadre_id),V.name,TD.mobile_no,CT.caste_name FROM booth_publication_voter BPV,booth B,voter V LEFT OUTER JOIN voter_area VA ON V.voter_id = VA.voter_id" +
		  		" LEFT OUTER JOIN tdp_cadre TD ON V.voter_id = TD.voter_id AND TD.enrollment_year = 2014 AND TD.is_deleted = 'N' LEFT OUTER JOIN caste_state CS ON TD.caste_state_id = CS.caste_state_id LEFT OUTER JOIN caste CT on CS.caste_id = CT.caste_id WHERE " +
		  		" V.voter_id = BPV.voter_id AND BPV.booth_id = B.booth_id AND B.constituency_id = :constituencyId AND B.publication_date_id = :publicationDateId AND B.part_no = :partNo GROUP BY V.house_no ORDER BY BPV.serial_no");
		  query.setParameter("constituencyId",constituencyId);
		  query.setParameter("publicationDateId",publicationDateId);
		  query.setParameter("partNo",partNo);
		  return query.list();
	  }
	  
		public List<Object[]> getTdpCadreCountInALocationForEnrollment(List<Long> locationValue,String type)
		{
			if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION"))
				type = "MUNICIPAL-CORP-GMC";
			
			if(type.equalsIgnoreCase("customWard"))
				type ="WARD";
			String str = "select model.enrollmentYear,count(model.tdpCadreId) from TdpCadre model where model.isDeleted = 'N'  and ";
			
			if(type.equalsIgnoreCase("constituency"))
				str += " model.userAddress.constituency.constituencyId in (:locationValue) ";
			else if(type.equalsIgnoreCase("mandal"))
				str += " model.userAddress.tehsil.tehsilId in (:locationValue) ";
			else if(type.equalsIgnoreCase("MUNICIPAL-CORP-GMC"))
				str += " model.userAddress.localElectionBody.localElectionBodyId in (:locationValue) ";
			else if(type.equalsIgnoreCase("panchayat"))
				str += " model.userAddress.panchayat.panchayatId in (:locationValue) ";
			else if(type.equalsIgnoreCase("hamlet"))
				str += " model.userAddress.hamlet.hamletId in (:locationValue) ";
			else if(type.equalsIgnoreCase("WARD"))
				str += " model.userAddress.ward.constituencyId in (:locationValue) ";
			else if(type.equalsIgnoreCase("BOOTH"))
				str += " model.userAddress.booth.boothId in (:locationValue) ";
			str+=" group by model.enrollmentYear order by model.enrollmentYear desc";
			Query query = getSession().createQuery(str);
			query.setParameterList("locationValue", locationValue);
			query.setMaxResults(2);
			return query.list();
			
		}
		public List<Object[]> getMobileNosByMemberShipId(String queryStr)	{
			Query query = getSession().createQuery("select model.memberShipNo , model.mobileNo from TdpCadre model " +
					" where  model.isDeleted = 'N' and model.enrollmentYear = '2014' and ("+queryStr+") ");
			return query.list();
		}
		
		
		
		public List<Object[]> getMemberDetlsByMembershipNo(String queryStr)
		{
			StringBuilder str = new StringBuilder();
			str.append("select distinct model.firstname,model.mobileNo,model.age,model.gender,model.memberShipNo,model.image");
			str.append(" from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = '2014' and ("+queryStr+") ");
			Query query = getSession().createQuery(str.toString());
			return query.list();
		}
		
		
		public List<Object[]> getMemberAddressDetlsByMembershipNo(String queryStr)
		{
			StringBuilder str = new StringBuilder();
			str.append("select model.firstname,model.mobileNo,model.age,model.gender,model.memberShipNo ");
			str.append(" ,district.districtName");
			str.append(" ,constituency.name");	
			str.append(" ,tehsil.tehsilName");
			str.append(" ,panc.panchayatName");
			str.append(" ,localElectionBody.name");
			str.append(" ,model.cardNumber,model.image");
			str.append(" from TdpCadre model left join model.userAddress.panchayat panc ");
			str.append(" left join model.userAddress.tehsil tehsil ");
		    str.append(" left join model.userAddress.constituency constituency ");
			str.append(" left join model.userAddress.localElectionBody localElectionBody ");
			str.append(" left join model.userAddress.district district ");
			str.append(" where  model.isDeleted = 'N' and model.enrollmentYear = '2014' and ("+queryStr+") ");
			Query query = getSession().createQuery(str.toString());

			return query.list();
		}
		
		public List<String> checkForMemberExists(String queryStr)
		{
			Query query = getSession().createQuery("select model.memberShipNo from TdpCadre model where  " +
					" model.isDeleted = 'N' and model.enrollmentYear = '2014' and ("+queryStr+") ");
			return query.list();
		}
		
		public Long getTdpCadreCountForLocations(String userAccessType,List<Long> constituencyIds){
			StringBuilder str = new StringBuilder();
			str.append("select count(model.tdpCadreId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' ");
			
			if(userAccessType.equalsIgnoreCase("TS"))
				str.append(" and model.userAddress.constituency.district.districtId between 1 and 10 ");
			
			if(userAccessType.equalsIgnoreCase("AP"))
				str.append(" and model.userAddress.constituency.district.districtId between 11 and 23 ");
		
			str.append(" and model.userAddress.state.stateId = 1 ");
			
			if(userAccessType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			{
				if(constituencyIds != null && constituencyIds.size() > 0)
					str.append(" and  model.userAddress.constituency.constituencyId in(:constituencyIds)  ");
			}
			if(userAccessType.equalsIgnoreCase(IConstants.DISTRICT))
			{
				if(constituencyIds != null && constituencyIds.size() > 0)
					str.append(" and  model.userAddress.district.districtId in(:constituencyIds)  ");
			}
			
			Query query = getSession().createQuery(str.toString());
			
			if(constituencyIds != null && constituencyIds.size() > 0)
				query.setParameterList("constituencyIds", constituencyIds);
			return (Long) query.uniqueResult();
		}
		
		public List<Object[]> getVoterCadreCasteDetailsBySearchCriteria(Long stateId,String locationType,List<Long> locationIdsList,Long casteStateId,String nameStr)
		{
			if(locationType != null)
			{
				StringBuilder str = new StringBuilder();
				boolean isStateSelected = false;
				str.append(" select ");
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					str.append(" constituency.constituencyId, count(*), constituency.name ");
				}
				else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.DISTRICT) || locationType.equalsIgnoreCase(IConstants.STATE)))
				{
					str.append(" district.districtId, count(*), district.districtName ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
				{
					str.append(" tehsil.tehsilId, count(*), tehsil.tehsilName ");
				}	
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				{
					str.append(" panc.panchayatId, count(*), panc.panchayatName ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				{
					str.append(" localElectionBody.localElectionBodyId, count(*), localElectionBody.name ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
				{
					str.append(" ward.constituencyId, count(*), ward.name ");
				}	
				
				str.append(" from TdpCadre model left join model.userAddress.panchayat panc ");
				str.append(" left join model.userAddress.tehsil tehsil ");
			    str.append(" left join model.userAddress.constituency constituency ");
				str.append(" left join model.userAddress.localElectionBody localElectionBody ");
				str.append(" left join model.userAddress.district district ");
				str.append(" left join model.userAddress.ward ward ");
				str.append(" left join model.casteState casteState ");
				str.append(" where  model.isDeleted = 'N' and model.enrollmentYear = '2014' ");
				
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					str.append(" and district.districtId  in (:locationIdsList) and constituency.constituencyId is not null  ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
				{
					str.append(" and district.districtId in (:locationIdsList) and constituency.constituencyId is not null ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
				{
					str.append(" and constituency.constituencyId  in (:locationIdsList) and  tehsil.tehsilId is not null ");
				}	
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				{
					str.append(" and tehsil.tehsilId in (:locationIdsList) and panc.panchayatId is not null and localElectionBody.localElectionBodyId is null ");
				}	
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				{
					str.append(" and localElectionBody.localElectionBodyId in (:locationIdsList) ");
				}	
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
				{
					str.append(" and ward.constituencyId in (:locationIdsList) and ward.constituencyId is not null and  localElectionBody.localElectionBodyId is not null ");
				}	
				else if(stateId != null &&  stateId.longValue() ==0L) 	//AP & TS
				{
					isStateSelected = true;
					str.append(" and (district.districtId between 1 and 23)  ");
				}
				else if(stateId != null &&  stateId.longValue() ==1L)	//AP
				{
					isStateSelected = true;
					str.append(" and ( district.districtId between 11 and 23 ) ");
				}
				else if(stateId != null &&  stateId.longValue() ==2L)	//TS
				{
					isStateSelected = true;
					str.append(" and ( district.districtId  between 1 and 10 ) ");
				}
				
				if(casteStateId != null && casteStateId.longValue() != 0L)
				{
					str.append(" and casteState.casteStateId =:casteStateId");
				}
				if(nameStr != null && nameStr.trim().length() != 0L)
				{
					str.append("  and ( model.firstname like '%"+nameStr+"%' or model.lastname like '%"+nameStr+"%' ) ");
				}
				
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				{
					str.append(" group by constituency.constituencyId order by constituency.name ");
				}
				else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.DISTRICT) || locationType.equalsIgnoreCase(IConstants.STATE)))
				{
					str.append(" group by district.districtId  order by district.districtName ");
				}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
				{
					str.append(" group by tehsil.tehsilId  order by tehsil.tehsilName");
				}	
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				{
					str.append(" group by panc.panchayatId order by panc.panchayatName ");
				}	
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
				{
					str.append(" group by  localElectionBody.localElectionBodyId order by localElectionBody.name ");
				}	
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
				{
					str.append(" group by ward.constituencyId order by  ward.name   ");
				}	
				Query query = getSession().createQuery(str.toString());
				
				if(casteStateId != null && casteStateId.longValue() != 0L)
				{
					query.setParameter("casteStateId", casteStateId);
				}			
				if(!isStateSelected && (locationIdsList != null && locationIdsList.get(0) != 0L && locationIdsList.size()>  0))
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
		
		/*public List<Object[]> getMobileNoByTdpCadreIdList(List<Long> tdpCadreIdsList,int firstRecord,int maxResult)
		{
			if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			{
				StringBuilder queryStr = new StringBuilder();
				
				queryStr.append(" select distinct model.tdpCadreId, model.firstname, model.relativename,  ");
				queryStr.append(" model.gender ,model.memberShipNo, model.refNo , model.mobileNo, model.image, model.cardNumber,model.age,date(model.dateOfBirth), constituency.name,voter.age,occupatn.occupation, ");
				queryStr.append(" tehsil.tehsilName , panc.panchayatName,localElectionBody.name,district.districtName,caste.casteName,voter.voterIDCardNo, electionType.electionType, model.houseNo,  ");
				queryStr.append(" constituency.constituencyId, tehsil.tehsilId, panc.panchayatId, localElectionBody.localElectionBodyId, district.districtId,voter.houseNo,model.aadheerNo ");
				queryStr.append(" from TdpCadre model left join model.userAddress.panchayat panc ");
				queryStr.append(" left join model.userAddress.tehsil tehsil ");
				queryStr.append(" left join model.userAddress.constituency constituency ");
				queryStr.append(" left join model.userAddress.localElectionBody localElectionBody ");
				queryStr.append(" left join model.userAddress.localElectionBody.electionType electionType ");
				queryStr.append(" left join model.userAddress.district district ");
				queryStr.append(" left join model.occupation occupatn ");
				queryStr.append(" left join model.voter voter ");
				queryStr.append(" left join model.casteState.caste caste ");
				queryStr.append(" left join model.familyVoter familyVoter ");
				queryStr.append(" where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
				queryStr.append(" and model.tdpCadreId in (:tdpCadreIdsList)  ");
				queryStr.append(" order by model.firstname ");
				
				Query query = getSession().createQuery(queryStr.toString());
				query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
				if(firstRecord >= 0 && maxResult>0)
				{
					query.setFirstResult(firstRecord);
					query.setMaxResults(maxResult);
				}
				
				return query.list();
			}
			else
			{
				return null;
			}
		}*/
		
		public List<Object[]> getMobileNoByTdpCadreIdList(List<Long> tdpCadreIdsList,int firstRecord,int maxResult)
		{
			if(tdpCadreIdsList != null && tdpCadreIdsList.size()>0)
			{
				StringBuilder queryStr = new StringBuilder();
				
				queryStr.append(" select distinct TC.tdp_cadre_id , TC.first_name ,TC.relative_name, TC.gender , TC.membership_id, TC.ref_no, TC.mobile_no, "+	
						" TC.image ,TC.card_number, TC.age, date(TC.date_of_birth), C.name,V.age,O.occupation, T.tehsil_name,P.panchayat_name,LEB.name,"+	
						" D.district_name,CA.caste_name,V.voter_id_card_no, ET.election_type, TC.house_no, C.constituency_id, T.tehsil_id, P.panchayat_id,"+	
						"  LEB.local_election_body_id,  D.district_id, V.house_no,TC.cadre_aadher_no, TBC.tdp_basic_committee_id, TR.role , TDC.tdp_committee_level_id , "+	
						" TDC.tdp_committee_level_value, TCR.tdp_committee_role_id ,TCL.tdp_committee_level ,DC.constituency_no "+	
								
						" 	from  "+	
						" 	tdp_cadre TC  LEFT JOIN voter V on TC.voter_id = V.voter_id "+	
						"   LEFT JOIN occupation O on TC.occupation_id = O.occupation_id"+	
						" 	  LEFT JOIN  caste_state CS on TC.caste_state_id = CS.caste_state_id "+	
					   " 	  LEFT JOIN caste CA on CS.caste_id = CA.caste_id "+	
						" 			LEFT JOIN tdp_committee_member TCM on TCM.tdp_cadre_id = TC.tdp_cadre_id"+	
						" 			LEFT JOIN tdp_committee_role TCR on TCM.tdp_committee_role_id = TCR.tdp_committee_role_id"+	
						" 			LEFT JOIN tdp_roles TR on TCR.tdp_roles_id = TR.tdp_roles_id"+	
						" 			LEFT JOIN tdp_committee TDC on TCR.tdp_committee_id = TDC.tdp_committee_id"+	
											" 			LEFT JOIN tdp_basic_committee TBC on TDC.tdp_basic_committee_id = TBC.tdp_basic_committee_id"+	
						" 		  LEFT JOIN tdp_committee_level TCL on TDC.tdp_committee_level_id = TCL.tdp_committee_level_id"+	
						" 		  , user_address UA LEFT JOIN district D on UA.district_id = D.district_id "+	
						" 		  LEFT JOIN constituency C on UA.constituency_id = C.constituency_id " +
						"	LEFT JOIN delimitation_constituency DC on DC.constituency_id = C.constituency_id "+	
						" 		  LEFT JOIN tehsil T  on UA.tehsil_id = T.tehsil_id"+	
						" 		  LEFT JOIN panchayat P  on UA.panchayat_id = P.panchayat_id  "+	
						" 		  LEFT JOIN local_election_body LEB  on UA.local_election_body = LEB.local_election_body_id "+	
						" 			LEFT JOIN election_type ET on LEB.election_type_id = ET.election_type_id "+					 
					" 	 where  "+
					" 		TC.tdp_cadre_id in (:tdpCadreIdsList) and TC.is_deleted = 'N' and TC.enrollment_year = 2014  and TCM.is_active='Y' "+
					" 		AND TC.address_id = UA.user_address_id and DC.year = 2009 order by  TC.first_name ");
				
				Query query = getSession().createSQLQuery(queryStr.toString());
				query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
				if(firstRecord >= 0 && maxResult>0)
				{
					query.setFirstResult(firstRecord);
					query.setMaxResults(maxResult);
				}
				
				return query.list();
			}
			else
			{
				return null;
			}
		}
		
		public Long getRegisterCadreInfoForState(Long stateId,Long enrollmentYear){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select count(distinct model.tdpCadreId) from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = :stateId and model.enrollmentYear = :enrollmentYear");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("stateId", stateId);
			query.setParameter("enrollmentYear", enrollmentYear);
			return (Long) query.uniqueResult();
		}
		public Long getRegisterConstituenciesForState(Long stateId,Long enrollmentYear,List<Long> ids,String type){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select count(distinct model.userAddress.constituency.constituencyId) from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = :enrollmentYear");
			if(type.equalsIgnoreCase(IConstants.STATE))
			queryStr.append(" and  model.userAddress.state.stateId = :stateId");
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			queryStr.append(" and model.userAddress.constituency.district.districtId in(:ids)");
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				queryStr.append(" and model.userAddress.constituency.constituencyId in(:ids)");
			Query query = getSession().createQuery(queryStr.toString());
			if(type.equalsIgnoreCase(IConstants.STATE))
			query.setParameter("stateId", stateId);
			if(type.equalsIgnoreCase(IConstants.DISTRICT) || type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				query.setParameterList("ids", ids);
			query.setParameter("enrollmentYear", enrollmentYear);
			return (Long) query.uniqueResult();
		}
		
		public List<Object[]> getTotalRecordsByState(List<Long> districtIds,String type,Date fromDate,Date toDate,Long stateId){
			StringBuilder str = new StringBuilder();
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
			 str.append("select count(model.tdpCadreId),model.userAddress.constituency.district.districtId"); 
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 str.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId"); 
			else if(type.equalsIgnoreCase(IConstants.TEHSIL))
				str.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId"); 
			else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY) || type.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION))
				str.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId"); 
			 str.append("  from TdpCadre model" +
					" where model.isDeleted = 'N' " +
					" and model.enrollmentYear = 2014 and model.userAddress.constituency.district.state.stateId = :stateId "); 
			 if(fromDate != null && toDate != null){
			   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
			 }
			 if(type.equalsIgnoreCase(IConstants.DISTRICT))
			    str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.district.districtId ");
			 else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
				str.append(" and model.userAddress.constituency.district.districtId in(:districtIds) group by model.userAddress.constituency.constituencyId");
			 else if(type.equalsIgnoreCase(IConstants.TEHSIL))
					str.append(" and model.userAddress.tehsil.tehsilId in(:districtIds) and model.userAddress.localElectionBody.localElectionBodyId is null group by model.userAddress.tehsil.tehsilId");
			 else if(type.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY) || type.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION) )
					str.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:districtIds) group by model.userAddress.localElectionBody.localElectionBodyId");
			Query query = getSession().createQuery(str.toString());
			if(fromDate != null && toDate != null){
				query.setParameter("fromDate",fromDate);
				query.setParameter("toDate",toDate);
			}
			query.setParameter("stateId", stateId);
			query.setParameterList("districtIds", districtIds);
			
			return query.list();
		}
		public List<Object[]> getTotalRecordsByAccessTypeByState(List<Long> districtIds,String type,Date fromDate,Date toDate,Long stateId){
			StringBuilder str = new StringBuilder();
			
			 str.append(" select count(model.tdpCadreId),model.userAddress.constituency.district.districtId "); 
			 
			 str.append("  from TdpCadre model" +
					" where model.isDeleted = 'N' " +
					" and model.enrollmentYear = 2014 and model.userAddress.constituency.district.state.stateId = :stateId "); 
			 if(fromDate != null && toDate != null){
			   str.append(" and date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate ");
			 }
			 
			str.append(" and model.userAddress.constituency.constituencyId in (:districtIds) group by model.userAddress.constituency.district.districtId ");
			 
			
			Query query = getSession().createQuery(str.toString());
			if(fromDate != null && toDate != null){
				query.setParameter("fromDate",fromDate);
				query.setParameter("toDate",toDate);
			}
			query.setParameterList("districtIds", districtIds);
			query.setParameter("stateId", stateId);
			return query.list();
		}
		
		public Long getRegisterCadreInfoForUserBetweenDatesByIds(Date fromDate,Date toDate,List<Long> constiIds,List<Long> districtIds){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select count(model.tdpCadreId) from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
			
			if(fromDate != null){
				queryStr.append(" and date(model.surveyTime) >=:fromDate ");
			}
			
			if(toDate != null){
				queryStr.append(" and date(model.surveyTime) <=:toDate ");
			}
			if(constiIds != null && constiIds.size() > 0)
			queryStr.append(" and  model.userAddress.constituency.constituencyId in(:constiIds)");
			if(districtIds != null && districtIds.size() > 0)
			queryStr.append(" and  model.userAddress.district.districtId in(:districtIds)");
			Query query = getSession().createQuery(queryStr.toString());
			if(fromDate != null){
			   query.setDate("fromDate", fromDate);
			}
			if(toDate != null){
			  query.setDate("toDate", toDate);
			}
			if(constiIds != null && constiIds.size() > 0)
				query.setParameterList("constiIds", constiIds);
			if(districtIds != null && districtIds.size() > 0)
				query.setParameterList("districtIds", districtIds);
			return (Long) query.uniqueResult();
		}
				
		public Long checkVoterRegisteredOrNot(Long voterId,Long enrollmentYear){
			Query query = getSession().createQuery("select count(*)  from TdpCadre model where model.voterId = :voterId  and model.isDeleted = 'N' and model.enrollmentYear =:enrollmentYear");
			query.setParameter("voterId", voterId);
			query.setParameter("enrollmentYear",enrollmentYear);
			return (Long)query.uniqueResult();
		}
		
		public List<Object[]> getMemberInfoyMembershipNo(String queryStr)
		{
			StringBuilder str = new StringBuilder();
			str.append("select model.firstname,model.mobileNo,model.memberShipNo ");
			str.append(" ,model.cardNumber,model.image");
			str.append(" from TdpCadre model "); 
			str.append(" where  model.isDeleted = 'N' and model.enrollmentYear = '2014' and ("+queryStr+") ");
			Query query = getSession().createQuery(str.toString());

			return query.list();
		}
		
		public List getTdpCadreIdByMembership(String queryStr)
		{
			Session session = getSession();
			session.setFlushMode(FlushMode.AUTO);
			Query query = session.createQuery("select model.tdpCadreId from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = '2014' and ("+queryStr+")");
			
			return query.list();
			
		}
		
		public List<Long> getTdpCadreIdByMembershipId(String membershipId)
		{
			Session session = getSession();
			session.setFlushMode(FlushMode.AUTO);
			Query query = session.createQuery("select model.tdpCadreId from TdpCadre model where model.isDeleted = 'N' and model.enrollmentYear = '2014' and model.memberShipNo = :membershipId");
			query.setParameter("membershipId",membershipId);
			return query.list();
			
		}
		
		public List<Object[]> checkCardNumberExists(Long tdpCadreId)
		{
			Query query = getSession().createQuery("select model.cardNumber,model.memberShipNo,model.voterId from TdpCadre model where model.tdpCadreId=:tdpCadreId");
			query.setParameter("tdpCadreId", tdpCadreId);
			return query.list();
			
		}
		
		
		public List<String> getCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId){
			StringBuilder str = new StringBuilder();
			
			str.append(" select model.memberShipNo from TdpCadre model " +
					" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is not null ");
			str.append(query);
			str.append( " order by date(model.surveyTime)" );
			
			Query qry = getSession().createQuery(str.toString());
			
			if(constiId!=null){
				qry.setParameter("constituencyId", constiId);
			}
			if(distId!=null){
				qry.setParameter("districtId", distId);
			}
			if(mandalId!=null){
				qry.setParameter("tehsilId", mandalId);
			}
			if(mobileNo!=null && mobileNo.trim().length()>0){
				qry.setParameter("mobileNo", mobileNo);
			}
			if(trNo!=null && trNo.trim().length()>0){
				qry.setParameter("trNo", trNo);
			}
			if(surveyDate!=null){
				qry.setDate("surveyDate", surveyDate);
			}
			if(townId != null)
			{
				qry.setParameter("townId",townId);
			}
			
			return qry.list();
		}
		
		public List<String> getOtherSttateCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId){
			StringBuilder str = new StringBuilder();
			
			str.append(" select model.memberShipNo from TdpCadre model " +
					" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is not null ");
			str.append(query);
			str.append( " order by date(model.surveyTime)" );
			
			Query qry = getSession().createQuery(str.toString());
			
			if(constiId!=null){
				qry.setParameter("constituencyId", constiId);
			}
			if(distId!=null){
				qry.setParameter("districtId", distId);
			}
			if(mandalId!=null){
				qry.setParameter("tehsilId", mandalId);
			}
			if(mobileNo!=null && mobileNo.trim().length()>0){
				qry.setParameter("mobileNo", mobileNo);
			}
			if(trNo!=null && trNo.trim().length()>0){
				qry.setParameter("trNo", trNo);
			}
			if(surveyDate!=null){
				qry.setDate("surveyDate", surveyDate);
			}
			if(townId != null)
			{
				qry.setParameter("townId",townId);
			}
			return qry.list();
		}
		
		
		public List<String> getNonVoterCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId){
			StringBuilder str = new StringBuilder();
			
			str.append(" select model.memberShipNo from TdpCadre model " +
					" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is null ");
			str.append(query);
			str.append( " order by date(model.surveyTime)" );
			
			Query qry = getSession().createQuery(str.toString());
			
			if(constiId!=null){
				qry.setParameter("constituencyId", constiId);
			}
			if(distId!=null){
				qry.setParameter("districtId", distId);
			}
			if(mandalId!=null){
				qry.setParameter("tehsilId", mandalId);
			}
			if(mobileNo!=null && mobileNo.trim().length()>0){
				qry.setParameter("mobileNo", mobileNo);
			}
			if(trNo!=null && trNo.trim().length()>0){
				qry.setParameter("trNo", trNo);
			}
			if(surveyDate!=null){
				qry.setDate("surveyDate", surveyDate);
			}
			
			if(townId != null)
			{
				qry.setParameter("townId",townId);
			}
			return qry.list();
		}
		
		public List<String> getOtherSttateNonVoterCardNumbersForSearch(String query,Long constiId,String mobileNo,String trNo,Date surveyDate,Long distId,Long mandalId,Long townId){
			StringBuilder str = new StringBuilder();
			
			str.append(" select model.memberShipNo from TdpCadre model " +
					" where model.isDeleted = 'N' and model.enrollmentYear = 2014 and model.voterId is null ");
			str.append(query);
			str.append( " order by date(model.surveyTime)" );
			
			Query qry = getSession().createQuery(str.toString());
			
			if(constiId!=null){
				qry.setParameter("constituencyId", constiId);
			}
			if(distId!=null){
				qry.setParameter("districtId", distId);
			}
			if(mandalId!=null){
				qry.setParameter("tehsilId", mandalId);
			}
			if(mobileNo!=null && mobileNo.trim().length()>0){
				qry.setParameter("mobileNo", mobileNo);
			}
			if(trNo!=null && trNo.trim().length()>0){
				qry.setParameter("trNo", trNo);
			}
			if(surveyDate!=null){
				qry.setDate("surveyDate", surveyDate);
			}
			if(townId != null)
			{
				qry.setParameter("townId",townId);
			}
			
			return qry.list();
		}
		
		public List<Object[]> getCadrePartialDetailsByMemberShip(List<String> memberCardNos)	{
			Query query = getSession().createQuery("select model.memberShipNo , " +
					" model.voterId," +
					" model.firstname," +
					" model.voter.voterId," +
					" model.voter.voterIDCardNo,model.mobileNo,model.userAddress.userAddressId,model.tdpCadreId,model.cardNumber" +
					" from TdpCadre model " +
					" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
			query.setParameterList("memberCardNos", memberCardNos);
			return query.list();
		}
		
		public List<Object[]> getOtherStateCadrePartialDetailsByMemberShip(List<String> memberCardNos)	{
			Query query = getSession().createQuery("select model.memberShipNo , " +
					" model.voterId," +
					" model.firstname," +
					" model.voter.voterId," +
					" model.voter.voterIDCardNo,model.mobileNo,model.userAddress.userAddressId,model.tdpCadreId,model.cardNumber" +
					" from TdpCadre model " +
					" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
			query.setParameterList("memberCardNos", memberCardNos);
			return query.list();
		}
		
		public List<Object[]> getCadrePartialDetailsByMemberShipIdForNonVoters(List<String> memberCardNos)	{
			Query query = getSession().createQuery("select model.memberShipNo , " +
					" model.voterId," +
					" model.firstname," +
					" model.tdpCadreId," +
					" model.mobileNo," +
					" model.userAddress.userAddressId,model.cardNumber from TdpCadre model " +
					" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
			query.setParameterList("memberCardNos", memberCardNos);
			return query.list();
		}
		
		public List<Object[]> getOtherStateCadrePartialDetailsByMemberShipIdForNonVoters(List<String> memberCardNos)	{
			Query query = getSession().createQuery("select model.memberShipNo , " +
					" model.voterId," +
					" model.firstname," +
					" model.tdpCadreId," +
					" model.mobileNo," +
					" model.userAddress.userAddressId,model.cardNumber from TdpCadre model " +
					" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
			query.setParameterList("memberCardNos", memberCardNos);
			return query.list();
		}
		
		public List<Object[]> getMembershipNosByTdpCadreIds(List<Long> tdpCadreIdsList)
		{
			Query query = getSession().createQuery("select model.voterId,model.refNo from TdpCadre model where model.voterId in (:tdpCadreIdsList) and model.isDeleted='N' and model.enrollmentYear = 2014 order by model.tdpCadreId ");
			query.setParameterList("tdpCadreIdsList",tdpCadreIdsList);
			return query.list();
		}
		

		public Long getIsAlreadyTempararyRegistered(Long mobile, String cadreName )
		{
			Query query = getSession().createQuery(" select count(model.tdpCadreId) from TdpCadre model where model.mobileNo like '"+mobile+"' and  " +
					" model.isDeleted='N' and model.enrollmentYear = 2014 and model.firstname like '%"+cadreName+"%' ");

			return (Long) query.uniqueResult();
		}
		
		public List getNewlyRegistredCadreCnt(Date fromDate,Date toDate){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append("select count(model.tdpCadreId) from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
			
			/*if(fromDate != null){
				queryStr.append(" and date(model.surveyTime) >=:fromDate ");
			}
			
			if(toDate != null){
				queryStr.append(" and date(model.surveyTime) <=:toDate ");
			}*/
			
			if((fromDate != null && toDate != null) && fromDate.equals(toDate))
				queryStr.append("  and date(model.surveyTime) =:fromDate");
			else if((fromDate != null))
				queryStr.append("  and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate ");
			Query query = getSession().createQuery(queryStr.toString());
			if((fromDate != null && toDate != null) && fromDate.equals(toDate)){
			   query.setDate("fromDate", fromDate);
			}
			if((fromDate != null && toDate != null) && !fromDate.equals(toDate)){
				query.setDate("fromDate", fromDate);
			  query.setDate("toDate", toDate);
			}
			return query.list();
		}
		
		public List<Object[]> getCadreFormalDetails(List<Long> tdpCadreIds){
			
			Query query = getSession().createQuery(" select distinct  model.tdpCadreId," +
								" model.firstname," +
								" date(model.dateOfBirth)," +
								" model.age," +
								" model.mobileNo," +
								" model.image," +
								" model.memberShipNo," +
								" model.userAddress.constituency.constituencyId," +
								" model.userAddress.constituency.name" +
								" from TdpCadre model" +
								" where model.tdpCadreId in (:tdpCadreIds)" +
								" and model.isDeleted ='N' and model.enrollmentYear = 2014 order by model.tdpCadreId asc ");
			query.setParameterList("tdpCadreIds", tdpCadreIds);
			
			return query.list();
		}
		
		/*public List<Object[]> getCadreFormalDetailsByYear(List<Long> tdpCadreIds,Long enrollmentYear){
					
					Query query = getSession().createQuery(" select distinct  model.tdpCadreId," +
										" model.firstname," +
										" date(model.dateOfBirth)," +
										" model.age," +
										" model.mobileNo," +
										" model.image," +
										" model.memberShipNo," +
										" model.userAddress.constituency.constituencyId," +
										" model.userAddress.constituency.name,model.voter.voterIDCardNo,model.dataSourceType," +
										" model.tdpMemberType.tdpMemberTypeId,model.tdpMemberType.memberType,model.voter.voterId " +
										" from TdpCadre model   " +
										" where model.tdpCadreId in (:tdpCadreIds)" +
										" and model.isDeleted ='N' and model.enrollmentYear = :enrollmentYear and model.tdpMemberType.isDeleted='false' order by model.tdpCadreId asc ");
					query.setParameterList("tdpCadreIds", tdpCadreIds);
					query.setParameter("enrollmentYear", enrollmentYear);
					return query.list();
				}*/
		public List<Object[]> getCadreFormalDetailsByYear(List<Long> tdpCadreIds,Long enrollmentYear){
			
			Query query = getSession().createQuery(" select distinct  model.tdpCadreId," +
								" model.firstname," +
								" date(model.dateOfBirth)," +
								" model.age," +
								" model.mobileNo," +
								" model.image," +
								" model.memberShipNo," +
								" model.userAddress.constituency.constituencyId," +
								" model.userAddress.constituency.name,vot.voterIDCardNo,model.dataSourceType," +
								" model.tdpMemberType.tdpMemberTypeId,model.tdpMemberType.memberType,vot.voterId " +
								" from TdpCadre model left join model.voter vot   " +
								" where model.tdpCadreId in (:tdpCadreIds)" +
								" and model.isDeleted ='N' and model.enrollmentYear = :enrollmentYear and model.tdpMemberType.isDeleted='false' order by model.tdpCadreId asc ");
			query.setParameterList("tdpCadreIds", tdpCadreIds);
			query.setParameter("enrollmentYear", enrollmentYear);
			return query.list();
		}
		public List<Object[]> getCadreDetailsByYearSourceWise(List<Long> tdpCadreIds,Long enrollmentYear){
			Query query = getSession().createQuery(" select distinct  model.tdpCadreId," +
					" model.firstname," +
					" date(model.dateOfBirth)," +
					" model.age," +
					" model.mobileNo," +
					" model.image," +
					" model.memberShipNo," +
					" model.userAddress.constituency.constituencyId," +
					" model.userAddress.constituency.name,vot.voterIDCardNo,model.dataSourceType," +
					" model.tdpMemberType.tdpMemberTypeId,model.tdpMemberType.memberType,vot.voterId " +
					" from TdpCadre model left join model.voter vot   " +
					" where model.tdpCadreId in (:tdpCadreIds)" +
					" and model.isDeleted ='N' and model.enrollmentYear = :enrollmentYear and model.tdpMemberType.isDeleted='false' order by model.tdpCadreId asc ");
					query.setParameterList("tdpCadreIds", tdpCadreIds);
					query.setParameter("enrollmentYear", enrollmentYear);
					return query.list();
		  }
		
		
		public Object[] cadreFormalDetailedInformation(Long cadreId,Long enrollmentYear,Long tdpMemberTypeId){

			
			StringBuilder queryStr=new StringBuilder();
			
			queryStr.append(" select model.tdpCadreId," +//0
					"model.firstname," +
					"date(model.dateOfBirth)," +
					"model.age," +
					"eduQualification.eduQualificationId,eduQualification.qualification," +
					"  occupation.occupationId," +
					"occupation.occupation," +
					"voter.voterId," +
					"panchayat.panchayatName," +
					"tehsil.tehsilName," +
					" constituency.name," +//10
					"model.mobileNo," +
					"constituency.constituencyId," +
					"voter.voterIDCardNo," +
					"model.image," +
					"model.memberShipNo," +
					"model.houseNo " +
					" ,district.districtName," +
					"state.stateName," +
					"caste.casteName," +
					"model.insertedWebUserId," +//20
					"date(model.insertedTime)," +
					"model.emailId,model.dataSourceType" +
					",panchayat.panchayatId," +
					"tehsil.tehsilId," +
					"district.districtId," +
					"state.stateId," +
					"parliamentConstituency.constituencyId," +
					"parliamentConstituency.name , " +
					" booth.boothId," +
					"booth.partNo," +//30
					" ward.constituencyId," +
					" ward.name," +
					"constituency.areaType ," +
					" familyVoter.voterId," +
					"familyVoter.voterIDCardNo, " +//35
					"model.isDeleted," +
					"cadreDeleteReason.cadreDeleteReasonId," +
					" cadreDeleteReason.reason," +
					"voter.gender," +
					"model.relativename," +//40
					"model.relativeType," +
					"ward.name "  +
					" from TdpCadre model " );
			queryStr.append(" left join model.educationalQualifications eduQualification ");
			queryStr.append(" left join model.occupation occupation ");
			queryStr.append(" left join model.voter voter ");
			queryStr.append(" left join model.familyVoter familyVoter ");
			queryStr.append(" left join model.userAddress.booth booth ");
			queryStr.append(" left join model.userAddress.panchayat panchayat ");
			queryStr.append(" left join model.userAddress.ward ward ");
			queryStr.append(" left join model.userAddress.tehsil tehsil ");
			queryStr.append(" left join model.userAddress.constituency constituency ");
			//queryStr.append(" left join model.userAddress.ward constituency1 ");
			queryStr.append(" left join model.userAddress userAddress1 ");
			queryStr.append(" left join model.userAddress.district  district");
			queryStr.append(" left join model.userAddress.state state ");
			queryStr.append(" left join model.casteState.caste caste");
			queryStr.append(" left join model.userAddress.parliamentConstituency parliamentConstituency ");
			queryStr.append(" left join model.cadreDeleteReason cadreDeleteReason ");
			
			if(cadreId !=null){
				queryStr.append(" where model.tdpCadreId =:cadreId and (model.isDeleted ='N' or model.isDeleted = 'MD' ) ");
			}
			if(tdpMemberTypeId != null && tdpMemberTypeId.longValue() >1L)
				queryStr.append(" and model.tdpMemberTypeId =:tdpMemberTypeId ");
			
			if(enrollmentYear !=null){
				queryStr.append(" and model.enrollmentYear =:enrollmentYear  ");
			}
	
			Query query=getSession().createQuery(queryStr.toString());
			
			query.setParameter("cadreId", cadreId);
			query.setParameter("enrollmentYear", enrollmentYear);
			
			if(tdpMemberTypeId != null && tdpMemberTypeId.longValue() >1L)
				query.setParameter("tdpMemberTypeId", tdpMemberTypeId);
			
			return (Object[]) query.uniqueResult();
		}
		public List<Object[]> complaintDetailsOfCadre(String memberShipId){
			
			StringBuilder queryStr=new StringBuilder();
			
			queryStr.append("select Complaint_id,CAST(Subject as char(2550)) As Subject,Seviority,Raised_Date, " +
					" Completed_Status,issue_type,type_of_grevience" +
					" from complaint_master  " +
					" where membership_id="+memberShipId+" ");
			
			Query query=getSession().createSQLQuery(queryStr.toString());
			
			return query.list();
		}


		public Long getMemberShipRegistrationsInCadreLocation(String locationtype,Long locationId,Long year,Long constituencyId,List<Long> constituencyIdsList)
		{
			StringBuilder str = new StringBuilder();
			str.append(" select count(model.tdpCadreId) from TdpCadre model,UserAddress model2 where model.userAddress.userAddressId = model2.userAddressId and " +
					" model.enrollmentYear =:year and model.isDeleted = 'N' and ");
			
			    if(locationtype.equalsIgnoreCase("Constituency"))
				 str.append("  model2.constituency.constituencyId =:locationId");
				
				else if(locationtype.equalsIgnoreCase("Mandal"))
					 str.append("  model2.panchayat.tehsil.tehsilId =:locationId and model2.localElectionBody is null ");
				
				
				else if(locationtype.equalsIgnoreCase("Panchayat"))
					 str.append("  model2.panchayat.panchayatId =:locationId");
				
				
				else if(locationtype.equalsIgnoreCase("Booth"))
					 str.append("  model2.booth.boothId =:locationId ");
			    
				else if(locationtype.equalsIgnoreCase("Muncipality"))
				 str.append("model2.localElectionBody.localElectionBodyId =:locationId ");
			    
				else if(locationtype.equalsIgnoreCase("District"))
				 str.append(" model2.constituency.district.districtId =:locationId ");
			    
				else if(locationtype.equalsIgnoreCase("Parliament"))
				  str.append("  model2.constituency.constituencyId in (:constituencyIdsList) ");
			    
			  if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
				str.append(" and model2.constituency.constituencyId =:constituencyId "); 
			
			Query query = getSession().createQuery(str.toString());
			
			
			if(!locationtype.equalsIgnoreCase("Parliament"))
			  query.setParameter("locationId", locationId);
			
			query.setParameter("year", year);
			
		   if(!locationtype.equalsIgnoreCase("District") && !locationtype.equalsIgnoreCase("Parliament"))
			query.setParameter("constituencyId", constituencyId);
		   
		   if(constituencyIdsList != null && locationtype.equalsIgnoreCase("Parliament"))
			  query.setParameterList("constituencyIdsList", constituencyIdsList);  
		   
			return (Long) query.uniqueResult();		
			
		}
		
		public String getMemberShipIdByCadreId(Long tdpCadreId)
		{
			Query query = getSession().createQuery(" select model.memberShipNo from TdpCadre model where model.tdpCadreId =:tdpCadreId and model.isDeleted ='N' and model.enrollmentYear = 2014 ");
			query.setParameter("tdpCadreId", tdpCadreId);
			return (String) query.uniqueResult();
		}
		
	
		public List<Object[]> getPartyApprovedFundByMembershipId(String membershipId)
		{
			Query query = getSession().createSQLQuery(" select count(Complaint_id) as count,sum(approved_amount) as amount,issue_type as issueType from complaint_master where membership_id =:membershipId " +
					" and (Completed_Status = 'Approved' or Completed_Status = 'Completed') and Subject != '' and type_of_issue = 'Welfare' and " +
					"  approved_amount is not null and approved_amount != ''" +
					" and (delete_status != '0' or delete_status is null)  group by issue_type ")
			.addScalar("count",Hibernate.LONG)
			.addScalar("amount",Hibernate.LONG)
			.addScalar("issueType",Hibernate.STRING);
			query.setParameter("membershipId", membershipId);
			 
			return query.list();
					
		}
		
		public List<Object[]> getGovtApprovedFundByMembershipId(String membershipId)
		{
			Query query = getSession().createSQLQuery(" select count(Complaint_id) as count,sum(approved_amount) as amount,issue_type issueType from complaint_master where membership_id =:membershipId and " +
					" (Completed_Status = 'Approved' or Completed_Status = 'Completed') and issue_type = 'CM Relief' and approved_amount != '' and approved_amount is not null " +
					" and (delete_status != '0' or delete_status is null) and Subject != '' ")
			
			 .addScalar("count",Hibernate.LONG)
			 .addScalar("amount",Hibernate.LONG)
			 .addScalar("issueType",Hibernate.STRING);
			query.setParameter("membershipId", membershipId);
			return query.list();
		}
		
		public List<Object[]> getRequestedAmountByMembershipId(String membershipId)
		{
			Query query = getSession().createSQLQuery("select sum(expected_amount) as expamt,sum(health_amount) as helamt,count(Complaint_id) as count from complaint_master where" +
					" membership_id = :membershipId and ((expected_amount is not null and expected_amount != '') or (health_amount is not null and health_amount != '')) " +
					"and (delete_status != '0' or delete_status is null) and issue_type in ('Health','Personal' ,'CM Relief','Financial Support') and Subject != '' ")
					
			 .addScalar("expamt",Hibernate.LONG)
			 .addScalar("helamt",Hibernate.LONG)
			 .addScalar("count",Hibernate.LONG);
			query.setParameter("membershipId", membershipId);
			return query.list();
			
		}
		
		public List<Object[]> getEducationalRequestedAmountByMembershipId(String membershipId)
		{
			
			Query query = getSession().createSQLQuery("select sum(approved_amount) as apprAmt,count(Complaint_id) as count from complaint_master where " +
					" membership_id = :membershipId and issue_type = 'Educational' and approved_amount is not null and approved_amount != '' " +
					" and (delete_status != '0' or delete_status is null)  and Subject != '' ")
			
			.addScalar("apprAmt",Hibernate.LONG)
			.addScalar("count",Hibernate.LONG);
			query.setParameter("membershipId", membershipId);
			return query.list();
			
		}
		
		
		public List<Object[]> getSurveyPaticipatedCountByVoterIdcardNoList(List<String> voterIdCardNoList)
		{
			//Query query = getSession().createSQLQuery(" select count(respondent_id),voter_id from survey.respondent where voter_id in (:voterIdCardNoList) group by voter_id");
			Query query = getSession().createSQLQuery(" select distinct count(distinct R.voter_id),voter_id,SAI.survey_id from survey.respondent R, survey.survey_answer_info SAI, survey.survey S" +
					" where R.survey_answer_info_id = SAI.survey_answer_info_id and SAI.survey_id = S.survey_id and S.is_deleted='false' and R.voter_id in (:voterIdCardNoList) group by SAI.survey_id, R.voter_id ");
			query.setParameterList("voterIdCardNoList", voterIdCardNoList);
			
			return query.list();
		}
		
		/*public Long getCadreIdByMembershipId(String memberShipNo,Long constituencyId)
		{
			Query query = getSession().createQuery(" select distinct model.tdpCadreId from TdpCadre model where model.memberShipNo =:memberShipNo and model.isDeleted = 'N' " +
					" and model.enrollmentYear = 2014 and model.userAddress.constituency.constituencyId =:constituencyId ");
			
			query.setParameter("memberShipNo", memberShipNo);
			query.setParameter("constituencyId", constituencyId);
			
			return (Long) query.uniqueResult();
		}*/
		
		public List<Long> getCadreIdByMembershipId(String memberShipNo,Long constituencyId)
		{
			memberShipNo = memberShipNo != null&& !memberShipNo.isEmpty() && memberShipNo.length()==7 ? "0"+memberShipNo:memberShipNo;
			StringBuilder str = new StringBuilder();
			str.append(" select distinct model.tdpCadreId from TdpCadre model where model.memberShipNo =:memberShipNo and model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
			if(constituencyId != null && constituencyId > 0L)
			 str.append(" and model.userAddress.constituency.constituencyId =:constituencyId ");
			
			Query query = getSession().createQuery(str.toString());
			
			query.setParameter("memberShipNo", memberShipNo);
			if(constituencyId != null && constituencyId > 0L)
			query.setParameter("constituencyId", constituencyId);
			
			return query.list();
		}
		
		public List<Object[]> getCategorywiseStatusCount(String memberShipNo)
		{
			Query query = getSession().createSQLQuery(" select type_of_issue as typeOfIssue,count(Complaint_id) as count ,Completed_Status as completedStatus from complaint_master where membership_id =:membershipId " +
					" and (delete_status != '0' or delete_status is null) and Completed_Status is not null and Completed_Status != '' and Subject is not null and Subject != '' group by type_of_issue,Completed_Status order by type_of_issue ")
			
			
			 .addScalar("typeOfIssue",Hibernate.STRING)
			 .addScalar("count",Hibernate.LONG)
			 .addScalar("completedStatus",Hibernate.STRING);
			 
			query.setParameter("membershipId", memberShipNo);
			return query.list();
		}
		public List<Object[]> cadresInformationOfCandidate(List<Long> cadreIds,Long enrollmentYear){

			
			StringBuilder queryStr=new StringBuilder();
			
			queryStr.append(" select model.tdpCadreId,model.firstname,date(model.dateOfBirth),model.age,eduQualification.eduQualificationId,eduQualification.qualification," +
					"  occupation.occupationId,occupation.occupation,voter.voterId,panchayat.panchayatName,tehsil.tehsilName," +
					" constituency.name,model.mobileNo,constituency.constituencyId,voter.voterIDCardNo,model.image,model.memberShipNo,model.houseNo " +
					" ,district.districtName,state.stateName,caste.casteName,model.insertedWebUserId,date(model.insertedTime),model.emailId,model.dataSourceType" +
					",panchayat.panchayatId,tehsil.tehsilId,district.districtId,state.stateId,parliamentConstituency.constituencyId,parliamentConstituency.name , " +
					" booth.boothId,booth.partNo, ward.constituencyId, ward.name,model.relativename " +
					" from TdpCadre model " );
			queryStr.append(" left join model.educationalQualifications eduQualification ");
			queryStr.append(" left join model.occupation occupation ");
			queryStr.append(" left join model.voter voter ");
			queryStr.append(" left join model.userAddress.booth booth ");
			queryStr.append(" left join model.userAddress.panchayat panchayat ");
			queryStr.append(" left join model.userAddress.ward ward ");
			queryStr.append(" left join model.userAddress.tehsil tehsil ");
			queryStr.append(" left join model.userAddress.constituency constituency ");
			queryStr.append(" left join model.userAddress userAddress1 ");
			queryStr.append(" left join model.userAddress.district  district");
			queryStr.append(" left join model.userAddress.state state ");
			queryStr.append(" left join model.casteState.caste caste");
			queryStr.append(" left join model.userAddress.parliamentConstituency parliamentConstituency ");
			
			if(cadreIds !=null && cadreIds.size()>0){
				queryStr.append(" where model.tdpCadreId in (:cadreIds) and model.isDeleted ='N'");
			}
			if(enrollmentYear !=null){
				queryStr.append(" and model.enrollmentYear =:enrollmentYear  ");
			}
			
			Query query=getSession().createQuery(queryStr.toString());
			
			
			query.setParameterList("cadreIds", cadreIds);
			query.setParameter("enrollmentYear", enrollmentYear);
			
			return  query.list();
		}
		
		public List<Object[]> checkVoterCardNosCadreOrNot(String voterCardNoStr)
		{
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select distinct voter.voterIDCardNo,familyVoter.voterIDCardNo, model.tdpCadreId,model.memberShipNo ");
			queryStr.append(" from TdpCadre model ");
			queryStr.append(" left join model.voter voter ");
			queryStr.append(" left join model.familyVoter familyVoter ");
			queryStr.append(" where model.isDeleted = 'N' and model.enrollmentYear = 2014 ");
			queryStr.append(" and (voter.voterIDCardNo like '"+voterCardNoStr+"'  or (familyVoter.voterId is not null and familyVoter.voterIDCardNo like '"+voterCardNoStr+"' )) ");
			Query query = getSession().createQuery(queryStr.toString());
			return query.list();
		
		}
		
		public List<Object[]> checkVoterCardNosCadreNosOrNot(List<String> voterCardNoList)
		{
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select distinct voter.voterIDCardNo,model.tdpCadreId,model.memberShipNo,model.image,model.isDeleted,cadreDeleteReason.cadreDeleteReasonId," +
					" cadreDeleteReason.reason ");
			queryStr.append(" from TdpCadre model ");
			queryStr.append(" left join model.voter voter ");
			queryStr.append(" left join model.cadreDeleteReason cadreDeleteReason ");
			queryStr.append(" where (model.isDeleted = 'N' or model.isDeleted ='MD' ) and model.enrollmentYear = 2014 ");
			queryStr.append(" and (voter.voterIDCardNo in (:voterCardNoList)) ");
			Query query = getSession().createQuery(queryStr.toString());
			
			query.setParameterList("voterCardNoList", voterCardNoList);
			return query.list();
		
		}
		
		public List<Long> getAllRemovedCadre(){
			
			Query query = getSession().createQuery(" select model.tdpCadreId from TdpCadre model " +
					" where " +
					" model.isDeleted = 'MD' and model.enrollmentYear = 2014 ");
			
			return query.list();
		}
		
		public List<Object[]> getAllCadreDetailsByCadreIds(List<Long> cadreIds){
			
			Query query = getSession().createQuery("select model.tdpCadreId, model.firstname,model.memberShipNo,model.mobileNo,model.image," +
					" date(model.dateOfBirth),model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,model.casteState.caste.casteName,model.age," +
					" model.relativename " +
					" from " +
					" TdpCadre model " +
					" where model.isDeleted ='N' " +
					" and model.tdpCadreId in (:cadreIds) ");
			
			query.setParameterList("cadreIds", cadreIds);
			
			return query.list();
			
		}
		
		public List<Object[]> getCadreDetailsByMembershipNoOrVoterId(String memberShipNo,String voterId){
			
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select model.tdpCadreId,model.memberShipNo from TdpCadre model where model.isDeleted = 'N' ");
			
			if(!(memberShipNo.equalsIgnoreCase("null"))){
				queryStr.append(" and model.memberShipNo = :memberShipNo ");
			}
			if(!(voterId.equalsIgnoreCase("null"))){
				queryStr.append(" and model.voter.voterIDCardNo = :voterId ");
			}
			
			Query query = getSession().createQuery(queryStr.toString());
			if(!(memberShipNo.equalsIgnoreCase("null"))){
				query.setParameter("memberShipNo", memberShipNo);
			}
			if(!(voterId.equalsIgnoreCase("null"))){
				query.setParameter("voterId", voterId);
			}
			
			return query.list();
		}
		
		public Long getTdpCadreIdByVoterId(Long voterId)
		{
			Query query = getSession().createQuery("select distinct model.tdpCadreId  from TdpCadre model where model.voterId = :voterId  and model.isDeleted = 'N' " +
					" and model.enrollmentYear = :enrollMentYear ");
			query.setParameter("voterId", voterId);
			query.setParameter("enrollMentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
			return (Long) query.uniqueResult();
		}
		
		
		public Long getTdpCadreCountsForDivisions(List<Long> divisionIds){
			Query query = getSession().createQuery("select count(tc.tdpCadreId)  from TdpCadre tc  where tc.userAddress.ward.constituencyId in (:divisionIds) and tc.isDeleted = 'N' " +
					" and tc.enrollmentYear = :enrollMentYear ");
			query.setParameterList("divisionIds",divisionIds);
			query.setParameter("enrollMentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
			return (Long) query.uniqueResult();
		}
		public List<Object[]> getDivisionWiseCadresCount(List<Long> divisionIds){
			
			Query query = getSession().createQuery("select tc.userAddress.ward.constituencyId,count(tc.tdpCadreId) as totalCadres" +
					"  from TdpCadre tc  where tc.userAddress.ward.constituencyId in (:divisionIds) and tc.isDeleted = 'N' " +
					" and tc.enrollmentYear = :enrollMentYear " +
					" group by tc.userAddress.ward.constituencyId ");
			query.setParameterList("divisionIds",divisionIds);
			query.setParameter("enrollMentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
			return query.list();
		}
		public List<Object[]> getTdpCadreBoothsForDivision(Long wardId)
		{
			Query query = getSession().createQuery("select distinct bpv.booth.boothId,bpv.booth.partNo  from TdpCadre tc,BoothPublicationVoter bpv  where tc.userAddress.ward.constituencyId =:wardId and tc.isDeleted = 'N' " +
					" and tc.enrollmentYear = :enrollMentYear and tc.voterId = bpv.voter.voterId and bpv.booth.publicationDate.publicationDateId = 17");
			query.setParameter("wardId",wardId);
			query.setParameter("enrollMentYear", IConstants.CADRE_ENROLLMENT_NUMBER);
			return query.list();
		}
		
		public Long getUserAddressId(Long tdpCadreId){
			
			Query query=getSession().createQuery(" select tc.userAddress.userAddressId from TdpCadre tc where tc.tdpCadreId=:tdpCadreId and tc.isDeleted='N' ");
			query.setParameter("tdpCadreId",tdpCadreId);
			return (Long)query.uniqueResult();
		}
		public Long getUserPresentAddressId(Long tdpCadreId){
			
			Query query=getSession().createQuery(" select tc.presentAddress.userAddressId from TdpCadre tc where tc.tdpCadreId=:tdpCadreId and tc.isDeleted='N' ");
			query.setParameter("tdpCadreId",tdpCadreId);
			return (Long)query.uniqueResult();
		}
		public Long getUserPermenentAddressId(Long tdpCadreId){
			
			Query query=getSession().createQuery(" select tc.permanentAddress.userAddressId from TdpCadre tc where tc.tdpCadreId=:tdpCadreId and tc.isDeleted='N' ");
			query.setParameter("tdpCadreId",tdpCadreId);
			return (Long)query.uniqueResult();
		}
		public Long getUserWorkAddressId(Long tdpCadreId){
			
			Query query=getSession().createQuery(" select tc.workLocation.userAddressId from TdpCadre tc where tc.tdpCadreId=:tdpCadreId and tc.isDeleted='N' ");
			query.setParameter("tdpCadreId",tdpCadreId);
			return (Long)query.uniqueResult();
		}
		public List<UserAddress> getUserAddress(Long tdpCadreId){
			
			Query query=getSession().createQuery(" select tc.userAddress.userAddress from TdpCadre tc where tc.tdpCadreId=:tdpCadreId ");
			query.setParameter("tdpCadreId",tdpCadreId);
			return query.list();
		}
		public List<Object[]> getAffliatedCadreCountDetails(String type,Date date){
			
			StringBuilder str = new StringBuilder();			
			str.append(" select model.dataSourceType,count(distinct model.tdpCadreId) from TdpCadre model" +
					" where model.isDeleted ='N' and model.tdpMemberTypeId = 2 ");			
			if(type !=null && type.equalsIgnoreCase("toDay")){
				str.append(" and date(model.updatedTime) = :date ");
			}			
			str.append(" group by model.dataSourceType  ");	
			
			Query query = getSession().createQuery(str.toString());		
			
			if(type !=null && type.equalsIgnoreCase("toDay")){
				query.setParameter("date",date);
			}
			
			return query.list();
			
		}
		
		public List<Object[]> getRtcUnionZoneWiseDetails(String searchType,Date date){
			/*select tcl.rtc_zone_id,tc.data_source_type,count(tc.tdp_cadre_id) from tdp_cadre tc,tdp_cadre_location tcl
			where tc.tdp_cadre_location_id = tcl.tdp_cadre_location_id
			and tc.tdp_member_type_id=2
			and tc.is_deleted='N'
			group by tcl.rtc_zone_id,tc.data_source_type;
			*/
			StringBuilder str = new StringBuilder();
			
			str.append(" select tdpCadreLocation.rtcZone.rtcZoneId,tdpCadreLocation.rtcZone.zoneName," +
					" model.dataSourceType,count(distinct model.tdpCadreId) " +
					" from TdpCadre model " +
					" left join model.tdpCadreLocation tdpCadreLocation " +
					" where model.tdpMemberTypeId = 2" +
					"  and model.isDeleted ='N' ");
			
			if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
				str.append(" and date(model.updatedTime) = :date ");
			}	
			
			str.append(" group by tdpCadreLocation.rtcZone.rtcZoneId,model.dataSourceType " +
					" order by tdpCadreLocation.rtcZone.zoneName ");
			
			Query query = getSession().createQuery(str.toString());	
			
			if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
				query.setParameter("date",date);
			}
			
			return query.list();
			
		}
		
		public List<Object[]> getRtcUnionAllLocationDetails(String searchType,Date date){
			
			StringBuilder str = new StringBuilder();
			
			str.append(" select tdpCadreLocation.rtcRegion.rtcRegionId,tdpCadreLocation.rtcRegion.regionName," +
					" model.dataSourceType,count(distinct model.tdpCadreId) " +
					" from TdpCadre model " +
					" left join model.tdpCadreLocation tdpCadreLocation " +
					" where model.tdpMemberTypeId = 2" +
					"  and model.isDeleted ='N' ");			
			if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
				str.append(" and date(model.updatedTime) = :date ");
			}			
			str.append(" group by tdpCadreLocation.rtcRegion.rtcRegionId,model.dataSourceType " +
					" order by tdpCadreLocation.rtcRegion.regionName ");			
			Query query = getSession().createQuery(str.toString());				
			if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
				query.setParameter("date",date);
			}			
			return query.list();
			
		}
		
		public List<Object[]> getRtcUnionDeptDetails(String searchType,Date date){
			
			StringBuilder str = new StringBuilder();
			
			str.append(" select tdpCadreLocation.rtcDepot.rtcDepotId,tdpCadreLocation.rtcDepot.depotName," +
					" model.dataSourceType,count(distinct model.tdpCadreId) " +
					" from TdpCadre model " +
					" left join model.tdpCadreLocation tdpCadreLocation " +
					" where model.tdpMemberTypeId = 2" +
					"  and model.isDeleted ='N' ");			
			if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
				str.append(" and date(model.updatedTime) = :date ");
			}			
			str.append(" group by tdpCadreLocation.rtcDepot.rtcDepotId,model.dataSourceType " +
					" order by tdpCadreLocation.rtcDepot.depotName ");			
			Query query = getSession().createQuery(str.toString());				
			if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
				query.setParameter("date",date);
			}			
			return query.list();
			
		}
		
		public List<Object[]> getAffiliatedCadreDetails(String type,String searchType,Long locationId){
		
			StringBuilder str = new StringBuilder();
			
				str.append(" select model.tdpCadreId,model.firstname,model.mobileNo,voter.voterIDCardNo," +
						" model.idCardNo, " +
						" model.dataSourceType,model.image " +
						" from TdpCadre model left join model.voter voter" +
						" left join model.tdpCadreLocation tdpCadreLocation " +
						" where model.tdpMemberTypeId = 2" +
						"  and model.isDeleted ='N' ");			
				
				if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
					str.append(" and date(model.updatedTime) = :date ");
				}
				else if(searchType !=null && searchType.equalsIgnoreCase("web")){
					str.append(" and model.dataSourceType = 'WEB'  ");
				}
				else if(searchType !=null && searchType.equalsIgnoreCase("tab")){
					str.append(" and model.dataSourceType = 'TAB'  ");
				}
				
			if(type !=null && !type.isEmpty() && type.equalsIgnoreCase("depot")){	
				if(locationId !=null && locationId>0){
					str.append(" and tdpCadreLocation.rtcDepotId =:locationId ");
				}											
			}
			else if(type !=null && !type.isEmpty() && type.equalsIgnoreCase("region")){	
				if(locationId !=null && locationId>0){
					str.append(" and tdpCadreLocation.rtcRegionId =:locationId ");
				}
			}
			else if(type !=null && !type.isEmpty() && type.equalsIgnoreCase("zone")){	
				if(locationId !=null && locationId>0){
					str.append(" and tdpCadreLocation.rtcZoneId =:locationId ");
				}
			}
			str.append(" order by model.firstname ");
			
			//Query Execution
			Query query = getSession().createQuery(str.toString());		
			
			if(searchType !=null && searchType.equalsIgnoreCase("toDay")){
				query.setParameter("date",new DateUtilService().getCurrentDateAndTime());
			}	
			
			
			if(type !=null && !type.isEmpty()){	
				if(locationId !=null && locationId>0){
					query.setParameter("locationId", locationId);
				}
			}
			
			return query.list();
			
		}
		
		
		public List checkUnionMemberExists(String voterCardNo,Long memberTypeId)
		{
			if(memberTypeId != null && memberTypeId.longValue()>1L){
				Query query = getSession().createQuery("select distinct model.tdpCadreId,model.voter.voterIDCardNo from TdpCadre model where model.voter.voterIDCardNo = :voterCardNo" +
						" and model.tdpMemberType.tdpMemberTypeId = :memberTypeId " +
						" and model.isDeleted='N'");
				query.setParameter("voterCardNo", voterCardNo);
				query.setParameter("memberTypeId", memberTypeId);
				return query.list();
			}
			else{
				Query query = getSession().createQuery("select distinct model.tdpCadreId,model.voter.voterIDCardNo from TdpCadre model where model.voter.voterIDCardNo = :voterCardNo" +
						" and model.isDeleted='N'");
				query.setParameter("voterCardNo", voterCardNo);
				return query.list();
			}
		}
		
		public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencIdRTC(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre,Integer startIndex,Integer maxIndex,Long memberTypeId)
		{
			StringBuilder str = new StringBuilder();
			
			
			str.append(" select distinct TC.tdpCadreId, TC.firstname, TC.relativename, TC.dateOfBirth, TC.age, TC.gender, TC.houseNo, UA.userAddressId,TC.voterId,TC.relativeType ");
			str.append(" ,TC.image from TdpCadre TC, UserAddress UA where "+queryStr+" TC.userAddress.constituency.constituencyId = :constituencyId ");
			
			if(panchayatId.longValue() != 0L)
			{
				if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					str.append(" and TC.userAddress.panchayat.panchayatId = :id ");
				}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					str.append(" and TC.userAddress.localElectionBody.localElectionBodyId = :id ");
				}
			}
			
			if(boothId.longValue() != 0L)
			{
				str.append(" and TC.userAddress.booth.boothId = :boothId ");
			}
			
			if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))//2016
			{
				str.append(" and TC.enrollmentYear in (:cadreEnrollmentYear)  ");
			}
			
			if(memberTypeId != null && memberTypeId.longValue() >1L){
				if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))//2016
				{
					str.append(" and TC.tdpMemberTypeId=:memberTypeId  ");
				}
				else
				{
					str.append(" and  TC.tdpMemberTypeId=:memberTypeId ");
				}
			}
			
			str.append(" and TC.userAddress.userAddressId = UA.userAddressId  and TC.isDeleted = 'N'  order by TC.houseNo ");
			
			Query query = getSession().createQuery(str.toString()); 
			
			query.setParameter("constituencyId", constituencyId);
			query.setParameter("memberTypeId", memberTypeId);
			//query.setParameter("tdpMemberTypeId", IConstants.AFFLIATED_TDP_MEMBER_TYPE_ID);

			if(panchayatId.longValue() != 0L)
			{
				if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
				}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
				}
			}
			
			if(boothId.longValue() != 0L)
			{
				query.setParameter("boothId", boothId);
			}		
			if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))//2016
			{
				query.setParameter("cadreEnrollmentYear", IConstants.RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER);
			}
			
			if(startIndex != null && maxIndex != null){
				  query.setFirstResult(startIndex);
				  query.setMaxResults(maxIndex);
				}
			return query.list();
		}
		
		public Long getCadreDetailsForCadreRegistratiobByconstituencIdCountRTC(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre,Long memberTypeId)
		{
			StringBuilder str = new StringBuilder();
			
			if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
			{
				str.append(" select count(*) ");
			}
			else
			{
				str.append(" select count(*) ");
			}

			str.append("  from TdpCadre TC, UserAddress UA where "+queryStr+" TC.userAddress.constituency.constituencyId = :constituencyId ");
			
			if(panchayatId.longValue() != 0L)
			{
				if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					str.append(" and TC.userAddress.panchayat.panchayatId = :id ");
				}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					str.append(" and TC.userAddress.localElectionBody.localElectionBodyId = :id ");
				}
			}
			
			if(boothId.longValue() != 0L)
			{
				str.append(" and TC.userAddress.booth.boothId = :boothId ");
			}
			
			if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
			{
				str.append(" and TC.enrollmentYear in (:cadreEnrollmentYear)  ");
			}
			
			if(memberTypeId != null && memberTypeId.longValue() >1L){
				if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
				{
					str.append(" and TC.tdpMemberTypeId=:memberTypeId  ");
				}
				else
				{
					str.append(" and TC.enrollmentYear not in (:cadreEnrollmentYear) and TC.tdpMemberTypeId=:memberTypeId ");
				}
			}
			
			str.append(" and TC.userAddress.userAddressId = UA.userAddressId  and TC.isDeleted = 'N'  order by TC.houseNo ");
			
			Query query = getSession().createQuery(str.toString()); 
			
			query.setParameter("constituencyId", constituencyId);
			

			if(panchayatId.longValue() != 0L)
			{
				if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
					query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
				}else if(panchayatId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
					query.setParameter("id", Long.valueOf(panchayatId.toString().substring(1)));
				}
			}
			
			if(boothId.longValue() != 0L)
			{
				query.setParameter("boothId", boothId);
			}		
			
			query.setParameter("cadreEnrollmentYear", IConstants.RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER);
			query.setParameter("memberTypeId", memberTypeId);
			
			return (Long)query.uniqueResult();
		}
		
		public List<Long> getVoterDetailsByVoterIdsAndRTCAffliatedCadre(List<Long> voterIdList,Long memberTypeId)
		{
			Query query = getSession().createQuery("select distinct model.voterId  from TdpCadre model " +
					" where model.voterId in (:voterIdList) and model.isDeleted = 'N' and model.tdpMemberTypeId=:memberTypeId " +
					" and model.enrollmentYear in (:enrollmentYear)  ");
			
			query.setParameterList("voterIdList", voterIdList);
			query.setParameter("memberTypeId", memberTypeId);
			query.setParameter("enrollmentYear",  IConstants.RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER);
			//query.setParameter("tdpMemberTypeId", IConstants.AFFLIATED_TDP_MEMBER_TYPE_ID);
			
			return query.list();
		}
		
		public Long getTodayTabAndWebUsersCount(String type){
			
			StringBuilder str = new StringBuilder();
			
			if(type !=null && type.equalsIgnoreCase("TAB")){
				str.append(" select count(distinct model.insertedUserId) from TdpCadre model ");
			}
			else if(type !=null && type.equalsIgnoreCase("WEB")){
				str.append(" select count(distinct model.insertedWebUserId) from TdpCadre model ");
			}			
			str.append(" where model.isDeleted ='N'" +
					" and model.tdpMemberTypeId = 2 ");			
			str.append(" and date(model.updatedTime) = :date ");			
			str.append(" and model.dataSourceType = :type ");	
			
			Query query = getSession().createQuery(str.toString()); 			
			query.setParameter("date", new DateUtilService().getCurrentDateAndTime());
			query.setParameter("type", type);
			
			return (Long) query.uniqueResult();
		}
		public List<Long> checkVoterAsAffliatedCadre(Long voterId,Long memberTypeId){
			
			Query query=getSession().createQuery(" select model.tdpCadreId from  TdpCadre model where model.isDeleted='N' and model.tdpMemberTypeId=:memberTypeId and model.voterId=:voterId and model.enrollmentYear in (:enrollmentYear) ");
			query.setParameter("voterId", voterId);
			query.setParameter("memberTypeId", memberTypeId);
			query.setParameter("enrollmentYear",  IConstants.RTC_AFFLIATED_CADRE_ENROLLMENT_NUMBER);
			//query.setParameter("tdpMemberTypeId", IConstants.AFFLIATED_TDP_MEMBER_TYPE_ID);
			
			return query.list();
		}
		
		public List<Object[]> getLocationwiseCadreRegistraionDetails(List<Long> membereTypeIdsList,String searchTypeStr,
				Date fromDate,Date toDate){
			
			StringBuilder queryStr = new StringBuilder();
			if(searchTypeStr != null && !searchTypeStr.isEmpty())
			{
				if(searchTypeStr.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					queryStr.append(" select distinct UA.constituency.constituencyId,UA.constituency.name, ");
				else if(searchTypeStr.trim().equalsIgnoreCase(IConstants.DISTRICT))
					queryStr.append("select distinct UA.district.districtId,UA.district.districtName, ");
				
				queryStr.append(" TC.dataSourceType, count(distinct TC.tdpCadreId)  from TdpCadre TC, UserAddress UA where TC.userAddress = UA.userAddressId ");
				
				if(membereTypeIdsList != null && membereTypeIdsList.size()>0)
					queryStr.append(" and TC.tdpMemberTypeId in (:membereTypeIdsList) ");
				if(fromDate != null && toDate != null)
					queryStr.append(" and (date(TC.insertedTime) between :fromDate and :toDate )");
				queryStr.append(" and TC.isDeleted ='N' and TC.enrollmentYear = "+IConstants.UNIONS_REGISTRATION_YEAR+" ");
				queryStr.append(" group by ");
				if(searchTypeStr.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					queryStr.append(" UA.constituency.constituencyId, ");
				else if(searchTypeStr.trim().equalsIgnoreCase(IConstants.DISTRICT))
					queryStr.append(" UA.district.districtId, ");
				
				queryStr.append(" TC.dataSourceType ");
				
				queryStr.append(" order by ");
				if(searchTypeStr.trim().equalsIgnoreCase(IConstants.CONSTITUENCY))
					queryStr.append(" UA.constituency.name,TC.dataSourceType asc ");
				else if(searchTypeStr.trim().equalsIgnoreCase(IConstants.DISTRICT))
					queryStr.append(" UA.district.districtName,TC.dataSourceType asc ");
				
			}
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameterList("membereTypeIdsList", membereTypeIdsList);
			if(fromDate != null && toDate != null){
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			return query.list();
		}
		
		public List<Object[]> getCadreCountsByTdpMemberType(Date fromDate,Date toDate){
			
			StringBuilder sb = new StringBuilder();
			
			sb.append(" select count(distinct model.tdpCadreId)," +
						" model.tdpMemberType.tdpMemberTypeId," +
						" model.tdpMemberType.memberType," +
						" model.dataSourceType" +
						" from TdpCadre model where model.isDeleted ='N'");
			if(fromDate != null && toDate != null){
				sb.append(" and (date(model.surveyTime) between :fromDate and :toDate) ");
			}
			sb.append(" and model.enrollmentYear = "+IConstants.UNIONS_REGISTRATION_YEAR+" ");
			sb.append(" group by model.tdpMemberType.tdpMemberTypeId,model.dataSourceType ");
			
			Query query = getSession().createQuery(sb.toString());
			
			if(fromDate != null && toDate != null){
				query.setParameter("fromDate", fromDate);
				query.setParameter("toDate", toDate);
			}
			
			return query.list();
		}
		
	  public List<Object[]> getAllCountsForUnionMembersRegistered(Date fromDate,Date toDate){
		  	StringBuilder queryStr = new StringBuilder();
			queryStr.append("select distinct model.dataSourceType , count(distinct model.tdpCadreId) from TdpCadre model where " +
					" model.tdpMemberType.isDeleted = 'false' and model.tdpMemberType.tdpMemberTypeId !=1 " +
					" and model.isDeleted='N' and model.enrollmentYear = "+IConstants.UNIONS_REGISTRATION_YEAR+" ");
			if(fromDate != null && toDate != null){
				queryStr.append(" and (date(model.surveyTime) between :fromDate and :toDate) ");
			}
			queryStr.append(" group by model.dataSourceType order by model.dataSourceType asc");
			Query query = getSession().createQuery(queryStr.toString());
			if(fromDate != null && toDate != null){
				query.setParameter("fromDate", fromDate);
				query.setParameter("toDate", toDate);
			}
				return query.list();
	  }
  
		
		
	public List<Long> getCadreDetailsByTdpMemberType(Date fromDate,Date toDate,RtcUnionInputVO inputVO){
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select model.tdpCadreId from TdpCadre model where");
			if(inputVO.getLocationType().equalsIgnoreCase("District"))	
				sb.append("  model.userAddress.district.districtId = :locationId");
			if(inputVO.getLocationType().equalsIgnoreCase("constituency"))	
				sb.append("  model.userAddress.constituency.constituencyId = :locationId");
			if(fromDate != null && toDate != null){
				sb.append(" and (date(model.surveyTime) between :fromDate and :toDate) ");
			}
			if(inputVO.getAppType() != null && !inputVO.getAppType().isEmpty())
				sb.append(" and model.dataSourceType = :appType");
			if(inputVO.getMemeberTypeIds() != null && inputVO.getMemeberTypeIds().size() > 0)
				sb.append(" and model.tdpMemberType.tdpMemberTypeId in(:memberTypeIds)");
			sb.append(" and model.isDeleted ='N' and model.enrollmentYear = "+IConstants.UNIONS_REGISTRATION_YEAR+" ");
			Query query = getSession().createQuery(sb.toString());
			if(fromDate != null && toDate != null){
				query.setParameter("fromDate", fromDate);
				query.setParameter("toDate", toDate);
			}
			if(fromDate != null && toDate != null)
			{
				query.setParameter("fromDate", fromDate);
				query.setParameter("toDate", toDate);
			}
			if(inputVO.getAppType() != null && !inputVO.getAppType().isEmpty())
				query.setParameter("appType", inputVO.getAppType());
			if(inputVO.getLocationType() != null)
				query.setParameter("locationId", inputVO.getLocationId());
			if(inputVO.getMemeberTypeIds() != null && inputVO.getMemeberTypeIds().size() > 0)
				query.setParameterList("memberTypeIds", inputVO.getMemeberTypeIds());
			
			return query.list();
		}
	  public List<Long> getCadreDetailsByTdpMemberTypeSourceWise(Date fromDate, Date toDate, RtcUnionInputVO inputVO){
		  StringBuilder sb = new StringBuilder();
		  sb.append(" select model.tdpCadreId from TdpCadre model where");
		  
		  if(inputVO.getLocationType().equalsIgnoreCase("District")){
			  sb.append("  model.userAddress.district.districtId = :locationId");
		  }
		  
		  if(inputVO.getLocationType().equalsIgnoreCase("constituency")){
			  sb.append("  model.userAddress.constituency.constituencyId = :locationId");
		  }
		  
		  if(fromDate != null && toDate != null){
				sb.append(" and (date(model.surveyTime) between :fromDate and :toDate) ");
		  }
		  
		  if( inputVO.getSourceType() != null &&  !inputVO.getSourceType().trim().equalsIgnoreCase("0"))
			  sb.append("  and model.dataSourceType = :dataSourceType");
		  
		  if(inputVO.getMemeberTypeIds() != null && inputVO.getMemeberTypeIds().size() > 0){
				sb.append(" and model.tdpMemberType.tdpMemberTypeId in(:memberTypeIds)");
		  }
		  
		  sb.append(" and model.isDeleted ='N' and model.enrollmentYear = "+IConstants.UNIONS_REGISTRATION_YEAR+" ");
		  Query query = getSession().createQuery(sb.toString());
		  if(fromDate != null && toDate != null){
			  	query.setParameter("fromDate", fromDate);
				query.setParameter("toDate", toDate);
		  }
		  if(inputVO.getMemeberTypeIds() != null && inputVO.getMemeberTypeIds().size() > 0){
				query.setParameterList("memberTypeIds", inputVO.getMemeberTypeIds());
		  }
		  
		  if( inputVO.getSourceType() != null &&  !inputVO.getSourceType().trim().equalsIgnoreCase("0"))
			  query.setParameter("dataSourceType", inputVO.getSourceType());
		  
		  if(inputVO.getLocationType() != null){
				query.setParameter("locationId", inputVO.getLocationId());
		  }
		  return query.list();
	  }
	  public List<Object[]>  getmemberShipIdsByVoterIds(Long cadreEnrollmentYear,List<Long> voterIds){
		  
		  /*select   model.voter_id,model.membership_id,model.tdp_member_type_id
		    from     tdp_cadre model
		     where   model.voter_id in (6296782,62932815) 
		             and model.enrollment_year=2014 and model.is_deleted='N' and model.tdp_member_type_id is null or model.tdp_member_type_id =1;*/
		  
		  Query query=getSession().createQuery("" +
		  " select  model.voterId,model.memberShipNo,model.tdpMemberTypeId,model.dataSourceType " +
		  " from    TdpCadre model " +
		  " where   model.voterId in (:voterIds)  and model.enrollmentYear =:cadreEnrollmentYear" +
		  "         and   model.isDeleted='N' and model.tdpMemberTypeId is null or  model.tdpMemberTypeId =1 ");
		  query.setParameterList("voterIds", voterIds);
		  query.setParameter("cadreEnrollmentYear", cadreEnrollmentYear);
		  
		  return  query.list();
		  
	  }
	  
	  public List<Object[]> getTdpCadreIdForMemberShipNums(List<String> membershipNums){
		  Query query = getSession().createQuery(" select model.tdpCadreId,model.memberShipNo " +
		  		" from TdpCadre model " +
		  		" where model.memberShipNo in (:membershipNums) and model.isDeleted = 'N' ");
		  query.setParameterList("membershipNums", membershipNums);
		  return query.list();
		  
	  }
	  
	  public List<Object[]>  searchMemberByCriteria(String searchType,String searchValue,LocationInputVO locationVo){
			
			StringBuilder sb=new StringBuilder();
			
			sb.append(" SELECT  model.tdpCadreId ,model.firstname,model.mobileNo,model.userAddress.constituency.name," +
					"           model.memberShipNo, voter.voterIDCardNo, model.image " +
					"   FROM    TdpCadre model LEFT JOIN model.voter voter" +
					"   WHERE   model.isDeleted='N' AND model.enrollmentYear = :enrollmentYear ");
			if(searchType.equalsIgnoreCase("mobileno")){
				
				sb.append(" AND model.mobileNo = :searchValue ");
				
			}else if(searchType.equalsIgnoreCase("mebershipno")){
				
				sb.append(" AND model.memberShipNo = :searchValue ");
				
			}else if(searchType.equalsIgnoreCase("votercardno")){
				
				sb.append(" AND voter.voterIDCardNo = :searchValue ");
			}
			else if(searchType.equalsIgnoreCase("name"))
			{
				sb.append(" AND model.firstname LIKE '%"+searchValue+"%' ");
				 if(locationVo != null)
					{
							if(locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)//State
							{
								if(locationVo.getStateId() == 1)
								sb.append("and model.userAddress.district.districtId > 10  and model.userAddress.district.districtId <=23");
								if(locationVo.getStateId() == 36)
									sb.append("and model.userAddress.district.districtId >= 1  and model.userAddress.district.districtId <=11");	
								//sb.append("and model.userAddress.state.stateId in (:locationStateIds) ");
							}
							if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)//District
							{
								sb.append("and model.userAddress.district.districtId in(:districtIds) ");
							}
							if(locationVo.getConstituencyIds() != null && locationVo.getConstituencyIds().size() > 0)//Constituency
							{
								sb.append("and model.userAddress.constituency.constituencyId in(:constituencyIds) ");
							}
							if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)//Tehsil
							{
								sb.append("and model.userAddress.tehsil.tehsilId in(:tehsilIds) ");
							}
							if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)//Village
							{
								sb.append(" and model.userAddress.panchayat.panchayatId in (:panchayatIds) ");
							}
							if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)//ward
							{
								sb.append(" and model.userAddress.ward.constituencyId in (:wardIds) ");
							}
							if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)//Town
							{
								sb.append(" and model.userAddress.localElectionBody.localElectionBodyId in(:townIds) ");
							}
							if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)//Divison
							{
								sb.append(" and model.userAddress.ward.constituencyId in(:divisonIds) ");
							}
							
					}
			}
			
			
			Query query = getSession().createQuery(sb.toString());
			if(!searchType.equalsIgnoreCase("name"))
			query.setParameter("searchValue",searchValue);
			 if(searchType.equalsIgnoreCase("name"))
			 {
				 query.setFirstResult(0);
				 query.setMaxResults(100);
				 if(locationVo != null)
					{
						/*if(locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)
							query.setParameterList("locationStateIds", locationVo.getStateIdsList());*/
						if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)
							query.setParameterList("districtIds", locationVo.getDistrictIdsList());
						if(locationVo.getConstituencyIds() != null && locationVo.getConstituencyIds().size() > 0)
							query.setParameterList("constituencyIds", locationVo.getConstituencyIds());
						if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)
							query.setParameterList("tehsilIds", locationVo.getTehsilIdsList());
						if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)
							query.setParameterList("panchayatIds", locationVo.getVillageIdsList());
						if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)
							query.setParameterList("wardIds", locationVo.getWardIdsList());
						if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
							query.setParameterList("townIds", locationVo.getTownIdsList());
						if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)
							query.setParameterList("divisonIds", locationVo.getDivisionIdsList());
					}
			 }
			 
			query.setParameter("enrollmentYear",IConstants.CADRE_ENROLLMENT_NUMBER);
			return query.list();
		}
	  
	  public List<Object[]>  advancedSearchMemberForPublicRepresentative(String searchType,LocationInputVO locationVo,LocationInputVO inputVo){
			
			StringBuilder str=new StringBuilder();
			
			str.append(" select model.tdpCadreId ,model.firstname,model.mobileNo,model2.userAddress.constituency.name," +
					"   model.memberShipNo,model.voter.voterIDCardNo,model2.publicRepresentativeType.type,model.image " +
					"   from TdpCadre model,PublicRepresentative model2,TdpCadreCandidate model1 where model.isDeleted='N' and model.enrollmentYear = :enrollmentYear"
					+ " and model2.candidate.candidateId = model1.candidate.candidateId and model.tdpCadreId = model1.tdpCadre.tdpCadreId ");
			
			if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
				str.append(" and model2.publicRepresentativeType.publicRepresentativeTypeId in(:roles) ");
			}
			if(locationVo != null)
			{
					if(locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)//State
					{
						if(locationVo.getStateId() == 1)
							str.append("and model2.userAddress.district.districtId > 10  and model2.userAddress.district.districtId <=23");
							if(locationVo.getStateId() == 36)
								str.append("and model2.userAddress.district.districtId >= 1  and model2.userAddress.district.districtId < 11");
					}
					if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)//District
					{
						str.append("and model2.userAddress.district.districtId in(:districtIds) ");
					}
					if(locationVo.getConstituencyIds() != null && locationVo.getConstituencyIds().size() > 0)//Constituency
					{
						str.append("and model.userAddress.constituency.constituencyId in(:constituencyIds) ");
					}
					if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)//Tehsil
					{
						str.append("and model2.userAddress.tehsil.tehsilId in(:tehsilIds) ");
					}
					if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)//Village
					{
						str.append(" and model2.userAddress.panchayat.panchayatId in (:panchayatIds) ");
					}
					if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)//ward
					{
						str.append(" and model2.userAddress.ward.constituencyId in (:wardIds) ");
					}
					if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)//Town
					{
						str.append(" and model2.userAddress.localElectionBody.localElectionBodyId in(:townIds) ");
					}
					if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)//Divison
					{
						str.append(" and model2.userAddress.ward.constituencyId in(:divisonIds) ");
					}
					
			}
			Query query = getSession().createQuery(str.toString());
			if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
				query.setParameterList("roles", inputVo.getDesignationIds());
			}
			if(locationVo != null)
			{
				/*if(locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)
					query.setParameterList("locationStateIds", locationVo.getStateIdsList());*/
				if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)
					query.setParameterList("districtIds", locationVo.getDistrictIdsList());
				if(locationVo.getConstituencyIds() != null && locationVo.getConstituencyIds().size() > 0)
					query.setParameterList("constituencyIds", locationVo.getConstituencyIds());
				if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)
					query.setParameterList("tehsilIds", locationVo.getTehsilIdsList());
				if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)
					query.setParameterList("panchayatIds", locationVo.getVillageIdsList());
				if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)
					query.setParameterList("wardIds", locationVo.getWardIdsList());
				if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
					query.setParameterList("townIds", locationVo.getTownIdsList());
				if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)
					query.setParameterList("divisonIds", locationVo.getDivisionIdsList());
			}
			query.setParameter("enrollmentYear",IConstants.CADRE_ENROLLMENT_NUMBER);
			return query.list();
		}
	  
	  
	  public List<Object[]> advancedSearchMemberForCadreCommittee(String searchType,LocationInputVO locationVo,String locationType,LocationInputVO inputVo){
			
			StringBuilder str=new StringBuilder();
			
			str.append(" select model.tdpCadreId ,model.firstname,model.mobileNo,model.userAddress.constituency.name," +
					"  model.memberShipNo,model.voter.voterIDCardNo,TCM.tdpCommitteeRole.tdpRoles.role,model.image " +
					"  from TdpCadre model,TdpCommitteeMember TCM where model.isDeleted='N' and model.enrollmentYear = :enrollmentYear"
					+ " and model.tdpCadreId = TCM.tdpCadre.tdpCadreId ");
			str.append(" and  model.isDeleted = 'N' " +
					" and  model.enrollmentYear = :enrollmentYear " +
					" and TCM.isActive = 'Y'" );
		 	if(locationVo != null) // For Location Filter
		 	{
					if( locationVo.getLevelId().equals(5l))//mandal,town,div
					{
						if(locationType.equalsIgnoreCase("mandal"))
						{
							str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:mandalLevelId  ");
						}
						else if(locationType.equalsIgnoreCase("town"))
						{
							str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:townLevelId  ");
						}
						
						else if(locationType.equalsIgnoreCase("division"))
						{
							str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:divisionLevelId ");
						}
						
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) ");
						
						
					}else if(locationVo.getLevelId().equals(6l))//village,ward
					{
						
						if(locationType.equalsIgnoreCase("panchayat"))
						{
							str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:panchayatLevelId  ");
						}
						else if(locationType.equalsIgnoreCase("ward"))
						{
							str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:wardLevelId  ");
						}
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) ");
						
						
					}else//State,District Level
					{
						
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId ");
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) ");
					}
			
		}		
		if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
			str.append(" and TCM.tdpCommitteeRole.tdpRolesId in  (:roles) ");
		}
		
		if(inputVo.getCommitteeId() !=null && inputVo.getCommitteeId()>0){
			str.append( " and TCM.tdpCommitteeRole.tdpCommittee.tdpBasicCommittee.tdpBasicCommitteeId =:basicCommitteeId  ");
		}
		Query query = getSession().createQuery(str.toString());
		if(locationVo != null)
		{
				if(locationVo.getLevelId().equals(5l))//mandal,town,div
				{
					
					if(locationType.equalsIgnoreCase("mandal"))
					{
						query.setParameter("mandalLevelId", IConstants.MANDAL_LEVEL_ID);
						if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)
							query.setParameterList("levelValues", locationVo.getTehsilIdsList());
					}
					else if(locationType.equalsIgnoreCase("town"))
					{
						
						query.setParameter("townLevelId", IConstants.TOWN_LEVEL_ID);
						if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
							query.setParameterList("levelValues", locationVo.getTownIdsList());
					}
					
					else if(locationType.equalsIgnoreCase("division"))
					{
						
						query.setParameter("divisionLevelId", IConstants.DIVISION_LEVEL_ID);
						if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)
							query.setParameterList("levelValues", locationVo.getDivisionIdsList());
					}
				
			}else if(locationVo.getLevelId().equals(6l))//village,ward
				{
					
					if(locationType.equalsIgnoreCase("panchayat"))
					{
						query.setParameter("panchayatLevelId", IConstants.VILLAGE_LEVEL_ID);
						if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)
							query.setParameterList("levelValues", locationVo.getVillageIdsList());
						
					}
					else if(locationType.equalsIgnoreCase("ward"))
					{
						query.setParameter("wardLevelId", IConstants.WARD_LEVEL_ID);
						if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)
							query.setParameterList("levelValues", locationVo.getWardIdsList());
						
					}
				
				}else//State,District Level
				{
					query.setParameter("levelId",locationVo.getLevelId());
					if(locationVo.getLevelId() == 10l && locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)
					{
						query.setParameterList("levelValues", locationVo.getStateIdsList());
					}
					else
						query.setParameterList("levelValues", locationVo.getDistrictIdsList());
					
				}
		}
		if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
			query.setParameterList("roles", inputVo.getDesignationIds());
		}
		
		if(inputVo.getCommitteeId() !=null && inputVo.getCommitteeId()>0){
			query.setParameter("basicCommitteeId",inputVo.getCommitteeId() );
		}
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		return query.list();
	}
	  
	
	public List<Object[]> checkVoterCardNumberRegistration(String voterIDCardNo){
		
		Query query = getSession().createQuery("select model.voter.voterIDCardNo," +
									" model.tdpCadreId, model.payMentStatus, model.tdpCadreId" +
									" from TdpCadre model" +
									" where model.voter.voterIDCardNo = :voterIDCardNo" +
									" and model.isDeleted = 'N'" +
									" and model.tdpMemberTypeId = 5" +
									" and model.enrollmentYear = 2016");
		query.setParameter("voterIDCardNo", voterIDCardNo);
		
		return query.list();
	}
	
	public List<Object[]> checkAlreayRegistrationByMemberShipNo(List<Long> tdpCadreIdsList){
		
		Query query = getSession().createQuery("select model.tdpCadreId," +
									" model.parentTdpCadreId, model.payMentStatus" +
									" from TdpCadre model" +
									" where model.parentTdpCadreId in (:tdpCadreIdsList)" +
									" and model.isDeleted = 'N'" +
									" and model.tdpMemberTypeId = 5" +
									" and model.enrollmentYear = 2016");
		query.setParameterList("tdpCadreIdsList", tdpCadreIdsList);
		
		return query.list();
	}

	public List<Object[]> getConstituencyForCadreIds(List<Long> tdpcadreIds){
		Query query = getSession().createQuery("" +
			" select  model.tdpCadreId,model.userAddress.constituency.name from TdpCadre model where model.isDeleted = 'N' and model.tdpCadreId in (:tdpcadreIds) ");
		query.setParameterList("tdpcadreIds",tdpcadreIds);
		return query.list();
	}
	
	
	public List<Object[]> getAddressForCadreIds(List<Long> tdpcadreIds){
		Query query = getSession().createQuery("" +
			" select  model.tdpCadreId,model.userAddress from TdpCadre model where model.isDeleted = 'N' and model.tdpCadreId in (:tdpcadreIds) ");
		query.setParameterList("tdpcadreIds",tdpcadreIds);
		return query.list();
	}
	
	public List<Object[]> checkMemberPaymentExistsByTypeId(String memberShipNo,Long tdpMemberTypeId,Long enrollmentYear)
	{
		Query query = getSession().createQuery("select distinct model.tdpCadreId,model.mobileNo, model.payMentStatus from TdpCadre model where model.memberShipNo = '"+memberShipNo.trim()+"' and " +
				" model.isDeleted = 'N' and model.tdpMemberTypeId ="+tdpMemberTypeId+" and model.enrollmentYear="+enrollmentYear+"  order by model.tdpCadreId desc ");
		return query.list();
	}
	public List<Object[]> getPRConstituenciesByCadreIds(List<Long> cadreIds)
	{
		Query query=getSession().createQuery(" select distinct model.tdpCadre.tdpCadreId,model1.userAddress.constituency.name,model1.userAddress.constituency.constituencyId" +
				" from TdpCadreCandidate model,PublicRepresentative model1 " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model.tdpCadre.tdpCadreId in (:cadreIds)  and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014  ");
	
		query.setParameterList("cadreIds", cadreIds);
		
	return query.list();
	}
	
	public List<UserAddress> getUserAddressForPR(List<Long> cadreIds){
		
		Query query=getSession().createQuery(" select model1.userAddress  from TdpCadreCandidate model,PublicRepresentative model1 " +
				" where model.candidate.candidateId = model1.candidate.candidateId " +
				" and model.tdpCadre.tdpCadreId in (:cadreIds)  and model.tdpCadre.isDeleted ='N' and model.tdpCadre.enrollmentYear = 2014  ");
		query.setParameterList("cadreIds", cadreIds);
		return query.list();
	}
public List<Object[]> getUserAddressForCadre(List<Long> tdpCadreIds){
		
		Query query=getSession().createQuery(" select model.tdpCadreId,model.userAddress from TdpCadre model where model.tdpCadreId in (:tdpCadreIds)");
		   query.setParameterList("tdpCadreIds", tdpCadreIds);
		 return query.list();
	}
public List<Object[]> getCandidatesConstituency(List<Long> tdpCadreIds){
		
		Query query=getSession().createQuery(" select model.tdpCadreId,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name from TdpCadre model where model.tdpCadreId in (:tdpCadreIds)");
		   query.setParameterList("tdpCadreIds", tdpCadreIds);
		 return query.list();
	}

	@Override
	public Object[] getCadreDetailsByMmbrShpId(String memberShipNo) {
		
		StringBuilder queryString=new StringBuilder();
		//tdpCadreId,firstname,lastname,relativename,mobileNo,emailId,gender,age,dateOfBirth,qualification,occupation,stateId,
		//stateName,districtId,districtName,constituencyId,name,tehsilId,tehsilName,constituencyId,name,panchayatId,panchayatName,localElectionBodyId,name
		queryString.append(" select model.tdpCadreId,model.firstname,model.lastname,model.relativename," +//4
							" model.mobileNo,model.emailId,model.gender,model.age," + //8
				            " model.dateOfBirth,model.educationalQualifications.eduQualificationId,model.occupation.occupationId,"+ //11
							" state.stateId,state.stateName,district.districtId,district.districtName,"+//15
				            " constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName,"+//19
							" ward.constituencyId,ward.name,panchayat.panchayatId,panchayat.panchayatName,"+//23
				            " localElectionBody.localElectionBodyId,localElectionBody.name"+ //25
							" from  TdpCadre  model " +
							" left join model.userAddress.state state" +
							" left join model.userAddress.district district" +
							" left join model.userAddress.constituency constituency" +
							" left join model.userAddress.tehsil tehsil" +
							" left join model.userAddress.ward ward" +
							" left join model.userAddress.panchayat panchayat" +
							" left join model.userAddress.localElectionBody localElectionBody  " +
							" where " +
							" model.isDeleted='N' and model.enrollmentYear=:enrollmentYear and model.memberShipNo=:memberShipNo ");
		
			Query query=getSession().createQuery(queryString.toString());
			
			query.setParameter("memberShipNo", memberShipNo);
			query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		
		return (Object[]) query.uniqueResult();
}
	
 public Long getCadreIdByMemberShip(String memberShipNo){
	 
	 Query query = getSession().createQuery("select model.tdpCadreId from TdpCadre model" +
	 		" where " +
	 		" model.memberShipNo = :memberShipNo " +
	 		" and model.isDeleted = 'N'" +
	 		" and model.enrollmentYear = :enrollmentYear ");
	 
	 query.setParameter("enrollmentYear",IConstants.CADRE_ENROLLMENT_YEAR);
	 query.setParameter("memberShipNo", memberShipNo);
	 return (Long) query.uniqueResult();
 }
	
 	public List<Object[]> getMobileNumberDetailsByTdpCadre(Long tdpCadreId){
 		/*Query query = getSession().createQuery("select model.tdpCadreId," +
 													" model.mobileNo," +
 													" model.mobileNumberDetails.mobileConnectionType.mobileConnectionTypeId," +
 													" model.mobileNumberDetails.mobileConnectionType.connectionType," +
 													" model.mobileNumberDetails.mobileNetworkProvider.mobileNetworkProviderId," +
 													" model.mobileNumberDetails.mobileNetworkProvider.providerName," +
 													" model.mobileNumberDetails.mobileBrand.mobileBrandId," +
 													" model.mobileNumberDetails.mobileBrand.brandName," +
 													" model.mobileNumberDetails.mobileBrandModel.mobileBrandModelId," +
 													" model.mobileNumberDetails.mobileBrandModel.modelNumber," +
 													" model.mobileNumberDetails.mobileDeviceFeature.mobileDeviceFeatureId," +
 													" model.mobileNumberDetails.mobileDeviceFeature.type," +
 													" model.mobileNumberDetails.mobileNetworkType.mobileNetworkTypeId," +
 													" model.mobileNumberDetails.mobileNetworkType.networkType" +
 													" from TdpCadre model" +
 													" where model.tdpCadreId = :tdpCadreId" +
 													" and model.isDeleted = 'N'");*/
 		Query query = getSession().createSQLQuery("select tc.tdp_cadre_id,tc.mobile_no,mct.mobile_connection_type_id,mct.connection_type," +
 				" mnp.mobile_network_provider_id,mnp.provider_name,mb.mobile_brand_id,mb.brand_name,mbm.mobile_brand_model_id,mbm.model_number," +
 				" mdf.mobile_device_feature_id,mdf.type,mnt.mobile_network_type_id,mnt.network_type" +
 				" from tdp_cadre tc,mobile_number_details mnd,mobile_connection_type mct,mobile_network_provider mnp,mobile_brand mb," +
 				" mobile_brand_model mbm,mobile_device_feature mdf,mobile_network_type mnt" +
 				" where tc.mobile_number_details_id = mnd.mobile_number_details_id and mnd.mobile_connection_type_id = mct.mobile_connection_type_id" +
 				" and mnd.mobile_network_provider_id = mnp.mobile_network_provider_id and mnd.mobile_brand_id = mb.mobile_brand_id" +
 				" and mnd.mobile_brand_model_id = mbm.mobile_brand_model_id and mnd.mobile_device_feature_id = mdf.mobile_device_feature_id" +
 				" and mnd.mobile_network_type_id = mnt.mobile_network_type_id and tc.tdp_cadre_id = :tdpCadreId and tc.is_deleted = 'N'");
 		query.setParameter("tdpCadreId", tdpCadreId);
 		
 		return query.list();
 	}
 	public List<Object[]> getTotalCadreCountAgeRangeIdWise(Set<Long> ageWiseIds){
 		Query query = getSession().createQuery(" select VAR.voterAgeRangeId,count(distinct TC.tdpCadreId) from VoterAgeRange VAR, TdpCadre TC" +
 				" where TC.age >= VAR.minValue and  TC.age <= VAR.maxValue and VAR.voterAgeRangeId in (:ageWiseIds) and TC.isDeleted = 'N' and TC.enrollmentYear = '2014' and VAR.voterAgeRangeId != 7 " +
 				" group by VAR.voterAgeRangeId");
 		query.setParameterList("ageWiseIds", ageWiseIds);
 		return query.list();
 	}
 	public List<Object[]> genderWiseTdpCadre()
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.gender,count(distinct model.tdpCadreId)" +
				   " from   TdpCadre model " +
				   " where  model.gender is not null " +
				   "        and model.isDeleted = 'N' and model.enrollmentYear = 2014 " +
				   " group by model.gender ");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}
}
