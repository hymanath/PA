package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.itgrids.partyanalyst.dao.IProblemHistoryDAO;
import com.itgrids.partyanalyst.dao.IUserProblemDAO;
import com.itgrids.partyanalyst.dto.CallCenterVO;
import com.itgrids.partyanalyst.dto.ProblemBeanVO;
import com.itgrids.partyanalyst.service.ICallCenterService;

public class CallCenterService implements ICallCenterService{

	private IProblemHistoryDAO problemHistoryDAO;
	private IUserProblemDAO userProblemDAO;
	
	
	
	public IUserProblemDAO getUserProblemDAO() {
		return userProblemDAO;
	}

	public void setUserProblemDAO(IUserProblemDAO userProblemDAO) {
		this.userProblemDAO = userProblemDAO;
	}

	public IProblemHistoryDAO getProblemHistoryDAO() {
		return problemHistoryDAO;
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
			/*hbQuery= "model.problemLocation.problemAndProblemSource.problemExternalSource.name like '%"+name+"%' or " +
					"model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.firstName like '%"+name+"%' " +
							"or  model1.cadre.middleName like '%"+name+"%' or model1.cadre.lastName like '%"+name+"%')" ;*/
			hbQuery=" and model.problem.externalSource.name like '%"+name+"%' " ;
		}				
		
		//checking empty field for mobile 
		if(!mobileNum.equalsIgnoreCase("")){
				/*hbQuery = "model.problemLocation.problemAndProblemSource.problemExternalSource.mobile ="+mobileNum+""+
						" or model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.mobile ="+mobileNum+")";*/
				hbQuery = "and model.problem.externalSource.mobile ="+mobileNum+"";
						
			/*else{
				hbQuery += "and model.problemLocation.problemAndProblemSource.problemExternalSource.mobile ="+mobileNum+""+
						" or model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.mobile ="+mobileNum+")";
				hbQuery += "and model.problem.externalSource.mobile ="+mobileNum+"";
						
			}*/
		}
		//checking empty field for referenceId
		 if(!refNum.equalsIgnoreCase("")){
			 
				 //hbQuery += "and model.problemLocation.problemAndProblemSource.problem.referenceNo='"+refNum+"'";
				 hbQuery += " and model.problem.referenceNo='"+refNum+"'";
		}
		 //checking empty field for EmailId
		 if(!emailId.equalsIgnoreCase("")){
			
				/* hbQuery = "model.problemLocation.problemAndProblemSource.problemExternalSource.email ='"+emailId+"' or" +
				 		" model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.email ='"+emailId+"')"; */
				 hbQuery = "model.problem.externalSource.email ='"+emailId+"'";
					 		
			 
			/* else{
				 hbQuery += " and model.problemLocation.problemAndProblemSource.problemExternalSource.email ='"+emailId+"' or" +
				 		" model.problemHistoryId in (from CadreProblemDetails model1 where model1.cadre.email ='"+emailId+"')"; 
				 hbQuery += " and model.problem.externalSource.email ='"+emailId+"' or" +
					 		" model.problem.problemId in (select model1.problem.problemId from CadreProblems model1 where model1.cadre.email ='"+emailId+"')";
			 }*/
		 }
		 if(fromDate!=null && toDate !=null){
			 
				 hbQuery +=" and date(model.updatedTime) >=? and date(model.updatedTime) <=?";
			
		 }
		else{
					
			if(fromDate != null)
				hbQuery = " and date(model.updatedTime) =?";
			if(toDate != null)
				hbQuery =" and date(model.updatedTime) =?";
			
		 }
		 if(userId != null)
		 {
			 StringBuilder subUserIdAndparentIds = new StringBuilder();
			 subUserIdAndparentIds.append(userId.toString());
			
			// hbQuery+=" and model.problemLocation.problemAndProblemSource.user.userId in("+subUserIdAndparentIds+")";
			 hbQuery+=" and model.user.userId in("+subUserIdAndparentIds+")";
		 }
		 //checking for empty fields and executing query
		if(!name.equalsIgnoreCase("") ||!mobileNum.equalsIgnoreCase("") ||!refNum.equalsIgnoreCase("")||!emailId.equalsIgnoreCase("")||fromDate!=null || toDate!=null){
		// result = problemHistoryDAO.getCompleteProblemDetailsBySearchString(hbQuery,fromDate,toDate);		
			result = userProblemDAO.getProblemDetailsByBetweenDates(hbQuery,fromDate,toDate);
		 
		 
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
			problemBeanVO.setName(params[7].toString());
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
