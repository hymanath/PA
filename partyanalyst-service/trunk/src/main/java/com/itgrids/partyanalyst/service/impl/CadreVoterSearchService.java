package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreVoterSearchService;
import com.itgrids.partyanalyst.utils.IConstants;



public class CadreVoterSearchService implements ICadreVoterSearchService{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private ITdpCadreInfoDAO tdpCadreInfoDAO;
	private ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO;
	private ICadreDetailsService cadreDetailsService;
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;
	private ITehsilDAO tehsilDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	
	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public void setTehsilDAO(ITehsilDAO tehsilDAO) {
		this.tehsilDAO = tehsilDAO;
	}

	public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
		this.localElectionBodyDAO = localElectionBodyDAO;
	}

	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	
	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setTdpCadreCasteInfoDAO(ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO) {
		this.tdpCadreCasteInfoDAO = tdpCadreCasteInfoDAO;
	}
	
	public void setTdpCadreDAO(ITdpCadreDAO tdpCadreDAO) {
		this.tdpCadreDAO = tdpCadreDAO;
	}
	
	public void setTdpCadreInfoDAO(ITdpCadreInfoDAO tdpCadreInfoDAO) {
		this.tdpCadreInfoDAO = tdpCadreInfoDAO;
	}
	
	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}


	public List<LocationWiseBoothDetailsVO> getAllDistrictsAndConstis(String type,Long id){	
		 
		List<LocationWiseBoothDetailsVO> returnList = new ArrayList<LocationWiseBoothDetailsVO>();
		try {
			List<Object[]> list = new ArrayList<Object[]>();
			if(type.equalsIgnoreCase(IConstants.DISTRICT)){
				 list = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1l, id);
			}
			else{
				 list = constituencyDAO.getDistrictConstituencies(id);	
			}
						
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					LocationWiseBoothDetailsVO vo = new LocationWiseBoothDetailsVO();
					vo.setLocationId(Long.valueOf(obj[0].toString()));
					vo.setLocationName(obj[1].toString());				
					returnList.add(vo);
				}
			}
		  }catch(Exception e){
			  LOG.error("Exception Occured in getAllDistrictsAndConstis()", e);
		  }
		 return  returnList;
	  } 
	
	public List<TdpCadreVO> getCadreVoterDetailsBySearchCriteria(String searchType,Long stateId, String locationType,Long locationId,Long casteStateId, String nameStr,String isFinal)
	{
		List<TdpCadreVO> returnList =  new ArrayList<TdpCadreVO>();
		try {
			List<Object[]> tdpCadreDetails = null;
			List<Object[]> wardOrMuncipalityList = null;
			List<Long> muncipalityORCorprationIdsList = new ArrayList<Long>();
			String areaType = null;

			List<Long> locationIdsList = new ArrayList<Long>();
			if(locationId != null && locationId.longValue() != 0L)
				locationIdsList.add(locationId);
			
			TdpCadreVO returnVO = new TdpCadreVO();
			
			
			if(isFinal == null || isFinal.toString().trim().length() ==0) //search for location wise count
			{
				
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
				{
					Constituency constituency = constituencyDAO.get(locationId.longValue());
					String constituencyType = constituency.getAreaType();
					
					if(constituencyType != null && ( constituencyType.equalsIgnoreCase(IConstants.RURALURBAN) || constituencyType.equalsIgnoreCase(IConstants.URBAN)))
					{
						List<Object[]> tehsilList = tehsilDAO.findTehsilsByConstituencyIdAndPublicationDateId(locationId.longValue(),IConstants.VOTER_DATA_PUBLICATION_ID);
						
						if(tehsilList != null && tehsilList.size()>0)
						{
							List<Long> tehsilIdsList = new ArrayList<Long>();
							for (Object[] tehsil : tehsilList) {
								Long tehsilId = tehsil[0] != null ? Long.valueOf(tehsil[0].toString().trim()):0L;							
								tehsilIdsList.add(tehsilId);
							}
							
							muncipalityORCorprationIdsList = localElectionBodyDAO.getMuncipalitiesAndCorporationsForConstituency(tehsilIdsList);
							areaType = IConstants.LOCAL_ELECTION_BODY;
						}
					}
				}
				else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.WARD)))
				{
					muncipalityORCorprationIdsList = constituencyDAO.getAllWardsByLocalElectionBodyIds(locationIdsList);	
					if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
					{
						locationType = null;
						areaType = IConstants.WARD;
					}
								
				}
				
				if(searchType !=null && (searchType.equalsIgnoreCase("Any") || searchType.equalsIgnoreCase("Cadre")))
				{
					if(nameStr == null || nameStr.trim().length() == 0)
					{
						if(casteStateId == null || casteStateId.longValue() == 0L)
						{
							if(locationType != null)
								tdpCadreDetails = tdpCadreInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId, locationType,locationIdsList);
							
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = tdpCadreInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList);
							}
						}
						else
						{
							tdpCadreDetails = tdpCadreCasteInfoDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationIdsList,casteStateId);
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = tdpCadreCasteInfoDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList,casteStateId);
							}
						}
					}				
					else 
					{
						tdpCadreDetails = tdpCadreDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationId,casteStateId,nameStr);
					}
					
					returnVO.setCadreSearchList(buildResult(tdpCadreDetails,wardOrMuncipalityList));
				}
				if(searchType !=null && (searchType.equalsIgnoreCase("Any") || searchType.equalsIgnoreCase("Voter")))
				{
					if(nameStr == null || nameStr.trim().length() == 0)
					{
						if(casteStateId == null || casteStateId.longValue() == 0L)
						{
							tdpCadreDetails = voterInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId, locationType,locationIdsList);
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = voterInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList);
							}							
						}
						else
						{
							tdpCadreDetails = userVoterDetailsDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationIdsList,casteStateId);
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = userVoterDetailsDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList,casteStateId);
							}
						}
					}				
					else 
					{
						tdpCadreDetails = boothPublicationVoterDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationId,casteStateId,nameStr);
					}
				
					returnVO.setVoterSearchList(buildResult(tdpCadreDetails,wardOrMuncipalityList));
				}				
				
			}
			else // final results
			{
				
				Long locationLevel = 0L;
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
    			{
					locationLevel = 4L;
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
    			{
					locationLevel = 5L;
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
    			{
					locationLevel = 6L;
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCAL_ELECTION_BODY))
    			{
					locationLevel = 7L;
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
    			{
					locationLevel = 8L;
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
    			{
					locationLevel = 9L;
    			}
				
				if(searchType.equalsIgnoreCase("Any") || (searchType !=null && searchType.equalsIgnoreCase("Cadre")))
				{
					TdpCadreVO tdpCadreVO= cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationId, nameStr,null,null, null, 
																												null,casteStateId,null,null,null,null,null);
					if(tdpCadreVO != null)
					{
						List<TdpCadreVO> tdpCadreVOList = tdpCadreVO.getTdpCadreDetailsList();
						
						if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
						{
							List<TdpCadreVO> cadresList = new ArrayList<TdpCadreVO>();
							for (TdpCadreVO voterVO : tdpCadreVOList) {
								
								TdpCadreVO vo = getMatchedTdpCadreVOByCasteName(cadresList,voterVO.getCasteName().trim());
								if(vo != null)
								{
									vo.getVoterSearchList().add(voterVO);
								}
								else
								{
									vo = new TdpCadreVO();
									vo.setCasteName(voterVO.getCasteName().trim());
									vo.getVoterSearchList().add(voterVO);
									cadresList.add(vo);
								}
							}
							
							if(cadresList != null && cadresList.size()>0)
							{
								returnVO.setCadreSearchList(cadresList);
							}
						}						
					}
				}
				if(searchType.equalsIgnoreCase("Any")  || (searchType !=null && searchType.equalsIgnoreCase("Voter")))
				{
					List<Object[]> votersList = boothPublicationVoterDAO.getVoterCadreCasteDetailsByName(stateId, locationType, locationId, casteStateId, nameStr);
					if(votersList != null && votersList.size()>0)
					{
						List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>();
						
						for (Object[] voter : votersList) {
							String name = voter[0] != null ? voter[0].toString().trim():"";
							String relativeName = voter[1] != null ? voter[1].toString().trim():"";
							String gender = voter[2] != null ? voter[2].toString().trim():"";
							String age = voter[3] != null ? voter[3].toString().trim():"";
							String tehsil = voter[4] != null ? voter[4].toString().trim():"";
							String constituency = voter[5] != null ? voter[5].toString().trim():"";
							String localBody = voter[6] != null ? voter[6].toString().trim():"";	
							String district = voter[7] != null ? voter[7].toString().trim():"";
							String panchayat = voter[8] != null ? voter[8].toString().trim():"";
							String casteName = voter[9] != null ? voter[9].toString().trim():"";
							String partNo = voter[10] != null ? voter[10].toString().trim():"";
							Long constituencyId = voter[11] != null ? Long.valueOf(voter[11].toString().trim()):0L;
							String imagePath = voter[12] != null ? voter[12].toString().trim():"";
							
							//vo.setImage(voter[7]!=null ?voterPath+voter[8].toString().trim()+"/"+voter[7].toString().trim()+".jpg":"");
							
							String voterPath="voter_images/"+constituencyId+"/Part"+partNo+"/"+imagePath.trim()+".jpg";
							
							TdpCadreVO vo = new TdpCadreVO();
							vo.setCadreName(name);
							vo.setRelativeName(relativeName);
							vo.setGender(gender);
							vo.setAge(Long.valueOf(age.toString()));
							vo.setTehsil(tehsil);
							vo.setLocalElectionBody(localBody);
							vo.setConstituency(constituency);
							vo.setDistrict(district);
							vo.setPanchayat(panchayat);
							vo.setCasteName(casteName);
							vo.setImageURL(voterPath);
							tdpCadreVOList.add(vo);
							
							
						}
						
						if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
						{
							List<TdpCadreVO> voterList = new ArrayList<TdpCadreVO>();
							for (TdpCadreVO voterVO : tdpCadreVOList) {
								
								TdpCadreVO vo = getMatchedTdpCadreVOByCasteName(voterList,voterVO.getCasteName().trim());
								if(vo != null)
								{
									vo.getVoterSearchList().add(voterVO);
								}
								else
								{
									vo = new TdpCadreVO();
									vo.setCasteName(voterVO.getCasteName().trim());
									vo.getVoterSearchList().add(voterVO);
									voterList.add(vo);
								}
							}
							
							if(voterList != null && voterList.size() >0)
							{
								returnVO.setVoterSearchList(voterList);
							}
						}
					}
				}
			}
			
			if(returnList != null && returnList.size() ==0)
			{
				returnList.add(returnVO);
			}
			
		} catch (Exception e) {
			returnList = null;
			LOG.error("Exception occured in getCadreVoterDetailsBySearchCriteria() in CadreVoterSearchService ",e);
		}
		
		return returnList;
	}
	
	public TdpCadreVO getMatchedTdpCadreVOByCasteName(List<TdpCadreVO> tdpCadreVOList,String casteName)
	{
		TdpCadreVO returnVO = null;
		try {
				if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
				{
					for (TdpCadreVO tdpCadreVO : tdpCadreVOList) 
					{
						if(tdpCadreVO.getCasteName().trim().equalsIgnoreCase(casteName.trim()))
						{
							return tdpCadreVO;
						}
					}
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnVO;
	}
	public List<TdpCadreVO> buildResult(List<Object[]> tdpCadreDetails,List<Object[]> wardOrMuncipalityList)
	{
		List<TdpCadreVO>  returnList = new ArrayList<TdpCadreVO>();
		
		try {
			if(tdpCadreDetails != null && tdpCadreDetails.size()>0)
			{
				for (Object[] candidate : tdpCadreDetails) 
				{
					TdpCadreVO candidateVO = new TdpCadreVO();
					Long id = candidate[0] != null ? Long.valueOf(candidate[0].toString().trim()):0L;
					String name = candidate[2] != null ? candidate[2].toString().trim():"";
					Long count =  candidate[1] != null ? Long.valueOf(candidate[1].toString().trim()):0L;
					
					candidateVO.setConstituencyId(id);
					candidateVO.setConstituency(name);
					candidateVO.setTotalCount(count);
					
					returnList.add(candidateVO);
				}
			}
			
			if(wardOrMuncipalityList != null && wardOrMuncipalityList.size()>0)
			{
				for (Object[] candidate : wardOrMuncipalityList) 
				{
					TdpCadreVO candidateVO = new TdpCadreVO();
					Long id = candidate[0] != null ? Long.valueOf(candidate[0].toString().trim()):0L;
					String name = candidate[2] != null ? candidate[2].toString().trim():"";
					Long count =  candidate[1] != null ? Long.valueOf(candidate[1].toString().trim()):0L;
					LocalElectionBody localElectionBody = null;
					Constituency constituency = null;
					try {
						localElectionBody = localElectionBodyDAO.get(id);
						if(localElectionBody != null)
							candidateVO.setConstituency(name+" "+localElectionBody.getElectionType().getElectionType().toLowerCase());
					} catch (Exception e) {
						constituency = constituencyDAO.get(id);
						if(constituency != null)
						{
							localElectionBody = localElectionBodyDAO.get(constituency.getLocalElectionBody().getLocalElectionBodyId());
							candidateVO.setConstituency(name+" ("+localElectionBody.getName()+" "+localElectionBody.getElectionType().getElectionType().toLowerCase()+")");
						}
					}
					
					candidateVO.setConstituencyId(id);
					
					
					 
					candidateVO.setResponseCode("1");
					candidateVO.setTotalCount(count);
					
					returnList.add(candidateVO);
				}
			}
		} catch (Exception e) {
			LOG.error("Exception occured in buildResult() in CadreVoterSearchService ",e);
		}
		return returnList;
	}
}
