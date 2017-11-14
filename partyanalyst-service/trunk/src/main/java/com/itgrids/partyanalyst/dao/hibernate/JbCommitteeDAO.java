package com.itgrids.partyanalyst.dao.hibernate;


import java.util.Date;
import java.util.List;

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
	public List<Object[]> getDistrictWiseCommitteeDetails(Date fromDate,Date endDate,String type){
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
		
		/*sb.append(" group by model.jbCommitteeLevel.jbCommitteeLevelId ");
		if(type != null && type.equalsIgnoreCase("district")){
			sb.append(" , district.districtId ");
		}else if(type != null && type.equalsIgnoreCase("constituency")){
			sb.append(" ,constituency.constituencyId ");
		}else if(type.equalsIgnoreCase("parliament")){
			sb.append(" , model.userAddress.parliamentConstituency.constituencyId  ");
		}*/
		
		Query query = getSession().createQuery(sb.toString());
		return query.list();
		
	}
	
}
