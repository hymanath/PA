package com.itgrids.partyanalyst.web.action;

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
import com.itgrids.partyanalyst.dto.SmsResultVO;
import com.itgrids.partyanalyst.helper.CadreSMSVO;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSMSAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	
	private CadreSMSVO cadreSMSVO;
	private SmsResultVO totalCadres;
	private List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
	private CadreManagementService cadreManagementService = null;
	private IRegionServiceData regionServiceDataImp;
	private List<SelectOptionVO> parliamentConstituencies;
	private IStaticDataService staticDataService; 

	private static final Logger log = Logger.getLogger(CadreSMSAction.class);
	
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public List<SelectOptionVO> getList() {
		return list;
	}

	public void setList(List<SelectOptionVO> list) {
		this.list = list;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

	public CadreSMSVO getCadreSMSVO() {
		return cadreSMSVO;
	}

	public void setCadreSMSVO(CadreSMSVO cadreSMSVO) {
		this.cadreSMSVO = cadreSMSVO;
	}

	public SmsResultVO getTotalCadres() {
		return totalCadres;
	}

	public void setTotalCadres(SmsResultVO totalCadres) {
		this.totalCadres = totalCadres;
	}

	public List<SelectOptionVO> getParliamentConstituencies() {
		return parliamentConstituencies;
	}

	public void setParliamentConstituencies(
			List<SelectOptionVO> parliamentConstituencies) {
		this.parliamentConstituencies = parliamentConstituencies;
	}

	
	public IStaticDataService getStaticDataService() {
		return staticDataService;
	}

	public void setStaticDataService(IStaticDataService staticDataService) {
		this.staticDataService = staticDataService;
	}

	public String execute() throws Exception{
		if(log.isDebugEnabled())
			log.debug("CadreSMSMessage.execute() started");
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		cadreSMSVO = new CadreSMSVO();
		if(user==null)
			return ERROR;
		return SUCCESS;
	}
	
	public String getUserLocationWiseData(){
		if(log.isDebugEnabled())
			log.debug("CadreSMSMessage.getuserLocationWiseData() started");
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		String accessType = user.getAccessType();
		String accessValue = user.getAccessValue();
		Long userID = user.getRegistrationID();
		Long id = new Long(accessValue);
		cadreSMSVO = new CadreSMSVO();
		if(log.isDebugEnabled())
			log.debug("cadre access type:"+accessType);
		if("COUNTRY".equals(accessType)){
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
			regions.add(new SelectOptionVO(2L,"STATE"));
			regions.add(new SelectOptionVO(3L,"DISTRICT"));
			regions.add(new SelectOptionVO(4L,"ASSEMBLY CONSTITUENCY"));
			regions.add(new SelectOptionVO(5L,"MANDAL/TEHSIL"));
			regions.add(new SelectOptionVO(6L,"VILLAGE"));
			regions.add(new SelectOptionVO(7L,"MUNICIPAL-CORP-GMC"));
			regions.add(new SelectOptionVO(8L,"WARD"));
			regions.add(new SelectOptionVO(9L,"BOOTH"));
			regions.add(new SelectOptionVO(10L,"PARLIAMENT CONSTITUENCY"));
			cadreSMSVO.setRegions(regions);
			//states = cadreManagementService.getUserAccessStates(userID);
			states = regionServiceDataImp.getStatesByCountry(new Long(1));
			states.add(0,new SelectOptionVO(0L,"Select State"));
			cadreSMSVO.setStates(states);
		}else if("STATE".equals(accessType)){
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
			regions.add(new SelectOptionVO(2L,"STATE"));
			regions.add(new SelectOptionVO(3L,"DISTRICT"));
			regions.add(new SelectOptionVO(10L,"PARLIAMENT CONSTITUENCY"));
			regions.add(new SelectOptionVO(4L,"ASSEMBLY CONSTITUENCY"));
			regions.add(new SelectOptionVO(5L,"MANDAL/TEHSIL"));
			regions.add(new SelectOptionVO(6L,"VILLAGE"));
			regions.add(new SelectOptionVO(7L,"MUNICIPAL-CORP-GMC"));
			regions.add(new SelectOptionVO(8L,"WARD"));
			regions.add(new SelectOptionVO(9L,"BOOTH"));
			
			cadreSMSVO.setRegions(regions);
			SelectOptionVO object = new SelectOptionVO();
			String name = cadreManagementService.getStateName(new Long(accessValue));
			object.setId(id);
			object.setName(name);
			states.add(object);
			cadreSMSVO.setStates(states);
			//districts = cadreManagementService.getUserAccessDistricts(userID);
			districts = regionServiceDataImp.getDistrictsByStateID(id);
			districts.add(0,new SelectOptionVO(0L,"Select District"));
			cadreSMSVO.setDistricts(districts);
		}else if("DISTRICT".equals(accessType)){
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> stateDistrict = cadreManagementService.getStateDistrictByDistrictID(id);
			states.add(stateDistrict.get(0));
			cadreSMSVO.setStates(states);
			districts.add(stateDistrict.get(1));
			cadreSMSVO.setDistricts(districts);
			constituencies = regionServiceDataImp.getConstituenciesByDistrictID(stateDistrict.get(1).getId());
			constituencies.add(0,new SelectOptionVO(0L,"Select Constituency"));
			cadreSMSVO.setConstituencies(constituencies);
			regions.add(new SelectOptionVO(3L,"DISTRICT"));
			regions.add(new SelectOptionVO(4L,"ASSEMBLY CONSTITUENCY"));
			regions.add(new SelectOptionVO(5L,"MANDAL/TEHSIL"));
			regions.add(new SelectOptionVO(6L,"VILLAGE"));
			regions.add(new SelectOptionVO(7L,"MUNICIPAL-CORP-GMC"));
			regions.add(new SelectOptionVO(8L,"WARD"));
			regions.add(new SelectOptionVO(9L,"BOOTH"));
			cadreSMSVO.setRegions(regions);
			
		}else if("MLA".equals(accessType) || "MP".equals(accessType)){
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> mandals = new ArrayList<SelectOptionVO>();
			String areaType = regionServiceDataImp.getConstituencyAreaType(new Long(accessValue));
			if(areaType != null && areaType.equals(IConstants.CONST_TYPE_URBAN))
			{
				regions.add(new SelectOptionVO(4L,"ASSEMBLY CONSTITUENCY"));
				regions.add(new SelectOptionVO(7L,"MUNICIPAL-CORP-GMC"));
				regions.add(new SelectOptionVO(8L,"WARD"));
				regions.add(new SelectOptionVO(9L,"BOOTH"));
				cadreSMSVO.setRegions(regions);
			} else
			{
				regions.add(new SelectOptionVO(10L,"PARLIAMENT CONSTITUENCY"));
				regions.add(new SelectOptionVO(4L,"ASSEMBLY CONSTITUENCY"));
				regions.add(new SelectOptionVO(5L,"MANDAL/TEHSIL"));
				regions.add(new SelectOptionVO(6L,"VILLAGE"));
				regions.add(new SelectOptionVO(7L,"MUNICIPAL-CORP-GMC"));
				regions.add(new SelectOptionVO(8L,"WARD"));
				regions.add(new SelectOptionVO(9L,"BOOTH"));
				cadreSMSVO.setRegions(regions);
			}
			try{
			  if("MLA".equals(accessType)){
				List<SelectOptionVO> stateDistrictConstituency = cadreManagementService.getStateDistricConstituencytByConstituencyID(id);
				states.add(stateDistrictConstituency.get(0));
				cadreSMSVO.setStates(states);
				districts.add(stateDistrictConstituency.get(1));
				cadreSMSVO.setDistricts(districts);
				constituencies.add(stateDistrictConstituency.get(2));
				cadreSMSVO.setConstituencies(constituencies);
				//mandals = cadreManagementService.getUserAccessMandals(userID);
				mandals = regionServiceDataImp.getMandalsByConstituencyID(stateDistrictConstituency.get(2).getId());
				mandals.add(0,new SelectOptionVO(0L,"Select Mandal"));
				cadreSMSVO.setMandals(mandals);
			  }else{
				  regions = new ArrayList<SelectOptionVO>();
				    regions.add(new SelectOptionVO(10L,"PARLIAMENT CONSTITUENCY"));
				    regions.add(new SelectOptionVO(4L,"ASSEMBLY CONSTITUENCY"));
					regions.add(new SelectOptionVO(5L,"MANDAL/TEHSIL"));
					regions.add(new SelectOptionVO(6L,"VILLAGE"));
					regions.add(new SelectOptionVO(7L,"MUNICIPAL-CORP-GMC"));
					regions.add(new SelectOptionVO(8L,"WARD"));
					regions.add(new SelectOptionVO(9L,"BOOTH"));
					cadreSMSVO.setRegions(regions);
				cadreSMSVO.setStates(regionServiceDataImp.getStateByParliamentConstituencyID(new Long(accessValue)));
				ConstituencyInfoVO constituencyInfoVO = staticDataService.getLatestAssemblyConstituenciesForParliament(new Long(accessValue));
				 if(constituencyInfoVO != null){
				     cadreSMSVO.setConstituencies(constituencyInfoVO.getAssembyConstituencies());
				     cadreSMSVO.getParliamentConstituencys().add(new SelectOptionVO(constituencyInfoVO.getConstituencyId(),constituencyInfoVO.getConstituencyName())); 
				 }
			  }
			}catch(Exception e){
				log.error("Exception rised in getUserLocationWiseData",e);
			}
		}
		return SUCCESS;
	}
	
	public String sendSMS() throws Exception{
		if(log.isDebugEnabled())
			log.debug("CadreSMSMessage.sendSMS() started");
		String param=request.getParameter("task");
		JSONObject jsonObject = new JSONObject(param);
		System.out.println("In json object = "+jsonObject);
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		String type = jsonObject.getString("SMS_LEVEL_TYPE");
		Long value = new Long(jsonObject.getString("SMS_LEVEL_VALUE"));
		String message = jsonObject.getString("SMS_MESSAGE");
		String includeCadreName = jsonObject.getString("SMS_INCLUDE_CADRE_NAME");

		if(log.isDebugEnabled())
			log.debug("cadre type:"+type);
		totalCadres = cadreManagementService.sendSMSMessage(userID,type,value, message, includeCadreName);
		return SUCCESS;
	}
	

	public String getRegionsByCadreScope() throws Exception{
		if(log.isDebugEnabled())
			log.debug("CadreSMSMessage.getRegionsByCadreScope() started");
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		Long regionID = new Long(request.getParameter("REGION_ID"));
		String region = request.getParameter("REGION");

		if(log.isDebugEnabled())
			log.debug("region scope:"+region);
		list = cadreManagementService.findRegionByCadreScope(userID,regionID,region);
		return SUCCESS;
	}
	
	public String getUsersCadreLevelData(){
		if(log.isDebugEnabled())
			log.debug("CadreSMSMessage.getuserLocationWiseData() started");
		HttpSession session = request.getSession();
		RegistrationVO user = (RegistrationVO)session.getAttribute("USER");
		String accessType = user.getAccessType();
		
		list.add(new SelectOptionVO(1L,"Country"));
		list.add(new SelectOptionVO(2L,"State"));
		list.add(new SelectOptionVO(3L,"District"));
		list.add(new SelectOptionVO(4L,"Constituency"));
		list.add(new SelectOptionVO(5L,"Mandal"));
		list.add(new SelectOptionVO(6L,"Village"));
		
		/*if("COUNTRY".equals(accessType)){
			list.add(new SelectOptionVO(0L,"Country"));
			list.add(new SelectOptionVO(1L,"State"));
			list.add(new SelectOptionVO(2L,"District"));
			list.add(new SelectOptionVO(3L,"Constituency"));
			list.add(new SelectOptionVO(4L,"Mandal"));
			list.add(new SelectOptionVO(5L,"Village"));
		} else if("STATE".equals(accessType)){
			list.add(new SelectOptionVO(1L,"State"));
			list.add(new SelectOptionVO(2L,"District"));
			list.add(new SelectOptionVO(3L,"Constituency"));
			list.add(new SelectOptionVO(4L,"Mandal"));
			list.add(new SelectOptionVO(5L,"Village"));
		} else if("DISTRICT".equals(accessType)){
			list.add(new SelectOptionVO(2L,"District"));
			list.add(new SelectOptionVO(3L,"Constituency"));
			list.add(new SelectOptionVO(4L,"Mandal"));
			list.add(new SelectOptionVO(5L,"Village"));
		} else if("MLA".equals(accessType) || "MP".equals(accessType) ){
			list.add(new SelectOptionVO(3L,"Constituency"));
			list.add(new SelectOptionVO(4L,"Mandal"));
			list.add(new SelectOptionVO(5L,"Village"));
		}*/
		return SUCCESS;
	}
}
