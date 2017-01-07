package com.itgrids.cardprint.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

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
  			
  		}catch(Exception e){
  			LOG.error("Exception Occurred At execute() in CardPrintAdminAction class",e) ;
  		}
  		return Action.SUCCESS;
  	}

			
}
