package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IVoterCastBasicInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterCastInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dto.CastVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;
import com.itgrids.partyanalyst.model.CasteState;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.Party;
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
	

	public IVoterCastInfoDAO getVoterCastInfoDAO() {
		return voterCastInfoDAO;
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
					  List<Long> mandalIdsList = new ArrayList<Long>(0);
					  List<Long> wardsList = new ArrayList<Long>(0);
					  List<Long>panchayatIdsList = new ArrayList<Long>(0);
					  List<Long> localBodiesList = new ArrayList<Long>(0);
					  Set<Long> boothIdsList = new HashSet<Long>(0);
					  List<Long> constituencyIds = new ArrayList<Long>();
					  constituencyIds.add(reportLevelValue);
					  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.CONSTITUENCY,constituencyIds,publicationDateId,reportLevelValue);
					  //InsertVoterCasteBasicInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,publicationDateId,reportLevelValue,userId);
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
						 for(Object[] params : list)
						   panchayatIdsList.add((Long)params[0]);
					  }
					 
					  if(mandalIdsList != null && mandalIdsList.size() >0)
					  {
						// InsertVoterCasteInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue, publicationDateId,reportLevelValue,userId);
						 //InsertVoterCasteBasicInfoForALocation(IConstants.MANDAL,mandalId,publicationDateId,reportLevelValue,userId);
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.MANDAL,mandalIdsList,publicationDateId,reportLevelValue);
					  }
					  
					  if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					     saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.PANCHAYAT,panchayatIdsList,publicationDateId,reportLevelValue);
					  List<Object[]> list2 = null;
					  if(panchayatIdsList.size() > 0)
						  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
					  
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
						  saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(userId,IConstants.LOCALELECTIONBODY,localBodiesList,publicationDateId,reportLevelValue);
						  List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localBodiesList,publicationDateId,reportLevelValue);
						  
						  if(list3 != null && list3.size() > 0)
						  {
							  for(Object[] params : list3)
								  boothIdsList.add((Long)params[0]); 
						  }
						  
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
								mandalMap.put(voterCastInfo.getReportLevelValue(), voterCastInfoVO);
							}else{
								voterCastInfoVO1 = voterCastInfoVO.getVoterCastInfoVO();
								castVOs = voterCastInfoVO1.getCastVOs();
							}
							castVO = new CastVO();
							//castvo.setCasteCategoryName("");
							castVO.setCastName(voterCastInfo.getCasteState().getCaste().getCasteName());
							//castVO.setCastStateId(voterCastInfo.getCasteState().getCasteStateId());
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
					
					public void saveCastAndGenderWiseVotersCountByPublicationIdInMultipleLocation(Long userId,String locationType,List<Long> locationIds,Long publicationDateId,Long constituencyId)
					{
						List<VoterCastInfo> resultList = new ArrayList<VoterCastInfo>();
						Map<Long,VoterCastBasicInfo> casteBasicInfoMap = new HashMap<Long,VoterCastBasicInfo>();
						VoterCastBasicInfo voterCastBasicInfo = null;
						Constituency constituency = constituencyDAO.get(constituencyId);
						Map<Long,Map<Long,VoterCastInfo>> locationsMap = new HashMap<Long,Map<Long,VoterCastInfo>>();
						try{
							List<Object[]> list = boothPublicationVoterDAO.getCastAndGenderWiseVotersCountByPublicationIdMultipleALocation(userId,locationType,locationIds,publicationDateId,constituencyId);
							String location = locationType;
							if("localElectionBody".equalsIgnoreCase(location)){
								location = "Local Election Body";
							}
							VoterReportLevel voterReportLevel = voterReportLevelDAO.getReportLevelByType(location);
							
							for(Long id:locationIds){
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
												try{
													percentage = (new BigDecimal(voterCastInfo.getCasteVoters()*(100.0)/locationCount.doubleValue())).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
												}catch (Exception e) {}
												finally{
													voterCastInfo.setCastePercentage(Double.parseDouble(percentage));
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
}
