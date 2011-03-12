package com.itgrids.partyanalyst.web.action;

import java.io.File;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.itgrids.partyanalyst.dto.MPTCElectionResultVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IMptcElectionService;
import com.itgrids.partyanalyst.service.IMuncipleDataUploadService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class MPTCElectionAction  extends ActionSupport implements ServletRequestAware{
	
	private HttpServletRequest request;
	private IRegionServiceData regionServiceDataImp; 
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private String task;
	private MPTCElectionResultVO resultVO;
	private IMptcElectionService mptcElectionService;
	private IMuncipleDataUploadService muncipleDataUploadService;
	
	private Long electionTypeID;
	private String electionType;
	
	public Long getElectionTypeID() {
		return electionTypeID;
	}

	public void setElectionTypeID(Long electionTypeID) {
		this.electionTypeID = electionTypeID;
	}

	public Long getCountryID() {
		return countryID;
	}

	public void setCountryID(Long countryID) {
		this.countryID = countryID;
	}

	public Long getStateID() {
		return stateID;
	}

	public void setStateID(Long stateID) {
		this.stateID = stateID;
	}

	public Long getDistrictID() {
		return districtID;
	}

	public void setDistrictID(Long districtID) {
		this.districtID = districtID;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getElectionType() {
		return electionType;
	}

	public void setElectionType(String electionType) {
		this.electionType = electionType;
	}

	public IMuncipleDataUploadService getMuncipleDataUploadService() {
		return muncipleDataUploadService;
	}

	public void setMuncipleDataUploadService(
			IMuncipleDataUploadService muncipleDataUploadService) {
		this.muncipleDataUploadService = muncipleDataUploadService;
	}

	public String getElecSubtype() {
		return elecSubtype;
	}

	public void setElecSubtype(String elecSubtype) {
		this.elecSubtype = elecSubtype;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	private Long countryID;
	private Long stateID;
	private Long districtID;
	private String year;
	private String elecSubtype;
	private static Logger log = Logger.getLogger(MPTCElectionAction.class);
	
	private File file;
	public void setServletRequest(HttpServletRequest request) {
		this.request =request;
	}
	
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	public List<SelectOptionVO> getStateList() {
		return stateList;
	}

	public void setStateList(List<SelectOptionVO> stateList) {
		this.stateList = stateList;
	}


	public List<SelectOptionVO> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<SelectOptionVO> districtList) {
		this.districtList = districtList;
	}

	public MPTCElectionResultVO getResultVO() {
		return resultVO;
	}

	public void setResultVO(MPTCElectionResultVO resultVO) {
		this.resultVO = resultVO;
	}

	public void setMptcElectionService(IMptcElectionService mptcElectionService) {
		this.mptcElectionService = mptcElectionService;
	}

	public String execute() {
		log.debug("MPTCElectionAction.execute()....Started");
		log.debug("MPTCElectionAction.execute() countryID:"+countryID);
		log.debug("MPTCElectionAction.execute() electionType:"+electionType);
		log.debug("MPTCElectionAction.execute() stateID:"+stateID);
		log.debug("MPTCElectionAction.execute() districtID:"+districtID);
		log.debug("MPTCElectionAction.execute() year:"+year);
		if(file==null)
			log.debug("MPTCElectionAction.execute() File is null null:");
		else
			log.debug("MPTCElectionAction.execute() File is not null");
		
		if(IConstants.MPTC_ELECTION_TYPE.equalsIgnoreCase(electionType) ||
				IConstants.ZPTC_ELECTION_TYPE.equalsIgnoreCase(electionType)){
			resultVO = mptcElectionService
			.uploadMPTCElectionData(electionTypeID, countryID, stateID,
					districtID, year, file, elecSubtype);
			
		}else{
			resultVO = muncipleDataUploadService.readExcelDataForMuncipalities(file, electionTypeID, stateID, year, elecSubtype);
			resultVO.setElectionType(electionType);
		}
			
		String result =ERROR;
		if(resultVO.getExceptionEncountered()==null){
			result = SUCCESS;
	    } else{
			log.error(resultVO.getExceptionEncountered().getMessage(),resultVO.getExceptionEncountered());
		}
		return result;
	}
	public String getStates(){
		stateList = regionServiceDataImp.getStatesByCountry(1L);
		stateList.add(0, new SelectOptionVO(0L,"Select State"));
		Collections.sort(stateList);
		return SUCCESS;
	}
	public String getDistricts() throws Exception{
		log.debug("MPTCElectionAction.getDistricts()....Started");
		String state = request.getParameter("stateID");
		log.debug("MPTCElectionAction.getDistricts() stateID:"+stateID);
		
		districtList = regionServiceDataImp.getDistrictsByStateID(new Long(state));
		districtList.add(0, new SelectOptionVO(0L,"Select District"));
		return SUCCESS;
	}

}
