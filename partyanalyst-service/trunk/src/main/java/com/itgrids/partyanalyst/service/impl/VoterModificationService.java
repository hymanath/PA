package com.itgrids.partyanalyst.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IBoothPublicationVoterDAO;
import com.itgrids.partyanalyst.dao.IVoterInfoDAO;
import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.dto.VoterAgeRangeVO;
import com.itgrids.partyanalyst.excel.booth.VoterModificationVO;
import com.itgrids.partyanalyst.service.IVoterModificationService;
import com.itgrids.partyanalyst.service.IVotersAnalysisService;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterModificationService implements IVoterModificationService{

	private static final Logger LOG = Logger.getLogger(VoterModificationService.class);
	
	private IVotersAnalysisService votersAnalysisService;
	private IBoothPublicationVoterDAO boothPublicationVoterDAO;
	private IVoterModificationDAO voterModificationDAO;
	private static final Logger log = Logger.getLogger(VotersAnalysisService.class);
	private IVoterInfoDAO voterInfoDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	
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
			 log.error("Exception Occured in getVoterInfoByPublicationDateList() Method, Exception - "+e);
			 return voterAgeRangeVOList;
		}
		 
	 }
	 
}
