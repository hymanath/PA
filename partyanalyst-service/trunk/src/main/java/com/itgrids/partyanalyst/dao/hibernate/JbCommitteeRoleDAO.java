package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IJbCommitteeRoleDAO;
import com.itgrids.partyanalyst.model.JbCommitteeRole;

public class JbCommitteeRoleDAO extends GenericDaoHibernate<JbCommitteeRole, Long> implements IJbCommitteeRoleDAO {

	public JbCommitteeRoleDAO() {
		super(JbCommitteeRole.class);
		
	}
	
 public List<Object[]> getDesignationsIdsByCommitteeId(Long committeeId){
	 StringBuilder sb = new StringBuilder();
	   //0 jbCommitteeRoleId,1 jbMemberTypeId,2 memberType, 3 maxMembers
		sb.append("SELECT model.jbCommitteeRoleId,model.jbMemberType.jbMemberTypeId,model.jbMemberType.memberType,model.maxMembers ");
		sb.append("from JbCommitteeRole model ");
		sb.append("where model.jbCommittee.jbCommitteeId =:committeeId and model.jbCommittee.jbCommitteeId.isDeleted ='N' ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("committeeId", committeeId);
		
		return query.list();
 }
 public  List<Object[]> getCommitteeWiseTotalMemberCount(String type,Set<Long> userAccessVals){
	 StringBuilder sb = new StringBuilder();
	 //0 committeeLeveId,1 level name,2 committeeId,3 maxMemebers
      sb.append("select model.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommittee.jbCommitteeLevel.name,model.jbCommittee.jbCommitteeId,sum(model.maxMembers) ");
      if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" ,district.districtId,district.districtName ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(", constituency.constituencyId,constituency.name ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append("  ,parliamentConstituency.constituencyId,parliamentConstituency.name ");
		}
      sb.append(" from JbCommitteeRole model ");

		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" left join model.jbCommittee.userAddress.district district ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" left join  model.jbCommittee.userAddress.constituency constituency ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" left join  model.jbCommittee.userAddress.parliamentConstituency parliamentConstituency ");
		}
		
		if(type != null && type.equalsIgnoreCase("district") && userAccessVals != null && userAccessVals.size() >0){
			sb.append(" where  district.districtId in (:userAccessVals)  ");
		}else if(type != null && type.equalsIgnoreCase("constituency") && userAccessVals != null && userAccessVals.size() >0){
			sb.append(" where constituency.constituencyId in (:userAccessVals) ");
		}
		
      sb.append(" group by model.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommittee.jbCommitteeId ");
      if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" , district.districtId ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" ,constituency.constituencyId ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" ,parliamentConstituency.constituencyId  ");
		}
      Query query = getSession().createQuery(sb.toString());
      if(userAccessVals != null && userAccessVals.size() >0){
			query.setParameterList("userAccessVals", userAccessVals);
		}
      return query.list();
 }
 public  List<Object[]> getCommitteeLvlWiseTotalMemberCountInLocation(Date fromDate,Date endDate  ,Long levelId,Long levelVal, Long committeeLvlId){
	 StringBuilder sb = new StringBuilder();
	 //0 committeeLeveId,1 level name,2 committeeId,3 maxMemebers
      sb.append("select model.jbCommittee.jbCommitteeId,model.jbCommittee.committeeName,sum(model.maxMembers)   ");
      /*if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 3l){
			sb.append(" left join model.userAddress.district district ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 4l){
			sb.append(" left join  model.userAddress.constituency constituency ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 10l){
			sb.append(" left join  model.userAddress.parliamentConstituency parliamentConstituency ");
		}*/
      sb.append(" from JbCommitteeRole model ");
      if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 3l){
			sb.append(" left join model.jbCommittee.userAddress.district district ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 4l){
			sb.append(" left join  model.jbCommittee.userAddress.constituency constituency ");
		}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 10l){
			sb.append(" left join  model.jbCommittee.userAddress.parliamentConstituency parliamentConstituency ");
		}
      sb.append(" where model.isDeleted='N' ");
      if(levelId != null && levelId.longValue()  == 3l && levelVal != null && levelVal.longValue() >0l ){
			sb.append(" and district.districtId = :levelVal ");
		}else if(levelId != null && levelId.longValue()  == 4l && levelVal != null && levelVal.longValue() >0l){
			sb.append(" and constituency.constituencyId = :levelVal ");
		}else if(levelId != null && levelId.longValue()  == 10l && levelVal != null && levelVal.longValue() >0l){
			sb.append("   and parliamentConstituency.constituencyId = :levelVal  ");
		}
      if(committeeLvlId != null && committeeLvlId.longValue() >0l ){
			sb.append("   and model.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId = :committeeLvlId  ");
		}
      sb.append(" group by model.jbCommittee.jbCommitteeId ");
      /*if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" , district.districtId ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" ,constituency.constituencyId ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" ,parliamentConstituency.constituencyId  ");
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
 
 public Long getTotalRoleMemberCountByCommitteId(Long committeeId){
	 StringBuilder sb = new StringBuilder();
		sb.append("SELECT sum(model.maxMembers) ");
		sb.append("from JbCommitteeRole model ");
		sb.append("where model.jbCommittee.jbCommitteeId =:committeeId and model.jbCommittee.jbCommitteeId.isDeleted ='N' ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("committeeId", committeeId);
		
		return (Long)query.uniqueResult();
 }
}
