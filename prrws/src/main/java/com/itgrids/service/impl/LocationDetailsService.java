package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.ILocalElectionBodyDAO;
import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.dao.IPetitionDepartmentDAO;
import com.itgrids.dao.IPetitionDesignationDAO;
import com.itgrids.dao.IPetitionMemberDAO;
import com.itgrids.dao.IPetitionWorkDetailsDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.RepresentationRequestVO;
import com.itgrids.service.ILocationDetailsService;
import com.itgrids.utils.CommonMethodsUtilService;

@Service
@Transactional
public class LocationDetailsService implements ILocationDetailsService {

	private static final Logger LOG = Logger.getLogger(LocationDetailsService.class);
	
	@Autowired
	private CommonMethodsUtilService commonMethodsUtilService;
	@Autowired
	private IDistrictDAO districtDAO;
	@Autowired
	private ITehsilDAO tehsilDAO;
	@Autowired
	private IConstituencyDAO constituencyDAO;
	@Autowired
	private ILocalElectionBodyDAO localElectionBodyDAO;
	@Autowired
	private IPanchayatDAO panchayatDAO;
	@Autowired
	private IPetitionDesignationDAO petitionDesignationDAO;
	@Autowired
	private IPetitionDepartmentDAO petitionDepartmentDAO;
	@Autowired
	private IPetitionMemberDAO petitionMemberDAO;
	@Autowired
	private IPetitionWorkDetailsDAO petitionWorkDetailsDAO;
	 /**
		 * Date : 30/11/2017
		 * Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
		 * @description : to get districs details in a state
		 * @param : state Id
		 * @return  List<LocationFundDetailsVO> 
		 */
	public List<LocationFundDetailsVO> getAllDistrictsInState(Long stateId){
		 List<LocationFundDetailsVO> voList = null;
		 try{
			 if(stateId != null){
				 List<Object[]> objList = districtDAO.getDistrictIdName(stateId);
				 if(objList != null && objList.size() > 0){
					 voList = new ArrayList<LocationFundDetailsVO>();
					 for (Object[] objects : objList) {
						 LocationFundDetailsVO vo = new LocationFundDetailsVO();
						 vo.setId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						 vo.setName(commonMethodsUtilService.getStringValueForObject(objects[1]));
						 voList.add(vo);
					 }
				 }
			 }
		 }catch(Exception e){
			// e.printStackTrace();
			LOG.error(" Exception raised at getdistrictidandname(); "); 
		 }
		return voList;
	 }
	/**
	 * Date : 30/11/2017
	 * Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
	 * @description : to get ConstituencyNames details in a state
	 * @param : districtId Id
	 * @return  List<LocationVO> 
	 */
	public List<LocationVO> getConstituencyNamesByDistrictId( Long districtId){
		try{
			List<LocationVO> finalList = new ArrayList<LocationVO>(0);
			List<Object[]> objects=constituencyDAO.getConstituencyNamesByDistrictId(districtId);
			if(objects != null && objects.size() > 0){
				for(Object[] param : objects){
					LocationVO vo = new LocationVO();
					vo.setLocationId(Long.parseLong(commonMethodsUtilService.getStringValueForObject(param[0])));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
			return finalList;
		}catch(Exception e){
			LOG.error(" Exception occured in FundManagementDashboardService ,getLocationsNamesBySubLocation() ",e);
		}
		return null;
	}
	/**
	 * Date : 30/11/2017
	 * Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
	 * @description : to get Tehsils And LocalElectionBody details in a constituency
	 * @param : constituency Id
	 * @return  List<KeyValueVO> 
	 */
	public List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(Long constituencyId){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try {
			List<Long> tehsilIdsList = new ArrayList<Long>();
			List<Object[]> tehsils = tehsilDAO.getTehsilsForConstituency(constituencyId);
			if(tehsils != null && tehsils.size() > 0){
				for (Object[] objects : tehsils) {
					KeyValueVO vo = new KeyValueVO();
					String idStr="2" +commonMethodsUtilService.getStringValueForObject(objects[0]);
					vo.setKey(commonMethodsUtilService.getLongValueForString(idStr));
					vo.setValue(commonMethodsUtilService.getStringValueForObject(objects[1]));
					voList.add(vo);
					tehsilIdsList.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
				}
				List<KeyValueVO> localElectionBodyeList = getLocalElectionBodysByTehsilIds(tehsilIdsList);
				if(localElectionBodyeList != null && localElectionBodyeList.size() > 0){
					voList.addAll(localElectionBodyeList);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception Occured in getTehsilsForConstituency() method, Exception - ",e);
		}
		return voList;
	}
	/**
	 * Date : 30/11/2017
	* Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
	 * @description : to get  LocalElectionBody details in a tehsilIds
	 * @param : tehsilIds Ids list
	 * @return  List<KeyValueVO> 
	 */
	public List<KeyValueVO> getLocalElectionBodysByTehsilIds(List<Long> tehsilIds){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try{
		List<Object[]> localelectionBodyObjs = localElectionBodyDAO.getLocalElectionBodyByTehsilId(tehsilIds);
		if(localelectionBodyObjs != null && localelectionBodyObjs.size() > 0){
			for (Object[] param : localelectionBodyObjs) {
				KeyValueVO vo = new KeyValueVO();
				String idStr= "1"+commonMethodsUtilService.getStringValueForObject(param[0]);
				vo.setKey(commonMethodsUtilService.getLongValueForString(idStr));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				voList.add(vo);
			}
		}
		}catch(Exception e){
			LOG.error("Exception Occured in getLocalElectionBodysByTehsilIds() method, Exception - ",e);
		}
		return voList;
	}
	/**
	 * Date : 30/11/2017
	 * Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
	 * @description : to get  Panchayats details in a tehsil
	 * @param : tehsilId 
	 * @return  List<KeyValueVO> 
	 */
	public List<KeyValueVO> getPanchayatsByTehsilId(Long tehsilId){
		List<KeyValueVO> voList = new ArrayList<KeyValueVO>(0);
		try{
			List<Object[]> panchaytsObj = panchayatDAO.getPanchayatIdAndName(tehsilId);
			if(panchaytsObj != null && panchaytsObj.size() > 0){
				for (Object[] param : panchaytsObj) {
					KeyValueVO vo = new KeyValueVO();
					String idStr= "2"+commonMethodsUtilService.getStringValueForObject(param[0]);
					vo.setKey(commonMethodsUtilService.getLongValueForString(idStr));
					vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
					voList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception Occured in getLocalElectionBodysByTehsilIds() method, Exception - ",e);
		}
		return voList;
	}
	
	/*
     * Author : krishna
     * Date : 01/12/2017
     * Description : { Getting Department List }
     */
    public List<KeyValueVO> getPetitionDepartmentList(){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	try{
    		LOG.info("Entered into LocationDetailsService of getPetitionDepartmentDetailsList ");
    		List<Object[]> petitionDetailsObjsList = petitionDepartmentDAO.getAllPetitionList();
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			for(Object[] param: petitionDetailsObjsList){
    				KeyValueVO vo = new KeyValueVO();
    				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
    				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
    				resultList.add(vo);
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Exception occured at getMgnregsFMSWorksDetailsByCategory() in LocationDetailsService class ", e);
    	}
    	return resultList;
    	
    }
    /*
    * Author : krishna
     * Date : 01/12/2017
     * Description : { Getting Designation List }
     */
    public List<KeyValueVO> getPetitionDesignationList(){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	try{
    		LOG.info("Entered into LocationDetailsService of getPetitionDepartmentDetailsList ");
    		List<Object[]> petitionDetailsObjsList = petitionDesignationDAO.getpetitionDesignationList();
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			for(Object[] param: petitionDetailsObjsList){
    				KeyValueVO vo = new KeyValueVO();
    				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
    				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
    				resultList.add(vo);
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Exception occured at getMgnregsFMSWorksDetailsByCategory() in LocationDetailsService class ", e);
    	}
    	return resultList;
    	
    }
    
    public List<RepresentationRequestVO> getRepresentativeSearchWiseDetails(InputVO inputVO){
    	List<RepresentationRequestVO> resultList = new ArrayList<RepresentationRequestVO>();
    	Map<Long,RepresentationRequestVO> representWiseSearchMap = new HashMap<Long,RepresentationRequestVO>();
    	List<Long> memberidsLst = new ArrayList<Long>();
    	
    	try{
    		LOG.info("enterd into LocationDetailsService getRepresentativeSearchWiseDetails");
    		List<Object[]> representRefObjLst = petitionMemberDAO.getRepresentativeSearchDetailsBy(inputVO.getFilterType(),inputVO.getFilterValue());
    		if(representRefObjLst != null && representRefObjLst.size() >0){
    			for(Object[] objs : representRefObjLst){
    				Long petitinMembrId = commonMethodsUtilService.getLongValueForObject(objs[0]);
    				RepresentationRequestVO searchVO = representWiseSearchMap.get(petitinMembrId);
    				if(searchVO == null){
    					searchVO = new RepresentationRequestVO();
    					searchVO.setPetitionMemberId(petitinMembrId);
    					searchVO.setCandidateName(commonMethodsUtilService.getStringValueForObject(objs[2]));
    					searchVO.setReferrerCandidateId(commonMethodsUtilService.getLongValueForObject(objs[1]));
    					searchVO.setMobileNo(commonMethodsUtilService.getStringValueForObject(objs[3]));
    					searchVO.setAge(commonMethodsUtilService.getLongValueForObject(objs[4]));
    					memberidsLst.add(petitinMembrId);
    					representWiseSearchMap.put(searchVO.getPetitionMemberId(), searchVO);
    				}
    			}
    		}
    		
    		List<Object[]> workLocationObjLst = petitionWorkDetailsDAO.getWorkLocationDetailsByPetitionMemberId(memberidsLst);
    		if(workLocationObjLst != null && workLocationObjLst.size() >0){
    			for(Object[] workObj : workLocationObjLst){
    				Long memberId = commonMethodsUtilService.getLongValueForObject(workObj[0]);
    				RepresentationRequestVO workVO = representWiseSearchMap.get(memberId);
    				if(workVO != null){
    					workVO.setPetitionMemberId(commonMethodsUtilService.getLongValueForObject(workObj[0]));
    					workVO.setWorkName(commonMethodsUtilService.getStringValueForObject(workObj[1]));
    					workVO.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(workObj[2]));
    					workVO.setIsPreviousPetition(commonMethodsUtilService.getStringValueForObject(workObj[3]));
    					workVO.setPreviousPetitionRefNo(commonMethodsUtilService.getStringValueForObject(workObj[4]));
    					
    				}else{
    					workVO = new RepresentationRequestVO();
    					workVO.setPetitionMemberId(commonMethodsUtilService.getLongValueForObject(workObj[0]));
    					workVO.setWorkName(commonMethodsUtilService.getStringValueForObject(workObj[1]));
    					workVO.setNoOfWorks(commonMethodsUtilService.getLongValueForObject(workObj[2]));
    					workVO.setIsPreviousPetition(commonMethodsUtilService.getStringValueForObject(workObj[3]));
    					workVO.setPreviousPetitionRefNo(commonMethodsUtilService.getStringValueForObject(workObj[4]));
    					representWiseSearchMap.put(workVO.getPetitionMemberId(),workVO);
    				}
    			}
    		}
    		
    		if(representWiseSearchMap != null && representWiseSearchMap.size() >0){
    			resultList.addAll(representWiseSearchMap.values());
    		}
    		
    	}catch(Exception e){
    		LOG.error("Exception raised into LocationDetailsService of getRepresentativeSearchWiseDetails() ",e);
    	}
    	return resultList;
    }
}
