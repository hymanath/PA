package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PartyCadreSearchAction extends ActionSupport implements ServletRequestAware {
	
	private static final Logger LOG = Logger.getLogger(PartyCadreSearchAction.class);
	private HttpServletRequest request;
	private CadreManagementService cadreManagementService;
	
	private List<SelectOptionVO> constituencysList;
	private Long electionscopeId;
	private Long stateId;
	private List<SelectOptionVO> panchayatList,committeLevels;
	JSONObject jObj = null;
	private String task = null;
	
	
	
	public List<SelectOptionVO> getCommitteLevels() {
		return committeLevels;
	}
	public void setCommitteLevels(List<SelectOptionVO> committeLevels) {
		this.committeLevels = committeLevels;
	}
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public List<SelectOptionVO> getPanchayatList() {
		return panchayatList;
	}
	public void setPanchayatList(List<SelectOptionVO> panchayatList) {
		this.panchayatList = panchayatList;
	}
	public Long getElectionscopeId() {
		return electionscopeId;
	}
	public void setElectionscopeId(Long electionscopeId) {
		this.electionscopeId = electionscopeId;
	}
	public Long getStateId() {
		return stateId;
	}
	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}
	public List<SelectOptionVO> getConstituencysList() {
		return constituencysList;
	}
	public void setConstituencysList(List<SelectOptionVO> constituencysList) {
		this.constituencysList = constituencysList;
	}
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	
	
	public String execute(){
		
		return SUCCESS;
	}

	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
		}
	public String getAllConstituencys() throws Exception
	{
		constituencysList =cadreManagementService.getAllConstituencys(electionscopeId,stateId);
				try{
						if(constituencysList!=null && constituencysList.size()>0){
					
								SelectOptionVO selectOptionVO = new SelectOptionVO();
								selectOptionVO.setId(0l);
								selectOptionVO.setName("Select Constituency");
								constituencysList.add(0,selectOptionVO);
							}
						return Action.SUCCESS;
					}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getAllConstituencys() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}
	public String getAllPanchayat() throws Exception
	{
		
		try{
			
			jObj =new JSONObject(getTask());
			panchayatList=cadreManagementService.getAllPanchayat(jObj.getLong("constituencyId"));
			
				if(panchayatList!=null && panchayatList.size()>0){
					
					SelectOptionVO selectOptionVO = new SelectOptionVO();	
					panchayatList.add(selectOptionVO);
				}
				return Action.SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getAllPanchayat() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCommitteeLevels() 
	{
		
		try{
			
			jObj =new JSONObject(getTask());
			committeLevels=cadreManagementService.getCommitteeLevels();
			return Action.SUCCESS;
		}
		catch(Exception e)
		{
			LOG.error("Exception Rised In getAllPanchayat() in PartyCadreSearchAction class",e);
		}
		return Action.SUCCESS;
	}
	
}
