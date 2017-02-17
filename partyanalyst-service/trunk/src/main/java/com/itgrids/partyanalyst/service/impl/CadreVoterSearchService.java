package com.itgrids.partyanalyst.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.dao.ISmsOtpDetailsDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreCasteInfoDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreDAO;
import com.itgrids.partyanalyst.dao.ITdpCadreInfoDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dto.LocationWiseBoothDetailsVO;
import com.itgrids.partyanalyst.dto.TdpCadreVO;
import com.itgrids.partyanalyst.dto.VoterDetailsVO;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.SmsOtpDetails;
import com.itgrids.partyanalyst.model.TdpCadre;
import com.itgrids.partyanalyst.service.ICadreDetailsService;
import com.itgrids.partyanalyst.service.ICadreVoterSearchService;
import com.itgrids.partyanalyst.service.ISmsSenderService;
import com.itgrids.partyanalyst.utils.CommonMethodsUtilService;
import com.itgrids.partyanalyst.utils.DateUtilService;
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
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IMobileNumbersDAO mobileNumbersDAO;
	private IBoothDAO boothDAO;
	private ISmsSenderService smsSenderService;
	private ISmsOtpDetailsDAO smsOtpDetailsDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private CommonMethodsUtilService commonMethodsUtilService= new CommonMethodsUtilService();
	
	public DateUtilService getDateUtilService() {
		return dateUtilService;
	}

	public void setDateUtilService(DateUtilService dateUtilService) {
		this.dateUtilService = dateUtilService;
	}

	public ISmsOtpDetailsDAO getSmsOtpDetailsDAO() {
		return smsOtpDetailsDAO;
	}

	public void setSmsOtpDetailsDAO(ISmsOtpDetailsDAO smsOtpDetailsDAO) {
		this.smsOtpDetailsDAO = smsOtpDetailsDAO;
	}

	public ISmsSenderService getSmsSenderService() {
		return smsSenderService;
	}

	public void setSmsSenderService(ISmsSenderService smsSenderService) {
		this.smsSenderService = smsSenderService;
	}

	public void setBoothDAO(IBoothDAO boothDAO) {
		this.boothDAO = boothDAO;
	}

	public IMobileNumbersDAO getMobileNumbersDAO() {
		return mobileNumbersDAO;
	}

	public void setMobileNumbersDAO(IMobileNumbersDAO mobileNumbersDAO) {
		this.mobileNumbersDAO = mobileNumbersDAO;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

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
			else if(type.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				 list = constituencyDAO.getDistrictConstituencies(id);	
			}else if(type.equalsIgnoreCase("constituencyByState")){			
				 list = constituencyDAO.findConstituenciesByStateId(id);	
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
			String electionbodyName = "";
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
					
					if(constituencyType != null && ( constituencyType.equalsIgnoreCase(IConstants.RURALURBAN)))
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
					else if(constituencyType != null && constituencyType.equalsIgnoreCase(IConstants.URBAN))
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
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{	
								electionbodyName = localElectionBodyDAO.get(muncipalityORCorprationIdsList.get(0)).getElectionType().getElectionType();
								
								if(searchType !=null && (searchType.equalsIgnoreCase("Voter")))
								{
									return getCadreVoterWardDetailsBySearchCriteria(searchType,stateId,IConstants.CONSTITUENCY,locationId,casteStateId,nameStr,electionbodyName,"votersCount");
								}
								else if(searchType !=null && (searchType.equalsIgnoreCase("Cadre")))
								{
									return getCadreVoterWardDetailsBySearchCriteria(searchType,stateId,IConstants.CONSTITUENCY,locationId,casteStateId,nameStr,electionbodyName,"cadreCount");
								}
							}		
						}
					}
				}
				else if(locationType != null && (locationType.equalsIgnoreCase(IConstants.WARD)))
				{
					muncipalityORCorprationIdsList = constituencyDAO.getAllWardsByLocalElectionBodyIds(locationIdsList);	
					if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
					{	
						electionbodyName = localElectionBodyDAO.get(locationIdsList.get(0)).getElectionType().getElectionType();
						
						if(searchType !=null && (searchType.equalsIgnoreCase("Voter") || searchType.equalsIgnoreCase("Any")))
						{
							return getCadreVoterWardDetailsBySearchCriteria(searchType,stateId,IConstants.LOCAL_ELECTION_BODY,locationId,casteStateId,nameStr,electionbodyName,"votersCount");
						}
						else if(searchType !=null && (searchType.equalsIgnoreCase("Cadre") || searchType.equalsIgnoreCase("Any")))
						{
							return getCadreVoterWardDetailsBySearchCriteria(searchType,stateId,IConstants.LOCAL_ELECTION_BODY,locationId,casteStateId,nameStr,electionbodyName,"cadreCount");
						}
					}								
				}
								
				if(searchType !=null && searchType.equalsIgnoreCase("Cadre")  || searchType.equalsIgnoreCase("Any") )
				{
					if(nameStr == null || nameStr.trim().length() == 0)
					{
						if(casteStateId == null || casteStateId.longValue() == 0L)
						{
							if(locationType != null)
								tdpCadreDetails = tdpCadreInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId, locationType,locationIdsList,3L);//Srishilam  and Teja
							
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = tdpCadreInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList,3L);
							}
						}
						else
						{
							tdpCadreDetails = tdpCadreCasteInfoDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationIdsList,casteStateId,3L);
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = tdpCadreCasteInfoDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList,casteStateId,3L);
							}
						}
					}				
					else 
					{
						tdpCadreDetails = tdpCadreDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationIdsList,casteStateId,nameStr);
						if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
						{
							wardOrMuncipalityList = tdpCadreDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList,casteStateId,nameStr);
						}
					}
					
					if(electionbodyName.equalsIgnoreCase("Greater Municipal Corp"))
					{
						tdpCadreDetails = null;	
					}
					returnVO.setCadreSearchList(buildResult(tdpCadreDetails,wardOrMuncipalityList));
				}
				else if(searchType !=null && searchType.equalsIgnoreCase("Voter")  || searchType.equalsIgnoreCase("Any"))
				{
					if(nameStr == null || nameStr.trim().length() == 0)
					{
						if(casteStateId == null || casteStateId.longValue() == 0L)
						{
							if(!electionbodyName.equalsIgnoreCase("Greater Municipal Corp"))
							{
								tdpCadreDetails = voterInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId, locationType,locationIdsList);
							}
							
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = voterInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList);
							}							
						}
						else
						{
							if(!electionbodyName.equalsIgnoreCase("Greater Municipal Corp"))
							{
								tdpCadreDetails = voterCastInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId,locationType,locationIdsList,casteStateId);
							}
							if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
							{
								wardOrMuncipalityList = voterCastInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId,areaType,muncipalityORCorprationIdsList,casteStateId);
							}
						}
					}				
					else 
					{
						if(!electionbodyName.equalsIgnoreCase("Greater Municipal Corp"))
						{
							tdpCadreDetails = boothPublicationVoterDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationId,casteStateId,nameStr);
						}
						if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
						{
							wardOrMuncipalityList = boothPublicationVoterDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,muncipalityORCorprationIdsList.get(0),casteStateId,nameStr);
						}
					}
					
					returnVO.setVoterSearchList(buildResult(tdpCadreDetails,wardOrMuncipalityList));
				}
			}
			else // final results
			{
				
				Long locationLevel = 0L;
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.STATE))
    			{
					locationLevel = 3L;
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
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
					
					if(searchType !=null && searchType.equalsIgnoreCase("Cadre"))
					{
						muncipalityORCorprationIdsList = boothDAO.getLocalbodiesByConstituencyIds(locationIdsList,IConstants.VOTER_DATA_PUBLICATION_ID);	
						if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
						{	
							locationId = muncipalityORCorprationIdsList.get(0);
						}
					}
					else
					{
						locationLevel = 7L;
					}
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
    			{
					//locationLevel = 8L;
					if(searchType !=null && searchType.equalsIgnoreCase("Cadre"))
					{
						locationLevel = 7L;
					}
					else
					{
						locationLevel = 4L;
						locationType = IConstants.CONSTITUENCY;
					}
					
					/*muncipalityORCorprationIdsList = boothDAO.getLocalbodiesByConstituencyIds(locationIdsList,IConstants.VOTER_DATA_PUBLICATION_ID);	
					if(muncipalityORCorprationIdsList != null && muncipalityORCorprationIdsList.size()>0)
					{	
						locationId = muncipalityORCorprationIdsList.get(0);
					}*/
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
    			{
					locationLevel = 9L;
    			}
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.DISTRICT))
    			{
					locationLevel = 9L;
    			}
				if(searchType.equalsIgnoreCase("Any") || (searchType !=null && searchType.equalsIgnoreCase("Cadre")))
				{
					if(isFinal != null && isFinal.equalsIgnoreCase("cadreCount"))
					{
						TdpCadreVO tdpCadreVO= cadreDetailsService.tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte(locationLevel,locationId, nameStr,null,null, null, 
								null,casteStateId,null,null,null,null,null);
						
					/*	if(nameStr != null && nameStr.trim().length()>0)
						{
							tdpCadreVO= cadreDetailsService.tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte(locationLevel,locationId, nameStr,null,null, null, 
									null,casteStateId,null,null,null,null,null);
						}
						else
						{
							if(casteStateId != null && casteStateId.longValue()>0)
								tdpCadreDetails = tdpCadreCasteInfoDAO.getVoterCadreCasteDetailsBySearchCriteria(stateId,locationType,locationIdsList,casteStateId);
							else
								tdpCadreDetails = tdpCadreInfoDAO.getVoterCadreDetailsBySearchCriteria(stateId, locationType,locationIdsList);
						}*/
												
						if(tdpCadreVO != null)
						{
							List<TdpCadreVO> tdpCadreVOList = tdpCadreVO.getTdpCadreDetailsList();
						
							if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
							{
								returnVO.setCadreSearchList(tdpCadreVOList);
							}						
						}
					}
					else if(isFinal != null && isFinal.equalsIgnoreCase("cadreDetails"))
					{
						TdpCadreVO tdpCadreVO= cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationId, nameStr,null,null, null, 
								null,casteStateId,null,null,null,null,null,0,0,false,3l,null);
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
				}
				if(searchType.equalsIgnoreCase("Any")  || (searchType !=null && searchType.equalsIgnoreCase("Voter")))
				{
					if(isFinal != null && isFinal.equalsIgnoreCase("votersCount"))
					{
						List<Object[]> votersList = null;
						if(nameStr != null && nameStr.trim().length()>0)
						{
							votersList = boothPublicationVoterDAO.getVoterCasteWiseCountDetailsByName(stateId, locationType, locationId, casteStateId, nameStr);
						}
						else
						{
							votersList = voterCastInfoDAO.getCasteWiseVoterDetailsBySearchCriteria(stateId, locationType, locationIdsList, casteStateId);
						}
						
						if(votersList != null && votersList.size()>0)
						{
							List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>();
							for (Object[] voter : votersList) {
								TdpCadreVO cadreVO = new TdpCadreVO();
								cadreVO.setId(voter[1] != null ? Long.valueOf(voter[1].toString().trim()):0L);
								cadreVO.setCasteName(voter[0] != null ? voter[0].toString().trim():"");						
								cadreVO.setTotalCount(voter[2] != null ? Long.valueOf(voter[2].toString().trim()):0L);
								
								tdpCadreVOList.add(cadreVO);
							}
							if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
							{
								returnVO.setVoterSearchList(tdpCadreVOList);
							}
						}
					}
					else if(isFinal != null && isFinal.equalsIgnoreCase("votersDetails"))
					{
						List<Object[]> votersList = boothPublicationVoterDAO.getVoterCadreCasteDetailsByName(stateId, locationType, locationId, casteStateId, nameStr);
						if(votersList != null && votersList.size()>0)
						{
							List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>();
							List<Long> voterIdsList = new ArrayList<Long>();
							
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
								Long voterId =voter[13] != null ? Long.valueOf(voter[13].toString().trim()):0L;
								if(voterId != null && voterId.longValue()>0)
								{
									voterIdsList.add(voterId);
								}
								//vo.setImage(voter[7]!=null ?voterPath+voter[8].toString().trim()+"/"+voter[7].toString().trim()+".jpg":"");
								
								String voterPath = IConstants.VOTER_IMG_FOLDER_PATH+"/"+cadreDetailsService.getVoterImageUrlByVoterId(voterId);
								
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
								vo.setVoterId(voterId);
								
								tdpCadreVOList.add(vo);
								
								
							}
							
							Map<Long,String> votersMobileMap = new HashMap<Long, String>();
							if(voterIdsList != null && voterIdsList.size() >0)
							{
								List<Object[]> votersMobileNo = 	mobileNumbersDAO.getUservoterDetailsByUserId(1L,voterIdsList);
								if(votersMobileNo != null && votersMobileNo.size() > 0)
								{
									for (Object[] voter : votersMobileNo) {
										Long voterId =voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L;
										String mobileNo =voter[1] != null ? voter[1].toString().trim():"-";
										
										if(votersMobileMap.get(voterId.longValue()) != null)
										{
											mobileNo = votersMobileMap.get(voterId.longValue());
											mobileNo = mobileNo+", ";
										}
										
										votersMobileMap.put(voterId, mobileNo);
									}								
								}
							}
						
							
							if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
							{
								List<TdpCadreVO> voterList = new ArrayList<TdpCadreVO>();
								for (TdpCadreVO voterVO : tdpCadreVOList) {
									
									TdpCadreVO vo = getMatchedTdpCadreVOByCasteName(voterList,voterVO.getCasteName().trim());
									if(vo != null)
									{
										List<TdpCadreVO> addedVoterList =vo.getVoterSearchList();
										if(addedVoterList != null && addedVoterList.size()>0)
										{
											for (TdpCadreVO addedVoter : addedVoterList) {
												String mobileNo = votersMobileMap.get(addedVoter.getVoterId().longValue());
												addedVoter.setMobileNo(mobileNo != null? mobileNo :"-");
											}
										}
										
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
					if(name != null && name.trim().length()>0)
					{
						returnList.add(candidateVO);
					}
					
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
							if(!name.startsWith("WARD"))
								candidateVO.setConstituency(name+" ("+localElectionBody.getName()+" "+localElectionBody.getElectionType().getElectionType().toLowerCase()+")");
							else
								candidateVO.setConstituency(name);
						}
					}
					
					candidateVO.setConstituencyId(id);
					
					
					 
					candidateVO.setResponseCode("1");
					candidateVO.setTotalCount(count);
					if(name != null && name.trim().length()>0)
					{
						returnList.add(candidateVO);
					}
				}
			}
			
			if(returnList != null && returnList.size()>0)
			{
				Collections.sort(returnList, new Comparator<TdpCadreVO>() {
					public int compare(TdpCadreVO o1, TdpCadreVO o2) {
						return o1.getConstituency().compareTo(o2.getConstituency());
					}
				});
			}
			
		} catch (Exception e) {
			LOG.error("Exception occured in buildResult() in CadreVoterSearchService ",e);
		}
		return returnList;
	}
	//1111
	public List<TdpCadreVO> getCadreVoterWardDetailsBySearchCriteria(String searchType,Long stateId, String locationType,Long locationId,Long casteStateId, String nameStr,String electionbodyName,String isFinal)
	{
		List<TdpCadreVO> returnList =  new ArrayList<TdpCadreVO>();
		try {

			List<Long> locationIdsList = new ArrayList<Long>();
			if(locationId != null && locationId.longValue() != 0L)
				locationIdsList.add(locationId);
			
			TdpCadreVO returnVO = new TdpCadreVO();
			
			if(electionbodyName.equalsIgnoreCase("Greater Municipal Corp"))
			{
				returnVO.setLocalElectionBodyId(locationId);
				returnVO.setConstituencyId(1L);
				returnVO.setConstituency("Greater");	
			}
			else if(electionbodyName.equalsIgnoreCase("Muncipality"))
			{
				returnVO.setConstituencyId(2L);
			}
			else if(electionbodyName.equalsIgnoreCase("CORPORATION"))
			{
				returnVO.setConstituencyId(3L);
			}	
			
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
				if(isFinal != null && isFinal.equalsIgnoreCase("cadreCount"))
				{
					TdpCadreVO tdpCadreVO= cadreDetailsService.tdpCadreCastewiseCountDetailsBySearchCriteriaForCommitte(locationLevel,locationId, nameStr,null,null, null, 
							null,casteStateId,null,null,null,null,null);
					if(tdpCadreVO != null)
					{
						List<TdpCadreVO> tdpCadreVOList = tdpCadreVO.getTdpCadreDetailsList();
					
						if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
						{
							returnVO.setCadreSearchList(tdpCadreVOList);
						}						
					}
				}
				else if(isFinal != null && isFinal.equalsIgnoreCase("cadreDetails"))
				{
					TdpCadreVO tdpCadreVO= cadreDetailsService.searchTdpCadreDetailsBySearchCriteriaForCommitte(locationLevel,locationId, nameStr,null,null, null, 
							null,casteStateId,null,null,null,null,null,0,0,false,3l,null);
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
			}
			if(searchType.equalsIgnoreCase("Any")  || (searchType !=null && searchType.equalsIgnoreCase("Voter")))
			{
				if(isFinal != null && isFinal.equalsIgnoreCase("votersCount"))
				{
					List<Object[]> votersList = null;
					if(nameStr != null && nameStr.trim().length()>0)
					{
						votersList = boothPublicationVoterDAO.getVoterCasteWiseCountDetailsByName(stateId, locationType, locationId, casteStateId, nameStr);
					}
					else
					{
						votersList = voterCastInfoDAO.getCasteWiseVoterDetailsBySearchCriteria(stateId, locationType, locationIdsList, casteStateId);
					}
					
					if(votersList != null && votersList.size()>0)
					{
						List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>();
						for (Object[] voter : votersList) {
							TdpCadreVO cadreVO = new TdpCadreVO();
							cadreVO.setId(voter[1] != null ? Long.valueOf(voter[1].toString().trim()):0L);
							cadreVO.setCasteName(voter[0] != null ? voter[0].toString().trim():"");						
							cadreVO.setTotalCount(voter[2] != null ? Long.valueOf(voter[2].toString().trim()):0L);
							
							tdpCadreVOList.add(cadreVO);
						}
						if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
						{
							returnVO.setVoterSearchList(tdpCadreVOList);
						}
					}
				}
				else if(isFinal != null && isFinal.equalsIgnoreCase("votersDetails"))
				{
					List<Object[]> votersList = boothPublicationVoterDAO.getVoterCadreCasteDetailsByName(stateId, locationType, locationId, casteStateId, nameStr);
					if(votersList != null && votersList.size()>0)
					{
						List<TdpCadreVO> tdpCadreVOList = new ArrayList<TdpCadreVO>();
						List<Long> voterIdsList = new ArrayList<Long>();
						
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
							Long voterId =voter[13] != null ? Long.valueOf(voter[13].toString().trim()):0L;
							if(voterId != null && voterId.longValue()>0)
							{
								voterIdsList.add(voterId);
							}
							//vo.setImage(voter[7]!=null ?voterPath+voter[8].toString().trim()+"/"+voter[7].toString().trim()+".jpg":"");
							
							String voterPath = IConstants.VOTER_IMG_FOLDER_PATH+"/"+cadreDetailsService.getVoterImageUrlByVoterId(voterId);
							
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
							vo.setVoterId(voterId);
							
							tdpCadreVOList.add(vo);
							
							
						}
						
						Map<Long,String> votersMobileMap = new HashMap<Long, String>();
						if(voterIdsList != null && voterIdsList.size() >0)
						{
							List<Object[]> votersMobileNo = 	mobileNumbersDAO.getUservoterDetailsByUserId(1L,voterIdsList);
							if(votersMobileNo != null && votersMobileNo.size() > 0)
							{
								for (Object[] voter : votersMobileNo) {
									Long voterId =voter[0] != null ? Long.valueOf(voter[0].toString().trim()):0L;
									String mobileNo =voter[1] != null ? voter[1].toString().trim():"-";
									
									if(votersMobileMap.get(voterId.longValue()) != null)
									{
										mobileNo = votersMobileMap.get(voterId.longValue());
										mobileNo = mobileNo+", ";
									}
									
									votersMobileMap.put(voterId, mobileNo);
								}								
							}
						}
					
						
						if(tdpCadreVOList != null && tdpCadreVOList.size()>0)
						{
							List<TdpCadreVO> voterList = new ArrayList<TdpCadreVO>();
							for (TdpCadreVO voterVO : tdpCadreVOList) {
								
								TdpCadreVO vo = getMatchedTdpCadreVOByCasteName(voterList,voterVO.getCasteName().trim());
								if(vo != null)
								{
									List<TdpCadreVO> addedVoterList =vo.getVoterSearchList();
									if(addedVoterList != null && addedVoterList.size()>0)
									{
										for (TdpCadreVO addedVoter : addedVoterList) {
											String mobileNo = votersMobileMap.get(addedVoter.getVoterId().longValue());
											addedVoter.setMobileNo(mobileNo != null? mobileNo :"-");
										}
									}
									
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
			LOG.error("Exception occured in getCadreVoterWardDetailsBySearchCriteria() in CadreVoterSearchService ",e);
		}
		
		return returnList;
	}
	
	public VoterDetailsVO getVoterDetailsByVoterCardNumber(String voterIDCardNo,Long constId){
		VoterDetailsVO voterDetailsvo = new VoterDetailsVO();
		
		try {
			VoterDetailsVO vo=null;
			List<VoterDetailsVO> voterDetailsList = new ArrayList<VoterDetailsVO>();
			Map<String,String> voterCheckMap = new LinkedHashMap<String, String>();
			List<Object[]> list =  null;
			List<Object[]> list1 = tdpCadreDAO.checkVoterCardNumberRegistration(voterIDCardNo);
			Long tdpCadreId = 0L;
			String registeredVoter = "";
			if(list1 != null && list1.size() > 0){
				for (Object[] obj : list1) {
					registeredVoter = obj[0] != null ? obj[0].toString():"";
					String paymentStatus = commonMethodsUtilService.getStringValueForObject(obj[2]);
					if(paymentStatus != null && paymentStatus.trim().equalsIgnoreCase(IConstants.NOT_PAID_STATUS))
						voterCheckMap.put(registeredVoter, "Payment is Pending");
					else
						voterCheckMap.put(registeredVoter, "Already Registered");
					tdpCadreId = commonMethodsUtilService.getLongValueForObject(obj[3]);
					break;
				}
				
				list = tdpCadreDAO.getRegisteredMemberDetails(tdpCadreId);
			}
			else
				list = boothPublicationVoterDAO.getVoterDetailsByVoterCardNumber(voterIDCardNo,constId);
				
			
				if(list != null && list.size() > 0){
					for (Object[] obj : list) {
						vo = new VoterDetailsVO();
						
						vo.setVoterId(Long.valueOf(obj[0] != null ? obj[0].toString():"0"));
						vo.setVoterName(obj[1] != null ? obj[1].toString():"");
						vo.setRelativeName(obj[2] != null ? obj[2].toString():"");
						String voterCardNo = obj[3] != null ? obj[3].toString():"";
						vo.setVoterIDCardNo(voterCardNo);
						String check = voterCheckMap.get(voterCardNo);
						if(check != null && check.contains("Payment")){
							vo.setPaymentStatus("PAY NOW");
						}
						if(check != null){
							vo.setAlreadyRegistered(check);
						}
						vo.setGender(obj[4] != null ? obj[4].toString():"");
						vo.setAge(obj[5] != null ? obj[5].toString():"");
						vo.setDateOfBirth(obj[6] != null ? obj[6].toString():"");
						vo.setMobileNo(obj[7] != null ? obj[7].toString():"");
						vo.setHouseNo(obj[8] != null ? obj[8].toString():""); //model.address.houseNo
						vo.setBoothId(Long.valueOf(obj[9] != null ? obj[9].toString():"0"));//model.address.booth.boothId
						vo.setPartNo(obj[10] != null ? obj[10].toString():"");//model.address.booth.partNo
						vo.setDistrictId(Long.valueOf(obj[11] != null ? obj[11].toString():"0"));//model.address.district.distractid
						vo.setDistrictName(obj[12] != null ? obj[12].toString():"");
						vo.setConstituencyId(Long.valueOf(obj[13] != null ? obj[13].toString():"0"));
						vo.setConstituencyName(obj[14] != null ? obj[14].toString():"");
						vo.setTehsilId(Long.valueOf(obj[15] != null ? obj[15].toString():"0"));
						vo.setTehsil(obj[16] != null ? obj[16].toString():"");
						vo.setLocalElectionBodyId(Long.valueOf(obj[17] != null ? obj[17].toString():"0"));
						vo.setLeb(obj[18] != null ? obj[18].toString():"");
						if(obj.length>19)
							vo.setImage(commonMethodsUtilService.getStringValueForObject(obj[19]));
						voterDetailsList.add(vo);
					}
				}
			
			
			
			
			voterDetailsvo.setSubList(voterDetailsList);
			
		} catch (Exception e) {
			LOG.error("Exception occured in getVoterDetailsByVoterCardNumber() in CadreVoterSearchService ",e);
		}
		return voterDetailsvo;
	}
	
	public String generateOTPForMobileNumber(Long userId,String mobileNo,String refNo){
		String status = null;
		try {
				List<Object[]> userList = smsOtpDetailsDAO.checkForExpire(mobileNo);
				Calendar calendar = Calendar.getInstance();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				if(userList == null || userList.size() == 0 ){
					status = getAndSaveOtp(userId,mobileNo,refNo);
					
				}else{
					String lastDate = userList.get(0)[1].toString();
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				    Date lstlDate = df.parse(lastDate);
					String unit = "hour";
					long interval = 1l;
					boolean valid = commonMethodsUtilService.isActiveForSomeHours(lstlDate, unit, interval);
					if(valid==true){
				    	calendar.setTime(lstlDate);
				    	calendar.add(Calendar.HOUR, 1);// adding next once hour.
				    	int hours = calendar.get(Calendar.HOUR_OF_DAY);
				    	String timeFormat ="AM";
				    	 if(hours > 11 && hours <24)
				    		 timeFormat ="PM";
						String messageStr = " TNGF Registration OTP number: "+userList.get(0)[2].toString()+". This OTP is valid upto "+sdf.format(calendar.getTime())+" "+timeFormat+".";
						//smsSenderService.sendSMS(userId,IConstants.SMS_AFFILIATED_GRADUATES_ENROLLMENT_MODULE, true, messageStr, mobileNo);
						status = IConstants.SUCCESS;
					}else{
						status = getAndSaveOtp(userId,mobileNo,refNo);
					}
					
				}
		} catch (Exception e) {
			status = IConstants.FAILURE;
			LOG.error("Exception occured in generateOTPForMobileNumber() in CadreVoterSearchService ",e);
		}
		return status;
	}
	
	public String validateOTP(String mobileNo,String refNo,String otp){
		String status = null;
		try {
			
			Long smsOTPDetailsId = smsOtpDetailsDAO.validateOTP(mobileNo, refNo, otp);
			if(smsOTPDetailsId != null && smsOTPDetailsId.longValue() > 0l){
				SmsOtpDetails smsOtpDetails = smsOtpDetailsDAO.get(smsOTPDetailsId);
				smsOtpDetails.setIsDeleted("Y");
				smsOtpDetailsDAO.save(smsOtpDetails);
				status = IConstants.SUCCESS;
			}
		      
		} catch (Exception e) {
			status = IConstants.FAILURE;
			LOG.error("Exception occured in validateOTP() in CadreVoterSearchService ",e);
		}
		return status;
	}
	public String getAndSaveOtp(Long userId,String mobileNo,String refNo){
		String status = null;
		try{
			Date currentTime = dateUtilService.getCurrentDateAndTime();
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(currentTime);
	    	calendar.add(Calendar.HOUR, 1);// adding next once hour.
	    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	    	int hours = calendar.get(Calendar.HOUR_OF_DAY);
	    	String timeFormat ="AM";
	    	if(hours > 11 && hours <24)
	    		 timeFormat ="PM";
			Random random=new Random();
		    int number= 0;
		    String otp = "";
		    do{
		      number = Math.abs(random.nextInt());
		      otp = String.valueOf(number);
		    }while(otp.trim().length()<=6);
		    
		    otp = String.valueOf(number).substring(0,6);
		    if(otp != null && !otp.isEmpty()){
		    String messageStr = " TNGF Registration OTP number: "+otp+". This OTP is valid upto "+sdf.format(calendar.getTime())+" "+timeFormat+".";
		    SmsOtpDetails smsOtpDetails = new SmsOtpDetails();
		    
		    smsOtpDetails.setOtpReferenceId(refNo);
		    smsOtpDetails.setOtpNo(otp);
		    smsOtpDetails.setIsDeleted("N");
		    smsOtpDetails.setInsertedTime(dateUtilService.getCurrentDateAndTime());
		    smsOtpDetails.setUpdatedTime(dateUtilService.getCurrentDateAndTime());
		    smsOtpDetails.setMobileNo(mobileNo);
		    smsOtpDetails.setGenerateTime(dateUtilService.getCurrentDateAndTime());
		    smsOtpDetails.setUserId(userId);
		    smsOtpDetails = smsOtpDetailsDAO.save(smsOtpDetails);
		      
		    //smsSenderService.sendSMS(userId,IConstants.SMS_AFFILIATED_GRADUATES_ENROLLMENT_MODULE, true, messageStr, mobileNo);
		      
		    status = IConstants.SUCCESS;
		    }
			
		}catch(Exception e){
			e.printStackTrace();
			status = IConstants.FAILURE;
		}
		 return status;
	
	}
}
