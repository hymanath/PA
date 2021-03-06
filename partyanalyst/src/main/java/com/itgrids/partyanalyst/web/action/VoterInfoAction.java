package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.HamletProblemVO;
import com.itgrids.partyanalyst.dto.LocalLeadersVO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.TotalMPTCMandalLeaderVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IProblemManagementService;
import com.opensymphony.xwork2.ActionSupport;

public class VoterInfoAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long hamletId;
	private ConstituencyManagementVO constituencyManagementVO;
	private IConstituencyManagementService constituencyManagementService;
	private HttpServletRequest request;
	private List<VoterHouseInfoVO> votersByHouseNos;
	private List<LocalLeadersVO> localLeaders;
	private List<PoliticalChangesVO> politicalChanges;
	private List<HamletProblemVO> hamletProblems;
	private ProblemManagementDataVO problemManagementDataVO;
	private IProblemManagementService problemManagementService;
	private VotersInfoForMandalVO votersInfoForMandalVO; 
	
	JSONObject jObj = null;
	private String task = null;
	
	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Long getHamletId() {
		return hamletId;
	}

	public void setHamletId(Long hamletId) {
		this.hamletId = hamletId;
	}

	public ProblemManagementDataVO getProblemManagementDataVO() {
		return problemManagementDataVO;
	}

	public void setProblemManagementDataVO(
			ProblemManagementDataVO problemManagementDataVO) {
		this.problemManagementDataVO = problemManagementDataVO;
	}

	public IConstituencyManagementService getConstituencyManagementService() {
		return constituencyManagementService;
	}

	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}

	public ConstituencyManagementVO getConstituencyManagementVO() {
		return constituencyManagementVO;
	}

	public void setConstituencyManagementVO(
			ConstituencyManagementVO constituencyManagementVO) {
		this.constituencyManagementVO = constituencyManagementVO;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
		
	public List<VoterHouseInfoVO> getVotersByHouseNos() {
		return votersByHouseNos;
	}

	public void setVotersByHouseNos(List<VoterHouseInfoVO> votersByHouseNos) {
		this.votersByHouseNos = votersByHouseNos;
	}
	
	public List<LocalLeadersVO> getLocalLeaders() {
		return localLeaders;
	}

	public void setLocalLeaders(List<LocalLeadersVO> localLeaders) {
		this.localLeaders = localLeaders;
	}
	
	public List<PoliticalChangesVO> getPoliticalChanges() {
		return politicalChanges;
	}

	public void setPoliticalChanges(List<PoliticalChangesVO> politicalChanges) {
		this.politicalChanges = politicalChanges;
	}
	
	public List<HamletProblemVO> getHamletProblems() {
		return hamletProblems;
	}

	public void setHamletProblems(List<HamletProblemVO> hamletProblems) {
		this.hamletProblems = hamletProblems;
	}

	public IProblemManagementService getProblemManagementService() {
		return problemManagementService;
	}

	public void setProblemManagementService(IProblemManagementService problemManagementService) {
		this.problemManagementService = problemManagementService;
	}
	
	public VotersInfoForMandalVO getVotersInfoForMandalVO() {
		return votersInfoForMandalVO;
	}

	public void setVotersInfoForMandalVO(VotersInfoForMandalVO votersInfoForMandalVO) {
		this.votersInfoForMandalVO = votersInfoForMandalVO;
	}

	public String getVotersByHamlet()
	{
		String param=null;
		
		param=getTask();
		LOG.info("param:"+param);		
		
		try {
			jObj=new JSONObject(param);
			LOG.info("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("findVoters"))
		{
			String hamletId = jObj.getString("selected");
			String checkedEle = jObj.getString("checkedele");
			
			String constituencyId=jObj.getString("MANDAL");
			constituencyManagementVO = new ConstituencyManagementVO();
			//constituencyManagementVO.setVoterDetails(constituencyManagementService.getVoterInfo(Long.valueOf(hamletId), "2009", 0l, 20000, false));
			/*constituencyManagementVO.setVotersByHouseNos(constituencyManagementService.getVoterHouseDetails(Long.valueOf(hamletId), "2009"));*/
			
		
			
			/*VoterCastInfoVO votersByCast  = constituencyManagementService.getVotersCastInfoForHamlet(Long.valueOf(hamletId), "2009");
			constituencyManagementVO.setVoterCastInfodetails(votersByCast);		
			*/
			
			
			//added for Local Cast statics
			if(checkedEle.equalsIgnoreCase("Panchayat"))
			{
			VoterCastInfoVO votersByCast  = constituencyManagementService.getVotersCastInfoForPanchayat(Long.valueOf(hamletId), "2009");
			constituencyManagementVO.setVoterCastInfodetails(votersByCast);
			}
			if(checkedEle.equalsIgnoreCase("Pollingstation"))
			{
				VoterCastInfoVO votersByCast  = constituencyManagementService.getVotersCastInfoForPollingStation(Long.valueOf(hamletId), "2009");
				constituencyManagementVO.setVoterCastInfodetails(votersByCast);
			}
			
			
			problemManagementDataVO = problemManagementService.getProblemsForAHamlet(Long.valueOf(hamletId), "2009");
			ResultStatus resultStatus = problemManagementDataVO.getResultStatus();
			if(resultStatus.getResultCode() == 0)
				constituencyManagementVO.setHamletProblems(problemManagementDataVO.getHamletProblems());
			for(HamletProblemVO problems:problemManagementDataVO.getHamletProblems()){
				LOG.debug("Problems" + problems.getProblemDesc());
			}
			if(resultStatus.getResultCode() == 1)
				LOG.debug("No Problems Data Found....");
						
			PoliticalChangesVO politicalChangesVO1 = new PoliticalChangesVO();
			//PoliticalChangesVO politicalChangesVO2 = new PoliticalChangesVO();
			
			politicalChangesVO1.setOccuredDate("09-12-2009");
			politicalChangesVO1.setDescription("Ex-Sarpanch Vijaya Bhaskar and his cadre has joined new Party from congress Party!");
			politicalChangesVO1.setImpact("Congress");
			/*
			politicalChangesVO2.setDate("29-12-2009");
			politicalChangesVO2.setDescription("Opposition leader arranged a free health checkup camp");
			politicalChangesVO2.setImpact("Impact");
			*/
			politicalChanges = new ArrayList<PoliticalChangesVO>();
			politicalChanges.add(politicalChangesVO1);
			//politicalChanges.add(politicalChangesVO2);
			
			constituencyManagementVO.setPoliticalChanges(politicalChanges);
			
			String mandalID = jObj.getString("MANDAL");
			
			TotalMPTCMandalLeaderVO   mptcMandalLeaders = constituencyManagementService.getMPTCElectionResultForMandal(Long.valueOf(mandalID));
			constituencyManagementVO.setTotalMPTCMandalLeaderVO(mptcMandalLeaders);
		}
		
		else if(jObj.getString("task").equalsIgnoreCase("findVotersByConstituencyID"))
		{
			constituencyManagementVO = new ConstituencyManagementVO();
			String constituencyId = jObj.getString("selected");
			String checkedEle = jObj.getString("checkedele");
			String flag = jObj.getString("flag");
			if(checkedEle.equalsIgnoreCase("Assembly"))
			{
				VoterCastInfoVO votersByCast  = constituencyManagementService.getVotersCastInfoForAssembly(Long.valueOf(constituencyId), "2009");
				constituencyManagementVO.setVoterCastInfodetails(votersByCast);
			}
			if(checkedEle.equalsIgnoreCase("Mandal") && Long.valueOf(flag)== -1)
			{
				VoterCastInfoVO votersByCast  = constituencyManagementService.getVotersCastInfoForMandal(Long.valueOf(constituencyId), "2009");
				constituencyManagementVO.setVoterCastInfodetails(votersByCast);
			}
			//urban
			if(checkedEle.equalsIgnoreCase("Mandal") && Long.valueOf(flag)!= -1)
			{
				
				VoterCastInfoVO votersByCast  = constituencyManagementService.getVotersCastInfoForUrban(Long.valueOf(constituencyId), "2009");
				constituencyManagementVO.setVoterCastInfodetails(votersByCast);
			}
			
			
		}
		//Important Families
		else if(jObj.getString("task").equalsIgnoreCase("findVotersForImp"))
		{
			String hamletId = jObj.getString("selected");
			String checkedEle = jObj.getString("checkedele");
			
			String constituencyId=jObj.getString("MANDAL");
			constituencyManagementVO = new ConstituencyManagementVO();
			//added for Important Families
			constituencyManagementVO.setVotersByHouseNos(constituencyManagementService.getVoterHouseInfoDetails(Long.valueOf(hamletId), "2009",checkedEle));
			constituencyManagementVO.setVotersCount(constituencyManagementService.getVoterHouseDetailsForPanchayat(Long.valueOf(hamletId), "2009",checkedEle));
			constituencyManagementVO.setAgewisevotersCount(constituencyManagementService.getVoterAgeDetailsForPanchayat(Long.valueOf(hamletId), "2009",checkedEle));
			
			
			
		}
		
		return SUCCESS;
	}
	
	
	public String execute() throws Exception{
		
		
		return SUCCESS;
	}

	@SuppressWarnings("unused")
	public String displayPerameters(){
	Long hamletId = Long.parseLong(request.getParameter("hamletId"));
	Integer startIndex = Integer.parseInt(request.getParameter("startIndex"));
	Integer results = Integer.parseInt(request.getParameter("results"));
	String order = request.getParameter("dir");
	String columnName = request.getParameter("sort");
	//long partNo = Long.parseLong(request.getParameter("partNo"));
	Boolean isVoter = Boolean.parseBoolean(request.getParameter("isVoter"));
	String checkedele = request.getParameter("checkedele");
	List<VoterVO> voters = null;

	List<VoterHouseInfoVO> voterHouses = null;
	List<Object[]> votersForPollingStation = null;
	String pollingstation = null;

	constituencyManagementVO = new ConstituencyManagementVO();

	if(isVoter == true && checkedele.equalsIgnoreCase("pollingstation"))

	voters = constituencyManagementService.getVoterInfoForPollingStation(hamletId, "2009", startIndex, results, order, columnName);
	else
	voters = constituencyManagementService.getVoterInfo(hamletId, "2009", startIndex, results, order, columnName);




	// votersForPollingStation = constituencyManagementService.getVoterInfoForPollingStation(hamletId, "2009",hamletId, startIndex, results, order, columnName);


	/*else
	voterHouses = constituencyManagementService.getVoterHouseDetails(hamletId, "2009", startIndex, results, order, columnName);
	*/
	constituencyManagementVO.setVotersByHouseNos(voterHouses);
	constituencyManagementVO.setVoterDetails(voters);

	Long totalVoters = 0l;
	if(voters != null && voters.size() > 0)
	totalVoters = voters.get(0).getTotalVoters();
	if(voterHouses != null && voterHouses.size() > 0)
	totalVoters = voterHouses.get(0).getTotalHousesCount();

	constituencyManagementVO.setVoterDetailsCount(totalVoters);

	return SUCCESS;
	}

	public String getBasicVotersInfo()
	{
		String param = null;
		
		param = getTask();
		
		try{
			jObj=new JSONObject(param);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		Long constituencyId = jObj.getLong("constituencyId");
		String checkedele = jObj.getString("checkedele");
		String flag = jObj.getString("flag");
		 votersInfoForMandalVO = constituencyManagementService.getBasicVotersInfo(constituencyId,"2009",checkedele,Long.valueOf(flag));
		
		return SUCCESS;
	}
	
	
}
