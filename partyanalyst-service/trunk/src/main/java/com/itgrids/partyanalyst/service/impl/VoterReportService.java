package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.jam.internal.parser.ParamStructPool;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterPartyInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterCastInfoVO;
import com.itgrids.partyanalyst.dto.VoterReportVO;
import com.itgrids.partyanalyst.dto.VotersInfoForMandalVO;
import com.itgrids.partyanalyst.model.VoterCastBasicInfo;
import com.itgrids.partyanalyst.model.VoterCastInfo;
import com.itgrids.partyanalyst.model.VoterPartyInfo;
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
			  List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>(0);
			  List<SelectOptionVO> wardsList = new ArrayList<SelectOptionVO>(0);
			  List<Long>panchayatIdsList = new ArrayList<Long>(0);
			  List<Long> localBodiesList = new ArrayList<Long>(0);
			  List<Long> boothIdsList = new ArrayList<Long>(0);
			  InsertVoterPartyInfoForALocation(IConstants.CONSTITUENCY,reportLevelValue,null,publicationDateId,reportLevelValue,userId);
			 
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
				  panchayatsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			  }
			 
			  for(Long mandalId : mandalIdsList)
			  {
				  InsertVoterPartyInfoForALocation(IConstants.MANDAL,mandalId,reportLevelValue, publicationDateId,reportLevelValue,userId);
				
			  }
			  
			  for(SelectOptionVO selectOptionVO : panchayatsList)
			  {
				  InsertVoterPartyInfoForALocation(IConstants.PANCHAYAT,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId);
				 
			  }
			  
			  List<Object[]> list2 = null;
			  if(panchayatIdsList.size() > 0)
				  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
			  
			  if(list2 != null && list2.size() > 0)
			  {
				  for(Object[] params : list2)
					  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			  }
			  
			   // List<Long> wardsList = new ArrayList<Long>();
			  if(localBodiesList != null && localBodiesList.size() >0){
				  
				List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
						localBodiesList, publicationDateId,reportLevelValue);
				
				if(wards != null && wards.size() >0){
					
					for(Object[] ward:wards)
					if(ward[0] != null){
						wardsList.add(new SelectOptionVO((Long)ward[0],ward[1].toString()));
					}		
				}
				  
			  }
			 if(localBodiesList.size() > 0)
			  {
				  for(Long localBodyId : localBodiesList)
				  {
					  InsertVoterPartyInfoForALocation(IConstants.LOCALELECTIONBODY,localBodyId,reportLevelValue, publicationDateId,reportLevelValue,userId);
					  
				  }
				  List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localBodiesList,publicationDateId,reportLevelValue);
				  
				  if(list3 != null && list3.size() > 0)
				  {
					  for(Object[] params : list3)
					  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
				  }
				  
			  }
            for(SelectOptionVO selectOptionVO:wardsList){
				  
            	InsertVoterPartyInfoForALocation(
						  IConstants.WARD,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId);
          	 
				 
			  }
			  for(SelectOptionVO selectOptionVO : boothsList)
				  if(!boothIdsList.contains(selectOptionVO.getId()))
					  boothIdsList.add(selectOptionVO.getId());
			  
			  for(Long boothId :boothIdsList)
			  {
				  SelectOptionVO selectOptionVO = null;
				  for(SelectOptionVO optionVO : boothsList)
				  if(optionVO.getId().equals(boothId))
				  {
					  selectOptionVO = optionVO;
					  break;
				  }
				  InsertVoterPartyInfoForALocation(IConstants.BOOTH,selectOptionVO.getId(),new Long(selectOptionVO.getName()), publicationDateId,reportLevelValue,userId);
				  
				  
			  }
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			 
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		  }
	}
		  public ResultStatus InsertVoterPartyInfoForALocation(String locationType, Long locationValue, Long parentLocationId, Long publicationDateId,Long constituencyId,Long userId)
		  {
			  
			  	ResultStatus resultStatus = new ResultStatus();
			  
			  try{
				  List<VoterCastInfoVO> partyCategoryWiseList = null;
				 
				  partyCategoryWiseList = votersAnalysisService.getPartyWiseCastAndGenderWiseVotersCountByPublicationIdInALocation(userId,locationType,locationValue,publicationDateId,constituencyId);
				 if(partyCategoryWiseList != null && partyCategoryWiseList.size() > 0)
				  {
					  VoterCastInfoVO votersInfo = new VoterCastInfoVO();
					  for(VoterCastInfoVO voters : partyCategoryWiseList){
						
					  votersInfo.setReportLevelId(votersAnalysisService.getReportLevelId(locationType));
					  votersInfo.setReportLevelValue(locationValue);
					  votersInfo.setPublicationDateId(publicationDateId);
				      votersInfo.setFemaleVoters(voters.getFemaleVoters());
					  votersInfo.setMaleVoters(voters.getMaleVoters());
					  votersInfo.setLocationId(constituencyId);
					  votersInfo.setUserId(userId);
					  votersInfo.setTotalVoters(voters.getTotalVoters());
					  votersInfo.setPartyPercentage(Double.parseDouble(voters.getVotesPercent()));
					  votersInfo.setPartyId(voters.getPartyId());
					  
					 
					  saveVotersDataInVoterPartyInfoTable(votersInfo);
				  }
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
							voterPartyInfo.setReportLevelId(votersInfo.getReportLevelId());
							voterPartyInfo.setReportLevelValue(votersInfo.getReportLevelValue());
							voterPartyInfo.setUserId(votersInfo.getUserId());
							voterPartyInfo.setPartyVoters(votersInfo.getTotalVoters());
							voterPartyInfo.setPartyMaleVoters(votersInfo.getMaleVoters());
							voterPartyInfo.setPartyFemaleVoters(votersInfo.getFemaleVoters());
							voterPartyInfo.setPartyPercentage(votersInfo.getPartyPercentage());
							voterPartyInfo.setPublicationDateId(votersInfo.getPublicationDateId());
							voterPartyInfo.setConstituencyId(votersInfo.getLocationId());
							voterPartyInfo.setPartyId(votersInfo.getPartyId());
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
	
}
