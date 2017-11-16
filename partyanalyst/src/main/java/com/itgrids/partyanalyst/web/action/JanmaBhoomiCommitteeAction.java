package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONArray;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CadreAmountDetailsVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeMemberVO;
import com.itgrids.partyanalyst.dto.JanmabhoomiCommitteeVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.service.IJanmabhoomiCommitteeService;
import com.itgrids.partyanalyst.service.impl.LeaderCadreDashBoardService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class JanmaBhoomiCommitteeAction  extends ActionSupport implements ServletRequestAware  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7220092157888269387L;
	private static final Logger LOG = Logger.getLogger(JanmaBhoomiCommitteeAction.class);
	private HttpServletRequest request;
	private HttpSession session;
	private String task;
	public  transient JSONObject jObj;
	private IJanmabhoomiCommitteeService janmabhoomiCommitteeService;
	private List<JanmabhoomiCommitteeVO>  janmabhoomiCommitteeVOList;
	private JanmabhoomiCommitteeVO janmabhoomiCommitteeVO;
	private JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO;
	private ResultStatus resultStatus;

	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public List<JanmabhoomiCommitteeVO> getJanmabhoomiCommitteeVOList() {
		return janmabhoomiCommitteeVOList;
	}
	public void setJanmabhoomiCommitteeVOList(
			List<JanmabhoomiCommitteeVO> janmabhoomiCommitteeVOList) {
		this.janmabhoomiCommitteeVOList = janmabhoomiCommitteeVOList;
	}
	public JanmabhoomiCommitteeMemberVO getJanmabhoomiCommitteeMemberVO() {
		return janmabhoomiCommitteeMemberVO;
	}
	public void setJanmabhoomiCommitteeMemberVO(
			JanmabhoomiCommitteeMemberVO janmabhoomiCommitteeMemberVO) {
		this.janmabhoomiCommitteeMemberVO = janmabhoomiCommitteeMemberVO;
	}
	public void setJanmabhoomiCommitteeService(
			IJanmabhoomiCommitteeService janmabhoomiCommitteeService) {
		this.janmabhoomiCommitteeService = janmabhoomiCommitteeService;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(final HttpServletRequest request) {
		this.request = request;
	}
	public HttpSession getSession() {
		return session;
	}
	public void setSession(final HttpSession session) {
		this.session = session;
	}
	public String getTask() {
		return task;
	}
	public void setTask(final String task) {
		this.task = task;
	}
    //override
	public void setServletRequest(final HttpServletRequest request) {
		this.request = request;
		
	}
	
	public JSONObject getjObj() {
		return jObj;
	}
	public void setjObj(JSONObject jObj) {
		this.jObj = jObj;
	}
	public JanmabhoomiCommitteeVO getJanmabhoomiCommitteeVO() {
		return janmabhoomiCommitteeVO;
	}
	public IJanmabhoomiCommitteeService getJanmabhoomiCommitteeService() {
		return janmabhoomiCommitteeService;
	}
	
	public String janmabhoomiCommitteeDashboard()
	{
		return Action.SUCCESS;
	} 
	public String getDistrictWiseCommitteeDetails(){
		try {
			jObj=new JSONObject(getTask());
			   String fromDate = jObj.getString("fromDate");
			   String endDate = jObj.getString("endDate");
			   String type = jObj.getString("type");
			  janmabhoomiCommitteeVOList = janmabhoomiCommitteeService.getDistrictWiseCommitteeDetails(fromDate,endDate,type);
			  
		} catch (Exception e) {
			LOG.error("Excepting Occured in getDistrictWiseCommitteeDetails() of JanmaBhoomiCommitteeAction ", e);
		}
		return Action.SUCCESS;
	}
	 
	 public String getJbCommitteeStatusCount(){
		 try {
			 jObj = new JSONObject(getTask());
			 janmabhoomiCommitteeVO  =  janmabhoomiCommitteeService.getJbCommitteeStatusCount(jObj.getString("fromDateStr"),jObj.getString("toDateStr"));
		} catch (Exception e) {
			LOG.error("Exception raised at getJbCommitteeStatusCount", e);
		}
		 return Action.SUCCESS;
	 }
	 
	
	public String getJanmabhoomiCommitteeOverview(){ 
		  try{
			  jObj=new JSONObject(getTask());
			  janmabhoomiCommitteeMemberVO = janmabhoomiCommitteeService.getJanmabhoomiCommitteeOverview(jObj.getLong("committeId"),jObj.getString("fromDate"),jObj.getString("toDate"));			  
		  }catch(Exception e){
			  LOG.error("Entered into getJanmabhoomiCommitteeOverview method in JanmaBhoomiCommitteeAction ",e);
		  }
		  return Action.SUCCESS;
	  }
	
	public String saveJanmabhoomiCommitteeMember(){ 
		  try{
			  //jObj=new JSONObject(getTask());
			  resultStatus = janmabhoomiCommitteeService.saveJanmabhoomiCommitteeMember(janmabhoomiCommitteeMemberVO);			  
		  }catch(Exception e){
			  LOG.error("Entered into getJanmabhoomiCommitteeOverview method in JanmaBhoomiCommitteeAction ",e);
		  }
		  return Action.SUCCESS;
	  }
	
	public String getJanmabhoomiCommitteesByLocIdAndCommLvlId(){ 
		  try{
			  jObj=new JSONObject(getTask());
			  String startDate = jObj.getString("fromDate"); 
			  String endDate = jObj.getString("endDate"); 
			  Long locationId = jObj.getLong("locationId"); 
			  Long levelId = jObj.getLong("levelId");
			  Long committeeLvlId = jObj.getLong("committeeLvlId"); 
			  String status = jObj.getString("status"); 
			  janmabhoomiCommitteeVOList = janmabhoomiCommitteeService.getJanmabhoomiCommitteesByLocIdAndCommLvlId(startDate,endDate,locationId,levelId,committeeLvlId,status);			  
		  }catch(Exception e){
			  LOG.error("Entered into getJanmabhoomiCommitteeOverview method in JanmaBhoomiCommitteeAction ",e);
		  }
		  return Action.SUCCESS;
	  }
	
	public String searchByMemberIdOrVoterId(){ 
		  try{
			  jObj=new JSONObject(getTask());
			  janmabhoomiCommitteeMemberVO = janmabhoomiCommitteeService.searchByMemberIdOrVoterId(jObj.getLong("locationLevel"),jObj.getLong("locationValue"),jObj.getString("memberShipCardNo"),jObj.getString("voterCardNo"));			  
		  }catch(Exception e){
			  LOG.error("Entered into searchByMemberIdOrVoterId method in JanmaBhoomiCommitteeAction ",e);
		  }
		  return Action.SUCCESS;
	  }
	
	public String getAllCategories(){ 
		  try{
			  janmabhoomiCommitteeVOList = janmabhoomiCommitteeService.getCategories();
		  }catch(Exception e){
			  LOG.error("Entered into getAllCategories method in JanmaBhoomiCommitteeAction ",e);
		  }
		  return Action.SUCCESS;
	  }
	
	public String getStatewiseCastNamesByCasteCategoryGroupId(){ 
		  try{
			  jObj=new JSONObject(getTask());
			  List<Long> categoryGrouIdsList = new ArrayList<Long>(0); 
		      JSONArray categoryGrouIdValues = jObj.getJSONArray("categoryGrouIdsList");
		      
		        if(categoryGrouIdValues != null && categoryGrouIdValues.length()> 0){
		          for(int i = 0;i<categoryGrouIdValues.length();i++){
		        	  categoryGrouIdsList.add(new Long(categoryGrouIdValues.getInt(i)));
		          }
		        }   
			  janmabhoomiCommitteeVOList = janmabhoomiCommitteeService.getStatewiseCastNamesByCasteCategoryGroupId(categoryGrouIdsList);
		  }catch(Exception e){
			  LOG.error("Entered into getStatewiseCastNamesByCasteCategoryGroupId method in JanmaBhoomiCommitteeAction ",e);
		  }
		  return Action.SUCCESS;
	  }
}
