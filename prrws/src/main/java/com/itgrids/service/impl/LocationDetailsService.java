package com.itgrids.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itgrids.dao.IConstituencyDAO;
import com.itgrids.dao.IDistrictDAO;
import com.itgrids.dao.ILocalElectionBodyDAO;
import com.itgrids.dao.IPanchayatDAO;
import com.itgrids.dao.IParliamentAssemblyDAO;
import com.itgrids.dao.IPmBriefLeadDAO;
import com.itgrids.dao.IPmDepartmentDAO;
import com.itgrids.dao.IPmDepartmentDesignationHierarchyDAO;
import com.itgrids.dao.IPmDeptSubjectDetailsDAO;
import com.itgrids.dao.IPmDesignationDAO;
import com.itgrids.dao.IPmGrantDAO;
import com.itgrids.dao.IPmLeadDAO;
import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.dao.IPmRefCandidateDesignationDAO;
import com.itgrids.dao.IPmRepresenteeDAO;
import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.dao.IPmStatusDAO;
import com.itgrids.dao.IPmSubWorkDetailsDAO;
import com.itgrids.dao.IPmSubjectDAO;
import com.itgrids.dao.IPmWorkTypeDAO;
import com.itgrids.dao.ITehsilDAO;
import com.itgrids.dao.IWorkMainCategoryDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.LocationFundDetailsVO;
import com.itgrids.dto.LocationVO;
import com.itgrids.dto.PmOfficerVO;
import com.itgrids.service.ILocationDetailsService;
import com.itgrids.service.IPmRequestDetailsService;
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
	private IPmDeptSubjectDetailsDAO pmDeptSubjectDetailsDAO;
	//@Autowired
	//private IPetitionDesignationDAO petitionDesignationDAO;
	//@Autowired
	//private IPetitionDepartmentDAO petitionDepartmentDAO;
	//@Autowired
	//private IPmDesignationDAO pmDesignationDAO;
	
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
	@Autowired
	private IPmDesignationDAO pmDesignationDAO;
	@Autowired
	private IPmSubWorkDetailsDAO pmSubWorkDetailsDAO;
	@Autowired
	private IPmRepresenteeDAO pmRepresenteeDAO;
	@Autowired
	private IPmRefCandidateDAO pmRefCandidateDAO;
	@Autowired
	private IPmRefCandidateDesignationDAO pmRefCandidateDesignationDAO;
	@Autowired
	private IPmRepresenteeDesignationDAO pmRepresenteeDesignationDAO;
	@Autowired
	private IPmRequestDetailsService pmRequestDetailsService;
	@Autowired
	private IPmDepartmentDesignationHierarchyDAO pmDepartmentDesignationHierarchyDAO;
	
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
						 String name = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(objects[1]));
						 vo.setName(name);
						 voList.add(vo);
					 }
				 }
			  }
			 if(commonMethodsUtilService.isListOrSetValid(voList)){
			 Collections.sort(voList,new Comparator<LocationFundDetailsVO>() {
					public int compare(LocationFundDetailsVO o1,
							LocationFundDetailsVO o2) {
						return o1.getName().compareTo(o2.getName());
					}
				});
			 }
		 }catch(Exception e){
			// e.printStackTrace();
			LOG.error(" Exception raised at getdistrictidandname(); ",e); 
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
				 }else if(searchType.trim().equalsIgnoreCase("self") || searchType.trim().equalsIgnoreCase("represent")){
					 objects = constituencyDAO.getConstituencyNamesByDistrictId(districtId);
				 }else{
					 objects = constituencyDAO.getPetitionsConstituencyList(districtId,searchType,searchId);
				 }
			 }
			 
			if(objects != null && objects.size() > 0){
				for(Object[] param : objects){
					LocationVO vo = new LocationVO();
					vo.setLocationId(Long.parseLong(commonMethodsUtilService.getStringValueForObject(param[0])));
					String locationName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
					vo.setLocationName(locationName);
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
					vo.setValue(commonMethodsUtilService.getCapitalStringValueForObject(objects[1]));
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
				vo.setValue(commonMethodsUtilService.getCapitalStringValueForObject(param[1]) +" "+commonMethodsUtilService.getCapitalStringValueForObject(param[2]));
				//vo.setElectionType(commonMethodsUtilService.getStringValueForObject(param[2]));
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
					String panchaytName = commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
					vo.setValue(panchaytName);
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
    				//String deptName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
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
    				;
    			}
    				
    		}
    		 
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			List<Long> addedDesignationsList = new ArrayList<Long>();
    			for(Object[] param: petitionDetailsObjsList){
    				if(!addedDesignationsList.contains(commonMethodsUtilService.getLongValueForObject(param[0]))){
    					KeyValueVO vo = new KeyValueVO();
        				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
        				//String DesigName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
        				vo.setValue(param[1] != null ? param[1].toString().trim():"");
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
    				String Cname = commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
    				vo.setValue(Cname);
    				resultList.add(vo);
    			}
    		}
    	}catch(Exception e){
    		LOG.error("Exception occured at getParliamentByDistricList() in LocationDetailsService class ", e);
    	}
    	return resultList;
    }
    
public List<KeyValueVO>  getPmSubjectList(Long deptId){
    	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
    	    try{
    		LOG.info("Entered into LocationDetailsService of getPmSubjectList ");
    		List<Object[]> petitionDetailsObjsList = pmDeptSubjectDetailsDAO.getPmSubjectList(deptId);
    		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
    			for(Object[] param: petitionDetailsObjsList){
    				KeyValueVO vo = new KeyValueVO();
    				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
    				String subjectName = commonMethodsUtilService.getStringValueForObject(commonMethodsUtilService.getStringValueForObject(param[1]));
    				vo.setValue(subjectName);
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
				String subSubjectName = commonMethodsUtilService.getStringValueForObject(commonMethodsUtilService.getStringValueForObject(param[1]));
				vo.setValue(subSubjectName);
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
public List<KeyValueVO>  getPmBriefLeadList(Long designationId,List<Long> deptIds,List<Long> statusIds,String fromDate,String toDate){
	List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
	    try{
		LOG.info("Entered into LocationDetailsService of getPmBriefLeadList ");
		Date startDate = null;
		Date endDate = null;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		if(fromDate != null &&  !fromDate.isEmpty() && !toDate.isEmpty() && toDate!= null){
			startDate = format.parse(fromDate);
			endDate = format.parse(toDate);
		}
		List<Object[]> petitionDetailsObjsList = null;
		if(designationId == null || designationId.longValue()==0L)
			petitionDetailsObjsList = pmBriefLeadDAO.getAllPmBriefLeadDetailsDeptWiseList(deptIds,statusIds,startDate,endDate);
		else
			petitionDetailsObjsList = pmBriefLeadDAO.gePmBriefLeadDetailsList(designationId);
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				resultList.add(vo);
			}
		}
		/* Collections.sort(resultList,new Comparator<KeyValueVO>() {
				public int compare(KeyValueVO o1,
						KeyValueVO o2) {
					return o1.getValue().compareTo(o2.getValue());
				}
			});*/
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
		List<Object[]> petitionDetailsObjsList = pmStatusDAO.getPmStatusList(null);
		if(petitionDetailsObjsList != null && petitionDetailsObjsList.size() >0){
			for(Object[] param: petitionDetailsObjsList){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				vo.setValue(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
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
				String workName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
				vo.setValue(workName);
				resultList.add(vo);
			}
		}
	}catch(Exception e){
		LOG.error("Exception occured at getWorkTypeList() in LocationDetailsService class ", e);
	}
	return resultList;
}

public List<KeyValueVO> getPmDesignations(String searchType){
	List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
	try{
		List<Object[]> desiObjs= null;
		if(searchType != null && !searchType.trim().equalsIgnoreCase("all"))
			desiObjs=pmDesignationDAO.getAllReferredCandidateDesignationList();
		else 
			desiObjs=pmDesignationDAO.getAllpetitionDesignationList();
		
		if(desiObjs != null && desiObjs.size() > 0){
			for( Object [] param:  desiObjs){
				KeyValueVO vo = new KeyValueVO();
				vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
			//	String designation = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
				vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
				finalList.add(vo);
			}
		}
	}catch(Exception e){
		LOG.error("Exception occured at getPmDesignations() in LocationDetailsService class ", e);

	}
	return finalList;
}

	public List<KeyValueVO> getDistrictBySearchType(InputVO  inputVO,List<Long> deptIds){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			Date fromDateStr = null;
			Date toDateStr = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			List<Object[]> districtObjs=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null && inputVO.getFromDate().length() > 0 && inputVO.getToDate().length() > 0){
				fromDateStr = dateFormat.parse(inputVO.getFromDate());
				toDateStr = dateFormat.parse(inputVO.getToDate());
			}
			Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(inputVO.getLocationId(),null,inputVO);
			 Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			/*KeyValueVO deptVO = getDeptIdsListBYUserIds(userId);
			List<Long> deptIds = deptVO.getDeptIdsList();*/
			if(inputVO.getFilterType() !=null && (inputVO.getFilterType().trim().equalsIgnoreCase("work") || inputVO.getFilterType().trim().equalsIgnoreCase("pmOfficer") || inputVO.getFilterType().trim().equalsIgnoreCase("subject") || inputVO.getFilterType().trim().equalsIgnoreCase("lead"))){
				districtObjs=pmSubWorkDetailsDAO.getAllDistricts(fromDateStr,toDateStr,deptIds,inputVO.getDesignationIds(),inputVO.getpType(),inputVO.getSubProgramIdsList(),inputVO.getStatusIds(),inputVO.getLeadIdsList(),petitionIdsList);
			}else if(inputVO.getFilterType() !=null && inputVO.getFilterType().trim().equalsIgnoreCase("referral")){
				districtObjs=pmRefCandidateDAO.getAllDistrictsByReferral(fromDateStr,toDateStr,deptIds,inputVO.getDesignationIds(),inputVO.getpType(),inputVO.getStatusIds(),petitionIdsList);
			}else if(inputVO.getFilterType() !=null && inputVO.getFilterType().trim().equalsIgnoreCase("referrelDesignation")){
				districtObjs=pmRefCandidateDesignationDAO.getAllDistrictsByReferalAndDesignation(fromDateStr,toDateStr,deptIds,inputVO.getDesignationIds(),inputVO.getpType(),inputVO.getStatusIds(),petitionIdsList);
			}else if(inputVO.getFilterType() !=null && (inputVO.getFilterType().trim().equalsIgnoreCase("representee"))){
				districtObjs=pmRepresenteeDAO.getAllDistrictsBySearchType(null,fromDateStr,toDateStr,deptIds,inputVO.getDesignationIds(),inputVO.getpType(),inputVO.getStatusIds(),petitionIdsList);
			}else if(inputVO.getFilterType() !=null && ( inputVO.getFilterType().trim().equalsIgnoreCase("name") || inputVO.getFilterType().trim().equalsIgnoreCase("mobile") || inputVO.getFilterType().trim().equalsIgnoreCase("email") || inputVO.getFilterType().trim().equalsIgnoreCase("endorsmentNO"))){
				districtObjs=pmRepresenteeDAO.getAllDistrictsBySearchType(inputVO,fromDateStr,toDateStr,deptIds,inputVO.getDesignationIds(),inputVO.getpType(),inputVO.getStatusIds(),petitionIdsList);
			}else if(inputVO.getFilterType() !=null && inputVO.getFilterType().trim().equalsIgnoreCase("representeeDesignation")){
				districtObjs=pmRepresenteeDesignationDAO.getAllDistrictsByRepresenteeDesignationWise(fromDateStr,toDateStr,deptIds,inputVO.getDesignationIds(),inputVO.getpType(),inputVO.getStatusIds(),petitionIdsList);
			}
			if(districtObjs != null && districtObjs.size() >0 ){
				for(Object[] param : districtObjs ){
					KeyValueVO vo = new KeyValueVO();
					vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
					String districtName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setValue(districtName);
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getDistrictBySearchType() in LocationDetailsService class ", e);
		}
		return finalList;
	}
	
	public List<KeyValueVO> getDistrictBySearchTypeInsubject(InputVO inputVO){
		List<KeyValueVO> disticList = new ArrayList<KeyValueVO>();
		try {
			LOG.info("Entered into LocationDetailsService of getDistrictBySearchTypeInsubject");
			Date fromDateStr = null;
			Date toDateStr = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			List<Object[]> districtObjs=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null && inputVO.getFromDate().length() > 0 && inputVO.getToDate().length() > 0){
				fromDateStr = dateFormat.parse(inputVO.getFromDate());
				toDateStr = dateFormat.parse(inputVO.getToDate());
			}
			//Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(inputVO.getLocationId(),null,inputVO);
			// Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			if(inputVO.getFilterType() != null && inputVO.getFilterType().length() >0 && inputVO.getFilterType().equalsIgnoreCase("work")){
				List<Object[]> districtSubjectList = pmSubWorkDetailsDAO.getAllDistrictsInSubjectWiseSearch(fromDateStr,toDateStr,inputVO.getDeptIdsList(),inputVO.getSubjectIdsLst(),inputVO.getSubSubjectIdsLst(),null);
				if(districtSubjectList != null && districtSubjectList.size() >0){
					for(Object[] objs : districtSubjectList){
						KeyValueVO disticVO = new KeyValueVO();
						disticVO.setId(commonMethodsUtilService.getLongValueForObject(objs[0]));
						disticVO.setName(commonMethodsUtilService.getStringValueForObject(objs[1]));
						disticList.add(disticVO);
					}
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised into LocationDetailsService of getDistrictBySearchTypeInsubject",e);
		}
		return disticList;
		
	}
	public List<KeyValueVO>  getConstituencyBySearchTypeAndDistrictIdInSubSubject(InputVO inputVO){
		List<KeyValueVO> resultList = new ArrayList<KeyValueVO>();
		try {
			LOG.info("Entered into LocationDetailsService of getConstituencyBySearchTypeAndDistrictIdInSubSubject ");
			Date fromDateStr = null;
			Date toDateStr = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			List<Object[]> districtObjs=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null && inputVO.getFromDate().length() > 0 && inputVO.getToDate().length() > 0){
				fromDateStr = dateFormat.parse(inputVO.getFromDate());
				toDateStr = dateFormat.parse(inputVO.getToDate());
			}
			//Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(inputVO.getLocationId(),null,inputVO);
			// Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			if(inputVO.getFilterType() != null && inputVO.getFilterType().length() >0 && inputVO.getFilterType().equalsIgnoreCase("work")){
				List<Object[]> constituencyObjesList = pmSubWorkDetailsDAO.getConstituencyBySearchTypeAndDistrictIdInSubSubject(fromDateStr,toDateStr,inputVO.getSearchLvlVals(),inputVO.getFilterType(),inputVO.getDeptIdsList(),inputVO.getSubjectIdsLst(),inputVO.getSubSubjectIdsLst(),null);
				 if(constituencyObjesList != null && constituencyObjesList.size() >0){
					 for(Object[] param : constituencyObjesList){
						 KeyValueVO vo = new KeyValueVO();
						 vo.setId(commonMethodsUtilService.getLongValueForObject(param[0]));
						 vo.setName(commonMethodsUtilService.getStringValueForObject(param[1]));
						 resultList.add(vo);
						 
					 }
				 }
				
			}
			
		} catch (Exception e) {
		   LOG.error("Exception raised into LocationDetailsService of getConstituencyBySearchTypeAndDistrictIdInSubSubject ",e);
		}
		return resultList;
		
	}
	
	public List<KeyValueVO> getConstituenciesBySearchTypeAndDistrictId(InputVO  inputVO,List<Long> districtIds,List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,List<Long> subjectIds,List<Long> leadIdsList){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			Date fromDateStr = null;
			Date toDateStr = null;
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			//List<Object[]> districtObjs=null;
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null && inputVO.getFromDate().length() > 0 && inputVO.getToDate().length() > 0){
				fromDateStr = dateFormat.parse(inputVO.getFromDate());
				toDateStr = dateFormat.parse(inputVO.getToDate());
			}
			Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(inputVO.getLocationId(),null,inputVO);
			 Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			List<Object[]> conObjs=null;
			if(inputVO.getFilterType() !=null && (inputVO.getFilterType().trim().equalsIgnoreCase("work") || inputVO.getFilterType().trim().equalsIgnoreCase("pmOfficer") || inputVO.getFilterType().trim().equalsIgnoreCase("subject") ||  inputVO.getFilterType().trim().equalsIgnoreCase("lead"))){
				conObjs=pmSubWorkDetailsDAO.getAllConstituenciesByDistricId(fromDateStr,toDateStr,districtIds,deptIds,pmDesignationIds,type, statIds,petitionIdsList,subjectIds,leadIdsList);
			}else if(inputVO.getFilterType() !=null && inputVO.getFilterType().trim().equalsIgnoreCase("referral")){
				conObjs=pmRefCandidateDAO.getAllConstituenciesByReferralAndDistrict(fromDateStr,toDateStr,districtIds,deptIds,pmDesignationIds,type, statIds,petitionIdsList);
			}else if(inputVO.getFilterType() !=null && inputVO.getFilterType().trim().equalsIgnoreCase("referrelDesignation")){
				conObjs=pmRefCandidateDesignationDAO.getAlConstituenciesByReferalAndDesignationBydistrict(fromDateStr,toDateStr,districtIds,deptIds,pmDesignationIds,type,statIds,petitionIdsList);
			}else if(inputVO.getFilterType() !=null && (inputVO.getFilterType().trim().equalsIgnoreCase("representee"))){
				conObjs=pmRepresenteeDAO.getAlConstituenciesBySearchType(null,fromDateStr,toDateStr,districtIds,deptIds,pmDesignationIds,type,statIds,petitionIdsList);
			}else if(inputVO.getFilterType() !=null && ( inputVO.getFilterType().trim().equalsIgnoreCase("name") || inputVO.getFilterType().trim().equalsIgnoreCase("mobile") || inputVO.getFilterType().trim().equalsIgnoreCase("email") || inputVO.getFilterType().trim().equalsIgnoreCase("endorsmentNO"))){
				conObjs=pmRepresenteeDAO.getAlConstituenciesBySearchType(inputVO,fromDateStr,toDateStr,districtIds,deptIds,pmDesignationIds,type,statIds,petitionIdsList);
			}/*else if(inputVO.getFilterType() !=null && (inputVO.getFilterType().trim().equalsIgnoreCase("representee") || inputVO.getFilterType().trim().equalsIgnoreCase("name") || inputVO.getFilterType().trim().equalsIgnoreCase("mobile") || inputVO.getFilterType().trim().equalsIgnoreCase("email") || inputVO.getFilterType().trim().equalsIgnoreCase("endorsmentNO"))){
				conObjs=pmRepresenteeDAO.getAlConstituenciesBySearchType(inputVO,fromDateStr,toDateStr,districtIds,deptIds,pmDesignationIds,type,statIds,petitionIdsList);
			}*/else if(inputVO.getFilterType() !=null && inputVO.getFilterType().trim().equalsIgnoreCase("representeeDesignation")){
				conObjs=pmRepresenteeDesignationDAO.getAllConstituenciesByRepresenteeDesignationWise(fromDateStr,toDateStr,districtIds,deptIds,pmDesignationIds,type,statIds,petitionIdsList);
			}
			if(conObjs != null && conObjs.size() >0 ){
				for(Object[] param : conObjs ){
					KeyValueVO vo = new KeyValueVO();
					vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
					String constName = commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
					vo.setValue(constName);
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getConstituenciesBySearchTypeAndDistrictId() in LocationDetailsService class ", e);
		}
		return finalList;
	}
	public List<KeyValueVO> getMandalsBySearchTypeAndConstituencyId(InputVO inputVO,String serchType,List<Long> conIds,List<Long> deptIds,String fromDate,String toDate,List<Long> desigids,String desigType,List<Long> statIds,Long userId,List<Long> subjectIdsLst,List<Long> leadIdsLst){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			List<Object[]> conObjs=null;
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()){
				startDate = format.parse(fromDate);
				endDate = format.parse(toDate);
			}
			Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null,null);
			 Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			
			if(serchType !=null && (serchType.trim().equalsIgnoreCase("work") || serchType.trim().equalsIgnoreCase("pmOfficer")|| serchType.trim().equalsIgnoreCase("subject") ||  serchType.trim().equalsIgnoreCase("lead"))){
				conObjs=pmSubWorkDetailsDAO.getAllMandalsByDistricId(conIds,deptIds,startDate,endDate,desigids,desigType,statIds,petitionIdsList,subjectIdsLst,leadIdsLst);
			}else if(serchType !=null && serchType.trim().equalsIgnoreCase("referral")){
				conObjs=pmRefCandidateDAO.getAllMandalsByReferralAndDistrict(conIds,deptIds,startDate,endDate,desigids,desigType,statIds,petitionIdsList);
			}else if(serchType !=null && serchType.trim().equalsIgnoreCase("referrelDesignation")){
				conObjs=pmRefCandidateDesignationDAO.getAllMandalsByReferalAndDesignationBydistrict(conIds,deptIds,startDate,endDate,desigids,desigType,statIds,petitionIdsList);
			}else if(inputVO.getFilterType() !=null && (inputVO.getFilterType().trim().equalsIgnoreCase("representee"))){
				conObjs=pmRepresenteeDAO.getAllMandalsBySearchType(null,conIds,deptIds,startDate,endDate,desigids,desigType,statIds,petitionIdsList);
			}else if(inputVO.getFilterType() !=null && ( inputVO.getFilterType().trim().equalsIgnoreCase("name") || inputVO.getFilterType().trim().equalsIgnoreCase("mobile") || inputVO.getFilterType().trim().equalsIgnoreCase("email") || inputVO.getFilterType().trim().equalsIgnoreCase("endorsmentNO"))){
				conObjs=pmRepresenteeDAO.getAllMandalsBySearchType(inputVO,conIds,deptIds,startDate,endDate,desigids,desigType,statIds,petitionIdsList);
			}/*else if(serchType !=null && (serchType.trim().equalsIgnoreCase("representee") || serchType.trim().equalsIgnoreCase("name") || serchType.trim().equalsIgnoreCase("mobile") || serchType.trim().equalsIgnoreCase("email") || serchType.trim().equalsIgnoreCase("endorsmentNO"))){
				conObjs=pmRepresenteeDAO.getAllMandalsBySearchType(inputVO,conIds,deptIds,startDate,endDate,desigids,desigType,statIds,petitionIdsList);
			}*/if(serchType !=null && serchType.trim().equalsIgnoreCase("representeeDesignation")){
				conObjs=pmRepresenteeDesignationDAO.getAllMandalsByRepresenteeDesignationAndconstincy(conIds,deptIds,startDate,endDate,desigids,desigType,statIds,petitionIdsList);
			}
			if(conObjs != null && conObjs.size() >0 ){
				for(Object[] param : conObjs ){
					//KeyValueVO vo = new KeyValueVO();
					KeyValueVO matchedVO =null;
					if(commonMethodsUtilService.getLongValueForObject(param[4]) ==0l){
						matchedVO = getMatchVO(finalList,commonMethodsUtilService.getLongValueForObject(param[0]));
					}else if(commonMethodsUtilService.getLongValueForObject(param[4]) >0l){
						 matchedVO = getMatchVO(finalList,commonMethodsUtilService.getLongValueForObject(param[2]));
					}
					if(matchedVO == null){
						matchedVO= new KeyValueVO();
						//matchedVO.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
						String mandalName = "";
						if(commonMethodsUtilService.getLongValueForObject(param[4]) ==6l || commonMethodsUtilService.getLongValueForObject(param[4]) ==7l){
							String id="2"+commonMethodsUtilService.getLongValueForObject(param[2]);
							matchedVO.setKey(Long.valueOf(id));
							mandalName = commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[3]));
							matchedVO.setValue(mandalName+" Corporation");
							if(commonMethodsUtilService.getLongValueForObject(param[2]) >0l)
							finalList.add(matchedVO);
						}else if(commonMethodsUtilService.getLongValueForObject(param[4]) ==5l ){
							String id="2"+commonMethodsUtilService.getLongValueForObject(param[2]);
							matchedVO.setKey(Long.valueOf(id));
							mandalName = commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[3]));
							matchedVO.setValue(mandalName+" Muncipality");
							if(commonMethodsUtilService.getLongValueForObject(param[2]) >0l)
							finalList.add(matchedVO);
						}else{
							String id="1"+commonMethodsUtilService.getLongValueForObject(param[0]);
							matchedVO.setKey(Long.valueOf(id));
							mandalName = commonMethodsUtilService.getCapitalStringValueForObject(commonMethodsUtilService.getCapitalStringValueForObject(param[1]));
							matchedVO.setValue(mandalName);
							if(commonMethodsUtilService.getLongValueForObject(param[0]) >0l)
							finalList.add(matchedVO);
						}
					}
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getConstituenciesBySearchTypeAndDistrictId() in LocationDetailsService class ", e);
		}
		return finalList;
	}
	private KeyValueVO getMatchVO(List<KeyValueVO> subList,Long id) {
		 try {
			  if (subList == null || subList.size() == 0) {
				  return null;
			  }
			  for (KeyValueVO VO : subList) {
				if (VO.getKey() == id) {
					return VO;
				}
			}
		} catch (Exception e) {
			 LOG.error("Exception raised at getMatchVO - PmRequestDetailsService service",e);
		 }
		 return null;
	}
	public List<KeyValueVO> getDesignationsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,Long desigId,List<Long> statusIds,Long userId){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			List<Object[]> desiObjs=null;
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()){
				startDate = format.parse(fromDate);
				endDate = format.parse(toDate);
			}
			Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null,null);
			 Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			
			if(searchType !=null && searchType.trim().equalsIgnoreCase("referrelDesignation")){
				desiObjs=pmRefCandidateDesignationDAO.getDesignationsByReferlDesigtion(startDate,endDate,deptIds,desigId,statusIds,petitionIdsList);
			}else if(searchType !=null && searchType.trim().equalsIgnoreCase("representeeDesignation")){
				desiObjs=pmRepresenteeDesignationDAO.getDesignationsByRepresenteeDesigtion(deptIds,startDate,endDate,desigId,statusIds,petitionIdsList);
			}
			if(desiObjs != null && desiObjs.size() > 0){
				for(Object[] param : desiObjs ){
					KeyValueVO vo = new KeyValueVO();
					vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
					//String desigName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getDesignationsBySearchType() in LocationDetailsService class ", e);

		}
		return finalList;
	}
	public List<KeyValueVO> getDepartmentsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusIds,Long userId){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			List<Object[]> deptObjs=null;
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()){
				startDate = format.parse(fromDate);
				endDate = format.parse(toDate);
			}
			
			Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null,null);
			 Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			if(searchType !=null && searchType.trim().equalsIgnoreCase("department")){
				deptObjs=pmSubWorkDetailsDAO.getDepartmentsByWorks(deptIds,startDate,endDate,statusIds,petitionIdsList);
			}
			if(deptObjs != null && deptObjs.size() > 0){
				for(Object[] param : deptObjs ){
					KeyValueVO vo = new KeyValueVO();
					vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
				   //String deptName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getDepartmentsBySearchType() in LocationDetailsService class ", e);
		}
		return finalList;
	}
	
	public List<KeyValueVO> getSubjectsBySearchType(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusIds,String subjectId,Long userId){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			List<Object[]> deptObjs=null;
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()){
				startDate = format.parse(fromDate);
				endDate = format.parse(toDate);
			}
			//Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null,null);
			// Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			if(searchType !=null && searchType.trim().equalsIgnoreCase("subject")){
				deptObjs=pmSubWorkDetailsDAO.getSubjectsForSearchPage(deptIds,startDate,endDate,statusIds,Long.valueOf(subjectId),null);
			}
			if(deptObjs != null && deptObjs.size() > 0){
				for(Object[] param : deptObjs ){
					KeyValueVO vo = new KeyValueVO();
					vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
					//String deptName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getDepartmentsBySearchType() in LocationDetailsService class ", e);
		}
		return finalList;
	}
	public List<KeyValueVO> getSubSubjectsBySubjectId(String searchType,String fromDate,String toDate,List<Long> subSubjectIdsLst,List<Long> statusIds,List<Long> deptIdsLst,List<Long> subjectIdsLst,String assretType,Long userId){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			List<Object[]> deptObjs=null;
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()){
				startDate = format.parse(fromDate);
				endDate = format.parse(toDate);
			}
			//Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null,null);
			 //Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			if(searchType !=null && searchType.trim().equalsIgnoreCase("subSubject")){
				deptObjs=pmSubWorkDetailsDAO.getSubSubjectsBySubjectId(startDate,endDate,statusIds,subSubjectIdsLst,deptIdsLst,subjectIdsLst,null);
			}
			if(deptObjs != null && deptObjs.size() > 0){
				for(Object[] param : deptObjs ){
					KeyValueVO vo = new KeyValueVO();
					vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
					//String deptName = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getDepartmentsBySearchType() in LocationDetailsService class ", e);
		}
		return finalList;
	}
	public List<KeyValueVO> getChildOfficersByParentOfficerId(String searchType,String fromDate,String toDate,List<Long> deptIds,List<Long> statusIds,List<Long> pmOfficerIds,Long userId,List<Long> pmDeptDesigIds
			,Long officerDesigId){
		List<KeyValueVO> finalList = new ArrayList<KeyValueVO>();
		try{
			List<Object[]> deptObjs=null;
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()){
				startDate = format.parse(fromDate);
				endDate = format.parse(toDate);
			}
			/*if(officerDesigId.longValue() == 2l || officerDesigId.longValue() == 23l || officerDesigId.longValue() == 86l){
				pmDeptDesigIds.clear();
			}*/
			Set<Long> pmOffcrDesigids = null;
			if(officerDesigId.longValue() != 2l && officerDesigId.longValue() != 23l && officerDesigId.longValue() != 86l){
				List<Object[]> childDeptDesignationsList = pmDepartmentDesignationHierarchyDAO.getSubDesignationDetailsForParentDeptDesignations(pmDeptDesigIds,null,"FORWARD");
				if(commonMethodsUtilService.isListOrSetValid(childDeptDesignationsList)){
					pmOffcrDesigids = new HashSet<Long>();
					for (Object[] param : childDeptDesignationsList) {
						pmOffcrDesigids.add(commonMethodsUtilService.getLongValueForObject(param[0]));
						pmOffcrDesigids.add(commonMethodsUtilService.getLongValueForObject(param[2]));
					}
				}
			}
			Map<Long,List<Long>> assignedPetitionsMap =  pmRequestDetailsService.getAssignedPetitionforPetitionDeptDesignationOfficer(userId,null,null);
			 Set<Long> petitionIdsList = assignedPetitionsMap.keySet();
			 
			if(searchType !=null && searchType.trim().equalsIgnoreCase("pmOfficer")){
				deptObjs=pmSubWorkDetailsDAO.getChildOfficersByParentOfficerId(deptIds,startDate,endDate,statusIds,pmOfficerIds,petitionIdsList,pmOffcrDesigids);
			}
		//	Map<Long,KeyValueVO> officers=new HashMap<Long,KeyValueVO>();
			if(deptObjs != null && deptObjs.size() > 0){
				for(Object[] param : deptObjs ){
					KeyValueVO vo = new KeyValueVO();
					vo.setKey(commonMethodsUtilService.getLongValueForObject(param[0]));
					String officername = commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getStringValueForObject(param[1]));
					vo.setValue(commonMethodsUtilService.getStringValueForObject(param[1]));
					finalList.add(vo);
				}
			}
		}catch(Exception e){
			LOG.error("Exception occured at getDepartmentsBySearchType() in LocationDetailsService class ", e);
		}
		return finalList;
	}
	
	public List<PmOfficerVO> getLocationWiseRepresentationsOverviewDetails(InputVO inputVO){
		List<PmOfficerVO> returnList = new ArrayList<PmOfficerVO>();
		
		try {
			Date startDate = null;
			Date endDate = null;
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			if(inputVO.getFromDate() != null && inputVO.getToDate() != null && !inputVO.getFromDate().isEmpty() && !inputVO.getToDate().isEmpty()){
				startDate = format.parse(inputVO.getFromDate());
				endDate = format.parse(inputVO.getToDate());
			}
			Map<Long,PmOfficerVO> locationMap = new HashMap<Long,PmOfficerVO>();
			List<Object[]> objList = pmSubWorkDetailsDAO.getLocationWiseRepresentationsOverviewDetails(inputVO, startDate, endDate);
			
			if(commonMethodsUtilService.isListOrSetValid(objList)){
				for (Object[] param : objList) {
					PmOfficerVO locationVO =locationMap.get(commonMethodsUtilService.getLongValueForObject(param[3]));
					if(locationVO == null){
						locationVO = new PmOfficerVO();
						locationVO.setLocationId(commonMethodsUtilService.getLongValueForObject(param[3]));
						locationVO.setLocationName(commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getCapitalStringValueForObject(param[4])));
						if(inputVO.getAssetType() != null && inputVO.getAssetType().equalsIgnoreCase("constituency")){
							locationVO.setId(commonMethodsUtilService.getLongValueForObject(param[5]));
							locationVO.setName(commonMethodsUtilService.toConvertStringToTitleCase(commonMethodsUtilService.getCapitalStringValueForObject(param[6])));
						}
						locationMap.put(commonMethodsUtilService.getLongValueForObject(param[3]), locationVO);
					}
					
					if(locationVO.getPetitionIds() == null){
						locationVO.setPetitionIds(new HashSet<Long>());
					}
					locationVO.getPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(locationVO.getWithOutCostPetitionIds() == null){
						locationVO.setWithOutCostPetitionIds(new HashSet<Long>());
					}
					if(locationVO.getSubWorkIds() == null){
						locationVO.setSubWorkIds(new HashSet<Long>());
					}
					if(locationVO.getWithOutCostSubWorkIds() == null){
						locationVO.setWithOutCostSubWorkIds(new HashSet<Long>());
					}
					String estimationCost = commonMethodsUtilService.getStringValueForObject(param[2]);
					if(!locationVO.getSubWorkIds().contains(commonMethodsUtilService.getLongValueForObject(param[1]))
							&& !estimationCost.equalsIgnoreCase("") && !estimationCost.equalsIgnoreCase("0")){
						locationVO.getSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(param[1]));
						BigDecimal decmial2= new BigDecimal(locationVO.getEstimationCost());
						BigDecimal decmial1= new BigDecimal(estimationCost);
						BigDecimal totalCost1 = decmial2.add(decmial1);
						locationVO.setEstimationCost(totalCost1.toString());
					}else if(!locationVO.getWithOutCostSubWorkIds().contains(commonMethodsUtilService.getLongValueForObject(param[1]))
							&& (estimationCost.equalsIgnoreCase("") || estimationCost.equalsIgnoreCase("0"))){
						locationVO.getWithOutCostSubWorkIds().add(commonMethodsUtilService.getLongValueForObject(param[1]));
						locationVO.getWithOutCostPetitionIds().add(commonMethodsUtilService.getLongValueForObject(param[0]));
					}
					
				}
			}
			
			if(commonMethodsUtilService.isMapValid(locationMap)){
				returnList.addAll(locationMap.values());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnList;
	}
}
