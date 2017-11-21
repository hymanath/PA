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
 
	public List<Object[]> getJbCommitteeStatusCount(){
			
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
 }
	public List<Object[]> getDistrictWiseCommitteeDetails(Date fromDate,Date endDate,String type,Set<Long> userAccessVals){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommitteeLevel.name,model.isCommitteeConfirmed," +
				"model.startDate,model.completedDate,model.jbCommitteeId " );
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" ,district.districtId,district.districtName ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(", constituency.constituencyId,constituency.name ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append("  ,parliamentConstituency.constituencyId,parliamentConstituency.name ");
		}
		
		sb.append(" from  JbCommittee model   ");
		
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" left join model.userAddress.district district ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" left join  model.userAddress.constituency constituency ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" left join  model.userAddress.parliamentConstituency parliamentConstituency ");
		}
		sb.append(" where model.isDeleted = 'N' ");
		
		if(type != null && type.equalsIgnoreCase("district") && userAccessVals != null && userAccessVals.size() >0){
			sb.append(" and district.districtId in (:userAccessVals)  ");
		}else if(type != null && type.equalsIgnoreCase("constituency") && userAccessVals != null && userAccessVals.size() >0){
			sb.append(" and constituency.constituencyId in (:userAccessVals) ");
		}
		
		/*sb.append(" group by model.jbCommitteeLevel.jbCommitteeLevelId ");
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" , district.districtId ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" ,constituency.constituencyId ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" , model.userAddress.parliamentConstituency.constituencyId  ");
		}*/
		
		Query query = getSession().createQuery(sb.toString());
		if(userAccessVals != null && userAccessVals.size() >0){
			query.setParameterList("userAccessVals", userAccessVals);
		}
		return query.list();
		
	}
	public List<Object[]> getLocationWiseCommitteeDetailsForCommitteeLvl(Date fromDate,Date endDate,Long levelId,Long levelVal,Long committeeLvlId,String status){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.jbCommitteeId,model.committeeName " );
		
		
		sb.append(" from  JbCommittee model   ");
		
		if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 3l){
			sb.append(" left join model.userAddress.district district ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 4l){
			sb.append(" left join  model.userAddress.constituency constituency ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 10l){
			sb.append(" left join  model.userAddress.parliamentConstituency parliamentConstituency ");
		}
		sb.append(" where model.isDeleted = 'N' ");
		if(levelId != null && levelId.longValue()  == 3l && levelVal != null && levelVal.longValue() >0l ){
			sb.append(" and district.districtId = :levelVal ");
		}else if(levelId != null && levelId.longValue()  == 4l && levelVal != null && levelVal.longValue() >0l){
			sb.append(" and constituency.constituencyId = :levelVal ");
		}else if(levelId != null && levelId.longValue()  == 10l && levelVal != null && levelVal.longValue() >0l){
			sb.append("   and parliamentConstituency.constituencyId = :levelVal  ");
		}
		
		if(committeeLvlId != null && committeeLvlId.longValue() >0l ){
			sb.append("   and model.jbCommitteeLevel.jbCommitteeLevelId = :committeeLvlId  ");
		}
		if(status != null && status.equalsIgnoreCase("Not Started")){
			sb.append("   and model.isCommitteeConfirmed = 'N' and model.startDate is null ");
		}else if(status != null && status.equalsIgnoreCase("Approved")){
			sb.append("   and model.isCommitteeConfirmed = 'Y' and model.completedDate is not null ");
		}
		
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
