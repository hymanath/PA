package com.itgrids.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.itgrids.dao.IDivisionDAO;
import com.itgrids.dao.IDocumentDAO;
import com.itgrids.dao.IGovtMainWorkDAO;
import com.itgrids.dao.IGovtWorkDAO;
import com.itgrids.dao.IGovtWorkDocumentDAO;
import com.itgrids.dao.IGovtWorkProgressCommentDAO;
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
import com.itgrids.dao.IMobileAppUserTypeDAO;
import com.itgrids.dao.IMobileAppUserWorkTypeDAO;
import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.ISubDivisionDAO;
import com.itgrids.dao.ISubDivisionTehsilDAO;
import com.itgrids.dao.ITehsilConstituencyDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dto.DocumentVO;
import com.itgrids.dto.GovtMainWorkVO;
import com.itgrids.dto.GovtWorksVO;
import com.itgrids.dto.LocationAddressVO;
import com.itgrids.dto.MobileAppInputVO;
import com.itgrids.dto.MobileAppLoginVO;
import com.itgrids.dto.ResultStatus;
import com.itgrids.dto.SmallVO;
import com.itgrids.dto.WorkStatusVO;
import com.itgrids.model.District;
import com.itgrids.model.GovtWork;
import com.itgrids.model.GovtWorkDocument;
import com.itgrids.model.GovtWorkProgress;
import com.itgrids.model.GovtWorkProgressComment;
import com.itgrids.model.GovtWorkProgressDocument;
import com.itgrids.model.GovtWorkProgressTrack;
import com.itgrids.model.GovtWorkProgressUpdate;
import com.itgrids.model.GovtWorkType;
import com.itgrids.model.MobileAppUserLogin;
import com.itgrids.model.MobileAppUserType;
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
	@Autowired
	private IMobileAppUserTypeDAO mobileAppUserTypeDAO;
	@Autowired
	private IDivisionDAO divisionDAO;
	@Autowired
	private ISubDivisionDAO subDivisionDAO;
	@Autowired
	private ISubDivisionTehsilDAO subDivisionTehsilDAO;
	@Autowired
	private IGovtWorkProgressCommentDAO govtWorkProgressCommentDAO;
	
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
				
				//get user sub user types
				if(obj[5] != null && (Long)obj[5] > 0l){
					List<MobileAppUserType> mautList = mobileAppUserTypeDAO.getUserSubUserTypes((Long)obj[5]);
					if(mautList != null && mautList.size() > 0){
						for (MobileAppUserType mobileAppUserType : mautList) {
							SmallVO vo = new SmallVO();
							vo.setKey(mobileAppUserType.getMobileAppUserTypeId());
							vo.setValue(mobileAppUserType.getUserType());
							finalVO.getSubUserTypes().add(vo);
						}
					}
				}
					
				
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
					
					Map<Long,List<Long>> userLocationsMap = new HashMap<Long, List<Long>>();
					//0-locationScopeId,1-locationScope,2-locationValue
					List<Object[]> locationsObjList = mobileAppUserLocationDAO.getMobileAppuserLocations((Long)obj[0]);
					if(locationsObjList != null && locationsObjList.size() > 0){
						for (Object[] objects : locationsObjList) {
							if(userLocationsMap.get((Long)objects[0]) == null){
								List<Long> locationsList = new ArrayList<Long>(0);
								locationsList.add((Long)objects[2]);
								userLocationsMap.put((Long)objects[0],locationsList);
							}else{
								userLocationsMap.get((Long)objects[0]).add((Long)objects[2]);
							}
						}
					}
					
					if(userLocationsMap != null && userLocationsMap.size() > 0){
						List<GovtMainWorkVO> allWorksObj = new ArrayList<GovtMainWorkVO>(0);
						for (Entry<Long, List<Long>> entry : userLocationsMap.entrySet()) {
							List<GovtMainWorkVO> voList = getUsersGovtMainWorks1((Long)obj[0],workTypeId,entry.getKey(),entry.getValue());
							if(voList != null && voList.size() > 0){
								allWorksObj.addAll(voList);
							}
						}
						finalVO.setMainWorksVOList(allWorksObj);
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
		Map<Long,List<Long>> userLocationsMap = new HashMap<Long, List<Long>>();
		//0-locationScopeId,1-locationScope,2-locationValue
		List<Object[]> locationsObjList = mobileAppUserLocationDAO.getMobileAppuserLocations(userId);
		if(locationsObjList != null && locationsObjList.size() > 0){
			for (Object[] objects : locationsObjList) {
				if(userLocationsMap.get((Long)objects[0]) == null){
					List<Long> locationsList = new ArrayList<Long>(0);
					locationsList.add((Long)objects[2]);
					userLocationsMap.put((Long)objects[0],locationsList);
				}else{
					userLocationsMap.get((Long)objects[0]).add((Long)objects[2]);
				}
			}
		}
		
		if(userLocationsMap != null && userLocationsMap.size() > 0){
			for (Entry<Long, List<Long>> entry : userLocationsMap.entrySet()) {
				mainWorksVOList.addAll(getUsersGovtMainWorks1(userId,workTypeId,entry.getKey(),entry.getValue()));
			}
		}
		return mainWorksVOList;
	}
	
	public List<GovtMainWorkVO> getUsersGovtMainWorks1(Long userId,Long workTypeId,Long locationScopeId,List<Long> locationValues){
		List<GovtMainWorkVO> mainWorksVOList = new ArrayList<GovtMainWorkVO>(0);
		
		//0-workId,1-name,2-kms
		List<Object[]> objList1 = govtMainWorkDAO.getAllMainWorksForUser(workTypeId,locationScopeId,locationValues);
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
		List<Object[]> objList = govtWorkDAO.getUsersGovtMainWorks(userId,workTypeId,locationScopeId,locationValues);
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
				List<Object[]> objList12 = govtWorkProgressDAO.getCompletedMianWorkPercentage(userId,workTypeId,locationScopeId,locationValues);
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
	
	/*public void getTheLocationsOfMObileAppUser(MobileAppLoginVO finalVO,Long mobileAppUserId){
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
	}*/
	
	
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
						
						if(folderName.contains("/app/static_content/PRRWS/"))
							destPath = destPath.replace("/app/static_content/PRRWS/","");
						
						//save docuemntin document table
						Long documentId = saveDocuemnt(destPath,imageStr,govtWorksVO.getUserId());
						if(documentId != null && documentId > 0l){
							GovtWorkDocument govtWorkDocument = new GovtWorkDocument();
							govtWorkDocument.setGovtWorkId(govtWork.getGovtWorkId());
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
			
			Object completedPercentage = govtWorkProgressDAO.getWorkOverallWorkCompletedPercentage(workStatusVOList.get(0).getWorkId());
			if(completedPercentage != null){
				GovtWork govtWork = govtWorkDAO.get(workStatusVOList.get(0).getWorkId());
				govtWork.setCompletedPercentage(Double.parseDouble(completedPercentage.toString()));
				govtWork.setUpdatedBy(workStatusVOList.get(0).getUserId());
				govtWork.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				govtWorkDAO.save(govtWork);
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
								
							if(folderName.contains("/app/static_content/PRRWS/"))
								destPath = destPath.replace("/app/static_content/PRRWS/","");
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
		govtWorkProgressTrack.setGovtWorkProgressCommentId(workStatusVO.getCommentId() != null ? workStatusVO.getCommentId():null);
		govtWorkProgressTrack.setWorkLength(workStatusVO.getCurrentWorkLength() != null ? workStatusVO.getCurrentWorkLength():null);
		govtWorkProgressTrack.setUpdatedBy(workStatusVO.getUserId());
		govtWorkProgressTrack.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		//govtWorkProgressTrack.setCompletedPercentage(workStatusVO.getWorkCompletedPercentage() != null ? workStatusVO.getWorkCompletedPercentage():null);
		govtWorkProgressTrack.setCompletedPercentage(workStatusVO.getCurrentWorkCompletedPercentage() != null ? workStatusVO.getCurrentWorkCompletedPercentage():null);
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
			
			Object totalWorkLength = null;
			if(inputVO.getWorkZoneIds() != null && inputVO.getWorkZoneIds().size() > 0){
				totalWorkLength = govtWorkDAO.getOverallLength(inputVO.getWorkZoneIds());
			}else{
				if(inputVO.getUserIds() != null && inputVO.getUserIds().size() > 0 && (inputVO.getLocationScopeId() == null || inputVO.getLocationScopeId() == 0l)){
					for (Long userId : inputVO.getUserIds()) {
						//0-locationScopeId,1-locationScope,2-locationValue
						List<Object[]> objList = mobileAppUserLocationDAO.getMobileAppuserLocations(userId);
						if(objList != null && objList.size() > 0){
							inputVO.setLocationScopeId((Long)objList.get(0)[0]);
							for (Object[] objects : objList) {
								inputVO.getLocationIds().add((Long)objects[2]);
							}
						}
					}
				}
				totalWorkLength = govtWorkDAO.getOverallWork(inputVO.getWorkTypeId(),inputVO.getLocationScopeId(),inputVO.getLocationIds());
			}
			
			List<Object[]> objList = null;
			//0-statusId,1-statuName,2-date,3-length
			if(inputVO.getWorkZoneIds() != null && inputVO.getWorkZoneIds().size() > 0l){
				objList = govtWorkProgressTrackDAO.getStatusWiseDayReportForWorkZone(inputVO.getWorkZoneIds(),startDate,endDate,inputVO.getReportType());
			}else{
				objList = govtWorkProgressTrackDAO.getStatusWiseDayReport(inputVO.getWorkTypeId(),inputVO.getLocationScopeId(),inputVO.getLocationIds(),startDate,endDate,inputVO.getReportType());
			}
			
			
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
							matchedDateVO.setKms(matchedDateVO.getKms()+(objects[3] != null ? Double.parseDouble(objects[3].toString()):0.00));
						else
							matchedDateVO.setKms(matchedDateVO.getKms()+Double.parseDouble(objects[4].toString()));
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
	
	public List<GovtMainWorkVO> getWorkTypeWiseCompletedDetails(){
		List<GovtMainWorkVO> finalList = new ArrayList<GovtMainWorkVO>(0);
		try {
			Map<Long,GovtMainWorkVO> map = new HashMap<Long, GovtMainWorkVO>();
			
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
				List<Object[]> objLsit = govtWorkDAO.getWorksCountByMainType();
				if(objLsit != null && objLsit.size() > 0){
					for (Object[] objects : objLsit) {
						if(map.get((Long)objects[0]) != null){
							map.get((Long)objects[0]).setWorksCount((Long)objects[1]);
						}
					}
				}
				
				//get completed works and sum of kms group by workType
				//0-workTypeId,1-workscount,2-worklength
				List<Object[]> objList1 = govtWorkDAO.getCompletedWorksCount();
				if(objList1 != null && objList1.size() > 0){
					for (Object[] objects : objList1) {
						if(map.get(Long.parseLong(objects[0].toString())) != null){
							map.get(Long.parseLong(objects[0].toString())).setCompletedWorksCount(Long.parseLong(objects[1].toString()));
							map.get(Long.parseLong(objects[0].toString())).setCompletedKms(Double.parseDouble(objects[2].toString()));
						}
					}
				}
				
				//get praposal works count group by worktype
				//0-workTypeId,1-count,2-kms
				List<Object[]> objList2 = govtMainWorkDAO.getPraposalWorksCount(null);
				if(objList2 != null && objList2.size() > 0){
					for (Object[] objects : objList2) {
						if(map.get(Long.parseLong(objects[0].toString())) != null){
							map.get(Long.parseLong(objects[0].toString())).setWorkPraposedAreas(objects[1] != null ? Long.parseLong(objects[1].toString()):null);
						}
					}
				}
				
				/*//get workZones for workType
				//0-workTypeId,1-workZones
				List<Object[]> objList3 = govtWorkDAO.getWorkZonesCountForDateType(null);
				if(objList3 != null && objList3.size() > 0){
					for (Object[] objects : objList3) {
						if(map.get((Long)objects[0]) != null){
							map.get((Long)objects[0]).setWorkZonesCount(objects[1] != null ? (Long)objects[1]:null);
						}
					}
				}*/
				
				finalList.addAll(map.values());
				//finalList.get(0).setTotalCount(Long.parseLong(workTypeList.size()+""));
			}
			
			
		} catch (Exception e) {
			LOG.error("exception cuured at getWorkTypeWiseCompletedDetails", e);
		}
		return finalList;
	}
	
	public List<SmallVO> getSubUsers(Long userTypeId,List<Long> userIds,Long workTypeId){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			Map<Long,List<Long>> userLocationsMap = new HashMap<Long, List<Long>>();
			if(userIds != null && userIds.size() > 0){
				for (Long userId : userIds) {
					//0-locationScopeId,1-locationScope,2-locationValue
					List<Object[]> objList = mobileAppUserLocationDAO.getMobileAppuserLocations(userId);
					if(objList != null && objList.size() > 0){
						for (Object[] objects : objList) {
							if(userLocationsMap.get((Long)objects[0]) == null){
								List<Long> locationsList = new ArrayList<Long>(0);
								locationsList.add((Long)objects[2]);
								userLocationsMap.put((Long)objects[0],locationsList);
							}else{
								userLocationsMap.get((Long)objects[0]).add((Long)objects[2]);
							}
						}
					}
				}
			}
				
			if(userLocationsMap != null && userLocationsMap.size() > 0){
				for (Entry<Long, List<Long>> entry : userLocationsMap.entrySet()) {
					List<Object[]> objList = mobileAppUserLocationDAO.getSubUserDetails(userTypeId-1,entry.getKey(),entry.getValue(),workTypeId);
					if(objList != null && objList.size() > 0){
						for (Object[] objects : objList) {
							SmallVO vo = new SmallVO();
							vo.setKey((Long)objects[0]);
							vo.setValue(objects[1].toString());
							voList.add(vo);
						}
					}
				}
			}
		} catch (Exception e) {
			LOG.error("exception occured while getting the sub user details of user", e);
		}
		return voList;
	}
	
	public List<LocationAddressVO> getUsersAssignedLocations(List<Long> userIds){
		List<LocationAddressVO> voList = new ArrayList<LocationAddressVO>(0);
		try {
			if(userIds != null && userIds.size() > 0){
				Map<Long,List<Long>> locationsMap = new HashMap<Long, List<Long>>(0);
				for (Long userId : userIds) {
					//0-locationScopeId,1-locationScope,2-locationValue
					List<Object[]>  objList = mobileAppUserLocationDAO.getMobileAppuserLocations(userId);
					if(objList != null && objList.size() > 0){
						for (Object[] objects : objList) {
							if(locationsMap.get((Long)objects[0]) == null){
								List<Long> locationsList = new ArrayList<Long>(0);
								locationsList.add((Long)objects[2]);
								locationsMap.put((Long)objects[0],locationsList);
							}else{
								locationsMap.get((Long)objects[0]).add((Long)objects[2]);
							}
						}
						
					}
				}
				
				if(locationsMap != null && locationsMap.size() > 0){
					Long locationScopeId = (Long)locationsMap.keySet().toArray()[0];
					List<Long> locationIds = locationsMap.get(locationScopeId);
					
					List<Object[]> objList = null;
					if(locationScopeId == 3l){//district
						objList = districtDAO.getDistrictIdAndNameByDistrictIds(locationIds);
					}else if(locationScopeId == 4l){//const
						objList = constituencyDAO.getConstIdAndNameByConstIds(locationIds);
					}else if(locationScopeId == 5l){//mandal
						objList = tehsilDAO.getTehsilIdAndNameByIds(locationIds);
					}else if(locationScopeId == 6l){//village
						objList = panchayatDAO.getPanchayatIdAndNameByIds(locationIds);
					}else if(locationScopeId == 12l){//division
						objList = divisionDAO.getDivisionIdAndNameByIds(locationIds);
					}else if(locationScopeId == 13l){//sub-division
						objList = subDivisionDAO.getSubDivisionIdAndNameByIds(locationIds);
					}
					
					
					if(objList != null && objList.size() > 0){
						for (Object[] objects : objList) {
							LocationAddressVO vo = new LocationAddressVO();
							vo.setLocationScopeId(locationScopeId);
							vo.setLocationLevelId((Long)objects[0]);
							vo.setLocationLevelName(objects[1].toString());
							voList.add(vo);
						}
					}				
				}
			}
		} catch (Exception e) {
			LOG.error("exception raised while getting locations of users", e);
		}
		return voList;
	}
	
	public List<SmallVO> getAllWorkZonesOfLocations(Long locationScopeId,List<Long> locationIds){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			List<Object[]> objList = govtWorkDAO.getAllWorkZonesOfLocations(locationScopeId,locationIds);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					SmallVO vo = new SmallVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("exception raised while getting the work zones related to locations", e);
		}
		return voList;
	}
	
	public List<SmallVO> getImgesForMobAppDashboard(MobileAppInputVO inputVO){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			
			Date startDate=null,endDate=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				startDate = sdf.parse(inputVO.getFromDate());
				endDate = sdf.parse(inputVO.getToDate());
			}
			
			List<Object[]> objList1 = null;
			
			if((inputVO.getWorkZoneIds() == null || inputVO.getWorkZoneIds().size() == 0) && inputVO.getUserIds() != null && inputVO.getUserIds().size() > 0 && (inputVO.getLocationScopeId() == null || inputVO.getLocationScopeId() == 0l)){
				for (Long userId : inputVO.getUserIds()) {
					//0-locationScopeId,1-locationScope,2-locationValue
					List<Object[]> objList = mobileAppUserLocationDAO.getMobileAppuserLocations(userId);
					if(objList != null && objList.size() > 0){
						inputVO.setLocationScopeId((Long)objList.get(0)[0]);
						for (Object[] objects : objList) {
							inputVO.getLocationIds().add((Long)objects[2]);
						}
					}
				}
			}
			
			objList1 = govtWorkProgressDocumentDAO.getStatusWiseDocs(inputVO.getWorkZoneIds(),inputVO.getLocationScopeId(),inputVO.getLocationIds(),inputVO.getStatusId(),startDate,endDate);
			if(objList1 != null && objList1.size() > 0){
				for (Object[] objects : objList1) {
					SmallVO vo = new SmallVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured while fetching the images for mobile app dash board", e);
		}
		return voList;
	}
	
	public List<DocumentVO> getLocationStatusDayWiseKms(MobileAppInputVO inputVO){
		List<DocumentVO> voList = new ArrayList<DocumentVO>(0);
		try {
			Date startDate=null,endDate=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				startDate = sdf.parse(inputVO.getFromDate());
				endDate = sdf.parse(inputVO.getToDate());
			}
			
			Map<String,DocumentVO> map = new LinkedHashMap<String, DocumentVO>();
			if(startDate != null && endDate != null){
				List<String> dateList = commonMethodsUtilService.getBetweenDatesInString(startDate, endDate);
				if(dateList != null && dateList.size() > 0){
					for (String string : dateList) {
						DocumentVO vo = new DocumentVO();
						vo.setInsertedTime(string);
						map.put(string, vo);
					}
				}
			}
			
			
			List<Object[]> objList = govtWorkProgressTrackDAO.getLocationStatusDayWiseKms(startDate,endDate,inputVO.getStatusId(),inputVO.getWorkTypeId(),inputVO.getDistrictId(),
					inputVO.getDivisonId(),inputVO.getSubDivisonId(),inputVO.getMandalId());
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					if(map.get(objects[0].toString()) != null){
						map.get(objects[0].toString()).setKms(Double.parseDouble(objects[1].toString()));
					}
				}
				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("exception occured while getting location status day wise kms", e);
		}
		return voList;
	}
	
	public List<DocumentVO> getLocationLevelStatusDayWiseKms(MobileAppInputVO inputVO){
		List<DocumentVO> finalList = new ArrayList<DocumentVO>(0);
		try {
			Date startDate=null,endDate=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				startDate = sdf.parse(inputVO.getFromDate());
				endDate = sdf.parse(inputVO.getToDate());
			}
			
			List<String> dateList = null;
			Map<Long,DocumentVO> map = new HashMap<Long, DocumentVO>();//locationId,lovationVO
			if(startDate != null && endDate != null){
				dateList = commonMethodsUtilService.getBetweenDatesInString(startDate, endDate);
			}
			//0-date,1-sum,2-locationId
			List<Object[]> objList =  govtWorkProgressTrackDAO.getLocationLevelStatusDayWiseKms(startDate,endDate,inputVO.getStatusId(),inputVO.getWorkTypeId(),inputVO.getDistrictId(),
					inputVO.getDivisonId(),inputVO.getSubDivisonId(),inputVO.getMandalId(),inputVO.getLocationLevelId());
			
			if(objList != null && objList.size() > 0){
				List<Long> locationIds = new ArrayList<Long>(0);
				for (Object[] objects : objList) {
					if(map.get(Long.parseLong(objects[2].toString())) == null){
						locationIds.add(Long.parseLong(objects[2].toString()));
						DocumentVO vo = new DocumentVO();
						vo.setDocumentId(Long.parseLong(objects[2].toString()));
						
						getDates(vo.getList(),dateList);
						DocumentVO matchedDateVO = getMatchedDateVo(vo.getList(),objects[0].toString());
						if(matchedDateVO != null)
							matchedDateVO.setKms(Double.parseDouble(objects[1].toString()));
						
						map.put(Long.parseLong(objects[2].toString()),vo);
					}else{
						DocumentVO matchedDateVO = getMatchedDateVo(map.get(Long.parseLong(objects[2].toString())).getList(),objects[0].toString());
						if(matchedDateVO != null)
							matchedDateVO.setKms(Double.parseDouble(objects[1].toString()));
					}
				}
				
				if(locationIds != null && locationIds.size() > 0){
					List<Object[]> locationsObjList = null;
					if(inputVO.getLocationLevelId() == 3l){
						locationsObjList = districtDAO.getDistrictIdAndNameByDistrictIds(locationIds);
					}else if(inputVO.getLocationLevelId() == 4l){
						locationsObjList = constituencyDAO.getConstIdAndNameByConstIds(locationIds);
					}else if(inputVO.getLocationLevelId() == 5l){
						locationsObjList = tehsilDAO.getTehsilIdAndNameByIds(locationIds);
					}else if(inputVO.getLocationLevelId() == 6l){
						locationsObjList = panchayatDAO.getPanchayatIdAndNameByIds(locationIds);
					}else if(inputVO.getLocationLevelId() == 12l){
						locationsObjList = divisionDAO.getDivisionIdAndNameByIds(locationIds);
					}else if(inputVO.getLocationLevelId() == 13l){
						locationsObjList = subDivisionDAO.getSubDivisionIdAndNameByIds(locationIds);
					} 
					
					if(locationsObjList != null && locationsObjList.size() > 0){
						for (Object[] objects : locationsObjList) {
							if(map.get((Long)objects[0]) != null)
								map.get((Long)objects[0]).setDocumentName(objects[1].toString());
						}
					}
				}
				
				//calculate totals and %'s
				if(map != null && map.size() > 0){
					Map<Long,Long> usersMap = new HashMap<Long, Long>();//locationId,userId
					if(inputVO.getLocationLevelId() == 3l){//distLevel
						//0-locationValue,1-userId
						List<Object[]> objList12 = mobileAppUserLocationDAO.getAllEngineers(4l);
						if(objList12 != null && objList12.size() > 0){
							for (Object[] obj : objList12) {
								usersMap.put((Long)obj[0], (Long)obj[1]);
							}
						}
					}
					
					for (Entry<Long, DocumentVO> entry : map.entrySet()) {
						DocumentVO locationVO = entry.getValue();
						locationVO.setUserId(usersMap.get(entry.getKey()));
						if(locationVO != null && locationVO.getList() != null && locationVO.getList().size() > 0){
							Double totalKms = 0.00;
							//calculate total kms
							for (DocumentVO inVO : locationVO.getList()) {
								totalKms = totalKms+(inVO.getKms() != null ? inVO.getKms():0.00);
							}
							
							locationVO.setKms(totalKms);
							
							//calculate %'s
							if(totalKms > 0.00){
								for (DocumentVO inVO : locationVO.getList()) {
									inVO.setCompletedPercentage((inVO.getKms()*100.0)/totalKms);
								}
							}
						}
					}
				}
			}
			finalList.addAll(map.values());
			
		} catch (Exception e) {
			LOG.error("Exception occured while fetching getLocationLevelStatusDayWiseKms ", e);
		}
		return finalList;
	}
	
	public DocumentVO getMatchedDateVo(List<DocumentVO> volIst,String date){
		if(volIst != null && volIst.size() > 0){
			for (DocumentVO documentVO : volIst) {
				if(documentVO.getInsertedTime().equals(date))
					return documentVO;
			}
		}
		return null;
	}
	
	public void getDates(List<DocumentVO> voList,List<String> dateList){
		if(dateList != null && dateList.size() > 0){
			for (String string : dateList) {
				DocumentVO vo = new DocumentVO();
				vo.setInsertedTime(string);
				voList.add(vo);
			}
		}
	}
	
	//inputs - startDate,endDate,WorkTypeId,locationscopeId,locationLevelId
	public List<DocumentVO> getLocationLevelSubDayWiseKms(MobileAppInputVO inputVO){
		List<DocumentVO> voList = new ArrayList<DocumentVO>(0);
		try {
			Date startDate=null,endDate=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				startDate = sdf.parse(inputVO.getFromDate());
				endDate = sdf.parse(inputVO.getToDate());
			}
			
			List<String> dateList = null;
			Map<Long,DocumentVO> map = new HashMap<Long, DocumentVO>();//locationId,lovationVO
			if(startDate != null && endDate != null){
				dateList = commonMethodsUtilService.getBetweenDatesInString(startDate, endDate);
			}
			
			//0-statusId,1-status,2-date,3-count
			List<Object[]> objList =  govtWorkProgressTrackDAO.getLocationLevelSubDayWiseKms(startDate,endDate,inputVO.getWorkTypeId(),inputVO.getLocationScopeId(),inputVO.getLocationLevelId());
			
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					if(map.get(Long.parseLong(objects[0].toString())) == null){
						DocumentVO vo = new DocumentVO();
						vo.setDocumentId(Long.parseLong(objects[0].toString()));
						vo.setDocumentName(objects[1].toString());
						
						getDates(vo.getList(), dateList);
						DocumentVO matchedDateVO = getMatchedDateVo(vo.getList(), objects[2].toString());
						if(matchedDateVO != null){
							matchedDateVO.setKms(Double.parseDouble(objects[3].toString()));
						}
						
						map.put(Long.parseLong(objects[0].toString()),vo);
					}else{
						DocumentVO matchedDateVO = getMatchedDateVo(map.get(Long.parseLong(objects[3].toString())).getList(),objects[0].toString());
						if(matchedDateVO != null)
							matchedDateVO.setKms(Double.parseDouble(objects[3].toString()));
					}
				}
				
				//calculating total & %'s
				if(map != null && map.size() > 0){
					for (Entry<Long, DocumentVO> entry : map.entrySet()) {
						DocumentVO statusVO = entry.getValue();
						if(statusVO != null && statusVO.getList() != null && statusVO.getList().size() > 0){
							Double totalKms = 0.00;
							//calculate total kms
							for (DocumentVO inVO : statusVO.getList()) {
								totalKms = totalKms+(inVO.getKms() != null ? inVO.getKms():0.00);
							}
							
							statusVO.setKms(totalKms);
							
							//calculate %'s
							if(totalKms > 0.00){
								for (DocumentVO inVO : statusVO.getList()) {
									inVO.setCompletedPercentage((inVO.getKms()*100.0)/totalKms);
								}
							}
						}
					}
					voList.addAll(map.values());
				}
			}
			
		} catch (Exception e) {
			LOG.error("exception raised at getLocationLevelSubDayWiseKms", e);
		}
		return voList;
	}
	
	public GovtMainWorkVO getStateLevelOverAllDetails(Long workTypeId){
		GovtMainWorkVO finalVO = new GovtMainWorkVO();
		try {
			//get praposal works count group by worktype
			//0-workTypeId,1-count,2-kms,3-estimationCost
			List<Object[]> objList = govtMainWorkDAO.getPraposalWorksCount(workTypeId);
			if(objList != null && objList.size() > 0){
				finalVO.setWorkPraposedAreas(Long.parseLong(objList.get(0)[1].toString()));
				finalVO.setTotalKms(Double.parseDouble(objList.get(0)[2].toString()));
				finalVO.setEstimationCost(Double.parseDouble(objList.get(0)[3].toString()));
			}
				
			//get workZones for workType
			//0-workTypeId,1-workZones
			List<Object[]> objList1 = govtWorkDAO.getWorkZonesCountForDateType(workTypeId);
			if(objList1 != null && objList1.size() > 0)
				finalVO.setWorksCount((Long)objList1.get(0)[1]);
			
			//get work completed kms for workType
			Object[] completedDetails = govtWorkProgressDAO.getWorkCompletedKms(workTypeId);
			if(completedDetails != null && completedDetails.length > 0){
				finalVO.setCompletedWorksCount(Long.parseLong(completedDetails[0].toString()));
				finalVO.setCompletedKms(Double.parseDouble(completedDetails[1].toString()));
			}
			
			if(finalVO.getWorksCount() != null && finalVO.getWorksCount() > 0l && finalVO.getCompletedWorksCount() != null && finalVO.getCompletedWorksCount() > 0){
				finalVO.setCompletedPercentage((finalVO.getCompletedWorksCount()*100.00)/finalVO.getWorksCount());
			}
		} catch (Exception e) {
			LOG.error("Excetion occured while getting the getStateLevelOverAllDetails",e);
		}
		return finalVO;
	}
	
	public List<DocumentVO> getRecentWorkDocuments(Long workTypeId){
		List<DocumentVO> voList = new ArrayList<DocumentVO>(0);
		try {
			//0-panchayatId,1-mandalId,2-docId,3-path,4-insertedDate
			List<Object[]> objList = govtWorkProgressDocumentDAO.getRecentWorkDocuments(workTypeId);
			List<Long> panchayatIds = new ArrayList<Long>(0);
			List<Long> mandalIds = new ArrayList<Long>(0);
			
			if(objList != null && objList.size() > 0){
				for (Object[] obj : objList) {
					if(obj[0] != null)
						panchayatIds.add((Long)obj[0]);
					if(obj[1] != null)
						mandalIds.add((Long)obj[1]);
				}
				
				List<Object[]> panchObjList = null;
				if(panchayatIds.size() > 0)
					panchObjList = panchayatDAO.getPanchayatIdAndNameByIds(panchayatIds);
				
				List<Object[]> manObjList = null;
				if(mandalIds.size() > 0)
					manObjList = tehsilDAO.getTehsilIdAndNameByIds(mandalIds);
				
				Map<Long,String> panchNamesMap = new HashMap<Long, String>();
				Map<Long,String> manNamesMap = new HashMap<Long, String>();
				
				if(panchObjList != null && panchObjList.size() > 0){
					for (Object[] objects : panchObjList) {
						panchNamesMap.put((Long)objects[0], objects[1].toString());
					}
				}
				
				if(manObjList != null && manObjList.size() > 0){
					for (Object[] objects : manObjList) {
						manNamesMap.put((Long)objects[0], objects[1].toString());
					}
				}
				
				for (Object[] obj : objList) {
					DocumentVO vo = new DocumentVO();
					if(obj[0] != null){
						vo.setPanchayatId((Long)obj[0]);
						vo.setPanchayatName(panchNamesMap.get((Long)obj[0]) != null ? panchNamesMap.get((Long)obj[0]):"");
					}
					if(obj[1] != null){
						vo.setMandalId((Long)obj[1]);
						vo.setMandalName(manNamesMap.get((Long)obj[1]) != null ? manNamesMap.get((Long)obj[1]):"");
					}
					
					vo.setDocumentId((Long)obj[2]);
					vo.setPath(obj[3].toString());
					vo.setInsertedTime(obj[4].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured while fetching the recent docs", e);
		}
		return voList;
	}
	
	public List<GovtWorksVO> getStatusWiseWorksAndKms(Long workTypeId){
		List<GovtWorksVO> voList = new ArrayList<GovtWorksVO>(0);
		try {
			List<Object[]> allStatusObjList = govtWorkStatusDAO.getAllStatusOfWorkType(workTypeId);
			if(allStatusObjList != null && allStatusObjList.size() > 0){
				Map<Long,GovtWorksVO> map = new HashMap<Long, GovtWorksVO>();
				for (Object[] objects : allStatusObjList) {
					if(map.get((Long)objects[2]) == null){
						GovtWorksVO vo = new GovtWorksVO();
						vo.setStatusId((Long)objects[2]);
						vo.setStatus(objects[3].toString());
						map.put((Long)objects[2],vo);
					}
				}
				
				//get all works and kms status wise
				//0-statusId,1-works count,2-kms
				List<Object[]> statusWiseDetailsObjList = govtWorkProgressDAO.getStatusWiseAllWorksAndKms(workTypeId);
				Object[] overallWorksKmsObjList = govtWorkDAO.getOverallWorksLengthOfWorkType(workTypeId);
				
				if(statusWiseDetailsObjList != null && statusWiseDetailsObjList.size() > 0){
					for (Object[] object : statusWiseDetailsObjList) {
						if(map.get(Long.parseLong(object[0].toString())) != null){
							map.get(Long.parseLong(object[0].toString())).setStatusWorks(Long.parseLong(object[1].toString()));
							map.get(Long.parseLong(object[0].toString())).setStatusKms(Double.parseDouble(object[2].toString()));
						}
					}
				}
				
				if(overallWorksKmsObjList != null && overallWorksKmsObjList.length > 0 && map.size() > 0){
					for (Entry<Long, GovtWorksVO> entry : map.entrySet()) {
						if(overallWorksKmsObjList[0] != null && Long.parseLong(overallWorksKmsObjList[0].toString()) > 0l){
							entry.getValue().setTotalWorks(Long.parseLong(overallWorksKmsObjList[0].toString()));
							entry.getValue().setCompletedPercentage((entry.getValue().getStatusWorks()*100.00)/entry.getValue().getTotalWorks());
						}
						
						if(overallWorksKmsObjList[1] != null && Double.parseDouble(overallWorksKmsObjList[1].toString()) > 0.00){
							entry.getValue().setTotalKms(Double.parseDouble(overallWorksKmsObjList[1].toString()));
							entry.getValue().setCompletedKmsPercentage((entry.getValue().getStatusKms()*100.00)/entry.getValue().getTotalKms());
						}
					}
				}
				
				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("Exception occured while getting the status wise works and kilometers", e);
		}
		return voList;
	}
	
	public List<GovtWorksVO> getLocationWiseOverview(MobileAppInputVO inputVO){
		List<GovtWorksVO> voList = new ArrayList<GovtWorksVO>(0);
		try {
			//0-statusId,1-statusName,2-kms,3-locationId(,4-workZoneId,5-workzonename)
			List<Object[]> objList = govtWorkProgressDAO.getLocationWiseOverview(inputVO.getWorkTypeId(),inputVO.getLocationScopeId(),inputVO.getDistrictId(),inputVO.getDivisonId(),inputVO.getSubDivisonId(),inputVO.getMandalId(),inputVO.getWorkZone());
			
			if(objList != null && objList.size() > 0){
				//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-govtWorkStatus
				List<Object[]> allStatusObjList = govtWorkStatusDAO.getAllStatusOfWorkType(inputVO.getWorkTypeId());
					
				Map<Long,GovtWorksVO> finalMap = new HashMap<Long, GovtWorksVO>();
				List<Long> locationIds = new ArrayList<Long>(0);//for location Names
				
				for (Object[] obj : objList) {
					locationIds.add(Long.parseLong(obj[3].toString()));
					
					GovtWorksVO matchedLocationVo = finalMap.get(Long.parseLong(obj[3].toString()));
					if(matchedLocationVo == null){
						matchedLocationVo = new GovtWorksVO();
						matchedLocationVo.setLocationValue(Long.parseLong(obj[3].toString()));
						finalMap.put(Long.parseLong(obj[3].toString()),matchedLocationVo);
						
						matchedLocationVo = finalMap.get(Long.parseLong(obj[3].toString()));
					}
					
					//0-statusId,1-statusName,2-kms,3-locationId(,4-workZoneId,5-workzonename)
					if(inputVO.getWorkZone() != null && inputVO.getWorkZone().equals("Y")){
						GovtWorksVO matchedWorkVO = getmatchedWorkVO(matchedLocationVo.getWorksList(), Long.parseLong(obj[4].toString()));
						if(matchedWorkVO == null){
							matchedWorkVO = new GovtWorksVO();
							matchedWorkVO.setWorkId(Long.parseLong(obj[4].toString()));
							matchedWorkVO.setWorkName(obj[5].toString());
							
							if(allStatusObjList != null && allStatusObjList.size() > 0){
								matchedWorkVO.setStatusList(setStatusList(allStatusObjList));
							}
							
							matchedLocationVo.getWorksList().add(matchedWorkVO);
							matchedWorkVO = getmatchedWorkVO(matchedLocationVo.getWorksList(), Long.parseLong(obj[4].toString()));
						}
						
						//set status
						GovtWorksVO matchedStatusVO = getMatchedWorkStatusVO1(matchedWorkVO.getStatusList(), Long.parseLong(obj[0].toString()));
						if(matchedStatusVO != null){
							matchedStatusVO.setTotalKms(Double.parseDouble(obj[2].toString()));
						}
					}else{
						if(matchedLocationVo.getStatusList().size() == 0){
							matchedLocationVo.setStatusList(setStatusList(allStatusObjList));
						}
						
						GovtWorksVO matchedStatusVO = getMatchedWorkStatusVO1(matchedLocationVo.getStatusList(), Long.parseLong(obj[0].toString()));
						if(matchedStatusVO != null){
							matchedStatusVO.setTotalKms(Double.parseDouble(obj[2].toString()));
						}
					}
				}
				
				if(finalMap != null && finalMap.size() > 0 && locationIds.size() > 0){
					List<Object[]> locationNamesObjList = null;
					if(inputVO.getLocationScopeId() == 3l){
						locationNamesObjList = districtDAO.getDistrictIdAndNameByDistrictIds(locationIds);
					}else if(inputVO.getLocationScopeId() == 12l){
						locationNamesObjList = divisionDAO.getDivisionIdAndNameByIds(locationIds);
					}else if(inputVO.getLocationScopeId() == 13l){
						locationNamesObjList = subDivisionDAO.getSubDivisionIdAndNameByIds(locationIds);
					}else if(inputVO.getLocationScopeId() == 5l){
						locationNamesObjList = tehsilDAO.getTehsilIdAndNameByIds(locationIds);
					}else if(inputVO.getLocationScopeId() == 6l){
						locationNamesObjList = panchayatDAO.getPanchayatIdAndNameByIds(locationIds);
					}
					
					Map<Long,String> locationNamesMap = new HashMap<Long, String>();
					if(locationNamesObjList != null && locationNamesObjList.size() > 0){
						for (Object[] objects : locationNamesObjList) {
							locationNamesMap.put((Long)objects[0], objects[1].toString());
						}
					}
					
					for (Entry<Long, GovtWorksVO> entry : finalMap.entrySet()) {
						entry.getValue().setLocation(locationNamesMap.get(entry.getKey()));
						//calculation totals and %'s
						if(inputVO.getWorkZone() != null && inputVO.getWorkZone().equals("Y")){
							if(entry.getValue().getWorksList() != null && entry.getValue().getWorksList().size() > 0){
								for (GovtWorksVO worksVO : entry.getValue().getWorksList()) {
									if(worksVO.getStatusList() != null && worksVO.getStatusList().size() > 0){
										Double totalLength = 0.00;
										for (GovtWorksVO statusVO : worksVO.getStatusList()) {
											totalLength = totalLength+statusVO.getTotalKms();
										}
										
										if(totalLength > 0.00){
											worksVO.setTotalKms(totalLength);
											for (GovtWorksVO statusVO : worksVO.getStatusList()) {
												statusVO.setCompletedPercentage((statusVO.getTotalKms()*100.00)/totalLength);
											}
										}
									}
								}
							}
						}else{
							if(entry.getValue().getStatusList() != null && entry.getValue().getStatusList().size() > 0){
								Double totalLength = 0.00;
								for (GovtWorksVO statusVO : entry.getValue().getStatusList()) {
									totalLength = totalLength+statusVO.getTotalKms();
								}
								
								if(totalLength > 0.00){
									entry.getValue().setTotalKms(totalLength);
									for (GovtWorksVO statusVO : entry.getValue().getStatusList()) {
										statusVO.setCompletedPercentage((statusVO.getTotalKms()*100.00)/totalLength);
									}
								}
							}
						}
					}
				}
				if(finalMap.size() > 0)
					voList.addAll(finalMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("exception occured at getLOCATIONWISEOVERVIEW", e);
		}
		return voList;
	}
	
	public GovtWorksVO getMatchedWorkStatusVO1(List<GovtWorksVO> voList, Long statusId){
		if(voList != null && voList.size() > 0){
			for (GovtWorksVO govtWorksVO : voList) {
				if(govtWorksVO.getStatusId().equals(statusId))
					return govtWorksVO;
			}
		}
		return null;
	}
	
	public List<GovtWorksVO> setStatusList(List<Object[]> statusObjList){
		List<GovtWorksVO> voList = new ArrayList<GovtWorksVO>(0);
		if(statusObjList != null && statusObjList.size() > 0){
			for (Object[] objects : statusObjList) {
				GovtWorksVO vo = new GovtWorksVO();
				vo.setStatusId((Long)objects[2]);
				vo.setStatus(objects[3].toString());
				voList.add(vo);
			}
		}
		return voList;
	}
	
	public GovtWorksVO getmatchedWorkVO(List<GovtWorksVO> voList,Long workId){
		if(voList != null && voList.size() > 0){
			for (GovtWorksVO govtWorksVO : voList) {
				if(govtWorksVO.getWorkId().equals(workId))
					return govtWorksVO;
			}
		}
		return null;
	}
	
	public GovtMainWorkVO getLocationLevelWiseOverviewDetails(Long locationScopeId,Long locationValue,Long workTypeId){
		GovtMainWorkVO finalVo = new GovtMainWorkVO();
		try {
			//0-workcount,1-worklength
			Object[] objArr = govtWorkDAO.getAllworkZonesOfLocation(locationScopeId,locationValue,workTypeId);
			Object estimationCostObj = govtMainWorkDAO.getEstimationCosrOfLocationBasedMainWorks(locationScopeId,locationValue,workTypeId);
			//0-completedworkcount,1-completedworklength
			Object[] completedObjArr = govtWorkDAO.getCompletedWorksDetailsOfLocation(locationScopeId,locationValue,workTypeId);
			
			if(objArr != null && objArr.length > 0){
				finalVo.setWorksCount(Long.parseLong(objArr[0].toString()));
				finalVo.setTotalKms(Double.parseDouble(objArr[1].toString()));
			}
			if(estimationCostObj != null)
				finalVo.setEstimationCost(Double.parseDouble(estimationCostObj.toString()));
			if(completedObjArr != null && completedObjArr.length > 0){
				finalVo.setCompletedWorksCount(Long.parseLong(completedObjArr[0].toString()));
				finalVo.setCompletedKms(Double.parseDouble(completedObjArr[1].toString()));
			}
		} catch (Exception e) {
			LOG.error("exception occured at getLocationLevelWiseOverviewDetails", e);
		}
		return finalVo;
	}
	
	public List<WorkStatusVO> getLocationLevelStatusWiseOverviewDetails(Long locationScopeId,Long locationValue,Long workTypeId){
		List<WorkStatusVO> resultList = new ArrayList<WorkStatusVO>(0);
		try {
			//0-workcount,1-worklength
			Object[] objArr = govtWorkDAO.getAllworkZonesOfLocation(locationScopeId,locationValue,workTypeId);
			Double totalWorkLength = 0.00;
			if(objArr != null && objArr.length > 0)
				totalWorkLength = Double.parseDouble(objArr[1].toString());
			
			//0-statusId,1-length
			List<Object[]> objList = govtWorkProgressDAO.getLocationLevelStatusWiseOverviewDetails(locationScopeId,locationValue,workTypeId);
			Map<Long,Double> statusLengthMap = new HashMap<Long, Double>();
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					statusLengthMap.put(Long.parseLong(objects[0].toString()), Double.parseDouble(objects[1].toString()));
				}
			}
			
			//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-govtWorkStatus
			List<Object[]> allstatusObjList = govtWorkStatusDAO.getAllStatusOfWorkType(workTypeId);
			if(allstatusObjList != null && allstatusObjList.size() > 0){
				for (Object[] objects : allstatusObjList) {
					WorkStatusVO vo = new WorkStatusVO();
					vo.setGovtWorkStatusId((Long)objects[2]);
					vo.setGovtWorkStatus(objects[3].toString());
					vo.setWorkLenght(statusLengthMap.get((Long)objects[2]));
					vo.setTotalLenght(totalWorkLength);
					
					if(totalWorkLength>0 && statusLengthMap.get((Long)objects[2]) != null){
						vo.setWorkCompletedPercentage((statusLengthMap.get((Long)objects[2])*100.00)/totalWorkLength);
					}
					resultList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("exception occured at getLocationLevelStatusWiseOverviewDetails", e);
		}
		return resultList;
	}
	
	public List<WorkStatusVO> getWorkZoneStatusWiseKms(Long locationScopeId,Long locationValue,Long workTypeId){
		List<WorkStatusVO> resultList = new ArrayList<WorkStatusVO>(0);
		try {
			//0-workZoneId,1-zonename,2-statusId,3-length
			List<Object[]> objList = govtWorkProgressDAO.getWorkZoneStatusWiseKms(locationScopeId,locationValue,workTypeId);
			Map<Long,WorkStatusVO> workZonesMap = new HashMap<Long, WorkStatusVO>();
			if(objList != null && objList.size() > 0){
				//0-statusTypeId,1-statusType,2-govtWorkStatusId,3-govtWorkStatus
				List<Object[]> allstatusObjList = govtWorkStatusDAO.getAllStatusOfWorkType(workTypeId);
				
				for (Object[] objects : objList) {
					WorkStatusVO vo = workZonesMap.get(Long.parseLong(objects[0].toString()));
					if(vo == null){
						vo = new WorkStatusVO();
						vo.setGovtWorkStatusId(Long.parseLong(objects[0].toString()));//work zone id
						vo.setGovtWorkStatus(objects[1].toString());//work zone name
						
						vo.setWorkStatusVOList(getStatusVOList(allstatusObjList));
						
						WorkStatusVO matchedStatusVO = getMatchedWorkStatusVO(vo.getWorkStatusVOList(), Long.parseLong(objects[2].toString()));
						if(matchedStatusVO != null)
							matchedStatusVO.setWorkLenght(Double.parseDouble(objects[3].toString()));
						
						workZonesMap.put(Long.parseLong(objects[0].toString()),vo);
					}else{
						WorkStatusVO matchedStatusVO = getMatchedWorkStatusVO(vo.getWorkStatusVOList(), Long.parseLong(objects[2].toString()));
						if(matchedStatusVO != null)
							matchedStatusVO.setWorkLenght(Double.parseDouble(objects[3].toString()));
					}
				}
				
				//calculate totals & %'s
				if(workZonesMap.size() > 0){
					for (Entry<Long, WorkStatusVO> entry : workZonesMap.entrySet()) {
						if(entry.getValue().getWorkStatusVOList() != null && entry.getValue().getWorkStatusVOList().size() > 0){
							Double totalLength = 0.00;
							for (WorkStatusVO inVO : entry.getValue().getWorkStatusVOList()) {
								totalLength = totalLength+inVO.getWorkLenght();
							}
							
							if(totalLength != null && totalLength > 0.00){
								entry.getValue().setWorkLenght(totalLength);
								for (WorkStatusVO inVO : entry.getValue().getWorkStatusVOList()) {
									inVO.setWorkCompletedPercentage((inVO.getWorkLenght()*100.00)/totalLength);
								}
							}
						}
					}
				}
				
				if(workZonesMap != null && workZonesMap.size() > 0)
					resultList.addAll(workZonesMap.values());
			}
			
		} catch (Exception e) {
			LOG.error("exception occured at getWorkZoneStatusWiseKms", e);
		}
		return resultList;
	}
	
	public List<WorkStatusVO> getStatusVOList(List<Object[]> allstatusObjList){
		List<WorkStatusVO> voList = new ArrayList<WorkStatusVO>(0);
		if(allstatusObjList != null && allstatusObjList.size() >0){
			for (Object[] objects : allstatusObjList) {
				WorkStatusVO vo = new WorkStatusVO();
				vo.setGovtWorkStatusId((Long)objects[2]);
				vo.setGovtWorkStatus(objects[3].toString());
				voList.add(vo);
			}
		}
		return voList;
	}
	public GovtWorksVO getWorkZoneMainOverview(Long govtWorkId){
		GovtWorksVO finalVo = new GovtWorksVO();
		try {
			Object[] objArr = govtWorkDAO.getWorkZoneMainOverview(govtWorkId);
			Object completedKm = govtWorkProgressDAO.getWorkOverallWorkCompletedKms(govtWorkId);
			
			if(objArr !=null){
				finalVo.setWorkLenght((Double) objArr[0]);
				finalVo.setFundAllocated((Double)objArr[0]);
			}
			finalVo.setCompletedLength(completedKm !=null ?(Double) completedKm:0.00);
		} catch (Exception e) {
			LOG.error("exception occured at getWorkZoneMainOverview", e);
		}
		return finalVo;
	}
	public List<WorkStatusVO> getWorkZoneStatusDetailsInfo(Long govtWorkId,Long workTypeId){
		List<WorkStatusVO> finalVOList = new ArrayList<WorkStatusVO>(0);
		try {
			List<Object[]> allStatusLsit = govtWorkStatusDAO.getAllStatusOfWorkType(workTypeId);
			finalVOList = getStatusVOList(allStatusLsit);
			
			//0-statusId,1-workLength,1-Perc,3-date
			List<Object[]>  statusList = govtWorkProgressDAO.getWorkZoneStatusDetailsInfo(govtWorkId);
			if(finalVOList !=null && finalVOList.size() >0){
					if(statusList !=null && statusList.size() >0){
						for (Object[] objects : statusList) {
							WorkStatusVO vo = getMatchedWorkStatusVO(finalVOList,(Long)objects[0]);
							if(vo !=null){
								vo.setWorkLenght(objects[1] != null ? (Double)objects[1]:0.00);
								vo.setCurrentWorkLength(objects[2] != null ? (Double)objects[2]:0.00);
								vo.setDate(objects[3] != null ? objects[3].toString():"");
								
							}
						}
					}
			 }
		} catch (Exception e) {
			LOG.error("exception occured at getWorkZoneStatusDetailsInfo", e);
		}
		return finalVOList;
	}
	public List<DocumentVO> getWorkZoneDocumentDetailsInfo(Long govtWorkId){
		List<DocumentVO> finalVOList = new ArrayList<DocumentVO>(0);
		try {
			Date startDate = null,endDate = null;
			startDate = dateUtilService.getDateBeforeNDays(15);
			endDate = dateUtilService.getCurrentDateAndTime();
			
			//0-docId,1-path,2-date
			List<Object[]>  documentList = govtWorkProgressDocumentDAO.getWorkZoneDocumentDetailsInfo(startDate,endDate,govtWorkId);
		
			if(documentList !=null &&documentList.size() >0){
				for (Object[] obj : documentList) {
					DocumentVO vo = new DocumentVO();
					
					vo.setDocumentId(obj[0] != null ? (Long)obj[0]:0l);
					vo.setPath(obj[1] != null ? obj[1].toString():"");
					vo.setInsertedTime(obj[2] != null ? obj[2].toString():"");
					
					finalVOList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("exception occured at getWorkZoneDocumentDetailsInfo", e);
		}
		return finalVOList;
	}
	public List<GovtWorksVO> getWorkZoneWorkStategsDetailsInfo(String startDateStr,String endDateStr,Long govtWorkId,Long statusId){
		List<GovtWorksVO> finalVOList = new ArrayList<GovtWorksVO>(0);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			Date startDate = null,endDate = null;
			
			if(startDateStr != null && endDateStr != null){
				startDate = sdf.parse(startDateStr);
				endDate = sdf.parse(endDateStr);
			}
			
			//0-date,1-length
		List<Object[]>  dateList = govtWorkProgressTrackDAO.getWorkZoneWorkStategsDetailsInfo(startDate,endDate,govtWorkId,statusId);
			if(dateList !=null && dateList.size() >0){
				for (Object[] obj : dateList){
					GovtWorksVO vo = new GovtWorksVO();
					
					vo.setDate(obj[0] != null ? obj[0].toString():"");
					vo.setWorkLenght(obj[0] != null ? Double.parseDouble(obj[0].toString()):0.00);
					
					finalVOList.add(vo);
				}
			}
		
		} catch (Exception e) {
			LOG.error("exception occured at getWorkZoneWorkStategsDetailsInfo", e);
		}
		return finalVOList;
	}
	public void getStatusTemplate(List<GovtWorksVO> finalList,List<Object[]> objList){
		
	}
	
	public List<DocumentVO> getLocationOverviewStatusDayWiseKms(String fromDate,String toDate,Long locationScopeId,Long locationValue,Long workTypeId){
		List<DocumentVO> finalList = new ArrayList<DocumentVO>(0);
		try {
			Date startDate=null,endDate=null;
			if(fromDate != null && toDate != null){
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				startDate = sdf.parse(fromDate);
				endDate = sdf.parse(toDate);		
			}
			
			Map<String,DocumentVO> map = new LinkedHashMap<String, DocumentVO>();
			if(startDate != null && endDate != null){
				List<String> dateList = commonMethodsUtilService.getBetweenDatesInString(startDate, endDate);
				if(dateList != null && dateList.size() > 0){
					for (String string : dateList) {
						DocumentVO vo = new DocumentVO();
						vo.setInsertedTime(string);
						map.put(string, vo);
					}
				}
			}
			
			
			List<Object[]> objList = govtWorkProgressTrackDAO.getLocationOverviewStatusDayWiseKms(startDate,endDate,locationScopeId,locationValue,workTypeId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					if(map.get(objects[0].toString()) != null)
						map.get(objects[0].toString()).setKms(Double.parseDouble(objects[1].toString()));
				}
				finalList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("exception occured at getLocationOverviewStatusDayWiseKms", e);
		}
		return finalList;
	}	
	
	public List<SmallVO> getAllDistrictsOfAp(){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			List<District> objList = districtDAO.getAll();
			if(objList != null && objList.size() > 0){
				for (District district : objList) {
					SmallVO vo = new SmallVO();
					vo.setKey(district.getDistrictId());
					vo.setValue(district.getDistrictName());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getAllDistrictsOfAp", e);
		}
		return voList;
	}
	
	public List<SmallVO> getDivisionsOfDistrict(Long districtId){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			List<Object[]> objList = divisionDAO.getDivisionsOfDistrict(districtId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					SmallVO vo = new SmallVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getDivisionsOfDistrict", e);
		}
		return voList;
	}
	
	public List<SmallVO> getSubDivisionsOfDivision(Long divisionId){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			List<Object[]> objList = subDivisionDAO.getSubDivisionsOfDivision(divisionId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					SmallVO vo = new SmallVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getSubDivisionsOfDivision", e);
		}
		return voList;
	}
	
	public List<SmallVO> getTehsilsOfSubDivision(Long subDivisonId){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			List<Object[]> objList = subDivisionTehsilDAO.getTehsilsOfSubDivision(subDivisonId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					SmallVO vo = new SmallVO();
					vo.setKey((Long)objects[0]);
					vo.setValue(objects[1].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getTehsilsOfSubDivision", e);
		}
		return voList;
	}
	
	public List<SmallVO> getAllStatusOfWorkType(Long workTypeId){
		List<SmallVO> voList = new ArrayList<SmallVO>(0);
		try {
			List<Object[]> objList = govtWorkStatusDAO.getAllStatusOfWorkType(workTypeId);
			if(objList != null && objList.size() > 0){
				for (Object[] objects : objList) {
					SmallVO vo = new SmallVO();
					vo.setKey((Long)objects[2]);
					vo.setValue(objects[3].toString());
					voList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getAllStatusOfWorkType", e);
		}
		return voList;
	}
	
	public ResultStatus updateWorkStatusComments(WorkStatusVO vo){
		ResultStatus rs = new ResultStatus();
		try {
			
			if(vo.getComment() == null || vo.getComment().trim().isEmpty()){
				rs.setMessage("failure");
				return rs;
			}else{
				GovtWorkProgressComment govtWorkProgressComment = new GovtWorkProgressComment();
				govtWorkProgressComment.setComment(vo.getComment());
				govtWorkProgressComment.setUpdatedBy(vo.getUserId() != null ? vo.getUserId():null);
				govtWorkProgressComment.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				govtWorkProgressComment = govtWorkProgressCommentDAO.save(govtWorkProgressComment);
				
				GovtWorkProgress govtWorkProgress = govtWorkProgressDAO.getWorkProgressId(vo.getWorkId(),vo.getGovtWorkStatusId());
				if(govtWorkProgress == null){
					govtWorkProgress = new GovtWorkProgress();
					govtWorkProgress.setGovtWorkId(vo.getWorkId());
					govtWorkProgress.setGovtWorkStatusId(vo.getGovtWorkStatusId());
					govtWorkProgress.setWorkLength(0.00);
					govtWorkProgress.setCompletedPercentage(0.00);
					govtWorkProgress.setIsCompleted("N");
				}
				govtWorkProgress.setGovtWorkProgressCommentId(govtWorkProgressComment.getGovtWorkProgressCommentId());
				govtWorkProgress.setUpdatedBy(vo.getUserId() != null ? vo.getUserId():null);
				govtWorkProgress.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				govtWorkProgress = govtWorkProgressDAO.save(govtWorkProgress);
				
				
				//save details in tracking table
				GovtWorkProgressUpdate govtWorkProgressUpdate = new GovtWorkProgressUpdate();
				govtWorkProgressUpdate.setGovtWorkId(vo.getWorkId());
				govtWorkProgressUpdate.setUpdatedBy(vo.getUserId());
				govtWorkProgressUpdate.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
				Long uniqueUpdateId = govtWorkProgressUpdateDAO.save(govtWorkProgressUpdate).getGovtWorkProgressUpdateId();
				
				vo.setCommentId(govtWorkProgressComment.getGovtWorkProgressCommentId());
				saveWorkStatusDetailsInTracking(vo,uniqueUpdateId);
			}
			
			rs.setMessage("success");
		} catch (Exception e) {
			rs.setMessage("failure");
			LOG.error("exception occured while updating the work status comments", e);
		}
		return rs;
	}
}
