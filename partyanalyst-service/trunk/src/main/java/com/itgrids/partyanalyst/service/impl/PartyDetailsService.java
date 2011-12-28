package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.dao.IPartyManifestoDAO;
import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.dao.IPartyUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.PartyPageVO;
import com.itgrids.partyanalyst.dto.PartyVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.Candidate;
import com.itgrids.partyanalyst.model.CandidateProfileDescription;
import com.itgrids.partyanalyst.model.CandidateUpdatesEmail;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyGallery;
import com.itgrids.partyanalyst.model.PartyManifesto;
import com.itgrids.partyanalyst.model.PartyProfileDescription;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;
import com.itgrids.partyanalyst.dao.hibernate.PartyProfileDescriptionDAO;

public class PartyDetailsService implements IPartyDetailsService {
	
	private static final Logger log = Logger
			.getLogger(PartyDetailsService.class);
	private DateUtilService dateUtilService = new DateUtilService();
	private IPartyDAO partyDAO;
	private IPartyProfileDescriptionDAO partyProfileDescriptionDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private IPartyGalleryDAO partyGalleryDAO;
	private PartyProfileDescription partyProfileDescription;
	private IGallaryDAO gallaryDAO;
	private IContentTypeDAO contentTypeDAO;
	private IRegistrationDAO registrationDAO;
	private IUserGallaryDAO userGallaryDAO;
	private IFileTypeDAO fileTypeDAO;
	private ISourceLanguageDAO sourceLanguageDAO;
	private ISourceDAO sourceDAO;
	private IRegionScopesDAO regionScopesDAO;
	private IElectionTypeDAO electionTypeDAO;
	private IFileDAO fileDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private IPartyManifestoDAO partyManifestoDAO;
	private IElectionDAO electionDAO;
	private IStateDAO stateDAO;
	private ICandidateDAO candidateDAO;
	private IConstituencyDAO constituencyDAO; 
	private IMessageToCandidateDAO messageToCandidateDAO;
	private IPartyUpdatesEmailDAO partyUpdatesEmailDAO;
	
	public IPartyManifestoDAO getPartyManifestoDAO() {
		return partyManifestoDAO;
	}

	public void setPartyManifestoDAO(IPartyManifestoDAO partyManifestoDAO) {
		this.partyManifestoDAO = partyManifestoDAO;
	}

	public IElectionTypeDAO getElectionTypeDAO() {
		return electionTypeDAO;
	}

	public void setElectionTypeDAO(IElectionTypeDAO electionTypeDAO) {
		this.electionTypeDAO = electionTypeDAO;
	}

	public IFileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}

	public ISourceDAO getSourceDAO() {
		return sourceDAO;
	}

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	public ISourceLanguageDAO getSourceLanguageDAO() {
		return sourceLanguageDAO;
	}

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}

	public IFileTypeDAO getFileTypeDAO() {
		return fileTypeDAO;
	}

	public void setFileTypeDAO(IFileTypeDAO fileTypeDAO) {
		this.fileTypeDAO = fileTypeDAO;
	}

	public IUserGallaryDAO getUserGallaryDAO() {
		return userGallaryDAO;
	}

	public void setUserGallaryDAO(IUserGallaryDAO userGallaryDAO) {
		this.userGallaryDAO = userGallaryDAO;
	}

	public IPartyUpdatesEmailDAO getPartyUpdatesEmailDAO() {
		return partyUpdatesEmailDAO;
	}

	public void setPartyUpdatesEmailDAO(IPartyUpdatesEmailDAO partyUpdatesEmailDAO) {
		this.partyUpdatesEmailDAO = partyUpdatesEmailDAO;
	}

	public IMessageToCandidateDAO getMessageToCandidateDAO() {
		return messageToCandidateDAO;
	}

	public void setMessageToCandidateDAO(
			IMessageToCandidateDAO messageToCandidateDAO) {
		this.messageToCandidateDAO = messageToCandidateDAO;
	}

	public IConstituencyDAO getConstituencyDAO() {
		return constituencyDAO;
	}

	public void setConstituencyDAO(IConstituencyDAO constituencyDAO) {
		this.constituencyDAO = constituencyDAO;
	}

	public ICandidateDAO getCandidateDAO() {
		return candidateDAO;
	}

	public void setCandidateDAO(ICandidateDAO candidateDAO) {
		this.candidateDAO = candidateDAO;
	}

	public IRegistrationDAO getRegistrationDAO() {
		return registrationDAO;
	}

	public void setRegistrationDAO(IRegistrationDAO registrationDAO) {
		this.registrationDAO = registrationDAO;
	}

	public IContentTypeDAO getContentTypeDAO() {
		return contentTypeDAO;
	}

	public void setContentTypeDAO(IContentTypeDAO contentTypeDAO) {
		this.contentTypeDAO = contentTypeDAO;
	}

	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}

	public PartyProfileDescription getPartyProfileDescription() {
		return partyProfileDescription;
	}

	public void setPartyProfileDescription(
			PartyProfileDescription partyProfileDescription) {
		this.partyProfileDescription = partyProfileDescription;
	}

	public IPartyGalleryDAO getPartyGalleryDAO() {
		return partyGalleryDAO;
	}

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}

	public IPartyProfileDescriptionDAO getPartyProfileDescriptionDAO() {
		return partyProfileDescriptionDAO;
	}

	public void setPartyProfileDescriptionDAO(
			IPartyProfileDescriptionDAO partyProfileDescriptionDAO) {
		this.partyProfileDescriptionDAO = partyProfileDescriptionDAO;
	}

	public IPartyDAO getPartyDAO() {
		return partyDAO;
	}

	public void setPartyDAO(IPartyDAO partyDAO) {
		this.partyDAO = partyDAO;
	}	

	public IElectionDAO getElectionDAO() {
		return electionDAO;
	}

	public void setElectionDAO(IElectionDAO electionDAO) {
		this.electionDAO = electionDAO;
	}

	public IStateDAO getStateDAO() {
		return stateDAO;
	}

	public void setStateDAO(IStateDAO stateDAO) {
		this.stateDAO = stateDAO;
	}

	public PartyVO getPartyDetails(Long partyId) {
		PartyVO partyVO = new PartyVO();
		List<Party> party = partyDAO.getPartyDetails(partyId);
		if (party != null) {
			for (Party party2 : party) {
				partyVO.setPartyLongName(party2.getLongName());
				partyVO.setAddress(party2.getAddress());
				partyVO.setPartyFlag(party2.getPartyFlag());
				partyVO.setPartyId(party2.getPartyId());
				partyVO.setPartyLogo(party2.getPartyLogo());
				partyVO.setPartyRecognization(party2.getPartyRecognization());
				partyVO.setPartyShortName(party2.getShortName());
				partyVO.setSymbol(party2.getSymbol());

			}
			return partyVO;
		}
		return null;
	}

	public List<String> getPartyProfileDescriptionById(Long partyId) {
		try {
			List<Object> results = partyProfileDescriptionDAO
					.getPartyProfileDescription(partyId);

			if (results != null && results.size() > 0) {
				List<String> descList = new ArrayList<String>(0);
				for (Object desc : results)
					descList.add(desc.toString());
				return descList;
			} else
				return null;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<FileVO> getNewsToDisplay(Long partyId, int firstResult,int maxResult, String queryType) 
	{
		List<FileVO> retValue = new ArrayList<FileVO>();
		try {
			List<File> file = partyGalleryDAO.getFirstFourNewsForParty(partyId,firstResult, maxResult, queryType);
			
			if(file != null && file.size() > 0)
			{
				for (File file2 : file) 
				{
					FileVO fileVO = new FileVO();
					fileVO.setFileId((Long) file2.getFileId());
					fileVO.setFileName1(file2.getFileName() != null ? file2.getFileName() : "");
					fileVO.setPath(file2.getFilePath());
					fileVO.setFileTitle1(file2.getFileTitle() != null ? file2.getFileTitle() : "");
					fileVO.setFileDescription1(file2.getFileDescription() != null ? file2.getFileDescription(): "");
					fileVO.setSource(file2.getSourceObj() != null ? file2.getSourceObj().getSource() : "");
					fileVO.setLanguage(file2.getLanguage() != null ? file2.getLanguage().getLanguage() : "");
					fileVO.setFileDate(file2.getFileDate() != null ? file2.getFileDate().toString() : "");
					retValue.add(fileVO);
				}
		   }
		  return retValue;
		} catch (Exception e) {
			log.error("Exception occured, Check log for details - "+e);
			return retValue;
		}
	}

	public FileVO copyFileToFileVO(File file) {
		try {
			log.debug("Entered into copyFileToFileVO() Method");
			FileVO fileVO = null;

			if (file != null) {
				fileVO = new FileVO();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				fileVO.setFileId(file.getFileId());
				fileVO.setName(file.getFileName());
				fileVO.setPath(file.getFilePath());
				fileVO.setFileType(file.getFileType() != null ? file
						.getFileType().getType() : "");
				fileVO.setTitle(file.getFileTitle());
				fileVO.setDescription(file.getFileDescription());
				fileVO.setKeywords(file.getKeywords());
				fileVO.setSource(file.getSource());
				fileVO.setFileDate(file.getFileDate() != null ? sdf.format(file
						.getFileDate()) : "");
			}
			return fileVO;
		} catch (Exception e) {
			log.error("Error Occured in copyFileToFileVO() Method - " + e);
			return null;
		}
	}

	public List<FileVO> getPartyLatestVideos(Long partyId, Integer startIndex,
			Integer maxRecords) {
		try {
			log.debug("Entered into getCandidateLatestVideos() Method");

			List<FileVO> fileList = null;
			List<File> list = fileGallaryDAO.getPartyLatestVideos(partyId,
					startIndex, maxRecords);

			if (list != null && list.size() > 0) {
				fileList = new ArrayList<FileVO>(0);
				FileVO fileVO = null;
				for (File file : list) {
					fileVO = copyFileToFileVO(file);
					if (fileVO != null)
						fileList.add(fileVO);
				}
			}

			return fileList;
		} catch (Exception e) {
			log.error("Error Occured in getCandidateLatestVideos() Method - "
					+ e);
			return null;
		}
	}

	public List<FileVO> getFirstThreePhotoGallaryDetail(Long partyId)
	{
		List<FileVO> retValue = new ArrayList<FileVO>();
		try {
			List<Object[]> results = partyGalleryDAO.getPartyGallaryDetail(partyId, 0, 20, IConstants.PHOTO_GALLARY);

			if(results != null && results.size() > 0)
			{
				for(Object[] gallary : results)
				{
					FileVO fileVO = new FileVO();
					List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
					
					for(Object[] startingRecord : record)
					{
						fileVO.setFileId((Long) startingRecord[0]);
						fileVO.setName(startingRecord[1] != null ? startingRecord[1].toString(): "");
						fileVO.setPath(startingRecord[2] != null ? startingRecord[2].toString():"");
						String title = "";
						
						if(startingRecord[3] != null && startingRecord[3].toString().length() >= 18)
						{
							title = startingRecord[3].toString().substring(0, 17);
							title = title + "...";
						} 
						else
						{
						if(startingRecord[3] != null) {
							title = startingRecord[3].toString();
						}
					}
					fileVO.setTitle(title);
				}
				fileVO.setGallaryId((Long) gallary[0]);
				fileVO.setSizeOfGallary((long) (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size()));
				fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() : "");
				fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString() : "");
				fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() : "");
				fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() : "");
				retValue.add(fileVO);
			}
		  }
			return retValue;
		} catch (Exception e) {
			log.error("Exception occured, check for log details - "+e);
			return retValue;
		}
	}

	public List<FileVO> getPartyPhotoGallaryDetailWithOutGallerySizeZero(Long partyId, int firstRecord, int maxRecord, String type) 
	{
		List<FileVO> retValue = new ArrayList<FileVO>();
		try {
			List<Object[]> results = partyGalleryDAO.getPartyGallaryDetail(partyId, firstRecord, maxRecord, type);

			if(results != null && results.size() > 0)
			{
				for (Object[] gallary : results) 
				{
					FileVO fileVO = new FileVO();
					List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
					for (Object[] startingRecord : record)
					{
						if (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size() > 0L) {
							fileVO.setFileId((Long) startingRecord[0]);
							fileVO.setName(startingRecord[1] != null ? startingRecord[1].toString(): "");
							fileVO.setPath(startingRecord[2] != null ? startingRecord[2].toString(): "");
							fileVO.setTitle(startingRecord[3] != null ? startingRecord[3].toString():"");
							fileVO.setGallaryId((Long) gallary[0]);
							fileVO.setSizeOfGallary((long) (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size()));
							fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() : "");
							fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString(): "");
							fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString(): "");
							fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString(): "");
							retValue.add(fileVO);
						}
					}
				}
			}
			return retValue;
		} catch (Exception e) {
			e.printStackTrace();
			return retValue;
		}
	}

	public List<FileVO> getPartyPhotoGallaryDetail(Long partyId,
			int firstRecord, int maxRecord, String type) {
		List<FileVO> retValue = new ArrayList<FileVO>();
		try {
			List<Object[]> results = partyGalleryDAO.getPartyGallaryDetail(
					partyId, firstRecord, maxRecord, type);

			for (Object[] gallary : results) {
				FileVO fileVO = new FileVO();
				List<Object[]> record = fileGallaryDAO
						.getStartingRecordInGallary((Long) gallary[0]);
				for (Object[] startingRecord : record) {
					fileVO.setFileId((Long) startingRecord[0]);
					fileVO.setName(startingRecord[1] != null ? WordUtils
							.capitalize(startingRecord[1].toString()) : "");
					if (type.equalsIgnoreCase(IConstants.VIDEO_GALLARY))
						fileVO.setPath(startingRecord[2].toString());
					else
						fileVO.setPath(IConstants.UPLOADED_FILES + "/"
								+ startingRecord[1].toString());

					fileVO.setTitle(startingRecord[3] != null ? WordUtils
							.capitalize(startingRecord[3].toString()) : "");

				}
				fileVO.setGallaryId((Long) gallary[0]);
				fileVO.setSizeOfGallary((long) (fileGallaryDAO
						.getAllRecordInGallary((Long) gallary[0]).size()));
				fileVO.setGallaryName(gallary[1] != null ? WordUtils
						.capitalize(gallary[1].toString()) : "");
				fileVO.setGallaryDescription(gallary[2] != null ? WordUtils
						.capitalize(gallary[2].toString()) : "");
				fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3]
						.toString() : "");
				fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4]
						.toString() : "");
				retValue.add(fileVO);
			}
			return retValue;
		} catch (Exception e) {
			e.printStackTrace();
			return retValue;
		}
	}

	public List<SelectOptionVO> getAllPartysNames() {
		try {
			log.debug("Entered into getAllPartysNames() Method");

			List<SelectOptionVO> partySelectList = null;
			List<Object[]> list = partyDAO.findAllPartyNames();

			if (list != null && list.size() > 0) {
				partySelectList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for (Object[] params : list) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long) params[0]);
					selectOptionVO.setName(params[2].toString());
					partySelectList.add(selectOptionVO);
				}
			}
			return partySelectList;
		} catch (Exception e) {
			log
					.error("Exception Occured in  getAllPartysNames() method - "
							+ e);
			return null;
		}
	}

	// added
	public ResultStatus saveDescription(GallaryVO gallaryVO) {
		log.debug("Entered into saveDescription() Method");
		Long orderNo;
		partyProfileDescription = new PartyProfileDescription();
		ResultStatus resultStatus = new ResultStatus();
		try {
			List<Object> results = partyProfileDescriptionDAO
					.getMaxOrderNo(gallaryVO.getCandidateId());

			orderNo = results.get(0) == null ? 0l : (Long) results.get(0);
			orderNo = orderNo + 1;
			partyProfileDescription.setDescription(gallaryVO.getDescription());
			partyProfileDescription.setOrderNo(orderNo);
			partyProfileDescription.setParty(partyDAO.get(gallaryVO
					.getCandidateId()));
			partyProfileDescriptionDAO.save(partyProfileDescription);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in saveDescription() method - " + e);
			return resultStatus;
		}
	}

	public ResultStatus subScribeEmailAlertForAUser(String emailId ,Long partyId){
		 
		 ResultStatus statusCode = new ResultStatus()  ;
		 try {
			PartyUpdatesEmail partyUpdatesEmail = new PartyUpdatesEmail();
				 
				 partyUpdatesEmail.setEmail(emailId);
				 partyUpdatesEmail.setParty(partyDAO.get(partyId));
				 partyUpdatesEmail.setUnsubscribed("false");
				 partyUpdatesEmailDAO.save(partyUpdatesEmail);
		
		     statusCode.setResultCode(ResultCodeMapper.SUCCESS);
		 
		     return statusCode;
		
		 }catch(Exception e){
			 e.printStackTrace();
			 statusCode.setExceptionEncountered(e);
			 statusCode.setResultCode(ResultCodeMapper.FAILURE);
			 return statusCode;
		 }
	 }
	

	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,String createOrUpdate)
	{  
		 	Gallary gallary = null;
	     	PartyGallery partyGallery = new PartyGallery();
		ResultStatus resultStatus = new ResultStatus();
		try {
			if (createOrUpdate.trim().equalsIgnoreCase("Update")
					&& gallaryVO.getGallaryId() != null)
				gallary = gallaryDAO.get(gallaryVO.getGallaryId());
			else
				gallary = new Gallary();
			UserGallary userGallary = null;
			gallary.setName(gallaryVO.getGallaryName());
			gallary.setDescription(gallaryVO.getDescription());
			if (createOrUpdate.trim().equalsIgnoreCase("Create")) {
				gallary.setContentType((ContentType) contentTypeDAO
						.getContentTypeByType(gallaryVO.getContentType()));
				gallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				gallary.setIsDelete(IConstants.FALSE);
			}
			gallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
			gallary.setIsPrivate(gallaryVO.getVisibility());

			gallary = gallaryDAO.save(gallary);
			partyGallery.setIsDelete(IConstants.FALSE);
			partyGallery.setIsPrivate(gallaryVO.getVisibility());
			partyGallery.setParty(partyDAO.get(gallaryVO.getCandidateId()));
			partyGallery.setGallery(gallary);
			partyGallery
					.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			partyGallery
					.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
			partyGallery = partyGalleryDAO.save(partyGallery);
			if (createOrUpdate.trim().equalsIgnoreCase("Create")) {
				userGallary = new UserGallary();
				userGallary.setGallary(gallary);
				userGallary.setRegistration(registrationDAO.get(gallaryVO
						.getUserId()));
				userGallaryDAO.save(userGallary);
			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}

	public List<SelectOptionVO> getPartyGallarySelectList(Long partyId,
			String contentType) {
		try {
			log.debug("Entered into getCandidateGallarySelectList() Method");

			List<SelectOptionVO> gallarySelectList = null;
			List<Object[]> list = partyGalleryDAO.getGallariesByPartyId(
					partyId, contentType);

			if (list != null && list.size() > 0) {
				gallarySelectList = new ArrayList<SelectOptionVO>(0);
				SelectOptionVO selectOptionVO = null;
				for (Object[] params : list) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long) params[0]);
					selectOptionVO.setName(params[1].toString());
					gallarySelectList.add(selectOptionVO);
				}
			}
			return gallarySelectList;
		} catch (Exception e) {
			log
					.error("Exception Occured in getCandidateGallarySelectList() method - "
							+ e);
			return null;
		}
	}

	public List<FileVO> getElectionType() {

		List<FileVO> retValue = new ArrayList<FileVO>();
		try {
			List<ElectionType> electionType = electionTypeDAO.getAll();
			for (ElectionType result : electionType) {
				FileVO fileVO = new FileVO();
				fileVO.setCandidateId(result.getElectionTypeId());
				fileVO.setFile(result.getElectionType());
				retValue.add(fileVO);
			}

			return retValue;
		} catch (Exception e) {
			e.printStackTrace();
			return retValue;
		}
	}

	public List<FileVO> getPartyManifestoInfo(long partyId) {
		 
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		 try {
         List<Object[]> results = partyManifestoDAO.getPartyManifestoInfo(partyId);
         	
         for (Object[] objects : results) {
	 	   	 FileVO fileVO = new FileVO();
	 	   	 fileVO.setFile(objects[0].toString());
	 	   	 fileVO.setGallaryName(objects[1].toString());
	 	   	 fileVO.setTitle(objects[2].toString());
	 	   	 fileVO.setFileDate(objects[3].toString());
	 	   	 fileVO.setIds((Long)objects[4]);
	 	   	 fileVO.setFileName1(objects[5].toString());
	 	   	 fileVO.setPath(objects[6].toString());
	 	   	 fileVO.setProblem(objects[7].toString());
	 	   	 fileVO.setLanguage(objects[8].toString());
	 	   	 fileVO.setPathOfFile(IConstants.UPLOADED_FILES+"/"+objects[5].toString());
 	   	fileVOList.add(fileVO);
 	  }
     return fileVOList;
	} catch (Exception e) {
			e.printStackTrace();
			return fileVOList;

	}

 }
	public List<FileVO> getPartyManifestoBasedOnStateIdAndPartyd(Long stateId,Long partyId){
		
		String queryStr ="";
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		if(stateId != null){
			
			queryStr = "and model.state.stateId ="+stateId+"";
		}
		List<Object[]> results = partyManifestoDAO.getPartyManifestoInfo(partyId,queryStr);
		fileVOList = setToFileVO(results);
		return fileVOList;
		
	}
	public List<FileVO> setToFileVO(List<Object[]> results) {
	
		List<FileVO> retValue = new ArrayList<FileVO>();
		for (Object[] objects : results) {
	   	 FileVO fileVO = new FileVO();
	   	 fileVO.setFile(objects[0].toString());
	   	 fileVO.setGallaryName(objects[1].toString());
	   	 fileVO.setTitle(objects[2].toString());
	   	 fileVO.setDescription(objects[3].toString());
	   	 fileVO.setFileDate(objects[4].toString());
	   	 fileVO.setIds((Long)objects[5]);
	   	 fileVO.setFileName1(objects[6].toString());
	   	 fileVO.setPath(objects[7].toString());
	   	 fileVO.setProblem(objects[8].toString());
	   	 fileVO.setLanguage(objects[9].toString());
	   	 fileVO.setPathOfFile(IConstants.UPLOADED_FILES+"/"+objects[6].toString());
	   	 retValue.add(fileVO);
	  }
    return retValue;
	}
	public List<FileVO> getSelectedState(Long partyId)
	{
		List<FileVO> fileVO = new ArrayList<FileVO>();
		try{
		List<Object[]> list = partyManifestoDAO.getSelectedState(partyId);
		for(Object[]  objects : list)
		{
			FileVO fileVO2=new FileVO();
			fileVO2.setCandidateId((Long)objects[0]);
			fileVO2.setDescription(objects[1].toString());
			fileVO.add(fileVO2);
		}
		return fileVO;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	//to upload party manifesto
	public ResultStatus uploadPartyManifesto(FileVO fileVO)
	{
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		 
		log.debug("Entered into uploadPartyManifesto() method in PartyDetailsService ");
		File file = new File();
		file.setFileName(fileVO.getName());
		file.setFileDate(dateUtilService.getCurrentDateAndTime());
		file.setFilePath(fileVO.getPath());
		file.setFileDescription(fileVO.getDescription());
		file.setFileType(fileTypeDAO.getFileType(fileVO.getContentType()).get(0));
		file.setLanguage(sourceLanguageDAO.get(fileVO.getLanguegeId()));
		
		file = fileDAO.save(file);
		
		PartyManifesto partyManifesto = new PartyManifesto();
		partyManifesto.setFile(file);
		partyManifesto.setElection(electionDAO.get(fileVO.getElectionId()));
		if(fileVO.getIds() != null)
		 partyManifesto.setParty(partyDAO.get(fileVO.getIds()));
		if(fileVO.getStateId() != null)
		 partyManifesto.setState(stateDAO.get(fileVO.getStateId()));
		
		partyManifestoDAO.save(partyManifesto);
		
		resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
	  }catch (Exception e) {
		log.error("Exception encountered in uploadPartyManifesto() method in PartyDetailsService, Check log for Details - "+e);
		resultStatus.setExceptionEncountered(e);
		resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			
	 }
		
	  return resultStatus;
  }
	public List<GallaryVO> getPartyProfileInfo(Long partyId)
	{
		
			log.debug("Entered into getPartyProfileInfo() Method");
			List<GallaryVO> gallaryVOList = new ArrayList<GallaryVO>(0);
			GallaryVO gallaryVO = new GallaryVO();
			List<Object[]> list = partyProfileDescriptionDAO.getPartyProfileInfo(partyId);
		try
		   {
			for(Object[] params:list)
			{
				gallaryVO = new GallaryVO();
				gallaryVO.setOrderNo((Long)params[0]);
				gallaryVO.setDescription(params[1].toString());
				gallaryVO.setPartyProfileDescriptionId((Long)params[2]);
				gallaryVOList.add(gallaryVO);
				
			}
			return gallaryVOList;
		}
			catch(Exception e){
				log.error("Exception Occured in getPartyProfileInfo() method - "+e);
			return gallaryVOList;
			}
			
		}
	
	public ResultStatus deleteProfileDescById(Long profDescId)
	{
		log.debug("Entered into deleteProfileDescById() Method");
		ResultStatus resultStatus=new ResultStatus();
		int flag=partyProfileDescriptionDAO.deletePartyProfileDescriptionById(profDescId);
		if(flag!=0){
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;	
		}
		else
		{
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
			
	}
	public ResultStatus updateProfileDescription(List<GallaryVO> gallaryVO , Long partyId)
	{
		log.debug("Entered into updateProfileDescription() Method");
		ResultStatus resultStatus = new ResultStatus();	
	
		try{
			Party party = partyDAO.get(partyId);
			
				for(GallaryVO params : gallaryVO)
				 {
				    PartyProfileDescription partyProfileDescription = partyProfileDescriptionDAO.get(params.getPartyProfileDescriptionId());
				    partyProfileDescription.setParty(party);
				    partyProfileDescription.setDescription(params.getDescription());
				    partyProfileDescription.setOrderNo(params.getOrderNo());
				    partyProfileDescriptionDAO.save(partyProfileDescription);
				 }
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		
			return resultStatus;
     	 }
		catch (Exception e) {
			
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in updateProfileDescription() method - "+e);
		
		 return resultStatus;
	    }
	}

	
	public GallaryVO getPartyGalleryDetails(Long galleryId, Long partyId) {
		
		GallaryVO gallaryVO = new GallaryVO();
		try {
		List<Object[]> result = partyGalleryDAO.getPartyGalleriesDescForUpdate(galleryId, partyId);
		 for(Object[] params : result){
			gallaryVO.setGallaryId(new Long(params[0].toString()));
			gallaryVO.setGallaryName(params[1].toString());
			gallaryVO.setDescription(params[2].toString());
		 }
		return gallaryVO;
		}catch (Exception e) {
			e.printStackTrace();
			return gallaryVO;
		}
	}

	public List<SelectOptionVO> getElectionTypesBasedOnStateIdAndPartyId(
			Long partyId, Long stateId) {
		
		List<SelectOptionVO> selOptionVO = new ArrayList<SelectOptionVO>();
		SelectOptionVO optionVO = new SelectOptionVO();
		List<Object[]> electionTypeObj = partyManifestoDAO.getElectionTypes(partyId,stateId);
		optionVO.setId(0l);
		optionVO.setName("Select Election Type");
		selOptionVO.add(0,optionVO);
		if(electionTypeObj !=null && electionTypeObj.size() >0){
			for(Object[] electionTypes : electionTypeObj){
				optionVO = new SelectOptionVO();
				optionVO.setId((Long) electionTypes[0]);
				optionVO.setName(electionTypes[1].toString());
				selOptionVO.add(optionVO);
			}
		}
		return selOptionVO;
	}

	
	public List<SelectOptionVO> getElectionYearsBasedOnElectionTypeIdAndPartyId(
			Long electionTypeId, Long partyId, Long stateId) {
		
		List<SelectOptionVO> selOptionVO = new ArrayList<SelectOptionVO>();
		SelectOptionVO optionVO = new SelectOptionVO();
		List<Object[]> electionYearsObj = partyManifestoDAO.getElectionYearsBasedOnElectionTypeId(electionTypeId, partyId, stateId);
		optionVO.setId(0l);
		optionVO.setName("Select Election Year");
		selOptionVO.add(0,optionVO);
		if(electionYearsObj != null && electionYearsObj.size() >0){
			for(Object[] electionYears : electionYearsObj){
				optionVO = new SelectOptionVO();
				optionVO.setId((Long) electionYears[1]);
				optionVO.setName(electionYears[0].toString());
				selOptionVO.add(optionVO);
			}
		}
		return selOptionVO;
	}

	@Override
	public List<FileVO> getPartyRelatedManifestoBasedOnYear(Long electionId,
			Long partyId, Long stateId) {
		
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		FileVO fileVO = new FileVO();
		List<File> manifestoFileObj = partyManifestoDAO.getPartyManifestoBasedOnElectionYear(electionId,partyId,stateId);
		for(File file : manifestoFileObj){
			fileVO = new FileVO();
			fileVO.setTitle(file.getFileTitle());
			fileVO.setDescription(file.getFileDescription());
			fileVO.setFileDate(file.getFileDate().toString());
			fileVO.setPathOfFile(file.getFilePath());
			fileVOList.add(fileVO);
		}
		return fileVOList;
	}

	public List<FileVO> getPartyManifestoDetailsBasedOnSelection(PartyPageVO partyPageVO) {
		
		List<FileVO> fileVOList = new ArrayList<FileVO>();
		String queryStr ="";
		try {
			if(partyPageVO.getStateId()!=null)
				queryStr+= "and model.state.stateId ="+partyPageVO.getStateId()+"";
			if(partyPageVO.getElectionTypeId()!=null)
				queryStr+= "and model.election.electionScope.electionType.electionTypeId ="+partyPageVO.getElectionTypeId()+"";
			if(partyPageVO.getElectionId()!=null)
				queryStr+= "and model.election.electionId = "+partyPageVO.getElectionId()+"";
	
		List<Object[]> partyManifestoObj = partyManifestoDAO.getPartyManifestoInfo(partyPageVO.getPartyId(), queryStr);
			fileVOList = setToFileVO(partyManifestoObj);
		
		return fileVOList;
		}
	 catch (Exception e) {
			e.printStackTrace();
			return fileVOList;
		}
	}
	
}
	

