package com.itgrids.core.api.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.INominatedPostLocationDashboardService;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominatedPostGovtOrderDAO;
import com.itgrids.partyanalyst.dto.NominatedPostCandidateDtlsVO;
import com.itgrids.partyanalyst.dto.NominatedPostDetailsVO;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class NominatedPostLocationDashboardService implements INominatedPostLocationDashboardService{

	private static Logger LOG = Logger.getLogger(NominatedPostLocationDashboardService.class);
	private CommonMethodsUtilService commonMethodsUtilService;
	private INominatedPostDAO nominatedPostDAO;
	private INominatedPostApplicationDAO nominatedPostApplicationDAO;
	private INominatedPostGovtOrderDAO nominatedPostGovtOrderDAO;
	private ICadreDetailsService cadreDetailsService;
	
	public INominatedPostApplicationDAO getNominatedPostApplicationDAO() {
		return nominatedPostApplicationDAO;
	}
	public void setNominatedPostApplicationDAO(
			INominatedPostApplicationDAO nominatedPostApplicationDAO) {
		this.nominatedPostApplicationDAO = nominatedPostApplicationDAO;
	}
	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}
	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public INominatedPostGovtOrderDAO getNominatedPostGovtOrderDAO() {
		return nominatedPostGovtOrderDAO;
	}
	public void setNominatedPostGovtOrderDAO(
			INominatedPostGovtOrderDAO nominatedPostGovtOrderDAO) {
		this.nominatedPostGovtOrderDAO = nominatedPostGovtOrderDAO;
	}
	
	public ICadreDetailsService getCadreDetailsService() {
		return cadreDetailsService;
	}
	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	
	/**
	 * @param List<NominatedPostCandidateDtlsVO> returnList
	 * @author Hymavathi 
	 *  @since 4-OCT-2017
	 */
	public List<NominatedPostCandidateDtlsVO> getNominatedPositionWiseCandidates(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLvlId
			,Long startIndex,Long endIndex){
		List<NominatedPostCandidateDtlsVO> finalList = new CopyOnWriteArrayList<NominatedPostCandidateDtlsVO>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
		
			List<Object[]> candList = nominatedPostDAO.getNominatedPositionWiseCandidates( locationValues, startDate,  endDate, locationTypeId, year, boardLvlId, startIndex, endIndex);
			if(commonMethodsUtilService.isListOrSetValid(candList)){
				for (Object[] param : candList) {
					NominatedPostCandidateDtlsVO vo = new NominatedPostCandidateDtlsVO();
					vo.setNominatedPostCandiateId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setImage(commonMethodsUtilService.getStringValueForObject(param[2]));
					vo.setTdpCadreId(commonMethodsUtilService.getLongValueForObject(param[3]));
					vo.setMemberShipId(commonMethodsUtilService.getStringValueForObject(param[4]));
					vo.setDepartmentId(commonMethodsUtilService.getLongValueForObject(param[5]));
					vo.setDepartment(commonMethodsUtilService.getStringValueForObject(param[6]));
					vo.setBoardId(commonMethodsUtilService.getLongValueForObject(param[7]));
					vo.setBoard(commonMethodsUtilService.getStringValueForObject(param[8]));
					vo.setPositionId(commonMethodsUtilService.getLongValueForObject(param[9]));
					vo.setPosition(commonMethodsUtilService.getStringValueForObject(param[10]));
					vo.setStatus(commonMethodsUtilService.getStringValueForObject(param[12]));
					vo.setLevelValue(commonMethodsUtilService.getLongValueForObject(param[13]));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[14]));
					finalList.add(vo);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised in getNominatedPositionWiseCandidates method of NominatedPostLocationDashboardService"+e);
		}
		return finalList;
	}
	/**
	 * @return List<NominatedPostCandidateDtlsVO> finalList
	 * @author Srujana 
	 *  @since 5-OCT-2017
	 */
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
			List<Object[]> nominatedPostList = nominatedPostDAO.getNominatedPostLocationStatusWiseCount(locationValues,startDate,endDate,locationTypeId,boardLevelId);
			if(nominatedPostList != null && nominatedPostList.size()>0){
				for(Object[] param :nominatedPostList){
					Long levelId = commonMethodsUtilService.getLongValueForObject(param[3]); 
					Long statusId =commonMethodsUtilService.getLongValueForObject(param[0]);
					String levelName=commonMethodsUtilService.getStringValueForObject(param[4]);
				   if(levelId.longValue() == 5l || levelId.longValue() == 6l){
						levelId =5l;
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
		            }
		            deptVO.setTotalPosts(deptVO.getTotalPosts()+commonMethodsUtilService.getLongValueForObject(param[2]));
		            total=total+commonMethodsUtilService.getLongValueForObject(param[2]);
				}
			}
			List<Object[]> recivedApplicationsCount =nominatedPostApplicationDAO.getLocationWiseApplicationCount(locationValues,startDate,endDate,locationTypeId,boardLevelId); 
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
						deptVO.setRecivedCount(commonMethodsUtilService.getLongValueForObject(param[0]));
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
	/**
	 * @param List<NominatedPostCandidateDtlsVO> returnList
	 * @author Hymavathi 
	 *  @since 5-OCT-2017
	 */
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
			Map<Long,NominatedPostDetailsVO> deptMap = new HashMap<Long,NominatedPostDetailsVO>();
			List<Object[]> postsList = nominatedPostDAO.getDepartmentWisePostDetails(locationValues, startDate, endDate, locationTypeId, year, boardLevelId, deptId);
			List<Object[]> applicationList = nominatedPostApplicationDAO.getDepartmentWiseApplicationDetails(locationValues, startDate, endDate, locationTypeId, year, boardLevelId, deptId);
			if(commonMethodsUtilService.isListOrSetValid(postsList)){
				for (Object[] param : postsList) {
					NominatedPostDetailsVO deptVO = deptMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(deptVO == null){
						deptVO =new NominatedPostDetailsVO();
						deptVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						deptVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						deptMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), deptVO);
					}
					
					if(commonMethodsUtilService.getLongValueForObject(param[2]) == 3l || commonMethodsUtilService.getLongValueForObject(param[2]) == 4l){
						deptVO.setFinalOrGOCnt(deptVO.getFinalOrGOCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					if(commonMethodsUtilService.getLongValueForObject(param[2]) == 1l){
						deptVO.setOpenCnt(deptVO.getOpenCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					deptVO.setTotalCount(deptVO.getTotalCount()+commonMethodsUtilService.getLongValueForObject(param[4]));
				}
			}
			
			if(commonMethodsUtilService.isListOrSetValid(applicationList)){
				for (Object[] param : applicationList) {
					NominatedPostDetailsVO deptVO = deptMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(deptVO == null){
						deptVO =new NominatedPostDetailsVO();
						deptVO.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						deptVO.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						deptMap.put(commonMethodsUtilService.getLongValueForObject(param[0]), deptVO);
					}
					
					if(commonMethodsUtilService.getLongValueForObject(param[2]) == 3l ){
						deptVO.setShorlistedCnt(deptVO.getShorlistedCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					if(commonMethodsUtilService.getLongValueForObject(param[2]) == 6l){
						deptVO.setReadyToFinalCnt(deptVO.getReadyToFinalCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
					}
					deptVO.setReceivedCnt(deptVO.getReceivedCnt()+commonMethodsUtilService.getLongValueForObject(param[4]));
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
	/**
	 * @return List<NominatedPostCandidateDtlsVO> finalList
	 * @author Srujana 
	 *  @since 6-OCT-2017
	 */
	public List<NominatedPostCandidateDtlsVO> getLevelWiseGoIssuedPostions(List<Long> locationValues,String fromDateStr, String toDateStr,Long locationTypeId,String year,Long boardLvlId
			,List<Long> statusIds,Long startIndex,Long endIndex){
		List<NominatedPostCandidateDtlsVO> finalList = new CopyOnWriteArrayList<NominatedPostCandidateDtlsVO>();
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

			Date startDate = null;
			Date endDate = null;
			if(fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0 && fromDateStr != null && !fromDateStr.isEmpty() && fromDateStr.trim().length() > 0){
				startDate = sdf.parse(fromDateStr);
				endDate = sdf.parse(toDateStr);
			}
			Map<Long,NominatedPostCandidateDtlsVO> candidatesMap = new TreeMap<Long,NominatedPostCandidateDtlsVO> ();
			List<Object[]> pageNationCount = nominatedPostGovtOrderDAO.getLevelWiseGoIssuedPostions(locationValues,startDate, endDate,locationTypeId,year,boardLvlId,statusIds, null, null);
			List<Object[]> candList = nominatedPostGovtOrderDAO.getLevelWiseGoIssuedPostions(locationValues,startDate, endDate,locationTypeId,year,boardLvlId,statusIds, startIndex, endIndex);
			if(commonMethodsUtilService.isListOrSetValid(candList)){
				for (Object[] param : candList) {
					NominatedPostCandidateDtlsVO vo = new NominatedPostCandidateDtlsVO();
					vo.setNominatedPostCandiateId(commonMethodsUtilService.getLongValueForObject(param[0]));
					vo.setCandidateName(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setDepartmentId(commonMethodsUtilService.getLongValueForObject(param[2]));
					vo.setDepartment(commonMethodsUtilService.getStringValueForObject(param[3]));
					vo.setBoardId(commonMethodsUtilService.getLongValueForObject(param[4]));
					vo.setBoard(commonMethodsUtilService.getStringValueForObject(param[5]));
					vo.setPositionId(commonMethodsUtilService.getLongValueForObject(param[6]));
					vo.setPosition(commonMethodsUtilService.getStringValueForObject(param[7]));
					if(commonMethodsUtilService.getStringValueForObject(param[8]).equalsIgnoreCase("M")){
						vo.setGender("Male");	
					}else if(commonMethodsUtilService.getStringValueForObject(param[8]).equalsIgnoreCase("F")){
						vo.setGender("Female");
					}
					
					vo.setCasteCategory(commonMethodsUtilService.getStringValueForObject(param[9]));
					
					vo.setImage(commonMethodsUtilService.getStringValueForObject(param[10]));
					vo.setNominatedPostId(commonMethodsUtilService.getLongValueForObject(param[11]));
					candidatesMap.put(vo.getNominatedPostId(), vo);
					finalList.add(vo);
				}
			}
			if(pageNationCount != null && finalList != null && pageNationCount.size() >0 && finalList.size() >0){
				finalList.get(0).setPostCount(Long.valueOf(pageNationCount.size()));
			}
			List<Object[]> goIssuedExpiredDates = nominatedPostGovtOrderDAO.getLevelWiseGoIssuedDate(locationValues,startDate, endDate,locationTypeId,year,boardLvlId,statusIds,candidatesMap.keySet());
			if(commonMethodsUtilService.isListOrSetValid(candList)){
				for (Object[] param : goIssuedExpiredDates) {
					NominatedPostCandidateDtlsVO vo =candidatesMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
					if(vo != null){
						String goDateStr = commonMethodsUtilService.getStringValueForObject(param[0]);
						if(goDateStr != null && !goDateStr.isEmpty() ){
						Date date = new SimpleDateFormat("yyyy-MM-dd").parse(goDateStr);
						String format = new SimpleDateFormat("MMM dd").format(date);
						vo.setDate(format+","+goDateStr.substring(0,4));
						}
					}
					
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("Exception raised in getLevelWiseGoIssuedPostions method of NominatedPostLocationDashboardService"+e);
		}
		return finalList;
	}

}
