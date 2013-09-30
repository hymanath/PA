package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.ICrossVotingEstimationService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class PopulateVoterDataAction extends ActionSupport implements ServletRequestAware{
	
	private static final long serialVersionUID = -2193964229664094993L;
	private static final Logger Log = Logger.getLogger(PopulateVoterDataAction.class);
	private HttpServletRequest request;
	private ConstituencyManagementVO constituencyManagementVO;
	private IVotersAnalysisService votersAnalysisService;
	private List<SelectOptionVO> constituencyList;
	private ResultStatus resultStatus;
	private IVoterModificationService voterModificationService;
    private List<SelectOptionVO> allConstituenciesList = new ArrayList<SelectOptionVO>(0);
	private IVoterReportService voterReportService;
	private List<SelectOptionVO> publicationNamesList;
	private IStaticDataService staticDataService;
	private ICrossVotingEstimationService crossVotingEstimationService;
	
	public ICrossVotingEstimationService getCrossVotingEstimationService() {
		return crossVotingEstimationService;
	}
	public void setCrossVotingEstimationService(
			ICrossVotingEstimationService crossVotingEstimationService) {
		this.crossVotingEstimationService = crossVotingEstimationService;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}
	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}

	private HttpSession session;
	private String task;
	JSONObject jObj;
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
	
	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
		public void setServletRequest(HttpServletRequest arg0) {
			
			this.request=arg0;
		}
		public ConstituencyManagementVO getConstituencyManagementVO() {
			return constituencyManagementVO;
		}
		public void setConstituencyManagementVO(
				ConstituencyManagementVO constituencyManagementVO) {
			this.constituencyManagementVO = constituencyManagementVO;
		}
		public IVotersAnalysisService getVotersAnalysisService() {
			return votersAnalysisService;
		}
		public void setVotersAnalysisService(
				IVotersAnalysisService votersAnalysisService) {
			this.votersAnalysisService = votersAnalysisService;
		}

		public IVoterModificationService getVoterModificationService() {
			return voterModificationService;
		}
		public void setVoterModificationService(
				IVoterModificationService voterModificationService) {
			this.voterModificationService = voterModificationService;
		}
		
		public List<SelectOptionVO> getPublicationNamesList() {
			return publicationNamesList;
		}
		public void setPublicationNamesList(List<SelectOptionVO> publicationNamesList) {
			this.publicationNamesList = publicationNamesList;
		}
		public List<SelectOptionVO> getAllConstituenciesList() {
			return allConstituenciesList;
		}
		public void setAllConstituenciesList(List<SelectOptionVO> allConstituenciesList) {
			this.allConstituenciesList = allConstituenciesList;
		}
		public String execute()
		{
			
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
			return ERROR;
			allConstituenciesList = user.getUserAccessVoterConstituencies();
			if(constituencyList == null || constituencyList.isEmpty()){
				Long userID = user.getRegistrationID();
				Long electionYear = new Long(IConstants.PRESENT_ELECTION_YEAR);
				Long electionTypeId = new Long(IConstants.ASSEMBLY_ELECTION_TYPE_ID);
				allConstituenciesList = crossVotingEstimationService.getConstituenciesForElectionYearAndTypeWithUserAccess(userID,electionYear,electionTypeId);
			}
			constituencyList = votersAnalysisService.getConstituenciesFromBoothPublicationVoter();
			constituencyList.add(0,new SelectOptionVO(0L,"Select Constituency"));
			return SUCCESS;
		}
		
		public String insertVotersDataToIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
				
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in insertVotersDataToIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			Boolean hamletChecked = jObj.getBoolean("hamletChecked");
			Boolean hamletBoothChecked = jObj.getBoolean("hamletBoothChecked");
			Long userId =regVO.getRegistrationID();
			resultStatus = votersAnalysisService.insertVotersDataInIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"),userId,hamletChecked,hamletBoothChecked);
			return Action.SUCCESS;
		}
		
		public String insertVotersBasicInfoToIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
				
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in insertVotersDataToIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			Long userId = regVO.getRegistrationID();
			resultStatus = voterReportService.insertVotersBasicInfoToIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"),userId);
			return Action.SUCCESS;
		}
		
		public String insertVotersCasteDataToIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in insertVotersDataToIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			Long userId =  regVO.getRegistrationID();
			Boolean hamletChecked =jObj.getBoolean("hamletChecked");
			Boolean boothChecked=jObj.getBoolean("boothChecked");
			resultStatus = voterReportService.insertVotersCasteDataInIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"),userId,hamletChecked,boothChecked);
			return Action.SUCCESS;
			
		}
		
		
		public String insertVotersPartyDataToIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in insertVotersDataToIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			Long userId =  regVO.getRegistrationID();
			//resultStatus = voterReportService.insertVotersPartyDataToIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"),userId);
			resultStatus = votersAnalysisService.updateVoterStatusInVoterModification(jObj.getLong("id"));
			return Action.SUCCESS;
			
		}
		public String deleteVotersDataFromIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in deleteVotersDataInIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return null;
			//resultStatus = votersAnalysisService.deleteVotersDataFromIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"));
			resultStatus = votersAnalysisService.deleteVoterInfoFromIntermediateTablesByConstituencyId(jObj.getLong("id"), jObj.getLong("publicationDateId"));
					
			return Action.SUCCESS;
		}
		
		public String deleteVotersCastDataFromIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in deleteVotersCastDataFromIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
			if(user == null)
				return null;
			Long userId =  user.getRegistrationID();
			resultStatus = votersAnalysisService.deleteVotersCastDataFromIntermediateTables(jObj.getLong("id"),jObj.getLong("publicationDateId"),userId);
			return Action.SUCCESS;
		}
		
		public String deleteVotersPartyDataFromIntermediateTables(){
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in deleteVotersDataInIntermediateTables() Method, Exception -"+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return null;
			Long userId =  user.getRegistrationID();
			resultStatus = votersAnalysisService.deleteVotersPartyDataFromIntermediateTables(jObj.getLong("id"),jObj.getLong("publicationDateId"),userId);
			return Action.SUCCESS;
		}
		
		public String deletevotermodificationFromIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in deletevotermodificationFromIntermediateTables() Method, Exception -"+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return null;
			resultStatus = voterReportService.deletevotermodificationFromIntermediateTables(jObj.getLong("id"),jObj.getLong("publicationDateId"));
			return Action.SUCCESS;
		}
		
		public String insertVoterModificationDataToIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in insertGenderWiseVoterModifInfoInVoterModificationInfoTable() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return null;
			
			resultStatus = voterModificationService.insertGenderWiseVoterModifInfoInVoterModificationInfoTable(jObj.getLong("id"), jObj.getLong("publicationDateId"));
			return Action.SUCCESS;
		}
		
		public String deleteVoterModifiedData()
		{
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in deletevotermodificationFromIntermediateTables() Method, Exception -"+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
			if(user == null)
				return null;
			resultStatus = voterReportService.deleteVoterModifiedData(jObj.getLong("id"),jObj.getLong("publicationDateId"));
			return Action.SUCCESS;	
		}
		
		public String insertVotersCasteAndPartyDataToIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				Log.error("Exception Occured in insertVotersCasteAndPartyDataToIntermediateTables() Method, Exception - ",e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			Long userId =  null;
			if(regVO.getParentUserId()!=null){
					userId=regVO.getMainAccountId();
				}
				else{
					userId = regVO.getRegistrationID();
				}
			resultStatus = votersAnalysisService.deleteVotersCastDataFromIntermediateTables(jObj.getLong("id"),jObj.getLong("publicationDateId"),userId);
			resultStatus = votersAnalysisService.deleteVotersPartyDataFromIntermediateTables(jObj.getLong("id"),jObj.getLong("publicationDateId"),userId);
			resultStatus = voterReportService.insertVotersPartyDataToIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"),userId);
			resultStatus = voterReportService.insertVotersCasteDataInIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"),userId,false,false);
			return Action.SUCCESS;
			
		}
		
		
		public String getPublicationListForVoterData()
		{
			try{
				jObj = new JSONObject(getTask());
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in getPublicationListForVoterData() method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			
			publicationNamesList = votersAnalysisService.getPublicationListForVoterData(jObj.getLong("id"));
			publicationNamesList.add(0, new SelectOptionVO(0L,"Select Publication Date"));
			
			return Action.SUCCESS;
		}
		
		public String deleteVotersBasicInfoFromIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.error("Exception Occured in deleteVotersBasicInfoFromIntermediateTables() method, Exception - "+e);	
			}
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(regVo == null)
				return null;
			Long userId = regVo.getRegistrationID();
			resultStatus = voterReportService.deleteVotersBasicInfoFromIntermediateTables(jObj.getLong("id"));
			return Action.SUCCESS;
		}
		
		public String insertPreviousEleVotingIntoIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
				
			}catch (Exception e) {
				e.printStackTrace();
				Log.error("Exception Occured in insertVotersDataToIntermediateTables() Method, Exception - "+e);
			}
			HttpSession session = request.getSession();
			RegistrationVO regVO = (RegistrationVO)session.getAttribute("USER");
			if(regVO == null)
				return null;
			Long userId = regVO.getRegistrationID();
			resultStatus = voterReportService.insertVotingTrendzToIntermediateTables(jObj.getLong("id"), jObj.getLong("publicationDateId"),userId);
			return Action.SUCCESS;
		}
		
		public String deletePreviousEleVotingIntoIntermediateTables()
		{
			try{
				jObj = new JSONObject(getTask());
			}
			catch(Exception e)
			{
				e.printStackTrace();
				Log.error("Exception Occured in deletePreviousEleVotingIntoIntermediateTables() method, Exception - "+e);	
			}
			HttpSession session = request.getSession();
			RegistrationVO regVo = (RegistrationVO) session.getAttribute(IConstants.USER);
			if(regVo == null)
				return null;
			Long userId = regVo.getRegistrationID();
			resultStatus = voterReportService.deletePreviousEleVotingIntoIntermediateTables(jObj.getLong("id"));
			return Action.SUCCESS;
		}
		
		
		public String insertConstituencyBasicData()
		{
			try{
				
			 HttpSession session = request.getSession();
				RegistrationVO regVo = (RegistrationVO) session.getAttribute(IConstants.USER);
				if(regVo == null)
					return null;
			 Long userId = regVo.getRegistrationID();
			 jObj = new JSONObject(getTask());
			 if(jObj.getString("task").equalsIgnoreCase("insertConstituencyBasicData"))
			  resultStatus = voterModificationService.insertConstituencyBasicData(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),userId);
			
			 else if(jObj.getString("task").equalsIgnoreCase("deleteConstituencyBasicData"))
				 resultStatus = voterModificationService.deleteConstituencyBasicData(jObj.getLong("constituencyId"),jObj.getLong("publicationId"),userId);
				
			}catch (Exception e) {
			 e.printStackTrace();
			 Log.error(" Exception Occured in insertConstituencyBasicData() method, Exception - "+e);
			}
			return Action.SUCCESS;
		}
		
		
}
