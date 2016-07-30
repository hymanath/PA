package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.dao.INominatedPostMemberDAO;
import com.itgrids.partyanalyst.dao.INominatedPostStatusDAO;
import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.dao.IPositionDAO;
import com.itgrids.partyanalyst.dto.CastePositionVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.model.Position;
import com.itgrids.partyanalyst.service.INominatedPostMainDashboardService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.SetterAndGetterUtilService;

public class NominatedPostMainDashboardService implements INominatedPostMainDashboardService {

	private final static Logger LOG =  Logger.getLogger(NominatedPostProfileService.class);
	private INominatedPostApplicationDAO nominatedPostApplicationDAO;
	private IPositionDAO positionDAO;
	private CommonMethodsUtilService commonMethodsUtilService = new CommonMethodsUtilService();
	private IApplicationStatusDAO applicationStatusDAO;
	private INominatedPostStatusDAO nominatedPostStatusDAO;
	private INominatedPostDAO nominatedPostDAO;
	private SetterAndGetterUtilService setterAndGetterUtilService ;
	private INominatedPostFinalDAO nominatedPostFinalDAO;
	private INominationPostCandidateDAO nominationPostCandidateDAO;
	private INominatedPostMemberDAO nominatedPostMemberDAO;
	
	
	public IApplicationStatusDAO getApplicationStatusDAO() {
		return applicationStatusDAO;
	}
	public void setApplicationStatusDAO(IApplicationStatusDAO applicationStatusDAO) {
		this.applicationStatusDAO = applicationStatusDAO;
	}
	public INominatedPostStatusDAO getNominatedPostStatusDAO() {
		return nominatedPostStatusDAO;
	}
	public void setNominatedPostStatusDAO(
			INominatedPostStatusDAO nominatedPostStatusDAO) {
		this.nominatedPostStatusDAO = nominatedPostStatusDAO;
	}
	public INominatedPostDAO getNominatedPostDAO() {
		return nominatedPostDAO;
	}
	public void setNominatedPostDAO(INominatedPostDAO nominatedPostDAO) {
		this.nominatedPostDAO = nominatedPostDAO;
	}
	public SetterAndGetterUtilService getSetterAndGetterUtilService() {
		return setterAndGetterUtilService;
	}
	public void setSetterAndGetterUtilService(
			SetterAndGetterUtilService setterAndGetterUtilService) {
		this.setterAndGetterUtilService = setterAndGetterUtilService;
	}
	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}
	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}
	public INominatedPostApplicationDAO getNominatedPostApplicationDAO() {
		return nominatedPostApplicationDAO;
	}
	public void setNominatedPostApplicationDAO(
			INominatedPostApplicationDAO nominatedPostApplicationDAO) {
		this.nominatedPostApplicationDAO = nominatedPostApplicationDAO;
	}
	public IPositionDAO getPositionDAO() {
		return positionDAO;
	}
	public void setPositionDAO(IPositionDAO positionDAO) {
		this.positionDAO = positionDAO;
	}
	public INominatedPostFinalDAO getNominatedPostFinalDAO() {
		return nominatedPostFinalDAO;
	}
	public void setNominatedPostFinalDAO(
			INominatedPostFinalDAO nominatedPostFinalDAO) {
		this.nominatedPostFinalDAO = nominatedPostFinalDAO;
	}
	public INominationPostCandidateDAO getNominationPostCandidateDAO() {
		return nominationPostCandidateDAO;
	}
	public void setNominationPostCandidateDAO(
			INominationPostCandidateDAO nominationPostCandidateDAO) {
		this.nominationPostCandidateDAO = nominationPostCandidateDAO;
	}
	public INominatedPostMemberDAO getNominatedPostMemberDAO() {
		return nominatedPostMemberDAO;
	}
	public void setNominatedPostMemberDAO(
			INominatedPostMemberDAO nominatedPostMemberDAO) {
		this.nominatedPostMemberDAO = nominatedPostMemberDAO;
	}
	@Override
	public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId) {
	
		List<CastePositionVO> resultList = new ArrayList<CastePositionVO>();
		try{
			List<Object[]> positionList = positionDAO.getAllPositions();
			if(positionList != null && positionList.size() > 0)
			{
				CastePositionVO castePositionVO = null;
				for(Object[] params : positionList)
				{
					castePositionVO = new CastePositionVO();
					castePositionVO.setPositionId((Long)params[0]);
					castePositionVO.setPositionName(params[1].toString());
					castePositionVO.setCasteList(getDefaultCastList(LocationLevelId));
					resultList.add(castePositionVO);
				}
			}
			if(LocationLevelId.longValue()==5){
				List<Object[]> rtrnMandalList = nominatedPostFinalDAO.getLocationWiseCastePositionCount(5l,positionId);
				pushMatchCastePositionCntToVO(rtrnMandalList,resultList);
				List<Object[]> rtrnMncpaltyCrprtnList = nominatedPostFinalDAO.getLocationWiseCastePositionCount(6l,positionId);
				pushMatchCastePositionCntToVO(rtrnMncpaltyCrprtnList,resultList);
			}else{
				List<Object[]> rtrnObjList = nominatedPostFinalDAO.getLocationWiseCastePositionCount(LocationLevelId,positionId);
				pushMatchCastePositionCntToVO(rtrnObjList,resultList);
			}
		 }catch(Exception e) {
		   LOG.error("Error occured at getLocationWiseCastePositionCount() in NominatedPostMainDashboardService class ",e);	
		}
		return resultList;
	}
 public void pushMatchCastePositionCntToVO(List<Object[]> rtrnObjList,List<CastePositionVO> resultList){
	 
	 try{
		 if(rtrnObjList != null && rtrnObjList.size() > 0)
			{
				for(Object[] params : rtrnObjList)
				{
					Long positionId = (Long)params[2];
					Long casteStateId = (Long)params[0];
					Long count = params[4] !=null ? (Long)params[4]:0l;
					
					for(CastePositionVO vo : getMatchedVO(resultList,positionId).getCasteList())
					{
						if(vo.getCasteId().equals(casteStateId.longValue())){
							vo.setCount(vo.getCount()+count);
						}
					}
				}
			}
	 }catch(Exception e){
		 LOG.error("Error occured at pushMatchCastePositionCntToVO() in NominatedPostMainDashboardService class ",e); 
	 }
	}
	public CastePositionVO getMatchedVO(List<CastePositionVO> list,Long positionId)
	{
		try{
			for(CastePositionVO vo : list)
				if(vo.getPositionId().equals(positionId)){
					return vo;
				}
		}catch (Exception e) {
			LOG.error("Exception occured in getMatchedVO ",e);
		}
		return null;
	}
	public List<CastePositionVO> getDefaultCastList(Long LocationLevelId){
		List<Object[]> casteList = nominatedPostFinalDAO.getCandidateCasteList(LocationLevelId);
		List<CastePositionVO> casteDefaultList = new ArrayList<CastePositionVO>(0);
		
		if(casteList != null && casteList.size() > 0)
		{
			CastePositionVO castePositionVO = null;
			for(Object[] params : casteList)
			{
				castePositionVO = new CastePositionVO();
				castePositionVO.setCasteId((Long)params[0]);
				castePositionVO.setCasteName(params[1].toString());
				casteDefaultList.add(castePositionVO);
			}
		}
		return casteDefaultList;
		
	}
	@Override
	public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId) {
		List<CastePositionVO> resultList = new ArrayList<CastePositionVO>(0);
		try{
			List<Object[]> positionList = positionDAO.getAllPositions();
			if(positionList != null && positionList.size() > 0)
			{
				CastePositionVO castePositionVO = null;
				for(Object[] params : positionList)
				{
					castePositionVO = new CastePositionVO();
					castePositionVO.setPositionId((Long)params[0]);
					castePositionVO.setPositionName(params[1].toString());
					castePositionVO.setCasteList(getdefaultCasteGroupList(LocationLevelId));
					resultList.add(castePositionVO);
				}
			}
			 if(LocationLevelId.longValue()==5){
					List<Object[]> rtrnMandalList = nominatedPostFinalDAO.getLocationWiseCasteGroupPositionCount(5l,positionId);
					pushMatchCastePositionCntToVO(rtrnMandalList,resultList);
					List<Object[]> rtrnMncpaltyCrprtnList = nominatedPostFinalDAO.getLocationWiseCasteGroupPositionCount(6l,positionId);
					pushMatchCastePositionCntToVO(rtrnMncpaltyCrprtnList,resultList);
				}else{
					List<Object[]> rtrnObjList = nominatedPostFinalDAO.getLocationWiseCasteGroupPositionCount(LocationLevelId,positionId);
					pushMatchCastePositionCntToVO(rtrnObjList,resultList);
				}
		 }catch(Exception e) {
			 LOG.info("Error occured at getLocationWiseCasteGroupPositionCount() in NominatedPostMainDashboardService class ",e);
		}
		return resultList;
	}
 public List<CastePositionVO> getdefaultCasteGroupList(Long locationLevelId){
	 List<CastePositionVO> defaultCasteGroupList = new ArrayList<CastePositionVO>();
	   try{
		  List<Object[]> rtrnObjCsteGrupList = nominatedPostFinalDAO.getCasteGroup(locationLevelId); 
		   if(rtrnObjCsteGrupList != null && rtrnObjCsteGrupList.size() > 0){
			   for (Object[] obj : rtrnObjCsteGrupList) {
				CastePositionVO vo = new CastePositionVO();
				  vo.setCasteId(commonMethodsUtilService.getLongValueForObject(obj[0]));
				  vo.setCasteName(commonMethodsUtilService.getStringValueForObject(obj[1]));
				  defaultCasteGroupList.add(vo);
			}
		   }
	   }catch(Exception e){
		   LOG.info("Error occured at getdefaultCasteGroupList() in NominatedPostMainDashboardService class ",e); 
	   }
	return defaultCasteGroupList;
 }
   public List<CastePositionVO> setPositionDataToVO(List<Object[]> rtrnObjList){
	   List<CastePositionVO> finalList = new ArrayList<CastePositionVO>(0);
	   
	   Map<Long,Map<Long,CastePositionVO>> castePositionMap = new HashMap<Long,Map<Long,CastePositionVO>>(0);
	   Map<Long,CastePositionVO> defaultCastmap = new HashMap<Long, CastePositionVO>();
	   
		Map<Long,String> positionMap = new HashMap<Long, String>(0);
		try{
			 if(rtrnObjList != null && !rtrnObjList.isEmpty()){
				   for(Object[] obj : rtrnObjList) {
					  Map<Long,CastePositionVO> casteMap = castePositionMap.get((Long)obj[2]);
					   if(casteMap == null){
						   casteMap = new HashMap<Long, CastePositionVO>();
						   castePositionMap.put((Long)obj[2], casteMap);
					   }
					         CastePositionVO VO = new CastePositionVO();
					         VO.setCasteId(commonMethodsUtilService.getLongValueForObject(obj[0]));
					         VO.setCasteName(commonMethodsUtilService.getStringValueForObject(obj[1]));
					         VO.setPositionId(commonMethodsUtilService.getLongValueForObject(obj[2]));
					         VO.setPositionName(commonMethodsUtilService.getStringValueForObject(obj[3]));
					         VO.setCount(commonMethodsUtilService.getLongValueForObject(obj[4]));
					         casteMap.put((Long)obj[0], VO);
				}
			   }
		   List<Position> positionList = positionDAO.getAll();
		    if(positionList != null && !positionList.isEmpty()){
		    	for (Position position : positionList) {
					positionMap.put(position.getPositionId(), position.getPositionName());
				}
		    }
	
			if(positionMap != null && positionMap.size() > 0){
				for (Entry<Long, String> entrySet : positionMap.entrySet()) {
					  Map<Long,CastePositionVO> casteMap = castePositionMap.get(entrySet.getKey());
					    if(casteMap == null){
					    	 CastePositionVO castePositionVO = new CastePositionVO();
					    	 castePositionVO.setPositionId(entrySet.getKey());
					    	 castePositionVO.setPositionName(entrySet.getValue());
					    	  finalList.add(castePositionVO);
					    }else{
					    	    CastePositionVO positionVO = new CastePositionVO();
					    	    positionVO.setPositionId(entrySet.getKey());
					    	    positionVO.setPositionName(entrySet.getValue());
					    	    positionVO.getCasteList().addAll(casteMap.values());
					    	    finalList.add(positionVO);
					    }
				}
			}
			
		}catch(Exception e){
			LOG.info("Error occured at setPositionDataToVO() in NominatedPostMainDashboardService class ",e);	
		}
		 return finalList; 
   }
   /**
	 * @Author  Hyma
	 * @Version NominatedPostProfileService.java  July 28, 2016 05:30:00 PM 
	 * @return List<IdNameVO>
	 * description  { Getting All Status Count By Position Id From Database }
	 */
	public NominatedPostDashboardVO getAllPositionWiseStatus(Long positionId){
		NominatedPostDashboardVO returnVO = new NominatedPostDashboardVO();
		try{
			
		List<Object[]> allNomintdStatslist = nominatedPostStatusDAO.getAllNominatedStatusList();
		if(commonMethodsUtilService.isListOrSetValid(allNomintdStatslist)){
			List<NominatedPostDashboardVO> nominatedStatsVoList = new ArrayList<NominatedPostDashboardVO>();
			String[] setterPropertiesList = {"statusId","statusName"};
			nominatedStatsVoList = (List<NominatedPostDashboardVO>) setterAndGetterUtilService.setValuesToVO(allNomintdStatslist, setterPropertiesList, "com.itgrids.partyanalyst.dto.NominatedPostDashboardVO");
			returnVO.setNominatedStatusList(nominatedStatsVoList);
		}
		
		List<Object[]> nomintdStats = nominatedPostDAO.getNominatdPostStatusCntByPosition(positionId);
		if(commonMethodsUtilService.isListOrSetValid(nomintdStats)){
			for(Object[] obj : nomintdStats){
			NominatedPostDashboardVO matchedVO = (NominatedPostDashboardVO)setterAndGetterUtilService.getMatchedVOfromList(returnVO.getNominatedStatusList(), "statusId", commonMethodsUtilService.getStringValueForObject(obj[0]));
			if(matchedVO != null){
				matchedVO.setStatusCount(commonMethodsUtilService.getLongValueForObject(obj[2]));
			}
		}
	}
		
		List<Object[]> allApplctnStatsList = applicationStatusDAO.getAllApplicationStatusList();
		if(commonMethodsUtilService.isListOrSetValid(allApplctnStatsList)){
			List<NominatedPostDashboardVO> appctnStatusVoList = new ArrayList<NominatedPostDashboardVO>();
			String[] setterPropertiesList = {"statusId","statusName"};
			appctnStatusVoList = (List<NominatedPostDashboardVO>) setterAndGetterUtilService.setValuesToVO(allApplctnStatsList, setterPropertiesList, "com.itgrids.partyanalyst.dto.NominatedPostDashboardVO");
			returnVO.setApplicatnStatsList(appctnStatusVoList);
		}
		
		List<Object[]> appctnStatus = nominatedPostApplicationDAO.getApplicationStatusCntByPositionId(positionId);
		if(commonMethodsUtilService.isListOrSetValid(appctnStatus)){
			for(Object[] obj : appctnStatus){
			NominatedPostDashboardVO matchedVO = (NominatedPostDashboardVO)setterAndGetterUtilService.getMatchedVOfromList(returnVO.getApplicatnStatsList(), "statusId", commonMethodsUtilService.getStringValueForObject(obj[0]));
			if(matchedVO != null){
				matchedVO.setStatusCount(commonMethodsUtilService.getLongValueForObject(obj[2]));
			}
		}
	}
} catch (Exception e) {
	e.printStackTrace();
	LOG.error("Exception raised at getAllPositionWiseStatus() method of NominatedPostProfileService", e);
}
		return returnVO;
}
	/**
	 * @Author  Hyma
	 * @Version NominatedPostProfileService.java  July 28, 2016 05:30:00 PM 
	 * @return List<IdNameVO>
	 * description  { Getting All Positions From Database }
	 */
	public List<IdNameVO> getPositions(){  
		List<IdNameVO> returnList = new ArrayList<IdNameVO>();
		try{
		List<Object[]> list = positionDAO.getAllPositions();
		if(commonMethodsUtilService.isListOrSetValid(list)){
			String[] setterPropertiesList = {"id","name"};
			returnList = (List<IdNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdNameVO");
		}
		}catch(Exception e){
			e.printStackTrace();  
			LOG.error("Exception Occured in getPositions()", e);
		}
		return returnList;
	}
	public List<IdAndNameVO> getCastGroupList(){
		LOG.info("Entered into getCastGroupList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> cstGrpList = new ArrayList<IdAndNameVO>();
			List<Object[]> castGroupList = nominationPostCandidateDAO.getCastGroupList();
			if(castGroupList != null && castGroupList.size() > 0){
				setDataToVO(castGroupList, cstGrpList);
			}
			return cstGrpList;  
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getCastGroupList()", e);
		}
		return null;    
	}
	public List<IdAndNameVO> getApplicationStatusList(){
		LOG.info("Entered into getApplicationStatusList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> appStatusList = new ArrayList<IdAndNameVO>();
			List<Object[]> applicationStatusList = nominatedPostFinalDAO.getApplicationStatusList();
			if(applicationStatusList != null && applicationStatusList.size() > 0){
				setDataToVO(applicationStatusList, appStatusList);  
			}
			return appStatusList;  
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getCastGroupList()", e);
		}
		return null;
	}
	public List<IdAndNameVO> getPositionList(){
		LOG.info("Entered into getPositionList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> pstnList = new ArrayList<IdAndNameVO>();
			List<Object[]> positionList = nominatedPostMemberDAO.getPositionList();
			if(positionList != null && positionList.size() > 0){
				setDataToVO(positionList, pstnList);
			}
			return pstnList;  
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getPositionList()", e);
		}
		return null;
	}
	public List<IdAndNameVO> getLocationLevelList(){
		LOG.info("Entered into getLocationLevelList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> locationLvlList = new ArrayList<IdAndNameVO>();
			List<Object[]> locationLevelList = nominatedPostMemberDAO.getLocationLevelList();
			if(locationLevelList != null && locationLevelList.size() > 0){
				setDataToVO(locationLevelList, locationLvlList);
			}
			return locationLvlList;  
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getLocationLevelList()", e);
		}
		return null;
	}
	public List<IdAndNameVO> getDepartmentList(){
		LOG.info("Entered into getDepartmentList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> deptList = new ArrayList<IdAndNameVO>();
			List<Object[]> departmentList = nominatedPostMemberDAO.getDepartmentList();
			if(departmentList != null && departmentList.size() > 0){
				setDataToVO(departmentList, deptList);
			}
			return deptList;  
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getDepartmentList()", e);
		}
		return null;
	}
	public List<IdAndNameVO> getBoardList(){
		LOG.info("Entered into getboardList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> brdList = new ArrayList<IdAndNameVO>();
			List<Object[]> boardList = nominatedPostMemberDAO.getBoardList();
			if(boardList != null && boardList.size() > 0){
				setDataToVO(boardList, brdList);
			}
			return brdList;  
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getBoardList()", e);
		}
		return null;
	}
	public void setDataToVO(List<Object[]> objectArrayList, List<IdAndNameVO> voList){
		try{
			IdAndNameVO idAndNameVO = null;
			for(Object[] castGroup : objectArrayList){
				idAndNameVO = new IdAndNameVO();
				idAndNameVO.setId(castGroup[0] != null ? (Long)castGroup[0] : 0l);
				idAndNameVO.setName(castGroup[1] != null ? castGroup[1].toString() : "");
				voList.add(idAndNameVO);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getPositions() of NominatedPostMainDashboardService", e);
		}
	}
	
 
   
}
