package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dto.CadrePrintInputVO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
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

	public List<Object[]> getCadreDetailsForCadreRegistratiobByconstituencId(Long constituencyId, String queryStr,Long panchayatId,Long boothId,String isPresentCadre)
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
        		" and model.cardNumber is null and model.dispatchStatus is null ");
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
		
		System.out.println(" Cadre Query >>>>>>>>>>>>>>>> " + query.toString());
		
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
        		" and model.cardNumber is null and model.dispatchStatus is null ");
       
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
			query = getSession().createQuery("select model.districtId, model.districtName from District model where model.districtId > 10 and model.state.stateId = 1");
		}
		else if(stateId==2)
		{
			query = getSession().createQuery("select model.districtId, model.districtName from District model where model.districtId < 11 and model.state.stateId = 1 ");
		}
		else
		{
			query = getSession().createQuery("select model.districtId, model.districtName from District model where model.state.stateId = 1 ");
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
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}
	
	public List<Object[]> getTdpCadreDetailsBySearchCriteria(String refNo, String mobileNo)
	{
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select distinct model.memberShipNo, model.refNo, model.firstname, model.lastname, model.relativename,  ");
		queryStr.append(" model.gender, model.userAddress.constituency.name, model.mobileNo,model.image, model.dispatchStatus,model.tdpCadreId ");
		queryStr.append(" from TdpCadre model where model.isDeleted = 'N' ");
		
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
	@SuppressWarnings("unchecked")
	public List<Object[]> getLastHoursWorkingMembersDetails(Date presentDate, Date lastHours) {
		
		String resultQuery = "select model.tdpCadreId, model.insertedBy.cadreSurveyUserId, model.insertedBy.userName, model.latitude, model.longititude from TdpCadre model right join (select newmodel.insertedBy.cadreSurveyUserId crtUser, max(newmodel.surveyTime) survTime from TdpCadre newmodel where newmodel.enrollmentYear = 2014 and newmodel.isDeleted = 'N' and newmodel.dataSourceType = 'TAB' and (newmodel.surveyTime >= :lastHours and newmodel.surveyTime <= :presentDate) and newmodel.insertedBy.cadreSurveyUserId is not null group by newmodel.insertedBy.cadreSurveyUserId) surRes on model.insertedBy.cadreSurveyUserId = crtUser and model.surveyTime = survTime";
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
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWorkingMembersDetails(Date date){
		
		String resultQuery = "select model.tdpCadreId, model.insertedBy.cadreSurveyUserId, model.insertedBy.userName, model.latitude, model.longititude from TdpCadre model right join (select newmodel.insertedBy.cadreSurveyUserId crtUser, max(newmodel.surveyTime) survTime from TdpCadre newmodel where newmodel.enrollmentYear = 2014 and newmodel.isDeleted = 'N' and newmodel.dataSourceType = 'TAB' and date(model.surveyTime) = :date and newmodel.insertedBy.cadreSurveyUserId is not null group by newmodel.insertedBy.cadreSurveyUserId) surRes on model.insertedBy.cadreSurveyUserId = crtUser and model.surveyTime = survTime";
		Query query = getSession().createQuery(resultQuery);
		query.setDate("date", date);
		
		return query.list();
	}
	
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
	
}
