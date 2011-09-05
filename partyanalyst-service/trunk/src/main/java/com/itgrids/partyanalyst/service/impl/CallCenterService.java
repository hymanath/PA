package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dto.CallCenterVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.service.ICallCenterService;

public class CallCenterService implements ICallCenterService{

	private IProblemHistoryDAO problemHistoryDAO;
	
	private IRegistrationDAO registrationDAO;
	
	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
	}

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public void setProblemHistoryDAO(IProblemHistoryDAO problemHistoryDAO) {
		this.problemHistoryDAO = problemHistoryDAO;
	}

	public List<ProblemBeanVO> getProblemDetails(CallCenterVO callCenterVO) {
		
		List<ProblemBeanVO> problemBeanVOList =new ArrayList<ProblemBeanVO>();
		List<Object[]> result = new ArrayList<Object[]>();
		List<String> userIdsList = new ArrayList<String>();
		String hbQuery="";
		String name= callCenterVO.getName();
		String mobileNum = callCenterVO.getMobileNum();
		String refNum = callCenterVO.getRefNum();
		String emailId = callCenterVO.getEmailId();
		Date fromDate = callCenterVO.getFromDate();
		Date toDate = callCenterVO.getToDate();
		Long userId = callCenterVO.getUserId();
		try {
		//checking empty field for name 
		if(!name.equalsIgnoreCase("")){
			hbQuery= "model.problemLocation.problemAndProblemSource.problemExternalSource.name like '%"+name+"%' or " +
					"model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.firstName like '%"+name+"%' " +
							"or  model1.cadre.middleName like '%"+name+"%' or model1.cadre.lastName like '%"+name+"%')" ;
		}				
		
		//checking empty field for mobile 
		if(!mobileNum.equalsIgnoreCase("")){
			if(name.equalsIgnoreCase("")){
				hbQuery = "model.problemLocation.problemAndProblemSource.problemExternalSource.mobile ="+mobileNum+""+
						" or model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.mobile ="+mobileNum+")";
			}
			else{
				hbQuery += "and model.problemLocation.problemAndProblemSource.problemExternalSource.mobile ="+mobileNum+""+
						" or model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.mobile ="+mobileNum+")";
			}
		}
		//checking empty field for referenceId
		 if(!refNum.equalsIgnoreCase("")){
			 if(name.equalsIgnoreCase("")&& mobileNum.equalsIgnoreCase("")){
				 hbQuery = "model.problemLocation.problemAndProblemSource.problem.referenceNo='"+refNum+"'";
			 }
			 else {
				 hbQuery += "and model.problemLocation.problemAndProblemSource.problem.referenceNo='"+refNum+"'";
			 }
		}
		 //checking empty field for EmailId
		 if(!emailId.equalsIgnoreCase("")){
			 if(name.equalsIgnoreCase("")&& mobileNum.equalsIgnoreCase("")&&refNum.equalsIgnoreCase("")){
				 hbQuery = "model.problemLocation.problemAndProblemSource.problemExternalSource.email ='"+emailId+"' or" +
				 		" model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.email ='"+emailId+"')"; 
			 }
			 else{
				 hbQuery += " and model.problemLocation.problemAndProblemSource.problemExternalSource.email ='"+emailId+"' or" +
				 		" model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.email ='"+emailId+"')"; 
			 }
		 }
		 if(fromDate!=null && toDate !=null){
			 if(name.equalsIgnoreCase("")&& mobileNum.equalsIgnoreCase("") && refNum.equalsIgnoreCase("") && 
					 emailId.equalsIgnoreCase("")){
				 
				 hbQuery ="date(model.dateUpdated) >=? and date(model.dateUpdated) <=?";
						
					}
			 else{
				 hbQuery +=" and date(model.dateUpdated) >=? and date(model.dateUpdated) <=?";
			 }
		 }
		else{
			if(name.equalsIgnoreCase("")&& mobileNum.equalsIgnoreCase("") && refNum.equalsIgnoreCase("") && 
					 emailId.equalsIgnoreCase("")){			
			if(fromDate != null)
				hbQuery = "date(model.dateUpdated) =?";
			if(toDate != null)
				hbQuery ="date(model.dateUpdated) =?";
				
			 }
			else {
				if(fromDate != null)
					hbQuery += " and date(model.dateUpdated) =? ";
				if(toDate != null)
					hbQuery +=" and date(model.dateUpdated) =?";
			}
		 }
		 if(userId != null)
		 {
			 // Retrieving subUser and parentUser ids
			 /* List<Object[]> userIds = registrationDAO.getSubusersByParentUserId(userId);
			    if(userIds.size()!=0)
			    {
					 Object[] params = (Object[])userIds.get(0);
					 userIdsList.add(params[0].toString());
					 userIdsList.add(params[1].toString());
					 StringBuilder subUserIdAndparentIds = new StringBuilder();
					 subUserIdAndparentIds.append(params[0]).append(",").append(params[1]);
						 
					 hbQuery+=" and model.problemLocation.problemAndProblemSource.user.registrationId in("+subUserIdAndparentIds+")";
				}
		
			    else
			    {
					hbQuery+=" and model.problemLocation.problemAndProblemSource.user.registrationId in("+userId+")";
				}*/
			 
			 StringBuilder subUserIdAndparentIds = new StringBuilder();
			 subUserIdAndparentIds.append(userId.toString());http://localhost:8080/PartyAnalyst/images/icons/homePage_new/blue_header_top_left_login.jpg
			 hbQuery+=" and model.problemLocation.problemAndProblemSource.user.registrationId in("+subUserIdAndparentIds+")";
		 }
		 //checking for empty fields and executing query
		if(!name.equalsIgnoreCase("") ||!mobileNum.equalsIgnoreCase("") ||!refNum.equalsIgnoreCase("")||!emailId.equalsIgnoreCase("")||fromDate!=null || toDate!=null){
		 result = problemHistoryDAO.getCompleteProblemDetailsBySearchString(hbQuery,fromDate,toDate);
		}
		// Iterating result and setting values to VO
		for(Object[] params :result){
			ProblemBeanVO problemBeanVO =new ProblemBeanVO();
			problemBeanVO.setProblem(params[0].toString());
			problemBeanVO.setDescription(params[1].toString());
			problemBeanVO.setStatus(params[2].toString());
			problemBeanVO.setReportedDate(params[3].toString());
			problemBeanVO.setProblemPostedBy(params[4].toString());
			problemBeanVO.setImpactLevel(params[5].toString());
			problemBeanVO.setProblemHistoryId(new Long(params[6].toString()));
			problemBeanVOList.add(problemBeanVO);
		}
		return problemBeanVOList;
	 }
		catch(Exception e){
			e.printStackTrace();
			return problemBeanVOList;
		}
	}
}
