package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.ICasteStateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyMandalDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.IHamletDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.IQueryTempDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterCategoryValueDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.InfluencingPeopleBeanVO;
import com.itgrids.partyanalyst.dto.PartyResultVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.Booth;
import com.itgrids.partyanalyst.model.BoothPublicationVoter;
import com.itgrids.partyanalyst.model.Cadre;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.DelimitationConstituency;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Hamlet;
import com.itgrids.partyanalyst.model.InfluencingPeople;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.QueryTemp;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Voter;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterPartyInfo;
import com.itgrids.partyanalyst.model.VoterReportLevel;
import com.itgrids.partyanalyst.service.IVoterReportService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;


public class VoterReportService implements IVoterReportService{
	private static final Logger LOG = Logger.getLogger(VoterReportService.class);
	private IBoothDAO boothDAO;
	private RegionServiceDataImp regionServiceDataImp;
	private IPanchayatDAO panchayatDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	
	private IVoterPartyInfoDAO voterPartyInfoDAO;
	private TransactionTemplate transactionTemplate = null;
	private IVoterReportLevelDAO voterReportLevelDAO;
	private IVotersAnalysisService votersAnalysisService;
	
	private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;
	
	private IVoterModificationInfoDAO voterModificationInfoDAO;
	private ICasteStateDAO casteStateDAO;
	private IConstituencyDAO constituencyDAO;
	private IVoterCastInfoDAO voterCastInfoDAO;
	private IVoterCastBasicInfoDAO voterCastBasicInfoDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IPartyDAO partyDAO;
	private IVoterModificationDAO voterModificationDAO;
	private IVoterDAO voterDAO;
	private IWardDAO wardDAO;
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IPublicationDateDAO publicationDateDAO;
	private IQueryTempDAO queryTempDAO;
	IUserVoterCategoryValueDAO userVoterCategoryValueDAO;
	private IHamletDAO hamletDAO;
	private IStateDAO stateDAO;
	private IDistrictDAO districtDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private ITehsilDAO tehsilDAO;
	
	public IQueryTempDAO getQueryTempDAO() {
		return queryTempDAO;
	}

	public void setQueryTempDAO(IQueryTempDAO queryTempDAO) {
		this.queryTempDAO = queryTempDAO;
	}

	public IWardDAO getWardDAO() {
		return wardDAO;
	}

	public void setWardDAO(IWardDAO wardDAO) {
		this.wardDAO = wardDAO;
	}

	public static Logger getLog() {
		return LOG;
	}

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
	}

	public IVoterDAO getVoterDAO() {
		return voterDAO;
	}

	public void setVoterDAO(IVoterDAO voterDAO) {
		this.voterDAO = voterDAO;
	}

	public void setVoterCastInfoDAO(IVoterCastInfoDAO voterCastInfoDAO) {
		this.voterCastInfoDAO = voterCastInfoDAO;
	}

	public IVoterCastBasicInfoDAO getVoterCastBasicInfoDAO() {
		return voterCastBasicInfoDAO;
	}

	public void setVoterCastBasicInfoDAO(
			IVoterCastBasicInfoDAO voterCastBasicInfoDAO) {
		this.voterCastBasicInfoDAO = voterCastBasicInfoDAO;
	}

	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public ICasteStateDAO getCasteStateDAO() {
		return casteStateDAO;
	}

	public void setCasteStateDAO(ICasteStateDAO casteStateDAO) {
		this.casteStateDAO = casteStateDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
		return voterModificationInfoDAO;
	}

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}

	public IVoterModificationAgeInfoDAO getVoterModificationAgeInfoDAO() {
		return voterModificationAgeInfoDAO;
	}

	public void setVoterModificationAgeInfoDAO(
			IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
		this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
	}

	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}

	public IVoterPartyInfoDAO getVoterPartyInfoDAO() {
		return voterPartyInfoDAO;
	}

	public void setVoterPartyInfoDAO(IVoterPartyInfoDAO voterPartyInfoDAO) {
		this.voterPartyInfoDAO = voterPartyInfoDAO;
	}

	public IBoothDAO getBoothDAO() {
			return boothDAO;
		}

		public void setBoothDAO(IBoothDAO boothDAO) {
			this.boothDAO = boothDAO;
		}

		public RegionServiceDataImp getRegionServiceDataImp() {
			return regionServiceDataImp;
		}

		public void setRegionServiceDataImp(RegionServiceDataImp regionServiceDataImp) {
			this.regionServiceDataImp = regionServiceDataImp;
		}

		public IPanchayatDAO getPanchayatDAO() {
			return panchayatDAO;
		}

		public void setPanchayatDAO(IPanchayatDAO panchayatDAO) {
			this.panchayatDAO = panchayatDAO;
		}

		public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
			return assemblyLocalElectionBodyDAO;
		}

		public void setAssemblyLocalElectionBodyDAO(
				IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
			this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
		}

		public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
			return boothPublicationVoterDAO;
		}

		public void setBoothPublicationVoterDAO(
				IBoothPublicationVoterDAO boothPublicationVoterDAO) {
			this.boothPublicationVoterDAO = boothPublicationVoterDAO;
		}

		
		public IVoterReportLevelDAO getVoterReportLevelDAO() {
			return voterReportLevelDAO;
		}

		public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
			this.voterReportLevelDAO = voterReportLevelDAO;
		}
		public IVoterModificationDAO getVoterModificationDAO() {
				return voterModificationDAO;
			}
		
		public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
			this.voterModificationDAO = voterModificationDAO;
		}
	
	    public IPartyDAO getPartyDAO() {
			return partyDAO;
		}

		public void setPartyDAO(IPartyDAO partyDAO) {
			this.partyDAO = partyDAO;
		}

	    public IUserVoterCategoryValueDAO getUserVoterCategoryValueDAO() {
			return userVoterCategoryValueDAO;
		}

		public void setUserVoterCategoryValueDAO(
				IUserVoterCategoryValueDAO userVoterCategoryValueDAO) {
			this.userVoterCategoryValueDAO = userVoterCategoryValueDAO;
		}

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
			return userVoterDetailsDAO;
		}

		public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
			this.userVoterDetailsDAO = userVoterDetailsDAO;
		}

		
	    public IPublicationDateDAO getPublicationDateDAO() {
			return publicationDateDAO;
		}

		public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
			this.publicationDateDAO = publicationDateDAO;
		}

		
	public IHamletDAO getHamletDAO() {
			return hamletDAO;
		}

		public void setHamletDAO(IHamletDAO hamletDAO) {
			this.hamletDAO = hamletDAO;
		}
		

	public IStateDAO getStateDAO() {
			return stateDAO;
		}

		public void setStateDAO(IStateDAO stateDAO) {
			this.stateDAO = stateDAO;
		}

		public IDistrictDAO getDistrictDAO() {
			return districtDAO;
		}

		public void setDistrictDAO(IDistrictDAO districtDAO) {
			this.districtDAO = districtDAO;
		}

		public ILocalElectionBodyDAO getLocalElectionBodyDAO() {
			return localElectionBodyDAO;
		}

		public void setLocalElectionBodyDAO(ILocalElectionBodyDAO localElectionBodyDAO) {
			this.localElectionBodyDAO = localElectionBodyDAO;
		}

		public ITehsilDAO getTehsilDAO() {
			return tehsilDAO;
		}

		public void setTehsilDAO(ITehsilDAO tehsilDAO) {
			this.tehsilDAO = tehsilDAO;
		}

	public VoterReportVO getVoterDetailsInaLocation(String range,Long rangeValue)
	{
		try{
			VoterReportVO voterReportVO = new VoterReportVO();
			
			
			return voterReportVO;
		}catch (Exception e) {
			LOG.error("Exception Occured in getVoterDetailsInaLocation() method with arguements Range - " +
					range+" Value - "+rangeValue);
			return null;
		}
	}
	
	public ResultStatus insertVotersPartyDataToIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId)
	{
		
		ResultStatus resultStatus = new ResultStatus();
		  try{
			  List<Long> mandalIdsList = new ArrayList<Long>(0);
			  List<SelectOptionVO> panchayatsList = new ArrayList<SelectOptionVO>(0);
			  List<Long> wardsList = new ArrayList<Long>(0);
			  List<Long> localBodiesList = new ArrayList<Long>(0);
			  Set<Long> boothIdsList = new HashSet<Long>(0);
			  List<Long> constiIds = new ArrayList<Long>();
			  List<Long> panchayatIds = new ArrayList<Long>();
			  
			  constiIds.add(reportLevelValue);
			  InsertVoterPartyInfoForALocation(IConstants.CONSTITUENCY,constiIds,publicationDateId,reportLevelValue,userId);
			 
			  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
			  
			  if(mandalsList == null || mandalsList.size() == 0)
				  return null;
			  
			  for(SelectOptionVO selectOptionVO : mandalsList)
			  {
				  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
					  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
				  else
					  localBodiesList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
			  }
			  List<Object[]> list = null;
			  if(mandalIdsList != null && mandalIdsList.size() >0){ 
				   list = panchayatDAO.getPanchayatIdsByMandalIdsList(mandalIdsList);
			  }
			  
			  
			 if(list != null && list.size() > 0)
			  {
				 for(Object[] params : list){
					 panchayatIds.add((Long)params[0]);
				    panchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
				 }
			  }
			 
			 if(mandalIdsList.size() > 0)
			  {
				  InsertVoterPartyInfoForALocation(IConstants.MANDAL,mandalIdsList, publicationDateId,reportLevelValue,userId);
				
			  }
			  
			  if(panchayatIds.size() > 0){
				  InsertVoterPartyInfoForALocation(IConstants.PANCHAYAT,panchayatIds, publicationDateId,reportLevelValue,userId);
					 
			  }
			  List<Object[]> list2 = null;
			  if(panchayatIds.size() > 0)
				  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIds, publicationDateId);
			  
			  if(list2 != null && list2.size() > 0)
			  {
				  for(Object[] params : list2)
					  boothIdsList.add((Long)params[0]);
			  }
			  
			   // List<Long> wardsList = new ArrayList<Long>();
			  if(localBodiesList != null && localBodiesList.size() >0){
				  
				List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
						localBodiesList, publicationDateId,reportLevelValue);
				
				if(wards != null && wards.size() >0){
					
					for(Object[] ward:wards)
					if(ward[0] != null){
						wardsList.add((Long)ward[0]);
					}		
				}
				  
			  }
			 if(localBodiesList.size() > 0)
			  {
				  
					  InsertVoterPartyInfoForALocation(IConstants.LOCALELECTIONBODY,localBodiesList, publicationDateId,reportLevelValue,userId);
				
				  List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localBodiesList,publicationDateId,reportLevelValue);
				  
				  if(list3 != null && list3.size() > 0)
				  {
					  for(Object[] params : list3)
						  boothIdsList.add((Long)params[0]); 
				  }
				  
			  }
            if(wardsList.size() > 0){
				  
            	InsertVoterPartyInfoForALocation(
						  IConstants.WARD,wardsList, publicationDateId,reportLevelValue,userId);
          	 
				 
			  }
              List<Long> booths = null;
              if(boothIdsList.size() > 0)
              booths = new ArrayList<Long>(boothIdsList);
			 if(booths != null && booths.size() > 0)
			  //InsertVoterPartyInfoForALocation(IConstants.BOOTH,booths, publicationDateId,reportLevelValue,userId);
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			 
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
	}
		  public ResultStatus InsertVoterPartyInfoForALocation(String locationType, List<Long> locationValue, Long publicationDateId,Long constituencyId,Long userId)
		  {
			  
			  	ResultStatus resultStatus = new ResultStatus();
			  
			  try{
				 
				  List<VoterPartyInfo> partyCategoryWiseList = getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,locationType,locationValue,publicationDateId,constituencyId);
				 if(partyCategoryWiseList != null && partyCategoryWiseList.size() > 0)
				  {
					 voterPartyInfoDAO.saveAllObjects(partyCategoryWiseList);
				  }
				
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
			  }catch (Exception e) {
				  
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  return resultStatus;
			  }
		  }
		  
		  public ResultStatus saveVotersDataInVoterPartyInfoTable(final VoterCastInfoVO votersInfo)
		  {
			 
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
						protected void doInTransactionWithoutResult(TransactionStatus status) 
						{
							VoterPartyInfo voterPartyInfo = new VoterPartyInfo();
							voterPartyInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
							voterPartyInfo.setReportLevelValue(votersInfo.getReportLevelValue());
							voterPartyInfo.setUserId(votersInfo.getUserId());
							voterPartyInfo.setPartyVoters(votersInfo.getTotalVoters());
							voterPartyInfo.setPartyMaleVoters(votersInfo.getMaleVoters());
							voterPartyInfo.setPartyFemaleVoters(votersInfo.getFemaleVoters());
							voterPartyInfo.setPartyPercentage(votersInfo.getPartyPercentage());
							voterPartyInfo.setPublicationDateId(votersInfo.getPublicationDateId());
							voterPartyInfo.setConstituencyId(votersInfo.getLocationId());
							voterPartyInfo.setParty(partyDAO.get(votersInfo.getPartyId()));
							voterPartyInfoDAO.save(voterPartyInfo);
							}
						});
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
			  }catch (Exception e) {
				  e.printStackTrace();
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  return resultStatus;
			}
		  }
		  
		 public ResultStatus deletevotermodificationFromIntermediateTables(Long constituencyId,Long publicationId)
		  {
			  ResultStatus resultStatus = new ResultStatus();
				try{
					
					if(constituencyId != null && constituencyId > 0)
					{
						List<Long> voterModificationInfoIds = voterModificationInfoDAO.getVoterModificationInfoIds(constituencyId, publicationId);
						if(voterModificationInfoIds != null && voterModificationInfoIds.size() > 0)
						voterModificationAgeInfoDAO.deleteVoterModicationAgeInfoById(voterModificationInfoIds);
						voterModificationInfoDAO.deletevotermodificationInfoByConstituencyId(constituencyId,publicationId);
						
						
					}
					resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					return resultStatus;
				}catch (Exception e) {
					e.printStackTrace();
					
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					return resultStatus;
				}  
		  }
		 
		 
		 public ResultStatus insertVotersCasteDataInIntermediateTables(Long reportLevelValue, Long publicationDateId,Long userId)
			{
				
				  ResultStatus resultStatus = new ResultStatus();
				  try{
					  Map<Long,Long> mandalIdsList = new HashMap<Long,Long>(0);
					  Map<Long,Long> wardsList = new HashMap<Long,Long>(0);
					  Map<Long,Long> panchayatIdsList = new HashMap<Long,Long>(0);
					  Map<Long,Long> localBodiesList = new HashMap<Long,Long>(0);
					  Set<Long> boothIdsList = new HashSet<Long>(0);
					  Map<Long,Long> constituencyIds = new HashMap<Long,Long>();
					  constituencyIds.put(reportLevelValue,1l);
					  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.CONSTITUENCY,constituencyIds,publicationDateId,reportLevelValue);
					  //InsertVoterCasteBasicInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,publicationDateId,reportLevelValue,userId);
					  List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(reportLevelValue,IConstants.PRESENT_YEAR, null);
					  
					  if(mandalsList == null || mandalsList.size() == 0)
						  return null;
					  Long totalVoters = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.CONSTITUENCY), reportLevelValue, reportLevelValue, publicationDateId, userId);
					  for(SelectOptionVO selectOptionVO : mandalsList)
					  {
						  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
							  mandalIdsList.put(new Long(selectOptionVO.getId().toString().substring(1)),totalVoters);
						  else
							  localBodiesList.put((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0),totalVoters);
					  }
					  List<Object[]> list = null;
					  if(mandalIdsList != null && mandalIdsList.size() >0){ 
						  List<Long> ids = new ArrayList<Long>();
						  ids.addAll(mandalIdsList.keySet());
						   list = panchayatDAO.getPanchayatIdsByMandalIdsList(ids);
					  }
					  if(mandalIdsList != null && mandalIdsList.size() >0)
					  {
						// InsertVoterCasteInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue, publicationDateId,reportLevelValue,userId);
						 //InsertVoterCasteBasicInfoForALocation(IConstants.MANDAL,mandalId,publicationDateId,reportLevelValue,userId);
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.MANDAL,mandalIdsList,publicationDateId,reportLevelValue);
					  }
					 if(list != null && list.size() > 0)
					  {
						 Map<Long,Long> mandalTotalVotersMap = new HashMap<Long,Long>();
						 for(Object[] params : list){
							 Long total = mandalTotalVotersMap.get((Long)params[1]);
						    if(total == null){
						    	total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.MANDAL), (Long)params[1], reportLevelValue, publicationDateId, userId);
						    	if(total == null)
						    		total = 0l;
						    	mandalTotalVotersMap.put((Long)params[1],total);
						    }
						   panchayatIdsList.put((Long)params[0],total);
						 }
					  }
					 
					  
					  
					  if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					     saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.PANCHAYAT,panchayatIdsList,publicationDateId,reportLevelValue);
					 /* List<Object[]> list2 = null;
					  if(panchayatIdsList.size() > 0)
						  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
					  
					  if(list2 != null && list2.size() > 0)
					  {
						  for(Object[] params : list2)
							  boothIdsList.add((Long)params[0]);
					  }*/
					  
					   // List<Long> wardsList = new ArrayList<Long>();
					  if(localBodiesList != null && localBodiesList.size() >0){
						  List<Long> ids = new ArrayList<Long>();
						  ids.addAll(localBodiesList.keySet());
						List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
								ids, publicationDateId,reportLevelValue);
						
						if(wards != null && wards.size() >0){
							Map<Long,Long> lclBdyTotalVotersMap = new HashMap<Long,Long>();
							for(Object[] ward:wards)	
							if(ward[0] != null){
								Long total = lclBdyTotalVotersMap.get((Long)ward[1]);
							    if(total == null){
							    	//total = voterInfoDAO.getVotersCountInALocation(votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY),(Long)ward[1],publicationDateId,reportLevelValue);
							    	total = voterCastInfoDAO.getVotersCastCount(votersAnalysisService.getReportLevelId(IConstants.LOCALELECTIONBODY), (Long)ward[1], reportLevelValue, publicationDateId, userId);
							    	if(total == null)
							    		total = 0l;
							    	lclBdyTotalVotersMap.put((Long)ward[1],total);
							    }	
								wardsList.put((Long)ward[0],total);
							}		
						}
						  
					  }
					 if(localBodiesList.size() > 0)
					  {
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.LOCALELECTIONBODY,localBodiesList,publicationDateId,reportLevelValue);
						 /* List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localBodiesList,publicationDateId,reportLevelValue);
						  
						  if(list3 != null && list3.size() > 0)
						  {
							  for(Object[] params : list3)
								  boothIdsList.add((Long)params[0]); 
						  }*/
						  
					  }
		              if(wardsList != null && wardsList.size() > 0){
						  
		            	  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.WARD,wardsList,publicationDateId,reportLevelValue);
		            	  //InsertVoterCasteBasicInfoForALocation(IConstants.WARD,new Long(selectOptionVO.getId()),publicationDateId,reportLevelValue,userId);
						 
					  }
		             
					   if(boothIdsList != null && boothIdsList.size() > 0){
						  //InsertVoterCasteInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId);
						  //InsertVoterCasteBasicInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),publicationDateId,reportLevelValue,userId);
						   //saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.BOOTH,new ArrayList<Long>(boothIdsList),publicationDateId,reportLevelValue);
					   }  
					  
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					  return resultStatus;
				  }catch (Exception e) {
					  
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				  }
			}
		   
		  /* public ResultStatus InsertVoterCasteInfoForALocation(String locationType, Long locationValue, Long parentLocationId, Long publicationDateId,Long constituencyId,Long userId)
			  {
				
				  ResultStatus resultStatus = new ResultStatus();
				  
				  try{
					  List<VoterCastInfoVO> castCategoryWiseList = null;
					 
					  castCategoryWiseList = votersAnalysisService.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationValue,publicationDateId,constituencyId);
					 if(castCategoryWiseList != null && castCategoryWiseList.size() > 0)
					  {
						  VoterCastInfoVO votersInfo = new VoterCastInfoVO();
						  for(VoterCastInfoVO voters : castCategoryWiseList){
							
						  votersInfo.setReportLevelId(votersAnalysisService.getReportLevelId(locationType));
						  votersInfo.setReportLevelValue(locationValue);
						  votersInfo.setPublicationDateId(publicationDateId);
					      votersInfo.setFemaleVoters(voters.getFemaleVoters());
						  votersInfo.setMaleVoters(voters.getMaleVoters());
						  votersInfo.setLocationId(constituencyId);
						  votersInfo.setUserId(userId);
						  votersInfo.setCasteStateId(voters.getCasteStateId());
						  votersInfo.setTotalVoters(voters.getTotalVoters());
						  votersInfo.setCastePercentage(Double.parseDouble(voters.getVotesPercent()));
						 
						  saveVotersDataInVoterCasteInfoTable(votersInfo);
					  }
					}
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
				  }catch (Exception e) {
					 
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				  }
			  }*/
			 
			public ResultStatus saveVotersDataInVoterCasteInfoTable(final VoterCastInfoVO votersInfo)
			{
				
				  ResultStatus resultStatus = new ResultStatus();
				  try{
					  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
							protected void doInTransactionWithoutResult(TransactionStatus status) 
							{
								
								VoterCastInfo votercastInfo = new VoterCastInfo();
								votercastInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
								votercastInfo.setReportLevelValue(votersInfo.getReportLevelValue());
								votercastInfo.setUserId(votersInfo.getUserId());
								votercastInfo.setCasteState(casteStateDAO.get(votersInfo.getCasteStateId()));
								votercastInfo.setCasteVoters(votersInfo.getTotalVoters());
								votercastInfo.setCasteMaleVoters(votersInfo.getMaleVoters());
								votercastInfo.setCasteFemaleVoters(votersInfo.getFemaleVoters());
								votercastInfo.setCastePercentage(votersInfo.getCastePercentage());
								votercastInfo.setPublicationDateId(votersInfo.getPublicationDateId());
								votercastInfo.setConstituency(constituencyDAO.get(votersInfo.getLocationId()));
								voterCastInfoDAO.save(votercastInfo);
							}
					  });
					 
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
					  return resultStatus;
				  }catch (Exception e) {
					  e.printStackTrace();
					  
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				}
			  }
			/*public ResultStatus InsertVoterCasteBasicInfoForALocation(String locationType, Long locationValue,Long publicationDateId,Long constituencyId,Long userId)
			  {
				//log.info(" Entered into calculateAndInsertVoterInfoForALocation() Method, with Values - Location Type - "+locationType+" - Location Value - "+locationValue+", Parent Location Id - "+parentLocationId+" and Publicarion Date Id - "+publicationDateId);
				  ResultStatus resultStatus = new ResultStatus();
				  
				  try{
					  List<VoterCastInfoVO> castCategoryWiseList = null;
					  Long totalVoters = 0L;
						
						 totalVoters = voterInfoDAO.getVotersCountInALocation(votersAnalysisService.getReportLevelId(locationType),locationValue,publicationDateId,constituencyId);
						
						if(locationType.equalsIgnoreCase("hamlet")){
							
							List<Long> hamlets = new ArrayList<Long>();
							hamlets.add(locationValue);
						List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
							
					    List<Long> countList = 	boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds,publicationDateId);
					   if(countList != null && countList.size() >0)
						 totalVoters = countList.get(0);
						}
					  castCategoryWiseList = votersAnalysisService.getCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationValue,publicationDateId,constituencyId);
					  List<SelectOptionVO> selectoptionList = votersAnalysisService.getCastCategoryWiseVotersCountByPublicationIdInALocation(userId,locationType,locationValue,publicationDateId,constituencyId);
					 
					  Long votesConsidered = 0l;
					  
					 if(castCategoryWiseList != null && castCategoryWiseList.size() > 0)
					  {
						  VoterCastInfoVO votersInfo = new VoterCastInfoVO();
						  for(VoterCastInfoVO voters : castCategoryWiseList)
							  votesConsidered = votesConsidered + voters.getTotalVoters();
						  for(VoterCastInfoVO voters : castCategoryWiseList){
						  votersInfo.setReportLevelId(votersAnalysisService.getReportLevelId(locationType));
						  votersInfo.setReportLevelValue(locationValue);
						  votersInfo.setPublicationDateId(publicationDateId);
						  votersInfo.setTotalCasts(castCategoryWiseList.size());
						  votersInfo.setCasteAssignedVoters(votesConsidered);
						  if(totalVoters != null)
						  votersInfo.setCasteNotAssignedVoters(totalVoters - votesConsidered);
						  votersInfo.setLocationId(constituencyId);
						  votersInfo.setUserId(userId);
						  
						  votersInfo.setFemaleVoters(voters.getFemaleVoters());
						  votersInfo.setMaleVoters(voters.getMaleVoters());
						  votersInfo.setCasteStateId(voters.getCasteStateId());
						  votersInfo.setTotalVoters(voters.getTotalVoters());
						  votersInfo.setCastePercentage(Double.parseDouble(voters.getVotesPercent()));
						  
						  
						  saveVotersDataInVoterCasteInfoTable(votersInfo);
					  }
						  saveVotersDataInVoterCasteBasicInfoTable(votersInfo,selectoptionList);
					}
					  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
				  }catch (Exception e) {
					  
					  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					  return resultStatus;
				  }
			  }*/
				  
				  
				  
				  public ResultStatus saveVotersDataInVoterCasteBasicInfoTable(final VoterCastInfoVO votersInfo,final List<SelectOptionVO> list)
					{
						 
						  ResultStatus resultStatus = new ResultStatus();
						  try{
							  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
									protected void doInTransactionWithoutResult(TransactionStatus status) 
									{
										
										VoterCastBasicInfo voterCastBasicInfo = new VoterCastBasicInfo();
										voterCastBasicInfo.setReportLevelValue(votersInfo.getReportLevelValue());
										voterCastBasicInfo.setVoterReportLevel(voterReportLevelDAO.get(votersInfo.getReportLevelId()));
										voterCastBasicInfo.setUserId(votersInfo.getUserId());
										voterCastBasicInfo.setTotalCastes(new Long(votersInfo.getTotalCasts()));
										voterCastBasicInfo.setCasteAssignedVoters(votersInfo.getCasteAssignedVoters());
										voterCastBasicInfo.setCasteNotAssignedVoters(votersInfo.getCasteNotAssignedVoters());
										voterCastBasicInfo.setPublicationDateId(votersInfo.getPublicationDateId());
										voterCastBasicInfo.setConstituency(constituencyDAO.get(votersInfo.getLocationId()));
										for(SelectOptionVO params : list)
										{
											if(params.getName().equalsIgnoreCase("OC"))
												voterCastBasicInfo.setOcVoters(params.getId());
											if(params.getName().equalsIgnoreCase("BC"))
												voterCastBasicInfo.setBcVoters(params.getId());
											if(params.getName().equalsIgnoreCase("SC"))
												voterCastBasicInfo.setScVoters(params.getId());
											if(params.getName().equalsIgnoreCase("ST"))
											voterCastBasicInfo.setStVoters(params.getId());
											
										}
										voterCastBasicInfoDAO.save(voterCastBasicInfo);
									}
							  });
							 
							  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
							  return resultStatus;
						  }catch (Exception e) {
							  e.printStackTrace();
							 
							  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
							  return resultStatus;
						}
					  }
				  
				  public ResultStatus deleteVoterModifiedData(Long constituencyId,Long publicationDateId)
				  {
					LOG.info(" Entered into deleteVoterModifiedData() method"); 
					ResultStatus resultStatus = new ResultStatus();
					try{
						if(constituencyId != null && constituencyId > 0)
						{
						voterModificationDAO.deleteVoterModifiedDataByCOnstituencyId(constituencyId, publicationDateId);
						resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
						}
						
					}
					catch(Exception e)
					{
					LOG.error("Exception Occured in deleteVoterModifiedData()", e);	
					resultStatus.setResultCode(ResultCodeMapper.FAILURE);
					}
					return resultStatus;
				  }
				//get voters count by cast category like OC BC SC ST from intermediate table by location level(constituency,mandal,booth etc) 
					public void getCastCategoryWiseVotersCountByPublicationIdInALocationFromIntermediateTable(Long userId,Long levelId,Long levelValue,Long publicationDateId,Long constituencyId,VoterCastInfoVO voterCastInfoVO)
					{
						final List<SelectOptionVO> castCategoryWiseList = new ArrayList<SelectOptionVO>(0);
						try{
							final List<VoterCastBasicInfo>  voterCastBasicInfoList =  voterCastBasicInfoDAO.getVotersCastBasicInfo(levelId,levelValue,constituencyId,publicationDateId,userId);
							if(voterCastBasicInfoList != null && !voterCastBasicInfoList.isEmpty() && voterCastBasicInfoList.get(0) != null){
								voterCastInfoVO.setDataPresent(true);
								final VoterCastBasicInfo voterCastBasicInfo = voterCastBasicInfoList.get(0);
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getBcVoters(),"BC"));
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getOcVoters(),"OC"));
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getScVoters(),"SC"));
									castCategoryWiseList.add(new SelectOptionVO(voterCastBasicInfo.getStVoters(),"ST "));
							}
							voterCastInfoVO.setCastCategoryWiseVotersList(castCategoryWiseList);
							
						}catch (Exception e) {
							LOG.error("Exception Occured in getCastCategoryWiseVotersCountByPublicationIdInALocation() Method, Exception is - ",e);
							
						}
					}
					
					// getting cast wise voters information for a user
					public void  getVotersCastWiseDetailsInALocationFromIntermediateTable(Long userId,Long reportLvlId,Long locationId,Long publicationDateId,Long constituencyId,VoterCastInfoVO voterCastInfoVO){
						getCastCategoryWiseVotersCountByPublicationIdInALocationFromIntermediateTable(userId,reportLvlId,locationId,publicationDateId,constituencyId,voterCastInfoVO);
						//if(voterCastInfoVO.isDataPresent()){
						  voterCastInfoVO.setVoterCastInfoVOList(getCastNGenderWiseVotersCountByPublIdInALocFromIntermedTable(userId,reportLvlId,locationId,publicationDateId,constituencyId));
						/*}
						if(!voterCastInfoVO.isDataPresent()){
							final Long count = voterCastInfoDAO.getRecordsCountToCheckDataPresent(constituencyId);
							if(count != null && count.longValue() > 0l){
								voterCastInfoVO.setDataPresent(true);
							}
						}*/
					}
					
					//getting male and female voters count by cast wise from intermediate table by location level(constituency,mandal,booth etc) 
					public List<VoterCastInfoVO> getCastNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,Long constituencyId)
					{
						final List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
						try{
							final List<VoterCastInfo> list = voterCastInfoDAO.getVotersCastInfo(reportLvlId, levelValue, constituencyId, publicationDateId, userId);
							
							VoterCastInfoVO voterCastInfoVO = null;
							for(VoterCastInfo voterCastInfo:list)
							{
								voterCastInfoVO = new VoterCastInfoVO();
								voterCastInfoVO.setCastName(voterCastInfo.getCasteState().getCaste().getCasteName());
								voterCastInfoVO.setMaleVoters(voterCastInfo.getCasteMaleVoters());
								voterCastInfoVO.setFemaleVoters(voterCastInfo.getCasteFemaleVoters());
								voterCastInfoVO.setTotalVoters(voterCastInfo.getCasteVoters());
								voterCastInfoVO.setCasteStateId(voterCastInfo.getCasteState().getCasteStateId());
								voterCastInfoVO.setVotesPercent(voterCastInfo.getCastePercentage().toString());
								voterCastInfoVO.setCasteCategoryName(voterCastInfo.getCasteState().getCasteCategoryGroup().getCasteCategory().getCategoryName());
								resultList.add(voterCastInfoVO);
							}
							
						}catch (Exception e) {
							LOG.error("Exception Occured in getCastNGenderWiseVotersCountByPublIdInALocFromIntermedTable() Method, Exception is - ",e);
						}
						return resultList;
					}
					
					//getting male and female voters count by party wise from intermediate table by location level(constituency,mandal,booth etc) 
					public void getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable(Long userId,Long reportLvlId,Long levelValue,Long publicationDateId,Long constituencyId,VoterCastInfoVO mainVO)
					{
						final List<VoterCastInfoVO> resultList = new ArrayList<VoterCastInfoVO>(0);
						try{
							final List<VoterPartyInfo> list = voterPartyInfoDAO.getVotersPartyInfo(reportLvlId, levelValue, constituencyId, publicationDateId, userId);
							
							if(list != null && !list.isEmpty() && list.get(0) != null){
								/*mainVO.setDataPresent(true);
							}else{
								final Long count = voterPartyInfoDAO.getRecordsCountToCheckDataPresent(constituencyId);
								if(count != null && count.longValue() > 0l){
									mainVO.setDataPresent(true);
								}
				                return;	
							}*/
								VoterCastInfoVO voterCastInfoVO = null;
								
								for(VoterPartyInfo voterPartyInfo:list)
								{
									voterCastInfoVO = new VoterCastInfoVO();
									voterCastInfoVO.setPartyName(voterPartyInfo.getParty().getShortName());
									voterCastInfoVO.setMaleVoters(voterPartyInfo.getPartyMaleVoters());
									voterCastInfoVO.setFemaleVoters(voterPartyInfo.getPartyFemaleVoters());
									voterCastInfoVO.setTotalVoters(voterPartyInfo.getPartyVoters());
									voterCastInfoVO.setPartyId(voterPartyInfo.getParty().getPartyId());
									voterCastInfoVO.setVotesPercent(voterPartyInfo.getPartyPercentage().toString());
									resultList.add(voterCastInfoVO);
								}
							}
							mainVO.setPartyWisevoterCastInfoVOList(resultList);
							
						}catch (Exception e) {
							LOG.error("Exception Occured in getPartyNGenderWiseVotersCountByPublIdInALocFromIntermedTable() Method, Exception is - ",e);
							
						}
					}
					
					public List<VoterCastInfoVO> getVotersCastInfoForMultipleMandal(List<SelectOptionVO> mandalsList,Long publicationDateId,Long userId,Long constituencyId){
						final Map<Long,String> mandalIds = new HashMap<Long,String>();
						final Map<Long,String> localBodyIds = new HashMap<Long,String>();
						final List<VoterCastInfoVO> mandalCasts = new ArrayList<VoterCastInfoVO>();
						for (SelectOptionVO mandal : mandalsList){
							if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2")){
								
							  mandalIds.put(Long.valueOf(mandal.getId().toString().trim().substring(1)),mandal.getName());
							
							}else{
							
								 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.valueOf(mandal.getId().toString().substring(1)));
								 localBodyIds.put(Long.valueOf(list.get(0).toString()),mandal.getName());
							}
							
						}
						
						if(!mandalIds.isEmpty()){
							Long reportLvlId = votersAnalysisService.getReportLevelId(IConstants.MANDAL);
							final List<VoterCastInfo> mandalWiseInfo =  voterCastInfoDAO.getVotersCastInfoByMultipleLevelValues(reportLvlId,mandalIds.keySet(),constituencyId,publicationDateId,userId);
							 if(mandalWiseInfo != null && !mandalWiseInfo.isEmpty()){
								 mandalCasts.addAll(populateCastDetailsToVo(mandalWiseInfo ,mandalIds,reportLvlId,publicationDateId,constituencyId));
							 }
						}
						if(!localBodyIds.isEmpty()){
							Long reportLvlId = votersAnalysisService.getReportLevelId("localElectionBody");
							final List<VoterCastInfo> lclBodyWiseInfo =  voterCastInfoDAO.getVotersCastInfoByMultipleLevelValues(reportLvlId,localBodyIds.keySet(),constituencyId,publicationDateId,userId);
							if(lclBodyWiseInfo != null && !lclBodyWiseInfo.isEmpty()){
								mandalCasts.addAll(populateCastDetailsToVo(lclBodyWiseInfo,localBodyIds,reportLvlId,publicationDateId,constituencyId));
						    }
						}
						return mandalCasts;
					}
					
					public List<VoterCastInfoVO> populateCastDetailsToVo(List<VoterCastInfo> castInfo ,Map<Long,String> mandalIds,Long reportLvlId,Long publicationDateId,Long constituencyId){
				        final Map<Long,VoterCastInfoVO> mandalMap = new HashMap<Long,VoterCastInfoVO>();
				        VoterCastInfoVO voterCastInfoVO = null;
				        VoterCastInfoVO voterCastInfoVO1 = null;
				        List<CastVO> castVOs = null;
				        CastVO castVO = null;
						for(VoterCastInfo voterCastInfo:castInfo){
							voterCastInfoVO = mandalMap.get(voterCastInfo.getReportLevelValue());
							
							if(voterCastInfoVO == null){
								voterCastInfoVO = new VoterCastInfoVO();
								voterCastInfoVO1 =  new VoterCastInfoVO();
								castVOs = new ArrayList<CastVO>();
								voterCastInfoVO1.setCastVOs(castVOs);
								voterCastInfoVO1.setTotalVoters(voterInfoDAO.getVotersCountInALocation(reportLvlId,voterCastInfo.getReportLevelValue(),publicationDateId,constituencyId));
								voterCastInfoVO.setVoterCastInfoVO(voterCastInfoVO1);
								voterCastInfoVO.setMandalName(mandalIds.get(voterCastInfo.getReportLevelValue()));
								voterCastInfoVO.setLocationId(voterCastInfo.getReportLevelValue());
								mandalMap.put(voterCastInfo.getReportLevelValue(), voterCastInfoVO);
							}else{
								voterCastInfoVO1 = voterCastInfoVO.getVoterCastInfoVO();
								castVOs = voterCastInfoVO1.getCastVOs();
							}
							castVO = new CastVO();
							//castvo.setCasteCategoryName("");
							castVO.setCastName(voterCastInfo.getCasteState().getCaste().getCasteName());
							castVO.setCastStateId(voterCastInfo.getCasteState().getCasteStateId());
							castVO.setCastCount(voterCastInfo.getCasteVoters());
							castVO.setMalevoters(voterCastInfo.getCasteMaleVoters());
							castVO.setFemalevoters(voterCastInfo.getCasteFemaleVoters());
							castVO.setCasteCategoryName(voterCastInfo.getCasteState().getCasteCategoryGroup().getCasteCategory().getCategoryName());
							castVO.setCastPercentage(voterCastInfo.getCastePercentage().toString());
							castVOs.add(castVO);
						}
						
						return new ArrayList<VoterCastInfoVO>(mandalMap.values());
					}
					
					public List<VoterCastInfoVO> getVotersCastInfoForMultipleValues(List<SelectOptionVO> subList,Long publicationDateId,Long userId,Long constituencyId,Long locationLvl){
						Map<Long,String> ids = new HashMap<Long,String>();
						
						List<VoterCastInfoVO> castList = new ArrayList<VoterCastInfoVO>();
						for (SelectOptionVO id : subList){
							ids.put(id.getId(),id.getName());
						}
						
						if(!ids.isEmpty()){
							List<VoterCastInfo> mandalWiseInfo =  voterCastInfoDAO.getVotersCastInfoByMultipleLevelValues(locationLvl,ids.keySet(),constituencyId,publicationDateId,userId);
							 if(mandalWiseInfo != null && !mandalWiseInfo.isEmpty()){
								 castList.addAll(populateCastDetailsToVo(mandalWiseInfo ,ids,locationLvl,publicationDateId,constituencyId));
							 }
						}
						
						return castList;
					}
					public List<VoterPartyInfo> getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(Long userId,String locationType,List<Long> locationIds,Long publicationDateId,Long constituencyId)
					{
						List<VoterPartyInfo> resultList = new ArrayList<VoterPartyInfo>();
						
						Map<Long,Map<Long,VoterPartyInfo>> locationsMap = new HashMap<Long,Map<Long,VoterPartyInfo>>();
						try{
							List<Object[]> list = boothPublicationVoterDAO.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,locationType,locationIds,publicationDateId,constituencyId);
							
							if(list != null && list.size() > 0)
							{
								VoterPartyInfo voterPartyInfo = null;
								Map<Long,VoterPartyInfo> partiesMap = null;
								Map<Long,Party> parties = new HashMap<Long,Party>();
								Map<Long,Long> locationTotalCount = new HashMap<Long,Long>();
								String location = locationType;
								if("localElectionBody".equalsIgnoreCase(location)){
									location = "Local Election Body";
								}
								VoterReportLevel voterReportLevel = voterReportLevelDAO.getReportLevelByType(location);
								
								for(Object[] params : list)
								{
									partiesMap = locationsMap.get((Long)params[4]);
									if(partiesMap == null){
										partiesMap = new HashMap<Long,VoterPartyInfo>();
										locationsMap.put((Long)params[4], partiesMap);
									}
									
									voterPartyInfo = partiesMap.get((Long)params[3]);
									
									if(voterPartyInfo == null){
										voterPartyInfo = new VoterPartyInfo();
										Party party = parties.get((Long)params[3]);
										if(party == null)
											party = partyDAO.get((Long)params[3]);
										voterPartyInfo.setParty(party);
										voterPartyInfo.setUserId(userId);
										voterPartyInfo.setVoterReportLevel(voterReportLevel);
										voterPartyInfo.setReportLevelValue((Long)params[4]);
										voterPartyInfo.setConstituencyId(constituencyId);
										voterPartyInfo.setPublicationDateId(publicationDateId);
										partiesMap.put((Long)params[3], voterPartyInfo);
									}
									
									String gender = params[1].toString();
									
									if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")){
										voterPartyInfo.setPartyMaleVoters((Long)params[2]);
										
									}else if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")){
										voterPartyInfo.setPartyFemaleVoters((Long)params[2]);
										
									}
									if(voterPartyInfo.getPartyVoters() != null){
										voterPartyInfo.setPartyVoters(voterPartyInfo.getPartyVoters()+(Long)params[2]);
									}else{
										voterPartyInfo.setPartyVoters((Long)params[2]);
									}
									if(locationTotalCount.get((Long)params[4]) != null){
										locationTotalCount.put((Long)params[4],(locationTotalCount.get((Long)params[4])+(Long)params[2]));
									}else{
										locationTotalCount.put((Long)params[4],(Long)params[2]);
									}
									
								}
								
								if(locationsMap.size() > 0){
									for(Long locationKey:locationsMap.keySet()){
										partiesMap = locationsMap.get(locationKey);
										Long locationCount = locationTotalCount.get(locationKey);
										if(partiesMap.size() > 0){
											resultList.addAll(partiesMap.values());
											for(Long partyKey:partiesMap.keySet()){
												voterPartyInfo = partiesMap.get(partyKey);
												if(voterPartyInfo.getPartyFemaleVoters() == null)
													voterPartyInfo.setPartyFemaleVoters(0l);
												if(voterPartyInfo.getPartyMaleVoters() == null)
													voterPartyInfo.setPartyMaleVoters(0l);
												if(voterPartyInfo.getPartyVoters() == null)
													voterPartyInfo.setPartyVoters(0l);
												String percentage = "0.00";
												try{
													percentage = (new BigDecimal(voterPartyInfo.getPartyVoters()*(100.0)/locationCount.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
												}catch (Exception e) {}
												finally{
													voterPartyInfo.setPartyPercentage(Double.parseDouble(percentage));
												}
											}
										}
									}
								}
								
							}
							return resultList;
						}catch (Exception e) {
							LOG.error("Exception Occured in getCastAndGenderWiseVotersCountByPublicationIdInALocation() Method, Exception is - ",e);
							return resultList;
						}
					}
					
					public void saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(Long userId,String locationType,Map<Long,Long> locationIds,Long publicationDateId,Long constituencyId)
					{
						List<VoterCastInfo> resultList = new ArrayList<VoterCastInfo>();
						Map<Long,VoterCastBasicInfo> casteBasicInfoMap = new HashMap<Long,VoterCastBasicInfo>();
						VoterCastBasicInfo voterCastBasicInfo = null;
						Constituency constituency = constituencyDAO.get(constituencyId);
						Map<Long,Map<Long,VoterCastInfo>> locationsMap = new HashMap<Long,Map<Long,VoterCastInfo>>();
						try{
							List<Long> ids = new ArrayList<Long>();
							ids.addAll(locationIds.keySet());
							List<Object[]> list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdMultipleALocation(userId,locationType,ids,publicationDateId,constituencyId);
							String location = locationType;
							if("localElectionBody".equalsIgnoreCase(location)){
								location = "Local Election Body";
							}
							VoterReportLevel voterReportLevel = voterReportLevelDAO.getReportLevelByType(location);
							
							for(Long id:ids){
								Long totalVoters = voterInfoDAO.getVotersCountInALocation(voterReportLevel.getVoterReportLevelId(),id,publicationDateId,constituencyId);
								if(totalVoters == null)
									totalVoters = 0l;
								voterCastBasicInfo = new VoterCastBasicInfo();
								voterCastBasicInfo.setVoterReportLevel(voterReportLevel);
								voterCastBasicInfo.setReportLevelValue(id);
								voterCastBasicInfo.setUserId(userId);
								voterCastBasicInfo.setPublicationDateId(publicationDateId);
								voterCastBasicInfo.setConstituency(constituency);
								voterCastBasicInfo.setOcVoters(0l);
								voterCastBasicInfo.setBcVoters(0l);
								voterCastBasicInfo.setScVoters(0l);
								voterCastBasicInfo.setStVoters(0l);
								voterCastBasicInfo.setTotalCastes(0l);
								voterCastBasicInfo.setCasteNotAssignedVoters(totalVoters);
								voterCastBasicInfo.setCasteAssignedVoters(0l);
								casteBasicInfoMap.put(id,voterCastBasicInfo);
							}
							
							if(list != null && list.size() > 0)
							{
								VoterCastInfo voterCastInfo = null;
								Map<Long,VoterCastInfo> casteMap = null;
								
								
								Map<Long,Long> locationTotalCount = new HashMap<Long,Long>();
								
								for(Object[] params : list)
								{
									casteMap = locationsMap.get((Long)params[5]);
									voterCastBasicInfo = casteBasicInfoMap.get((Long)params[5]);
									if(voterCastBasicInfo == null){
										voterCastBasicInfo = new VoterCastBasicInfo();
										voterCastBasicInfo.setVoterReportLevel(voterReportLevel);
										voterCastBasicInfo.setReportLevelValue((Long)params[5]);
										voterCastBasicInfo.setUserId(userId);
										voterCastBasicInfo.setPublicationDateId(publicationDateId);
										voterCastBasicInfo.setConstituency(constituency);
										voterCastBasicInfo.setOcVoters(0l);
										voterCastBasicInfo.setBcVoters(0l);
										voterCastBasicInfo.setScVoters(0l);
										voterCastBasicInfo.setStVoters(0l);
										voterCastBasicInfo.setTotalCastes(0l);
										Long totalVoters = voterInfoDAO.getVotersCountInALocation(voterReportLevel.getVoterReportLevelId(),(Long)params[5],publicationDateId,constituencyId);
										if(totalVoters == null)
											totalVoters = 0l;
										voterCastBasicInfo.setCasteNotAssignedVoters(totalVoters);
										voterCastBasicInfo.setCasteAssignedVoters(0l);
										casteBasicInfoMap.put((Long)params[5],voterCastBasicInfo);
									}
									if(casteMap == null){
										casteMap = new HashMap<Long,VoterCastInfo>();
										locationsMap.put((Long)params[5], casteMap);
									}
									
									voterCastInfo = casteMap.get((Long)params[3]);
									
									if(voterCastInfo == null){
										voterCastInfo = new VoterCastInfo();
										if(params[0] != null)
										 voterCastInfo.setCasteState((CasteState)params[0]);
										voterCastInfo.setUserId(userId);
										voterCastInfo.setVoterReportLevel(voterReportLevel);
										voterCastInfo.setReportLevelValue((Long)params[5]);
										voterCastInfo.setConstituency(constituency);
										voterCastInfo.setPublicationDateId(publicationDateId);
										casteMap.put((Long)params[3], voterCastInfo);
									}
									
									String gender = params[1].toString();
									
									if(gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("Male")){
										voterCastInfo.setCasteMaleVoters((Long)params[2]);
										
									}else if(gender.equalsIgnoreCase("F") || gender.equalsIgnoreCase("Female")){
										voterCastInfo.setCasteFemaleVoters((Long)params[2]);
										
									}
									setRemainingFields(voterCastInfo,voterCastBasicInfo,params,locationTotalCount);
									
									
									
								}
								
								if(locationsMap.size() > 0){
									for(Long locationKey:locationsMap.keySet()){
										casteMap = locationsMap.get(locationKey);
										
										Long locationCount = locationTotalCount.get(locationKey);
										Long parentCount =  locationIds.get(locationKey);
										if(casteMap.size() > 0){
											voterCastBasicInfo = casteBasicInfoMap.get(locationKey);
											voterCastBasicInfo.setTotalCastes(new Long(casteMap.size()));
											voterCastBasicInfo.setCasteAssignedVoters(voterCastBasicInfo.getOcVoters()+voterCastBasicInfo.getBcVoters()+voterCastBasicInfo.getScVoters()+voterCastBasicInfo.getStVoters());
											//Long totalVoters = voterInfoDAO.getVotersCountInALocation(voterReportLevel.getVoterReportLevelId(),locationKey,publicationDateId,constituencyId);
											//if(totalVoters != null)
											 voterCastBasicInfo.setCasteNotAssignedVoters(voterCastBasicInfo.getCasteNotAssignedVoters() -voterCastBasicInfo.getCasteAssignedVoters());
											resultList.addAll(casteMap.values());
											for(Long casteKey:casteMap.keySet()){
												voterCastInfo = casteMap.get(casteKey);
												if(voterCastInfo.getCasteFemaleVoters() == null)
													voterCastInfo.setCasteFemaleVoters(0l);
												if(voterCastInfo.getCasteMaleVoters() == null)
													voterCastInfo.setCasteMaleVoters(0l);
												if(voterCastInfo.getCasteVoters() == null)
													voterCastInfo.setCasteVoters(0l);
												String percentage = "0.00";
												String subLvlpercentage = "0.00";
												try{
												   if(voterCastInfo.getCasteVoters() != null){
													 if(locationCount != null && locationCount > 0l)
													   percentage = (new BigDecimal(voterCastInfo.getCasteVoters()*(100.0)/locationCount.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
													 if(parentCount != null && parentCount > 0l)
													   subLvlpercentage = (new BigDecimal(voterCastInfo.getCasteVoters()*(100.0)/parentCount.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
													}
												}catch (Exception e) {}
												finally{
													voterCastInfo.setCastePercentage(Double.parseDouble(percentage));
													if(!"constituency".equalsIgnoreCase(locationType))
													   voterCastInfo.setSubLeveCastePercentage(Double.parseDouble(subLvlpercentage));
												}
											}
										}
									}
								}
								
							}
							
						}catch (Exception e) {
							LOG.error("Exception Occured in getCastAndGenderWiseVotersCountByPublicationIdInALocation() Method, Exception is - ",e);
						}
					   if(resultList != null && resultList.size() > 0){
						   voterCastInfoDAO.saveAllObjects(resultList);
					   }
					   if(casteBasicInfoMap != null && casteBasicInfoMap.size() > 0){
						   voterCastBasicInfoDAO.saveAllObjects(new ArrayList<VoterCastBasicInfo>(casteBasicInfoMap.values()));
					   }
					}
		 public void setRemainingFields(VoterCastInfo voterCastInfo,VoterCastBasicInfo voterCastBasicInfo,Object[] params,Map<Long,Long> locationTotalCount){
			    if(voterCastInfo.getCasteVoters() != null){
					voterCastInfo.setCasteVoters(voterCastInfo.getCasteVoters()+(Long)params[2]);
				}else{
					voterCastInfo.setCasteVoters((Long)params[2]);
				}
				if(locationTotalCount.get((Long)params[5]) != null){
					locationTotalCount.put((Long)params[5],(locationTotalCount.get((Long)params[5])+(Long)params[2]));
				}else{
					locationTotalCount.put((Long)params[5],(Long)params[2]);
				}
				if("OC".equalsIgnoreCase(params[4].toString()))
					voterCastBasicInfo.setOcVoters(voterCastBasicInfo.getOcVoters()+(Long)params[2]);
				if("BC".equalsIgnoreCase(params[4].toString()))
					voterCastBasicInfo.setBcVoters(voterCastBasicInfo.getBcVoters()+(Long)params[2]);
				if("SC".equalsIgnoreCase(params[4].toString()))
					voterCastBasicInfo.setScVoters(voterCastBasicInfo.getScVoters()+(Long)params[2]);
				if("ST".equalsIgnoreCase(params[4].toString()))
				    voterCastBasicInfo.setStVoters(voterCastBasicInfo.getStVoters()+(Long)params[2]);
			 
		 }

				  
				  public List<VoterVO> getVoterDetailsForAdminEdit(Long boothId,Long userId,Long startIndex,Long endIndex){
					 
					  List<VoterVO> resultvalue = new ArrayList<VoterVO>();
					  List<Object[]> serialNosAndVIdsList = new ArrayList<Object[]>(0);
					  Map<Long, Long> serialNoMap = new HashMap<Long, Long>(0);
					  List<Voter> votersDetails = new ArrayList<Voter>(0);
					  VoterVO voterVO = null;
					  Map<Long,VoterVO> voterMap = new HashMap<Long, VoterVO>();
					  Long totalCount = 0L;
					  long sNo = 0l;
					  serialNosAndVIdsList = boothPublicationVoterDAO.getVIdsAndSerialNoByBoothId(boothId,startIndex,endIndex);
					  if(serialNosAndVIdsList != null && serialNosAndVIdsList.size() > 0)
					  {
					  for(Object[] param : serialNosAndVIdsList)
					  serialNoMap.put((Long)param[0], (Long)param[1]);
					  }
					  Long count = new Long(0);
					  List<Long> voterIds = new ArrayList<Long>(0);

					  if(serialNosAndVIdsList != null && serialNosAndVIdsList.size() > 0){
					  for(Object[] param1 : serialNosAndVIdsList){
					  voterIds.add((Long) param1[0]);
					  }
					  }
					  
					  
					  if(voterIds !=null && voterIds.size() > 0){
						  votersDetails = voterDAO.getVoterInfoByVoterId(voterIds);
						  totalCount = new Long(votersDetails.size());
						  }
						  if(votersDetails != null && votersDetails.size() > 0){

							  for(Voter voters : votersDetails){

								  voterVO = new VoterVO();
								  voterVO.setsNo(++sNo);
								  if(voters.getVoterId()!= null)
								  voterVO.setVoterIds(voters.getVoterId());

								  if(voters.getName()!= null)
								  voterVO.setFirstName(voters.getName());

								  voterVO.setVoterId((++count)+"");

								  if(voters.getAge()!= null)
								  voterVO.setAge(voters.getAge());

								  if(voters.getGender()!= null)
								  voterVO.setGender(voters.getGender());

								  if(voters.getHouseNo()!= null)
								  voterVO.setHouseNo(voters.getHouseNo());

								  if(voters.getRelativeName()!= null)
								  voterVO.setRelativeFirstName(voters.getRelativeName());

								  if(voters.getRelationshipType()!= null)
								  voterVO.setRelationshipType(voters.getRelationshipType());

								 /* if(voters.getCast()!= null)
								  voterVO.setCast(voters.getCast());

								  if(voters.getCastCatagery()!= null)
								  voterVO.setCastCatagery(voters.getCastCatagery());*/

								  if(voters.getVoterIDCardNo()!= null)
								  voterVO.setVoterIDCardNo(voters.getVoterIDCardNo());


								  voterVO.setMobileNo(voters.getMobileNo()!=null ? voters.getMobileNo() :" ");

								  if(serialNoMap.get(voters.getVoterId())!= null)
								  voterVO.setSerialNo(serialNoMap.get(voters.getVoterId()));

								  if(voterVO!= null)
									  resultvalue.add(voterVO);
								   }
								  }
						  
					  return resultvalue;  
				  }
				  
			public VoterVO saveVoterDetailsList(List<VoterVO> voterIds,Long userId,Long boothId){
					  List<Long> serialnos = new ArrayList<Long>();
					  VoterVO voterVO = new VoterVO();
					  Voter voter = new Voter();
					  
					  for(VoterVO list : voterIds)
					  {
						  serialnos = boothPublicationVoterDAO.checkSerialNoandVoterIdDuplicates(list.getSerialNo(),new Long(list.getVoterId()),boothId);
							if( serialnos !=null && serialnos.size()>0){
								voterVO.setNumbers(serialnos);
							}
					  }
							if(voterVO.getNumbers() != null)
								return voterVO;
							else
						 for(VoterVO list : voterIds)
							 {	
						  voterVO.setVoterId(list.getVoterId());
						  voterVO.setSerialNo(list.getSerialNo());
						  Voter voterValue = voterDAO.getVoterByVoterID(new Long(list.getVoterId()));
						 
						  if(list.getFirstName() != null && !list.getFirstName().equalsIgnoreCase(""))
								voterValue.setName(list.getFirstName());
						  if(list.getVoterIDCardNo() != null && !list.getVoterIDCardNo().equalsIgnoreCase(""))
								voterValue.setVoterIDCardNo(list.getVoterIDCardNo());
						  if(list.getGender() != null && !list.getGender().equalsIgnoreCase(""))
								voterValue.setGender(list.getGender());
						  if(list.getAge() != null && list.getAge()> 0)
								voterValue.setAge(list.getAge());
						  if(list.getHouseNo() != null && !list.getHouseNo().equalsIgnoreCase(""))
								voterValue.setHouseNo(list.getHouseNo());
						  if(list.getRelativeFirstName() != null && !list.getRelativeFirstName().equalsIgnoreCase(""))
								voterValue.setRelativeName(list.getRelativeFirstName());
						  if(list.getRelationshipType() != null && !list.getRelationshipType().equalsIgnoreCase(""))
								voterValue.setRelationshipType(list.getRelationshipType());
								voterValue.setMobileNo(list.getMobileNo());
						  if(voterValue != null)
								voterDAO.save(voterValue);
				  
					  boothPublicationVoterDAO.updateSerialNoByVIdBId(voterVO.getSerialNo(),new Long(voterVO.getVoterId()),boothId);
							 }
					  	voterVO.setStatus( "success");
					  return voterVO;
				  }
				  
			public List<VoterHouseInfoVO> getVoterInfoByBIdandVId(List<VoterHouseInfoVO> votersList,Long publicationDateId){
					  List<VoterHouseInfoVO> voterHouseInfoVOList = new ArrayList<VoterHouseInfoVO>();
					  List<Long> voterIds =new ArrayList<Long>();
					  long count = 0;
					 for(VoterHouseInfoVO list : votersList)
					 {
						 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
						 voterHouseInfoVO.setVoterId(list.getVoterId());
						 voterHouseInfoVO.setBoothId(list.getBoothId());
						 voterIds.add(voterHouseInfoVO.getVoterId());
					 }
					 List<BoothPublicationVoter> values = boothPublicationVoterDAO.getAllVoterDetailsByVoterIds(voterIds,publicationDateId);
					 for(BoothPublicationVoter list : values)
					 {
						 VoterHouseInfoVO voterHouseInfoVO = new VoterHouseInfoVO();
						 	 voterHouseInfoVO.setCount(++count);
						 if(list.getSerialNo() != null)
							 voterHouseInfoVO.setsNo(list.getSerialNo());
						 if(list.getBoothId() != null)
							 voterHouseInfoVO.setBoothId(list.getBoothId());
						 if(list.getVoter().getName() != null)
						 voterHouseInfoVO.setName(list.getVoter().getName());
						 if(list.getVoter().getVoterIDCardNo() != null)
						 voterHouseInfoVO.setVoterIdCardNo(list.getVoter().getVoterIDCardNo());
						 if( list.getVoter().getHouseNo() != null)
						 voterHouseInfoVO.setHouseNo(list.getVoter().getHouseNo());
						 if( list.getVoter().getAge() != null)
						 voterHouseInfoVO.setAge(new Long(list.getVoter().getAge()));
						 /*if( list.getVoter().getCast() != null)
						 voterHouseInfoVO.setCast(list.getVoter().getCast());*/
						 if( list.getVoter().getVoterId() != null)
						 voterHouseInfoVO.setVoterId(new Long(list.getVoter().getVoterId()));
						 if( list.getVoter().getGender() != null)
						 voterHouseInfoVO.setGender(list.getVoter().getGender());
						 if( list.getVoter().getRelationshipType() != null)
						 voterHouseInfoVO.setRelationship(list.getVoter().getRelationshipType());
						 if( list.getVoter().getRelativeName() != null)
						 voterHouseInfoVO.setGaurdian(list.getVoter().getRelativeName());
						 voterHouseInfoVO.setMobileNo(list.getVoter().getMobileNo()!= null ? list.getVoter().getMobileNo() :" ");
						 if( voterHouseInfoVO != null)
						 voterHouseInfoVOList.add(voterHouseInfoVO);
					 }	
					 return voterHouseInfoVOList;
				  }
			public VoterVO saveVoterSearchDetailsList(List<VoterVO> voterIds,Long userId){
					  List<Long> serialnos = new ArrayList<Long>();
					  VoterVO voterVO = new VoterVO();
					  Voter voter = new Voter();
					  for(VoterVO list : voterIds)
					  {
						  serialnos = boothPublicationVoterDAO.checkSerialNoandVoterIdDuplicates(list.getSerialNo(),new Long(list.getVoterId()),list.getBoothId());
							if( serialnos !=null && serialnos.size()>0){
								voterVO.setNumbers(serialnos);
							}
					  }
						if(voterVO.getNumbers() != null)
							return voterVO;
						else
					 for(VoterVO list : voterIds)
						 {	
					  voterVO.setBoothId(list.getBoothId());
					  voterVO.setVoterId(list.getVoterId());
					  voterVO.setSerialNo(list.getSerialNo());
					  Voter voterValue = voterDAO.getVoterByVoterID(new Long(list.getVoterId()));
					 
					  if(list.getFirstName() != null && !list.getFirstName().equalsIgnoreCase(""))
							voterValue.setName(list.getFirstName());
					  if(list.getVoterIDCardNo() != null && !list.getVoterIDCardNo().equalsIgnoreCase(""))
							voterValue.setVoterIDCardNo(list.getVoterIDCardNo());
					  if(list.getGender() != null && !list.getGender().equalsIgnoreCase(""))
							voterValue.setGender(list.getGender());
					  if(list.getAge() != null && list.getAge()> 0)
							voterValue.setAge(list.getAge());
					  if(list.getHouseNo() != null && !list.getHouseNo().equalsIgnoreCase(""))
							voterValue.setHouseNo(list.getHouseNo());
					  if(list.getRelativeFirstName() != null && !list.getRelativeFirstName().equalsIgnoreCase(""))
							voterValue.setRelativeName(list.getRelativeFirstName());
					  if(list.getRelationshipType() != null && !list.getRelationshipType().equalsIgnoreCase(""))
							voterValue.setRelationshipType(list.getRelationshipType());
							voterValue.setMobileNo(list.getMobileNo());
					  if(voterValue != null)
							voterDAO.save(voterValue);
			  
				  boothPublicationVoterDAO.updateSerialNoByVIdBId(voterVO.getSerialNo(),new Long(voterVO.getVoterId()),voterVO.getBoothId());
						 }
				  	voterVO.setStatus( "success");
				  return voterVO;
				  }
			 public List<SelectOptionVO> getWardsInMunicipality(Long lclElecBodyId,Long publicationDateId){
				 List<SelectOptionVO> list = new ArrayList<SelectOptionVO>();
				
				 try{
					 List<Object[]> wards = wardDAO.findByWardsByAssemblyLocalElectionBodyId(lclElecBodyId, publicationDateId);
			 for(Object[] ward:wards){
				 SelectOptionVO  selectOptionVO = new SelectOptionVO();
				
				 selectOptionVO.setId((Long)ward[0]);
				 selectOptionVO.setName(ward[1]!=null? ward[1].toString():"");
				 selectOptionVO.setValue(ward[1].toString());
				 List<SelectOptionVO> list2 = votersAnalysisService.getBoothForWard((Long)ward[0],publicationDateId);
					if(list2 != null && list2.size()>0)
						selectOptionVO.setSelectOptionsList(list2);
					
						 list.add(selectOptionVO);
			 }
				
				 }catch(Exception e){
					 LOG.error("Exception rised in getwardsInMunicipality ",e);
				}
				 return list;
				 
			 }
			 
			 public List<VoterCastInfoVO> getVoterAttributeDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
				 Map<Long,VoterCastInfoVO> category = new HashMap<Long,VoterCastInfoVO>();
				 try{
					 Map<Long,Map<Long,VoterCastInfoVO>> categoryValues = new HashMap<Long,Map<Long,VoterCastInfoVO>>();
					 List<Object[]> categoriesAndValues = userVoterCategoryValueDAO.getCatergoryAndValues(attributeIds,userId);
					 String locationType1 = locationType;
					 Long totalVoters = 0l;
					 if(locationType.equalsIgnoreCase("mandal"))
						{
							String mandalId= locationId.toString();
							String id=mandalId.substring(1);
							locationId = new Long(id);
							if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("2")){
								locationType = "mandal";
								locationType1 = "mandal";
							}else if(mandalId.toString().substring(0,1).trim().equalsIgnoreCase("1")){
								locationType = "localElectionBody";
								locationType1 = IConstants.LOCALELECTIONBODY;
								List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationId);
								locationId = (Long) list.get(0);
							}
						}
					 if(locationType.equalsIgnoreCase("hamlet")){
							
							List<Long> hamlets = new ArrayList<Long>();
							hamlets.add(locationId);
						List<Long> voterIds = boothPublicationVoterDAO.getVoterIdsForuserByHamletIds(userId , hamlets);
					    if(voterIds != null && voterIds.size() >0){	
						    List<Long> countList = 	boothPublicationVoterDAO.getTotalVotersCountForHamletByVoterIds(voterIds,publicationId);
						    if(countList != null && countList.size() >0)
							 totalVoters = countList.get(0);
					    }
					}
					 
					 if(locationType.equalsIgnoreCase("customWard")){
							
						 VotersInfoForMandalVO votersInfoForMandalVO = votersAnalysisService.getVotersCountForCustomWard(locationId, publicationId, "customWard");
						
					    if(votersInfoForMandalVO.getTotVoters() != null){	
							 totalVoters = votersInfoForMandalVO.getTotVoters().longValue();
					    }
					}
					 if(!locationType.equalsIgnoreCase("hamlet") && !locationType.equalsIgnoreCase("customWard")){
					  Long reportLvlId = votersAnalysisService.getReportLevelId(locationType1);
					  totalVoters = voterInfoDAO.getVotersCountInALocation(reportLvlId,locationId,publicationId,constituencyId);
					 }
					 
					  Map<Long,VoterCastInfoVO> categoryValue = null;
					 VoterCastInfoVO voterCastInfoVO = null;
					 
					 for(Object[] value:categoriesAndValues){
						 if(categoryValues.get((Long)value[0]) == null){
							 voterCastInfoVO = new VoterCastInfoVO();
							 voterCastInfoVO.setId((Long)value[0]);
							 voterCastInfoVO.setName(value[1] != null?value[1].toString():"");
							 if(totalVoters != null)
							 voterCastInfoVO.setTotalVoters(totalVoters);
							 category.put((Long)value[0],voterCastInfoVO);
							 categoryValue = new HashMap<Long,VoterCastInfoVO>();
							 categoryValues.put((Long)value[0], categoryValue);
						 }else{
							 categoryValue = categoryValues.get((Long)value[0]);
						 }
						 
						 if(categoryValue.get((Long)value[2]) == null){
							 voterCastInfoVO = new VoterCastInfoVO();
							 categoryValue.put((Long)value[2], voterCastInfoVO);
						 }else{
							 voterCastInfoVO =  categoryValue.get((Long)value[2]);
						 }
						 
						 voterCastInfoVO.setId((Long)value[2]);
						 voterCastInfoVO.setName(value[3] != null?value[3].toString():"");
						 if(value[4] != null)
						 voterCastInfoVO.setOrderNo(new Long(value[4].toString()));
					 }
					 
					 List<Object[]> attributeValuesList = new ArrayList<Object[]>();
					 if(!locationType.equalsIgnoreCase("hamlet")  && !locationType.equalsIgnoreCase("customWard")){
						 attributeValuesList = boothPublicationVoterDAO.getVoterAttributeDetails(userId,attributeIds,locationType,locationId,constituencyId,publicationId);
					 }else{
						 attributeValuesList = boothPublicationVoterDAO.getVoterAttributeDetailsForHamlet(userId,attributeIds,locationType,locationId,constituencyId,publicationId);
					 }
					 
					 for(Object[] value:attributeValuesList){
						 categoryValue = categoryValues.get((Long)value[1]);
						 if(categoryValue != null){
							 voterCastInfoVO = categoryValue.get((Long)value[2]);
							 if(voterCastInfoVO != null){
								 if(value[3] != null){
									 if("M".equalsIgnoreCase(value[3].toString()) || "Male".equalsIgnoreCase(value[3].toString())){
										 voterCastInfoVO.setMaleVoters((Long)value[0]);
									 }
									 else if("F".equalsIgnoreCase(value[3].toString()) || "Female".equalsIgnoreCase(value[3].toString())){
										 voterCastInfoVO.setFemaleVoters((Long)value[0]);
									 }
								 }
								 voterCastInfoVO.setTotalVoters(voterCastInfoVO.getTotalVoters()+(Long)value[0]);
								 voterCastInfoVO = category.get((Long)value[1]);
								 if(voterCastInfoVO != null){
									 voterCastInfoVO.setPartyWiseAssignedVoters(voterCastInfoVO.getPartyWiseAssignedVoters()+(Long)value[0]);
								 }
							 }
						 }
					 }
					 
					 for(Long categoryKey:categoryValues.keySet()){
						 Map<Long,VoterCastInfoVO> mainCategory1 = categoryValues.get(categoryKey);
						 VoterCastInfoVO mainCategory2 = category.get(categoryKey);
						 mainCategory2.setPartyWiseNotAssignedVoters(mainCategory2.getTotalVoters().longValue() - mainCategory2.getPartyWiseAssignedVoters());
						 for(Long categoryValueKey:mainCategory1.keySet()){
							 voterCastInfoVO = mainCategory1.get(categoryValueKey);
							 if( mainCategory2.getPartyWiseAssignedVoters().longValue() > 0l){
								 voterCastInfoVO.setPartyPercentage(Double.parseDouble((new BigDecimal(voterCastInfoVO.getTotalVoters()*(100.0)/mainCategory2.getPartyWiseAssignedVoters().doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
							 }else{
								 voterCastInfoVO.setPartyPercentage(Double.parseDouble("0.00"));
							 }
						 }
						 mainCategory2.setPartyWisevoterCastInfoVOList(new ArrayList<VoterCastInfoVO>(mainCategory1.values()));
					 }
				 }catch(Exception e){
					 LOG.error("Exception rised in getVoterAttributeDetails ",e);
				 }
				 return new ArrayList<VoterCastInfoVO>(category.values());
			 }
			 
			 public List<VoterCastInfoVO> getVoterAttributeSubDetails(Long userId,List<Long> attributeIds,String locationType,Long locationId,Long constituencyId,Long publicationId){
				 
				 List<Object[]> categoriesAndValues = userVoterCategoryValueDAO.getCatergoryAndValues(attributeIds,userId);
				 if(categoriesAndValues != null && categoriesAndValues.size() > 0){
					 if("constituency".equalsIgnoreCase(locationType)){
						 return getMandalAndMunicipalDetailsForConstituency(userId,attributeIds.get(0),constituencyId,publicationId,categoriesAndValues);
					 }else if("mandal".equalsIgnoreCase(locationType)){
						 return getWardsAndPanchayatDetailsForMandal(userId, attributeIds.get(0),locationId,publicationId,constituencyId,categoriesAndValues);
					 }else if("panchayat".equalsIgnoreCase(locationType)){
						 return getBoothsInPanchayat(userId,attributeIds.get(0),locationId,publicationId,constituencyId,categoriesAndValues);
					 }else if("ward".equalsIgnoreCase(locationType)){
						 return getBoothsInWard(userId,attributeIds.get(0),locationId,publicationId,constituencyId,categoriesAndValues);
					 }
				 }
				 return null;
			 }
			 
			 public List<VoterCastInfoVO> getMandalAndMunicipalDetailsForConstituency(Long userId,Long attributeId,Long constituencyId,Long publicationId,List<Object[]> categoriesAndValues){
				 List<SelectOptionVO> mandalsList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
					Map<Long,String> mandalIds = new HashMap<Long,String>();
					Map<Long,String> localBodyIds = new HashMap<Long,String>();
					List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
					for (SelectOptionVO mandal : mandalsList){
						if(mandal.getId().toString().substring(0,1).equalsIgnoreCase("2"))
						mandalIds.put(new Long(mandal.getId().toString().trim().substring(1)),mandal.getName());
						else
						{
							List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(mandal.getId().toString().substring(1)));
							localBodyIds.put(new Long(list.get(0).toString()),mandal.getName());
						}
						
					}
					if(mandalIds.size() > 0){
					  List<Object[]> mandalValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"mandal", mandalIds.keySet(), constituencyId, publicationId);
					  returnVal.addAll(populateAttributesDetailToVo(mandalValuesList,mandalIds,userId,categoriesAndValues,"Mandal/Municipality"));
					}
					if(localBodyIds.size() > 0){
						 List<Object[]> localBodyValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"localElectionBody", localBodyIds.keySet(), constituencyId, publicationId);
						 returnVal.addAll(populateAttributesDetailToVo(localBodyValuesList,localBodyIds,userId,categoriesAndValues,"Mandal/Municipality"));
					}
					return returnVal;
			 }
			 
			 public List<VoterCastInfoVO> getWardsAndPanchayatDetailsForMandal(Long userId,Long attributeId,Long id,Long publicationDateId,Long constituencyId,List<Object[]> categoriesAndValues){
				 List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
				 if(id.toString().substring(0, 1).trim().equalsIgnoreCase("2"))
					{
						List<Object[]> panchayaties = panchayatDAO.getPanchayatsBymandalId(new Long(id.toString().substring(1).trim()));
						Map<Long,String> panchayatIds = new HashMap<Long,String>();
						for (Object[] panchayat : panchayaties){
							panchayatIds.put((Long)panchayat[0], panchayat[1]!= null?panchayat[1].toString():"");
						}
						if(panchayatIds.size() > 0){
							List<Object[]> panchayatValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"panchayat", panchayatIds.keySet(), constituencyId, publicationDateId);
							returnVal.addAll(populateAttributesDetailToVo(panchayatValuesList,panchayatIds,userId,categoriesAndValues,"Panchayat"));
						} 
					}
					else{
						List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().substring(1).trim()));
						List<Object[]> wardsList = boothDAO.getWardsByLocalElecBodyId((Long) list.get(0),publicationDateId,constituencyId);
						
						Map<Long,String> wardIds = new HashMap<Long,String>();
						for (Object[] ward : wardsList){
							wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
						}
						if(wardIds.size() > 0){
							List<Object[]> wardValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"ward", wardIds.keySet(), constituencyId, publicationDateId);
							returnVal.addAll(populateAttributesDetailToVo(wardValuesList,wardIds,userId,categoriesAndValues,"Ward"));
						}else{
							List<Object[]> wards = userVoterDetailsDAO.getWardsBYLocalElectionBodyId((Long) list.get(0) ,publicationDateId ,userId);
							for (Object[] ward : wards){
								wardIds.put((Long)ward[0], ward[1]!= null?ward[1].toString():"");
							}
							List<Object[]> wardValuesList = boothPublicationVoterDAO.getVoterAttributeDetailsForHamletForDifferentLocations(userId, attributeId,"customWard",wardIds.keySet(),constituencyId,publicationDateId);
							returnVal.addAll(populateAttributesDetailToVo(wardValuesList,wardIds,userId,categoriesAndValues,"Ward"));
						}
					}
				 return returnVal;
			 } 
			 
			 public List<VoterCastInfoVO> getBoothsInWard(Long userId,Long attributeId,Long id,Long publicationDateId,Long constituencyId,List<Object[]> categoriesAndValues){
				 List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
				 List<Object[]> boothsList = boothDAO.getBoothsForWard(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0){
						List<Object[]> boothValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"booth", boothIds.keySet(), constituencyId, publicationDateId);
						returnVal.addAll(populateAttributesDetailToVo(boothValuesList,boothIds,userId,categoriesAndValues,"Booth"));
					}
				return returnVal;
			 } 
			 
			 public List<VoterCastInfoVO> getBoothsInPanchayat(Long userId,Long attributeId,Long id,Long publicationDateId,Long constituencyId,List<Object[]> categoriesAndValues){
				 List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>();
				 List<Object[]> boothsList = boothDAO.getBoothsInAPanchayat(id,publicationDateId);
					Map<Long,String> boothIds = new HashMap<Long,String>();
					for (Object[] booth : boothsList){
						boothIds.put((Long)booth[0], booth[1]!= null?("booth-"+booth[1].toString()):"");
					}
					if(boothIds.size() > 0){
						List<Object[]> boothValuesList =	boothPublicationVoterDAO.getVoterAttributeDetailsForDifferentLocations(userId, attributeId,"booth", boothIds.keySet(), constituencyId, publicationDateId);
						returnVal.addAll(populateAttributesDetailToVo(boothValuesList,boothIds,userId,categoriesAndValues,"Booth"));
					}
				return returnVal;
			 }
			 
			 public List<VoterCastInfoVO> populateAttributesDetailToVo(List<Object[]> attributeValuesList,Map<Long,String> locations,Long userId,List<Object[]> categoriesAndValues,String locationTyp){
				  Map<Long,VoterCastInfoVO> location = new HashMap<Long,VoterCastInfoVO>();
					 try{
						 Map<Long,Map<Long,VoterCastInfoVO>> locationValues = new HashMap<Long,Map<Long,VoterCastInfoVO>>();
						 
						  Map<Long,VoterCastInfoVO> locationValue = null;
						 VoterCastInfoVO voterCastInfoVO = null;
						 Double totalVoters = 0d;
						 for(Long locationId:locations.keySet()){
							
							 voterCastInfoVO = new VoterCastInfoVO();
							 voterCastInfoVO.setId(locationId);
							 voterCastInfoVO.setName(locations.get(locationId));
							 voterCastInfoVO.setMandalName(locationTyp);
							 voterCastInfoVO.setCastName((categoriesAndValues.get(0)[1]).toString());
							 location.put(locationId,voterCastInfoVO);
							 locationValue = new HashMap<Long,VoterCastInfoVO>();
							 locationValues.put(locationId, locationValue);
							 
						      for(Object[] value:categoriesAndValues){
								voterCastInfoVO = new VoterCastInfoVO();
								locationValue.put((Long)value[2], voterCastInfoVO);
								voterCastInfoVO.setId((Long)value[2]);
								voterCastInfoVO.setName(value[3] != null?value[3].toString():"");
						      }
						 
						 }
						 for(Object[] value:attributeValuesList){
							 locationValue = locationValues.get((Long)value[1]);
							 if(locationValue != null){
								 voterCastInfoVO = locationValue.get((Long)value[2]);
								 if(voterCastInfoVO != null){
									 if(value[3] != null){
										 if("M".equalsIgnoreCase(value[3].toString()) || "Male".equalsIgnoreCase(value[3].toString())){
											 voterCastInfoVO.setMaleVoters((Long)value[0]);
										 }
										 else if("F".equalsIgnoreCase(value[3].toString()) || "Female".equalsIgnoreCase(value[3].toString())){
											 voterCastInfoVO.setFemaleVoters((Long)value[0]);
										 }
									 }
									 voterCastInfoVO = location.get((Long)value[1]);
									 voterCastInfoVO.setTotalVoters(voterCastInfoVO.getTotalVoters()+(Long)value[0]);
									 totalVoters = totalVoters+((Long)value[0]).doubleValue();
								 }
							 }
						 }
						 
						 for(Long locationKey:location.keySet()){
							 Map<Long,VoterCastInfoVO> mainCategory1 = locationValues.get(locationKey);
							  voterCastInfoVO = location.get(locationKey);
								 if( totalVoters.longValue() > 0l){
									 voterCastInfoVO.setPartyPercentage(Double.parseDouble((new BigDecimal(voterCastInfoVO.getTotalVoters()*(100.0)/totalVoters.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString()));
								 }else{
									 voterCastInfoVO.setPartyPercentage(Double.parseDouble("0.00"));
								 }
								 List<VoterCastInfoVO> locationVals =  new ArrayList<VoterCastInfoVO>(mainCategory1.values());
								 Collections.sort(locationVals,sortCategoryValues);
								 voterCastInfoVO.setPartyWisevoterCastInfoVOList(locationVals);
						 }
					 }catch(Exception e){
						 LOG.error("Exception rised in populateAttributesDetailToVo ",e);
					 }
					  List<VoterCastInfoVO> returnVal = new ArrayList<VoterCastInfoVO>(location.values());
					  Collections.sort(returnVal,sortByLocation);
					 return returnVal;
			 }
			 
			 public static Comparator<VoterCastInfoVO> sortCategoryValues = new Comparator<VoterCastInfoVO>()
					    {
					   
					        public int compare(VoterCastInfoVO voterCastInfoVO1, VoterCastInfoVO voterCastInfoVO2)
					        {
					            return (voterCastInfoVO1.getId().intValue()) - (voterCastInfoVO2.getId().intValue());
					        }
					    };
			public static Comparator<VoterCastInfoVO> sortByLocation = new Comparator<VoterCastInfoVO>()
				{
							   
				 public int compare(VoterCastInfoVO voterCastInfoVO1, VoterCastInfoVO voterCastInfoVO2)
				{
		           return voterCastInfoVO1.getName().compareTo(voterCastInfoVO2.getName());
			    }
			 };
			 
		public List<SelectOptionVO> getPanchayatsByTehsilId(Long tehsilId){
			 List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
			 SelectOptionVO selectOptionVO = null;
			 try{
				 List<Object[]> panchayatiesList = panchayatDAO.getPanchayatsByTehsilId(tehsilId);
				 for(Object[] panchayat:panchayatiesList){
					 selectOptionVO = new SelectOptionVO((Long)panchayat[0],panchayat[1].toString());
					 returnVal.add(selectOptionVO);
				 }
			 }catch(Exception e){
				 LOG.error("Exception rised in getPanchayatsByTehsilId ",e);
			 }
			 returnVal.add(0,new SelectOptionVO(0l,"Select Location"));
			 return returnVal;
		}
		
		public List<SelectOptionVO> getBoothsByPanchayatIDConstiId(Long panchayatId,Long constituencyId){
			 List<SelectOptionVO> returnVal = new ArrayList<SelectOptionVO>();
			 SelectOptionVO selectOptionVO = null;
			 try{
				 Long publicationId = getLatestPublicationIdByConstiId(constituencyId);
				 List<Object[]> boothsList = boothDAO.getBoothsByPanchayatIDConstiId(panchayatId,constituencyId,publicationId);
				 for(Object[] booth : boothsList){
					 selectOptionVO = new SelectOptionVO((Long)booth[0],"Booth-"+booth[1].toString());
					 returnVal.add(selectOptionVO);
				 }
			 }catch(Exception e){
				 LOG.error("Exception rised in getBoothsByPanchayatIDConstiId ",e);
			 }
			 returnVal.add(0,new SelectOptionVO(0l,"Select Location"));
			 return returnVal;
		}
		
		public Long getLatestPublicationIdByConstiId(Long constituencyId){
			 return publicationDateDAO.getLatestPublicationIdByConstiId(constituencyId);
		}
		/**
		 * This Service is used for Getting All Selected User Categoery Volues Based on Selection
		 * @param Long userId
		 * @param List<Long> ids
		 * @param String type
		 * @param String status
		 * @param Long constituencyId
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getSelectedUserCategoeryDetails(Long userId,List<Long> ids , String type,String status,Long constituencyId,Long publicationId)
		{
			Map<Long,Long> attributeKeys = new HashMap<Long,Long>();
			List<SelectOptionVO> resultData = new ArrayList<SelectOptionVO>();
			SelectOptionVO selectOptionVO = null;
			try {
				LOG.debug("entered into getSelectedUserCategoeryDetails() method in VoterReportService");
				
				
				if(status.equalsIgnoreCase("cast") || status.equalsIgnoreCase("party"))
				{
					if(type.equalsIgnoreCase("mandal"))
					{
						List<Long> mandalIds      = new ArrayList<Long>();
						Long muncipalityId = 0l;
						for (Long values : ids) {
							Long mandalid = 0l;
							
							if(values.toString().substring(0,1).trim().equalsIgnoreCase("1"))
							{
								muncipalityId = Long.valueOf(values.toString().substring(1).trim());
							}
							else
							{
								mandalid =Long.valueOf(values.toString().substring(1).trim());
								mandalIds.add(mandalid);
							}
						}
						if(mandalIds != null && mandalIds.size() > 0)
						{
							//List<Object[]> castOrPartyDetails = boothPublicationVoterDAO.getPartysOrCatstesForSelectedLevel(userId,mandalIds,type,status);
							List<Object[]> castOrPartyDetails = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,2l,mandalIds,publicationId);
							if(castOrPartyDetails != null && castOrPartyDetails.size() > 0)
							{
								
								for (Object[] parms : castOrPartyDetails) {
								  if(attributeKeys.get((Long)parms[0]) == null){
									selectOptionVO = new SelectOptionVO();
									selectOptionVO.setId((Long)parms[0]);
									attributeKeys.put((Long)parms[0], (Long)parms[0]);
									selectOptionVO.setName(parms[1].toString());
									resultData.add(selectOptionVO);
								  }
								}
							}
						}
						if(muncipalityId != null && muncipalityId > 0)
						{
							List list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(muncipalityId);
							if(list != null)
							{
								Long localBodyId = (Long)list.get(0);
								List<Long> localBodyIds = new ArrayList<Long>();
								localBodyIds.add(localBodyId);
								//List<Object[]> castesList = boothPublicationVoterDAO.getCastesListForSelectedMuncipality(userId, localBodyId, constituencyId,status);
								List<Object[]> castesList = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId, 5l, localBodyIds,publicationId);
								for (Object[] parms : castesList) {
								 if(attributeKeys.get((Long)parms[0]) == null){
									 attributeKeys.put((Long)parms[0], (Long)parms[0]);
									selectOptionVO = new SelectOptionVO();
									selectOptionVO.setId((Long)parms[0]);
									selectOptionVO.setName(parms[1].toString());
									resultData.add(selectOptionVO);
								 }
								}
							}
						}
					}
					else if(type.equalsIgnoreCase("panchayat"))
					{
						//List<Object[]> castOrPartyDetails = boothPublicationVoterDAO.getPartysOrCatstesForSelectedLevel(userId,ids,type,status);
						List<Object[]> castOrPartyDetails = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,3l,ids,publicationId);
						if(castOrPartyDetails != null && castOrPartyDetails.size() > 0)
						{
							
							for (Object[] parms : castOrPartyDetails) {
							  if(attributeKeys.get((Long)parms[0]) == null){
								 attributeKeys.put((Long)parms[0], (Long)parms[0]);
								selectOptionVO = new SelectOptionVO();
								selectOptionVO.setId((Long)parms[0]);
								selectOptionVO.setName(parms[1].toString());
								resultData.add(selectOptionVO);
							  }
							}
						}
					}
					else if(type.equalsIgnoreCase("booth"))
					{
						List<Object[]> castOrPartyDetails = null;
						synchronized (this) {
							
						
						for(Long boIds : ids)
							queryTempDAO.save(new QueryTemp(boIds));
						voterDAO.flushAndclearSession();
						
						 castOrPartyDetails = boothPublicationVoterDAO.getCatstesForBooths(userId, ids, publicationId);
						queryTempDAO.deleteAll();
						voterDAO.flushAndclearSession();
						}
						//List<Object[]> castOrPartyDetails = boothPublicationVoterDAO.getPartysOrCatstesForSelectedLevel(userId,ids,type,status,publicationId);
						//List<Object[]> castOrPartyDetails = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,3l,ids);
						if(castOrPartyDetails != null && castOrPartyDetails.size() > 0)
						{
							
							for (Object[] parms : castOrPartyDetails) {
							 if(attributeKeys.get((Long)parms[0]) == null){
								attributeKeys.put((Long)parms[0], (Long)parms[0]);
								selectOptionVO = new SelectOptionVO();
								selectOptionVO.setId((Long)parms[0]);
								selectOptionVO.setName(parms[1].toString());
								resultData.add(selectOptionVO);
							 }
							}
						}
					}
					
					
				}
				else
				{
					Long categoeryId = Long.valueOf(status); 
					List<Object[]> categoeryValues = userVoterCategoryValueDAO.getCategoeryValuesDAO(userId, categoeryId);
					if(categoeryValues != null && categoeryValues.size() >0)
					{
						for (Object[] parms : categoeryValues) {
						 if(attributeKeys.get((Long)parms[0]) == null){
							attributeKeys.put((Long)parms[0], (Long)parms[0]);
							selectOptionVO = new SelectOptionVO();
							selectOptionVO.setId((Long)parms[0]);
							selectOptionVO.setName(parms[1].toString());
							resultData.add(selectOptionVO);
						 }
						}
					}
				}
			} catch (Exception e) {
				LOG.error("exception raised in  getSelectedUserCategoeryDetails() method in VoterReportService",e);
			}
			Collections.sort(resultData);
			return resultData;
		}
		/**
		 * This Service is Used For Getting All Wards in a Urban Constituency Selection
		 * @param Long constituencyId
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getAllWardsInUrbanConstituency(Long constituencyId,Long publicationId)
		{
			List<SelectOptionVO> returnData = new ArrayList<SelectOptionVO>();
			List result = assemblyLocalElectionBodyDAO.findByConstituencyId(constituencyId);
			Object[] localBodyObject = (Object[]) result.get(0);
			Long localBodyId = (Long) localBodyObject[0];
				List<Object[]> wardsList = wardDAO.getWardsListByLocalEleBodyIdAndConstituencyId(localBodyId,8l,constituencyId);
				if(wardsList != null && wardsList.size() > 0)
				{
					SelectOptionVO selectOptionVO = null;
					for (Object[] parms : wardsList) {
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						selectOptionVO.setName(parms[1].toString());
						returnData.add(selectOptionVO);
					}
					
				}
			
			return returnData;
		}
		/**
		 * This Service is used for getting All User Categiery Values for Selected Wards
		 * @param Long userId
		 * @param Long constituencyId
		 * @param List<Long> ids
		 * @param String status
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getUserCategoeryValuesForWards(Long userId,Long constituencyId,List<Long> ids ,String status,Long publicationId)
		{
			List<SelectOptionVO> returnData = new ArrayList<SelectOptionVO>();
			//List<Object[]> userCategoeres = boothPublicationVoterDAO.getAllCastesOrPartesForSelectedWards(userId,ids,constituencyId,status);
			List<Object[]> userCategoeres = voterCastInfoDAO.getCastAndPartyForSelectedLevel(userId,6l,ids,publicationId);
			if(userCategoeres != null && userCategoeres.size() > 0)
			{
				SelectOptionVO selectOptionVO = null;
				for (Object[] parms : userCategoeres) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					returnData.add(selectOptionVO);
				}
			}
			return returnData;
			
		}
		/**
		 * This Service is used for Getting All Booths In a Selectd Wards in Urban Constituency
		 * @param List<Long> ids
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getAllBoothsForSelectedWards(List<Long> ids,Long publicationId)
		{
			List<SelectOptionVO> returnData = new ArrayList<SelectOptionVO>();
			List<Object[]> boothsList = boothDAO.getBoothsForSelectedWards(ids,publicationId);
			{
				if(boothsList != null && boothsList.size() > 0)
				{
					SelectOptionVO selectOptionVO = null;
					for (Object[] parms : boothsList) {
						selectOptionVO = new SelectOptionVO();
						selectOptionVO.setId((Long)parms[0]);
						String name = "BOOTH-"+parms[1].toString();
						selectOptionVO.setName(name);
						returnData.add(selectOptionVO);
					}
				}
			}
			return returnData;
		}
		/**
		 * This Service is used For Getting All User Categoery values in a Selected Muncipal wards
		 * @param Long userId
		 * @param Long constituencyId
		 * @param String type
		 * @param List<Long> ids
		 * @return List<SelectOptionVO>
		 */
		public List<SelectOptionVO> getUserCategoeryValuesForMuncipalWards(Long userId,Long constituencyId,String type,List<Long> ids,Long publicationId)
		{
			List<SelectOptionVO> resultData = new ArrayList<SelectOptionVO>();
			List<Object[]> userCategValues = boothPublicationVoterDAO.getUserCategValuesForSelectedMuncipalWards(userId,constituencyId,type,ids,publicationId);
			if(userCategValues != null && userCategValues.size() > 0)
			{
				SelectOptionVO selectOptionVO = null;
				for (Object[] parms : userCategValues) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long)parms[0]);
					selectOptionVO.setName(parms[1].toString());
					resultData.add(selectOptionVO);
				}
			}
			return resultData;
		}
		
		/**
		 * This Service is used for getting Counts (Cadre,Influencing People and Candidate) For Selected Level(booth or Panchayat)
		 * @param Long userId
		 * @param Long id
		 * @param Long constituencyId
		 * @param String selLevel
		 * @param Long publicationDateId
		 * @return List<InfluencingPeopleBeanVO>
		 */
		public List<InfluencingPeopleBeanVO>  getCountsOfSelectedLevel(Long userId,Long id,Long constituencyId,String selLevel,Long publicationDateId)
		{
			List<InfluencingPeopleBeanVO> resultCount = new ArrayList<InfluencingPeopleBeanVO>();
			InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
			List<Long> boothids = new ArrayList<Long>();
			if(selLevel.equalsIgnoreCase("booth"))
			{
				boothids.add(id);
			}
			else if(selLevel.equalsIgnoreCase("panchayat"))
			{
				List<Object[]> booths = boothDAO.getBoothsInAPanchayat(id,publicationDateId); 
				if(booths != null && booths.size() > 0)
				{
					for (Object[] parms : booths) {
						boothids.add((Long)parms[0]);
					}
				}
			}
			
			
			List<Long> inluencingPeopleCount = boothPublicationVoterDAO.getInfluencingPeopleCountForSelectedLevel(boothids,constituencyId,userId);
			if(inluencingPeopleCount != null)
			{
				for (Long influeneCount : inluencingPeopleCount) {
					influencingPeopleBeanVO.setInfluencePeopleCount(influeneCount.longValue());
					//resultCount.add(influencingPeopleBeanVO);
				}
				
			}
			
			List<Long> cadreCount = boothPublicationVoterDAO.getCadreCountForSelectedLevel(boothids,constituencyId,userId);
			if(cadreCount != null && cadreCount.size() > 0)
			{
				for (Long countCadre : cadreCount) {
					influencingPeopleBeanVO.setCadreCount(countCadre.longValue());
					//resultCount.add(influencingPeopleBeanVO);
				}
			}
			
			List<Long> politicanCount = boothPublicationVoterDAO.getPoliticianCountForSelectedLevel(boothids,constituencyId);
			if(politicanCount != null && politicanCount.size() > 0)
			{
				for (Long countPolitican : politicanCount) {
					influencingPeopleBeanVO.setPoliticianCount(countPolitican.longValue());
					
				}
			}
			resultCount.add(influencingPeopleBeanVO);
			return resultCount;
		}
		
		/**
		 * This Service is used for Showing Details (Cadre,Influencing People and Candidate) For Selected Level(booth or Panchayat)
		 * @param Long userId
		 * @param Long id
		 * @param Long constituencyId
		 * @param String selLevel
		 * @param Long publicationDateId
		 * @param String type
		 * @param Integer startIndex
		 * @param Integer maxIndex
		 * @param String order
		 * @param String columnName
		 * @return List<VoterVO>
		 */
		public List<VoterVO> showVoterDetailsForSelcetedLevel(Long userId,Long id,Long constituencyId,String selLevel,Long publicationDateId,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
		{
			List<VoterVO> resultData = new ArrayList<VoterVO>();
			List<Long> boothids = new ArrayList<Long>();
			if(selLevel.equalsIgnoreCase("booth"))
			{
				boothids.add(id);
			}
			else if(selLevel.equalsIgnoreCase("panchayat"))
			{
				List<Object[]> booths = boothDAO.getBoothsInAPanchayat(id,publicationDateId); 
				if(booths != null && booths.size() > 0)
				{
					for (Object[] parms : booths) {
						boothids.add((Long)parms[0]);
					}
				}
			}
			if(type.equalsIgnoreCase("InfluencePeople"))
			{
				
				List<InfluencingPeople> influencingData = boothPublicationVoterDAO.getInfluencingPeopleDetailsForSelectedlevel(boothids,constituencyId,userId,startIndex,maxIndex,order,columnName);
				if(influencingData != null && influencingData.size() > 0)
				{
					List<Long> inluencingPeopleCount = boothPublicationVoterDAO.getInfluencingPeopleCountForSelectedLevel(boothids,constituencyId,userId);
					Long totalRecords = inluencingPeopleCount.get(0).longValue();
					resultData = storeInfluencingPeopleDetails(influencingData,selLevel,id,totalRecords);
				}
			}
			else if(type.equalsIgnoreCase("Cadre"))
			{
				List<Cadre> cadreDetails = boothPublicationVoterDAO.getCadreDetailsForSelectedlevel(boothids,constituencyId,userId,startIndex,maxIndex,order,columnName);
				if(cadreDetails != null && cadreDetails.size() > 0)
				{
					List<Long> cadreCount = boothPublicationVoterDAO.getCadreCountForSelectedLevel(boothids,constituencyId,userId);
					Long totalRecords = cadreCount.get(0).longValue();
					resultData = storeCadrePeopleDetails(cadreDetails,selLevel,id,totalRecords);
				}
				
			}
			else if(type.equalsIgnoreCase("Politician"))
			{
				List<Candidate> candidateDetails = boothPublicationVoterDAO.getPoliticanDetailsForSelectedlevel(boothids,constituencyId,startIndex,maxIndex,order,columnName);
				if(candidateDetails != null && candidateDetails.size() > 0)
				{
					List<Long> politicanCount = boothPublicationVoterDAO.getPoliticianCountForSelectedLevel(boothids,constituencyId);
					Long totalRecords = politicanCount.get(0).longValue();
					resultData = storeCandidateDetails(candidateDetails,selLevel,id,totalRecords);
				}
			}
			
			
			return resultData;
		}
		/**
		 * This Service is used for getting counts for (cadre,influencing people and candidate) for selected hamlet
		 * @param Long hamletId
		 * @param userId userId
		 * @return List<InfluencingPeopleBeanVO> resultCount
		 */
		public List<InfluencingPeopleBeanVO> getcountForSelectedTypeInHamlet(Long hamletId,Long userId,String selLevel)
		{
			List<InfluencingPeopleBeanVO> resultCount = new ArrayList<InfluencingPeopleBeanVO>();
			InfluencingPeopleBeanVO influencingPeopleBeanVO = new InfluencingPeopleBeanVO();
			
			List<Long> influencingPeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"InfluencePeople",selLevel);
			if(influencingPeopleCount != null && influencingPeopleCount.size() > 0)
			{
				for (Long influenceCount : influencingPeopleCount) {
					influencingPeopleBeanVO.setInfluencePeopleCount(influenceCount.longValue());
				}
			}
			
			List<Long> cadrePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Cadre",selLevel);
			if(cadrePeopleCount != null && cadrePeopleCount.size() > 0)
			{
				for (Long cadreCount : cadrePeopleCount) {
					influencingPeopleBeanVO.setCadreCount(cadreCount.longValue());
				}
			}
			
			List<Long> candidatePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Politician",selLevel);
			if(candidatePeopleCount != null && candidatePeopleCount.size() > 0)
			{
				for (Long candidateCount : candidatePeopleCount) {
					influencingPeopleBeanVO.setPoliticianCount(candidateCount.longValue());
				}
			}
			resultCount.add(influencingPeopleBeanVO);
			return resultCount;
		}
		
		/**
		 * This Service is used for Showing Details (Cadre,Influencing People and Candidate) For Selected Hamlet
		 * @param Long userId
		 * @param Long hamletId
		 * @param String selLevel
		 * @param String type
		 * @param Integer startIndex
		 * @param Integer maxIndex
		 * @param String order
		 * @param String columnName
		 * @return List<VoterVO>
		 */
		public List<VoterVO> showingVoterDetailsForSelectedHamlet(Long hamletId,Long userId,String selLevel,String type,Integer startIndex,Integer maxIndex,String order,String columnName)
		{
			List<VoterVO> resultList = null;
			
			if(type.equalsIgnoreCase("InfluencePeople"))
			{
				List<InfluencingPeople> influencingpeopleData = userVoterDetailsDAO.getInfluencingPeopleDetailsForSelectedHamlet(hamletId,userId,startIndex,maxIndex,order,columnName,selLevel);
				if(influencingpeopleData != null && influencingpeopleData.size() > 0)
				{
					List<Long> influencingPeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"InfluencePeople",selLevel);
					Long totalRecods = influencingPeopleCount.get(0).longValue();
					resultList = storeInfluencingPeopleDetails(influencingpeopleData,selLevel,hamletId,totalRecods);
				}
				
				
			}
			else if(type.equalsIgnoreCase("Cadre"))
			{
				List<Cadre> cadrepeopleData = userVoterDetailsDAO.getCadreDetailsForSelectedHamlet(hamletId,userId,startIndex,maxIndex,order,columnName,selLevel);
				if(cadrepeopleData != null && cadrepeopleData.size() > 0)
				{
					List<Long> cadrePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Cadre",selLevel);
					Long totalRecods = cadrePeopleCount.get(0).longValue();
					resultList = storeCadrePeopleDetails(cadrepeopleData,selLevel,hamletId,totalRecods);
				}
			}
			else if(type.equalsIgnoreCase("Politician"))
			{
				List<Candidate> candidatepeopleData = userVoterDetailsDAO.getCandidateDetailsForSelectedHamlet(hamletId,userId,startIndex,maxIndex,order,columnName,selLevel);
				if(candidatepeopleData != null && candidatepeopleData.size() > 0)
				{
					List<Long> candidatePeopleCount = userVoterDetailsDAO.getCountForSelectedTypeInHamlet(hamletId,userId,"Politician",selLevel);
					Long totalRecods = candidatePeopleCount.get(0).longValue();
					resultList = storeCandidateDetails(candidatepeopleData,selLevel,hamletId,totalRecods);
				}
			}
			return resultList;
		}
		/**
		 * This Service is used for storing all Influencing People Details into VoterVO.
		 * @param List<InfluencingPeople> influencingData
		 * @param String type
		 * @param Long id
		 * @return List<VoterVO>
		 */
		public List<VoterVO> storeInfluencingPeopleDetails(List<InfluencingPeople> influencingData,String type,Long id,Long totalRecords)
		{
			List<VoterVO> resultData = null;
			VoterVO voterVO = null;
			if(influencingData != null && influencingData.size() > 0)
			{
				resultData = new ArrayList<VoterVO>();
				Long count = 1l;
				for (InfluencingPeople influencingPeople : influencingData) {
						voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(influencingPeople.getVoter().getName());
						voterVO.setVoterIDCardNo(influencingPeople.getVoter().getVoterIDCardNo());
						voterVO.setGender(influencingPeople.getVoter().getGender());
						voterVO.setAge(influencingPeople.getVoter().getAge());
						voterVO.setHouseNo(influencingPeople.getVoter().getHouseNo());
						voterVO.setRelativeFirstName(influencingPeople.getVoter().getRelativeName());
						voterVO.setMobileNo(influencingPeople.getVoter().getMobileNo());
						++count;
						voterVO.setTotalVoters(totalRecords);
						voterVO.setInfluencingRange(influencingPeople.getInfluencingScope());
						voterVO.setInfluencingRegion(getRegionNameBasedOnScope(influencingPeople.getInfluencingScope(),influencingPeople.getInfluencingScopeValue()));
						if(type.equalsIgnoreCase("booth"))
						{
							Booth booth = boothDAO.get(id);
							voterVO.setLocalArea("BOOTH - " + booth.getPartNo());
						}
						else if(type.equalsIgnoreCase("panchayat"))
						{
							voterVO.setLocalArea(panchayatDAO.get(id).getPanchayatName().toString());
						}
						else if(type.equalsIgnoreCase("hamlet"))
						{
							voterVO.setLocalArea(hamletDAO.get(id).getHamletName().toString());
						}
						else if(type.equalsIgnoreCase("customWard"))
						{
							voterVO.setLocalArea(constituencyDAO.get(id).getName().toString());
						}
						resultData.add(voterVO);
				}
			}
			return resultData;
			
		}
		/**
		 * This Service is used for storing all Cadre People Details into VoterVO.
		 * @param List<Cadre> cadreDetails
		 * @param String type
		 * @param Long id
		 * @return List<VoterVO>
		 */
		public List<VoterVO> storeCadrePeopleDetails(List<Cadre> cadreDetails,String type,Long id,Long totalRecords)
		{
			List<VoterVO> resultData = null;
			VoterVO voterVO = null;
			if(cadreDetails != null && cadreDetails.size() > 0)
			{
				resultData = new ArrayList<VoterVO>();
				Long count = 1l;
				for (Cadre cadre : cadreDetails) {
						voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(cadre.getVoter().getName());
						voterVO.setVoterIDCardNo(cadre.getVoter().getVoterIDCardNo());
						voterVO.setGender(cadre.getVoter().getGender());
						voterVO.setAge(cadre.getVoter().getAge());
						voterVO.setHouseNo(cadre.getVoter().getHouseNo());
						voterVO.setRelativeFirstName(cadre.getVoter().getRelativeName());
						voterVO.setMobileNo(cadre.getVoter().getMobileNo());
						++count;
						voterVO.setTotalVoters(totalRecords);
						if(type.equalsIgnoreCase("booth"))
						{
							Booth booth = boothDAO.get(id);
							voterVO.setLocalArea("BOOTH - " + booth.getPartNo());
						}
						else if(type.equalsIgnoreCase("panchayat"))
						{
							voterVO.setLocalArea(panchayatDAO.get(id).getPanchayatName().toString());
						}
						else if(type.equalsIgnoreCase("hamlet"))
						{
							voterVO.setLocalArea(hamletDAO.get(id).getHamletName().toString());
						}
						else if(type.equalsIgnoreCase("customWard"))
						{
							voterVO.setLocalArea(constituencyDAO.get(id).getName().toString());
						}
						resultData.add(voterVO);
				}
			}
			return resultData;
		}
		/**
		 * This Service is used for storing all Candidates Details into VoterVO.
		 * @param List<Candidate> candidateDetails
		 * @param String type
		 * @param Long id
		 * @return List<VoterVO>
		 */
		public List<VoterVO> storeCandidateDetails(List<Candidate> candidateDetails,String type,Long id,Long totalRecords)
		{
			List<VoterVO> resultData = null;
			VoterVO voterVO = null;
			if(candidateDetails != null && candidateDetails.size() > 0)
			{
				resultData = new ArrayList<VoterVO>();
				Long count = 1l;
				for (Candidate candidate : candidateDetails) {
						voterVO = new VoterVO();
						voterVO.setVoterId((Long.valueOf(count).toString()));
						voterVO.setFirstName(candidate.getVoter().getName());
						voterVO.setVoterIDCardNo(candidate.getVoter().getVoterIDCardNo());
						voterVO.setGender(candidate.getVoter().getGender());
						voterVO.setAge(candidate.getVoter().getAge());
						voterVO.setHouseNo(candidate.getVoter().getHouseNo());
						voterVO.setRelativeFirstName(candidate.getVoter().getRelativeName());
						voterVO.setMobileNo(candidate.getVoter().getMobileNo());
						voterVO.setTotalVoters(totalRecords);
						++count;
						if(type.equalsIgnoreCase("booth"))
						{
							Booth booth = boothDAO.get(id);
							voterVO.setLocalArea("BOOTH - " + booth.getPartNo());
						}
						else if(type.equalsIgnoreCase("panchayat"))
						{
							voterVO.setLocalArea(panchayatDAO.get(id).getPanchayatName().toString());
						}
						else if(type.equalsIgnoreCase("hamlet"))
						{
							voterVO.setLocalArea(hamletDAO.get(id).getHamletName().toString());
						}
						else if(type.equalsIgnoreCase("customWard"))
						{
							voterVO.setLocalArea(constituencyDAO.get(id).getName().toString());
						}
						resultData.add(voterVO);
				}
			}
			return resultData;
		}
		
		public String getRegionNameBasedOnScope(String infScope,String regionId){
			
			if(infScope.equalsIgnoreCase(IConstants.STATE)){
				State state = stateDAO.get(new Long(regionId));
				return state.getStateName();
			}else if(infScope.equalsIgnoreCase(IConstants.DISTRICT)){
				District district = districtDAO.get(new Long(regionId));
				return district.getDistrictName();
			}else if(infScope.equalsIgnoreCase(IConstants.CONSTITUENCY)){
				Constituency constituency = constituencyDAO.get(new Long(regionId));
				return constituency.getName();
			}else if(infScope.equalsIgnoreCase("MUNCIPALITY/CORPORATION") || infScope.equalsIgnoreCase(IConstants.LOCAL_BODY_ELECTION)){
				LocalElectionBody localBody = localElectionBodyDAO.get(new Long(regionId));
				String localBodyName = localBody.getName() + " (" + localBody.getElectionType().getElectionType() + " )";
				return localBodyName;
			}else if(infScope.equalsIgnoreCase(IConstants.MANDAL) || infScope.equalsIgnoreCase(IConstants.TEHSIL)){
				if(regionId.length() !=0 && regionId != null)
				{
					Tehsil tehsil = tehsilDAO.get(new Long(regionId));
					return tehsil.getTehsilName();
				}
			}else if(infScope.equalsIgnoreCase(IConstants.VILLAGE) || infScope.equalsIgnoreCase(IConstants.HAMLET)){
				Hamlet hamlet = hamletDAO.get(new Long(regionId));
				return hamlet.getHamletName();
			}else if(infScope.equalsIgnoreCase(IConstants.WARD)){
				Constituency constituency = constituencyDAO.get(new Long(regionId));
				return constituency.getName();
			}else if(infScope.equalsIgnoreCase(IConstants.BOOTH)){
				Booth booth = boothDAO.get(new Long(regionId));
				return booth.getPartNo().toString()+" Booth";
			}
		 return null;
		}
}
