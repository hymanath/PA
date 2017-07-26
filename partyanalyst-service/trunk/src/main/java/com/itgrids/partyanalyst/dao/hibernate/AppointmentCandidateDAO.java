package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAppointmentCandidateDAO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.model.AppointmentCandidate;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.utils.IConstants;

public class AppointmentCandidateDAO extends GenericDaoHibernate<AppointmentCandidate, Long> implements IAppointmentCandidateDAO {

	public AppointmentCandidateDAO() {
		super(AppointmentCandidate.class);
	}

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
				"          model.membershipId,model.voterIdCardNo,model.designationId,model.appointmentCandidateTypeId,model.imageURL " +
				"  from AppointmentCandidate model  left join model.userAddress.constituency constituency");
		if(searchType.equalsIgnoreCase("mobileno")){
			
			sb.append(" where model.mobileNo = :searchValue ");
			
		}else if(searchType.equalsIgnoreCase("mebershipno")){
			
			sb.append(" where model.membershipId = :searchValue ");
			
		}else if(searchType.equalsIgnoreCase("votercardno")){
			
			sb.append(" where model.voterIdCardNo = :searchValue ");
		}
		else if(searchType.equalsIgnoreCase("name"))
		{
			sb.append(" where model.name like '%"+searchValue+"%' ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(!searchType.equalsIgnoreCase("name"))
		query.setParameter("searchValue",searchValue);
		 if(searchType.equalsIgnoreCase("name"))
		 {
			 query.setFirstResult(0);
			 query.setMaxResults(100);
		 }
		return query.list();
	}
	
	public List<UserAddress> getUserWorkAddress(Long id){
		
		Query query=getSession().createQuery(" select ac.userAddress.userAddress from AppointmentCandidate ac where ac.appointmentCandidateId=:appointmentCandidateId ");
		query.setParameter("appointmentCandidateId",id);
		return query.list();
	}
	
public List<Object[]>  advancedSearchAppointmentRequestedMembersForPublicRepresentative(String searchType,LocationInputVO locationVo,LocationInputVO inputVo){
	StringBuilder str=new StringBuilder();
	str.append("select model.appointmentCandidateId,model.name,model.tdpCadreId,model.mobileNo," +
				" model.candidateDesignation.designation,constituency.name," +
				" model.membershipId,model.voterIdCardNo,model.designationId,model.appointmentCandidateTypeId,model.imageURL " +
				"  from PublicRepresentative model2,TdpCadreCandidate model1,AppointmentCandidate model  left join model.userAddress.constituency constituency");
		str.append(" where model2.candidate.candidateId = model1.candidate.candidateId and model.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId ");
		if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
			str.append(" and model2.publicRepresentativeType.publicRepresentativeTypeId in(:roles)");
		}
		if(locationVo != null)
		{
				if(locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)//State
				{
					str.append(" and model2.userAddress.state.stateId in (:locationStateIds) ");
				}
				if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)//District
				{
					str.append(" and model2.userAddress.district.districtId in(:districtIds) ");
				}
				if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)//Tehsil
				{
					str.append(" and model2.userAddress.tehsil.tehsilId in(:tehsilIds) ");
				}
				if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)//Village
				{
					str.append(" and model2.userAddress.panchayat.panchayatId in (:panchayatIds) ");
				}
				if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)//ward
				{
					str.append(" and model2.userAddress.ward.constituencyId in (:wardIds) ");
				}
				if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)//Town
				{
					str.append(" and model2.userAddress.localElectionBody.localElectionBodyId in(:townIds) ");
				}
				if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)//Divison
				{
					str.append(" and model2.userAddress.ward.constituencyId in(:divisonIds) ");
				}
				
		}
		Query query = getSession().createQuery(str.toString());
		if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
			query.setParameterList("roles", inputVo.getDesignationIds());
		}
		
		if(locationVo != null)
		{
			if(locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)
				query.setParameterList("locationStateIds", locationVo.getStateIdsList());
			if(locationVo.getDistrictIdsList() != null && locationVo.getDistrictIdsList().size() > 0)
				query.setParameterList("districtIds", locationVo.getDistrictIdsList());
			if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)
				query.setParameterList("tehsilIds", locationVo.getTehsilIdsList());
			if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)
				query.setParameterList("panchayatIds", locationVo.getVillageIdsList());
			if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)
				query.setParameterList("wardIds", locationVo.getWardIdsList());
			if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
				query.setParameterList("townIds", locationVo.getTownIdsList());
			if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)
				query.setParameterList("divisonIds", locationVo.getDivisionIdsList());
		}
		
		return query.list();
	}

public List<Object[]> advancedSearchAppointmentMembersForCadreCommittee(String searchType,LocationInputVO locationVo,String locationType,LocationInputVO inputVo){
	StringBuilder str=new StringBuilder();
	str.append("select model.appointmentCandidateId,model.name,model.tdpCadre.tdpCadreId,model.mobileNo," +
			" model.candidateDesignation.designation,constituency.name," +
			" model.membershipId,model.voterIdCardNo,model.designationId,model.appointmentCandidateTypeId,model.imageURL " +
			"  from TdpCommitteeMember TCM,AppointmentCandidate model  left join model.userAddress.constituency constituency");
	str.append(" where model.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId ");
	str.append(" and  model.tdpCadre.isDeleted = 'N' " +
				" and  model.tdpCadre.enrollmentYear = :enrollmentYear " +
				" and TCM.isActive = 'Y'" );
	 	if(locationVo != null) // For Location Filter
	 	{
				if( locationVo.getLevelId().equals(5l))//mandal,town,div
				{
					if(locationType.equalsIgnoreCase("mandal"))
					{
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:mandalLevelId  ");
					}
					else if(locationType.equalsIgnoreCase("town"))
					{
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:townLevelId  ");
					}
					
					else if(locationType.equalsIgnoreCase("division"))
					{
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:divisionLevelId ");
					}
					
					str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) ");
					
					
				}else if(locationVo.getLevelId().equals(6l))//village,ward
				{
					
					if(locationType.equalsIgnoreCase("panchayat"))
					{
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:panchayatLevelId  ");
					}
					else if(locationType.equalsIgnoreCase("ward"))
					{
						str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:wardLevelId  ");
					}
					str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) ");
					
					
				}else//State,District Level
				{
					str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelId =:levelId ");
					str.append(" and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevelValue in (:levelValues) ");
				}
		
	}		
	if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
		str.append(" and TCM.tdpCommitteeRole.tdpRolesId in  (:roles) ");
	}
	
	if(inputVo.getCommitteeId() !=null && inputVo.getCommitteeId()>0){
		str.append( " and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeId =:basicCommitteeId  ");
	}
	Query query = getSession().createQuery(str.toString());
	if(locationVo != null)
	{
			if(locationVo.getLevelId().equals(5l))//mandal,town,div
			{
				
				if(locationType.equalsIgnoreCase("mandal"))
				{
					query.setParameter("mandalLevelId", IConstants.MANDAL_LEVEL_ID);
					if(locationVo.getTehsilIdsList() != null && locationVo.getTehsilIdsList().size() > 0)
						query.setParameterList("levelValues", locationVo.getTehsilIdsList());
				}
				else if(locationType.equalsIgnoreCase("town"))
				{
					
					query.setParameter("townLevelId", IConstants.TOWN_LEVEL_ID);
					if(locationVo.getTownIdsList() != null && locationVo.getTownIdsList().size() > 0)
						query.setParameterList("levelValues", locationVo.getTownIdsList());
				}
				
				else if(locationType.equalsIgnoreCase("division"))
				{
					
					query.setParameter("divisionLevelId", IConstants.DIVISION_LEVEL_ID);
					if(locationVo.getDivisionIdsList() != null && locationVo.getDivisionIdsList().size() > 0)
						query.setParameterList("levelValues", locationVo.getDivisionIdsList());
				}
			
		}else if(locationVo.getLevelId().equals(6l))//village,ward
			{
				
				if(locationType.equalsIgnoreCase("panchayat"))
				{
					query.setParameter("panchayatLevelId", IConstants.VILLAGE_LEVEL_ID);
					if(locationVo.getVillageIdsList() != null && locationVo.getVillageIdsList().size() > 0)
						query.setParameterList("levelValues", locationVo.getVillageIdsList());
					
				}
				else if(locationType.equalsIgnoreCase("ward"))
				{
					query.setParameter("wardLevelId", IConstants.WARD_LEVEL_ID);
					if(locationVo.getWardIdsList() != null && locationVo.getWardIdsList().size() > 0)
						query.setParameterList("levelValues", locationVo.getWardIdsList());
					
				}
			
			}else//State,District Level
			{
				query.setParameter("levelId",locationVo.getLevelId());
				if(locationVo.getLevelId() == 10l && locationVo.getStateIdsList() != null && locationVo.getStateIdsList().size() > 0)
				{
					query.setParameterList("levelValues", locationVo.getStateIdsList());
				}
				else
					query.setParameterList("levelValues", locationVo.getDistrictIdsList());
				
			}
	}
	if(inputVo.getDesignationIds() !=null && inputVo.getDesignationIds().size()>0){
		query.setParameterList("roles", inputVo.getDesignationIds());
	}
	
	if(inputVo.getCommitteeId() !=null && inputVo.getCommitteeId()>0){
		query.setParameter("basicCommitteeId",inputVo.getCommitteeId() );
	}
	query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
	return query.list();
}

	public List<Object[]> getAppointmentCandidateIdForCadreIds(List<Long> cadreIds,Long aptUserID)
	{
		StringBuilder str = new StringBuilder();
		str.append("select model.tdpCadreId,model.appointmentCandidateId from AppointmentCandidate model "
				+ " where model.tdpCadreId in(:cadreIds) ");
		if(aptUserID != null && aptUserID > 0)
		{
			str.append(" and model.createdUser.userId = :aptUserID");
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("cadreIds", cadreIds);
		if(aptUserID != null && aptUserID > 0)
			query.setParameter("aptUserID", aptUserID);
		return query.list();
	}
	
	public List<AppointmentCandidate> getAppointmentCandidateObjByMemship(String membershipId){
		StringBuilder str = new StringBuilder();
		
		str.append(" select model from  AppointmentCandidate model " +
				" where " +
				" model.membershipId = :membershipId  ");
		
		Query query = getSession().createQuery(str.toString());		
		query.setParameter("membershipId", membershipId);	
		
		return query.list();
		
	}
	
	public List<Object[]>  getPublicRepresentativeWiseAppointmentCnt(List<Long> statusIds,String type,Long aptUserId){
			
		StringBuilder str=new StringBuilder();
		
		str.append("SELECT ");
		if(type.equalsIgnoreCase("unique"))
		str.append(" COUNT(DISTINCT model.appointmentCandidate.tdpCadreId),");
		else
			str.append(" COUNT(model.appointmentCandidate.tdpCadreId),");
		
		str.append(" model.appointmentCandidate.candidateDesignation.appointmentCandidateDesignationId," +
				"  model.appointmentCandidate.candidateDesignation.designation " +
				" ");
		
		str.append(" FROM AppointmentCandidateRelation model " +
				" WHERE model.appointment.isDeleted = 'N' " +
				" AND	model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId = 1 AND model.appointmentCandidate.tdpCadreId is NOT NULL " );
		
		if(statusIds != null && statusIds.size() > 0)
			str.append(" AND model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
		if(aptUserId !=null)
			str.append(" AND model.appointment.appointmentUser.appointmenUserId = :appointmenUserId ");
		
		str.append(" GROUP BY " +
				"		   model.appointmentCandidate.candidateDesignation.appointmentCandidateDesignationId " +
				" ORDER BY model.appointmentCandidate.candidateDesignation.orderNo ");
		
		/*str.append("model2.publicRepresentativeType.publicRepresentativeTypeId,"
				+ "model2.publicRepresentativeType.type  from PublicRepresentative model2,TdpCadreCandidate model1,AppointmentCandidateRelation model ");
		str.append(" where model2.candidate.candidateId = model1.candidate.candidateId and model.appointmentCandidate.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId ");
		if(statusIds != null && statusIds.size() > 0)
			str.append(" and model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
		if(aptUserId !=null)
			str.append(" and model.appointment.appointmentUser.appointmenUserId = :appointmenUserId ");
		str.append(" group by model2.publicRepresentativeType.type ");*/
		
		 Query query = getSession().createQuery(str.toString());
		 if(statusIds != null && statusIds.size() > 0)
			 query.setParameterList("statusIds", statusIds);
		 if(aptUserId !=null){
			 query.setParameter("appointmenUserId", aptUserId);
		 }
		 return query.list();
		}
	
	
	public List<Object[]>  getPublicRepresentativeWiseAppointmentMembers(List<Long> statusIds,String type,Long roleId,Long aptUserId){	
		
		StringBuilder str=new StringBuilder();
		str.append("SELECT ");
		if(type.equalsIgnoreCase("unique"))
		str.append(" DISTINCT model.appointmentCandidate.tdpCadreId,");
		else
			str.append("model.appointmentCandidate.tdpCadreId,");
		str.append("model.appointmentCandidate.name,model.appointmentCandidate.candidateDesignation.appointmentCandidateDesignationId," +
				" model.appointmentCandidate.candidateDesignation.designation,model.appointmentCandidate.mobileNo," +
				" model.appointmentCandidate.imageURL,model.appointmentCandidateId,0,0 ");
		
		str.append(" FROM AppointmentCandidateRelation model " +
				" WHERE model.appointment.isDeleted = 'N'" +
				" AND  model.appointmentCandidate.tdpCadreId is NOT NULL" +
				" AND  model.appointmentCandidate.appointmentCandidateType.appointmentCandidateTypeId = 1 ");
		
		if(statusIds != null && statusIds.size() > 0)
			str.append(" AND model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
		if(roleId != null && roleId > 0)
			str.append(" AND model.appointmentCandidate.candidateDesignation.appointmentCandidateDesignationId =:roleId ");
		
		if(aptUserId != null && aptUserId > 0)
			str.append(" AND model.appointment.appointmentUser.appointmenUserId = :appointmenUserId ");
		
		str.append(" ORDER BY model.appointmentCandidate.name ");
		
		Query query = getSession().createQuery(str.toString());
		 if(statusIds != null && statusIds.size() > 0)
			 query.setParameterList("statusIds", statusIds);
		 if(roleId != null && roleId > 0)
			 query.setParameter("roleId", roleId);
		 if(aptUserId != null && aptUserId > 0)
			 query.setParameter("appointmenUserId", aptUserId);
		 return query.list();
		
		/*StringBuilder str=new StringBuilder();
		str.append("select ");
		if(type.equalsIgnoreCase("unique"))
		str.append(" distinct model.appointmentCandidate.tdpCadreId,");
		else
			str.append("model.appointmentCandidate.tdpCadreId,");
		str.append("model.appointmentCandidate.name,model.appointmentCandidate.candidateDesignation.appointmentCandidateDesignationId," +
				"model.appointmentCandidate.candidateDesignation.designation,model.appointmentCandidate.mobileNo," +
				"model.appointmentCandidate.imageURL,model.appointmentCandidateId");
		str.append(",model2.publicRepresentativeType.publicRepresentativeTypeId,"
				+ "model2.publicRepresentativeType.type  from PublicRepresentative model2,TdpCadreCandidate model1," +
				" AppointmentCandidateRelation model ");
		str.append(" where model2.candidate.candidateId = model1.candidate.candidateId and model.appointmentCandidate.tdpCadre.tdpCadreId = model1.tdpCadre.tdpCadreId ");
		if(statusIds != null && statusIds.size() > 0)
			str.append(" and model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
		if(roleId != null && roleId > 0)
			str.append(" and model2.publicRepresentativeType.publicRepresentativeTypeId =:roleId ");
		if(aptUserId != null && aptUserId > 0)
			str.append(" and model.appointment.appointmentUser.appointmenUserId = :appointmenUserId ");
		Query query = getSession().createQuery(str.toString());
		 if(statusIds != null && statusIds.size() > 0)
			 query.setParameterList("statusIds", statusIds);
		 if(roleId != null && roleId > 0)
			 query.setParameter("roleId", roleId);
		 if(aptUserId != null && aptUserId > 0)
			 query.setParameter("appointmenUserId", aptUserId);
		 return query.list();*/	
		}
	
	
	public List<Object[]>  getCommitteeWiseAppointmentMembers(List<Long> statusIds,String type,Long roleId,Long aptUserId){
		StringBuilder str=new StringBuilder();
		str.append("select ");
		if(type.equalsIgnoreCase("unique"))
		str.append(" distinct model.appointmentCandidate.tdpCadreId,");
		else
		str.append("model.appointmentCandidate.tdpCadreId,");
		str.append("model.appointmentCandidate.name,model.appointmentCandidate.candidateDesignation.appointmentCandidateDesignationId," +
				"model.appointmentCandidate.candidateDesignation.designation,model.appointmentCandidate.mobileNo," +
				"model.appointmentCandidate.imageURL,model.appointmentCandidateId");
		str.append(" from TdpCommitteeMember TCM, AppointmentCandidateRelation model " +
				" where  model.appointmentCandidate.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId" +
				" and TCM.isActive = 'Y' ");
				if(statusIds != null && statusIds.size() > 0)
					str.append(" and model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
				if(roleId != null && roleId > 0)
				str.append(" and  TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = :roleId ");
				if(aptUserId != null && aptUserId > 0)
					str.append(" and model.appointment.appointmentUser.appointmenUserId = :appointmenUserId ");
		Query query = getSession().createQuery(str.toString());
		 if(statusIds != null && statusIds.size() > 0)
			 query.setParameterList("statusIds", statusIds);
		 if(roleId != null && roleId > 0)
			 query.setParameter("roleId", roleId);
		 if(aptUserId != null && aptUserId > 0)
			 query.setParameter("appointmenUserId", aptUserId);
		 return query.list();
		}
	public List<Object[]>  getCommitteeMemROleWiseAppointmentMembers(List<Long> statusIds,String type,Long roleId,Long aptUserId,Long levelId){
		StringBuilder str=new StringBuilder();
		str.append("select ");
		if(type.equalsIgnoreCase("unique"))
		str.append(" distinct model.appointmentCandidate.tdpCadreId,");
		else
		str.append("model.appointmentCandidate.tdpCadreId,");
		str.append("model.appointmentCandidate.name,model.appointmentCandidate.candidateDesignation.appointmentCandidateDesignationId," +
				"model.appointmentCandidate.candidateDesignation.designation,model.appointmentCandidate.mobileNo," +
				"model.appointmentCandidate.imageURL,model.appointmentCandidateId");
		str.append(" from TdpCommitteeMember TCM, AppointmentCandidateRelation model " +
				" where  model.appointmentCandidate.tdpCadre.tdpCadreId = TCM.tdpCadre.tdpCadreId and TCM.tdpCommitteeRole.tdpCommittee.tdpCommitteeLevel.tdpCommitteeLevelId = :levelId " +
				" AND TCM.isActive = 'Y'" +
				" AND model.appointmentCandidate.appointmentCandidateTypeId = 2 ");
				if(statusIds != null && statusIds.size() > 0)
					str.append(" and model.appointment.appointmentStatus.appointmentStatusId in(:statusIds) ");
				if(roleId != null && roleId > 0)
				str.append(" and  TCM.tdpCommitteeRole.tdpRoles.tdpRolesId = :roleId ");
				if(aptUserId != null && aptUserId > 0)
					str.append(" and model.appointment.appointmentUser.appointmenUserId = :appointmenUserId ");
		Query query = getSession().createQuery(str.toString());
		 if(statusIds != null && statusIds.size() > 0)
			 query.setParameterList("statusIds", statusIds);
		 if(roleId != null && roleId > 0)
			 query.setParameter("roleId", roleId);
		 if(aptUserId != null && aptUserId > 0)
			 query.setParameter("appointmenUserId", aptUserId);
		 query.setParameter("levelId",levelId);
		 return query.list();
		}
	public Long appointmntCandExist(String memberShipNo){
		StringBuilder sb = new StringBuilder();
		  sb.append("select model.appointmentCandidateId" +
		  		" from AppointmentCandidate model" +
		  		" where model.membershipId = :memberShipNo");
		  Query query = getSession().createQuery(sb.toString());
			query.setParameter("memberShipNo", memberShipNo);
			return (Long) query.uniqueResult();	
		
	}
	public Long appointmntCandExistForVtrId(String voterId){
		StringBuilder sb = new StringBuilder();
		  sb.append("select model.appointmentCandidateId" +
		  		" from AppointmentCandidate model" +
		  		" where model.voterIdCardNo = :voterId");
		  Query query = getSession().createQuery(sb.toString());
			query.setParameter("voterId", voterId);
			return (Long) query.uniqueResult();	
		
	}
	public Long todayAppointmentCandidateCount(Long userId,Date insertedDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		  sb.append("select count(distinct model.appointment.appointmentId)" +
		  		" from AppointmentCandidateRelation model" +
		  		" where model.appointment.createdBy = :userId and model.appointment.isDeleted='N' " +
		  		" and model.appointmentCandidate.tdpCadre.isDeleted = 'N' ");
		  if(insertedDate != null && endDate != null)
			  sb.append(" and ( date(model.appointment.insertedTime) between :endDate and :insertedDate) ");
		  sb.append(" group by model.appointment.createdBy ");
		  Query query = getSession().createQuery(sb.toString());
			query.setParameter("userId", userId);
			 if(insertedDate != null && endDate != null){
				 query.setDate("insertedDate", insertedDate); 
				 query.setDate("endDate", endDate); 
			 }
		return (Long) query.uniqueResult();	
	}
	public List<Object[]> appointmentCandidateDetails(Date fromDate,Date toDate,Long userId){
		StringBuilder sb = new StringBuilder();
		  sb.append("select model.appointmentCandidate.appointmentCandidateId,model.appointmentCandidate.name," +
		  		" d.designation,model.appointmentCandidate.imageURL,model.appointment.insertedTime " +
		  		" from AppointmentCandidateRelation model " +
		  		" left join model.appointmentCandidate.candidateDesignation d" +
		  		" where model.appointment.createdBy = :userId and model.appointment.isDeleted='N'" +
		  		" and model.appointmentCandidate.tdpCadre.isDeleted = 'N' ");
		  if(fromDate != null && toDate != null)
			  sb.append(" and ( date(model.appointment.insertedTime) between :fromDate and :toDate) ");
		  
		  sb.append(" order by model.appointment.insertedTime desc" );
		  
		  Query query = getSession().createQuery(sb.toString());
			if(fromDate != null && toDate != null){
				query.setDate("fromDate", fromDate); 
			    query.setDate("toDate", toDate);
			 }
			query.setParameter("userId", userId);
			//sb.append(" group by model.createdBy ");
		return query.list();
		
	}
	public Long getAppointmentCandUniqueCount(Long userId,Date fromDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		  sb.append("select " +
		  		" count(distinct model.appointmentCandidateId)" +
		  		" from AppointmentCandidateRelation model" +
		  		" where model.appointment.createdBy = :userId " +
		  		" and model.appointment.isDeleted='N' " +
		  		" and model.appointmentCandidate.tdpCadre.isDeleted = 'N' ");
		  
		  if(fromDate != null && endDate != null)
			  sb.append(" and date(model.appointment.insertedTime) between :endDate and :fromDate ");
		  
		  Query query = getSession().createQuery(sb.toString());
			query.setParameter("userId", userId);
			 if(fromDate != null && endDate != null){
				 query.setDate("fromDate", fromDate); 
				 query.setDate("endDate", endDate); 
			 }
		return (Long) query.uniqueResult();	
	}
	public Long todayAppointmentCandUniqueCount(Long userId,Date insertedDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		  sb.append("select count(distinct model.appointmentCandidateId)" +
		  		" from AppointmentCandidateRelation model" +
		  		" where model.appointment.createdBy = :userId and model.appointment.isDeleted='N' " +
		  		" and model.appointmentCandidate.tdpCadre.isDeleted = 'N' ");
		  if(insertedDate != null && endDate != null)
			  sb.append(" and ( date(model.appointment.insertedTime) between :endDate and :insertedDate) ");
		  
		  Query query = getSession().createQuery(sb.toString());
			query.setParameter("userId", userId);
			 if(insertedDate != null && endDate != null){
				 query.setDate("insertedDate", insertedDate); 
				 query.setDate("endDate", endDate); 
			 }
		return (Long) query.uniqueResult();	
	}
}
