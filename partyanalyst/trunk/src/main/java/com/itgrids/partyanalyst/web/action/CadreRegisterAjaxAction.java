package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class CadreRegisterAjaxAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SelectOptionVO> namesList;
	JSONObject jObj = null;
	private String task = null;
	private HttpServletRequest request;
	private List<SelectOptionVO> designationsList;
	private CadreManagementService cadreManagementService;
	private RegionServiceDataImp regionServiceDataImp;
	private IConstituencyManagementService constituencyManagementService;
	private IStaticDataService staticDataService;
	private HttpSession session;
	
	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public IConstituencyManagementService getConstituencyManagementService() {
		return constituencyManagementService;
	}

	public void setConstituencyManagementService(
			IConstituencyManagementService constituencyManagementService) {
		this.constituencyManagementService = constituencyManagementService;
	}

	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public List<SelectOptionVO> getNamesList() {
		return namesList;
	}

	public void setNamesList(List<SelectOptionVO> namesList) {
		this.namesList = namesList;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}	
	
	public List<SelectOptionVO> getDesignationsList() {
		return designationsList;
	}

	public void setDesignationsList(List<SelectOptionVO> designationsList) {
		this.designationsList = designationsList;
	}	

	public String execute() throws Exception
	{
		System.out.println("In execute ****************");
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
		/*if(jObj.getString("reportLevel").equalsIgnoreCase("Country")){
			
		}
		else */
		if(jObj.getString("reportLevel").equalsIgnoreCase("state") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			System.out.println("In report level state");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> districtNames=cadreManagementService.findDistrictsByState(selectedVal);			
			SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
			districtNames.add(0, obj);
			setNamesList(districtNames);	
		}		
		else if(jObj.getString("reportLevel").equalsIgnoreCase("district") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			System.out.println("In report level District");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> mandalsNames=cadreManagementService.findMandalsByDistrict(selectedVal);	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Mandal");
			mandalsNames.add(0, obj);
			
			setNamesList(mandalsNames);	
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("constituency") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			System.out.println("In report level Constituency");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> constituencynames=regionServiceDataImp.getConstituenciesByDistrictID(new Long(selectedVal));	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Constituency");
			constituencynames.add(0, obj);
			setNamesList(constituencynames);	
			
			
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("mandal") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			System.out.println("In report level Mandal");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> villageNames = cadreManagementService.findVillagesByTehsilID(selectedVal);
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Village");
			villageNames.add(0, obj);
			setNamesList(villageNames);	
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("village") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			System.out.println("In report level Mandal");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> hamletNames = staticDataService.getHamletsForTownship(new Long(selectedVal));	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Hamlet");
			hamletNames.add(0, obj);
			setNamesList(hamletNames);	
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("Constituencies") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			System.out.println("In report level Constituencies");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> mandals=regionServiceDataImp.getMandalsByConstituencyID(new Long(selectedVal));	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Mandal");
			mandals.add(0, obj);
			setNamesList(mandals);	
		}
		else if((jObj.getString("reportLevel").equalsIgnoreCase("State") || 
					jObj.getString("reportLevel").equalsIgnoreCase("District") || 
					jObj.getString("reportLevel").equalsIgnoreCase("Constituency") || 
					jObj.getString("reportLevel").equalsIgnoreCase("Mandal") || 
					jObj.getString("reportLevel").equalsIgnoreCase("Village")) && jObj.getString("type").equalsIgnoreCase("cadreLevel"))
		{
			String value=jObj.getString("reportLevel");
			String id=jObj.getString("type");
			System.out.println("value = "+value+"id = "+id);
			
						
				List<SelectOptionVO> stateNames=cadreManagementService.findStatesByCountryID("1");	
				SelectOptionVO obj = new SelectOptionVO(0L,"Select State");
				stateNames.add(0, obj);
				setNamesList(stateNames);
			/*
				List<SelectOptionVO> districtNames=cadreManagementService.findDistrictsByState(id);
				setNamesList(districtNames);*/
			
		}//cadreLevelDistrict
		else if(jObj.getString("reportLevel").equalsIgnoreCase("cadreLevelDistrict"))
		{
			String value=jObj.getString("value");
			String id=jObj.getString("id");
			List<SelectOptionVO> mandalNames=cadreManagementService.findMandalsByDistrict(id);	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Mandal");
			mandalNames.add(0, obj);
			setNamesList(mandalNames);
		}
		return Action.SUCCESS;
	}
	
	public String getTownshipsForMandal(){
		String param=null;
		
		param=getTask();
		System.out.println("param:"+param);		
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("findTownships"))
		{
			String mandalId = jObj.getString("selected");
			namesList = staticDataService.findTownshipsByTehsilID(new Long(mandalId));
			namesList.add(0,new SelectOptionVO(0L,"Select Village"));
		}
		return SUCCESS;
	}
	public String getDesignations(){
		String param=null;
		
		param=getTask();
		session = request.getSession();
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		if(regVO==null)
			return ERROR;
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("designations"))
		{
			String id = jObj.getString("id");
			designationsList = cadreManagementService.getDesignationsInCommittee(regVO.getParty(),new Long(id));
			designationsList.add(0,new SelectOptionVO(0L,"Select Village"));
		}
		return SUCCESS;
	}
}
