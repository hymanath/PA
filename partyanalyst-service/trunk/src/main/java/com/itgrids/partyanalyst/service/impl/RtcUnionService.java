package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyWardDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IRtcDepotDAO;
import com.itgrids.partyanalyst.dao.IRtcRegionDAO;
import com.itgrids.partyanalyst.dao.IRtcZoneDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.IUnionTypeDesignationDAO;
import com.itgrids.partyanalyst.dao.IUserAddressDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.IdNameVO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.RtcUnionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.RtcZone;
import com.itgrids.partyanalyst.model.UserAddress;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IRtcUnionService;
import com.itgrids.partyanalyst.utils.DateUtilService;

public class RtcUnionService implements IRtcUnionService{

	 private static final Logger LOG = Logger.getLogger(RtcUnionService.class);
	 private IDistrictDAO districtDAO;
	 private IConstituencyDAO constituencyDAO;
	 private IPanchayatDAO  panchayatDAO;
	 private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	 private IAssemblyLocalElectionBodyWardDAO assemblyLocalElectionBodyWardDAO;
	 private IRegionServiceData regionServiceDataImp;
	 private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	 private IUserVoterDetailsDAO userVoterDetailsDAO;
	 private ITdpCadreDAO tdpCadreDAO;
	 private IUserAddressDAO userAddressDAO;
	 
	 
	 

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
    
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
    
	
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
    
	public void setUserAddressDAO(IUserAddressDAO userAddressDAO) {
		this.userAddressDAO = userAddressDAO;
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
	        	//if(!(localBdyId.longValue() == 20l ||  localBdyId.longValue() == 124l || localBdyId.longValue() == 119l)){
	        		vo = new LocationWiseBoothDetailsVO();
		        	vo.setLocationId(Long.valueOf("5"+localBodi[0].toString()));
		        	vo.setLocationName(localBodi[1].toString() +" "+ localBodi[2].toString());
		        	locationsList.add(vo);
	        	/*}else{
	        		if(!greaterCorpIds.contains(localBdyId)){
	        			greaterCorpIds.add(localBdyId);
	        		}
	        	}*/
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
	
	public VoterAddressVO getVoterAddressDetails(Long candidateId,String searchType){
		VoterAddressVO addressVO=null;
		 try{
			
			 if(searchType.equalsIgnoreCase("voter")){
				 
				 addressVO=getVoterAddressDetailsByVoterId(candidateId);
				 
			 }else  if(searchType.equalsIgnoreCase("cadre")){
				 addressVO=getVoterAddressDetailsByCadreId(candidateId);
			 }
			 
			 if(addressVO!=null){
				 
				 if(addressVO.getDistrictId()!=null){
					 addressVO.setConstList(getConstituenciesForDistrict(addressVO.getDistrictId()));
				 }
				 if(addressVO.getConstituencyId()!=null){
					 addressVO.setTehLebDivList(getLocationsOfSublevelConstituencyMandal(addressVO.getConstituencyId(),null,4l));
				 }
				 if(addressVO.getTehsilId()!=null){
					 addressVO.setVillWardList(getLocationsOfSublevelConstituencyMandal(null,addressVO.getTehsilId().toString(),5l));
				 }
				 if(addressVO.getLocalElectionBodyId()!=null){
					 addressVO.setVillWardList(getLocationsOfSublevelConstituencyMandal(null,addressVO.getLocalElectionBodyId().toString(),5l));
				 }
			 }
		} catch(Exception e){
			LOG.error("Exception riased at voterAddressDetails", e);
		}
		 return addressVO;
	}
	
	public VoterAddressVO getVoterAddressDetailsByCadreId(Long cadreId){
		
		VoterAddressVO addressVO=new VoterAddressVO();
		
		try{
			Long userAddressid=tdpCadreDAO.getUserAddressId(cadreId);
			List<UserAddress> userAddressList=userAddressDAO.getUserAddressByUserAddressId(userAddressid);
			
			if(userAddressList!=null && userAddressList.size()>0){
				
				UserAddress address=userAddressList.get(0);
				
				if(address!=null){
					
					if(address.getDistrict()!=null){
						addressVO.setDistrictId(address.getDistrict().getDistrictId()!=null?address.getDistrict().getDistrictId():0l);
					}
					
					if(address.getConstituency()!=null){
						addressVO.setConstituencyId(address.getConstituency().getConstituencyId()!=null?address.getConstituency().getConstituencyId():0l);
					}
					
					if(address.getTehsil()!=null){
						addressVO.setTehsilId(address.getTehsil().getTehsilId()!=null?Long.valueOf("4"+address.getTehsil().getTehsilId().toString()):0l);
					}
					
					if(address.getLocalElectionBody()!=null){
						addressVO.setLocalElectionBodyId(address.getLocalElectionBody().getLocalElectionBodyId()!=null?Long.valueOf("5"+address.getLocalElectionBody().getLocalElectionBodyId().toString()):0l);
					}
					
					if(address.getPanchayat()!=null){
						addressVO.setVillageId(address.getPanchayat().getPanchayatId()!=null?Long.valueOf("7"+address.getPanchayat().getPanchayatId().toString()):0l);
					}
					if(address.getWard()!=null){
						addressVO.setWardId(address.getWard().getConstituencyId()!=null?Long.valueOf("8"+address.getWard().getConstituencyId().toString()):0l);
					}
				}
			}
			
		}catch(Exception e){
			LOG.error("Exception riased at getVoterAddressDetailsByCadreId", e);
		}
		return addressVO;
	}
	
	public VoterAddressVO getVoterAddressDetailsByVoterId(Long voterId)
	{
		VoterAddressVO addressVO=new VoterAddressVO();
		try {
			
			List<Booth> locationDetails = boothPublicationVoterDAO.getVoterAddressDetails(voterId);
			if(locationDetails != null && locationDetails.size() > 0)
			{
				Booth booth = locationDetails.get(0);
				if(booth != null)
				{
					
					if(booth.getConstituency() != null){
						addressVO.setConstituencyId(booth.getConstituency().getConstituencyId()!=null?booth.getConstituency().getConstituencyId():0l);
					}
					
					if(booth.getConstituency() != null && booth.getConstituency().getDistrict() != null){
						addressVO.setDistrictId(booth.getConstituency().getDistrict().getDistrictId()!=null?booth.getConstituency().getDistrict().getDistrictId():0l);
					}
					
					if(booth.getTehsil() != null){
						addressVO.setTehsilId(booth.getTehsil().getTehsilId()!=null?Long.valueOf("4"+booth.getTehsil().getTehsilId().toString()):0l);
					}
					
					if(booth.getLocalBody() != null){
						addressVO.setLocalElectionBodyId(booth.getLocalBody().getLocalElectionBodyId()!=null?Long.valueOf("5"+booth.getLocalBody().getLocalElectionBodyId().toString()):0l);
					}
					
					if(booth.getPanchayat() != null){
						addressVO.setVillageId(booth.getPanchayat().getPanchayatId()!=null?Long.valueOf("7"+booth.getPanchayat().getPanchayatId().toString()):0l);
					}
					
					Long wardId=null;
					if(booth.getLocalBodyWard() != null){
						addressVO.setWardId(booth.getLocalBodyWard().getConstituencyId()!=null?Long.valueOf("8"+booth.getLocalBodyWard().getConstituencyId().toString()):0l);
					}
					else{
						List<Constituency> wardsList =  userVoterDetailsDAO.getWardByVoterId(voterId);
						if(wardsList != null && wardsList.size() > 0){
						   if(wardsList.get(0)!=null){
							   addressVO.setWardId(wardsList.get(0).getConstituencyId()!=null?Long.valueOf("8"+wardsList.get(0).getConstituencyId().toString()):0l);
						   }
						}
					}
					
				}
			}
		} catch (Exception e) {
			LOG.error("Exception raised in getVoterAddressDetails in getVoterAddressDetails service", e);
		}
		return addressVO;
	}
	
	public RtcUnionVO getRtcUnionBasicDetails(){
		RtcUnionVO fnlVo = new RtcUnionVO();
		
		try{
			
			DateUtilService date = new DateUtilService();
						
			List<Object[]> overAllDetails = tdpCadreDAO.getAffliatedCadreCountDetails(null, null);
			
			List<Object[]> todayDetails =  tdpCadreDAO.getAffliatedCadreCountDetails("toDay", date.getCurrentDateAndTime());
			
			if(overAllDetails !=null && overAllDetails.size()>0){				
				Long totalAffliated = 0l;
				
				for (Object[] objects : todayDetails) {	
					
					if(objects[0] !=null && objects[0].toString().equalsIgnoreCase("WEB")){
						fnlVo.setWebCount(objects[1] !=null ? (Long)objects[1]:0l);
					}else if(objects[0] !=null && objects[0].toString().equalsIgnoreCase("TAB")){
						fnlVo.setTabCount(objects[1] !=null ? (Long)objects[1]:0l);
					}else if(objects[0] !=null && objects[0].toString().equalsIgnoreCase("ONLINE")){
						fnlVo.setOnlineCount(objects[1] !=null ? (Long)objects[1]:0l);
					}					
					totalAffliated = totalAffliated +Long.parseLong(objects[1] !=null ? objects[1].toString():"") ;
				}
				
				fnlVo.setTotalCount(totalAffliated);				
			}
			
			if(todayDetails !=null && todayDetails.size()>0){				
				Long totalAffliated = 0l;
				
				for (Object[] objects : todayDetails) {	
					
					if(objects[0] !=null && objects[0].toString().equalsIgnoreCase("WEB")){
						fnlVo.setTodayWebCount(objects[1] !=null ? (Long)objects[1]:0l);
					}else if(objects[0] !=null && objects[0].toString().equalsIgnoreCase("TAB")){
						fnlVo.setTodayTabCount(objects[1] !=null ? (Long)objects[1]:0l);
					}else if(objects[0] !=null && objects[0].toString().equalsIgnoreCase("ONLINE")){
						fnlVo.setTodayOnlineCount(objects[1] !=null ? (Long)objects[1]:0l);
					}					
					totalAffliated = totalAffliated +Long.parseLong(objects[1] !=null ? objects[1].toString():"");
				}
				
				fnlVo.setTodayTotalCount(totalAffliated);				
			}
			
		}catch (Exception e) {
			LOG.error("Exception riased at getRtcUnionBasicDetails in RtcUnionService Service class", e);
		}
		return fnlVo;
	}
	
	public RtcUnionVO getRtcUnionZoneWiseDetails(){
		
		RtcUnionVO fnlVo = new RtcUnionVO();
		
		try{			
			DateUtilService date = new DateUtilService();
			
			List<Object[]> rtcZoneObjectList = rtcZoneDAO.getRtcZoneDetails();						
			List<RtcUnionVO> rtcZoneList = new ArrayList<RtcUnionVO>(0);	
			
			//Default Details Assigning
			if(rtcZoneObjectList !=null && rtcZoneObjectList.size()>0){
				settingDefaultValuesToUnionVo(rtcZoneObjectList,rtcZoneList);
			}
			List<Object[]> overallList = tdpCadreDAO.getRtcUnionZoneWiseDetails(null, null);
			
			List<Object[]> todaylList = tdpCadreDAO.getRtcUnionZoneWiseDetails("today", date.getCurrentDateAndTime());
			
			if(overallList !=null && overallList.size()>0){
				settingZoneDetailsIntoList(overallList,rtcZoneList,null);				
			}
			if(todaylList !=null && todaylList.size()>0){
				settingZoneDetailsIntoList(todaylList,rtcZoneList,"today");
			}
			
			if(rtcZoneList !=null && rtcZoneList.size()>0){
				fnlVo.setRtcUnionVoList1(rtcZoneList);
			}
			
		}catch (Exception e) {
			LOG.error("Exception riased at getRtcUnionZoneWiseDetails in RtcUnionService Service class", e);
		}
		return fnlVo;
	}
	
	
	public void settingZoneDetailsIntoList(List<Object[]> overallList,List<RtcUnionVO> rtcZoneList,String serachType){
		try{
			
			if(overallList !=null){
					
			for(RtcUnionVO rtc : rtcZoneList){
				
					//zone wise total counts
					Long totalCount=0l;
					Long toDayTotalCount=0l;
					
					for(Object[] objectList : overallList){					
						Long zoneId = objectList[0] !=null ? (Long)objectList[0]:0l;
						
						if(rtc.getId() == zoneId){							
							if(objectList[2] !=null && objectList[2].toString().equalsIgnoreCase("WEB")){
								
								if(serachType !=null && serachType.equalsIgnoreCase("today")){
									rtc.setTodayWebCount(objectList[3] !=null ? (Long)objectList[3]:0l);
								}else{
									rtc.setWebCount(objectList[3] !=null ? (Long)objectList[3]:0l);
								}
								
							}else if(objectList[2] !=null && objectList[2].toString().equalsIgnoreCase("TAB")){
								
								if(serachType !=null && serachType.equalsIgnoreCase("today")){
									rtc.setTodayTabCount(objectList[3] !=null ? (Long)objectList[3]:0l);
								}else{
									rtc.setTabCount(objectList[3] !=null ? (Long)objectList[3]:0l);
								}
								
							}else if(objectList[2] !=null && objectList[2].toString().equalsIgnoreCase("ONLINE")){								
								if(serachType !=null && serachType.equalsIgnoreCase("today")){
									rtc.setTodayOnlineCount(objectList[3] !=null ? (Long)objectList[3]:0l);
								}else{
									rtc.setOnlineCount(objectList[3] !=null ? (Long)objectList[3]:0l);
								}								
							}
							
							if(serachType !=null && serachType.equalsIgnoreCase("today")){					
								toDayTotalCount = toDayTotalCount + Long.parseLong(objectList[3] !=null ? objectList[3].toString():"");						
							}
							else{
								totalCount = totalCount + Long.parseLong(objectList[3] !=null ? objectList[3].toString():"");
							}
							
						}
						
					}					
					rtc.setTodayTotalCount(toDayTotalCount);
					rtc.setTotalCount(totalCount);					
				}
				
			}
			
			
		}catch (Exception e) {
			LOG.error("Exception riased at settingZoneDetailsIntoList in RtcUnionService Service class", e);
		}
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
					 vo.setOnlineCount(0l);
					 
					 vo.setTodayTotalCount(0l);
					 vo.setTodayTabCount(0l);
					 vo.setTodayWebCount(0l);
					 vo.setTodayOnlineCount(0l);
					 
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
			DateUtilService dateUtil = new DateUtilService();
			
			List<RtcUnionVO> resultList = new ArrayList<RtcUnionVO>(0);			
			List<Object[]> regionDaoDetails =  rtcRegionDAO.getAllRegionsWithZone();
			
			//Default Details Assigning
			if(regionDaoDetails != null && regionDaoDetails.size() > 0){				
				settingDefaultValuesToAllLocationDetails(regionDaoDetails,resultList);					
			}					
			List<Object[]> overAllResult = tdpCadreDAO.getRtcUnionAllLocationDetails(null,null);			
			List<Object[]> toDayResult = tdpCadreDAO.getRtcUnionAllLocationDetails("toDay",dateUtil.getCurrentDateAndTime());
			
			if(overAllResult !=null && overAllResult.size()>0){
				settingZoneDetailsIntoList(overAllResult,resultList,null);
			}			
			if(toDayResult !=null && toDayResult.size()>0){
				settingZoneDetailsIntoList(toDayResult,resultList,"today");
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
					 vo.setName(rtcZone[1] !=null ? rtcZone[1].toString():"");		
					 
					 vo.setZoneId(rtcZone[2] !=null ? (Long)rtcZone[2]:0l);
					 vo.setZoneName(rtcZone[3] !=null ? rtcZone[3].toString():"");
					 
					 vo.setTotalCount(0l);
					 vo.setWebCount(0l);
					 vo.setTabCount(0l);
					 vo.setOnlineCount(0l);
					 
					 vo.setTodayTotalCount(0l);
					 vo.setTodayTabCount(0l);
					 vo.setTodayWebCount(0l);
					 vo.setTodayOnlineCount(0l);
					 
					 resultList.add(vo);
				}
			 }
		}catch (Exception e) {
			LOG.error("Exception riased at settingDefaultValuesToRegionsList in RtcUnionService Service class", e);
		}
		
	}
	
}
