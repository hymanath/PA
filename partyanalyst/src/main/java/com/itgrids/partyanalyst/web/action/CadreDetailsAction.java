package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreCommitteeMemberVO;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreDetailsAction extends ActionSupport{
	
	private static final Logger LOG = Logger.getLogger(CadreDetailsAction.class);
	private HttpServletRequest 					request;
	private HttpSession 						session;
	private String 								task;
	private JSONObject							jObj;
	private ICadreDetailsService  				cadreDetailsService;
	private CadreCommitteeMemberVO              cadreCommitteeMemberVO;   
	private List<CadreCommitteeMemberVO>      	cadreCommitteeMemberVOList;
	private Long 								cadreId;
	

	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	public HttpSession getSession() {
		return session;
	}


	public void setSession(HttpSession session) {
		this.session = session;
	}


	public String getTask() {
		return task;
	}


	public void setTask(String task) {
		this.task = task;
	}


	public JSONObject getjObj() {
		return jObj;
	}


	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	
	public ICadreDetailsService getCadreDetailsService() {
		return cadreDetailsService;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}

	public CadreCommitteeMemberVO getCadreCommitteeMemberVO() {
		return cadreCommitteeMemberVO;
	}

	public void setCadreCommitteeMemberVO(
			CadreCommitteeMemberVO cadreCommitteeMemberVO) {
		this.cadreCommitteeMemberVO = cadreCommitteeMemberVO;
	}
	public List<CadreCommitteeMemberVO> getCadreCommitteeMemberVOList() {
		return cadreCommitteeMemberVOList;
	}


	public void setCadreCommitteeMemberVOList(
			List<CadreCommitteeMemberVO> cadreCommitteeMemberVOList) {
		this.cadreCommitteeMemberVOList = cadreCommitteeMemberVOList;
	}

	public Long getCadreId() {
		return cadreId;
	}


	public void setCadreId(Long cadreId) {
		this.cadreId = cadreId;
	}


	public String execute(){
		
		return Action.SUCCESS;
	}
	
	public String cadreFormalDetailedInformation(){
		
		try {
			jObj=new JSONObject(getTask());
			
			cadreCommitteeMemberVO=cadreDetailsService.cadreFormalDetailedInformation(jObj.getLong("cadreId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in cadreFormalDetailedInformation  method in CadreDetailsAction.",e);
		}
		
		return Action.SUCCESS;
	}
	public String complaintDetailsOfCadre(){
		
		try {
			jObj=new JSONObject(getTask());
			
			cadreCommitteeMemberVO=cadreDetailsService.complaintDetailsOfCadre(jObj.getLong("cadreId"));
			
		} catch (Exception e) {
			LOG.error("Exception raised in cadreFormalDetailedInformation  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
	public String getEventDetailsOfCadre(){
		try{
			jObj=new JSONObject(getTask());
			
			cadreCommitteeMemberVOList=cadreDetailsService.getEventDetailsOfCadre(jObj.getLong("cadreId"));
		}catch(Exception e){
			LOG.error("Exception raised in getEventDetailsOfCadre  method in CadreDetailsAction.",e);
		}
		return Action.SUCCESS;
	}
}
