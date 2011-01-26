package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.opensymphony.xwork2.Action;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IConstituencySearchService;
import com.itgrids.partyanalyst.service.IRegionServiceData;

public class PartyResultScopeAction {

	private List<SelectOptionVO> namesList;
	JSONObject jObj = null,respObj=null;
	private String task = null;
	private IConstituencySearchService constituencySearchService=null;
	private IRegionServiceData regionServiceDataImp=null;
	
	public List<SelectOptionVO> getNamesList() {
		return namesList;
	}

	public void setNamesList(List<SelectOptionVO> namesList) {
		this.namesList = namesList;
	}
	

	public String getTask() {
		System.out.println("In getTask");
		return task;
	}

	public void setTask(String task) {
		System.out.println("In setTask");
		this.task = task;
	}


	public IConstituencySearchService getConstituencySearchService() {
		return constituencySearchService;
	}

	public void setRegionServiceDataImp(IRegionServiceData service) {
		regionServiceDataImp = service;
	}
	
	public void setConstituencySearchService(
			IConstituencySearchService constituencySearchService) {
		this.constituencySearchService = constituencySearchService;
	}
	
	
	//JSONObject jObj;
	
	public String execute() 
	{
		System.out.println("In execute method + Election scope level action");	
		if(regionServiceDataImp==null){
			System.out.println("regionServiceData is null Narender");
		}
		if(constituencySearchService==null){
			System.out.println("constituencySearchService is null Narender");
		}
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
		
		if((jObj.getString("reportLevel").equalsIgnoreCase("State") || jObj.getString("reportLevel").equalsIgnoreCase("District") 
				|| jObj.getString("reportLevel").equalsIgnoreCase("Constituency") || jObj.getString("reportLevel").equalsIgnoreCase("MLA") 
				|| jObj.getString("reportLevel").equalsIgnoreCase("MP")) 
				&& jObj.getString("selected").equalsIgnoreCase("null"))
		{
			System.out.println("Search criteria = State");				
			List<SelectOptionVO> stateNames=new ArrayList<SelectOptionVO>();			
			SelectOptionVO stateSelectOptionVO1 = new SelectOptionVO();
			SelectOptionVO stateSelectOptionVO2 = new SelectOptionVO();
			SelectOptionVO stateSelectOptionVO3 = new SelectOptionVO();
			
			stateSelectOptionVO1.setId(new Long(1));
			stateSelectOptionVO1.setName("Andhra Pradesh");
			
			stateSelectOptionVO3.setId(new Long(12));
			stateSelectOptionVO3.setName("Karnataka");
			
			stateSelectOptionVO2.setId(new Long(24));
			stateSelectOptionVO2.setName("Tamil Nadu");
			
			
			stateNames.add(stateSelectOptionVO1);
			stateNames.add(stateSelectOptionVO2);
			stateNames.add(stateSelectOptionVO3);
			setNamesList(stateNames);			
		}		
		else if (jObj.getString("reportLevel").equalsIgnoreCase("District") && jObj.getString("selected")!="null")
		{
				System.out.println("Search criteria = District and not null");
				String selectedVal=jObj.getString("selected");
				

				List<SelectOptionVO> districtNames= regionServiceDataImp.getDistrictsByStateID(new Long(1));	
					
				
				setNamesList(districtNames);								
		}
		else if (jObj.getString("reportLevel").equalsIgnoreCase("Constituency") && jObj.getString("selected")!="null")
		{
			Long countryID = 1L;
			Long stateID = 1L;
			String selectedVal=jObj.getString("selected");
			Long elecTypeId = new Long(selectedVal);
			
			List<SelectOptionVO> selectOptionList = constituencySearchService.getConstituencyNamesByElectionScope(countryID,stateID,elecTypeId);
			
			for(SelectOptionVO selectOptionVO:selectOptionList)
			{
				System.out.println(selectOptionVO.getId()+ " " + selectOptionVO.getName());
			}			
			setNamesList(selectOptionList);
		}
		else if (jObj.getString("reportLevel").equalsIgnoreCase("MLA") && jObj.getString("selected")!="null")
		{
			Long countryID = 1L;// India
			Long stateID = 1L;//AP
			Long typeID = 2L;// Assembly
			List<SelectOptionVO> selectOptionList = regionServiceDataImp.getAllConstituenciesByElectionTypeInState(typeID,stateID);
			
			for(SelectOptionVO selectOptionVO:selectOptionList)
			{
				System.out.println(selectOptionVO.getId()+ " " + selectOptionVO.getName());
			}			
			setNamesList(selectOptionList);
		}
		else if (jObj.getString("reportLevel").equalsIgnoreCase("MP") && jObj.getString("selected")!="null")
		{
			Long countryID = 1L;// India
			Long stateID = 1L;//AP
			Long typeID = 1L;// Parliamentary
			List<SelectOptionVO> selectOptionList = regionServiceDataImp.getAllConstituenciesByElectionTypeInState(typeID,stateID);
			System.out.println("selectOptionList.size in action"+selectOptionList.size());
			for(SelectOptionVO selectOptionVO:selectOptionList)
			{
				System.out.println(selectOptionVO.getId()+ " " + selectOptionVO.getName());
			}			
			setNamesList(selectOptionList);
		}
		
		System.out.println("param = "+param);
        
		 return Action.SUCCESS;		
	}
	
	  public String getJSON(){
		  System.out.println("In r getjson....");
	    	return execute();
	    }
	  public String getAccessValue(){
		  System.out.println("**************In get Access value***********....");
	    	return Action.SUCCESS;
	    }
	  

		
}
