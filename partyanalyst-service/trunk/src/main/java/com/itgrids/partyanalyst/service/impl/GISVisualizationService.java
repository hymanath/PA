package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.GISVisualizationBasicVO;
import com.itgrids.partyanalyst.dto.GISVisualizationDetailsVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IGISVisualizationService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

/*
 * @author Srishailam Pittala
 * @Date 08th,Oct, 2016
 * 
 * */
public class GISVisualizationService implements IGISVisualizationService{
	private static final Logger LOG = Logger.getLogger(GISVisualizationService.class);
	
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO;
	private IBoothConstituencyElectionDAO boothConstituencyElectionDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;  
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IBoothDAO boothDAO;
	private ITehsilDAO tehsilDAO;
	private IPanchayatDAO panchayatDAO;
	private IRegionServiceData regionServiceDataImp;
	private ITdpCadreDAO tdpCadreDAO;
	
	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}
	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public ITdpCadreDAO getTdpCadreDAO() {
		return tdpCadreDAO;
	}
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}
	public IDelimitationConstituencyMandalDAO getDelimitationConstituencyMandalDAO() {
		return delimitationConstituencyMandalDAO;
	}
	public void setDelimitationConstituencyMandalDAO(
			IDelimitationConstituencyMandalDAO delimitationConstituencyMandalDAO) {
		this.delimitationConstituencyMandalDAO = delimitationConstituencyMandalDAO;
	}
	public IBoothConstituencyElectionDAO getBoothConstituencyElectionDAO() {
		return boothConstituencyElectionDAO;
	}
	public void setBoothConstituencyElectionDAO(
			IBoothConstituencyElectionDAO boothConstituencyElectionDAO) {
		this.boothConstituencyElectionDAO = boothConstituencyElectionDAO;
	}
	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}
	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}
	public IAssemblyLocalElectionBodyWardDAO getAssemblyLocalElectionBodyWardDAO() {
		return assemblyLocalElectionBodyWardDAO;
	}
	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}
	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}
	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}
	
	public Map<Long,GISVisualizationDetailsVO> buildLocationsDetails(List<Object[]> locationsList){
		Map<Long,GISVisualizationDetailsVO> returnMap = new HashMap<Long,GISVisualizationDetailsVO>(0);
		try {
			if(commonMethodsUtilService.isListOrSetValid(locationsList)){
				for (Object[] param : locationsList) {
					Long id = commonMethodsUtilService.getLongValueForObject(param[0]);
					String name = commonMethodsUtilService.getStringValueForObject(param[1]);
					returnMap.put(id, new GISVisualizationDetailsVO(id,name));
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in buildLocationsDetails Method in GISVisualizationService Class",e);
		}
		return returnMap;
	}
	public Map<Long,GISVisualizationDetailsVO> getLocationDetailsBasedOnParameters(GISVisualizationParameterVO inputVO){
		Map<Long,GISVisualizationDetailsVO> returnMap = new HashMap<Long,GISVisualizationDetailsVO>(0);
		try {
			List<Object[]> locationsList = null;
			if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE) ){
				locationsList = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1L, inputVO.getStateId());
				returnMap = buildLocationsDetails(locationsList);
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
				locationsList = constituencyDAO.getConstituenciesByDistrictId(inputVO.getParentLocationTypeId());
				if(inputVO.getChildLocationTypeId().longValue()>0L)
					locationsList = constituencyDAO.getConstituencyDetailsByCintiId(inputVO.getChildLocationTypeId().longValue());
				returnMap = buildLocationsDetails(locationsList);
			}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN))
				locationsList = boothDAO.getBoothsInAConstituencyByPublication(inputVO.getParentLocationTypeId(), IConstants.AFFILIATED_VOTER_PUBLICATION_ID);
				else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL) || inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURALURBAN)){
					List<SelectOptionVO> mandalORMuncipalityList = regionServiceDataImp.getMandalsAndMuncipalitiesByConstituencyID(inputVO.getParentLocationTypeId());
					if(commonMethodsUtilService.isListOrSetValid(mandalORMuncipalityList)){
						for (SelectOptionVO selectOptionVO : mandalORMuncipalityList) {
							returnMap.put(selectOptionVO.getId(), new GISVisualizationDetailsVO(selectOptionVO.getId(),selectOptionVO.getName()));
						}
					}
				}
			}
			else {
				Long mandalORMuncipalityId = inputVO.getParentLocationTypeId();
				//String mandalORMuncipalityIdStr = mandalORMuncipalityId.toString().substring(0, 1);
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.URBAN) ){
					locationsList= boothDAO.getBoothsByLocalElectionBody(mandalORMuncipalityId,IConstants.AFFILIATED_VOTER_PUBLICATION_ID);
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					locationsList= boothDAO.getBoothsByTehsilId(mandalORMuncipalityId,IConstants.AFFILIATED_VOTER_PUBLICATION_ID);
				}
				returnMap = buildLocationsDetails(locationsList);
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getLocationDetailsBasedOnParameters Method in GISVisualizationService Class",e);
		}
		return returnMap;
	}
	
	
	public GISVisualizationDetailsVO getMembershipDriveVisualizationDetails(GISVisualizationParameterVO inputVO){
		GISVisualizationDetailsVO parentLocationVO = new GISVisualizationDetailsVO();
	  try {
		  Map<Long,GISVisualizationDetailsVO> locationsMap = null;
		   //if(inputVO.getChildLocationTypeId().longValue()>0L){
			   locationsMap =  getLocationDetailsBasedOnParameters(inputVO);
		  // }
		   
		   List<Object[]> locationDetails = tdpCadreDAO.getLocationsRegistrationsDetails(inputVO);
		   Long dailyTargetCount = IConstants.DAY_WISE_TARGET_REGISTRATIONS_COUNT;
		   
		   if(commonMethodsUtilService.isListOrSetValid(locationDetails)){
			   for (Object[] param : locationDetails) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String areaType = commonMethodsUtilService.getStringValueForObject(param[4]);
					Long registeredCount = commonMethodsUtilService.getLongValueForObject(param[6]);
					if(areaType == null || areaType.length()==0)
						areaType = commonMethodsUtilService.getStringValueForObject(param[2]);
					Float perc = (float) (registeredCount*100.0/dailyTargetCount);
					
					GISVisualizationDetailsVO locationVO = locationsMap.get(locationId);
					if(locationVO != null){
						locationVO.setAreaType(areaType);
						locationVO.setRegisteredCount(registeredCount);
						locationVO.setPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(perc));
						
						parentLocationVO.setRegisteredCount(parentLocationVO.getRegisteredCount()+registeredCount);
					}
			   }
			   parentLocationVO.setStartedCount(Long.valueOf(String.valueOf(locationDetails.size())));
		   }
		   
		   parentLocationVO.setTotalCount(Long.valueOf(String.valueOf(locationsMap.size())));
		   parentLocationVO.setNotStartedCount(parentLocationVO.getTotalCount()-parentLocationVO.getStartedCount());
		   parentLocationVO.getLocationsList().addAll(locationsMap.values());
		   List<GISVisualizationBasicVO> statusList = updateLocationPerformanceStatusDetails(locationsMap);
		   if(commonMethodsUtilService.isListOrSetValid(statusList))
			   parentLocationVO.getStatusList().addAll(statusList);
		   
	} catch (Exception e) {
		LOG.error("Exception Occured in getMembershipDriveVisualizationDetails Method in GISVisualizationService Class",e);
	}
	  return parentLocationVO;
	}
	
	public List<GISVisualizationBasicVO> updateLocationPerformanceStatusDetails(Map<Long,GISVisualizationDetailsVO> locationsMap){
		List<GISVisualizationBasicVO> statusList = new ArrayList<GISVisualizationBasicVO>(0);
		Map<String ,GISVisualizationBasicVO> statusMap = new HashMap<String, GISVisualizationBasicVO>(0);
		try {
			String[] statusArr = IConstants.MEMBERSHIP_DRIVE_PERFORMANCE_PARAMETERS;
			
			
			if(statusArr.length>0){
				for (int i = 0; i < statusArr.length; i++) {
					String[] tempstatusArr =statusArr[i].split("-");
					GISVisualizationBasicVO statusVO = new GISVisualizationBasicVO();
					statusVO.setStatus(commonMethodsUtilService.getStringValueForObject(tempstatusArr[0]));
					statusVO.setPerc(commonMethodsUtilService.getStringValueForObject(tempstatusArr[1]));
					statusVO.setColorCode(commonMethodsUtilService.getStringValueForObject(tempstatusArr[2]));
					
					statusMap.put(statusVO.getStatus(), statusVO);
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationsMap)){
				for (Long locationId : locationsMap.keySet()) {
					GISVisualizationDetailsVO vo = locationsMap.get(locationId);
					if(vo != null){
						Double perc = Double.valueOf(vo.getPerc());
						if(perc>=90.0){
							GISVisualizationBasicVO statusVO = statusMap.get("VERY GOOD".trim());
							statusVO.setCount(statusVO.getCount()+1);
						}
						else if(perc>=80.0 && perc<90.0){
							GISVisualizationBasicVO statusVO = statusMap.get("GOOD".trim());
							statusVO.setCount(statusVO.getCount()+1);
						}
						else if(perc>=60.0 && perc<80.0){
							GISVisualizationBasicVO statusVO = statusMap.get("OK".trim());
							statusVO.setCount(statusVO.getCount()+1);
						}
						else if(perc>=30.0 && perc<60.0){
							GISVisualizationBasicVO statusVO = statusMap.get("POOR".trim());
							statusVO.setCount(statusVO.getCount()+1);
						}
						else if(perc>=10.0 && perc<30.0){
							GISVisualizationBasicVO statusVO = statusMap.get("VERY POOR".trim());
							statusVO.setCount(statusVO.getCount()+1);
						}else{
							GISVisualizationBasicVO statusVO = statusMap.get("OTHERS".trim());
							statusVO.setCount(statusVO.getCount()+1);
						}
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(statusMap)){
				statusList.addAll(statusMap.values());
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in calculateLocationPerformance Method in GISVisualizationService Class",e);
		}
		return statusList;
	}
	
}
