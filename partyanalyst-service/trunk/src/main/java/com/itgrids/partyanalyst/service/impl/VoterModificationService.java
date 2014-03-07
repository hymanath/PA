package com.itgrids.partyanalyst.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IConstituencyHierarchyInfoDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyDAO;
import com.itgrids.partyanalyst.dao.IDistrictDAO;
import com.itgrids.partyanalyst.dao.ILocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IPanchayatDAO;
import com.itgrids.partyanalyst.dao.IPanchayatHamletDAO;
import com.itgrids.partyanalyst.dao.IPublicationDateDAO;
import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.IUserVoterDetailsDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationAgeInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterReportLevelDAO;
import com.itgrids.partyanalyst.dao.IVoterStatusDAO;
import com.itgrids.partyanalyst.dto.ConstituencyHierarchyInfoVO;
import com.itgrids.partyanalyst.dto.PanchayatAddedOrDeletedVO;
import com.itgrids.partyanalyst.dto.PdfVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAdderdOrDeletedRengesInfoVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.dto.VotersDetailsVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.ConstituencyHierarchyInfo;
import com.itgrids.partyanalyst.model.LocalElectionBody;
import com.itgrids.partyanalyst.model.VoterAgeRange;
import com.itgrids.partyanalyst.model.VoterModificationAgeInfo;
import com.itgrids.partyanalyst.model.VoterModificationInfo;
import com.itgrids.partyanalyst.service.IRegionServiceData;
import com.itgrids.partyanalyst.service.IStaticDataService;
import com.itgrids.partyanalyst.service.IVoterModifiationPdfsGenerations;
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
	private IUserVoterDetailsDAO userVoterDetailsDAO;
	private IRegionServiceData regionServiceDataImp;
	private IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO;
	private IPanchayatHamletDAO panchayatHamletDAO;
	private IDistrictDAO districtDAO ;
	private IDelimitationConstituencyDAO delimitationConstituencyDAO;
	private IVoterModifiationPdfsGenerations voterModifiationPdfsGenerations;
	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14,
		      Font.BOLD);
	private static Font catFont1 = new Font(Font.FontFamily.TIMES_ROMAN, 11,
		      Font.BOLD);
	 private IStaticDataService staticDataService;
	    

		public IStaticDataService getStaticDataService() {
			return staticDataService;
		}

		public void setStaticDataService(IStaticDataService staticDataService) {
			this.staticDataService = staticDataService;
		}
   /* private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
		      Font.NORMAL, BaseColor.RED);
    private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
		      Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
		      Font.BOLD);
	  */
	
		
	
	/**
	 * @return the voterModifiationPdfsGenerations
	 */
	public IVoterModifiationPdfsGenerations getVoterModifiationPdfsGenerations() {
		return voterModifiationPdfsGenerations;
	}

	public IDistrictDAO getDistrictDAO() {
		return districtDAO;
	}

	public void setDistrictDAO(IDistrictDAO districtDAO) {
		this.districtDAO = districtDAO;
	}

	/**
	 * @return the delimitationConstituencyDAO
	 */
	public IDelimitationConstituencyDAO getDelimitationConstituencyDAO() {
		return delimitationConstituencyDAO;
	}

	/**
	 * @param delimitationConstituencyDAO the delimitationConstituencyDAO to set
	 */
	public void setDelimitationConstituencyDAO(
			IDelimitationConstituencyDAO delimitationConstituencyDAO) {
		this.delimitationConstituencyDAO = delimitationConstituencyDAO;
	}

	/**
	 * @param voterModifiationPdfsGenerations the voterModifiationPdfsGenerations to set
	 */
	public void setVoterModifiationPdfsGenerations(
			IVoterModifiationPdfsGenerations voterModifiationPdfsGenerations) {
		this.voterModifiationPdfsGenerations = voterModifiationPdfsGenerations;
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

	public IUserVoterDetailsDAO getUserVoterDetailsDAO() {
		return userVoterDetailsDAO;
	}

	public void setUserVoterDetailsDAO(IUserVoterDetailsDAO userVoterDetailsDAO) {
		this.userVoterDetailsDAO = userVoterDetailsDAO;
	}

	public IRegionServiceData getRegionServiceDataImp() {
		return regionServiceDataImp;
	}

	public void setRegionServiceDataImp(IRegionServiceData regionServiceDataImp) {
		this.regionServiceDataImp = regionServiceDataImp;
	}

	public IConstituencyHierarchyInfoDAO getConstituencyHierarchyInfoDAO() {
		return constituencyHierarchyInfoDAO;
	}

	public void setConstituencyHierarchyInfoDAO(
			IConstituencyHierarchyInfoDAO constituencyHierarchyInfoDAO) {
		this.constituencyHierarchyInfoDAO = constituencyHierarchyInfoDAO;
	}
	public IPanchayatHamletDAO getPanchayatHamletDAO() {
		return panchayatHamletDAO;
	}

	public void setPanchayatHamletDAO(IPanchayatHamletDAO panchayatHamletDAO) {
		this.panchayatHamletDAO = panchayatHamletDAO;
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
					 if(ageRange != null && ageRange.equalsIgnoreCase(IConstants.YOUNGER_VOTERS))
					  ageRange = IConstants.YOUNG_VOTERS_AGE_RANGE;
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
				 List<Long> previousPublications = publicationDateDAO.getPreviousPublicationIds(publicationDateId) ;
				 Long previousPublicationId = publicationDateId;
				 if(previousPublications != null && previousPublications.size() > 0){
					 previousPublicationId = previousPublications.get(0);
				 }
			  if(constituencyIdsList != null && constituencyIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("constituency", constituencyIdsList, constituencyId, publicationDateId,previousPublicationId);
			  
			  if(mandalIdsList != null && mandalIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("mandal", mandalIdsList, constituencyId, publicationDateId,previousPublicationId);
			  
			  if(localEleBodyIdsList != null && localEleBodyIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("localElectionBody", localEleBodyIdsList, constituencyId, publicationDateId,previousPublicationId);
			  
			  if(panchayatIdsList != null && panchayatIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("panchayat", panchayatIdsList, constituencyId, publicationDateId,previousPublicationId);
			  
			  /*if(boothIdsList != null && boothIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("booth", boothIdsList, constituencyId, publicationDateId);*/
			  
			  /*if(wardIdsList != null && wardIdsList.size() > 0)
				  saveGenderWiseVoterModifInfoInIntermediateTables("ward", wardIdsList, constituencyId, publicationDateId,previousPublicationId);
			  */
			 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			 return resultStatus;
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in insertGenderWiseVoterModifInfoInVoterModificationInfoTable() Method, Exception - "+e);
			 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			 return resultStatus;
		}
	 }
	 
	 public ResultStatus saveGenderWiseVoterModifInfoInIntermediateTables(String locationType, List<Long> locationValuesList, Long constituencyId, Long publicationDateId,Long previousPublicationId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
			 for(Long locationVal:locationValuesList){
				 List<Long> value = new ArrayList<Long>();
				 value.add(locationVal);
			 List<VoterModificationGenderInfoVO> modificationGenderInfoVO = getGenderWiseVoterModificationsByPublicationId(locationType, value, constituencyId, publicationDateId,previousPublicationId);
			 
			 if(modificationGenderInfoVO == null || modificationGenderInfoVO.size() == 0)
				 modificationGenderInfoVO =  setDefaultValuesForGenderwiseVoterModification(locationVal,locationType);
				 
			 saveGenderWiseVoterModifInfoInVoterModificationInfoTable(modificationGenderInfoVO,constituencyId,publicationDateId,locationType);
			 
			 List<VoterModificationAgeRangeVO> ageRangeVOs = getVotersAddedAndDeletedCountAgeWiseByPublicationId(locationType, value, constituencyId, publicationDateId,previousPublicationId);
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
	 
	 public List<VoterModificationGenderInfoVO> getGenderWiseVoterModificationsByPublicationId(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId,Long previousPublicationId)
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
			 List<Object[]> list = new ArrayList<Object[]>();
			 if(!(locationType.equalsIgnoreCase("booth") || locationType.equalsIgnoreCase("ward"))){
				 List<Object[]> listDel = voterModificationDAO.getGenderWiseVoterModificationByPublicationId(locationType, locationValuesList, constituencyId, publicationDateId,queryStr.toString(),"Deleted",previousPublicationId);
				 if(listDel != null && listDel.size() > 0){
					 list.addAll(listDel);
				 }
				 List<Object[]> listOthers = voterModificationDAO.getGenderWiseVoterModificationByPublicationId(locationType, locationValuesList, constituencyId, publicationDateId,queryStr.toString(),"Added",publicationDateId);
				 if(listOthers != null && listOthers.size() > 0){
					 list.addAll(listOthers);
				 }	 
			 }else{
				 list = voterModificationDAO.getGenderWiseVoterModificationByPublicationId(locationType, locationValuesList, constituencyId, publicationDateId,queryStr.toString(),"",publicationDateId);
			 }
			 
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
	 
	 
	 public List<VoterModificationAgeRangeVO> getVotersAddedAndDeletedCountAgeWiseByPublicationId(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId,Long previousPublicationId)
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
				 Long ageFrom = null;
				 Long ageTo = null;
				 if(ageRange.equalsIgnoreCase("Young Voters"))
				 {
					// System.out.println("yes");
					 ageFrom = 18l;
					 ageTo   = 22l;
				 }
				 else
				 {
					 String[] ages = ageRange.split("-");
					 ageFrom = new Long(ages[0].trim());
					  ageTo = null;
					 if(!ages[1].trim().equalsIgnoreCase("Above"))
						 ageTo = Long.valueOf(ages[1].trim());
				 }
				
				
					 
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
				 
				 List<Object[]> list = new ArrayList<Object[]>();
				 if(!(locationType.equalsIgnoreCase("booth") || locationType.equalsIgnoreCase("ward"))){
					 List<Object[]> listDel = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(locationType, locationValuesList, constituencyId, publicationDateId, ageFrom, ageTo,queryStr.toString(),"Deleted",previousPublicationId);
					 if(listDel != null && listDel.size() > 0){
						 list.addAll(listDel);
					 }
					 List<Object[]> listOthers = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(locationType, locationValuesList, constituencyId, publicationDateId, ageFrom, ageTo,queryStr.toString(),"Added",publicationDateId);
					 if(listOthers != null && listOthers.size() > 0){
						 list.addAll(listOthers);
					 }	 
				 }else{
					 list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(locationType, locationValuesList, constituencyId, publicationDateId, ageFrom, ageTo,queryStr.toString(),"",publicationDateId);
				 }
				 // list = voterModificationDAO.getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(locationType, locationValuesList, constituencyId, publicationDateId, ageFrom, ageTo,queryStr.toString());
				 
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
				 stringBuilder.append(" model2.constituency.constituencyId, model2.constituency.name ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.MANDAL))
				 stringBuilder.append(" model2.tehsil.tehsilId,model2.tehsil.tehsilName ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || locationType.equalsIgnoreCase("localElectionBody"))
				 stringBuilder.append(" model2.localBody.localElectionBodyId,model2.localBody.name,model2.localBody.electionType.electionType ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				 stringBuilder.append(" model2.panchayat.panchayatId,model2.panchayat.panchayatName ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.BOOTH))
				 stringBuilder.append(" model2.boothId,model2.partNo ");
			 
			 else if(locationType.equalsIgnoreCase(IConstants.WARD))
				 stringBuilder.append(" model2.localBodyWard.constituencyId,model2.localBodyWard.name ");
			
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
				 queryStr.append("and model.voter.age >= 18 and  model.voter.age <= 22");
			 else if(ageRangeId.longValue() == 2)
				 queryStr.append("and model.voter.age >= 18 and  model.voter.age <= 25");
			 else if(ageRangeId.longValue() == 3)
				 queryStr.append("and model.voter.age >= 26 and  model.voter.age <= 35");
			 else if(ageRangeId.longValue() == 4)
				 queryStr.append("and model.voter.age >= 36 and  model.voter.age <= 45");
			 else if(ageRangeId.longValue() == 5)
				 queryStr.append("and model.voter.age >= 46 and  model.voter.age <= 60");
			 else if(ageRangeId.longValue() == 6)
				 queryStr.append("and model.voter.age > 60");
			 else if(ageRangeId.longValue() == 1)
			 {
				 Long ageFrom =  IConstants.YOUNG_VOTERS_AGE_FROM;
				 Long ageTo = IConstants.YOUNG_VOTERS_AGE_TO;
				 
				 queryStr.append("and model.voter.age >= :ageFrom and  model.voter.age <= :ageTo ");
			 }
				
			
			 
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
	 
	 
	 public ResultStatus insertConstituencyBasicData(Long constituencyId,Long publicationId,Long userId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
			 ConstituencyHierarchyInfoVO constituencyHierarchyInfoVO = new ConstituencyHierarchyInfoVO();
			 List<Long> mandalIdsList = new ArrayList<Long>(0);
			 List<Long> muncipalityIdsList = new ArrayList<Long>(0);
			 
			 String areatype =  constituencyDAO.get(constituencyId).getAreaType();
			 
			List<SelectOptionVO> mundalsAndMuncipalityList = regionServiceDataImp.getSubRegionsInConstituency(constituencyId, IConstants.PRESENT_YEAR, null);
			 if(mundalsAndMuncipalityList != null && mundalsAndMuncipalityList.size() > 0)
			 {
				for(SelectOptionVO optionVO:mundalsAndMuncipalityList)
				{
				 if(optionVO.getId().toString().substring(0,1).equalsIgnoreCase("2"))
					 mandalIdsList.add(new Long(optionVO.getId().toString().substring(1)));
				 else
				  muncipalityIdsList.add(optionVO.getId());
				}
			 }
			 
			 constituencyHierarchyInfoVO.setMandalIdsList(mandalIdsList);
			 constituencyHierarchyInfoVO.setMuncipalityIdsList(muncipalityIdsList);
			 
			 if(areatype.equalsIgnoreCase(IConstants.RURAL) || areatype.equalsIgnoreCase(IConstants.RURALURBAN))
			  constituencyHierarchyInfoVO.setPanchayatIdsList(boothDAO.getPanchayatsListByConstituencyId(constituencyId, publicationId)); 
			
			 constituencyHierarchyInfoVO.setBoothIdsList(boothDAO.getBoothsListByConstituencyId(constituencyId, publicationId));
			 //constituencyHierarchyInfoVO.setHamletIdsList(userVoterDetailsDAO.getHamletIdsListByUserIdAndConstituencyId(constituencyId,publicationId,userId));
			 constituencyHierarchyInfoVO.setHamletIdsList(boothDAO.getHamletIdsListByConstituencyId(constituencyId, publicationId));
			 
			 if(muncipalityIdsList != null && muncipalityIdsList.size() > 0)
			  constituencyHierarchyInfoVO.setWardIdsList(getWardIdsList(muncipalityIdsList, userId, constituencyId, publicationId));
			 
			 constituencyHierarchyInfoVO.setUserId(userId);
			 constituencyHierarchyInfoVO.setConstituencyId(constituencyId);
			 constituencyHierarchyInfoVO.setPublicationDateId(publicationId);
			 
			 //constituency 
			 constituencyHierarchyInfoVO.setReportLevelId(voterReportLevelDAO.getReportLevelIdByType(IConstants.CONSTITUENCY));
			 constituencyHierarchyInfoVO.setReportLevelValue(constituencyId);
			 setConstituencyBasicInformation(constituencyHierarchyInfoVO);
			 
			 setConstituencyMandalData(constituencyHierarchyInfoVO,IConstants.MANDAL);
			 setConstituencyPanchayatData(constituencyHierarchyInfoVO,IConstants.PANCHAYAT);
			 setConstituencyBoothData(constituencyHierarchyInfoVO,IConstants.BOOTH);
			 setConstituencyWardData(constituencyHierarchyInfoVO,IConstants.WARD);
			 setConstituencyHamletData(constituencyHierarchyInfoVO,IConstants.HAMLET);
			 setConstituencyMuncipalityData(constituencyHierarchyInfoVO,IConstants.LOCALELECTIONBODY);
			 
			 
		 return resultStatus;
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error(" Exception Occured in insertConstituencyBasicData() method, Exception - "+e);
			 
			 return resultStatus;
		}
	 }
	 
	 
	 public List<Long> getWardIdsList(List<Long> muncipalityIdsList,Long userId,Long constituencyId,Long publicationId)
	 {
		 List<Long> wardIdsList = new ArrayList<Long>(0);
		 try{
			
			 if(muncipalityIdsList != null && muncipalityIdsList.size() > 0)
				{
				  for(Long id : muncipalityIdsList)
				  {
					List<Long> wardIds = null;
					List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
	  				id = (Long)list2.get(0);
	  			    String electionType = localElectionBodyDAO.get(id).getElectionType().getElectionType();
	  			    if(electionType != null && electionType.equalsIgnoreCase(IConstants.GHMC))
	  			     wardIds = boothDAO.getWardIdsByLocalEleBodyId(id, publicationId, constituencyId);
	  			    else
	  			     wardIds = userVoterDetailsDAO.getWardIdsByLocalEleBodyId(constituencyId, userId, publicationId, id);
	  			    if(wardIds != null && wardIds.size() > 0)
	  			    	wardIdsList.addAll(wardIds);
				  }
				} 
			 return wardIdsList;
		 }catch (Exception e) {
		  e.printStackTrace();
		  LOG.error(" Exception Occured in getWardIdsList() method, Exception - "+e);
		  return wardIdsList;	 
		}
	 }
	 
	 
	 public ResultStatus setConstituencyMandalData(ConstituencyHierarchyInfoVO hierarchyInfoVO,String type)
	 {
		 ResultStatus resultStatus = new ResultStatus();
			try{
			List<Long> mandalIdsList = hierarchyInfoVO.getMandalIdsList();
			if(mandalIdsList == null || mandalIdsList.size() == 0)
			{
			  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			  return resultStatus;
			}
			
			List<ConstituencyHierarchyInfoVO> constituencyHierarchyInfoVOList = new ArrayList<ConstituencyHierarchyInfoVO>(0);
			Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
			
			//Panchayats Count By MandalIdsList
			List<Object[]> panchayatsCountList = boothPublicationVoterDAO.getPanchayatsCountByMandalIdsList(mandalIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(),"panchayatsCount");
			if(panchayatsCountList != null && panchayatsCountList.size() > 0)
			 setConstituencyHieraryData(panchayatsCountList, constituencyHierarchyInfoVOList, "panchayatsCount");
			
			//booths Count by MandalIdsList
			List<Object[]> boothsCountList = boothPublicationVoterDAO.getPanchayatsCountByMandalIdsList(mandalIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(),"boothsCount");
			if(boothsCountList != null && boothsCountList.size() > 0)
			 setConstituencyHieraryData(boothsCountList, constituencyHierarchyInfoVOList, "boothsCount");
			
			//hamlets Count by mandalIds List
			//List<Object[]> hamletsCount = userVoterDetailsDAO.getHamletIdsListByMandalIdsList(hierarchyInfoVO.getConstituencyId(),  hierarchyInfoVO.getPublicationDateId(),  hierarchyInfoVO.getUserId(), mandalIdsList,"mandalHamlets");
			List<Object[]> hamletsCount = panchayatHamletDAO.getHamletCount(mandalIdsList,"mandalHamlets");
			if(hamletsCount != null && hamletsCount.size() > 0)
			 setConstituencyHieraryData(hamletsCount, constituencyHierarchyInfoVOList, "hamletsCount");
			
			if(constituencyHierarchyInfoVOList != null && constituencyHierarchyInfoVOList.size() > 0)
			 saveConstituencyInfoData(constituencyHierarchyInfoVOList,hierarchyInfoVO,reportLevelId);
			
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
			}catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in setConstituencyMandalData() method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
	 }
	 
	 public ResultStatus setConstituencyPanchayatData(ConstituencyHierarchyInfoVO hierarchyInfoVO,String type)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
		 List<Long> PanchayatIdsList = hierarchyInfoVO.getPanchayatIdsList();
		 if(PanchayatIdsList == null || PanchayatIdsList.size() == 0)
		 {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		 }
			
		 List<ConstituencyHierarchyInfoVO> constituencyHierarchyInfoVOList = new ArrayList<ConstituencyHierarchyInfoVO>(0);
		 Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
		 
		 //booths Count by panchayatIdsList
		 List<Object[]> boothsCount = boothPublicationVoterDAO.getBoothsCountByPanchayatIdsList(PanchayatIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), "panchayatBooths");
		 if(boothsCount != null && boothsCount.size() > 0)
			setConstituencyHieraryData(boothsCount, constituencyHierarchyInfoVOList, "boothsCount");
		 
		 //hamlets Count by panchayat Ids
		 //List<Object[]> hamletsCount = userVoterDetailsDAO.getHamletIdsListByMandalIdsList(hierarchyInfoVO.getConstituencyId(),  hierarchyInfoVO.getPublicationDateId(),  hierarchyInfoVO.getUserId(), PanchayatIdsList,"panchayatHamlets");
		 List<Object[]> hamletsCount = panchayatHamletDAO.getHamletCount(PanchayatIdsList, "panchayatHamlets");
		 if(hamletsCount != null && hamletsCount.size() > 0)
			setConstituencyHieraryData(hamletsCount, constituencyHierarchyInfoVOList, "hamletsCount");
		  
		  if(constituencyHierarchyInfoVOList != null && constituencyHierarchyInfoVOList.size() > 0)
			saveConstituencyInfoData(constituencyHierarchyInfoVOList,hierarchyInfoVO,reportLevelId);
		 
		  
		  
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in setConstituencyMandalData() method, Exception - "+e);
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
		} 
	 }
	 
	 public ResultStatus setConstituencyBoothData(ConstituencyHierarchyInfoVO hierarchyInfoVO,String type)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
		 List<Long> boothIdsList = hierarchyInfoVO.getBoothIdsList();
		 if(boothIdsList == null || boothIdsList.size() == 0)
		 {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		 }
			
		 List<ConstituencyHierarchyInfoVO> constituencyHierarchyInfoVOList = new ArrayList<ConstituencyHierarchyInfoVO>(0);
		 Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
		 
		 //hamlets Count By boothIdsList
		 List<Object[]> hamletsCount = userVoterDetailsDAO.getHamletIdsListByMandalIdsList(hierarchyInfoVO.getConstituencyId(),hierarchyInfoVO.getPublicationDateId(), hierarchyInfoVO.getUserId(), boothIdsList,"boothHamlets");
		 if(hamletsCount != null && hamletsCount.size() > 0)
		  setConstituencyHieraryData(hamletsCount, constituencyHierarchyInfoVOList, "hamletsCount");
		 
		 //wards Count by BoothIdsList
		  List<Object[]> wardsCount = userVoterDetailsDAO.getWardsCountByBoothIdsList(boothIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), hierarchyInfoVO.getUserId()); 
		  if(wardsCount != null && wardsCount.size() > 0)
			setConstituencyHieraryData(wardsCount, constituencyHierarchyInfoVOList, "wardsCount");
		 
		 if(constituencyHierarchyInfoVOList != null && constituencyHierarchyInfoVOList.size() > 0)
			saveConstituencyInfoData(constituencyHierarchyInfoVOList,hierarchyInfoVO,reportLevelId);
		 
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in setConstituencyBoothData() method, Exception - "+e);
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
		} 
	 }
	 
	 public ResultStatus setConstituencyWardData(ConstituencyHierarchyInfoVO hierarchyInfoVO,String type)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
		 List<Long> wardIdsList = hierarchyInfoVO.getWardIdsList();
		 if(wardIdsList == null || wardIdsList.size() == 0)
		 {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		 }
		 Long localEleBodyId = hierarchyInfoVO.getMuncipalityIdsList().get(0);
		 
		 List<ConstituencyHierarchyInfoVO> constituencyHierarchyInfoVOList = new ArrayList<ConstituencyHierarchyInfoVO>(0);
		 Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
		 
		 
			List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(localEleBodyId.toString().trim().substring(1)));
			localEleBodyId = (Long)list2.get(0);
			String electionType = localElectionBodyDAO.get(localEleBodyId).getElectionType().getElectionType();
			
			List<Object[]> boothsCount = null;
			if(electionType != null && electionType.equalsIgnoreCase(IConstants.GHMC))
			 boothsCount = boothPublicationVoterDAO.getBoothsCountByPanchayatIdsList(wardIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), "wardBooths");
			else
			 boothsCount = userVoterDetailsDAO.getBoothsCountByWardIdsList(wardIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), hierarchyInfoVO.getUserId(),"wardBooths");
			
			if(boothsCount != null && boothsCount.size() > 0)
			 setConstituencyHieraryData(boothsCount, constituencyHierarchyInfoVOList, "boothsCount"); 
			
			if(constituencyHierarchyInfoVOList != null && constituencyHierarchyInfoVOList.size() > 0)
			 saveConstituencyInfoData(constituencyHierarchyInfoVOList,hierarchyInfoVO,reportLevelId);
		 
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in setConstituencyBoothData() method, Exception - "+e);
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
		} 
	 }
	 
	 
	 public ResultStatus setConstituencyHamletData(ConstituencyHierarchyInfoVO hierarchyInfoVO,String type)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
		 List<Long> hamletIdsList = hierarchyInfoVO.getHamletIdsList();
		 if(hamletIdsList == null || hamletIdsList.size() == 0)
		 {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		 }
		 
		 List<ConstituencyHierarchyInfoVO> constituencyHierarchyInfoVOList = new ArrayList<ConstituencyHierarchyInfoVO>(0);
		 Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
		 
		List<Object[]> boothsCount = userVoterDetailsDAO.getBoothsCountByWardIdsList(hamletIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), hierarchyInfoVO.getUserId(),"hamletBooths");
		if(boothsCount != null && boothsCount.size() > 0)
		 setConstituencyHieraryData(boothsCount, constituencyHierarchyInfoVOList, "boothsCount");
		 
		 if(constituencyHierarchyInfoVOList != null && constituencyHierarchyInfoVOList.size() > 0)
			 saveConstituencyInfoData(constituencyHierarchyInfoVOList,hierarchyInfoVO,reportLevelId);
		 
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in setConstituencyHamletData() method, Exception - "+e);
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
		} 
		  
	 }
	 
	 
	 public ResultStatus setConstituencyMuncipalityData(ConstituencyHierarchyInfoVO hierarchyInfoVO,String type)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
		 List<Long> muncipalityIdsList = hierarchyInfoVO.getMuncipalityIdsList();
		 if(muncipalityIdsList == null || muncipalityIdsList.size() == 0)
		 {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		 }
		 List<Long> localElectionBodyIdsList = new ArrayList<Long>(0);
		 for(Long id:muncipalityIdsList)
		 {
		  List<Object> list2 = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(new Long(id.toString().trim().substring(1)));
		  localElectionBodyIdsList.add((Long)list2.get(0));
		 }
		 
		 List<ConstituencyHierarchyInfoVO> constituencyHierarchyInfoVOList = new ArrayList<ConstituencyHierarchyInfoVO>(0);
		 Long reportLevelId = voterReportLevelDAO.getReportLevelIdByType(type);
		 
		 if(localElectionBodyIdsList != null && localElectionBodyIdsList.size() > 0)
		 {
		  //get booths COunt 
		  List<Object[]> boothsCount = boothPublicationVoterDAO.getBoothsCountByPanchayatIdsList(localElectionBodyIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), "muncipalityBooths");
		  if(boothsCount != null && boothsCount.size() > 0)
			setConstituencyHieraryData(boothsCount, constituencyHierarchyInfoVOList, "boothsCount");
		  
		  //wards Count
		  List<Object[]> wardsList = null;
		  String electionType = localElectionBodyDAO.get(localElectionBodyIdsList.get(0)).getElectionType().getElectionType();
		  if(electionType != null && electionType.equalsIgnoreCase(IConstants.GHMC))
		   wardsList = boothPublicationVoterDAO.getMuncipalityWardsCount(hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), localElectionBodyIdsList);	
		  else
		   wardsList = userVoterDetailsDAO.getWardsCountByMuncipalityIdsList(localElectionBodyIdsList, hierarchyInfoVO.getConstituencyId(), hierarchyInfoVO.getPublicationDateId(), hierarchyInfoVO.getUserId());
			  
		  if(wardsList != null && wardsList.size() > 0)
		   setConstituencyHieraryData(wardsList, constituencyHierarchyInfoVOList, "wardsCount");
		 
		 }
		 
		 if(constituencyHierarchyInfoVOList != null && constituencyHierarchyInfoVOList.size() > 0)
			 saveConstituencyInfoData(constituencyHierarchyInfoVOList,hierarchyInfoVO,reportLevelId);
		 
		 resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		 return resultStatus;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error(" Exception Occured in setConstituencyHamletData() method, Exception - "+e);
		 resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		 return resultStatus;
		} 
	 }
	 
	 public ConstituencyHierarchyInfoVO checkConstituencyHierachyVOExist(Long reportLevelvalue,List<ConstituencyHierarchyInfoVO> list)
	 {
		try{
		if(list == null || list.size() == 0)
			return null;
		for(ConstituencyHierarchyInfoVO vo:list)
		 if(vo.getReportLevelValue().equals(reportLevelvalue))
			 return vo;
			
			return null;
		}catch (Exception e) {
		 e.printStackTrace();
		 LOG.error("Exception Occured in checkConstituencyHierachyVOExist() method, Exception - "+e);
		 return null;
		}
	 }
	 
	 public void setConstituencyHieraryData(List<Object[]> list,List<ConstituencyHierarchyInfoVO> constituencyHierarchyInfoVOList,String type)
	 {
		 try{
			 ConstituencyHierarchyInfoVO infoVO = null;
			 if(list != null && list.size() > 0)
				{
				  for(Object[] params:list)
				  {
					 infoVO = checkConstituencyHierachyVOExist((Long)params[0],constituencyHierarchyInfoVOList);
					 if(infoVO == null)
					 {
						infoVO = new ConstituencyHierarchyInfoVO();
						infoVO.setReportLevelValue((Long)params[0]); 
						constituencyHierarchyInfoVOList.add(infoVO);
					 }
					 if(type != null && type.equalsIgnoreCase("panchayatsCount"))
					  infoVO.setTotalPanchayats((Long)params[1]); 
					 else if(type != null && type.equalsIgnoreCase("boothsCount"))
					  infoVO.setTotalBooths((Long)params[1]); 
					 else if(type != null && type.equalsIgnoreCase("hamletsCount"))
					  infoVO.setTotalHamlets((Long)params[1]);  
					 else if(type != null && type.equalsIgnoreCase("wardsCount"))
					  infoVO.setTotalWards((Long)params[1]);
					 
				  }
				}
			 
		 }catch (Exception e) {
			 e.printStackTrace();
			 LOG.error("Exception Occured in setConstituencyHieraryData() method, Exception - "+e);
			}
	 }
	 
	 
	 public ResultStatus saveConstituencyInfoData(List<ConstituencyHierarchyInfoVO> list,ConstituencyHierarchyInfoVO hierarchyInfoVO,Long reportLevelId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
		
			 if(list != null && list.size() > 0)
				{
				 for(ConstituencyHierarchyInfoVO infoVO:list)
				 {
					 infoVO.setReportLevelId(reportLevelId);
					 infoVO.setConstituencyId(hierarchyInfoVO.getConstituencyId());
					 infoVO.setPublicationDateId(hierarchyInfoVO.getPublicationDateId());
					 infoVO.setUserId(hierarchyInfoVO.getUserId());
				 }
				 
				 for(ConstituencyHierarchyInfoVO infoVO : list)
				  saveConstituencyBasicInformation(infoVO);
				 
				} 
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			 return resultStatus;
		 }catch (Exception e) {
		  e.printStackTrace();
		  LOG.error(" Exception Occured in saveConstituencyInfoData() method, Exception - "+e);
		  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
		}
	 }
	 
	 public ResultStatus setConstituencyBasicInformation(ConstituencyHierarchyInfoVO hierarchyInfoVO)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 	
		try{	
		ConstituencyHierarchyInfoVO ConstituencyHierarchyInfo = new ConstituencyHierarchyInfoVO();
		
		ConstituencyHierarchyInfo.setReportLevelId(hierarchyInfoVO.getReportLevelId());
		ConstituencyHierarchyInfo.setReportLevelValue(hierarchyInfoVO.getReportLevelValue());
		ConstituencyHierarchyInfo.setConstituencyId(hierarchyInfoVO.getConstituencyId());
		ConstituencyHierarchyInfo.setUserId(hierarchyInfoVO.getUserId());
		ConstituencyHierarchyInfo.setPublicationDateId(hierarchyInfoVO.getPublicationDateId());
		ConstituencyHierarchyInfo.setTotalMandals(hierarchyInfoVO.getMandalIdsList()!= null?(long)hierarchyInfoVO.getMandalIdsList().size():0L);
		ConstituencyHierarchyInfo.setTotalPanchayats(hierarchyInfoVO.getPanchayatIdsList() != null?(long)hierarchyInfoVO.getPanchayatIdsList().size():0L);
		ConstituencyHierarchyInfo.setTotalMuncipalities(hierarchyInfoVO.getMuncipalityIdsList()!= null?(long)hierarchyInfoVO.getMuncipalityIdsList().size():0L);
		ConstituencyHierarchyInfo.setTotalBooths(hierarchyInfoVO.getBoothIdsList()!= null?(long)hierarchyInfoVO.getBoothIdsList().size():0L);
		ConstituencyHierarchyInfo.setTotalHamlets(hierarchyInfoVO.getHamletIdsList()!= null?(long)hierarchyInfoVO.getHamletIdsList().size():0L);
		ConstituencyHierarchyInfo.setTotalWards(hierarchyInfoVO.getWardIdsList() != null?(long)hierarchyInfoVO.getWardIdsList().size():0L);
		
		saveConstituencyBasicInformation(ConstituencyHierarchyInfo);
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  return resultStatus;
	  }catch (Exception e) {
		  e.printStackTrace();
		  resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  return resultStatus;
	}
	 }
	 
	 public ResultStatus saveConstituencyBasicInformation(final ConstituencyHierarchyInfoVO hierarchyInfoVO)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
			  transactionTemplate.execute(new TransactionCallbackWithoutResult() {
					protected void doInTransactionWithoutResult(TransactionStatus status) 
			{	
			
		ConstituencyHierarchyInfo ConstituencyHierarchyInfo = new ConstituencyHierarchyInfo();
		
		ConstituencyHierarchyInfo.setReportLevelId(hierarchyInfoVO.getReportLevelId());
		ConstituencyHierarchyInfo.setReportLevelValue(hierarchyInfoVO.getReportLevelValue());
		ConstituencyHierarchyInfo.setConstituencyId(hierarchyInfoVO.getConstituencyId());
		ConstituencyHierarchyInfo.setUserId(hierarchyInfoVO.getUserId());
		ConstituencyHierarchyInfo.setPublicationDateId(hierarchyInfoVO.getPublicationDateId());
		ConstituencyHierarchyInfo.setMandals(hierarchyInfoVO.getTotalMandals());
		ConstituencyHierarchyInfo.setPanchayats(hierarchyInfoVO.getTotalPanchayats());
		ConstituencyHierarchyInfo.setMunicipalities(hierarchyInfoVO.getTotalMuncipalities());
		ConstituencyHierarchyInfo.setBooths(hierarchyInfoVO.getTotalBooths());
		ConstituencyHierarchyInfo.setHamlets(hierarchyInfoVO.getTotalHamlets());
		ConstituencyHierarchyInfo.setWards(hierarchyInfoVO.getTotalWards());
		
		constituencyHierarchyInfoDAO.save(ConstituencyHierarchyInfo);
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
	 
	 public ResultStatus deleteConstituencyBasicData(Long constituencyId,Long publicationDateId,Long userId)
	 {
		 ResultStatus resultStatus = new ResultStatus();
		 try{
		
			constituencyHierarchyInfoDAO.deleteConstituencyBasicInfo(constituencyId, publicationDateId, userId);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
			 
		 }catch (Exception e) {
			e.printStackTrace();
			LOG.error(" Exception Occured in deleteConstituencyBasicData() method, Exception - "+e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		 }
		 
	 }
	 
	 /**
	  * This service is used for getting added and deleted voters cout by panchayat lavel and added count or deleted copunt based on range
	  * @param Long constituencyId
	  * @param Long publicationDateId
	  * @return List<VoterAdderdOrDeletedRengesInfoVO> 
	  * @date 06-12-2013
	  */
	 public List<VoterAdderdOrDeletedRengesInfoVO> getReportForVotersAddedOrDeletedVotersForSelectdConstituency(Long constituencyId,Long publicationDateId,String reprtType)
	 {
		 List<VoterAdderdOrDeletedRengesInfoVO> returnList = null;
		 VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO = null;
		 List<VoterAdderdOrDeletedRengesInfoVO> addedVotersListForVO = null;
		 List<VoterAdderdOrDeletedRengesInfoVO> deletedVotersListForVO = null;
		 List<VoterAdderdOrDeletedRengesInfoVO> addedRurlUrbanVotersListForVO = null;
		 List<VoterAdderdOrDeletedRengesInfoVO> deletedRurlUrbanVotersListForVO = null;
		 List<PanchayatAddedOrDeletedVO> panchayWiseAddedDeletedList = null;
		 try {
			LOG.debug("Entered into  getReportForVotersAddedOrDeletedVotersForSelectdConstituency() in  VoterModificationService Service");
			Map<Long,Long> panchayatAddedCountMap = null;//Map<panchayatId,addedcount>
			Map<Long,Long> panchayatDeletedCountMap = null;//Map<panchayatId,Deletedcount>
			Map<Long,Long> ruralUrbanAddedCountMap = null;//Map<ruralUrbanId,addedCount>
			Map<Long,Long> ruralUrbanDeletedCountMap = null;//Map<ruralUrbanId,deletedCount>
			Map<Long,List<Long>> constituencyMandalMap = null;//Map<constituencyId,mandalIds>
			Map<Long,List<Long>> mandalPanchayatMap = null;//Map<mandalIs,panchayatIds>
			Map<Long,String> mandalNameMap = null;//Map<mandalId,MandalName>
			Map<Long,String> panchayatNameMap = null;//Map<panchayatId,panchayatName>
			Map<Long,Long> panchayatMandalMap = null;//Map<panchayatId,tehsilId>
			List<Long> mandalIds = null;
			List<Long> panchayatIds = null;
			
			List<Object[]> mandalList = boothDAO.getTehsilsForConstituency(constituencyId,publicationDateId);
			if(mandalList != null && mandalList.size() > 0)
			{
				constituencyMandalMap = new HashMap<Long, List<Long>>();
				mandalNameMap = new HashMap<Long, String>();
				mandalIds = new ArrayList<Long>();
				for (Object[] parms : mandalList) {
					mandalIds.add((Long)parms[0]);
					mandalNameMap.put((Long)parms[0], parms[1].toString());
				}
				constituencyMandalMap.put(constituencyId, mandalIds);
				
			}
			if(mandalIds != null)
			{
				List<Object[]> panchayatsList = boothDAO.getPanchayatsForConstituency(mandalIds,publicationDateId);
				if(panchayatsList != null && panchayatsList.size() > 0)
				{
					mandalPanchayatMap = new HashMap<Long, List<Long>>();
					panchayatNameMap = new HashMap<Long, String>();
					panchayatMandalMap = new HashMap<Long, Long>();
					for (Object[] parms : panchayatsList) {
						
						panchayatIds = mandalPanchayatMap.get((Long)parms[2]);
						if(panchayatIds == null)
						{
							panchayatIds = new ArrayList<Long>();
							mandalPanchayatMap.put((Long)parms[2], panchayatIds);
							panchayatMandalMap.put((Long)parms[0], (Long)parms[2]);
						}
						panchayatIds.add((Long)parms[0]);
						panchayatNameMap.put((Long)parms[0], parms[1].toString());
					}
					
				}
			}
			if(reprtType.equalsIgnoreCase("panchayatWise"))
			{
				panchayWiseAddedDeletedList = new ArrayList<PanchayatAddedOrDeletedVO>();
				voterAdderdOrDeletedRengesInfoVO = new VoterAdderdOrDeletedRengesInfoVO();
				getAddedAndDeletedVotersByPanchayatLevel(constituencyId,publicationDateId,panchayatNameMap,mandalNameMap,panchayWiseAddedDeletedList,panchayatMandalMap);
			}
			else
			{
				returnList = new ArrayList<VoterAdderdOrDeletedRengesInfoVO>();
				voterAdderdOrDeletedRengesInfoVO = new VoterAdderdOrDeletedRengesInfoVO();
				String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
				 if(constituencyType.equalsIgnoreCase("RURAL-URBAN"))
				 {
					 List<Object[]> addedVotersListForRuralUrban   = voterModificationInfoDAO.getVotersAddedOrDeletedInfoByPanchayatLevel(constituencyId,5l,publicationDateId,1l);
					 if(addedVotersListForRuralUrban != null && addedVotersListForRuralUrban.size() > 0)
					 {
						 ruralUrbanAddedCountMap = new HashMap<Long, Long>();
						 addedRurlUrbanVotersListForVO = new ArrayList<VoterAdderdOrDeletedRengesInfoVO>();
						 for (Object[] parms : addedVotersListForRuralUrban)
						 {
							 ruralUrbanAddedCountMap.put((Long)parms[0], (Long)parms[1]);
						 }
						 fillAddedOrDeletedVoterDetails(constituencyMandalMap,ruralUrbanAddedCountMap,mandalNameMap,mandalPanchayatMap,addedRurlUrbanVotersListForVO,"add",constituencyId,"RURAL-URBAN");
					 }
					 List<Object[]> deletedVotersListRuralUrban = voterModificationInfoDAO.getVotersAddedOrDeletedInfoByPanchayatLevel(constituencyId,5l,publicationDateId,2l);
					 if(deletedVotersListRuralUrban != null && deletedVotersListRuralUrban.size() > 0)
					 {
						 ruralUrbanDeletedCountMap = new HashMap<Long, Long>();
						 deletedRurlUrbanVotersListForVO = new ArrayList<VoterAdderdOrDeletedRengesInfoVO>();
						 for (Object[] parms : deletedVotersListRuralUrban)
						 {
							 ruralUrbanDeletedCountMap.put((Long)parms[0], (Long)parms[1]);
						 } 
						 fillAddedOrDeletedVoterDetails(constituencyMandalMap,ruralUrbanDeletedCountMap,mandalNameMap,mandalPanchayatMap,deletedRurlUrbanVotersListForVO,"delete",constituencyId,"RURAL-URBAN");
					 }
				 }
				
				List<Object[]> addedVotersList   = voterModificationInfoDAO.getVotersAddedOrDeletedInfoByPanchayatLevel(constituencyId,3l,publicationDateId,1l);
				if(addedVotersList != null && addedVotersList.size() > 0)
				{
					panchayatAddedCountMap = new HashMap<Long, Long>();
					addedVotersListForVO = new ArrayList<VoterAdderdOrDeletedRengesInfoVO>();
					for (Object[] parms : addedVotersList) {
						panchayatAddedCountMap.put((Long)parms[0], (Long)parms[1]);
					}
					fillAddedOrDeletedVoterDetails(constituencyMandalMap,panchayatAddedCountMap,mandalNameMap,mandalPanchayatMap,addedVotersListForVO,"add",constituencyId,"RURAL");
				}
				List<Object[]> deletedVotersList = voterModificationInfoDAO.getVotersAddedOrDeletedInfoByPanchayatLevel(constituencyId,3l,publicationDateId,2l);
				if(deletedVotersList != null && deletedVotersList.size() > 0)
				{
					panchayatDeletedCountMap = new HashMap<Long, Long>();
					deletedVotersListForVO = new ArrayList<VoterAdderdOrDeletedRengesInfoVO>();
					for (Object[] parms : deletedVotersList) {
						panchayatDeletedCountMap.put((Long)parms[0], (Long)parms[1]);
					}
					fillAddedOrDeletedVoterDetails(constituencyMandalMap,panchayatDeletedCountMap,mandalNameMap,mandalPanchayatMap,deletedVotersListForVO,"delete",constituencyId,"RURAL");
				}
			}
			
		} catch (Exception e) {
			LOG.error("Eceprtion occured in  getReportForVotersAddedOrDeletedVotersForSelectdConstituency() in  VoterModificationService Service",e);
		}
		 voterAdderdOrDeletedRengesInfoVO.setPanchayWiseAddedDeletedList(panchayWiseAddedDeletedList);
		 voterAdderdOrDeletedRengesInfoVO.setRuralUrbanAddedVoterDetails(addedRurlUrbanVotersListForVO);
		 voterAdderdOrDeletedRengesInfoVO.setRuralUrbanDeletedVoterDetails(deletedRurlUrbanVotersListForVO);
		 voterAdderdOrDeletedRengesInfoVO.setAddedVoeterDetails(addedVotersListForVO);
		 voterAdderdOrDeletedRengesInfoVO.setDeletedVoterDetails(deletedVotersListForVO);
		 returnList.add(voterAdderdOrDeletedRengesInfoVO);
		// createTable4(returnList);
		 return returnList;
	 }
	 
	 /**
	  * This service is used for filling the VoterAdderdOrDeletedRengesInfoVO.
	  * @param constituencyMandalMap
	  * @param panchayatAddedCountMap
	  * @param mandalNameMap
	  * @param mandalPanchayatMap
	  * @param addedVotersListForVO
	  * @param type
	  * @param constituencyId
	  * @param areaType
	  * @Date 06-12-2013
	  */
	 public void fillAddedOrDeletedVoterDetails(Map<Long,List<Long>> constituencyMandalMap,Map<Long,Long> panchayatAddedCountMap,Map<Long,String> mandalNameMap, Map<Long,List<Long>> mandalPanchayatMap,List<VoterAdderdOrDeletedRengesInfoVO> addedVotersListForVO,String type,Long constituencyId,String areaType)
	 {
		 if(areaType.equalsIgnoreCase("RURAL"))
		 {
			 List<Long> mandalIds = constituencyMandalMap.get(constituencyId);
			 if(mandalIds != null && mandalIds.size() > 0)
			 {
				 for (Long mandalId : mandalIds) {
					 VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO = new VoterAdderdOrDeletedRengesInfoVO();
					 voterAdderdOrDeletedRengesInfoVO.setName(mandalNameMap.get(mandalId));
					 voterAdderdOrDeletedRengesInfoVO.setId(mandalId);
					 List<Long> panchayatIds =  mandalPanchayatMap.get(mandalId);
					 if(type.equalsIgnoreCase("Add"))
					 {
						 voterAdderdOrDeletedRengesInfoVO.setNoChangesAddedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt1To50AddedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt51To100AddedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt101To150AddedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt151To200Addedcount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt201To300AddedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setAbove300AddedCount(0l);
						 Long count = 0l;
						 for (Long panchayatId : panchayatIds) {
							count = panchayatAddedCountMap.get(panchayatId);
							justfiyTheAddedVoterDetails(count,voterAdderdOrDeletedRengesInfoVO);
						}
					 }
					 else
					 {
						 voterAdderdOrDeletedRengesInfoVO.setNoChangesDeletedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt1To50DeletedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt51To100DeletedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt101To150DeletedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt151To200DeletedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setBt201To300DeletedCount(0l);
						 voterAdderdOrDeletedRengesInfoVO.setAbove300DeletedCount(0l);
						 Long count = 0l;
						 for (Long panchayatId : panchayatIds) {
							count = panchayatAddedCountMap.get(panchayatId);
							justfiyTheDeletedVoterDetails(count,voterAdderdOrDeletedRengesInfoVO);
						}
						 
					 }
					 addedVotersListForVO.add(voterAdderdOrDeletedRengesInfoVO);
				}
			 }
		 }
		 else
		 {
			 Set<Long> ruralUrbanId = panchayatAddedCountMap.keySet();
			 for (Long id : ruralUrbanId) {
				 VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO = new VoterAdderdOrDeletedRengesInfoVO();
				 voterAdderdOrDeletedRengesInfoVO.setName(constituencyDAO.get(constituencyId).getName() + " Muncipality");
				 voterAdderdOrDeletedRengesInfoVO.setId(id);
				 if(type.equalsIgnoreCase("Add"))
				 {
					 voterAdderdOrDeletedRengesInfoVO.setNoChangesAddedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt1To50AddedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt51To100AddedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt101To150AddedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt151To200Addedcount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt201To300AddedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setAbove300AddedCount(0l);
					 Long count = panchayatAddedCountMap.get(id);
					 justfiyTheAddedVoterDetails(count,voterAdderdOrDeletedRengesInfoVO);
				 }
				 else
				 {
					 voterAdderdOrDeletedRengesInfoVO.setNoChangesDeletedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt1To50DeletedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt51To100DeletedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt101To150DeletedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt151To200DeletedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setBt201To300DeletedCount(0l);
					 voterAdderdOrDeletedRengesInfoVO.setAbove300DeletedCount(0l);
					 Long count = panchayatAddedCountMap.get(id);
					 justfiyTheDeletedVoterDetails(count,voterAdderdOrDeletedRengesInfoVO);
					
				 } 
				 addedVotersListForVO.add(voterAdderdOrDeletedRengesInfoVO);
			}
			 
		 }
		 
	 }
	 
	/**
	 * This service is used for justify the count details for added voters
	 * @param count
	 * @param voterAdderdOrDeletedRengesInfoVO
	 * @Date 06-12-2013
	 */
	 public void justfiyTheAddedVoterDetails(Long count, VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO)
	 {
		 if(count !=null)
		 {
			 if(count == 0)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setNoChangesAddedCount(voterAdderdOrDeletedRengesInfoVO.getNoChangesAddedCount() + 1);
			 }
			 else if(count >= 1 && count <=50)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt1To50AddedCount(voterAdderdOrDeletedRengesInfoVO.getBt1To50AddedCount() + 1);
			 }
			 else if(count >= 51 && count <= 100)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt51To100AddedCount(voterAdderdOrDeletedRengesInfoVO.getBt51To100AddedCount() + 1);
				
			 }
			 else if(count >= 101 && count <= 150)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt101To150AddedCount(voterAdderdOrDeletedRengesInfoVO.getBt101To150AddedCount() + 1);
				 
			 }
			 else if(count >= 151 && count <= 200)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt151To200Addedcount(voterAdderdOrDeletedRengesInfoVO.getBt151To200Addedcount() + 1);
			 }
			 else if(count >= 201 && count <= 300)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt201To300AddedCount(voterAdderdOrDeletedRengesInfoVO.getBt201To300AddedCount() + 1);
			 }
			 else
			 {
				 voterAdderdOrDeletedRengesInfoVO.setAbove300AddedCount(voterAdderdOrDeletedRengesInfoVO.getAbove300AddedCount() + 1); 
			 }
		 }
		
		 
	 }
	 /**
	  * This service is used for justify the count details for deleted voters
	  * @param count
	  * @param voterAdderdOrDeletedRengesInfoVO
	  * @Date 06-12-2013
	  */
	 public void justfiyTheDeletedVoterDetails(Long count, VoterAdderdOrDeletedRengesInfoVO voterAdderdOrDeletedRengesInfoVO)
	 {
		 if(count !=null)
		 {
			 if(count == 0)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setNoChangesDeletedCount(voterAdderdOrDeletedRengesInfoVO.getNoChangesDeletedCount() + 1);
			 }
			 else if(count >= 1 && count <=50)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt1To50DeletedCount(voterAdderdOrDeletedRengesInfoVO.getBt1To50DeletedCount() + 1);
			 }
			 else if(count >= 51 && count <= 100)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt51To100DeletedCount(voterAdderdOrDeletedRengesInfoVO.getBt51To100DeletedCount() + 1);
				
			 }
			 else if(count >= 101 && count <= 150)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt101To150DeletedCount(voterAdderdOrDeletedRengesInfoVO.getBt101To150DeletedCount() + 1);
				 
			 }
			 else if(count >= 151 && count <= 200)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt151To200DeletedCount(voterAdderdOrDeletedRengesInfoVO.getBt151To200DeletedCount()+ 1);
			 }
			 else if(count >= 201 && count <= 300)
			 {
				 voterAdderdOrDeletedRengesInfoVO.setBt201To300DeletedCount(voterAdderdOrDeletedRengesInfoVO.getBt201To300DeletedCount() + 1);
			 }
			 else
			 {
				 voterAdderdOrDeletedRengesInfoVO.setAbove300DeletedCount(voterAdderdOrDeletedRengesInfoVO.getAbove300DeletedCount() + 1); 
			 }
		 }
		
		 
	 }
	 /**
	  * This service is used for getting the added , deleted and total voter for panchayat level
	  * @param constituencyId
	  * @param publicationId
	  * @param panchayatNameMap
	  * @param mandalNameMap
	  * @param panchayWiseAddedDeletedList
	  * @param panchayatMandalMap
	  * @date 06-12-2013
	  */
	 public void getAddedAndDeletedVotersByPanchayatLevel(Long constituencyId,Long publicationId,Map<Long,String> panchayatNameMap,Map<Long,String> mandalNameMap,List<PanchayatAddedOrDeletedVO> panchayWiseAddedDeletedList,Map<Long,Long> panchayatMandalMap)
	 {
		 try {
			 LOG.debug("Entered into  getAddedAndDeletedVotersByPanchayatLevel() in  VoterModificationService Service");
			 Map<Long,Long> addedCountMap = null;
			 Map<Long,Long> deleteCountMap = null;
			 List<Object[]> panchayatAddedDeletedList = voterModificationInfoDAO.getPanchayatWiseAddedAndDeletedVoters(constituencyId,publicationId,3l);
			 if(panchayatAddedDeletedList != null && panchayatAddedDeletedList.size() > 0)
			 {
				 addedCountMap  = new HashMap<Long, Long>();
				 deleteCountMap = new HashMap<Long, Long>();
				 for (Object[] parms : panchayatAddedDeletedList) {
					 if((Long)parms[2] == 1)
					 {
						 addedCountMap.put((Long)parms[0], (Long)parms[1]);
					 }
					 else
					 {
						 deleteCountMap.put((Long)parms[0], (Long)parms[1]);
					 }
				}
			 }
			 Set<Long> panchayatIds = panchayatNameMap.keySet();
			 if(panchayatIds != null && panchayatIds.size() > 0)
			 {
				 for (Long panchayatId : panchayatIds) {
					 PanchayatAddedOrDeletedVO panchayatAddedOrDeletedVO = new PanchayatAddedOrDeletedVO();
					 panchayatAddedOrDeletedVO.setAddedCount(addedCountMap.get(panchayatId));
					 panchayatAddedOrDeletedVO.setDeletedCount(deleteCountMap.get(panchayatId));
					 panchayatAddedOrDeletedVO.setTotal(panchayatAddedOrDeletedVO.getAddedCount() + panchayatAddedOrDeletedVO.getDeletedCount());
					 panchayatAddedOrDeletedVO.setPanchayatId(panchayatId);
					 panchayatAddedOrDeletedVO.setPanchayatName(panchayatNameMap.get(panchayatId));
					 panchayatAddedOrDeletedVO.setMandalId(panchayatMandalMap.get(panchayatId));
					 panchayatAddedOrDeletedVO.setMandalName(mandalNameMap.get(panchayatAddedOrDeletedVO.getMandalId()));
					 panchayWiseAddedDeletedList.add(panchayatAddedOrDeletedVO);
				}
			 }
			 
		} catch (Exception e) {
			LOG.error("Eceprtion occured in  getAddedAndDeletedVotersByPanchayatLevel() in  VoterModificationService Service",e);
		}
	 }
	 
	 public PdfVO getAddedVotersInAPublication(Long constituency, Long prevPubliationId,Long presentPublicationId)
	 {
		  
		 PdfVO PdfVO = new PdfVO();
		 Long constiId = constituency;
		 List<VoterVO> voters = new ArrayList<VoterVO>();
		 List<VoterVO> delVoters = new ArrayList<VoterVO>();
		 String municipalityName = "";
		 // getting added voters for all mandals
		 List<Object[]> addedVotersList1 = voterModificationDAO.getAddedVotersInAPublicationForMandal(presentPublicationId, constiId);
		 // // getting added voters for all muncipalites
		 List<Object[]> addedVotersList2 = voterModificationDAO.getAddedVotersInAPublicationForMunicipality(presentPublicationId, constiId);
		 // getting deleted voters for all mandals
		 List<Object[]> addedVotersList3 = voterModificationDAO.getDeletedVotersInAPublicationForMandal(prevPubliationId,presentPublicationId, constiId);
		// getting deleted voters for all muncipalites
		 List<Object[]> addedVotersList4 = voterModificationDAO.getDeletedVotersInAPublicationForMunicipality(prevPubliationId,presentPublicationId, constiId);
		 // getting voters count panchayat wise for previous publication
		 List<Object[]> perviousVotersCount = voterInfoDAO.getVotersCountInPunchayatAndLocalElecBody(constiId,prevPubliationId);
		// getting voters count panchayat wise for present publication
		 List<Object[]> presentVotersCount = voterInfoDAO.getVotersCountInPunchayatAndLocalElecBody(constiId,presentPublicationId);
		 // getiing mandal and panchayat names for previous publication
		 List<Object[]> panchayatDetails = boothDAO.getPanchayatMandalDetails(constiId, prevPubliationId);
		 // getiing muncipality details for previous publication
		 List<Object[]> localBodyDetails = boothDAO.getMunicDetails(constiId, prevPubliationId);
		 // contains panchayt wise added voters details by age range
		 LinkedHashMap<Long,VotersDetailsVO> panchayatAddedList = new LinkedHashMap<Long,VotersDetailsVO>();
		// contains muncipality wise added voters details by age range
		 LinkedHashMap<Long,VotersDetailsVO> municAddedList = new LinkedHashMap<Long,VotersDetailsVO>();
		 // contains booth wise added count
		 LinkedHashMap<Long,VotersDetailsVO> municAddedBoothList = new LinkedHashMap<Long,VotersDetailsVO>();
		// contains panchayt wise deleted voters details by age range
		 LinkedHashMap<Long,VotersDetailsVO> panchayatDelList = new LinkedHashMap<Long,VotersDetailsVO>();
		// contains muncipality wise deleted voters details by age range
		 LinkedHashMap<Long,VotersDetailsVO> municDelList = new LinkedHashMap<Long,VotersDetailsVO>();
		 //// contains booth wise deleted count
		 LinkedHashMap<Long,VotersDetailsVO> municDelBoothList = new LinkedHashMap<Long,VotersDetailsVO>();
		 //compete added voters details by age range
		 List<VotersDetailsVO> added = new ArrayList<VotersDetailsVO>();
		//compete deletd voters details by age range
		 List<VotersDetailsVO> deleted = new ArrayList<VotersDetailsVO>();
		 // contails total added and deleted count and percentage by panchayat or muncipality wise
		 List<VotersDetailsVO> complete = new ArrayList<VotersDetailsVO>();
		 //contails panchayt wise total added deleted count
		 LinkedHashMap<Long,VotersDetailsVO> totalAddedPanc = new LinkedHashMap<Long,VotersDetailsVO>();
		 //contails muncipality wise total added deleted count
		 LinkedHashMap<Long,VotersDetailsVO> totalAddedMunc = new LinkedHashMap<Long,VotersDetailsVO>();
		 Map<Long,Long> pancVotersCount = new HashMap<Long,Long>();
		 Map<Long,Long> mandVotersCount = new HashMap<Long,Long>();
		 Map<Long,Long> presentPancVotersCount = new HashMap<Long,Long>();
		 Map<Long,Long> presentMandVotersCount = new HashMap<Long,Long>();
		 LinkedHashMap<Long,VotersDetailsVO> addedPancpercMap = new LinkedHashMap<Long,VotersDetailsVO>();
		 LinkedHashMap<Long,VotersDetailsVO> deletedPancpercMap = new LinkedHashMap<Long,VotersDetailsVO>();
		 LinkedHashMap<Long,VoterModificationAgeRangeVO> votersMap = new LinkedHashMap<Long,VoterModificationAgeRangeVO>();
		 votersMap.put(1l, new VoterModificationAgeRangeVO());
		 votersMap.put(2l, new VoterModificationAgeRangeVO());
		 votersMap.put(3l, new VoterModificationAgeRangeVO());
		 votersMap.put(4l, new VoterModificationAgeRangeVO());
		 votersMap.put(5l, new VoterModificationAgeRangeVO());
		 votersMap.put(6l, new VoterModificationAgeRangeVO());
		 // fillintg the panchat wise voters and mandal wise voters count for previous publication
		 populatePreviousVotersCount(pancVotersCount,mandVotersCount,perviousVotersCount);
		// fillintg the panchat wise voters and mandal wise voters count for present publication
		 populatePreviousVotersCount(presentPancVotersCount,presentMandVotersCount,presentVotersCount);
		 // populating added vootes counts,age wise count panchayat wise
		 populateData(addedVotersList1,panchayatAddedList,voters,"added",totalAddedPanc,votersMap,null,municipalityName);
		 // populating added vootes counts,age wise count muncipality wise
		 populateData(addedVotersList2,municAddedList,voters,"added",totalAddedMunc,votersMap,municAddedBoothList,municipalityName);

		 added.addAll(panchayatAddedList.values());
		 added.addAll(municAddedList.values());
		// populating deleted vootes counts,age wise count panchayat wise
		 populateData(addedVotersList3,panchayatDelList,delVoters,"deleted",totalAddedPanc,votersMap,null,municipalityName);
		// populating deleted vootes counts,age wise count muncipality wise
		 populateData(addedVotersList4,municDelList,delVoters,"deleted",totalAddedMunc,votersMap,municDelBoothList,municipalityName);
		 deleted.addAll(panchayatDelList.values());
		 deleted.addAll(municDelList.values());
		 // calucating percentage of voters added and deleted for panchayat wise
		 populatePercentage(totalAddedPanc,pancVotersCount,presentPancVotersCount);
		 // calucating percentage of voters added and deleted for muncipality wise
		 populatePercentage(totalAddedMunc,mandVotersCount,presentMandVotersCount);
		 // calucating range percentage of voters added and deleted for panchayat wise
		 populateAddedDelPancPercDetails(panchayatDetails,addedPancpercMap,deletedPancpercMap,totalAddedPanc);
		 // calucating range percentage of voters added and deleted for muncipality wise
		 populateAddDeletedMunicPercDetails(localBodyDetails,addedPancpercMap,deletedPancpercMap,totalAddedMunc);
		 complete.addAll(totalAddedPanc.values());
		 complete.addAll(totalAddedMunc.values());
		 
		 
		 PdfVO.setAddedVoterDetails(voters);
		 PdfVO.setDeletedVoterDetils(delVoters);
		 PdfVO.setAddedVoterDetailsVoList(added);
		 PdfVO.setDeletedVoterDetailsVoList(deleted);
		 PdfVO.setCompleteVoterDetailsVoList(complete);
		 PdfVO.setAddedDetaildByPerc(new ArrayList<VotersDetailsVO>(addedPancpercMap.values()));
		 PdfVO.setDeletedDetaildByPerc(new ArrayList<VotersDetailsVO>(deletedPancpercMap.values()));
		 PdfVO.setAgeRangeVOList(new ArrayList<VoterModificationAgeRangeVO>(votersMap.values()));
		 PdfVO.setBoothWiseAddedList(new ArrayList<VotersDetailsVO>(municAddedBoothList.values()));
		 PdfVO.setBoothWiseDeletedList(new ArrayList<VotersDetailsVO>(municDelBoothList.values()));
		 return PdfVO;
		}
	
	 public void populatePreviousVotersCount(Map<Long,Long> pancVotersCount,Map<Long,Long> mandVotersCount,List<Object[]> perviousVotersCount)
	 {
		 for(Object[] count:perviousVotersCount)
		 {
			 if(count[2] != null && ((Long)count[2]).longValue() == 3 )
			 {
			  pancVotersCount.put((Long)count[1],(Long)count[0]);
			 }
			 else
			 {
			  mandVotersCount.put((Long)count[1],(Long)count[0]);
			 }
		 }
	}

	

	 public void populateData(List<Object[]> votersList,Map<Long,VotersDetailsVO> panchayatList,List<VoterVO> voters,String type,Map<Long,VotersDetailsVO> totalAdded,LinkedHashMap<Long,VoterModificationAgeRangeVO> votersMap,LinkedHashMap<Long,VotersDetailsVO> boothWise,String municipalityName){
		 VoterVO vo = null;
		 for(Object[] voter:votersList){
			 if(boothWise != null){
				 if(voter[1] != null && voter[3] != null)
				    municipalityName = voter[1].toString()+" "+voter[3].toString();
				 VotersDetailsVO boothVoter = boothWise.get(new Long(voter[4].toString().trim()));
				 if(boothVoter == null){
				 boothVoter = new VotersDetailsVO();
				 boothVoter.setName("Booth-"+voter[4].toString());
				 boothVoter.setCastName(municipalityName);
				 boothWise.put(new Long(voter[4].toString().trim()),boothVoter);
				 }
				 boothVoter.setTotalVoters(boothVoter.getTotalVoters()+1);
				 }

		 VotersDetailsVO panchayat = panchayatList.get((Long)voter[2]);
		 if(panchayat == null){
		 panchayat = new VotersDetailsVO();
		 panchayat.setPanchayatname(voter[3] != null?voter[3].toString():"");
		 panchayat.setTehsilName(voter[1] != null?voter[1].toString():"");
		 panchayatList.put((Long)voter[2], panchayat);

		 }
		 VotersDetailsVO c = totalAdded.get((Long)voter[2]);
		 if(c == null){
		 c = new VotersDetailsVO();
		 c.setPanchayatname(voter[3] != null?voter[3].toString():"");
		 c.setTehsilName(voter[1] != null?voter[1].toString():"");
		 totalAdded.put((Long)voter[2], c);
		 }
		 if("added".equalsIgnoreCase(type)){
		 c.setMaleVotersCountBetween18To25(c.getMaleVotersCountBetween18To25()+1);
		 }else if("deleted".equalsIgnoreCase(type)){
		 c.setTotalMaleVotesFor18To25(c.getTotalMaleVotesFor18To25()+1);
		 }
		 c.setTotalMaleVotersFor26To35(c.getTotalMaleVotersFor26To35()+1);
		 long age = ((Long)voter[9]).longValue();
		 panchayat.setMaleVotersCountBetween18To25(panchayat.getMaleVotersCountBetween18To25()+1);
		 if(age >= 18 && age <= 22){
		 panchayat.setTotalMaleVotersForYoungerVoters(panchayat.getTotalMaleVotersForYoungerVoters()+1);
		 VoterModificationAgeRangeVO vmVo = votersMap.get(1l);
		 if("added".equalsIgnoreCase(type)){
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setAddedMale(vmVo.getAddedMale() + 1);
			}
			else
			{
				vmVo.setAddedFemale(vmVo.getAddedFemale() + 1);
			}
			vmVo.setAddedCount(vmVo.getAddedCount()+1);
		 }else{
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setDeletedMale(vmVo.getDeletedMale() + 1);
			}
			else
			{
				vmVo.setDeletedFemale(vmVo.getDeletedFemale() + 1);
			}
		    vmVo.setDeletedCount(vmVo.getDeletedCount()+1);
		 }
		 }
		 else if(age >= 23 && age <= 25){
		 panchayat.setTotalMaleVotesFor18To25(panchayat.getTotalMaleVotesFor18To25()+1);
		 VoterModificationAgeRangeVO vmVo = votersMap.get(2l);
		 if("added".equalsIgnoreCase(type)){
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setAddedMale(vmVo.getAddedMale() + 1);
			}
			else
			{
				vmVo.setAddedFemale(vmVo.getAddedFemale() + 1);
			}
			vmVo.setAddedCount(vmVo.getAddedCount()+1);
		 }else{
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setDeletedMale(vmVo.getDeletedMale() + 1);
			}
			else
			{
				vmVo.setDeletedFemale(vmVo.getDeletedFemale() + 1);
			}
			vmVo.setDeletedCount(vmVo.getDeletedCount()+1);
		 }
		 }
		 else if(age >= 26 && age <= 35){
		 panchayat.setTotalMaleVotersFor26To35(panchayat.getTotalMaleVotersFor26To35()+1);
		 VoterModificationAgeRangeVO vmVo = votersMap.get(3l);
		 if("added".equalsIgnoreCase(type)){
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setAddedMale(vmVo.getAddedMale() + 1);
			}
			else
			{
				vmVo.setAddedFemale(vmVo.getAddedFemale() + 1);
			}
			vmVo.setAddedCount(vmVo.getAddedCount()+1);
		 }else{
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setDeletedMale(vmVo.getDeletedMale() + 1);
			}
			else
			{
				vmVo.setDeletedFemale(vmVo.getDeletedFemale() + 1);
			}
			vmVo.setDeletedCount(vmVo.getDeletedCount()+1);
		 }
		 }
		 else if(age >= 36 && age <= 45){
		 panchayat.setTotalMaleVotersFor36To45(panchayat.getTotalMaleVotersFor36To45()+1);
		 VoterModificationAgeRangeVO vmVo = votersMap.get(4l);
		 if("added".equalsIgnoreCase(type)){
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setAddedMale(vmVo.getAddedMale() + 1);
			}
			else
			{
				vmVo.setAddedFemale(vmVo.getAddedFemale() + 1);
			}
			vmVo.setAddedCount(vmVo.getAddedCount()+1);
		 }else{
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setDeletedMale(vmVo.getDeletedMale() + 1);
			}
			else
			{
				vmVo.setDeletedFemale(vmVo.getDeletedFemale() + 1);
			}
			vmVo.setDeletedCount(vmVo.getDeletedCount()+1);
		 }
		 }
		 else if(age >= 46 && age <= 60){
		 panchayat.setTotalMaleVotersFor46To60(panchayat.getTotalMaleVotersFor46To60()+1);
		 VoterModificationAgeRangeVO vmVo = votersMap.get(5l);
		 if("added".equalsIgnoreCase(type)){
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setAddedMale(vmVo.getAddedMale() + 1);
			}
			else
			{
				vmVo.setAddedFemale(vmVo.getAddedFemale() + 1);
			}
			vmVo.setAddedCount(vmVo.getAddedCount()+1);
		 }else{
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setDeletedMale(vmVo.getDeletedMale() + 1);
			}
			else
			{
				vmVo.setDeletedFemale(vmVo.getDeletedFemale() + 1);
			}
			vmVo.setDeletedCount(vmVo.getDeletedCount()+1);
		 }
		 }
		 else if(age >60){
		 panchayat.setTotalMaleVotersForAbove60(panchayat.getTotalMaleVotersForAbove60()+1);
		 VoterModificationAgeRangeVO vmVo = votersMap.get(6l);
		 if("added".equalsIgnoreCase(type)){
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setAddedMale(vmVo.getAddedMale() + 1);
			}
			else
			{
				vmVo.setAddedFemale(vmVo.getAddedFemale() + 1);
			}
			vmVo.setAddedCount(vmVo.getAddedCount()+1);
		 }else{
			if(voter[8].toString().equalsIgnoreCase("M"))
			{
				vmVo.setDeletedMale(vmVo.getDeletedMale() + 1);
			}
			else
			{
				vmVo.setDeletedFemale(vmVo.getDeletedFemale() + 1);
			}
			vmVo.setDeletedCount(vmVo.getDeletedCount()+1);
		 }
		 }
		 vo = new VoterVO();
		 vo.setTownShip(voter[1] != null?voter[1].toString():"");
		 vo.setPanchayatName(voter[3] != null?voter[3].toString():"");
		 vo.setBoothName(voter[4] != null?voter[4].toString():"");
		 vo.setVoterIDCardNo(voter[5] != null?voter[5].toString():"");
		 vo.setFirstName(voter[6] != null?voter[6].toString():"");
		 vo.setHouseNo(voter[7] != null?"#"+voter[7].toString():"");
		 vo.setGender(voter[8] != null?voter[8].toString():"");
		 vo.setAge((Long)voter[9]);
		 vo.setRelativeFirstName(voter[10] != null?voter[10].toString():"");
		 vo.setRelationshipType(voter[11] != null?voter[11].toString():"");
		 voters.add(vo);
		 }
		 }


		
		
		public void populatePercentage(LinkedHashMap<Long,VotersDetailsVO> totalAddedDel,Map<Long,Long> votersCount,Map<Long,Long> presentVotersCount){
			DecimalFormat df = new DecimalFormat("###.##");
			for(Long id:totalAddedDel.keySet()){
				VotersDetailsVO vo = totalAddedDel.get(id);
				Long totalVoters = votersCount.get(id);
				if(totalVoters != null && totalVoters.longValue() >0){
					vo.setVotersPercentFor18To25(df.format(vo.getMaleVotersCountBetween18To25()*100/totalVoters.doubleValue()));
					vo.setVotersPercentFor26To35(df.format(vo.getTotalMaleVotesFor18To25()*100/totalVoters.doubleValue()));
				}
				vo.setTotalVoters(presentVotersCount.get(id));
			}
		}

	public void populateAddedDelPancPercDetails(List<Object[]> panchayatDetails,Map<Long,VotersDetailsVO> addedPancpercMap,Map<Long,VotersDetailsVO> deletedPancpercMap,LinkedHashMap<Long,VotersDetailsVO> totalAddedPanc){
			for(Object[] panchayat:panchayatDetails){
				VotersDetailsVO addedMandal = addedPancpercMap.get((Long)panchayat[2]);
				if(addedMandal == null){
					addedMandal = new VotersDetailsVO();
					addedMandal.setName(panchayat[3].toString());
					addedPancpercMap.put((Long)panchayat[2], addedMandal);
				}
				VotersDetailsVO deletedMandal = deletedPancpercMap.get((Long)panchayat[2]);
				if(deletedMandal == null){
					deletedMandal = new VotersDetailsVO();
					deletedMandal.setName(panchayat[3].toString());
					deletedPancpercMap.put((Long)panchayat[2], deletedMandal);
				}
				VotersDetailsVO panchayatVo = totalAddedPanc.get((Long)panchayat[0]);
				if(panchayatVo == null){
					addedMandal.setTotalMaleVotesFor18To25(addedMandal.getTotalMaleVotesFor18To25()+1);
					deletedMandal.setTotalMaleVotesFor18To25(deletedMandal.getTotalMaleVotesFor18To25()+1);
				}else{
					if(panchayatVo.getVotersPercentFor18To25() != null){
						Double pancPerc = new Double(panchayatVo.getVotersPercentFor18To25());
						if(pancPerc == 0){
							addedMandal.setTotalMaleVotesFor18To25(addedMandal.getTotalMaleVotesFor18To25()+1);
						}else if(pancPerc > 0 && pancPerc<= 3){
							addedMandal.setTotalMaleVotersFor26To35(addedMandal.getTotalMaleVotersFor26To35()+1);
						}else if(pancPerc > 3 && pancPerc<= 5){
							addedMandal.setTotalFemaleVotersForAbove60(addedMandal.getTotalFemaleVotersForAbove60()+1);
						}else if(pancPerc > 5 && pancPerc<= 10){
							addedMandal.setTotalVotersForYoungerVoters(addedMandal.getTotalVotersForYoungerVoters()+1);
						}else if(pancPerc > 10 && pancPerc<= 20){
							addedMandal.setTotalMaleVotersFor36To45(addedMandal.getTotalMaleVotersFor36To45()+1);
						}else if(pancPerc > 20 && pancPerc<= 40){
							addedMandal.setTotalMaleVotersFor46To60(addedMandal.getTotalMaleVotersFor46To60()+1);
						}else if(pancPerc > 40){
							addedMandal.setTotalMaleVotersForAbove60(addedMandal.getTotalMaleVotersForAbove60()+1);
						}
					}else{
						addedMandal.setTotalMaleVotesFor18To25(addedMandal.getTotalMaleVotesFor18To25()+1);
					}
					if(panchayatVo.getVotersPercentFor26To35() != null){
						Double pancPerc = new Double(panchayatVo.getVotersPercentFor26To35());
						if(pancPerc == 0){
							deletedMandal.setTotalMaleVotesFor18To25(deletedMandal.getTotalMaleVotesFor18To25()+1);
						}else if(pancPerc > 0 && pancPerc<= 3){
							deletedMandal.setTotalMaleVotersFor26To35(deletedMandal.getTotalMaleVotersFor26To35()+1);
						}else if(pancPerc > 3 && pancPerc<= 5){
							deletedMandal.setTotalFemaleVotersForAbove60(deletedMandal.getTotalFemaleVotersForAbove60()+1);
						}else if(pancPerc > 5 && pancPerc<= 10){
							deletedMandal.setTotalVotersForYoungerVoters(deletedMandal.getTotalVotersForYoungerVoters()+1);
						}else if(pancPerc > 10 && pancPerc<= 20){
							deletedMandal.setTotalMaleVotersFor36To45(deletedMandal.getTotalMaleVotersFor36To45()+1);
						}else if(pancPerc > 20 && pancPerc<= 40){
							deletedMandal.setTotalMaleVotersFor46To60(deletedMandal.getTotalMaleVotersFor46To60()+1);
						}else if(pancPerc > 40){
							deletedMandal.setTotalMaleVotersForAbove60(deletedMandal.getTotalMaleVotersForAbove60()+1);
						}
					}else{
						deletedMandal.setTotalMaleVotesFor18To25(deletedMandal.getTotalMaleVotesFor18To25()+1);
					}
				}
			}
		}
	    public void populateAddDeletedMunicPercDetails(List<Object[]> localBodyDetails,Map<Long,VotersDetailsVO> addedPancpercMap,Map<Long,VotersDetailsVO> deletedPancpercMap,LinkedHashMap<Long,VotersDetailsVO> totalAddedMunc ){
	    	for(Object[] panchayat:localBodyDetails){
				VotersDetailsVO addedMandal = addedPancpercMap.get((Long)panchayat[0]);
				if(addedMandal == null){
					addedMandal = new VotersDetailsVO();
					addedMandal.setName(panchayat[1].toString()+" "+panchayat[2].toString());
					addedPancpercMap.put((Long)panchayat[0], addedMandal);
				}
				VotersDetailsVO deletedMandal = deletedPancpercMap.get((Long)panchayat[0]);
				if(deletedMandal == null){
					deletedMandal = new VotersDetailsVO();
					deletedMandal.setName(panchayat[1].toString()+" "+panchayat[2].toString());
					deletedPancpercMap.put((Long)panchayat[0], deletedMandal);
				}
				VotersDetailsVO panchayatVo = totalAddedMunc.get((Long)panchayat[0]);
				if(panchayatVo == null){
					addedMandal.setTotalMaleVotesFor18To25(addedMandal.getTotalMaleVotesFor18To25()+1);
					deletedMandal.setTotalMaleVotesFor18To25(deletedMandal.getTotalMaleVotesFor18To25()+1);
				}else{
					if(panchayatVo.getVotersPercentFor18To25() != null){
						Double pancPerc = new Double(panchayatVo.getVotersPercentFor18To25());
						if(pancPerc == 0){
							addedMandal.setTotalMaleVotesFor18To25(addedMandal.getTotalMaleVotesFor18To25()+1);
						}else if(pancPerc > 0 && pancPerc <= 3){
							addedMandal.setTotalMaleVotersFor26To35(addedMandal.getTotalMaleVotersFor26To35()+1);
						}else if(pancPerc > 3 && pancPerc <= 5){
							addedMandal.setTotalFemaleVotersForAbove60(addedMandal.getTotalFemaleVotersForAbove60()+1);
						}else if(pancPerc > 5 && pancPerc <= 10){
							addedMandal.setTotalVotersForYoungerVoters(addedMandal.getTotalVotersForYoungerVoters()+1);
						}else if(pancPerc > 10 && pancPerc<= 20){
							addedMandal.setTotalMaleVotersFor36To45(addedMandal.getTotalMaleVotersFor36To45()+1);
						}else if(pancPerc > 20 && pancPerc<= 40){
							addedMandal.setTotalMaleVotersFor46To60(addedMandal.getTotalMaleVotersFor46To60()+1);
						}else if(pancPerc > 40){
							addedMandal.setTotalMaleVotersForAbove60(addedMandal.getTotalMaleVotersForAbove60()+1);
						}
					}else{
						addedMandal.setTotalMaleVotesFor18To25(addedMandal.getTotalMaleVotesFor18To25()+1);
					}
					if(panchayatVo.getVotersPercentFor26To35() != null){
						Double pancPerc = new Double(panchayatVo.getVotersPercentFor26To35());
						if(pancPerc == 0){
							deletedMandal.setTotalMaleVotesFor18To25(deletedMandal.getTotalMaleVotesFor18To25()+1);
						}else if(pancPerc > 0 && pancPerc<= 3){
							deletedMandal.setTotalMaleVotersFor26To35(deletedMandal.getTotalMaleVotersFor26To35()+1);
						}else if(pancPerc > 3 && pancPerc<= 5){
							deletedMandal.setTotalFemaleVotersForAbove60(deletedMandal.getTotalFemaleVotersForAbove60()+1);
						}else if(pancPerc > 5 && pancPerc <= 10){
							deletedMandal.setTotalVotersForYoungerVoters(deletedMandal.getTotalVotersForYoungerVoters()+1);
						}else if(pancPerc > 10 && pancPerc<= 20){
							deletedMandal.setTotalMaleVotersFor36To45(deletedMandal.getTotalMaleVotersFor36To45()+1);
						}else if(pancPerc > 20 && pancPerc<= 40){
							deletedMandal.setTotalMaleVotersFor46To60(deletedMandal.getTotalMaleVotersFor46To60()+1);
						}else if(pancPerc > 40){
							deletedMandal.setTotalMaleVotersForAbove60(deletedMandal.getTotalMaleVotersForAbove60()+1);
						}
					}else{
						deletedMandal.setTotalMaleVotesFor18To25(deletedMandal.getTotalMaleVotesFor18To25()+1);
					}
				}
			}
		}	
		
		   public SelectOptionVO createPdf(Long id,Long publicatIonId,String locationType,Long locationValue,String queryType,String path,String type)
		   {
			    
			    SelectOptionVO selectOptionVO = new SelectOptionVO();
			    
			    try {
			    	
			    	LOG.info("Enterd into createPdf () in VoterModification Service");
			    	List<Object[]> constiList =new ArrayList<Object[]>();
			    	if(type.equalsIgnoreCase("district"))
			    	{
			    		constiList =  constituencyDAO.getDistrictConstituencies(id);
			    	}
			    	else
			    	{
			    		Object[] values = {id,null};
			    		constiList.add(values);
			    	}
			    	if(constiList != null && constiList.size() > 0)
			    	{
			    		
			    		//String distName = districtDAO.get(id).getDistrictName();
			    		//FileOutputStream fos = null;
				    	//String FILE1 = path+"VMR"+"/"+""+distName+".zip";
					    //File file1  = new File(FILE1);
				    	//fos = new FileOutputStream(file1);
				    	
				    	//ZipOutputStream zos = new ZipOutputStream(fos);
			    		for (Object[] objects : constiList)
			    		{
			    			Document document = new Document();
			    			Long constituencyId = (Long)objects[0];
			    			locationValue = constituencyId;
			    			Object[] values = constituencyDAO.constituencyName(constituencyId).get(0);
					    	String constituencyType = constituencyDAO.get(constituencyId).getAreaType();
					    	String constituenyName = values[0].toString().toUpperCase();
					    	String districtName = values[1].toString().toUpperCase();
					    	Long constituenyNo = delimitationConstituencyDAO.getConstituencyNo(constituencyId,2009l);
						   
						   // Random randomGenerator = new Random();
						   // int no = randomGenerator.nextInt(10000);
						    //String fileSeparator = System.getProperty("file.separator");
					    	
						    String filePath = "VMR"+"/"+""+districtName+"_"+constituenyNo+"_"+constituenyName+".pdf";
						    String FILE = path+filePath;
						    File file  = new File(FILE);
						    file.createNewFile();
						   // selectOptionVO.setUrl("VMR"+"/"+""+distName+".zip");
						  	try {
						  		PdfWriter.getInstance(document, new FileOutputStream(FILE));
						  	} catch (FileNotFoundException e) {
						  		e.printStackTrace();
						  	} catch (DocumentException e) {
						  		e.printStackTrace();
						  	}
						  	Long previousPublicationId = getPerviousPublicationId(publicatIonId);
						  	String presentPublicationName = publicationDateDAO.get(publicatIonId).getName().toUpperCase();
						  	String previousPublicationName = publicationDateDAO.get(previousPublicationId).getName().toUpperCase();
						  	document.open();
						  	addTitlePage(document,constituenyName,presentPublicationName,previousPublicationName);
						  	
						  	PdfVO pdfVO = getAddedVotersInAPublication(constituencyId,previousPublicationId,publicatIonId);
							List<VoterAgeRangeVO> voterAgeRangeVOList = getVoterInfoByPublicationDateListNew( locationType, locationValue, constituencyId, previousPublicationId, publicatIonId);
				  			if(voterAgeRangeVOList != null && voterAgeRangeVOList.size() > 0)
				  			{
				  				voterModifiationPdfsGenerations.buildVoterModifivationReport(voterAgeRangeVOList,document,constituenyName);
				  			}
						  	VoterModificationGenderInfoVO voterModificationGenderInfoVO = getGenderWiseVoterModificationsBetweenPublications( locationType, locationValue, constituencyId, previousPublicationId, publicatIonId, queryType);
						  	if(voterModificationGenderInfoVO != null)
						  	{
						  		voterModifiationPdfsGenerations.buildGenderWiseVoterModifivationReport(voterModificationGenderInfoVO,document,constituenyName);
						  	}
				  			//List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList =  getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications( locationType, locationValue, constituencyId, previousPublicationId, publicatIonId, queryType);
				  			List<VoterModificationAgeRangeVO> voterModificationAgeRangeVOList =  pdfVO.getAgeRangeVOList();
				  			if(voterModificationAgeRangeVOList != null && voterModificationAgeRangeVOList.size() > 0)
				  			{
				  				voterModifiationPdfsGenerations.buildVoterModifivationReportByAgeRange(voterModificationAgeRangeVOList,document,constituenyName);
				  				voterModifiationPdfsGenerations.buildVoterModifivationReportByAgeRangeByGender(voterModificationAgeRangeVOList,document,constituenyName);
				  			}
						  	/*List<VoterModificationGenderInfoVO> listForGender = getGenderWiseVoterModificationsForEachPublication( locationType, locationValue, constituencyId, fromPublicationDateId, toPublicationDateId, queryType);
						  	if(listForGender != null && listForGender.size() > 0)
						  	{
						  		buildAddedDeletedVotesByGender(listForGender,document,constituenyName);
						  	}*/
						   VoterModificationVO voterModificationVO  = getSubLevelsVoterModificationDetailsByLocationValue(
									 locationType,  locationValue,  constituencyId,
									 previousPublicationId,  publicatIonId);
						    if(voterModificationVO != null)
						    {
					  		List<VoterModificationVO> list = voterModificationVO.getModifiedVotersList();
					  		   voterModifiationPdfsGenerations.buildAddedDeletedVotesByMundal(list,document,constituenyName);
						    }
						    List<VoterAdderdOrDeletedRengesInfoVO> returnList = getReportForVotersAddedOrDeletedVotersForSelectdConstituency(constituencyId,publicatIonId,"");
						   // PdfVO pdfVO = getAddedVotersInAPublication(constituencyId,previousPublicationId,publicatIonId);
						 
						    if(returnList != null && returnList.size() > 0)
						    {
							   					  
						    	voterModifiationPdfsGenerations.createTableForAddedVoters(document,returnList,constituenyName);
						    	voterModifiationPdfsGenerations.deletedVotersDetails(document,returnList,constituenyName);
							  
						    }
						    if(pdfVO != null)
						    {
						    	 voterModifiationPdfsGenerations.panchayatWiseAddedDeletedVoterDetails(document,pdfVO,constituenyName);
						    	 List<VotersDetailsVO> addedList   = pdfVO.getCompleteVoterDetailsVoList();
						    	 if(addedList != null && addedList.size() > 1)
						    	 {
						    		 Collections.sort(addedList,sortData);
						    	 }
						    	 voterModifiationPdfsGenerations.panchayatWiseAddedDeletedVoterDetailsByList(document,addedList,constituenyName,"add");
						    	 List<VotersDetailsVO> deletedList = pdfVO.getCompleteVoterDetailsVoList();
						    	 
						    	 if(deletedList != null && deletedList.size() > 0)
						    	 {
						    		 Collections.sort(deletedList,deletedDatesortData);
						    	 }
						    	 voterModifiationPdfsGenerations.panchayatWiseAddedDeletedVoterDetailsByList(document,deletedList,constituenyName,"delete");
						    	 List<VotersDetailsVO> added   = pdfVO.getAddedDetaildByPerc();
						    	 if(added != null && added.size() > 1)
						    	 {
						    		 voterModifiationPdfsGenerations.buildAddedOrDeletedVotersbyPrecReport("add",document,pdfVO,constituenyName); 
						    	 }
						    	 List<VotersDetailsVO> deleted   = pdfVO.getDeletedDetaildByPerc();
						    	 if(deleted != null && deleted.size() >1)
						    	 {
						    		 voterModifiationPdfsGenerations.buildAddedOrDeletedVotersbyPrecReport("delete",document,pdfVO,constituenyName);
						    	 }
						    	 
						    	 
						    	 voterModifiationPdfsGenerations.agewiseAddedDeletedVoterDetails("add",document,pdfVO,constituenyName);
						    	 voterModifiationPdfsGenerations.agewiseAddedDeletedVoterDetails("delete",document,pdfVO,constituenyName);
						    	 List<VotersDetailsVO> boothWiseAdded = pdfVO.getBoothWiseAddedList();
						    	 List<VotersDetailsVO> boothWiseDeleted = pdfVO.getBoothWiseDeletedList();
						    	 if(boothWiseAdded != null && boothWiseAdded.size() > 0)
						    	 {
						    		 voterModifiationPdfsGenerations.buildAddedOrDeletedVotersbyBoothWiseReport("add",document,pdfVO,constituenyName,constituencyType);
						    	 }
						    	 if(boothWiseDeleted != null && boothWiseDeleted.size() > 0)
						    	 {
						    		 voterModifiationPdfsGenerations.buildAddedOrDeletedVotersbyBoothWiseReport("delete",document,pdfVO,constituenyName,constituencyType);
						    	 }
						    	 voterModifiationPdfsGenerations.totalAddedOrDeletedVoterDetails("add",document,pdfVO,constituenyName);
						    	 voterModifiationPdfsGenerations.totalAddedOrDeletedVoterDetails("delete",document,pdfVO,constituenyName);
						    	 
						    	 document.close();
						    	 /*addToZipFile(filePath, zos);
						    	 zos.close();
								 fos.close();*/
						    }
						}
			    		
			    		
						
						selectOptionVO.setName("success");
			    	}
			    	
				} catch (Exception e) {
					selectOptionVO.setName("fail");
					LOG.error("Exception occured in createPdf () in VoterModification Service",e);
				}
			    
			   return selectOptionVO;
			   
		  }
		  
		   public  void addToZipFile(String fileName, ZipOutputStream zos)  {

				//System.out.println("Writing '" + fileName + "' to zip file");
				try {
					File file = new File(fileName);
					FileInputStream fis = new FileInputStream(file);
					ZipEntry zipEntry = new ZipEntry(fileName);
					zos.putNextEntry(zipEntry);

					byte[] bytes = new byte[1024];
					int length;
					while ((length = fis.read(bytes)) >= 0) {
						zos.write(bytes, 0, length);
					}

					zos.closeEntry();
					fis.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		   
		  public Long getPerviousPublicationId(Long publicationDateId)
		  {
			  List<Long> previousPublications = publicationDateDAO.getPreviousPublicationIds(publicationDateId) ;
			  Long previousPublicationId = publicationDateId;
			  if(previousPublications != null && previousPublications.size() > 0)
			  {
			    previousPublicationId = previousPublications.get(0);
		      }
			  return previousPublicationId;
		  }
		  
		  public static Comparator<VotersDetailsVO> sortData = new Comparator<VotersDetailsVO>()
	      {
	   
	        public int compare(VotersDetailsVO resultList1, VotersDetailsVO resultList2)
	        {
	        	if(resultList2 == null && resultList1 == null){
	        		return 0;
	        	}else if(resultList2 == null){
	        		return -1;
	        	}else if(resultList1 == null){
	        		return 1;
	        	}
	        	if(resultList2.getVotersPercentFor18To25() == null && resultList1.getVotersPercentFor18To25() == null){
	        		return 0;
	        	}else if(resultList2.getVotersPercentFor18To25() == null){
	        		return -1;
	        	}else if(resultList1.getVotersPercentFor18To25() == null){
	        		return 1;
	        	}
	        	return (Double.valueOf(resultList2.getVotersPercentFor18To25())).compareTo(Double.valueOf(resultList1.getVotersPercentFor18To25()));
	           
	        	
	        }
	      };
				
	      public static Comparator<VotersDetailsVO> deletedDatesortData = new Comparator<VotersDetailsVO>()
	      {
	   
	        public int compare(VotersDetailsVO resultList1, VotersDetailsVO resultList2)
	        {
	        	if(resultList2 == null && resultList1 == null){
	        		return 0;
	        	}else if(resultList2 == null){
	        		return -1;
	        	}else if(resultList1 == null){
	        		return 1;
	        	}
	        	if(resultList2.getVotersPercentFor26To35() == null && resultList1.getVotersPercentFor26To35() == null){
	        		return 0;
	        	}else if(resultList2.getVotersPercentFor26To35() == null){
	        		return -1;
	        	}else if(resultList1.getVotersPercentFor26To35() == null){
	        		return 1;
	        	}
	        	return (Double.valueOf(resultList2.getVotersPercentFor26To35())).compareTo(Double.valueOf(resultList1.getVotersPercentFor26To35()));
	           
	        	
	        }
	      };
	      
	      @SuppressWarnings("unused")
		  private static void addTitlePage(Document document,String constituencyName,String presentPublicationName,String previousPublicationName)
	    	    throws DocumentException {
	    	    Paragraph preface = new Paragraph();
	    	    // We add one empty line
	    	    //addEmptyLine(preface, 1);
	    	    // Lets write a big header
	    	    preface.setAlignment(Element.ALIGN_CENTER);
	    	    preface.add(new Paragraph(constituencyName.toUpperCase()  +" CONSTITUENCY VOTER MODIFICATION REPORT " , catFont));
	    	    preface.add(new Paragraph(previousPublicationName+ " TO " +presentPublicationName+"" , catFont1));
	    	    document.add(preface);
	    	    //addEmptyLine(preface, 1);
	    	    // Will create: Report generated by: _name, _date
	    	    // preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	    	    //smallBold));
	    	    //addEmptyLine(preface, 3);
	    	    ////preface.add(new Paragraph("This document describes something which is very important ",
	    	    //     smallBold));

	    	    // addEmptyLine(preface, 8);

	    	    // preface.add(new Paragraph("This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).",
	    	    //    redFont));

	    	   
	    	    // Start a new page
	    	    // document.newPage();
	    	  }
	      
		     /* private static void addEmptyLine(Paragraph paragraph, int number)
		      {
		    	    for (int i = 0; i < number; i++)
		    	    {
		    	       paragraph.add(new Paragraph(" "));
		    	    }
		      }*/
	      public List<VoterAgeRangeVO> getVoterInfoByPublicationDateListNew(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
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
	 				 Map<Long,Long> maleCount = new HashMap<Long,Long>(); 
	 				Map<Long,Long> femaleCount = new HashMap<Long,Long>(); 
	 			   List<Object[]> list = voterInfoDAO.getVoterInfoByPublicationDateIdsNew(votersAnalysisService.getReportLevelId(locationType), locationValue, publicationDateIds);
	 			   List<Object[]> maleList = voterInfoDAO.getVoterInfoByPublicationDateIdsNewMaleCount(votersAnalysisService.getReportLevelId(locationType), locationValue, publicationDateIds);
	 			   List<Object[]> femaleList = voterInfoDAO.getVoterInfoByPublicationDateIdsNewFemaleCount(votersAnalysisService.getReportLevelId(locationType), locationValue, publicationDateIds);
	 			   for(Object[] male:maleList){
	 				  maleCount.put((Long)male[1], (Long)male[0]);
	 			   }
	 			  for(Object[] female:femaleList){
	 				 femaleCount.put((Long)female[1], (Long)female[0]);
	 			   }
	 			   if(list != null && list.size() > 0)
	 			   {
	 				   voterAgeRangeVOList = new ArrayList<VoterAgeRangeVO>(0);
	 				 for(Object[] params : list)
	 				 {
	 					 VoterAgeRangeVO voterAgeRangeVO = new VoterAgeRangeVO();
	 					 voterAgeRangeVO.setTotalVoters((Long)params[0]);
	 					 voterAgeRangeVO.setMaleVoters(maleCount.get((Long)params[1]));
	 					 voterAgeRangeVO.setFemaleVoters(femaleCount.get((Long)params[1]));
	 					 voterAgeRangeVO.setPublicationDate(params[2].toString());
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
	      public List<SelectOptionVO> insertGenderWiseVoterModifInfoInVoterModificationInfoTableForDistrict(Long districtId, Long publicationDateId)
		  {
			  LOG.info(" Entered into insertGenderWiseVoterModifInfoInVoterModificationInfoTableForDistrict() Method, with Values - Report Level Value - "+districtId+" and Publicarion Date Id - "+publicationDateId);
			  List<SelectOptionVO> result = new ArrayList<SelectOptionVO>();
			  ResultStatus resultStatus = new ResultStatus();
			  try{
				  SelectOptionVO mainvo = new SelectOptionVO();
				 
				  List<SelectOptionVO> constituencies = staticDataService.getConstituenciesFordistricts(districtId);
				  if(constituencies != null && constituencies.size() > 0)
					
				  if(constituencies != null && constituencies.size() > 0)
				  {
				  for(SelectOptionVO vo :constituencies )
				  {
					  resultStatus=insertGenderWiseVoterModifInfoInVoterModificationInfoTable(vo.getId(), publicationDateId);
				  if(resultStatus.getResultCode() == 1)
				  {
					  result.add(new SelectOptionVO(vo.getId(),vo.getName())); 
				  }
				  }
				  mainvo.setTotalCount(new Long(constituencies.size()));
				  result.add(mainvo);
				  result.get(0).setTotalCount(new Long(constituencies.size()));
				  }
				 
				  
			  }catch (Exception e) {
				  LOG.error("Exception Occured in insertGenderWiseVoterModifInfoInVoterModificationInfoTableForDistrict(), Exception is -",e);
				  
			 }
			return result;
		  }	
	}

	
