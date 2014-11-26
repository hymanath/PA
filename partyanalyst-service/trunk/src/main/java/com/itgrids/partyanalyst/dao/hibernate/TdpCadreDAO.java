package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.model.TdpCadre;
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
	
	public Long getWorkingMembersCount(Date date){
		Query query = getSession().createQuery("select count(distinct model.insertedBy.cadreSurveyUserId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType='TAB' and date(model.surveyTime) =:date and model.insertedBy.cadreSurveyUserId is not null");
		
		query.setDate("date", date);
		return (Long)query.uniqueResult();
	}
	public Long getWorkingMembersForWebCount(Date date){
		Query query = getSession().createQuery("select count(distinct  model.insertedWebUser.userId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType='WEB' and date(model.surveyTime) =:date and  model.insertedWebUser.userId is not null");
		
		query.setDate("date", date);
		return (Long)query.uniqueResult();
	}
	public List<TdpCadre> getVoterByVoterId(Long voterId)
	{
		Query query = getSession().createQuery("select model  from TdpCadre model where model.voterId = :voterId  and model.isDeleted = 'N'");
		query.setParameter("voterId", voterId);
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
			queryStr.append(" model.memberShipNo like '%"+enrollmentNo+"%' ");
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
		Query query = getSession().createQuery("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo from TdpCadre model where model.memberShipNo = :memberCardNo  and model.isDeleted = 'N'");
		query.setParameter("memberCardNo", memberCardNo);
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseCadreDetails(Long panchayatId )
	{
		Query query = getSession().createQuery("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo,model.refNo,model.cardNumber,model.image from TdpCadre model where model.userAddress.panchayat.panchayatId = :panchayatId  and model.isDeleted = 'N'");
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
		queryStr.append("  and model.enrollmentYear =:year group by model.userAddress.tehsil.tehsilId,model.enrollmentYear");

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
	
	public Long getLastHoursWorkingMemberCount(Date presentDate, Date lastHours)
	{
		Query query = getSession().createQuery("select " +
				" count(distinct model.insertedBy.cadreSurveyUserId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' " +
				" and model.dataSourceType='TAB' and ( model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and model.insertedBy.cadreSurveyUserId is not null");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		//System.out.println(" Cadre Query >>>>>>>>>>>>>>>> " + query.toString());
		
		return (Long)query.uniqueResult();
	}
	
	public Long getLastHoursWorkingMemberCountForWeb(Date presentDate, Date lastHours)
	{
		Query query = getSession().createQuery("select " +
				" count(distinct  model.insertedWebUser.userId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' " +
				" and model.dataSourceType='WEB' and ( model.surveyTime >= :lastHours and model.surveyTime <= :presentDate) and  model.insertedWebUser.userId is not null");
		
		query.setParameter("presentDate", presentDate);
		query.setParameter("lastHours", lastHours);
		
		//System.out.println(" Cadre Query >>>>>>>>>>>>>>>> " + query.toString());
		
		return (Long)query.uniqueResult();
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

		queryStr.append("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo,model.refNo,model.cardNumber,model.image from TdpCadre model where model.isDeleted = 'N' " );
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
		queryStr.append(" model.gender, model.userAddress.constituency.name, model.mobileNo,model.image, model.cardNumber,model.tdpCadreId, model.voter.voterIDCardNo ");
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
		queryStr.append(" and (model.insertedWebUser.userId !=3930 or model.insertedWebUser.userId is null) ");
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
		queryStr.append(" and model.insertedWebUser.userId = 3930 ");
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
	queryString.append(" insert into "+table+" (voter_name,voter_id_card_number,panchayat_name,mandal_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time) " );
	queryString.append(" select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no,P.name_local as VILLAGE,T.name_local as MANDAl ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'R' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg' ),DATE_ADD(now(),INTERVAL 330 MINUTE) ");
	queryString.append(" from   tdp_cadre TDP,   panchayat P,   constituency C,   tehsil T,   district D,   user_address UA,		voter_names V,voter VV  ,booth_publication_voter BPV , booth B	where  TDP.address_id = UA.user_address_id and UA.constituency_id = C.constituency_id  and UA.panchayat_id = P.panchayat_id  and UA.district_id = D.district_id  and UA.tehsil_id = T.tehsil_id and TDP.voter_id = V.voter_id ");
	queryString.append(" and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL'  and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11 and TDP.constituency_id is null and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and TDP.inserted_web_user_id != 3930 and TDP.data_source_type != 'ONLINE' and date(TDP.inserted_time) > '2014-11-15' ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.constituency_id in (:constIds) " );
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
	queryString.append(" insert into "+table+"  (voter_name,voter_id_card_number,panchayat_name,mandal_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time) " );
	queryString.append(" select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no,P.name_local as VILLAGE,T.name_local as MANDAl ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'R' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg' ) ,DATE_ADD(now(),INTERVAL 330 MINUTE) ");
	queryString.append(" from   tdp_cadre TDP,   panchayat P,   constituency C,   tehsil T,   district D, user_address UA,voter_names V,voter VV   ,booth_publication_voter BPV , booth B 	where  TDP.address_id = UA.user_address_id and UA.constituency_id = C.constituency_id  and UA.panchayat_id = P.panchayat_id  and UA.district_id = D.district_id  and UA.tehsil_id = T.tehsil_id and TDP.voter_id = V.voter_id ");
	queryString.append(" and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL-URBAN' and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11   and TDP.constituency_id is null  and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and TDP.inserted_web_user_id != 3930 and TDP.data_source_type != 'ONLINE' and date(TDP.inserted_time) > '2014-11-15' ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.constituency_id in (:constIds) " );
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
	queryString.append("  insert into "+table+"  (voter_name,voter_id_card_number,muncipality_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time) ");
	queryString.append("  select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no, LEB.name_local as MUNCIPALITYNAME ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'RU' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg'),DATE_ADD(now(),INTERVAL 330 MINUTE) ");
	queryString.append("  from     tdp_cadre TDP, local_election_body LEB,   voter_names V,  voter VV,  constituency C,  district D, user_address UA   ,booth_publication_voter BPV , booth B	where TDP.address_id = UA.user_address_id and UA.local_election_body = LEB.local_election_body_id  and UA.constituency_id = C.constituency_id   and UA.district_id = D.district_id " );
	queryString.append("  and TDP.voter_id = V.voter_id  and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'RURAL-URBAN' and BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11 and TDP.constituency_id is null and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and TDP.inserted_web_user_id != 3930 and TDP.data_source_type != 'ONLINE' and date(TDP.inserted_time) > '2014-11-15' ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.constituency_id in (:constIds) " );
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
	queryString.append("  insert into "+table+"  (voter_name,voter_id_card_number,muncipality_name,constituency_name,district_name,tdp_cadre_id,member_ship_member,photo_type,ref_number,image,constituency_type,voter_image_path,inserted_time) ");
	queryString.append("  select   distinct concat(V.firstname,' ',V.lastname)  as VOTERNAME, VV.voter_id_card_no, LEB.name_local as MUNCIPALITYNAME ,C.name_local as CONSTITYENCY,D.name_local as DISTRICT,TDP.tdp_cadre_id as UNIQUEID,SUBSTRING(membership_id FROM 5) AS MEMBERSHIPID,TDP.photo_type AS PHOTOTYPE ,TDP.ref_no,concat('www.mytdp.com/images/cadre_images/',TDP.image) as MEMBER_IMAGE ,'U' , concat(B.constituency_id,'/','Part',B.part_no,'/',VV.voter_id_card_no ,'.jpg'),DATE_ADD(now(),INTERVAL 330 MINUTE) ");
	queryString.append("  from     tdp_cadre TDP, local_election_body LEB,   voter_names V,  voter VV,  constituency C,  district D, user_address UA  ,booth_publication_voter BPV , booth B	where TDP.address_id = UA.user_address_id and UA.local_election_body = LEB.local_election_body_id  and UA.constituency_id = C.constituency_id   and UA.district_id = D.district_id " );
	queryString.append("  and TDP.voter_id = V.voter_id  and TDP.voter_id = VV.voter_id and TDP.enrollment_year = 2014 and TDP.is_deleted = 'N' and C.area_type = 'URBAN'  and   BPV.voter_id = VV.voter_id 	and BPV.booth_id = B.booth_id and B.publication_date_id = 11 and TDP.constituency_id is null and TDP.image is not null and TDP.voter_id is not null and concat(V.firstname,' ',V.lastname) is not null and TDP.family_voterId is null and TDP.inserted_web_user_id != 3930 and TDP.data_source_type != 'ONLINE' and date(TDP.inserted_time) > '2014-11-15' ");
	//queryString.append(" and  date(TDP.inserted_time) = :prevDate " );
	
	if(constIds != null)
	{
		queryString.append("  and C.constituency_id in (:constIds) " );
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
				" model.userAddress.userAddressId from TdpCadre model " +
				" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
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
	
	
	 
		public List<Object[]> getDaywiseWebuserDetailsByUserANDType(Long userId, Date fromDate,Date toDate,String type)
		{
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select date(model.surveyTime), count(model.tdpCadreId) from TdpCadre model where model.isDeleted='N' and model.enrollmentYear = 2014 and ");
			queryStr.append(" model.insertedWebUserId = :userId and model.dataSourceType='"+type+"' ");
			
			if(fromDate != null && toDate != null)
			{
				queryStr.append(" and ( date(model.surveyTime) >= :fromDate and date(model.surveyTime) <= :toDate  )  ");
			}
			
			queryStr.append(" group by date(model.surveyTime) order by date(model.surveyTime) desc ");
			
			Query query=getSession().createQuery(queryStr.toString());
			
			if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate",fromDate);	
				query.setDate("toDate",toDate);	
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
		Query query=getSession().createQuery("select count(model.tdpCadreId),model.userAddress.constituency.name " +
				" from TdpCadre model " +
				" where model.userAddress.constituency.constituencyId in(:constituencyIds) and" +
				"  model.enrollmentYear = 2014 and" +
				"  model.isDeleted='N'" +
				"  group by model.userAddress.constituency.constituencyId " +
				"  order by model.userAddress.constituency.name");
        query.setParameterList("constituencyIds", constituencyIds);
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
	
	public Integer checkForExists(String uniqueKey)
	{
		Query query = getSession().createQuery("select count(model.uniqueKey) from TdpCadre model where model.uniqueKey = :uniqueKey and  model.isDeleted = 'N' and model.enrollmentYear = :enrollmentYear and model.familyVoterId is not null ");
		query.setParameter("uniqueKey", uniqueKey);
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
				" model.userAddress.userAddressId from TdpCadre model " +
				" where model.memberShipNo in(:memberCardNos) and model.isDeleted = 'N'");
		query.setParameterList("memberCardNos", memberCardNos);
		return query.list();
	}
}
