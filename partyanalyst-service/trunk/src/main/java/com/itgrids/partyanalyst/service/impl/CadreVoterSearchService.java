package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dto.CadreAddressVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreVoterSearchService;
import com.itgrids.partyanalyst.utils.IConstants;



public class CadreVoterSearchService implements ICadreVoterSearchService{

	private static final Logger LOG = Logger.getLogger(CadreRegistrationService.class);
	private ITdpCadreDAO tdpCadreDAO;
	private ITdpCadreInfoDAO tdpCadreInfoDAO;
	private ITdpCadreCasteInfoDAO tdpCadreCasteInfoDAO;
	private ICadreDetailsService cadreDetailsService;
	
	public void setCadreDetailsService(ICadreDetailsService cadreDetailsService) {
		this.cadreDetailsService = cadreDetailsService;
	}
	
	private IDistrictDAO districtDAO;
	private IConstituencyDAO constituencyDAO;


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
		List<TdpCadreVO> returnList = null;
		try {
			List<Object[]> tdpCadreDetails = null;
			
			if(isFinal == null || isFinal.toString().trim().length() ==0) //search for location wise count
			{
				if(searchType !=null && searchType.equalsIgnoreCase("Cadre"))
				{
					if(nameStr == null || nameStr.trim().length() == 0)
					{
						if(casteStateId == null || casteStateId.longValue() == 0L)
						{
							tdpCadreDetails = tdpCadreInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId, locationType,locationId);
						}
						else
						{
							tdpCadreDetails = tdpCadreCasteInfoDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationId,casteStateId);
						}
					}				
					else 
					{
						tdpCadreDetails = tdpCadreDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationId,casteStateId,nameStr);
					}
				}
				else if(searchType !=null && searchType.equalsIgnoreCase("Voter"))
				{
					if(nameStr == null || nameStr.trim().length() == 0)
					{
						if(casteStateId == null || casteStateId.longValue() == 0L)
						{
							tdpCadreDetails = tdpCadreInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId, locationType,locationId);
						}
						else
						{
							tdpCadreDetails = tdpCadreCasteInfoDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationId,casteStateId);
						}
					}				
					else 
					{
						tdpCadreDetails = tdpCadreDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationId,casteStateId,nameStr);
					}
				}				
				
				if(tdpCadreDetails != null && tdpCadreDetails.size()>0)
				{
					returnList = new ArrayList<TdpCadreVO>();
					
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
