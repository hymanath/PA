package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.DailyUpdatesVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.EntitlementsHelper;
import com.itgrids.partyanalyst.service.IPartyCandidateSpecialPageScheduleService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DailyUpdatesAction  extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5447494995035472724L;
	private HttpServletRequest request;
	private EntitlementsHelper entitlementsHelper;
    private IPartyCandidateSpecialPageScheduleService partyCandidateSpecialPageScheduleService;
    private String message;
    private List<SelectOptionVO> options;
    JSONObject jObj = null;
    private String task = null;
    
	public EntitlementsHelper getEntitlementsHelper() {
		return entitlementsHelper;
	}

	public void setEntitlementsHelper(EntitlementsHelper entitlementsHelper) {
		this.entitlementsHelper = entitlementsHelper;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public IPartyCandidateSpecialPageScheduleService getPartyCandidateSpecialPageScheduleService() {
		return partyCandidateSpecialPageScheduleService;
	}

	public void setPartyCandidateSpecialPageScheduleService(
			IPartyCandidateSpecialPageScheduleService partyCandidateSpecialPageScheduleService) {
		this.partyCandidateSpecialPageScheduleService = partyCandidateSpecialPageScheduleService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute(){
		HttpSession session = request.getSession();
		if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE))
			return INPUT;
		if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE))
			return ERROR;
		return Action.SUCCESS;
	}
	
	public String sendDailyUpdates(){
		try {
			jObj = new JSONObject(task);
			if(jObj.getString("task").equalsIgnoreCase("sendupdates")){
			  DailyUpdatesVO dailyUpdatesVO = new DailyUpdatesVO();
		      HttpSession session = request.getSession();
		      if(session.getAttribute(IConstants.USER) == null && 
				!entitlementsHelper.checkForEntitlementToViewReport(null, IConstants.ADMIN_PAGE)){
			   message = "not loggedIn";
			   return Action.SUCCESS;
		      }
		      if(!entitlementsHelper.checkForEntitlementToViewReport((RegistrationVO)session.getAttribute(IConstants.USER), IConstants.ADMIN_PAGE)){
			   message = "lessPrivileges";
			   return Action.SUCCESS;
		      }
		      boolean isPartySelected = jObj.getBoolean("partySelected");
		      boolean isCandidateSelected = jObj.getBoolean("candidateSelected");
		      boolean isSpecialPageSelected = jObj.getBoolean("specialPageSelected");
		      boolean all = false;
		      List<Long> specialPageIdsList = new ArrayList<Long>();
		      List<Long> candidateIdsList = new ArrayList<Long>();
		      List<Long> partyIdsList = new ArrayList<Long>();
		      if(!isPartySelected && !isCandidateSelected && !isSpecialPageSelected){
		    	  all = true;
		      }
		      String candidatePage = jObj.getString("candidateids");
		      String partyPage = jObj.getString("partyids");
		      String specialPage = jObj.getString("specialpageids");
		      
		      if(specialPage != null && specialPage.length() >0){
		    	  String[] specialPageIds = specialPage.split(",");
		    	  for(String id :specialPageIds){
		    		  if(id != null && id.length() > 0)
		    		  specialPageIdsList.add(new Long(id));
		    	  }
		      }
		      
              if(partyPage != null && partyPage.length() >0){
            	  String[] partyIds = partyPage.split(",");
            	  for(String id :partyIds){
		    		  if(id != null && id.length() > 0)
		    			  partyIdsList.add(new Long(id));
		    	  }
		      }
              
              if(candidatePage != null && candidatePage.length() >0){
            	  String[] candidateIds = candidatePage.split(",");
            	  for(String id :candidateIds){
		    		  if(id != null && id.length() > 0)
		    			  candidateIdsList.add(new Long(id));
		    	  }
		      }
              dailyUpdatesVO.setAll(all);
              dailyUpdatesVO.setCandidateIdsList(candidateIdsList);
              dailyUpdatesVO.setCandidateSelected(isCandidateSelected);
              dailyUpdatesVO.setPartyIdsList(partyIdsList);
              dailyUpdatesVO.setPartySelected(isPartySelected);
              dailyUpdatesVO.setSpecialPageIdsList(specialPageIdsList);
              dailyUpdatesVO.setSpecialPageSelected(isSpecialPageSelected);
              SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
              
              dailyUpdatesVO.setFrom(sdf.parse(jObj.getString("from")));
              dailyUpdatesVO.setTo(sdf.parse(jObj.getString("to")));
		      partyCandidateSpecialPageScheduleService.sendMailsToAllSubscriders(dailyUpdatesVO);
		       message = "success";
		      return Action.SUCCESS;
			}else{
				if(jObj.getString("task").equalsIgnoreCase("party")){
					options = partyCandidateSpecialPageScheduleService.getAllParties();
				}else if(jObj.getString("task").equalsIgnoreCase("candidate")){
					options = partyCandidateSpecialPageScheduleService.getAllCandidates();
				}else if(jObj.getString("task").equalsIgnoreCase("specialpage")){
					options = partyCandidateSpecialPageScheduleService.getAllSpecialPages();
				}
				return "data";
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		      message = "failure";
	      return Action.SUCCESS;
	}

	public List<SelectOptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<SelectOptionVO> options) {
		this.options = options;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	
}
