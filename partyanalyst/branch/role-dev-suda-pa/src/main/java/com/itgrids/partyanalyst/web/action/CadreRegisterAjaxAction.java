package com.itgrids.partyanalyst.web.action;

import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.ISessionConstants;
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
	private static final Logger log = Logger.getLogger(CadreRegisterAjaxAction.class);
	
	
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
		if(log.isDebugEnabled())			
			log.debug("In CadreRegisterAjaxAction execute");
		String param=null;
		
		param=getTask();
		if(log.isDebugEnabled())			
			log.debug("param:"+param);		
		session = request.getSession();
		try {
			jObj=new JSONObject(param);
			if(log.isDebugEnabled())			
				log.debug("jObj = "+jObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(jObj.getString("reportLevel").equalsIgnoreCase("state") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			if(log.isDebugEnabled())
				log.debug("In report level state");
			String selectedVal=jObj.getString("selected");
			List<SelectOptionVO> districtNames=cadreManagementService.findDistrictsByState(selectedVal);			
			SelectOptionVO obj = new SelectOptionVO(0L,"Select District");
			districtNames.add(0, obj);
			setNamesList(districtNames);
			try{
				if("official".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.DISTRICTS_O, districtNames);
				} else if("current".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.DISTRICTS, districtNames);
				} else if("cadreLevel".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.DISTRICTS_C, districtNames);
				}
			} catch(NoSuchElementException e)
			{
				e.printStackTrace();
				log.error(e);
			}
			
		}		
		else if(jObj.getString("reportLevel").equalsIgnoreCase("district") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			if(log.isDebugEnabled())
				log.debug("In report level District");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> mandalsNames=cadreManagementService.findMandalsByDistrict(selectedVal);	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Mandal");
			mandalsNames.add(0, obj);
			setNamesList(mandalsNames);
			try 
			{
				if("cadreLevel".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.MANDALS_C, mandalsNames);
				}
			}catch(NoSuchElementException e)
			{
				e.printStackTrace();
				log.error(e);
			}
			
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("constituency") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			if(log.isDebugEnabled())
				log.debug("In report level Constituency");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> constituencynames=regionServiceDataImp.getConstituenciesByDistrictID(new Long(selectedVal));	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Constituency");
			constituencynames.add(0, obj);
			setNamesList(constituencynames);
			try
			{
				if("official".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.CONSTITUENCIES_O, constituencynames);
				} else if("current".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.CONSTITUENCIES, constituencynames);
				} else if("cadreLevel".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.CONSTITUENCIES_C, constituencynames);
				}
			}catch(NoSuchElementException e)
			{
				e.printStackTrace();
				log.error(e);
			}
			
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("mandal") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			if(log.isDebugEnabled())
				log.debug("In report level Mandal");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> villageNames = cadreManagementService.findVillagesByTehsilID(selectedVal);
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Village");
			villageNames.add(0, obj);
			setNamesList(villageNames);	
			try
			{
				if("official".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.VILLAGES_O, villageNames);
				} else if("current".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.VILLAGES, villageNames);
				} else if("cadreLevel".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.VILLAGES_C, villageNames);
				}
			} catch(NoSuchElementException e)
			{
				e.printStackTrace();
				log.error(e);
			}
			
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("village") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			if(log.isDebugEnabled())
				log.debug("In report level Mandal");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> hamletNames = staticDataService.getHamletsForTownship(new Long(selectedVal));	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Hamlet");
			hamletNames.add(0, obj);
			setNamesList(hamletNames);	
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("Constituencies") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			if(log.isDebugEnabled())
				log.debug("In report level Constituencies");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> mandals=regionServiceDataImp.getMandalsByConstituencyID(new Long(selectedVal));	
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Mandal");
			mandals.add(0, obj);
			setNamesList(mandals);
			try 
			{
				if("official".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.MANDALS_O, mandals);
				} else if("current".equals(jObj.getString("addresstype")))
				{
					session.setAttribute(ISessionConstants.MANDALS, mandals);
				}
			} catch(NoSuchElementException e)
			{
				e.printStackTrace();
				log.error(e);
			}	
			
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
				try 
				{
					if("cadreLevel".equals(jObj.getString("addresstype")))
					{
						session.setAttribute(ISessionConstants.STATES_C, stateNames);
					}
				}catch(NoSuchElementException e)
				{
					e.printStackTrace();
					log.error(e);
				}
			
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
			if(jObj.getString("task").equalsIgnoreCase("findTownships"))
			{
				String mandalId = jObj.getString("selected");
				namesList = staticDataService.findTownshipsByTehsilID(new Long(mandalId));
				namesList.add(0,new SelectOptionVO(0L,"Select Village"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getDesignations(){
		String param=null;
		
		param=getTask();
		session = request.getSession();
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
			if(jObj.getString("task").equalsIgnoreCase("designations"))
			{
				String id = jObj.getString("id");
				designationsList = cadreManagementService.getDesignationsInCommittee(new Long(id));
				designationsList.add(0,new SelectOptionVO(0L,"Select Designation"));
				session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS, designationsList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
