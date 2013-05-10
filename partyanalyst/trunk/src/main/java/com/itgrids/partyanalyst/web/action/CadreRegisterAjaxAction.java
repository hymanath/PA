package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IConstituencyManagementService;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.service.impl.RegionServiceDataImp;
import com.itgrids.partyanalyst.utils.IConstants;
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
	private IVotersAnalysisService votersAnalysisService;
	private IStaticDataService staticDataService;
	private HttpSession session;
	private List<SelectOptionVO> resultList;
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

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	
	

	public List<SelectOptionVO> getResultList() {
		return resultList;
	}

	public void setResultList(List<SelectOptionVO> resultList) {
		this.resultList = resultList;
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
		} catch (ParseException e) {
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
			String selectedradioEle = jObj.getString("checkedele");
			if(selectedradioEle.equalsIgnoreCase("village"))
			{
			List<SelectOptionVO> hamletNames = staticDataService.getHamletsForTownship(new Long(selectedVal));
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Hamlet");
			hamletNames.add(0, obj);
			setNamesList(hamletNames);	
			}
			if(selectedradioEle.equalsIgnoreCase("panchayat"))
			{
			List<SelectOptionVO> hamletNames = staticDataService.getHamletsForPanchayath(new Long(selectedVal));
			SelectOptionVO obj = new SelectOptionVO(0L,"Select Hamlet");
			hamletNames.add(0, obj);
			setNamesList(hamletNames);	
			}
			
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("Constituencies") && jObj.getString("type").equalsIgnoreCase("cadreDetails"))
		{
			if(log.isDebugEnabled())
				log.debug("In report level Constituencies");
			String selectedVal=jObj.getString("selected");
			
			//List<SelectOptionVO> mandals=regionServiceDataImp.getMandalsByConstituencyID(new Long(selectedVal));
			
			List<SelectOptionVO> mandals= regionServiceDataImp.getSubRegionsInConstituency(new Long(selectedVal), IConstants.PRESENT_YEAR, null);
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
					if("cadreLevel".equals(jObj.getString("type")))
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
	public String AjaxHandler()
	{
		String param=null;
		
		param=getTask();
		System.out.println("param:"+param);		
		
		try {
			jObj=new JSONObject(param);
			System.out.println("jObj = "+jObj);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		 if(jObj.getString("task").equalsIgnoreCase("getPanchayat"))
			{
				String selectedVal=jObj.getString("selected");
				String checkedVal =jObj.getString("checkedele"); 
				if(checkedVal.equalsIgnoreCase("panchayat"))
				{
					/*//constituencyWise
				designationsList = staticDataService.getPanchayatiesByConstituencyId(new Long(selectedVal));*/
				//mandalwise
				designationsList = staticDataService.getPanchayatiesByMandalId(new Long(selectedVal));
				designationsList.add(0,new SelectOptionVO(0l,"Select Panchayat"));
				}
				if(checkedVal.equalsIgnoreCase("pollingstation"))
				{
					/*//constituency
				designationsList = staticDataService.getBoothsByConstituencyId(new Long(selectedVal));*/
				//mandal wise polling station
				designationsList = staticDataService.getBoothsByMandalId(new Long(selectedVal),jObj.getLong("constituencyId"));
				designationsList.add(0,new SelectOptionVO(0l,"Select Polling Station"));
				}
				
				if(checkedVal.equalsIgnoreCase("pollingstationByPublication"))
				{
					if(jObj.getString("type").equalsIgnoreCase("mandal"))
					   designationsList = staticDataService.getBoothsInAMandalIdByPublication(new Long(selectedVal),jObj.getLong("publicationValue"),jObj.getLong("constituencyId"));
					else
					   designationsList = votersAnalysisService.getBoothsInMunicipality(new Long(selectedVal),jObj.getLong("publicationValue"),jObj.getLong("constituencyId"));
					designationsList.add(0,new SelectOptionVO(0l,"Select Polling Station"));
				}
			}
		 return SUCCESS;
				
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
			String selectedradioEle = jObj.getString("checkedele");
			if(selectedradioEle.equalsIgnoreCase("village"))
			{
			namesList = staticDataService.findTownshipsByTehsilID(new Long(mandalId));
			namesList.add(0,new SelectOptionVO(0L,"Select Village"));
			}
			if(selectedradioEle.equalsIgnoreCase("panchayat"))
			{
			namesList = staticDataService.getPanchayathiesByTehsilId(new Long(mandalId));
			namesList.add(0,new SelectOptionVO(0L,"Select Panchayath"));
			}
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
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		if(jObj.getString("task").equalsIgnoreCase("designations"))
		{
			String id = jObj.getString("id");
			designationsList = cadreManagementService.getDesignationsInCommittee(new Long(id));
			designationsList.add(0,new SelectOptionVO(0L,"Select Designation"));
			session.setAttribute(ISessionConstants.COMMITTEE_DESIGNATIONS, designationsList);
		}
		return SUCCESS;
	}
	
	public String getReportLevelDetails()
	
	{
		String param=null;
		
		param=getTask();
		session = request.getSession();
		
		try {
			log.debug("entered into getConstiReltaedData() in CadreRegistrationAjaxAction");
			jObj=new JSONObject(param);
			if(jObj.getString("task").equalsIgnoreCase("getReportLevelDetails"))
			{
				String type   = jObj.getString("type");
				Long level    = jObj.getLong("level");
				Long id       = jObj.getLong("id");
				resultList    = staticDataService.getSelectedLevelDetails(type,level,id);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getSelectedMandalOrPanchayatData"))
			{
				String type   = jObj.getString("type");
				Long level    = jObj.getLong("level");
				String[] selectedValues = jObj.getString("values").split(",");
				List<Long> ids = new ArrayList<Long>();
				for (String parm : selectedValues) {
					ids.add(Long.valueOf(parm.trim()));
				}
				resultList    = staticDataService.getPanchayatsOrBoothsForSelectedLevel(type,level,ids);
			}
			else if(jObj.getString("task").equalsIgnoreCase("getConstituencyType"))
			{
				Long constituencyId = jObj.getLong("constituencyId");
				resultList = staticDataService.getConstituencyType(constituencyId);
			}
			
		}
		catch (Exception e) {
			log.error("exception raised in getConstiReltaedData() in CadreRegistrationAjaxAction", e);
		}
		return SUCCESS;
	}
	
}
