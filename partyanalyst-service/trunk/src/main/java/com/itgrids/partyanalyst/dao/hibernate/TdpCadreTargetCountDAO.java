package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.model.TdpCadreTargetCount;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreTargetCountDAO extends GenericDaoHibernate<TdpCadreTargetCount, Long> implements ITdpCadreTargetCountDAO {

	public TdpCadreTargetCountDAO() {
		super(TdpCadreTargetCount.class);
	}
	public List<Object[]> getTotalCadreTargetCountLocationWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId){
		
		StringBuilder queryStr = new StringBuilder();
	        queryStr.append(" select");
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append(" model.constituency.state.stateId,");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" model.constituency.district.districtId,");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	          queryStr.append(" model.parliamentConstituency.constituencyId, ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" model.constituency.constituencyId, ");  
		  }
		   queryStr.append(" sum(model.targetCount) " +
				" from " +
				" TdpCadreTargetCount model where model.enrollmentYear.enrollmentYearId=4 ");
		   if(stateId != null && stateId.longValue() > 0){
				 if(stateId != null && stateId.longValue() > 0){
					   if(stateId.longValue()==1l){
							queryStr.append(" and  model.constituency.district.districtId > 10 and  model.constituency.state.stateId = 1 ");
						}else if(stateId.longValue()==36l){
							queryStr.append(" and  model.constituency.district.districtId < 11 ");
						}
				 } 
		   }
		 
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	         queryStr.append(" group by model.constituency.state.stateId ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	             queryStr.append(" group by model.constituency.district.districtId ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	             queryStr.append(" group by model.parliamentConstituency.constituencyId ");  
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	          queryStr.append(" group by model.constituency.constituencyId ");  
		  }
		   Query query = getSession().createQuery(queryStr.toString());
	
		   return query.list();
	}
}
