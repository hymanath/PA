package com.itgrids.partyanalyst.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.ICasteDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.PartyPositionVO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.service.ICasteReportService;
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

	public List<CastVO> getCasteWiseInfo(Long constituencyId,Long publicationId,String type,Long userId)

	{
		List<CastVO> resultList = new ArrayList<CastVO>();
		
		try{
			if(type.equalsIgnoreCase(IConstants.BOOTH))
			{
				getCasteWiseInfoForBooth1(resultList,constituencyId, publicationId, type, userId);
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
				 sortlocationsList(locationsList);
				 resultVo.setCasteList(locationsList);
				 resultList.add(resultVo); 
				 resultList.get(0).setPartyName(constituencyDAO.get(constituencyId).getName());
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
	 
	 public List<CastVO> getCasteWiseInfoForBooth1(List<CastVO> resultList,Long constituencyId,Long publicationId,String type,Long userId)

		{
			
			
				try{
					Map<Long,Map<Long,Map<Long,CastVO>>> resulMap = new HashMap<Long, Map<Long,Map<Long,CastVO>>>();
					List<Object[]> casteList = userVoterDetailsDAO.getCasteReport(constituencyId,publicationId,type,userId);
				
					 if(casteList != null && casteList.size() > 0)
					 {
						  for(Object[] params : casteList)
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
					 for(Long panchayat : resulMap.keySet())
					 {
						CastVO resultVo = new CastVO();
						resultVo.setPanchayatId(panchayat);
						resultVo.setPanchayat(panchayatDAO.getPanchayatNameById(panchayat));
						Map<Long,Map<Long,CastVO>> casteMap = resulMap.get(panchayat);
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
							;
							 for(Long locationId : locationIds)
							 {
								 
								 CastVO locationvo = new CastVO();
								 locationvo.setLocationId(locationId);
								 locationvo.setLocationName(boothDAO.getBoothPartNoByBoothId(locationId));
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
					 
					 for(Object[] params2 : casteList)
					 {
						 setValuesForaCasteInBooth(resultList,(Long)params2[2],(Long)params2[3],(Long)params2[0],(Long)params2[5]);
						 
					 }
					
				
			}
			catch(Exception e)
			{
			log.error("Exception Occured in getCasteWiseInfo() method in CasteReportService",e);	
			}
				sortPanchayat(resultList);
			return resultList;
		}

 }

