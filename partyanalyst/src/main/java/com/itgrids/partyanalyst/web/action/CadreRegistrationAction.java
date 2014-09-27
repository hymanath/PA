package com.itgrids.partyanalyst.web.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadrePreviousRollesVO;
import com.itgrids.partyanalyst.dto.CadreRegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.ICadreRegistrationService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegistrationAction  extends ActionSupport implements ServletRequestAware{

	private static final Logger         	LOG = Logger.getLogger(CadreRegistrationAction.class);
	private HttpServletRequest         		request;
	
	private ICadreRegistrationService   	cadreRegistrationService;
	
	private String 							task;
	private JSONObject                  	jobj;
	private CadreRegistrationVO				cadreRegistrationVO;
	private List<CadrePreviousRollesVO> 	previousRollesList;
	private List<CadrePreviousRollesVO> 	previousElectionssList;
	private ResultStatus					resultStatus;
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	

	public ICadreRegistrationService getCadreRegistrationService() {
		return cadreRegistrationService;
	}

	public void setCadreRegistrationService(
			ICadreRegistrationService cadreRegistrationService) {
		this.cadreRegistrationService = cadreRegistrationService;
	}


	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public CadreRegistrationVO getCadreRegistrationVO() {
		return cadreRegistrationVO;
	}


	public void setCadreRegistrationVO(CadreRegistrationVO cadreRegistrationVO) {
		this.cadreRegistrationVO = cadreRegistrationVO;
	}


	public List<CadrePreviousRollesVO> getPreviousRollesList() {
		return previousRollesList;
	}


	public void setPreviousRollesList(List<CadrePreviousRollesVO> previousRollesList) {
		this.previousRollesList = previousRollesList;
	}


	public List<CadrePreviousRollesVO> getPreviousElectionssList() {
		return previousElectionssList;
	}


	public void setPreviousElectionssList(
			List<CadrePreviousRollesVO> previousElectionssList) {
		this.previousElectionssList = previousElectionssList;
	}


	public String execute()
	{
		try {
			LOG.info("Entered into execute method in CadreRegistrationAction Action");
			saveCadreDetails();
		} catch (Exception e) {
			LOG.error("Exception raised in execute method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public String saveCadreDetails()
	{
		try {
			LOG.info("Entered into saveCadreDetails method in CadreRegistrationAction Action");
			CadreRegistrationVO cadreRegistrationVO = new CadreRegistrationVO();
			cadreRegistrationVO.setVoterName("PRASAD");
			cadreRegistrationVO.setDob(convertToDateFormet("22-12-2014"));
			cadreRegistrationVO.setGender("M");
			cadreRegistrationVO.setRelativeName("PRASAD");
			cadreRegistrationVO.setVoterId(13812457l);
			cadreRegistrationVO.setPreviousEnrollmentNumber("12345678");
			cadreRegistrationVO.setPartyMemberSince(convertToDateFormet("22-12-2014"));
			cadreRegistrationVO.setBloodGroupId(3l);
			cadreRegistrationVO.setCasteId(23l);
			cadreRegistrationVO.setEducationId(2l);
			cadreRegistrationVO.setOccupationId(2l);
			cadreRegistrationVO.setMobileNumber("9494302021");
			cadreRegistrationVO.setPreviousEnrollmentNumber("123456");
			cadreRegistrationVO.setHouseNo("123");
			
			CadrePreviousRollesVO rolesVO1 = new CadrePreviousRollesVO();
			
			rolesVO1.setDesignationLevelId(1l);
			rolesVO1.setDesignationLevelValue(1l);
			rolesVO1.setFromDate(convertToDateFormet("22-12-2014"));
			rolesVO1.setToDate(convertToDateFormet("22-12-2014"));
			
			
			CadrePreviousRollesVO rolesVO2 = new CadrePreviousRollesVO();
			
			rolesVO2.setDesignationLevelId(2l);
			rolesVO2.setDesignationLevelValue(2l);
			rolesVO2.setFromDate(convertToDateFormet("22-12-2014"));
			rolesVO2.setToDate(convertToDateFormet("22-12-2014"));
			
			
			CadrePreviousRollesVO electionVO = new CadrePreviousRollesVO();
			
			electionVO.setElectionTypeId(1l);
			electionVO.setConstituencyId(232l);
			
			List<CadrePreviousRollesVO> 	previousRollesList = new ArrayList<CadrePreviousRollesVO>();
			List<CadrePreviousRollesVO> 	previousElectionssList = new ArrayList<CadrePreviousRollesVO>();
			
			
			previousRollesList.add(rolesVO1);
			previousRollesList.add(rolesVO2);
			previousElectionssList.add(electionVO);
			
			cadreRegistrationVO.setPreviousRollesList(previousRollesList);
			cadreRegistrationVO.setPreviousParicaptedElectionsList(previousElectionssList);
			
			resultStatus = cadreRegistrationService.saveCadreRegistration(cadreRegistrationVO);
		} catch (Exception e) {
			LOG.error("Exception raised in saveCadreDetails method in CadreRegistrationAction Action",e);
		}
		return Action.SUCCESS;
	}
	
	public Date convertToDateFormet(String dateStr)
	{
		Date date = null;
		try {
			SimpleDateFormat originalFormat = new SimpleDateFormat("dd-MM-yyyy");
			date = originalFormat.parse(dateStr);
		} catch (Exception e) {
			LOG.error("Exception raised in convertToDateFormet method in CadreRegistrationAction Action",e);
		}
		return date;
		
	}

}
