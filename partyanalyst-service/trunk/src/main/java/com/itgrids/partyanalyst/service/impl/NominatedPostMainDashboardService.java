package com.itgrids.partyanalyst.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.regex.REUtil;

import com.itgrids.partyanalyst.dao.IApplicationStatusDAO;
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
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.CastePositionVO;
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
	
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IPanchayatDAO panchayatDAO;
	private 
	DecimalFormat decimalFormat = new DecimalFormat("#.##");

	
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
			List<Object[]> casteList = nominatedPostFinalDAO.getCandidateCasteList(LocationLevelId,stateId);
			List<Object[]> positionList = positionDAO.getAllPositions();
			if(casteList != null && casteList.size() > 0)
			{
				CastePositionVO castePositionVO = null;
				for(Object[] params : casteList)
				{
					castePositionVO = new CastePositionVO();
					castePositionVO.setCasteId(commonMethodsUtilService.getLongValueForObject(params[0]));
					castePositionVO.setCasteName(commonMethodsUtilService.getStringValueForObject(params[1]));
					castePositionVO.setPositionList(getDefaultPositionList(positionList));
					resultList.add(castePositionVO);
				}
			}
			if(LocationLevelId.longValue()==5){
				List<Object[]> rtrnMandalList = nominatedPostFinalDAO.getLocationWiseCastePositionCount(5l,positionId,stateId);//For Mandal and Muncipality/Corporation
				pushMatchCastePositionCntToVO(rtrnMandalList,resultList);
				//List<Object[]> rtrnMncpaltyCrprtnList = nominatedPostFinalDAO.getLocationWiseCastePositionCount(6l,positionId);
				//pushMatchCastePositionCntToVO(rtrnMncpaltyCrprtnList,resultList);
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
	public List<CastePositionVO> getDefaultPositionList(List<Object[]> positionList){
		List<CastePositionVO> positionDefaultList = new ArrayList<CastePositionVO>(0);
		
		if(positionList != null && positionList.size() > 0)
		{
			CastePositionVO postionVO = null;
			for(Object[] params : positionList)
			{
				postionVO = new CastePositionVO();
				postionVO.setPositionId(commonMethodsUtilService.getLongValueForObject(params[0]));
				postionVO.setPositionName(commonMethodsUtilService.getStringValueForObject(params[1]));
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
			 List<Object[]> rtrnObjCsteGrupList = nominatedPostFinalDAO.getCasteGroup(LocationLevelId,stateId);
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
					resultList.add(castePositionVO);
				}
			}
			 if(LocationLevelId.longValue()==5){
					List<Object[]> rtrnMandalList = nominatedPostFinalDAO.getLocationWiseCasteGroupPositionCount(5l,positionId,stateId); //For Mandal and Muncipality/Corporation
					pushMatchCasteGroupPositionCntToVO(rtrnMandalList,resultList);
				//	List<Object[]> rtrnMncpaltyCrprtnList = nominatedPostFinalDAO.getLocationWiseCasteGroupPositionCount(6l,positionId);
				//	pushMatchCastePositionCntToVO(rtrnMncpaltyCrprtnList,resultList);
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
	public List<IdAndNameVO> getBoardList(List<Long> deptId){
		LOG.info("Entered into getboardList() of NominatedPostMainDashboardService.");
		try{
			List<IdAndNameVO> brdList = new ArrayList<IdAndNameVO>();
			List<Object[]> boardList = nominatedPostMemberDAO.getBoardList(deptId);
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
           
	    /*   Double totalPositionPer = (resultVO.getTotalPositionCn() *100.00)/totalPostsCnt;
		   if(!(totalPositionPer.isNaN())){
			   resultVO.setTotalPositionCntPer(decimalFormat.format(totalPositionPer));  
		   }
		   */
		   
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
/*		   Double noCandidatePer = (resultVO.getNoCandidateCnt()*100.00)/totalPositionCnt;
		   if(!(noCandidatePer.isNaN())){
			   resultVO.setNoCandidateCntPer(decimalFormat.format(noCandidatePer));
		   }
		   Double shortListedPer = (resultVO.getShortedListedCndtCnt()*100.00)/totalPositionCnt;
		   if(!(shortListedPer.isNaN())){
			   resultVO.setShortListedCntper(decimalFormat.format(shortListedPer));   
		   }*/
	/*	   Double finalReviewPer = (resultVO.getFinalReviewCantCnt()*100.00)/totalPositionCnt;
		   if(!(finalReviewPer.isNaN())){
			   resultVO.setFinalReviewPer(decimalFormat.format(finalReviewPer));  
		   }*/
		 
		/*   Double ttlOpenPositionPer = (resultVO.getTotalOpendPositionCnt()*100.00)/totalPositionCnt;
		   if(!ttlOpenPositionPer.isNaN()){
			  resultVO.setTotalOpendPositionCntPer(decimalFormat.format(ttlOpenPositionPer)); 
		   }*/
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
			 //  Long mandalLevelttlPstnCnt = nominatedPostDAO.getAllPositionCntPositionAndLocationWise(positionId,5l,stateId);
			   List<Object[]> rtrnManDalRsltList = nominatedPostDAO.getTotalPositionCntPositionAndLocationWise(positionId,5l,stateId);
			 //  Object[] rtrnMandalShortListedCnt = nominatedPostFinalDAO.getShortListedPositionCntPositionAndLocationWise(positionId,5l,stateId);
			 //  Long  notCandidateMandalCnt = nominatedPostDAO.getNoCandiateCntPositionAndLocationWise(positionId,stateId, 5l);  
			   setPositionCntDataToVO(null,rtrnManDalRsltList,null,null,resultVO);
			 //  Long corpLevelttlPstnCnt = nominatedPostDAO.getAllPositionCntPositionAndLocationWise(positionId,6l,stateId);
			   List<Object[]> rtrnMncpltyPositionCntList = nominatedPostDAO.getTotalPositionCntPositionAndLocationWise(positionId,6l,stateId);
			 //  Object[] rtrnMncpltyShortListedCnt = nominatedPostFinalDAO.getShortListedPositionCntPositionAndLocationWise(positionId,6l,stateId);
			 //  Long  notMncpltyCandidateCnt = nominatedPostDAO.getNoCandiateCntPositionAndLocationWise(positionId,stateId, 6l);  
			   setPositionCntDataToVO(null,rtrnMncpltyPositionCntList,null,null,resultVO);
		   }else{
			//   Long ttlPositionCnt = nominatedPostDAO.getAllPositionCntPositionAndLocationWise(positionId,boardLevelId,stateId);
			   List<Object[]> rtrnPositionCntList = nominatedPostDAO.getTotalPositionCntPositionAndLocationWise(positionId,boardLevelId,stateId);
			//   Object[] rtrnShortListedCnt = nominatedPostFinalDAO.getShortListedPositionCntPositionAndLocationWise(positionId,boardLevelId,stateId);
			 //  Long  notCandidateCnt = nominatedPostDAO.getNoCandiateCntPositionAndLocationWise(positionId,stateId,boardLevelId);  
			   setPositionCntDataToVO(null,rtrnPositionCntList,null,null,resultVO);
		   }
		 }else if(reportType != null && reportType.equalsIgnoreCase("application")){
			
			   if(boardLevelId != null && boardLevelId.longValue()==5){
				  List<Object[]> rtrnMandalApplicationDtlsCnt = nominatedPostApplicationDAO.getApplicationDetailsCntPositionAndLocationWise(positionId,5l,stateId);  
				  setApplicationCntDataToVO(rtrnMandalApplicationDtlsCnt,resultVO); //For Mandal and Muncipality/Corporation
				  // List<Object[]> rtrnMncpltyApplicationDtlsCnt = nominatedPostApplicationDAO.getApplicationDetailsCntPositionAndLocationWise(positionId, 6l);  
				   //setApplicationCntDataToVO(rtrnMncpltyApplicationDtlsCnt,resultVO);
			   }else{
				   List<Object[]> rtrnApplicationDtlsCnt = nominatedPostApplicationDAO.getApplicationDetailsCntPositionAndLocationWise(positionId,boardLevelId,stateId);  
				   setApplicationCntDataToVO(rtrnApplicationDtlsCnt,resultVO);
			   }
			}
	   }catch(Exception e){
		   LOG.error("Exception Occured in poulateData() of NominatedPostMainDashboardService", e);
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
				if(status != null && status.equalsIgnoreCase("Open")){
					resultVO.setOpenPostCnt(resultVO.getOpenPostCnt()+count);
				}else if(status != null && status.equalsIgnoreCase("Confirmed")){
					resultVO.setConfirmCntCnt(resultVO.getConfirmCntCnt()+count);
				}else if(status != null && status.equalsIgnoreCase("GO Issued")){
					resultVO.setGoIssuedCnt(resultVO.getGoIssuedCnt()+count);
				}
			}
		}
		resultVO.setTotalPostsCnt(resultVO.getTotalPostsCnt()+totalPostsCnt);
	/*	if(rtrnShortListedCnt != null && rtrnShortListedCnt.length > 0){
			Long shortListedCnt = commonMethodsUtilService.getLongValueForObject(rtrnShortListedCnt[2]);
			 resultVO.setShortedListedCndtCnt(resultVO.getShortedListedCndtCnt()+shortListedCnt);
		 }*/
	/*	 if(notCandidateRlstCnt != null ){
			  resultVO.setNoCandidateCnt(resultVO.getNoCandidateCnt()+notCandidateRlstCnt);
		   }*/
		// resultVO.setTotalPositionCn(resultVO.getTotalPositionCn()+totalPositionCnt);
	}catch(Exception e){
		 LOG.error("Exception Occured in setPositionCntDataToVO() of NominatedPostMainDashboardService", e);
	}
	return resultVO;
}
public CastePositionVO setApplicationCntDataToVO(List<Object[]> rtrnApplicationDtlsList,CastePositionVO resultVO){
	   
	   Long ttlApplicationRcvedCnt=0l;
	//   Long ttlInProgressCnt =0l;
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
		   //  resultVO.setInProgressCnt(resultVO.getInProgressCnt()+ttlInProgressCnt);
		     resultVO.setTotalAppReceivedCnt(resultVO.getTotalAppReceivedCnt()+ttlApplicationRcvedCnt);
		 } 
	}catch (Exception e) {
		 LOG.error("Exception Occured in setApplicationCntDataToVO() of NominatedPostMainDashboardService", e);
	}
	return resultVO;
}
}
