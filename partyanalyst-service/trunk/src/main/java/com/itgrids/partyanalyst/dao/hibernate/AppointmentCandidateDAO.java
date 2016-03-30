package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.model.AppointmentCandidate;
import com.itgrids.partyanalyst.model.UserAddress;

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
	      		          " and model.appointment.appointmentPriorityId=:appointmenPriorityId " +
	      		          " and model.appointment.isDeleted='N'");
		   
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
		     
		      queryStr.append("select model.appointment.appointmentStatus.appointmentStatusId,model.appointment.appointmentStatus.status,count(model.appointment.appointmentStatus.appointmentStatusId) from AppointmentCandidate model " +
		      		" where model.appointment.isDeleted='N'");
		     
		     if(tdpCadreId!=null && tdpCadreId>0l){
		       queryStr.append(" and model.tdpCadreId=:tdpCadreId");
		      }
		      if(mobileNO!=null && !mobileNO.isEmpty()){
		    	queryStr.append(" and model.mobileNo=:mobileNo ");  
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
		     
	      queryStr.append("select model.appointment.updatedTime,model.appointment.appointmentStatus.status from AppointmentCandidate model where model.appointment.isDeleted='N' ");
	     
	     if(tdpCadreId!=null && tdpCadreId>0l){
	       queryStr.append(" and model.tdpCadreId=:tdpCadreId");
	      }
	      if(mobileNO!=null && !mobileNO.isEmpty()){
	    	queryStr.append(" and model.mobileNo=:mobileNo ");  
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
	   
	        queryStr.append("select max(model.appointment.updatedTime)  from AppointmentCandidate model where model.appointment.isDeleted='N'");
	     
	        if(tdpCadreId!=null && tdpCadreId>0l){
			       queryStr.append(" and model.tdpCadreId=:tdpCadreId");
			 }
		    if(mobileNo!=null && !mobileNo.isEmpty()){
		    	queryStr.append(" and model.mobileNo=:mobileNo ");  
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
	
	
	public List<Object[]>  searchAppointmentRequestedMember(String searchType,String searchValue){
		
		StringBuilder sb=new StringBuilder();
		
		sb.append(" select model.appointmentCandidateId,model.name,model.tdpCadreId,model.mobileNo," +
				"          model.candidateDesignation.designation,constituency.name," +
				"          model.membershipId,model.voterIdCardNo " +
				"  from AppointmentCandidate model  left join model.userAddress.constituency constituency");
		if(searchType.equalsIgnoreCase("mobileno")){
			
			sb.append(" where model.mobileNo = :searchValue ");
			
		}else if(searchType.equalsIgnoreCase("mebershipno")){
			
			sb.append(" where model.membershipId = :searchValue ");
			
		}else if(searchType.equalsIgnoreCase("votercardno")){
			
			sb.append(" where model.voterIdCardNo = :searchValue ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("searchValue",searchValue);
		return query.list();
	}
	
	public List<UserAddress> getUserWorkAddress(Long id){
		
		Query query=getSession().createQuery(" select ac.userAddress.userAddress from AppointmentCandidate ac where ac.appointmentCandidateId=:appointmentCandidateId ");
		query.setParameter("appointmentCandidateId",id);
		return query.list();
	}
	
	
	

}
