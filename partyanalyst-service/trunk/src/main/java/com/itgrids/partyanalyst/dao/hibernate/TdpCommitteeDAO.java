package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCommitteeDAO;
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

	public Long getTotalCommitteesCountByLocation(String state,List<Long> levelIds){
		//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId

		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId) " +
				" from TdpCommittee model where model.tdpBasicCommittee.tdpBasicCommitteeId = 1  ");
		if(state != null)
		{
			str.append(" and model.state= :state ");
		}
		if(levelIds != null && levelIds.size() > 0)
		{
			str.append(" and model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) ");
		}		
		//str.append(" group by model.tdpCommitteeLevel.tdpCommitteeLevelId ");

		Query query = getSession().createQuery(str.toString());
		if(state != null)
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
	
	public List<Object[]> getTotalCompletedCommitteesCountByLocation(String state,List<Long> levelIds,Date startDate,Date endDate){
		//0count ,1 isCommitteeConfirmed,2.tdpCommitteeLevelId,3.tdpBasicCommitteeId

		StringBuilder str = new StringBuilder();

		str.append("select count(model.tdpCommitteeId),model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId " +
				" from TdpCommittee model where  " +
				"  model.isCommitteeConfirmed= 'Y' ");
		if(state != null)
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
		if(state != null)
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
				" model.district.districtId " +// DISTRICT
				" from TdpCommittee model where  " +
				" model.tdpCommitteeLevel.tdpCommitteeLevelId in (:levelIds) " +
				" and model.district.districtId in(:districtIds) ");
		
		if(type.equalsIgnoreCase("started")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) " +
						" and model.completedDate is null ");
			}
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  ");
			}
		}
		
		str.append(" group by model.district.districtId,model.tdpBasicCommittee.tdpCommitteeType.tdpCommitteeTypeId ");

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
				str.append(" and ( date(model.startedDate)>=:startDate and date(model.startedDate)<=:endDate) " +
						" and model.completedDate is null ");
			}
		}else if(type.equalsIgnoreCase("completed")){
			if(startDate != null && endDate !=null){
				str.append(" and ( date(model.completedDate)>=:startDate and date(model.completedDate)<=:endDate )  ");
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
}
