package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterAgeRangeDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.dto.VoterModificationGenderInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterModificationService implements IVoterModificationService{

	private static final Logger LOG = Logger.getLogger(VoterModificationService.class);
	
	private IVotersAnalysisService votersAnalysisService;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IVoterModificationDAO voterModificationDAO;
	private IVoterAgeRangeDAO voterAgeRangeDAO;
	private IVoterInfoDAO voterInfoDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	
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
				 voterModificationVO.setPreviousPublicationId(getPreviousPublicationIds(toPublicationDateId).get(0));
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
					 ageTo = new Long(ages[1].trim());
					 
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
	 
	 public List<VoterAgeRangeVO> getVoterInfoByPublicationDateList(String locationType,Long locationValue,Long constituencyId,Long fromPublicationDateId,Long toPublicationDateId)
	 {
		 List<VoterAgeRangeVO> voterAgeRangeVOList = null;
		 try{
			 
			 List<Long> publicationDateIds = getVoterPublicationIdsBetweenTwoPublications(fromPublicationDateId, toPublicationDateId);
			 
			 if(publicationDateIds != null && publicationDateIds.size() > 0)
			 {
				 if(locationType.equalsIgnoreCase(IConstants.MANDAL))
				 {
					 Long id = new Long(locationValue.toString().trim().substring(1));
					 
					 if(locationValue.toString().trim().substring(0,1).equalsIgnoreCase("2"))
						 locationValue = id;
					 else
					 {
						 List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(id);
						 locationValue = (Long)list.get(0);
						 locationType = IConstants.LOCALELECTIONBODY;
					 }
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
	 
}
