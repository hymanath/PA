package com.itgrids.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.IPetitionBriefLeadDAO;
import com.itgrids.dao.IPetitionDepartmentDAO;
import com.itgrids.dao.IPetitionDesignationDAO;
import com.itgrids.dao.IPetitionGrantDAO;
import com.itgrids.dao.IPetitionLeadDAO;
import com.itgrids.dao.IPetitionMemberDAO;
import com.itgrids.dao.IPetitionStatusDAO;
import com.itgrids.dao.IPetitionSubjectDAO;
import com.itgrids.dao.IPetitionWorkDetailsDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dao.impl.PetitionSubjectDAO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.service.ILocationDetailsService;
import com.itgrids.utils.CommonMethodsUtilService;
import com.itgrids.utils.IConstants;

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
	@Autowired
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	@Autowired
	private IPetitionSubjectDAO petitionSubjectDAO;
	@Autowired
	private IPetitionLeadDAO petitionLeadDAO;
	@Autowired
	private IPetitionBriefLeadDAO petitionBriefLeadDAO;
	@Autowired 
	private IPetitionGrantDAO petitionGrantDAO;
	@Autowired 
	private IPetitionStatusDAO petitionStatusDAO;
	 /**
		 * Date : 30/11/2017
		 * Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
		 * @description : to get districs details in a state
		 * @param : state Id
		 * @return  List<LocationFundDetailsVO> 
		 */
	public List<LocationFundDetailsVO> getAllDistrictsInState(Long stateId,String searchType){
		 List<LocationFundDetailsVO> voList = null;
		 try{
			 if(stateId != null){
				 List<Object[]> objList = null;
				 if(searchType != null){
					 if(searchType.equalsIgnoreCase("all")){
						 objList = districtDAO.getDistrictIdName(stateId);
					 } else if(searchType.trim().equalsIgnoreCase("petitionsData")){
						 objList = districtDAO.getPetitionsDistrictsList(stateId);
					 }
				 }
				 
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

			 Collections.sort(voList,new Comparator<LocationFundDetailsVO>() {
					public int compare(LocationFundDetailsVO o1,
							LocationFundDetailsVO o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});

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
	public List<LocationVO> getConstituencyNamesByDistrictId( Long districtId,String searchType){
		try{
			List<LocationVO> finalList = new ArrayList<LocationVO>(0);
			List<Object[]> objects = null;
			 if(searchType != null){
				 if(searchType.equalsIgnoreCase("all")){
					 objects = constituencyDAO.getConstituencyNamesByDistrictId(districtId);
				 } else if(searchType.trim().equalsIgnoreCase("petitionsData")){
					 objects = constituencyDAO.getPetitionsConstituencyList(districtId);
				 }
			 }
			 
			if(objects != null && objects.size() > 0){
				for(Object[] param : objects){
					LocationVO vo = new LocationVO();
					vo.setLocationId(Long.parseLong(commonMethodsUtilService.getStringValueForObject(param[0])));
					vo.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
			Collections.sort(finalList,new Comparator<LocationVO>() {
				public int compare(LocationVO o1,LocationVO o2) {
					return o1.getLocationName().compareTo(o2.getLocationName());
				}
			});
			 
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
			
			Collections.sort(voList,new Comparator<KeyValueVO>() {
				public int compare(KeyValueVO o1,KeyValueVO o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
			
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
				vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
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
			Collections.sort(voList,new Comparator<KeyValueVO>() {
				public int compare(KeyValueVO o1,KeyValueVO o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
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
    public List<KeyValueVO> getPetitionDepartmentList(String searchType){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	try{
    		LOG.info("Entered into LocationDetailsService of getPetitionDepartmentDetailsList ");
    		List<Object[]> petitionDetailsObjsList = null; 
    		if(searchType != null){
    			if(searchType.trim().equalsIgnoreCase("all"))
    				petitionDetailsObjsList = petitionDepartmentDAO.getAllPetitionDepartmentsList();
    			else if(searchType.trim().equalsIgnoreCase("petitionGivenDepts"))
    				petitionDetailsObjsList = petitionDepartmentDAO.getGivenPetitionDepartmentsList();
    		}
    		
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			for(Object[] param: petitionDetailsObjsList){
    				KeyValueVO vo = new KeyValueVO();
    				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
    				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
    				resultList.add(vo);
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Exception occured at getPetitionDepartmentList() in LocationDetailsService class ", e);
    	}
    	return resultList;
    	
    }
    /*
    * Author : krishna
     * Date : 01/12/2017
     * Description : { Getting Designation List }
     */
    public List<KeyValueVO> getPetitionDesignationList(String searchType){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	try{
    		LOG.info("Entered into LocationDetailsService of getPetitionDepartmentDetailsList ");
    		List<Object[]> petitionDetailsObjsList = null;
    		if(searchType != null){
    			if(searchType.trim().equalsIgnoreCase("all"))
    				petitionDetailsObjsList = petitionDesignationDAO.getAllpetitionDesignationList();
    			else if(searchType.trim().equalsIgnoreCase("refCandidateDesignations"))
    				petitionDetailsObjsList = petitionDesignationDAO.getAllReferredCandidateDesignationList();
    			else if(searchType.trim().equalsIgnoreCase("petitionGivenRefCandidateDesignations"))
    				petitionDetailsObjsList = petitionDesignationDAO.getGivenPetitionCandidateDesignationList();
    				List<Object[]> petitionReprDesignationsList = petitionDesignationDAO.getGivenpetitionReprDesignationsList();
    				if(!commonMethodsUtilService.isListOrSetValid(petitionDetailsObjsList))
    					petitionDetailsObjsList =new ArrayList<Object[]>();
    				if(commonMethodsUtilService.isListOrSetValid(petitionReprDesignationsList))
    					petitionDetailsObjsList.addAll(petitionReprDesignationsList);
    				
    		}
    		 
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			List<Long> addedDesignationsList = new ArrayList<Long>();
    			for(Object[] param: petitionDetailsObjsList){
    				if(!addedDesignationsList.contains(commonMethodsUtilService.getLongValueForObject(param[0]))){
    					KeyValueVO vo = new KeyValueVO();
        				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
        				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
        				resultList.add(vo);
        				addedDesignationsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
    				}
    			}
    			
    			Collections.sort(resultList,new Comparator<KeyValueVO>() {
    				public int compare(KeyValueVO o1,KeyValueVO o2) {
    					return o1.getValue().compareTo(o2.getValue());
    				}
    			});
    		}
    	}catch(Exception e){
    		LOG.error("Exception occured at getPetitionDepartmentDetailsList() in LocationDetailsService class ", e);
    	}
    	return resultList;
    	
    }
    
    public List<KeyValueVO>  getParliamentIdsByConstituencyList(){
    	
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	List<Long> parliamentIdsLst = Arrays.asList(IConstants.AP_PARLIAMENT_IDS_LIST);
    	try{
    		LOG.info("Entered into LocationDetailsService of getParliamentByDistricList ");
    		List<Object[]> petitionDetailsObjsList = parliamentAssemblyDAO.getParliamentIdsByConstituencyList(parliamentIdsLst);
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			for(Object[] param: petitionDetailsObjsList){
    				KeyValueVO vo = new KeyValueVO();
    				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
    				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
    				resultList.add(vo);
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Exception occured at getParliamentByDistricList() in LocationDetailsService class ", e);
    	}
    	return resultList;
    }
    
public List<KeyValueVO>  getPetitionSubjectList(){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	    try{
    		LOG.info("Entered into LocationDetailsService of getPetitionSubjectList ");
    		List<Object[]> petitionDetailsObjsList = petitionSubjectDAO.getPetitionSubjectList();
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			for(Object[] param: petitionDetailsObjsList){
    				KeyValueVO vo = new KeyValueVO();
    				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
    				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
    				resultList.add(vo);
    			}
    		}
    		 Collections.sort(resultList,new Comparator<KeyValueVO>() {
 				public int compare(KeyValueVO o1,
 						KeyValueVO o2) {
 					return o1.getValue().compareTo(o2.getValue());
 				}
 			});
    	}catch(Exception e){
    		LOG.error("Exception occured at getPetitionSubjectList() in LocationDetailsService class ", e);
    	}
    	return resultList;
    }

public List<KeyValueVO>  getPetitionSubSubjectList(Long subjectId){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPetitionSubSubjectList ");
		List<Object[]> petitionDetailsObjsList = petitionSubjectDAO.getPetitionSubSubjectList(subjectId);
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
		 Collections.sort(resultList,new Comparator<KeyValueVO>() {
				public int compare(KeyValueVO o1,
						KeyValueVO o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
	}catch(Exception e){
		LOG.error("Exception occured at getPetitionSubSubjectList() in LocationDetailsService class ", e);
	}
	return resultList;
}

/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Lead Details List }
  */
public List<KeyValueVO>  getPetitionLeadDetailsList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPetitionLeadDetailsList ");
		List<Object[]> petitionDetailsObjsList = petitionLeadDAO.getPetitionLeadDetailsList();
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
		 Collections.sort(resultList,new Comparator<KeyValueVO>() {
				public int compare(KeyValueVO o1,
						KeyValueVO o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
	}catch(Exception e){
		LOG.error("Exception occured at getPetitionLeadDetailsList() in LocationDetailsService class ", e);
	}
	return resultList;
}
/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Brief Lead Details List }
  */
public List<KeyValueVO>  getPetitionBriefLeadList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPetitionBriefLeadList ");
		List<Object[]> petitionDetailsObjsList = petitionBriefLeadDAO.gePetitionBriefLeadDetailsList();
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
		 Collections.sort(resultList,new Comparator<KeyValueVO>() {
				public int compare(KeyValueVO o1,
						KeyValueVO o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
	}catch(Exception e){
		LOG.error("Exception occured at getPetitionBriefLeadList() in LocationDetailsService class ", e);
	}
	return resultList;
}

/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Grant List Details }
  */
public List<KeyValueVO>  getPetitionGrantList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPetitionGrantList ");
		List<Object[]> petitionDetailsObjsList = petitionGrantDAO.getPetitionGrantList();
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
		 Collections.sort(resultList,new Comparator<KeyValueVO>() {
				public int compare(KeyValueVO o1,
						KeyValueVO o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});
	}catch(Exception e){
		LOG.error("Exception occured at getPetitionGrantList() in LocationDetailsService class ", e);
	}
	return resultList;
}
/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Status List Details }
  */
public List<KeyValueVO>  getPetitionStatusList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPetitionStatusList ");
		List<Object[]> petitionDetailsObjsList = petitionStatusDAO.getPetitionStatusList();
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
	}catch(Exception e){
		LOG.error("Exception occured at getPetitionStatusList() in LocationDetailsService class ", e);
	}
	return resultList;
}
}
