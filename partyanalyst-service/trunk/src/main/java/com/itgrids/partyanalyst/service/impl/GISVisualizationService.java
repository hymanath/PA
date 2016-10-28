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
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
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
	private ICadreSurveyUserAssignDetailsDAO cadreSurveyUserAssignDetailsDAO;
	private ITdpCadreLocationInfoDAO tdpCadreLocationInfoDAO;
	private ITdpCadreDateWiseInfoDAO tdpCadreDateWiseInfoDAO;
	private ITdpCadreTargetCountDAO tdpCadreTargetCountDAO;
	private ITabUserLocationDetailsDAO tabUserLocationDetailsDAO;
	
	
	
	
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
		   
		   if(inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE) && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.RURALURBAN)){
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
		   
		   //today
		  // inputVO.setStartDate(new SimpleDateFormat("dd-MM-yyyy").format(new DateUtilService().getCurrentDateAndTime()));
		  // inputVO.setEndDate(new SimpleDateFormat("dd-MM-yyyy").format(new DateUtilService().getCurrentDateAndTime()));
		   
		   inputVO.setStartDate(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("dd-MM-yyyy").parse("26-10-2016")));
		   inputVO.setEndDate(new SimpleDateFormat("dd-MM-yyyy").format(new SimpleDateFormat("dd-MM-yyyy").parse("26-10-2016")));
		   
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
					setDataToMap( resultList,locationsMap, YCPMLAMap);
				}else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.DISTRICT)){
					resultList = boothConstituencyElectionDAO.getAssemblyLevelElectionResultsForGISVisualization(inputVO);
					YCPMLAMap = getYcpCandidatesJoinedIntoTDPParty();
					setDataToMap( resultList,locationsMap, YCPMLAMap);
				}else if(inputVO.getParentLocationType() != null && inputVO.getParentLocationType().equalsIgnoreCase(IConstants.ASSEMBLY_CONSTITUENCY_TYPE)){
					if(inputVO.getChildLocationType() != null && inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURAL)){
						resultList = boothConstituencyElectionDAO.getMandalLevelElectionResultsForGISVisualization(inputVO);
						setDataToMap( resultList,locationsMap, YCPMLAMap);
					}else if(inputVO.getChildLocationType() != null && inputVO.getChildLocationType().equalsIgnoreCase(IConstants.RURALURBAN)){
						inputVO.setParentLocationType(IConstants.RURAL);
						resultList = boothConstituencyElectionDAO.getMandalLevelElectionResultsForGISVisualization(inputVO);
						setDataToMap( resultList,locationsMap, YCPMLAMap);
						
						inputVO.setParentLocationType(IConstants.MUNCIPALITY_CORPORATION_LEVEL);
						resultList = boothConstituencyElectionDAO.getMunciORUrbanLevelElectionResultsForGISVisualization(inputVO);
						setDataToMap( resultList,locationsMap, YCPMLAMap);
						inputVO.setParentLocationType(IConstants.ASSEMBLY_CONSTITUENCY_TYPE);
					}
					else if(inputVO.getChildLocationType() != null && inputVO.getChildLocationType().equalsIgnoreCase(IConstants.URBAN)){
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
					GISVisualizationDetailsVO vo = locationsMap.get(locationId);
					if(vo != null){
						String perc = (new BigDecimal(votersEarned*(100.0)/validVotes)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
						vo.setIsYCPArea("false");
						vo.setValidVotes(validVotes.toString());
						vo.setEarnedVotesIn2014(votersEarned.toString());
						vo.setEarnedVotesPercIn2014(perc.toString());
						if(YCPMLAMap.get(locationId) != null){
							vo.setIsYCPArea("true");
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
			YCPCandidatesMap.put(271L,"Chand Basha");						//kadiri
			YCPCandidatesMap.put(149L,"Jyothula Nehru");					//jaggampeta
			YCPCandidatesMap.put(212L,"Varapula Subbarao");					//prathipadu
			YCPCandidatesMap.put(244L,"Adi narayana Reddy");				//jammalamadugu
			YCPCandidatesMap.put(242L,"Jayaramulu");						//badvel
			YCPCandidatesMap.put(196L,"Jaleel Khan");						//vijayawada west
			YCPCandidatesMap.put(258L,"M.Mani Gandhi");						//kodumuru
			YCPCandidatesMap.put(262L,"Bhuma Nagi Reddy");					//nandyal
			YCPCandidatesMap.put(254L,"Bhuma Akhila Priya");				//allagadda
			YCPCandidatesMap.put(332L,"Budda Rajashekar Reddy");			//Srisailam
			YCPCandidatesMap.put(260L,"SV Mohan Reddy");					//Kurnool
			YCPCandidatesMap.put(231L,"Pasim Sunil Kumar");					//gudur
			YCPCandidatesMap.put(344L,"David Raju");						//yerragondapalem
			YCPCandidatesMap.put(218L,"Gottipati Ravi Kumar");				//Addanki
			YCPCandidatesMap.put(222L,"Muthamula Ashok Reddy");				//Giddalur
			YCPCandidatesMap.put(223L,"Pothula Rama Rao");					//Kandukur
			YCPCandidatesMap.put(114L,"Kalamatta Venkata Ramana"); 			//Pathapatnam
			YCPCandidatesMap.put(122L,"Ravu Venkata Sujaya Krishna Ranga Rao");//bobbili
			YCPCandidatesMap.put(359L,"Kidari Sarveswar Rao");				//Araku
			YCPCandidatesMap.put(284L,"N.Amarnath Reddy"); 					//Palamaner

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
							if(commonMethodsUtilService.isListOrSetValid(trackingVO.getLastOneHrusersList())){
								try{
									Float avg = (float)(trackingVO.getLastOneHrCount()/trackingVO.getLastOneHrusersList().size());
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
					statusVO.setPerc(commonMethodsUtilService.getStringValueForObject(tempstatusArr[1]));
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
		LOG.error("Exception Occured in getMembershipDriveVisualizationDetails Method in GISVisualizationService Class",e);
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
			List<GISUserTrackingVO> tabUserList = new ArrayList<GISUserTrackingVO>();
			Map<Long,GISUserTrackingVO> tabUserMap = new HashMap<Long,GISUserTrackingVO>();
			List<Object[]> totalTabUsersData = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"total");
			setLocationWiseTabUserTrackingDetails(totalTabUsersData,"total",returnVO,tabUserMap);
			
			
			List<Object[]> todayTabUsersData = tdpCadreDAO.getLocationWiseTabUserTrackingDetails(inputVO,"today");
			setLocationWiseTabUserTrackingDetails(todayTabUsersData,"today",returnVO,tabUserMap);
			
			Date fromDate=null;
			Date toDate=null;
			
			if(inputVO.getStartDate() !=null && inputVO.getEndDate() !=null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = sdf.parse(inputVO.getStartDate());
				 toDate  =sdf.parse(inputVO.getEndDate());
			}
			
			List<Object[]> latestObj = tabUserLocationDetailsDAO.getLattitudeLangitudeOfTabUser(inputVO.getParentLocationTypeId(),fromDate,toDate,inputVO.getParentLocationType());
			if(commonMethodsUtilService.isListOrSetValid(latestObj)){
				for (Object[] param : latestObj) {
					GISUserTrackingVO tabVO =  tabUserMap.get(param[0] !=null ? (Long)param[0]:0l);
					if(tabVO != null){
						tabVO.setLattitude(param[3] !=null ? param[3].toString():"");
						tabVO.setLongitude(param[4] !=null ? param[4].toString():"");
						tabVO.setSurveyTime(param[5] !=null ? param[5].toString():"");
					 }
				   }
				}
			
			if(commonMethodsUtilService.isMapValid(tabUserMap)){
				for(GISUserTrackingVO tabUser :tabUserMap.values()){
					tabUserList.add(tabUser);
				}
			}
			
			returnVO.setUsersList(tabUserList);
			
		}catch (Exception e) {
			LOG.error("Exception Occured in getLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
		return returnVO;
	}
	public void setLocationWiseTabUserTrackingDetails(List<Object[]> tabusersData , String type,GISUserTrackingVO returnVO,Map<Long,GISUserTrackingVO> tabUserMap){
		
		try{
			if(commonMethodsUtilService.isListOrSetValid(tabusersData)){
				for (Object[] param : tabusersData) {
					returnVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[0]));
					returnVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					GISUserTrackingVO tabUser = new GISUserTrackingVO();
					tabUser.setId(commonMethodsUtilService.getLongValueForObject(param[2]));
					tabUser.setName(commonMethodsUtilService.getStringValueForObject(param[3]));
					tabUser.setImagePath(commonMethodsUtilService.getStringValueForObject(param[4]));
					tabUser.setMobileNo(commonMethodsUtilService.getStringValueForObject(param[5]));
					
					if(type.equalsIgnoreCase("total")){
						tabUser.setTotalCount(commonMethodsUtilService.getLongValueForObject(param[6]));	
					}else if(type.equalsIgnoreCase("today")){
						tabUser.setTodayCount(commonMethodsUtilService.getLongValueForObject(param[6]));
					}
					tabUserMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), tabUser);
				}
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception Occured in setLocationWiseTabUserTrackingDetails Method in GISVisualizationService Class",e);
		}
	}
	
}
