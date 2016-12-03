package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothConstituencyElectionDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICadreSurveyUserAssignDetailsDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITabUserLocationDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreTargetCountDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfo;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GISVisualizationBasicVO;
import com.itgrids.partyanalyst.dto.GISVisualizationDetailsVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.service.IGISVisualizationService;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
	private ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO;
	private ITdpCadreDateWiseInfoDAO tdpCadreDateWiseInfoDAO;
	private ITdpCadreTargetCountDAO tdpCadreTargetCountDAO;
	private ITabUserLocationDetailsDAO tabUserLocationDetailsDAO;
	private ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO;
	
	
	
	
	
	public ITdpCadreUserHourRegInfo getTdpCadreUserHourRegInfoDAO() {
		return tdpCadreUserHourRegInfoDAO;
	}
	public void setTdpCadreUserHourRegInfoDAO(
			ITdpCadreUserHourRegInfo tdpCadreUserHourRegInfoDAO) {
		this.tdpCadreUserHourRegInfoDAO = tdpCadreUserHourRegInfoDAO;
	}
	public ITabUserLocationDetailsDAO getTabUserLocationDetailsDAO() {
		return tabUserLocationDetailsDAO;
	}
	public void setTabUserLocationDetailsDAO(
			ITabUserLocationDetailsDAO tabUserLocationDetailsDAO) {
		this.tabUserLocationDetailsDAO = tabUserLocationDetailsDAO;
	}
	public ITdpCadreTargetCountDAO getTdpCadreTargetCountDAO() {
		return tdpCadreTargetCountDAO;
	}
	public void setTdpCadreTargetCountDAO(
			ITdpCadreTargetCountDAO tdpCadreTargetCountDAO) {
		this.tdpCadreTargetCountDAO = tdpCadreTargetCountDAO;
	}
	public ITdpCadreDateWiseInfoDAO getTdpCadreDateWiseInfoDAO() {
		return tdpCadreDateWiseInfoDAO;
	}
	public void setTdpCadreDateWiseInfoDAO(
			ITdpCadreDateWiseInfoDAO tdpCadreDateWiseInfoDAO) {
		this.tdpCadreDateWiseInfoDAO = tdpCadreDateWiseInfoDAO;
	}
	public ITdpCadreLocationInfoDAO getTdpCadreLocationInfoDAO() {
		return tdpCadreLocationInfoDAO;
	}
	public void setTdpCadreLocationInfoDAO(
			ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO) {
		this.tdpCadreLocationInfoDAO = tdpCadreLocationInfoDAO;
	}
	public ICadreSurveyUserAssignDetailsDAO getCadreSurveyUserAssignDetailsDAO() {
		return cadreSurveyUserAssignDetailsDAO;
	}
	public void setCadreSurveyUserAssignDetailsDAO(
			ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO) {
		this.cadreSurveyUserAssignDetailsDAO = cadreSurveyUserAssignDetailsDAO;
	}
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
				//locationsList = constituencyDAO.getConstituenciesByDistrictId(inputVO.getParentLocationTypeId());
				locationsList = constituencyDAO.getConstituenciesByStatTypeId(inputVO.getStateId());
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
				if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ){
					locationsList= boothDAO.getBoothsByLocalElectionBody(mandalORMuncipalityId,IConstants.AFFILIATED_VOTER_PUBLICATION_ID);
				}else if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					locationsList= panchayatDAO.getPanchayatsBymandalId(mandalORMuncipalityId);
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
		   locationsMap =  getLocationDetailsBasedOnParameters(inputVO);
		   Map<Long,Long> loctnsTargetCntMap = new HashMap<Long,Long>();
		   //updateUserTrackingDetasil(locationsMap,inputVO);
		   if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURALURBAN)){
			   inputVO.setChildLocationType(IConstants.RURAL);
			   List<Object[]> targetList = tdpCadreTargetCountDAO.getTargetCountForLocationsWise(inputVO);
			   
			   if(targetList != null && targetList.size() > 0){
				   for(Object[] obj :targetList){
					   loctnsTargetCntMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]),commonMethodsUtilService.getLongValueForObject(obj[2]));
				   }
			   }
			   
			   inputVO.setChildLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL);
			   targetList = tdpCadreTargetCountDAO.getTargetCountForLocationsWise(inputVO);
			   
			   if(targetList != null && targetList.size() > 0){
				   for(Object[] obj :targetList){
					   loctnsTargetCntMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]),commonMethodsUtilService.getLongValueForObject(obj[2]));
				   }
			   }
			   inputVO.setChildLocationType(IConstants.RURALURBAN);
		   }else{
			   	List<Object[]> targetList = tdpCadreTargetCountDAO.getTargetCountForLocationsWise(inputVO);
			   
			   	if(targetList != null && targetList.size() > 0){
				   for(Object[] obj :targetList){
					   loctnsTargetCntMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]),commonMethodsUtilService.getLongValueForObject(obj[2]));
				   }
			   	}
		   }
		   //tdpCadreDAO.getLocationsRegistrationsDetails(inputVO);
			upateElectionResulstsForVisualization(parentLocationVO,locationsMap,inputVO);
		   
		   if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && inputVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN)){
			   inputVO.setChildLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ;
			   List<Object[]> locationDetails = tdpCadreLocationInfoDAO.getLocationsRegistrationsDetails(inputVO);
			   setMembershipDriveVisualizationDetails(locationDetails,parentLocationVO,locationsMap,loctnsTargetCntMap);
			   inputVO.setChildLocationType(IConstants.RURAL) ;
			   List<Object[]> locationDetails1 = tdpCadreLocationInfoDAO.getLocationsRegistrationsDetails(inputVO);
			   setMembershipDriveVisualizationDetails(locationDetails1,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}else if(inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURALURBAN)){
					inputVO.setChildLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ;
				   List<Object[]> locationDetails = tdpCadreLocationInfoDAO.getLocationsRegistrationsDetails(inputVO);
				   setMembershipDriveVisualizationDetails(locationDetails,parentLocationVO,locationsMap,loctnsTargetCntMap);
				   inputVO.setChildLocationType(IConstants.RURAL) ;
				   List<Object[]> locationDetails1 = tdpCadreLocationInfoDAO.getLocationsRegistrationsDetails(inputVO);
				   setMembershipDriveVisualizationDetails(locationDetails1,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}else{
			
				 List<Object[]> locationDetails2 = tdpCadreLocationInfoDAO.getLocationsRegistrationsDetails(inputVO);
				  setMembershipDriveVisualizationDetails(locationDetails2,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}
		   
		   //between dates
		   if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && inputVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN)){
			   inputVO.setChildLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ;
			   List<Object[]> locationDetails = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
			   setDateWiseMembershipDriveVisualizationDetails(locationDetails,parentLocationVO,locationsMap,loctnsTargetCntMap);
			   inputVO.setChildLocationType(IConstants.RURAL) ;
			   List<Object[]> locationDetails1 = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
			   setDateWiseMembershipDriveVisualizationDetails(locationDetails1,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}else{
				 List<Object[]> locationDetails2 = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
				 setDateWiseMembershipDriveVisualizationDetails(locationDetails2,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}
		   
		   //today
		   inputVO.setStartDate(new SimpleDateFormat("dd-MM-yyyy").format(new DateUtilService().getCurrentDateAndTime()));
		   inputVO.setEndDate(new SimpleDateFormat("dd-MM-yyyy").format(new DateUtilService().getCurrentDateAndTime()));
		   
		   if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && inputVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN)){
			   inputVO.setChildLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ;
			   List<Object[]> locationDetails = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
			   setDateWiseMembershipDriveVisualizationDetails(locationDetails,parentLocationVO,locationsMap,loctnsTargetCntMap);
			   inputVO.setChildLocationType(IConstants.RURAL) ;
			   List<Object[]> locationDetails1 = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
			   setDateWiseMembershipDriveVisualizationDetails(locationDetails1,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}else{
				 List<Object[]> locationDetails2 = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
				 setDateWiseMembershipDriveVisualizationDetails(locationDetails2,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}
		   
		 if(parentLocationVO != null){
			 Long todayTarget = parentLocationVO.getTargetCount()/IConstants.CADRE_REGISTRATION_2016_DAYS;
			 Double perc  = parentLocationVO.getTodayNewRegCount()*100.0/ todayTarget;
			 parentLocationVO.setTodayTarget(todayTarget);
			 parentLocationVO.setTodayRegPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(perc));
			 Double newPerc = 0.0;
			 if(parentLocationVO.getTodayRegCount().longValue() > 0l)
			 newPerc = parentLocationVO.getTodayNewRegCount()*100.0/parentLocationVO.getTodayRegCount();
			 Double renPerc = 0.0;
			 
			 if(parentLocationVO.getTodayRegCount().longValue() > 0l)
			 renPerc = parentLocationVO.getTodayRenewalCount()*100.0/parentLocationVO.getTodayRegCount();
			 parentLocationVO.setTodayNewRegPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(newPerc));
			 parentLocationVO.setTodayRenewalPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(renPerc));
			 
		 }
		 	
	} catch (Exception e) {
		LOG.error("Exception Occured in getMembershipDriveVisualizationDetails Method in GISVisualizationService Class",e);
	}
	  return parentLocationVO;
	}
	
	public void upateElectionResulstsForVisualization(GISVisualizationDetailsVO parentLocationVO,Map<Long,GISVisualizationDetailsVO> locationsMap,GISVisualizationParameterVO inputVO){
		try {
			List<Object[]> resultList = null;
			Map<Long,String> YCPMLAMap = new HashMap<Long, String>();
				if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
					resultList = boothConstituencyElectionDAO.getDistrictLevelElectionResultsForGISVisualization(inputVO);
					for (int i = 11; i <= 23; i++) { // YCP candidates joined in tdp districts list
							if(i != 15)// except 15 West Godavari distict
								YCPMLAMap.put(Long.valueOf(String.valueOf(i)), "YES");
					}
					setDataToMap( resultList,locationsMap, YCPMLAMap);
				}else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					resultList = boothConstituencyElectionDAO.getAssemblyLevelElectionResultsForGISVisualization(inputVO);
					YCPMLAMap = getYcpCandidatesJoinedIntoTDPParty();
					setDataToMap( resultList,locationsMap, YCPMLAMap);
				}else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					if(inputVO.getAreaType() != null && inputVO.getAreaType().equalsIgnoreCase(IConstants.RURAL)){
						resultList = boothConstituencyElectionDAO.getMandalLevelElectionResultsForGISVisualization(inputVO);
						setDataToMap( resultList,locationsMap, YCPMLAMap);
					}else if(inputVO.getAreaType() != null && inputVO.getAreaType().equalsIgnoreCase(IConstants.RURALURBAN)){
						inputVO.setParentLocationType(IConstants.RURAL);
						resultList = boothConstituencyElectionDAO.getMandalLevelElectionResultsForGISVisualization(inputVO);
						setDataToMap( resultList,locationsMap, YCPMLAMap);
						
						inputVO.setParentLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL);
						resultList = boothConstituencyElectionDAO.getMunciORUrbanLevelElectionResultsForGISVisualization(inputVO);
						setDataToMap( resultList,locationsMap, YCPMLAMap);
						inputVO.setParentLocationType(IConstants.ASSEMBLY_CONSTITUENCY_TYPE);
					}
					else if(inputVO.getAreaType() != null && inputVO.getAreaType().equalsIgnoreCase(IConstants.URBAN)){
						resultList = boothConstituencyElectionDAO.getLocalBodyBoothLevelElectionResultsForGISVisualization(inputVO);
						setDataToMap( resultList,locationsMap, YCPMLAMap);
					}
				}
				else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURAL)){
					inputVO.setParentLocationType(IConstants.PANCHAYAT);
					resultList = boothConstituencyElectionDAO.getPanchayatLevelElectionResultsForGISVisualization(inputVO);
					setDataToMap( resultList,locationsMap, YCPMLAMap);
				}
				else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.URBAN) || 
						 inputVO.getParentLocationType().equalsIgnoreCase(IConstants.MUNCIPALITY_CORPORATION_LEVEL)){
					resultList = boothConstituencyElectionDAO.getLocalBodyBoothLevelElectionResultsForGISVisualization(inputVO);
					setDataToMap( resultList,locationsMap, YCPMLAMap);
				}
				
				if(parentLocationVO != null && locationsMap != null && locationsMap.size()>0){
					for ( Long locationId: locationsMap.keySet()) {
						GISVisualizationDetailsVO vo = locationsMap.get(locationId);
						if(vo.getIsYCPArea() != null && vo.getIsYCPArea().equalsIgnoreCase("true")){
							parentLocationVO.setIsYCPArea("true");
							break;
						}
					}
				}
				
		} catch (Exception e) {
			LOG.error("Exception Occured in upateElectionResulstsForVisualization Method in GISVisualizationService Class",e);
		}
	}

	public void setDataToMap(List<Object[]> resultList,Map<Long,GISVisualizationDetailsVO> locationsMap,Map<Long,String> YCPMLAMap){
		try {
			if(commonMethodsUtilService.isListOrSetValid(resultList)){
				for (Object[] param : resultList) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					Long votersEarned = commonMethodsUtilService.getLongValueForObject(param[1]);
					Long validVotes = commonMethodsUtilService.getLongValueForObject(param[2]);
					
					String marginVotes = commonMethodsUtilService.getStringValueForObject(param[3]);
					String marginVotesPerc = commonMethodsUtilService.getStringValueForObject(param[4]);
					String rank = commonMethodsUtilService.getStringValueForObject(param[5]);
					
					GISVisualizationDetailsVO vo = locationsMap.get(locationId);
					boolean isAlreadySetPartyDetails = false;
					if(vo != null){
						if(vo.getFirstPositionPartyId() != null && vo.getFirstPositionPartyId().longValue()>0L )
							isAlreadySetPartyDetails = true;
						
						if(!isAlreadySetPartyDetails){
							String perc = (new BigDecimal(votersEarned*(100.0)/validVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
							vo.setIsYCPArea("false");
							//vo.setValidVotes(validVotes.toString());
							vo.setEarnedVotesIn2014(votersEarned.toString());
							vo.setEarnedVotesPercIn2014(perc.toString());
							vo.setMarginVotes(marginVotes);
							vo.setMarginVotesPerc(marginVotesPerc);
							//vo.setRank("LOSS");
							
							if(rank != null && rank.trim().equalsIgnoreCase("1"))
								vo.setRank("WON");
							    vo.setFirstPositionPartyId(commonMethodsUtilService.getLongValueForObject(param[6]));
							    vo.setFirstPositionPartyName(commonMethodsUtilService.getStringValueForObject(param[7]));
							    vo.setFirstPartyImageLogo("http://www.mytdp.in/images/party_flags/"+commonMethodsUtilService.getStringValueForObject(param[8]));
							if(YCPMLAMap.get(locationId) != null)
								vo.setIsYCPArea("true");	
						}else{//This section will execute only in the case of assembly.
							if(rank != null && rank.trim().equalsIgnoreCase("2")){
								vo.setSecondPositionPartyId(commonMethodsUtilService.getLongValueForObject(param[6]));
								vo.setSecondPositionPartyName(commonMethodsUtilService.getStringValueForObject(param[7]));
								vo.setSecondPartyImageLogo("http://www.mytdp.in/images/party_flags/"+commonMethodsUtilService.getStringValueForObject(param[8]));
							}else if(rank != null && rank.trim().equalsIgnoreCase("3")){
							   vo.setThirdPositionPartyId(commonMethodsUtilService.getLongValueForObject(param[6]));
							   vo.setThirdPositionPartyName(commonMethodsUtilService.getStringValueForObject(param[7]));
							   vo.setThirdPartyImageLogo("http://www.mytdp.in/images/party_flags/"+commonMethodsUtilService.getStringValueForObject(param[8]));
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public Map<Long,String> getYcpCandidatesJoinedIntoTDPParty(){
		
		 Map<Long,String> YCPCandidatesMap = new HashMap<Long, String>(0);
		try {
			YCPCandidatesMap.put(271L,"Chand Basha");							//kadiri
			YCPCandidatesMap.put(149L,"Jyothula Nehru");						//jaggampeta
			YCPCandidatesMap.put(212L,"Varapula Subbarao");						//prathipadu
			YCPCandidatesMap.put(244L,"Adi narayana Reddy");					//jammalamadugu
			YCPCandidatesMap.put(242L,"Jayaramulu");							//badvel
			YCPCandidatesMap.put(196L,"Jaleel Khan");							//vijayawada west
			YCPCandidatesMap.put(258L,"M.Mani Gandhi");							//kodumuru
			YCPCandidatesMap.put(262L,"Bhuma Nagi Reddy");						//nandyal
			YCPCandidatesMap.put(254L,"Bhuma Akhila Priya");					//allagadda
			YCPCandidatesMap.put(332L,"Budda Rajashekar Reddy");				//Srisailam
			YCPCandidatesMap.put(260L,"SV Mohan Reddy");						//Kurnool
			YCPCandidatesMap.put(231L,"Pasim Sunil Kumar");						//gudur
			YCPCandidatesMap.put(344L,"David Raju");							//yerragondapalem
			YCPCandidatesMap.put(218L,"Gottipati Ravi Kumar");					//Addanki
			YCPCandidatesMap.put(222L,"Muthamula Ashok Reddy");					//Giddalur
			YCPCandidatesMap.put(223L,"Pothula Rama Rao");						//Kandukur
			YCPCandidatesMap.put(114L,"Kalamatta Venkata Ramana"); 				//Pathapatnam
			YCPCandidatesMap.put(122L,"Ravu Venkata Sujaya Krishna Ranga Rao");//bobbili
			YCPCandidatesMap.put(359L,"Kidari Sarveswar Rao");					//Araku
			YCPCandidatesMap.put(284L,"N.Amarnath Reddy"); 						//Palamaner

		} catch (Exception e) {
			LOG.error("Exception Occured in getYcpCandidatesJoinedIntoTDPParty Method in GISVisualizationService Class",e);
		}
		return YCPCandidatesMap;
	}
	public void setMembershipDriveVisualizationDetails(List<Object[]> locationDetails,GISVisualizationDetailsVO parentLocationVO,
			Map<Long,GISVisualizationDetailsVO> locationsMap,Map<Long,Long> loctnsTargetCntMap){
		try{
			//Long dailyTargetCount = IConstants.DAY_WISE_TARGET_REGISTRATIONS_COUNT;
			 //  Long totalTerget  = 1000000l;
		 if(commonMethodsUtilService.isListOrSetValid(locationDetails)){
			   for (Object[] param : locationDetails) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String areaType = commonMethodsUtilService.getStringValueForObject(param[4]);
				
					Long regCount2014 = commonMethodsUtilService.getLongValueForObject(param[6]);
					Long regCount2016 = commonMethodsUtilService.getLongValueForObject(param[8]);
					Long newRegCount = commonMethodsUtilService.getLongValueForObject(param[10]);
					Long renewalRegCount = commonMethodsUtilService.getLongValueForObject(param[12]);
					
					
					if(areaType == null || areaType.length()==0)
						areaType = commonMethodsUtilService.getStringValueForObject(param[2]);
					
					Long targetCount = 0l;
					targetCount = loctnsTargetCntMap.get(locationId);
					
					GISVisualizationDetailsVO locationVO = locationsMap.get(locationId);
					if(locationVO != null){
						locationVO.setAreaType(areaType);
						locationVO.setRegCount2014(regCount2014);
						locationVO.setRegCount2014Perc(commonMethodsUtilService.getStringValueForObject(param[7]));
						locationVO.setRegCount2016(regCount2016);
						locationVO.setRegCount2016Perc(commonMethodsUtilService.getStringValueForObject(param[9]));
						locationVO.setNewRegCount(newRegCount);
						locationVO.setNewRegCountPerc(commonMethodsUtilService.getStringValueForObject(param[11]));
						locationVO.setRenewalCount(renewalRegCount);
						locationVO.setRenewalCountPerc(commonMethodsUtilService.getStringValueForObject(param[13]));
						locationVO.setTargetCount(targetCount != null ? targetCount.longValue() : 0l);
						
						parentLocationVO.setRegCount2014(parentLocationVO.getRegCount2014()+regCount2014);
						parentLocationVO.setRegCount2016(parentLocationVO.getRegCount2016()+regCount2016);
						parentLocationVO.setNewRegCount(parentLocationVO.getNewRegCount()+newRegCount);
						parentLocationVO.setRenewalCount(parentLocationVO.getRenewalCount()+renewalRegCount);
						
						if(targetCount != null)
						parentLocationVO.setTargetCount(parentLocationVO.getTargetCount()+targetCount);
						
					}
			   }
			   parentLocationVO.setStartedCount(Long.valueOf(String.valueOf(locationDetails.size())));
		   }
		   
		   parentLocationVO.setTotalCount(Long.valueOf(String.valueOf(locationsMap.size())));
		   parentLocationVO.setNotStartedCount(parentLocationVO.getTotalCount()-parentLocationVO.getStartedCount());
		   if(parentLocationVO.getLocationsList() != null && parentLocationVO.getLocationsList().size()==0)
			   parentLocationVO.getLocationsList().addAll(locationsMap.values());
		   
		   if(parentLocationVO.getTargetCount() != null && parentLocationVO.getTargetCount().longValue() >0l){
		   Float reg2014Perc = (float) (parentLocationVO.getRegCount2014()*100.0/parentLocationVO.getTargetCount());
		   Float reg2016Perc = (float) (parentLocationVO.getRegCount2016()*100.0/parentLocationVO.getTargetCount());
		   Float newCadrePerc = (float) (parentLocationVO.getNewRegCount()*100.0/parentLocationVO.getTargetCount());
		   Float renewalCadrePerc = (float) (parentLocationVO.getRenewalCount()*100.0/parentLocationVO.getTargetCount());
			
			parentLocationVO.setRegCount2014Perc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(reg2014Perc));
			parentLocationVO.setRegCount2016Perc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(reg2016Perc));
			parentLocationVO.setNewRegCountPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(newCadrePerc));
			parentLocationVO.setRenewalCountPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(renewalCadrePerc));
		   }
		   
		   List<GISVisualizationBasicVO> statusList = updateLocationPerformanceStatusDetails(locationsMap);

		   if(commonMethodsUtilService.isListOrSetValid(statusList))
			   parentLocationVO.getStatusList().addAll(statusList);
		   
	}catch (Exception e) {
				LOG.error("Exception Occured in setMembershipDriveVisualizationDetails Method in GISVisualizationService Class",e);
			}
	}
	public List<GISUserTrackingVO> updateUserTrackingDetasil(Map<Long,GISVisualizationDetailsVO> locationsMap,GISVisualizationParameterVO inputVO){
		List<GISUserTrackingVO> userTrackingList = new ArrayList<GISUserTrackingVO>(0);
		try {
				List<Object[]> assignedUsersList = cadreSurveyUserAssignDetailsDAO.getUserTrackingDetails(inputVO);
				if(commonMethodsUtilService.isListOrSetValid(assignedUsersList)){
					for (Object[] param : assignedUsersList) {
						Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						GISVisualizationDetailsVO locationVO = locationsMap.get(locationId);
						if(locationVO != null){
							GISUserTrackingVO trackingVO = new GISUserTrackingVO();
							trackingVO.setAllocatedCount(commonMethodsUtilService.getLongValueForObject(param[2]));
							locationVO.setUserTrackingVO(trackingVO);
						}
					}
				}
				
				List<Object[]> trackingList = tdpCadreDAO.getLocationsUserTrackingDetails(inputVO,"all");
				Set<Long> userIdsList = new HashSet<Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(trackingList)){
					for (Object[] param : trackingList) {
						Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						GISVisualizationDetailsVO locationVO = locationsMap.get(locationId);
						if(locationVO != null){
							GISUserTrackingVO trackingVO = locationVO.getUserTrackingVO();
							if(trackingVO != null){
								trackingVO.setRegisteredCount(trackingVO.getRegisteredCount()+commonMethodsUtilService.getLongValueForObject(param[7]));
								
								/*GISUserTrackingVO userVO = new GISUserTrackingVO();
								userVO.setId(commonMethodsUtilService.getLongValueForObject(param[6]));
								userVO.setRegisteredCount(commonMethodsUtilService.getLongValueForObject(param[7]));
								if(!userIdsList.contains(userVO.getId())){
									trackingVO.getUsersList().add(userVO);
									userIdsList.add(userVO.getId());
								
								}*/
							}
						}
					}
				}
			
				List<Object[]> LastOneHrTrackingList = tdpCadreDAO.getLocationsUserTrackingDetails(inputVO,"LastOneHr");
				Set<Long> OneHruserIdsList = new HashSet<Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(LastOneHrTrackingList)){
					for (Object[] param : LastOneHrTrackingList) {
						Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
						GISVisualizationDetailsVO locationVO = locationsMap.get(locationId);
						if(locationVO != null){
							GISUserTrackingVO trackingVO = locationVO.getUserTrackingVO();
							if(trackingVO != null){
								trackingVO.setLastOneHrCount(trackingVO.getLastOneHrCount()+commonMethodsUtilService.getLongValueForObject(param[7]));
								
								/*GISUserTrackingVO userVO = new GISUserTrackingVO();
								userVO.setId(commonMethodsUtilService.getLongValueForObject(param[6]));
								userVO.setLastOneHrCount(commonMethodsUtilService.getLongValueForObject(param[7]));
								
								if(!OneHruserIdsList.contains(userVO.getId())){
									trackingVO.getLastOneHrusersList().add(userVO);
									OneHruserIdsList.add(userVO.getId());
									
								}*/
							}
						}
					}
				}
				
				for (Long locationId : locationsMap.keySet()) {
					GISVisualizationDetailsVO locationVO =locationsMap.get(locationId);
					if(locationVO != null){
						GISUserTrackingVO trackingVO = locationVO.getUserTrackingVO();
						if(trackingVO != null){
							if(commonMethodsUtilService.isListOrSetValid(trackingVO.getUsersList())){
								try{
									Float avg1 = (float)(trackingVO.getRegisteredCount()/trackingVO.getUsersList().size());
									Double avgPerc1 = Math.floor(Double.valueOf(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(avg1)));
									trackingVO.setAvgOutput(avgPerc1.longValue());
								} catch (Exception e) {
									//LOG.error("101 : Exception Occured While updating percentage ranges in updateUserTrackingDetasil method of GISVisualizationService ",e);
								}
							}
							if(commonMethodsUtilService.isListOrSetValid(trackingVO.getLastOneHrActiveusersList())){
								try{
									Float avg = (float)(trackingVO.getLastOneHrCount()/trackingVO.getLastOneHrActiveusersList().size());
									Double avgPerc = Math.floor(Double.valueOf(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(avg)));
									trackingVO.setLastOneHrAvgCount(avgPerc.longValue());
								} catch (Exception e) {
									//LOG.error("102 :  Exception Occured While updating percentage ranges in updateUserTrackingDetasil method of GISVisualizationService ",e);
								}
							}
						}
					}
				}
				
		} catch (Exception e) {
			LOG.error("Exception Occured in updateUserTrackingDetasil Method in GISVisualizationService Class",e);
		}
		return userTrackingList;
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
					if(i==0)
						statusVO.setPerc(" Above-100 % ");
					else if(i==1)
						statusVO.setPerc(" 90 - 100 % ");
					else if(i==2)
						statusVO.setPerc(" 80 - 90 % ");
					else if(i==3)
						statusVO.setPerc(" 60 - 80 % ");
					else if(i==4)
						statusVO.setPerc(" Below - 60 % ");
					//statusVO.setPerc(commonMethodsUtilService.getStringValueForObject(tempstatusArr[1]));
					//statusVO.setColorCode(commonMethodsUtilService.getStringValueForObject(tempstatusArr[2]));
					
					statusMap.put(statusVO.getStatus(), statusVO);
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationsMap)){
				for (Long locationId : locationsMap.keySet()) {
					try {
						GISVisualizationDetailsVO vo = locationsMap.get(locationId);
						if(vo != null){
							Double perc = Double.valueOf(vo.getRegCount2016Perc());
							if(perc>100.0){
								GISVisualizationBasicVO statusVO = statusMap.get("VERY GOOD".trim());
								statusVO.setCount(statusVO.getCount()+1);
							}
							else if(perc>90.0 && perc<=100.0){
								GISVisualizationBasicVO statusVO = statusMap.get("GOOD".trim());
								statusVO.setCount(statusVO.getCount()+1);
							}
							else if(perc>80.0 && perc<=90.0){
								GISVisualizationBasicVO statusVO = statusMap.get("OK".trim());
								statusVO.setCount(statusVO.getCount()+1);
							}
							else if(perc>60.0 && perc<=80.0){
								GISVisualizationBasicVO statusVO = statusMap.get("POOR".trim());
								statusVO.setCount(statusVO.getCount()+1);
							}
							else if(perc<60.0){
								GISVisualizationBasicVO statusVO = statusMap.get("VERY POOR".trim());
								statusVO.setCount(statusVO.getCount()+1);
							}/*else{
								GISVisualizationBasicVO statusVO = statusMap.get("OTHERS".trim());
								statusVO.setCount(statusVO.getCount()+1);
							}*/
						}
					} catch (Exception e) {
						//LOG.error("103 :  Exception Occured While updating percentage ranges in updateLocationPerformanceStatusDetails method of GISVisualizationService ",e);
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
	public GISVisualizationDetailsVO getMembershipDriveDayWiseVisualizationDetails(GISVisualizationParameterVO inputVO){
		GISVisualizationDetailsVO parentLocationVO = new GISVisualizationDetailsVO();
	  try {
		  Map<Long,GISVisualizationDetailsVO> locationsMap = null;
		  Map<Long,Long> loctnsTargetCntMap = new HashMap<Long,Long>();
		   locationsMap =  getLocationDetailsBasedOnParameters(inputVO);
		   updateUserTrackingDetasil(locationsMap,inputVO);
		   List<Object[]> targetList = tdpCadreTargetCountDAO.getTargetCountForLocationsWise(inputVO);
		   
		   if(targetList != null && targetList.size() > 0){
			   for(Object[] obj :targetList){
				   loctnsTargetCntMap.put(commonMethodsUtilService.getLongValueForObject(obj[0]),commonMethodsUtilService.getLongValueForObject(obj[2]));
			   }
		   }
		   //tdpCadreDAO.getLocationsRegistrationsDetails(inputVO);
		   
		   if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURALURBAN)){
			   inputVO.setChildLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL) ;
			   List<Object[]> locationDetails = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
			   setDateWiseMembershipDriveVisualizationDetails(locationDetails,parentLocationVO,locationsMap,loctnsTargetCntMap);
			   inputVO.setChildLocationType(IConstants.RURAL) ;
			   List<Object[]> locationDetails1 = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
			   setDateWiseMembershipDriveVisualizationDetails(locationDetails1,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}else{
				 List<Object[]> locationDetails2 = tdpCadreDateWiseInfoDAO.getDateWiseLocationsRegistrationsDetails(inputVO);
				 setDateWiseMembershipDriveVisualizationDetails(locationDetails2,parentLocationVO,locationsMap,loctnsTargetCntMap);
			}
		   
		 //  List<GISVisualizationBasicVO> statusList = updateLocationPerformanceStatusDetails(locationsMap);

		   //if(commonMethodsUtilService.isListOrSetValid(statusList))
			//   parentLocationVO.getStatusList().addAll(statusList);
	} catch (Exception e) {
		LOG.error("Exception Occured in getMembershipDriveDayWiseVisualizationDetails Method in GISVisualizationService Class",e);
	}
	  return parentLocationVO;
	}
	
	public void setDateWiseMembershipDriveVisualizationDetails(List<Object[]> locationDetails,GISVisualizationDetailsVO parentLocationVO,
			Map<Long,GISVisualizationDetailsVO> locationsMap,Map<Long,Long> loctnsTargetCntMap){
		try{
			
		 if(commonMethodsUtilService.isListOrSetValid(locationDetails)){
			   for (Object[] param : locationDetails) {
					Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String areaType = commonMethodsUtilService.getStringValueForObject(param[4]);
				
					Long todayRegCount2016 = commonMethodsUtilService.getLongValueForObject(param[6]);
					Long todayNewRegCount = commonMethodsUtilService.getLongValueForObject(param[8]);
					Long todayreNewalRegCount = commonMethodsUtilService.getLongValueForObject(param[10]);
					
					if(areaType == null || areaType.length()==0)
						areaType = commonMethodsUtilService.getStringValueForObject(param[2]);
					Long targetCount = null;
					 targetCount = loctnsTargetCntMap.get(locationId);
					
					GISVisualizationDetailsVO locationVO = locationsMap.get(locationId);
					if(locationVO != null){
						
						locationVO.setAreaType(areaType);
						locationVO.setTodayRegCount(todayRegCount2016);
						locationVO.setTodayRegPerc(commonMethodsUtilService.getStringValueForObject(param[7]));
						locationVO.setTodayNewRegCount(todayNewRegCount);
						locationVO.setTodayNewRegPerc(commonMethodsUtilService.getStringValueForObject(param[9]));
						locationVO.setTodayRenewalCount(todayreNewalRegCount);
						locationVO.setTodayRenewalPerc(commonMethodsUtilService.getStringValueForObject(param[11]));
						locationVO.setTargetCount(targetCount != null ? targetCount.longValue() : 0l);
						
						parentLocationVO.setTodayRegCount(parentLocationVO.getTodayRegCount()+todayRegCount2016);
						parentLocationVO.setTodayNewRegCount(parentLocationVO.getTodayNewRegCount()+todayNewRegCount);
						parentLocationVO.setTodayRenewalCount(parentLocationVO.getTodayRenewalCount()+todayreNewalRegCount);
						
						if(targetCount != null)
						parentLocationVO.setTargetCount(parentLocationVO.getTargetCount()+targetCount);
						
					}
			   }
			   parentLocationVO.setStartedCount(Long.valueOf(String.valueOf(locationDetails.size())));
		   }
		   
		   parentLocationVO.setTotalCount(Long.valueOf(String.valueOf(locationsMap.size())));
		   parentLocationVO.setNotStartedCount(parentLocationVO.getTotalCount()-parentLocationVO.getStartedCount());
		   if( parentLocationVO.getLocationsList() != null &&  parentLocationVO.getLocationsList().size() == 0)
			   parentLocationVO.getLocationsList().addAll(locationsMap.values());
		   
		  
		   Float todayReg2016Perc = (float) (parentLocationVO.getTodayRegCount()*100.0/parentLocationVO.getTargetCount());
		   Float todayNewCadrePerc = (float) (parentLocationVO.getTodayNewRegCount()*100.0/parentLocationVO.getTargetCount());
		   Float todayRenewalCadrePerc = (float) (parentLocationVO.getTodayRenewalCount()*100.0/parentLocationVO.getTargetCount());
			
			parentLocationVO.setRegCount2016Perc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(todayReg2016Perc));
			parentLocationVO.setNewRegCountPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(todayNewCadrePerc));
			parentLocationVO.setRenewalCountPerc(commonMethodsUtilService.roundTo2DigitsFloatValueAsString(todayRenewalCadrePerc));
		   
		   
	}catch (Exception e) {
				LOG.error("Exception Occured in setDateWiseMembershipDriveVisualizationDetails Method in GISVisualizationService Class",e);
			}
	}
	
	public GISUserTrackingVO getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO){
		GISUserTrackingVO returnVO = new GISUserTrackingVO();
		try{
			
			if(inputVO.getChildLocationType() == null || !inputVO.getChildLocationType().equalsIgnoreCase("old")){
				return getNewLocationWiseTabUserTrackingDetails(inputVO);
			}
			
			//System.out.println(inputVO.getParentLocationType() +" starting @ : "+new Date());
			Set<Long> cadreSurveyUserIdsLsit = new HashSet<Long>(0);
			Map<Long,GISUserTrackingVO> tabUserMap = new HashMap<Long,GISUserTrackingVO>();
			//List<Object[]> totalTabUsersData = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"total");
			//List<Object[]> totalTabUsersData = tabUserLocationDetailsDAO.getLocationWiseTabUserTrackingDetails(inputVO,"total");
			List<Object[]> totalTabUsersData = tdpCadreUserHourRegInfoDAO.getLocationWiseTabUserTrackingDetails(inputVO,"total");
			setLocationWiseTabUserTrackingDetails(totalTabUsersData,"total",returnVO,tabUserMap,cadreSurveyUserIdsLsit);
			
			returnVO.setActiveCount(Long.valueOf(String.valueOf(cadreSurveyUserIdsLsit.size())));
			cadreSurveyUserIdsLsit.clear();
			
			Date fromDate=null;
			Date toDate=null;
			
			if(inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = sdf.parse(inputVO.getStartDate());
				 toDate  =sdf.parse(inputVO.getEndDate());
			}
			
			if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
				List<Object[]> latestObj = tabUserLocationDetailsDAO.getLattitudeLangitudeOfTabUser(inputVO.getParentLocationTypeId(),fromDate,toDate,inputVO.getParentLocationType());
				List<Long> locationIdsList = new ArrayList<Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(latestObj)){
					for (Object[] param : latestObj) 
						locationIdsList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
				if(commonMethodsUtilService.isListOrSetValid(locationIdsList)){
					List<Object[]> latestLatLongitudeDtls = tabUserLocationDetailsDAO.getLattitudeLangitudeOfTabbUserByIds(locationIdsList);
					if(commonMethodsUtilService.isListOrSetValid(latestLatLongitudeDtls)){
						for (Object[] param : latestLatLongitudeDtls) {
							try {
								GISUserTrackingVO tabVO =  tabUserMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
								if(tabVO != null){
									tabVO.setLattitude(validLattitudeORLongitudeDetails(commonMethodsUtilService.getStringValueForObject(param[3])));
									tabVO.setLongitude(validLattitudeORLongitudeDetails(commonMethodsUtilService.getStringValueForObject(param[4])));
									tabVO.setSurveyTime(commonMethodsUtilService.getStringValueForObject(param[5]));
								 }
							} catch (Exception e) {
								LOG.error("Error occured while updating langitude and lattitude for tab user for GIS Service ",e);
							}
						}
					}
				}
			}
			
			
			//List<Object[]> todayTabUsersData = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"today");
			//List<Object[]> todayTabUsersData = tabUserLocationDetailsDAO.getLocationWiseTabUserTrackingDetails(inputVO,"today");
			List<Object[]> todayTabUsersData = tdpCadreUserHourRegInfoDAO.getLocationWiseTabUserTrackingDetails(inputVO,"today");
			setLocationWiseTabUserTrackingDetails(todayTabUsersData,"today",returnVO,tabUserMap,cadreSurveyUserIdsLsit);
			
			returnVO.setTodayActiveCount(Long.valueOf(String.valueOf(cadreSurveyUserIdsLsit.size())));
			cadreSurveyUserIdsLsit.clear();
		
			List<Object[]>  assignedUsersList = cadreSurveyUserAssignDetailsDAO.getUserTrackingDetails(inputVO);
			Map<Long,Long> locationWiseAllocatedCoutnMap = new HashMap<Long, Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(assignedUsersList)){
			for (Object[] param : assignedUsersList) { 
				//if(returnVO.getLocationId().longValue() == commonMethodsUtilService.getLongValueForObject(param[0]))
				locationWiseAllocatedCoutnMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				
				Long count=commonMethodsUtilService.getLongValueForObject(param[2]);
				if(returnVO.getAllocatedCount() != null && returnVO.getAllocatedCount().longValue()>0L)
					returnVO.setAllocatedCount(returnVO.getAllocatedCount()+count);
				else
					returnVO.setAllocatedCount(count);//total Allocated count
			   }
			}
			returnVO.setTodayInActiveCount(returnVO.getAllocatedCount()-returnVO.getTodayActiveCount());
			
			//List<Object[]> lastOneHrTrackingList = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"LastOneHr");
			//List<Object[]> lastOneHrTrackingList = tabUserLocationDetailsDAO.getLocationWiseTabUserTrackingDetails(inputVO,"LastOneHr");
			
			List<Object[]> lastOneHrTrackingList = tdpCadreUserHourRegInfoDAO.getLocationWiseTabUserTrackingDetails(inputVO,"LastOneHr");
			setLocationWiseTabUserTrackingDetails(lastOneHrTrackingList,"LastOneHr",returnVO,tabUserMap,cadreSurveyUserIdsLsit);
			
			returnVO.setLastOneHrActiveCount(Long.valueOf(String.valueOf(cadreSurveyUserIdsLsit.size())));
			returnVO.setLastOneHrInActiveCount(returnVO.getTodayActiveCount()-returnVO.getLastOneHrActiveCount());
			cadreSurveyUserIdsLsit.clear();
			
			if(commonMethodsUtilService.isListOrSetValid(lastOneHrTrackingList)){
			//returnVO.setLastOneHrActiveCount(Long.valueOf(lastOneHrTrackingList.size()));// last one hour active members
			for (Object[] param : lastOneHrTrackingList) {
				GISUserTrackingVO tabVO =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
					if(tabVO != null){
						tabVO.setLastOneHrCount(tabVO.getLastOneHrCount()+commonMethodsUtilService.getLongValueForObject(param[6]));//last one hour Output Of Each tab User
					}
				}
			}
			
			//returnVO.setActiveCount(Long.valueOf(tabUserMap.size()));//total active members
			if(commonMethodsUtilService.isMapValid(tabUserMap)){
				for(GISUserTrackingVO tabUser :tabUserMap.values()){
					returnVO.setOverAllOutput(returnVO.getOverAllOutput()+tabUser.getTotalCount());//total output
					returnVO.setLastOneHrCount(returnVO.getLastOneHrCount()+tabUser.getLastOneHrCount());//last one hour Output Of All tab Users
					//tabUserList.add(tabUser);
				}
			}
			
			if(returnVO.getActiveCount() != null && returnVO.getActiveCount().longValue() > 0l)
				returnVO.setAvgOutput(returnVO.getOverAllOutput()/returnVO.getActiveCount());
			
			if(returnVO.getLastOneHrActiveCount() != null && returnVO.getLastOneHrActiveCount().longValue() > 0l)
				returnVO.setLastOneHrAvgCount(returnVO.getLastOneHrCount()/returnVO.getLastOneHrActiveCount());
			
			//returnVO.setUsersList(tabUserList);
			Map<Long,GISUserTrackingVO> locationWiseUsersMap = segriteLocationdetials(returnVO,inputVO,locationWiseAllocatedCoutnMap);
			
			returnVO.getTodayActiveUsersList().clear();
			returnVO.getTodayInActiveUsersList().clear();
			returnVO.getLastOneHrActiveusersList().clear();
			returnVO.getLastOneHrInActiveusersList().clear();
			
			if(commonMethodsUtilService.isMapValid(locationWiseUsersMap)){
				returnVO.getUsersList().addAll(locationWiseUsersMap.values());
			}
			
			
			returnVO.setInActiveCount(returnVO.getAllocatedCount()-returnVO.getActiveCount());
			
			//System.out.println(inputVO.getParentLocationType() +" compelted @ : "+new Date());			
		}catch (Exception e) {
			LOG.error("Exception Occured in getLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
		return returnVO;
	}
	
	
	public void setLocationWiseTabUserTrackingDetails(List<Object[]> tabusersData , String type,GISUserTrackingVO returnVO,Map<Long,GISUserTrackingVO> tabUserMap,Set<Long> cadreSurveyUserIdsLsit){
		try{
			Map<Long,GISUserTrackingVO> tempMap = new HashMap<Long, GISUserTrackingVO>(0);
			if(commonMethodsUtilService.isListOrSetValid(tabusersData)){
				
					//returnVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					//returnVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					if(type.equalsIgnoreCase("total")){
						for (Object[] param : tabusersData) {
							GISUserTrackingVO tabUser =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabUser == null){
								tabUser = new GISUserTrackingVO();
								tabUserMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tabUser);
							}
							tabUser.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
							tabUser.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
							tabUser.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
							tabUser.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
							tabUser.setImagePath("http://www.mytdp.in/tab_user_images/"+(commonMethodsUtilService.getStringValueForObject(param[4])));
							tabUser.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
							
							if(type.equalsIgnoreCase("total")){
								tabUser.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[6]));	
							}else if(type.equalsIgnoreCase("today")){
								tabUser.setTodayCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							}else if(type.equalsIgnoreCase("LastOneHr")){
								tabUser.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							}
							tabUser.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
							tabUser.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
							
							cadreSurveyUserIdsLsit.add(commonMethodsUtilService.getLongValueForObject(param[9]));
						}
					}
					else if(type.equalsIgnoreCase("today")){
						for (Object[] param : tabusersData) {
							GISUserTrackingVO tabUser =  tempMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabUser == null){
								tabUser = new GISUserTrackingVO();
								tempMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tabUser);
							}
							tabUser.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
							tabUser.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
							tabUser.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
							tabUser.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
							tabUser.setImagePath("http://www.mytdp.in/tab_user_images/"+(commonMethodsUtilService.getStringValueForObject(param[4])));
							tabUser.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
							tabUser.setTodayCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							tabUser.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
							tabUser.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
							
							GISUserTrackingVO tabVO =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabVO != null){
								tabUser.setLattitude(validLattitudeORLongitudeDetails(tabVO.getLattitude()));
								tabUser.setLongitude(validLattitudeORLongitudeDetails(tabVO.getLongitude()));
								tabUser.setSurveyTime(tabVO.getSurveyTime());
							 }
							
							cadreSurveyUserIdsLsit.add(commonMethodsUtilService.getLongValueForObject(param[9]));
						}
						
						if(commonMethodsUtilService.isMapValid(tempMap)){
							returnVO.getTodayActiveUsersList().addAll(tempMap.values());
							if(commonMethodsUtilService.isMapValid(tabUserMap)){
								for (Long userId : tabUserMap.keySet()) {
									if(!tempMap.keySet().contains(userId)){
										returnVO.getTodayInActiveUsersList().add(tabUserMap.get(userId));
									}
								}
							}
						}
					}
					else if(type.equalsIgnoreCase("LastOneHr")){
						for (Object[] param : tabusersData) {
							GISUserTrackingVO tabUser =  tempMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabUser == null){
								tabUser = new GISUserTrackingVO();
								tempMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tabUser);
							}
							tabUser.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
							tabUser.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
							tabUser.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
							tabUser.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
							tabUser.setImagePath("http://www.mytdp.in/tab_user_images/"+(commonMethodsUtilService.getStringValueForObject(param[4])));
							tabUser.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
							tabUser.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							tabUser.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
							tabUser.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
							
							GISUserTrackingVO tabVO =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabVO != null){
								tabUser.setLattitude(validLattitudeORLongitudeDetails(tabVO.getLattitude()));
								tabUser.setLongitude(validLattitudeORLongitudeDetails(tabVO.getLongitude()));
								tabUser.setSurveyTime(tabVO.getSurveyTime());
							 }
							
							cadreSurveyUserIdsLsit.add(commonMethodsUtilService.getLongValueForObject(param[9]));
						}
						
						if(commonMethodsUtilService.isMapValid(tempMap)){
							returnVO.getLastOneHrActiveusersList().addAll(tempMap.values());
							if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayActiveUsersList())){
								for (GISUserTrackingVO vo : returnVO.getTodayActiveUsersList()) {
									if(!tempMap.keySet().contains(vo.getId())){
										returnVO.getLastOneHrInActiveusersList().add(vo);
									}
								}
							}
						}
					}
				}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in setLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
	}
	
	public Map<Long,GISUserTrackingVO> segriteLocationdetials(GISUserTrackingVO returnVO,GISVisualizationParameterVO inputVO,Map<Long,Long> locationWiseAllocatedCoutnMap){
		Map<Long,GISUserTrackingVO> locationWiseUsersMap = new HashMap<Long, GISUserTrackingVO>(0);
		try {
			
			if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayActiveUsersList())){
						List<GISUserTrackingVO> activeUserLocationsList = returnVO.getTodayActiveUsersList();
						if(commonMethodsUtilService.isListOrSetValid(activeUserLocationsList)){
							for (GISUserTrackingVO tabUser : activeUserLocationsList) {
								GISUserTrackingVO locationVO = new GISUserTrackingVO();
								if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
									locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
								}
							
								locationVO.getTodayActiveUsersList().add(tabUser);
								locationVO.setActiveCount(Long.valueOf(String.valueOf(locationVO.getTodayActiveUsersList().size())));
								if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
									locationVO.setLocationId(tabUser.getDistrictId());
									locationVO.setLocationName(tabUser.getDistrictName());
									locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
								}
								locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
							}
						}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayInActiveUsersList())){
					List<GISUserTrackingVO> inActiveUserLocationsList = returnVO.getTodayInActiveUsersList();
					if(commonMethodsUtilService.isListOrSetValid(inActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : inActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
							}
						
							locationVO.getTodayInActiveUsersList().add(tabUser);
							locationVO.setInActiveCount(Long.valueOf(String.valueOf(locationVO.getTodayInActiveUsersList().size())));
							if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
								locationVO.setLocationId(tabUser.getDistrictId());
								locationVO.setLocationName(tabUser.getDistrictName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
							locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
						}
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrActiveusersList())){
					List<GISUserTrackingVO> lastOneHrActiveUserLocationsList = returnVO.getLastOneHrActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
							}
						
							locationVO.getLastOneHrActiveusersList().add(tabUser);
							locationVO.setLastOneHrActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrActiveusersList().size())));
							if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
								locationVO.setLocationId(tabUser.getDistrictId());
								locationVO.setLocationName(tabUser.getDistrictName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
							locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
						}
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrInActiveusersList())){
					List<GISUserTrackingVO> lastOneHrInActiveUserLocationsList = returnVO.getLastOneHrInActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrInActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrInActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
						
							locationVO.getLastOneHrInActiveusersList().add(tabUser);
							locationVO.setLastOneHrInActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrInActiveusersList().size())));
							if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
								locationVO.setLocationId(tabUser.getDistrictId());
								locationVO.setLocationName(tabUser.getDistrictName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
							locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
						}
					}
				}
			}
			else{

				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayActiveUsersList())){
						List<GISUserTrackingVO> activeUserLocationsList = returnVO.getTodayActiveUsersList();
						
						if(commonMethodsUtilService.isListOrSetValid(activeUserLocationsList)){
							for (GISUserTrackingVO tabUser : activeUserLocationsList) {
								GISUserTrackingVO locationVO = new GISUserTrackingVO();
								if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
									locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
								}
							
								locationVO.getTodayActiveUsersList().add(tabUser);
								locationVO.setActiveCount(Long.valueOf(String.valueOf(locationVO.getTodayActiveUsersList().size())));
								if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
									locationVO.setLocationId(tabUser.getLocationId());
									locationVO.setLocationName(tabUser.getLocationName());
									locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
								}
								locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
							}
						}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayInActiveUsersList())){
					List<GISUserTrackingVO> inActiveUserLocationsList = returnVO.getTodayInActiveUsersList();
					if(commonMethodsUtilService.isListOrSetValid(inActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : inActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
							}
						
							locationVO.getTodayInActiveUsersList().add(tabUser);
							locationVO.setInActiveCount(Long.valueOf(String.valueOf(locationVO.getTodayInActiveUsersList().size())));
							if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
								locationVO.setLocationId(tabUser.getLocationId());
								locationVO.setLocationName(tabUser.getLocationName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
							}
							locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
						}
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrActiveusersList())){
					List<GISUserTrackingVO> lastOneHrActiveUserLocationsList = returnVO.getLastOneHrActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
							}
						
							locationVO.getLastOneHrActiveusersList().add(tabUser);
							locationVO.setLastOneHrActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrActiveusersList().size())));
							if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
								locationVO.setLocationId(tabUser.getLocationId());
								locationVO.setLocationName(tabUser.getLocationName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
							}
							locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
						}
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrInActiveusersList())){
					List<GISUserTrackingVO> lastOneHrInActiveUserLocationsList = returnVO.getLastOneHrInActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrInActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrInActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
							}
						
							locationVO.getLastOneHrInActiveusersList().add(tabUser);
							locationVO.setLastOneHrInActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrInActiveusersList().size())));
							if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
								locationVO.setLocationId(tabUser.getLocationId());
								locationVO.setLocationName(tabUser.getLocationName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
							}
							locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
						}
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationWiseUsersMap)){
				for (Long locationId : locationWiseUsersMap.keySet()) {
					GISUserTrackingVO vo = locationWiseUsersMap.get(locationId);
					if(vo != null){
						vo.setTodayActiveCount(Long.valueOf(vo.getTodayActiveUsersList() != null?String.valueOf(vo.getTodayActiveUsersList().size()):"0"));
						vo.setTodayInActiveCount(Long.valueOf(vo.getTodayInActiveUsersList() != null?String.valueOf(vo.getTodayInActiveUsersList().size()):"0"));
						if(inputVO.getParentLocationType() != null && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
							//vo.getLastOneHrActiveusersList().clear();
							//vo.getLastOneHrInActiveusersList().clear();
							//vo.getTodayActiveUsersList().clear();
							//vo.getTodayActiveUsersList().clear();
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in setLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
		
		return locationWiseUsersMap;
	}
	
	public GISUserTrackingVO getNewLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO){
		GISUserTrackingVO returnVO = new GISUserTrackingVO();
		try{
			//System.out.println(inputVO.getParentLocationType() +" starting @ : "+new Date());
			Set<Long> cadreSurveyUserIdsLsit = new HashSet<Long>(0);
			Map<Long,GISUserTrackingVO> tabUserMap = new HashMap<Long,GISUserTrackingVO>();
			//List<Object[]> totalTabUsersData = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"total");
			//List<Object[]> totalTabUsersData = tabUserLocationDetailsDAO.getLocationWiseTabUserTrackingDetails(inputVO,"total");
			List<Object[]> totalTabUsersData = tdpCadreUserHourRegInfoDAO.getLocationWiseTabUserTrackingDetails(inputVO,"total");
			setNewLocationWiseTabUserTrackingDetails(totalTabUsersData,"total",returnVO,tabUserMap,cadreSurveyUserIdsLsit);
			
			returnVO.setActiveCount(Long.valueOf(String.valueOf(cadreSurveyUserIdsLsit.size())));
			cadreSurveyUserIdsLsit.clear();
			
			Date fromDate=null;
			Date toDate=null;
			
			if(inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = sdf.parse(inputVO.getStartDate());
				 toDate  =sdf.parse(inputVO.getEndDate());
			}
			
			if(inputVO.getParentLocationType() != null && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				List<Object[]> latestObj = tabUserLocationDetailsDAO.getLattitudLangitudeOfTabUser(inputVO,fromDate,toDate);
				List<Long> locationIdsList = new ArrayList<Long>(0);
				if(commonMethodsUtilService.isListOrSetValid(latestObj)){
					for (Object[] param : latestObj) 
						locationIdsList.add(commonMethodsUtilService.getLongValueForObject(param[1]));
				}
				if(commonMethodsUtilService.isListOrSetValid(locationIdsList)){
					List<Object[]> latestLatLongitudeDtls = tabUserLocationDetailsDAO.getLattitudeLangitudeOfTabbUserByIds(locationIdsList);
					if(commonMethodsUtilService.isListOrSetValid(latestLatLongitudeDtls)){
						for (Object[] param : latestLatLongitudeDtls) {
							try {
								GISUserTrackingVO tabVO =  tabUserMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
								if(tabVO != null){
									tabVO.setLattitude(validLattitudeORLongitudeDetails(commonMethodsUtilService.getStringValueForObject(param[3])));
									tabVO.setLongitude(validLattitudeORLongitudeDetails(commonMethodsUtilService.getStringValueForObject(param[4])));
									tabVO.setSurveyTime(commonMethodsUtilService.getStringValueForObject(param[5]));
								 }
							} catch (Exception e) {
								LOG.error("Error occured while updating langitude and lattitude for tab user for GIS Service ",e);
							}
						}
					}
				}
			}
			
			
			//List<Object[]> todayTabUsersData = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"today");
			//List<Object[]> todayTabUsersData = tabUserLocationDetailsDAO.getLocationWiseTabUserTrackingDetails(inputVO,"today");
			List<Object[]> todayTabUsersData = tdpCadreUserHourRegInfoDAO.getLocationWiseTabUserTrackingDetails(inputVO,"today");
			setNewLocationWiseTabUserTrackingDetails(todayTabUsersData,"today",returnVO,tabUserMap,cadreSurveyUserIdsLsit);
			
			returnVO.setTodayActiveCount(Long.valueOf(String.valueOf(cadreSurveyUserIdsLsit.size())));
			cadreSurveyUserIdsLsit.clear();
		
			List<Object[]>  assignedUsersList = cadreSurveyUserAssignDetailsDAO.getNewUserTrackingDetails(inputVO);
			Map<Long,Long> locationWiseAllocatedCoutnMap = new HashMap<Long, Long>(0);
			if(commonMethodsUtilService.isListOrSetValid(assignedUsersList)){
			for (Object[] param : assignedUsersList) { 
				//if(returnVO.getLocationId().longValue() == commonMethodsUtilService.getLongValueForObject(param[0]))
				locationWiseAllocatedCoutnMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), commonMethodsUtilService.getLongValueForObject(param[2]));
				
				Long count=commonMethodsUtilService.getLongValueForObject(param[2]);
				if(returnVO.getAllocatedCount() != null && returnVO.getAllocatedCount().longValue()>0L)
					returnVO.setAllocatedCount(returnVO.getAllocatedCount()+count);
				else
					returnVO.setAllocatedCount(count);//total Allocated count
			   }
			}
			returnVO.setTodayInActiveCount(returnVO.getAllocatedCount()-returnVO.getTodayActiveCount());
			
			//List<Object[]> lastOneHrTrackingList = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"LastOneHr");
			//List<Object[]> lastOneHrTrackingList = tabUserLocationDetailsDAO.getLocationWiseTabUserTrackingDetails(inputVO,"LastOneHr");
			
			List<Object[]> lastOneHrTrackingList = tdpCadreUserHourRegInfoDAO.getLocationWiseTabUserTrackingDetails(inputVO,"LastOneHr");
			setNewLocationWiseTabUserTrackingDetails(lastOneHrTrackingList,"LastOneHr",returnVO,tabUserMap,cadreSurveyUserIdsLsit);
			
			returnVO.setLastOneHrActiveCount(Long.valueOf(String.valueOf(cadreSurveyUserIdsLsit.size())));
			returnVO.setLastOneHrInActiveCount(returnVO.getTodayActiveCount()-returnVO.getLastOneHrActiveCount());
			cadreSurveyUserIdsLsit.clear();
			
			if(commonMethodsUtilService.isListOrSetValid(lastOneHrTrackingList)){
			//returnVO.setLastOneHrActiveCount(Long.valueOf(lastOneHrTrackingList.size()));// last one hour active members
			for (Object[] param : lastOneHrTrackingList) {
				GISUserTrackingVO tabVO =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
					if(tabVO != null){
						tabVO.setLastOneHrCount(tabVO.getLastOneHrCount()+commonMethodsUtilService.getLongValueForObject(param[6]));//last one hour Output Of Each tab User
					}
				}
			}
			
			//returnVO.setActiveCount(Long.valueOf(tabUserMap.size()));//total active members
			if(commonMethodsUtilService.isMapValid(tabUserMap)){
				for(GISUserTrackingVO tabUser :tabUserMap.values()){
					returnVO.setOverAllOutput(returnVO.getOverAllOutput()+tabUser.getTotalCount());//total output
					returnVO.setLastOneHrCount(returnVO.getLastOneHrCount()+tabUser.getLastOneHrCount());//last one hour Output Of All tab Users
					//tabUserList.add(tabUser);
				}
			}
			
			if(returnVO.getActiveCount() != null && returnVO.getActiveCount().longValue() > 0l)
				returnVO.setAvgOutput(returnVO.getOverAllOutput()/returnVO.getActiveCount());
			
			if(returnVO.getLastOneHrActiveCount() != null && returnVO.getLastOneHrActiveCount().longValue() > 0l)
				returnVO.setLastOneHrAvgCount(returnVO.getLastOneHrCount()/returnVO.getLastOneHrActiveCount());
			
			//returnVO.setUsersList(tabUserList);
			Map<Long,GISUserTrackingVO> locationWiseUsersMap = segriteNewLocationdetials(returnVO,inputVO,locationWiseAllocatedCoutnMap);
			
			returnVO.getTodayActiveUsersList().clear();
			returnVO.getTodayInActiveUsersList().clear();
			returnVO.getLastOneHrActiveusersList().clear();
			returnVO.getLastOneHrInActiveusersList().clear();
			
			if(commonMethodsUtilService.isMapValid(locationWiseUsersMap)){
				returnVO.getUsersList().addAll(locationWiseUsersMap.values());
			}
			
			
			returnVO.setInActiveCount(returnVO.getAllocatedCount()-returnVO.getActiveCount());
			
			//System.out.println(inputVO.getParentLocationType() +" compelted @ : "+new Date());			
		}catch (Exception e) {
			LOG.error("Exception Occured in getLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
		return returnVO;
	}
	
	
	public void setNewLocationWiseTabUserTrackingDetails(List<Object[]> tabusersData , String type,GISUserTrackingVO returnVO,Map<Long,GISUserTrackingVO> tabUserMap,Set<Long> cadreSurveyUserIdsLsit){
		try{
			Map<Long,GISUserTrackingVO> tempMap = new HashMap<Long, GISUserTrackingVO>(0);
			if(commonMethodsUtilService.isListOrSetValid(tabusersData)){
				
					//returnVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					//returnVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					if(type.equalsIgnoreCase("total")){
						for (Object[] param : tabusersData) {
							GISUserTrackingVO tabUser =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabUser == null){
								tabUser = new GISUserTrackingVO();
								tabUserMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tabUser);
							}
							tabUser.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
							tabUser.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
							tabUser.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
							tabUser.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
							tabUser.setImagePath("http://www.mytdp.in/tab_user_images/"+(commonMethodsUtilService.getStringValueForObject(param[4])));
							tabUser.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
							
							if(type.equalsIgnoreCase("total")){
								tabUser.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[6]));	
							}else if(type.equalsIgnoreCase("today")){
								tabUser.setTodayCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							}else if(type.equalsIgnoreCase("LastOneHr")){
								tabUser.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							}
							tabUser.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
							tabUser.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
							
							cadreSurveyUserIdsLsit.add(commonMethodsUtilService.getLongValueForObject(param[9]));
						}
					}
					else if(type.equalsIgnoreCase("today")){
						for (Object[] param : tabusersData) {
							GISUserTrackingVO tabUser =  tempMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabUser == null){
								tabUser = new GISUserTrackingVO();
								tempMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tabUser);
							}
							tabUser.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
							tabUser.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
							tabUser.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
							tabUser.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
							tabUser.setImagePath("http://www.mytdp.in/tab_user_images/"+(commonMethodsUtilService.getStringValueForObject(param[4])));
							tabUser.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
							tabUser.setTodayCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							tabUser.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
							tabUser.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
							
							GISUserTrackingVO tabVO =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabVO != null){
								tabUser.setLattitude(validLattitudeORLongitudeDetails(tabVO.getLattitude()));
								tabUser.setLongitude(validLattitudeORLongitudeDetails(tabVO.getLongitude()));
								tabUser.setSurveyTime(tabVO.getSurveyTime());
							 }
							
							cadreSurveyUserIdsLsit.add(commonMethodsUtilService.getLongValueForObject(param[9]));
						}
						
						if(commonMethodsUtilService.isMapValid(tempMap)){
							returnVO.getTodayActiveUsersList().addAll(tempMap.values());
							if(commonMethodsUtilService.isMapValid(tabUserMap)){
								for (Long userId : tabUserMap.keySet()) {
									if(!tempMap.keySet().contains(userId)){
										returnVO.getTodayInActiveUsersList().add(tabUserMap.get(userId));
									}
								}
							}
						}
					}
					else if(type.equalsIgnoreCase("LastOneHr")){
						for (Object[] param : tabusersData) {
							GISUserTrackingVO tabUser =  tempMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabUser == null){
								tabUser = new GISUserTrackingVO();
								tempMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tabUser);
							}
							tabUser.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
							tabUser.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
							tabUser.setId(commonMethodsUtilService.getLongValueForObject(param[9]));
							tabUser.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
							tabUser.setImagePath("http://www.mytdp.in/tab_user_images/"+(commonMethodsUtilService.getStringValueForObject(param[4])));
							tabUser.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
							tabUser.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[6]));
							
							tabUser.setDistrictId(commonMethodsUtilService.getLongValueForObject(param[7]));
							tabUser.setDistrictName(commonMethodsUtilService.getStringValueForObject(param[8]));
							
							GISUserTrackingVO tabVO =  tabUserMap.get(param[2] !=null ? (Long)param[2]:0l);
							if(tabVO != null){
								tabUser.setLattitude(validLattitudeORLongitudeDetails(tabVO.getLattitude()));
								tabUser.setLongitude(validLattitudeORLongitudeDetails(tabVO.getLongitude()));
								tabUser.setSurveyTime(tabVO.getSurveyTime());
							 }
							
							cadreSurveyUserIdsLsit.add(commonMethodsUtilService.getLongValueForObject(param[9]));
						}
						
						if(commonMethodsUtilService.isMapValid(tempMap)){
							returnVO.getLastOneHrActiveusersList().addAll(tempMap.values());
							if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayActiveUsersList())){
								for (GISUserTrackingVO vo : returnVO.getTodayActiveUsersList()) {
									if(!tempMap.keySet().contains(vo.getId())){
										returnVO.getLastOneHrInActiveusersList().add(vo);
									}
								}
							}
						}
					}
				}
			
		}catch (Exception e) {
			LOG.error("Exception Occured in setLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
	}
	
	public Map<Long,GISUserTrackingVO> segriteNewLocationdetials(GISUserTrackingVO returnVO,GISVisualizationParameterVO inputVO,Map<Long,Long> locationWiseAllocatedCoutnMap){
		Map<Long,GISUserTrackingVO> locationWiseUsersMap = new HashMap<Long, GISUserTrackingVO>(0);
		try {
			
			if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.STATE)){
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayActiveUsersList())){
						List<GISUserTrackingVO> activeUserLocationsList = returnVO.getTodayActiveUsersList();
						if(commonMethodsUtilService.isListOrSetValid(activeUserLocationsList)){
							for (GISUserTrackingVO tabUser : activeUserLocationsList) {
								GISUserTrackingVO locationVO = new GISUserTrackingVO();
								if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
									locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
								}
							
								locationVO.getTodayActiveUsersList().add(tabUser);
								locationVO.setActiveCount(locationVO.getActiveCount()+Long.valueOf(String.valueOf(locationVO.getTodayActiveUsersList().size())));
								if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
									locationVO.setDistrictId(tabUser.getDistrictId());
									locationVO.setDistrictName(tabUser.getDistrictName());
									locationVO.setLocationId(tabUser.getDistrictId());
									locationVO.setLocationName(tabUser.getDistrictName());
									locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
								}
								locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
							}
						}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayInActiveUsersList())){
					List<GISUserTrackingVO> inActiveUserLocationsList = returnVO.getTodayInActiveUsersList();
					if(commonMethodsUtilService.isListOrSetValid(inActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : inActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
							}
						
							locationVO.getTodayInActiveUsersList().add(tabUser);
							locationVO.setInActiveCount(locationVO.getInActiveCount()+Long.valueOf(String.valueOf(locationVO.getTodayInActiveUsersList().size())));
							if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
								locationVO.setDistrictId(tabUser.getDistrictId());
								locationVO.setDistrictName(tabUser.getDistrictName());
								locationVO.setLocationId(tabUser.getDistrictId());
								locationVO.setLocationName(tabUser.getDistrictName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
							locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
						}
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrActiveusersList())){
					List<GISUserTrackingVO> lastOneHrActiveUserLocationsList = returnVO.getLastOneHrActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
							}
						
							locationVO.getLastOneHrActiveusersList().add(tabUser);
							locationVO.setLastOneHrActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrActiveusersList().size())));
							if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
								locationVO.setDistrictId(tabUser.getDistrictId());
								locationVO.setDistrictName(tabUser.getDistrictName());
								locationVO.setLocationId(tabUser.getDistrictId());
								locationVO.setLocationName(tabUser.getDistrictName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
							locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
						}
					}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrInActiveusersList())){
					List<GISUserTrackingVO> lastOneHrInActiveUserLocationsList = returnVO.getLastOneHrInActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrInActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrInActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getDistrictId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getDistrictId());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
						
							locationVO.getLastOneHrInActiveusersList().add(tabUser);
							locationVO.setLastOneHrInActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrInActiveusersList().size())));
							if(locationVO.getDistrictId() == null || locationVO.getDistrictId().longValue() == 0L){
								locationVO.setDistrictId(tabUser.getDistrictId());
								locationVO.setDistrictName(tabUser.getDistrictName());
								locationVO.setLocationId(tabUser.getDistrictId());
								locationVO.setLocationName(tabUser.getDistrictName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getDistrictId()));
							}
							locationWiseUsersMap.put(tabUser.getDistrictId(), locationVO);
						}
					}
				}
			}
			else{

				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayActiveUsersList())){
						List<GISUserTrackingVO> activeUserLocationsList = returnVO.getTodayActiveUsersList();
						
						if(commonMethodsUtilService.isListOrSetValid(activeUserLocationsList)){
							for (GISUserTrackingVO tabUser : activeUserLocationsList) {
								GISUserTrackingVO locationVO = new GISUserTrackingVO();
								if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
									locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
								}
							
								locationVO.getTodayActiveUsersList().add(tabUser);
								locationVO.setActiveCount(locationVO.getActiveCount()+Long.valueOf(String.valueOf(locationVO.getTodayActiveUsersList().size())));
								if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
									locationVO.setDistrictId(tabUser.getDistrictId());
									locationVO.setDistrictName(tabUser.getDistrictName());
									locationVO.setLocationId(tabUser.getLocationId());
									locationVO.setLocationName(tabUser.getLocationName());
									locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
								}
								
								if(locationVO.getActiveLongitudeStr() == null){
									locationVO.setActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
									locationVO.setActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
								}else if(locationVO.getLast1hrInactiveLongitudeStr() == null){
									locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
									locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
								}else if(locationVO.getLast1hrActiveLongitudeStr() == null){
									locationVO.setLast1hrActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
									locationVO.setLast1hrActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
								}else if(locationVO.getInActiveLongitudeStr() != null && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty()){
									locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
									locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
								}else if(locationVO.getActiveLongitudeStr() != null  && !locationVO.getActiveLongitudeStr().toString().trim().isEmpty()){
									locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
									locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
								}else if(locationVO.getInActiveLongitudeStr() != null  && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty() ){
									locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
									locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
								}							
								
								
								locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
							}
						}
				}
				
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getTodayInActiveUsersList())){
					List<GISUserTrackingVO> inActiveUserLocationsList = returnVO.getTodayInActiveUsersList();
					if(commonMethodsUtilService.isListOrSetValid(inActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : inActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
							}
						
							locationVO.getTodayInActiveUsersList().add(tabUser);
							locationVO.setInActiveCount(locationVO.getInActiveCount()+Long.valueOf(String.valueOf(locationVO.getTodayInActiveUsersList().size())));
							if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
								locationVO.setDistrictId(tabUser.getDistrictId());
								locationVO.setDistrictName(tabUser.getDistrictName());
								locationVO.setLocationId(tabUser.getLocationId());
								locationVO.setLocationName(tabUser.getLocationName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
							}
							
							if(locationVO.getActiveLongitudeStr() == null){
								locationVO.setActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getLast1hrInactiveLongitudeStr() == null){
								locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getLast1hrActiveLongitudeStr() == null){
								locationVO.setLast1hrActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getInActiveLongitudeStr() != null && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty()){
								locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getActiveLongitudeStr() != null  && !locationVO.getActiveLongitudeStr().toString().trim().isEmpty()){
								locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getInActiveLongitudeStr() != null  && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty() ){
								locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}	
							
							locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
						}
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrActiveusersList())){
					List<GISUserTrackingVO> lastOneHrActiveUserLocationsList = returnVO.getLastOneHrActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
							}
						
							locationVO.getLastOneHrActiveusersList().add(tabUser);
							locationVO.setLastOneHrActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrActiveusersList().size())));
							if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
								locationVO.setDistrictId(tabUser.getDistrictId());
								locationVO.setDistrictName(tabUser.getDistrictName());
								locationVO.setLocationId(tabUser.getLocationId());
								locationVO.setLocationName(tabUser.getLocationName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
							}
							
							
							if(locationVO.getActiveLongitudeStr() == null){
								locationVO.setActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getLast1hrInactiveLongitudeStr() == null){
								locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getLast1hrActiveLongitudeStr() == null){
								locationVO.setLast1hrActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getInActiveLongitudeStr() != null && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty()){
								locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getActiveLongitudeStr() != null  && !locationVO.getActiveLongitudeStr().toString().trim().isEmpty()){
								locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getInActiveLongitudeStr() != null  && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty() ){
								locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}	
							
							locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
						}
					}
				}
				if(commonMethodsUtilService.isListOrSetValid(returnVO.getLastOneHrInActiveusersList())){
					List<GISUserTrackingVO> lastOneHrInActiveUserLocationsList = returnVO.getLastOneHrInActiveusersList();
					if(commonMethodsUtilService.isListOrSetValid(lastOneHrInActiveUserLocationsList)){
						for (GISUserTrackingVO tabUser : lastOneHrInActiveUserLocationsList) {
							GISUserTrackingVO locationVO = new GISUserTrackingVO();
							if(locationWiseUsersMap.get(tabUser.getLocationId()) != null){
								locationVO = locationWiseUsersMap.get(tabUser.getLocationId());
							}
						
							locationVO.getLastOneHrInActiveusersList().add(tabUser);
							locationVO.setLastOneHrInActiveCount(Long.valueOf(String.valueOf(locationVO.getLastOneHrInActiveusersList().size())));
							if(locationVO.getLocationId() == null || locationVO.getLocationId().longValue() == 0L){
								locationVO.setDistrictId(tabUser.getDistrictId());
								locationVO.setDistrictName(tabUser.getDistrictName());
								locationVO.setLocationId(tabUser.getLocationId());
								locationVO.setLocationName(tabUser.getLocationName());
								locationVO.setAllocatedCount(locationWiseAllocatedCoutnMap.get(tabUser.getLocationId()));
							}
							
							
							if(locationVO.getActiveLongitudeStr() == null){
								locationVO.setActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getLast1hrInactiveLongitudeStr() == null){
								locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getLast1hrActiveLongitudeStr() == null){
								locationVO.setLast1hrActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getInActiveLongitudeStr() != null && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty()){
								locationVO.setLast1hrInactiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setLast1hrInActiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getActiveLongitudeStr() != null  && !locationVO.getActiveLongitudeStr().toString().trim().isEmpty()){
								locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}else if(locationVO.getInActiveLongitudeStr() != null  && !locationVO.getInActiveLongitudeStr().toString().trim().isEmpty() ){
								locationVO.setInActiveLongitudeStr(validLattitudeORLongitudeDetails(tabUser.getLongitude()));
								locationVO.setInactiveLattitudeStr(validLattitudeORLongitudeDetails(tabUser.getLattitude()));
							}	
							
							locationWiseUsersMap.put(tabUser.getLocationId(), locationVO);
						}
					}
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationWiseUsersMap)){
				for (Long locationId : locationWiseUsersMap.keySet()) {
					GISUserTrackingVO vo = locationWiseUsersMap.get(locationId);
					if(vo != null){
						vo.setTodayActiveCount(Long.valueOf(vo.getTodayActiveUsersList() != null?String.valueOf(vo.getTodayActiveUsersList().size()):"0"));
						vo.setTodayInActiveCount(vo.getAllocatedCount() - vo.getTodayActiveCount());
						vo.setLastOneHrInActiveCount(vo.getTodayActiveCount() - vo.getLastOneHrActiveCount());
						
						if(inputVO.getParentLocationType() != null && !inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
							vo.getLastOneHrActiveusersList().clear();
							vo.getLastOneHrInActiveusersList().clear();
							vo.getTodayActiveUsersList().clear();
							vo.getTodayInActiveUsersList().clear();
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in setLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
		
		return locationWiseUsersMap;
	}
	
	private String validLattitudeORLongitudeDetails(String valueStr){
		try {
			return valueStr != null && valueStr.trim().length()>9?valueStr.substring(0, 9):valueStr;
		} catch (Exception e) {
			LOG.error("Exception Occured in validLattitudeORLongitudeDetails Method in GISVisualizationService Class",e);
		}
		return valueStr;
	}
}
