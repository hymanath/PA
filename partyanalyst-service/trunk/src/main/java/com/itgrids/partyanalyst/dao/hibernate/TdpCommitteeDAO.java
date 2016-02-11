package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
import com.itgrids.partyanalyst.dto.CommitteeSummaryVO;
import com.itgrids.partyanalyst.model.TdpCommittee;

public class TdpCommitteeDAO extends GenericDaoHibernate<TdpCommittee, Long>  implements ITdpCommitteeDAO {

	public TdpCommitteeDAO() {
		super(TdpCommittee.class);
	}

	public List<Object[]> getAllAffiliatedCommittiesInALocation(Long levelId,Long levelValue){
		Query query = getSession().createQuery("select model.tdpCommitteeId,model.tdpBasicCommittee.name from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue and " +
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		return query.list();
	}
	
	public List<Long> getMainCommittiesInALocation(Long levelId,Long levelValue){
		Query query = getSession().createQuery("select model.tdpCommitteeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue and " +
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 1");
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
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

	public Long getTotalCommitteesCountByLocation(String state,List<Long> levelIds,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){
		//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId

		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId) " +
				" from TdpCommittee model where model.tdpBasicCommittee.tdpBasicCommitteeId = 1  ");
		
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
			query.setParameter("state", state);
		}
		if(levelIds != null && levelIds.size() > 0)
		{
			query.setParameterList("levelIds", levelIds);
		}
		
		return (Long)query.uniqueResult();
	}
	
	public List<Object[]> getLocationByTypeIdAndLevel(List<Long> levelIds,Long committeTypeId,Long constituencyId,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCommitteeLevelValue,model.isCommitteeConfirmed,model.tdpCommitteeLevelId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in(:levelIds) and " +
				" model.tdpBasicCommittee.tdpBasicCommitteeId =:committeTypeId and model.constituency.constituencyId =:constituencyId");
		if(status.equalsIgnoreCase("Conform"))
			str.append(" and  model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
			else if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			else if(status.equalsIgnoreCase("NotStarted"))
			str.append(" and model.startedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevelValue");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("levelIds", levelIds);
		query.setParameter("committeTypeId", committeTypeId);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getLocationsByTypeIdAndLevel(Long levelId,Long committeTypeId,List<Long> locationValues,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCommitteeLevelValue,model.isCommitteeConfirmed,model.tdpCommitteeLevelId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and " +
				" model.tdpBasicCommittee.tdpBasicCommitteeId =:committeTypeId and model.tdpCommitteeLevelValue in(:locationValues)");
		
		if(status.equalsIgnoreCase("Conform"))
		str.append(" and  model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		else if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		else if(status.equalsIgnoreCase("NotStarted"))
		str.append(" and model.startedDate is null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId,model.tdpCommitteeLevelValue");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("levelId", levelId);
		query.setParameter("committeTypeId", committeTypeId);
		query.setParameterList("locationValues", locationValues);
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
	public List<Object[]> getLocationWiseVillageDetails(Long constituencyId){
		//0 basiccommId,1 name,2confirmd,3count
		Query query = getSession().createQuery("select TBC.tdpBasicCommitteeId,TBC.name,TC.isCommitteeConfirmed,count(*) " +
				" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId in (6,8) " +
				" and  TC.constituency.constituencyId = :constituencyId group by TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	 //getting started 
	public List<Object[]> getLocationWiseVillageStartedDetails(Long constituencyId){
		//0 basiccommId,3count
		Query query = getSession().createQuery("select TBC.tdpBasicCommitteeId,count(*) " +
				" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId and TC.tdpCommitteeLevelId in (6,8) " +
				" and  TC.constituency.constituencyId = :constituencyId and TC.startedDate is not null and TC.isCommitteeConfirmed ='N'  and " +
				" TC.completedDate is null group by TBC.tdpBasicCommitteeId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	//getting total and confirmed counts for mandal lvl
		public List<Object[]> getLocationWiseMandalDetails(List<Long> locationIds,Long levelId){
			//0 basiccommId,1 name,2confirmd,3count
			Query query = getSession().createQuery("select TBC.tdpBasicCommitteeId,TBC.name,TC.isCommitteeConfirmed,count(*) " +
					" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  " +
					" and  TC.tdpCommitteeLevel.tdpCommitteeLevelId = :levelId and TC.tdpCommitteeLevelValue in(:locationIds) " +
					" group by TBC.tdpBasicCommitteeId,TC.isCommitteeConfirmed");
			query.setParameter("levelId", levelId);
			query.setParameterList("locationIds", locationIds);
			return query.list();
		}
		 //getting started  for mandal lvl
		public List<Object[]> getLocationWiseMandalStartedDetails(List<Long> locationIds,Long levelId){
			//0 basiccommId,3count
			Query query = getSession().createQuery("select TBC.tdpBasicCommitteeId,count(*) " +
					" from TdpCommittee TC , TdpBasicCommittee TBC where TC.tdpBasicCommitteeId = TBC.tdpBasicCommitteeId  " +
					" and   TC.tdpCommitteeLevel.tdpCommitteeLevelId = :levelId and TC.tdpCommitteeLevelValue in(:locationIds) " +
					" and TC.startedDate is not null and TC.isCommitteeConfirmed ='N'  and " +
					" TC.completedDate is null group by TBC.tdpBasicCommitteeId");
			query.setParameter("levelId", levelId);
			query.setParameterList("locationIds", locationIds);
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
	
	public List<Object[]> getTotalCompletedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){
		//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId

		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId,model.districtId " +
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
		if(startDate != null && endDate !=null){
			str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  ");
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
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		
		return query.list();
	}
	
	public List<Object[]> committeesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.district.districtId,model.tdpBasicCommittee.tdpBasicCommitteeId " +// BASIC_COMMITTEE_ID sri
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.district.districtId in(:districtIds) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) and model.isCommitteeConfirmed = 'N' and model.startedDate is not null and model.completedDate is null ");
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
	
	
	public List<Object[]> committeesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.constituency.constituencyId" +// DISTRICT
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.constituency.constituencyId in(:constiIds) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate)");
			}
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
		}
		
		str.append(" group by model.constituency.constituencyId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

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
	
	public List<Object[]> getCommitteesCountByDistrictIdAndLevel(List<Long> districtIds,List<Long> levelIds){
		Query query = getSession().createQuery(" select count(model.tdpCommitteeId), model.district.districtId, model.tdpBasicCommitteeId" +
				" from TdpCommittee model" +
				" where model.district.districtId in(:districtIds)" +
				" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds)" +
				" and model.district.districtId is not null" +
				//" and model.tdpBasicCommitteeId = :basicCommty  " +
				" group by model.district.districtId, model.tdpBasicCommitteeId order by model.tdpBasicCommitteeId ");
		
		query.setParameterList("districtIds", districtIds);
		query.setParameterList("levelIds", levelIds);
		//query.setParameter("basicCommty", 1l);
		
		return query.list();
	}
	
	public List<Object[]> getCommitteesCountByConstituencyIdAndLevel(List<Long> constituencyIds,List<Long> levelIds){
		Query query = getSession().createQuery(" select count(model.tdpCommitteeId), model.constituency.constituencyId,model.tdpBasicCommitteeId " +
				" from TdpCommittee model" +
				" where model.constituency.constituencyId in(:constituencyIds)" +
				" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.constituency.constituencyId is not null " +
				//" and model.tdpBasicCommitteeId = :basicCommty  " +
				" group by model.constituency.constituencyId,model.tdpBasicCommitteeId ");
		
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameterList("levelIds", levelIds);
		//query.setParameter("basicCommty", 1l);
		
		return query.list();
	}
	
	public List<Object[]> committeesCountByLocationIds(Long levelId,List<Long> levelValues,Date startDate,Date endDate,String type){
		StringBuilder str = new StringBuilder();


		str.append("select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId, model.tdpBasicCommittee.tdpBasicCommitteeId " + 
				" from TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue in(:levelValues) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) ");
				
			}
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  ");
			}
			str.append(" and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'");
		}
		
		str.append(" group by model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

		Query query = getSession().createQuery(str.toString());
		
		
		query.setParameterList("levelValues", levelValues);
			query.setParameter("levelId", levelId);
		if(startDate != null && endDate !=null ){
			query.setParameter("startDate", startDate);
			query.setParameter("endDate", endDate);	
		}
		
		return query.list();
	}
	
	public List<Object[]> totalCommitteesCountByLocationIds(Long levelId,List<Long> levelValues){
		StringBuilder str = new StringBuilder();
        //0count,1locationID
		str.append("select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpBasicCommitteeId from TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId " + 
				"  and model.tdpCommitteeLevelValue in(:levelValues)  ");	
		str.append(" group by model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		
		query.setParameterList("levelValues", levelValues);
			query.setParameter("levelId", levelId);
		
		return query.list();
	}
	
	public List<Object[]> getCompletedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){

		StringBuilder str = new StringBuilder();

		str.append("select count(distinct model.tdpCommitteeId), " +
		" model.tdpBasicCommittee.name,model.tdpBasicCommittee.tdpBasicCommitteeId " +
		" from TdpCommittee model where ");
		str.append(" model.state= :state ");
		if(startDate !=null && endDate !=null){
			str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate ) ");
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
		}
		str.append("and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2 and" +
				"  model.isCommitteeConfirmed = 'Y' " +
				"group by model.tdpBasicCommittee.tdpBasicCommitteeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("state", state);		
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
		return query.list();
	}
	
	public List<Object[]> getStartedAffliCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate,List<Long> districtIds,List<Long> assemblyIds,List<Long> locationlevelValueList){

		StringBuilder str = new StringBuilder();

		str.append("select count(distinct model.tdpCommitteeId), " +
		" model.tdpBasicCommittee.name,model.tdpBasicCommittee.tdpBasicCommitteeId " +
		" from TdpCommittee model where ");
		str.append(" model.state= :state ");
		if(startDate !=null && endDate !=null){
			
			str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate ) ");
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
		}
		str.append("and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId = 2 and" +
				"  model.isCommitteeConfirmed = 'N' and model.completedDate is null " +
				"group by model.tdpBasicCommittee.tdpBasicCommitteeId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("state", state);		
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
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from " +
				" TdpCommittee model where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and model.tdpCommitteeLevelValue =:levelValue ");
		
		if(constituencyId != null && constituencyId > 0)
			str.append(" and model.constituency.constituencyId =:constituencyId ");
		
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		if(constituencyId != null && constituencyId > 0)
		  query.setParameter("constituencyId", constituencyId);
		return query.list();
				
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from " +
				" TdpCommittee model,Panchayat p where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		
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
		return query.list();
				
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long localElectionBody,Long constituencyId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.tdpCommitteeId),model.tdpCommitteeLevelValue,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from " +
				" TdpCommittee model,Constituency con where model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		
		if(localElectionBody != null && localElectionBody > 0)
			str.append(" and con.localElectionBody.localElectionBodyId =:localElectionBody and model.tdpCommitteeLevelValue = con.constituencyId ");
		if(constituencyId != null && constituencyId > 0)
			str.append(" and model.constituency.constituencyId =:constituencyId ");
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		if(constituencyId != null && constituencyId > 0)
			  query.setParameter("constituencyId", constituencyId);
		if(localElectionBody != null && localElectionBody > 0)
		  query.setParameter("localElectionBody", localElectionBody);
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
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long constituencyId,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId and " +
				" model.tdpCommitteeLevelValue =:levelValue ");
		
		  if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			else if(status.equalsIgnoreCase("completed"))
			str.append(" and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		  if(constituencyId != null && constituencyId > 0)
			 str.append("and model.constituency.constituencyId =:constituencyId");
		  
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("levelValue", levelValue);
		
		if(constituencyId != null && constituencyId > 0)
		  query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	public List<Object[]> getcommitteesCountByDistrict(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> districtIds){
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
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
			str.append(" and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
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
	}
	public List<Object[]> getcommitteesCountByConstituency(List<Long> levelIds,Date startDate,Date endDate,String type,List<Long> constiIds){
		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId)," + // COMMITTEES COUNT
				" model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId," + // COMMITTEE TYPE (MAIN/AFFLIATED)
				" model.constituency.constituencyId" +// DISTRICT
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.constituency.constituencyId in(:constiIds) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate)");
			}
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  and model.completedDate is not null and model.isCommitteeConfirmed = 'Y'  ");
			}
			str.append(" and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		}
		
		str.append(" group by model.constituency.constituencyId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

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
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillage(Long levelId,Long tehsilId,Long constituencyId,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model,Panchayat p  where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		
		  if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			else if(status.equalsIgnoreCase("completed"))
			str.append(" and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
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
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getStartedOrComplcommitteesCountByLevelIdAndLevelValueForVillageMunicipal(Long levelId,Long localElectionBody,Long constituencyId,String status){

		StringBuilder str = new StringBuilder();
		str.append("select count(distinct model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId from TdpCommittee model,Constituency con  where " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId =:levelId  ");
		
		  if(status.equalsIgnoreCase("Started"))
			str.append(" and model.startedDate is not null and model.isCommitteeConfirmed = 'N' and model.completedDate is null");
			else if(status.equalsIgnoreCase("completed"))
			str.append(" and model.completedDate is not null and model.isCommitteeConfirmed = 'Y' ");
		  if(constituencyId != null && constituencyId > 0)
			 str.append("and model.constituency.constituencyId =:constituencyId");
		  if(localElectionBody != null && localElectionBody > 0)
				str.append(" and con.localElectionBody.localElectionBodyId =:localElectionBody and model.tdpCommitteeLevelValue = con.constituencyId ");
		str.append(" group by model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");
		Query query = getSession().createQuery(str.toString());
		
		query.setParameter("levelId", levelId);
		query.setParameter("localElectionBody", localElectionBody);
		
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

}
