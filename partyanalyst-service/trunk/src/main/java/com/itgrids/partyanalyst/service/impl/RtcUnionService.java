package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRtcDepotDAO;
import com.itgrids.partyanalyst.dao.IRtcRegionDAO;
import com.itgrids.partyanalyst.dao.IRtcZoneDAO;
import com.itgrids.partyanalyst.dao.IUnionTypeDesignationDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.RtcUnionVO;
import com.itgrids.partyanalyst.model.RtcZone;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IRtcUnionService;

public class RtcUnionService implements IRtcUnionService{

	 private static final Logger LOG = Logger.getLogger(RtcUnionService.class);
	 private IDistrictDAO districtDAO;
	 private IConstituencyDAO constituencyDAO;
	 private IPanchayatDAO  panchayatDAO;
	 private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	 private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	 private IRegionServiceData regionServiceDataImp;
	 
	 public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	 }
     
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	
	
	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyWardDAO(
			IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO) {
		this.assemblyLocalElectionBodyWardDAO = assemblyLocalElectionBodyWardDAO;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public List<IdNameVO> getDistrictsForState(Long stateId){
		 List<IdNameVO> idNameVOList=null;
		 try{
			 List<Object[]> rslt=districtDAO.getDistrictsForState(stateId);
			 idNameVOList=setDataToVO(rslt,idNameVOList);
		 }catch(Exception e){
			 LOG.error("Exception Occured in getDistrictsForState(), Exception",e);
		}
		 return idNameVOList;
	 }
	
	public List<IdNameVO> getConstituenciesForDistrict(Long districtId){
		 List<IdNameVO> idNameVOList=null;
		 try{
			 List<Object[]> rslt=constituencyDAO.getConstituenciesByDistId(districtId);
			 idNameVOList=setDataToVO(rslt,idNameVOList);
		 }catch(Exception e){
			 LOG.error("Exception Occured in getConstituenciesForDistrict(), Exception",e);
		}
		 return idNameVOList;
	 }
	
     public List<LocationWiseBoothDetailsVO> getLocationsOfSublevelConstituencyMandal(Long constituencyId, String mandalStr, Long locationLevelId){
		
		List<Long> constiIds = new ArrayList<Long>();
		List<Long> mandalIds = new ArrayList<Long>();
		List<Long> localBodyIds = new ArrayList<Long>();
		
		if(locationLevelId.equals(4l)){
			constiIds.add(constituencyId);
		}else if(locationLevelId.equals(5l)){
			if(!mandalStr.equalsIgnoreCase("0")){
				String firstLetter = mandalStr.substring(0, 1);
				if(firstLetter.equalsIgnoreCase("4")){
					Long mandalId = Long.valueOf(mandalStr.substring(1));
					mandalIds.add(mandalId);
				}else if(firstLetter.equalsIgnoreCase("5")){
					Long localBody = Long.valueOf(mandalStr.substring(1));
					localBodyIds.add(localBody);
				}
				
			}
		}
		
		try{
			if(locationLevelId.equals(4l)){
				return getMandalMunicCorpDetailsOfConstituencies(constiIds);
			}else{
				return getPanchayatWardDivisionDetailsOfSubLocation(mandalIds, localBodyIds);
			}
		}catch(Exception e){
			LOG.error("Exception raised in getLocationsList", e);
			return new ArrayList<LocationWiseBoothDetailsVO>(); 
		}
	}
	
	
	// TO GET MANDAL/LOCAL BODY/ DIVISION DETAILS OF CONSTITUENCIES
	public List<LocationWiseBoothDetailsVO> getMandalMunicCorpDetailsOfConstituencies(List<Long> constituencyIds){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		List<Long> greaterCorpIds = new ArrayList<Long>();
		List<SelectOptionVO> locations = regionServiceDataImp.getAllMandalsByAllConstituencies(constituencyIds);
		List<Object[]> localBodies = assemblyLocalElectionBodyDAO.getAllLocalBodiesInAConstituencyList(constituencyIds);
	        for(SelectOptionVO location:locations){
	        	vo = new LocationWiseBoothDetailsVO();
	        	vo.setLocationId(Long.valueOf("4"+location.getId()));
	        	vo.setLocationName(location.getName()+" Mandal");
	        	locationsList.add(vo);
	        }
	        for(Object[] localBodi:localBodies){
	        	Long localBdyId = (Long)localBodi[0];
	        	if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("5"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString() +" "+ localBodi[2].toString());
		        	locationsList.add(vo);
	        	}else{
	        		if(!greaterCorpIds.contains(localBdyId)){
	        			greaterCorpIds.add(localBdyId);
	        		}
	        	}
	        }
	        if(greaterCorpIds.size() > 0){
        	  for(Long id:greaterCorpIds){
        		  List<Object[]>  wards = assemblyLocalElectionBodyWardDAO.findWardsByLocalBodyConstiIds(greaterCorpIds, constituencyIds);
        		  for(Object[] location:wards){
        			  vo = new LocationWiseBoothDetailsVO();
  		        	vo.setLocationId(Long.valueOf("6"+location[0].toString()));
  		        	vo.setLocationName(location[1].toString());
  		        	locationsList.add(vo);
        		  }
        	  }
	        }
	        return locationsList;
	}
	
	
	// TO GET VILLAGE / WARD DETAILS OF MANDAL/LOCAL BODY/DIVISION
	public List<LocationWiseBoothDetailsVO> getPanchayatWardDivisionDetailsOfSubLocation(List<Long> mandals, List<Long> localBodys){
		List<LocationWiseBoothDetailsVO> locationsList = new ArrayList<LocationWiseBoothDetailsVO>();
		LocationWiseBoothDetailsVO vo = null;
		
	        if(mandals.size() > 0){
	        	List<Object[]> panchayatsList = panchayatDAO.getAllPanchayatsInMandals(mandals);
	        	for(Object[] panchayat:panchayatsList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("7"+(Long)panchayat[0]));
		        	vo.setLocationName(panchayat[1].toString()+"("+panchayat[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        if(localBodys.size() > 0){
	        	List<Object[]> localBodyList = constituencyDAO.getWardsInLocalElectionBody(localBodys);
	        	for(Object[] localBody:localBodyList){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("8"+(Long)localBody[0]));
		        	vo.setLocationName(localBody[1].toString()+"("+localBody[2].toString()+")");
		        	locationsList.add(vo);
	        	}
	        }
	        return locationsList;
	}
	
	public List<IdNameVO> setDataToVO(List<Object[]> rslt, List<IdNameVO> idNameVOList){
		
		if(rslt!=null && rslt.size()>0){
			idNameVOList=new ArrayList<IdNameVO>();
			for(Object[] obj:rslt){
				IdNameVO vo=new IdNameVO();
				vo.setId(obj[0]!=null?(Long)obj[0]:0l);
				vo.setName(obj[1]!=null?obj[1].toString():"");
				idNameVOList.add(vo);
			}
		}
		return idNameVOList;
	}
	 private IRtcZoneDAO rtcZoneDAO;
	 private IRtcRegionDAO rtcRegionDAO;
	 private IRtcDepotDAO rtcDepotDAO;
	 private IUnionTypeDesignationDAO unionTypeDesignationDAO;
	 
	 
	 public IRtcZoneDAO getRtcZoneDAO() {
		return rtcZoneDAO;
	}
	public void setRtcZoneDAO(IRtcZoneDAO rtcZoneDAO) {
		this.rtcZoneDAO = rtcZoneDAO;
	}
	public IRtcRegionDAO getRtcRegionDAO() {
		return rtcRegionDAO;
	}
	public void setRtcRegionDAO(IRtcRegionDAO rtcRegionDAO) {
		this.rtcRegionDAO = rtcRegionDAO;
	}
	public IRtcDepotDAO getRtcDepotDAO() {
		return rtcDepotDAO;
	}
	public void setRtcDepotDAO(IRtcDepotDAO rtcDepotDAO) {
		this.rtcDepotDAO = rtcDepotDAO;
	}
	public IUnionTypeDesignationDAO getUnionTypeDesignationDAO() {
		return unionTypeDesignationDAO;
	}
	public void setUnionTypeDesignationDAO(
			IUnionTypeDesignationDAO unionTypeDesignationDAO) {
		this.unionTypeDesignationDAO = unionTypeDesignationDAO;
	}
	
	public List<IdNameVO> getAllRTCZones(){
		List<IdNameVO> fianaVoList = new ArrayList<IdNameVO>(0);
		 try {
			 List<RtcZone> rtcZoneList = rtcZoneDAO.getAll();
			 
			 if(rtcZoneList != null && rtcZoneList.size() > 0){
				 for (RtcZone rtcZone : rtcZoneList) {
					 IdNameVO vo = new IdNameVO();
					 vo.setId(rtcZone.getRtcZoneId());
					 vo.setName(rtcZone.getZoneName());
					 fianaVoList.add(vo);
				}
			 }
			 
		} catch (Exception e) {
			LOG.error("Exception raised at getAllRTCZones", e);
		}
		 
		 return fianaVoList;
	 }
	
	public List<IdNameVO> getRegionsOfZone(Long zoneId){
		List<IdNameVO> finalVoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> rtcRegionObjList = rtcRegionDAO.getRegionsOfZone(zoneId);
			
			if(rtcRegionObjList != null && rtcRegionObjList.size() > 0){
				for (Object[] objects : rtcRegionObjList) {
					IdNameVO vo =new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finalVoList.add(vo);
				}
			}
			
		} catch (Exception e) {
			LOG.error("Exception raised at getRegionsOfZone", e);
		}
		return finalVoList;
	}
	
	public List<IdNameVO> getDepotsOfRegion(Long regionId){
		List<IdNameVO> finaleVoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> rtcDepotsObjList = rtcDepotDAO.getDepotsOfRegion(regionId);
			
			if(rtcDepotsObjList != null && rtcDepotsObjList.size() > 0){
				for (Object[] objects : rtcDepotsObjList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finaleVoList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exceptionr riased at getDepotsOfRegion", e);
		}
		return finaleVoList;
	}
	
	public List<IdNameVO> getDesignationsOfUnionType(Long uniontypeId){
		List<IdNameVO> finalevoList = new ArrayList<IdNameVO>(0);
		try {
			List<Object[]> unionTypeDesignationObjList = unionTypeDesignationDAO.getDesignationsOfUnionType(uniontypeId);
			if(unionTypeDesignationObjList != null && unionTypeDesignationObjList.size() > 0){
				for (Object[] objects : unionTypeDesignationObjList) {
					IdNameVO vo = new IdNameVO();
					vo.setId(objects[0]!=null?(Long)objects[0]:0l);
					vo.setName(objects[1]!=null?objects[1].toString():"");
					finalevoList.add(vo);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception riased at getDesignationsOfUnionType", e);
		}
		return finalevoList;
	}
	
	public RtcUnionVO getRtcUnionBasicDetails(){
		RtcUnionVO fnlVo = new RtcUnionVO();
		
		try{
			
			fnlVo.setTotalCount(0l);
			fnlVo.setTabCount(0l);
			fnlVo.setWebCount(0l);
			fnlVo.setTodayTotalcCount(0l);
			fnlVo.setTodayTabCount(0l);
			fnlVo.setTodayWebCount(0l);
			
		}catch (Exception e) {
			LOG.error("Exception riased at getRtcUnionBasicDetails in RtcUnionService Service class", e);
		}
		return fnlVo;
	}
	
	public RtcUnionVO getRtcUnionZoneWiseDetails(){
		
		RtcUnionVO fnlVo = new RtcUnionVO();
		
		try{
			
			List<Object[]> rtcZoneObjectList = rtcZoneDAO.getRtcZoneDetails();						
			List<RtcUnionVO> rtcZoneList = new ArrayList<RtcUnionVO>(0);	
			
			//Default Details Assigning
			if(rtcZoneObjectList !=null && rtcZoneObjectList.size()>0){
				settingDefaultValuesToUnionVo(rtcZoneObjectList,rtcZoneList);
			}
			
			if(rtcZoneList !=null && rtcZoneList.size()>0){
				fnlVo.setRtcUnionVoList1(rtcZoneList);
			}
			
		}catch (Exception e) {
			LOG.error("Exception riased at getRtcUnionZoneWiseDetails in RtcUnionService Service class", e);
		}
		return fnlVo;
	}
	
	public void settingDefaultValuesToUnionVo(List<Object[]> objectList,List<RtcUnionVO> resultList){
		try{			
			if(objectList != null && objectList.size() > 0){
				 for (Object[] rtcZone : objectList) {					 
					 RtcUnionVO vo = new RtcUnionVO();					 
					 vo.setId(rtcZone[0] !=null ? (Long)rtcZone[0]:0l);
					 vo.setName(rtcZone[1] !=null ? rtcZone[1].toString():"");		
					 
					 vo.setTotalCount(0l);
					 vo.setTabCount(0l);
					 vo.setWebCount(0l);
					 
					 vo.setTodayTotalcCount(0l);
					 vo.setTodayTabCount(0l);
					 vo.setTodayWebCount(0l);
					 
					 resultList.add(vo);
				}
			 }
			
			
		}catch (Exception e) {
			LOG.error("Exception riased at settingDefaultValuesToUnionVo in RtcUnionService Service class", e);
		}
	}
	
	public RtcUnionVO getRtcUnionLocationWiseDetails(String type,Long typeId){
		
		RtcUnionVO fnlVo = new RtcUnionVO();
		
		try{						
			List<Object[]> rtcDaoList =null;
			List<RtcUnionVO> resultList = new ArrayList<RtcUnionVO>(0);
			
			if(type !=null && type.equalsIgnoreCase("region")){
				rtcDaoList = rtcRegionDAO.getRegionsOfZone(typeId);
			}else if(type !=null && type.equalsIgnoreCase("depot")){
				rtcDaoList = rtcRegionDAO.getRegionsOfZone(typeId);
			}
			
			//Default Details Assigning
			if(rtcDaoList != null && rtcDaoList.size() > 0){				
				settingDefaultValuesToUnionVo(rtcDaoList,resultList);					
			}
			
			if(resultList !=null && resultList.size()>0){
				fnlVo.setRtcUnionVoList1(resultList);
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception riased at getRtcUnionLocationWiseDetails in RtcUnionService Service class", e);
		}
		return fnlVo;
	}
	public RtcUnionVO getRtcUnionAllLocationDetails(){
		RtcUnionVO fnlVo = new RtcUnionVO();
		
		try{						
			List<RtcUnionVO> resultList = new ArrayList<RtcUnionVO>(0);
			
			List<Object[]> regionDaoDetails =  rtcRegionDAO.getAllRegionsWithZone();
			
			//Default Details Assigning
			if(regionDaoDetails != null && regionDaoDetails.size() > 0){				
				settingDefaultValuesToAllLocationDetails(regionDaoDetails,resultList);					
			}
			
			if(resultList !=null && resultList.size()>0){
				fnlVo.setRtcUnionVoList1(resultList);
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception riased at getRtcUnionAllLocationDetails in RtcUnionService Service class", e);
		}
		return fnlVo;
	}
	public void settingDefaultValuesToAllLocationDetails(List<Object[]> objectList,List<RtcUnionVO> resultList){
	
		try{
			
			if(objectList != null && objectList.size() > 0){
				 for (Object[] rtcZone : objectList) {					 
					 RtcUnionVO vo = new RtcUnionVO();
					 
					 vo.setId(rtcZone[0] !=null ? (Long)rtcZone[0]:0l);
					 vo.setName(rtcZone[1] !=null ? rtcZone[0].toString():"");		
					 
					 vo.setZoneId(rtcZone[2] !=null ? (Long)rtcZone[2]:0l);
					 vo.setZoneName(rtcZone[3] !=null ? rtcZone[3].toString():"");
					 
					 vo.setTotalCount(0l);
					 vo.setWebCount(0l);
					 vo.setTabCount(0l);
					 
					 vo.setTodayTotalcCount(0l);
					 vo.setTodayTabCount(0l);
					 vo.setTodayWebCount(0l);
					 
					 resultList.add(vo);
				}
			 }
			
			
		}catch (Exception e) {
			LOG.error("Exception riased at settingDefaultValuesToRegionsList in RtcUnionService Service class", e);
		}
		
	}
	
}
