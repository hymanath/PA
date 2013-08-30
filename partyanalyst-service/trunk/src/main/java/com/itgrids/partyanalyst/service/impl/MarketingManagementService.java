package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IDemoRequestActionsDAO;
import com.itgrids.partyanalyst.dao.IDemoRequestDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dto.DemoRequestVO;
import com.itgrids.partyanalyst.dto.EmailDetailsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.DemoRequest;
import com.itgrids.partyanalyst.model.DemoRequestActions;
import com.itgrids.partyanalyst.service.IMarketingManagementService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

 public class MarketingManagementService implements IMarketingManagementService{
	 
   private static final Logger LOG = Logger.getLogger(MarketingManagementService.class);
   private IDemoRequestDAO demoRequestDAO;
   private DateUtilService dateUtilService = new DateUtilService();
   private IDemoRequestActionsDAO demoRequestActionsDAO;
   private IUserDAO userDAO;
   
   public IDemoRequestDAO getDemoRequestDAO() {
	return demoRequestDAO;
   }

   public void setDemoRequestDAO(IDemoRequestDAO demoRequestDAO) {
	this.demoRequestDAO = demoRequestDAO;
   }

  public IDemoRequestActionsDAO getDemoRequestActionsDAO() {
	return demoRequestActionsDAO;
  }

   public void setDemoRequestActionsDAO(
		IDemoRequestActionsDAO demoRequestActionsDAO) {
	this.demoRequestActionsDAO = demoRequestActionsDAO;
   }

   public IUserDAO getUserDAO() {
	return userDAO;
   }

   public void setUserDAO(IUserDAO userDAO) {
 	this.userDAO = userDAO;
   }

 public ResultStatus saveDemoRequestData(EmailDetailsVO emailDetailsVO)
   {
		ResultStatus resultStatus = new ResultStatus();
		try{
		
			DemoRequest demoRequest = new DemoRequest();
			demoRequest.setAspirantName(emailDetailsVO.getCandidateName()!= null?emailDetailsVO.getCandidateName():"");
			demoRequest.setConstituency(emailDetailsVO.getConstituencyName()!= null?emailDetailsVO.getConstituencyName():"");
			demoRequest.setEmail(emailDetailsVO.getEmail()!=null?emailDetailsVO.getEmail():"");
			demoRequest.setMobileNo(emailDetailsVO.getMobile()!= null?emailDetailsVO.getMobile():"");
			demoRequest.setRequesteTime(dateUtilService.getCurrentDateAndTime());
			demoRequestDAO.save(demoRequest);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);	
		 return resultStatus;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in saveAndSendEmailToAdminGroup() method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}
  
  public List<DemoRequestVO> getDemoRequestData()
  {
	  List<DemoRequestVO> demoRequestVOsList = null;
	  try{
		
		  List<DemoRequest> list = demoRequestDAO.getDemoRequestList();
		  if(list != null && list.size() > 0)
		  {
			  demoRequestVOsList = new ArrayList<DemoRequestVO>(0);
			  for(DemoRequest demoRequest :list)
			  {
				  DemoRequestVO detailsVO = new DemoRequestVO();
				  detailsVO.setName(demoRequest.getAspirantName() != null ?demoRequest.getAspirantName():" ");
				  detailsVO.setConstituency(demoRequest.getConstituency() != null ?demoRequest.getConstituency():" ");
				  detailsVO.setEmail(demoRequest.getEmail() != null?demoRequest.getEmail():" ");
				  detailsVO.setMobileNo(demoRequest.getMobileNo()!= null?demoRequest.getMobileNo():" ");
				  detailsVO.setRequestedTime(demoRequest.getRequesteTime().toString().substring(0,16));
				  detailsVO.setId(demoRequest.getDemoRequestId());
				  detailsVO.setAssignedTo((demoRequest.getAssignedTo() != null&&!demoRequest.getAssignedTo().trim().equalsIgnoreCase(""))?demoRequest.getAssignedTo():"Not Assigned");
				  detailsVO.setCount(demoRequest.getDemoRequestActions()!= null?demoRequest.getDemoRequestActions().size():0);
				  demoRequestVOsList.add(detailsVO);
			  }
		  }
		 return demoRequestVOsList;
	  }catch (Exception e) {
		e.printStackTrace();
		LOG.error(" Exception Occured in getDemoRequestData() method, Exception - "+e);
		return null;
	}
	  
  }
  
  public DemoRequestVO getSelectedDemoRequestData(Long demoRequestId)
  {
	  DemoRequestVO demoRequestVO = null;
	  try{
		  DemoRequest demoRequest = demoRequestDAO.getDemoRequestByDemoRequestId(demoRequestId);
		  if(demoRequest == null)
		   return demoRequestVO;
		  
		  demoRequestVO = new DemoRequestVO();
		  demoRequestVO.setName(demoRequest.getAspirantName()!= null?demoRequest.getAspirantName():" ");
		  demoRequestVO.setConstituency(demoRequest.getConstituency()!= null?demoRequest.getConstituency():" ");
		  demoRequestVO.setEmail(demoRequest.getEmail()!= null?demoRequest.getEmail():" ");
		  demoRequestVO.setMobileNo(demoRequest.getMobileNo());
		  demoRequestVO.setAssignedTo(demoRequest.getAssignedTo() !=null?demoRequest.getAssignedTo():"");
		  
		  
		  List<Object[]> list = demoRequestActionsDAO.getDemoRequestActionsByDemoRequestId(demoRequestId);
		  
		  
		  if(list != null && list.size() > 0)
		  {
			 List<DemoRequestVO> demoRequestActionsList = new ArrayList<DemoRequestVO>(0);
			 Long SNO = 1L;
			 for(Object[] params:list)
			 {
				 DemoRequestVO requestVO = new DemoRequestVO();
				 requestVO.setType(params[0] !=null?params[0].toString():" ");
				 requestVO.setContent(params[1] != null?params[1].toString():"");
				 requestVO.setResponse(params[2] != null?params[2].toString():"");
				 requestVO.setRequestedTime(params[3] != null?params[3].toString().substring(0,16):"");
				 String name = "";
				 if(params[4] != null)
				  name = params[4].toString();
				 if(params[5] != null)
				  name +=" "+params[5].toString();
				 requestVO.setName(name);
				 requestVO.setId(SNO);
				 demoRequestActionsList.add(requestVO);
				 SNO ++;
			 }
			 
			
			 demoRequestVO.setDemoRequestVOList(demoRequestActionsList);
 		  }
		  
		  return demoRequestVO;
	  }catch (Exception e) {
		  e.printStackTrace();
		  LOG.error(" Exception Occured in getSelectedDemoRequestData() method, Exception -  "+e);
		  return demoRequestVO;
	}
  }
  
  public ResultStatus saveDemoRequestActionsData(Long demoRequestId,String type,String content,String response,Long userId)
  {
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		 DemoRequestActions demoRequestActions = new DemoRequestActions();
		 demoRequestActions.setActionTime(dateUtilService.getCurrentDateAndTime());
		 demoRequestActions.setContent(content != null?content:" ");
		 demoRequestActions.setResponse(response != null?response:" ");
		 demoRequestActions.setType(type != null?type:" ");
		 demoRequestActions.setDemoRequest(demoRequestDAO.get(demoRequestId));
		 demoRequestActions.setUser(userDAO.get(userId));
		 demoRequestActionsDAO.save(demoRequestActions);
		  
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);  
		return resultStatus;
	  }catch (Exception e) {
		  e.printStackTrace();
		  LOG.error(" Exception Occured in saveDemoRequestActionsData() method, Exception - "+e);
		  resultStatus.setResultCode(ResultCodeMapper.FAILURE);  
		  return resultStatus;
	}
  }
  
  public List<SelectOptionVO> getDemoRequestActionTypes()
  {
	  List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
	  try{
		 String DemoRequestActions = IConstants.DEMO_REQUEST_ACTIONS;
		 String ActiontypeArr[] = DemoRequestActions.split(",");
		 for(String actionType:ActiontypeArr)
		 {
			SelectOptionVO selectOptionVO = new SelectOptionVO();
			selectOptionVO.setName(actionType);
			selectOptionVOList.add(selectOptionVO);
		 }
		 
		 return selectOptionVOList;
	  }catch (Exception e) {
		e.printStackTrace();
		LOG.error("Exception Occured in getDemoRequestActionTypes() method, Exception - "+e);
		return selectOptionVOList;
	}
  }
  
  public ResultStatus deleteDemoRequestData(Long demoRequestId)
  {
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		  Integer result = demoRequestDAO.deleteDemoRequestByDemoRequestId(demoRequestId);
		  if(result > 0)
		   resultStatus.setResultCode(ResultCodeMapper.SUCCESS);  
		  else
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
	  }catch (Exception e) {
		e.printStackTrace();
		LOG.error("ExceptionOccured in deleteDemoRequestData() method, Exception - "+e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);  
		return resultStatus;
	}
  }
  
  public ResultStatus upDateDemoRequestDetails(DemoRequestVO demoRequestVO)
  {
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		DemoRequest demoRequest = demoRequestDAO.get(demoRequestVO.getId());
		demoRequest.setAspirantName(demoRequestVO.getName() != null?demoRequestVO.getName():" ");
		demoRequest.setConstituency(demoRequestVO.getConstituency()!= null?demoRequestVO.getConstituency():" ");
		demoRequest.setEmail(demoRequestVO.getEmail()!= null?demoRequestVO.getEmail():" ");
		demoRequest.setMobileNo(demoRequestVO.getMobileNo()!= null?demoRequestVO.getMobileNo():" ");
		demoRequest.setAssignedTo(demoRequestVO.getAssignedTo()!= null?demoRequestVO.getAssignedTo():"");
		demoRequest.setRequesteTime(dateUtilService.getCurrentDateAndTime());
		demoRequest.setUpdatedBy(demoRequestVO.getUserId());
		demoRequestDAO.save(demoRequest);
		
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		return resultStatus;
	  }catch (Exception e) {
	   e.printStackTrace();
	   LOG.error("Exception Occured in upDateDemoRequestDetails() method, Exception - "+e);
	   resultStatus.setResultCode(ResultCodeMapper.FAILURE);
	   return resultStatus;
	}
  }
  

}
