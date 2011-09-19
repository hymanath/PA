package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICallTrackingProblemDAO;
import com.itgrids.partyanalyst.dto.CallTrackingVO;
import com.itgrids.partyanalyst.model.CallTrackingProblem;

public class CallTrackingProblemDAO extends GenericDaoHibernate<CallTrackingProblem, Long> implements ICallTrackingProblemDAO{
	
	public CallTrackingProblemDAO() {
		super(CallTrackingProblem.class);
	}
     public List<Object[]> getProblemDetailbyMobileNo(String mobile){
    	 return getHibernateTemplate().find("select model.problemPurpose,model.referenceNo,model.callTrackingDetail.name, "+
    			 "model.callTrackingDetail.mobile,model.callTrackingDetail.problemAddedDate,model.callTrackingDetail.villageOrTown "+
    			 "from CallTrackingProblem model where model.callTrackingDetail.mobile="+mobile);
     }
     public List<Object[]> getProblemDetailbyTodayDate(Date today){
    	 return getHibernateTemplate().find("select model.problemId,model.problemPurpose,model.referenceNo,model.callTrackingDetail.name, "+
    			 "model.callTrackingDetail.mobile,model.callTrackingDetail.problemAddedDate,model.callTrackingDetail.villageOrTown "+
    			 "from CallTrackingProblem model where date(model.callTrackingDetail.problemAddedDate) = ? ",today);
     }
     public List<Object[]> getProblemDetailbyProblemId(Long problemId){
    	 return getHibernateTemplate().find("select model.problemId,model.problemPurpose,model.referenceNo,model.callTrackingDetail.name, "+
    			 "model.callTrackingDetail.mobile,model.callTrackingDetail.problemAddedDate,model.callTrackingDetail.villageOrTown "+
    			 "from CallTrackingProblem model where model.problemId = ? ",problemId);
     }
     
    public List<Object[]> searchCallTrackingProblem(CallTrackingVO callTrackingVO,Date addedDate)
     {
 		StringBuilder query = new StringBuilder();
 		query.append("select model.problemId,model.problemPurpose,model.referenceNo,model.callTrackingDetail.name, "+
   			 "model.callTrackingDetail.mobile,model.callTrackingDetail.problemAddedDate,model.callTrackingDetail.villageOrTown "+
			 "from CallTrackingProblem model where model.problemId != null");
 
 		if(callTrackingVO.getName()!= null && callTrackingVO.getName().trim().length()>0)
 			query.append("  and model.callTrackingDetail.name = :name");
 		if(callTrackingVO.getMobile()!= null && callTrackingVO.getMobile().trim().length()>0)
 			query.append("  and model.callTrackingDetail.mobile = :mobile");	
 		if(callTrackingVO.getProblemPurpose()!= null && callTrackingVO.getProblemPurpose().trim().length()>0)
 			query.append("  and model.problemPurpose = :problemPurpose");	
 		if(callTrackingVO.getReferenceNo()!= null && callTrackingVO.getReferenceNo().trim().length()>0)
 			query.append("  and model.referenceNo = :referenceNo");	
 		if(callTrackingVO.getVillageOrTown()!= null && callTrackingVO.getVillageOrTown().trim().length()>0)
 			query.append("  and model.callTrackingDetail.villageOrTown = :villageOrTown");
 		if(addedDate!= null)
 			query.append("  and date(model.callTrackingDetail.problemAddedDate) = :addedDate");
 		
 		
 		Query queryObject = getSession().createQuery(query.toString());
 		
 		
 		if(callTrackingVO.getName()!= null && callTrackingVO.getName().trim().length()>0)
 		   queryObject.setString("name", callTrackingVO.getName());
 		if(callTrackingVO.getMobile()!= null && callTrackingVO.getMobile().trim().length()>0)
 		   queryObject.setString("mobile", callTrackingVO.getMobile());
 		if(callTrackingVO.getProblemPurpose()!= null && callTrackingVO.getProblemPurpose().trim().length()>0)
 		   queryObject.setString("problemPurpose", callTrackingVO.getProblemPurpose());
 		if(callTrackingVO.getReferenceNo()!= null && callTrackingVO.getReferenceNo().trim().length()>0)
  		   queryObject.setString("referenceNo", callTrackingVO.getReferenceNo());
 		if(callTrackingVO.getVillageOrTown()!= null && callTrackingVO.getVillageOrTown().trim().length()>0)
   		   queryObject.setString("villageOrTown", callTrackingVO.getVillageOrTown());
 		if(addedDate!= null)
  		   queryObject.setDate("addedDate", addedDate);
  		
 		
 	  return	queryObject.list();
 	}
 	
}
