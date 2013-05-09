package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVoterStatusDAO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;
import com.itgrids.partyanalyst.model.VoterModificationInfo;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterModificationService implements IVoterModificationService{

	private static final Logger LOG = Logger.getLogger(VoterModificationService.class);
	
	private IVotersAnalysisService votersAnalysisService;
	private IRegionServiceData regionServiceData ;	
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IVoterModificationDAO voterModificationDAO;
	private IVoterAgeRangeDAO voterAgeRangeDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IPublicationDateDAO publicationDateDAO;
	private IVoterModificationInfoDAO voterModificationInfoDAO;
	private ITehsilDAO tehsilDAO;
	private IPanchayatDAO panchayatDAO;
	private IBoothDAO boothDAO;
	private ILocalElectionBodyDAO localElectionBodyDAO;
	private IVoterReportLevelDAO voterReportLevelDAO;
	private IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO;
	private TransactionTemplate transactionTemplate = null;
	private IVoterStatusDAO voterStatusDAO;
	
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

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	private IConstituencyDAO constituencyDAO;
	
	public IVoterModificationInfoDAO getVoterModificationInfoDAO() {
		return voterModificationInfoDAO;
	}

	public void setVoterModificationInfoDAO(
			IVoterModificationInfoDAO voterModificationInfoDAO) {
		this.voterModificationInfoDAO = voterModificationInfoDAO;
	}

	public IRegionServiceData getRegionServiceData() {
		return regionServiceData;
	}

	public void setRegionServiceData(IRegionServiceData regionServiceData) {
		this.regionServiceData = regionServiceData;
	}

	
	
	public IPublicationDateDAO getPublicationDateDAO() {
		return publicationDateDAO;
	}

	public void setPublicationDateDAO(IPublicationDateDAO publicationDateDAO) {
		this.publicationDateDAO = publicationDateDAO;
	}

	public IBoothPublicationVoterDAO getBoothPublicationVoterDAO() {
		return boothPublicationVoterDAO;
	}

	public void setBoothPublicationVoterDAO(
			IBoothPublicationVoterDAO boothPublicationVoterDAO) {
		this.boothPublicationVoterDAO = boothPublicationVoterDAO;
	}
	
	public IVoterModificationDAO getVoterModificationDAO() {
		return voterModificationDAO;
	}

	public void setVoterModificationDAO(IVoterModificationDAO voterModificationDAO) {
		this.voterModificationDAO = voterModificationDAO;
	}

	public IVoterAgeRangeDAO getVoterAgeRangeDAO() {
		return voterAgeRangeDAO;
	}

	public void setVoterAgeRangeDAO(IVoterAgeRangeDAO voterAgeRangeDAO) {
		this.voterAgeRangeDAO = voterAgeRangeDAO;
	}

	public IVotersAnalysisService getVotersAnalysisService() {
		return votersAnalysisService;
	}

	public void setVotersAnalysisService(
			IVotersAnalysisService votersAnalysisService) {
		this.votersAnalysisService = votersAnalysisService;
	}
	
	public IVoterInfoDAO getVoterInfoDAO() {
		return voterInfoDAO;
	}

	public void setVoterInfoDAO(IVoterInfoDAO voterInfoDAO) {
		this.voterInfoDAO = voterInfoDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IVoterStatusDAO getVoterStatusDAO() {
		return voterStatusDAO;
	}

	public void setVoterStatusDAO(IVoterStatusDAO voterStatusDAO) {
		this.voterStatusDAO = voterStatusDAO;
	}

	public IVoterReportLevelDAO getVoterReportLevelDAO() {
		return voterReportLevelDAO;
	}

	public void setVoterReportLevelDAO(IVoterReportLevelDAO voterReportLevelDAO) {
		this.voterReportLevelDAO = voterReportLevelDAO;
	}
	
	public TransactionTemplate getTransactionTemplate() {
		return transactionTemplate;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public IVoterModificationAgeInfoDAO getVoterModificationAgeInfoDAO() {
		return voterModificationAgeInfoDAO;
	}

	public void setVoterModificationAgeInfoDAO(
			IVoterModificationAgeInfoDAO voterModificationAgeInfoDAO) {
		this.voterModificationAgeInfoDAO = voterModificationAgeInfoDAO;
	}

	/**
	 * This method will return VoterModificationVO, which contains Voters Added/deleted Count Between two Publications 
	 * @param String locationType
	 * @param Long locationValue
	 * @param Long Constituency Id
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @author Kamalakar Dandu
	 * @return List<{@link VoterModificationVO}>
	 * 
	 */
	 public VoterModificationVO getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 VoterModificationVO voterModificationVO = new VoterModificationVO(); 
		 LOG.debug("Entered into getAddedAndDeletedVotersCountInBetweenPublicationsInALocation() Method");
		 try{
			 List<Long> publicationIdsList = new ArrayList<Long>(0);
			 voterModificationVO.setPresentPublicationId(toPublicationDateId);
			 if(fromPublicationDateId == null || fromPublicationDateId == 0 || fromPublicationDateId.equals(toPublicationDateId))
			 {
				 publicationIdsList.add(toPublicationDateId);
				 Long previousPublicationId = 0L;
				 List<Long> idslist = getPreviousPublicationIds(toPublicationDateId);
				 if(idslist != null && idslist.size() > 0)
					 previousPublicationId = idslist.get(0); 
				 voterModificationVO.setPreviousPublicationId(previousPublicationId);
			 }
			 else
			 {
				 publicationIdsList = getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId,toPublicationDateId);
				 publicationIdsList.remove(fromPublicationDateId);
				 voterModificationVO.setPreviousPublicationId(fromPublicationDateId);
			 }
				 
			 List<Object[]> list = voterModificationDAO.getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(locationType, locationValue, constituencyId, publicationIdsList);
			 
			 for(Object[] params : list)
			 {
				 if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
					 voterModificationVO.setAddedCount((Long)params[0]);
				 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
					 voterModificationVO.setDeletedCount((Long)params[0]);
			 }
			 
			 return voterModificationVO;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getAddedAndDeletedVotersCountInBetweenPublicationsInALocation() Method");
			 LOG.error("Exception is - "+e);
			 return voterModificationVO;
		 }
	 }
	 /**
	 * This method will return List of Publication Ids Between two Publications 
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @author Kamalakar Dandu
	 * @return List<Long>
	 * 
	 */
	 public List<Long> getVoterPublicationIdsBetweenTwoPublications(Long fromPublicationDateId,Long toPublicationDateId) 
	 {
		 LOG.debug("Entered into getVoterPublicationIdsBetweenTwoPublications() Method");
		 try{
			 /*List<Long> temp = new ArrayList<Long>(0);
			 temp.add(7L);
			 temp.add(8L);
			 //return boothPublicationVoterDAO.getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId, toPublicationDateId);
			 return temp;*/
			 
			 return voterInfoDAO.getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId, toPublicationDateId);
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVoterPublicationIdsBetweenTwoPublications() Method");
			 LOG.error("Exception is - "+e);
			 return null;
		 }
	 }
	 
	 /**
	 * This method will return List of Publication Ids of Prevoius Publications for the given Publication  
	 * @param Long Publication Date Id
	 * @author Kamalakar Dandu
	 * @return List<Long>
	 * 
	 */
	 public List<Long> getPreviousPublicationIds(Long publicationDateId)
	 {
		 LOG.debug("Entered into getPreviousPublicationId() Method");
		 try{
			 /*List<Long> temp = new ArrayList<Long>(0);
			 temp.add(7L);
			// return boothPublicationVoterDAO.getPreviousPublicationIds(publicationDateId);
			 return temp;*/
			 
			 return voterInfoDAO.getPreviousPublicationIds(publicationDateId);
			 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getPreviousPublicationId() Method");
			 LOG.error("Exception is - "+e);
			 return null; 
		 }
	 }
	 
	 public List<Long> getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 LOG.debug("Entered into getVoterPublicationIdsBetweenTwoPublicationsForVotersModification() Method");
		 List<Long> publicationIdsList = new ArrayList<Long>(0);
		 try{
			 if(fromPublicationDateId == null || fromPublicationDateId == 0 || fromPublicationDateId.equals(toPublicationDateId))
				 publicationIdsList.add(toPublicationDateId);
			 else
			 {
				 publicationIdsList = getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId,toPublicationDateId);
				 publicationIdsList.remove(fromPublicationDateId);
			 }
			 return publicationIdsList;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVoterPublicationIdsBetweenTwoPublicationsForVotersModification() Method");
			 LOG.error("Exception is - "+e);
			 return publicationIdsList; 
		 }
	 }
	 
	 public List<VoterModificationAgeRangeVO> getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType)
	 {
		 LOG.debug("Entered into getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
		 List<VoterModificationAgeRangeVO> result = new ArrayList<VoterModificationAgeRangeVO>(0);
		 try{
			 List<String> ageRanges = voterAgeRangeDAO.getAllVoterAgeRanges();
			 VoterModificationAgeRangeVO voterModificationAgeRangeVO = null;
			 if("intermediate".equalsIgnoreCase(queryType) && locationType != null && !locationType.equalsIgnoreCase(IConstants.BOOTH)){
				 //getting data from intermediate table
			   result = getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable(locationType,locationValue,constituencyId,fromPublicationDateId,toPublicationDateId);
			 }
			 
			 //if data not present in intermediate table then calculating by actual process
			 if("main".equalsIgnoreCase(queryType) || result.isEmpty()){
				 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
				 for(String ageRange : ageRanges)
				 {
					 try{
					 voterModificationAgeRangeVO = new VoterModificationAgeRangeVO();
					 voterModificationAgeRangeVO.setRange(ageRange);
					 String[] ages = ageRange.split("-");
					 Long ageFrom = new Long(ages[0].trim());
					 Long ageTo = null;
					 if(!ages[1].trim().equalsIgnoreCase("Above"))
						 ageTo = Long.valueOf(ages[1].trim());
						 
					 List<Object[]> list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation(locationType, locationValue, constituencyId, publicationIdsList, ageFrom, ageTo);
					 
					 if(list != null && list.size() > 0)
					 {
						 for(Object[] params :list)
						 {
							 if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
							 	voterModificationAgeRangeVO.setAddedCount((Long)params[0]);
							 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
							 	voterModificationAgeRangeVO.setDeletedCount((Long)params[0]);
							 if(locationType.equalsIgnoreCase(IConstants.BOOTH))
							 {
								 if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
									 	voterModificationAgeRangeVO.setMovedCount((Long)params[0]);
								else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
									voterModificationAgeRangeVO.setRelocatedCount((Long)params[0]);
							 }
							 	
						 }
					 }
					 result.add(voterModificationAgeRangeVO);
					 }catch (Exception e) {}
				 }
			 }
			 return result; 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
			 LOG.error("Exception is - ",e);
			 return result; 
		 }
	 }
	 
	 public List<VoterModificationAgeRangeVO> getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 LOG.debug("Entered into getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable() Method");
		 Map<Long,VoterModificationAgeRangeVO> mapObjects = new HashMap<Long,VoterModificationAgeRangeVO>();
		 try{
			 List<VoterAgeRange> ageRanges = voterAgeRangeDAO.getAll();
			 Map<Long,String> ageRangeMap = new HashMap<Long,String>();
			 for(VoterAgeRange range:ageRanges){
				 ageRangeMap.put(range.getVoterAgeRangeId(), range.getAgeRange());
			 }
			 String location = locationType;
			  if("localElectionBody".equalsIgnoreCase(locationType)){
				  location = "Local Election Body";
			  }
			 VoterModificationAgeRangeVO voterModificationAgeRangeVO = null;
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
			 List<Object[]> ageWiseAddedDelVoters = voterModificationAgeInfoDAO.getGenderWiseVoterModificationsBetweenPublications(getReportLevelId(location),locationValue,constituencyId,publicationIdsList,ageRangeMap.keySet());
			 for(Object[] ageRange : ageWiseAddedDelVoters)
			 {
				 try{
				 voterModificationAgeRangeVO = mapObjects.get((Long)ageRange[1]);
				 if(voterModificationAgeRangeVO == null){
					 voterModificationAgeRangeVO = new VoterModificationAgeRangeVO();
					 mapObjects.put((Long)ageRange[1], voterModificationAgeRangeVO);
					 voterModificationAgeRangeVO.setRange(ageRangeMap.get((Long)ageRange[1]));
				 }
				 if(ageRange[2].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
					voterModificationAgeRangeVO.setAddedCount((Long)ageRange[0]);
				 else if(ageRange[2].toString().equalsIgnoreCase(IConstants.STATUS_DELETED));
					voterModificationAgeRangeVO.setDeletedCount((Long)ageRange[0]);
				 }catch (Exception e) {}
			 }
			 return new ArrayList<VoterModificationAgeRangeVO>(mapObjects.values()); 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVotersAddedDeletedCountAgeWiseInBetwnPublicsFromIntermediateTable() Method");
			 LOG.error("Exception is - ",e);
			 return new ArrayList<VoterModificationAgeRangeVO>(); 
		 }
		 
	 }
	 
	 /**
	 * This method will return {@link VoterModificationGenderInfoVO}, which contains Gender wise Newly Added/Deleted Voters Count.     
	 * @param String locationType
	 * @param Long locationValue
	 * @param Long Constituency Id
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @param String queryType values=("intermediate" (first calculate from intermediate table if not present then from actual calculation),"main" (direct actual calculation and no intermediate table calculation ))
	 * @author Kamalakar Dandu
	 * @return {@link VoterModificationGenderInfoVO}
	 * 
	 */
	
	 public VoterModificationGenderInfoVO getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType)
	 {
		 LOG.debug("Entered into getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
		 VoterModificationGenderInfoVO result = new VoterModificationGenderInfoVO();
		 try{
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
			 if("intermediate".equalsIgnoreCase(queryType) && locationType!= null && !locationType.equalsIgnoreCase(IConstants.BOOTH)){
				  String location = locationType;
				  if("localElectionBody".equalsIgnoreCase(locationType)){
					  location = "Local Election Body";
				  }
				  // getting sum of all added/deleted male/female voters count from intermediate table for all publications present between selected publications
				  getGendWiseVoterModifsBetwnPublicationsFromIntermediateTable(getReportLevelId(location),locationValue,constituencyId,publicationIdsList,result);
			 }
			  //if data not present in intermediate table then calculating by actual process
			  if("main".equalsIgnoreCase(queryType) || !result.isDataPresent()){
				  List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationsBetweenPublications(locationType, locationValue, constituencyId, publicationIdsList);
				 
				  if(list != null && list.size() > 0)
				  {
					 for(Object[] params : list)
					 {
						 if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						 {
							 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
								 result.setAddedMale((Long)params[0]);
							 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
								 result.setAddedFemale((Long)params[0]);
						 }
						 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
						 {
							 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
								 result.setDeletedMale((Long)params[0]);
							 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
								 result.setDeletedFemale((Long)params[0]);
						 }
						 
						 if(locationType.equalsIgnoreCase(IConstants.BOOTH))
						 {
							 if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 result.setMovedMale((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 result.setMovedFemale((Long)params[0]);
							 }
							 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 result.setRelocatedMale((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 result.setRelocatedFemale((Long)params[0]);
							 }
						 }
					 }
					 result.setAddedTotal(result.getAddedMale() + result.getAddedFemale());
					 result.setDeletedTotal(result.getDeletedMale() + result.getDeletedFemale());
					 if(locationType.equalsIgnoreCase(IConstants.BOOTH))
					 {
						 result.setMovedTotal(result.getMovedMale() + result.getMovedFemale());
						 result.setRelocatedTotal(result.getRelocatedMale() + result.getRelocatedFemale());
					 }
				  }
		    }
			 return result;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
			 LOG.error("Exception is - "+e);
			 return result;
		 }
	 }
	 
	 public void getGendWiseVoterModifsBetwnPublicationsFromIntermediateTable(Long reportLevelId,Long locationValue,Long constituencyId,List<Long> publicationIdsList,VoterModificationGenderInfoVO result){
		 try{
		  List<Object[]> addedDeletedList = voterModificationInfoDAO.getGenderWiseVoterModificationsBetweenPublications(reportLevelId,locationValue,constituencyId,publicationIdsList);
		  if(addedDeletedList != null && !addedDeletedList.isEmpty()){
			  result.setDataPresent(true);
			  for(Object[] addedDeleted:addedDeletedList){
				  if((IConstants.STATUS_ADDED).equalsIgnoreCase(addedDeleted[2].toString())){
					  result.setAddedMale((Long)addedDeleted[0]);
					  result.setAddedFemale((Long)addedDeleted[1]);
				  }else if((IConstants.STATUS_DELETED).equalsIgnoreCase(addedDeleted[2].toString())){
					  result.setDeletedMale((Long)addedDeleted[0]);
					  result.setDeletedFemale((Long)addedDeleted[1]);
				  }
			  }
			  if(result.getAddedMale() != null &&  result.getAddedFemale() != null)
			     result.setAddedTotal(result.getAddedMale() + result.getAddedFemale());
			  if(result.getDeletedMale() != null &&  result.getDeletedFemale() != null)
				 result.setDeletedTotal(result.getDeletedMale() + result.getDeletedFemale());
		  }
		 }catch(Exception e){
			 LOG.error("Exception Occured in getGendWiseVoterModifsBetwnPublicationsFromIntermediateTable() Method",e);
		 }
	 }
	 
	 
	 /**
	 * This method will return List<{@link VoterModificationGenderInfoVO}>, which contains Gender wise Newly Added/Deleted Voters Count.     
	 * @param String locationType
	 * @param Long locationValue
	 * @param Long Constituency Id
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @param String queryType values=("intermediate" (first calculate from intermediate table if not present then from actual calculation),"main" (direct actual calculation and no intermediate table calculation ))
	 * @author Kamalakar Dandu
	 * @return List<{@link VoterModificationGenderInfoVO}>
	 * 
	 */
	 public List<VoterModificationGenderInfoVO> getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String queryType)
	 {
		 LOG.debug("Entered into getGenderWiseVoterModificationsForEachPublication() Method");
		 List<VoterModificationGenderInfoVO> result = new ArrayList<VoterModificationGenderInfoVO>();
		 try{
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
			 if("intermediate".equalsIgnoreCase(queryType) && !locationType.equalsIgnoreCase(IConstants.BOOTH)){
				 String location = locationType;
				  if("localElectionBody".equalsIgnoreCase(locationType)){
					  location = "Local Election Body";
				  }
				 // getting data from intermediate table 
				 getGendWiseVoterModifsForEachPublicationsFromIntermediateTable(getReportLevelId(location),locationValue,constituencyId,publicationIdsList,result);
			 }
			// if data from not present in intermediate table then calculating by normal process
			 if("main".equalsIgnoreCase(queryType) || result.isEmpty()){
				 List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationsForEachPublication(locationType, locationValue, constituencyId, publicationIdsList);
				 
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params : list)
					 {
						 VoterModificationGenderInfoVO infoVO = getVoterModificationGenderInfoVOFromResultList((Long)params[0],result);
						 boolean flag = false;
						 
						 if(infoVO == null)
						 {
							 infoVO = new VoterModificationGenderInfoVO();
							 infoVO.setPublicationId((Long)params[0]);
							 infoVO.setPublicationName(params[4].toString());
							 flag = true;
						 }
						 if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						 {
							 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
								 infoVO.setAddedMale((Long)params[1]);
							 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
								 infoVO.setAddedFemale((Long)params[1]);
						 }
						 else if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
						 {
							 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
								 infoVO.setDeletedMale((Long)params[1]);
							 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
								 infoVO.setDeletedFemale((Long)params[1]);
						 }
						 
						 if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
						 {
							 if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
							 {
								 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
									 infoVO.setMovedMale((Long)params[1]);
								 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
									 infoVO.setMovedFemale((Long)params[1]);
							 }
							 else if(params[2].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
							 {
								 if(params[3].toString().equalsIgnoreCase(IConstants.MALE))
									 infoVO.setRelocatedMale((Long)params[1]);
								 else if(params[3].toString().equalsIgnoreCase(IConstants.FEMALE))
									 infoVO.setRelocatedFemale((Long)params[1]);
							 }
						 }
						 if(flag)
							 result.add(infoVO); 
					 }
				 }
			 }
			 for(VoterModificationGenderInfoVO infoVO : result)
			 {
				 List<Long>	idsList = getPreviousPublicationIds(infoVO.getPublicationId());
				 if(idsList != null && idsList.size() > 0)
				 {
					 infoVO.setPreviousPublicationId(idsList.get(0));
					 infoVO.setPreviousPublicationName(publicationDateDAO.getNamePublicationDateId(idsList.get(0)));
				 }
				 else
					 infoVO.setPreviousPublicationId(0L);
				 
				 infoVO.setAddedTotal(infoVO.getAddedMale() + infoVO.getAddedFemale());
				 infoVO.setDeletedTotal(infoVO.getDeletedMale() + infoVO.getDeletedFemale());
				 
				 if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
				 {
					 infoVO.setMovedTotal(infoVO.getMovedMale() + infoVO.getMovedFemale());
					 infoVO.setRelocatedTotal(infoVO.getRelocatedMale() + infoVO.getRelocatedFemale());
				 }
			 }
			 return result;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getGenderWiseVoterModificationsForEachPublication() Method");
			 LOG.error("Exception is - "+e);
			 return result;
		 }
	 }
	 
	 public void getGendWiseVoterModifsForEachPublicationsFromIntermediateTable(Long reportLevelId,Long locationValue,Long constituencyId,List<Long> publicationIdsList,List<VoterModificationGenderInfoVO> resultList){
		try{
		 // getting sum of all added/deleted male/female voters count from intermediate table for each publication wise present between selected publications
		 List<Object[]> addedDeletedList = voterModificationInfoDAO.getGenderWiseVoterModificationsForEachPublication(reportLevelId,locationValue,constituencyId,publicationIdsList);
		 VoterModificationGenderInfoVO result = null;
		 Map<Long,VoterModificationGenderInfoVO> resultMap = new HashMap<Long,VoterModificationGenderInfoVO>();
		 if(addedDeletedList != null && !addedDeletedList.isEmpty()){
			 //populating data to VoterModificationGenderInfoVO
			  for(Object[] addedDeleted:addedDeletedList){
				  result = resultMap.get((Long)addedDeleted[3]);
				  if(result == null){
					  result = new VoterModificationGenderInfoVO();
					  result.setPublicationId((Long)addedDeleted[3]);
					  result.setPublicationName(addedDeleted[4].toString());
					  resultMap.put((Long)addedDeleted[3], result);
				  }
				  if((IConstants.STATUS_ADDED).equalsIgnoreCase(addedDeleted[2].toString())){
					  result.setAddedMale((Long)addedDeleted[0]);
					  result.setAddedFemale((Long)addedDeleted[1]);
				  }else if((IConstants.STATUS_DELETED).equalsIgnoreCase(addedDeleted[2].toString())){
					  result.setDeletedMale((Long)addedDeleted[0]);
					  result.setDeletedFemale((Long)addedDeleted[1]);
				  }
			  }
			  if(!resultMap.isEmpty()){
				   for(VoterModificationGenderInfoVO vo : resultMap.values()){
					   if(vo.getAddedMale() != null &&  vo.getAddedFemale() != null)
					     vo.setAddedTotal(vo.getAddedMale() + vo.getAddedFemale());
					   if(vo.getDeletedMale() != null &&  vo.getDeletedFemale() != null)
					     vo.setDeletedTotal(vo.getDeletedMale() + vo.getDeletedFemale());
				   }
			       resultList.addAll(resultMap.values());
			   }
		  }
		 }catch(Exception e){
			 LOG.error("Exception Occured in getGendWiseVoterModifsForEachPublicationsFromIntermediateTable() Method",e);
		 }
	 }
	 
	 /**
	 * This method search for existing item in List, if available returns that Object otherwise null 
	 * @param List<{@link VoterModificationGenderInfoVO}>
	 * @param Long publicationDateId
	 * @author Kamalakar Dandu
	 * @return List<{@link VoterModificationGenderInfoVO}>
	 * 
	 */
	 public VoterModificationGenderInfoVO getVoterModificationGenderInfoVOFromResultList(Long publicationDateId,List<VoterModificationGenderInfoVO> resultList)
	 {
		 try{
			 if(resultList != null && resultList.size() > 0)
				 for(VoterModificationGenderInfoVO infoVO : resultList)
					 if(infoVO.getPublicationId().equals(publicationDateId))
						 return infoVO;
			 return null;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVoterModificationGenderInfoVOFromResultList() Method");
			 LOG.error("Exception is - "+e);
			 return null;
		}
	 }
	 public List<VoterAgeRangeVO> getVoterInfoByPublicationDateList(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 List<VoterAgeRangeVO> voterAgeRangeVOList = null;
		 try{
			 
			 List<Long> publicationDateIds = getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId, toPublicationDateId);
			 
			 if(publicationDateIds != null && publicationDateIds.size() > 0)
			 {
				 
				/*if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)|| locationType.equalsIgnoreCase("localElectionBody"))
				{
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
					locationValue = (Long)list.get(0);
				}*/
			   List<Object[]> list = voterInfoDAO.getVoterInfoByPublicationDateIds(votersAnalysisService.getReportLevelId(locationType), locationValue, publicationDateIds);
			   if(list != null && list.size() > 0)
			   {
				   voterAgeRangeVOList = new ArrayList<VoterAgeRangeVO>(0);
				 for(Object[] params : list)
				 {
					 VoterAgeRangeVO voterAgeRangeVO = new VoterAgeRangeVO();
					 voterAgeRangeVO.setTotalVoters((Long)params[0]);
					 voterAgeRangeVO.setMaleVoters((Long)params[1]);
					 voterAgeRangeVO.setFemaleVoters((Long)params[2]);
					 voterAgeRangeVO.setPublicationDate(params[3].toString());
					 voterAgeRangeVOList.add(voterAgeRangeVO);
				 }
			   }
			}
			 return voterAgeRangeVOList;
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getVoterInfoByPublicationDateList() Method, Exception - "+e);
			 return voterAgeRangeVOList;
		}
		 
	 }
	 
	 /**
	 * This method will give Newly Added/Deleted Voters Info in the form of List<{@link VoterVO}>
	 * @param String locationType
	 * @param Long locationValue
	 * @param Long Constituency Id
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @param String status
	 * @author Kamalakar Dandu
	 * @return List<{@link VoterVO}>
	 * 
	 */
	 public List<VoterVO> getModifiedVotersInALocationBetweenPublucations(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId,String status)
	 {
		 List<VoterVO> votersList = new ArrayList<VoterVO>(0);
		 LOG.debug("Entered into getModifiedVotersInALocationBetweenPublucations() Method");
		 try{
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
			 List<Object[]> list = voterModificationDAO.getModifiedVotersInALocationBetweenPublucations(locationType, locationValue, constituencyId, publicationIdsList,status);
			 if(list != null && list.size() > 0)
				 return coypModifiedVoterDataToVoterList(list);
			 return votersList;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVoterModificationGenderInfoVOFromResultList() Method");
			 LOG.error("Exception is - "+e);
			 return votersList;
		}
	 }
	 
	 /**
	  * This method will get the subLevel voter added , deleted information
	  * @author Samba Penugonda
	  * @param locationType , which specifies the type of location like constituency,mandal....etc
	  * @param constituencyId ,constitunecy id of the location
	  * @param fromPublicationDateId , this is from PublicationDateId.
	  * @param toPublicationDateId , this is to PublicationDateId.
	  * @return all the sublevel voter added , deleted information for selected location
	  */
	 
	public VoterModificationVO getSubLevelsVoterModificationDetails(
			String locationType, Long locationValue, Long constituencyId,
			Long fromPublicationDateId, Long toPublicationDateId)	 {
		 VoterModificationVO voterModificationVO = new VoterModificationVO();
		 LOG.debug("Entered into getModifiedVotersInALocationBetweenPublucations() Method");
		 try{
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
			 
			 List<SelectOptionVO> list1 = new ArrayList<SelectOptionVO>();
			 List<SelectOptionVO> list2 = new ArrayList<SelectOptionVO>();
			 List<Long> locationValues = new ArrayList<Long>();
			 List<Long> localBodies = new ArrayList<Long>();
			 List<Object[]> modifiedVoterDetails = null;
			 List<Object[]> subLevelDetails = null;
			
			 
			 if(locationType.equalsIgnoreCase("constituency"))
			 {
				 list1 =  regionServiceData.getTehsilsInConstituency(constituencyId);
				 list2 = regionServiceData.getLocalElectionBodiesInConstituency(constituencyId, IConstants.PRESENT_YEAR);
				 
				 for(SelectOptionVO selectVO:list1)			 
					 locationValues.add(new Long(selectVO.getId().toString().substring(1)));
				 for(SelectOptionVO selectVO:list2)			 
					 localBodies.add(selectVO.getId());
				 
				 if(locationValues != null && locationValues.size() >0)
				 subLevelDetails = voterModificationInfoDAO
						.getVoterModificationReportDetailsByReportLevelId(
								IConstants.MANDAL_REPORT_LEVEL_ID,
								locationValues, constituencyId,
								publicationIdsList);
				
				
				 if(subLevelDetails == null ||subLevelDetails.size() == 0)
				{
				 
					modifiedVoterDetails = voterModificationDAO
							.getConstitunecyGenderWiseVoterModificationsForEachPublicationByMandal(
									locationValues,constituencyId, publicationIdsList);
					processModifiedVotersDetails(modifiedVoterDetails,voterModificationVO);
				}else
					processModifiedVotersDetailsFromIntermediateTables(subLevelDetails,voterModificationVO,"mandal","false");

				 if(localBodies != null && localBodies.size() > 0)
				 subLevelDetails = voterModificationInfoDAO
							.getVoterModificationReportDetailsByReportLevelId(
									IConstants.LOCALELECTIONBODY_REPORT_LEVEL_ID,
									localBodies, constituencyId,
									publicationIdsList);
				 
				 
				 if(subLevelDetails == null ||subLevelDetails.size() == 0){
				
					 if(localBodies != null && localBodies.size() >0)
					 {
					 
						modifiedVoterDetails = voterModificationDAO
								.getConstitunecyGenderWiseVoterModificationsForEachPublicationByLocalElectionBody(
										localBodies, constituencyId,
										publicationIdsList);
					 
						processModifiedVotersDetails(modifiedVoterDetails,voterModificationVO);
					 }
				 }else
					 processModifiedVotersDetailsFromIntermediateTables(subLevelDetails,voterModificationVO,"localElectionBody","true");

				
			 }
			 else if(locationType.equalsIgnoreCase("mandal"))
			 {				 
				 list1 =  regionServiceData.getPanchayitiesInTehsil(new Long("2"+locationValue.toString()));
				 for(SelectOptionVO selectVO:list1)			 
					 locationValues.add(new Long(selectVO.getId().toString().substring(1)));
				 
				 
				 subLevelDetails = voterModificationInfoDAO
							.getVoterModificationReportDetailsByReportLevelId(
									IConstants.PANCHAYAT_REPORT_LEVEL_ID,
									locationValues, constituencyId,
									publicationIdsList);
					
					
					 if(subLevelDetails == null ||subLevelDetails.size() == 0)
					{
				 
				      modifiedVoterDetails = voterModificationDAO.getMandalGenderWiseVoterModificationsForEachPublicationByPanchayat( 
							locationValues,constituencyId,publicationIdsList);
				 
				      processModifiedVotersDetails(modifiedVoterDetails,voterModificationVO);
					}else
						processModifiedVotersDetailsFromIntermediateTables(subLevelDetails,voterModificationVO,"panchayat","false");

				 
			 }
			 else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			 {
				 list1 =  regionServiceData.getHamletsOrWards(locationValue,IConstants.PRESENT_YEAR);
				 
				 for(SelectOptionVO selectVO:list1)			 
					 localBodies.add(new Long(selectVO.getId().toString().substring(1)));
				
				 
				 if(localBodies != null && localBodies.size() >0)
				 {
					 subLevelDetails = voterModificationInfoDAO
								.getVoterModificationReportDetailsByReportLevelId(
										IConstants.WARD_REPORT_LEVEL_ID,
										localBodies, constituencyId,
										publicationIdsList);
					
					
				 }
				 
				 
				 if(subLevelDetails == null ||subLevelDetails.size() == 0)
				{
				 modifiedVoterDetails =  voterModificationDAO.getLocalElectionBodyGenderWiseVoterModificationsForEachPublicationByWard( 
							locationValues,constituencyId,publicationIdsList);
				 
				 if(localBodies != null && localBodies.size() >0)
					 processModifiedVotersDetailsForLocalBody(modifiedVoterDetails,voterModificationVO);
				}else
					processModifiedVotersDetailsFromIntermediateTables(subLevelDetails,voterModificationVO,"ward","false");
				 
			 }
			 else if(locationType.equalsIgnoreCase("panchayat"))
			 {
				 list1 = regionServiceData.getBoothsInAPanchayatByPublicationId(new Long("2"+locationValue.toString()),fromPublicationDateId);
				 
				 for(SelectOptionVO selectVO:list1)			 
					 locationValues.add(new Long(selectVO.getId()));
				 
				 subLevelDetails = voterModificationInfoDAO
							.getVoterModificationReportDetailsByReportLevelId(
									IConstants.BOOTH_REPORT_LEVEL_ID,
									locationValues, constituencyId,
									publicationIdsList);
				
				
			
				
				 if(subLevelDetails == null ||subLevelDetails.size() == 0)
				{
				 modifiedVoterDetails =  voterModificationDAO.getPanchayatGenderWiseVoterModificationsForEachPublicationByBooth( 
							locationValues,constituencyId,publicationIdsList);
				 processModifiedVotersDetails(modifiedVoterDetails,voterModificationVO);
				}else
					processModifiedVotersDetailsFromIntermediateTables(subLevelDetails,voterModificationVO,"booth","false");
				 
			 }
			 else if(locationType.equalsIgnoreCase("ward"))
			 {
				 list1 = regionServiceData.getboothsInWard(constituencyId,locationValue,fromPublicationDateId);
				 
				 for(SelectOptionVO selectVO:list1)			 
					 locationValues.add(new Long(selectVO.getId()));
				 
				 
				 
				 subLevelDetails = voterModificationInfoDAO
							.getVoterModificationReportDetailsByReportLevelId(
									IConstants.BOOTH_REPORT_LEVEL_ID,
									locationValues, constituencyId,
									publicationIdsList);
			
				 if(subLevelDetails == null ||subLevelDetails.size() == 0)
				 {
				 modifiedVoterDetails =  voterModificationDAO.getWardGenderWiseVoterModificationsForEachPublicationByBooth( 
						 locationValues,constituencyId,publicationIdsList);
				 processModifiedVotersDetails(modifiedVoterDetails,voterModificationVO);
				 }else
					 processModifiedVotersDetailsFromIntermediateTables(subLevelDetails,voterModificationVO,"booth","false");

				 
			 }
			
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVoterModificationGenderInfoVOFromResultList() Method");
			 LOG.error("Exception is - "+e);
		}
		 
		 return voterModificationVO;
	 }
	
	/**
	 * This method will process sublevel voter modification details for a selected location
	 * @param modifiedVoterDetails , this has the voter added , deleted details
	 * @param voterModificationVO , we need to set those values to this  vo.
	 */
	
	public void processModifiedVotersDetailsForLocalBody(
			List<Object[]> modifiedVoterDetails,
			VoterModificationVO voterModificationVO) throws Exception	 {
		 
		LOG.error("Entered in  processModifiedVotersDetailsForLocalBody service method");
		try
		{
		 List<VoterModificationVO> modificationDetails = new ArrayList<VoterModificationVO>();
		 Map<Long,VoterModificationVO> map = new HashMap<Long, VoterModificationVO>();
		 
		 
		 for(Object[] obj:modifiedVoterDetails)
		 { 
			 VoterModificationVO voterModificationVO1 = null;
			 
			 if(map.get((Long)obj[3]) != null)
				 voterModificationVO1 = map.get((Long)obj[3]);
			 else
				 voterModificationVO1 = new VoterModificationVO();
			 
			 if(obj[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
			 {				 
				 if(obj[2].toString().equalsIgnoreCase("M"))
					 voterModificationVO1.setMaleVotersAdded((Long)obj[1]);
				 else if(obj[2].toString().equalsIgnoreCase("F"))
					 voterModificationVO1.setFemaleVotersAdded((Long)obj[1]);
				 
			 }
			 else if(obj[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
			 {				 
				 if(obj[2].toString().equalsIgnoreCase("M"))
					 voterModificationVO1.setMaleVotersDeleted((Long)obj[1]);
				 else if(obj[2].toString().equalsIgnoreCase("F"))
					 voterModificationVO1.setFemaleVotersDeleted((Long)obj[1]);
				 
			 }
			 
			 if(voterModificationVO1.getId() != null)
			 {
			  voterModificationVO1.setId((Long)obj[3]);
			  voterModificationVO1.setName(obj[4].toString());
			 
			 }
			 
			 map.put((Long)obj[3], voterModificationVO1);
		 }
		 
		 for (Map.Entry<Long, VoterModificationVO> entry : map.entrySet()) 
			 modificationDetails.add(entry.getValue());
		
		 
		 voterModificationVO.setModifiedLocalBodyVotersList(modificationDetails);
		}
		catch(Exception e)
		{
			LOG.error("Exception raised in processModifiedVotersDetailsForLocalBody service method");
			throw new  Exception();
			
		}
		 
	 }
	//
	
	
	 public void processModifiedVotersDetailsFromIntermediateTables(List<Object[]> modifiedVoterDetails,VoterModificationVO voterModificationVO,String type,String isLocalBody)
	 {
		 
		 List<VoterModificationVO> modificationDetails = new ArrayList<VoterModificationVO>();
		 Map<Long,VoterModificationVO> map = new HashMap<Long, VoterModificationVO>();
		 
		 
		 try
		 {
			 
			 for(Object[] obj:modifiedVoterDetails)
			 {
				 VoterModificationVO voterModificationVO1 = null;
				 
				 if(map.get((Long)obj[3]) != null)
					 voterModificationVO1 = map.get((Long)obj[3]);
				 else
					 voterModificationVO1 = new VoterModificationVO();
				 
				 if(obj[4].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
				 {
					 voterModificationVO1.setMaleVotersAdded((Long)obj[1]);
					 voterModificationVO1.setFemaleVotersAdded((Long)obj[2]);
					 voterModificationVO1.setAddedCount((Long)obj[0]);
					 
				 }else  if(obj[4].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
				 {
					 
					 voterModificationVO1.setMaleVotersDeleted((Long)obj[1]);
					 voterModificationVO1.setFemaleVotersDeleted((Long)obj[2]);
					 voterModificationVO1.setDeletedCount((Long)obj[0]);
				 }
				 
				 if(voterModificationVO1.getId() == null)
				 {
				  voterModificationVO1.setId((Long)obj[3]);
				  
				  if(type.equalsIgnoreCase("mandal"))
				    voterModificationVO1.setName(tehsilDAO.get((Long)obj[3]).getTehsilName());
				  else if(type.equalsIgnoreCase("panchayat"))
				    voterModificationVO1.setName(panchayatDAO.get((Long)obj[3]).getPanchayatName());
				  else if(type.equalsIgnoreCase("ward"))
					    voterModificationVO1.setName(constituencyDAO.get((Long)obj[3]).getName());
				  else if(type.equalsIgnoreCase("booth"))
					    voterModificationVO1.setName("BOOTH - "+boothDAO.get((Long)obj[3]).getPartNo());
				  else if(type.equalsIgnoreCase("localElectionBody"))
					    voterModificationVO1.setName(localElectionBodyDAO.get((Long)obj[3]).getName()+" MUNCIPALITY");
				 }
				 
				 map.put((Long)obj[3], voterModificationVO1);
				 
			 }
			 
			 for (Map.Entry<Long, VoterModificationVO> entry : map.entrySet())
			 {
				
					 entry.getValue().setAddedCount(entry.getValue().getFemaleVotersAdded()+entry.getValue().getMaleVotersAdded());
				 	 entry.getValue().setDeletedCount(entry.getValue().getFemaleVotersDeleted()+entry.getValue().getMaleVotersDeleted());
				 
				 modificationDetails.add(entry.getValue());
			 }
			 
			 if(isLocalBody.equalsIgnoreCase("true"))			 
			   voterModificationVO.setModifiedLocalBodyVotersList(modificationDetails);
			 else			 
			 voterModificationVO.setModifiedVotersList(modificationDetails);
		 
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }
		 
	 }
	 public void processModifiedVotersDetails(List<Object[]> modifiedVoterDetails,VoterModificationVO voterModificationVO)
	 {
		 
		 List<VoterModificationVO> modificationDetails = new ArrayList<VoterModificationVO>();
		 Map<Long,VoterModificationVO> map = new HashMap<Long, VoterModificationVO>();
		 
		 
		 try
		 {
			 
		 
		 for(Object[] obj:modifiedVoterDetails)
		 { 
			 VoterModificationVO voterModificationVO1 = null;
			 
			 if(map.get((Long)obj[3]) != null)
				 voterModificationVO1 = map.get((Long)obj[3]);
			 else
				 voterModificationVO1 = new VoterModificationVO();
			 
			
			 if(obj[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
			 {				 
				 if(obj[2].toString().equalsIgnoreCase("M"))
					 voterModificationVO1.setMaleVotersAdded((Long)obj[0]);
				 else if(obj[2].toString().equalsIgnoreCase("F"))
					 voterModificationVO1.setFemaleVotersAdded((Long)obj[0]);
				 
			 }
			 else if(obj[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
			 {				 
				 if(obj[2].toString().equalsIgnoreCase("M"))
					 voterModificationVO1.setMaleVotersDeleted((Long)obj[0]);
				 else if(obj[2].toString().equalsIgnoreCase("F"))
					 voterModificationVO1.setFemaleVotersDeleted((Long)obj[0]);
				 
			 }
			 
			 if(voterModificationVO1.getId() == null)
			 {
			  voterModificationVO1.setId((Long)obj[3]);
			  voterModificationVO1.setName(obj[4].toString());
			 
			 }
			 
			 map.put((Long)obj[3], voterModificationVO1);
		 }
		 
		 for (Map.Entry<Long, VoterModificationVO> entry : map.entrySet())
		 {
			
				 entry.getValue().setAddedCount(entry.getValue().getFemaleVotersAdded()+entry.getValue().getMaleVotersAdded());
			 	 entry.getValue().setDeletedCount(entry.getValue().getFemaleVotersDeleted()+entry.getValue().getMaleVotersDeleted());
			 
			 modificationDetails.add(entry.getValue());
		 }
		
		 
		 voterModificationVO.setModifiedVotersList(modificationDetails);
		 
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }
		 
	 }
	 
	 /**
	 * This method Copy the Voter Data from Object[] to List<{@link VoterVO}>
	 * @param List<Object[]> inputList
	 * @author Kamalakar Dandu
	 * @return List<{@link VoterVO}>
	 * 
	 */
	 public List<VoterVO> coypModifiedVoterDataToVoterList(List<Object[]> inputList)
	 {
		 List<VoterVO> votersList = new ArrayList<VoterVO>();
		 try{
			 if(inputList == null || inputList.size() == 0)
				 return votersList;
			 
			 VoterVO voter = null;
			 for(Object[] params : inputList)
			 {
				 try{
					 voter = new VoterVO();
					 voter.setVoterId(params[0].toString());
					 voter.setFirstName(params[1].toString());
					 voter.setGender(params[2].toString());
					 voter.setAge((Long)params[3]);
					 voter.setRelativeFirstName(params[4].toString());
					 voter.setRelationshipType(params[5].toString());
					 voter.setBoothId((Long)params[6]);
					 voter.setBoothNo(Long.valueOf(params[7].toString()));
					 voter.setBoothName("Booth-"+params[7].toString());
					 voter.setPanchayatName(params[8].toString());
					 voter.setStatus(params[9].toString());
					 voter.setPublicationDateId((Long)params[10]);
					 voter.setPublicationName(params[11].toString());
					 voter.setHouseNo(params[12] != null ? params[12].toString() : "");
					 votersList.add(voter);
				 }catch (Exception e) {
					 LOG.error("Exception is - "+e);
				 }
			 }
			 return votersList;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in coypModifiedVoterDataToVoterList() Method");
			 LOG.error("Exception is - "+e);
			 return votersList;
		 }
	 }
	 
	 public String getLocationNameByLocationValue(String locationType, Long locationValue)
	 {
		 try{
			 if(locationValue == null)
				 return "";
			 /*if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || locationType.equalsIgnoreCase("localElectionBody"))
			 {
				 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
				 locationValue = (Long)list.get(0);
			 }*/
			 List locationNames = boothPublicationVoterDAO.getLocationNameByLocationValue(locationType, locationValue);
			 if(locationNames != null && locationNames.size() > 0)
				 return locationNames.get(0).toString();
			 return "";
		 }catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLocationNameByLocationValue() Method, Exception - "+e);
			return null;
		}
	 }
	 
	 public String getPublicationNameByPublicationDateId(Long publicationDateId)
	 {
		 try{
			 if(publicationDateId == null || publicationDateId == 0)
				return "";
			  return publicationDateDAO.getNamePublicationDateId(publicationDateId);
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getPublicationNameByPublicationDateId() Method, Exception - "+e);
			 return "";
		}
	 }
	 public Long getReportLevelId(String type)
	 {
		 Long reportLevelId = 0l;
		 try{
			 reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
			 return reportLevelId;
		 }catch (Exception e) {
			LOG.error("Exception Occured in getReportLevelId() Method, Exception - ",e);
			return reportLevelId;
		}
	 }
	 
	public ResultStatus insertGenderWiseVoterModifInfoInVoterModificationInfoTable(Long constituencyId, Long publicationDateId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
			 List<Long> constituencyIdsList = new ArrayList<Long>(0);
			 List<Long> mandalIdsList = new ArrayList<Long>(0);
			 List<Long> panchayatIdsList = new ArrayList<Long>(0);
			 List<Long> localEleBodyIdsList = new ArrayList<Long>(0);
			 List<Long> boothIdsList = new ArrayList<Long>(0);
			 List<Long> wardIdsList = new ArrayList<Long>(0);
			 List<SelectOptionVO> boothsList = new ArrayList<SelectOptionVO>(0);
			 
			 constituencyIdsList.add(constituencyId);
			 
			 List<SelectOptionVO> mandalsList = regionServiceData.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
			 if(mandalsList == null || mandalsList.size() == 0)
				  return null;
			  
			  for(SelectOptionVO selectOptionVO : mandalsList)
			  {
				  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
					  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
				  else
					  localEleBodyIdsList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
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
			 
			 
			 List<Object[]> list2 = null;
			  if(panchayatIdsList.size() > 0)
				  list2 = boothDAO.getBoothIdsByPanchayatIdsInAPublication(panchayatIdsList, publicationDateId);
			  
			  if(list2 != null && list2.size() > 0)
			  {
				  for(Object[] params : list2)
					  boothIdsList.add((Long)params[0]);
			  }
			  
			  
			  if(localEleBodyIdsList.size() > 0)
			  {
				  List<Object[]> list3 = boothDAO.getBoothIdsInLocalBodiesForAPublication(localEleBodyIdsList,publicationDateId,constituencyId);
				  
				  if(list3 != null && list3.size() > 0)
				  {
					  for(Object[] params : list3)
						  boothsList.add(new SelectOptionVO((Long)params[0],params[1].toString())); 
				  }
				  
			  }
			  
			  for(SelectOptionVO selectOptionVO : boothsList)
				  if(!boothIdsList.contains(selectOptionVO.getId()))
					  boothIdsList.add(selectOptionVO.getId());
			  
			  /*for(Long boothId :boothIdsList)
			  {
				  SelectOptionVO selectOptionVO = null;
				  for(SelectOptionVO optionVO : boothsList)
				  if(optionVO.getId().equals(boothId))
				  {
					  selectOptionVO = optionVO;
					  break;
				  }
			}*/
			  
			  if(localEleBodyIdsList != null && localEleBodyIdsList.size() >0){
				  
					List<Object[]> wards = boothDAO.getWardsByLocalElecBodyIds(
							localEleBodyIdsList, publicationDateId,constituencyId);
					
					if(wards != null && wards.size() >0){
						for(Object[] ward:wards)
						if(ward[0] != null){
							wardIdsList.add((Long)ward[0]);
						}		
					}
					  
				  }
				  
			  if(constituencyIdsList != null && constituencyIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("constituency", constituencyIdsList, constituencyId, publicationDateId);
			  
			  if(mandalIdsList != null && mandalIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("mandal", mandalIdsList, constituencyId, publicationDateId);
			  
			  if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("localElectionBody", localEleBodyIdsList, constituencyId, publicationDateId);
			  
			  if(panchayatIdsList != null && panchayatIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("panchayat", panchayatIdsList, constituencyId, publicationDateId);
			  
			  /*if(boothIdsList != null && boothIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("booth", boothIdsList, constituencyId, publicationDateId);*/
			  
			  if(wardIdsList != null && wardIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("ward", wardIdsList, constituencyId, publicationDateId);
			  
			 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			 return resultStatus;
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in insertGenderWiseVoterModifInfoInVoterModificationInfoTable() Method, Exception - "+e);
			 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 return resultStatus;
		}
	 }
	 
	 public ResultStatus saveGenderWiseVoterModifInfoInIntermediateTables(String locationType, List<Long> locationValuesList, Long constituencyId, Long publicationDateId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
			 for(Long locationVal:locationValuesList){
				 List<Long> value = new ArrayList<Long>();
				 value.add(locationVal);
			 List<VoterModificationGenderInfoVO> modificationGenderInfoVO = getGenderWiseVoterModificationsByPublicationId(locationType, value, constituencyId, publicationDateId);
			 
			 if(modificationGenderInfoVO == null || modificationGenderInfoVO.size() == 0)
				 modificationGenderInfoVO =  setDefaultValuesForGenderwiseVoterModification(locationVal,locationType);
				 
			 saveGenderWiseVoterModifInfoInVoterModificationInfoTable(modificationGenderInfoVO,constituencyId,publicationDateId,locationType);
			 
			 List<VoterModificationAgeRangeVO> ageRangeVOs = getVotersAddedAndDeletedCountAgeWiseByPublicationId(locationType, value, constituencyId, publicationDateId);
			 saveAgeWiseAddedAndDeletedVotersCountInVoterAgeInfo(ageRangeVOs,constituencyId, publicationDateId,locationType);
			 }
			
			 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			 return resultStatus;
		 }catch (Exception e) {
			 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 LOG.error("Exception Occured in saveGenderWiseVoterModifInfoInIntermediateTables() Method, Exception - "+e);
			 return resultStatus;
		}
		 
	 }
	 
	 public List<VoterModificationGenderInfoVO> getGenderWiseVoterModificationsByPublicationId(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId)
	 {
		 LOG.debug("Entered into getGenderWiseVoterModificationsByPublicationId() Method");
		 List<VoterModificationGenderInfoVO> result = new ArrayList<VoterModificationGenderInfoVO>(0);
		 try{
			 StringBuilder queryStr = new StringBuilder();
			 queryStr.append(" select count(model.voter.voterId),model.voterStatus.status,model.voter.gender ");
			 
			 if(locationType.equalsIgnoreCase("constituency"))
				 queryStr.append(" ,model2.booth.constituency.constituencyId ");
				else if(locationType.equalsIgnoreCase("mandal"))
					queryStr.append(", model2.booth.tehsil.tehsilId ");
				else if(locationType.equalsIgnoreCase("panchayat"))
					queryStr.append(" ,model2.booth.panchayat.panchayatId ");
				else if(locationType.equalsIgnoreCase("booth"))
					queryStr.append(" ,model2.booth.boothId ");
				else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
					queryStr.append(" ,model2.booth.localBody.localElectionBodyId ");
				else if(locationType.equalsIgnoreCase("ward"))
					queryStr.append(" ,model2.booth.localBodyWard.constituencyId ");
				
			 
			 List<Object[]> list = voterModificationDAO.getGenderWiseVoterModificationByPublicationId(locationType, locationValuesList, constituencyId, publicationDateId,queryStr.toString());
			 
				
			 
			 if(list != null && list.size() > 0)
			 {
				 VoterModificationGenderInfoVO genderInfoVO = new VoterModificationGenderInfoVO();
				 for(Object[] params : list)
				 {
					 if(params[1]!= null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
					 {
						 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
							 genderInfoVO.setAddedMale((Long)params[0]);
						 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
							 genderInfoVO.setAddedFemale((Long)params[0]);
						 
						 genderInfoVO.setAddedTotal(genderInfoVO.getAddedMale() + genderInfoVO.getAddedFemale());
					 }
					 else if(params[1]!= null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
					 {
						 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
							 genderInfoVO.setDeletedMale((Long)params[0]);
						 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
							 genderInfoVO.setDeletedFemale((Long)params[0]);
						 genderInfoVO.setDeletedTotal(genderInfoVO.getDeletedMale() + genderInfoVO.getDeletedFemale());
					 }
					 
					 if(locationType != null && locationType.equalsIgnoreCase("booth"))
					 {
						  if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
						 {
							 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
								 genderInfoVO.setMovedMale((Long)params[0]);
							 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
								 genderInfoVO.setMovedFemale((Long)params[0]);
							 genderInfoVO.setMovedTotal(genderInfoVO.getMovedMale()+genderInfoVO.getMovedFemale());
						 }
						 
						 else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
						 {
							 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
								 genderInfoVO.setRelocatedMale((Long)params[0]);
							 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
								 genderInfoVO.setRelocatedFemale((Long)params[0]);
							 genderInfoVO.setRelocatedTotal(genderInfoVO.getRelocatedMale()+genderInfoVO.getRelocatedFemale());
						 }
					 }
					 
					genderInfoVO.setReportLevelValue((Long)params[3]);
					
				 }
				 result.add(genderInfoVO);
			 }
			 
			 return result;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
			 LOG.error("Exception is - "+e);
			 return result;
		 }
	 }
	 
	 public ResultStatus saveGenderWiseVoterModifInfoInVoterModificationInfoTable(final List<VoterModificationGenderInfoVO> modificationGenderInfoVO,final Long constituencyId,final Long publicationDateId,final String locationType)
	 {
		 LOG.info("Entred into saveVotersDataInVoterAgeInfoTable() Method ");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						 if(modificationGenderInfoVO != null && modificationGenderInfoVO.size() > 0)
						 {
							 for(VoterModificationGenderInfoVO genderInfoVO : modificationGenderInfoVO)
							 { 
							 
								 VoterModificationInfo voterModificationInfo = new VoterModificationInfo();
								 voterModificationInfo.setConstituencyId(constituencyId);
								 voterModificationInfo.setVoterReportLevel(voterReportLevelDAO.get(votersAnalysisService.getReportLevelId(locationType)));
								 voterModificationInfo.setReportLevelValue(genderInfoVO.getReportLevelValue());
								 voterModificationInfo.setPublicationDate(publicationDateDAO.get(publicationDateId));
								 if(genderInfoVO.getAddedTotal() != null)
								 {
									 voterModificationInfo.setTotalVoters(genderInfoVO.getAddedTotal());
									 voterModificationInfo.setMaleVoters(genderInfoVO.getAddedMale());
									 voterModificationInfo.setFemaleVoters(genderInfoVO.getAddedFemale());
									 voterModificationInfo.setVoterStatus(voterStatusDAO.get(voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_ADDED)));
									 voterModificationInfoDAO.save(voterModificationInfo);
								 
								 }
							 
								 if(genderInfoVO.getDeletedTotal() != null)
								 {
									 voterModificationInfo.setTotalVoters(genderInfoVO.getDeletedTotal());
									 voterModificationInfo.setMaleVoters(genderInfoVO.getDeletedMale());
									 voterModificationInfo.setFemaleVoters(genderInfoVO.getDeletedFemale());
									 voterModificationInfo.setVoterStatus(voterStatusDAO.get(voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_DELETED)));
									 voterModificationInfoDAO.save(voterModificationInfo);
								 }
								 
								 if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
								 {
									 if(genderInfoVO.getMovedTotal() != null)
									 {
										 voterModificationInfo.setTotalVoters(genderInfoVO.getMovedTotal());
										 voterModificationInfo.setMaleVoters(genderInfoVO.getMovedMale());
										 voterModificationInfo.setFemaleVoters(genderInfoVO.getMovedFemale());
										 voterModificationInfo.setVoterStatus(voterStatusDAO.get(voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_MOVED)));
										 voterModificationInfoDAO.save(voterModificationInfo);
									 }
									 
									 if(genderInfoVO.getRelocatedTotal() != null)
									 {
										 voterModificationInfo.setTotalVoters(genderInfoVO.getRelocatedTotal());
										 voterModificationInfo.setMaleVoters(genderInfoVO.getRelocatedMale());
										 voterModificationInfo.setFemaleVoters(genderInfoVO.getRelocatedFemale());
										 voterModificationInfo.setVoterStatus(voterStatusDAO.get(voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_RELOCATED)));
										 voterModificationInfoDAO.save(voterModificationInfo);
									 }
								 }
								
							 }
							 
						 }
						
					}});
			 // voterModificationInfoDAO.flushAndclearSession();
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
		  }catch (Exception e) {
			  LOG.error("Exception Occured in saveVotersDataInVoterAgeInfoTable() Method, Exception - "+e);
			  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			  return resultStatus;
		}
	 }
	 
	 
	 //AgeWise
	 
	 
	 public List<VoterModificationAgeRangeVO> getVotersAddedAndDeletedCountAgeWiseByPublicationId(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId)
	 {
		 LOG.debug("Entered into getVotersAddedAndDeletedCountAgeWiseByPublicationId() Method");
		 List<VoterModificationAgeRangeVO> result = new ArrayList<VoterModificationAgeRangeVO>(0);
		 try{
			 List<String> ageRanges = voterAgeRangeDAO.getAllVoterAgeRanges();
			 VoterModificationAgeRangeVO voterModificationAgeRangeVO = null;
			 
			 for(String ageRange : ageRanges)
			 {
				 try{
				 voterModificationAgeRangeVO = new VoterModificationAgeRangeVO();
				 voterModificationAgeRangeVO.setRange(ageRange);
				 String[] ages = ageRange.split("-");
				 Long ageFrom = new Long(ages[0].trim());
				 Long ageTo = null;
				 if(!ages[1].trim().equalsIgnoreCase("Above"))
					 ageTo = Long.valueOf(ages[1].trim());
					 
				 StringBuilder queryStr = new StringBuilder();
				 queryStr.append(" select count(model.voter.voterId),model.voterStatus.status,model.voter.gender ");
				 
				 if(locationType.equalsIgnoreCase("constituency"))
					 queryStr.append(" ,model2.booth.constituency.constituencyId ");
					else if(locationType.equalsIgnoreCase("mandal"))
						queryStr.append(" ,model2.booth.tehsil.tehsilId ");
					else if(locationType.equalsIgnoreCase("panchayat"))
						queryStr.append(" ,model2.booth.panchayat.panchayatId ");
					else if(locationType.equalsIgnoreCase("booth"))
						queryStr.append(" ,model2.booth.boothId ");
					else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
						queryStr.append(" ,model2.booth.localBody.localElectionBodyId ");
					else if(locationType.equalsIgnoreCase("ward"))
						queryStr.append(" ,model2.booth.localBodyWard.constituencyId ");
				 
				 List<Object[]> list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(locationType, locationValuesList, constituencyId, publicationDateId, ageFrom, ageTo,queryStr.toString());
				 
				 if(list != null && list.size() > 0)
				 {
					 for(Object[] params :list)
					 {
						 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						 {
							 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
								 voterModificationAgeRangeVO.setAddedMale((Long)params[0]);
							 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
								 voterModificationAgeRangeVO.setAddedFemale((Long)params[0]);
						 }
						 else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
						 {
							 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
								 voterModificationAgeRangeVO.setDeletedMale((Long)params[0]);
							 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
								 voterModificationAgeRangeVO.setDeletedFemale((Long)params[0]);
						 }
						 if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
						 { 
							 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 voterModificationAgeRangeVO.setMovedMale((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 voterModificationAgeRangeVO.setMovedFemale((Long)params[0]);
							 }
							 else if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 voterModificationAgeRangeVO.setRelocatedMale((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 voterModificationAgeRangeVO.setRelocatedFemale((Long)params[0]);
							 }
						 }
							voterModificationAgeRangeVO.setReportLevelValue((Long)params[3]);
					 }
					voterModificationAgeRangeVO.setAddedCount(voterModificationAgeRangeVO.getAddedMale() + voterModificationAgeRangeVO.getAddedFemale());
					voterModificationAgeRangeVO.setDeletedCount(voterModificationAgeRangeVO.getDeletedMale() + voterModificationAgeRangeVO.getDeletedFemale());
					
					if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
					{
						voterModificationAgeRangeVO.setMovedCount(voterModificationAgeRangeVO.getMovedMale() + voterModificationAgeRangeVO.getMovedFemale());
						voterModificationAgeRangeVO.setRelocatedCount(voterModificationAgeRangeVO.getRelocatedMale() + voterModificationAgeRangeVO.getRelocatedFemale());
					}
				 }
				 if(list == null || list.size() == 0)
					 voterModificationAgeRangeVO.setReportLevelValue(locationValuesList.get(0).longValue());
				 
				 result.add(voterModificationAgeRangeVO);
				 }catch (Exception e) {}
			 }
			 return result; 
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
			 LOG.error("Exception is - "+e);
			 return result; 
		 }
	 }

	public ResultStatus saveAgeWiseAddedAndDeletedVotersCountInVoterAgeInfo(final List<VoterModificationAgeRangeVO> ageRangeVOs,final Long constituencyId,final Long publicationDateId,final String locationType)
	{
		LOG.info("Entred into saveAgeWiseAddedAndDeletedVotersCountInVoterAgeInfo() Method ");
		  ResultStatus resultStatus = new ResultStatus();
		  try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
					{
						 if(ageRangeVOs != null && ageRangeVOs.size() > 0)
						 {
							 for(VoterModificationAgeRangeVO ageRangeVO : ageRangeVOs)
							 {
								 
								     Long addedVoterModificationId = voterModificationInfoDAO.getVoterModificationInfoIdByReportLevelValue(votersAnalysisService.getReportLevelId(locationType), ageRangeVO.getReportLevelValue(), publicationDateId, voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_ADDED), constituencyId);
								     Long deletedVoterModificationId = voterModificationInfoDAO.getVoterModificationInfoIdByReportLevelValue(votersAnalysisService.getReportLevelId(locationType), ageRangeVO.getReportLevelValue(), publicationDateId, voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_DELETED), constituencyId);
								     Long movedVoterModifiedId = voterModificationInfoDAO.getVoterModificationInfoIdByReportLevelValue(votersAnalysisService.getReportLevelId(locationType), ageRangeVO.getReportLevelValue(), publicationDateId, voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_MOVED), constituencyId);
								     Long relocatedVoterModifiedId = voterModificationInfoDAO.getVoterModificationInfoIdByReportLevelValue(votersAnalysisService.getReportLevelId(locationType), ageRangeVO.getReportLevelValue(), publicationDateId, voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_RELOCATED), constituencyId);
								     
								     VoterModificationAgeInfo voterModificationAgeInfo = new VoterModificationAgeInfo();
								     voterModificationAgeInfo.setVoterAgeRangeId(voterAgeRangeDAO.getVoterAgeRangeIdByType(ageRangeVO.getRange()));
								     if(addedVoterModificationId != null && ageRangeVO.getAddedCount() != null)
								     {
								    	 voterModificationAgeInfo.setTotalVoters(ageRangeVO.getAddedCount());
								    	 voterModificationAgeInfo.setMaleVoters(ageRangeVO.getAddedMale());
								    	 voterModificationAgeInfo.setFemaleVoters(ageRangeVO.getAddedFemale());
								    	 voterModificationAgeInfo.setVoterModificationInfo(voterModificationInfoDAO.get(addedVoterModificationId));
									     voterModificationAgeInfoDAO.save(voterModificationAgeInfo);
								     }
								     
								     if(deletedVoterModificationId != null && ageRangeVO.getDeletedCount() != null)
								     {
								    	 voterModificationAgeInfo.setTotalVoters(ageRangeVO.getDeletedCount());
								    	 voterModificationAgeInfo.setMaleVoters(ageRangeVO.getDeletedMale());
								    	 voterModificationAgeInfo.setFemaleVoters(ageRangeVO.getDeletedFemale());
								    	 voterModificationAgeInfo.setVoterModificationInfo(voterModificationInfoDAO.get(deletedVoterModificationId));
								    	 voterModificationAgeInfoDAO.save(voterModificationAgeInfo);
								     }
								     
								     if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
								     {
									     if(movedVoterModifiedId != null && ageRangeVO.getMovedCount() != null)
									     {
									    	 voterModificationAgeInfo.setTotalVoters(ageRangeVO.getMovedCount());
									    	 voterModificationAgeInfo.setMaleVoters(ageRangeVO.getMovedMale());
									    	 voterModificationAgeInfo.setFemaleVoters(ageRangeVO.getMovedFemale());
									    	 voterModificationAgeInfo.setVoterModificationInfo(voterModificationInfoDAO.get(movedVoterModifiedId));
									    	 voterModificationAgeInfoDAO.save(voterModificationAgeInfo);
									     }
									     
									     if(relocatedVoterModifiedId != null && ageRangeVO.getRelocatedCount() != null)
									     {
									    	 voterModificationAgeInfo.setTotalVoters(ageRangeVO.getRelocatedCount());
									    	 voterModificationAgeInfo.setMaleVoters(ageRangeVO.getRelocatedMale());
									    	 voterModificationAgeInfo.setFemaleVoters(ageRangeVO.getRelocatedFemale());
									    	 voterModificationAgeInfo.setVoterModificationInfo(voterModificationInfoDAO.get(relocatedVoterModifiedId));
									    	 voterModificationAgeInfoDAO.save(voterModificationAgeInfo);
									     }
								     }
							 }
						 }
						 	
					}});
				 // voterModificationInfoDAO.flushAndclearSession();
				  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				  return resultStatus;
			  }catch (Exception e) {
				  LOG.error("Exception Occured in saveAgeWiseAddedAndDeletedVotersCountInVoterAgeInfo() Method, Exception - "+e);
				  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				  return resultStatus;
			}
	}
	
	public List<VoterModificationGenderInfoVO> setDefaultValuesForGenderwiseVoterModification(Long locationVal,String locationType)
	{
		List<VoterModificationGenderInfoVO> genderInfoVOs = new ArrayList<VoterModificationGenderInfoVO>(0);
		try{
			 VoterModificationGenderInfoVO genderInfoVO = new VoterModificationGenderInfoVO();
			 genderInfoVO.setAddedTotal(0l);
			 genderInfoVO.setAddedMale(0l);
			 genderInfoVO.setAddedFemale(0l);
			 genderInfoVO.setDeletedMale(0l);
			 genderInfoVO.setDeletedFemale(0l);
			 genderInfoVO.setDeletedTotal(0l);
			 if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			 {
				 genderInfoVO.setMovedTotal(0l);
				 genderInfoVO.setMovedMale(0l);
				 genderInfoVO.setMovedFemale(0l);
				 genderInfoVO.setRelocatedTotal(0l);
				 genderInfoVO.setRelocatedMale(0l);
				 genderInfoVO.setRelocatedFemale(0l);
			 }
			 genderInfoVO.setReportLevelValue(locationVal);
			 genderInfoVOs.add(genderInfoVO);
			return genderInfoVOs;
		}catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in setDefaultValuesForGenderwiseVoterModification() Method, Exception - "+e);
			return genderInfoVOs;
		}
	}
	
	public Long getLocalElectionBodyIdByAssemblyLocalElectionBodyId(Long assemblyLocalElectionBodyId)
	{
		try{
			return assemblyLocalElectionBodyDAO.getLocalElectionBodyIdByAssemblyLocalElectionBodyId(assemblyLocalElectionBodyId);
		}catch (Exception e) {
			return null;
		}
	}
	 
	
	public VoterModificationVO getSubLevelsVoterModificationDetailsByLocationValue(
			String locationType, Long locationValue, Long constituencyId,
			Long fromPublicationDateId, Long toPublicationDateId)	 {
		 VoterModificationVO voterModificationVO = new VoterModificationVO();
		 LOG.debug("Entered into getSubLevelsVoterModificationDetailsByLocationValue() Method");
		 try{
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
			 List<Long> pubIdsListForTotVoters = new ArrayList<Long>(0);
			 
			 if(fromPublicationDateId != null && fromPublicationDateId > 0 && !pubIdsListForTotVoters.equals(fromPublicationDateId))
				 pubIdsListForTotVoters.add(fromPublicationDateId);
			 if(toPublicationDateId != null && toPublicationDateId > 0 && !pubIdsListForTotVoters.equals(toPublicationDateId))
				 pubIdsListForTotVoters.add(toPublicationDateId);
			 
			 List<SelectOptionVO> locationValuesList = new ArrayList<SelectOptionVO>(0);
			 
			 if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 {
				 List<SelectOptionVO> mandalsList = regionServiceData.getSubRegionsInConstituency(constituencyId,IConstants.PRESENT_YEAR, null);
				 if(mandalsList == null || mandalsList.size() == 0)
					  return null;
				 
				 List<Long> mandalIdsList = new ArrayList<Long>(0);
				 List<Long> localEleBodyIdsList = new ArrayList<Long>(0);
				 
				  for(SelectOptionVO selectOptionVO : mandalsList)
				  {
					  if(selectOptionVO.getId().toString().substring(0,1).equalsIgnoreCase(IConstants.RURAL_TYPE))
						  mandalIdsList.add(new Long(selectOptionVO.getId().toString().substring(1)));
					  else
						  localEleBodyIdsList.add((Long)assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(selectOptionVO.getId().toString().substring(1))).get(0));
				  }
				  
				  if(mandalIdsList != null && mandalIdsList.size() > 0)
					 locationValuesList.add(new SelectOptionVO(mandalIdsList, "mandal"));
				  
				  if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
					locationValuesList.add(new SelectOptionVO(localEleBodyIdsList, IConstants.LOCALELECTIONBODY));
				
			 }
			 
			 else if(locationType.equalsIgnoreCase("mandal"))
			 {
				 List<Long> panchayatIdsList = panchayatDAO.getPanchayatIdsListByMandalId(locationValue);
				 if(panchayatIdsList != null && panchayatIdsList.size() > 0)
					locationValuesList.add(new SelectOptionVO(panchayatIdsList, "panchayat"));
				
			 }
			 else if(locationType.equalsIgnoreCase("panchayat") || locationType.equalsIgnoreCase("ward"))
			 {
				// List<String> partNoList = boothDAO.getPartNoByPanchayatIdAndPublicationDateIdsList(locationValue, publicationIdsList, constituencyId, locationType);
				 
				 List<Long> boothIdsList = boothDAO.getBoothIdsByPanchayatIdAndPublicationDateIdsList(locationValue, publicationIdsList, constituencyId, locationType);
				 if(boothIdsList != null && boothIdsList.size() > 0)
					 locationValuesList.add(new SelectOptionVO(boothIdsList, "booth"));
			 }
			 
			 else if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || locationType.equalsIgnoreCase("localElectionBody"))
			 {
				
				 //locationValue = getLocalElectionBodyIdByAssemblyLocalElectionBodyId(new Long(locationValue.toString().substring(1)));
				 
				 List<Long> wardIdsList =  boothDAO.getWardsByLocalElecBodyIdAndPublicationIdsList(locationValue, publicationIdsList, constituencyId); 
				 if(wardIdsList != null && wardIdsList.size() > 0)
				    locationValuesList.add(new SelectOptionVO(wardIdsList, "ward"));
			 }
			 
			 if(locationValuesList != null && locationValuesList.size() > 0)
				 voterModificationVO = getVoterModificationSubLevelsData(locationValuesList, constituencyId, publicationIdsList,pubIdsListForTotVoters);
				  
			
			
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getSubLevelsVoterModificationDetailsByLocationValue() Method");
			 LOG.error("Exception is - "+e);
		}
		 
		 return voterModificationVO;
	 }
	
	 public VoterModificationVO getVoterModificationSubLevelsData(List<SelectOptionVO> selectOptionVOList, Long constituencyId, List<Long> publicationIdsList,List<Long> pubIdsListForTotVoters)
	 {
		 VoterModificationVO voterModificationVO = new VoterModificationVO();
		 List<VoterModificationVO> voterModificationVOsList = new ArrayList<VoterModificationVO>(0);
		 try{
			 
			 if(selectOptionVOList != null && selectOptionVOList.size() > 0)
			 {
				 for(SelectOptionVO optionVO : selectOptionVOList)
					 getVoterModificationDataByPublicationDateList(optionVO, constituencyId, publicationIdsList, voterModificationVOsList,pubIdsListForTotVoters);
					 
			 }
			 voterModificationVO.setModifiedVotersList(voterModificationVOsList);
			 return voterModificationVO;
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getVoterModificationSubLevelsData() Method, Exception - "+e);
			 return voterModificationVO;
		}
	 }

	 
	 public String getVoterModificationSublevelQueryString(String locationType)
	 {
		 try{
			 StringBuilder stringBuilder = new StringBuilder();
			 stringBuilder.append(" select count(model.voter.voterId),model.voterStatus.status,model.voter.gender,"); 
			 
			 if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 stringBuilder.append(" model2.booth.constituency.constituencyId, model2.booth.constituency.name ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
				 stringBuilder.append(" model2.booth.tehsil.tehsilId,model2.booth.tehsil.tehsilName ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || locationType.equalsIgnoreCase("localElectionBody"))
				 stringBuilder.append(" model2.booth.localBody.localElectionBodyId,model2.booth.localBody.name,model2.booth.localBody.electionType.electionType ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				 stringBuilder.append(" model2.booth.panchayat.panchayatId,model2.booth.panchayat.panchayatName ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
				 stringBuilder.append(" model2.booth.boothId,model2.booth.partNo ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.WARD))
				 stringBuilder.append(" model2.booth.localBodyWard.constituencyId,model2.booth.localBodyWard.name ");
			
			 return stringBuilder.toString();
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getVoterModificationSublevelQueryString() method, Exception - "+e);
			 return "";
		}
	 }
	 
	 public void getVoterModificationDataByPublicationDateList(SelectOptionVO optionVO, Long constituencyId,List<Long> publicationIdsList, List<VoterModificationVO> voterModificationVOsList, List<Long> pubIdsListForTotVoters)
	 {
		 try{
			 if(optionVO != null)
			 {
				 List<Object[]> list  = null;
				 
				 if(optionVO.getType() != null && !optionVO.getType().equalsIgnoreCase(IConstants.BOOTH))
				   list = voterModificationInfoDAO.getVoterModificationGenderDetailsByLocationValuesList(optionVO.getLocationValuesList(), publicationIdsList, constituencyId, votersAnalysisService.getReportLevelId(optionVO.getType()));
				 if(list != null && list.size() > 0)
					 getVotermodificationDetailsFromVoterModifInfoTable(list, voterModificationVOsList,optionVO,publicationIdsList,constituencyId,pubIdsListForTotVoters);
				 else
				 {
					 String queryString = getVoterModificationSublevelQueryString(optionVO.getType());
					 list = voterModificationDAO.getSublevelVoterModificationDetailsByLocationValues(constituencyId, publicationIdsList, optionVO.getLocationValuesList(), optionVO.getType(), queryString);
					 getVotermodificationDetailsFromVoterModificationTable(list, voterModificationVOsList, optionVO,pubIdsListForTotVoters,constituencyId);
				 }
					
				 
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getVoterModificationDataByPublicationDateList() Method, Exception - +e");
			 
		}
	 }
	 
	 
	 public void getVotermodificationDetailsFromVoterModifInfoTable(List<Object[]> voterModifDetails, List<VoterModificationVO> voterModificationVOs, SelectOptionVO optionVO, List<Long> publicationIdsList, Long constituencyId, List<Long> pubIdsListForTotVoters)
	 {
		 try{
			 
			 if(voterModifDetails != null && voterModifDetails.size() > 0)
			 {
				 List<Long> locationIds = optionVO.getLocationValuesList();
				 
				 for(Long id : locationIds)
				 {
					 VoterModificationVO modificationVO = new VoterModificationVO();
					 for(Object[] params : voterModifDetails)
					 {
						if(id.equals(params[4]))
						{
						 if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						 {
							 modificationVO.setMaleVotersAdded((Long)params[1]);
							 modificationVO.setFemaleVotersAdded((Long)params[2]);
							 modificationVO.setAddedCount((Long)params[0]);
						 }
						 else if(params[3] != null && params[3].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
						 {
							 modificationVO.setMaleVotersDeleted((Long)params[1]);
							 modificationVO.setFemaleVotersDeleted((Long)params[2]);
							 modificationVO.setDeletedCount((Long)params[0]);
						 }
						 
						 /* else if(params[3] != null && ((Long)params[3]).equals(3l))
						 {
							 modificationVO.setMaleVotersMoved((Long)params[1]);
							 modificationVO.setFemaleVotersMoved((Long)params[2]);
							 modificationVO.setMovedCount((Long)params[0]);
						 }
						 else if(params[3] != null && ((Long)params[3]).equals(4l))
						 {
							 modificationVO.setMaleVotersRelocated((Long)params[1]);
							 modificationVO.setFemaleVotersRelocated((Long)params[2]);
							 modificationVO.setRelocatedCount((Long)params[0]);
						 } */
						 if(modificationVO.getId() == null)
						 {
							   modificationVO.setId((Long)params[4]);
							    if(optionVO.getType().equalsIgnoreCase(IConstants.CONSTITUENCY))
							    {
								  modificationVO.setName(constituencyDAO.get((Long)params[4]).getName());
								  modificationVO.setLocationType("constituency");
							    }
							    else if(optionVO.getType().equalsIgnoreCase("mandal"))
							    {
							      modificationVO.setLocationType("mandal");
								  modificationVO.setName(tehsilDAO.get((Long)params[4]).getTehsilName());
							    }
								else if(optionVO.getType().equalsIgnoreCase("panchayat"))
								{
								  modificationVO.setLocationType("panchayat");	
								  modificationVO.setName(panchayatDAO.get((Long)params[4]).getPanchayatName());
								}
								else if(optionVO.getType().equalsIgnoreCase("ward"))
								{
								  modificationVO.setLocationType("ward");
								  modificationVO.setName(constituencyDAO.get((Long)params[4]).getName());
								}
								else if(optionVO.getType().equalsIgnoreCase("booth"))
								{
								  modificationVO.setLocationType("booth");
								  modificationVO.setName(boothDAO.get((Long)params[4]).getPartNo());
								}
								else if(optionVO.getType().equalsIgnoreCase("localElectionBody") || optionVO.getType().equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
								{
									modificationVO.setLocationType("localElectionBody");
									LocalElectionBody localElectionBody = localElectionBodyDAO.get((Long)params[4]);
									modificationVO.setName(localElectionBody.getName()+" "+localElectionBody.getElectionType().getElectionType());
								} 
							
						 }
						
						 modificationVO.setSelectOptionVOsList(getTotalVotersByPublicationIdsList(pubIdsListForTotVoters, votersAnalysisService.getReportLevelId(optionVO.getType()), (Long)params[4], constituencyId));
						 
					   }
					 }
					 voterModificationVOs.add(modificationVO);
				 }
				 
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getVotermodificationDetailsFromVoterModifInfoTable() Method, Exception - +e");
		}
	 }
	 
	 
	 public void getVotermodificationDetailsFromVoterModificationTable(List<Object[]> voterModifDetails, List<VoterModificationVO> voterModificationVOsList, SelectOptionVO optionVO, List<Long> pubIdsListForTotVoters,Long constituencyId)
	 {
		 try{
			 
			 if(voterModifDetails != null && voterModifDetails.size() > 0)
			 {
				 List<String> locationNamesList = new ArrayList<String>(0);
				 
				for(Object[] params: voterModifDetails)
				{
					if(!locationNamesList.contains(params[4]))
						locationNamesList.add(params[4].toString());
				}
				Collections.sort(locationNamesList);
				
				for(String locationName : locationNamesList)
				{
					VoterModificationVO modificationVO = new VoterModificationVO();
					
					for(Object[] params : voterModifDetails)
					 {
						if(locationName.equalsIgnoreCase(params[4].toString()))
						{
							 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 modificationVO.setMaleVotersAdded((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 modificationVO.setFemaleVotersAdded((Long)params[0]);
								 
								 modificationVO.setAddedCount(modificationVO.getMaleVotersAdded()+modificationVO.getFemaleVotersAdded());
							 
							 }
							 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
							 {
								 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
									 modificationVO.setMaleVotersDeleted((Long)params[0]);
								 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
									 modificationVO.setFemaleVotersDeleted((Long)params[0]);
								 
								 modificationVO.setDeletedCount(modificationVO.getMaleVotersDeleted()+modificationVO.getFemaleVotersDeleted());
							 }
							 if(optionVO.getType() != null && optionVO.getType().equalsIgnoreCase(IConstants.BOOTH))
							 {
								 if(params[1] != null&& params[1].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
								 {
									 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
										 modificationVO.setMaleVotersMoved((Long)params[0]);
									 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
										 modificationVO.setFemaleVotersMoved((Long)params[0]);
								 
									 modificationVO.setMovedCount(modificationVO.getMaleVotersMoved()+modificationVO.getFemaleVotersMoved());
								 }
							 
								 if(params[1] != null && params[1].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
								 {
									 if(params[2].toString().equalsIgnoreCase(IConstants.MALE))
										 modificationVO.setMaleVotersRelocated((Long)params[0]);
									 else if(params[2].toString().equalsIgnoreCase(IConstants.FEMALE))
										 modificationVO.setFemaleVotersRelocated((Long)params[0]);
								 
									 modificationVO.setRelocatedCount(modificationVO.getMaleVotersRelocated()+modificationVO.getFemaleVotersRelocated());
								 }
							 }
						 
							 if(optionVO.getType() != null && optionVO.getType().equalsIgnoreCase(IConstants.BOOTH))
							 {
								modificationVO.setPartNo(new Long(params[4].toString())); 
								modificationVO.setName(params[4].toString());
							 }
							 else if(optionVO.getType() != null && (optionVO.getType().equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || optionVO.getType().equalsIgnoreCase("localElectionBody")))
								 modificationVO.setName(params[4].toString()+" "+params[5].toString());
							 else
								 modificationVO.setName(params[4].toString());
							 
							  modificationVO.setLocationType(optionVO.getType());
							 
							 	modificationVO.setId((Long)params[3]);
							 	
							 modificationVO.setSelectOptionVOsList(getTotalVotersByPublicationIdsList(pubIdsListForTotVoters, votersAnalysisService.getReportLevelId(optionVO.getType()), (Long)params[3], constituencyId));
						}
					 }
					
					 voterModificationVOsList.add(modificationVO);
				}
				 
			 }
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getVotermodificationDetailsFromVoterModificationTable() Method, Exception -" +e);
			 
			 
		}
	 }
	 
	 public List<SelectOptionVO> getTotalVotersByPublicationIdsList(List<Long> publicationDateIdsList, Long reportLevelId, Long locationValue, Long constituencyId)
	 {
		 try{
			 
			 List<SelectOptionVO> selectOptionVOList = new ArrayList<SelectOptionVO>(0);
			 
			List<Object[]> list = voterInfoDAO.getTotalVotersByPublicationDateIdsList(publicationDateIdsList, reportLevelId, locationValue, constituencyId);
			 if(list != null && list.size() > 0)
				 for(Object[] params : list)
					 selectOptionVOList.add(new SelectOptionVO((Long)params[0],params[1].toString()));
			 
			 return selectOptionVOList;
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in getTotalVotersByPublicationIdsList() method, Exception - "+e);
			 return null;
		}
	}
	 public List<VoterVO> getSelectedVotersDetails(VoterModificationVO voterModificationVO)
	 {
		 LOG.debug("Entered into the getSelectedVotersDetails service method");
		 
		 List<VoterVO> voterDetails = new ArrayList<VoterVO>();
		 
		 try
		 {
		 
		 List<Long> publicationIdsList = new ArrayList<Long>(0);
		 if(voterModificationVO.getPreviousPublicationId() == null || voterModificationVO.getPreviousPublicationId() == 0 || voterModificationVO.getPreviousPublicationId().equals(voterModificationVO.getPresentPublicationId()))
		 {
			 publicationIdsList.add(voterModificationVO.getPresentPublicationId());
			 Long previousPublicationId = 0L;
			 List<Long> idslist = getPreviousPublicationIds(voterModificationVO.getPresentPublicationId());
			 if(idslist != null && idslist.size() > 0)
				 previousPublicationId = idslist.get(0); 
			 voterModificationVO.setPreviousPublicationId(previousPublicationId);
		 }
		 else
		 {
			 publicationIdsList = getVoterPublicationIdsBetweenTwoPublications(voterModificationVO.getPreviousPublicationId(),voterModificationVO.getPresentPublicationId());
			 publicationIdsList.remove(voterModificationVO.getPreviousPublicationId());
		 }
		 
		 
		 Long constituencyId = voterModificationVO.getConstituencyId();
		 Long locationValue = voterModificationVO.getLocationValue();
		 String type = voterModificationVO.getLocationType();
		 String isForGender = voterModificationVO.getIsForGender();
		 Long voterStatusId = voterModificationVO.getVoterStatusId();
		 
		 StringBuffer queryStr = new StringBuffer();
		 
		 if(isForGender.equalsIgnoreCase("true")){
			 
			 if(!voterModificationVO.getGender().equalsIgnoreCase("TOTAL"))
			   queryStr.append("and model.voter.gender = '"+voterModificationVO.getGender()+"'");
			 
		 }
		 else{
			 
			 Long ageRangeId = voterModificationVO.getAgeRangeId();
			 
			 if(ageRangeId.longValue() == 1)
				 queryStr.append("and model.voter.age >= 18 and  model.voter.age <= 25");
			 else if(ageRangeId.longValue() == 2)
				 queryStr.append("and model.voter.age >= 26 and  model.voter.age <= 35");
			 else if(ageRangeId.longValue() == 3)
				 queryStr.append("and model.voter.age >= 36 and  model.voter.age <= 45");
			 else if(ageRangeId.longValue() == 4)
				 queryStr.append("and model.voter.age >= 46 and  model.voter.age <= 60");
			 else if(ageRangeId.longValue() == 5)
				 queryStr.append("and model.voter.age > 60");
			 
		 }
		 queryStr.append(" and model.voterStatus.voterStatusId = '"+voterStatusId+"'");
		 
		 
		List<Object[]> votersList =  voterModificationDAO.getAllSelectedVotersDetails(constituencyId, publicationIdsList,locationValue,type,queryStr.toString());
		
		
		
		   for(Object[] params:votersList){
			
			   VoterVO voterVO = new VoterVO();
			   
			   voterVO.setVoterId(params[0].toString());
			   voterVO.setFirstName(params[1].toString());
			   voterVO.setGender(params[2].toString());
			   voterVO.setAge((Long)params[3]);
			   if(params[4] != null)
			   voterVO.setRelativeFirstName(params[4].toString());
			   if(params[5] != null)
			   voterVO.setRelationshipType(params[5].toString());
			   if(params[6] != null)
			   voterVO.setBoothId((Long)params[6]);
			   if(params[7] != null){
			   voterVO.setBoothNo(Long.valueOf(params[7].toString()));
			   voterVO.setBoothName("Booth-"+params[7].toString());
			   }
			   
			   if(params[8] != null)
			   voterVO.setPanchayatName(params[8].toString());
			   voterVO.setStatus(params[9].toString());
			   voterVO.setPublicationDateId((Long)params[10]);
			   voterVO.setPublicationName(params[11].toString());
			   voterVO.setHouseNo(params[12] != null ? params[12].toString() : "");
			   voterVO.setVoterIDCardNo(params[13].toString());
			   voterVO.setHouseNo(params[14].toString());
			   voterVO.setVillagesCovered(params[15].toString());
			   voterDetails.add(voterVO);
				 
		  }
		 }catch(Exception e){
			 LOG.error("Exception raised in  the getSelectedVotersDetails service method");
			 e.printStackTrace();			 
		 }
		   return voterDetails;		 
	 }
	 
	 public String getLocationTypeForLocalEleBodyByLocalEleBodyId(Long localEleBodyId)
	 {
		 try{
			 return localElectionBodyDAO.getLocationTypeForLocalEleBodyByLocalEleBodyId(localEleBodyId);
		 }catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getLocationTypeForLocalEleBody() method,Exception - "+e);
			return "";
		}
	 }
	 
	 
	 public VoterModificationVO getBoothWiseVoterModificationDetails(String locationType,Long locationValue, Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 VoterModificationVO voterModificationVO = new VoterModificationVO();
		 try{
			
			 List<SelectOptionVO> locationValuesList = new ArrayList<SelectOptionVO>(0); 
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
			 List<Long> pubIdsListForTotVoters = new ArrayList<Long>(0);
			 
			 if(fromPublicationDateId != null && fromPublicationDateId > 0 && !pubIdsListForTotVoters.equals(fromPublicationDateId))
				 pubIdsListForTotVoters.add(fromPublicationDateId);
			 if(toPublicationDateId != null && toPublicationDateId > 0 && !pubIdsListForTotVoters.equals(toPublicationDateId))
				 pubIdsListForTotVoters.add(toPublicationDateId);
			 
			List<Long> boothIds = boothDAO.getBoothIdsByLocalValuesList(locationType, locationValue, constituencyId, publicationIdsList);
			 if(boothIds != null && boothIds.size() > 0)
				 locationValuesList.add(new SelectOptionVO(boothIds,"booth"));
			 
			 /*if(locationValuesList != null && locationValuesList.size() > 0)
				 voterModificationVO = getVoterModificationSubLevelsData(locationValuesList, constituencyId, publicationIdsList,pubIdsListForTotVoters);*/
			
			 voterModificationVO = getBoothWiseSubLevelData(locationValuesList, publicationIdsList, pubIdsListForTotVoters, constituencyId);
			
			 
			 return voterModificationVO;
		 }catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getBoothWiseVoterModificationDetails() Method, Exception - "+e);
			return voterModificationVO;
		}
	 }
	 

	 public VoterModificationVO getBoothWiseSubLevelData(List<SelectOptionVO> locationValuesList,List<Long> publicationIdsList,List<Long> pubIdsListForTotVoters,Long constituencyId)
	 {
		 VoterModificationVO voterModificationVO = new VoterModificationVO();
		 List<VoterModificationVO> voterModificationVOsList = new ArrayList<VoterModificationVO>(0);
		 try{
			    for(SelectOptionVO optionVO : locationValuesList)
			    {
				  String queryString = getVoterModificationSublevelQueryString(optionVO.getType());
				  List<Object[]> list = voterModificationDAO.getSublevelVoterModificationDetailsByLocationValues(constituencyId, publicationIdsList, optionVO.getLocationValuesList(), optionVO.getType(), queryString);
				  getVotermodificationDetailsFromVoterModificationTable(list, voterModificationVOsList, optionVO,pubIdsListForTotVoters,constituencyId);
			   }
			 voterModificationVO.setModifiedVotersList(voterModificationVOsList);
			 return voterModificationVO;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getBoothWiseSubLevelData() Method, Exception - "+e);
			 return voterModificationVO;
		}
	 }
	 public List<VoterModificationVO> getBoothWiseModificationsCompleteDetails(Long constituencyId, Long locationValue, Long publicationDateId, String locationType)
	 {
		 List<VoterModificationVO> voterModificationVOsList = new ArrayList<VoterModificationVO>(0);
		 try{
			 
			 List<Long> publicationIdsList = new ArrayList<Long>(0);
			 publicationIdsList.add(publicationDateId);
			      
			 List<Long> boothIds = new ArrayList<Long>(0);
			 
			 List<String> partNosList = new ArrayList<String>(0);
			 List<Long> partNoList = new ArrayList<Long>(0);
			 
			 List<Long> movedPartNosList = new ArrayList<Long>(0);
			 List<Long> relocatedPartNosList = new ArrayList<Long>(0);
			 
			 if(locationType != null && !locationType.equalsIgnoreCase("booth"))
			   boothIds = boothDAO.getBoothIdsByLocalValuesList(locationType, locationValue, constituencyId, publicationIdsList);
			 else
				 boothIds.add(locationValue);
			 
			 partNosList = boothDAO.getPartNosByBoothIdsList(constituencyId, publicationDateId, boothIds);
			 for(String no : partNosList)
				 partNoList.add(new Long(no));
		     
			 VoterModificationVO voterModificationVO = null;
			  
			 List<Object[]> statusList = voterModificationDAO.getBoothWiseVotersDataByBoothIds(constituencyId, publicationDateId, partNoList);
			 
			 if(statusList != null && statusList.size() > 0)
			 {
				for(Object[] status : statusList)
				{
					voterModificationVO = checkForvoterModificationVO(new Long(status[2].toString()),voterModificationVOsList);
					
					if(voterModificationVO == null)
					{
						voterModificationVO = new VoterModificationVO();
						voterModificationVO.setId((Long)status[3]);
						voterModificationVO.setPartNo(new Long(status[2].toString()));
						voterModificationVO.setVillageCovered(status[4] != null ? status[4].toString() : "");
						voterModificationVOsList.add(voterModificationVO);
					}
					 
				      if(status[1].toString().equalsIgnoreCase(IConstants.STATUS_ADDED))
						voterModificationVO.setAddedCount((Long)status[0]);
					  else if(status[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED))
						voterModificationVO.setDeletedCount((Long)status[0]);
					  else if(status[1].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
					  {
						voterModificationVO.setMovedCount((Long)status[0]);
						movedPartNosList.add(new Long(status[2].toString()));
					  }
					  else if(status[1].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
					  {
						voterModificationVO.setRelocatedCount((Long)status[0]);
						relocatedPartNosList.add(new Long(status[2].toString()));
					  }
				  }
			 
			 
				  for(Long part : movedPartNosList)
				   if(!relocatedPartNosList.contains(part))
					 relocatedPartNosList.add(part);
			      if(relocatedPartNosList != null && relocatedPartNosList.size() > 0)
			        getMovedOrRelocatedVoters(constituencyId, publicationDateId, relocatedPartNosList,voterModificationVOsList);
			 	
			 } 
			 
			 return voterModificationVOsList;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getBoothWiseModificationsCompleteDetails() Method, Exception - "+e);
			 return voterModificationVOsList;
		}
	 }
	 
	 public VoterModificationVO checkForvoterModificationVO(Long partNo, List<VoterModificationVO> list)
	 {
		 try{
			 if(list == null || list.size() == 0)
				 return null;
			 for(VoterModificationVO modificationVO : list)
				 if(modificationVO.getPartNo().equals(partNo))
					 return modificationVO;
			 return null;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in checkForvoterModificationVO() method, Exception - "+e);
			 return null;
		 }
	 }
	 
	 public void getMovedOrRelocatedVoters(Long constituencyId, Long publicationDateId, List<Long> partNosList,List<VoterModificationVO> resuList)
	 {
		 try{
			 List<Object[]> list = voterModificationDAO.getMovedOrRelocatedVoterDetails(constituencyId, publicationDateId, partNosList);
			 if(list != null && list.size() > 0)
			 {
				 VoterModificationVO modificationVO2 = null;
				 for(Object[] params : list)
				 {
					 VoterVO voterVO = new VoterVO();
					 voterVO.setFirstName(params[1] != null ?params[1].toString() :"");
					 voterVO.setVoterId(params[0] != null ?params[0].toString() : "");
					 voterVO.setGender(params[2] != null ?params[2].toString() : "");
					 voterVO.setAge(params[3] != null ?(Long)params[3] : 0l);
					 voterVO.setRelationshipType(params[5] != null ?params[5].toString():"");
					 voterVO.setVillagesCovered(params[8] != null ?params[8].toString():"");
					 voterVO.setBoothId((Long)params[6]);
					 voterVO.setHouseNo(params[9] !=null?params[9].toString():"");
					 voterVO.setVoterIDCardNo(params[10] !=null?params[10].toString():"");
					 voterVO.setRelativeName(params[11] !=null?params[11].toString():"");
					 voterVO.setPartNo(new Long(params[7].toString()));
					 
					 modificationVO2 = checkForvoterModificationVO(new Long(params[7].toString()), resuList);
					 
					 if(params[12] != null && params[12].toString().equalsIgnoreCase(IConstants.STATUS_MOVED))
					 {
						List<Long> list1 = voterModificationDAO.getPartNoForMovedOrRelocatedVoter((Long)params[0], publicationDateId, constituencyId, voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_RELOCATED));
						if(list1 != null && list1.size() > 0)
						  voterVO.setMovedOrRelocatedPartNo((Long)list1.get(0));
						 
					   modificationVO2.getMovedVoterVOsList().add(voterVO); 
					 }
					 if(params[12] != null && params[12].toString().equalsIgnoreCase(IConstants.STATUS_RELOCATED))
					 {
						 List<Long> list1 = voterModificationDAO.getPartNoForMovedOrRelocatedVoter((Long)params[0], publicationDateId, constituencyId, voterStatusDAO.getVoterStatusIdByStatus(IConstants.STATUS_MOVED));
						 if(list1 != null && list1.size() > 0)
							 voterVO.setMovedOrRelocatedPartNo((Long)list1.get(0));
					   modificationVO2.getRelocatedVoterVOsList().add(voterVO);
					 }
				 }
			 }
		 }catch (Exception e) {
			e.printStackTrace();
			LOG.error("Exception Occured in getMovedOrRelocatedVoters() method, Exception - "+e);
		}
	 }
	 
}
