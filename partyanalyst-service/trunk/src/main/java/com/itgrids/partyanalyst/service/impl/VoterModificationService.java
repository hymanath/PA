package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

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
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationInfoDAO;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
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
			 return boothPublicationVoterDAO.getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId, toPublicationDateId);
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
			 return boothPublicationVoterDAO.getPreviousPublicationIds(publicationDateId);
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
	 
	 public List<VoterModificationAgeRangeVO> getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 LOG.debug("Entered into getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
		 List<VoterModificationAgeRangeVO> result = new ArrayList<VoterModificationAgeRangeVO>(0);
		 try{
			 List<String> ageRanges = voterAgeRangeDAO.getAllVoterAgeRanges();
			 VoterModificationAgeRangeVO voterModificationAgeRangeVO = null;
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
						 else if(params[1].toString().equalsIgnoreCase(IConstants.STATUS_DELETED));
						 	voterModificationAgeRangeVO.setDeletedCount((Long)params[0]);
					 }
				 }
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
	 
	 /**
	 * This method will return {@link VoterModificationGenderInfoVO}, which contains Gender wise Newly Added/Deleted Voters Count.     
	 * @param String locationType
	 * @param Long locationValue
	 * @param Long Constituency Id
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @author Kamalakar Dandu
	 * @return {@link VoterModificationGenderInfoVO}
	 * 
	 */
	 public VoterModificationGenderInfoVO getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 LOG.debug("Entered into getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
		 VoterModificationGenderInfoVO result = new VoterModificationGenderInfoVO();
		 try{
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
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
				 }
				 result.setAddedTotal(result.getAddedMale() + result.getAddedFemale());
				 result.setDeletedTotal(result.getDeletedMale() + result.getDeletedFemale());
			 }
			 return result;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getVotersAddedAndDeletedCountAgeWiseInBeetweenPublications() Method");
			 LOG.error("Exception is - "+e);
			 return result;
		 }
	 }
	 
	 /**
	 * This method will return List<{@link VoterModificationGenderInfoVO}>, which contains Gender wise Newly Added/Deleted Voters Count.     
	 * @param String locationType
	 * @param Long locationValue
	 * @param Long Constituency Id
	 * @param Long From Publication Id
	 * @param Long To Publication Id
	 * @author Kamalakar Dandu
	 * @return List<{@link VoterModificationGenderInfoVO}>
	 * 
	 */
	 public List<VoterModificationGenderInfoVO> getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 LOG.debug("Entered into getGenderWiseVoterModificationsForEachPublication() Method");
		 List<VoterModificationGenderInfoVO> result = new ArrayList<VoterModificationGenderInfoVO>();
		 try{
			 List<Long> publicationIdsList = getVoterPublicationIdsBetweenTwoPublicationsForVotersModification(fromPublicationDateId, toPublicationDateId);
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
					 if(flag)
						 result.add(infoVO); 
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
				 }
			 }
			 return result;
		 }catch (Exception e) {
			 LOG.error("Exception Occured in getGenderWiseVoterModificationsForEachPublication() Method");
			 LOG.error("Exception is - "+e);
			 return result;
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
				 
				if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY)|| locationType.equalsIgnoreCase("localElectionBody"))
				{
					List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
					locationValue = (Long)list.get(0);
				}
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
					 voter.setBoothName(params[8].toString());
					 voter.setPanchayatId((Long)params[9]);
					 voter.setPanchayatName(params[10].toString());
					 voter.setStatus(params[11].toString());
					 voter.setPublicationDateId((Long)params[12]);
					 voter.setPublicationName(params[13].toString());
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
			 if(locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || locationType.equalsIgnoreCase("localElectionBody"))
			 {
				 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(locationValue);
				 locationValue = (Long)list.get(0);
			 }
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
	 
}
