package com.itgrids.partyanalyst.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.LocationInputVO;
import com.itgrids.partyanalyst.model.UserAddress;

public class LocationService {
	private IConstituencyDAO constituencyDAO;
	private IDistrictDAO districtDAO;
	private ITehsilDAO tehsilDAO;
	private IPublicRepresentativeDAO publicRepresentativeDAO;
	private static Logger LOG = Logger.getLogger(LocationService.class);
	
	
	
	
	 public IPublicRepresentativeDAO getPublicRepresentativeDAO() {
		return publicRepresentativeDAO;
	}
	public void setPublicRepresentativeDAO(
			IPublicRepresentativeDAO publicRepresentativeDAO) {
		this.publicRepresentativeDAO = publicRepresentativeDAO;
	}
	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}
	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}
	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}
	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}
	public LocationInputVO getCandidateLocationDetails(LocationInputVO mainVo){
			
		 LocationInputVO locationVO = new LocationInputVO();
			
			try{
				List<Long> contryIds = mainVo.getCounrtyIds();
				List<Long> stateIds=mainVo.getLocalStateIds();//inputs from UI
				List<Long> districtIds=mainVo.getLocalDistrictIds();//inputs from UI
				List<Long> constituencyIds=mainVo.getLocalConstituencyIds();//inputs from UI	
				List<Long> mandalIdsList=mainVo.getLocalMandalIds();////inputs from UI	
				List<Long> panchayatIdsList=mainVo.getLocalPanchayatIds();////inputs from UI	
				
				
				
				List<Long> levelValues =new ArrayList<Long>(0);
				List<Long> levelValues1 =new ArrayList<Long>(0);//town 
				List<Long> levelValues2 =new ArrayList<Long>(0);//division
				
				Long levelId = mainVo.getLevelId();
				String levelStr = mainVo.getLevelStr();
				
				if(levelId !=null && levelId>0){	
					if(levelId ==10l){//state
						
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("state") ){					
							if(districtIds == null || districtIds.size()<=0){							
								levelValues.addAll(stateIds);					
							}
							//Setting to VO
							if(mainVo.getStateId() == 0)
							{
							levelValues.add(1l);levelValues.add(36l);
							}
							else
							levelValues.add(mainVo.getStateId());
							locationVO.setStateIdsList(levelValues);//stateIds List						
							locationVO.setLevelId(levelId);
							
						}/*else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("district")){							
							List<Long> states = districtDAO.getStateIdsByDistrictIds(districtIds);		
							if(states !=null){
								levelValues.addAll(states);		
							}
												
						}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("Constituency")){
							
							List<Long> states = constituencyDAO.getStateIdsByConstituency(constituencyIds);
							if(states !=null){
								levelValues.addAll(states);		
							}
							
						}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("mandal"))	{
							
							List<Long> mandalIds =new ArrayList<Long>(0);
							List<Long> municipalIds = new ArrayList<Long>(0);
							
							if(mandalIdsList !=null && mandalIdsList.size()>0){
								for (Long long1 : mandalIds) {
									if(long1.toString().substring(0,1) == "2"){
										mandalIds.add(Long.valueOf((long1.toString().substring(1))));
									}if(long1.toString().substring(0,1) == "1"){
										municipalIds.add(Long.valueOf((long1.toString().substring(1))));
									}
									
								}
							}
							
							if(mandalIds !=null && mandalIds.size()>0){
								levelValues.addAll(tehsilDAO.getStateIdToByTehsil(mandalIds));
							}
							
							if(municipalIds !=null && municipalIds.size()>0){
								
							}				
						}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("village")){
							
						}*/
					}
					else if(levelId == 11l){//district
						
						//List<Long> districtIdsLst = new ArrayList<Long>(0);
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("state") ){
							
								List<Object[]> districts = districtDAO.getDistrictsForState(mainVo.getStateId());
								if(districts !=null && districts.size()>0){
									for (Object[] objects : districts) {									
										levelValues.add(objects[0] !=null ? (Long)objects[0]:0l);									
									}
								}							
						}
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("district") ){
							levelValues.addAll(districtIds);						
						}
						
						//Setting to VO
						if(levelValues !=null && levelValues.size()>0){
							locationVO.setDistrictIdsList(levelValues);//stateIds List						
							locationVO.setLevelId(levelId);
						}
					
						
					}
					
					else if(levelId == 1l){//Constituency
						
						//List<Long> districtIdsLst = new ArrayList<Long>(0);
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("state") ){
							
								List<Object[]> constituencies = constituencyDAO.getConstituenciesByStateId(mainVo.getStateId());
								if(constituencies !=null && constituencies.size()>0){
									for (Object[] objects : constituencies) {									
										levelValues.add(objects[0] !=null ? (Long)objects[0]:0l);									
									}
								}							
						}
						
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("district") ){
							
							List<Object[]> constituencies = constituencyDAO.getConstituenciesByDistrictIds(districtIds);
							if(constituencies !=null && constituencies.size()>0){
								for (Object[] objects : constituencies) {									
									levelValues.add(objects[0] !=null ? (Long)objects[0]:0l);									
								}
							}							
					}
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("constituency") ){
							levelValues.addAll(constituencyIds);						
						}
						//Setting to VO
						if(levelValues !=null && levelValues.size()>0){
							locationVO.setConstituencyIds(levelValues);//stateIds List						
							locationVO.setLevelId(levelId);
						}
					
						
					}
					
					
					
					else if(levelId == 5l){//Mandal
						
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("district")){
							
							List<Long> tehsilList = getTehsilIdsList("Tehsil",levelStr,districtIds);
							List<Long> townIds = getTehsilIdsList("Town",levelStr,districtIds);
							List<Long> divisionIds=  getTehsilIdsList("Division",levelStr,districtIds);
							
							if(tehsilList !=null && tehsilList.size()>0){
								levelValues.addAll(tehsilList);
								//locationVO.setTehsilIdsList(levelValues);
							}if(townIds !=null && townIds.size()>0){
								levelValues1.addAll(townIds);
								//locationVO.setTownIdsList(levelValues1);
							}
							if(divisionIds !=null && divisionIds.size()>0){
								levelValues2.addAll(divisionIds);
								//locationVO.setDivisionIdsList(levelValues2);
							}
							
						}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("constituency")){
							
							List<Long> tehsilList = getTehsilIdsList("Tehsil",levelStr,constituencyIds);
							List<Long> townIds = getTehsilIdsList("Town",levelStr,constituencyIds);
							List<Long> divisionIds=  getTehsilIdsList("Division",levelStr,constituencyIds);
							
							if(tehsilList !=null && tehsilList.size()>0){
								levelValues.addAll(tehsilList);
								//locationVO.setTehsilIdsList(levelValues);
							}if(townIds !=null && townIds.size()>0){
								levelValues1.addAll(townIds);
								//locationVO.setTownIdsList(levelValues1);
							}
							if(divisionIds !=null && divisionIds.size()>0){
								levelValues2.addAll(divisionIds);
								//locationVO.setDivisionIdsList(levelValues2);
							}
							
						}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("Mandal")){
							if(mandalIdsList !=null && mandalIdsList.size()>0){
								
								List<Long> divisionWiseIds = new ArrayList<Long>(0);
								
								for (Long long1 : mandalIdsList) {								
									if(long1.toString().substring(0,1).equalsIgnoreCase("2")){
										levelValues.add(Long.valueOf((long1.toString().substring(1))));//Mandals
									}if(long1.toString().substring(0,1).equalsIgnoreCase("1")){
										
										Long value = Long.valueOf((long1.toString().substring(1)));
										
										if(value !=null && value !=20l && value !=124l  ){
											levelValues1.add(value);//towns/localBodies
										}else{
											divisionWiseIds.add(value);
										}
										
									}								
								}
								
								if(divisionWiseIds !=null && divisionWiseIds.size()>0){
									levelValues2.addAll(constituencyDAO.getDivisionIdsOfGreater(divisionWiseIds));
								}
								
								//locationVO.setTehsilIdsList(levelValues);
								//locationVO.setTownIdsList(levelValues1);
								//locationVO.setDivisionIdsList(levelValues2);
								
								//locationVO.setLevelId(levelId);
							}
						}
						
						//Setting to VO
						if(levelValues !=null && levelValues.size()>0){
							locationVO.setTehsilIdsList(levelValues);
						}if(levelValues1 !=null && levelValues1.size()>0){
							locationVO.setTownIdsList(levelValues1);
						}if(levelValues2 !=null && levelValues2.size()>0){
							locationVO.setDivisionIdsList(levelValues2);
						}
						locationVO.setLevelId(levelId);
						
					}else if(levelId == 6l){//village
						
						if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("district")){
							
							levelValues.addAll(getVillagesIdsList("village",levelStr,districtIds));
							levelValues1.addAll(getVillagesIdsList("ward",levelStr,districtIds));
							
						}
						else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("constituency")){
							
							levelValues.addAll(getVillagesIdsList("village",levelStr,constituencyIds));
							levelValues1.addAll(getVillagesIdsList("ward",levelStr,constituencyIds));
							
						}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("mandal")){
							
							List<Long> mandalIds = new ArrayList<Long>(0);
							List<Long> localBodyIds = new ArrayList<Long>(0);
							
							for (Long long1 : mandalIdsList) {
								if(long1.toString().substring(0,1).equalsIgnoreCase("2")){
									mandalIds.add(Long.valueOf((long1.toString().substring(1))));//Mandals								
								}if(long1.toString().substring(0,1).equalsIgnoreCase("1")){																											
									localBodyIds.add(Long.valueOf((long1.toString().substring(1))));//Towns && Divisions
								}
								
							}
							
							List<Long> panchayats=new ArrayList<Long>(0);
							List<Long> wards = new ArrayList<Long>();
							
							if(mandalIds !=null && mandalIds.size()>0){
								panchayats = getVillagesIdsList("village",levelStr,mandalIds);
							}
							if(localBodyIds !=null && localBodyIds.size()>0){
								wards = getVillagesIdsList("ward",levelStr,localBodyIds);
							}
							
							
							if(panchayats !=null && panchayats.size()>0){
								levelValues.addAll(panchayats);//village level values For Mandal
							}if(wards !=null && wards.size()>0){
								levelValues1.addAll(wards);//ward level values For Local election Body
							}
							
						}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("village")){
							
							if(panchayatIdsList !=null && panchayatIdsList.size()>0){
								for (Long long1 : panchayatIdsList) {
									if(long1.toString().substring(0,1).equalsIgnoreCase("1")){
										levelValues.add(Long.valueOf((long1.toString().substring(1))));//Panchayat/Village
									}if(long1.toString().substring(0,1).equalsIgnoreCase("2")){																											
										levelValues1.add(Long.valueOf((long1.toString().substring(1))));//Ward
									}								
								}
							}
							
						}
						
						//Setting to VO
						if(levelValues !=null && levelValues.size()>0){
							locationVO.setVillageIdsList(levelValues);
						}
						if(levelValues1 !=null && levelValues1.size()>0){
							locationVO.setWardIdsList(levelValues1);
						}
						
						locationVO.setLevelId(levelId);
						
					}else if(levelId == 12l){
						locationVO.setLevelId(levelId);
						locationVO.getCounrtyIds().add(1l);
					}
							
				}
				
				
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return locationVO;
			
		}
		public List<Long> getTehsilIdsList(String locationType,String levelStr,List<Long> locationIds){
			List<Long> tehsilIds = new ArrayList<Long>(0);
			try{			
				if(locationType !=null && locationType.equalsIgnoreCase("Tehsil")){				
					if(levelStr !=null && levelStr.equalsIgnoreCase("district")){					
						tehsilIds = tehsilDAO.getAllTehsilDetails(locationIds);
					}else if(levelStr !=null && levelStr.equalsIgnoreCase("constituency")){
						tehsilIds = constituencyDAO.getAllTehsilsByConstituency(locationIds);
					}				
				}			
				else if(locationType !=null && locationType.equalsIgnoreCase("Town")){	
					if(levelStr !=null && levelStr.equalsIgnoreCase("district")){
						
						tehsilIds = districtDAO.getLocalBodiesOfDistrict(locationIds);//towns for District
						
					}else if(levelStr !=null && levelStr.equalsIgnoreCase("constituency")){
						
						tehsilIds = constituencyDAO.getAllLocalBodiesForConstituency(locationIds);
						
					}
				}
				else if(locationType !=null && locationType.equalsIgnoreCase("Division")){	
					if(levelStr !=null && levelStr.equalsIgnoreCase("district")){
						if(locationIds !=null && locationIds.size()>0)
							tehsilIds = constituencyDAO.getAllDivisions(locationIds);//Divisions For District
						
					}else if(levelStr !=null && levelStr.equalsIgnoreCase("constituency")){
							tehsilIds =	constituencyDAO.getAllDivisionsOfConstituency(locationIds);
					}
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			return tehsilIds;
		}
		
		public List<Long> getVillagesIdsList(String locationType,String levelStr,List<Long> locationIds){
			
			List<Long> villageWards = new ArrayList<Long>();
			
			try{
				
				if(locationType !=null && locationType.equalsIgnoreCase("village")){//Panchayat's only coming
					
					if(levelStr !=null && levelStr.equalsIgnoreCase("district")){	
						
						villageWards = constituencyDAO.getAllPanchayatsForDistrict(locationIds);
						
					}else if(levelStr !=null && levelStr.equalsIgnoreCase("constituency")){
						
						villageWards = constituencyDAO.getAllPanchayatsForConstituency(locationIds);
						
					}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("mandal")){
						
							villageWards = constituencyDAO.getAllPanchayatsByTehsilId(locationIds);
						
					}
					
				}else if(locationType !=null && locationType.equalsIgnoreCase("ward")){//wards only coming
					
					if(levelStr !=null && levelStr.equalsIgnoreCase("district")){	
						
						villageWards = constituencyDAO.getAllWardsForDistrict(locationIds);
						
					}else if(levelStr !=null && levelStr.equalsIgnoreCase("constituency")){
						
						villageWards = constituencyDAO.getAllWardsForConstituency(locationIds, 12l);
						
					}else if(levelStr !=null && !levelStr.isEmpty() && levelStr.equalsIgnoreCase("mandal")){//Local Election Body
						
							villageWards = constituencyDAO.getAllWardIdsForLocalBody(locationIds);
						
					}
				}
							
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return villageWards;
		}
		
		public String getLocationForDesignation(Long tdpCadreId,Long designationId)
		{
			
			String location = "";
			try{
				List<UserAddress> userAddressList = publicRepresentativeDAO.getUserAddressForCadre(tdpCadreId);
				
				if(userAddressList != null && userAddressList.size() > 0)
				{   
					UserAddress userAddress = userAddressList.get(0);
					  if(Arrays.asList(IConstants.PR_STATE_DESG_IDS).contains(designationId))
					  {
						  location = userAddress.getState() != null ? userAddress.getState().getStateName()+" State" : "";
					  }
					  else if(Arrays.asList(IConstants.PR_DISTRICT_DESG_IDS).contains(designationId))
					  {
						  location = userAddress.getDistrict() != null ? userAddress.getDistrict().getDistrictName() +" District": "";
					  }
					  else  if(Arrays.asList(IConstants.PR_AC_DESG_IDS).contains(designationId))
					  {
						  location = userAddress.getConstituency() != null ? userAddress.getConstituency().getName() +" Constituency" : "";
					  }
					  else if(Arrays.asList(IConstants.PR_PC_DESG_IDS).contains(designationId))
					  {
						  location = userAddress.getParliamentConstituency() != null ? userAddress.getParliamentConstituency().getName()+" Parliament" : "";
					  }
					  else if(Arrays.asList(IConstants.PR_MANDAL_DESG_IDS).contains(designationId))
					  {
						  location = userAddress.getTehsil() != null ? userAddress.getTehsil().getTehsilName() +" Mandal": "";
					  }
					  else if(Arrays.asList(IConstants.PR_TOWN_DESG_IDS).contains(designationId))
					  {
						  location = userAddress.getLocalElectionBody() != null ? userAddress.getLocalElectionBody().getName() +" Town": "";
					  }
					  else if(Arrays.asList(IConstants.PR_WARD_DESG_IDS).contains(designationId))
					  {
						  location = userAddress.getWard() != null ? userAddress.getWard().getName(): "";
					  }
					  else
						  location = userAddress.getState() != null ? userAddress.getState().getStateName()+" State" : "";  
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return location;
		}
		
		public Map<Long,String> getLocationMapForDesignation(Map<Long,Long> designationMap , List<Long> tdpCadreIds)
		{
			
			Map<Long,String> publicRepresLocaMap = new HashMap<Long, String>();
			
			String location = "";
			try{
				//0.stateId,1.state,2.districtId,3.district,4.constituencyId,5.name,6.parlaimentId,7.parliament,8.tehsilId,9.tehsil,10.localBodyId,11.localBody,
				//12.wardId,13.ward
				List<Object[]> userAddressList = publicRepresentativeDAO.getUserAddressForCadre1(tdpCadreIds);
				
				if(userAddressList != null && userAddressList.size() > 0)
				{   
					//Object[] userAddress = userAddressList.get(0);
					
					for(Object[]  userAddress: userAddressList){
						
						Long designationId = 0l;
						if((Long)userAddress[14] !=null){							
							designationId = designationMap.get((Long)userAddress[14]);						
						}
							
						
						if(Arrays.asList(IConstants.PR_STATE_DESG_IDS).contains(designationId))
						  {
							  if(userAddress[0] !=null && (Long)userAddress[0] >0){
								  location = userAddress[1] != null ? userAddress[1].toString().trim()+" State" : "";
							  }							  
						  }
						  else if(Arrays.asList(IConstants.PR_DISTRICT_DESG_IDS).contains(designationId))
						  {
							  if(userAddress[2] !=null && (Long)userAddress[2] >0){
								  location = userAddress[3] != null ? userAddress[3].toString().trim() +" District": "";
							  }									  
						  }
						  else  if(Arrays.asList(IConstants.PR_AC_DESG_IDS).contains(designationId)){
							  if(userAddress[4] !=null && (Long)userAddress[4] >0){							  
								  location = userAddress[5] != null ? userAddress[5].toString().trim()+" Constituency" : "";
							  }
						  }
						
						  else if(Arrays.asList(IConstants.PR_PC_DESG_IDS).contains(designationId))
						  {
							  if(userAddress[6] !=null && (Long)userAddress[6] >0){
								  location = userAddress[7] != null ? userAddress[7].toString().trim() +" Parliament" : "";
							  }
							  
						  }
						  else if(Arrays.asList(IConstants.PR_MANDAL_DESG_IDS).contains(designationId))
						  {
							  if(userAddress[8] !=null && (Long)userAddress[8] >0){
								  location = userAddress[9] != null ? userAddress[9].toString().trim() +" Mandal": "";
							  }
						  }
						  else if(Arrays.asList(IConstants.PR_TOWN_DESG_IDS).contains(designationId))
						  {
							  if(userAddress[10] !=null && (Long)userAddress[10] >0){
								  location = userAddress[11] != null ? userAddress[11].toString().trim() +" Town": "";
							  }
						  }
						  else if(Arrays.asList(IConstants.PR_WARD_DESG_IDS).contains(designationId))
						  {
							  if(userAddress[12] !=null && (Long)userAddress[12] >0){
								  location = userAddress[13] != null ? userAddress[13].toString().trim(): "";
							  }
						  }
						  else
							  location = userAddress[1] != null ? userAddress[1].toString().trim()+" State" : ""; 
						
						publicRepresLocaMap.put((Long)userAddress[14], location);
					}
					
				}
			}
			catch(Exception e)
			{
				LOG.error("Exception occured in getLocationMapForDesignation() - ",e);
			}
			return publicRepresLocaMap;
		}
}
