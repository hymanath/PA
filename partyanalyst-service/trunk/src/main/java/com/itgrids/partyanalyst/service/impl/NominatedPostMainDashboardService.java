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
import com.itgrids.partyanalyst.dao.INominatedPostStatusDAO;
import com.itgrids.partyanalyst.dao.IPositionDAO;
import com.itgrids.partyanalyst.dto.CastePositionVO;
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
	@Override
	public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId) {
	
		List<CastePositionVO> castePositionVoList = null;
		try{
			 List<Object[]> rtrnObjList = nominatedPostApplicationDAO.getLocationWiseCastePositionCount(LocationLevelId);
			 castePositionVoList = setPositionDataToVO(rtrnObjList);
		 }catch(Exception e) {
		   LOG.info("Error occured at getLocationWiseCastePositionCount() in NominatedPostMainDashboardService class ",e);	
		}
		return castePositionVoList;
	}
	@Override
	public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId) {
		List<CastePositionVO> casteGroupVoList = null;
		try{
			 List<Object[]> rtrnObjList = nominatedPostApplicationDAO.getLocationWiseCasteGroupPositionCount(LocationLevelId);
			 casteGroupVoList = setPositionDataToVO(rtrnObjList);
		 }catch(Exception e) {
			 LOG.info("Error occured at getLocationWiseCasteGroupPositionCount() in NominatedPostMainDashboardService class ",e);
		}
		return casteGroupVoList;
	}
   public List<CastePositionVO> setPositionDataToVO(List<Object[]> rtrnObjList){
	   List<CastePositionVO> finalList = new ArrayList<CastePositionVO>(0);
	   
	   Map<Long,Map<Long,CastePositionVO>> castePositionMap = new HashMap<Long,Map<Long,CastePositionVO>>(0);
	   
	   
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
					         
					         //VO.setCasteList(defaultCastes());
					         
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
   
   /*public List<CastePositionVO> defaultCastes(){
	   Long<CastePositionVO> finalList = new ArrayList<E>();
	   try{
		   
		   nominatedPostCandidate
		   
	   }catch (Exception e) {
		// TODO: handle exception
	}
	   return finalList;
   }*/
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
   
}
