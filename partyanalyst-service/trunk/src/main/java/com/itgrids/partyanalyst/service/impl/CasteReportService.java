package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.PartyTrendsVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.PartyTrends;
import com.itgrids.partyanalyst.service.ICasteReportService;
import com.itgrids.partyanalyst.service.ISuggestiveModelService;
import com.itgrids.partyanalyst.utils.IConstants;



public class CasteReportService implements ICasteReportService{

	
	private static final Logger log = Logger.getLogger(CasteReportService.class);
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private ITehsilDAO tehsilDAO;
	private ICasteDAO casteDAO;
	private IPanchayatDAO panchayatDAO;
	private IBoothDAO boothDAO;
	private IConstituencyDAO constituencyDAO;
	private IUserDAO userDAO;
	private IPanchayatHamletDAO panchayatHamletDAO ;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IHamletDAO hamletDAO;
	@Autowired
	private IPartyTrendsDAO partyTrendsDAO;
	@Autowired
	private TransactionTemplate transactionTemplate;
    private ISuggestiveModelService suggestiveModelService;
	
	public IPartyTrendsDAO getPartyTrendsDAO() {
		return partyTrendsDAO;
	}

	public void setPartyTrendsDAO(IPartyTrendsDAO partyTrendsDAO) {
		this.partyTrendsDAO = partyTrendsDAO;
	}
	public IHamletDAO getHamletDAO() {
		return hamletDAO;
	}

	public void setHamletDAO(IHamletDAO hamletDAO) {
		this.hamletDAO = hamletDAO;
	}

	public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
		return localElectionBodyDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
	}

	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IPanchayatDAO getPanchayatDAO() {
		return panchayatDAO;
	}

	public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
		this.panchayatDAO = panchayatDAO;
	}

	public IBoothDAO getBoothDAO() {
		return boothDAO;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public ICasteDAO getCasteDAO() {
		return casteDAO;
	}

	public void setCasteDAO(ICasteDAO casteDAO) {
		this.casteDAO = casteDAO;
	}

	public ITehsilDAO getTehsilDAO() {
		return tehsilDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public ISuggestiveModelService getSuggestiveModelService() {
		return suggestiveModelService;
	}

	public void setSuggestiveModelService(
			ISuggestiveModelService suggestiveModelService) {
		this.suggestiveModelService = suggestiveModelService;
	}

	public List<CastVO> getCasteWiseInfo(Long constituencyId,Long publicationId,String type,Long userId,String partialChecked)

	{
		List<CastVO> resultList = new ArrayList<CastVO>();
		List<CastVO> result = new ArrayList<CastVO>();
		
		try{
			if(type.equalsIgnoreCase(IConstants.BOOTH) || type.equalsIgnoreCase(IConstants.HAMLET) || type.equalsIgnoreCase(IConstants.PANCHAYAT))
			{
				result =  getCasteWiseInfoForBooth1(resultList,constituencyId, publicationId, type, userId,partialChecked);
				return result;
			}
			else
			{
			Map<Long,Map<String,CastVO>>  resultMap = new HashMap<Long, Map<String,CastVO>>();
			List<Object[]> casteList = userVoterDetailsDAO.getCasteReport(constituencyId,publicationId,type,userId);
			
			
			List<Long> locationIds = new ArrayList<Long>();
			List<Long> casteStateIds = new ArrayList<Long>();
			 if(casteList != null && casteList.size() > 0)
			 {
				  for(Object[] params : casteList)
				 {
					 if(!locationIds.contains((Long)params[3]))
						 locationIds.add((Long)params[3]);
					 if(!casteStateIds.contains((Long)params[2]))
						 casteStateIds.add((Long)params[2]);
				 }
			}
			 List<Long> lclIds = new ArrayList<Long>();
				List<Long> lclCasteIds = new ArrayList<Long>();
				List<Object[]> localbody = null;
			 if(type.equalsIgnoreCase(IConstants.MANDAL))
				{
				 localbody = userVoterDetailsDAO.getLocalbodyCasteReport(constituencyId,publicationId,userId);
				
				for(Object[] lclbody : localbody)
				{
					 if(!lclIds.contains((Long)lclbody[3]) && !locationIds.contains((Long)lclbody[3]))
						 lclIds.add((Long)lclbody[3]);
					 if(!casteStateIds.contains((Long)lclbody[2]) && !lclCasteIds.contains((Long)lclbody[2]))
						 lclCasteIds.add((Long)lclbody[2]);
				}
			
				}
			if(lclCasteIds != null && lclCasteIds.size() > 0)
			 casteStateIds.addAll(lclCasteIds);
			 for(Long casteId : casteStateIds)
			 {
				 CastVO resultVo = new CastVO(); 
				 List<CastVO> locationsList = new ArrayList<CastVO>();
				 resultVo.setCastStateId(casteId);
				 resultVo.setCastName(casteDAO.get(casteId).getCasteName());
				 
				 for(Long locationId : locationIds)
				 {
					 CastVO locationVo = new CastVO();
					 locationVo.setLocationId(locationId);
					 if(type.equalsIgnoreCase(IConstants.MANDAL))
					 locationVo.setLocationName(tehsilDAO.getTehsilNameById(locationId));
					 else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
						 locationVo.setLocationName(panchayatDAO.getPanchayatNameById(locationId));
					     locationVo.setCastCount(0l);
					     locationsList.add(locationVo);	
				 }
				 if(type.equalsIgnoreCase(IConstants.MANDAL))
				 {
					 if(lclIds != null && lclIds.size() > 0)
					 {
						 for(Long lclId : lclIds)
						 {
							 CastVO lclbodyVo = new CastVO();
							 lclbodyVo.setLocationId(lclId);
						     lclbodyVo.setLocationName(localElectionBodyDAO.getLocalElectionBodyName(lclId) +" Muncipality");
							 lclbodyVo.setCastCount(0l);
							 locationsList.add(lclbodyVo);	
							
						 }
					 }
				 }
				 sortlocationsList(locationsList);
				 resultVo.setCasteList(locationsList);
				 resultList.add(resultVo); 
				 resultList.get(0).setPartyName(constituencyDAO.get(constituencyId).getName());
			 }
			 if(type.equalsIgnoreCase(IConstants.MANDAL))
			 {
			 for(Object[] lclbody : localbody)
			 {
				  setValuesForaCasteInLocation(resultList,(Long)lclbody[2],(Long)lclbody[3],(Long)lclbody[0]);
				 
			 }
			 }
			  for(Object[] params2 : casteList)
			 {
				  setValuesForaCasteInLocation(resultList,(Long)params2[2],(Long)params2[3],(Long)params2[0]);
				 
			 }
		}
			
		}
		catch(Exception e)
		{
		log.error("Exception Occured in getCasteWiseInfo() method in CasteReportService",e);	
		}
		
		return resultList;
	}
	 public List<CastVO> setValuesForaCasteInLocation(List<CastVO> resultList,Long casteId,Long locationId,Long count)
	  {
		 
		  try{
			  for(CastVO castVo : resultList)
			  {
				  if(castVo.getCastStateId().equals(casteId))
				  {
					  for(CastVO locationvo : castVo.getCasteList())
					  {
						  if(locationvo.getLocationId().equals(locationId))
						  {
							  locationvo.setCastCount(count);
						  }
					  }
				  }
			  }
		  }
		  catch(Exception e)
		  {
			  log.error("Exception Occured in setValuesForaCasteInLocation() method in CasteReportService",e);	  
		  }
		return resultList;
		
	  }
	
		public void sortlocationsList(List<CastVO> localitiesList)
		{
			 Collections.sort(localitiesList, new Comparator<CastVO>() {

					public int compare(CastVO arg0,
							CastVO arg1) {
						
						return arg0.getLocationName().trim().toUpperCase().compareTo(arg1.getLocationName().trim().toUpperCase());
					}
				});
		}
		public void sortCastsList(List<CastVO> localitiesList)
		{
			 Collections.sort(localitiesList, new Comparator<CastVO>() {

					public int compare(CastVO arg0,
							CastVO arg1) {
						
						return arg0.getCastName().trim().toUpperCase().compareTo(arg1.getCastName().trim().toUpperCase());
					}
				});
		}
		
		public void sortPanchayat(List<CastVO> panchayats)
		{
			 Collections.sort(panchayats, new Comparator<CastVO>() {

					public int compare(CastVO arg0,
							CastVO arg1) {
						
						return arg0.getPanchayat().trim().toUpperCase().compareTo(arg1.getPanchayat().trim().toUpperCase());
					}
				});
		}
		
 
	 public List<CastVO> setValuesForaCasteInBooth(List<CastVO> resultList,Long casteId,Long locationId,Long count,Long panchayatId)
	  {
		 
		  try{
			  for(CastVO panchayatvo : resultList)
			  {
				  if(panchayatvo.getPanchayatId().equals(panchayatId))
				  {
					  for(CastVO castvo : panchayatvo.getCasteList())
					  {
				  if(castvo.getCastStateId().equals(casteId))
				  {
					  for(CastVO locationvo : castvo.getCasteList())
					  {
						  if(locationvo.getLocationId().equals(locationId))
						  {
							  locationvo.setCastCount(count);
							
						  }
					  }
				  }
				 }
				  }
			  }
		  }
		  catch(Exception e)
		  {
			  log.error("Exception Occured in setValuesForaCasteInLocation() method in CasteReportService",e);	  
		  }
		return resultList;
		
	  }
	 
	 public List<CastVO> getCasteWiseInfoForBooth1(List<CastVO> resultList,Long constituencyId,Long publicationId,String type,Long userId,String partialChecked)

		{
		 		 List<Object[]> castesList = new ArrayList<Object[]>();
		         List<Object[]> casteList = null;
		         List<Object[]> casteList1 = null;
				try{
					Map<Long,Map<Long,Map<Long,CastVO>>> resulMap = new HashMap<Long, Map<Long,Map<Long,CastVO>>>();
					if(partialChecked.equalsIgnoreCase("true"))
					{
						List<Long> tehsilIds = userVoterDetailsDAO.getTehsils(constituencyId, publicationId);
						if(tehsilIds != null && tehsilIds.size() > 0)
						{
							for(Long tehsilId:tehsilIds){
								Set<String> partialIds  = new HashSet<String>();
								List<Object[]> partialBooth = userVoterDetailsDAO.getPartialPanchayats(tehsilId,constituencyId,publicationId);
								for(Object[] p:partialBooth){
									if(p[0] !=null){
										partialIds.add(p[0].toString());
									}
									if(p[1] !=null){
										partialIds.add(p[1].toString());
									}
								}
								String paids = "";
								for(String panchayat:partialIds){
									if(paids.length()==0)
										paids =panchayat;
									else
										paids =paids+","+panchayat;
								}
								if(paids != "")
								{
								 casteList = userVoterDetailsDAO.getCasteReportForPartial(constituencyId,publicationId,type,userId,paids,tehsilId);
								 casteList1 = userVoterDetailsDAO.getCasteReportForNotPartial(constituencyId,publicationId,type,userId,paids,tehsilId);
								}
								 if(casteList != null && casteList.size() > 0)
									 castesList.addAll(casteList);
									 if(casteList1 != null && casteList1.size() > 0)
										 castesList.addAll(casteList1);
									 }
							
					                }
		                           }
				   else
				   {
					   castesList = userVoterDetailsDAO.getCasteReport(constituencyId,publicationId,type,userId);
				   }
					 if(castesList != null && castesList.size() > 0)
					 {
						  for(Object[] params : castesList)
						 {
							   Map<Long,Map<Long,CastVO>> casteMap = resulMap.get((Long)params[5]);
							  if(casteMap == null)
							  {
								  casteMap = new HashMap<Long, Map<Long,CastVO>>();
								  resulMap.put((Long)params[5], casteMap);
							  }
							 
							  Map<Long,CastVO> locationMap = casteMap.get((Long)params[2]) ;
							  if(locationMap == null)
							  {
								  locationMap = new HashMap<Long, CastVO>();
								  casteMap.put((Long)params[2], locationMap);
							  }
							  CastVO vo = locationMap.get((Long)params[3]);
							  if(vo == null)
							  {
								  vo = new CastVO();
							      locationMap.put((Long)params[3], vo);
							  }
							  vo.setLocationName(params[6].toString());
							  vo.setCastCount((Long)params[0]);
					     }
					}
					 Map<Long,Long> totalCountMap = new HashMap<Long, Long>();
					 for(Long superLevel : resulMap.keySet())
					 {
						CastVO resultVo = new CastVO();
						resultVo.setPanchayatId(superLevel);
						if(type.equalsIgnoreCase(IConstants.BOOTH) || type.equalsIgnoreCase(IConstants.HAMLET))
						resultVo.setPanchayat(panchayatDAO.getPanchayatNameById(superLevel));
						else
							resultVo.setPanchayat(tehsilDAO.getTehsilNameById(superLevel));
						Map<Long,Map<Long,CastVO>> casteMap = resulMap.get(superLevel);
						List<Long> locationIds = new ArrayList<Long>();
						
						for(Long casteId : casteMap.keySet())
						 {
                             Map<Long,CastVO> locationMap = casteMap.get(casteId) ;
                             Long total = 0l;
     						
							 for(Long locationId : locationMap.keySet())
							 {
								 CastVO countvo =  locationMap.get(locationId);
								 total = total + countvo.getCastCount(); 
							     if(!locationIds.contains(locationId))
								 locationIds.add(locationId); 
							 }	
							 totalCountMap.put(casteId, total);
						 }
						List<CastVO> castes = new ArrayList<CastVO>();
						 for(Long casteId : casteMap.keySet())
						 {
							 CastVO castvo = new CastVO();
							 castvo.setCastStateId(casteId);
							 castvo.setCastName(casteDAO.get(casteId).getCasteName());
							 castvo.setTotalVoters(totalCountMap.get(casteId));
							 Map<Long,CastVO> locationMap = casteMap.get(casteId) ;
							 List<CastVO> locations = new ArrayList<CastVO>();
							 List<CastVO> locations1 = new ArrayList<CastVO>();
							
							 for(Long locationId : locationIds)
							 {
								 
								 CastVO locationvo = new CastVO();
								 locationvo.setLocationId(locationId);
								 if(type.equalsIgnoreCase(IConstants.BOOTH))
								 locationvo.setLocationName(boothDAO.getBoothPartNoByBoothId(locationId));
								 else if(type.equalsIgnoreCase(IConstants.HAMLET))
								 locationvo.setLocationName(hamletDAO.get(locationId).getHamletName()); 
								 else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
								 locationvo.setLocationName(panchayatDAO.getPanchayatNameById(locationId)); 	 
								 locationvo.setCastCount(0l);
								 locations.add(locationvo); 
							
							 }	
							 
							 sortlocationsList(locations);
							 castvo.setCasteList(locations);
						     castes.add(castvo); 
						 }
					    sortCastsList(castes);
						resultVo.setCasteList(castes);
						resultList.add(resultVo);
						resultList.get(0).setPartyName(constituencyDAO.get(constituencyId).getName());
					 }
					 
					 for(Object[] params2 : castesList)
					 {
						 setValuesForaCasteInBooth(resultList,(Long)params2[2],(Long)params2[3],(Long)params2[0],(Long)params2[5]);
						 
					 }
					
				
			}
			catch(Exception e)
			{
			log.error("Exception Occured in getCasteWiseInfo() method in CasteReportService",e);	
			}
				sortPanchayat(resultList);
				List<CastVO> result = new ArrayList<CastVO>();
				result = setDataList(resultList);
				
			return result;
			
		}
	
	 
 public List<CastVO> setDataList(List<CastVO> resultList)
	 {
	 List<CastVO> result = new ArrayList<CastVO>();
		 try{
			 String constituencyName =resultList.get(0).getPartyName();
			for(CastVO vo : resultList)
			{
			List<CastVO> casteList = vo.getCasteList();
			int locationsSize =  casteList.get(0).getCasteList().size();
			int maxindex= 0;
			for(int i=0;i<locationsSize;i=i+3)
			{
				
				CastVO resultVo = new CastVO();
				resultVo.setPanchayatId(vo.getPanchayatId());
				resultVo.setPanchayat(vo.getPanchayat());
				List<CastVO> castList = new ArrayList<CastVO>();
			    for(CastVO cast : casteList)
				{
					CastVO castvo = new CastVO();
					castvo.setCastStateId(cast.getCastStateId());
					castvo.setCastName(cast.getCastName());
					
					List<CastVO> locationList = new ArrayList<CastVO>();
					Long total = 0l;
					for(CastVO location : cast.getCasteList().subList(maxindex,  cast.getCasteList().size()- 0))
					{
						
						CastVO locationvo = new CastVO();
						locationvo.setLocationId(location.getLocationId());
						locationvo.setLocationName(location.getLocationName());
						locationvo.setCastCount(location.getCastCount());
						locationList.add(locationvo);
						total = location.getCastCount() + total;
						if(locationList.size() >= 3)
						{
						  break;
						}
						
					}
					castvo.setTotalVoters(total);
					castvo.setCasteList(locationList);
					castList.add(castvo);
				}
			    if(locationsSize >= 3)
				maxindex = maxindex + 3;
			    resultVo.setCasteList(castList);
			    result.add(resultVo);	
			    result.get(0).setPartyName(constituencyName);
			}
			
			}
		 }
		 catch(Exception e)
		 {
			 log.error("Exception Occured in createNewLocationList() method in CasteReportService",e);		 
		 }
		return result;
		
	 }
	 public ResultStatus getVoterAddressDetails (Long constituencyId,Long publicationId,Long userId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
			List<Object[]> boothHnos = userVoterDetailsDAO.getVoterHnoAndBooths(constituencyId,publicationId);
			 
			Map<Long,List<String>> boothHousesMap = new HashMap<Long, List<String>>();
		  	if(boothHnos !=null && boothHnos.size() > 0)
			{
		  		for(Object[] params : boothHnos)
				{
					List<String> hnos = boothHousesMap.get((Long)params[0]);
					if(hnos == null)
						hnos = new ArrayList<String>();
					
					hnos.add(params[1].toString());	
					boothHousesMap.put((Long)params[0], hnos);
				}
			}
		  	
		  	List<Long> voterIds = new ArrayList<Long>();
		  
		  	List<VoterHouseInfoVO> resultList = new ArrayList<VoterHouseInfoVO>();
		 
		  	List<Long> boothIds = new ArrayList<Long>(boothHousesMap.keySet());
		  	
		    for(Long boothId :boothHousesMap.keySet())
		    	{
		  		if(boothHousesMap.get(boothId) != null)
		  		{
		  		for(String hno : boothHousesMap.get(boothId))
		  			{
		  				List<Object[]>  list = userVoterDetailsDAO.getElderVoterDetails(boothId, hno);
		  				 if(list != null && list.size() > 0)
		  				{
		  					for(Object[] params : list)
		  					{
		  						
		  						VoterHouseInfoVO vo = new VoterHouseInfoVO();
		  						voterIds.add((Long)params[0]);
		  						vo.setVoterId((Long)params[0]);
		  						vo.setName(params[1]!= null?params[1].toString() : "");
		  						vo.setAge((Long)params[2]);
		  						vo.setGender(params[3]!= null?params[3].toString() : "");
		  						vo.setHouseNo(hno);
		  						vo.setBoothId(boothId);
		  						resultList.add(vo);
		  					}
		  					
		  				}
		  		}
			}
			}
		    createExcelForVoterAddress(resultList,voterIds,constituencyId,boothIds,userId);
		    resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
			 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 }
		  	
	 return resultStatus;
	 }
	 public void createExcelForVoterAddress(List<VoterHouseInfoVO> resultList ,List<Long> voterIds,Long constituencyId,List<Long> boothIds,Long userId)
	 
	 {
		try{
			Constituency constituency = constituencyDAO.get(constituencyId);
			String areaType = constituency.getAreaType();
			String districtName ="";
			if(constituency.getDistrict() != null)
			districtName = constituency.getDistrict().getDistrictName();
			Map<Long,String> voterCaste = new HashMap<Long, String>();
		 	Map<Long,String> tehsilMap = new HashMap<Long, String>();
		  	Map<Long,String> hamletMap = new HashMap<Long, String>();
		  	Map<Long,String> panchayatMap = new HashMap<Long, String>();
			
		  	List<Object[]> casteInfo =  userVoterDetailsDAO.getCasteForVoter(voterIds,userId);
		  	if(casteInfo != null && casteInfo.size() > 0)
			for(Object[] casteName : casteInfo)
			{
				BigInteger voterId =(BigInteger)casteName[0];
				String caste = voterCaste.get(voterId.longValue());
				if(caste == null)
				voterCaste.put(voterId.longValue(),casteName[1].toString());
				else
				voterCaste.put(voterId.longValue(),caste);	
			}
		  	List<Object[]> hamletInfo =  userVoterDetailsDAO.getHamletForVoter(voterIds,userId);
		  	if(hamletInfo != null && hamletInfo.size() > 0)
			for(Object[] hamlet : hamletInfo)
			{
				BigInteger voterId1 =(BigInteger)hamlet[0];
				String hamletName = voterCaste.get(voterId1.longValue());
				if(hamletName == null)
					hamletMap.put(voterId1.longValue(),hamlet[1].toString());
				else
					hamletMap.put(voterId1.longValue(),hamletName);	
			}
			List<Object[]> tehsils = userVoterDetailsDAO.getLocationForVoter(boothIds,IConstants.TEHSIL);
			if(tehsils != null && tehsils.size() > 0)
				for(Object[] tehsil : tehsils)
				{
					String mandal = tehsilMap.get((Long)tehsil[0]);
					if(mandal == null)
						tehsilMap.put((Long)tehsil[0],tehsil[1].toString());
					else
						tehsilMap.put((Long)tehsil[0],mandal);	
				}
			List<Object[]> panchayats = userVoterDetailsDAO.getLocationForVoter(boothIds,IConstants.PANCHAYAT);
			if(panchayats != null && panchayats.size() > 0)
				for(Object[] panchayat : panchayats)
				{
					String panchayatName = panchayatMap.get((Long)panchayat[0]);
					if(panchayatName == null)
						panchayatMap.put((Long)panchayat[0],panchayat[1].toString());
					else
						panchayatMap.put((Long)panchayat[0],panchayatName);	
				}
			
			/** excel header **/
		    HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Sample sheet");
			Row header = sheet.createRow(0);
		    header.createCell(0).setCellValue("SNO");
			header.createCell(1).setCellValue("Voter Name");
			header.createCell(2).setCellValue("Caste");
		    header.createCell(3).setCellValue("Age");
		    header.createCell(4).setCellValue("Gender");
		    header.createCell(5).setCellValue("House No");
		    if(areaType.equalsIgnoreCase(IConstants.RURAL) || areaType.equalsIgnoreCase(IConstants.RURALURBAN))
		    {
		    header.createCell(6).setCellValue("Hamlet Name");
		    header.createCell(7).setCellValue("Panchayat Name");
		    if(areaType.equalsIgnoreCase(IConstants.RURAL))
		    header.createCell(8).setCellValue("Mandal Name");
		    else if(areaType.equalsIgnoreCase(IConstants.RURALURBAN))
		     header.createCell(8).setCellValue("Mandal/Muncipality");
		    header.createCell(9).setCellValue("District Name");
		    }
		    else
		    {
		    header.createCell(6).setCellValue("Muncipality");		
		    header.createCell(7).setCellValue("District Name");
		    }
		    int rowNum = 0;
		    for(VoterHouseInfoVO voter : resultList)
		    {
		    	rowNum++;
		       Row dataRow = sheet.createRow(rowNum);
	    	   dataRow.createCell(0).setCellValue(rowNum);
	    	   dataRow.createCell(1).setCellValue(voter.getName());
	    	   dataRow.createCell(2).setCellValue(voterCaste.get(voter.getVoterId()));
	    	   dataRow.createCell(3).setCellValue(voter.getAge());
	    	   dataRow.createCell(4).setCellValue(voter.getGender());
	    	   dataRow.createCell(5).setCellValue(voter.getHouseNo());
	    	   if(areaType.equalsIgnoreCase(IConstants.RURAL) || areaType.equalsIgnoreCase(IConstants.RURALURBAN))
			    {
	    		   dataRow.createCell(6).setCellValue(hamletMap.get(voter.getVoterId()));
	    		   dataRow.createCell(7).setCellValue(panchayatMap.get(voter.getBoothId()));
			    if(areaType.equalsIgnoreCase(IConstants.RURAL))
			    	dataRow.createCell(8).setCellValue(tehsilMap.get(voter.getBoothId()));
			    else if(areaType.equalsIgnoreCase(IConstants.RURALURBAN))
			    {
			    	Booth booth = boothDAO.get(voter.getBoothId());
			    	String tehsil ="";
			    	String localbody ="";
			    	if (booth.getLocalBody() != null)
						localbody = booth.getLocalBody().getName();
					if(localbody != null && localbody != "")
						tehsil = localbody +" Muncipality";
					else
						tehsil = booth.getTehsil().getTehsilName();	
			    	dataRow.createCell(8).setCellValue(tehsil);
			    }
			    dataRow.createCell(9).setCellValue(districtName);
			    }
			    else
			    {
			    	Booth booth = boothDAO.get(voter.getBoothId());
			    	String tehsil ="";
			    	String localbody ="";
			    	if (booth.getLocalBody() != null)
						localbody = booth.getLocalBody().getName();
					String electionType =constituency.getElectionScope().getElectionType().getElectionType();
					
					if(localbody != null && localbody != "")
					{
						if(electionType.equalsIgnoreCase(IConstants.GHMC))
						tehsil = localbody +" Corporation";
						
					}
					else if(electionType.equalsIgnoreCase(IConstants.MUNCIPLE_ELECTION_TYPE))
					{
						tehsil = localbody +" Muncipality";
					}
			    	dataRow.createCell(6).setCellValue(tehsil);		
			    	dataRow.createCell(7).setCellValue(districtName);
			    }
	    	   try {
	    		   	String pathSeperator = System.getProperty(IConstants.FILE_SEPARATOR);
	    	        FileOutputStream out =
	    	                new FileOutputStream(new File(IConstants.STATIC_CONTENT_FOLDER_URL+"Reports"+pathSeperator+constituency.getName()+"_voterAddressExcel.xls"));
	    	        workbook.write(out);
	    	        out.close();
	    	        System.out.println("Excel written successfully..");
	    	         
	    	    } catch (FileNotFoundException e) {
	    	        e.printStackTrace();
	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    } 
		   }
		    
		}
		catch(Exception e)
		{
			log.error("Exception Occured in createExcelForVoterAddress()", e) ;
		}
	 }
	
	// @Override
	public <K,V>TreeMap<K,V> loadConstituenciesForReport() {
		 TreeMap<Long, String> map =new TreeMap<Long, String>();
	//	List<Object[]> obj=(List<Object[]>) partyTrendsDAO.loadConst();
		 List<Object[]> obj=(List<Object[]>) partyTrendsDAO.findAssemblyConstituenciesForSimaAndra(2L, 1l, Arrays.asList(new String[]{"RURAL","RURAL-URBAN"}),  Arrays.asList(new Long[]{1L,2L,3L,4L,5L,6L,7L,8L,9L,10L}));
		for (Object[] objects : obj) {
			map.put(Long.valueOf(objects[0].toString()), objects[1].toString());
			
		}
		return (TreeMap<K, V>) map ;
	}
	
	public ResultStatus  generateXL(List<Long> constIds) throws IOException {
		 Map<Long, List<PartyTrendsVO>> map =new HashMap<Long,List<PartyTrendsVO> >();
	    List<Long> constIdRemains=	 (List<Long>)partyTrendsDAO.loadConst(constIds);

	      new ArrayList<PartyTrendsVO>();
	    
	    if(constIdRemains != null && constIdRemains.size() > 0 ){
	    	
	    	//paVo=getPartyTrendsForConstituencies(constIdRemainsLeft);
	    	for(Long constiId:constIdRemains){
	    		List<Long> remIds= new ArrayList<Long>();
	    		remIds.add(constiId);
	    	final List<PartyTrendsVO> paVo = suggestiveModelService.calculateOrderOfPriorityForConstituency(null, remIds, null, null, null, null, null, null, null, null);
		    if(paVo != null && paVo.size() > 0){	
		    	try{
			    		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			    		  protected void doInTransactionWithoutResult(TransactionStatus status) 
			    		 {
			    			  for (PartyTrendsVO partyTrendsVO : paVo) {
								
			    				  partyTrendsDAO.save(getPartyTrends(partyTrendsVO));
							}
			    			  
			    		 }
			    		});
			    	
			    }catch (Exception e) {
			    	log.debug("exception occured while saving PartyTrends",e);
			    }
		    }
	    }
		}
	    
		List<Object[]> obj=(List<Object[]>) partyTrendsDAO.loadEntitiesForXl(constIds);
		
		for (Object[] objects : obj) {
			
			Long constId=Long.valueOf(objects[0].toString());
			PartyTrendsVO vo = new PartyTrendsVO();
			vo.setConstituencyId(constId);
			vo.setConstituencyName(objects[1].toString());
			vo.setName(objects[2].toString());
			vo.setPervTrenzWt(new BigDecimal(objects[3].toString()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			vo.setPrpWt(new BigDecimal(objects[4].toString()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			vo.setTotalWt(new BigDecimal(objects[6].toString()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			vo.setYoungVotersWt(new BigDecimal(objects[5].toString()).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue());
			if(map.containsKey(constId))
			{
				map.get(constId).add(vo);
			}else
			{
				List<PartyTrendsVO> l = new ArrayList<PartyTrendsVO>();
				l.add(vo);
				map.put(constId, l);
			}
		}
		String url = generateXL(map);
		ResultStatus s =new ResultStatus();
	    s.setResultCode(0);
	    s.setMessage(url);
		return s ;
	}
	public   PartyTrends getPartyTrends(PartyTrendsVO vo) {
		PartyTrends pt =new PartyTrends();
		
		pt.setConstituency(constituencyDAO.get(vo.getConstituencyId())) ;
	
		pt.setName(vo.getName());
		pt.setPervTrenzWt(vo.getPervTrenzWt()) ;
		pt.setPrpWt(vo.getPrpWt());
		pt.setYoungVotersWt(vo.getYoungVotersWt());
		pt.setTotalWt(vo.getTotalWt());
		
		return pt;
	}
   public String  generateXL(Map<Long,List<PartyTrendsVO>> map) throws IOException

   


   {
	   Random randomNum = new Random();
		String filename= "Strategy/"+"report"+randomNum.nextInt(10000000)+".xls";
	   FileOutputStream fileOut =    new FileOutputStream(IConstants.STATIC_CONTENT_FOLDER_URL+filename);
	   Set<Long> keys = map.keySet();
	    HSSFWorkbook workbook=new HSSFWorkbook();
	    HSSFSheet sheet =null;
	    
	  for (Long long1 : keys) {
		List<PartyTrendsVO> voa=  map.get(long1);
		
		   sheet =  workbook.createSheet(voa.get(0).getConstituencyName());
		    sheet.setColumnWidth(0, 4800);
			sheet.setColumnWidth(1, 4800);
			sheet.setColumnWidth(2, 3000);
			sheet.setColumnWidth(3, 3000);
			sheet.setColumnWidth(4, 3000);
			sheet.setColumnWidth(5, 3000);
		  int cnt=1;
		  HSSFRow rowhead =sheet.createRow(0);
		  Cell cell = rowhead.createCell(0);
			 
	       cell.setCellValue("Constituency");
	       
	       cell = rowhead.createCell(1);
	       cell.setCellValue("Panchayat");
	       cell = rowhead.createCell(2);
	       cell.setCellValue("P.T Weight%");
	       cell = rowhead.createCell(3);
	       cell.setCellValue("PRP Weight%");
	       cell = rowhead.createCell(4);
	       cell.setCellValue("Young Voters Weight%");
	       
	       cell = rowhead.createCell(5);
	       cell.setCellValue("Total Weight");
	       
		for (PartyTrendsVO partyTrendsVO : voa) {
			  rowhead =sheet.createRow(cnt);
			 
			   cell = rowhead.createCell(0);
			 
		       cell.setCellValue(partyTrendsVO.getConstituencyName());
		       
		       cell = rowhead.createCell(1);
		       cell.setCellValue(partyTrendsVO.getName());
		       cell = rowhead.createCell(2);
		       cell.setCellValue(partyTrendsVO.getPervTrenzWt());
		       cell = rowhead.createCell(3);
		       cell.setCellValue(partyTrendsVO.getPrpWt());
		       cell = rowhead.createCell(4);
		       cell.setCellValue(partyTrendsVO.getYoungVotersWt());
		       
		       cell = rowhead.createCell(5);
		       cell.setCellValue(partyTrendsVO.getTotalWt());
		       cnt++;
		}
	  }
	    workbook.write(fileOut);
		 fileOut.close(); 

		  
	return filename;
   }
	


}