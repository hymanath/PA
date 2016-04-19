package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.service.ICadreCommitteeService;
import com.itgrids.partyanalyst.service.ICadreVoterSearchService;
import com.itgrids.partyanalyst.service.IMahaNaduService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreVoterSearchAction extends ActionSupport implements ServletRequestAware{

	private static final Logger LOG = Logger.getLogger(CadreVoterSearchAction.class);
	
	private HttpServletRequest request;	
	private String task;
	private JSONObject jobj;
	private List<SelectOptionVO> casteDetails;
	private List<LocationWiseBoothDetailsVO> resultList;
	private IMahaNaduService mahaNaduService;
	private ICadreVoterSearchService cadreVoterSearchService;
	private List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>();
	private ICadreCommitteeService cadreCommitteeService;
	private VoterDetailsVO voterDetailsvo;
	private String status;
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VoterDetailsVO getVoterDetailsvo() {
		return voterDetailsvo;
	}

	public void setVoterDetailsvo(VoterDetailsVO voterDetailsvo) {
		this.voterDetailsvo = voterDetailsvo;
	}

	public ICadreCommitteeService getCadreCommitteeService() {
		return cadreCommitteeService;
	}

	public void setCadreCommitteeService(
			ICadreCommitteeService cadreCommitteeService) {
		this.cadreCommitteeService = cadreCommitteeService;
	}

	
	public List<TdpCadreVO> getTdpCadreVOList() {
		return tdpCadreVOList;
	}
	public void setTdpCadreVOList(List<TdpCadreVO> tdpCadreVOList) {
		this.tdpCadreVOList = tdpCadreVOList;
	}
	public ICadreVoterSearchService getCadreVoterSearchService() {
		return cadreVoterSearchService;
	}
	public void setCadreVoterSearchService(
			ICadreVoterSearchService cadreVoterSearchService) {
		this.cadreVoterSearchService = cadreVoterSearchService;
	}
	public IMahaNaduService getMahaNaduService() {
		return mahaNaduService;
	}
	public void setMahaNaduService(IMahaNaduService mahaNaduService) {
		this.mahaNaduService = mahaNaduService;
	}
	
	public List<LocationWiseBoothDetailsVO> getResultList() {
		return resultList;
	}
	public void setResultList(List<LocationWiseBoothDetailsVO> resultList) {
		this.resultList = resultList;
	}
	public List<SelectOptionVO> getCasteDetails() {
		return casteDetails;
	}
	public void setCasteDetails(List<SelectOptionVO> casteDetails) {
		this.casteDetails = casteDetails;
	}
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
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

public String execute(){
		
		try {
			LOG.info("Entered into  cadreVoterSearch method");
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			
			if(user == null)
				return Action.INPUT;
			
			casteDetails = mahaNaduService.getCasteCategories();
			
			
		} catch (Exception e) {
			LOG.error("Exception raised in cadreVoterSearch method",e);
		}
		return Action.SUCCESS;
	}
	
	
	public String getDistrictsAndConstituencies(){
		
		LOG.info("Entered into getDistrictsAndConstituencies method");
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		try {		
			jobj = new JSONObject(request.getParameter("task"));
			String type=jobj.getString("type");
			if(type.equalsIgnoreCase("mandal")){
				resultList = cadreCommitteeService.getMandalsByConstituency(jobj.getLong("id"));	
			}else if(type.equalsIgnoreCase("panchayat")){			
				resultList = cadreCommitteeService.getPanchayatWardByMandalId(Long.toString(jobj.getLong("id")));	
			}
			else{
				resultList = cadreVoterSearchService.getAllDistrictsAndConstis(type,jobj.getLong("id"));
			}
		}
		catch(Exception e){
			LOG.error("Exception raised in getDistrictsAndConstituencies method", e);
		}
		return Action.SUCCESS;
		
		
	}
	
	public String getCadreVoterDetailsByLocation(){
		LOG.info("Entered into getCadreVoterDetailsByLocation method");
		//HttpSession session = request.getSession();
		//RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		
		try {		
					jobj = new JSONObject(getTask());
					
					String searchType 	= jobj.getString("searchType");
					Long stateId		= jobj.getLong("stateId");
					Long locationId 	= jobj.getLong("locationId");
					String locationType = jobj.getString("locationType");
					Long casteStateId 	= jobj.getLong("casteStateId");
					String searchName 	= jobj.getString("searchName");
					String isFinal 		= jobj.getString("isFinal");
					
					tdpCadreVOList = cadreVoterSearchService.getCadreVoterDetailsBySearchCriteria(searchType,stateId,locationType,locationId,casteStateId,searchName,isFinal);					
		}
		catch(Exception e){
			LOG.error("Exception raised in getExistingCadreInfoForCommittee method in CadreRegistrationAction action", e);
		}
		return Action.SUCCESS;
		
		
	}
	
	public String getVoterDetailsByVoterCardNumber(){
		LOG.info("Entered into getVoterDetailsByVoterCardNumber method");
		try {
			jobj = new JSONObject(getTask());
			
			String voterIDCardNo 	= jobj.getString("voterIDCardNo");
			
			voterDetailsvo = cadreVoterSearchService.getVoterDetailsByVoterCardNumber(voterIDCardNo);
			
		} catch (Exception e) {
			LOG.error("Exception raised in getVoterDetailsByVoterCardNumber method in CadreVoterSearchAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String generateOTPForMobileNumber(){
		LOG.info("Entered into generateOTPForMobileNumber method");
		try {
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			Long userId = user.getRegistrationID();
			
			jobj = new JSONObject(getTask());
			
			String mobileNo	= jobj.getString("mobileNo");
			String refNo = jobj.getString("refNo");
			
			status = cadreVoterSearchService.generateOTPForMobileNumber(userId,mobileNo,refNo);
			
		} catch (Exception e) {
			LOG.error("Exception raised in generateOTPForMobileNumber method in CadreVoterSearchAction action", e);
		}
		return Action.SUCCESS;
	}
	
	public String validateOTP(){
		LOG.info("Entered into validateOTP method");
		try {
			
			jobj = new JSONObject(getTask());
			
			String mobileNo	= jobj.getString("mobileNo");
			String refNo = jobj.getString("refNo");
			String otp = jobj.getString("otp");
			
			status = cadreVoterSearchService.validateOTP(mobileNo,refNo,otp);
			//status = "Success";
		} catch (Exception e) {
			LOG.error("Exception raised in validateOTP method in CadreVoterSearchAction action", e);
		}
		return Action.SUCCESS;
	}
}
