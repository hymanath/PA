package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.SelectOptionVO;

public class CadreRegisterPageAction extends ActionSupport implements ServletRequestAware,ServletContextAware{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CadreManagementService cadreManagementService;
	private UserCadresInfoVO userCadresInfoVO = new UserCadresInfoVO();
	private HttpServletRequest request;
	private HttpSession session;
	private List<SelectOptionVO> stateList;
	private List<SelectOptionVO> districtList;
	private List<SelectOptionVO> constituencyList;
	private List<SelectOptionVO> mandalList;
	private List<SelectOptionVO> villageList;
	
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

	public List<SelectOptionVO> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<SelectOptionVO> mandalList) {
		this.mandalList = mandalList;
	}

	public List<SelectOptionVO> getVillageList() {
		return villageList;
	}

	public void setVillageList(List<SelectOptionVO> villageList) {
		this.villageList = villageList;
	}

	
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}
	
	public void setServletRequest(HttpServletRequest request) {		
		this.request = request;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public String execute(){
		List<SelectOptionVO> sList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> dList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> cList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> mList = new ArrayList<SelectOptionVO>();
		List<SelectOptionVO> vList = new ArrayList<SelectOptionVO>();
		
		session = request.getSession();
		
		RegistrationVO regVO = (RegistrationVO) session.getAttribute("USER");
		String accessType =regVO.getAccessType();
	
		if("MLA".equals(accessType))
		{
			System.out.println("Access Type = MLA ****");
			
			stateList = new ArrayList<SelectOptionVO>();			
			SelectOptionVO state1 = new SelectOptionVO();
			state1.setId(new Long(1));
			state1.setName("Andhra Pradesh");
			
			stateList.add(state1);
			
			//--------------
			districtList = new ArrayList<SelectOptionVO>();
			SelectOptionVO district1 = new SelectOptionVO();
			district1.setId(new Long(0));
			district1.setName("Nellore");
			districtList.add(district1);
			
			setDistrictList(dList);
			
			//------------------
			constituencyList = new ArrayList<SelectOptionVO>();
			SelectOptionVO constituency1 = new SelectOptionVO();
			constituency1.setId(new Long(0));
			constituency1.setName("Allur");
			constituencyList.add(constituency1);
			
			setConstituencyList(cList);
			
			//--------------------
			mandalList = new ArrayList<SelectOptionVO>();
			SelectOptionVO mandal1 = new SelectOptionVO();
			mandal1.setId(new Long(0));
			mandal1.setName("Select Mandal");
			mandalList.add(mandal1);
			
			setMandalList(mList);
			
			//-----------------
						
			SelectOptionVO village1 = new SelectOptionVO();
			village1.setId(new Long(0));
			village1.setName("Select village");
			vList.add(village1);
			
			setVillageList(vList);
						
		}else if("COUNTRY".equals(accessType))
		{
			System.out.println("Access Type = Country ****");
			
			SelectOptionVO state1 = new SelectOptionVO();
			state1.setId(new Long(1));
			state1.setName("Andhra Pradesh");
			
			SelectOptionVO state2 = new SelectOptionVO();
			state2.setId(new Long(2));
			state2.setName("Karnataka");
			
			SelectOptionVO state3 = new SelectOptionVO();
			state3.setId(new Long(3));
			state3.setName("Tamil Nadu");
			
			SelectOptionVO state4 = new SelectOptionVO();
			state4.setId(new Long(4));
			state4.setName("Kerala");
			
			sList.add(state1);
			sList.add(state2);
			sList.add(state3);
			sList.add(state4);
			
			setStateList(sList);
			
			 //--------------
			
			SelectOptionVO district1 = new SelectOptionVO();
			district1.setId(new Long(0));
			district1.setName("Select District");
			dList.add(district1);
			
			setDistrictList(dList);
			
			//------------------
			
			SelectOptionVO constituency1 = new SelectOptionVO();
			constituency1.setId(new Long(0));
			constituency1.setName("Select Constituency");
			cList.add(constituency1);
			
			setConstituencyList(cList);
			
			//--------------------
			
			SelectOptionVO mandal1 = new SelectOptionVO();
			mandal1.setId(new Long(0));
			mandal1.setName("Select Mandal");
			mList.add(mandal1);
			
			setMandalList(mList);
			
			//-----------------
						
			SelectOptionVO village1 = new SelectOptionVO();
			village1.setId(new Long(0));
			village1.setName("Select village");
			vList.add(village1);
			
			setVillageList(vList);
			
			
		}else if("STATE".equals(accessType))
		{
			System.out.println("Access Type = State ****");
			
			SelectOptionVO state1 = new SelectOptionVO();
			state1.setId(new Long(1));
			state1.setName("Andhra Pradesh");
			
			sList.add(state1);
			
			//--------------
			
			SelectOptionVO district1 = new SelectOptionVO();
			district1.setId(new Long(0));
			district1.setName("Select District");
			dList.add(district1);
			
			setDistrictList(dList);
			
			//------------------
			
			SelectOptionVO constituency1 = new SelectOptionVO();
			constituency1.setId(new Long(0));
			constituency1.setName("Select Constituency");
			cList.add(constituency1);
			
			setConstituencyList(cList);
			
			//--------------------
			
			SelectOptionVO mandal1 = new SelectOptionVO();
			mandal1.setId(new Long(0));
			mandal1.setName("Select Mandal");
			mList.add(mandal1);
			
			setMandalList(mList);
			
			//-----------------
						
			SelectOptionVO village1 = new SelectOptionVO();
			village1.setId(new Long(0));
			village1.setName("Select village");
			vList.add(village1);
			
			setVillageList(vList);
			
		}else if("DISTRICT".equals(accessType))
		{
			System.out.println("Access Type = District ****");
			
			SelectOptionVO state1 = new SelectOptionVO();
			state1.setId(new Long(1));
			state1.setName("Andhra Pradesh");
			
			sList.add(state1);
			
			//--------------
			
			SelectOptionVO district1 = new SelectOptionVO();
			district1.setId(new Long(1));
			district1.setName("Nellore");
			dList.add(district1);
			
			setDistrictList(dList);
			
			//------------------
			
			SelectOptionVO constituency1 = new SelectOptionVO();
			constituency1.setId(new Long(0));
			constituency1.setName("Select Constituency");
			cList.add(constituency1);
			
			setConstituencyList(cList);
			
			//--------------------
			
			SelectOptionVO mandal1 = new SelectOptionVO();
			mandal1.setId(new Long(0));
			mandal1.setName("Select Mandal");
			mList.add(mandal1);
			
			setMandalList(mList);
			
			//-----------------
						
			SelectOptionVO village1 = new SelectOptionVO();
			village1.setId(new Long(0));
			village1.setName("Select village");
			vList.add(village1);
			
			setVillageList(vList);
			
			
			
		}else if("MANDAL".equals(accessType))
		{
			System.out.println("Access Type = Mandal ****");
			
			SelectOptionVO state1 = new SelectOptionVO();
			state1.setId(new Long(1));
			state1.setName("Andhra Pradesh");
			
			sList.add(state1);
			
			//--------------
			
			SelectOptionVO district1 = new SelectOptionVO();
			district1.setId(new Long(1));
			district1.setName("Nellore");
			dList.add(district1);
			
			setDistrictList(dList);
			
			//------------------
			
			SelectOptionVO constituency1 = new SelectOptionVO();
			constituency1.setId(new Long(1));
			constituency1.setName("Allur");
			cList.add(constituency1);
			
			setConstituencyList(cList);
			
			//--------------------
			
			SelectOptionVO mandal1 = new SelectOptionVO();
			mandal1.setId(new Long(0));
			mandal1.setName("Select Mandal");
			mList.add(mandal1);
			
			setMandalList(mList);
			
			//-----------------
						
			SelectOptionVO village1 = new SelectOptionVO();
			village1.setId(new Long(0));
			village1.setName("Select village");
			vList.add(village1);
			
			setVillageList(vList);
			
		}else if("VILLAGE".equals(accessType))
		{
			System.out.println("Access Type = Village ****");
			
			SelectOptionVO state1 = new SelectOptionVO();
			state1.setId(new Long(1));
			state1.setName("Andhra Pradesh");
			
			sList.add(state1);
			
			//--------------
			
			SelectOptionVO district1 = new SelectOptionVO();
			district1.setId(new Long(0));
			district1.setName("Nellore");
			dList.add(district1);
			
			setDistrictList(dList);
			
			//------------------
			
			SelectOptionVO constituency1 = new SelectOptionVO();
			constituency1.setId(new Long(0));
			constituency1.setName("Allur");
			cList.add(constituency1);
			
			setConstituencyList(cList);
			
			//--------------------
			
			SelectOptionVO mandal1 = new SelectOptionVO();
			mandal1.setId(new Long(0));
			mandal1.setName("Allur");
			mList.add(mandal1);
			
			setMandalList(mList);
			
			//-----------------
						
			SelectOptionVO village1 = new SelectOptionVO();
			village1.setId(new Long(0));
			village1.setName("Allur Village");
			vList.add(village1);
			
			setVillageList(vList);
			
		}else if("MP".equals(accessType))
		{
			System.out.println("Access Type = MP ****");
			
			SelectOptionVO state1 = new SelectOptionVO();
			state1.setId(new Long(1));
			state1.setName("Andhra Pradesh");
			
			sList.add(state1);
			
			//--------------
			
			SelectOptionVO district1 = new SelectOptionVO();
			district1.setId(new Long(0));
			district1.setName("Nellore");
			dList.add(district1);
			
			setDistrictList(dList);
			
			//------------------
			
			SelectOptionVO constituency1 = new SelectOptionVO();
			constituency1.setId(new Long(0));
			constituency1.setName("Allur");
			cList.add(constituency1);
			
			setConstituencyList(cList);
			
			//--------------------
			
			SelectOptionVO mandal1 = new SelectOptionVO();
			mandal1.setId(new Long(0));
			mandal1.setName("Select Mandal");
			mList.add(mandal1);
			
			setMandalList(mList);
			
			//-----------------
						
			SelectOptionVO village1 = new SelectOptionVO();
			village1.setId(new Long(0));
			village1.setName("Select village");
			vList.add(village1);
			
			setVillageList(vList);
			
		}
		
		
		
		
		
		
		return Action.SUCCESS;
	}
	

}
