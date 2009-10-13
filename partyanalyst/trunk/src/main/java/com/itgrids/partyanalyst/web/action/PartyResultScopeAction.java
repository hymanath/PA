package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import com.opensymphony.xwork2.Action;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IConstituencySearchService;

public class PartyResultScopeAction {

	private List<SelectOptionVO> namesList;
	JSONObject jObj = null,respObj=null;
	private String task = null;
	private IConstituencySearchService constituencySearchService;
	
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

	public void setConstituencySearchService(
			IConstituencySearchService constituencySearchService) {
		this.constituencySearchService = constituencySearchService;
	}
	
	
	//JSONObject jObj;
	
	public String execute() 
	{
		System.out.println("In execute method + Election scope level action");				
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
		
		if(jObj.getString("reportLevel").equalsIgnoreCase("State") && jObj.getString("selected").equalsIgnoreCase("null"))
		{
			System.out.println("Search criteria = State");				
			List<SelectOptionVO> stateNames=new ArrayList<SelectOptionVO>();			
			SelectOptionVO stateSelectOptionVO1 = new SelectOptionVO();
			stateSelectOptionVO1.setId(new Long(1));
			stateSelectOptionVO1.setName("Andhra Pradesh");
			
			stateNames.add(stateSelectOptionVO1);		
			setNamesList(stateNames);			
		}
		else if (jObj.getString("reportLevel").equalsIgnoreCase("District") && jObj.getString("selected").equalsIgnoreCase("null"))
		{
				System.out.println("Search criteria = District");			
				List<SelectOptionVO> stateNames=new ArrayList<SelectOptionVO>();
				
				SelectOptionVO stateSelectOptionVO1 = new SelectOptionVO();
				stateSelectOptionVO1.setId(new Long(1));
				stateSelectOptionVO1.setName("Andhra Pradesh");
				
				stateNames.add(stateSelectOptionVO1);
				setNamesList(stateNames);							
		}
		else if (jObj.getString("reportLevel").equalsIgnoreCase("Constituency") && jObj.getString("selected").equalsIgnoreCase("null"))
		{
				System.out.println("Search criteria = Constituency");	
				
				List<SelectOptionVO> stateNames=new ArrayList<SelectOptionVO>();
				
				SelectOptionVO stateSelectOptionVO1 = new SelectOptionVO();
				stateSelectOptionVO1.setId(new Long(1));
				stateSelectOptionVO1.setName("Andhra Pradesh");
				
				stateNames.add(stateSelectOptionVO1);
				
				setNamesList(stateNames);					
		}
		else if (jObj.getString("reportLevel").equalsIgnoreCase("District") && jObj.getString("selected")!="null")
		{
				System.out.println("Search criteria = District and not null");
				String selectedVal=jObj.getString("selected");
				
				List<SelectOptionVO> districtNames=new ArrayList<SelectOptionVO>();
				
				SelectOptionVO districtSelectOptionVO1 = new SelectOptionVO();
				districtSelectOptionVO1.setId(new Long(1));
				districtSelectOptionVO1.setName("Adilabad");
				
				SelectOptionVO districtSelectOptionVO2 = new SelectOptionVO();
				districtSelectOptionVO2.setId(new Long(3));
				districtSelectOptionVO2.setName("Chittoor");
				
				SelectOptionVO districtSelectOptionVO3 = new SelectOptionVO();
				districtSelectOptionVO3.setId(new Long(7));
				districtSelectOptionVO3.setName("Hyderabad");				
				
				SelectOptionVO districtSelectOptionVO4 = new SelectOptionVO();
				districtSelectOptionVO4.setId(new Long(15));
				districtSelectOptionVO4.setName("Nellore");
				
				SelectOptionVO districtSelectOptionVO5 = new SelectOptionVO();
				districtSelectOptionVO5.setId(new Long(18));
				districtSelectOptionVO5.setName("RangaReddy");
				
				districtNames.add(districtSelectOptionVO1);
				districtNames.add(districtSelectOptionVO2);
				districtNames.add(districtSelectOptionVO3);
				districtNames.add(districtSelectOptionVO4);
				districtNames.add(districtSelectOptionVO5);	
				
				setNamesList(districtNames);								
		}
		else if (jObj.getString("reportLevel").equalsIgnoreCase("Constituency") && jObj.getString("selected")!="null")
		{
			String selectedVal=jObj.getString("selected");
			
			List<SelectOptionVO> selectOptionList = constituencySearchService.getConstituencyNames(new Long(1));
			
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
	  


		
}
