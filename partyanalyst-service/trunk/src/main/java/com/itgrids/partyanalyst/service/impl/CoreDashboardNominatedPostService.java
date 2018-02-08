package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.partyanalyst.dao.IActivityMemberAccessLevelDAO;
import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.ActivityMemberVO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.dto.UserTypeVO;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICoreDashboardGenericService;
import com.itgrids.partyanalyst.service.ICoreDashboardNominatedPostService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;


public class CoreDashboardNominatedPostService implements ICoreDashboardNominatedPostService {
	private final static Logger LOG = Logger.getLogger(CoreDashboardNominatedPostService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private SetterAndGetterUtilService setterAndGetterUtilService;
	private INominatedPostDAO nominatedPostDAO;
	private INominatedPostApplicationDAO nominatedPostApplicationDAO;
	private ICadreDetailsService cadreDetailsService;
    private INominatedPostGovtOrderDAO nominatedPostGovtOrderDAO;
    private IBoardLevelDAO boardLevelDAO;
    private DateUtilService dateUtilService = new DateUtilService();
    private ICoreDashboardGenericService coreDashboardGenericService;
    private IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO;
    private IDistrictDAO districtDAO;
    private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
    private IConstituencyDAO constituencyDAO;
    private ITehsilDAO tehsilDAO;
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}

	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	
	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}

	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}

	public INominatedPostApplicationDAO getNominatedPostApplicationDAO() {
		return nominatedPostApplicationDAO;
	}

	public void setNominatedPostApplicationDAO(
			INominatedPostApplicationDAO nominatedPostApplicationDAO) {
		this.nominatedPostApplicationDAO = nominatedPostApplicationDAO;
	}

	public ICadreDetailsService getCadreDetailsService() {
		return cadreDetailsService;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	public INominatedPostGovtOrderDAO getNominatedPostGovtOrderDAO() {
		return nominatedPostGovtOrderDAO;
	}

	public void setNominatedPostGovtOrderDAO(
			INominatedPostGovtOrderDAO nominatedPostGovtOrderDAO) {
		this.nominatedPostGovtOrderDAO = nominatedPostGovtOrderDAO;
	}

	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public IBoardLevelDAO getBoardLevelDAO() {
		return boardLevelDAO;
	}

	public void setBoardLevelDAO(IBoardLevelDAO boardLevelDAO) {
		this.boardLevelDAO = boardLevelDAO;
	}
	
	public ICoreDashboardGenericService getCoreDashboardGenericService() {
		return coreDashboardGenericService;
	}

	public void setCoreDashboardGenericService(
			ICoreDashboardGenericService coreDashboardGenericService) {
		this.coreDashboardGenericService = coreDashboardGenericService;
	}
	public IActivityMemberAccessLevelDAO getActivityMemberAccessLevelDAO() {
		return activityMemberAccessLevelDAO;
	}

	public void setActivityMemberAccessLevelDAO(
			IActivityMemberAccessLevelDAO activityMemberAccessLevelDAO) {
		this.activityMemberAccessLevelDAO = activityMemberAccessLevelDAO;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public List<NominatedPostCandidateDtlsVO> getLevelWisePostsOverView(List<Long> locationValues,String fromDateStr,String toDateStr,Long locationTypeId,Long boardLevelId){
		List<NominatedPostCandidateDtlsVO> finalList =new ArrayList<NominatedPostCandidateDtlsVO>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			Long total=0l;
			Map<Long,NominatedPostCandidateDtlsVO> levelMap= new HashMap<Long,NominatedPostCandidateDtlsVO>();
			List<Object[]> nominatedPostList = nominatedPostDAO.getNominatedPostLocationStatusBasedWiseCount(locationValues,startDate,endDate,locationTypeId,boardLevelId);
			if(nominatedPostList != null && nominatedPostList.size()>0){
				for(Object[] param : nominatedPostList){
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[3]); 
					Long statusId = commonMethodsUtilService.getLongValueForObject(param[0]);
					String levelName = commonMethodsUtilService.getStringValueForObject(param[4]);
				   if(levelId.longValue() == 5l || levelId.longValue() == 6l){
						levelId = 5l;
						levelName = "Mandal/Muncipality/Corporation";
					}else if(levelId.longValue() == 7l || levelId.longValue() == 8l){
						levelId =7l;
						levelName = "Village/Ward";
					}
					NominatedPostCandidateDtlsVO deptVO = levelMap.get(levelId);
			        if(deptVO == null){
			            deptVO =new NominatedPostCandidateDtlsVO();
			            deptVO.setBoardLevelId(levelId);
			            deptVO.setBoard(levelName);
			            levelMap.put(levelId, deptVO);
			          }
		            if(statusId.longValue() == 1l){
		        	   deptVO.setOpenCount(deptVO.getOpenCount()+commonMethodsUtilService.getLongValueForObject(param[2])); 
		            }else if(statusId.longValue() == 4l || statusId.longValue() == 3l){
		            	deptVO.setGoIsuuedCount(deptVO.getGoIsuuedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
		            }else if(statusId.longValue() == 2l){
		            	deptVO.setFinalizedPost(deptVO.getFinalizedPost()+commonMethodsUtilService.getLongValueForObject(param[2]));
		            }
		            deptVO.setTotalPosts(deptVO.getTotalPosts()+commonMethodsUtilService.getLongValueForObject(param[2]));
		            total=total+commonMethodsUtilService.getLongValueForObject(param[2]);
				}
			}
			List<Object[]> recivedApplicationsCount = nominatedPostApplicationDAO.getLocationWiseApplicationCount(locationValues,startDate,endDate,locationTypeId,boardLevelId); 
			if(recivedApplicationsCount != null && recivedApplicationsCount.size()>0){
				for(Object[] param : recivedApplicationsCount){
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[1]); 
					String levelName = commonMethodsUtilService.getStringValueForObject(param[2]);
					if(levelId.longValue() == 5l || levelId.longValue() == 6l){
						levelId =5l;
						levelName = "Mandal/Muncipality/Corporation";
					}else if(levelId.longValue() == 7l || levelId.longValue() == 8l){
						levelId =7l;
						levelName = "Village/Ward";
					}
					NominatedPostCandidateDtlsVO deptVO = levelMap.get(levelId);
					if(deptVO != null){
						deptVO.setRecivedCount(deptVO.getRecivedCount()+commonMethodsUtilService.getLongValueForObject(param[0]));
					}
					//total=total+deptVO.getRecivedCount();
				}
				if(commonMethodsUtilService.isMapValid(levelMap)){
					for(Entry<Long,NominatedPostCandidateDtlsVO> entry :levelMap.entrySet()){
						finalList.add(entry.getValue());
					}
				}
				if(finalList != null){
					for(NominatedPostCandidateDtlsVO vo:finalList){
						vo.setTotalRecePer(100.00);
						vo.setOpenPostPer((Double.parseDouble(cadreDetailsService.calculatePercentage(vo.getTotalPosts(), vo.getOpenCount()))));
						vo.setGoIssuedPer((Double.parseDouble(cadreDetailsService.calculatePercentage(vo.getTotalPosts(), vo.getGoIsuuedCount()))));
						vo.setFinalizedPer((Double.parseDouble(cadreDetailsService.calculatePercentage(vo.getTotalPosts(), vo.getFinalizedPost()))));
						vo.setTotalPer(100.00);
					}
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getLevelWisePostsOverView method of LocationDashboardService"+e);
		}
		
		return finalList;
	}

	public List<NominatedPostDetailsVO> getDepartmentWisePostAndApplicationDetails(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLevelId,Long deptId){
		List<NominatedPostDetailsVO> returnsList = new ArrayList<NominatedPostDetailsVO>();
		try{
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			List<Long> levelValues= new ArrayList<Long>();
			
			if(locationTypeId != null && locationTypeId.longValue() == 3l){
				levelValues = districtDAO.getAllDistrictIds();
			}else if(locationTypeId != null && locationTypeId.longValue() == 10l){
				List<Long> districtids = new ArrayList<Long>();
				Long[] ids = IConstants.AP_NEW_DISTRICTS_IDS;
				for (Long obj : ids) {
					districtids.add(obj);
				}
				levelValues= delimitationConstituencyAssemblyDetailsDAO.getAllParliamentStateIds(districtids);
			}else if(locationTypeId != null && locationTypeId.longValue() == 4l){
				levelValues= constituencyDAO.getConstituenciesIdsList(locationValues);
			}else if(locationTypeId != null && locationTypeId.longValue() == 5l){
				levelValues= tehsilDAO.getAllTehsilIds(locationValues);
			}
			Map<Long,NominatedPostDetailsVO> deptMap = new HashMap<Long,NominatedPostDetailsVO>();
			List<Object[]> postsList = nominatedPostDAO.getDepartmentWisePostDetails(levelValues, startDate, endDate, locationTypeId, year, boardLevelId, deptId);
			//List<Object[]> applicationList = nominatedPostApplicationDAO.getDepartmentWiseApplicationDetails(locationValues, startDate, endDate, locationTypeId, year, boardLevelId, deptId);
			if(commonMethodsUtilService.isListOrSetValid(postsList)){
				for (Object[] param : postsList) {
					NominatedPostDetailsVO deptVO = deptMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(deptVO == null){
						deptVO =new NominatedPostDetailsVO();
						deptVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						deptVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						deptMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), deptVO);
					}
					
					/*if(commonMethodsUtilService.getLongValueForObject(param[2]) == 3l || commonMethodsUtilService.getLongValueForObject(param[2]) == 4l){
						deptVO.setFinalOrGOCnt(deptVO.getFinalOrGOCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}*/
					if(commonMethodsUtilService.getLongValueForObject(param[2]) == 1l){
						deptVO.setOpenCnt(deptVO.getOpenCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					if(commonMethodsUtilService.getLongValueForObject(param[2]) == 4l){
						deptVO.setGoIsuuedCnt(deptVO.getGoIsuuedCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					if(commonMethodsUtilService.getLongValueForObject(param[2]) == 3l){
						deptVO.setFinalizedCnt(deptVO.getFinalizedCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					deptVO.setTotalCount(deptVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			
			List<Object[]> expirePostList = nominatedPostGovtOrderDAO.getNominatedPostExpireDetails(locationValues, startDate, endDate, locationTypeId, year, boardLevelId, deptId);
			if(commonMethodsUtilService.isListOrSetValid(expirePostList)){
				for (Object[] param : expirePostList) {
					NominatedPostDetailsVO deptVO = deptMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(deptVO == null){
						deptVO =new NominatedPostDetailsVO();
						deptVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						deptVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						deptMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), deptVO);
					}
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					Date expire= null;
					List<Date> betweenDatesList = null;
					String expireDate = commonMethodsUtilService.getStringValueForObject(param[2]);
					if(expireDate != null && !expireDate.isEmpty()){
						expire = sdf1.parse(expireDate);
					}
					Date dateWithoutTime = sdf1.parse(sdf1.format(new Date()));
				if(expire != null){
				  betweenDatesList = commonMethodsUtilService.getBetweenDates(dateWithoutTime, expire);
				}
				if(betweenDatesList != null && betweenDatesList.size()>0){
                    if(betweenDatesList.size()<30){
                    	deptVO.setExpireOneMnth(deptVO.getExpireOneMnth()+commonMethodsUtilService.getLongValueForObject(param[3]));
					}
                    if(betweenDatesList.size()>30 && betweenDatesList.size()<60){
						deptVO.setExpireTwoMnth(deptVO.getExpireTwoMnth()+commonMethodsUtilService.getLongValueForObject(param[3]));
					}
                    if(betweenDatesList.size()>60 && betweenDatesList.size()<90){
						deptVO.setExpireThreMnth(deptVO.getExpireTwoMnth()+commonMethodsUtilService.getLongValueForObject(param[3]));
					}
				}		
				}
			}
			if(commonMethodsUtilService.isMapValid(deptMap)){
				returnsList.addAll(deptMap.values());
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised in getDepartmentWisePostAndApplicationDetails method of NominatedPostLocationDashboardService"+e);
		}
		return returnsList;
	}
	
	public List<NominatedPostCandidateDtlsVO> getNominatedPostLocationWiseBoardLevelCount(List<Long> locationValues,String fromDateStr,String toDateStr,Long locationTypeId,Long boardLevelId){
		List<NominatedPostCandidateDtlsVO> finalList =new ArrayList<NominatedPostCandidateDtlsVO>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			Map<Long,NominatedPostCandidateDtlsVO> levelMap= new HashMap<Long,NominatedPostCandidateDtlsVO>();
		    //0-districtId,1-districtName,2-statusId,3-statusName,4-count,5-boardLevel,6-boardLevelName
			List<Object[]> nominatedPostList = nominatedPostDAO.getNominatedPostLocationWiseBoardLevelCount(locationValues,startDate,endDate,locationTypeId,boardLevelId);
			if(nominatedPostList != null && nominatedPostList.size()>0){
				for(Object[] param : nominatedPostList){
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[0]); 
					Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
					String levelName = commonMethodsUtilService.getStringValueForObject(param[1]);
					NominatedPostCandidateDtlsVO deptVO = levelMap.get(levelId);
			        if(deptVO == null){
			            deptVO =new NominatedPostCandidateDtlsVO();
			            deptVO.setBoardLevelId(levelId);
			            deptVO.setBoard(levelName);
			            deptVO.setLevelList(getAllBoardLevelList(boardLevelId));
			            levelMap.put(levelId, deptVO);
			          }
			        NominatedPostCandidateDtlsVO matchedBoardVo = getMatchVO(deptVO.getLevelList(),commonMethodsUtilService.getLongValueForObject(param[5]));
			        if(matchedBoardVo != null){
		            if(statusId.longValue() == 1l){
		            	matchedBoardVo.setOpenCount(matchedBoardVo.getOpenCount()+commonMethodsUtilService.getLongValueForObject(param[2])); 
		            }else if(statusId.longValue() == 4l || statusId.longValue() == 3l){
		            	matchedBoardVo.setGoIsuuedCount(matchedBoardVo.getGoIsuuedCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
		            }else if(statusId.longValue() == 2l){
		            	matchedBoardVo.setFinalizedPost(matchedBoardVo.getFinalizedPost()+commonMethodsUtilService.getLongValueForObject(param[2]));
		            }
		            matchedBoardVo.setTotalPosts(matchedBoardVo.getTotalPosts()+commonMethodsUtilService.getLongValueForObject(param[2]));
			        }
				}
			}
			if(levelMap != null && levelMap.size()>0){
				finalList.addAll(levelMap.values());
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getLevelWisePostsOverView method of LocationDashboardService"+e);
		}
		
		return finalList;
	}
	public List<NominatedPostCandidateDtlsVO> getAllBoardLevelList(Long boardLevelId){
		List<NominatedPostCandidateDtlsVO> levelList =new ArrayList<NominatedPostCandidateDtlsVO>();
		try{
			List<Object[]> boardLevelList=boardLevelDAO.getAllBoardLevels(boardLevelId);
			for(Object[] param : boardLevelList){
				NominatedPostCandidateDtlsVO vo= new NominatedPostCandidateDtlsVO();
				vo.setLevelValue(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
				levelList.add(vo);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getLevelWisePostsOverView method of LocationDashboardService"+e);
		}
		return levelList;
	}
	public NominatedPostCandidateDtlsVO getMatchVO(
			List<NominatedPostCandidateDtlsVO> returnList, Long boardLevelId) {
		if (returnList == null)
			return null;
		for (NominatedPostCandidateDtlsVO locationVo : returnList) {
			if (locationVo.getLevelValue().equals(boardLevelId)) {
				return locationVo;
			}
		}
		return null;
	}
	
	public List<NominatedPostCandidateDtlsVO> getNominatedPostStateWiseCount(String fromDateStr,String toDateStr){
		List<NominatedPostCandidateDtlsVO> returnsList = new ArrayList<NominatedPostCandidateDtlsVO>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			Map<Long,NominatedPostCandidateDtlsVO> stateMap= new HashMap<Long,NominatedPostCandidateDtlsVO>();
			List<Object[]> stateList = nominatedPostDAO.getNominatedPostStateWiseCount(startDate,endDate);
			if(stateList != null && stateList.size()>0){
				for(Object[] param : stateList){
					Long statusId = commonMethodsUtilService.getLongValueForObject(param[2]);
					NominatedPostCandidateDtlsVO locatinVo  = stateMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locatinVo == null){
					locatinVo = new NominatedPostCandidateDtlsVO();
					locatinVo.setLevelValue(commonMethodsUtilService.getLongValueForObject(param[0]));
					locatinVo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					stateMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), locatinVo);
					}
					if(statusId == 1l){
						locatinVo.setOpenCount(locatinVo.getOpenCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					if(statusId == 4l){
						locatinVo.setGoIsuuedCount(locatinVo.getGoIsuuedCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					if(statusId == 3l){
						locatinVo.setFinalizedPost(locatinVo.getFinalizedPost()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					locatinVo.setTotalPosts(locatinVo.getTotalPosts()+commonMethodsUtilService.getLongValueForObject(param[4]));
				}
				
			}
			//0-stateId,1-stateName,2-date,3-count
			List<Object[]> expireStateList =nominatedPostGovtOrderDAO.getNominatedPostStateWiseExpireDetails(startDate,endDate);
			if(commonMethodsUtilService.isListOrSetValid(expireStateList)){
				for(Object[] param : expireStateList){
					NominatedPostCandidateDtlsVO matchedStateVo  = stateMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(matchedStateVo != null){
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
					Date expire= null;
					List<Date> betweenDatesList = null;
					String expireDate = commonMethodsUtilService.getStringValueForObject(param[2]);
					if(expireDate != null && !expireDate.isEmpty()){
						expire = sdf1.parse(expireDate);
					}
					Date dateWithoutTime = sdf1.parse(sdf1.format(new Date()));
				if(expire != null){
				  betweenDatesList = commonMethodsUtilService.getBetweenDates(dateWithoutTime, expire);
				}
				if(betweenDatesList != null && betweenDatesList.size()>0){
                    if(betweenDatesList.size()<30){
                    	matchedStateVo.setExpireOneMonth(matchedStateVo.getExpireOneMonth()+commonMethodsUtilService.getLongValueForObject(param[3]));
					}
                    if(betweenDatesList.size()>30 && betweenDatesList.size()<60){
                    	matchedStateVo.setExprireTwoMnth(matchedStateVo.getExprireTwoMnth()+commonMethodsUtilService.getLongValueForObject(param[3]));
					}
                    if(betweenDatesList.size()>60 && betweenDatesList.size()<90){
                    	matchedStateVo.setExpireThreeMnth(matchedStateVo.getExpireThreeMnth()+commonMethodsUtilService.getLongValueForObject(param[3]));
					}
				}
				}
				}
			}
		if(stateMap != null && stateMap.size()>0){
			returnsList.addAll(stateMap.values());
		}
			
		}catch(Exception e){
			e.printStackTrace();
			Log.error("Exception raised in getNominatedPostStateWiseCount method of LocationDashboardService"+e);
		}
		return returnsList;	
	}
public List<List<UserTypeVO>> getUserTypeWiseNominatedPostDetailsCnt(InputVO inputVO){
		
	    List<List<UserTypeVO>> resultList = new ArrayList<List<UserTypeVO>>(0);
	    
	    Map<Long,Set<Long>> locationLevelMap = null;
		Map<Long,Map<Long,UserTypeVO>> userTypeMapDtls = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date toDate=null;
		Date fromDate=null;
	  try{
		     ActivityMemberVO activityMemberVO = new ActivityMemberVO();
		     activityMemberVO.setUserId(inputVO.getUserId());
		     activityMemberVO.setActivityMemberId(inputVO.getActivityMemberId());
		     activityMemberVO.setUserTypeId(inputVO.getUserTypeId());
		     activityMemberVO = coreDashboardGenericService.getChildActivityMembersAndLocationsNew(activityMemberVO);//calling generic method.
		     userTypeMapDtls = activityMemberVO.getUserTypesMap();
		     locationLevelMap = activityMemberVO.getLocationLevelIdsMap();
		     
		     
		    
		     List<NominatedPostCandidateDtlsVO> condiadtePostList = getLevelLocationWiseCounts(inputVO);
		    
		    if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
		    	
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
					  
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  
				    	  for(Long locationValueId:vo.getLocationValuesSet()){
				    		  
				    		  String key = vo.getLocationLevelId()+"-"+locationValueId;
				    		  
				    		  NominatedPostCandidateDtlsVO nomiantedCandidateVo = getMatchedVOByString(condiadtePostList, key);
				    		  
				    		  if(nomiantedCandidateVo != null){
				    				  vo.setId(nomiantedCandidateVo.getLevelValue());
				    				  vo.setLocationName(nomiantedCandidateVo.getLocationName());
				    				  vo.setStatePosts(nomiantedCandidateVo.getStatePosts());
				    				  vo.setDistrictPosts(nomiantedCandidateVo.getDistrictPosts());
				    				  vo.setConstncyPosts(nomiantedCandidateVo.getConstncyPosts());
				    				  vo.setMandalPosts(nomiantedCandidateVo.getMandalPosts());
				    				  vo.setCentralPosts(nomiantedCandidateVo.getCentralPosts());
				    				  vo.setVillagePosts(nomiantedCandidateVo.getVillagePosts());
				    				  vo.setTotalCount(nomiantedCandidateVo.getTotalPosts());
				    				  vo.setOveralTotal(nomiantedCandidateVo.getOveralTotal());
				    			  
				    			  
				    			  
				    		  }
				    		}
				      }
			}  
			} 
		    
		    //Calculate Percentage
		   if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
				  for (Entry<Long, Map<Long, UserTypeVO>> entry : userTypeMapDtls.entrySet()) {
				      Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				      for(UserTypeVO vo:userTypeMap.values()){
				    	  Long totalMembers = vo.getTotalCount();
				    	  Long overalTotal = vo.getOveralTotal();
				    	  if(totalMembers != null){
				    		  vo.setStatePostsPerc(calculatePercantage(vo.getStatePosts(), totalMembers));
				    		  vo.setDistrictPostsPerc(calculatePercantage(vo.getDistrictPosts(), totalMembers));
				    		  vo.setConstncyPostsPerc(calculatePercantage(vo.getConstncyPosts(), totalMembers));
				    		  vo.setMandalPostsPerc(calculatePercantage(vo.getMandalPosts(), totalMembers));
				    		  vo.setCentralPostsPerc(calculatePercantage(vo.getCentralPosts(), totalMembers));
				    		  vo.setVillagePostsPerc(calculatePercantage(vo.getVillagePosts(), totalMembers));
				    		  vo.setTotalPer(calculatePercantage(vo.getTotalCount(), overalTotal).toString());
				    	  }
					}
				}
			}
		    
		    //merging secreteries and general secrerteries.
		    if(userTypeMapDtls!=null && userTypeMapDtls.size()>0){
		        Map<Long,UserTypeVO> orgSecAndSecMap = new LinkedHashMap<Long,UserTypeVO>();
		        Map<Long,UserTypeVO>  secreteriesMap = null;
		        if(userTypeMapDtls.containsKey(11l)){
		          secreteriesMap = userTypeMapDtls.get(11l);
		          orgSecAndSecMap.putAll(secreteriesMap);
		          //remove secreteries from Map
		          userTypeMapDtls.remove(11l); 
		        }
		        
		        Map<Long,UserTypeVO>  organizingSecreteriesMap = null;
		        if(userTypeMapDtls.containsKey(4l)){
		          organizingSecreteriesMap = userTypeMapDtls.get(4l);
		          orgSecAndSecMap.putAll(organizingSecreteriesMap);
		        }
		       
		        if(organizingSecreteriesMap!=null && organizingSecreteriesMap.size()>0){
		        	userTypeMapDtls.put(4l, orgSecAndSecMap); 
		        }
		      }
		    
		    
			if(userTypeMapDtls != null && userTypeMapDtls.size() > 0){
				  for(Entry<Long, Map<Long, UserTypeVO>> entry:userTypeMapDtls.entrySet()){
				   Map<Long,UserTypeVO> userTypeMap = entry.getValue();
				   resultList.add(new ArrayList<UserTypeVO>(userTypeMap.values()));
			      }
			}
		 	if(resultList != null && resultList.size() > 0){
				for(List<UserTypeVO> memberList:resultList){
					Collections.sort(memberList, totalCountPercDesc);
				}
			}
	 }catch(Exception e) {
	  LOG.error("Exception raised at getUserTypeWiseMeetingCounductedNotCounductedMayBeDetailsCnt() method of CoreDashboardPartyMeetingService", e);
	}
	return resultList; 
 }
public static Comparator<UserTypeVO> totalCountPercDesc = new Comparator<UserTypeVO>() {
	public int compare(UserTypeVO member2, UserTypeVO member1) {

	Double perc2 = Double.valueOf(member2.getTotalPer());
	Double perc1 = Double.valueOf(member1.getTotalPer());
	//descending order of percantages.
	 return perc1.compareTo(perc2);
	}
	};
public List<NominatedPostCandidateDtlsVO> getLevelLocationWiseCounts(InputVO inputvo){
	List<NominatedPostCandidateDtlsVO> returnsList = null;
	try{
		Long userAccessLevelId=0l;
		Map<Long,Set<Long>> locationAccessLevelMap = new HashMap<Long, Set<Long>>(0);
		Set<Long> levelValues = new java.util.HashSet<Long>();
		
		List<Object[]> rtrnUsrAccssLvlIdAndVlusObjLst=activityMemberAccessLevelDAO.getLocationLevelAndValuesByActivityMembersId(inputvo.getActivityMemberId());
	    if(rtrnUsrAccssLvlIdAndVlusObjLst != null && !rtrnUsrAccssLvlIdAndVlusObjLst.isEmpty()){
		   userAccessLevelId = commonMethodsUtilService.getLongValueForObject(rtrnUsrAccssLvlIdAndVlusObjLst.get(0)[0]);
		   for (Object[] param : rtrnUsrAccssLvlIdAndVlusObjLst) {
			Set<Long> locationValuesSet= locationAccessLevelMap.get((Long)param[0]);
			 if(locationValuesSet == null){
				 locationValuesSet = new java.util.HashSet<Long>();
				 locationAccessLevelMap.put((Long)param[0],locationValuesSet);
			 }
			 locationValuesSet.add(param[1] != null ? (Long)param[1]:0l);
			 levelValues.add(param[1] != null ? (Long)param[1]:0l);
		}
		   inputvo.setLevelId(userAccessLevelId);
		   inputvo.setLevelValues(new ArrayList<Long>(levelValues));
	   }
	    returnsList = getLevelLocationWiseNominatedPostCounts(inputvo);
		if(returnsList != null && returnsList.size()>0){
			for(NominatedPostCandidateDtlsVO vo: returnsList){
					vo.setOveralTotal(inputvo.getOveralCount());
			}
		}
	}catch(Exception e ){
		e.printStackTrace();
		Log.error("Exception raised in getUserTypeWiseNominatedPostCandidateDetails method of LocationDashboardService"+e);
	}
	return returnsList;
	
}
public List<NominatedPostCandidateDtlsVO> getLevelLocationWiseNominatedPostCounts(InputVO inputVO){
	List<NominatedPostCandidateDtlsVO> returnList = new ArrayList<NominatedPostCandidateDtlsVO>(0);
	try {
		Map<String,NominatedPostCandidateDtlsVO> levelLocationMap = new LinkedHashMap<String,NominatedPostCandidateDtlsVO>();
		String[] levelArr = {"state","district","parliament","constituency"};
		for (int i = 0; i < levelArr.length; i++) {
			//setAllLevelValuesToMap(levelArr[i], levelLocationMap,inputVO.getLevelId(),inputVO.getLevelValues(),inputVO.getFromDateStr(),inputVO.getToDateStr(),overallTotal);
			setAllLevelValuesToMap(levelArr[i], levelLocationMap,inputVO);
		}
		
		if(levelLocationMap != null)
			returnList = new ArrayList<NominatedPostCandidateDtlsVO>(levelLocationMap.values());
		
	} catch (Exception e) {
		LOG.error("Exception raised in getOverAllCommitteeWiseMembersCounts() method of KaizalaLocationDashBoardService", e);
	}
	return returnList;
}
public void setAllLevelValuesToMap(String levelStr,Map<String,NominatedPostCandidateDtlsVO> levelLocationMap,InputVO inputVO){
	try {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     
		Date startDate = null;
		Date endDate = null;
		if(inputVO.getFromDateStr() != null && !inputVO.getFromDateStr().isEmpty() && inputVO.getFromDateStr().trim().length() > 0 && inputVO.getToDateStr() != null && !inputVO.getToDateStr().isEmpty() && inputVO.getToDateStr().trim().length() > 0){
			startDate = sdf.parse(inputVO.getFromDateStr());
			endDate = sdf.parse(inputVO.getToDateStr());
		}
		String keyLevelId = "";
		if(levelStr != null && levelStr.trim().equalsIgnoreCase("state"))
			keyLevelId = "2-";
		else if(levelStr != null && levelStr.trim().equalsIgnoreCase("district"))
			keyLevelId = "3-";
		else if(levelStr != null && levelStr.trim().equalsIgnoreCase("parliament"))
			keyLevelId = "4-";
		else if(levelStr != null && levelStr.trim().equalsIgnoreCase("constituency"))
			keyLevelId = "5-";
		// //0-stateId,1-stateName,2-count,3-boardLevelId,4-boardLevel
		List<Object[]> totalList = nominatedPostDAO.getLocationWiseNominatedPostCount(levelStr,inputVO.getLevelId(),inputVO.getLevelValues(),startDate,endDate);
		if(totalList != null && !totalList.isEmpty()){
			Long overallTotal =0l;
			for (Object[] obj : totalList) {
				Long locationId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				Long committeeId = Long.valueOf(obj[3] != null ? obj[3].toString():"0");
				String key = keyLevelId.toString()+locationId.toString();
				NominatedPostCandidateDtlsVO vo = levelLocationMap.get(key);
				if(vo == null){
					vo = new NominatedPostCandidateDtlsVO();
					vo.setLevelValue(locationId);
					vo.setLocationName(obj[1] != null ? obj[1].toString():"");
					vo.setKey(key);
					if(committeeId != null && committeeId.longValue() == 1l){
						vo.setCentralPosts(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 2l){
						vo.setStatePosts(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 3l){
						vo.setDistrictPosts(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 4l){
						vo.setConstncyPosts(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 5l){
						vo.setMandalPosts(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 7l){
						vo.setVillagePosts(Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					vo.setTotalPosts(vo.getTotalPosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					overallTotal=overallTotal+vo.getTotalPosts();
					levelLocationMap.put(key, vo);
				}
				NominatedPostCandidateDtlsVO matchedVo = levelLocationMap.get(key);
				if(matchedVo != null){
					if(committeeId != null && committeeId.longValue() == 1l){
						matchedVo.setCentralPosts(matchedVo.getCentralPosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 2l){
						matchedVo.setStatePosts(matchedVo.getStatePosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 3l){
						matchedVo.setDistrictPosts(matchedVo.getDistrictPosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 4l){
						matchedVo.setConstncyPosts(matchedVo.getConstncyPosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 5l){
						matchedVo.setMandalPosts(matchedVo.getMandalPosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					if(committeeId != null && committeeId.longValue() == 7l){
						matchedVo.setVillagePosts(matchedVo.getVillagePosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					}
					matchedVo.setTotalPosts(matchedVo.getTotalPosts()+Long.valueOf(obj[2] != null ? obj[2].toString():"0"));
					overallTotal = overallTotal+vo.getTotalPosts();
				}
				//vo.setOveralTotal(overallTotal);
			}
			inputVO.setOveralCount(overallTotal);
			
		}
		
	} catch (Exception e) {
		LOG.error("Exception raised in setAllLevelValuesToMap() method of CoreDashboardNominatedPostService", e);
	}
}
public NominatedPostCandidateDtlsVO getMatchedVOByString(List<NominatedPostCandidateDtlsVO> voList ,String key){
	if(voList != null && voList.size() > 0){
		for (NominatedPostCandidateDtlsVO nominatedVo : voList) {
			if(nominatedVo.getKey().equalsIgnoreCase(key))
				return nominatedVo;
		}
	}
	return null;
}
public UserTypeVO getMatchedVO(List<UserTypeVO> voList ,Long id){
	if(voList != null && voList.size() > 0){
		for (UserTypeVO userTypeVO : voList) {
			if(userTypeVO.getId().equals(id))
				return userTypeVO;
		}
	}
	return null;
}
public Double calculatePercantage(Long subCount,Long totalCount){
	Double d=0.0d;
	if(subCount.longValue()>0l && totalCount.longValue()==0l)
	LOG.error("Sub Count Value is "+subCount+" And Total Count Value  "+totalCount);

	if(totalCount.longValue() > 0l){
		 d = new BigDecimal(subCount * 100.0/totalCount).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();	 
	}
	return d;
}

public List<NominatedPostDetailsVO> getDepartMentAndBoardWisePositinsStatusCount(List<Long> locationValues,String fromDateStr, 
		String toDateStr,Long locationTypeId,Long boardLevelId,Long statusId){
	List<NominatedPostDetailsVO> returnsList = new ArrayList<NominatedPostDetailsVO>();
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     
		Date startDate = null;
		Date endDate = null;
		if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
			startDate = sdf.parse(fromDateStr);
			endDate = sdf.parse(toDateStr);
		}
		Map<Long,NominatedPostDetailsVO> deptMap = new HashMap<Long,NominatedPostDetailsVO>();
		//0-departId,1-deptName,2-count,3-boardId,4-boardName,5-positionId,6-positionName
		List<Object[]> postionDetails = nominatedPostDAO.getDepartMentAndBoardWisePositinsStatusCount(locationValues,startDate,endDate,locationTypeId,boardLevelId,statusId);
		if(postionDetails != null && postionDetails.size()>0l){
			for(Object[] param : postionDetails){
				NominatedPostDetailsVO vo = deptMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				if(vo == null){
					 vo = new NominatedPostDetailsVO();
					vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
					
					NominatedPostDetailsVO boardVo = new NominatedPostDetailsVO();
					boardVo.setBoardId(commonMethodsUtilService.getLongValueForObject(param[3]));
					boardVo.setBoard(commonMethodsUtilService.getStringValueForObject(param[4]));
					vo.getList().add(boardVo);
					
					NominatedPostDetailsVO positionVo = new NominatedPostDetailsVO();
					positionVo.setPositionId(commonMethodsUtilService.getLongValueForObject(param[5]));
					positionVo.setPosition(commonMethodsUtilService.getStringValueForObject(param[6]));
					boardVo.getSubList().add(positionVo);
					
					deptMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), vo);
				}
				NominatedPostDetailsVO matchedVo =deptMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
				if(matchedVo != null){
					matchedVo.setCount(matchedVo.getCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
				NominatedPostDetailsVO matchBoardVo = getMatchedBoardVo(matchedVo.getList(),commonMethodsUtilService.getLongValueForObject(param[3]));
				if(matchBoardVo != null){
					matchBoardVo.setBoardCunt(matchBoardVo.getBoardCunt()+commonMethodsUtilService.getLongValueForObject(param[2]));
					NominatedPostDetailsVO matchPositionVo = getMatchedPositionVo(matchBoardVo.getSubList() ,commonMethodsUtilService.getLongValueForObject(param[5]));
					if(matchPositionVo != null){
						matchPositionVo.setPositionCount(matchPositionVo.getPositionCount()+commonMethodsUtilService.getLongValueForObject(param[2]));
					}else{
						NominatedPostDetailsVO	positionVo = new NominatedPostDetailsVO();
						positionVo.setPositionId(commonMethodsUtilService.getLongValueForObject(param[5]));
						positionVo.setPosition(commonMethodsUtilService.getStringValueForObject(param[6]));
						positionVo.setPositionCount(commonMethodsUtilService.getLongValueForObject(param[2]));
						matchBoardVo.getSubList().add(positionVo);
					}
					
				}else{
				NominatedPostDetailsVO	boardVo = new NominatedPostDetailsVO();
				boardVo.setBoardId(commonMethodsUtilService.getLongValueForObject(param[3]));
				boardVo.setBoard(commonMethodsUtilService.getStringValueForObject(param[4]));
				boardVo.setBoardCunt(commonMethodsUtilService.getLongValueForObject(param[2]));
				matchedVo.getList().add(boardVo);
				}
				
				}
			}
			if(deptMap != null && deptMap.size()>0){
				returnsList.addAll(deptMap.values());
			}
		}
	}catch(Exception e){
		LOG.error("Exception raised in getDepartMentAndBoardWisePositinsStatusCount() method of CoreDashboardNominatedPostService", e);	
	}
	return returnsList;
}
public NominatedPostDetailsVO getMatchedBoardVo(List<NominatedPostDetailsVO> voList ,Long id){
	if(voList != null && voList.size() > 0){
		for (NominatedPostDetailsVO boardVo : voList) {
			if(boardVo.getBoardId().equals(id))
				return boardVo;
		}
	}
	return null;
}
public NominatedPostDetailsVO getMatchedPositionVo(List<NominatedPostDetailsVO> voList ,Long id){
	if(voList != null && voList.size() > 0){
		for (NominatedPostDetailsVO positionVo : voList) {
			if(positionVo.getPositionId().equals(id))
				return positionVo;
		}
	}
	return null;
}

public List<NominatedPostDetailsVO> getDepartMentWiseBoards(List<Long> locationValues,String fromDateStr, 
		String toDateStr,Long locationTypeId,Long boardLevelId,Long statusId,Long departmentId){
	List<NominatedPostDetailsVO> returnsList = new ArrayList<NominatedPostDetailsVO>();
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     
		Date startDate = null;
		Date endDate = null;
		if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
			startDate = sdf.parse(fromDateStr);
			endDate = sdf.parse(toDateStr);
		}
		//0-boardId,1-boardName
		List<Object[]> postionDetails = nominatedPostDAO.getDepartMentWiseBoards(locationValues,startDate,endDate,locationTypeId,boardLevelId,statusId,departmentId);
		if(postionDetails != null && postionDetails.size()>0l){
			for(Object[] param : postionDetails){
				NominatedPostDetailsVO vo=new NominatedPostDetailsVO();
				vo.setBoardId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setBoard(commonMethodsUtilService.getStringValueForObject(param[1]));
				returnsList.add(vo);
			}
		}
	}catch(Exception e){
		LOG.error("Exception raised in getDepartMentWiseBoards() method of CoreDashboardNominatedPostService", e);	
	}
	return returnsList;
}
public List<NominatedPostDetailsVO> getBoardWisePositions(List<Long> locationValues,String fromDateStr, 
		String toDateStr,Long locationTypeId,Long boardLevelId,Long statusId,Long boardId){
	List<NominatedPostDetailsVO> returnsList = new ArrayList<NominatedPostDetailsVO>();
	try{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	     
		Date startDate = null;
		Date endDate = null;
		if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && toDateStr != null && !toDateStr.isEmpty() && toDateStr.trim().length() > 0){
			startDate = sdf.parse(fromDateStr);
			endDate = sdf.parse(toDateStr);
		}
		//0-positionId,1-position
		List<Object[]> postionDetails = nominatedPostDAO.getBoardWisePositions(locationValues,startDate,endDate,locationTypeId,boardLevelId,statusId,boardId);
		if(postionDetails != null && postionDetails.size()>0l){
			for(Object[] param : postionDetails){
				NominatedPostDetailsVO vo=new NominatedPostDetailsVO();
				vo.setBoardId(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setBoard(commonMethodsUtilService.getStringValueForObject(param[1]));
				returnsList.add(vo);
			}
		}
	}catch(Exception e){
		LOG.error("Exception raised in getBoardWisePositions() method of CoreDashboardNominatedPostService", e);	
	}
	return returnsList;
}
}