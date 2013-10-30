package com.itgrids.partyanalyst.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;
import org.json.JSONObject;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.itgrids.partyanalyst.dto.CadreDetailsInfoVO;
import com.itgrids.partyanalyst.dto.CadreInfo;
import com.itgrids.partyanalyst.dto.CadreRegionInfoVO;
import com.itgrids.partyanalyst.dto.ConstituencyInfoVO;
import com.itgrids.partyanalyst.dto.RegistrationVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.UserCadresInfoVO;
import com.itgrids.partyanalyst.dto.StateToHamletVO;
import com.itgrids.partyanalyst.service.IConstituencyPageService;
import com.itgrids.partyanalyst.service.impl.CadreManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

@SuppressWarnings("serial")
public class CadresInfoAjaxAction extends ActionSupport implements ServletRequestAware,ServletContextAware{
	
	private String region;
	private String regionId;	
	private String cadreType;
	private HttpServletRequest request;
	private HttpSession session;
	private List<CadreRegionInfoVO> cadreRegionInfo;
	private List<CadreInfo> cadreInfo;
	private List<SelectOptionVO> zeroCadresRegion;
	private List<StateToHamletVO> zeroCadresRegion1;	
	private CadreDetailsInfoVO cadreDetailsInfoVO;
	private static final Logger log = Logger.getLogger(CadresInfoAjaxAction.class);
	JSONObject jObj = null;
	private String task = null;
	private CadreManagementService cadreManagementService;
	private IConstituencyPageService constituencyPageService;
	
	public void setCadreManagementService(
			CadreManagementService cadreManagementService) {
		this.cadreManagementService = cadreManagementService;
	}

	public String getCadreType() {
		return cadreType;
	}

	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public List<CadreRegionInfoVO> getCadreRegionInfo() {
		return cadreRegionInfo;
	}

	public void setCadreRegionInfo(List<CadreRegionInfoVO> cadreRegionInfo) {
		this.cadreRegionInfo = cadreRegionInfo;
	}

	public List<CadreInfo> getCadreInfo() {
		return cadreInfo;
	}

	public void setCadreInfo(List<CadreInfo> cadreInfo) {
		this.cadreInfo = cadreInfo;
	}
	
	
	public List<SelectOptionVO> getZeroCadresRegion() {
		return zeroCadresRegion;
	}

	public void setZeroCadresRegion(List<SelectOptionVO> zeroCadresRegion) {
		this.zeroCadresRegion = zeroCadresRegion;
	}

	public List<StateToHamletVO> getZeroCadresRegion1() {
		return zeroCadresRegion1;
	}

	public CadreDetailsInfoVO getCadreDetailsInfoVO() {
		return cadreDetailsInfoVO;
	}

	public void setCadreDetailsInfoVO(CadreDetailsInfoVO cadreDetailsInfoVO) {
		this.cadreDetailsInfoVO = cadreDetailsInfoVO;
	}

	public void setZeroCadresRegion1(List<StateToHamletVO> zeroCadresRegion1) {
		this.zeroCadresRegion1 = zeroCadresRegion1;
	}	

	public IConstituencyPageService getConstituencyPageService() {
		return constituencyPageService;
	}

	public void setConstituencyPageService(
			IConstituencyPageService constituencyPageService) {
		this.constituencyPageService = constituencyPageService;
	}
	public JSONObject getJObj() {
		return jObj;
	}

	public void setJObj(JSONObject obj) {
		jObj = obj;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String execute() throws Exception{
		log.debug("CadresInfoAjaxAction.execute() started");
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		region = request.getParameter("cadreRegion");
		regionId = request.getParameter("cadreId");
		cadreType = request.getParameter("cadreType");
		Boolean isParent = true;
		if(user.getParentUserId() != null)
			isParent = false;
		this.setRegion(region);
		this.setRegionId(regionId);
		this.setCadreType(cadreType);
		
		
		cadreRegionInfo = new ArrayList<CadreRegionInfoVO>();
		cadreInfo = new ArrayList<CadreInfo>();
		log.debug("region::"+region);
		if(cadreType.equalsIgnoreCase("TotalCadre"))
		{
			log.debug("In if total cadre");
			if(region.equalsIgnoreCase("Country"))
			{
				log.debug("Inside if block level = "+region);			
				
				cadreRegionInfo = cadreManagementService.getCountryAllStatesCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("State"))
			{
				log.debug("Inside if block level = "+region);
				
				cadreRegionInfo = cadreManagementService.getStateAllDistrictsCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("District"))
			{		
				log.debug("Inside if block level = "+region);
				cadreRegionInfo = cadreManagementService.getDistrictAllConstCadres(new Long(regionId), userID);
				//cadreRegionInfo = cadreManagementService.getDistrictAllMandalsCadres(new Long(regionId), userID);
				//cadreRegionInfo = cadreManagementService.getConstituencyAllMandalsCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
				
				
			}
			else if(region.equalsIgnoreCase("MLA") || region.equalsIgnoreCase("Constituency"))
			{
				log.debug("Inside if block level = "+region);
				ConstituencyInfoVO constituencyDetails = constituencyPageService.getConstituencyDetails(new Long(regionId));
				if(constituencyDetails != null && constituencyDetails.getArea_type().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
					cadreRegionInfo = cadreManagementService.getConstituencyAllMandalsCadres(new Long(regionId), userID);
				if(constituencyDetails != null && constituencyDetails.getArea_type().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
					cadreRegionInfo = cadreManagementService.getConstituencySubRegionalCadres(new Long(regionId), userID);
				if(constituencyDetails != null && constituencyDetails.getArea_type().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
				{
					cadreRegionInfo = cadreManagementService.getConstituencyAllMandalsCadres(new Long(regionId), userID);
					List<CadreRegionInfoVO> subRegionRegionCadreInfo = cadreManagementService.getConstituencySubRegionalCadres(new Long(regionId), userID);
					if(subRegionRegionCadreInfo.size() != 0)
						cadreRegionInfo.addAll(subRegionRegionCadreInfo);
				}	
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			else if(region.equalsIgnoreCase("Mandal"))
			{
				log.debug("Inside if block level = "+region);

				//cadreRegionInfo = cadreManagementService.getMandalAllVillagesCadres(new Long(regionId), userID);
				
				this.setCadreRegionInfo(cadreRegionInfo);
			}
			/*else if(region.equalsIgnoreCase("Village"))
			{
				
				cadreInfo = cadreManagementService.getCadresByVillage(new Long(regionId), userID);
				
				this.setCadreInfo(cadreInfo);
			}*/
			else if(region.equalsIgnoreCase("T"))
			{
				log.debug("region:"+region);
				cadreInfo = cadreManagementService.getCadresByVillage(new Long(regionId), userID);
				
				this.setCadreInfo(cadreInfo);
			}
			else if(region.equalsIgnoreCase("V"))
			{
				log.debug("region:"+region);
				cadreRegionInfo = cadreManagementService.getCadreSizeByHamlet(new Long(regionId), userID);
				
				this.setCadreInfo(cadreInfo);
			}
			/*else if(region.equalsIgnoreCase("HAMLET"))
			{
				log.debug("region:"+region);
				cadreInfo = cadreManagementService.getCadresByHamlet(new Long(regionId), userID);
				
				this.setCadreInfo(cadreInfo);
			}*/
		}
		else if(cadreType.equalsIgnoreCase("ZeroLevelCadre"))
		{
			log.debug("In if Zero level cadre");
			UserCadresInfoVO userCadresInfoVo = (UserCadresInfoVO) session.getAttribute("USERCADRESINFOVO");
			List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
			Map<Long, String> zeroLevelCadres = new HashMap<Long, String>();
			if(region.equalsIgnoreCase("STATE"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreStates();
				 zeroCadresRegion1 = getStates(zeroLevelCadres);
			}else if(region.equalsIgnoreCase("DISTRICT"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreDistricts();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 zeroCadresRegion1=cadreManagementService.getStateToDistrictByDistrict(keys);
			}else if(region.equalsIgnoreCase("MANDAL"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreMandals();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 zeroCadresRegion1=cadreManagementService.getStateToMandalByTehsil(keys);
			}else if(region.equalsIgnoreCase("VILLAGE"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreVillages();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 zeroCadresRegion1=cadreManagementService.getStateToRevenueVillageByRV(keys);
			}else if(region.equalsIgnoreCase("HAMLET"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreHamlets();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 zeroCadresRegion1=cadreManagementService.getStateToHamletByHamlets(keys);
			}
			
			 
			 Set<Long> set = zeroLevelCadres.keySet();
			 Iterator<Long> iterator = set.iterator();
			 while(iterator.hasNext()){
				 Long key = iterator.next();
				 String value = zeroLevelCadres.get(key);
				 SelectOptionVO obj = new SelectOptionVO();
				 obj.setId(key);
				 obj.setName(value);
				 regions.add(obj);
			 }

			 this.setZeroCadresRegion(regions);
		}
		else if(cadreType.equalsIgnoreCase("RegionLevelCadre"))
		{
			log.debug("In if Region level cadre");
			cadreInfo = cadreManagementService.getCadresByCadreLevel(region, userID, isParent, user.getAccessType(), new Long(user.getAccessValue()));
			
			this.setCadreInfo(cadreInfo);
		}
				
		return Action.SUCCESS;		
	}
	
	public String getCadreRegionLevelInfo(){
		
		cadreDetailsInfoVO = new CadreDetailsInfoVO();
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");		
		//Long userID = user.getRegistrationID();
		Long userID = user.getParentUserId() == null ? user.getRegistrationID() : user.getParentUserId();
		region = request.getParameter("cadreRegion");
		regionId = request.getParameter("cadreId");
		cadreType = request.getParameter("cadreType");
		cadreDetailsInfoVO.setRegion(region);
		cadreDetailsInfoVO.setRegionId(regionId);
		cadreDetailsInfoVO.setCadreType(cadreType);
		
		
		cadreRegionInfo = new ArrayList<CadreRegionInfoVO>();
		cadreInfo = new ArrayList<CadreInfo>();
		log.debug("region::"+region);
		
		if(region.equalsIgnoreCase("Country"))
		{
			log.debug("Inside if block level = "+region);			
			
			cadreRegionInfo = cadreManagementService.getCountryAllStatesCadres(new Long(regionId), userID);
			
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		}
		else if(region.equalsIgnoreCase("State"))
		{
			log.debug("Inside if block level = "+region);
			
			cadreRegionInfo = cadreManagementService.getStateAllDistrictsCadres(new Long(regionId), userID);
			
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		}
		else if(region.equalsIgnoreCase("District"))
		{		
			log.debug("Inside if block level = "+region);
			cadreRegionInfo = cadreManagementService.getDistrictAllConstCadres(new Long(regionId), userID);
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			
		}
		else if(region.equalsIgnoreCase("MP"))
		{		
			log.debug("Inside if block level = "+region);
			cadreRegionInfo = cadreManagementService.getParliamentAllConstCadres(new Long(regionId), userID);
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			
		}
		else if(region.equalsIgnoreCase("MLA") || region.equalsIgnoreCase("Constituency"))
		{
			log.debug("Inside if block level = "+region);
			ConstituencyInfoVO constituencyDetails = constituencyPageService.getConstituencyDetails(new Long(regionId));
			if(constituencyDetails != null && constituencyDetails.getArea_type().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL))
				cadreRegionInfo = cadreManagementService.getConstituencyAllMandalsCadres(new Long(regionId), userID);
			if(constituencyDetails != null && constituencyDetails.getArea_type().equalsIgnoreCase(IConstants.CONST_TYPE_URBAN))
				cadreRegionInfo = cadreManagementService.getConstituencySubRegionalCadres(new Long(regionId), userID);
			if(constituencyDetails != null && constituencyDetails.getArea_type().equalsIgnoreCase(IConstants.CONST_TYPE_RURAL_URBAN))
			{
				cadreRegionInfo = cadreManagementService.getConstituencyAllMandalsCadres(new Long(regionId), userID);
				List<CadreRegionInfoVO> subRegionRegionCadreInfo = cadreManagementService.getConstituencySubRegionalCadres(new Long(regionId), userID);
				if(subRegionRegionCadreInfo.size() != 0)
					cadreRegionInfo.addAll(subRegionRegionCadreInfo);
				
			}			
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		}
		else if(region.equalsIgnoreCase("Mandal") || region.equalsIgnoreCase("MUNCIPALITY") || region.equalsIgnoreCase("CORPORATION") || region.equalsIgnoreCase("GMC") || region.equalsIgnoreCase("Greater Municipal Corp"))
		{
			//contains booth level cadre if a booth details exists for cadre
			List<CadreRegionInfoVO> cadreBoothRegionInfo = new ArrayList<CadreRegionInfoVO>(0);
			
			if (IConstants.URBAN_TYPE.equals(regionId.substring(0,1)))
			{
				cadreRegionInfo = cadreManagementService.getLocalElectionBodyCadresByWardsCount(new Long(regionId.substring(1)), userID);
				if(!"Greater Municipal Corp".equalsIgnoreCase(region))
					cadreBoothRegionInfo = cadreManagementService.getLocalElectionBodyAllBoothsCadresCount(new Long(regionId.substring(1)), userID);
				if(cadreBoothRegionInfo.size()>0)
					cadreRegionInfo.addAll(cadreBoothRegionInfo);
				
			}
			if (IConstants.RURAL_TYPE.equals(regionId.substring(0,1)))
			{
				cadreRegionInfo = cadreManagementService.getMandalAllHamletsCadresCount(new Long(regionId.substring(1)), userID);
				cadreBoothRegionInfo = cadreManagementService.getMandalAllBoothsCadresCount(new Long(regionId.substring(1)), userID);
				if(cadreBoothRegionInfo.size()>0)
					cadreRegionInfo.addAll(cadreBoothRegionInfo);
			}
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			
			
			/*log.debug("Inside if block level = "+region);
			//contains booth level cadre if a booth details exists for cadre
			List<CadreRegionInfoVO> cadreBoothRegionInfo = new ArrayList<CadreRegionInfoVO>(0);
			//contains cadre booth details does not exists for cadre
			List<CadreRegionInfoVO> nonBoothRegionInfo = new ArrayList<CadreRegionInfoVO>(0);
			//cadreRegionInfo = cadreManagementService.getMandalAllVillagesCadres(new Long(regionId), userID);
			if (IConstants.URBAN_TYPE.equals(regionId.substring(0,1)))
			{
			cadreRegionInfo = cadreManagementService.getLocalElectionBodyCadresByWards(new Long(regionId.substring(1)), userID);
			cadreBoothRegionInfo = cadreManagementService.getLocalElectionBodyAllBoothsCadres(new Long(regionId.substring(1)), userID);
			//nonBoothRegionInfo = cadreManagementService.getAllNonAssignedBoothCadres(userID);
			if(cadreBoothRegionInfo.size()>0)
				cadreRegionInfo.addAll(cadreBoothRegionInfo);
			if(nonBoothRegionInfo.size()>0)
				cadreRegionInfo.addAll(nonBoothRegionInfo);			
				
			}
			if (IConstants.RURAL_TYPE.equals(regionId.substring(0,1)))
			{
				cadreRegionInfo = cadreManagementService.getMandalAllHamletsCadres(new Long(regionId.substring(1)), userID);
				cadreBoothRegionInfo = cadreManagementService.getMandalAllBoothsCadres(new Long(regionId.substring(1)), userID);
				//nonBoothRegionInfo = cadreManagementService.getAllNonAssignedBoothCadres(userID);
				if(cadreBoothRegionInfo.size()>0)
					cadreRegionInfo.addAll(cadreBoothRegionInfo);
				if(nonBoothRegionInfo.size()>0)
					cadreRegionInfo.addAll(nonBoothRegionInfo);			
				
			}
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		*/	
		}
		//previous working method
		/*else if(region.equalsIgnoreCase("WARD") || region.equalsIgnoreCase("VILLAGE"))
		{
			log.debug("region:"+region);
			cadreInfo = cadreManagementService.getCadresByHamlet(regionId.toString(), userID);
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		}*/
		
		else if(region.equalsIgnoreCase("CADRES BY VILLAGES"))
		{
			if (IConstants.RURAL_TYPE.equals(regionId.substring(0,1)))
			{
				cadreRegionInfo = cadreManagementService.getMandalAllHamletsCadres(new Long(regionId.substring(1)), userID);				
				//nonBoothRegionInfo = cadreManagementService.getAllNonAssignedBoothCadres(userID);
				
			}
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			/*log.debug("region:"+region);
			cadreInfo = cadreManagementService.getCadresByHamlet(regionId.toString(), userID);
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);*/
		}
		
		else if(region.equalsIgnoreCase("WARD") || region.equalsIgnoreCase("VILLAGE"))
		{
			log.debug("region:"+region);
			//contains booth level cadre if a booth details exists for cadre
			List<CadreRegionInfoVO> cadreBoothRegionInfo = new ArrayList<CadreRegionInfoVO>(0);
			//contains cadre booth details does not exists for cadre
			List<CadreRegionInfoVO> nonBoothRegionInfo = new ArrayList<CadreRegionInfoVO>(0);
			Boolean flag = false;
			cadreInfo = cadreManagementService.getCadresByHamlet(regionId.toString(), userID);
			if(region.equalsIgnoreCase("WARD"))
				flag = cadreManagementService.getLocalBodyElectionType(new Long(regionId.substring(1)));					
			if(flag == true)
			{
				cadreRegionInfo = cadreManagementService.getWardAllBoothsCadresCount(new Long(regionId.substring(1)), userID);
				nonBoothRegionInfo = cadreManagementService.getAllNonAssignedBoothCadresByWard(new Long(regionId.substring(1)), userID);
				if(nonBoothRegionInfo.size()>0)
					cadreRegionInfo.addAll(nonBoothRegionInfo);					
			}
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		}
		
		else if(region.equalsIgnoreCase("CADRES BY WARDS"))
		{
			if (IConstants.URBAN_TYPE.equals(regionId.substring(0,1)))
			{
				cadreRegionInfo = cadreManagementService.getLocalElectionBodyCadresByWards(new Long(regionId.substring(1)), userID);
				
			}			
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			
			/*log.debug("region:"+region);
			cadreInfo = cadreManagementService.getCadresByHamlet(regionId.toString(), userID);
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);*/
		}
		else if(region.equalsIgnoreCase("CADRES BY BOOTHS"))
		{
			List<CadreRegionInfoVO> nonBoothRegionInfo = new ArrayList<CadreRegionInfoVO>(0);
			if (IConstants.URBAN_TYPE.equals(regionId.substring(0,1)))
			{
				cadreRegionInfo = cadreManagementService.getLocalElectionBodyAllBoothsCadres(new Long(regionId.substring(1)), userID);
				nonBoothRegionInfo = cadreManagementService.getAllNonAssignedBoothCadresByLocalBody(new Long(regionId.substring(1)),userID);
				if(nonBoothRegionInfo.size()>0)
					cadreRegionInfo.addAll(nonBoothRegionInfo);
			}
			if (IConstants.RURAL_TYPE.equals(regionId.substring(0,1)))
			{
				cadreRegionInfo = cadreManagementService.getMandalAllBoothsCadres(new Long(regionId.substring(1)), userID);	
				nonBoothRegionInfo = cadreManagementService.getAllNonAssignedBoothCadresByTehsil(new Long(regionId.substring(1)),userID);
				if(nonBoothRegionInfo.size()>0)
					cadreRegionInfo.addAll(nonBoothRegionInfo);
			}			
			cadreDetailsInfoVO.setCadreRegionInfo(cadreRegionInfo);
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			
			/*log.debug("region:"+region);
			cadreInfo = cadreManagementService.getCadresByHamlet(regionId.toString(), userID);
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);*/
		}
		else if(region.equalsIgnoreCase("BOOTH"))
		{
			log.debug("region:"+region);
			cadreInfo = cadreManagementService.getCadresByBooth(regionId.toString(), userID.toString());
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		}
		else if(region.equalsIgnoreCase("Not Assigned To Any Booth"))
		{
			log.debug("region:"+region);
			cadreInfo = cadreManagementService.getCadresNotAssignedWithBooth(new Long(regionId.toString()),userID.toString());
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
		}
	 return Action.SUCCESS;
	}
	
	public String getBoothwiseCadreInfo(){
	
		cadreDetailsInfoVO = new CadreDetailsInfoVO();
		session = request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		Long userID = user.getRegistrationID();
		
		
		String param = getTask();
				
		try {
			jObj = new JSONObject(param);
			System.out.println(jObj);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		
		String taskType = jObj.getString("cadreRegion");
		Long regionId = jObj.getLong("cadreId");
		String cadreType = jObj.getString("cadreType");
		
		cadreInfo = new ArrayList<CadreInfo>();
		log.debug("region::"+region);
		
		
		if("CADRES BY BOOTHS IN WARD".equalsIgnoreCase(taskType))
		{
			String regionStr = regionId.toString();
			String subStr = regionStr.substring(1);
			cadreInfo = cadreManagementService.getCadresAssignedWithBoothInWard(new Long(subStr),userID.toString());
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			
			
		} else if("Not Assigned To Any Booth".equalsIgnoreCase(taskType))
		{
			String regionStr = regionId.toString();
			String subStr = regionStr.substring(1);
			cadreInfo = cadreManagementService.getCadresNotAssignedWithBoothInWard(new Long(subStr),userID.toString());
			
			cadreDetailsInfoVO.setCadreInfo(cadreInfo);			
		}
		
		
		
		
		return Action.SUCCESS;
	}
	
	
	public String getZeroLevelCadre()
	{
		Boolean isParent = true;
		cadreDetailsInfoVO = new CadreDetailsInfoVO();
		region = request.getParameter("cadreRegion");
		regionId = request.getParameter("cadreId");
		cadreType = request.getParameter("cadreType");
		cadreDetailsInfoVO.setRegion(region);
		cadreDetailsInfoVO.setRegionId(regionId);
		cadreDetailsInfoVO.setCadreType(cadreType);
		cadreRegionInfo = new ArrayList<CadreRegionInfoVO>();
		cadreInfo = new ArrayList<CadreInfo>();
		session=request.getSession();
		RegistrationVO user = (RegistrationVO) session.getAttribute("USER");
		
		if(user.getParentUserId() != null)			
			isParent = false;
		
		Long userID = user.getParentUserId() == null ? user.getRegistrationID() : user.getParentUserId();
		log.debug("In if Zero level cadre");
		UserCadresInfoVO userCadresInfoVo = (UserCadresInfoVO) session.getAttribute("USERCADRESINFOVO");
		List<SelectOptionVO> regions = new ArrayList<SelectOptionVO>();
		Map<Long, String> zeroLevelCadres = new HashMap<Long, String>();
		if(cadreType.equalsIgnoreCase("ZeroLevelCadre"))
		{
		
			if(region.equalsIgnoreCase("STATE"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreStates();
				 cadreDetailsInfoVO.setZeroCadresRegion1(getStates(zeroLevelCadres));
			}else if(region.equalsIgnoreCase("DISTRICT"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreDistricts();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToDistrictByDistrict(keys));
					 
			}else if(region.equalsIgnoreCase("PARLIAMENT CONSTITUENCY"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreParlConstituencies();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToConstituencyByParlConstituency(keys));					 
			}else if(region.equalsIgnoreCase("CONSTITUENCY"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreConstituencies();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToConstituencyByConstituency(keys));					 
			}else if(region.equalsIgnoreCase("MANDAL"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreMandals();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToMandalByTehsil(keys));					
			}else if(region.equalsIgnoreCase("MUNICIPAL/CORP/GMC"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreLocalElectionBodies();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToLocalElectionBodyByLEB(keys));
			}else if(region.equalsIgnoreCase("WARDS"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreWards();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToWardByWard(keys));					 
			}
			
			else if(region.equalsIgnoreCase("VILLAGE"))
			{
				zeroLevelCadres=userCadresInfoVo.getZeroCadreHamlets();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToHamletByHamlets(keys));
				
				 /*zeroLevelCadres=userCadresInfoVo.getZeroCadreVillages();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToRevenueVillageByRV(keys));
					 //zeroCadresRegion1=cadreManagementService.getStateToRevenueVillageByRV(keys);
*/			}else if(region.equalsIgnoreCase("HAMLET"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreHamlets();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getStateToHamletByHamlets(keys));					 
			}
			else if(region.equalsIgnoreCase("BOOTHS IN MANDALS"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreBoothsInMandal();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getVillageToBoothByBooths(keys));					 
			}
			else if(region.equalsIgnoreCase("BOOTHS In Municipal/Corp/GMC"))
			{
				 zeroLevelCadres=userCadresInfoVo.getZeroCadreBooths();
				 String keys = getIDs(zeroLevelCadres);
				 if(keys.length()>0)
					 cadreDetailsInfoVO.setZeroCadresRegion1(cadreManagementService.getLocalElectionBodyToBoothByBooths(keys));					 
			}
			 
			 Set<Long> set = zeroLevelCadres.keySet();
			 Iterator<Long> iterator = set.iterator();
			 while(iterator.hasNext()){
				 Long key = iterator.next();
				 String value = zeroLevelCadres.get(key);
				 SelectOptionVO obj = new SelectOptionVO();
				 obj.setId(key);
				 obj.setName(value);
				 regions.add(obj);
			 }
			 cadreDetailsInfoVO.setZeroCadresRegion(regions);			
		}else if(cadreType.equalsIgnoreCase("RegionLevelCadre"))
			{
				log.debug("In if Region level cadre");	
				cadreInfo = cadreManagementService.getCadresByCadreLevel(region, userID, isParent, user.getAccessType(), new Long(user.getAccessValue()));
				cadreDetailsInfoVO.setCadreInfo(cadreInfo);
			}
		 return Action.SUCCESS;
	}
	

	public void setServletRequest(HttpServletRequest request) {
		
		this.request=request;
	}

	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	
	private String getIDs(Map<Long, String> map){
		StringBuilder sb = new StringBuilder();
		Set<Entry<Long, String>> set = map.entrySet();
		Iterator<Entry<Long, String>> iterator = set.iterator();
		while(iterator.hasNext()){
			Entry<Long, String> entry = iterator.next();
			sb.append(IConstants.COMMA).append(entry.getKey());
		}
		String result = new String();
		if(sb.length()>0)
			result = sb.substring(1);
		return result;
	}
	
	
	private List<StateToHamletVO> getStates(Map<Long, String> map){
		List<StateToHamletVO> results = new ArrayList<StateToHamletVO>();
		Set<Entry<Long, String>> set = map.entrySet();
		Iterator<Entry<Long, String>> iterator = set.iterator();
		while(iterator.hasNext()){
			Entry<Long, String> entry = iterator.next();
			StateToHamletVO vo = new StateToHamletVO();
			SelectOptionVO state = new SelectOptionVO(entry.getKey(), entry.getValue());
			vo.setState(state);
			results.add(vo);
		}
		return results;
	}

}
