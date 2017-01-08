package com.itgrids.cardprint.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.cardprint.dto.PrintStatusVO;
import com.itgrids.cardprint.dto.UserVO;
import com.itgrids.cardprint.service.ICardPrintAdminService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CardPrintAdminAction extends ActionSupport implements ServletRequestAware {
	
	  private static final Logger LOG = Logger.getLogger(CardPrintAdminAction.class);
	
	  //Member Variables
	  private HttpServletRequest request;
	  private String task;
	  private JSONObject jobj;
	  private List<PrintStatusVO> printStatusList;
	  private Long cardPrintVendorId;
	  
	  //Attributes
	  private ICardPrintAdminService cardPrintAdminService;
	  
     //Implementation Methods
 	  public void setServletRequest(HttpServletRequest request) {
		this.request = request;
      }
		
	  //setters and getters	
	  public String getTask() {
		return task;
	  }
	
	  public void setTask(String task) {
	     this.task = task;
	  }
	
	  public JSONObject getJobj() {
		 return jobj;
	  }
	
      public void setJobj(JSONObject jobj) {
		 this.jobj = jobj;
	  }
		
      public void setCardPrintAdminService(
			ICardPrintAdminService cardPrintAdminService) {
		this.cardPrintAdminService = cardPrintAdminService;
	  }
      
	    public List<PrintStatusVO> getPrintStatusList() {
			return printStatusList;
		}
	
		public void setPrintStatusList(List<PrintStatusVO> printStatusList) {
			this.printStatusList = printStatusList;
		}
		
	
	     public Long getCardPrintVendorId() {
			return cardPrintVendorId;
		}

		public void setCardPrintVendorId(Long cardPrintVendorId) {
			this.cardPrintVendorId = cardPrintVendorId;
		}
		//Request Handling Methods
	public String execute(){
          try{
  			
  			HttpSession session = request.getSession();
  			UserVO user = (UserVO) session.getAttribute("USER");
  			if(user == null || user.getUserId() == null){
  				return Action.ERROR;
  			}
  			//entitlements using userType.
  			if(!(user.getUserType() != null && user.getUserType().equalsIgnoreCase("Print Vendor"))){
  				return "entitlementError";
  			}
  			
  			cardPrintVendorId = cardPrintAdminService.getPrintVendorIdByLoggedInUser(user.getUserId());
  			
  		}catch(Exception e){
  			LOG.error("Exception Occurred At execute() in CardPrintAdminAction class",e) ;
  		}
  		return Action.SUCCESS;
  	}
	public String getPrintStatusWiseConstitCountByLoggedUser(){
		try{ 
			  jobj = new JSONObject(getTask());
			  Long printVendorId  = jobj.getLong("printVendorId");
			     
			  printStatusList = cardPrintAdminService.getPrintStatusWiseConstitCountByLoggedUser(printVendorId);
			 
		}catch(Exception e){
			LOG.error("Exception Occurred At getPrintStatusWiseConstitCountByLoggedUser() in CardPrintAdminAction class",e) ;
		}
		return Action.SUCCESS;
	}
	public String getPrintStatusWiseRecordCountByLoggedUSer(){
		try{ 
			  jobj = new JSONObject(getTask());
			  Long printVendorId  = jobj.getLong("printVendorId");
			     
			  printStatusList = cardPrintAdminService.getPrintStatusWiseRecordCountByLoggedUSer(printVendorId);
			 
		}catch(Exception e){
			LOG.error("Exception Occurred At getPrintStatusWiseRecordCountByLoggedUSer() in CardPrintAdminAction class",e) ;
		}
		return Action.SUCCESS;
	}	
	
	public String constWisePrintStatusWiseRecordCountByLoggedUSer(){
		try{ 
			  jobj = new JSONObject(getTask());
			  Long printVendorId  = jobj.getLong("printVendorId");
			     
			  printStatusList = cardPrintAdminService.constWisePrintStatusWiseRecordCountByLoggedUSer(printVendorId);
			 
		}catch(Exception e){
			LOG.error("Exception Occurred At constWisePrintStatusWiseRecordCountByLoggedUSer() in CardPrintAdminAction class",e) ;
		}
		return Action.SUCCESS;
	}
	
	public String getPrintStatusWiseConstitCount(){
		try{     
			  printStatusList = cardPrintAdminService.getPrintStatusWiseConstitCount();
			 
		}catch(Exception e){
			LOG.error("Exception Occurred At getPrintStatusWiseConstitCount() in CardPrintAdminAction class",e) ;
		}
		return Action.SUCCESS;
	}
	public String getPrintStatusWiseRecordCount(){
		try{     
			  printStatusList = cardPrintAdminService.getPrintStatusWiseRecordCount();
			 
		}catch(Exception e){
			LOG.error("Exception Occurred At getPrintStatusWiseRecordCount() in CardPrintAdminAction class",e) ;
		}
		return Action.SUCCESS;
	}
	public String constWisePrintStatusWiseRecordCount(){
		try{     
			  printStatusList = cardPrintAdminService.constWisePrintStatusWiseRecordCount();
			 
		}catch(Exception e){
			LOG.error("Exception Occurred At constWisePrintStatusWiseRecordCount() in CardPrintAdminAction class",e) ;
		}
		return Action.SUCCESS;
	}
}
