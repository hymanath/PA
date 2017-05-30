package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.PetitionerDetailsVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.service.IMeekosamGrievanceService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class MeekosamGrievanceAction extends ActionSupport implements ServletRequestAware{

	private final static Logger LOG = Logger.getLogger(AlertManagementSystemAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private JSONObject jObj;
	private String task;
	private IMeekosamGrievanceService meekosamGrievanceService;
	private PetitionerDetailsVO petitionerDetailsVO;
	private String successMsg;
	private List<IdNameVO> idNameVOs;
	private List<IdAndNameVO> idAndNameVOs;
	private List<PetitionerDetailsVO> detailsVOs;
	
	
	public List<PetitionerDetailsVO> getDetailsVOs() {
		return detailsVOs;
	}
	public void setDetailsVOs(List<PetitionerDetailsVO> detailsVOs) {
		this.detailsVOs = detailsVOs;
	}
	public List<IdNameVO> getIdNameVOs() {
		return idNameVOs;
	}
	public void setIdNameVOs(List<IdNameVO> idNameVOs) {
		this.idNameVOs = idNameVOs;
	}
	public List<IdAndNameVO> getIdAndNameVOs() {
		return idAndNameVOs;
	}
	public void setIdAndNameVOs(List<IdAndNameVO> idAndNameVOs) {
		this.idAndNameVOs = idAndNameVOs;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public PetitionerDetailsVO getPetitionerDetailsVO() {
		return petitionerDetailsVO;
	}
	public void setPetitionerDetailsVO(PetitionerDetailsVO petitionerDetailsVO) {
		this.petitionerDetailsVO = petitionerDetailsVO;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
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
	public IMeekosamGrievanceService getMeekosamGrievanceService() {
		return meekosamGrievanceService;
	}
	public void setMeekosamGrievanceService(
			IMeekosamGrievanceService meekosamGrievanceService) {
		this.meekosamGrievanceService = meekosamGrievanceService;
	}
	
	
	public String execute(){
		session = request.getSession();
		RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		if(regVo != null)
			return Action.SUCCESS;
		else
			return Action.ERROR;
	}
	public String saveMeekosamPetitionerDetails(){
	   try {
		   session = request.getSession();
		   RegistrationVO regVo = (RegistrationVO)session.getAttribute("USER");
		   if(regVo == null)
			   successMsg = "failure";
		   Long userId = regVo.getRegistrationID();
		   petitionerDetailsVO.setUserId(userId);
		   successMsg = meekosamGrievanceService.saveMeekosamPetitionerDetails(petitionerDetailsVO);
		   
		} catch (Exception e) {
			LOG.error("Exception Raised in saveMeekosamPetitionerDetails() in MeekosamGrievanceAction",e);
		}
		   return Action.SUCCESS;
	}
	public String getAllMandalsByDistrictID(){
	   try {
		   jObj = new JSONObject(getTask());
		   Long districtId = jObj.getLong("districtId");
		   idNameVOs = meekosamGrievanceService.getAllMandalsByDistrictID(districtId);
		} catch (Exception e) {
			LOG.error("Exception Raised in getAllMandalsByDistrictID() in MeekosamGrievanceAction",e);
		}
		   return Action.SUCCESS;
	}
	public String getAllHamletByPanchayatID(){
	   try {
		   jObj = new JSONObject(getTask());
		   Long panchayatId = jObj.getLong("panchayatId");
		   idNameVOs = meekosamGrievanceService.getAllHamletByPanchayatID(panchayatId);
		} catch (Exception e) {
			LOG.error("Exception Raised in getAllHamletByPanchayatID() in MeekosamGrievanceAction",e);
		}
		   return Action.SUCCESS;
	}
	public String getMeekosamOccupationList(){
	   try {
		   idAndNameVOs = meekosamGrievanceService.getMeekosamOccupationList();
		} catch (Exception e) {
			LOG.error("Exception Raised in getMeekosamOccupationList() in MeekosamGrievanceAction",e);
		}
		   return Action.SUCCESS;
	}
	public String getMeekosamCasteCategoryList(){
	   try {
		   idAndNameVOs = meekosamGrievanceService.getMeekosamCasteCategoryList();
		} catch (Exception e) {
			LOG.error("Exception Raised in getMeekosamCasteCategoryList() in MeekosamGrievanceAction",e);
		}
		   return Action.SUCCESS;
	}
	public String getMeekosamArgeeCategoryList(){
	   try {
		   idAndNameVOs = meekosamGrievanceService.getMeekosamArgeeCategoryList();
		} catch (Exception e) {
			LOG.error("Exception Raised in getMeekosamArgeeCategoryList() in MeekosamGrievanceAction",e);
		}
		   return Action.SUCCESS;
	}
	public String getMeekosamAnnualIncomeList(){
	   try {
		   idAndNameVOs = meekosamGrievanceService.getMeekosamAnnualIncomeList();
		} catch (Exception e) {
			LOG.error("Exception Raised in getMeekosamAnnualIncomeList() in MeekosamGrievanceAction",e);
		}
		   return Action.SUCCESS;
	}
	public String searchPetitionerDetailsByVoterNoAadharNoMobileNo(){
		   try {
			   jObj = new JSONObject(getTask());
			   String cardNo = jObj.getString("cardNo");
			   String type = jObj.getString("type");
			   detailsVOs = meekosamGrievanceService.searchPetitionerDetailsByVoterNoAadharNoMobileNo(cardNo,type);
			   
			} catch (Exception e) {
				LOG.error("Exception Raised in getMeekosamAnnualIncomeList() in MeekosamGrievanceAction",e);
			}
			   return Action.SUCCESS;
		}
}