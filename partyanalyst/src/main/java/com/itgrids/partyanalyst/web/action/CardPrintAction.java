package com.itgrids.partyanalyst.web.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreValidateVO;
import com.itgrids.partyanalyst.dto.CardPrintStatusVO;
import com.itgrids.partyanalyst.dto.CardPrintVO;
import com.itgrids.partyanalyst.dto.CardPrintingDispatchVO;
import com.itgrids.partyanalyst.dto.SmallVO;
import com.itgrids.partyanalyst.service.ICardPrintService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CardPrintAction extends ActionSupport implements ServletRequestAware{

	private final static Logger LOG = Logger.getLogger(FieldMonitoringAction.class);
	
	//instance variables
	private HttpServletRequest request;
	private HttpSession	session;
	private JSONObject jObj;
	private String task;
	private List<SmallVO> smallVOList;
	private List<String> errorsList;
	
	//Attributes
	private ICardPrintService cardPrintService;
	private List<CardPrintVO> vendorList;
	private CardPrintVO cardPrintVO;
	private List<CardPrintingDispatchVO> cardPrintingDispatchVOList;
	private List<CardPrintStatusVO> cardPrintStatusVOList;
	private CadreValidateVO cadreValidateVO;
	
	//implementation methods
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	//setters and getters.
	public List<CardPrintingDispatchVO> getCardPrintingDispatchVOList() {
		return cardPrintingDispatchVOList;
	}
	public void setCardPrintingDispatchVOList(
			List<CardPrintingDispatchVO> cardPrintingDispatchVOList) {
		this.cardPrintingDispatchVOList = cardPrintingDispatchVOList;
	}
	public ICardPrintService getCardPrintService() {
		return cardPrintService;
	}
	public void setCardPrintService(ICardPrintService cardPrintService) {
		this.cardPrintService = cardPrintService;
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
	
	public List<CardPrintVO> getVendorList() {
		return vendorList;
	}
	public void setVendorList(List<CardPrintVO> vendorList) {
		this.vendorList = vendorList;
	}
	public CardPrintVO getCardPrintVO() {
		return cardPrintVO;
	}
	public void setCardPrintVO(CardPrintVO cardPrintVO) {
		this.cardPrintVO = cardPrintVO;
	}
	public List<CardPrintStatusVO> getCardPrintStatusVOList() {
		return cardPrintStatusVOList;
	}
	public void setCardPrintStatusVOList(
			List<CardPrintStatusVO> cardPrintStatusVOList) {
		this.cardPrintStatusVOList = cardPrintStatusVOList;
	}
	public CadreValidateVO getCadreValidateVO() {
		return cadreValidateVO;
	}
	public void setCadreValidateVO(CadreValidateVO cadreValidateVO) {
		this.cadreValidateVO = cadreValidateVO;
	}
	public List<SmallVO> getSmallVOList() {
		return smallVOList;
	}
	public void setSmallVOList(List<SmallVO> smallVOList) {
		this.smallVOList = smallVOList;
	}
	
	public List<String> getErrorsList() {
		return errorsList;
	}
	public void setErrorsList(List<String> errorsList) {
		this.errorsList = errorsList;
	}
	public String execute(){
		try{
			//jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getVendorNames();
			vendorList.add(0, new CardPrintVO(0l, "Select Vendor"));
			
		}catch(Exception e){
			LOG.error("Exception raised in cardPrintDashboard() in CardPrintAction",e);
		}
		return Action.SUCCESS;
	}
	public String cardPrintDashboard() {
			try{
			//jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getVendorNames();
			vendorList.add(0, new CardPrintVO(0l, "ALL"));
			
		}catch(Exception e){
			LOG.error("Exception raised in cardPrintDashboard() in CardPrintAction",e);
		}
			return Action.SUCCESS;	
	}
	public String getConstencyList(){
		try{
			jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getConstListByVendor(jObj.getLong("vendorId"),jObj.getLong("districtId"));
		}catch(Exception e){
			LOG.error("Exception raised in getConstencyList() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getDistrictList(){
		try{
			jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getDstrListByVendor(jObj.getLong("vendorId"));
		}catch(Exception e){
			LOG.error("Exception raised in getDistrictList() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getStatusWisePrintingConstituencyDetails(){
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			Long vendorId = jObj.getLong("vendorId");
			String fromDateStr = jObj.getString("startDate");
			String toDateStr = jObj.getString("endDate");
			
			cardPrintVO = cardPrintService.getStatusWisePrintingConstituencyDetails(stateId, vendorId, fromDateStr, toDateStr);
		} catch (Exception e) {
			LOG.error("Exception raised in getStatusWisePrintingConstituencyDetails() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getDistrictWiseStatusWiseConstituenciesCounts(){
		try {
			jObj = new JSONObject(getTask());
			Long vendorId = jObj.getLong("vendorId");
			String fromDateStr = jObj.getString("startDate");
			String toDateStr = jObj.getString("endDate");
			
			cardPrintVO = cardPrintService.getDistrictWiseStatusWiseConstituenciesCounts(vendorId, fromDateStr, toDateStr);
		} catch (Exception e) {
			LOG.error("Exception raised in getDistrictWiseStatusWiseConstituenciesCounts() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getPrintingDispatchDetails(){
		try {
			jObj = new JSONObject(getTask());
			Long vendorId = jObj.getLong("vendorId");
			Long districtId = jObj.getLong("districtId");
			Long constituencyId = jObj.getLong("constituencyId");
			
			cardPrintingDispatchVOList = cardPrintService.getPrintingDispatchDetails(vendorId, districtId, constituencyId);
		} catch (Exception e) {
			LOG.error("Exception raised in getPrintingDispatchDetails() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getVendorWiseStatusWiseConstituenciesDetails(){
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String fromDate = jObj.getString("fromDate");
			String toDate = jObj.getString("toDate");
			
			cardPrintVO = cardPrintService.getVendorWiseStatusWiseConstituenciesDetails(stateId, fromDate, toDate);
		} catch (Exception e) {
			LOG.error("Exception raised in getVendorWiseStatusWiseConstituenciesDetails() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getCardPrintingStatus(){
		return Action.SUCCESS;
	}
	
	public String cardPrintAdminStatus(){
		return Action.SUCCESS;
	}

	public String cardPrinStatusByLocation(){
		try {
			jObj = new JSONObject(getTask());
			Long stateId = jObj.getLong("stateId");
			String type = jObj.getString("type");
			
			cardPrintStatusVOList = cardPrintService.cardPrinStatusByLocation(type, stateId);
		} catch (Exception e) {
			LOG.error("Exception raised in cardPrinStatusByLocation() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	public String getEnrollmentDetailsByConstituency(){
		try{
			LOG.info("Entered into getEnrollmentDetailsByConstituency() in cardPrintAction");
			jObj = new JSONObject(getTask());
			vendorList = cardPrintService.getEnrollmentDetailsByConstituency();
		}catch(Exception e){
			LOG.error("Exception raised in getEnrollmentDetailsByConstituency() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	public String getConstNotVerfiedCardPrintStatusCadreAndValidate(){
		try{
			LOG.info("Entered into getConstNotVerfiedCardPrintStatusCadreAndValidate() in cardPrintAction");
			
			jObj = new JSONObject(getTask());
			Long constituencyId = jObj.getLong("constituencyId");
			cadreValidateVO = cardPrintService.getConstNotVerfiedCardPrintStatusCadreAndValidate(constituencyId);
			
		}catch(Exception e){
			LOG.error("Exception raised in getConstNotVerfiedCardPrintStatusCadreAndValidate() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	////
	public String cardPrintPostVerification(){
		return Action.SUCCESS;
	}
	
	public String getPrintPushedConstituencies(){
		
		try{
			 smallVOList = cardPrintService.getPrintPushedConstituencies();
		}catch(Exception e){
			LOG.error("Exception raised in getPrintPushedConstituencies() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	public String cadrePrintDataPostVerification(){
		try{
			errorsList = cardPrintService.postVerificationCadreData(new JSONObject(getTask()).getLong("constituencyId"));
		}catch(Exception e){
			LOG.error("Exception raised in cadrePrintDataPostVerificationAction() in CardPrintAction ",e);
		}
		return Action.SUCCESS;
	}
	
	
}
