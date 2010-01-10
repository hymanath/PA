package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.jfree.util.Log;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.ConstituencyManagementVO;
import com.itgrids.partyanalyst.dto.HamletProblemVO;
import com.itgrids.partyanalyst.dto.LocalLeadersVO;
import com.itgrids.partyanalyst.dto.PoliticalChangesVO;
import com.itgrids.partyanalyst.dto.ProblemManagementDataVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
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
	
	public String getVotersByHamlet()
	{
		String param=null;
		
		param=getTask();
		System.out.println("param:"+param);		
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("findVoters"))
		{
			String hamletId = jObj.getString("selected");
			constituencyManagementVO = new ConstituencyManagementVO();
			constituencyManagementVO.setVoterDetails(constituencyManagementService.getVoterInfo(new Long(hamletId), "2009"));
			constituencyManagementVO.setVotersByHouseNos(constituencyManagementService.getVoterHouseDetails(new Long(hamletId), "2009"));
			VoterCastInfoVO votersByCast  = constituencyManagementService.getVotersCastInfoForHamlet(new Long(hamletId), "2009");
			constituencyManagementVO.setVoterCastInfodetails(votersByCast);								
			
			
			problemManagementDataVO = problemManagementService.getProblemsForAHamlet(new Long(hamletId), "2009");
			ResultStatus resultStatus = problemManagementDataVO.getResultStatus();
			if(resultStatus.getResultCode() == 0)
				constituencyManagementVO.setHamletProblems(problemManagementDataVO.getHamletProblems());
			for(HamletProblemVO problems:problemManagementDataVO.getHamletProblems()){
				Log.debug("Problems" + problems.getProblemDesc());
			}
			if(resultStatus.getResultCode() == 1)
				Log.debug("No Problems Data Found....");
			LocalLeadersVO localLeadersVO1 = new LocalLeadersVO();
			LocalLeadersVO localLeadersVO2 = new LocalLeadersVO();
			LocalLeadersVO localLeadersVO3 = new LocalLeadersVO();
			LocalLeadersVO localLeadersVO4 = new LocalLeadersVO();
			LocalLeadersVO localLeadersVO5 = new LocalLeadersVO();
			LocalLeadersVO localLeadersVO6 = new LocalLeadersVO();
			
			localLeadersVO1.setName("Ravindranath Reddy");
			localLeadersVO1.setOccupation("Farmer");
			localLeadersVO1.setPosition("Sarpanch");
			localLeadersVO1.setInfluenceScope("Village");
			localLeadersVO1.setContactNumber("9848012345");
			
			localLeadersVO2.setName("Musthaq Ahmed");
			localLeadersVO2.setOccupation("Self Employed");
			localLeadersVO2.setPosition("N/A");
			localLeadersVO2.setInfluenceScope("Village");
			localLeadersVO2.setContactNumber("9848011315");
			
			localLeadersVO3.setName("Cristopher");
			localLeadersVO3.setOccupation("Teacher");
			localLeadersVO3.setPosition("N/A");
			localLeadersVO3.setInfluenceScope("Hamlet");
			localLeadersVO3.setContactNumber("9949011315");

			localLeadersVO4.setName("Sarojini");
			localLeadersVO4.setOccupation("Self Employed");
			localLeadersVO4.setPosition("N/A");
			localLeadersVO4.setInfluenceScope("Village");
			localLeadersVO4.setContactNumber("9844011325");
			
			localLeadersVO5.setName("Sai Prakash");
			localLeadersVO5.setOccupation("Farmer");
			localLeadersVO5.setPosition("Ward Member");
			localLeadersVO5.setInfluenceScope("Hamlet");
			localLeadersVO5.setContactNumber("9848022315");
			
			localLeadersVO6.setName("Musthaq Ahmed");
			localLeadersVO6.setOccupation("Self Employed");
			localLeadersVO6.setPosition("N/A");
			localLeadersVO6.setInfluenceScope("Village");
			localLeadersVO6.setContactNumber("9848011315");
			
			localLeaders = new ArrayList<LocalLeadersVO>();
			
			localLeaders.add(localLeadersVO1);
			localLeaders.add(localLeadersVO2);
			localLeaders.add(localLeadersVO3);
			localLeaders.add(localLeadersVO4);
			localLeaders.add(localLeadersVO5);
			localLeaders.add(localLeadersVO6);
			
			constituencyManagementVO.setLocalLeaders(localLeaders);
			
			PoliticalChangesVO politicalChangesVO1 = new PoliticalChangesVO();
			PoliticalChangesVO politicalChangesVO2 = new PoliticalChangesVO();
			
			politicalChangesVO1.setDate("09-12-2009");
			politicalChangesVO1.setDescription("Description about change");
			politicalChangesVO1.setImpact("Impact");
			
			politicalChangesVO2.setDate("29-12-2009");
			politicalChangesVO2.setDescription("Description about change");
			politicalChangesVO2.setImpact("Impact");
			
			politicalChanges = new ArrayList<PoliticalChangesVO>();
			politicalChanges.add(politicalChangesVO1);
			politicalChanges.add(politicalChangesVO2);
			
			constituencyManagementVO.setPoliticalChanges(politicalChanges);
			
			
		}
		
		return SUCCESS;
	}
	
	public String execute() throws Exception{
		
		
		return SUCCESS;
	}

	
	
}
