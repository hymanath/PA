package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class VoterReportAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	private static final Logger log = Logger.getLogger(VoterReportAction.class);
	private HttpSession session;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> parliamentConstituencyList;
	
	private IRegionServiceData regionServiceDataImp;
	private IStaticDataService staticDataService; 
	private CadreManagementService cadreManagementService;
	
	JSONObject jObj = null;
	private String task;
	private VoterReportVO voterReportVO;
	private IVoterReportService voterReportService;
	
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
	public List<SelectOptionVO> getConstituencyList() {
		return constituencyList;
	}
	public void setConstituencyList(List<SelectOptionVO> constituencyList) {
		this.constituencyList = constituencyList;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}
	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}
	public CadreManagementService getCadreManagementService() {
		return cadreManagementService;
	}
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	public List<SelectOptionVO> getParliamentConstituencyList() {
		return parliamentConstituencyList;
	}
	public void setParliamentConstituencyList(
			List<SelectOptionVO> parliamentConstituencyList) {
		this.parliamentConstituencyList = parliamentConstituencyList;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public VoterReportVO getVoterReportVO() {
		return voterReportVO;
	}
	public void setVoterReportVO(VoterReportVO voterReportVO) {
		this.voterReportVO = voterReportVO;
	}
	public IVoterReportService getVoterReportService() {
		return voterReportService;
	}
	public void setVoterReportService(IVoterReportService voterReportService) {
		this.voterReportService = voterReportService;
	}
	public String execute()
	{
		try{
			
			session = request.getSession();
			RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
			if(regVO==null)
				return ERROR;
			String accessType = regVO.getAccessType();
			Long accessValue= new Long(regVO.getAccessValue());
			
			stateList = new ArrayList<SelectOptionVO>();
			districtList = new ArrayList<SelectOptionVO>();
			constituencyList = new ArrayList<SelectOptionVO>();
			if("MLA".equals(accessType))
			{
				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByConstituencyID(accessValue);
				stateList.add(list.get(0));			
				districtList.add(list.get(1));			
				constituencyList.add(list.get(2));
				
			}else if("COUNTRY".equals(accessType))
			{
				stateList = cadreManagementService.findStatesByCountryID(accessValue.toString());
				stateList.add(0,new SelectOptionVO(0L, "Select State"));			
				
			}else if("STATE".equals(accessType)){
				
				String name = cadreManagementService.getStateName(accessValue);
				SelectOptionVO obj2 = new SelectOptionVO();
				obj2.setId(accessValue);
				obj2.setName(name);			
				stateList.add(obj2);
				districtList = staticDataService.getDistricts(accessValue);
				districtList.add(0,new SelectOptionVO(0l,"Select District"));	
				
			}else if("DISTRICT".equals(accessType)){
							
				List<SelectOptionVO> list = regionServiceDataImp.getStateDistrictByDistrictID(accessValue);
				stateList.add(list.get(0));			
				districtList.add(list.get(1));
				constituencyList = regionServiceDataImp.getConstituenciesByDistrictID(accessValue);
				constituencyList.add(0, new SelectOptionVO(0l,"Select Constituency"));
				
			} else if("MP".equals(accessType)){
				
				ConstituencyInfoVO constituencyInfoVO = new ConstituencyInfoVO();
				stateList = regionServiceDataImp.getStateByParliamentConstituencyID(accessValue);
				constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(accessValue);
				constituencyList = constituencyInfoVO.getAssembyConstituencies();
				constituencyList.add(0,new SelectOptionVO(0l,"Select Constituency"));
				parliamentConstituencyList.add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
				
			}
			
		}catch(Exception e)
		{
			log.error("Error Occured in the execute() of VoterReportAction class and Error is -- "+e);
			return null;
		}
		return SUCCESS;
	}
	
	public String ajaxHandler()
	{
		try {
			jObj = new JSONObject(getTask());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String range = jObj.getString("range");
		String rangeValue = jObj.getString("rangeValue");
		
		voterReportVO = voterReportService.getVoterDetailsInaLocation(range,Long.parseLong(rangeValue));
		return Action.SUCCESS;
	}

}
