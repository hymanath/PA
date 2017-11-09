package com.itgrids.core.api.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationWiseCasteInfoService;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IParliamentAssemblyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class LocationWiseCasteInfoService implements ILocationWiseCasteInfoService {
	
	private final static Logger LOG = Logger.getLogger(LocationDashboardService.class);
	private IConstituencyDAO constituencyDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO;
	private ICasteDAO casteDAO;
	private IVoterAgeRangeDAO voterAgeRangeDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IVoterAgeInfoDAO voterAgeInfoDAO;
	private IParliamentAssemblyDAO parliamentAssemblyDAO;

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public CommonMethodsUtilService getCommonMethodsUtilService() {
		return commonMethodsUtilService;
	}

	public void setCommonMethodsUtilService(
			CommonMethodsUtilService commonMethodsUtilService) {
		this.commonMethodsUtilService = commonMethodsUtilService;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}


	public ITdpCadreCasteInfoDAO getTdpCadreCasteInfoDAO() {
		return tdpCadreCasteInfoDAO;
	}

	public void setTdpCadreCasteInfoDAO(ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO) {
		this.tdpCadreCasteInfoDAO = tdpCadreCasteInfoDAO;
	}

	
	public ICasteDAO getCasteDAO() {
		return casteDAO;
	}

	public void setCasteDAO(ICasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}
	
	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}
	

	public IVoterAgeInfoDAO getVoterAgeInfoDAO() {
		return voterAgeInfoDAO;
	}

	public void setVoterAgeInfoDAO(IVoterAgeInfoDAO voterAgeInfoDAO) {
		this.voterAgeInfoDAO = voterAgeInfoDAO;
	}

	
	public IParliamentAssemblyDAO getParliamentAssemblyDAO() {
		return parliamentAssemblyDAO;
	}

	public void setParliamentAssemblyDAO(
			IParliamentAssemblyDAO parliamentAssemblyDAO) {
		this.parliamentAssemblyDAO = parliamentAssemblyDAO;
	}

	/**
	 * 
	 * @param locationTypeId
	 * @param locationValue
	 * @param publicationDateId
	 * @param casteGroupId
	 * @param enrollemntId
	 * @return
	 */
	@Override
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(Long locationTypeId, Long locationValue, Long publicationDateId,Long EnrollmentYearId,Long casteGroupId,
			String assendingType) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Long reportLevelId= 0l;
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			Long totalCadreCount=0l,totalVoterCount=0l;
			if(locationTypeId == 3l || locationTypeId == 4l || locationTypeId == 2l){
				 constituencyIds.add(locationValue);
		        reportLevelId= 1l;
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 5l){
		    	  reportLevelId=2l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 6l){
		    	  reportLevelId=3l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 7l){
		    	  reportLevelId=5l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 8l){
		    	  reportLevelId=6l;
		    	  constituencyIds.add(locationValue);
		      }
			
			Map<Long, LocationVotersVO> casteMap= new HashMap<Long, LocationVotersVO>();
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyIds, publicationDateId,locationTypeId, reportLevelId,casteGroupId);

			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					LocationVotersVO casteVO=casteMap.get(commonMethodsUtilService.getLongValueForObject(objects[2]));
					if(casteVO == null){
						casteVO= new LocationVotersVO();
						casteVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[2]));
						casteVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[3]));
						casteVO.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						casteVO.setCastgroup(commonMethodsUtilService.getStringValueForObject(objects[1]));
						casteMap.put(commonMethodsUtilService.getLongValueForObject(objects[2]),casteVO);
					}					
					casteVO.setTotalVoters(casteVO.getTotalVoters()+(Long) objects[4]);
					casteVO.setVoterPercentage(casteVO.getVoterPercentage()+(Double) objects[5]);
					casteVO.setMaleVoters(casteVO.getMaleVoters()+(Long) objects[6]);
					casteVO.setFemaleVoters(casteVO.getFemaleVoters()+(Long) objects[7]);
					totalVoterCount=totalVoterCount+commonMethodsUtilService.getLongValueForObject(objects[4]);
				}
			}
			
			// o-castegrpId,1-castgrpname,2-casteId,3-castName, 4-gender,5-count
			List<Object[]> objList = tdpCadreCasteInfoDAO.getCasteNGenderWiseCadreCounts(locationTypeId,constituencyIds,EnrollmentYearId,casteGroupId);
			
			for (Object[] param : objList) {
				LocationVotersVO casteVO=casteMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				if(casteVO == null){
					casteVO= new LocationVotersVO();
					casteVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(param[2]));
					casteVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(param[3]));
					casteVO.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(param[0]));
					casteVO.setCastgroup(commonMethodsUtilService.getStringValueForObject(param[1]));
					casteMap.put(commonMethodsUtilService.getLongValueForObject(param[2]),casteVO);
				}	
				if(casteVO!=null){
					if (param[4].toString().equalsIgnoreCase("M")) {
						casteVO.setMaleCadres(casteVO.getMaleCadres()+commonMethodsUtilService.getLongValueForObject(param[5]));
					} else if (param[4].toString().equalsIgnoreCase("F")) {
						casteVO.setFemaleCadres(casteVO.getFemaleCadres()+commonMethodsUtilService.getLongValueForObject(param[5]));
					}
					casteVO.setTotalCadres(casteVO.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(param[5]));
				}
				totalCadreCount=totalCadreCount+commonMethodsUtilService.getLongValueForObject(param[5]);
			}
			voList.addAll(casteMap.values());
			
			//for top 15 castes
			voList = compareList(voList,"desending");
			List<LocationVotersVO> topCastesList = new ArrayList<LocationVotersVO>(0);
			for(int i=0; i<voList.size(); i++){
				LocationVotersVO topCastesVo = voList.get(i);
				topCastesList.add(topCastesVo);
			}
			voList.get(0).setLocationVotersVOList1(topCastesList);

			// calculating totals and %'s
			if (voList != null && voList.size() > 0) {
				for (LocationVotersVO casteVO : voList) {
					if(casteVO.getTotalVoters() !=0l ){
						casteVO.setMaleVotersPerc(((casteVO.getMaleVoters() * 100) / casteVO.getTotalVoters()) + "%");
						casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters() * 100) / casteVO.getTotalVoters()) + "%");
						casteVO.setTotalVotersPerc((casteVO.getTotalVoters() * 100/totalVoterCount) +"%");
					}
					if(casteVO.getTotalCadres()!=0l){
						casteVO.setMaleCadrePerc(((casteVO.getMaleCadres() * 100) / casteVO.getTotalCadres()) + "%");
						casteVO.setFemaleCadrePerc(((casteVO.getFemaleCadres() * 100) / casteVO.getTotalCadres()) + "%");
						casteVO.setTotalCadrePerc((casteVO.getTotalCadres() * 100/totalCadreCount) +"%");
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupNAgeWiseVoterNCadreCounts", e);
		}
		voList = compareList(voList,assendingType);
		return voList;
	}
	
	public LocationVotersVO getMatchedVO(List<LocationVotersVO> voList, Long id) {
		if (voList != null && voList.size() > 0 && id != null && id > 0l) {
			for (LocationVotersVO locationVotersVO : voList) {
				if (locationVotersVO.getAgeRangeId().equals(id))
					return locationVotersVO;
			}
		}
		return null;
	}
	
	public List<LocationVotersVO> compareList(List<LocationVotersVO> voList,String assendingType) {
		if (assendingType.equalsIgnoreCase("desending")) {
			Collections.sort(voList, new Comparator<LocationVotersVO>() {
				public int compare(LocationVotersVO one, LocationVotersVO two) {
					return two.getTotalVoters().compareTo(one.getTotalVoters());
				}
			});
		} else if (assendingType.equalsIgnoreCase("assending")) {
			Collections.sort(voList, new Comparator<LocationVotersVO>() {
				public int compare(LocationVotersVO one, LocationVotersVO two) {
					return one.getTotalVoters().compareTo(two.getTotalVoters());
				}
			});
		} else if (assendingType.equalsIgnoreCase("atozSorting")) {
			Collections.sort(voList, new Comparator<LocationVotersVO>() {
				public int compare(LocationVotersVO one, LocationVotersVO two) {
					return one.getAgeRange().toUpperCase().compareTo(two.getAgeRange().toUpperCase());
				}
			});
		} else if (assendingType.equalsIgnoreCase("ztoaSorting")) {
			Collections.sort(voList, new Comparator<LocationVotersVO>() {
				public int compare(LocationVotersVO one, LocationVotersVO two) {
					return two.getAgeRange().toUpperCase().compareTo(one.getAgeRange().toUpperCase());
				}
			});
		}
		return voList;
	}
	//For Graph
	
	/**
	 * 
	 * @param locationTypeId
	 * @param locationValue
	 * @param publicationDateId
	 * @param enrollmentId
	 * 
	 *  
	 */
	public List<LocationVotersVO> getVotersCastGroupWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId,Long enrollmentId){
		
		try{
			List<LocationVotersVO>  listVo = new ArrayList<LocationVotersVO>();
			Long reportLevelId= 0l;
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			Long totalVoterCount=0l,totalCadreCount=0l;
			locationIds.add(locationValue);
			if(locationTypeId == 3l || locationTypeId == 4l || locationTypeId == 2l){
				 constituencyIds.add(locationValue);
		        reportLevelId= 1l;
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 5l){
		    	  reportLevelId=2l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 6l){
		    	  reportLevelId=3l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 7l){
		    	  reportLevelId=5l;
		    	  constituencyIds.add(locationValue);
		      }
		      else if(locationTypeId == 8l){
		    	  reportLevelId=6l;
		    	  constituencyIds.add(locationValue);
		      }
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCastGroupWiseCount(constituencyIds,locationTypeId, publicationDateId, reportLevelId);
			if(votersObjList!=null){
				for (Object[] objects : votersObjList) {
					LocationVotersVO vo = new LocationVotersVO();
					vo.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setTotalVoters(commonMethodsUtilService.getLongValueForObject(objects[2]));
					totalVoterCount=totalVoterCount+commonMethodsUtilService.getLongValueForObject(objects[2]);
					listVo.add(vo);
				}
			}
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> cadresObjList = tdpCadreCasteInfoDAO.getCasteGroupWiseCadreCounts(locationTypeId,constituencyIds,enrollmentId);
			
			if (cadresObjList != null && cadresObjList.size() > 0) {
				for (Object[] objects : cadresObjList) {
					LocationVotersVO matchedCGVO = getMatchedVO(listVo, (Long) objects[0]);
					if (matchedCGVO == null) {
						matchedCGVO = new LocationVotersVO();
						matchedCGVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						matchedCGVO.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[1]));
						listVo.add(matchedCGVO);
					} else {
						matchedCGVO.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[1]));
					}
					totalCadreCount=totalCadreCount+commonMethodsUtilService.getLongValueForObject(objects[1]);
				}
			}
			if (listVo != null && listVo.size() > 0) {
				for (LocationVotersVO casteVO : listVo) {
					if(totalVoterCount !=0l ){
						casteVO.setTotalVotersPerc((casteVO.getTotalVoters() * 100.0/totalVoterCount) +"%");
					}
					if(totalCadreCount!=0l){
						casteVO.setTotalCadrePerc((casteVO.getTotalCadres() * 100.0/totalCadreCount) +"%");
					}
				}
			}
			return listVo;
		}catch(Exception e){
			Log.error("Exception raised at getVotersCastGroupWiseCount", e);
			return null;
		}
		
		
	}
	
	// for expand block
	/**
	 * 
	 * @param locationTypeId
	 * @param locationValue
	 * @param publicationDateId
	 * @param casteGroupId
	 * @param casteId
	 * @return
	 */
	
	public List<LocationVotersVO> getCasteNAgeWiseVoterNCadreCounts(Long locationTypeId,Long locationValue, Long publicationDateId,Long casteGroupId, Long casteId,
			Long enrollemntyearId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			Long  reportLevelId=0l;
			if(locationTypeId == 3l || locationTypeId == 4l || locationTypeId == 2l){
				 constituencyIds.add(locationValue);
		        reportLevelId= 1l;
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  reportLevelId= 1l;
		      }else if(locationTypeId == 5l){
		    	  reportLevelId=2l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 6l){
		    	  reportLevelId=3l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 7l){
		    	  reportLevelId=5l;
		    	  constituencyIds.add(locationValue);
		      }
		      else if(locationTypeId == 8l){
		    	  reportLevelId=6l;
		    	  constituencyIds.add(locationValue);
		      }
			
			
			Map<Long, LocationVotersVO> map = new LinkedHashMap<Long, LocationVotersVO>();
			
			List<Object[]> ageRange = voterAgeRangeDAO.getSpecificAgeRangeList();
			// template building
			if (ageRange != null && ageRange.size() > 0) {
				for (Object[] objects : ageRange) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						inVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}
				}
			}
			//0 ageRangeId,1 gender,2 count
			//List<Object[]> votersObjList= null;
		    List<Object[]> votersObjList = userVoterDetailsDAO.getVotersCasteNAgeGroupWiseCounts(casteGroupId,casteId, reportLevelId,locationTypeId, constituencyIds,publicationDateId);
			//votersObjList = userVoterDetailsDAO.getVotersCasteNAgeGroupWiseCount(casteGroupId,casteId, constituencyIds,publicationDateId);

			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						//inVO.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}

					if (objects[1].toString().equalsIgnoreCase("M")) {
						map.get((Long) objects[0]).setMaleVoters((Long) objects[2]);
					} else if (objects[1].toString().equalsIgnoreCase("F")) {
						map.get((Long) objects[0]).setFemaleVoters((Long) objects[2]);
					}

				}
			}

			List<Object[]> cadresObjList = tdpCadreCasteInfoDAO.getCadresCasteNAgeGroupWiseCounts(locationTypeId,constituencyIds,casteGroupId, casteId,enrollemntyearId);
			if (cadresObjList != null && cadresObjList.size() > 0) {
				for (Object[] objects : cadresObjList) {
					if (map.get((Long) objects[0]) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						inVO.setAgeRangeId((Long) objects[0]);
						inVO.setAgeRange(objects[1].toString());
						map.put((Long) objects[0], inVO);
					}

					if (objects[2].toString().equalsIgnoreCase("M")) {
						map.get((Long) objects[0]).setMaleCadres((Long) objects[3]);
					} else if (objects[2].toString().equalsIgnoreCase("F")) {
						map.get((Long) objects[0]).setFemaleCadres((Long) objects[3]);
					}
				}
			}

			if (map != null && map.size() > 0) {
				Long totalVoters = 0l, totalMaleVoters = 0l, totalFemaleVoters = 0l, totalCadres = 0l,
						totalMaleCadres = 0l, totalFemaleCadres = 0l;
				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue()
					.setTotalVoters(entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters());
					entry.getValue()
					.setTotalCadres(entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres());

					totalVoters = totalVoters + entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters();
					totalMaleVoters = totalMaleVoters + entry.getValue().getMaleVoters();
					totalFemaleVoters = totalFemaleVoters + entry.getValue().getFemaleVoters();
					totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
					totalMaleCadres = totalMaleCadres + entry.getValue().getMaleCadres();
					totalFemaleCadres = totalFemaleCadres + entry.getValue().getFemaleCadres();
				}

				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue().setTotalVotersPerc(round(((entry.getValue().getTotalVoters() * 100.00) / totalVoters),2) + "%");
					entry.getValue().setMaleVotersPerc(round(((entry.getValue().getMaleVoters() * 100.00) / totalMaleVoters),2) + "%");
					entry.getValue().setFemaleVotersPerc(round(((entry.getValue().getFemaleVoters() * 100.00) / totalFemaleVoters),2) + "%");
					entry.getValue().setTotalCadrePerc(round(((entry.getValue().getTotalCadres() * 100.00) / totalCadres),2) + "%");
					entry.getValue().setMaleCadrePerc(round(((entry.getValue().getMaleCadres() * 100.00) / totalMaleCadres),2) + "%");
					entry.getValue().setFemaleCadrePerc(round(((entry.getValue().getFemaleCadres() * 100.00) / totalFemaleCadres),2) + "%");
				}

				voList.addAll(map.values());
			}
		} catch (Exception e) {
			LOG.error("Exception raised at getCasteNAgeWiseVoterNCadreCounts", e);
		}
		return voList;
	}
	
	
	public static double round(double value, int places) {
		if (places < 0) throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	//for voter And cadre
	/**
	 * 
	 * @param locationTypeId
	 * @param locationValue
	 * @param publicationDateId
	 * @return
	 */
	
	@Override
	public List<LocationVotersVO> getVotersAndcadreAgeWiseCount(Long locationTypeId,Long locationValue, Long publicationDateId, Long enrollmentId) {
		List<LocationVotersVO> voList = new LinkedList<LocationVotersVO>();
		try {
			Long reportLevelId= 0l;
			List<Long> constituencyIds = new ArrayList<Long>();
			List<Long> locationIds = new ArrayList<Long>();
			locationIds.add(locationValue);
			if(locationTypeId == 3l){
		        List<Object[]> locationValuesObj = constituencyDAO.getDistrictConstituenciesList(locationIds);
		        for (Object[] objects : locationValuesObj) {
		          if(objects!=null){
		        	  constituencyIds.add(commonMethodsUtilService.getLongValueForObject(objects[0]));
		          }
		        }
		        reportLevelId= 1l;
		      }else if(locationTypeId == 10l){
		    	  constituencyIds = delimitationConstituencyAssemblyDetailsDAO.findAssembliesConstituenciesForAListOfParliamentConstituency(locationIds);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 4l){
		    	  constituencyIds.add(locationValue);
		    	  reportLevelId=1l;
		      }else if(locationTypeId == 5l){
		    	  reportLevelId=2l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 6l){
		    	  reportLevelId=3l;
		    	  constituencyIds.add(locationValue);
		      }else if(locationTypeId == 7l){
		    	  reportLevelId=5l;
		    	  constituencyIds.add(locationValue);
		      }
		      else if(locationTypeId == 8l){
		    	  reportLevelId=6l;
		    	  constituencyIds.add(locationValue);
		      } 
			Map<Long, LocationVotersVO> map = new LinkedHashMap<Long, LocationVotersVO>();
			 List<VoterAgeRange> voterAgeRangeList = voterAgeRangeDAO.getVoterAgeRangeList();
		
			 if(voterAgeRangeList != null && voterAgeRangeList.size() > 0 ){
				for (VoterAgeRange voterAgeRange : voterAgeRangeList) {
					if (voterAgeRange.getVoterAgeRangeId() != 7) {
						LocationVotersVO ageRangeVo = new LocationVotersVO();
						ageRangeVo.setAgeRangeId(voterAgeRange.getVoterAgeRangeId());
						ageRangeVo.setAgeRange(voterAgeRange.getAgeRange());
						map.put(voterAgeRange.getVoterAgeRangeId(), ageRangeVo);
					}
				}
			 }
			List<Object[]> votersObjList = voterAgeInfoDAO.getVotersAgeWiseCount(constituencyIds, publicationDateId,reportLevelId);
			if (votersObjList != null && votersObjList.size() > 0) {
				for (Object[] objects : votersObjList) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					} else {
						LocationVotersVO vo = map.get(commonMethodsUtilService.getLongValueForObject(objects[0]));
						if (vo != null) {
							vo.setTotalVoters(objects[1] != null ? (Long) objects[1] : 0l);
							//vo.setTotalVotersPerc(objects[2] != null ? objects[2].toString() + " %" : "");
							vo.setMaleVoters(objects[3] != null ? (Long) objects[3] : 0l);
							//vo.setMaleVotersPerc(objects[4] != null ? objects[4].toString() + " %" : "");
							vo.setFemaleVoters(objects[5] != null ? (Long) objects[5] : 0l);
							//vo.setFemaleVotersPerc(objects[6] != null ? objects[6].toString() + " %" : "");
						}
					}
				}
			}
			if(locationTypeId == 2l){
		    	  constituencyIds.add(locationValue);
		      }
			//List<Object[]> cadreObjList = tdpCadreEnrollmentYearDAO.getGenderAndAgeGroupWiseCadreCount(locationTypeId,locationValue);
			List<Object[]> cadreObjList = tdpCadreCasteInfoDAO.getGenderAndAgeGroupWiseCadreCount(locationTypeId,constituencyIds,enrollmentId);
			if (cadreObjList != null && cadreObjList.size() > 0) {
				for (Object[] objects : cadreObjList) {
					if (map.get(commonMethodsUtilService.getLongValueForObject(objects[0])) == null) {
						LocationVotersVO inVO = new LocationVotersVO();
						map.put(commonMethodsUtilService.getLongValueForObject(objects[0]), inVO);
					}

					if ((objects[1].toString().trim()).equalsIgnoreCase("M")) {
						map.get(commonMethodsUtilService.getLongValueForObject(objects[0])).setMaleCadres((Long) objects[2]);
					} else if ((objects[1].toString().trim()).equalsIgnoreCase("F")) {
						map.get(commonMethodsUtilService.getLongValueForObject(objects[0])).setFemaleCadres((Long) objects[2]);
					}
				}
				
			}

			if (map != null && map.size() > 0) {
				LocationVotersVO voForTotalCounts = new LocationVotersVO();
				Long totalCadres = 0l, maleTotalCadres = 0l, femaleTotalCadres = 0l;
				Long totalVoters= 0l, maleTotalVoters=0l,femaleTotalVoters=0l;
				
				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					entry.getValue().setTotalCadres(entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres());
					totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
					maleTotalCadres = maleTotalCadres + entry.getValue().getMaleCadres();
					femaleTotalCadres = femaleTotalCadres + entry.getValue().getFemaleCadres();
					
					//voters
					entry.getValue().setTotalVoters(entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters());
					totalVoters = totalVoters + entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters();
					maleTotalVoters = maleTotalVoters + entry.getValue().getMaleVoters();
					femaleTotalVoters = femaleTotalVoters + entry.getValue().getFemaleVoters();
				}

				for (Entry<Long, LocationVotersVO> entry : map.entrySet()) {
					if (totalCadres > 0l)
						entry.getValue().setTotalCadrePerc(((entry.getValue().getTotalCadres() * 100) / totalCadres) + "%");
					if (maleTotalCadres > 0l)
						entry.getValue().setMaleCadrePerc(((entry.getValue().getMaleCadres() * 100) / maleTotalCadres) + "%");
					if (femaleTotalCadres > 0l)
						entry.getValue().setFemaleCadrePerc(((entry.getValue().getFemaleCadres() * 100) / femaleTotalCadres) + "%");

					if (totalVoters > 0l)
						entry.getValue().setTotalVotersPerc(((entry.getValue().getTotalVoters() * 100) / totalVoters) + "%");
					if (maleTotalVoters > 0l)
						entry.getValue().setMaleVotersPerc(((entry.getValue().getMaleVoters() * 100) / maleTotalVoters) + "%");
					if (femaleTotalVoters > 0l)
						entry.getValue().setFemaleVotersPerc(((entry.getValue().getFemaleVoters() * 100) / femaleTotalVoters) + "%");

					voForTotalCounts.setTotalVoters(voForTotalCounts.getTotalVoters() + entry.getValue().getTotalVoters());
					voForTotalCounts.setTotalCadres(voForTotalCounts.getTotalCadres() + entry.getValue().getTotalCadres());
					voForTotalCounts.setMaleVoters(voForTotalCounts.getMaleVoters() + entry.getValue().getMaleVoters());
					voForTotalCounts.setMaleCadres(voForTotalCounts.getMaleCadres() + entry.getValue().getMaleCadres());
					voForTotalCounts.setFemaleVoters(voForTotalCounts.getFemaleVoters() + entry.getValue().getFemaleVoters());
					voForTotalCounts.setFemaleCadres(voForTotalCounts.getFemaleCadres() + entry.getValue().getFemaleCadres());
				}

				voList.addAll(map.values());
				voList.add(voList.size(), voForTotalCounts);
			}

		} catch (Exception e) {
			LOG.error("Exception raised at votersAndcadreAgeWiseCount", e);
		}
		return voList;
	}
	
	@Override
	public List<LocationVotersVO> getCasteWiseCadreCounts(Long locationTypeId, List<Long> LocationValue,List<Long> enrollmentYearIds){
		List<LocationVotersVO> finalList = new ArrayList<LocationVotersVO>();
		try{
			List<Object[]> enrollmentYearsList =  tdpCadreCasteInfoDAO.enrollmentYearsBasedOnenrollmentYearIds(enrollmentYearIds);
			Map<Long,LocationVotersVO> cadreLocationMap = new HashMap<Long, LocationVotersVO>();
			Map<Long,LocationVotersVO> cadreCasteWiseMap = new HashMap<Long, LocationVotersVO>();
		
			List<Object[]> casteCadreList = tdpCadreCasteInfoDAO.getCasteWiseCadreCounts(locationTypeId, LocationValue, enrollmentYearIds);
			
			// o-casteCategoryId,1-casteCategoryName,2-caste_state_id,3-castName, 4-count,5-yearId 6-locationUId ,7-locationNAme
			if(commonMethodsUtilService.isListOrSetValid(casteCadreList)){
				for (Object[] objects : casteCadreList) {
					LocationVotersVO locationVo= cadreLocationMap.get(commonMethodsUtilService.getLongValueForObject(objects[6]));
					if(locationVo ==null){
						locationVo = new LocationVotersVO();
						locationVo.setLocationId(commonMethodsUtilService.getLongValueForObject(objects[6]));
						locationVo.setLocationName(commonMethodsUtilService.getStringValueForObject(objects[7]));
							
						LocationVotersVO yearVo = new LocationVotersVO();
						yearVo.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[5]));
							
						LocationVotersVO innerVo = new LocationVotersVO();
						innerVo.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(objects[0]));
						innerVo.setCastgroup(commonMethodsUtilService.getStringValueForObject(objects[1]));
						innerVo.setCasteId(commonMethodsUtilService.getLongValueForObject(objects[2]));
						innerVo.setCasteName(commonMethodsUtilService.getStringValueForObject(objects[3]));
						innerVo.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[4]));
						
						yearVo.getLocationVotersVOList().add(innerVo);
						locationVo.getLocationVotersVOList().add(yearVo);
						
						cadreLocationMap.put(commonMethodsUtilService.getLongValueForObject(objects[6]), locationVo);
					}else{
						LocationVotersVO yearVo= getMatchedYearVO(locationVo.getLocationVotersVOList(), commonMethodsUtilService.getLongValueForObject(objects[5]));
						
						if(yearVo == null){ 
							yearVo = new LocationVotersVO();
							yearVo.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[5]));
							
							LocationVotersVO innerVo = new LocationVotersVO();
							innerVo.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							innerVo.setCastgroup(commonMethodsUtilService.getStringValueForObject(objects[1]));
							innerVo.setCasteId(commonMethodsUtilService.getLongValueForObject(objects[2]));
							innerVo.setCasteName(commonMethodsUtilService.getStringValueForObject(objects[3]));
							innerVo.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[4]));
							yearVo.getLocationVotersVOList().add(innerVo);
							locationVo.getLocationVotersVOList().add(yearVo);
						}else{
							LocationVotersVO innerVo = new LocationVotersVO();
							innerVo.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(objects[0]));
							innerVo.setCastgroup(commonMethodsUtilService.getStringValueForObject(objects[1]));
							innerVo.setCasteId(commonMethodsUtilService.getLongValueForObject(objects[2]));
							innerVo.setCasteName(commonMethodsUtilService.getStringValueForObject(objects[3]));
							innerVo.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[4]));
							yearVo.getLocationVotersVOList().add(innerVo);
						}
						
					}
				}
			}
			
			Map<Long,Long> yearWiseTotalCadreMap = new HashMap<Long, Long>(0);
			
			for (Object[] param : casteCadreList) {
				
				LocationVotersVO casteVo = cadreCasteWiseMap.get(commonMethodsUtilService.getLongValueForObject(param[2]));
				if(casteVo == null){
					
					casteVo = new LocationVotersVO();
					casteVo.setCasteGroupId(commonMethodsUtilService.getLongValueForObject(param[0]));
					casteVo.setCastgroup(commonMethodsUtilService.getStringValueForObject(param[1]));
					casteVo.setCasteId(commonMethodsUtilService.getLongValueForObject(param[2]));
					casteVo.setCasteName(commonMethodsUtilService.getStringValueForObject(param[3]));
					
					LocationVotersVO yearVo = new LocationVotersVO();
					yearVo.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(param[5]));
					yearVo.setTotalCadres(yearVo.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(param[4]));
					
					casteVo.getLocationVotersVOList().add(yearVo);
					
					cadreCasteWiseMap.put(commonMethodsUtilService.getLongValueForObject(param[2]), casteVo);
				}else{
					LocationVotersVO yearVo= getMatchedYearVO(casteVo.getLocationVotersVOList(), commonMethodsUtilService.getLongValueForObject(param[5]));
					if(yearVo == null){ 
						yearVo = new LocationVotersVO();
						yearVo.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(param[5]));
						yearVo.setTotalCadres(yearVo.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(param[4]));

						casteVo.getLocationVotersVOList().add(yearVo);
					}else{
						yearVo.setTotalCadres(yearVo.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(param[4]));
						yearVo.getLocationVotersVOList().add(yearVo);
					}
				}
				
			}
			finalList.addAll(cadreLocationMap.values());
			if(commonMethodsUtilService.isListOrSetValid(finalList)){
				finalList.get(0).getLocationVotersVOList1().addAll(cadreCasteWiseMap.values());
			}
			for (LocationVotersVO objects : finalList) {
				for (LocationVotersVO param : objects.getLocationVotersVOList()) {
					Collections.sort(param.getLocationVotersVOList(), new Comparator<LocationVotersVO>() {
						public int compare(LocationVotersVO one, LocationVotersVO two) {
							return two.getTotalCadres().compareTo(one.getTotalCadres());
						}
					});
				}
			}
			//0-id,1-year2-desc
			for (Object[] objects : enrollmentYearsList) {
				for (LocationVotersVO locationVotersVO : finalList) {
					for (LocationVotersVO innerLocatioNVo : locationVotersVO.getLocationVotersVOList()) {
						if(innerLocatioNVo.getAgeRangeId() == commonMethodsUtilService.getLongValueForObject(objects[0])){
							innerLocatioNVo.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[2]));
						}
					}
					
				}
				for (LocationVotersVO casteVo : finalList.get(0).getLocationVotersVOList1()) {
					for (LocationVotersVO innerLocatioNVo : casteVo.getLocationVotersVOList()) {
						if(innerLocatioNVo.getAgeRangeId() == commonMethodsUtilService.getLongValueForObject(objects[0])){
							innerLocatioNVo.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[2]));
						}
					}
					
				}
				
			}
		}catch(Exception e){
			LOG.error("Exception raised at votersAndcadreAgeWiseCount", e);
		}
		
		return finalList;
		
	}
	
	public LocationVotersVO getMatchedYearVO(List<LocationVotersVO> voList, Long id) {
		if (voList != null && voList.size() > 0 && id != null && id.longValue() > 0l) {
			for (LocationVotersVO locationVotersVO : voList) {
				if (locationVotersVO != null && locationVotersVO.getAgeRangeId().longValue() == id.longValue())
					return locationVotersVO;
			}
		}
		return null;
	}

	/**
	 * @author  Sai Kumar  <href:saikumar.mandal@itgrids.com >
	 * @Date 8th Nov,2017
	 * @description to  get  Locationwise  Voters and Cadre details
	 * @param locationScopeId,locationValues, casteId,enrollmentYearId,publicationDateId
	 * @return List of LocationVotersVO 
	*/
	@Override
	public List<LocationVotersVO> getLocationWiseVoterNCadreCounts(Long locationTypeId,List<Long> locationValues, Long casteId,Long enrollmentYearId,Long publicationDateId) {
		//locationId,1 locationName,2 gender,3 count
		List<LocationVotersVO> finalLsit = new ArrayList<LocationVotersVO>();
		try{
		Map<Long,LocationVotersVO> locationIdsMap = new HashMap<Long, LocationVotersVO>();
		List<Long> consIdsList=new ArrayList<Long>();
		List<Object[]> locationWiseCadreList = null;
		List<Object[]> locationWiseVoterList =null;
		if(locationTypeId != null && locationTypeId.longValue() ==10l){
			List<Object[]> consIdsObj = parliamentAssemblyDAO.getConsIdsByParliamntsIds(locationValues);
			if(consIdsObj != null && consIdsObj.size() >0){
				for(Object[] param:consIdsObj){
					consIdsList.add(commonMethodsUtilService.getLongValueForObject(param[0]));
				}
			}
			  locationWiseCadreList = tdpCadreCasteInfoDAO.getLocationWiseCadreCounts(locationTypeId, consIdsList, casteId, enrollmentYearId);
			  locationWiseVoterList = userVoterDetailsDAO.getLocationWiseVoterCounts(locationTypeId, consIdsList, casteId, publicationDateId);
		}else{
		      locationWiseCadreList = tdpCadreCasteInfoDAO.getLocationWiseCadreCounts(locationTypeId, locationValues, casteId, enrollmentYearId);
		      locationWiseVoterList = userVoterDetailsDAO.getLocationWiseVoterCounts(locationTypeId, locationValues, casteId, publicationDateId);
		}
		if(locationWiseCadreList !=null && locationWiseCadreList.size() >0) {
			for(Object[] param : locationWiseCadreList){
				Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				if(locationIdsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) == null) {
					LocationVotersVO locationWiseVO = new LocationVotersVO();
					locationWiseVO.setLocationId(locationId);
					locationWiseVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					if(commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("m")){
						locationWiseVO.setMaleCadres(commonMethodsUtilService.getLongValueForObject(param[3]));
					}else{
						locationWiseVO.setFemaleCadres(commonMethodsUtilService.getLongValueForObject(param[3]));
					}
					locationWiseVO.setTotalCadres(locationWiseVO.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(param[3]));
					locationIdsMap.put(locationId, locationWiseVO);
				}else{
					LocationVotersVO locationWiseVO = locationIdsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("m")){
						locationWiseVO.setMaleCadres(commonMethodsUtilService.getLongValueForObject(param[3]));
					}else{
						locationWiseVO.setFemaleCadres(commonMethodsUtilService.getLongValueForObject(param[3]));
					}
					locationWiseVO.setTotalCadres(locationWiseVO.getTotalCadres()+commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
		}

		
		
		if(locationWiseVoterList !=null && locationWiseVoterList.size() >0) {
			for(Object[] param : locationWiseVoterList){
				Long locationId = commonMethodsUtilService.getLongValueForObject(param[0]);
				if(locationIdsMap.get(commonMethodsUtilService.getLongValueForObject(param[0])) == null) {
					LocationVotersVO locationWiseVO = new LocationVotersVO();
					locationWiseVO.setLocationId(locationId);
					locationWiseVO.setLocationName(commonMethodsUtilService.getStringValueForObject(param[1]));
					if(commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("m")){
						locationWiseVO.setMaleVoters(commonMethodsUtilService.getLongValueForObject(param[3]));
					}else{
						locationWiseVO.setFemaleVoters(commonMethodsUtilService.getLongValueForObject(param[3]));
					}
					locationWiseVO.setTotalVoters(locationWiseVO.getTotalVoters()+commonMethodsUtilService.getLongValueForObject(param[3]));
					locationIdsMap.put(locationId, locationWiseVO);
				}else{
					LocationVotersVO locationWiseVO = locationIdsMap.get(commonMethodsUtilService.getLongValueForObject(param[0]));
					if(commonMethodsUtilService.getStringValueForObject(param[2]).equalsIgnoreCase("m")){
						locationWiseVO.setMaleVoters(commonMethodsUtilService.getLongValueForObject(param[3]));
					}else{
						locationWiseVO.setFemaleVoters(commonMethodsUtilService.getLongValueForObject(param[3]));
					}
					locationWiseVO.setTotalVoters(locationWiseVO.getTotalVoters()+commonMethodsUtilService.getLongValueForObject(param[3]));
				}
			}
		}
		
		
		if (locationIdsMap != null && locationIdsMap.size() > 0) {
			Long totalCadres = 0l, maleTotalCadres = 0l, femaleTotalCadres = 0l;
			Long totalVoters= 0l, maleTotalVoters=0l,femaleTotalVoters=0l;
			
			for (Entry<Long, LocationVotersVO> entry : locationIdsMap.entrySet()) {
				
				totalCadres = totalCadres + entry.getValue().getMaleCadres() + entry.getValue().getFemaleCadres();
				maleTotalCadres = maleTotalCadres + entry.getValue().getMaleCadres();
				femaleTotalCadres = femaleTotalCadres + entry.getValue().getFemaleCadres();
				
				//voters
				totalVoters = totalVoters + entry.getValue().getMaleVoters() + entry.getValue().getFemaleVoters();
				maleTotalVoters = maleTotalVoters + entry.getValue().getMaleVoters();
				femaleTotalVoters = femaleTotalVoters + entry.getValue().getFemaleVoters();
			}

			for (Entry<Long, LocationVotersVO> entry : locationIdsMap.entrySet()) {
				if (totalCadres > 0l)
					entry.getValue().setTotalCadrePerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(Double.valueOf((((float)entry.getValue().getTotalCadres() * 100) / (float)totalCadres))) + "%");
				if (maleTotalCadres > 0l)
					entry.getValue().setMaleCadrePerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(Double.valueOf((((float)entry.getValue().getMaleCadres() * 100) / (float)maleTotalCadres))) + "%");
				if (femaleTotalCadres > 0l)
					entry.getValue().setFemaleCadrePerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(Double.valueOf((((float)entry.getValue().getFemaleCadres() * 100) / (float)femaleTotalCadres))) + "%");

				if (totalVoters > 0l)
					entry.getValue().setTotalVotersPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(Double.valueOf((((float)entry.getValue().getTotalVoters() * 100) / (float)totalVoters))) + "%");
				if (maleTotalVoters > 0l)
					entry.getValue().setMaleVotersPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(Double.valueOf((((float)entry.getValue().getMaleVoters() * 100) / (float)maleTotalVoters))) + "%");
				if (femaleTotalVoters > 0l)
					entry.getValue().setFemaleVotersPerc(commonMethodsUtilService.percentageMergeintoTwoDecimalPlaces(Double.valueOf((((float)entry.getValue().getFemaleVoters() * 100) / (float)femaleTotalVoters))) + "%");
			}
		}
		finalLsit.addAll(locationIdsMap.values());
		}catch (Exception e) {
			LOG.error("Exception raised at getLocationWiseVoterNCadreCounts ", e);
		}
		return finalLsit;
	}
	
}
