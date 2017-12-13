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
import com.itgrids.dao.IPmBriefLeadDAO;
import com.itgrids.dao.IPmDepartmentDAO;
import com.itgrids.dao.IPmDesignationDAO;
import com.itgrids.dao.IPmGrantDAO;
import com.itgrids.dao.IPmLeadDAO;
import com.itgrids.dao.IPmStatusDAO;
import com.itgrids.dao.IPmSubjectDAO;
import com.itgrids.dao.IPmWorkTypeDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dao.IWorkMainCategoryDAO;
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
	//@Autowired
	//private IPetitionDesignationDAO petitionDesignationDAO;
	//@Autowired
	//private IPetitionDepartmentDAO petitionDepartmentDAO;
	@Autowired
	private IPmDesignationDAO pmDesignationDAO;
	
	@Autowired
	private IPmDepartmentDAO pmDepartmentDAO;
	//@Autowired
	//private IPetitionMemberDAO petitionMemberDAO;
	//@Autowired
	//private IPetitionWorkDetailsDAO petitionWorkDetailsDAO;
	@Autowired
	private IParliamentAssemblyDAO parliamentAssemblyDAO;
	@Autowired
	private IPmSubjectDAO pmSubjectDAO;
	@Autowired
	private IPmLeadDAO pmLeadDAO;
	@Autowired
	private IPmBriefLeadDAO pmBriefLeadDAO;
	@Autowired 
	private IPmGrantDAO pmGrantDAO;
	@Autowired 
	private IPmStatusDAO pmStatusDAO;
	@Autowired 
	private IPmWorkTypeDAO pmWorkTypeDAO;
	@Autowired 
	private IWorkMainCategoryDAO workMainCategoryDAO;
	 /**
		 * Date : 30/11/2017
		 * Author :babu kurakula <href:kondababu.kurakula@itgrids.com>
		 * @description : to get districs details in a state
		 * @param : state Id
		 * @return  List<LocationFundDetailsVO> 
		 */
	public List<LocationFundDetailsVO> getAllDistrictsInState(Long stateId,String searchType,Long searchId){
		 List<LocationFundDetailsVO> voList = null;
		 try{
			 if(stateId != null){
				 List<Object[]> objList = null;
				 if(searchType != null){
					 if(searchType.equalsIgnoreCase("all")){
						 objList = districtDAO.getDistrictIdName(stateId);
					 } else if(searchType.trim().equalsIgnoreCase("petitionsData")){
						 ;//objList = districtDAO.getPetitionsDistrictsList(stateId,null,null);
					 } else if(searchType.trim().equalsIgnoreCase("designation")){
						 ;//objList = districtDAO.getPetitionsDistrictsList(stateId,searchType,searchId);
						 //List<Object[]> refLocationsList = districtDAO.getPetitionsDistrictsList(stateId,"refferer",searchId);
					 }else{
						 objList = districtDAO.getReffererCandidatesDistrictsList(stateId,searchType,searchId);
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
	public List<LocationVO> getConstituencyNamesByDistrictId( Long districtId,String searchType,Long searchId){
		try{
			List<LocationVO> finalList = new ArrayList<LocationVO>(0);
			List<Object[]> objects = null;
			 if(searchType != null){
				 if(searchType.equalsIgnoreCase("all")){
					 objects = constituencyDAO.getConstituencyNamesByDistrictId(districtId);
				 }else if(searchType.trim().equalsIgnoreCase("petitionsData")){
					 ;//objects = constituencyDAO.getPetitionsConstituencyList(districtId,null,null);
				 }else{
					 objects = constituencyDAO.getPetitionsConstituencyList(districtId,searchType,searchId);
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
	public List<KeyValueVO> getTehsilsAndLocalElectionBodyForConstituencyId(Long constituencyId,String searchType,Long searchId){
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
    public List<KeyValueVO> getPmDepartmentList(String searchType){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	try{
    		LOG.info("Entered into LocationDetailsService of getPmDepartmentList ");
    		List<Object[]> petitionDetailsObjsList = null; 
    		if(searchType != null){
    			if(searchType.trim().equalsIgnoreCase("all"))
    				petitionDetailsObjsList = pmDepartmentDAO.getAllPmDepartmentsList();
    			else if(searchType.trim().equalsIgnoreCase("petitionGivenDepts"))
    				petitionDetailsObjsList = pmDepartmentDAO.getGivenPmDepartmentsList();
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
    		LOG.error("Exception occured at getPmDepartmentList() in LocationDetailsService class ", e);
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
    				petitionDetailsObjsList = pmDesignationDAO.getAllpetitionDesignationList();
    			else if(searchType.trim().equalsIgnoreCase("refCandidateDesignations"))
    				petitionDetailsObjsList = pmDesignationDAO.getAllReferredCandidateDesignationList();
    			else if(searchType.trim().equalsIgnoreCase("petitionGivenRefCandidateDesignations")){
    				petitionDetailsObjsList = pmDesignationDAO.getGivenPetitionCandidateDesignationList();
    				List<Object[]> petitionReprDesignationsList = pmDesignationDAO.getGivenpetitionReprDesignationsList();
    				if(!commonMethodsUtilService.isListOrSetValid(petitionDetailsObjsList))
    					petitionDetailsObjsList =new ArrayList<Object[]>();
    				if(commonMethodsUtilService.isListOrSetValid(petitionReprDesignationsList))
    					petitionDetailsObjsList.addAll(petitionReprDesignationsList);
    			}
    				
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
    
public List<KeyValueVO>  getPmSubjectList(){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	    try{
    		LOG.info("Entered into LocationDetailsService of getPmSubjectList ");
    		List<Object[]> petitionDetailsObjsList = pmSubjectDAO.getPmSubjectList();
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
    		LOG.error("Exception occured at getPmSubjectList() in LocationDetailsService class ", e);
    	}
    	return resultList;
    }

public List<KeyValueVO>  getPmSubSubjectList(Long subjectId){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPetitionSubSubjectList ");
		List<Object[]> petitionDetailsObjsList = pmSubjectDAO.getPmSubSubjectList(subjectId);
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
		LOG.error("Exception occured at getPmSubSubjectList() in LocationDetailsService class ", e);
	}
	return resultList;
}

/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Lead Details List }
  */
public List<KeyValueVO>  getPmLeadDetailsList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPmLeadDetailsList ");
		List<Object[]> petitionDetailsObjsList = pmLeadDAO.getPmLeadDetailsList();
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
		LOG.error("Exception occured at getPmLeadDetailsList() in LocationDetailsService class ", e);
	}
	return resultList;
}
/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Brief Lead Details List }
  */
public List<KeyValueVO>  getPmBriefLeadList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPmBriefLeadList ");
		List<Object[]> petitionDetailsObjsList = pmBriefLeadDAO.gePmBriefLeadDetailsList();
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
		LOG.error("Exception occured at getPmBriefLeadList() in LocationDetailsService class ", e);
	}
	return resultList;
}

/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Grant List Details }
  */
public List<KeyValueVO>  getPmGrantList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPmGrantList ");
		List<Object[]> petitionDetailsObjsList = pmGrantDAO.getPmGrantList();
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
		LOG.error("Exception occured at getPmGrantList() in LocationDetailsService class ", e);
	}
	return resultList;
}
/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Status List Details }
  */
public List<KeyValueVO>  getPmStatusList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPmStatusList ");
		List<Object[]> petitionDetailsObjsList = pmStatusDAO.getPmStatusList();
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
	}catch(Exception e){
		LOG.error("Exception occured at getPmStatusList() in LocationDetailsService class ", e);
	}
	return resultList;
}

/*
 * Author : krishna
  * Date : 04/12/2017
  * Description : { Getting Petition Status List Details }
  */
public List<KeyValueVO>  getWorkTypeList(){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getWorkTypeList ");
		List<Object[]> workTypeObjsList = pmWorkTypeDAO.getWorkTypeList();
		if(workTypeObjsList != null && workTypeObjsList.size() >0){
			for(Object[] param: workTypeObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
	}catch(Exception e){
		LOG.error("Exception occured at getWorkTypeList() in LocationDetailsService class ", e);
	}
	return resultList;
}
}
