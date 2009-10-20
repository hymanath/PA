package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.service.CadreManagementService;
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
	
	
	private CadreManagementService cadreManagementService;
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
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
	
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
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
		if(jObj.getString("reportLevel").equalsIgnoreCase("state"))
		{
			System.out.println("In report level state");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> districtNames=cadreManagementService.findDistrictsByState(selectedVal);	
			
			setNamesList(districtNames);	
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("district"))
		{
			System.out.println("In report level District");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> mandalsNames=cadreManagementService.findMandalsByDistrict(selectedVal);	
			
			
			setNamesList(mandalsNames);	
		}
		else if(jObj.getString("reportLevel").equalsIgnoreCase("mandal"))
		{
			System.out.println("In report level Mandal");
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> villageNames=cadreManagementService.findVillagesByTehsilID(selectedVal);	
			
			setNamesList(villageNames);	
		}else if(jObj.getString("reportLevel").equalsIgnoreCase("cadreLevel"))
		{
			String value=jObj.getString("value");
			String id=jObj.getString("id");
			if("COUNTRY".equalsIgnoreCase(value)){
				
			} else if("COUNTRY".equalsIgnoreCase(value)){
				List<SelectOptionVO> countryNames=new ArrayList<SelectOptionVO>();
				SelectOptionVO countrySelectOptionVO = new SelectOptionVO();
				countrySelectOptionVO.setId(1L);
				countrySelectOptionVO.setName("India");
				countryNames.add(countrySelectOptionVO);
				setNamesList(countryNames);
				
			} else if("STATE".equalsIgnoreCase(value)){
				List<SelectOptionVO> stateNames=new ArrayList<SelectOptionVO>();
				SelectOptionVO stateSelectOptionVO = new SelectOptionVO();
				stateSelectOptionVO.setId(1L);
				stateSelectOptionVO.setName("Andhra Pradesh");
				stateNames.add(stateSelectOptionVO);
				setNamesList(stateNames);
			} else if("DISTRICT".equalsIgnoreCase(value)){

				List<SelectOptionVO> districtNames=cadreManagementService.findDistrictsByState("1");	
				
				setNamesList(districtNames);
			} else if("MANDAL".equalsIgnoreCase(value)){
				List<SelectOptionVO> mandalsNames=cadreManagementService.findMandalsByDistrict("15");			
				setNamesList(mandalsNames);	
			}
		}
		return Action.SUCCESS;
	}
}
