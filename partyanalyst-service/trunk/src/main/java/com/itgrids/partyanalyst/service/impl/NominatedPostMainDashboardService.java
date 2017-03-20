package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
import com.itgrids.partyanalyst.dao.IBoardLevelDAO;
import com.itgrids.partyanalyst.dao.ICasteCategoryDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.INominatedPostAgeRangeDAO;
import com.itgrids.partyanalyst.dao.INominatedPostApplicationDAO;
import com.itgrids.partyanalyst.dao.INominatedPostDAO;
import com.itgrids.partyanalyst.dao.INominatedPostFinalDAO;
import com.itgrids.partyanalyst.dao.INominatedPostMemberDAO;
import com.itgrids.partyanalyst.dao.INominatedPostStatusDAO;
import com.itgrids.partyanalyst.dao.INominationPostCandidateDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPositionDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCandidateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.CastePositionVO;
import com.itgrids.partyanalyst.dto.GeoLevelListVO;
import com.itgrids.partyanalyst.dto.GeoLevelReportVO;
import com.itgrids.partyanalyst.dto.IdAndNameVO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.NominatedPostDashboardVO;
import com.itgrids.partyanalyst.dto.NominatedPostVO;
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
	private INominatedPostAgeRangeDAO nominatedPostAgeRangeDAO;
	private IBoardLevelDAO boardLevelDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	private 
	DecimalFormat decimalFormat = new DecimalFormat("#.##");
	public ITdpCadreCandidateDAO tdpCadreCandidateDAO;
	private ICasteCategoryDAO casteCategoryDAO;

	
	
	public ICasteCategoryDAO getCasteCategoryDAO() {
		return casteCategoryDAO;
	}


	public void setCasteCategoryDAO(ICasteCategoryDAO casteCategoryDAO) {
		this.casteCategoryDAO = casteCategoryDAO;
	}


	public IBoardLevelDAO getBoardLevelDAO() {
		return boardLevelDAO;
	}


	public void setBoardLevelDAO(IBoardLevelDAO boardLevelDAO) {
		this.boardLevelDAO = boardLevelDAO;
	}


	public ITdpCadreCandidateDAO getTdpCadreCandidateDAO() {
		return tdpCadreCandidateDAO;
	}


	public void setTdpCadreCandidateDAO(ITdpCadreCandidateDAO tdpCadreCandidateDAO) {
		this.tdpCadreCandidateDAO = tdpCadreCandidateDAO;
	}
	
	public INominatedPostAgeRangeDAO getNominatedPostAgeRangeDAO() {
		return nominatedPostAgeRangeDAO;
	}
	public void setNominatedPostAgeRangeDAO(
			INominatedPostAgeRangeDAO nominatedPostAgeRangeDAO) {
		this.nominatedPostAgeRangeDAO = nominatedPostAgeRangeDAO;
	}
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
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public IStateDAO getStateDAO() {
		return stateDAO;
	}
	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
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
	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}
	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}
	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}
	/**
	 * @Author  Santosh
	 * @Version NominatedPostMainDashboardService.java  july 30, 2016 06:50:00 PM 
	 * @return List<CastePositionVO>
	 * description  { This service is used to get location wise caste position count }
	 */
	@Override
	public List<CastePositionVO> getLocationWiseCastePositionCount(Long LocationLevelId,Long positionId,Long stateId) {
	
		List<CastePositionVO> resultList = new ArrayList<CastePositionVO>();
		try{
			List<Object[]> casteList = nominatedPostFinalDAO.getCandidateCasteList(LocationLevelId,stateId,positionId);
			List<Object[]> positionList = positionDAO.getAllPositions();
			if(casteList != null && casteList.size() > 0)
			{
				CastePositionVO castePositionVO = null;
				for(Object[] params : casteList)
				{
					castePositionVO = new CastePositionVO();
					castePositionVO.setCasteId(commonMethodsUtilService.getLongValueForObject(params[0]));
					castePositionVO.setCasteName(commonMethodsUtilService.getStringValueForObject(params[1]));
					castePositionVO.setPositionList(getDefaultPositionList(positionList,positionId));
					resultList.add(castePositionVO);
				}
			}
			if(LocationLevelId.longValue()==5){
				List<Object[]> rtrnMandalList = nominatedPostFinalDAO.getLocationWiseCastePositionCount(5l,positionId,stateId);//For Mandal and Muncipality/Corporation
				pushMatchCastePositionCntToVO(rtrnMandalList,resultList);
			}else{
				List<Object[]> rtrnObjList = nominatedPostFinalDAO.getLocationWiseCastePositionCount(LocationLevelId,positionId,stateId);
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
					
					for(CastePositionVO vo : getCasteMatchedVO(resultList,casteStateId).getPositionList())
					{
						if(vo.getPositionId().equals(positionId.longValue())){
							vo.setCount(vo.getCount()+count);
						}
					}
				}
			}
	 }catch(Exception e){
		 LOG.error("Error occured at pushMatchCastePositionCntToVO() in NominatedPostMainDashboardService class ",e); 
	 }
	}
	public CastePositionVO getCasteMatchedVO(List<CastePositionVO> list,Long casteStateId)
	{
		try{
			for(CastePositionVO vo : list)
				if(vo.getCasteId().equals(casteStateId)){
					return vo;
				}
		}catch (Exception e) {
			LOG.error("Exception occured in getMatchedVO ",e);
		}
		return null;
	}
	public List<CastePositionVO> getDefaultPositionList(List<Object[]> positionList,Long positionId){
		List<CastePositionVO> positionDefaultList = new ArrayList<CastePositionVO>(0);
		
		if(positionList != null && positionList.size() > 0)
		{
			CastePositionVO postionVO = null;
			for(Object[] params : positionList)
			{
				postionVO = new CastePositionVO();
				postionVO.setPositionId(commonMethodsUtilService.getLongValueForObject(params[0]));
				postionVO.setPositionName(commonMethodsUtilService.getStringValueForObject(params[1]));
				if(positionId != null && positionId.longValue()>0L){
					if(positionId.longValue() == postionVO.getPositionId().longValue())
						positionDefaultList.add(postionVO);
				}
				else
					positionDefaultList.add(postionVO);
			}
		}
		return positionDefaultList;
		
	}
	/**
	 * @Author  Santosh
	 * @Version NominatedPostMainDashboardService.java  july 30, 2016 06:50:00 PM 
	 * @return List<CastePositionVO>
	 * description  { This service is used to get location wise caste group position count }
	 */
	@Override
	public List<CastePositionVO> getLocationWiseCasteGroupPositionCount(Long LocationLevelId,Long positionId,Long stateId) {
		List<CastePositionVO> resultList = new ArrayList<CastePositionVO>(0);
		try{
			 List<Object[]> rtrnObjCsteGrupList = nominatedPostFinalDAO.getCasteGroup(LocationLevelId,stateId,positionId);
			List<Object[]> positionList = positionDAO.getAllPositions();
			if(positionList != null && positionList.size() > 0)
			{
				CastePositionVO castePositionVO = null;
				for(Object[] params : positionList)
				{
					castePositionVO = new CastePositionVO();
					castePositionVO.setPositionId((Long)params[0]);
					castePositionVO.setPositionName(params[1].toString());
					castePositionVO.setCasteList(getdefaultCasteGroupList(rtrnObjCsteGrupList));
					if(positionId != null && positionId.longValue()>0L){
						if(positionId.longValue() == castePositionVO.getPositionId().longValue())
							resultList.add(castePositionVO);
					}
					else
						resultList.add(castePositionVO);
				}
			}
			 if(LocationLevelId.longValue()==5){
					List<Object[]> rtrnMandalList = nominatedPostFinalDAO.getLocationWiseCasteGroupPositionCount(5l,positionId,stateId); //For Mandal and Muncipality/Corporation
					pushMatchCasteGroupPositionCntToVO(rtrnMandalList,resultList);
				}else{
					List<Object[]> rtrnObjList = nominatedPostFinalDAO.getLocationWiseCasteGroupPositionCount(LocationLevelId,positionId,stateId);
					pushMatchCasteGroupPositionCntToVO(rtrnObjList,resultList);
				}
		 }catch(Exception e) {
			 LOG.info("Error occured at getLocationWiseCasteGroupPositionCount() in NominatedPostMainDashboardService class ",e);
		}
		return resultList;
	}
 public List<CastePositionVO> getdefaultCasteGroupList(List<Object[]> rtrnObjCsteGrupList){
	 List<CastePositionVO> defaultCasteGroupList = new ArrayList<CastePositionVO>();
	   try{
		   
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
 public void pushMatchCasteGroupPositionCntToVO(List<Object[]> rtrnObjList,List<CastePositionVO> resultList){
	 
	 try{
		 if(rtrnObjList != null && rtrnObjList.size() > 0)
			{
				for(Object[] params : rtrnObjList)
				{
					Long positionId = (Long)params[2];
					Long casteStateId = (Long)params[0];
					Long count = params[4] !=null ? (Long)params[4]:0l;
					
					for(CastePositionVO vo : getCasteGroupMatchedVO(resultList,positionId).getCasteList())
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
	public CastePositionVO getCasteGroupMatchedVO(List<CastePositionVO> list,Long positionId)
	{
		try{
			for(CastePositionVO vo : list)
				if(vo.getPositionId().equals(positionId)){
					return vo;
				}
		}catch (Exception e) {
			LOG.error("Exception occured in getCasteGroupMatchedVO ",e);
		}
		return null;
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
	 * @Version NominatedPostMainDashboardService.java  July 28, 2016 05:30:00 PM 
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
	 * @Version NominatedPostMainDashboardService.java  July 28, 2016 05:30:00 PM 
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
	public List<IdAndNameVO> getDepartmentList(Long boardLevelId){
		LOG.info("Entered into getDepartmentList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> deptList = new ArrayList<IdAndNameVO>();
			List<Object[]> departmentList = nominatedPostMemberDAO.getDepartmentList(boardLevelId);  
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
	public List<IdAndNameVO> getBoardsList(List<Long> deptId,Long boardLevelId){
		LOG.info("Entered into getboardList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> brdList = new ArrayList<IdAndNameVO>();
			List<Object[]> boardList = nominatedPostMemberDAO.getBoardsList(deptId,boardLevelId);
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
			LOG.error("Exception Occured in setDataToVO() of NominatedPostMainDashboardService", e);
		}
	}
	//getNominatedCandidateGroupByDist    
	public List<NominatedPostVO> getNominatedPostCandidateLocationWiseDetails(Long positionId, Long locationLevelId, Long deptId, Long corporationId, Long castGroupId, Long positionStatusId, Long stateId, String locationLevelName){
		LOG.info("Entered into getNominatedPostCandidateLocationWiseDetails() of NominatedPostMainDashboardService.");
		try{
			
			Map<Long,NominatedPostVO> idNominatedVOMap = new HashMap<Long,NominatedPostVO>();
			NominatedPostVO  nominatedPostVO = null;
			List<Object[]> locationList = new ArrayList<Object[]>(0);
			if(locationLevelName.equalsIgnoreCase("state")){
				locationList = stateDAO.getState(stateId);
			}
			else if(locationLevelName.equalsIgnoreCase("District")){
				locationList = districtDAO.getAllDistrictList(stateId);   
			}
			else if(locationLevelName.equalsIgnoreCase("Assembly")){
				locationList = constituencyDAO.getAllConstituencyList(stateId);   
			}
			else if(locationLevelName.equalsIgnoreCase("Mandal")){
				locationList = tehsilDAO.getAllTehsilList(stateId);   
			}
			else if(locationLevelName.equalsIgnoreCase("Muncipality/Corporation")){
				locationList = localElectionBodyDAO.getAllLocalElectionBodyList(stateId);
			}
			else if(locationLevelName.equalsIgnoreCase("Village")){
				locationList = panchayatDAO.getAllPanchayatList(stateId);   
			}  
			
			List<Object[]> nominatedCandidateLocationDetails = nominatedPostFinalDAO.getNominatedCandidateLocationDetails(positionId, locationLevelId, deptId, corporationId, castGroupId, positionStatusId, stateId, locationLevelName);
			List<Object[]> nominatedCandidateGroupByLocationAndGender = nominatedPostFinalDAO.getNominatedCandidateGroupByLocationAndGender(positionId, locationLevelId, deptId, corporationId, castGroupId, positionStatusId, stateId, locationLevelName);
			List<Object[]> nominatedCandidateGroupByLocationAndAgeGroup = nominatedPostFinalDAO.getNominatedCandidateGroupByLocationAndAgeGroup(positionId, locationLevelId, deptId, corporationId, castGroupId, positionStatusId, stateId, locationLevelName);
			for(Object[] locationIdAndName : locationList){
				nominatedPostVO = new NominatedPostVO();
				nominatedPostVO.setDistrictId(locationIdAndName[0] != null ? (Long)locationIdAndName[0] : 0l);   
				nominatedPostVO.setName(locationIdAndName[1] != null ? locationIdAndName[1].toString() : "");
				idNominatedVOMap.put((Long)locationIdAndName[0], nominatedPostVO);     
			}
			Long locationId = 0l;
			for(Object[] candidateGroupByLocation : nominatedCandidateLocationDetails){
				locationId = (Long)candidateGroupByLocation[0]; 
				nominatedPostVO = idNominatedVOMap.get(locationId);
				nominatedPostVO.setTotalPositions((Long)candidateGroupByLocation[2]);
				nominatedPostVO.setContains("true");    
			}
			for(Object[] candidateGroupByLocationAndGender : nominatedCandidateGroupByLocationAndGender){
				locationId = (Long)candidateGroupByLocationAndGender[0];
				nominatedPostVO = idNominatedVOMap.get(locationId);
				if(candidateGroupByLocationAndGender[2] != null){
					if(candidateGroupByLocationAndGender[2].toString().equalsIgnoreCase("male") || candidateGroupByLocationAndGender[2].toString().equalsIgnoreCase("M")){
						nominatedPostVO.setMaleCount((Long)candidateGroupByLocationAndGender[3]);
					}else{
						nominatedPostVO.setFemaleCount((Long)candidateGroupByLocationAndGender[3]);  
					}
				}
				
			} 
			for(Object[] candidateGroupByLocationAndAgeGroup : nominatedCandidateGroupByLocationAndAgeGroup){
				if(candidateGroupByLocationAndAgeGroup[0] != null){
					locationId = (Long)candidateGroupByLocationAndAgeGroup[0];  
				}
				locationId = (Long)candidateGroupByLocationAndAgeGroup[0];
				nominatedPostVO = idNominatedVOMap.get(locationId);
				if(candidateGroupByLocationAndAgeGroup[2] != null){
					Long count = (Long)candidateGroupByLocationAndAgeGroup[2];
					if(count != null){
						if(count.equals(1l)){
							nominatedPostVO.setFirstAgeGroupCount((Long)candidateGroupByLocationAndAgeGroup[3]);
						}else if(count.equals(2l)){
							nominatedPostVO.setSecondAgeGroupCount((Long)candidateGroupByLocationAndAgeGroup[3]);
						}else if(count.equals(3l)){
							nominatedPostVO.setThirdAgeGroupCount((Long)candidateGroupByLocationAndAgeGroup[3]);
						}else if(count.equals(4l)){
							nominatedPostVO.setFourthAgeGroupCount((Long)candidateGroupByLocationAndAgeGroup[3]);
						}else if(count.equals(5l)){
							nominatedPostVO.setFifthAgeGroupCount((Long)candidateGroupByLocationAndAgeGroup[3]);
						}
					}
				}
			}
			Collection<NominatedPostVO> nominatedPostVOList = idNominatedVOMap.values();
			List<NominatedPostVO> nominatedPostVOFinalList = new ArrayList<NominatedPostVO>(0);
			for(NominatedPostVO nominatedPostVOTemp : nominatedPostVOList){
				if(nominatedPostVOTemp.getContains().equalsIgnoreCase("true")){
					nominatedPostVOFinalList.add(nominatedPostVOTemp);
				}
			}  
		return nominatedPostVOFinalList;  
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getNominatedPostCandidateLocationWiseDetails() of NominatedPostMainDashboardService", e);
		}
		return null;
	}
	public List<NominatedPostVO> getNominatedPostCandidatePositionWiseDetails(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long locationId, String locationLevelName){
		LOG.info("Entered into getNominatedPostCandidatePositionWiseDetails() of NominatedPostMainDashboardService.");
		try{ 
			Map<Long,NominatedPostVO> idNominatedVOMap = new HashMap<Long,NominatedPostVO>();
			NominatedPostVO  nominatedPostVO = null;
			// Preparing Template For Positions  
			List<Object[]> positionsList = positionDAO.getAllPositions();  
			List<Object[]> nominatedCandidatePositionDetails = nominatedPostFinalDAO.getNominatedCandidatePositionDetails(positionId, boardLevelId, deptId, boardId, castegroupId, positionStatusId, stateId, locationId,  locationLevelName);
			List<Object[]> nominatedCandidateGroupByPositionAndGender = nominatedPostFinalDAO.getNominatedCandidateGroupByPositionAndGender(positionId, boardLevelId, deptId, boardId, castegroupId, positionStatusId, stateId, locationId,  locationLevelName);
			List<Object[]> nominatedCandidateGroupByPositionAndAgeGroup = nominatedPostFinalDAO.getNominatedCandidateGroupByPositionAndAgeGroup(positionId, boardLevelId, deptId, boardId, castegroupId, positionStatusId, stateId, locationId,  locationLevelName);
 
			for(Object[] positionIdAndName : positionsList){  
				nominatedPostVO = new NominatedPostVO();
				nominatedPostVO.setDistrictId(positionIdAndName[0] != null ? (Long)positionIdAndName[0] : 0l);   
				nominatedPostVO.setName(positionIdAndName[1] != null ? positionIdAndName[1].toString() : "");
				idNominatedVOMap.put((Long)positionIdAndName[0], nominatedPostVO);     
			}
			
			Long pstnId = 0l;
			for(Object[] candidateGroupByPosition : nominatedCandidatePositionDetails){
				pstnId = (Long)candidateGroupByPosition[0]; 
				nominatedPostVO = idNominatedVOMap.get(pstnId);
				nominatedPostVO.setTotalPositions((Long)candidateGroupByPosition[2]);
				nominatedPostVO.setContains("true");    
			}
			for(Object[] candidateGroupByPositionAndGender : nominatedCandidateGroupByPositionAndGender){
				pstnId = (Long)candidateGroupByPositionAndGender[0];
				nominatedPostVO = idNominatedVOMap.get(pstnId);
				if(candidateGroupByPositionAndGender[2] != null){
					if(candidateGroupByPositionAndGender[2].toString().equalsIgnoreCase("male") || candidateGroupByPositionAndGender[2].toString().equalsIgnoreCase("M")){
						nominatedPostVO.setMaleCount((Long)candidateGroupByPositionAndGender[3]);
					}else{
						nominatedPostVO.setFemaleCount((Long)candidateGroupByPositionAndGender[3]);  
					}
				}
				
			} 
			for(Object[] candidateGroupByPositionAndAgeGroup : nominatedCandidateGroupByPositionAndAgeGroup){
				if(candidateGroupByPositionAndAgeGroup[0] != null){
					pstnId = (Long)candidateGroupByPositionAndAgeGroup[0];  
				}
				nominatedPostVO = idNominatedVOMap.get(pstnId);
				if(candidateGroupByPositionAndAgeGroup[2] != null){
					Long count = (Long)candidateGroupByPositionAndAgeGroup[2];
					if(count != null){
						if(count.equals(1l)){
							nominatedPostVO.setFirstAgeGroupCount((Long)candidateGroupByPositionAndAgeGroup[3]);
						}else if(count.equals(2l)){
							nominatedPostVO.setSecondAgeGroupCount((Long)candidateGroupByPositionAndAgeGroup[3]);
						}else if(count.equals(3l)){
							nominatedPostVO.setThirdAgeGroupCount((Long)candidateGroupByPositionAndAgeGroup[3]);
						}else if(count.equals(4l)){
							nominatedPostVO.setFourthAgeGroupCount((Long)candidateGroupByPositionAndAgeGroup[3]);
						}else if(count.equals(5l)){
							nominatedPostVO.setFifthAgeGroupCount((Long)candidateGroupByPositionAndAgeGroup[3]);
						}
					}
				}
			}
			Collection<NominatedPostVO> nominatedPostVOList = idNominatedVOMap.values();
			List<NominatedPostVO> nominatedPostVOFinalList = new ArrayList<NominatedPostVO>(0);
			for(NominatedPostVO nominatedPostVOTemp : nominatedPostVOList){
				if(nominatedPostVOTemp.getContains().equalsIgnoreCase("true")){
					nominatedPostVOFinalList.add(nominatedPostVOTemp);
				}
			}   
			return nominatedPostVOFinalList;  	
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getNominatedPostCandidatePositionWiseDetails() of NominatedPostMainDashboardService", e);
		}
		return null;
	}
	
	/**
	 * @Author  Hyma
	 * @Version NominatedPostMainDashboardService.java  July 28, 2016 05:30:00 PM 
	 * @return List<NominatedPostDashboardVO>
	 * description  { Getting All Position Count For District From Database }
	 *//*
	public List<NominatedPostDashboardVO> getPositionsForDistrict(Long positionId,Long boardLevelId,Long deptId,Long boardId,Long castegroupId,Long positionStatusId,Long stateId,Long districtId, String locationLevelName){
		
		List<NominatedPostDashboardVO> returnList = new ArrayList<NominatedPostDashboardVO>();
		try{
			
			// Preparing Template For Positions 
			List<Object[]> positionsList = positionDAO.getAllPositions();
			if(commonMethodsUtilService.isListOrSetValid(positionsList)){
				String[] setterPropertiesList = {"statusId","statusName"};
				returnList = (List<NominatedPostDashboardVO>) setterAndGetterUtilService.setValuesToVO(positionsList, setterPropertiesList, "com.itgrids.partyanalyst.dto.NominatedPostDashboardVO");
			}
			
			// Preparing Template For Age Ranges 
			List<NominatedPostDashboardVO> ageRangesList = new ArrayList<NominatedPostDashboardVO>();
			List<Object[]> ageList = nominatedPostAgeRangeDAO.getAllAgeRanges();
			if(commonMethodsUtilService.isListOrSetValid(ageList)){
				String[] setterPropertiesList = {"ageRangeId","ageRange"};
				ageRangesList = (List<NominatedPostDashboardVO>) setterAndGetterUtilService.setValuesToVO(ageList, setterPropertiesList, "com.itgrids.partyanalyst.dto.NominatedPostDashboardVO");
			}
			
			for(NominatedPostDashboardVO position : returnList){
				position.setAgeList(ageRangesList);
			}
			List<Object[]> PostnCntForGender = nominatedPostFinalDAO.getPositionCountForGender(positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,"Gender");
			Long femaleCnt = 0l;
			Long maleCnt = 0l;
			Long totalCnt = 0l;
			for(Object[] obj : PostnCntForGender){
				NominatedPostDashboardVO positionVO = (NominatedPostDashboardVO)setterAndGetterUtilService.getMatchedVOfromList(returnList, "statusId", commonMethodsUtilService.getStringValueForObject(obj[0]));
				if(positionVO != null){
					if(obj[1].toString().equalsIgnoreCase("Female")){
						positionVO.setFemaleCount(femaleCnt + commonMethodsUtilService.getLongValueForObject(obj[2]));
					}else if(obj[1].toString().equalsIgnoreCase("Male")){
						positionVO.setMaleCount(maleCnt + commonMethodsUtilService.getLongValueForObject(obj[2]));
					}
					positionVO.setTotalCnt(totalCnt+positionVO.getMaleCount()+positionVO.getFemaleCount());
				}
			}
			
			List<Object[]> ageRangeList = nominatedPostFinalDAO.getPositionCountForGender(positionId,boardLevelId,deptId,boardId,castegroupId,positionStatusId,stateId,districtId,"Age");
			Long ageRangeCnt = 0l;
			for(Object[] obj : ageRangeList){
				NominatedPostDashboardVO positionVo = (NominatedPostDashboardVO)setterAndGetterUtilService.getMatchedVOfromList(returnList, "statusId", commonMethodsUtilService.getStringValueForObject(obj[0]));
				if(positionVo != null){
					NominatedPostDashboardVO ageRangeVO = (NominatedPostDashboardVO)setterAndGetterUtilService.getMatchedVOfromList(positionVo.getAgeList(), "ageRangeId", commonMethodsUtilService.getStringValueForObject(obj[1]));
					if(ageRangeVO != null){
						ageRangeVO.setAgeCount(ageRangeCnt + commonMethodsUtilService.getLongValueForObject(obj[3]));
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception Occured in getPositionsForDistrict() of NominatedPostMainDashboardService", e);
		}
		return returnList;
	}*/
	/**
	 * @Author  Santosh
	 * @Version NominatedPostMainDashboardService.java  Aug 2, 2016 06:50:00 PM 
	 * @return CastePositionVO
	 * description  { This service is used to get  position and application overview count position wise }
	 */
public CastePositionVO getPositionAndApplicationDetailsCntPositionWise(Long boardLevelId,Long positionId,String reportType,Long stateId){
	
	 CastePositionVO resultVO = new CastePositionVO();
	 try{
		 resultVO  =poulateCommonData(positionId,boardLevelId,stateId,reportType);
		 if(resultVO!=null){
			 if(reportType != null && reportType.equalsIgnoreCase("Position")){
				 calculatePositionPercentage(resultVO);	 //calculating position percentage
			 }else if(reportType != null && reportType.equalsIgnoreCase("Application")){
				 calculateApplicationPercentage(resultVO);  //calculating application percentage 
			 } 
		 }
	  }catch(Exception e){
		  LOG.error("Exception Occured in getPositionAndApplicationDetailsCntPositionWise() of NominatedPostMainDashboardService", e);  
	  }
	   return resultVO;
   }
/**
 * @Author  Santosh
 * @Version NominatedPostMainDashboardService.java  Aug 2, 2016 06:50:00 PM 
 * @return CastePositionVO
 * description  { This service is used to get  position and application overview count Location wise }
 */
public CastePositionVO getPositionAndApplicationDetailsCntLocationWise(Long boardLevelId,Long positionId,String reportType,Long stateId){
	  CastePositionVO resultVO = new CastePositionVO();
	  try{
	 	 CastePositionVO positionVO  = poulateCommonData(positionId,boardLevelId,stateId,"Position");
	 //	  positionVO.setTotalOpendPositionCnt(positionVO.getTotalPositionCn()-positionVO.getGoIssuedCnt());//total opened position
	 	 if(positionVO != null){
	 		 calculatePositionPercentage(positionVO); //calculating position percentage
	 	 }
	 	 resultVO.getPositionList().add(positionVO);
	 	 CastePositionVO applicationVO  = poulateCommonData(positionId,boardLevelId,stateId,"Application");
	 	  if(applicationVO != null){
	 		 calculateApplicationPercentage(applicationVO); //calculating application percentage  
	 	  }
	 	 resultVO.getApplicationList().add(applicationVO);
	  }catch(Exception e){
		  LOG.error("Exception Occured in getPositionAndApplicationDetailsCntLocationWise() of NominatedPostMainDashboardService", e);  
	  }
	   return resultVO;
  }
public void calculatePositionPercentage(CastePositionVO resultVO){
	try{
           Long totalPostsCnt = resultVO.getTotalPostsCnt();
          
           Double totalPostCntPer = (totalPostsCnt*100.00)/totalPostsCnt;
		    if(!(totalPostCntPer.isNaN())){
		       resultVO.setTotalPostsCntPer(decimalFormat.format(totalPostCntPer));
		    }
		   Double openCntPer = (resultVO.getOpenPostCnt()*100.00)/totalPostsCnt;
		   if(!(openCntPer.isNaN())){
			   resultVO.setOpenPostCntPer(decimalFormat.format(openCntPer)); 
		   }
		   Double finalizedPer = (resultVO.getConfirmCntCnt()*100.00)/totalPostsCnt;
		   if(!(finalizedPer.isNaN())){
			   resultVO.setConfirmCntPer(decimalFormat.format(finalizedPer));  
		   }
		   Double goIssuedPer = (resultVO.getGoIssuedCnt()*100.00)/totalPostsCnt;
		   if(!(goIssuedPer.isNaN())){
			   resultVO.setGoIssuedPer(decimalFormat.format(goIssuedPer));  
		   }
	}catch(Exception e){
		 LOG.error("Exception Occured in calculatePositionPercentage() of NominatedPostMainDashboardService", e);
	}
}
public void calculateApplicationPercentage(CastePositionVO resultVO){
	    try{
	    	 Long ttlApplicationRcvedCnt  = resultVO.getTotalAppReceivedCnt();
		     Double totalAppRecper = (resultVO.getTotalAppReceivedCnt()*100.00)/ttlApplicationRcvedCnt;
		     if(!(totalAppRecper.isNaN())){
		    	 resultVO.setTotalAppRecevidPer(decimalFormat.format(totalAppRecper)); 
		     }
		     Double rejectedCntper = (resultVO.getRejectedCnt()*100.00)/ttlApplicationRcvedCnt;
		     if(!(rejectedCntper.isNaN())){
		    	 resultVO.setRejectedAppPer(decimalFormat.format(rejectedCntper));
		     }
		     Double completedAppPer = (resultVO.getConfirmCntCnt()*100.00)/ttlApplicationRcvedCnt;
		     if(!(completedAppPer.isNaN())){
		    	 resultVO.setCompletedAppPer(decimalFormat.format(completedAppPer));	 
		     }
		     Double shortListedCntPer = (resultVO.getShortedListedCndtCnt()*100.00)/ttlApplicationRcvedCnt;
		     if(!(shortListedCntPer.isNaN())){
		    	 resultVO.setShortListedCntper(decimalFormat.format(shortListedCntPer)); 
		     }
		     Double readyForFinalReviewCntPer = (resultVO.getReadyForFinalReviewCnt()*100.00)/ttlApplicationRcvedCnt;
		     if(!(readyForFinalReviewCntPer.isNaN())){
		      resultVO.setReadyForFinalReviewCntPer(decimalFormat.format(readyForFinalReviewCntPer));	 
		     }
	    }catch(Exception e){
	    	 LOG.error("Exception Occured in calculateApplicationPercentage() of NominatedPostMainDashboardService", e);	
	 }
}
public CastePositionVO poulateCommonData(Long positionId,Long boardLevelId,Long stateId,String reportType){
	  
	   CastePositionVO resultVO = new  CastePositionVO();
	   try{
		   if(reportType != null && reportType.equalsIgnoreCase("Position")){
			
		   if(boardLevelId != null && boardLevelId.longValue()==5){
			   List<Object[]> rtrnManDalRsltList = nominatedPostDAO.getTotalPositionCntPositionAndLocationWise(positionId,5l,stateId);
			   setPositionCntDataToVO(null,rtrnManDalRsltList,null,null,resultVO);
			   List<Object[]> rtrnMncpltyPositionCntList = nominatedPostDAO.getTotalPositionCntPositionAndLocationWise(positionId,6l,stateId);
			   setPositionCntDataToVO(null,rtrnMncpltyPositionCntList,null,null,resultVO);
		   }else{
			   List<Object[]> rtrnPositionCntList = nominatedPostDAO.getTotalPositionCntPositionAndLocationWise(positionId,boardLevelId,stateId);
			   setPositionCntDataToVO(null,rtrnPositionCntList,null,null,resultVO);
		   }
		 }else if(reportType != null && reportType.equalsIgnoreCase("application")){
			
			   if(boardLevelId != null && boardLevelId.longValue()==5){
				  List<Object[]> rtrnMandalApplicationDtlsCnt = nominatedPostApplicationDAO.getApplicationDetailsCntPositionAndLocationWise(positionId,5l,stateId);  
				  setApplicationCntDataToVO(rtrnMandalApplicationDtlsCnt,resultVO); //For Mandal and Muncipality/Corporation
			   }else{
				   List<Object[]> rtrnApplicationDtlsCnt = nominatedPostApplicationDAO.getApplicationDetailsCntPositionAndLocationWise(positionId,boardLevelId,stateId);  
				   setApplicationCntDataToVO(rtrnApplicationDtlsCnt,resultVO);
			   }
			}
	   }catch(Exception e){
		   LOG.error("Exception Occured in poulateCommonData() of NominatedPostMainDashboardService", e);
	   }
	return resultVO;
}
public CastePositionVO setPositionCntDataToVO(Long totalPositionCnt,List<Object[]> rtrnPositionCntList,Object[] rtrnShortListedCnt,Long notCandidateRlstCnt,CastePositionVO resultVO){
	  
	   Long totalPostsCnt =0l;
	  try{
		if(rtrnPositionCntList != null && rtrnPositionCntList.size() > 0){
			for (Object[] param : rtrnPositionCntList) {
				String status = commonMethodsUtilService.getStringValueForObject(param[1]);
				Long count = commonMethodsUtilService.getLongValueForObject(param[2]);
				totalPostsCnt =totalPostsCnt+count;
				if(status != null && status.equalsIgnoreCase("Open") || status.equalsIgnoreCase("Final Review")){
					resultVO.setOpenPostCnt(resultVO.getOpenPostCnt()+count);
				}else if(status != null && status.equalsIgnoreCase("Confirmed")){
					resultVO.setConfirmCntCnt(resultVO.getConfirmCntCnt()+count);
				}else if(status != null && status.equalsIgnoreCase("GO Issued")){
					resultVO.setGoIssuedCnt(resultVO.getGoIssuedCnt()+count);
				}
			}
		}
		resultVO.setTotalPostsCnt(resultVO.getTotalPostsCnt()+totalPostsCnt);
	}catch(Exception e){
		 LOG.error("Exception Occured in setPositionCntDataToVO() of NominatedPostMainDashboardService", e);
	}
	return resultVO;
}
public CastePositionVO setApplicationCntDataToVO(List<Object[]> rtrnApplicationDtlsList,CastePositionVO resultVO){
	   
	   Long ttlApplicationRcvedCnt=0l;
	try{
		if(rtrnApplicationDtlsList != null && rtrnApplicationDtlsList.size() > 0){
		     for (Object[] param : rtrnApplicationDtlsList) {
				String status = commonMethodsUtilService.getStringValueForObject(param[1]);
				Long count = commonMethodsUtilService.getLongValueForObject(param[2]);
				ttlApplicationRcvedCnt =ttlApplicationRcvedCnt+count;
				if(status != null && status.equalsIgnoreCase("Rejected") || status.equalsIgnoreCase("Rejected in Final Review")){
					resultVO.setRejectedCnt(resultVO.getRejectedCnt()+count);
				}else if(status != null && status.equalsIgnoreCase("GO Passed") || status.equalsIgnoreCase("Confirmed")){
				   resultVO.setConfirmCntCnt(resultVO.getConfirmCntCnt()+count);	
				}else if(status != null && status.equalsIgnoreCase("Shortlisted")){
				  resultVO.setShortedListedCndtCnt(resultVO.getShortedListedCndtCnt()+count);
				}else if(status != null && status.equalsIgnoreCase("Ready For Final Review")){
                 resultVO.setReadyForFinalReviewCnt(resultVO.getReadyForFinalReviewCnt()+count);					
				}
			}
		     resultVO.setTotalAppReceivedCnt(resultVO.getTotalAppReceivedCnt()+ttlApplicationRcvedCnt);
		 } 
	}catch (Exception e) {
		 LOG.error("Exception Occured in setApplicationCntDataToVO() of NominatedPostMainDashboardService", e);
	}
	return resultVO;
}

public  List<IdNameVO> getNominatedPostCandidateDetils(Long stateId,Long casteStateId,Long positionId,Long boardLevelId,Long casteCategryId,
                                                       Long ageRangeTypeId,Long deptmentId,Long corptionId,
                                                       String genderType,List<Long> postStatusIds,Long locationId,String type){
	List<IdNameVO>  finalList = new ArrayList<IdNameVO>();
	List<Long> cadreIds = new ArrayList<Long>();
	Map<Long,IdNameVO> cadreMap = new HashMap<Long,IdNameVO>();
	try{
		List<Object[]> candidateLst = nominatedPostDAO.getStatusWiseNominatedProfileDetils(stateId,casteStateId,positionId,boardLevelId,casteCategryId,
                                                                                          ageRangeTypeId,deptmentId,corptionId,
                                                                                          genderType,postStatusIds,locationId,type);
		if(candidateLst !=null && !candidateLst.isEmpty()){
			for(Object[] param : candidateLst){
				IdNameVO idNameVO = new IdNameVO();
				idNameVO.setId(param[0] != null ? (Long)param[0] : 0l);
				idNameVO.setName(param[1] != null ? param[1].toString() : "");
				idNameVO.setMobileNo(param[2] != null ? param[2].toString() : "");
				idNameVO.setRelativeName(param[3] != null ? param[3].toString() : "");//relative Name
				idNameVO.setMembershipNo(param[4]!=null ? param[4].toString() : "");
				idNameVO.setImageUrl(param[5] != null ? param[5].toString() : "");// imageUrl
				//idNameVO.setDistId(param[6] != null ? (Long)param[6] :"");
				//idNameVO.setDistrictName(param[7] != null ? param[7].toString() :"");
				//idNameVO.setConstitunecyId(param[8] != null ? (Long)param[8] : 0l);
				//idNameVO.setConstituencyName(param[9] != null ? param[9].toString() : "");
				idNameVO.setCadreId(param[6] != null ? (Long)param[6] : 0l);
				if(idNameVO.getCadreId() != null && idNameVO.getCadreId().longValue() > 0l){
					cadreIds.add(idNameVO.getCadreId());
					cadreMap.put(idNameVO.getCadreId(), idNameVO);
				}
				idNameVO.setDepartmentId(param[7] != null ? (Long)param[7] : 0l);
				idNameVO.setDeptName(param[8] != null ? param[8].toString() : "");
				idNameVO.setBoardId(param[9] != null ? (Long)param[9] : 0l);
				idNameVO.setBoardName(param[10] != null ? param[10].toString() : "");
				idNameVO.setPostionId(param[11] != null ? (Long)param[11] : 0l);
				idNameVO.setPositionName(param[12] != null ? param[12].toString() : "");
				idNameVO.setApplicationStatusId(param[13] != null ? (Long)param[13] : 0l);
				idNameVO.setApplicationStatus(param[14] != null ? param[14].toString() : "");
				finalList.add(idNameVO);
			}
		}
		 
		List<Object[]> pubRepDetails = null;
		if(cadreIds != null && cadreIds.size() >0 )
			pubRepDetails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(cadreIds);
		
		if(pubRepDetails !=null && !pubRepDetails.isEmpty()){
			for(Object[] param : pubRepDetails){
					IdNameVO vo = cadreMap.get(param[0]);
					vo.setCadreUserId(param[1] != null ? (Long)param[1] : 0l);//public Rep Id
					vo.setPublicRepr(param[2] != null ? param[2].toString() : "");//
			  }
			}
		
	}catch(Exception e){
		LOG.error("Exception occured into NominatedPostMainDashboardervice",e);
	}
	return finalList;
}

public List<IdAndNameVO> getLocationAndBoardLevelWisePostsData(Long postLevelId,Long casteGrpId,Long casteId,Long ageRangeId,
		Long positionId,String gender,Long stateId,String searchType,List<Long> postStatusIds){
	List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	try{
		List<Object[]> locations =  null;
		if(searchType != null && searchType.equalsIgnoreCase("constituency")){
			locations = constituencyDAO.getStateWiseAssemblyConstituency(stateId);
		}else if(searchType != null && searchType.equalsIgnoreCase("district")){
			locations = districtDAO.getDistrictListBystateId(stateId);
		}
		returnList = setObjectDataToVO(returnList,locations,"locations");
		
		List<Object[]> locatnLvlCounts = nominatedPostFinalDAO.getLocationAndBoardLevelWisePostsData(postLevelId,casteGrpId,casteId,ageRangeId,positionId,gender,stateId,searchType,postStatusIds);
		returnList = setObjectDataToVO(returnList,locatnLvlCounts,"counts");
		
		
		
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception Occured in getLocationAndBoardLevelWisePostsData()", e);
	}
	return returnList;
	
}

public List<IdAndNameVO> setObjectDataToVO(List<IdAndNameVO> returnList,List<Object[]> locations,String type){
	
	try{
		if(type != null && type.equalsIgnoreCase("locations")){
		if(commonMethodsUtilService.isListOrSetValid(locations)){
			String[] setterPropertiesList = {"id","name"};
			returnList = (List<IdAndNameVO>) setterAndGetterUtilService.setValuesToVO(locations, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdAndNameVO");
		}
		
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for(IdAndNameVO  vo :returnList){
				vo.setDistList(getBoardLevels());
			}
		}
		}else if(type != null && type.equalsIgnoreCase("counts")){
			if(commonMethodsUtilService.isListOrSetValid(locations)){
				for(Object[]  obj :locations){
					IdAndNameVO locationVO = (IdAndNameVO)setterAndGetterUtilService.getMatchedVOfromList(returnList, "id", commonMethodsUtilService.getStringValueForObject(obj[0]));
					if(locationVO != null){
						if(obj[2] != null && (Long)obj[2] == 5l || (Long)obj[2] == 6l){
							IdAndNameVO levelVO = (IdAndNameVO)setterAndGetterUtilService.getMatchedVOfromList(locationVO.getDistList(), "id", "5");
								if(levelVO != null){
									levelVO.setTsTotal(levelVO.getTsTotal()+commonMethodsUtilService.getLongValueForObject(obj[4]));
								}
						}else if(obj[2] != null && (Long)obj[2] == 7l || (Long)obj[2] == 8l){
							IdAndNameVO levelVO = (IdAndNameVO)setterAndGetterUtilService.getMatchedVOfromList(locationVO.getDistList(), "id", "6");
								if(levelVO != null){
									levelVO.setTsTotal(levelVO.getTsTotal()+commonMethodsUtilService.getLongValueForObject(obj[4]));
								}
						}else{
							IdAndNameVO levelVO = (IdAndNameVO)setterAndGetterUtilService.getMatchedVOfromList(locationVO.getDistList(), "id", commonMethodsUtilService.getStringValueForObject(obj[2]));
								if(levelVO != null){
									levelVO.setTsTotal(levelVO.getTsTotal()+commonMethodsUtilService.getLongValueForObject(obj[4]));
								}
						}
					}
				}
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception Occured in setObjectDataToVO()", e);
	}
	return returnList;
}
/**
 * @Author  Hyma
 * @Version NominatedPostProfileService.java  July 13, 2016 05:30:00 PM 
 * @return List<IdNameVO>
 * description  { Getting All BoardLevels From Database }
 */
public List<IdAndNameVO> getBoardLevels(){
	List<IdAndNameVO> returnList = new ArrayList<IdAndNameVO>();
	try{
	//List<Object[]> list = boardLevelDAO.getBoardLevels();
		List<Object[]> list = new ArrayList<Object[]>();
		//list = [{11,"Central"}{21,"State"}{31,"District"}{41,"Assembly"}{51,"Mandal/Muncipality/Corporation"}{51,"Village/Ward"}];
		Object[] objArr1 = {1,"Central"};
		Object[] objArr2 = {2,"State"};
		Object[] objArr3 = {3,"District"};
		Object[] objArr4 = {4,"Assembly"};
		Object[] objArr5 = {5,"Mandal/Muncipality/Corporation"};
		Object[] objArr6 = {6,"Village/Ward"};
		list.add(objArr1);
		list.add(objArr2);
		list.add(objArr3);
		list.add(objArr4);
		list.add(objArr5);
		list.add(objArr6);
		
	if(commonMethodsUtilService.isListOrSetValid(list)){
		String[] setterPropertiesList = {"id","name"};
		returnList = (List<IdAndNameVO>) setterAndGetterUtilService.setValuesToVO(list, setterPropertiesList, "com.itgrids.partyanalyst.dto.IdAndNameVO");
	}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception Occured in getBoardLevels()", e);
	}
	return returnList;
}

public List<NominatedPostDashboardVO> setDataToCasteCategoryMap(List<Object[]> list,List<NominatedPostDashboardVO> returnList,String type){
	
	try{
		
		if(commonMethodsUtilService.isListOrSetValid(list)){
			for (Object[] obj : list) {
				NominatedPostDashboardVO locationVO = getMatchedVOByList(returnList, commonMethodsUtilService.getLongValueForObject(obj[6]));
				if(locationVO != null){
				
				locationVO.setStatusId(commonMethodsUtilService.getLongValueForObject(obj[6]));
				locationVO.setStatusName(commonMethodsUtilService.getStringValueForObject(obj[7]));
				Long casteId = Long.valueOf(obj[0] != null ? obj[0].toString():"0");
				Long ageId = Long.valueOf(obj[2] != null ? obj[2].toString():"0");
				String gender = obj[4] != null ? obj[4].toString():"";
				Long count = Long.valueOf(obj[5] != null ? obj[5].toString():"0");
				Map<Long,NominatedPostDashboardVO> casteMap = locationVO.getCasteMap();
				NominatedPostDashboardVO castevo = null;
				if(type != null && type.equalsIgnoreCase("casteCatgry")){
					 castevo = casteMap.get(casteId);
				}else if(type != null && type.equalsIgnoreCase("minority")){
					 castevo = casteMap.get(0l);
				}
				
				if(castevo != null){
					NominatedPostDashboardVO vo = getMatchedVOByList(castevo.getApplicatnStatsList(), ageId);
					if(vo == null){
						vo = new NominatedPostDashboardVO();
					}
					if(type != null && type.equalsIgnoreCase("casteCatgry")){
						vo.setId(casteId);
						vo.setName(obj[1] != null ? obj[1].toString():"");
					}else if(type != null && type.equalsIgnoreCase("minority")){
						vo.setId(0l);
						vo.setName("Minority");
					}
					
					if(gender.trim().equalsIgnoreCase("M") || gender.trim().equalsIgnoreCase("Male"))
						vo.setMaleCount(vo.getMaleCount()+count);
					else if(gender.trim().equalsIgnoreCase("F") || gender.trim().equalsIgnoreCase("Female"))
						vo.setFemaleCount(vo.getFemaleCount()+count);
			}
		}
	}
}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception Occured in setDataToCasteCategoryMap()", e);
	}
	return returnList;
}
public List<NominatedPostDashboardVO> getLocationAndBoardLevelWiseCasteCatgryPostsData(Long postLevelId,Long casteGrpId,Long casteId1,Long ageRangeId,Long positionId,String gender1,
		Long stateId,String searchType,List<Long> locIdsList,String type,List<Long> postStatusIds){
	List<NominatedPostDashboardVO> returnList = new ArrayList<NominatedPostDashboardVO>();
	try {
		
		Long totalCount = 0l;
		
		List<Object[]> casteCategrys = casteCategoryDAO.getAllCasteCategoryDetails();
		
		
		//0.casteId,1.caste,2.ageId,3.age,4.gender,5.count.
		List<Object[]> list = nominatedPostFinalDAO.getLocationAndBoardLevelWiseCasteCatgryPostsData( postLevelId, casteGrpId, casteId1, ageRangeId, positionId, 
				gender1, stateId, searchType, locIdsList,type,"casteCatgry",postStatusIds);
		
		
		if(commonMethodsUtilService.isListOrSetValid(list)){
			for (Object[] obj : list) {
				NominatedPostDashboardVO locationVO = getMatchedVOByList(returnList, commonMethodsUtilService.getLongValueForObject(obj[6]));
				if(locationVO == null){
					locationVO = new NominatedPostDashboardVO();
					Map<Long,NominatedPostDashboardVO> defaultCasteMap = new LinkedHashMap<Long, NominatedPostDashboardVO>();
					NominatedPostDashboardVO minrtycastevo = new NominatedPostDashboardVO();
					minrtycastevo.setStatusId(0l);
					minrtycastevo.setApplicatnStatsList(getAgeGroupList());
					minrtycastevo.setStatusName("Minority");
					defaultCasteMap.put(0l, minrtycastevo);
					if(commonMethodsUtilService.isListOrSetValid(casteCategrys)){
						for (Object[] obj1 : casteCategrys) {
							NominatedPostDashboardVO castevo1 = new NominatedPostDashboardVO();
							castevo1.setId(commonMethodsUtilService.getLongValueForObject(obj1[0]));
							castevo1.setStatusName(commonMethodsUtilService.getStringValueForObject(obj1[1]));
							castevo1.setApplicatnStatsList(getAgeGroupList());
							defaultCasteMap.put(commonMethodsUtilService.getLongValueForObject(obj1[0]), castevo1);
						}
					}
					locationVO.setCasteMap(defaultCasteMap);
					returnList.add(locationVO);
				}
				locationVO.setStatusId(commonMethodsUtilService.getLongValueForObject(obj[6]));
				locationVO.setStatusName(commonMethodsUtilService.getStringValueForObject(obj[7]));
			}
		}
		returnList = setDataToCasteCategoryMap(list,returnList,"casteCatgry");
			
		List<Object[]> minorityData = nominatedPostFinalDAO.getLocationAndBoardLevelWiseCasteCatgryPostsData( postLevelId, casteGrpId, casteId1, 
					ageRangeId, positionId, gender1, stateId, searchType, locIdsList,type,"minority",postStatusIds);
			
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for (Object[] obj : list) {
					NominatedPostDashboardVO locationVO = getMatchedVOByList(returnList, commonMethodsUtilService.getLongValueForObject(obj[6]));
					if(locationVO == null){
						locationVO = new NominatedPostDashboardVO();
						Map<Long,NominatedPostDashboardVO> defaultCasteMap = new LinkedHashMap<Long, NominatedPostDashboardVO>();
						NominatedPostDashboardVO minrtycastevo = new NominatedPostDashboardVO();
						minrtycastevo.setStatusId(0l);
						minrtycastevo.setApplicatnStatsList(getAgeGroupList());
						minrtycastevo.setStatusName("Minority");
						defaultCasteMap.put(0l, minrtycastevo);
						if(commonMethodsUtilService.isListOrSetValid(casteCategrys)){
							for (Object[] obj1 : casteCategrys) {
								NominatedPostDashboardVO castevo1 = new NominatedPostDashboardVO();
								castevo1.setId(commonMethodsUtilService.getLongValueForObject(obj1[0]));
								castevo1.setStatusName(commonMethodsUtilService.getStringValueForObject(obj1[1]));
								castevo1.setApplicatnStatsList(getAgeGroupList());
								defaultCasteMap.put(commonMethodsUtilService.getLongValueForObject(obj1[0]), castevo1);
							}
						}
						locationVO.setCasteMap(defaultCasteMap);
						returnList.add(locationVO);
					}
					locationVO.setStatusId(commonMethodsUtilService.getLongValueForObject(obj[6]));
					locationVO.setStatusName(commonMethodsUtilService.getStringValueForObject(obj[7]));
				}
			}
			returnList = setDataToCasteCategoryMap(minorityData,returnList,"minority");
			
		Long twentyTo29AgeRangeCount=0l;
		Long thirtyTo39AgeRangeCount=0l;
		Long fourtyTo49AgeRangeCount=0l;
		Long fiftyTo59AgeRangeCount=0l;
		Long sixtyAvoveAgeRangeCount=0l;
		Long overAllCount=0l;
		
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for(NominatedPostDashboardVO locationVO : returnList){
				Map<Long,NominatedPostDashboardVO> casteMap2 = locationVO.getCasteMap();
				List<NominatedPostDashboardVO> casteCatgryList = new ArrayList<NominatedPostDashboardVO>(casteMap2.values());
				locationVO.setPositinsList(casteCatgryList);
			}
		}
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for(NominatedPostDashboardVO locationVO : returnList){
				locationVO.getCasteMap().clear();
			}
		}
		
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for(NominatedPostDashboardVO locationVO : returnList){
				if(commonMethodsUtilService.isListOrSetValid(locationVO.getPositinsList())){
					for (NominatedPostDashboardVO castevo : locationVO.getPositinsList()) {
						NominatedPostDashboardVO totalvo = getMatchedVOByList(castevo.getApplicatnStatsList(), 0l);
							if(commonMethodsUtilService.isListOrSetValid(castevo.getApplicatnStatsList())){
								
								for (NominatedPostDashboardVO vo : castevo.getApplicatnStatsList()) {
									totalvo.setId(castevo.getId());
									totalvo.setName(castevo.getName());
									totalvo.setMaleCount(totalvo.getMaleCount()+vo.getMaleCount());
									totalvo.setFemaleCount(totalvo.getFemaleCount()+vo.getFemaleCount());
									totalvo.setStatusCount(totalvo.getStatusCount()+vo.getMaleCount()+vo.getFemaleCount());
									totalCount = totalCount+totalvo.getStatusCount();
									overAllCount = overAllCount + vo.getMaleCount()+vo.getFemaleCount();
									if(vo.getStatusId() != null && vo.getStatusId().longValue()==1l){
										twentyTo29AgeRangeCount = twentyTo29AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
									}else if(vo.getStatusId() != null && vo.getStatusId().longValue()==2l){
										thirtyTo39AgeRangeCount = thirtyTo39AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
									}else if(vo.getStatusId() != null && vo.getStatusId().longValue()==3l){
										fourtyTo49AgeRangeCount = fourtyTo49AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
									}else if(vo.getStatusId() != null && vo.getStatusId().longValue()==4l){
										fiftyTo59AgeRangeCount = fiftyTo59AgeRangeCount +vo.getMaleCount()+vo.getFemaleCount();
									}else{
										sixtyAvoveAgeRangeCount = sixtyAvoveAgeRangeCount +	vo.getMaleCount()+vo.getFemaleCount();
									}
								}
							}
							castevo.setTotalCnt(totalvo.getStatusCount());
						}
					}
				}
		}
		
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for(NominatedPostDashboardVO locationVO : returnList){
				if(commonMethodsUtilService.isListOrSetValid(locationVO.getPositinsList())){
					
					for (NominatedPostDashboardVO vo : locationVO.getPositinsList()) {
						Long count = vo.getTotalCnt();
						if(totalCount != null && totalCount.longValue() > 0l && count != null && count.longValue() > 0l){
						String percentage = (new BigDecimal((count * 100.0)/overAllCount.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP)).toString();
						vo.setPercentage(percentage);
						}
					}
				}
			}
		}
		
		if(commonMethodsUtilService.isListOrSetValid(returnList)){
			for(NominatedPostDashboardVO locationVO : returnList){
				if(locationVO.getPositinsList() !=null && locationVO.getPositinsList().size() > 0){
					NominatedPostDashboardVO vo = locationVO.getPositinsList().get(0);
					  vo.setTwentyTo29AgeRangeCount(twentyTo29AgeRangeCount);
					  vo.setThirtyTo39AgeRangeCount(thirtyTo39AgeRangeCount);
					  vo.setFourtyTo49AgeRangeCount(fourtyTo49AgeRangeCount);
					  vo.setFiftyTo59AgeRangeCount(fiftyTo59AgeRangeCount);
					  vo.setSixtyAvoveAgeRangeCount(sixtyAvoveAgeRangeCount);
					  vo.setOverAllCount(overAllCount);
				}
			}
		}
		
		
	} catch (Exception e) {
		LOG.error("Exception raised at getCasteGroupWiseCountsByPosition() method of NominatedPostMainDashboardervice", e);
	}
	return returnList;
}
public List<NominatedPostDashboardVO> getAgeGroupList(){
	List<NominatedPostDashboardVO> voList = new ArrayList<NominatedPostDashboardVO>();
	try {
		List<Object[]> list = nominatedPostAgeRangeDAO.getAllAgeRanges();
		if(commonMethodsUtilService.isListOrSetValid(list)){
			for (Object[] obj : list) {
				NominatedPostDashboardVO vo = new NominatedPostDashboardVO();
				vo.setStatusId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
				vo.setStatusName(obj[1] != null ? obj[1].toString():"");
				if(vo.getStatusId() != 6l)
					voList.add(vo);
			}
		}
		NominatedPostDashboardVO totalvo1= new NominatedPostDashboardVO();
		 //castevo.getPositinsList().add(totalvo);
		 
		
		totalvo1.setStatusId(0l);
		totalvo1.setStatusName("Total");
		voList.add(0,totalvo1);
	} catch (Exception e) {
		LOG.error("Exception raised at getAgeGroupList() method of NominatedPostMainDashboardervice", e);
	}
	return voList;
}
public NominatedPostDashboardVO getMatchedVOByList(List<NominatedPostDashboardVO> voList,Long id){
	NominatedPostDashboardVO returnvo = new NominatedPostDashboardVO();
	try {
		if(commonMethodsUtilService.isListOrSetValid(voList)){
			for (NominatedPostDashboardVO nominatedPostDashboardVO : voList) {
				if(nominatedPostDashboardVO.getStatusId().longValue() == id.longValue()){
					return nominatedPostDashboardVO;
				}
			}
		}
	} catch (Exception e) {
		LOG.error("Exception raised at getMatchedVOByList() method of NominatedPostMainDashboardervice", e);
	}
	return null;
}
public NominatedPostDashboardVO getMatchedVOByList1(List<NominatedPostDashboardVO> voList,Long id){
	NominatedPostDashboardVO returnvo = new NominatedPostDashboardVO();
	try {
		if(commonMethodsUtilService.isListOrSetValid(voList)){
			for (NominatedPostDashboardVO nominatedPostDashboardVO : voList) {
				if(nominatedPostDashboardVO.getId().longValue() == id.longValue()){
					return nominatedPostDashboardVO;
				}
			}
		}
	} catch (Exception e) {
		LOG.error("Exception raised at getMatchedVOByList() method of NominatedPostMainDashboardervice", e);
	}
	return null;
}
public  List<IdNameVO> getCandidateLocationWiseDetails(Long postLevelId,Long casteGrpId,Long casteId1,Long ageRangeId,Long positionId,String gender1,
		Long stateId,String searchType,List<Long> locIdsList,List<Long> postStatusIds,String type){
			List<IdNameVO>  finalList = new ArrayList<IdNameVO>();
			List<Long> cadreIds = new ArrayList<Long>();
			Map<Long,IdNameVO> cadreMap = new HashMap<Long,IdNameVO>();
			try{
			List<Object[]> candidateLst = nominatedPostFinalDAO.getCandidateLocationWiseDetails(postLevelId,casteGrpId,casteId1,ageRangeId,positionId,gender1,
					stateId,searchType,locIdsList,postStatusIds,type);
			if(candidateLst !=null && !candidateLst.isEmpty()){
			for(Object[] param : candidateLst){
			IdNameVO idNameVO = new IdNameVO();
			idNameVO.setId(param[0] != null ? (Long)param[0] : 0l);
			idNameVO.setName(param[1] != null ? param[1].toString() : "");
			idNameVO.setMobileNo(param[2] != null ? param[2].toString() : "");
			idNameVO.setRelativeName(param[3] != null ? param[3].toString() : "");//relative Name
			idNameVO.setMembershipNo(param[4]!=null ? param[4].toString() : "");
			idNameVO.setImageUrl(param[5] != null ? param[5].toString() : "");// imageUrl
			//idNameVO.setDistId(param[6] != null ? (Long)param[6] :"");
			//idNameVO.setDistrictName(param[7] != null ? param[7].toString() :"");
			//idNameVO.setConstitunecyId(param[8] != null ? (Long)param[8] : 0l);
			//idNameVO.setConstituencyName(param[9] != null ? param[9].toString() : "");
			idNameVO.setCadreId(param[6] != null ? (Long)param[6] : 0l);
			if(idNameVO.getCadreId() != null && idNameVO.getCadreId().longValue() > 0l){
			cadreIds.add(idNameVO.getCadreId());
			cadreMap.put(idNameVO.getCadreId(), idNameVO);
			}
			idNameVO.setDepartmentId(param[7] != null ? (Long)param[7] : 0l);
			idNameVO.setDeptName(param[8] != null ? param[8].toString() : "");
			idNameVO.setBoardId(param[9] != null ? (Long)param[9] : 0l);
			idNameVO.setBoardName(param[10] != null ? param[10].toString() : "");
			idNameVO.setPostionId(param[11] != null ? (Long)param[11] : 0l);
			idNameVO.setPositionName(param[12] != null ? param[12].toString() : "");
			idNameVO.setApplicationStatusId(param[13] != null ? (Long)param[13] : 0l);
			idNameVO.setApplicationStatus(param[14] != null ? param[14].toString() : "");
			finalList.add(idNameVO);
			}
			}
			
			List<Object[]> pubRepDetails = null;
			if(cadreIds != null && cadreIds.size() >0 )
			pubRepDetails = tdpCadreCandidateDAO.getPublicRepresentativeDetailsByCadreIds(cadreIds);
			
			if(pubRepDetails !=null && !pubRepDetails.isEmpty()){
			for(Object[] param : pubRepDetails){
			IdNameVO vo = cadreMap.get(param[0]);
			vo.setCadreUserId(param[1] != null ? (Long)param[1] : 0l);//public Rep Id
			vo.setPublicRepr(param[2] != null ? param[2].toString() : "");//
			}
			}
			
			}catch(Exception e){
			LOG.error("Exception occured into NominatedPostMainDashboardervice",e);
			}
			return finalList;
}

public List<GeoLevelListVO> getGeoLevelReportDetails(GeoLevelReportVO vo){
	List<GeoLevelListVO> locationsList = new ArrayList<GeoLevelListVO>();
	try{
		
		List<Object[]> geoReport = nominatedPostFinalDAO.getGeoLevelReportDetails(vo);
		
		 locationsList = buildGeoReportData(geoReport,0,1);
		List<GeoLevelListVO> positionList = buildGeoReportData(geoReport,2,3);
		List<GeoLevelListVO> castGrpList = buildGeoReportData(geoReport,4,5);
		List<GeoLevelListVO> casteList = buildGeoReportData(geoReport,6,7);
		List<GeoLevelListVO> boardLevelList = buildGeoReportData(geoReport,8,9);
		List<GeoLevelListVO> ageGrpList = buildGeoReportData(geoReport,10,11);
		
		
		locationsList = setReturnListValues(locationsList,positionList,"positions");
		locationsList = setReturnListValues(locationsList,castGrpList,"category");
		locationsList = setReturnListValues(locationsList,casteList,"caste");
		locationsList = setReturnListValues(locationsList,boardLevelList,"boardLevel");
		locationsList = setReturnListValues(locationsList,ageGrpList,"ageGrp");
		if(commonMethodsUtilService.isListOrSetValid(ageGrpList)){
			setCount(locationsList,geoReport);
		}
		
		//List<GeoLevelListVO> boardLevelList = buildGeoReportData(geoReport,8,9);
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception occured into getGeoLevelReportDetails () of NominatedPostMainDashboardervice ",e);
	}
	return locationsList;
}
public void setCount(List<GeoLevelListVO> list,List<Object[]> geoReport){
	
	try{
		if(commonMethodsUtilService.isListOrSetValid(geoReport)){
			
			Map<Long,Map<Long,Map<Long,Set<Long>>>> availableDataMap = new HashMap<Long,Map<Long,Map<Long,Set<Long>>>>(0);
			for(Object[] obj :geoReport){
				GeoLevelListVO locationVO = getMatchedVO(list, commonMethodsUtilService.getLongValueForObject(obj[0]));
				
				if(locationVO != null){
					Long totalCount = commonMethodsUtilService.getLongValueForObject(obj[13]);
					locationVO.setTotal(locationVO.getTotal()+totalCount);

					GeoLevelListVO positionVO = getMatchedVO(locationVO.getPositionList(), commonMethodsUtilService.getLongValueForObject(obj[2]));
					if(positionVO != null){
						
					GeoLevelListVO castCatgryVO = getMatchedVO(positionVO.getCasteCagryList(), commonMethodsUtilService.getLongValueForObject(obj[4]));
						if(castCatgryVO != null){								
							GeoLevelListVO casteVO = getMatchedVO(castCatgryVO.getCasteList(),commonMethodsUtilService.getLongValueForObject(obj[6]));
							if(casteVO != null){
								GeoLevelListVO boardLvlVO = getMatchedVO(casteVO.getBoardLvlList(), commonMethodsUtilService.getLongValueForObject(obj[8]));
								if(boardLvlVO != null){
									GeoLevelListVO ageRangeVO = getMatchedVO(boardLvlVO.getAgeRangeList(), commonMethodsUtilService.getLongValueForObject(obj[10]));
									if(ageRangeVO != null){
										Long count = commonMethodsUtilService.getLongValueForObject(obj[13]);
										if(commonMethodsUtilService.getStringValueForObject(obj[12]).equalsIgnoreCase("M") || commonMethodsUtilService.getStringValueForObject(obj[12]).equalsIgnoreCase("Male")){
											ageRangeVO.setMaleCount(ageRangeVO.getMaleCount()+count);
										}else if(commonMethodsUtilService.getStringValueForObject(obj[12]).equalsIgnoreCase("F") || commonMethodsUtilService.getStringValueForObject(obj[12]).equalsIgnoreCase("Female")){
											ageRangeVO.setFeMaleCount(ageRangeVO.getFeMaleCount()+count);
										}
										ageRangeVO.setTotal(ageRangeVO.getTotal()+count);
										if(count.longValue()>0L){
											
											
											Map<Long,Map<Long,Set<Long>>> availablePositionsMap = new HashMap<Long, Map<Long,Set<Long>>>(0);
											Map<Long,Set<Long>> availableCategoryMap = new HashMap<Long,Set<Long>>(0);
											//Map<Long,Set<Long>> availableCasteMap = new HashMap<Long,Set<Long>>(0);
											Set<Long> castIds = new HashSet<Long>(0);
											
											if(availableDataMap.get(locationVO.getLevelId()) != null){
												availablePositionsMap = availableDataMap.get(locationVO.getLevelId());
												if(availablePositionsMap.get(positionVO.getLevelId()) != null){
													availableCategoryMap = availablePositionsMap.get(positionVO.getLevelId());
												}
												if(availableCategoryMap.get(castCatgryVO.getLevelId()) != null){
													castIds = availableCategoryMap.get(castCatgryVO.getLevelId());
												}
											}
											castIds.add(casteVO.getLevelId());
											
											availableCategoryMap.put(castCatgryVO.getLevelId(), castIds);
											availablePositionsMap.put(positionVO.getLevelId(), availableCategoryMap);
											availableDataMap.put(locationVO.getLevelId(), availablePositionsMap);
										}
									}
								}
							}
						}
					}
				}
			}
			
			
			for (GeoLevelListVO vo : list) {
				//System.out.println(availableDataMap.get(vo.getLevelId()));
				if(commonMethodsUtilService.isListOrSetValid(vo.getPositionList())){
					List<GeoLevelListVO> filteredpositionList = new ArrayList<GeoLevelListVO>(0);
					
					Map<Long,Map<Long,Set<Long>>> availablePositionsMap = availableDataMap.get(vo.getLevelId());
					for (GeoLevelListVO positionVO : vo.getPositionList()) {
						//System.out.println(availableDataMap.get(vo.getLevelId().get(positionVO.getLevelId())));
						Map<Long,Set<Long>> availableCategoryMap = availablePositionsMap.get(positionVO.getLevelId());
						if(commonMethodsUtilService.isMapValid(availableCategoryMap)){
							
							GeoLevelListVO postionVO = new GeoLevelListVO();
							postionVO.setLevelId(positionVO.getLevelId());
							postionVO.setName(positionVO.getName());
							
							for (GeoLevelListVO categoryVO : positionVO.getCasteCagryList()) {
								//System.out.println(availableDataMap.get(vo.getLevelId().get(positionVO.getLevelId()).get(categoryVO.getLevelId())));
								Set<Long> castIds = availableCategoryMap.get(categoryVO.getLevelId());
								if(commonMethodsUtilService.isListOrSetValid(castIds)){
									
									GeoLevelListVO casteCategory = new GeoLevelListVO();
									casteCategory.setLevelId(categoryVO.getLevelId());
									casteCategory.setName(categoryVO.getName());
									
									for (GeoLevelListVO casteVO : categoryVO.getCasteList()) {
										if(castIds.contains(casteVO.getLevelId())){
											GeoLevelListVO caste = new GeoLevelListVO();
											caste.setLevelId(casteVO.getLevelId());
											caste.setName(casteVO.getName());
											caste.getBoardLvlList().addAll(casteVO.getBoardLvlList());
											
											casteCategory.getCasteList().add(caste);
										}
									}
									postionVO.getCasteCagryList().add(casteCategory);
								}
							}
							
							filteredpositionList.add(postionVO);
						}
					}
					vo.getPositionList().clear();
					vo.setPositionList(filteredpositionList);
				}
				
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception occured into setCount () of NominatedPostMainDashboardervice ",e);
	}
}
public void setListForVO(GeoLevelListVO vo,List<GeoLevelListVO> subList,String type){
	
	try{
		if(commonMethodsUtilService.isListOrSetValid(subList)){
			if(type != null && type.equalsIgnoreCase("positions")){
				for (GeoLevelListVO subVO : subList) {
					GeoLevelListVO postnVO = new GeoLevelListVO();
					postnVO.setLevelId(subVO.getLevelId());
					postnVO.setName(subVO.getName());
					vo.getPositionList().add(postnVO);
				}
			}else if(type != null && type.equalsIgnoreCase("category")){
				for (GeoLevelListVO subVO : subList) {
					GeoLevelListVO postnVO = new GeoLevelListVO();
					postnVO.setLevelId(subVO.getLevelId());
					postnVO.setName(subVO.getName());
					vo.getCasteCagryList().add(postnVO);
				}
			}
			else if(type != null && type.equalsIgnoreCase("caste")){
				for (GeoLevelListVO subVO : subList) {
					GeoLevelListVO postnVO = new GeoLevelListVO();
					postnVO.setLevelId(subVO.getLevelId());
					postnVO.setName(subVO.getName());
					vo.getCasteList().add(postnVO);
				}
			}
			else if(type != null && type.equalsIgnoreCase("boardLevel")){
				for (GeoLevelListVO subVO : subList) {
					GeoLevelListVO postnVO = new GeoLevelListVO();
					postnVO.setLevelId(subVO.getLevelId());
					postnVO.setName(subVO.getName());
					vo.getBoardLvlList().add(postnVO);
				}
			}else if(type != null && type.equalsIgnoreCase("ageGrp")){
				for (GeoLevelListVO subVO : subList) {
					GeoLevelListVO postnVO = new GeoLevelListVO();
					postnVO.setLevelId(subVO.getLevelId());
					postnVO.setName(subVO.getName());
					vo.getAgeRangeList().add(postnVO);
				}
			}
		}
	}catch(Exception e){
		e.printStackTrace();
		LOG.error("Exception occured into setListForVO () of NominatedPostMainDashboardervice ",e);
	}
}
public List<GeoLevelListVO> setReturnListValues(List<GeoLevelListVO> locationsList,List<GeoLevelListVO> subList,String type){
	
	try{
		
		
		if(commonMethodsUtilService.isListOrSetValid(locationsList)){
		for(GeoLevelListVO locVO :locationsList){
			if(type != null && type.equalsIgnoreCase("positions")){
				 setListForVO(locVO,subList,type);
				}else if(type != null && type.equalsIgnoreCase("category")){
				List<GeoLevelListVO>  posiList= locVO.getPositionList();
				for(GeoLevelListVO posiVO :posiList){
					posiVO.getCasteCagryList().clear();
					setListForVO(posiVO,subList,type);
				}
			}
			else if(type != null && type.equalsIgnoreCase("caste")){
				List<GeoLevelListVO>  posiList= locVO.getPositionList();
					for(GeoLevelListVO posiVO :posiList){
						List<GeoLevelListVO>  categoryList= posiVO.getCasteCagryList();
						for(GeoLevelListVO catgVO :categoryList){
							catgVO.getCasteList().clear();
							setListForVO(catgVO,subList,type);
						   }
						}
					}
			
			else if(type != null && type.equalsIgnoreCase("boardLevel")){
				List<GeoLevelListVO>  posiList= locVO.getPositionList();
				for(GeoLevelListVO posiVO :posiList){
					List<GeoLevelListVO>  categoryList= posiVO.getCasteCagryList();
					for(GeoLevelListVO catgVO :categoryList){
						List<GeoLevelListVO>  casteList= catgVO.getCasteList();
						for(GeoLevelListVO casteVO :casteList){
							setListForVO(casteVO,subList,type);
						}
					}
				}
			}else if(type != null && type.equalsIgnoreCase("ageGrp")){
				List<GeoLevelListVO>  posiList= locVO.getPositionList();
				for(GeoLevelListVO posiVO :posiList){
					List<GeoLevelListVO>  categoryList= posiVO.getCasteCagryList();
					for(GeoLevelListVO catgVO :categoryList){
						List<GeoLevelListVO>  casteList= catgVO.getCasteList();
						for(GeoLevelListVO casteVO :casteList){
							List<GeoLevelListVO>  boardLvlList= casteVO.getBoardLvlList();
							for(GeoLevelListVO boardLvl :boardLvlList){
								 setListForVO(boardLvl,subList,type);
							}
						}
					}
				}
			}
		}
		}
	}catch(Exception e){
		
	}
	return locationsList;
}
 public List<GeoLevelListVO> buildGeoReportData(List<Object[]> geoReport,int index1,int index2){
	 List<GeoLevelListVO> returnList = new ArrayList<GeoLevelListVO>();
	 Map<Long,GeoLevelListVO> map = new HashMap<Long,GeoLevelListVO>();
	 try{
		 
		 if(commonMethodsUtilService.isListOrSetValid(geoReport)){
			 for(Object[] obj : geoReport){
				 GeoLevelListVO locationVO = new GeoLevelListVO(); 
					 locationVO.setLevelId(commonMethodsUtilService.getLongValueForObject(obj[index1]));
					 locationVO.setName(commonMethodsUtilService.getStringValueForObject(obj[index2]));
					 
					 map.put(commonMethodsUtilService.getLongValueForObject(obj[index1]), locationVO);
			}
		}
	 }catch(Exception e){
		 e.printStackTrace();
			LOG.error("Exception occured into buildGeoReportData () of NominatedPostMainDashboardervice ",e);
	 }
	 if(commonMethodsUtilService.isMapValid(map)){
		 returnList.addAll(map.values());
	 }
	 return returnList;
 }
 
 public GeoLevelListVO getMatchedVO(List<GeoLevelListVO> list,Long id){
		GeoLevelListVO returnVO = null;
		try{
			if(commonMethodsUtilService.isListOrSetValid(list)){
				for(GeoLevelListVO vo:list){
					if(vo.getLevelId() != null && vo.getLevelId().longValue() == id.longValue() ){
						return vo;
						
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			LOG.error("Exception occured into getMatchedVO () of NominatedPostMainDashboardervice ",e);
		}
		return returnVO;
	}
}
