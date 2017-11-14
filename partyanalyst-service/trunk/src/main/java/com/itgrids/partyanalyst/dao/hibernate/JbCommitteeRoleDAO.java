package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;

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
		sb.append("where model.jbCommittee.jbCommitteeId =:committeeId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("committeeId", committeeId);
		
		return query.list();
 }
 public  List<Object[]> getCommitteeWiseTotalMemberCount(String type){
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
      sb.append(" group by model.jbCommittee.jbCommitteeLevel.jbCommitteeLevelId,model.jbCommittee.jbCommitteeId ");
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
