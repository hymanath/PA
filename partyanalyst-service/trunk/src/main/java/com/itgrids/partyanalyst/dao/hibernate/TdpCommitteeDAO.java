package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.TdpCommittee;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCommitteeDAO extends GenericDaoHibernate<TdpCommittee, Long>  implements ITdpCommitteeDAO {

	public TdpCommitteeDAO() {
		super(TdpCommittee.class);
	}

	public List<Object[]> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue){
		Query query = getSession().createQuery("select model.tdpCommitteeId,model.tdpBasicCommittee.name from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue and " +
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2 and model.tdpCommitteeEnrollmentId =:committeeEnrollmentId ");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("committeeEnrollmentId", IConstants.CURRENT_ENROLLMENT_ID);
		return query.list();
	}
	
	public List<Long> getMainCommittiesInALocation(Long levelId,Long levelValue,List<Long> enrollmentIds,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.tdpCommitteeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue and " +
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1  ");
		if(enrollmentIds != null && enrollmentIds.size() > 0){
			sb.append("  and model.tdpCommitteeEnrollmentId in (:enrollmentIds) ");
		}
		if(startDate != null && endDate != null){
			sb.append( " and (" +
					" (date(model.startedDate) between :startDate and :endDate )  OR  (date(model.completedDate) between :startDate and :endDate )" +
					"  )" );
		}//sb.append(" and date(model.startedDate) between :startDate and :endDate");
			
			
		Query query = getSession().createQuery(sb.toString());
			query.setParameter("levelId", levelId);
			query.setParameter("levelValue", levelValue);
			if(enrollmentIds != null && enrollmentIds.size() > 0){
				query.setParameterList("enrollmentIds", enrollmentIds);
			}
			
		if(startDate != null && endDate != null){
			query.setDate("startDate", startDate);
			query.setDate("endDate", endDate);
		}
		return query.list();
	}
	
	public List<Long> getTdpCommittee(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue){
		Query query = getSession().createQuery("select model.tdpCommitteeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:tdpCommitteeLevelId and  " +
				" model.tdpCommitteeLevelValue =:tdpCommitteeLevelValue and model.tdpBasicCommittee.tdpBasicCommitteeId = :tdpBasicCommitteeId");
		query.setParameter("tdpBasicCommitteeId", tdpBasicCommitteeId);
		query.setParameter("tdpCommitteeLevelId", tdpCommitteeLevelId);
		query.setParameter("tdpCommitteeLevelValue", tdpCommitteeLevelValue);
		return query.list();
	}

	public Long getTotalCommitteesCountByLocation(String state,List<Long> levelIds,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList ,List<Long> enrollmentIdsList,Date startDate,Date endDate){
		//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId
		Long stateId = 1L;
		if(state != null && state.equalsIgnoreCase("AP"))
			stateId = 1L;
		else if(state != null && (state.equalsIgnoreCase("TS") || state.equalsIgnoreCase("TG")))
			stateId = 36L;
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId) " +
				" from TdpCommittee model where model.tdpBasicCommittee.tdpBasicCommitteeId = 1  ");
		
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			str.append(" and model.tdpCommitteeLevelValue in (:locationlevelValueList) ");
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			str.append(" and model.userAddress.constituency.constituencyId in (:assemblyIds) ");
		}		
		else if(districtIds != null && districtIds.size()>0)
		{
			str.append(" and model.userAddress.district.districtId in (:districtIds) ");
		}
		else if(state != null)
		{
			str.append(" and model.userAddress.state.stateId = :state ");
		}
		
		if(levelIds != null && levelIds.size() > 0)
		{
			str.append(" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) ");
		}	
		if(startDate != null && endDate != null){
			str.append( " and ( (date(model.startedDate) between :startDate and :endDate )  OR  (date(model.completedDate) between :startDate and :endDate )  )" );
		
		}
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			str.append(" and model.tdpCommitteeEnrollmentId in (:enrollmentIdsList) ");
		}	
		
		//str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId ");

		Query query = getSession().createQuery(str.toString());
		
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			query.setParameterList("locationlevelValueList", locationlevelValueList);
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			query.setParameterList("assemblyIds", assemblyIds);
		}
		else if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		else if(state != null)
		{
			query.setParameter("state", stateId);
		}
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			query.setParameterList("enrollmentIdsList", enrollmentIdsList);
		}
		if(startDate != null && endDate != null){
			query.setDate("startDate",startDate);
			query.setDate("endDate",endDate);
		}
		
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getLocationByTypeIdAndLevel(List<Long> levelIds,Long committeTypeId,Long constituencyId,String status,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate)
	{
		String[] dataArr = status.split(":");
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCommitteeLevelValue,model.isCommitteeConfirmed,model.tdpCommitteeLevelId ," +		
				" model.district.districtName ," +
				" model.constituency.name " +
				" from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in(:levelIds) and " +
				" model.tdpBasicCommittee.tdpBasicCommitteeId =:committeTypeId  " +
				" and model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
			if(dataArr[0].toString().equalsIgnoreCase("Conform")){
				str.append(" and  model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				if(stDate != null && edDate != null){
					str.append( " and  (date(model.completedDate) between :stDate and :edDate ) " );
				}
			}
			else if(dataArr[0].toString().equalsIgnoreCase("Started")){
				str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
				if(stDate != null && edDate != null){
					str.append( " and  (date(model.startedDate) between :stDate and :edDate ) " );
				}
			}
			else if(dataArr[0].toString().equalsIgnoreCase("NotStarted"))
				str.append(" and model.startedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
			
			if(dataArr[1].toString().equalsIgnoreCase("state")){
				str.append( " and model.userAddress.state.stateId =:constituencyId ");
			}else if(dataArr[1].toString().equalsIgnoreCase("district")){
				str.append( " and model.userAddress.district.districtId =:constituencyId ");
			}else{
				str.append( " and model.userAddress.constituency.constituencyId =:constituencyId ");
			}
		str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevelValue");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("levelIds", levelIds);
		query.setParameter("committeTypeId", committeTypeId);
		query.setParameter("constituencyId", constituencyId);
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(!dataArr[0].toString().equalsIgnoreCase("NotStarted"))
			if(stDate != null && edDate != null){
				query.setParameter("stDate", stDate);
			    query.setParameter("edDate", edDate);
			}
		return query.list();
	}
	
	public List<Object[]> getLocationsByTypeIdAndLevel(Long levelId,Long committeTypeId,List<Long> locationValues,String status,List<Long> committeeEnrollmentIdsLst,Date stDate,Date edDate)
	{
		String[] dataArr = status.split(":");
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCommitteeLevelValue,model.isCommitteeConfirmed,model.tdpCommitteeLevelId ," +		
				" model.district.districtName ," +
				" c.name from TdpCommittee model left join model.constituency c where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and " +
				" model.tdpBasicCommittee.tdpBasicCommitteeId =:committeTypeId and model.tdpCommitteeLevelValue in(:locationValues) " +
				" and model.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		 
		if(dataArr[0].toString().equalsIgnoreCase("Conform")){
		str.append(" and  model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		if(stDate != null && edDate != null){
			 str.append( " and ( (date(model.startedDate) between :stDate and :edDate )  OR  ( date(model.completedDate) between :stDate and :edDate )  )" );
		 }
		}else if(dataArr[0].toString().equalsIgnoreCase("Started")){
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			if(stDate != null && edDate != null){
				 str.append( " and ( (date(model.startedDate) between :stDate and :edDate )  OR  ( date(model.completedDate) between :stDate and :edDate )  )" );
			 }
		}else if(dataArr[0].toString().equalsIgnoreCase("NotStarted"))
		str.append(" and model.startedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		 
		str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevelValue");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("committeTypeId", committeTypeId);
		query.setParameterList("locationValues", locationValues);
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(stDate != null && edDate != null && !dataArr[0].toString().equalsIgnoreCase("NotStarted")){
			query.setParameter("stDate", stDate);
		    query.setParameter("edDate", edDate);
		}
		return query.list();
	}
	
	public List<Object[]> getTotalCommitteesPanchayatLevel(Long constituencyId){
		//0count ,1 basic committeeId,2basic committee name,3committeeType
		Query query = getSession().createQuery("select count(model.tdpCommitteeId),model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name," +
				"model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model " +
				" where  model.constituency.constituencyId=:constituencyId and model.tdpCommitteeLevel.tdpCommitteeLevelId in(6,8) group by model.tdpBasicCommittee.tdpBasicCommitteeId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getLocationWiseDetails(List<Long> locationValues,Long locationTypeId){
		Query query = getSession().createQuery("select TBC.tdpCommitteeType.tdpCommitteeTypeId ,TBC.name,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed,count(*) " +
				"from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId = :locationTypeId " +
				"and TC.tdpCommitteeLevelValue in (:locationValues) group by TBC.tdpCommitteeTypeId ,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
		//query.setParameter("constituencyId", constituencyId);
		query.setParameter("locationTypeId", locationTypeId);
		query.setParameterList("locationValues", locationValues);
		return query.list();
	}
	
	/*public List<Object[]> getLocationWiseVillageDetails(Long constituencyId){
		//0 CommitteeTypeId,1 name,2 basiccommId,3confirmd,4 count
		Query query = getSession().createQuery("select TBC.tdpCommitteeType.tdpCommitteeTypeId ,TBC.name,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed,count(*) " +
				" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId in (6,8) " +
				" and  TC.constituency.constituencyId = :constituencyId group by TBC.tdpCommitteeTypeId ,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}*/
    //getting total and confirmed counts
	public List<Object[]> getLocationWiseVillageDetails(Long constituencyId,String reqLocationType,Date startDate,Date endDate, 
			 List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsLsit){
		//0 basiccommId,1 name,2confirmd,3count
		/*Query query = getSession().createQuery("select TBC.tdpBasicCommitteeId,TBC.name,TC.isCommitteeConfirmed,count(*),TC.completedDate " +
				" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId in (6,8) " +
				" and  TC.constituency.constituencyId = :constituencyId group by TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
				*/
		StringBuilder sb = new StringBuilder();
		sb.append(" select TBC.tdpBasicCommitteeId,TBC.name,TC.isCommitteeConfirmed,count(distinct TC.tdpCommitteeId),TC.completedDate  ");
		sb.append(" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  ");
		
		if(levelIdsLsit != null && levelIdsLsit.size() > 0){
			sb.append(" and TC.tdpCommitteeLevelId in (:levelIdsLsit) " );
		}
		if(reqLocationType != null && reqLocationType.trim().equalsIgnoreCase("State"))
		{
			sb.append(" and  TC.userAddress.state.stateId = :constituencyId ");
		}
		else if(reqLocationType != null && reqLocationType.trim().equalsIgnoreCase("District"))
		{
			sb.append(" and  TC.userAddress.district.districtId = :constituencyId ");
		}
		else if(reqLocationType != null && reqLocationType.trim().equalsIgnoreCase("Constituency"))
		{
			sb.append(" and  TC.userAddress.constituency.constituencyId = :constituencyId ");
		}
		
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0)
			sb.append(" and TC.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		if(startDate != null && endDate != null){
			//sb.append(" and ( date(TC.startedDate) between :startDate and :endDate ) ");
		}
		sb.append(" group by TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("constituencyId", constituencyId);
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0)
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(startDate != null && endDate != null){
			//query.setParameter("startDate", startDate);
			//query.setParameter("endDate", endDate);
		}
		if(levelIdsLsit != null && levelIdsLsit.size() > 0){
			query.setParameterList("levelIdsLsit", levelIdsLsit);
		}
		return query.list();
	}
	 //getting started 
	public List<Object[]> getLocationWiseVillageStartedDetails(Long constituencyId,String reqLocationType,Date startDate,Date endDate, 
			 List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsLsit){
		//0 basiccommId,3count
		/*Query query = getSession().createQuery("select TBC.tdpBasicCommitteeId,count(*) " +
				" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId in (6,8) " +
				" and  TC.constituency.constituencyId = :constituencyId and TC.startedDate is not null and TC.isCommitteeConfirmed ='N'  and " +
				" TC.completedDate is null group by TBC.tdpBasicCommitteeId");
		*/
		StringBuilder sb = new StringBuilder();
		sb.append(" select TBC.tdpBasicCommitteeId,count(*)  ");
		sb.append(" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  ");
		if(levelIdsLsit != null && levelIdsLsit.size() > 0){
			sb.append(" and TC.tdpCommitteeLevelId in (:levelIdsLsit) " );
		}
		if(reqLocationType != null && reqLocationType.trim().equalsIgnoreCase("State"))
		{
			sb.append(" and  TC.userAddress.state.stateId = :constituencyId ");
		}
		else if(reqLocationType != null && reqLocationType.trim().equalsIgnoreCase("District"))
		{
			sb.append(" and  TC.userAddress.district.districtId = :constituencyId ");
		}
		else if(reqLocationType != null && reqLocationType.trim().equalsIgnoreCase("Constituency"))
		{
			sb.append(" and  TC.userAddress.constituency.constituencyId = :constituencyId ");
		}
		//sb.append(" and  TC.userAddress.constituency.constituencyId = :constituencyId ");
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0)
			sb.append(" and TC.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) ");
		if(startDate != null && endDate != null){
			sb.append(" and ( date(TC.startedDate) between :startDate and :endDate ) and TC.startedDate is not null and TC.isCommitteeConfirmed ='N' and TC.completedDate is null ");
		}
		sb.append(" group by TBC.tdpBasicCommitteeId ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("constituencyId", constituencyId);
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0)
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(levelIdsLsit != null && levelIdsLsit.size() > 0){
			query.setParameterList("levelIdsLsit", levelIdsLsit);
		}
		return query.list();
	}
	//getting total and confirmed counts for mandal lvl
		public List<Object[]> getLocationWiseMandalDetails(List<Long> locationIds,Long levelId,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate){
			//0 basiccommId,1 name,2confirmd,3count
			StringBuilder str = new StringBuilder();
			str.append("select TBC.tdpBasicCommitteeId,TBC.name,TC.isCommitteeConfirmed,count(distinct TC.tdpCommitteeId),TC.completedDate " +
					"   from   TdpCommittee TC , TdpBasicCommittee TBC " +
					"   where  TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  and " +
					"          TC.tdpCommitteeLevel.tdpCommitteeLevelId = :levelId and TC.tdpCommitteeLevelValue in(:locationIds) and " +
					"          TC.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) "); 
			if(startDate != null && endDate != null){
				str.append( " and ( (date(TC.startedDate) between :startDate and :endDate )  OR  ( date(TC.completedDate) between :startDate and :endDate )  )" );
			}
			str.append(" group by TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
			Query query = getSession().createQuery(str.toString());
			query.setParameter("levelId", levelId);
			query.setParameterList("locationIds", locationIds);
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
			if(startDate != null && endDate != null){
				query.setParameter("startDate", startDate);
			    query.setParameter("endDate", endDate);
			}
				
			return query.list();
		}
		 //getting started  for mandal lvl
		public List<Object[]> getLocationWiseMandalStartedDetails(List<Long> locationIds,Long levelId,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate){
			//0 basiccommId,3count
			StringBuilder str = new StringBuilder();
			str.append("select TBC.tdpBasicCommitteeId,count(*) " +
					" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  " +
					" and  TC.tdpCommitteeLevel.tdpCommitteeLevelId = :levelId and TC.tdpCommitteeLevelValue in (:locationIds) " +
					" and TC.startedDate is not null and TC.isCommitteeConfirmed ='N'  and " +
					" TC.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) and " );
			if(startDate != null && endDate != null){
				str.append( "  ( (date(TC.startedDate) between :startDate and :endDate )  OR  ( date(TC.completedDate) between :startDate and :endDate )  )" );
			}
			str.append(" and TC.completedDate is null group by TBC.tdpBasicCommitteeId");
			Query query = getSession().createQuery(str.toString());
			query.setParameter("levelId", levelId);
			query.setParameterList("locationIds", locationIds);
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
			if(startDate != null && endDate != null){
				query.setParameter("startDate", startDate);
			    query.setParameter("endDate", endDate);
			}
			return query.list();
		}
		
		
		//getting total and confirmed counts for mandal lvl
		public List<Object[]> getLocationsWiseMandalDetails(List<Long> locationIds, List<Long> committeeLevelIdsList,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate, 
				String  accessType ,List<Long> mainOrAfflCommitteIds ){
			//0 basiccommId,1 name,2confirmd,3count
			/*String accessType ="district";
			List<Long> committeeLevelIdsList = new ArrayList<Long>(0);
			committeeLevelIdsList.add(6L);
			committeeLevelIdsList.add(8L);
			List<Long> mainOrAfflCommitteIds = new ArrayList<Long>(0);
			mainOrAfflCommitteIds.add(1L);
			mainOrAfflCommitteIds.add(2L);*/
			
			
			
			StringBuilder str = new StringBuilder();
			str.append("select TBC.tdpBasicCommitteeId,TBC.name,TC.isCommitteeConfirmed,count(distinct TC.tdpCommitteeId),TC.completedDate " +
					"   from   TdpCommittee TC , TdpBasicCommittee TBC " +
					"   where  TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  and  " +
					"          TC.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) "); 
			
			if(committeeLevelIdsList != null && committeeLevelIdsList.size()>0){
				str.append(" and TC.tdpCommitteeLevel.tdpCommitteeLevelId in (:committeeLevelIdsList)     ");
			}
			if(mainOrAfflCommitteIds != null && mainOrAfflCommitteIds.size()>0){
				str.append(" and TBC.tdpCommitteeTypeId in (:mainOrAfflCommitteIds)  ");
			}
			if(accessType != null && accessType.equalsIgnoreCase(IConstants.DISTRICT)){
				str.append(" and TC.userAddress.district.districtId in (:locationIds)  ");
			}
			else if(accessType != null && accessType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				str.append(" and TC.userAddress.constituency.constituencyId in (:locationIds)  ");
			}
			else if(accessType != null && accessType.equalsIgnoreCase(IConstants.STATE)){
				str.append(" and TC.userAddress.state.stateId in (:locationIds)  ");
			}
			if(startDate != null && endDate != null){
				//str.append( " and ( (date(TC.startedDate) between :startDate and :endDate )  OR  ( date(TC.completedDate) between :startDate and :endDate )  )" );
			}
			str.append(" group by TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
			Query query = getSession().createQuery(str.toString());
			//query.setParameter("levelId", levelId);
			query.setParameterList("locationIds", locationIds);
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
			if(startDate != null && endDate != null){
				//query.setParameter("startDate", startDate);
			   // query.setParameter("endDate", endDate);
			}
			if(committeeLevelIdsList != null && committeeLevelIdsList.size()>0){
				query.setParameterList("committeeLevelIdsList", committeeLevelIdsList);
			}
			if(mainOrAfflCommitteIds != null && mainOrAfflCommitteIds.size()>0){
				query.setParameterList("mainOrAfflCommitteIds", mainOrAfflCommitteIds);
			}
			return query.list();
		}
		 //getting started  for mandal lvl
	public List<Object[]> getLocationsWiseMandalStartedDetails(List<Long> locationIds, List<Long> committeeLevelIdsList,List<Long> committeeEnrollmentIdsLst,Date startDate,Date endDate,
			String  accessType , List<Long> mainOrAfflCommitteIds ){
		//0 basiccommId,3count 
		
		StringBuilder str = new StringBuilder();
		str.append("select TBC.tdpBasicCommitteeId,count(*) " +
				" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  " +
				" and  TC.startedDate is not null and TC.isCommitteeConfirmed ='N' and " +
				" TC.tdpCommitteeEnrollment.tdpCommitteeEnrollmentId in(:committeeEnrollmentIdsLst) " );
		
		if(committeeLevelIdsList != null && committeeLevelIdsList.size()>0){
			str.append(" and TC.tdpCommitteeLevel.tdpCommitteeLevelId in (:committeeLevelIdsList)     ");
		}
		if(mainOrAfflCommitteIds != null && mainOrAfflCommitteIds.size()>0){
			str.append(" and TBC.tdpCommitteeTypeId in (:mainOrAfflCommitteIds)  ");
		}
		if(accessType != null && accessType.equalsIgnoreCase(IConstants.DISTRICT)){
			str.append(" and TC.userAddress.district.districtId in (:locationIds)  ");
		}
		else if(accessType != null && accessType.equalsIgnoreCase(IConstants.CONSTITUENCY)){
			str.append(" and TC.userAddress.constituency.constituencyId in (:locationIds)  ");
		}
		else if(accessType != null && accessType.equalsIgnoreCase(IConstants.STATE)){
			str.append(" and TC.userAddress.state.stateId in (:locationIds)  ");
		}
		
		if(startDate != null && endDate != null){
			str.append( " and (date(TC.startedDate) between :startDate and :endDate ) " );
		}
		str.append(" and TC.completedDate is null group by TBC.tdpBasicCommitteeId");
		Query query = getSession().createQuery(str.toString());
		
		
		query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		if(committeeLevelIdsList != null && committeeLevelIdsList.size()>0){
			query.setParameterList("committeeLevelIdsList", committeeLevelIdsList);
		}
		if(mainOrAfflCommitteIds != null && mainOrAfflCommitteIds.size()>0){
			query.setParameterList("mainOrAfflCommitteIds", mainOrAfflCommitteIds);
		}
		query.setParameterList("locationIds", locationIds);
		if(startDate != null && endDate != null){
			query.setParameter("startDate", startDate);
		    query.setParameter("endDate", endDate);
		}
		
		return query.list();
	}
	
	/*public List<Object[]> muncipalList(Long constituencyId,List muncipalIds){
		Query query = getSession().createQuery("select TBC.tdpCommitteeType.tdpCommitteeTypeId ,TBC.name,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed,count(*) " +
				"from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId = 7 " +
				"and TC.tdpCommitteeLevelValue in (:ids) group by TBC.tdpCommitteeTypeId ,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
		//query.setParameter("constituencyId", constituencyId);
		query.setParameterList("ids", muncipalIds);
		return query.list();
	}
	
	public List<Object[]> divisionsList(Long constituencyId,List divisionIds){
		Query query = getSession().createQuery("select TBC.tdpCommitteeType.tdpCommitteeTypeId ,TBC.name,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed,count(*) " +
				"from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId = 9 " +
				"and TC.tdpCommitteeLevelValue in (:ids) group by TBC.tdpCommitteeTypeId ,TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
		//query.setParameter("constituencyId", constituencyId);
		query.setParameterList("ids", divisionIds);
		return query.list();
	}*/
	
	public List<Object[]> getTotalCompletedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList ,List<Long> enrollmentIdsList){
		//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId

		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.districtId,model.state " +
				" from TdpCommittee model where  " +
				"  model.isCommitteeConfirmed= 'Y' ");
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			str.append(" and model.tdpCommitteeLevelValue in (:locationlevelValueList) ");
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			str.append(" and model.constituency.constituencyId in (:assemblyIds) ");
		}		
		else if(districtIds != null && districtIds.size()>0)
		{
			str.append(" and model.districtId in (:districtIds) ");
		}
		else if(state != null)
		{
			str.append(" and model.state= :state ");
		}
		if(levelIds != null && levelIds.size() > 0)
		{
			str.append(" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) ");
		}
		/*if(startDate != null && endDate !=null){
			str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  ");
		}
		*/
		 
		if(startDate != null && endDate != null){
			str.append( " and ( (date(model.completedDate) between :startDate and :endDate )  )" );				
		}
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			str.append(" and model.tdpCommitteeEnrollmentId in (:enrollmentIdsList) ");
		}	
		 
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			query.setParameterList("locationlevelValueList", locationlevelValueList);
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			query.setParameterList("assemblyIds", assemblyIds);
		}
		else if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		else if(state != null)
		{
			query.setParameter("state", state);
		}
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
		{
			query.setParameterList("enrollmentIdsList", enrollmentIdsList);
		}
		if(startDate != null && endDate != null){
			query.setDate("startDate",startDate);
			query.setDate("endDate",endDate);
		}
		
		
		return query.list();
	}
	
	public List<Object[]> committeesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds,List<Long> committeeSpanTypeIdsList){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.userAddress.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId " +// BASIC_COMMITTEE_ID sri
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.userAddress.district.districtId in(:districtIds) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) and model.isCommitteeConfirmed = 'N' and model.startedDate is not null and model.completedDate is null ");
			}
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
		}
		
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeSpanTypeIdsList) ");
		}
		str.append(" group by model.userAddress.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		query.setParameterList("districtIds", districtIds);
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0)
			query.setParameterList("committeeSpanTypeIdsList", committeeSpanTypeIdsList);
		
		return query.list();
	}
	public String gettingConfirmedCommittee(Long tdpCommitteeId)
	{
		
		Query query = getSession().createQuery("select model.isCommitteeConfirmed " +
				" from TdpCommittee model " +
				" where model.tdpCommitteeId =:tdpCommitteeId and " +
				" model.isCommitteeConfirmed is not null ");
		query.setParameter("tdpCommitteeId", tdpCommitteeId);
		return (String)query.uniqueResult();
	}
	
	
	public List<Object[]> committeesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst){
		StringBuilder str = new StringBuilder();
		//constiIds.clear();
		//constiIds.add(307L);
		//constiIds.add(308L);
		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.userAddress.constituency.constituencyId" +// DISTRICT
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.constituency.constituencyId in(:constiIds)  ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate)  and model.completedDate is null and model.isCommitteeConfirmed = 'N' ");
			}
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
		}
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
		}
		str.append(" group by model.userAddress.constituency.constituencyId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		query.setParameterList("constiIds", constiIds);
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0)
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		
		return query.list();
	}
	
	public List<Object[]> getCommitteesCountByDistrictIdAndLevel(List<Long> districtIds,List<Long> levelIds,List<Long> committeeSpanTypeIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.tdpCommitteeId), district.districtId, model.tdpBasicCommitteeId " +
				" from TdpCommittee model " +
				" left join model.userAddress userAddress " +
				" left join userAddress.district  district " +
				" where district.districtId in (:districtIds)  and model.tdpCommitteeLevelId in (:levelIds)" +
				" and district.districtId is not null ");
		
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0){
			sb.append(" and model.tdpCommitteeEnrollmentId in (:committeeSpanTypeIdsList) ");
		}
		sb.append(" group by district.districtId, model.tdpBasicCommitteeId order by model.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("districtIds", districtIds);
		query.setParameterList("levelIds", levelIds);
		//query.setParameter("basicCommty", 1l);
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0)
			query.setParameterList("committeeSpanTypeIdsList", committeeSpanTypeIdsList);
		
		return query.list();
	}
	
	public List<Object[]> getCommitteesCountByConstituencyIdAndLevel(List<Long> constituencyIds,List<Long> levelIds, String reqAreaTypeStr, List<Long> committeeSpanTypeIdsList){
		
		//constituencyIds.clear();
		//constituencyIds.add(307L);
		//constituencyIds.add(308L);
		StringBuilder sb = new StringBuilder();
		sb.append(" select count(model.tdpCommitteeId), model.userAddress.constituency.constituencyId,model.tdpBasicCommitteeId " +
				" from TdpCommittee model" +
				" where model.userAddress.constituency.constituencyId in(:constituencyIds)" +
				" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.userAddress.constituency is not null  ");
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0){
			sb.append(" and model.tdpCommitteeEnrollmentId in (:committeeSpanTypeIdsList) ");
		}
		sb.append(" group by model.userAddress.constituency.constituencyId,model.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameterList("levelIds", levelIds);
		//query.setParameter("basicCommty", 1l);
		if(committeeSpanTypeIdsList != null && committeeSpanTypeIdsList.size()>0)
			query.setParameterList("committeeSpanTypeIdsList", committeeSpanTypeIdsList);
		
		return query.list();
		
		/*Query query = getSession().createQuery(" select count(model.tdpCommitteeId), model.constituency.constituencyId,model.tdpBasicCommitteeId " +
				" from TdpCommittee model" +
				" where model.constituency.constituencyId in(:constituencyIds)" +
				" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.constituency.constituencyId is not null " +
				//" and model.tdpBasicCommitteeId = :basicCommty  " +
				" group by model.constituency.constituencyId,model.tdpBasicCommitteeId ");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameterList("levelIds", levelIds);
		//query.setParameter("basicCommty", 1l);
		
		return query.list();*/
	}
	
	public List<Object[]> committeesCountByLocationIds1(Long levelId,List<Long> levelValues,Date startDate,Date endDate,String type,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList){
		StringBuilder str = new StringBuilder();

		//levelValues.clear();
		//levelValues.add(195L);
		str.append("select count(model.tdpCommitteeId),model.userAddress.constituency.constituencyId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId, model.tdpBasicCommittee.tdpBasicCommitteeId " + 
				" from TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.userAddress.constituency.constituencyId in(:levelValues) " +
				"  ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) ");
			}
			str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  ");
			}
			str.append("and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		}
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
		}
		str.append(" group by model.userAddress.constituency.constituencyId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

		Query query = getSession().createQuery(str.toString());
		
		
		query.setParameterList("levelValues", levelValues);
			query.setParameter("levelId", levelId);
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		}
		return query.list();
	}
	
	public List<Object[]> committeesCountByLocationIds(Long levelId,List<Long> levelValues,Date startDate,Date endDate,String type,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList){
		StringBuilder str = new StringBuilder();


		str.append("select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId, model.tdpBasicCommittee.tdpBasicCommitteeId " + 
				" from TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue in(:levelValues) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) ");
			}
			str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  ");
			}
			str.append("and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		}
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
		}
		str.append(" group by model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

		Query query = getSession().createQuery(str.toString());
		
		
		query.setParameterList("levelValues", levelValues);
			query.setParameter("levelId", levelId);
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		}
		return query.list();
	}
	
	public List<Object[]> totalCommitteesCountByLocationIds(Long levelId,List<Long> levelValues,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList){
		StringBuilder str = new StringBuilder();
        //0count,1locationID
		str.append("select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpBasicCommitteeId from TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId " + 
				"  and model.tdpCommitteeLevelValue in(:levelValues) ");	
		
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
		}
		
		str.append(" group by model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		}
		query.setParameterList("levelValues", levelValues);
			query.setParameter("levelId", levelId);
		
		return query.list();
	}
	
	public List<Object[]> totalCommitteesCountByLocationIds1(Long levelId,List<Long> levelValues,String reqLocationTypeStr, List<Long> committeeEnrollmentIdsLst,List<Long> levelIdsList){
		StringBuilder str = new StringBuilder();
        //0count,1locationID
		//levelValues.clear();
		//levelValues.add(195L);
		str.append("select count(model.tdpCommitteeId),model.userAddress.constituency.constituencyId,model.tdpBasicCommittee.tdpBasicCommitteeId from TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId " + 
				"  and model.userAddress.constituency.constituencyId in(:levelValues)  ");	
		
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentIdsLst) ");
		}
		
		str.append(" group by model.userAddress.constituency.constituencyId,model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(committeeEnrollmentIdsLst != null && committeeEnrollmentIdsLst.size()>0){
			query.setParameterList("committeeEnrollmentIdsLst", committeeEnrollmentIdsLst);
		}
		query.setParameterList("levelValues", levelValues);
			query.setParameter("levelId", levelId);
		
		return query.list();
	}
	
	public List<Object[]> getCompletedAffliCommitteesCountByLocation(String stateId,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit){

		StringBuilder str = new StringBuilder();
		if(stateId.trim().equalsIgnoreCase("AP")){
			stateId="1";
		}else if(stateId.trim().equalsIgnoreCase("TS")){
			stateId="36";
		}
		str.append("select count(distinct model.tdpCommitteeId), " +
		" model.tdpBasicCommittee.name,model.tdpBasicCommittee.tdpBasicCommitteeId " +
		" from TdpCommittee model where ");
		//str.append(" model.state= :state ");
		if(startDate !=null && endDate !=null){
			str.append("  ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate ) ");
		}
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			str.append(" and model.tdpCommitteeLevelValue in (:locationlevelValueList) ");
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			str.append(" and model.constituency.constituencyId in (:assemblyIds) ");
		}		
		else if(districtIds != null && districtIds.size()>0)
		{
			str.append(" and model.districtId in (:districtIds) ");
		}else if(stateId != null)
		{
			str.append(" and model.userAddress.state.stateId= :stateId ");
		}
		str.append("and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2 and" +
				"  model.isCommitteeConfirmed = 'Y' " );
		if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeSpanTypeIdsLsit) ");
		}
		str.append("group by model.tdpBasicCommittee.tdpBasicCommitteeId ");
		
		Query query = getSession().createQuery(str.toString());
		//query.setParameter("state", state);		
		query.setParameterList("levelIds", levelIds);
		if(startDate != null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			query.setParameterList("locationlevelValueList", locationlevelValueList);
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			query.setParameterList("assemblyIds", assemblyIds);
		}
		else if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}else if(stateId != null)
		{
			query.setParameter("stateId", Long.valueOf(stateId));
		}
		if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0){
			query.setParameterList("committeeSpanTypeIdsLsit", committeeSpanTypeIdsLsit);
		}
		return query.list();
	}
	
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String stateId,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList,List<Long> committeeSpanTypeIdsLsit){

		StringBuilder str = new StringBuilder();
		if(stateId.trim().equalsIgnoreCase("AP")){
			stateId="1";
		}else if(stateId.trim().equalsIgnoreCase("TS")){
			stateId="36";
		}
		str.append("select count(distinct model.tdpCommitteeId), " +
		" model.tdpBasicCommittee.name,model.tdpBasicCommittee.tdpBasicCommitteeId " +
		" from TdpCommittee model where ");
		//str.append(" model.state= :state ");
		if(startDate !=null && endDate !=null){
			
			str.append("  ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate ) ");
		}
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			str.append(" and model.tdpCommitteeLevelValue in (:locationlevelValueList) ");
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			str.append(" and model.constituency.constituencyId in (:assemblyIds) ");
		}		
		else if(districtIds != null && districtIds.size()>0)
		{
			str.append(" and model.districtId in (:districtIds) ");
		} else if(stateId != null)
		{
			str.append(" and model.userAddress.state.stateId= :stateId ");
		}
		str.append("and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2 and" +
				"  model.isCommitteeConfirmed = 'N' and model.completedDate is null "); 
		if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeSpanTypeIdsLsit) ");
		}
		str.append("group by model.tdpBasicCommittee.tdpBasicCommitteeId ");
		Query query = getSession().createQuery(str.toString());
		
		//query.setParameter("state", state);		
		query.setParameterList("levelIds", levelIds);
		if(startDate != null && endDate !=null){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);
		}
		if(locationlevelValueList != null && locationlevelValueList.size()>0)
		{
			query.setParameterList("locationlevelValueList", locationlevelValueList);
		}	
		else if(assemblyIds != null && assemblyIds.size()>0)
		{
			query.setParameterList("assemblyIds", assemblyIds);
		}
		else if(districtIds != null && districtIds.size()>0)
		{
			query.setParameterList("districtIds", districtIds);
		}
		else if(stateId != null)
		{
			query.setParameter("stateId", Long.valueOf(stateId));
		}
		if(committeeSpanTypeIdsLsit != null && committeeSpanTypeIdsLsit.size()>0){
			query.setParameterList("committeeSpanTypeIdsLsit", committeeSpanTypeIdsLsit);
		}
		return query.list();
	}
	
	public List<Object[]> getCommitteesCountForMandalByConstituencyIdAndLevel(List<Long> constituencyIds,List<Long> levelIds,String levelType){
		
		
		StringBuilder str = new StringBuilder();
		str.append("select count(model.tdpCommitteeId),");
		
		
		if(levelType.equalsIgnoreCase("mandal"))
		{
			str.append(" p.tehsil.tehsilId");	
			str.append(" from TdpCommittee model,Panchayat p" +
		" where p.panchayatId = model.tdpCommitteeLevelValue ");
		}
		else if(levelType.equalsIgnoreCase("muncipality"))
		{
			
			str.append(" c.localElectionBody.localElectionBodyId");	
			str.append(" from TdpCommittee model,Constituency c" +
		" where c.constituencyId = model.tdpCommitteeLevelValue ");
		}
		str.append(" and model.constituency.constituencyId in(:constituencyIds)" +
		" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
		" and model.constituency.constituencyId is not null " +
		" and model.tdpBasicCommitteeId = :basicCommty " );
		if(levelType.equalsIgnoreCase("mandal"))
		str.append("  group by p.tehsil.tehsilId");
		else if(levelType.equalsIgnoreCase("muncipality"))
		str.append(" group by c.localElectionBody.localElectionBodyId");
		Query query = getSession().createQuery(str.toString());
	/*	Query query = getSession().createQuery(" select count(model.tdpCommitteeId), model.tdpCommitteeLevelValue" +
				" from TdpCommittee model" +
				" where model.constituency.constituencyId in(:constituencyIds)" +
				" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.constituency.constituencyId is not null " +
				" and model.tdpBasicCommitteeId = :basicCommty  " +
				" group by model.tdpCommitteeLevelValue");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameterList("levelIds", levelIds);
		query.setParameter("basicCommty", 1l);
		
		return query.list();*/
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameterList("levelIds", levelIds);
		query.setParameter("basicCommty", 1l);
		return query.list();
	}
	
	public List<Object[]> committeesCountForMandalByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds,String levelType){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,"); 
		if(levelType.equalsIgnoreCase("mandal"))
		{
			str.append(" p.tehsil.tehsilId");	
			str.append(" from TdpCommittee model,Panchayat p" +
		" where p.panchayatId = model.tdpCommitteeLevelValue ");
		}
		else if(levelType.equalsIgnoreCase("muncipality"))
		{
			
			str.append(" c.localElectionBody.localElectionBodyId");	
			str.append(" from TdpCommittee model,Constituency c" +
		" where c.constituencyId = model.tdpCommitteeLevelValue ");
		}
		str.append(" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.constituency.constituencyId in(:constiIds)  ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate)  ");
			}
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
		}
		if(levelType.equalsIgnoreCase("mandal"))
			str.append("  group by p.tehsil.tehsilId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId");
			else if(levelType.equalsIgnoreCase("muncipality"))
				str.append(" group by c.localElectionBody.localElectionBodyId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId");
		Query query = getSession().createQuery(str.toString());
		
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		query.setParameterList("constiIds", constiIds);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getTotalCommittesCountByLevelIdAndLevelValue(List<Long> locationIdsList,List<Long> locationValuesList)
	{
		Query query = getSession().createQuery(" select count(model.tdpCommitteeId),model.tdpCommitteeLevelId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model where model.tdpCommitteeLevelId in " +
				" (:locationIdsList) and model.tdpCommitteeLevelValue in (:locationValuesList) group by model.tdpCommitteeLevelId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		query.setParameterList("locationIdsList",locationIdsList);
		query.setParameterList("locationValuesList",locationValuesList);
		return query.list();
	}
	
	/*public List<Object[]> getCommitteesCountByDistrict(List<Long> locationIdsList,List<Long> locationValuesList,String type){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId " +// BASIC_COMMITTEE_ID sri
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.district.districtId in(:districtIds) ");
		
		
		str.append(" group by model.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		
		
		return query.list();
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from " +
				" TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue ");
		
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		if(constituencyId != null && constituencyId > 0)
			str.append(" and model.constituency.constituencyId =:constituencyId ");
		
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		if(constituencyId != null && constituencyId > 0)
		  query.setParameter("constituencyId", constituencyId);
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		return query.list();
				
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from " +
				" TdpCommittee model,Panchayat p where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		
		if(tehsilId != null && tehsilId > 0)
			str.append(" and p.tehsil.tehsilId =:tehsilId and model.tdpCommitteeLevelValue = p.panchayatId");
		if(constituencyId != null && constituencyId > 0)
			str.append(" and model.constituency.constituencyId =:constituencyId ");
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		if(constituencyId != null && constituencyId > 0)
			  query.setParameter("constituencyId", constituencyId);
		if(tehsilId != null && tehsilId > 0)
		  query.setParameter("tehsilId", tehsilId);
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		} 
		return query.list();
				
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long localElectionBody,Long constituencyId,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from " +
				" TdpCommittee model,Constituency con where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		
		if(localElectionBody != null && localElectionBody > 0)
			str.append(" and con.localElectionBody.localElectionBodyId =:localElectionBody and model.tdpCommitteeLevelValue = con.constituencyId ");
		if(constituencyId != null && constituencyId > 0)
			str.append(" and model.constituency.constituencyId =:constituencyId ");
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		if(constituencyId != null && constituencyId > 0)
			  query.setParameter("constituencyId", constituencyId);
		if(localElectionBody != null && localElectionBody > 0)
		  query.setParameter("localElectionBody", localElectionBody);
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		} 
		return query.list();
				
	}
	
	/*public List<Object[]> committeesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId,String type){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId " +// BASIC_COMMITTEE_ID sri
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.district.districtId in(:districtIds) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) ");
			}
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
		}
		
		str.append(" group by model.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}*/
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId,String status,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and " +
				" model.tdpCommitteeLevelValue =:levelValue ");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		  if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			else if(status.equalsIgnoreCase("completed"))
			str.append(" and model.completedDate is not null and model.startedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		  if(constituencyId != null && constituencyId > 0)
			 str.append("and model.constituency.constituencyId =:constituencyId");
		  
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		if(constituencyId != null && constituencyId > 0)
		  query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	public List<Object[]> getcommitteesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds,List<Long> committeeEnrollmentYrIds){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId " +// BASIC_COMMITTEE_ID sri
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.district.districtId in(:districtIds) ");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		} 
		if(type.equalsIgnoreCase("started")){
			
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) ");
			}
			str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
			str.append("and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		}
		
		str.append(" group by model.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		} 
		query.setParameterList("districtIds", districtIds);
		
		return query.list();
	}
	public List<Object[]> getcommitteesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds
			,List<Long> committeeEnrollmentYrIds){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.userAddress.constituency.constituencyId" +// DISTRICT
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.userAddress.constituency.constituencyId in(:constiIds) ");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate)");
			}
			str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
			str.append("and model.startedDate is not null and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		}
		
		str.append(" group by model.userAddress.constituency.constituencyId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		query.setParameterList("constiIds", constiIds);
		
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,String status,List<Long> committeeEnrollmentYrIds)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model,Panchayat p  where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		  if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			else if(status.equalsIgnoreCase("completed"))
			str.append(" and model.completedDate is not null and model.startedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		  if(constituencyId != null && constituencyId > 0)
			 str.append("and model.constituency.constituencyId =:constituencyId");
		  if(tehsilId != null && tehsilId > 0)
				str.append(" and p.tehsil.tehsilId =:tehsilId and model.tdpCommitteeLevelValue = p.panchayatId");
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("tehsilId", tehsilId);
		
		if(constituencyId != null && constituencyId > 0)
		  query.setParameter("constituencyId", constituencyId);
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long localElectionBody,Long constituencyId,String status,List<Long> committeeEnrollmentYrIds){

		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model,Constituency con  where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:committeeEnrollmentYrIds) ");
		}
		  if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			else if(status.equalsIgnoreCase("completed"))
			str.append(" and model.completedDate is not null and model.startedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		  if(constituencyId != null && constituencyId > 0)
			 str.append("and model.constituency.constituencyId =:constituencyId");
		  if(localElectionBody != null && localElectionBody > 0)
				str.append(" and con.localElectionBody.localElectionBodyId =:localElectionBody and model.tdpCommitteeLevelValue = con.constituencyId ");
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("localElectionBody", localElectionBody);
		if(committeeEnrollmentYrIds != null && committeeEnrollmentYrIds.size()>0){
			query.setParameterList("committeeEnrollmentYrIds", committeeEnrollmentYrIds);
		}
		if(constituencyId != null && constituencyId > 0)
		  query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	
	}
	public List<Object[]> getCommitteesForLevelId(Long levelId)
	{
		
      Query query = getSession().createQuery("select distinct model.tdpBasicCommitteeId,model.tdpBasicCommittee.name from TdpCommittee model where " +
      " model.tdpCommitteeLevel.tdpCommitteeLevelId=:levelId" +
    		 " and model.startedDate is not null and model.isCommitteeConfirmed = 'N' ");
		query.setParameter("levelId", levelId);
		
		return query.list();
	}
	 //CORE DASHBOARD RELATED
	public Long getCommitteesCumulativeBasicReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,String state,Long basicCommitteeId,Date startDate,Date endDate,String status){
		StringBuilder str = new StringBuilder();
        
		str.append(" select count(distinct model.tdpCommitteeId) "+
			       " from   TdpCommittee model " +
				   " where  model.tdpBasicCommittee.tdpBasicCommitteeId = :basicCommitteeId ");
		
		if(userAccessLevelId == IConstants.STATE_LEVEl_ACCESS_ID){
			if(committeeLevelValueIds.contains(1l) && committeeLevelValueIds.contains(36l)){
				str.append(" and model.state in ('AP','TS') ");
			}else if(committeeLevelValueIds.contains(1l)){
				str.append(" and model.state = 'AP' ");
			}else if(committeeLevelValueIds.contains(36l)){
				str.append(" and model.state = 'TS' ");
			}
		}
		if(userAccessLevelId == IConstants.DISTRICT_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.district.districtId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.PARLIAMENT_LEVEl_ACCESS_ID || userAccessLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.constituency.constituencyId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.MANDAL_LEVEl_ID){
			str.append(" and model.userAddress.tehsil.tehsilId in (:committeeLevelValueIds) ");
		}
		
		if(status.equalsIgnoreCase("started")){
			str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' ");
			if( startDate!=null){
				str.append( " and date(model.startedDate)>=:startDate " );
			}
			if(endDate!=null){
				str.append( " and date(model.startedDate)<=:endDate " );
			}
		}
		else if(status.equalsIgnoreCase("completed")){
			str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
			if( startDate!=null){
				str.append( " and date(model.completedDate)>=:startDate " );
			}
			if(endDate!=null){
				str.append( " and date(model.completedDate)<=:endDate " );
			}
		}
		else if(status.equalsIgnoreCase("notStarted")){
			str.append(" and model.startedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}
			
		if(state!= null && !state.isEmpty() ){
			str.append(" and model.state =:state ");
		}

		Query query = getSession().createQuery(str.toString());
		
		if(userAccessLevelId != IConstants.STATE_LEVEl_ACCESS_ID){
			query.setParameterList("committeeLevelValueIds", committeeLevelValueIds);
		}
		query.setParameter("basicCommitteeId", basicCommitteeId);
		if(status.equalsIgnoreCase("started") || status.equalsIgnoreCase("completed")){
			if( startDate!=null){
				query.setDate("startDate",startDate);
			}
			if(endDate!=null){
				query.setDate("endDate",endDate);
			}
		}
		if(state!= null && !state.isEmpty() ){
			query.setParameter("state",state);
		}
		return (Long)query.uniqueResult();
	}
	
    public List<Object[]> getCommitteesCumulativeOverallReportChartsQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,List<Long> userAccessRequiredCommitteeLevelIds,String state,List<Long> basicCommitteeIds,Date startDate,Date endDate,String status){
		StringBuilder str = new StringBuilder();
        
		str.append(" select model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.tdpBasicCommittee.tdpCommitteeType.committeeType," +//1
				"           model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name," +//3
				   "        model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevel.tdpCommitteeLevel," +//5
				   "        count(distinct model.tdpCommitteeId)" +//6
				  "  from   TdpCommittee model " +
				  "  where  model.tdpCommitteeLevel.tdpCommitteeLevelId in (:userAccessRequiredCommitteeLevelIds) and " +
				  "         model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		if(userAccessLevelId == IConstants.STATE_LEVEl_ACCESS_ID){
			if(committeeLevelValueIds.contains(1l) && committeeLevelValueIds.contains(36l)){
				str.append(" and model.state in ('AP','TS') ");
			}else if(committeeLevelValueIds.contains(1l)){
				str.append(" and model.state = 'AP' ");
			}else if(committeeLevelValueIds.contains(36l)){
				str.append(" and model.state = 'TS' ");
			}
		}
		if(userAccessLevelId == IConstants.DISTRICT_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.district.districtId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.PARLIAMENT_LEVEl_ACCESS_ID || userAccessLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.constituency.constituencyId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.MANDAL_LEVEl_ID){
			str.append(" and model.userAddress.tehsil.tehsilId in (:committeeLevelValueIds) ");
		}
		
		if(status.equalsIgnoreCase("started")){
			str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' ");
			if( startDate!=null){
				str.append( " and date(model.startedDate)>=:startDate " );
			}
			if(endDate!=null){
				str.append( " and date(model.startedDate)<=:endDate " );
			}
		}
		else if(status.equalsIgnoreCase("completed")){
			str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
			if( startDate!=null){
				str.append( " and date(model.completedDate)>=:startDate " );
			}
			if(endDate!=null){
				str.append( " and date(model.completedDate)<=:endDate " );
			}
		}
		else if(status.equalsIgnoreCase("notStarted")){
			str.append(" and model.startedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}
			
		if(state!= null && !state.isEmpty() ){
			str.append(" and model.state =:state ");
		}
		str.append(" group by model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpCommitteeLevel.tdpCommitteeLevelId " +
				   " order by model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpCommitteeLevel.tdpCommitteeLevelId ");

		Query query = getSession().createQuery(str.toString());
		
		query.setParameterList("userAccessRequiredCommitteeLevelIds", userAccessRequiredCommitteeLevelIds);
		if(userAccessLevelId != IConstants.STATE_LEVEl_ACCESS_ID){
			query.setParameterList("committeeLevelValueIds", committeeLevelValueIds);
		}
		query.setParameterList("basicCommitteeIds", basicCommitteeIds);
		if(status.equalsIgnoreCase("started") || status.equalsIgnoreCase("completed")){
			if( startDate!=null){
				query.setDate("startDate",startDate);
			}
			if(endDate!=null){
				query.setDate("endDate",endDate);
			}
		}
		if(state!= null && !state.isEmpty() ){
			query.setParameter("state",state);
		}
		return query.list();
	}
    
    public List<Object[]> getCommitteesComparativeBascicReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,String state,List<Long> basicCommitteeIds,Date date){
    //public List<Object[]> getCommitteesComparativeBascicReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,String state,List<Long> basicCommitteeIds,Date date){
		StringBuilder str = new StringBuilder();
        
		str.append(" select model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.tdpBasicCommittee.tdpCommitteeType.committeeType," +//1
				   "        model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name," +//3
				   "        count(distinct model.tdpCommitteeId)" +//6
				  "  from   TdpCommittee model " +
				  "  where  model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		
		if(userAccessLevelId == IConstants.STATE_LEVEl_ACCESS_ID){
			if(committeeLevelValueIds.contains(1l) && committeeLevelValueIds.contains(36l)){
				str.append(" and model.state in ('AP','TS') ");
			}else if(committeeLevelValueIds.contains(1l)){
				str.append(" and model.state = 'AP' ");
			}else if(committeeLevelValueIds.contains(36l)){
				str.append(" and model.state = 'TS' ");
			}
		}
		if(userAccessLevelId == IConstants.DISTRICT_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.district.districtId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.PARLIAMENT_LEVEl_ACCESS_ID || userAccessLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.constituency.constituencyId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.MANDAL_LEVEl_ID){
			str.append(" and model.userAddress.tehsil.tehsilId in (:committeeLevelValueIds) ");
		}
		
		str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		/*if( month > 0 && year > 0){
			str.append( " and month(model.completedDate)= :month and year(model.completedDate) = :year" );
		}*/
		if(date != null){
			str.append( " and date(model.completedDate) <= :date" );
		}
		if(state!= null && !state.isEmpty() ){
			str.append(" and model.state =:state ");
		}
		str.append(" group by model.tdpBasicCommittee.tdpBasicCommitteeId " +
				   " order by model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		if(userAccessLevelId != IConstants.STATE_LEVEl_ACCESS_ID){
			query.setParameterList("committeeLevelValueIds", committeeLevelValueIds);
		}
		query.setParameterList("basicCommitteeIds", basicCommitteeIds);
		/*if( month > 0 && year > 0){
			query.setParameter("month",month);
			query.setParameter("year",year);
		}*/
		if(date != null){
			query.setDate("date",date);
		}
		if(state!= null && !state.isEmpty() ){
			query.setParameter("state",state);
		}
		return query.list();
	}
    public List<Object[]> getCommitteesComparativeOverallReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,List<Long> userAccessRequiredCommitteeLevelIds,String state,List<Long> basicCommitteeIds,Date date){
    //public List<Object[]> getCommitteesComparativeOverallReportChartQuery(Long userAccessLevelId ,List<Long> committeeLevelValueIds,List<Long> userAccessRequiredCommitteeLevelIds,String state,List<Long> basicCommitteeIds,int month,int year)
    	StringBuilder str = new StringBuilder();
        
		str.append(" select model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.tdpBasicCommittee.tdpCommitteeType.committeeType," +//1
				"           model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name," +//3
				   "        model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevel.tdpCommitteeLevel," +//5
				   "        count(distinct model.tdpCommitteeId)" +//6
				  "  from   TdpCommittee model " +
				  "  where  model.tdpCommitteeLevel.tdpCommitteeLevelId in (:userAccessRequiredCommitteeLevelIds) and " +
				  "         model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		if(userAccessLevelId == IConstants.STATE_LEVEl_ACCESS_ID){
			if(committeeLevelValueIds.contains(1l) && committeeLevelValueIds.contains(36l)){
				str.append(" and model.state in ('AP','TS') ");
			}else if(committeeLevelValueIds.contains(1l)){
				str.append(" and model.state = 'AP' ");
			}else if(committeeLevelValueIds.contains(36l)){
				str.append(" and model.state = 'TS' ");
			}
		}
		if(userAccessLevelId == IConstants.DISTRICT_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.district.districtId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.PARLIAMENT_LEVEl_ACCESS_ID || userAccessLevelId == IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			str.append(" and model.userAddress.constituency.constituencyId in (:committeeLevelValueIds) ");
		}else if(userAccessLevelId == IConstants.MANDAL_LEVEl_ID){
			str.append(" and model.userAddress.tehsil.tehsilId in (:committeeLevelValueIds) ");
		}
		
		str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		/*if( month > 0 && year > 0){
			str.append( " and month(model.completedDate)= :month and year(model.completedDate) = :year" );
		}*/
		if(date != null){
			str.append( " and date(model.completedDate) <= :date" );
		}
		if(state!= null && !state.isEmpty() ){
			str.append(" and model.state =:state ");
		}
		str.append(" group by model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpCommitteeLevel.tdpCommitteeLevelId" +
				   " order by model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpCommitteeLevel.tdpCommitteeLevelId");

		Query query = getSession().createQuery(str.toString());
		
		query.setParameterList("userAccessRequiredCommitteeLevelIds", userAccessRequiredCommitteeLevelIds);
		if(userAccessLevelId != IConstants.STATE_LEVEl_ACCESS_ID){
			query.setParameterList("committeeLevelValueIds", committeeLevelValueIds);
		}
		query.setParameterList("basicCommitteeIds", basicCommitteeIds);
		/*if( month > 0 && year > 0){
			query.setParameter("month",month);
			query.setParameter("year",year);
		}*/
		if(date != null){
			query.setParameter("date",date);
		}
		if(state!= null && !state.isEmpty() ){
			query.setParameter("state",state);
		}
		return query.list();
	}
	
    
    //////////////////////////////////////////////////// CORE DASHBOARD NEW ////////////////////////////////////////////////////////////////////
    public List<Object[]> getCommitteesTotalOrStartedOrCompletedCount(CommitteeInputVO committeeBO){
		StringBuilder str = new StringBuilder();
        
		str.append(" select model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevel.tdpCommitteeLevel," +//1
				   "        model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.tdpBasicCommittee.tdpCommitteeType.committeeType," +//3
				   "        count(distinct model.tdpCommitteeId)" +//4
				   " from   TdpCommittee model " +
				  "  where  model.tdpCommitteeLevel.tdpCommitteeLevelId in (:tdpCommitteeLevelIds)  " );
				
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			str.append(" and model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}*/
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			str.append(" and model.userAddress.state.stateId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			str.append(" and model.userAddress.district.districtId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			str.append(" and model.userAddress.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			str.append(" and model.userAddress.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			str.append(" and model.userAddress.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
		}
		
		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			
			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
				str.append("   and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
				if( committeeBO.getDate()!=null){
					str.append( " and ( date(model.startedDate) > :givendate or model.startedDate is null)" );
				}
				else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					str.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.startedDate is null)" );
				}else{
					str.append(" and model.startedDate is null ");
				}
			}
		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
			   str.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N' ");
				if(committeeBO.getDate()!=null){
					str.append( " and date(model.startedDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					str.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.completedDate is null )" );
				}else{
					str.append(" and model.completedDate is null ");
				}
			}
			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
				str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				
				if( committeeBO.getDate()!=null){
					str.append( " and date(model.completedDate) < :givendate " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					str.append( " and ( (date(model.startedDate) between :startDate and :endDate ))" );
				}
			} 
		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			str.append(" and model.userAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
			str.append(committeeBO.getCommitteesQueryString());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:enrollmentYearList) ");
		}
		
		str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId " +
				   " order by model.tdpCommitteeLevel.tdpCommitteeLevelId ");

		Query query = getSession().createQuery(str.toString());
		
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}*/
		query.setParameterList("tdpCommitteeLevelIds",committeeBO.getTdpCommitteeLevelIds()); 
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
			
		}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}
		
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			if(committeeBO.getDate()!=null){
				query.setDate("givendate",committeeBO.getDate());
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("startDate",committeeBO.getStartDate());
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size()>0){
			query.setParameterList("enrollmentYearList",committeeBO.getEnrollmentYearList());
		}
		
		return query.list();
	}
    
    public List<Object[]> levelWiseBasicCommitteesCount(CommitteeInputVO committeeBO){
		StringBuilder str = new StringBuilder();
        
		str.append(" select model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevel.tdpCommitteeLevel," +//1
				   "        model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name," +//3
				   "        model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.tdpBasicCommittee.tdpCommitteeType.committeeType," +//5
				   "        count(distinct model.tdpCommitteeId)" +//6
				   " from   TdpCommittee model " +
				  "  where  model.tdpCommitteeLevel.tdpCommitteeLevelId in (:tdpCommitteeLevelIds)  " );
				
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			str.append(" and model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}*/
		
		//locations
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			str.append(" and model.userAddress.state.stateId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			str.append(" and model.userAddress.district.districtId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			str.append(" and model.userAddress.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			str.append(" and model.userAddress.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			str.append(" and model.userAddress.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
		}
		
		//committee status
		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			
			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
				str.append("   and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
				if(committeeBO.getDate()!=null){
					str.append( " and ( date(model.startedDate) > :givendate or model.startedDate is null)" );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					str.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.startedDate is null)" );
				}else{
					str.append(" and model.startedDate is null ");
				}
			}
		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
			   str.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N' ");
				if(committeeBO.getDate()!=null){
					str.append( " and date(model.startedDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					str.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.completedDate is null)" );
				}else{
					str.append(" and model.completedDate is null ");
				}
			}
			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
				str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				if( committeeBO.getDate()!=null){
					str.append( " and date(model.completedDate) < :givendate " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					str.append( " and ( (date(model.completedDate) between :startDate and :endDate ) )" );
				}
			} 
		}
			
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			str.append(" and model.userAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size()>0){
			str.append(" and model.tdpCommitteeEnrollmentId in (:tdpCommitteeEnrollmentId) ");
		}
		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
			str.append(committeeBO.getCommitteesQueryString());
		}
		str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpBasicCommittee.tdpBasicCommitteeId " +
				   " order by model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}*/
		query.setParameterList("tdpCommitteeLevelIds",committeeBO.getTdpCommitteeLevelIds()); 
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
			
		}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}
		
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			if(committeeBO.getDate()!=null){
				query.setDate("givendate",committeeBO.getDate());
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("startDate",committeeBO.getStartDate());
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size()>0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		return query.list();
	}
    
    public List<Object[]> committeesPerformanceCohort(CommitteeInputVO committeeBO){
    	
    	StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		StringBuilder sbE = new StringBuilder();
		
		sbS.append("select count(distinct model.tdpCommitteeId),model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name ");//2
		
		sbE.append(" group by model.tdpBasicCommittee.tdpBasicCommitteeId ");
		
		if(committeeBO.getGroupingLocation().equalsIgnoreCase("State")){
			sbS.append(" ,model.userAddress.state.stateId,model.userAddress.state.stateName ");//4
			sbE.append(" ,model.userAddress.state.stateId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("District")){
			sbS.append(" ,model.userAddress.district.districtId,model.userAddress.district.districtName ");//4
			sbE.append(" ,model.userAddress.district.districtId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Parliament")){
			sbS.append(" ,model.userAddress.parliamentConstituency.constituencyId,model.userAddress.parliamentConstituency.name ");//4
			sbE.append(" ,model.userAddress.parliamentConstituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Constituency")){
			sbS.append(" ,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");//4
			sbE.append(" ,model.userAddress.constituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Mandal")){
			sbS.append(" ,model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");//4
			sbE.append(" ,model.userAddress.tehsil.tehsilId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("localElectionBody")){
			sbS.append(" ,model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name " +//4
					   " ,model.userAddress.localElectionBody.electionType.electionTypeId,model.userAddress.localElectionBody.electionType.electionType ");//6
			sbE.append(" ,model.userAddress.localElectionBody.localElectionBodyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Village")){
			sbS.append(" ,model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName ");//10
			sbE.append(" ,model.userAddress.panchayat.panchayatId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Ward")){
			sbS.append(" ,model.userAddress.ward.constituencyId,model.userAddress.ward.name ");//12
			sbE.append(" ,model.userAddress.ward.const" +
					"ituencyId ");
		}
		
		sbM.append(" from  TdpCommittee model " +
				  "  where model.tdpCommitteeLevel.tdpCommitteeLevelId in (:tdpCommitteeLevelIds) ");
			
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sbM.append(" and model.tdpCommitteeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size() >0l){
			sbM.append(" and model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}*/
		
		//locations
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			sbS.append(",model.userAddress.state.stateId,model.userAddress.state.stateName ");
			sbM.append(" and model.userAddress.state.stateId in (:userAccessLocationValues) ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			sbS.append(",model.userAddress.district.districtId,model.userAddress.district.districtName ");
			sbM.append(" and model.userAddress.district.districtId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			sbS.append(",model.userAddress.parliamentConstituency.constituencyId,model.userAddress.parliamentConstituency.name ");
			sbM.append(" and model.userAddress.parliamentConstituency.constituencyId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			sbS.append(" ,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
			sbM.append(" and model.userAddress.constituency.constituencyId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			sbS.append(",model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
			sbM.append(" and model.userAddress.tehsil.tehsilId in (:userAccessLocationValues) ");
		}
		
		//committee status
		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			
			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
				sbM.append("   and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
				if( committeeBO.getDate()!=null){
					sbM.append( " and ( date(model.startedDate) > :givendate or model.startedDate is null)" );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sbM.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.startedDate is null)" );
				}else{
					sbM.append(" and model.startedDate is null ");
				}
			}
		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
			   sbM.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N' ");
				if(committeeBO.getDate()!=null){
					sbM.append( " and date(model.startedDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sbM.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.completedDate is null)" );
				}else{
					sbM.append(" and model.completedDate is null ");
				}
			}
			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
				sbM.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				if( committeeBO.getDate()!=null){
					sbM.append( " and date(model.completedDate) < :givendate " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sbM.append( " and ( (date(model.completedDate) between :startDate and :endDate ))" );
				}
			} 
		}
			
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.userAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
			sbM.append(committeeBO.getCommitteesQueryString());
		}
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		Query query = getSession().createQuery(sbf.toString());
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size() >0l){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}*/
		query.setParameterList("tdpCommitteeLevelIds",committeeBO.getTdpCommitteeLevelIds()); 
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getTehsilIds());
		}
		
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			if(committeeBO.getDate()!=null){
				query.setDate("givendate",committeeBO.getDate());
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("startDate",committeeBO.getStartDate());
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		return query.list();
    }
    public Long getCountsForCommittees(CommitteeInputVO committeeBO,String status){
		StringBuilder str = new StringBuilder();
        
		str.append(" select count(distinct model.tdpCommitteeId) "+
				   " from   TdpCommittee model " +
				   " where  model.tdpCommitteeLevel.tdpCommitteeLevelId in (:tdpCommitteeLevelIds)  " );
				
		if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			str.append(" and model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			if(committeeBO.getStateIds().contains(1l) && committeeBO.getStateIds().contains(36l)){
				str.append(" and model.state in ('AP','TS') ");
			}else if(committeeBO.getStateIds().contains(1l)){
				str.append(" and model.state = 'AP' ");
			}else if(committeeBO.getStateIds().contains(36l)){
				str.append(" and model.state = 'TS' ");
			}
		}
		
		if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			str.append(" and model.userAddress.district.districtId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			str.append(" and model.userAddress.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			str.append(" and model.userAddress.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
		}
		
		if( status != null && !status.isEmpty()){
			
			if(status.equalsIgnoreCase("started")){
				str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' ");
				if( committeeBO.getStartDate()!=null){
					str.append( " and date(model.startedDate)>=:startDate " );
				}
				if(committeeBO.getEndDate()!=null){
					str.append( " and date(model.startedDate)<=:endDate " );
				}
			}
			else if(status.equalsIgnoreCase("completed")){
				str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				if( committeeBO.getStartDate()!=null){
					str.append( " and date(model.completedDate)>=:startDate " );
				}
				if(committeeBO.getEndDate()!=null){
					str.append( " and date(model.completedDate)<=:endDate " );
				}
			}
		}
		
			
		if(committeeBO.getState()!= null && !committeeBO.getState().isEmpty() ){
			str.append(" and model.state =:state ");
		}
		

		Query query = getSession().createQuery(str.toString());
		
		if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}
		query.setParameterList("tdpCommitteeLevelIds",committeeBO.getTdpCommitteeLevelIds()); 
		
		if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}
		
		if(status != null && !status.isEmpty()){
			if(committeeBO.getStartDate()!=null){
				query.setDate("startDate",committeeBO.getStartDate());
			}
			if(committeeBO.getEndDate()!=null){
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		if(committeeBO.getState()!= null && !committeeBO.getState().isEmpty() ){
			query.setParameter("state",committeeBO.getState());
		}
		return (Long)query.uniqueResult();
	}
    public List<Object[]> districtWiseCommitteesCount(CommitteeInputVO committeeBO,String status){
		StringBuilder str = new StringBuilder();
        
		str.append(" select model.tdpBasicCommittee.tdpBasicCommitteeId," +//0.commiteeId
					" model.tdpBasicCommittee.name," +					   //1.commiteeName
					" model.userAddress.district.districtId," +		       //2.districtId
					" model.userAddress.district.districtName," +		   //3.districtName
					" count(distinct model.tdpCommitteeId)"+			   //4.count	
				    " from TdpCommittee model " +
				    "  where  " );
				
		if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			str.append("  model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			if(committeeBO.getStateIds().contains(1l) && committeeBO.getStateIds().contains(36l)){
				str.append(" and model.state in ('AP','TS') ");
			}else if(committeeBO.getStateIds().contains(1l)){
				str.append(" and model.state = 'AP' ");
			}else if(committeeBO.getStateIds().contains(36l)){
				str.append(" and model.state = 'TS' ");
			}
		}
		
		if( status != null && !status.isEmpty()){
			
			if(status.equalsIgnoreCase("started")){
				str.append(" and model.startedDate is not null and model.completedDate is null and model.isCommitteeConfirmed = 'N' ");
				if( committeeBO.getStartDate()!=null){
					str.append( " and date(model.startedDate)>=:startDate " );
				}
				if(committeeBO.getEndDate()!=null){
					str.append( " and date(model.startedDate)<=:endDate " );
				}
			}
			else if(status.equalsIgnoreCase("completed")){
				str.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				if( committeeBO.getStartDate()!=null){
					str.append( " and date(model.completedDate)>=:startDate " );
				}
				if(committeeBO.getEndDate()!=null){
					str.append( " and date(model.completedDate)<=:endDate " );
				}
			}else if(status.equalsIgnoreCase("notStarted")){
				str.append(" and model.startedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			}
		}
		
		if(committeeBO.getState()!= null && !committeeBO.getState().isEmpty() ){
			str.append(" and model.state =:state ");
		}
		str.append(" group by tdpBasicCommittee.tdpBasicCommitteeId,model.userAddress.district.districtId ");
				  

		Query query = getSession().createQuery(str.toString());
		
		if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}
		
		if(status != null && !status.isEmpty() && (status.equalsIgnoreCase("started") || status.equalsIgnoreCase("completed")) ){
			if(committeeBO.getStartDate()!=null){
				query.setDate("startDate",committeeBO.getStartDate());
			}
			if(committeeBO.getEndDate()!=null){
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		if(committeeBO.getState()!= null && !committeeBO.getState().isEmpty() ){
			query.setParameter("state",committeeBO.getState());
		}
		
		return query.list();
	}
    
    public List<Object[]> getLocationWiseCommitteesCountByLocIds(CommitteeInputVO committeeBO){
		
		StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		StringBuilder sbE = new StringBuilder();
		
		sbS.append(" select count(distinct model.tdpCommitteeId) ");
		sbM.append(" from  TdpCommittee model ");
				
		//sbM.append(" where model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		sbM.append(" where model.tdpCommitteeId is not null ");
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			sbS.append(",model.userAddress.state.stateId ");
			//sbM.append(" and model.userAddress.state.stateId in (:tdpCommitteeLevelValues) ");
			sbE.append(" group by model.userAddress.state.stateId ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			sbS.append(",model.userAddress.district.districtId ");
			//sbM.append(" and model.userAddress.district.districtId in (:tdpCommitteeLevelValues) ");
			sbE.append(" group by model.userAddress.district.districtId ");
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			sbS.append(",model.userAddress.parliamentConstituency.constituencyId ");
			//sbM.append(" and model.userAddress.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
			sbE.append(" group by model.userAddress.parliamentConstituency.constituencyId ");
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			sbS.append(",model.userAddress.constituency.constituencyId");
			//sbM.append(" and model.userAddress.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
			sbE.append(" group by model.userAddress.constituency.constituencyId ");
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			sbS.append(",model.userAddress.tehsil.tehsilId ");
			//sbM.append(" and model.userAddress.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
			sbE.append(" group by model.userAddress.tehsil.tehsilId ");
		}
		
		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
		
			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
				sbM.append("   and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
				sbM.append(" and model.startedDate is null ");
			}
		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
				sbM.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N'  and model.completedDate is null ");
				if(committeeBO.getDate()!=null){
					sbM.append( " and date(model.startedDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sbM.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.completedDate is null)" );
				}else{
					sbM.append(" and model.completedDate is null ");
				}
			}
			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
				sbM.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				if( committeeBO.getDate()!=null){
					sbM.append( " and date(model.completedDate) < :givendate " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sbM.append( " and ( (date(model.completedDate) between :startDate and :endDate ))" );
				}
			} 
		}
			
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.userAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sbM.append(" and model.tdpCommitteeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
			sbM.append(committeeBO.getCommitteesQueryString());
		}
		
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		Query query = getSession().createQuery(sbf.toString());
		
		//query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		/*if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}*/
		
		
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			if(!committeeBO.getStatus().equalsIgnoreCase("notStarted")){
				if(committeeBO.getDate()!=null){
					query.setDate("givendate",committeeBO.getDate());
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					query.setDate("startDate",committeeBO.getStartDate());
					query.setDate("endDate",committeeBO.getEndDate());
				}
			}
		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		return query.list();
	}
    
    
    
    public List<Object[]> getCommitteeLevelWiseCountsByLocIds(CommitteeInputVO committeeBO){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" " +
	    "select  model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevel.tdpCommitteeLevel," +//1
	    "        model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name," +//3
		"        count(distinct model.tdpCommitteeId)" +//4
		" from   TdpCommittee model " +
	    " where  model.tdpCommitteeLevel.tdpCommitteeLevelId in (:tdpCommitteeLevelIds)  " );
		
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sb.append(" and model.tdpCommitteeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			sb.append(" and model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}*/
		
		//locations related.
		if(committeeBO.getQueryString() != null && committeeBO.getQueryString().length()>0){
			sb.append( committeeBO.getQueryString() );
		}
		
		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
		
			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
				sb.append("   and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
				if( committeeBO.getDate()!=null){
					sb.append( " and ( date(model.startedDate) > :givendate or model.startedDate is null)" );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sb.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.startedDate is null)" );
				}else{
					sb.append(" and model.startedDate is null ");
				}
			}
		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
				sb.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N' ");
				if(committeeBO.getDate()!=null){
					sb.append( " and date(model.startedDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sb.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.completedDate is null)" );
				}else{
					sb.append(" and model.completedDate is null ");
				}
			}
			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
				sb.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
				if( committeeBO.getDate()!=null){
					sb.append( " and date(model.completedDate) < :givendate " );
				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
					sb.append( " and ( (date(model.completedDate) between :startDate and :endDate ) )" );
				}
			} 
		}
			
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sb.append(" and model.userAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
			sb.append(committeeBO.getCommitteesQueryString());
		}
		sb.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpBasicCommittee.tdpBasicCommitteeId ");
		
		Query query = getSession().createQuery(sb.toString());
		
		query.setParameterList("tdpCommitteeLevelIds", committeeBO.getTdpCommitteeLevelIds());
		
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}*/
		
		//locations.
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}
		
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			if(committeeBO.getDate()!=null){
				query.setDate("givendate",committeeBO.getDate());
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("startDate",committeeBO.getStartDate());
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		return query.list();
	}
    
    public List<Object[]> getCumulativeCommitteesCountsByLocIds(CommitteeInputVO committeeBO){
		
 		StringBuilder sb = new StringBuilder();
 		
 		sb.append(" " +
 	    " select model.tdpBasicCommittee.tdpBasicCommitteeId,model.tdpBasicCommittee.name,count(distinct model.tdpCommitteeId)" +//2
 		" from   TdpCommittee model " +
 	    " where  model.tdpCommitteeId is not null ");
 		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sb.append(" and model.tdpCommitteeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
 		//locations related.
 		if(committeeBO.getQueryString() != null && committeeBO.getQueryString().length()>0){
 			sb.append( committeeBO.getQueryString() );
 		}
 		
 		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
 		
 			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
 				sb.append("   and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
 				if( committeeBO.getDate()!=null){
 					sb.append( " and ( date(model.startedDate) > :givendate or model.startedDate is null)" );
 				}else{
 					sb.append(" and model.startedDate is null ");
 				}
 			}
 		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
 				sb.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N' ");
 				if(committeeBO.getDate()!=null){
 					sb.append( " and date(model.startedDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
 				}else{
 					sb.append(" and model.completedDate is null ");
 				}
 			}
 			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
 				sb.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
 				if( committeeBO.getDate()!=null){
 					sb.append( " and date(model.completedDate) < :givendate " );
 				}
 			} 
 		}
 			
 		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
 			sb.append(" and model.userAddress.state.stateId = :stateId ");
 		}
 		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
 			sb.append(committeeBO.getCommitteesQueryString());
		}
 		sb.append(" group by model.tdpBasicCommittee.tdpBasicCommitteeId ");
 		
 		Query query = getSession().createQuery(sb.toString());
 		
 		//query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
 		
 		//locations.
 		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
 		}
 		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
 		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
 		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
 		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
 		}
 		
 		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
 			if(committeeBO.getDate()!=null){
 				query.setDate("givendate",committeeBO.getDate());
 			}
 		}
 		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
 			query.setParameter("stateId",committeeBO.getStateId());
 		}
 		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
 			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
 		return query.list();
 	}
    
    public List<Object[]> getTopPoorCommitteeLocations(CommitteeInputVO committeeBO){
    	
    	
    	StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		StringBuilder sbE = new StringBuilder();
		
		sbS.append("select count(distinct model.tdpCommitteeId)");//0
		
		if(committeeBO.getGroupingLocation().equalsIgnoreCase("State")){
			sbS.append(" ,model.userAddress.state.stateId,model.userAddress.state.stateName ");//2
			sbE.append("  group by model.userAddress.state.stateId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("District")){
			sbS.append(" ,model.userAddress.district.districtId,model.userAddress.district.districtName ");//2
			sbE.append("  group by model.userAddress.district.districtId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Constituency")){
			sbS.append(" ,model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");//2
			sbE.append("  group by model.userAddress.constituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Mandal")){
			sbS.append(" ,model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");//2
			sbE.append("  group by model.userAddress.tehsil.tehsilId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("localElectionBody")){
			sbS.append(" ,model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name " +//2
					   " ,model.userAddress.localElectionBody.electionType.electionTypeId,model.userAddress.localElectionBody.electionType.electionType ");//4
			sbE.append(" group by model.userAddress.localElectionBody.localElectionBodyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Village")){
			sbS.append(" ,model.userAddress.panchayat.panchayatId,model.userAddress.panchayat.panchayatName ");//2
			sbE.append(" group by model.userAddress.panchayat.panchayatId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Ward")){
			sbS.append(" ,model.userAddress.ward.constituencyId,model.userAddress.ward.name ");//2
			sbE.append(" group by model.userAddress.ward.constituencyId ");
		}
		
		sbM.append(" from  TdpCommittee model where model.tdpCommitteeId is not null ");
		if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size() >0l){
			sbM.append(" and  model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}
		
		//locations related.
 		if(committeeBO.getQueryString() != null && committeeBO.getQueryString().length()>0){
 			sbM.append( committeeBO.getQueryString() );
 		}
		
 		//notStarted or started or completed or total related.
 		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
 	 		
 			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
 				sbM.append("   and model.isCommitteeConfirmed = 'N' and model.completedDate is null ");
 				if( committeeBO.getDate()!=null){
 					sbM.append( " and ( date(model.startedDate) > :givendate or model.startedDate is null)" );
 				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
 					sbM.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.startedDate is null)" );
 				}else{
 					sbM.append(" and model.startedDate is null ");
 				}
 			}
 		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
 			  sbM.append(" and model.startedDate is not null and  model.isCommitteeConfirmed = 'N' ");
 				if(committeeBO.getDate()!=null){
 					sbM.append( " and date(model.startedDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
 				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
 					sbM.append( " and ( (date(model.startedDate) between :startDate and :endDate ) or model.completedDate is null)" );
 				}else{
 					sbM.append(" and model.completedDate is null ");
 				}
 			}
 			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
 				sbM.append(" and model.startedDate is not null  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
 				if( committeeBO.getDate()!=null){
 					sbM.append( " and date(model.completedDate) < :givendate " );
 				}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
 					sbM.append( " and ( (date(model.completedDate) between :startDate and :endDate ))" );
 				}
 			} 
 		}
			
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.userAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sbM.append(" and model.tdpCommitteeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
			
		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
			sbM.append(committeeBO.getCommitteesQueryString());
		}
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		Query query = getSession().createQuery(sbf.toString());
		if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size() >0l){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}
		
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
 			if(committeeBO.getDate()!=null){
 				query.setDate("givendate",committeeBO.getDate());
 			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
 				query.setDate("startDate",committeeBO.getStartDate());
 				query.setDate("endDate",committeeBO.getEndDate());
 			}
 		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		return query.list();
    }
    //
    public List<Long> getTdpCommitteeId(Long tdpBasicCommitteeId,Long tdpCommitteeLevelId,Long tdpCommitteeLevelValue,Long tdpCommitteeEnrollmentId){
		Query query = getSession().createQuery("select model.tdpCommitteeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:tdpCommitteeLevelId and  " +
				" model.tdpCommitteeLevelValue =:tdpCommitteeLevelValue and model.tdpBasicCommittee.tdpBasicCommitteeId = :tdpBasicCommitteeId and " +
				" model.tdpCommitteeEnrollmentId = :tdpCommitteeEnrollmentId");
		query.setParameter("tdpBasicCommitteeId", tdpBasicCommitteeId);
		query.setParameter("tdpCommitteeLevelId", tdpCommitteeLevelId);
		query.setParameter("tdpCommitteeLevelValue", tdpCommitteeLevelValue);
		query.setParameter("tdpCommitteeEnrollmentId", tdpCommitteeEnrollmentId);
		return query.list();
	}
    @SuppressWarnings("unchecked")
	public List<Object[]> getTdpCommitteeDetailsByEnrollmentId(List<Long> enrollmentIds){
		Query query = getSession().createQuery("select 0,min(model.startedDate),max(model.startedDate) from TdpCommittee model where " +
				" model.tdpCommitteeEnrollmentId in(:enrollmentIds) and model.startedDate is not null ");
		
		query.setParameterList("enrollmentIds", enrollmentIds);
		return query.list();
		
	}
	
	public List<Long> getCommitteeIds(Long levelId,Long levelValue,Long committeeEnrollmentId,Date startDate,Date endDate,Long basicCommitteetypeId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("select model.tdpCommitteeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue and " +
				" model.tdpBasicCommitteeId = :basicCommitteetypeId and model.tdpCommitteeEnrollmentId =:committeeEnrollmentId  ");
		if(startDate != null && endDate != null)
		str.append( " and ( (date(model.startedDate) between :startDate and :endDate )  OR  date(model.completedDate) between :startDate and :endDate )  )" );
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		query.setParameter("committeeEnrollmentId", committeeEnrollmentId);
		if(startDate != null && endDate != null){
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		}
		
		if(basicCommitteetypeId != null && basicCommitteetypeId.longValue() > 0l)
			query.setParameter("basicCommitteetypeId", basicCommitteetypeId);
		
		return query.list();
	}
	
	public Long getCommitteeConfirmRuleIdByCommitteeId(Long tdpCommitteeId){
		Query query = getSession().createQuery(" select model.committeeConfirmRuleId  from TdpCommittee model where model.tdpCommitteeId = :tdpCommitteeId");
		query.setParameter("tdpCommitteeId", tdpCommitteeId);
		return (Long)query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getTdpCommitteeMandalByConstituency(Long constituencyId,Long enrollmentId,String committeeType){
		Query query = null;
		
		if(committeeType.equalsIgnoreCase("BoothCommittee")){
			query = getSession().createQuery(" select  distinct model.address.tehsil.tehsilId,model.address.tehsil.tehsilName " +
					" from BoothInchargeCommittee model " +
					" where model.address.constituency.constituencyId = :constituencyId " +
					" and model.boothInchargeEnrollmentId = :enrollmentId order by model.address.tehsil.tehsilName ");
		}
		else if(committeeType.equalsIgnoreCase("PartyCommittee")){
			query = getSession().createQuery(" select  distinct model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName " +
					" from TdpCommittee model " +
					" where model.constituency.constituencyId = :constituencyId " +
					" and model.tdpCommitteeEnrollmentId = :enrollmentId order by model.userAddress.tehsil.tehsilName ");
		}
		
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("enrollmentId", enrollmentId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getTdpCommitteeAllPanchayatsInMandals(List<Long> ids,Long enrollmentId,Long constituencyId)
	{
		String queryString = "select distinct model.userAddress.panchayat.panchayatId," +
				" model.userAddress.panchayat.panchayatName,model.userAddress.tehsil.tehsilName " +
				" from TdpCommittee model " +
				" where model.userAddress.tehsil.tehsilId in (:ids) " +
				" and model.tdpCommitteeEnrollmentId = :enrollmentId " +
				" and model.userAddress.constituency.constituencyId =:constituencyId " +
				" order by model.userAddress.panchayat.panchayatName ";
		
		Query query = getSession().createQuery(queryString);
			query.setParameterList("ids", ids);
			query.setParameter("enrollmentId", enrollmentId);
			query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getTdpCommitteeLocalBodiesByConstituency(Long constituencyId,Long enrollmentId,String committeeType){
		Query query = null;
		if(committeeType.equalsIgnoreCase("BoothCommittee")){
			query = getSession().createQuery(" select  distinct model.address.localElectionBody.localElectionBodyId,model.address.localElectionBody.name " +
					" from BoothInchargeCommittee model " +
					" where model.address.constituency.constituencyId = :constituencyId " +
					" and model.boothInchargeEnrollmentId = :enrollmentId order by model.address.localElectionBody.name ");
		}
		else if(committeeType.equalsIgnoreCase("PartyCommittee")){
			query = getSession().createQuery(" select  distinct model.userAddress.localElectionBody.localElectionBodyId,model.userAddress.localElectionBody.name " +
					" from TdpCommittee model " +
					" where model.constituency.constituencyId = :constituencyId " +
					" and model.tdpCommitteeEnrollmentId = :enrollmentId order by model.userAddress.localElectionBody.name ");
		}
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("enrollmentId", enrollmentId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getTdpCommitteeWardsInLocalElectionBody(List<Long> localBodyIds,Long constituencyId){
		StringBuilder strQuery = new StringBuilder();
		/*str.append("SELECT distinct C1.constituency_id,C1.name,CONCAT(LEB.name, ' ', ET.election_type) FROM ");
		
		str.append(" tdp_committee TC,constituency C,constituency C1,user_address UA,local_election_body LEB,election_type ET ");
		
		str.append(" WHERE TC.constituency_id = C.constituency_id " +
				" AND TC.address_id = UA.user_address_id " +
				" AND UA.local_election_body = LEB.local_election_body_id " +
				" AND LEB.election_type_id = ET.election_type_id" );
		
		str.append(" AND LEB.local_election_body_id=C1.local_election_body_id AND UA.constituency_id = C.constituency_id ");
		
		str.append(" AND UA.local_election_body IN (:localBodyIds) " );
		if(constituencyId != null)
			str.append(" AND UA.constituency_id = :constituencyId " );
		
		str.append(" ORDER BY C1.constituency_id,C1.name ");*/
		
		strQuery.append(" SELECT distinct w.constituency_id,w.`name`, CONCAT(leb.`name`,'-',et.election_type) from  ");
		strQuery.append(" tdp_committee tc,  ");
		strQuery.append(" user_address ua, ");
		strQuery.append(" constituency w, ");
		strQuery.append(" local_election_body leb , ");
		strQuery.append(" election_type et ");
		strQuery.append(" where  ");
		strQuery.append(" ua.local_election_body = leb.local_election_body_id AND ");
		strQuery.append(" leb.election_type_id = et.election_type_id and ");
		strQuery.append(" ua.ward = w.constituency_id   ");
		if(localBodyIds != null && localBodyIds.size()>0)
			strQuery.append(" and ua.local_election_body in (:localBodyIds)   ");
		strQuery.append(" and tc.address_id = ua.user_address_id   ");
		if(constituencyId != null && constituencyId.longValue()>0L)
			strQuery.append("and  ua.constituency_id = :constituencyId   ");
		strQuery.append(" and tc.tdp_committee_enrollment_id = 2   ");
		strQuery.append(" and tc.tdp_committee_level_id in (8)  ");//9 is tdp_committee_Level_id
		
		Query query = getSession().createSQLQuery(strQuery.toString());
		
		if(localBodyIds != null && localBodyIds.size()>0)
			query.setParameterList("localBodyIds", localBodyIds);
		if(constituencyId != null)
			query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
	
	public List<Object[]> getLocationWiseCommittees(String locationType,Long locationId,Long tdpCommitteeEnrollmentYearId){
		
		//0-tdp_base_comitteeId,1-levelId,2-levelName,3-committeeConfrimed,4-start Date,5-completed Date
		StringBuilder sb = new StringBuilder();
		
		sb.append("select distinct model.tdpBasicCommitteeId,model.tdpCommitteeLevelId,model.tdpCommitteeLevel.tdpCommitteeLevel,model.isCommitteeConfirmed,model.startedDate,model.completedDate"
				+ " from TdpCommittee model "
				+ " where model.tdpCommitteeEnrollmentId= :tdpCommitteeEnrollmentYearId ");
		
		if(locationType.equalsIgnoreCase("constituency")){
			sb.append(" and model.userAddress.constituency.constituencyId=:locationId");
		}else if(locationType.equalsIgnoreCase("district")){
			sb.append(" and model.userAddress.district.districtId=:locationId");
		}else if(locationType.equalsIgnoreCase("state")){
			sb.append(" and model.userAddress.state.stateId=:locationId");
		}else if(locationType.equalsIgnoreCase("mandal")){
			sb.append(" and model.userAddress.tehsil.tehsilId=:locationId");
		}else if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" and model.userAddress.panchayat.panchayatId=:locationId");
		}else if(locationType.equalsIgnoreCase("Parliament")){
			sb.append(" and model.userAddress.parliamentConstituency.constituencyId=:locationId");
		}else if(locationType.equalsIgnoreCase("municipality")){
			sb.append(" and model.userAddress.localElectionBody.localElectionBodyId=:locationId");
		}else if(locationType.equalsIgnoreCase("wards")){
			sb.append(" and model.userAddress.ward.constituencyId=:locationId");
		}
		sb.append(" order by model.tdpCommitteeId ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("tdpCommitteeEnrollmentYearId", tdpCommitteeEnrollmentYearId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
	public List<Object[]> getLocationWiseCommitteesCnt(String locationType,Long locationId, Long tdpCommitteeEnrollmentYearId){
		
		StringBuilder sb = new StringBuilder();		
		
		sb.append(" select tcl.tdp_committee_level,tc.tdp_basic_committee_id,tr.role,tcm.tdp_cadre_id,t.first_name,t.membership_id,t.mobile_no," +
				" tc.tdp_committee_enrollment_id,tcm.inserted_time from tdp_committee_member tcm,tdp_committee tc,tdp_committee_role tcr,tdp_committee_level tcl ," +
				" user_address ua ,tdp_cadre_enrollment_year y,tdp_cadre t,tdp_roles tr where y.enrollment_year_id =:tdpCommitteeEnrollmentYearId and  y.is_deleted='N' and y.tdp_cadre_id = t.tdp_cadre_id and t.is_deleted='N' " +
				" and " +
				" t.enrollment_year=:enrollmentYear and t.address_id = ua.user_address_id and t.tdp_cadre_id = tcm.tdp_cadre_id and tcm.tdp_committee_role_id = tcr.tdp_committee_role_id and " +
				" tcr.tdp_committee_id = tc.tdp_committee_id and tc.tdp_committee_level_id = tcl.tdp_committee_level_id and tcl.tdp_committee_level_id >:levelId and " +
				" tc.tdp_committee_enrollment_id = :committeeEnrollId and tcm.status='F' and tcm.is_active='Y' and  tc.tdp_basic_committee_id =:tdpBasicCommitteeId and " +
				" tcr.tdp_roles_id = tr.tdp_roles_id ");

		if(locationType.equalsIgnoreCase("constituency")){
			sb.append(" and ua.constituency_id =:locationId");
		}else if(locationType.equalsIgnoreCase("district")){
			sb.append(" and ua.district_id  =:locationId ");
		}else if(locationType.equalsIgnoreCase("state")){
			sb.append(" and ua.state_id =:locationId ");
		}else if(locationType.equalsIgnoreCase("mandal")){
			sb.append(" and ua.tehsil_id =:locationId ");
		}else if(locationType.equalsIgnoreCase("panchayat")){
			sb.append(" and ua.panchayat_id =:locationId ");
		}/*else if(locationType.equalsIgnoreCase("Parliament")){
			sb.append(" and ua.parliament_constituency_id =:locationId ");
		}*/else if(locationType.equalsIgnoreCase("municipality")){
			sb.append(" and ua.local_election_body =:locationId ");
		}else if(locationType.equalsIgnoreCase("wards")){
			sb.append(" and ua.constituency_id =:locationId ");
		}
		
		   Query query = getSession().createSQLQuery((sb.toString()));
		    query.setParameter("locationId", locationId);
		    query.setParameter("tdpCommitteeEnrollmentYearId", tdpCommitteeEnrollmentYearId);
		    query.setParameter("enrollmentYear", "2014");
		    query.setParameter("committeeEnrollId", 2);
		    query.setParameter("tdpBasicCommitteeId", 1);
		    query.setParameter("levelId", 9);
		    
		   
		        return query.list();
			
		
	}
public List<Object[]> getCommitteeCandidatesByLevelWiseDetails(List<Long> roleIds,List<Long> committeeIds,Long basicCommoteeId,Long enrollmentId,List<Long> enrollmentYears){
	StringBuilder sb = new StringBuilder();
	sb.append("select distinct tdpCommitteeMember.tdpCadre.tdpCadreId,tdpCommitteeMember.tdpCadre.firstname," +// 0-tdpCadreId 1-name
			"tdpCommitteeMember.tdpCadre.image,tdpCommitteeMember.tdpCadre.mobileNo," +		//2-image  3-mobileNo
			"tdpCommitteeMember.tdpCadre.memberShipNo," +		//4-memberShipNo
			"tdpCommitteeRole.tdpRoles.role,tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevel," +//5-role	6-tdpCommitteeLevel
			"tdpCommittee.tdpCommitteeEnrollmentId ");		//7-tdpCommitteeEnrollmentId
	sb.append(" from TdpCommittee tdpCommittee,TdpCommitteeRole tdpCommitteeRole," +
			"TdpCommitteeMember tdpCommitteeMember,TdpCadre tdpCadre," +
			"TdpCadreEnrollmentYear tdpCadreEnrollmentYear,TdpRoles tdpRoles ," +
			"TdpCommitteeLevel tdpCommitteeLevel ");
	sb.append(" where tdpCommittee.tdpCommitteeId=tdpCommitteeRole.tdpCommitteeId " +
			"and tdpCommitteeMember.tdpCommitteeRoleId=tdpCommitteeRole.tdpCommitteeRoleId " +
			"and tdpCadreEnrollmentYear.tdpCadreId=tdpCadre.tdpCadreId " +
			"and tdpCommitteeMember.tdpCadreId=tdpCadreEnrollmentYear.tdpCadreId " +
			"and tdpCadre.isDeleted='N' and tdpCadreEnrollmentYear.isDeleted='N' " +
			"and tdpCommitteeMember.isActive='Y' "); 
		if(enrollmentYears !=null && enrollmentYears.size() >0){
			sb.append(" and tdpCadre.enrollmentYear in (:enrollmentYears)");
		}
		if(roleIds!=null && roleIds.size() >0){
			sb.append("	and tdpCommitteeRole.tdpRoles.tdpRolesId in (:roleIds) ");
		}
		if(committeeIds !=null && committeeIds.size() >0){
			sb.append("	and tdpCommittee.tdpCommitteeLevelId in (:committeeIds) ");
		}
		if(basicCommoteeId !=null && basicCommoteeId.longValue() >0L){
			sb.append("	and tdpCommittee.tdpBasicCommitteeId=:basicCommoteeId");

		}
		if(enrollmentId !=null && enrollmentId.longValue() >0L){
			sb.append("	and tdpCommittee.tdpCommitteeEnrollmentId=:enrollmentId");
		}
	Query query = getSession().createQuery(sb.toString());
	if(enrollmentYears !=null && enrollmentYears.size() >0){
		query.setParameterList("enrollmentYears", enrollmentYears);
	}
	if(roleIds!=null && roleIds.size() >0){
		query.setParameterList("roleIds", roleIds);
	}
	if(committeeIds !=null && committeeIds.size() >0){
		query.setParameterList("committeeIds", committeeIds);
	}
	if(basicCommoteeId !=null && basicCommoteeId.longValue() >0L){
		query.setParameter("basicCommoteeId", basicCommoteeId);

	}
	if(enrollmentId !=null && enrollmentId.longValue() >0L){
		query.setParameter("enrollmentId", enrollmentId);
	}

    return query.list();
}

public List<Object[]> getAvailableCommitteeDetails(String type , List<Long> enrollmentYearIdsList, Long committeeLevelId,List<Long> basicCommitteeTypeIdsList,List<Long> committeeTypeIdsList,Long locationScopeId,List<Long> locationValuesList)
{
	StringBuilder sb = new StringBuilder();
	sb.append(" SELECT  ");
	sb.append(" ua.district_id,d.district_name,tbc.name,tbc.tdp_basic_committee_id,sum(tcr.max_members)    ");
	sb.append(" from   ");
	sb.append(" tdp_committee tc,  ");
	sb.append(" tdp_basic_committee tbc,  ");
	sb.append(" tdp_committee_level tcl,  ");
	sb.append(" user_address ua ,  ");
	sb.append(" district d ,  ");
	sb.append(" tdp_committee_role tcr  ");
	sb.append(" where   ");
	sb.append(" tc.address_id = ua.user_address_id and   ");
	sb.append(" ua.district_id = d.district_id and   ");
	sb.append(" tc.tdp_basic_committee_id = tbc.tdp_basic_committee_id and   ");
	sb.append(" tcl.tdp_committee_level_id = tc.tdp_committee_level_id and   ");
	sb.append(" tc.tdp_committee_id = tcr.tdp_committee_id ");
	if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0)
		sb.append(" and tbc.tdp_basic_committee_id in (:committeeTypeIdsList) ");
	if(basicCommitteeTypeIdsList != null && basicCommitteeTypeIdsList.size()>0)
		sb.append(" and tbc.tdp_committee_type_id in (:basicCommitteeTypeIdsList) ");
	if(enrollmentYearIdsList != null && enrollmentYearIdsList.size()>0)
		sb.append(" and tc.tdp_committee_enrollment_id in (:enrollmentYearIdsList)    ");
	if(committeeLevelId != null && committeeLevelId.longValue()>0L)
		sb.append(" and tc.tdp_committee_level_id = :committeeLevelId  ");
	if(locationValuesList != null && locationValuesList.size()>0){
		if(locationScopeId !=null && locationScopeId.longValue() ==2L)
			sb.append(" and ua.state_id in (:locationValuesList) ");
	}
	if(type != null && type.trim().equalsIgnoreCase("completed")){
		sb.append(" and tc.is_committee_confirmed ='Y' and and tc.started_date is not null and tc.completed_date is not null ");
	}else if(type != null && type.trim().equalsIgnoreCase("started")){
		sb.append(" and tc.is_committee_confirmed ='N' and and tc.started_date is not null and tc.completed_date is  null ");
	}
	sb.append(" GROUP BY  ");
	sb.append(" ua.district_id,tbc.tdp_committee_type_id,tbc.tdp_basic_committee_id  ");
	sb.append(" ORDER BY  ");
	sb.append(" tbc.order_id  ");

	Query query = getSession().createSQLQuery(sb.toString());
	if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0)
		query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
	if(basicCommitteeTypeIdsList != null && basicCommitteeTypeIdsList.size()>0)
		query.setParameterList("basicCommitteeTypeIdsList", basicCommitteeTypeIdsList);
	if(enrollmentYearIdsList != null && enrollmentYearIdsList.size()>0)
		query.setParameterList("enrollmentYearIdsList", enrollmentYearIdsList);
	if(committeeLevelId != null && committeeLevelId.longValue()>0L)
		query.setParameter("committeeLevelId", committeeLevelId);
	if(locationValuesList != null && locationValuesList.size()>0)
		query.setParameterList("locationValuesList", locationValuesList);
	return query.list();
}

public List<Object[]> getCommitteeMembersAddedStatusDetails(List<Long> enrollmentYearIdsList, Long committeeLevelId,List<Long> basicCommitteeTypeIdsList,List<Long> committeeTypeIdsList,Long locationScopeId,List<Long> locationValuesList)
{
	StringBuilder sb = new StringBuilder();
	sb.append(" SELECT  ");
	sb.append(" ua.district_id,d.district_name,tbc.name,tbc.tdp_basic_committee_id,count(distinct tcm.tdp_cadre_id)    ");
	sb.append(" from   ");
	sb.append(" tdp_committee tc,  ");
	sb.append(" tdp_committee_role tcr,  ");
	sb.append(" tdp_committee_member tcm,  ");
	sb.append(" tdp_cadre tc1,  ");
	sb.append(" tdp_cadre_enrollment_year tcey,  ");
	sb.append(" tdp_basic_committee tbc,  ");
	sb.append(" tdp_committee_level tcl,  ");
	sb.append(" user_address ua ,  ");
	sb.append(" district d   ");
	sb.append(" where   ");
	sb.append(" tc.address_id = ua.user_address_id and   ");
	sb.append(" ua.district_id = d.district_id and   ");
	sb.append(" tc.tdp_basic_committee_id = tbc.tdp_basic_committee_id and   ");
	sb.append(" tcl.tdp_committee_level_id = tc.tdp_committee_level_id and   ");
	sb.append(" tc.tdp_committee_id = tcr.tdp_committee_id and   ");
	sb.append(" tcr.tdp_committee_role_id = tcm.tdp_committee_role_id and   ");
	sb.append(" tcm.is_active='Y' and   ");
	sb.append(" tc1.tdp_cadre_id = tcm.tdp_cadre_id and   ");
	sb.append(" tc1.tdp_cadre_id = tcey.tdp_cadre_id and   ");
	sb.append(" tc1.is_deleted='N' and   ");
	sb.append(" tcey.is_deleted='N'    ");
	
	if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0)
		sb.append(" and tbc.tdp_basic_committee_id in (:committeeTypeIdsList) ");
	if(basicCommitteeTypeIdsList != null && basicCommitteeTypeIdsList.size()>0)
		sb.append(" and tbc.tdp_committee_type_id in (:basicCommitteeTypeIdsList) ");
	if(enrollmentYearIdsList != null && enrollmentYearIdsList.size()>0)
		sb.append(" and tc.tdp_committee_enrollment_id in (:enrollmentYearIdsList)    ");
	if(committeeLevelId != null && committeeLevelId.longValue()>0L)
		sb.append(" and tc.tdp_committee_level_id = :committeeLevelId  ");
	if(locationValuesList != null && locationValuesList.size()>0){
		if(locationScopeId !=null && locationScopeId.longValue() ==2L)
			sb.append(" and ua.state_id in (:locationValuesList) ");
	}
	
	sb.append(" and tc1.enrollment_year ='2014' ");
	sb.append(" and tcey.tdp_cadre_id = tcm.tdp_cadre_id  ");
	sb.append(" GROUP BY   ");
	sb.append(" ua.district_id,tbc.tdp_committee_type_id,tbc.tdp_basic_committee_id  ");
	sb.append(" ORDER BY   ");
	sb.append(" tbc.order_id  ");

	Query query = getSession().createSQLQuery(sb.toString());
	if(committeeTypeIdsList != null && committeeTypeIdsList.size()>0)
		query.setParameterList("committeeTypeIdsList", committeeTypeIdsList);
	if(basicCommitteeTypeIdsList != null && basicCommitteeTypeIdsList.size()>0)
		query.setParameterList("basicCommitteeTypeIdsList", basicCommitteeTypeIdsList);
	if(enrollmentYearIdsList != null && enrollmentYearIdsList.size()>0)
		query.setParameterList("enrollmentYearIdsList", enrollmentYearIdsList);
	if(committeeLevelId != null && committeeLevelId.longValue()>0L)
		query.setParameter("committeeLevelId", committeeLevelId);
	if(locationValuesList != null && locationValuesList.size()>0)
		query.setParameterList("locationValuesList", locationValuesList);
	return query.list();
}
	
}
