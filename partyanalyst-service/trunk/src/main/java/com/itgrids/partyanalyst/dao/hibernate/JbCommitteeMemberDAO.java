package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IJbCommitteeMemberDAO;
import com.itgrids.partyanalyst.model.JbCommitteeMember;

public class JbCommitteeMemberDAO extends GenericDaoHibernate<JbCommitteeMember, Long> implements
		IJbCommitteeMemberDAO {

	public JbCommitteeMemberDAO() {
		super(JbCommitteeMember.class);
		
	}
	 public List<Object[]> getCommitteMemebersByRoleIds(List<Long> roleIdsList,Date fromDate,Date toDate){
		 StringBuilder sb = new StringBuilder();
		   //0 jbCommitteeMemberId,1 jbMemberTypeId,2 memberName, 3 mobileNo,4 isActive,5 status,6 voterIDCardNo
			sb.append("SELECT model.jbCommitteeMemberId,model.jbCommitteeRole.jbCommitteeRoleId , ");
			sb.append("model.memberName,model.mobileNo, model.isActive,model.status, ");
			sb.append(" voter.voterIDCardNo,casteCategory.casteCategoryId,casteCategory.categoryName,casteState.casteStateId," +
					" caste.casteName,party.partyId,party.shortName,tdpCadre.memberShipNo,voter.voterId,model.imagePath ");
			
			sb.append("from JbCommitteeMember model " +
					" left join model.tdpCadre tdpCadre " +
					" left join model.voter  voter " +
					" left join model.casteCategory casteCategory" +
					" left join model.casteState casteState " +
					" left join casteState.caste caste " +
					"left join model.party party ");
			if(roleIdsList != null && roleIdsList.size() >0)
			sb.append(" where model.jbCommitteeRole.jbCommitteeRoleId in(:roleIdsList) ");
			
			if(fromDate !=null && toDate !=null){
			sb.append("and (date(model.startDate) between :startDate and  :endDate ) ");
			}
			
			Query query = getSession().createQuery(sb.toString());
			
			if(fromDate !=null && toDate !=null){
		   		query.setDate("startDate", fromDate);
		   		query.setDate("endDate", toDate);
		   	}
			if(roleIdsList != null && roleIdsList.size() >0)
				query.setParameterList("roleIdsList", roleIdsList);
			return query.list();
	 }
	 
	 public List<Object[]> getCommitteeWiseTotalMemberAddedCount(String type,Set<Long> userAccessVals){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.jbCommitteeRole.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId," );
		 sb.append("model.jbCommitteeRole.jbCommittee.jbCommitteeId,count(model.jbCommitteeMemberId),model.jbCommitteeRole.jbCommittee.isCommitteeConfirmed," +
		 		"model.jbCommitteeRole.jbCommittee.startDate,model.jbCommitteeRole.jbCommittee.completedDate ");
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
		 
		 sb.append(" from JbCommitteeMember model ");
		 if(type != null && type.equalsIgnoreCase("district")){
				sb.append(" left join model.jbCommitteeRole.jbCommittee.userAddress.district district ");
			}else if(type != null && type.equalsIgnoreCase("constituency")){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.constituency constituency ");
			}else if(type != null && type.equalsIgnoreCase("parliament")){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.parliamentConstituency parliamentConstituency ");
			}else if(type != null && type.equalsIgnoreCase("mandal")){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.tehsil tehsil ");
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.constituency constituency ");
			}else if(type != null && type.equalsIgnoreCase("panchayat")){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.tehsil tehsil ");
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.panchayat panchayat ");
				
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.constituency constituency ");
				
				
			}
		 sb.append(" where model.jbCommitteeRole.jbCommittee.isDeleted = 'N' and model.isActive='Y' ");
		 if(type != null && type.equalsIgnoreCase("district") && userAccessVals != null && userAccessVals.size() >0){
				sb.append(" and district.districtId in (:userAccessVals)  ");
			}else if(type != null && type.equalsIgnoreCase("constituency") && userAccessVals != null && userAccessVals.size() >0){
				sb.append(" and constituency.constituencyId in (:userAccessVals) ");
			}
		 sb.append(" group by model.jbCommitteeRole.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommitteeRole.jbCommittee.jbCommitteeId ");
		 if(type != null && type.equalsIgnoreCase("district")){
				sb.append(" , district.districtId ");
			}else if(type != null && type.equalsIgnoreCase("constituency")){
				sb.append(" ,constituency.constituencyId ");
			}else if(type.equalsIgnoreCase("parliament")){
				sb.append(" ,parliamentConstituency.constituencyId  ");
			}else if(type.equalsIgnoreCase("mandal")){
				sb.append("  ,tehsil.tehsilId");
			}else if(type.equalsIgnoreCase("panchayat")){
				sb.append("  ,panchayat.panchayatId ");
			 }
		 Query query = getSession().createQuery(sb.toString());
		 if(userAccessVals != null && userAccessVals.size() >0){
				query.setParameterList("userAccessVals", userAccessVals);
			}
		 return query.list();
	 }
	 
	 public List<Object[]> getCommitteeWiseTotalMemberAddedCount(Date fromDate,Date endDate,Long levelId,Long levelVal,Long committeeLvlId){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.jbCommitteeRole.jbCommittee.jbCommitteeId,model.jbCommitteeRole.jbCommittee.committeeName,count(model.jbCommitteeMemberId),model.jbCommitteeRole.jbCommittee.isCommitteeConfirmed," +
		 		"model.jbCommitteeRole.jbCommittee.startDate,model.jbCommitteeRole.jbCommittee.completedDate ");
		
		 sb.append(" from JbCommitteeMember model ");
		 if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 3l){
				sb.append(" left join model.jbCommitteeRole.jbCommittee.userAddress.district district ");
			}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 4l){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.constituency constituency ");
			}else if(levelId != null && levelId.longValue() > 0l && levelId.longValue() == 10l){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.parliamentConstituency parliamentConstituency ");
			}
		 
		 sb.append(" where model.jbCommitteeRole.jbCommittee.isDeleted = 'N' and model.isActive='Y' ");
		 if(committeeLvlId != null && committeeLvlId.longValue() >0l ){
			 sb.append(" and model.jbCommitteeRole.jbCommittee.jbCommitteeLevelId = :committeeLvlId "); 
		 }
		 if(levelId != null && levelId.longValue()  == 3l && levelVal != null && levelVal.longValue() >0l ){
				sb.append(" and district.districtId = :levelVal ");
			}else if(levelId != null && levelId.longValue()  == 4l && levelVal != null && levelVal.longValue() >0l){
				sb.append(" and constituency.constituencyId = :levelVal ");
			}else if(levelId != null && levelId.longValue()  == 10l && levelVal != null && levelVal.longValue() >0l){
				sb.append("   and parliamentConstituency.constituencyId = :levelVal  ");
			}
		sb.append(" group by model.jbCommitteeRole.jbCommittee.jbCommitteeId ");
		/*  if(type != null && type.equalsIgnoreCase("district")){
				sb.append(" , district.districtId ");
			}else if(type != null && type.equalsIgnoreCase("constituency")){
				sb.append(" ,constituency.constituencyId ");
			}else if(type.equalsIgnoreCase("parliament")){
				sb.append(" , parliamentConstituency.constituencyId  ");
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
	 
	 public Long getMemberApprovedCountByCommitteId(Long committeeId){
		 StringBuilder sb = new StringBuilder();
			sb.append("SELECT count(model.jbCommitteeMemberId) ");
			sb.append("from JbCommitteeMember model ");
			sb.append("where model.jbCommitteeRole.jbCommittee.jbCommitteeId =:committeeId and model.jbCommitteeRole.jbCommittee.isDeleted ='N' ");
			sb.append("and model.status ='F' and model.isActive = 'Y' ");
			Query query = getSession().createQuery(sb.toString());
			query.setParameter("committeeId", committeeId);
			
			return (Long)query.uniqueResult();
	 }
	 
	 public List<Object[]> getCommitteeMembersByCommiteeId(Long committeeId){
		 StringBuilder sb = new StringBuilder();
		 //0 jbCommitteeMemberId,1 jbCommitteeRoleId,2 memberName,3 voterIDCardNo,4 tdpCadreId
			 sb.append(" SELECT model.jbCommitteeMemberId,model.jbCommitteeRole.jbCommitteeRoleId, ");
			 sb.append("model.memberName, voter.voterIDCardNo,tdpCadre.tdpCadreId ");
			 sb.append("from JbCommitteeMember model " +
						" left join model.tdpCadre tdpCadre " +
						" left join model.voter  voter " );
			 sb.append("where ");
			 sb.append(" model.jbCommitteeRole.jbCommittee.jbCommitteeId = :committeeId and model.jbCommitteeRole.jbCommittee.isDeleted ='N'" +
			 		" and model.isActive='Y' ");
			 
			 Query query = getSession().createQuery(sb.toString());
			 	query.setParameter("committeeId", committeeId);
				
			return query.list();
	 }
	 
	 public List<JbCommitteeMember> updateMembersToRejectStatus(Long committeeId){
		 StringBuilder sb = new StringBuilder();
			sb.append(" select model from JbCommitteeMember model "); 
			sb.append("where model.jbCommitteeRole.jbCommittee.jbCommitteeId =:committeeId and model.jbCommitteeRole.jbCommittee.isDeleted ='N' and model.isActive='Y' ");
			Query query = getSession().createQuery(sb.toString());
			query.setParameter("committeeId", committeeId);
			
			return query.list();
	 }
}
