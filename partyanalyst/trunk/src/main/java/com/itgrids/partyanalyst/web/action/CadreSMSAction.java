package com.itgrids.partyanalyst.web.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.json.JSONObject;

import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.helper.CadreSMSVO;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.opensymphony.xwork2.ActionSupport;

public class CadreSMSAction extends ActionSupport implements ServletRequestAware{

	private HttpServletRequest request;
	
	private CadreSMSVO cadreSMSVO;
	private Integer totalCadres;
	private List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
	private CadreManagementService cadreManagementService = null;

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

	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

	public CadreSMSVO getCadreSMSVO() {
		return cadreSMSVO;
	}

	public void setCadreSMSVO(CadreSMSVO cadreSMSVO) {
		this.cadreSMSVO = cadreSMSVO;
	}

	public Integer getTotalCadres() {
		return totalCadres;
	}

	public void setTotalCadres(Integer totalCadres) {
		this.totalCadres = totalCadres;
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
			regions.add(new SelectOptionVO(1L,"State"));
			regions.add(new SelectOptionVO(2L,"District"));
			regions.add(new SelectOptionVO(3L,"Constituency"));
			regions.add(new SelectOptionVO(4L,"Mandal"));
			regions.add(new SelectOptionVO(5L,"Village"));
			cadreSMSVO.setRegions(regions);
			states = cadreManagementService.getUserAccessStates(userID);
			states.add(0,new SelectOptionVO(0L,"Select State"));
			cadreSMSVO.setStates(states);
		}else if("STATE".equals(accessType)){
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
			regions.add(new SelectOptionVO(1L,"State"));
			regions.add(new SelectOptionVO(2L,"District"));
			regions.add(new SelectOptionVO(3L,"Constituency"));
			regions.add(new SelectOptionVO(4L,"Mandal"));
			regions.add(new SelectOptionVO(5L,"Village"));
			cadreSMSVO.setRegions(regions);
			SelectOptionVO object = new SelectOptionVO();
			String name = cadreManagementService.getStateName(new Long(accessValue));
			object.setId(id);
			object.setName(name);
			states.add(object);
			cadreSMSVO.setStates(states);
			districts = cadreManagementService.getUserAccessDistricts(userID);
			districts.add(0,new SelectOptionVO(0L,"Select District"));
			cadreSMSVO.setDistricts(districts);
		}else if("DISTRICT".equals(accessType)){
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
			regions.add(new SelectOptionVO(2L,"District"));
			regions.add(new SelectOptionVO(3L,"Constituency"));
			regions.add(new SelectOptionVO(4L,"Mandal"));
			regions.add(new SelectOptionVO(5L,"Village"));
			cadreSMSVO.setRegions(regions);
			List<SelectOptionVO> stateDistrict = cadreManagementService.getStateDistrictByDistrictID(id);
			states.add(stateDistrict.get(0));
			cadreSMSVO.setStates(states);
			districts.add(stateDistrict.get(1));
			cadreSMSVO.setDistricts(districts);
			constituencies = cadreManagementService.getUserAccessMLAConstituencies(userID);
			constituencies.add(0,new SelectOptionVO(0L,"Select Constituency"));
			cadreSMSVO.setConstituencies(constituencies);
		}else if("MLA".equals(accessType) || "MP".equals(accessType)){
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> states = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> districts = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> constituencies = new ArrayList<SelectOptionVO>();
			List<SelectOptionVO> mandals = new ArrayList<SelectOptionVO>();
			regions.add(new SelectOptionVO(3L,"Constituency"));
			regions.add(new SelectOptionVO(4L,"Mandal"));
			regions.add(new SelectOptionVO(5L,"Village"));
			cadreSMSVO.setRegions(regions);
			List<SelectOptionVO> stateDistrictConstituency = cadreManagementService.getStateDistricConstituencytByConstituencyID(id);
			states.add(stateDistrictConstituency.get(0));
			cadreSMSVO.setStates(states);
			districts.add(stateDistrictConstituency.get(1));
			cadreSMSVO.setDistricts(districts);
			constituencies.add(stateDistrictConstituency.get(2));
			cadreSMSVO.setConstituencies(constituencies);
			mandals = cadreManagementService.getUserAccessMandals(userID);
			mandals.add(0,new SelectOptionVO(0L,"Select Mandal"));
			cadreSMSVO.setMandals(mandals);
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
		Long value = value = new Long(jsonObject.getString("SMS_LEVEL_VALUE"));
		String message = jsonObject.getString("SMS_MESSAGE");

		if(log.isDebugEnabled())
			log.debug("cadre type:"+type);
		totalCadres = cadreManagementService.sendSMSMessage(userID,type,value, message);
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
		
		list.add(new SelectOptionVO(0L,"Country"));
		list.add(new SelectOptionVO(1L,"State"));
		list.add(new SelectOptionVO(2L,"District"));
		list.add(new SelectOptionVO(3L,"Constituency"));
		list.add(new SelectOptionVO(4L,"Mandal"));
		list.add(new SelectOptionVO(5L,"Village"));
		
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
