package com.itgrids.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.IDocumentDAO;
import com.itgrids.dao.IGovtMainWorkDAO;
import com.itgrids.dao.IGovtWorkDAO;
import com.itgrids.dao.IGovtWorkDocumentDAO;
import com.itgrids.dao.IGovtWorkProgressDAO;
import com.itgrids.dao.IGovtWorkProgressDocumentDAO;
import com.itgrids.dao.IGovtWorkProgressTrackDAO;
import com.itgrids.dao.IGovtWorkProgressUpdateDAO;
import com.itgrids.dao.IGovtWorkStatusDAO;
import com.itgrids.dao.IGovtWorkTypeDAO;
import com.itgrids.dao.ILocationAddressDAO;
import com.itgrids.dao.IMobileAppUserDAO;
import com.itgrids.dao.IMobileAppUserLocationDAO;
import com.itgrids.dao.IMobileAppUserLoginDAO;
import com.itgrids.dao.IMobileAppUserWorkTypeDAO;
import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.ITehsilConstituencyDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dao.impl.GovtMainWorkDAO;
import com.itgrids.dto.DocumentVO;
import com.itgrids.dto.GovtMainWorkVO;
import com.itgrids.dto.GovtWorksVO;
import com.itgrids.dto.LocationAddressVO;
import com.itgrids.dto.MobileAppInputVO;
import com.itgrids.dto.MobileAppLoginVO;
import com.itgrids.dto.MobileAppUserLocationVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.SmallVO;
import com.itgrids.dto.WorkStatusVO;
import com.itgrids.model.GovtWork;
import com.itgrids.model.GovtWorkDocument;
import com.itgrids.model.GovtWorkProgress;
import com.itgrids.model.GovtWorkProgressDocument;
import com.itgrids.model.GovtWorkProgressTrack;
import com.itgrids.model.GovtWorkProgressUpdate;
import com.itgrids.model.GovtWorkType;
import com.itgrids.model.MobileAppUserLogin;
import com.itgrids.service.IUnderGroundDrainageService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.DateUtilService;
import com.itgrids.utils.IConstants;

@Service
@Transactional
public class UnderGroundDrainageService implements IUnderGroundDrainageService{
	private static final Logger LOG = Logger.getLogger(UnderGroundDrainageService.class);
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private DateUtilService dateUtilService;
	@Autowired
	private IMobileAppUserDAO mobileAppUserDAO;
	@Autowired
	private IGovtWorkTypeDAO govtWorkTypeDAO;
	@Autowired
	private IMobileAppUserLoginDAO mobileAppUserLoginDAO;
	@Autowired
	private IMobileAppUserLocationDAO mobileAppUserLocationDAO;
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private ITehsilDAO tehsilDAO;
	@Autowired
	private IPanchayatDAO panchayatDAO;
	@Autowired
	private IGovtWorkDAO govtWorkDAO;
	@Autowired
	private IGovtWorkProgressDAO govtWorkProgressDAO;
	@Autowired
	private IGovtWorkStatusDAO govtWorkStatusDAO;
	@Autowired
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	@Autowired
	private ITehsilConstituencyDAO tehsilConstituencyDAO;
	@Autowired
	private ILocationAddressDAO locationAddressDAO;
	@Autowired
	private IGovtWorkProgressUpdateDAO govtWorkProgressUpdateDAO;
	@Autowired
	private IGovtWorkProgressTrackDAO govtWorkProgressTrackDAO;
	@Autowired
	private IGovtWorkProgressDocumentDAO govtWorkProgressDocumentDAO;
	@Autowired
	private IDocumentDAO documentDAO;
	@Autowired
	private IGovtWorkDocumentDAO govtWorkDocumentDAO;
	@Autowired
	private IMobileAppUserWorkTypeDAO mobileAppUserWorkTypeDAO;
	@Autowired
	private IGovtMainWorkDAO govtMainWorkDAO;
	
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}
	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}
	
	public MobileAppLoginVO checkLogin(MobileAppInputVO inputVO){
		MobileAppLoginVO finalVO = new MobileAppLoginVO();
		try {
			//0-mobileAppUserId,1-fullName,2-username,3-password,4-mobileNo,5-mobileAppUserTypeId,6-userType
			List<Object[]> objList = mobileAppUserDAO.checkLogin(inputVO.getUserName(),inputVO.getPassword());
			MobileAppUserLogin mobileAppUserLogin = new MobileAppUserLogin();
			if(objList != null && objList.size() > 0){
				Object[] obj = objList.get(0);
				
				finalVO.setUserId((Long)obj[0]);
				finalVO.setFullName(obj[1] != null ? obj[1].toString():"");
				finalVO.setUserName(inputVO.getUserName());
				finalVO.setPassword(inputVO.getPassword());
				finalVO.setMobileNum(obj[4] != null ? obj[4].toString():"");
				finalVO.setUserTypeId(obj[5] != null ? (Long)obj[5]:0l);
				finalVO.setUserType(obj[6] != null ? obj[6].toString():"");
				
				mobileAppUserLogin.setMobileAppUserId((Long)obj[0]);
				mobileAppUserLogin.setLoginStatus("Success");
				
				//get all work types
				List<Object[]> typesObjList = mobileAppUserWorkTypeDAO.getUsersGovtWorkTypes((Long)obj[0]);
				Long workTypeId=0l;
				if(typesObjList != null && typesObjList.size() > 0){
					workTypeId = (Long)typesObjList.get(0)[0];
					for (Object[] govtWorkType : typesObjList) {
						SmallVO vo = new SmallVO();
						vo.setKey((Long)govtWorkType[0]);
						vo.setValue(govtWorkType[1].toString());
						finalVO.getVoList().add(vo);
					}
					
					List<GovtMainWorkVO> voList = getUsersGovtMainWorks((Long)obj[0],workTypeId);
					if(voList != null && voList.size() > 0){
						
						//get completed status percentages
						//0-mainWorkId,1-totalKms,2-completedKms
						/*List<Object[]> objList12 = govtWorkProgressDAO.getCompletedMianWorkPercentage((Long)obj[0],workTypeId);
						if(objList12 != null && objList12.size() > 0){
							for (Object[] objects : objList12) {
								GovtMainWorkVO mainWorkVO = getMatchedMainWorkVO(Long.parseLong(objects[0].toString()),voList);
								if(mainWorkVO != null && mainWorkVO.getTotalKms() != null && mainWorkVO.getTotalKms() > 0l){
									mainWorkVO.setCompletedKms(Double.parseDouble(objects[2].toString()));
									mainWorkVO.setCompletedPercentage((Double.parseDouble(objects[2].toString())*100.00)/mainWorkVO.getTotalKms());
								}
							}
						}*/
						
						finalVO.setMainWorksVOList(voList);
					}
				}
				
				
				//get location details
				//getTheLocationsOfMObileAppUser(finalVO,(Long)obj[0]);
				
				//get user created work details
				/*List<GovtWorksVO> workVOList = getWorkDetailsOfMobileAppUser((Long)obj[0],workTypeId);
				if(workVOList != null && workVOList.size() > 0){
					finalVO.setWorksVOList(workVOList);
				}*/
				
				finalVO.setStatus("success");
			}else{
				finalVO.setStatus("failure");
				mobileAppUserLogin.setLoginStatus("Fail");
			}
			mobileAppUserLogin.setUsername(inputVO.getUserName());
			mobileAppUserLogin.setPassword(inputVO.getPassword());
			mobileAppUserLogin.setLoginTime(dateUtilService.getCurrentDateAndTime());
			mobileAppUserLogin.setImeiNo(inputVO.getImei());
			mobileAppUserLogin.setAppVersion(inputVO.getAppVersion() != null ? inputVO.getAppVersion():null);
			mobileAppUserLogin.setLattitude(inputVO.getLattitude() != null ? inputVO.getLattitude():null);
			mobileAppUserLogin.setLongitude(inputVO.getLongitude() != null ? inputVO.getLongitude():null);
			mobileAppUserLogin.setLatLongAccuracy(inputVO.getLatLongAccuracy() != null ? inputVO.getLatLongAccuracy():null);
			mobileAppUserLoginDAO.save(mobileAppUserLogin);
		} catch (Exception e) {
			LOG.error("Exception raised while checking the mobile app login", e);
			finalVO.setStatus("failure");
		}
		return finalVO;
	}
	
	public GovtMainWorkVO getMatchedMainWorkVO(Long mainWorkId,List<GovtMainWorkVO> voList){
		if(voList != null && voList.size() > 0){
			for (GovtMainWorkVO govtMainWorkVO : voList) {
				if(govtMainWorkVO.getGovtMainWorkId().equals(mainWorkId))
					return govtMainWorkVO;
			}
		}
		return null; 
	}
	
	public List<GovtMainWorkVO> getUsersGovtMainWorks(Long userId,Long workTypeId){
		List<GovtMainWorkVO> mainWorksVOList = new ArrayList<GovtMainWorkVO>(0);
		
		//0-workId,1-name,2-kms
		List<Object[]> objList1 = govtMainWorkDAO.getAllMainWorksForUser(userId,workTypeId);
		if(objList1 != null && objList1.size() > 0){
			for (Object[] objects : objList1) {
				GovtMainWorkVO vo = new GovtMainWorkVO();
				vo.setGovtMainWorkId(objects[0] != null ? Long.parseLong(objects[0].toString()):null);
				vo.setGovtMainWork(objects[1] != null ? objects[1].toString():null);
				vo.setTotalKms(objects[2] != null ? Double.parseDouble(objects[2].toString()):null);
				mainWorksVOList.add(vo);
			}
		}
		
		//0-mainWorkId,1-mainWorkName,2-totalKms,3-locationScopeId,4-locationValue,5-locationAddressId,6-count,7-workLength
		List<Object[]> objList = govtWorkDAO.getUsersGovtMainWorks(userId,workTypeId);
		if(objList != null && objList.size() > 0){
			for (Object[] objects : objList) {
				
				GovtMainWorkVO vo = getmatchedMainWorkVO(mainWorksVOList,Long.parseLong(objects[0].toString()));
				if(vo != null){
					vo.setLocationScopeId(objects[3] != null ? Long.parseLong(objects[3].toString()):null);
					vo.setLocationValue(objects[4] != null ? Long.parseLong(objects[4].toString()):null);
					vo.setLocationAddressId(objects[5] != null ? Long.parseLong(objects[5].toString()):null);
					vo.setWorksCount(objects[6] != null ? Long.parseLong(objects[6].toString()):null);
					vo.setProgressKms(objects[7] != null ? Double.parseDouble(objects[7].toString()):null);
				}else{
					vo = new GovtMainWorkVO();
					vo.setGovtMainWorkId(objects[0] != null ? Long.parseLong(objects[0].toString()):null);
					vo.setGovtMainWork(objects[1] != null ? objects[1].toString():null);
					vo.setTotalKms(objects[2] != null ? Double.parseDouble(objects[2].toString()):null);
					vo.setLocationScopeId(objects[3] != null ? Long.parseLong(objects[3].toString()):null);
					vo.setLocationValue(objects[4] != null ? Long.parseLong(objects[4].toString()):null);
					vo.setLocationAddressId(objects[5] != null ? Long.parseLong(objects[5].toString()):null);
					vo.setWorksCount(objects[6] != null ? Long.parseLong(objects[6].toString()):null);
					vo.setProgressKms(objects[7] != null ? Double.parseDouble(objects[7].toString()):null);
					mainWorksVOList.add(vo);
				}
				
				
			}
			
			if(mainWorksVOList != null && mainWorksVOList.size() > 0){
				//get completed status percentages
				//0-mainWorkId,1-totalKms,2-completedKms
				List<Object[]> objList12 = govtWorkProgressDAO.getCompletedMianWorkPercentage(userId,workTypeId);
				if(objList12 != null && objList12.size() > 0){
					for (Object[] objects : objList12) {
						GovtMainWorkVO mainWorkVO = getMatchedMainWorkVO(Long.parseLong(objects[0].toString()),mainWorksVOList);
						if(mainWorkVO != null && mainWorkVO.getTotalKms() != null && mainWorkVO.getTotalKms() > 0l){
							mainWorkVO.setCompletedKms(Double.parseDouble(objects[2].toString()));
							mainWorkVO.setCompletedPercentage((Double.parseDouble(objects[2].toString())*100.00)/mainWorkVO.getTotalKms());
						}
					}
				}
			}
		}
		return mainWorksVOList;
	}
	
	public GovtMainWorkVO getmatchedMainWorkVO(List<GovtMainWorkVO> voList,Long id){
		if(voList != null && voList.size() > 0){
			for (GovtMainWorkVO govtMainWorkVO : voList) {
				if(govtMainWorkVO.getGovtMainWorkId().equals(id))
					return govtMainWorkVO;
			}
		}
		return null;
	}
	
	public void getTheLocationsOfMObileAppUser(MobileAppLoginVO finalVO,Long mobileAppUserId){
		//get location details
		//0-regionScopesId,1-regionScopes,2-locationValue
		List<Object[]> locationsObjList = mobileAppUserLocationDAO.getMobileAppuserLocations(mobileAppUserId);
		if(locationsObjList != null && locationsObjList.size() > 0){
			Map<Long,ArrayList<Long>> locationsMap = new HashMap<Long, ArrayList<Long>>();
			Map<Long,String> regionScopesMap = new HashMap<Long, String>();
			for (Object[] objects : locationsObjList) {
				if(locationsMap.get((Long)objects[0]) == null){
					ArrayList<Long> list = new ArrayList<Long>(0);
					list.add(objects[2] != null ? (Long)objects[2]:0l);
					locationsMap.put((Long)objects[0],list);
					regionScopesMap.put((Long)objects[0], objects[1].toString());
				}else{
					locationsMap.get(objects[0]).add(objects[2] != null ? (Long)objects[2]:0l);
				}
			}
			
			if(locationsMap != null && locationsMap.size() > 0){
				for (Entry<Long, ArrayList<Long>> entry : locationsMap.entrySet()) {
					List<Object[]> objList1 = null;
					if(entry.getKey() == 3l){//DISTRICT
						objList1 = districtDAO.getDistrictIdAndNameByDistrictIds(entry.getValue());
					}else if(entry.getKey() == 4l){//CONSTITUENCY
						objList1 = constituencyDAO.getConstIdAndNameByConstIds(entry.getValue());
					}else if(entry.getKey() == 5l){//MANDAL
						objList1 = tehsilDAO.getTehsilIdAndNameByIds(entry.getValue());
					}else if(entry.getKey() == 6l){//VILLAGE
						objList1 = panchayatDAO.getPanchayatIdAndNameByIds(entry.getValue());
					}
					
					if(objList1 != null && objList1.size() > 0){
						MobileAppUserLocationVO locationVO = new MobileAppUserLocationVO();
						locationVO.setLocationScopeId(entry.getKey());
						locationVO.setLocationScope(regionScopesMap.get(entry.getKey()));
						for (Object[] objects : objList1) {
							SmallVO inVO = new SmallVO();
							inVO.setKey((Long)objects[0]);
							inVO.setValue(objects[1].toString());
							locationVO.getSmallVOList().add(inVO);
						}
						
						finalVO.getLovcationVoList().add(locationVO);
					}
				}
			}
		}
	}
	
	
	/*public List<GovtWorksVO> getWorkDetailsOfMobileAppUser(Long mobileAppUserId,Long workTypeId){
		List<GovtWorksVO> voList = new ArrayList<GovtWorksVO>(0);
		try {
			//0-userId,1-userName,2-workTypeId,3-workType,4-locationScopeId,5-locationScope,6-locationValue,7-govtWorkId,8-workZoneName,
			//9-workLength,10-completedPercentage
			List<Object[]> objList = govtWorkDAO.getWorkDetailsOfMobileAppUser(mobileAppUserId,workTypeId);
			if(objList != null && objList.size() > 0){
				List<Long> allWorkIds = new ArrayList<Long>(0);//to get the status
				//get location names -- start
				Map<Long,List<Long>> locationLevelIdsMap = new HashMap<Long, List<Long>>(0);
				Map<Long,String> locationLevelNamesMap = new HashMap<Long, String>(0);
				Map<Long,Map<Long,String>> locationNamesMap = new HashMap<Long, Map<Long,String>>(0);
				for (Object[] objects : objList) {
					if(locationLevelIdsMap.get((Long)objects[4]) == null){
						List<Long> list = new ArrayList<Long>(0);
						list.add((Long)objects[6]);
						locationLevelIdsMap.put((Long)objects[4],list);
						locationLevelNamesMap.put((Long)objects[4], objects[5].toString());
					}else{
						locationLevelIdsMap.get((Long)objects[4]).add((Long)objects[6]);
					}
				}
				
				if(locationLevelIdsMap != null && locationLevelIdsMap.size() > 0){
					for (Entry<Long, List<Long>> entry : locationLevelIdsMap.entrySet()) {
						List<Object[]> objList1 = null;
						if(entry.getKey() == 3l){//DISTRICT
							objList1 = districtDAO.getDistrictIdAndNameByDistrictIds(entry.getValue());
						}else if(entry.getKey() == 4l){//CONSTITUENCY
							objList1 = constituencyDAO.getConstIdAndNameByConstIds(entry.getValue());
						}else if(entry.getKey() == 5l){//MANDAL
							objList1 = tehsilDAO.getTehsilIdAndNameByIds(entry.getValue());
						}else if(entry.getKey() == 6l){//VILLAGE
							objList1 = panchayatDAO.getPanchayatIdAndNameByIds(entry.getValue());
						}
						
						if(objList1 != null && objList1.size() > 0){
							Map<Long,String> tempMap = new HashMap<Long, String>(0);
							for (Object[] objects : objList1) {
								tempMap.put((Long)objects[0], objects[1].toString());
							}
							locationNamesMap.put(entry.getKey(), tempMap);
						}
					}
				}
				//get location names -- end
				
				//setting the result to final vo -- start
				Map<Long,GovtWorksVO> worksTypeMap = new HashMap<Long, GovtWorksVO>(0);//workTypeId,vo
				for (Object[] objects : objList) {
					GovtWorksVO workTypeVO = worksTypeMap.get((Long)objects[2]);
					if(workTypeVO == null){
						workTypeVO = new GovtWorksVO();
						workTypeVO.setWorkTypeId(objects[2] != null ? (Long)objects[2]:0l);
						workTypeVO.setWorkType(objects[3] != null ? objects[3].toString():"");
						worksTypeMap.put((Long)objects[2],workTypeVO);
					}
					
					workTypeVO = worksTypeMap.get((Long)objects[2]);
					GovtWorksVO matchedLocationLevelVO =  getMatchedLocationLevelVO(workTypeVO.getLocationsScopeList(),(Long)objects[4]);
					if(matchedLocationLevelVO == null){
						matchedLocationLevelVO = new GovtWorksVO();
						matchedLocationLevelVO.setLocationScopeId((Long)objects[4]);
						matchedLocationLevelVO.setLocationScope(objects[5].toString());
						workTypeVO.getLocationsScopeList().add(matchedLocationLevelVO);
					}
					
					matchedLocationLevelVO =  getMatchedLocationLevelVO(workTypeVO.getLocationsScopeList(),(Long)objects[4]);
					GovtWorksVO matchedLocationVO = getMatchedLocationVO(matchedLocationLevelVO.getLocationsList(),(Long)objects[6]);
					if(matchedLocationVO == null){
						matchedLocationVO = new GovtWorksVO();
						matchedLocationVO.setLocationValue((Long)objects[6]);
						String locationName = "";
						if(locationNamesMap.get((Long)objects[4]) != null){
							locationName = locationNamesMap.get((Long)objects[4]).get((Long)objects[6]) != null ? locationNamesMap.get((Long)objects[4]).get((Long)objects[6]) : "";
						}
						matchedLocationVO.setLocation(locationName);
						matchedLocationLevelVO.getLocationsList().add(matchedLocationVO);
					}
					
					matchedLocationVO = getMatchedLocationVO(matchedLocationLevelVO.getLocationsList(),(Long)objects[6]);
					GovtWorksVO workVO = new GovtWorksVO();
					allWorkIds.add(objects[7] != null ? (Long)objects[7]:0l);
					workVO.setWorkId((Long)objects[7]);
					workVO.setWorkName(objects[8] != null ? objects[8].toString():"");
					workVO.setWorkLenght(objects[9] != null ? (Double)objects[9]:0.00);
					workVO.setCompletedPercentage(objects[10] != null ? (Double)objects[10]:0.00);
					matchedLocationVO.getWorksList().add(workVO);
				}
				
				//set current status to works
				if(allWorkIds != null && allWorkIds.size() > 0){
					//0-workId,1-statusId
					List<Object[]> workStatusObjList = govtWorkProgressDAO.getLatestStatusOfWork(allWorkIds);
					if(workStatusObjList != null && workStatusObjList.size() > 0){
						//get all status
						List<GovtWorkStatus> statusAllList = govtWorkStatusDAO.getAll();
						Map<Long,String> stausAllMap = new HashMap<Long, String>(0);
						if(statusAllList != null && statusAllList.size() > 0){
							for (GovtWorkStatus govtWorkStatus : statusAllList) {
								stausAllMap.put(govtWorkStatus.getGovtWorkStatusId(), govtWorkStatus.getStatusName());
							}
						}
						
						Map<Long,Long> workStatusMap = new HashMap<Long, Long>(0);
						for (Object[] objects : workStatusObjList) {
							workStatusMap.put((Long)objects[0], (Long)objects[1]);
						}
						
						if(worksTypeMap != null && worksTypeMap.size() > 0){
							voList.addAll(worksTypeMap.values());
							for (GovtWorksVO workTypeVO : voList) {
								if(workTypeVO != null && workTypeVO .getLocationsScopeList() != null && workTypeVO.getLocationsScopeList().size() > 0){
									for (GovtWorksVO locationScopeVO : workTypeVO .getLocationsScopeList()) {
										if(locationScopeVO != null && locationScopeVO.getLocationsList() != null && locationScopeVO.getLocationsList().size() > 0){
											for (GovtWorksVO locationVO : locationScopeVO.getLocationsList()) {
												if(locationVO != null && locationVO.getWorksList() != null && locationVO.getWorksList().size() > 0){
													for (GovtWorksVO worksVO : locationVO.getWorksList()) {
														if(workStatusMap.get(worksVO.getWorkId()) != null){
															worksVO.setStatusId(workStatusMap.get(worksVO.getWorkId()));
															worksVO.setStatus(stausAllMap.get(worksVO.getStatusId()));
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured while getting user created works", e);
		}
		return voList;
	}*/
	
	public GovtWorksVO getMatchedLocationLevelVO(List<GovtWorksVO> voList,Long locationLevelId){
		if(voList != null && voList.size() > 0){
			for (GovtWorksVO govtWorksVO : voList) {
				if(govtWorksVO.getLocationScopeId().equals(locationLevelId))
					return govtWorksVO;
			}
		}
		return null;
	}
	
	public GovtWorksVO  getMatchedLocationVO(List<GovtWorksVO> voList,Long locationValue){
		if(voList != null && voList.size() > 0){
			for (GovtWorksVO govtWorksVO : voList) {
				if(govtWorksVO.getLocationValue().equals(locationValue))
					return govtWorksVO;
			}
		}
		return null;
	}
	
	public ResultStatus saveWorkDetails(GovtWorksVO govtWorksVO){
		ResultStatus rs = new ResultStatus();
		try {
			if(govtWorksVO != null){
				
				/*LocationAddressVO locationAddressVO = getLocationHierarchy(govtWorksVO.getLocationScopeId(),govtWorksVO.getLocationValue());
				LocationAddress locationAddress = null;
				//location saving -- start
				if(locationAddressVO != null){
					if(govtWorksVO.getLocationScopeId() == 3l){
						locationAddress = new LocationAddress();
						locationAddress.setStateId(locationAddressVO.getStateId());
						locationAddress.setDistrictId(locationAddressVO.getDistrictId());
						locationAddress = locationAddressDAO.save(locationAddress);
					}else if(govtWorksVO.getLocationScopeId() == 4l){
						locationAddress = new LocationAddress();
						locationAddress.setStateId(locationAddressVO.getStateId());
						locationAddress.setDistrictId(locationAddressVO.getDistrictId());
						locationAddress.setConstituencyId(locationAddressVO.getConstituencyId());
						locationAddress.setParliamentId(locationAddressVO.getParliamentId());
						locationAddress = locationAddressDAO.save(locationAddress);
					}else if(govtWorksVO.getLocationScopeId() == 5l){
						locationAddress = new LocationAddress();
						locationAddress.setStateId(locationAddressVO.getStateId());
						locationAddress.setDistrictId(locationAddressVO.getDistrictId());
						locationAddress.setConstituencyId(locationAddressVO.getConstituencyId());
						locationAddress.setParliamentId(locationAddressVO.getParliamentId());
						locationAddress.setTehsilId(locationAddressVO.getTehsilId());
						locationAddress = locationAddressDAO.save(locationAddress);
					}else if(govtWorksVO.getLocationScopeId() == 6l){
						locationAddress = new LocationAddress();
						locationAddress.setStateId(locationAddressVO.getStateId());
						locationAddress.setDistrictId(locationAddressVO.getDistrictId());
						locationAddress.setConstituencyId(locationAddressVO.getConstituencyId());
						locationAddress.setParliamentId(locationAddressVO.getParliamentId());
						locationAddress.setTehsilId(locationAddressVO.getTehsilId());
						locationAddress.setPanchayatId(locationAddressVO.getPanchaytId());
						locationAddress = locationAddressDAO.save(locationAddress);
					}
				}*/
				//location saving -- end
				
				GovtWork govtWork = new GovtWork();
				govtWork.setGovtMainWorkId(govtWorksVO.getGovtMainWorkId() != null ? govtWorksVO.getGovtMainWorkId():null);
				//govtWork.setGovtWorkTypeId(govtWorksVO.getWorkTypeId() != null ? govtWorksVO.getWorkTypeId():null);
				govtWork.setWorkZoneName(govtWorksVO.getWorkZoneName() != null ? govtWorksVO.getWorkZoneName():null);
				//govtWork.setFundAllocated(govtWorksVO.getFundAllocated() != null ? govtWorksVO.getFundAllocated():null);
				govtWork.setWorkLength(govtWorksVO.getWorkLenght() != null ? govtWorksVO.getWorkLenght():null);
				//govtWork.setLocationScopeId(govtWorksVO.getLocationScopeId());
				//govtWork.setLocationValue(govtWorksVO.getLocationValue());
				//govtWork.setLocationAddressId(locationAddress != null && locationAddress.getLocationAddressId() != null ? locationAddress.getLocationAddressId():null);
				//govtWork.setCompletedPercentage(govtWorksVO.getCompletedPercentage() != null ? govtWorksVO.getCompletedPercentage():null);
				govtWork.setLattitude(govtWorksVO.getLattitude() != null ? govtWorksVO.getLattitude():null);
				govtWork.setLongitude(govtWorksVO.getLongitude() != null ? govtWorksVO.getLongitude():null);
				govtWork.setCreatedBy(govtWorksVO.getUserId() != null ? govtWorksVO.getUserId():null);
				govtWork.setCreatedTime(dateUtilService.getCurrentDateAndTime());
				govtWork.setUpdatedBy(govtWorksVO.getUserId() != null ? govtWorksVO.getUserId():null);
				govtWork.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				govtWork.setIsDeleted("N");
				govtWork = govtWorkDAO.save(govtWork);
				
				//save work documents -- start
				if(govtWorksVO.getImagesList() != null && govtWorksVO.getImagesList().size() > 0){
					String folderName = folderCreation();
					for (String imageStr : govtWorksVO.getImagesList()) {
						String destPath = folderName + "/" +UUID.randomUUID().toString() + ".jpg";
						commonMethodsUtilService.convertBase64StringToImage(imageStr, destPath);
						
						//save docuemntin document table
						Long documentId = saveDocuemnt(destPath,imageStr,govtWorksVO.getUserId());
						if(documentId != null && documentId > 0l){
							GovtWorkDocument govtWorkDocument = new GovtWorkDocument();
							govtWorkDocument.setGovtWorkId(govtWorksVO.getWorkId());
							govtWorkDocument.setDocumentId(documentId);
							govtWorkDocument.setUpdatedBy(govtWorksVO.getUserId());
							govtWorkDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
							govtWorkDocument.setIsDeleted("N");
							govtWorkDocumentDAO.save(govtWorkDocument);
						}
					}
				}
				//save work documents -- end
			}
			rs.setMessage("success");
		} catch (Exception e) {
			rs.setMessage("failure");
			LOG.error("Exception raised while saving the work detials", e);
		}
		return rs;
	}
	
	public LocationAddressVO getLocationHierarchy(Long locationScopeId,Long locationLevelId){
		LocationAddressVO addressVO = null;
		if(locationScopeId == 3l){//district level
			Object[] obj = districtDAO.getDistrictDetails(locationLevelId);
			if(obj != null && obj.length > 0){
				addressVO = new LocationAddressVO();
				addressVO.setStateId((Long)obj[2]);
				addressVO.setStateName(obj[3].toString());
				addressVO.setDistrictId((Long)obj[0]);
				addressVO.setDistrictName(obj[1].toString());
			}
		}else if(locationScopeId == 4l){//constituenct
			//0-constId,1-constName,2-distId,3-distName,4-stateId,5-stateName
			Object[] obj = constituencyDAO.getConstituencyDetails(locationLevelId);
			if(obj != null && obj.length > 0){
				addressVO = new LocationAddressVO();
				addressVO.setStateId((Long)obj[4]);
				addressVO.setStateName(obj[5].toString());
				addressVO.setDistrictId((Long)obj[2]);
				addressVO.setDistrictName(obj[3].toString());
				addressVO.setConstituencyId((Long)obj[0]);
				addressVO.setConstituencyName(obj[1].toString());
				
				if(addressVO.getConstituencyId() != null && addressVO.getConstituencyId() > 0l){
					//0-assemblyId,1-assemblyName,2-parliamentId,3-parliamentName
					List<Object[]> obj1 = parliamentAssemblyDAO.getParliamentOfConstituency(addressVO.getConstituencyId());
					if(obj1 != null && obj1.size() > 0){
						addressVO.setParliamentId((Long)obj1.get(0)[2]);
						addressVO.setParliamentName(obj1.get(0)[3].toString());
					}
				}
			}
		}else if(locationScopeId == 5l){//mandal
			//0-tehsilId,1-tehsilName,2-distId,3-distName,4-stateId,5-stateName
			Object[] obj = tehsilDAO.getTehsilDetails(locationLevelId);
			if(obj != null && obj.length > 0){
				addressVO = new LocationAddressVO();
				addressVO.setStateId((Long)obj[4]);
				addressVO.setStateName(obj[5].toString());
				addressVO.setDistrictId((Long)obj[2]);
				addressVO.setDistrictName(obj[3].toString());
				addressVO.setTehsilId((Long)obj[0]);
				addressVO.setTehsilName(obj[1].toString());
				
				//0-tehsilId,1-tehsilName,2-contId,3-contName
				List<Object[]> objList  = tehsilConstituencyDAO.getConstituencyOfTehsil(locationLevelId);
				if(objList != null && objList.size() > 0){
					addressVO.setConstituencyId((Long)objList.get(0)[2]);
					addressVO.setConstituencyName(objList.get(0)[3].toString());
					
					//0-assemblyId,1-assemblyName,2-parliamentId,3-parliamentName
					List<Object[]> obj1 = parliamentAssemblyDAO.getParliamentOfConstituency(addressVO.getConstituencyId());
					if(obj1 != null && obj1.size() > 0){
						addressVO.setParliamentId((Long)obj1.get(0)[2]);
						addressVO.setParliamentName(obj1.get(0)[3].toString());
					}
				}
			}
		}else if(locationScopeId == 6l){//panchayat
			//0-panchayatId,1-panchaytName,2-tehsilId,3-tehsilName,4-distId,5-distName,6-stateId,7-stateName
			List<Object[]> objList = panchayatDAO.getPanchayatDetails(locationLevelId);
			if(objList != null && objList.size() > 0){
				addressVO = new LocationAddressVO();
				addressVO.setPanchaytId((Long)objList.get(0)[0]);
				addressVO.setPanchaytName(objList.get(0)[1].toString());
				addressVO.setTehsilId((Long)objList.get(0)[2]);
				addressVO.setTehsilName(objList.get(0)[3].toString());
				addressVO.setDistrictId((Long)objList.get(0)[4]);
				addressVO.setDistrictName(objList.get(0)[5].toString());
				addressVO.setStateId((Long)objList.get(0)[6]);
				addressVO.setStateName(objList.get(0)[7].toString());
				
				//0-tehsilId,1-tehsilName,2-contId,3-contName
				List<Object[]> objList1  = tehsilConstituencyDAO.getConstituencyOfTehsil(addressVO.getTehsilId());
				if(objList1 != null && objList1.size() > 0){
					addressVO.setConstituencyId((Long)objList.get(0)[2]);
					addressVO.setConstituencyName(objList.get(0)[3].toString());
					
					//0-assemblyId,1-assemblyName,2-parliamentId,3-parliamentName
					List<Object[]> obj1 = parliamentAssemblyDAO.getParliamentOfConstituency(addressVO.getConstituencyId());
					if(obj1 != null && obj1.size() > 0){
						addressVO.setParliamentId((Long)obj1.get(0)[2]);
						addressVO.setParliamentName(obj1.get(0)[3].toString());
					}
				}
				
			}
		}
		return addressVO;
	}
	
	public Long saveDocuemnt(String destPath,String imageStr,Long userId){
		com.itgrids.model.Document document = new com.itgrids.model.Document();
		document.setPath(destPath);
		document.setBase64str(imageStr);
		document.setMobileAppUserId(userId);
		document.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		return documentDAO.save(document).getDocumentId();
	}
	
	public ResultStatus updateWorkStatus(List<WorkStatusVO> workStatusVOList){
		ResultStatus rs = new ResultStatus();
		try {
			GovtWorkProgressUpdate govtWorkProgressUpdate = new GovtWorkProgressUpdate();
			govtWorkProgressUpdate.setGovtWorkId(workStatusVOList.get(0).getWorkId());
			govtWorkProgressUpdate.setUpdatedBy(workStatusVOList.get(0).getUserId());
			govtWorkProgressUpdate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
			Long uniqueUpdateId = govtWorkProgressUpdateDAO.save(govtWorkProgressUpdate).getGovtWorkProgressUpdateId();
			
			for (WorkStatusVO workStatusVO : workStatusVOList) {
				GovtWorkProgress govtWorkProgressObj = govtWorkProgressDAO.getWorkProgressId(workStatusVO.getWorkId(),workStatusVO.getGovtWorkStatusId());
				if(govtWorkProgressObj == null){
					govtWorkProgressObj = new GovtWorkProgress();
					govtWorkProgressObj.setGovtWorkId(workStatusVO.getWorkId());
				}
				
				govtWorkProgressObj.setGovtWorkStatusId(workStatusVO.getGovtWorkStatusId());
				govtWorkProgressObj.setWorkLength(workStatusVO.getWorkLenght() != null ? workStatusVO.getWorkLenght():null);
				govtWorkProgressObj.setUpdatedBy(workStatusVO.getUserId());
				govtWorkProgressObj.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				govtWorkProgressObj.setCompletedPercentage(workStatusVO.getWorkCompletedPercentage() != null ? workStatusVO.getWorkCompletedPercentage():null);
				govtWorkProgressObj.setIsCompleted(workStatusVO.getIsCompleted() != null ? workStatusVO.getIsCompleted():"N");
				govtWorkProgressDAO.save(govtWorkProgressObj).getGovtWorkProgressId();
				
				//save in track
				saveWorkStatusDetailsInTracking(workStatusVO,uniqueUpdateId);
			}
			rs.setMessage("success");
		} catch (Exception e) {
			rs.setMessage("failure");
			LOG.error("Exception raised while updationg  the work status", e);
		}
		return rs;
	}
	
	public List<DocumentVO> updateWorkStatusDocuments(List<WorkStatusVO> workStatusVOList){
		List<DocumentVO> resultList = new ArrayList<DocumentVO>(0);
		try {
			if(workStatusVOList != null && workStatusVOList.size() > 0){
				GovtWorkProgressUpdate govtWorkProgressUpdate = new GovtWorkProgressUpdate();
				govtWorkProgressUpdate.setGovtWorkId(workStatusVOList.get(0).getWorkId());
				govtWorkProgressUpdate.setUpdatedBy(workStatusVOList.get(0).getUserId());
				govtWorkProgressUpdate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				Long uniqueUpdateId = govtWorkProgressUpdateDAO.save(govtWorkProgressUpdate).getGovtWorkProgressUpdateId();
				
				for (WorkStatusVO workStatusVO : workStatusVOList) {
					GovtWorkProgress govtWorkProgressObj = govtWorkProgressDAO.getWorkProgressId(workStatusVO.getWorkId(),workStatusVO.getGovtWorkStatusId());
					Long govtWorkProgressId = null;
					if(govtWorkProgressObj != null){
						govtWorkProgressId = govtWorkProgressObj.getGovtWorkProgressId();
					}
					
					//save status documents
					if(workStatusVO.getImagesList() != null && workStatusVO.getImagesList().size() > 0){
						String folderName = folderCreation();
						for (String imageStr : workStatusVO.getImagesList()) {
							String destPath = folderName + "/" +UUID.randomUUID().toString()+ ".jpg";
							commonMethodsUtilService.convertBase64StringToImage(imageStr, destPath);
								
							//save docuemntin document table
							Long documentId = saveDocuemnt(destPath,imageStr,workStatusVO.getUserId());
							GovtWorkProgressDocument govtWorkProgressDocument =  saveWorkStatusDocuments(govtWorkProgressId,uniqueUpdateId,documentId,workStatusVO.getUserId());
							
							if(govtWorkProgressDocument != null){
								DocumentVO vo = new DocumentVO();
								vo.setDocumentId(govtWorkProgressDocument.getDocumentId());
								vo.setPath(documentDAO.get(govtWorkProgressDocument.getDocumentId()).getPath());
								resultList.add(vo);
							}
							
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured while updating the work status documents", e);
		}
		return resultList;
	}
	
	public void saveWorkStatusDetailsInTracking(WorkStatusVO workStatusVO,Long uniqueUpdateId){
		GovtWorkProgressTrack govtWorkProgressTrack = new GovtWorkProgressTrack();
		govtWorkProgressTrack.setGovtWorkId(workStatusVO.getWorkId());
		govtWorkProgressTrack.setGovtWorkProgressUpdateId(uniqueUpdateId);
		govtWorkProgressTrack.setGovtWorkStatusId(workStatusVO.getGovtWorkStatusId());
		govtWorkProgressTrack.setWorkLength(workStatusVO.getWorkLenght() != null ? workStatusVO.getWorkLenght():null);
		govtWorkProgressTrack.setUpdatedBy(workStatusVO.getUserId());
		govtWorkProgressTrack.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		govtWorkProgressTrack.setCompletedPercentage(workStatusVO.getWorkCompletedPercentage() != null ? workStatusVO.getWorkCompletedPercentage():null);
		govtWorkProgressTrack.setIsCompleted(workStatusVO.getIsCompleted() != null ? workStatusVO.getIsCompleted():"N");
		govtWorkProgressTrack = govtWorkProgressTrackDAO.save(govtWorkProgressTrack);
	}
	
	public GovtWorkProgressDocument saveWorkStatusDocuments(Long govtWorkProgressId,Long uniqueUpdateId,Long documentId,Long userId){
		GovtWorkProgressDocument govtWorkProgressDocument = new GovtWorkProgressDocument();
		govtWorkProgressDocument.setGovtWorkProgressId(govtWorkProgressId);
		govtWorkProgressDocument.setGovtWorkProgressUpdateId(uniqueUpdateId);
		govtWorkProgressDocument.setDocumentId(documentId);
		govtWorkProgressDocument.setIsDeleted("N");
		govtWorkProgressDocument.setUpdatedBy(userId);
		govtWorkProgressDocument.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		return govtWorkProgressDocumentDAO.save(govtWorkProgressDocument);
	}
	
	public static String folderCreation(){
	  	 try {
	  		 LOG.debug(" in FolderForArticle ");
	  		
	  		Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			 int year = calendar.get(Calendar.YEAR);
			 int month = calendar.get(Calendar.MONTH);
			 int day = calendar.get(Calendar.DAY_OF_MONTH);
			
			 String staticPath = IConstants.STATIC_CONTENT_FOLDER_URL;
			 String activityDocDir = createFolder(staticPath+IConstants.GOVT_WORK_DOCS);
			 
			 String yr = String.valueOf(year); // YEAR YYYY
			 String yrDir = staticPath+IConstants.GOVT_WORK_DOCS+"/"+yr;
			 
			 String yrFldrSts = createFolder(yrDir);
			 if(!yrFldrSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 StringBuilder str = new StringBuilder();
			 int temp = month+1;
			 str.append(temp).append("-").append(day);
			 
			 
			 String mnth = str.toString();
			 String mnthDir = staticPath+IConstants.GOVT_WORK_DOCS+"/"+yr+"/"+mnth;
			 String mnthDirSts = createFolder(mnthDir);
			 if(!mnthDirSts.equalsIgnoreCase("SUCCESS")){
				 return "FAILED";
			 }
			 
			 return staticPath+IConstants.GOVT_WORK_DOCS+"/"+yr+"/"+mnth;
			 
		} catch (Exception e) {
			LOG.error(" Failed to Create");
			return "FAILED";
		}
		 
	}
	public static String createFolder(String dir){
	 	try {
			File theDir = new File(dir);
			  // if the directory does not exist, create it
			  if (!theDir.exists()) {
			    boolean result = false;
			    try{
			    	theDir.getParentFile().mkdirs();
			        theDir.mkdir();
			        result = true;
			     } catch(SecurityException se){
			        //handle it
			     }        
			     if(result) {    
			      LOG.debug("DIR With Name "+dir+" created");  
			     }
			  }else{
				  LOG.debug("DIR With Name "+dir+" EXISTS");
			  }
			  return "SUCCESS";
		} catch (Exception e) {
			LOG.error(dir+" Failed to Create");
			return "FAILED";
		}
	}
	
	public WorkStatusVO getMatchedWorkStatusTypeVO(List<WorkStatusVO> voList,Long statusTypeId){
		if(voList != null && voList.size() > 0){
			for (WorkStatusVO workStatusVO : voList) {
				if(workStatusVO.getStatusTypeId().equals(statusTypeId))
					return workStatusVO;
			}
		}
		return null;
	}
	
	public WorkStatusVO getMatchedWorkStatusVO(List<WorkStatusVO> voList,Long govtstatusId){
		if(voList != null && voList.size() > 0){
			for (WorkStatusVO workStatusVO : voList) {
				if(workStatusVO.getGovtWorkStatusId().equals(govtstatusId))
					return workStatusVO;
			}
		}
		return null;
	}
	
	public List<WorkStatusVO> getAllTheStatusOfGovtWork(Long workId){
		List<WorkStatusVO> voList = new ArrayList<WorkStatusVO>(0);
		try {
			//build work status template -- start
			Long workTypeId = govtWorkDAO.getWorkTypeId(workId);
			if(workTypeId != null && workTypeId > 0l){
				//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-govtWorkStatus
				List<Object[]> objList = govtWorkStatusDAO.getAllStatusOfWorkType(workTypeId);
				if(objList != null && objList.size() > 0){
					for (Object[] objects : objList) {
						WorkStatusVO matchedWorkStatusTypeVO = getMatchedWorkStatusTypeVO(voList,(Long)objects[0]);
						if(matchedWorkStatusTypeVO == null){
							matchedWorkStatusTypeVO = new WorkStatusVO();
							matchedWorkStatusTypeVO.setStatusTypeId((Long)objects[0]);
							matchedWorkStatusTypeVO.setStatusType(objects[1].toString());
							
							WorkStatusVO inVO = new WorkStatusVO();
							inVO.setGovtWorkStatusId((Long)objects[2]);
							inVO.setGovtWorkStatus(objects[3].toString());
							matchedWorkStatusTypeVO.getWorkStatusVOList().add(inVO);
							
							voList.add(matchedWorkStatusTypeVO);
						}else{
							WorkStatusVO inVO = new WorkStatusVO();
							inVO.setGovtWorkStatusId((Long)objects[2]);
							inVO.setGovtWorkStatus(objects[3].toString());
							matchedWorkStatusTypeVO.getWorkStatusVOList().add(inVO);
						}
					}
				}
			}
			//build work status template -- end
			
			//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-statusName,4-workLength,5-completedPercentage,6-isCompleted,7-updateById,8-updatedByName,9-time,10-govtWorkProgressId
			List<Object[]> objList = govtWorkProgressDAO.getAllstatusInfoOfGovtWork(workId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					WorkStatusVO matchedWorkStatusTypeVO = getMatchedWorkStatusTypeVO(voList,(Long)objects[0]);
					if(matchedWorkStatusTypeVO == null){
						matchedWorkStatusTypeVO = new WorkStatusVO();
						matchedWorkStatusTypeVO.setStatusTypeId((Long)objects[0]);
						matchedWorkStatusTypeVO.setStatusType(objects[1].toString());
						voList.add(matchedWorkStatusTypeVO);
						matchedWorkStatusTypeVO = getMatchedWorkStatusTypeVO(voList,(Long)objects[0]);
					}
					
					WorkStatusVO vo = getMatchedWorkStatusVO(matchedWorkStatusTypeVO.getWorkStatusVOList(),(Long)objects[2]);
					if(vo == null){
						vo = new WorkStatusVO();
						vo.setGovtWorkStatusId((Long)objects[2]);
						vo.setGovtWorkStatus(objects[3].toString());
						matchedWorkStatusTypeVO.getWorkStatusVOList().add(vo);
						vo = getMatchedWorkStatusVO(matchedWorkStatusTypeVO.getWorkStatusVOList(),(Long)objects[2]);
					}
					
					vo.setGovtWorkStatusId((Long)objects[2]);
					vo.setGovtWorkStatus(objects[3].toString());
					vo.setWorkLenght(objects[4] != null ? (Double)objects[4]:0.00);
					vo.setWorkCompletedPercentage(objects[5] != null ? (Double)objects[5]:0.00);
					vo.setIsCompleted(objects[6] != null ?objects[6].toString():"");
					vo.setUserId(objects[7] != null ? (Long)objects[7]:null);
					vo.setUserName(objects[8] != null ? objects[8].toString():null);
					vo.setDate(objects[9] != null ? objects[9].toString():null);
				}
				
				//get the status Documents
				//0-statusTypeId,1-govtStatusId,2-documentId,3-path,4-mobileAppUserId,5-username,6-insertedTime
				List<Object[]> objList1 = govtWorkProgressDocumentDAO.getStatusDocumentsOfGovtWork(workId);
				if(objList1 != null && objList1.size() > 0){
					for (Object[] objects : objList1) {
						WorkStatusVO matchedWorkStatusTypeVO = getMatchedWorkStatusTypeVO(voList,(Long)objects[0]);
						if(matchedWorkStatusTypeVO != null){
							WorkStatusVO matchedWorkStatusVO = getMatchedWorkStatusVO(matchedWorkStatusTypeVO.getWorkStatusVOList(),(Long)objects[1]);
							if(matchedWorkStatusVO != null){
								DocumentVO docVO = new DocumentVO();
								docVO.setDocumentId((Long)objects[2]);
								docVO.setPath(objects[3] != null ? objects[3].toString():"");
								docVO.setUserId(objects[4] != null ? (Long)objects[4]:0l);
								docVO.setUserName(objects[5] != null ? objects[5].toString():"");
								docVO.setInsertedTime(objects[6] != null ? objects[6].toString():"");
								matchedWorkStatusVO.getDocumentList().add(docVO);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured while fetching the stats of govtwork", e);
		}
		return voList;
	}

	public List<GovtWorksVO> getAllGovtWorksOfGovtMainWork(Long userId,Long mainWorkId){
		List<GovtWorksVO> voList = new ArrayList<GovtWorksVO>(0);
		try {
			//0-workId,1-workZoneName,2-workLength,3-locationScopeId,4-locationScopeValue
			List<Object[]> objList = govtWorkDAO.getWorkZoneDetailsOfMainWork(userId, mainWorkId);
			if(objList != null && objList.size() > 0){
				Map<Long,GovtWorksVO> map = new HashMap<Long, GovtWorksVO>();
				List<Long> allWorkIds = new ArrayList<Long>(0);
				for (Object[] objects : objList) {
					GovtWorksVO vo = new GovtWorksVO();
					allWorkIds.add((Long)objects[0]);
					vo.setWorkId((Long)objects[0]);
					vo.setWorkZoneName(objects[1].toString());
					vo.setWorkLenght(objects[2] != null ? (Double)objects[2]:null);
					
					if(objects[3]  != null){
						int locationScopeId = Integer.parseInt(objects[3].toString());
						vo.setLocationScopeId((Long)objects[3]);
						vo.setLocationValue((Long)objects[4]);
						switch(locationScopeId) {
						   case 3 :
							   vo.setLocationScope("District");
							   vo.setLocation(districtDAO.get((Long)objects[4]).getDistrictName());
							   break; // optional
						   case 4 :
							   vo.setLocationScope("Constituency");
							   vo.setLocation(constituencyDAO.get((Long)objects[4]).getName());
							   break; // optional
						   case 5 :
							   vo.setLocationScope("Tehsil");
							   vo.setLocation(tehsilDAO.get((Long)objects[4]).getTehsilName());
							   break; // optional
						   case 6 :
							   vo.setLocationScope("Village");
							   vo.setLocation(panchayatDAO.get((Long)objects[4]).getPanchayatName());
							   break; // optional
						}
					}
					map.put((Long)objects[0], vo);
				}
				
				//0-workId,1-finishedLength
				List<Object[]> objList1 = govtWorkProgressDAO.getAllFininsedKmsOfWorks(allWorkIds);
				if(objList1 != null && objList1.size() > 0){
					for (Object[] objects : objList1) {
						if(map.get(Long.parseLong(objects[0].toString())) != null){
							map.get(Long.parseLong(objects[0].toString())).setCompletedLength(Double.parseDouble((objects[1].toString())));
						}
					}
				}
				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("Exception occured while fetching the work zones of main work", e);
		}
		return voList;
	}
	
	public List<WorkStatusVO> getStatusWiseDayReport(MobileAppInputVO inputVO){
		List<WorkStatusVO> voList = new ArrayList<WorkStatusVO>(0);
		try {
			Date startDate=null,endDate=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				startDate = sdf.parse(inputVO.getFromDate());
				endDate = sdf.parse(inputVO.getToDate());
			}
			
			Object totalWorkLength = govtWorkDAO.getOverallWork(inputVO.getWorkTypeId(),inputVO.getWorkId());
			
			//0-statusId,1-statuName,2-date,3-length
			List<Object[]> objList = govtWorkProgressTrackDAO.getStatusWiseDayReport(inputVO.getWorkTypeId(),inputVO.getWorkId(),startDate,endDate,inputVO.getReportType());
			
			if(objList != null && objList.size() > 0){
				Map<Long,WorkStatusVO> map = new HashMap<Long, WorkStatusVO>();
				for (Object[] objects : objList) {
					WorkStatusVO vo = map.get(Long.parseLong(objects[0].toString()));
					if(vo == null){
						vo = new WorkStatusVO();
						vo.setGovtWorkStatusId(Long.parseLong(objects[0].toString()));
						vo.setGovtWorkStatus(objects[1].toString());
						
						if(inputVO.getReportType() != null){
							List<String> periodList = null;
							if(inputVO.getReportType().trim().equalsIgnoreCase("dayWise")){
								periodList = commonMethodsUtilService.getBetweenDatesInString(startDate, endDate);
							}else if(inputVO.getReportType().trim().equalsIgnoreCase("monthWise")){
								periodList = commonMethodsUtilService.getMonthYearBetweenDates(startDate, endDate);
							}
							
							if(periodList != null && periodList.size() > 0){
								for (String string : periodList) {
									DocumentVO voIn = new DocumentVO();
									voIn.setInsertedTime(string);
									vo.getDocumentList().add(voIn);
								}
							}
						}
						
						map.put(Long.parseLong(objects[0].toString()),vo);
						
						vo = map.get(Long.parseLong(objects[0].toString()));
					}
					
					DocumentVO matchedDateVO = null;
					if(inputVO.getReportType() != null && inputVO.getReportType().equalsIgnoreCase("dayWise"))
						matchedDateVO = getMatchedDateVO(vo.getDocumentList(),objects[2].toString());
					else
						matchedDateVO = getMatchedDateVO(vo.getDocumentList(),((Integer)objects[2]<10?"0"+objects[2].toString():objects[2].toString())+"-"+objects[3].toString());
					
					if(matchedDateVO != null){
						if(inputVO.getReportType() != null && inputVO.getReportType().equalsIgnoreCase("dayWise"))
							matchedDateVO.setKms(objects[3] != null ? Double.parseDouble(objects[3].toString()):null);
						else
							matchedDateVO.setKms(Double.parseDouble(objects[4].toString()));
					}else{
						matchedDateVO = new DocumentVO();
						matchedDateVO.setInsertedTime(objects[2].toString());
						if(inputVO.getReportType() != null && inputVO.getReportType().equalsIgnoreCase("dayWise"))
							matchedDateVO.setKms(objects[3] != null ? Double.parseDouble(objects[3].toString()):null);
						else
							matchedDateVO.setKms(Double.parseDouble(objects[4].toString()));
						vo.getDocumentList().add(matchedDateVO);
					}
					
				}
				
				if(map != null && map.size() > 0){
					for (Entry<Long, WorkStatusVO> entry : map.entrySet()) {
						Double processedLength = 0.00;
						List<DocumentVO> datesList = entry.getValue().getDocumentList();
						if(datesList != null && datesList.size() > 0){
							for (DocumentVO documentVO : datesList) {
								processedLength = processedLength+(documentVO.getKms() != null ? documentVO.getKms():0.00);
							}
							entry.getValue().setWorkLenght(processedLength);
						}
					}
					voList.addAll(map.values());
				}			
			}
			
			if(totalWorkLength != null){
				if(voList != null && voList.size() > 0)
					voList.get(0).setTotalLenght(Double.parseDouble(totalWorkLength.toString()));
				else{
					WorkStatusVO vo = new WorkStatusVO();
					vo.setTotalLenght(Double.parseDouble(totalWorkLength.toString()));
					voList.add(vo);
				}		
			}
		} catch (Exception e) {
			LOG.error("exception at updateWorkStatusDocuments", e);
		}
		
		return voList;
	}
	
	public DocumentVO getMatchedDateVO(List<DocumentVO> voList,String date){
		if(voList != null && voList.size() > 0){
			for (DocumentVO documentVO : voList) {
				if(documentVO.getInsertedTime().equalsIgnoreCase(date))
					return documentVO;
			}
		}
		return null;
	}
	
	public List<GovtMainWorkVO> getWorkTypeWiseCompletedDetails(MobileAppInputVO inputVO){
		List<GovtMainWorkVO> finalList = new ArrayList<GovtMainWorkVO>(0);
		try {
			Map<Long,GovtMainWorkVO> map = new HashMap<Long, GovtMainWorkVO>();
			
			Date fromDate = null, toDate = null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				fromDate = sdf.parse(inputVO.getFromDate());
				toDate = sdf.parse(inputVO.getToDate());
			}
			
			//get all work Types
			List<GovtWorkType> workTypeList =  govtWorkTypeDAO.getAll();
			if(workTypeList != null && workTypeList.size() > 0){
				for (GovtWorkType govtWorkType : workTypeList) {
					GovtMainWorkVO vo = new GovtMainWorkVO();
					vo.setGovtMainWorkId(govtWorkType.getGovtWorkTypeId());
					vo.setGovtMainWork(govtWorkType.getWorkType());
					map.put(govtWorkType.getGovtWorkTypeId(),vo);
				}
				
				//get works count group by workType
				//0-workTypeId,1-workscount
				List<Object[]> objLsit = govtWorkDAO.getWorksCountByMainType(fromDate,toDate);
				if(objLsit != null && objLsit.size() > 0){
					for (Object[] objects : objLsit) {
						if(map.get((Long)objects[0]) != null){
							map.get((Long)objects[0]).setWorksCount((Long)objects[1]);
						}
					}
				}
				
				//get completed works and sum of kms group by workType
				//0-workTypeId,1-workscount,2-worklength
				List<Object[]> objList1 = govtWorkProgressDAO.getCompletedWorksCount(fromDate,toDate);
				if(objList1 != null && objList1.size() > 0){
					for (Object[] objects : objList1) {
						if(map.get(Long.parseLong(objects[0].toString())) != null){
							map.get((Long)objects[0]).setCompletedWorksCount(Long.parseLong(objects[1].toString()));
							map.get((Long)objects[0]).setCompletedKms(Double.parseDouble(objects[2].toString()));
						}
					}
				}
				
				//get praposal works count group by worktype
				//0-workTypeId,1-count
				List<Object[]> objList2 = govtMainWorkDAO.getPraposalWorksCount();
				if(objList2 != null && objList2.size() > 0){
					for (Object[] objects : objList2) {
						if(map.get((Long)objects[0]) != null){
							map.get((Long)objects[0]).setWorkPraposedAreas(objects[1] != null ? (Long)objects[1]:null);
						}
					}
				}
				
				//get workZons for workType
				//0-workTypeId,1-workZones
				List<Object[]> objList3 = govtWorkDAO.getWorkZonesCountForDateType(fromDate,toDate);
				if(objList3 != null && objList3.size() > 0){
					for (Object[] objects : objList3) {
						if(map.get((Long)objects[0]) != null){
							map.get((Long)objects[0]).setWorkZonesCount(objects[1] != null ? (Long)objects[1]:null);
						}
					}
				}
				
				finalList.get(0).setTotalCount(Long.parseLong(workTypeList.size()+""));
			}
			
			
		} catch (Exception e) {
			LOG.error("exception cuured at getWorkTypeWiseCompletedDetails", e);
		}
		return finalList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
