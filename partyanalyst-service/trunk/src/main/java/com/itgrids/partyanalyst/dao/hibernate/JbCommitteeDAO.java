package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IJbCommitteeDAO;
import com.itgrids.partyanalyst.model.JbCommittee;

public class JbCommitteeDAO extends GenericDaoHibernate<JbCommittee, Long> implements IJbCommitteeDAO {

	public JbCommitteeDAO() {
		super(JbCommittee.class);
	}
 
	/*public List<Object[]> getJbCommitteeStatusCount(){
			
	 StringBuilder sb = new StringBuilder();
	 sb.append(" select " +
	 		   " jbCommittee.jbCommitteeId, " +
	 		   " jbCommittee.isCommitteeConfirmed, " +
	 		   " jbCommittee.startDate, " +
	 		   " jbCommittee.completedDate, " +
	 		   " jbCommittee.jbCommitteeLevel.jbCommitteeLevelId," +
	 		   " jbCommittee.jbCommitteeLevel.name" +
	 		   " from JbCommittee jbCommittee where " +
			   " jbCommittee.userAddress.state.stateId = 1 " +
			   " and jbCommittee.isDeleted = 'N' "); 	
	 Query query = getSession().createQuery(sb.toString());
	 return query.list();
 }*/
	
	public List<Object[]> getJbCommitteeStatusCount(String type,Set<Long> locationIdsList){
		//0 count(jbCommitteeId),1 isCommitteeConfirmed,2 startDate,3 completedDate,
		//4 jbCommitteeLevelId,5 name,6 jbCommitteeStatusId,7 status
		 StringBuilder sb = new StringBuilder();
		 sb.append(" select " +
		 		   " count(jbCommittee.jbCommitteeId), " +
		 		   " jbCommittee.isCommitteeConfirmed, " +
		 		   " jbCommittee.startDate, " +
		 		   " jbCommittee.completedDate, " +
		 		   " jbCommittee.jbCommitteeLevel.jbCommitteeLevelId," +
		 		   " jbCommittee.jbCommitteeLevel.name," +
		 		   " jbCommittee.jbCommitteeStatus.jbCommitteeStatusId," +
		 		   " jbCommittee.jbCommitteeStatus.status" +
		 		   " from JbCommittee jbCommittee ");
		 if(type != null && type.equalsIgnoreCase("district"))
			 sb.append(" left join jbCommittee.userAddress.district district ");
		 else if(type != null && type.equalsIgnoreCase("constituency"))
			 sb.append(" left join jbCommittee.userAddress.constituency constituency ");
		 
		 sb.append(" where jbCommittee.userAddress.state.stateId = 1 " +
				   " and jbCommittee.isDeleted = 'N' ");
		 
		 if(type != null && type.equalsIgnoreCase("district"))
			 sb.append(" and district.districtId in (:locationIdsList) ");
		 else if(type != null && type.equalsIgnoreCase("constituency"))
			 sb.append(" and constituency.constituencyId in (:locationIdsList)");
		 
		 sb.append(" group by jbCommittee.jbCommitteeLevel.jbCommitteeLevelId," +
				   " jbCommittee.jbCommitteeStatus.jbCommitteeStatusId "); 	
		 
		 Query query = getSession().createQuery(sb.toString());
		 
		 if(type != null && (type.equalsIgnoreCase("district") || type.equalsIgnoreCase("constituency")))
			 query.setParameterList("locationIdsList", locationIdsList);
		
		 return query.list();
	 }
	public List<Object[]> getDistrictWiseCommitteeDetails(Date fromDate,Date endDate,String type,Set<Long> userAccessVals,Set<Long> ids){//ids for constituency ids in case of prliament
		StringBuilder sb = new StringBuilder();
		// 0 levelId,1 levelName,2 count,3 statusId,4 status 
		sb.append(" select model.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommitteeLevel.name,count(model.jbCommitteeId)" +
				",model.jbCommitteeStatus.jbCommitteeStatusId,model.jbCommitteeStatus.status,model.jbCommitteeId " );
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" ,district.districtId,district.districtName,'','','','','','' ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(", constituency.constituencyId,constituency.name,constituency.district.districtId,constituency.district.districtName,'','','','' ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append("  ,parliamentConstituency.constituencyId,parliamentConstituency.name,'','','','','','' ");
		}else if(type.equalsIgnoreCase("mandal")){
			sb.append("  ,tehsil.tehsilId,tehsil.tehsilName,constituency.district.districtId,constituency.district.districtName, constituency.constituencyId,constituency.name,'','' ");
		}else if(type.equalsIgnoreCase("panchayat")){
			sb.append("  ,panchayat.panchayatId,panchayat.panchayatName,constituency.district.districtId,constituency.district.districtName, constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName ");
		 }
		
		sb.append(" ,model.jbCommitteeStatus.colour from  JbCommittee model   ");
		
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" left join model.userAddress.district district ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" left join  model.userAddress.constituency constituency ");
		}else if(type != null && type.equalsIgnoreCase("parliament")){
			sb.append(" left join  model.userAddress.parliamentConstituency parliamentConstituency ");
			if(ids != null && ids.size() >0)
				sb.append(" left join  model.userAddress.constituency constituency ");
		}else if(type != null && type.equalsIgnoreCase("mandal")){
			sb.append(" left join  model.userAddress.tehsil tehsil ");
			sb.append(" left join  model.userAddress.constituency constituency ");
		}else if(type != null && type.equalsIgnoreCase("panchayat")){
			sb.append(" left join  model.userAddress.tehsil tehsil ");
			sb.append(" left join  model.userAddress.panchayat panchayat ");
		
			sb.append(" left join  model.userAddress.constituency constituency ");
		
			
		}
		sb.append(" where model.isDeleted = 'N' ");
		
		if(type != null && type.equalsIgnoreCase("district") && userAccessVals != null && userAccessVals.size() >0){
			sb.append(" and district.districtId in (:userAccessVals)  ");
		}else if(type != null && type.equalsIgnoreCase("constituency") && userAccessVals != null && userAccessVals.size() >0){
			sb.append(" and constituency.constituencyId in (:userAccessVals) ");
		}else if(type != null && type.equalsIgnoreCase("parliament") && userAccessVals != null && userAccessVals.size() >0){
			sb.append(" and parliamentConstituency.constituencyId in (:userAccessVals) ");
			if(ids != null && ids.size() >0)
				sb.append(" and constituency.constituencyId in (:ids) ");
		}
		
		sb.append(" group by model.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommitteeStatus.jbCommitteeStatusId ");
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" , district.districtId ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" ,constituency.constituencyId ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" , model.userAddress.parliamentConstituency.constituencyId  ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(userAccessVals != null && userAccessVals.size() >0){
			query.setParameterList("userAccessVals", userAccessVals);
		}
		if(ids != null && ids.size() >0){
			query.setParameterList("ids", ids);
		}
		return query.list();
		
	}
	public List<Object[]> getLocationWiseCommitteeDetailsForCommitteeLvl(Date fromDate,Date endDate,Long levelId,Long levelVal,Long committeeLvlId,Long status,List<Long> matchedConstIdsForUserId){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.jbCommitteeId,model.committeeName " );
		
		
		sb.append(" from  JbCommittee model   ");
		
		if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 3l){
			sb.append(" left join model.userAddress.district district ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 4l){
			sb.append(" left join  model.userAddress.constituency constituency ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 10l){
			sb.append(" left join  model.userAddress.parliamentConstituency parliamentConstituency ");
			if(matchedConstIdsForUserId !=null && matchedConstIdsForUserId.size() >0)
				sb.append(" left join  model.userAddress.constituency constituency ");
		}
		sb.append(" where model.isDeleted = 'N' ");
		if(levelId != null && levelId.longValue()  == 3l && levelVal != null && levelVal.longValue() >0l ){
			sb.append(" and district.districtId = :levelVal ");
		}else if(levelId != null && levelId.longValue()  == 4l && levelVal != null && levelVal.longValue() >0l){
			sb.append(" and constituency.constituencyId = :levelVal ");
		}else if(levelId != null && levelId.longValue()  == 10l && levelVal != null && levelVal.longValue() >0l){
			sb.append("   and parliamentConstituency.constituencyId = :levelVal  ");
			if(matchedConstIdsForUserId !=null && matchedConstIdsForUserId.size() >0)
				sb.append(" and constituency.constituencyId in(:matchedConstIdsForUserId) ");
		}
		
		if(committeeLvlId != null && committeeLvlId.longValue() >0l ){
			sb.append("   and model.jbCommitteeLevel.jbCommitteeLevelId = :committeeLvlId  ");
		}
		
		if(status != null && status.longValue() > 0l){
			sb.append("   and model.jbCommitteeStatus.jbCommitteeStatusId = :status ");
		}
		sb.append(" order by model.jbCommitteeId asc ");	
		/*sb.append(" group by model.jbCommitteeLevel.jbCommitteeLevelId ");
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" , district.districtId ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" ,constituency.constituencyId ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" , model.userAddress.parliamentConstituency.constituencyId  ");
		}*/
		
		Query query = getSession().createQuery(sb.toString());
		if(levelVal != null && levelVal.longValue() >0l){
			query.setParameter("levelVal", levelVal);
		}
		if(committeeLvlId != null && committeeLvlId.longValue() >0l ){
			query.setParameter("committeeLvlId", committeeLvlId);
		}
		
		if(status != null && status.longValue() > 0l){
			query.setParameter("status", status);
		}
		if(matchedConstIdsForUserId !=null && matchedConstIdsForUserId.size() >0){
			query.setParameterList("matchedConstIdsForUserId", matchedConstIdsForUserId);
		}
		return query.list();
		
	}
	
	public List<Object[]> getJbCommitteeStatusByCommiteeId(Long committeeId){
		StringBuilder sb = new StringBuilder();
		    //0 jbCommitteeId,1 committeeName,2 isCommitteeConfirmed,3 startDate,4 completedDate
			sb.append("select model.jbCommitteeId,model.committeeName," );
			sb.append("model.isCommitteeConfirmed,model.startDate,model.completedDate ");
			sb.append(" from JbCommittee model ");
			sb.append("where model.jbCommitteeId = :committeeId");
		
		Query query = getSession().createQuery(sb.toString());
		if(committeeId != null && committeeId.longValue() >0l){
			query.setParameter("committeeId", committeeId);
		}
		return query.list();
	}
}
