package com.itgrids.core.api.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.util.Log;

import com.itgrids.core.api.service.ILocationWiseCasteInfoService;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreEnrollmentYearDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dto.LocationVotersVO;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;

public class LocationWiseCasteInfoService implements ILocationWiseCasteInfoService {
	
	private final static Logger LOG = Logger.getLogger(LocationDashboardService.class);
	private IConstituencyDAO constituencyDAO;
	private CommonMethodsUtilService commonMethodsUtilService;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO;
	private ICasteDAO casteDAO;
	

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

	/**
	 *VotersInformation for all or groupwise
	 *
	 **/
	@Override
	public List<LocationVotersVO> getVotersAndCadreCasteWiseCount(Long locationTypeId, Long locationValue, Long publicationDateId,
			Long EnrollmentYearId,Long casteGroupId) {
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
			
			Map<Long, LocationVotersVO> casteMap= new HashMap<Long, LocationVotersVO>();
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCasteWiseCount(constituencyIds, publicationDateId, reportLevelId,casteGroupId);

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
				}
			}
			
			/// o-castegrpId,1-casteId,3-gender,4-count
			List<Object[]> objList = tdpCadreCasteInfoDAO.getCasteNGenderWiseCadreCounts(locationTypeId,locationValue,EnrollmentYearId,casteGroupId);
			
			for (Object[] param : objList) {
				LocationVotersVO casteVO=casteMap.get(commonMethodsUtilService.getLongValueForObject(param[1]));
				if(casteVO!=null){
					if (param[2].toString().equalsIgnoreCase("M")) {
						casteVO.setMaleCadres(casteVO.getMaleCadres()+(Long) param[3]);
					} else if (param[2].toString().equalsIgnoreCase("F")) {
						casteVO.setFemaleCadres(casteVO.getFemaleCadres()+(Long) param[3]);
					}
					casteVO.setTotalCadres(casteVO.getTotalCadres()+(Long) param[3]);
				}
			}
			
			for (LocationVotersVO entry : casteMap.values()) {
				voList.add(entry);
			}
			
			Collections.sort(voList, new Comparator<LocationVotersVO>() {
			    public int compare(LocationVotersVO one, LocationVotersVO other) {
			        return other.getTotalVoters().compareTo(one.getTotalVoters());
			    }
			}); 
			
			// calculating totals and %'s
			if (voList != null && voList.size() > 0) {
				for (LocationVotersVO casteVO : voList) {
					if(casteVO.getTotalVoters() !=0l ){
						casteVO.setMaleVotersPerc(((casteVO.getMaleVoters() * 100) / casteVO.getTotalVoters()) + "%");
						casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters() * 100) / casteVO.getTotalVoters()) + "%");
						casteVO.setMaleCadrePerc(((casteVO.getMaleVoters() * 100) / casteVO.getTotalVoters()) + "%");
						casteVO.setFemaleVotersPerc(((casteVO.getFemaleVoters() * 100) / casteVO.getTotalVoters()) + "%");
					}
				}
			}

		} catch (Exception e) {
			LOG.error("Exception raised at getCasteGroupNAgeWiseVoterNCadreCounts", e);
		}
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
	
	public List<LocationVotersVO> getVotersCastGroupWiseCount(Long locationTypeId,Long locationValue,Long publicationDateId,Long enrollmentId){
		
		try{
			List<LocationVotersVO>  listVo = new ArrayList<LocationVotersVO>();
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
			List<Object[]> votersObjList = voterCastInfoDAO.getVotersCastGroupWiseCount(constituencyIds, publicationDateId, reportLevelId);
			if(votersObjList!=null){
				for (Object[] objects : votersObjList) {
					LocationVotersVO vo = new LocationVotersVO();
					vo.setAgeRangeId(commonMethodsUtilService.getLongValueForObject(objects[0]));
					vo.setAgeRange(commonMethodsUtilService.getStringValueForObject(objects[1]));
					vo.setTotalVoters(commonMethodsUtilService.getLongValueForObject(objects[2]));
				//	vo.setTotalCadres(commonMethodsUtilService.getLongValueForObject(objects[0]));
					listVo.add(vo);
				}
			}
			// 0-castegroupId,1-castegroup,2-casteId,3-castegroup,4-voterscount,5-percentage,6-maleVotersCount,7-femaleVotersCount
			List<Object[]> cadresObjList = tdpCadreCasteInfoDAO.getCasteGroupWiseCadreCounts(locationTypeId,locationValue,enrollmentId);
			
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
				}
			}
			return listVo;
		}catch(Exception e){
			Log.error("Exception raised at getVotersCastGroupWiseCount", e);
			return null;
		}
		
		
	}

}
