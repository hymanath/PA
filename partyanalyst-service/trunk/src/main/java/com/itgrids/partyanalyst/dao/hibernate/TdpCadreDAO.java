package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreDAO extends GenericDaoHibernate<TdpCadre, Long> implements ITdpCadreDAO{

	public TdpCadreDAO() {
		super(TdpCadre.class);
	}

	public List<Object[]> getRegisterCadreInfoBetweenDates(Date fromDate,Date toDate){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId from TdpCadre model where model.isDeleted = 'N' and  model.userAddress.state.stateId = 1 and model.enrollmentYear = 2014 ");
		
		if(fromDate != null){
			queryStr.append(" and date(model.surveyTime) >=:fromDate ");
		}
		
		if(toDate != null){
			queryStr.append(" and date(model.surveyTime) <=:toDate ");
		}
		
		queryStr.append(" group by model.userAddress.district.districtId ");	

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
		Query query = getSession().createQuery("select model.memberShipNo from TdpCadre model order by model.tdpCadreId desc ");
		query.setMaxResults(1);
		return (String)query.uniqueResult();
	}

	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre)
	{
		StringBuilder str = new StringBuilder();
		
		if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
		{
			str.append(" select distinct TC.tdpCadreId, TC.voter.name, TC.voter.relativeName, TC.dateOfBirth, TC.voter.age, TC.voter.gender, TC.voter.houseNo, UA.userAddressId,TC.voterId,TC.voter.relationshipType ");
		}
		else
		{
			str.append(" select distinct TC.tdpCadreId, TC.firstname, TC.relativename, TC.dateOfBirth, TC.age, TC.gender, UA.houseNo, UA.userAddressId,TC.voterId,TC.relativeType ");
		}

		str.append(" ,TC.image from TdpCadre TC, UserAddress UA where "+queryStr+" TC.userAddress.constituency.constituencyId = :constituencyId ");
		
		if(panchayatId.longValue() != 0L)
		{
			str.append(" and TC.userAddress.panchayatId = :panchayatId ");
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
		
		str.append(" and TC.userAddress.userAddressId = UA.userAddressId order by TC.houseNo ");
		
		Query query = getSession().createQuery(str.toString()); 
		
		query.setParameter("constituencyId", constituencyId);
		

		if(panchayatId.longValue() != 0L)
		{
			query.setParameter("panchayatId", panchayatId);
		}
		
		if(boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}		
		
		query.setParameter("cadreEnrollmentYear", IConstants.CADRE_ENROLLMENT_NUMBER);

		return query.list();
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
				
		str.append(" and model.userAddress.state.stateId = 1   ");
		
		Query query = getSession().createQuery(str.toString());
		
		return (Long) query.uniqueResult();
	}
	
	
	public Long getWorkStartedConstituencyYearCount(Long year,String state){
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
				
		str.append(" and model.userAddress.state.stateId = 1 ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("year", year);
		
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
	
	public List<Object[]> getCadreInfoConstituencytWise(List<Long> constituencyIds){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.constituency.constituencyId,model.userAddress.constituency.name,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N'   and model.userAddress.constituency.constituencyId in(:constituencyIds) " +
				" group by model.userAddress.constituency.constituencyId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	public List<Object[]> getCadreInfoDistrictWise(List<Long> districtIds){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.district.districtId,model.userAddress.district.districtName,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N'   and model.userAddress.district.districtId in(:districtIds) " +
				" group by model.userAddress.district.districtId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("districtIds", districtIds);
		return query.list();
	}
	
	public Long getWorkingMembersCount(Date date){
		Query query = getSession().createQuery("select count(distinct model.insertedBy.cadreSurveyUserId) from TdpCadre model where model.enrollmentYear = 2014 and model.isDeleted = 'N' and model.dataSourceType='TAB' and date(model.surveyTime) =:date and model.insertedBy.cadreSurveyUserId is not null");
		
		query.setDate("date", date);
		return (Long)query.uniqueResult();
	}
	
	public List<TdpCadre> getVoterByVoterId(Long voterId)
	{
		Query query = getSession().createQuery("select model  from TdpCadre model where model.voterId = :voterId");
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
		queryStr.append(" select model.firstname, model.relativename, model.previousEnrollmentNo, model.tdpCadreId from TdpCadre model where ");
		boolean candiNameExist = false;
		if(candidateName.length()>2){
			candiNameExist = true;
			queryStr.append(" model.firstname like '%"+candidateName+"%' ");
		}
		
		if(enrollmentNo.length()>2){
			if(candiNameExist){
				queryStr.append(" and ");
			}
			queryStr.append(" model.previousEnrollmentNo like '%"+enrollmentNo+"%' ");
		}
		
		
		if(constid != null && constid.longValue() != 0L)
		{
			queryStr.append(" and model.userAddress.constituency.constituencyId = :constid ");
		}
		
		if(panchayatId != null && panchayatId.longValue() != 0L)
		{
			queryStr.append(" and model.userAddress.panchayatId = :panchayatId ");
		}
		if(boothId != null && boothId.longValue() != 0L)
		{
			queryStr.append(" and model.userAddress.booth.boothId = :boothId");
		}
		if(isPresentCadre != null && isPresentCadre.trim().length()>0 && !isPresentCadre.equalsIgnoreCase("0"))
		{
			queryStr.append(" and model.enrollmentYear in (:year) ");
		}
		
		else
		{
			queryStr.append(" and model.enrollmentYear not in (:year) ");
		}
		
		queryStr.append("  and model.isDeleted = 'N' order by model.firstname ");
		
		Query query = getSession().createQuery(queryStr.toString());
		
		if(constid != null && constid.longValue() != 0L)
		{
			query.setParameter("constid", constid);
		}
		
		if(panchayatId != null && panchayatId.longValue() != 0L)
		{
			query.setParameter("panchayatId", panchayatId);
		}
		if(boothId != null && boothId.longValue() != 0L)
		{
			query.setParameter("boothId", boothId);
		}
		
			query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);

		return query.list();
		
	}
		
	public List<Object[]> getCandidateDataCollectionInfo(Date fromDate,Date toDate){
		//0 count,1 name,2 min,3 max,4 date,5 id
		Query query = getSession().createQuery("select count(*),model.insertedBy.userName,min(model.surveyTime),max(model.surveyTime),date(model.surveyTime),model.insertedBy.cadreSurveyUserId  from TdpCadre model where model.enrollmentYear = 2014  and model.dataSourceType ='TAB'  " +
				"   and model.isDeleted = 'N' and date(model.surveyTime) >=:fromDate and date(model.surveyTime) <=:toDate group by date(model.surveyTime),model.insertedBy.cadreSurveyUserId order by date(model.surveyTime),model.insertedBy.userName");
		query.setDate("fromDate", fromDate);
		query.setDate("toDate", toDate);
		return query.list();
	}
	
	public Long checkMemberShipExistsOrNot(String randomNo){
        Query query = getSession().createQuery("select count(*) from TdpCadre model where model.enrollmentYear = 2014 and model.memberShipNo =:randomNo ");
		query.setParameter("randomNo", randomNo);
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getCadreDetailsByMemberId(String memberCardNo)
	{
		Query query = getSession().createQuery("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo from TdpCadre model where model.memberShipNo = :memberCardNo");
		query.setParameter("memberCardNo", memberCardNo);
		return query.list();
	}
	
	public List<Object[]> getPanchayatWiseCadreDetails(Long panchayatId )
	{
		Query query = getSession().createQuery("select model.memberShipNo , model.voterId,model.firstname,model.relativename,model.voter.voterId,model.voter.voterIDCardNo from TdpCadre model where model.userAddress.panchayat.panchayatId = :panchayatId");
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
		Query query = getSession().createQuery("update TdpCadre model set model.cardNumber = :nfcCardNo where model.voterId = :voterId and  model.enrollmentYear = :year ");
		query.setParameter("nfcCardNo", nfcCardNo);
		query.setParameter("year", IConstants.CADRE_ENROLLMENT_NUMBER);
		query.setParameter("voterId", voterId);
		Integer c = query.executeUpdate();
		
		return c;
	}
	
	public List<Object[]> getConstituencyWiseAgeRangeCadreCount(Long constituencyId,String ageRange){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.constituencyId = :constituencyId ");
		if(ageRange.equals("18-25")){
		queryStr.append(" and model.age >=18 and model.age<=25 ");
		}else if(ageRange.equals("26-35")){
		queryStr.append(" and model.age >=26 and model.age<=35 ");
		}else if(ageRange.equals("36-45")){
		queryStr.append(" and model.age >=36 and model.age<=45 ");
		}else if(ageRange.equals("46-60")){
		queryStr.append(" and model.age >=46 and model.age<=60 ");
		}else if(ageRange.equals("above 60")){
		queryStr.append(" and model.age >60 ");
		}
		queryStr.append(" group by model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("constituencyId", constituencyId);
		return query.list();
    }
	
	public List<Object[]> getDistrictWiseAgeRangeCadreCount(Long districtId,String ageRange){
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append("select count(model.tdpCadreId),model.enrollmentYear " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.district.districtId = :districtId ");
		if(ageRange.equals("18-25")){
		queryStr.append(" and model.age >=18 and model.age<=25 ");
		}else if(ageRange.equals("26-35")){
		queryStr.append(" and model.age >=26 and model.age<=35 ");
		}else if(ageRange.equals("36-45")){
		queryStr.append(" and model.age >=36 and model.age<=45 ");
		}else if(ageRange.equals("46-60")){
		queryStr.append(" and model.age >=46 and model.age<=60 ");
		}else if(ageRange.equals("above 60")){
		queryStr.append(" and model.age >60 ");
		}
		queryStr.append(" group by model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("districtId", districtId);
		return query.list();
    }

	public Long getConstituencyWiseYearCount(Long constituencyId,Long enrollmentYear){
		
		String queryStr="select count(model.tdpCadreId) " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.constituencyId = :constituencyId " +
				" and model.enrollmentYear = :enrollmentYear" +
				" group by model.enrollmentYear ";
		Query query = getSession().createQuery(queryStr);
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("enrollmentYear", enrollmentYear);
		return (Long)query.uniqueResult();
    }
	
	public Long getDistrictWiseYearCount(Long districtId,Long enrollmentYear){
				
		String queryStr="select count(model.tdpCadreId) " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.district.districtId = :districtId " +
				" and model.enrollmentYear = :enrollmentYear " +
				" group by model.enrollmentYear ";
		Query query = getSession().createQuery(queryStr);
		query.setParameter("districtId", districtId);
		query.setParameter("enrollmentYear", enrollmentYear);
		return (Long)query.uniqueResult();
    }
	
	public List<Object[]> getConstituencyWiseGenderCadreCount(Long constituencyId){
		
		
		String queryStr="select count(model.tdpCadreId),model.enrollmentYear,model.gender " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.constituencyId = :constituencyId " +
				" group by  model.gender,model.enrollmentYear ";

		Query query = getSession().createQuery(queryStr);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
    }
	
	public List<Object[]> getDistrictWiseGenderCadreCount(Long districtId){
		
		String queryStr="select count(model.tdpCadreId),model.enrollmentYear,model.gender " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.district.districtId = :districtId  " +
				" group by  model.gender,model.enrollmentYear ";

		Query query = getSession().createQuery(queryStr);
		query.setParameter("districtId", districtId);
		
		return query.list();
    }

	
	public List<Object[]> getConstituencyWiseCastCadreCount(Long constituencyId){
		
		
		String queryStr= "select count(model.tdpCadreId),model.enrollmentYear,model.casteState.casteStateId,model.casteState.caste.casteName " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.constituencyId = :constituencyId " +
				" group by  model.casteState.casteStateId,model.enrollmentYear ";

		Query query = getSession().createQuery(queryStr);
		query.setParameter("constituencyId", constituencyId);
		
		return query.list();
    }
	
	public List<Object[]> getDistrictWiseCastCadreCount(Long districtId){
		
		String queryStr= "select count(model.tdpCadreId),model.enrollmentYear,model.casteState.casteStateId,model.casteState.caste.casteName " +
				" from TdpCadre model where model.isDeleted = 'N' " +
				" and model.userAddress.constituency.district.districtId = :districtId " +
				" group by  model.casteState.casteStateId,model.enrollmentYear ";

		Query query = getSession().createQuery(queryStr);
		query.setParameter("districtId", districtId);
		
		return query.list();
    }
		
	public List<Object[]> getCadreInfoPanchayatWise(List<Long> panchayatIds){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' and model.userAddress.panchayat.panchayatId in(:panchayatIds) " +
				" group by model.userAddress.panchayat.panchayatId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("panchayatIds", panchayatIds);
		return query.list();
	}
	
	public List<Object[]> getCadreInfoBoothWise(List<Long> boothIds){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.booth.boothId,model.userAddress.booth.partNo,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' and model.userAddress.booth.boothId in(:boothIds) " +
				" group by model.userAddress.booth.boothId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	public List<Object[]> getCadreInfoMandalWise(List<Long> tehsilIds){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' and model.userAddress.tehsil.tehsilId in(:tehsilIds) " +
				" group by model.userAddress.tehsil.tehsilId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("tehsilIds", tehsilIds);
		return query.list();
	}
	public List<Object[]> getCadreInfoLocalBodyWise(List<Long> localBdyIds){
		StringBuilder queryStr = new StringBuilder();
		//0 count,1 id,2 name ,3 year
		queryStr.append("select count(model.tdpCadreId),model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name,model.enrollmentYear from TdpCadre model where model.isDeleted = 'N' and model.userAddress.localElectionBody.localElectionBodyId in(:localBdyIds) " +
				" group by model.userAddress.localElectionBody.localElectionBodyId,model.enrollmentYear");

		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("localBdyIds", localBdyIds);
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
}
