package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;

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
			sb.append("model.memberName,model.mobileNo,mobileNo.isActive,model.status, ");
			sb.append(" voter.voterIDCardNo,casteCategory.casteCategoryId,casteCategory.categoryName,casteState.casteStateId," +
					" casteState.caste.casteName ");
			
			sb.append("from JbCommitteeMember model " +
					" left join model.tdpCadre tdpCadre " +
					" left join tdpCadre.voter  voter " +
					" left join model.casteCategory casteCategory" +
					" left join model.casteState casteState  ");
			sb.append("where ");
			sb.append(" model.jbCommitteeRole.jbCommitteeRoleId in(:roleIdsList) ");
			
			if(fromDate !=null && toDate !=null){
			sb.append("and (date(model.startDate) between :startDate and  :endDate ) ");
			}
			
			Query query = getSession().createQuery(sb.toString());
			
			query.setParameterList("roleIdsList", roleIdsList);
			if(fromDate !=null && toDate !=null){
		   		query.setDate("startDate", fromDate);
		   		query.setDate("endDate", toDate);
		   	}
			return query.list();
	 }
	 
	 public List<Object[]> getCommitteeWiseTotalMemberAddedCount(String type){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.jbCommitteeRole.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId," );
		 sb.append("model.jbCommitteeRole.jbCommittee.jbCommitteeId,count(model.jbCommitteeMemberId),model.jbCommitteeRole.jbCommittee.isCommitteeConfirmed," +
		 		"model.jbCommitteeRole.jbCommittee.startDate,model.jbCommitteeRole.jbCommittee.completedDate ");
		 if(type != null && type.equalsIgnoreCase("district")){
				sb.append(" ,district.districtId,district.districtName ");
			}else if(type != null && type.equalsIgnoreCase("constituency")){
				sb.append(", constituency.constituencyId,constituency.name ");
			}else if(type.equalsIgnoreCase("parliament")){
				sb.append("  ,parliamentConstituency.constituencyId,parliamentConstituency.name ");
			}
		 
		 sb.append(" from JbCommitteeMember model ");
		 if(type != null && type.equalsIgnoreCase("district")){
				sb.append(" left join model.jbCommitteeRole.jbCommittee.userAddress.district district ");
			}else if(type != null && type.equalsIgnoreCase("constituency")){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.constituency constituency ");
			}else if(type.equalsIgnoreCase("parliament")){
				sb.append(" left join  model.jbCommitteeRole.jbCommittee.userAddress.parliamentConstituency parliamentConstituency ");
			}
		 sb.append(" where model.jbCommitteeRole.jbCommittee.isDeleted = 'N'  ");
		 sb.append(" group by model.jbCommitteeRole.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommitteeRole.jbCommittee.jbCommitteeId ");
		 if(type != null && type.equalsIgnoreCase("district")){
				sb.append(" , district.districtId ");
			}else if(type != null && type.equalsIgnoreCase("constituency")){
				sb.append(" ,constituency.constituencyId ");
			}else if(type.equalsIgnoreCase("parliament")){
				sb.append(" , model.userAddress.parliamentConstituency.constituencyId  ");
			}
		 Query query = getSession().createQuery(sb.toString());
		 return query.list();
	 }
}
