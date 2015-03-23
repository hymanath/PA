package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
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


	public List<CadreAddressVO> getAllDistrictsAndConstis(String type,Long id){	
		 
		List<CadreAddressVO> returnList = new ArrayList<CadreAddressVO>();
		try {
			List<Object[]> list = new ArrayList<Object[]>();
			if(type.equalsIgnoreCase(IConstants.DISTRICT))
				 list = districtDAO.getDistrictIdAndNameByStateForStateTypeId(1l, id);
			else
				 list = constituencyDAO.getDistrictConstituencies(id);	
			
			if(list!=null && list.size()>0){
				for(Object[] obj:list){
					CadreAddressVO vo = new CadreAddressVO();
					vo.setId(Long.valueOf(obj[0].toString()));
					vo.setName(obj[1].toString());				
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
			
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.TEHSIL))
			{
				Constituency constituency = constituencyDAO.get(locationId.longValue());
				String constituencyType = constituency.getAreaType();
				
				if(constituencyType != null && constituencyType.equalsIgnoreCase(IConstants.RURALURBAN))
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
			else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.WARD) || locationType.equalsIgnoreCase(IConstants.PANCHAYAT)))
			{
				muncipalityORCorprationIdsList = constituencyDAO.getAllWardsByLocalElectionBodyIds(locationIdsList);	
				if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
				{
					locationType = IConstants.LOCAL_ELECTION_BODY;
					areaType = IConstants.WARD;
				}
							
			}
			
			if(isFinal == null || isFinal.toString().trim().length() ==0) //search for location wise count
			{
				if(searchType !=null && searchType.equalsIgnoreCase("Cadre"))
				{
					if(nameStr == null || nameStr.trim().length() == 0)
					{
						if(casteStateId == null || casteStateId.longValue() == 0L)
						{
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
				}
				else if(searchType !=null && searchType.equalsIgnoreCase("Voter"))
				{
					
				}				
				
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
						
						LocalElectionBody localElectionBody = localElectionBodyDAO.get(id);
						candidateVO.setConstituencyId(id);
						candidateVO.setConstituency(name+" "+localElectionBody.getElectionType().getElectionType().toLowerCase());
						candidateVO.setTotalCount(count);
						
						returnList.add(candidateVO);
					}
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
				
				if(searchType !=null && searchType.equalsIgnoreCase("Cadre"))
				{
					TdpCadreVO tdpCadreVO= cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationId, nameStr,null,null, null, 
																												null,casteStateId,null,null,null,null,null);
					if(tdpCadreVO != null)
					{
						List<TdpCadreVO> tdpCadreVOList = tdpCadreVO.getTdpCadreDetailsList();
						
						if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
						{
							returnList = new ArrayList<TdpCadreVO>();
							returnList.addAll(tdpCadreVOList);
						}						
					}
				}
				else if(searchType !=null && searchType.equalsIgnoreCase("Voter"))
				{
					
				}
			}
			
			
		} catch (Exception e) {
			returnList = null;
			LOG.error("Exception occured in getCadreVoterDetailsBySearchCriteria() in CadreVoterSearchService ",e);
		}
		
		return returnList;
	}
	
	
}
