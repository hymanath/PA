package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidate;

public class AppointmentCandidateDAO extends GenericDaoHibernate<AppointmentCandidate, Long> implements IAppointmentCandidateDAO {

	public AppointmentCandidateDAO() {
		super(AppointmentCandidate.class);
	}

	@Override
	public List<Object[]> getAppointmentCandidateDetails(Long candidateDsgntnId,Long appntmntPrrtyId,Long appntmntSttsId,Integer crrntMnth) {
		
		   StringBuilder queryStr=new StringBuilder();
		   queryStr.append("select model.appointmentCandidateId ," +//0
	      		          " model.name ," +//1
	      		          " model.mobileNo ," +//2
	      		          " model.candidateDesignation.designation," +//3
	      		          " model.userAddress.constituency.name ," +//4
	      		          " model.appointment.reason ," +//5
	      		          " model.appointment.appointmentPriority.priority ," +//6
	      		          " model.tdpCadreId " +//7
	      		          " from AppointmentCandidate model " +
	      		          " where model.candidateDesignation.appointmentCandidateDesignationId=:appointmentCandidateDesignationId " +
	      		          " and model.appointment.appointmentStatusId=:appointmentStatusId " +
	      		          " and model.appointment.appointmentPriorityId=:appointmenPriorityId ");
		   
				   if(crrntMnth!=null && crrntMnth>0l){
					   queryStr.append(" and month(model.appointment.insertedTime)=:crrntMnth");
				   }
				   
			        Query query=getSession().createQuery(queryStr.toString());
	        
		              query.setParameter("appointmentCandidateDesignationId", candidateDsgntnId);
		              query.setParameter("appointmentStatusId", appntmntSttsId);
		              query.setParameter("appointmenPriorityId", appntmntPrrtyId);
		           
		              if(crrntMnth!=null && crrntMnth>0l){
		            	  query.setParameter("crrntMnth", crrntMnth); 
		              }  
	     return query.list();
	}

	@Override
	public List<Object[]> getAppCandidatePreviousCountDetails(Long tdpCadreId,String mobileNO,Integer crrntMnth) {
		
		     StringBuilder queryStr=new StringBuilder();
		     
		      queryStr.append("select model.appointment.appointmentStatus.appointmentStatusId,model.appointment.appointmentStatus.status,count(model.appointment.appointmentStatus.appointmentStatusId) from AppointmentCandidate model ");
		     
		     if(tdpCadreId!=null && tdpCadreId>0l){
		       queryStr.append(" where model.tdpCadreId=:tdpCadreId");
		      }
		      if(mobileNO!=null && !mobileNO.isEmpty()){
		    	queryStr.append(" where model.mobileNo=:mobileNo ");  
		      }
		      
		      if(crrntMnth!=null && crrntMnth>0l){
				   queryStr.append(" and month(model.appointment.insertedTime)=:crrntMnth");
			   }
			   
		      queryStr.append(" group by model.appointment.appointmentStatus.appointmentStatusId");
		      
		      Query query=getSession().createQuery(queryStr.toString());
		      if(tdpCadreId!=null && tdpCadreId>0l){
		    	  query.setParameter("tdpCadreId", tdpCadreId);
		      }
		      if(mobileNO!=null && !mobileNO.isEmpty()){
			    	query.setParameter("mobileNo", mobileNO);
			  }
		      if(crrntMnth!=null && crrntMnth>0l){
            	  query.setParameter("crrntMnth", crrntMnth); 
              }  
		return query.list();
	}

	@Override
	public List<Object[]> getAppCandidatePreviousRequestedDetails(Long tdpCadreId, String mobileNO,Integer crrntMnth) {
		  StringBuilder queryStr=new StringBuilder();
		     
	      queryStr.append("select model.appointment.updatedTime,model.appointment.appointmentStatus.status from AppointmentCandidate model ");
	     
	     if(tdpCadreId!=null && tdpCadreId>0l){
	       queryStr.append(" where model.tdpCadreId=:tdpCadreId");
	      }
	      if(mobileNO!=null && !mobileNO.isEmpty()){
	    	queryStr.append(" where model.mobileNo=:mobileNo ");  
	      }
	      if(crrntMnth!=null && crrntMnth>0l){
			   queryStr.append(" and month(model.appointment.insertedTime)=:crrntMnth");
		   }
	      
	      Query query=getSession().createQuery(queryStr.toString());
	      
	      if(tdpCadreId!=null && tdpCadreId>0l){
	    	  query.setParameter("tdpCadreId", tdpCadreId);
	      }
	      if(mobileNO!=null && !mobileNO.isEmpty()){
		    	query.setParameter("mobileNo", mobileNO);
		  }
	      if(crrntMnth!=null && crrntMnth>0l){
        	  query.setParameter("crrntMnth", crrntMnth); 
          }  
		return query.list();
	}

	@Override
	public Object getMaxDate(Long tdpCadreId, String mobileNo,Integer crrntMnth) {
		
	      StringBuilder queryStr=new StringBuilder();
	   
	        queryStr.append("select max(model.appointment.updatedTime)  from AppointmentCandidate model");
	     
	        if(tdpCadreId!=null && tdpCadreId>0l){
			       queryStr.append(" where model.tdpCadreId=:tdpCadreId");
			 }
		    if(mobileNo!=null && !mobileNo.isEmpty()){
		    	queryStr.append(" where model.mobileNo=:mobileNo ");  
		     }
		   
		    if(crrntMnth!=null && crrntMnth>0l){
				   queryStr.append(" and month(model.appointment.insertedTime)=:crrntMnth");
			 }
		    
		    Query query=getSession().createQuery(queryStr.toString());
		   
		    if(tdpCadreId!=null && tdpCadreId>0l){
			  query.setParameter("tdpCadreId", tdpCadreId);
		    }
		    if(mobileNo!=null && !mobileNo.isEmpty()){
		      query.setParameter("mobileNo", mobileNo);	
		    }
		    
		    if(crrntMnth!=null && crrntMnth>0l){
	        	  query.setParameter("crrntMnth", crrntMnth); 
	          }  
		    
	    return query.uniqueResult();
	}

}
