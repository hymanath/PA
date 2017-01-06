package com.itgrids.cardprint.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.cardprint.dto.BasicVO;
import com.itgrids.cardprint.dto.PrintStatusUpdateVO;
import com.itgrids.cardprint.dto.ResultStatus;
import com.itgrids.cardprint.dto.UserVO;
import com.itgrids.cardprint.service.ICardPrintService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CardPrintAction extends ActionSupport implements ServletRequestAware{
	
	private static final Logger LOG = Logger.getLogger(CardPrintAction.class);
	
	//Member Variables
	private HttpServletRequest request;
	private String task;
	private JSONObject jobj;
	private List<BasicVO> basicVOList;
	private ResultStatus resultStatus;
	
	//Attributes
	private ICardPrintService cardPrintService;
	
	//setters and getters
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
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
	
	public void setCardPrintService(ICardPrintService cardPrintService) {
		this.cardPrintService = cardPrintService;
	}
	
	public List<BasicVO> getBasicVOList() {
		return basicVOList;
	}
	public void setBasicVOList(List<BasicVO> basicVOList) {
		this.basicVOList = basicVOList;
	}
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	//Implementation Methods
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	
	//Request Handling Methods
	public String  getAllVendors(){
		try{
			basicVOList = cardPrintService.getAllVendors();
			
		}catch(Exception e){
			LOG.error("Exception Occurred At getAllVendors() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
	
	public String  getAllPrintStatus(){
		try{
			basicVOList = cardPrintService.getAllPrintStatus();
			
		}catch(Exception e){
			LOG.error("Exception Occurred At getAllPrintStatus() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
	
	public String  getAllAssemblyConstituencies(){
		try{
			basicVOList = cardPrintService.getAllAssemblyConstituencies();
			
		}catch(Exception e){
			LOG.error("Exception Occurred At getAllAssemblyConstituencies() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
	
	public String getCardPrintUpdationDetails(){
		try{
			
			HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("USER");
			if(user == null || user.getUserId() == null){
				return Action.INPUT;
			}
		}catch(Exception e){
			LOG.error("Exception Occurred At getCardPrintUpdationDetails() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
	

	public String  saveConstituencyPrintStatus(){
		try{
			
			HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("USER");
			if(user == null || user.getUserId() == null){
			    resultStatus = new ResultStatus();
				resultStatus.setResultCode(0);
				resultStatus.setExceptionMsg("USER HAS LOGGED OUT. PLEASE SIGN IN..");
				return Action.SUCCESS;
			}
			
			jobj = new JSONObject(getTask());
			
			PrintStatusUpdateVO inputVO = new PrintStatusUpdateVO();
			inputVO.setUserId(user.getUserId());
			inputVO.setConstituencyId(jobj.getLong("constituencyId"));
			inputVO.setPrintStatusId(jobj.getLong("printStatusId"));
			inputVO.setRemarks(jobj.getString("remarks"));
			
			resultStatus = cardPrintService.saveConstituencyPrintStatus(inputVO);
			
		}catch(Exception e){
			LOG.error("Exception Occurred At saveConstituencyPrintStatus() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
	
	
	public String  updatePrintDetailsToTdpCadreCardPrint(){
		try{
			
			HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("USER");
			if(user == null || user.getUserId() == null){
			    resultStatus = new ResultStatus();
				resultStatus.setResultCode(0);
				resultStatus.setExceptionMsg("USER HAS LOGGED OUT.PLEASE SIGN IN..");
				return Action.SUCCESS;
			}
			
			jobj = new JSONObject(getTask());
			Long constituencyId = jobj.getLong("constituencyId");
			
			resultStatus = cardPrintService.updatePrintDetailsToTdpCadreCardPrint(constituencyId);
			
		}catch(Exception e){
			LOG.error("Exception Occurred At updatePrintDetailsToTdpCadreCardPrint() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
	
	public String adminCardPrint(){
        try{
			
			HttpSession session = request.getSession();
			UserVO user = (UserVO) session.getAttribute("USER");
			if(user == null || user.getUserId() == null){
				return Action.INPUT;
			}
		}catch(Exception e){
			LOG.error("Exception Occurred At adminCardPrint() in CardPrintAction class",e) ;
		}
		return Action.SUCCESS;
	}
}
