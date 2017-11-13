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
			sb.append("SELECT model.jbCommitteeMemberId,model.jbCommitteeRole.jbMemberType.jbMemberTypeId, ");
			sb.append("model.memberName,model.mobileNo,mobileNo.isActive,model.status, ");
			sb.append("model.tdpCadre.voter.voterIDCardNo ");
			
			sb.append("from JbCommitteeMember model ");
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
	 
	 public List<Object[]> getCommitteeWiseTotalMemberAddedCount(){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select model.jbCommitteeRole.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId," );
		 sb.append("model.jbCommitteeRole.jbCommittee.jbCommitteeId,count(model.jbCommitteeMemberId),model.jbCommitteeRole.jbCommittee.isCommitteeConfirmed," +
		 		"model.jbCommitteeRole.jbCommittee.startDate,model.jbCommitteeRole.jbCommittee.completedDate ");
		 sb.append("from JbCommitteeMember model where model.jbCommitteeRole.jbCommittee.isDeleted = 'N'  ");
		 sb.append(" group by model.jbCommitteeRole.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommitteeRole.jbCommittee.jbCommitteeId ");
		 Query query = getSession().createQuery(sb.toString());
		 return query.list();
	 }
}
