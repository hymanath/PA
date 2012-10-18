package com.itgrids.partyanalyst.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAllianceGroupDAO;
import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.ICandidateDAO;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.IDelimitationConstituencyAssemblyDetailsDAO;
import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.IElectionTypeDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFilePathsDAO;
import com.itgrids.partyanalyst.dao.IFileSourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;
import com.itgrids.partyanalyst.dao.IMessageToPartyDAO;
import com.itgrids.partyanalyst.dao.INominationDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyElectionResultDAO;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.dao.IPartyManifestoDAO;
import com.itgrids.partyanalyst.dao.IPartyPageCustomPagesDAO;
import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.dao.IPartyUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.IStateDAO;
import com.itgrids.partyanalyst.dao.IUserDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dao.IUserPartyRelationDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.dto.CandidateElectionResultVO;
import com.itgrids.partyanalyst.dto.CustomPageVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.PartyInfoVO;
import com.itgrids.partyanalyst.dto.PartyPageVO;
import com.itgrids.partyanalyst.dto.PartyVO;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.ElectionType;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FilePaths;
import com.itgrids.partyanalyst.model.FileSourceLanguage;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.MessageToParty;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.model.PartyElectionResult;
import com.itgrids.partyanalyst.model.PartyGallery;
import com.itgrids.partyanalyst.model.PartyManifesto;
import com.itgrids.partyanalyst.model.PartyProfileDescription;
import com.itgrids.partyanalyst.model.PartyUpdatesEmail;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.model.UserPartyRelation;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

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
	private IMessageToPartyDAO messageToPartyDAO;
	private IPartyPageCustomPagesDAO partyPageCustomPagesDAO;
	private IPartyElectionResultDAO partyElectionResultDAO;
	private INominationDAO nominationDAO;
	private IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO;
	private IAllianceGroupDAO allianceGroupDAO;
	private IFileSourceLanguageDAO fileSourceLanguageDAO;
	private IFilePathsDAO filePathsDAO;
	private IUserPartyRelationDAO userPartyRelationDAO;
	private IUserDAO userDAO;
	
	public IUserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IUserPartyRelationDAO getUserPartyRelationDAO() {
		return userPartyRelationDAO;
	}

	public void setUserPartyRelationDAO(IUserPartyRelationDAO userPartyRelationDAO) {
		this.userPartyRelationDAO = userPartyRelationDAO;
	}

	public IFilePathsDAO getFilePathsDAO() {
		return filePathsDAO;
	}

	public void setFilePathsDAO(IFilePathsDAO filePathsDAO) {
		this.filePathsDAO = filePathsDAO;
	}

	public IFileSourceLanguageDAO getFileSourceLanguageDAO() {
		return fileSourceLanguageDAO;
	}

	public void setFileSourceLanguageDAO(IFileSourceLanguageDAO fileSourceLanguageDAO) {
		this.fileSourceLanguageDAO = fileSourceLanguageDAO;
	}

	public IDelimitationConstituencyAssemblyDetailsDAO getDelimitationConstituencyAssemblyDetailsDAO() {
		return delimitationConstituencyAssemblyDetailsDAO;
	}

	public void setDelimitationConstituencyAssemblyDetailsDAO(
			IDelimitationConstituencyAssemblyDetailsDAO delimitationConstituencyAssemblyDetailsDAO) {
		this.delimitationConstituencyAssemblyDetailsDAO = delimitationConstituencyAssemblyDetailsDAO;
	}

	public INominationDAO getNominationDAO() {
		return nominationDAO;
	}

	public void setNominationDAO(INominationDAO nominationDAO) {
		this.nominationDAO = nominationDAO;
	}
    public void setPartyElectionResultDAO(IPartyElectionResultDAO partyElectionResultDAO) {
		this.partyElectionResultDAO = partyElectionResultDAO;
	}

	public IPartyElectionResultDAO getPartyElectionResultDAO() {
		return partyElectionResultDAO;
	}

	public IPartyPageCustomPagesDAO getPartyPageCustomPagesDAO() {
		return partyPageCustomPagesDAO;
	}

	public void setPartyPageCustomPagesDAO(
			IPartyPageCustomPagesDAO partyPageCustomPagesDAO) {
		this.partyPageCustomPagesDAO = partyPageCustomPagesDAO;
	}

	public IMessageToPartyDAO getMessageToPartyDAO() {
		return messageToPartyDAO;
	}

	public void setMessageToPartyDAO(IMessageToPartyDAO messageToPartyDAO) {
		this.messageToPartyDAO = messageToPartyDAO;
	}

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

	public IAllianceGroupDAO getAllianceGroupDAO() {
		return allianceGroupDAO;
	}

	public void setAllianceGroupDAO(IAllianceGroupDAO allianceGroupDAO) {
		this.allianceGroupDAO = allianceGroupDAO;
	}

	public PartyVO getPartyDetails(Long partyId) {
		PartyVO partyVO = new PartyVO();
		List<String> elecType = new ArrayList<String>(0);
		List<Party> party = partyDAO.getPartyDetails(partyId);
		List electionTypesList = partyManifestoDAO.getElectionTypeBasedOnPartyId(partyId);
		for(int i=0;i<electionTypesList.size();i++)
		{
			elecType.add(electionTypesList.get(i).toString());
		}
		if (party != null) {
			for (Party party2 : party) {
				
				if(party2.getLongName().contains("PARTY"))
					partyVO.setPartyLongName(party2.getLongName());
				else
					partyVO.setPartyLongName(party2.getLongName() + " PARTY");
				
				partyVO.setAddress(party2.getAddress());
				partyVO.setPartyFlag(party2.getPartyFlag());
				partyVO.setPartyId(party2.getPartyId());
				partyVO.setPartyLogo(party2.getPartyLogo());
				partyVO.setPartyRecognization(party2.getPartyRecognization());
				partyVO.setPartyShortName(party2.getShortName());
				partyVO.setSymbol(party2.getSymbol());
				partyVO.setElectionTypes(elecType);
				
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
			//List<File> file = partyGalleryDAO.getFirstFourNewsForParty(partyId,firstResult, maxResult, queryType);
			
			List<Object[]> filesList = partyGalleryDAO.getNewsForParty(partyId,firstResult, maxResult, queryType);
			if(filesList != null && filesList.size() > 0)
			{
				for (Object[] params : filesList) 
				{
					File file = (File)params[0];
					FileVO fileVO = new FileVO();
					fileVO.setFileId((Long) file.getFileId());
					fileVO.setFileName1(file.getFileName() != null ? file.getFileName() : "");
					fileVO.setPath(file.getFilePath());
					fileVO.setFileTitle1(file.getFileTitle() != null ? file.getFileTitle() : "");
					fileVO.setFileDescription1(file.getFileDescription() != null ? file.getFileDescription(): "");
					fileVO.setSource(file.getSourceObj() != null ? file.getSourceObj().getSource() : "");
					fileVO.setLanguage(file.getLanguage() != null ? file.getLanguage().getLanguage() : "");
					fileVO.setFileDate(file.getFileDate() != null ? file.getFileDate().toString() : "");
					fileVO.setContentId((Long)params[1]);
					
					List<FileVO> fileVOSourceLanguageList = new ArrayList<FileVO>();
					 Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
					 
						 
					 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
						 FileVO fileVOSourceLanguage = new FileVO();
						 fileVOSourceLanguage.setSource(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSource():"");
						 fileVOSourceLanguage.setSourceId(fileSourceLanguage.getSource()!=null?fileSourceLanguage.getSource().getSourceId():null);
						 fileVOSourceLanguage.setLanguage(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():"");
						 fileVOSourceLanguage.setLanguegeId(fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguageId():null);
						 fileVOSourceLanguage.setFileSourceLanguageId(fileSourceLanguage.getFileSourceLanguageId());
						 
						 List<FileVO> fileVOPathsList = new ArrayList<FileVO>();
						 
						 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
						 fileVOSourceLanguage.setMultipleNews(filePathsSet.size());
						 
						 for(FilePaths filePath : filePathsSet){
							 FileVO fileVOPath = new FileVO();
							 fileVOPath.setPath(filePath.getFilePath());
							 fileVOPath.setOrderNo(filePath.getOrderNo());
							 fileVOPath.setOrderName("Part-"+filePath.getOrderNo());
							 fileVOPathsList.add(fileVOPath);
						 }
						 Collections.sort(fileVOPathsList,CandidateDetailsService.sortData);
						 fileVOSourceLanguage.setFileVOList(fileVOPathsList);
						 fileVOSourceLanguageList.add(fileVOSourceLanguage);
					 }
					 fileVO.setMultipleSource(fileVOSourceLanguageList.size());
					 Collections.sort(fileVOSourceLanguageList,CandidateDetailsService.sourceSort);
					 fileVO.setFileVOList(fileVOSourceLanguageList);
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
				
				Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
				 String filePath = null;
				 String source = null;
				 String language = null;
				 List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
				 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
				 for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList)
				 {
					 source = fileSourceLanguage.getSource()!= null?fileSourceLanguage.getSource().getSource():"";
					 language = fileSourceLanguage.getLanguage()!=null?fileSourceLanguage.getLanguage().getLanguage():"";
					 Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
					 List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
					  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
					 
					 for(FilePaths singleFilePath : filePathsList)
					 {
						 filePath = singleFilePath.getFilePath(); 
						 break;
					 }
					 if(filePath != null)
						 break;
				 }
				
				
				fileVO.setPath(filePath);
				fileVO.setTitle(file.getFileTitle());
				fileVO.setDescription(file.getFileDescription());
				fileVO.setKeywords(file.getKeywords());
				fileVO.setSource(source);
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
					String path = null;
					FileVO fileVO = new FileVO();
					List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
					
					for(File file : record)
					{
						fileVO.setFileId(file.getFileId());
						
						
						String title = "";
						
						if(file.getFileTitle() != null && file.getFileTitle().length() >= 18)
						{
							title = file.getFileTitle().substring(0, 17);
							title = title + "...";
						} 
						else
						{
						if(file.getFileTitle() != null) {
							title = file.getFileTitle();
						}
						fileVO.setTitle(title);
						Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
						List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
						 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
				    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
				    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
				    		List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
							  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
				    		for(FilePaths filePath : filePathsList){
				    			if(path != null && path.trim().length() >0)
				    				break;
				    				path = filePath.getFilePath();
				    		}
				    		if(path != null && path.trim().length() >0)
			    				break;
				    	}
				    	if(path != null && path.trim().length() >0)
		    				break;
					}
					
				}
					fileVO.setPath(path);
					
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
					String path = null;
					FileVO fileVO = new FileVO();
					List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
					for (File file : record)
					{
						if (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size() > 0L) {
							fileVO.setFileId(file.getFileId());
							fileVO.setTitle(file.getFileTitle() != null ? file.getFileTitle():"");
							Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
							List<FileSourceLanguage> fileSourceLanguageList = new ArrayList<FileSourceLanguage>(fileSourceLanguageSet);
							 Collections.sort(fileSourceLanguageList,CandidateDetailsService.fileSourceLanguageSort);
					    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageList){
					    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
					    		List<FilePaths> filePathsList = new ArrayList<FilePaths>(filePathsSet);
								  Collections.sort(filePathsList,CandidateDetailsService.filePathsSort);
					    		for(FilePaths filePath : filePathsList){
					    			if(path != null && path.trim().length() >0)
					    				break;
					    				path = filePath.getFilePath();
					    		}
					    		if(path != null && path.trim().length() >0)
				    				break;
					    	}
							
						}
						if(path != null && path.trim().length() >0)
		    				break;
					}
					fileVO.setPath(path != null ? path: "");
					
					fileVO.setGallaryId((Long) gallary[0]);
					if(type != null && type.equalsIgnoreCase(IConstants.PHOTO_GALLARY))
						fileVO.setSizeOfGallary((fileGallaryDAO.getAllRecordCountInGallary((Long)gallary[0]).get(0)));
			    	else
					    fileVO.setSizeOfGallary((long) (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size()));
					fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() : "");
					fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString(): "");
					fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString(): "");
					fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString(): "");
					retValue.add(fileVO);
				}
			}
			return retValue;
		} catch (Exception e) {
			e.printStackTrace();
			return retValue;
		}
	}

	public List<FileVO> getPartyPhotoGallaryDetail(Long partyId,int firstRecord, int maxRecord, String type) 
	{
		List<FileVO> retValue = new ArrayList<FileVO>();
		try {
			List<Object[]> results = partyGalleryDAO.getPartyGallaryDetail(partyId, firstRecord, maxRecord, type);
            
			for (Object[] gallary : results) 
			{
				String path = null;
				FileVO fileVO = new FileVO();
				List<File> record = fileGallaryDAO.getStartingRecordInGallary((Long) gallary[0]);
				for(File file : record)
				{
					fileVO.setFileId(file.getFileId());
					
					
					Set<FileSourceLanguage> fileSourceLanguageSet = file.getFileSourceLanguage();
			    	for(FileSourceLanguage fileSourceLanguage : fileSourceLanguageSet){
			    		Set<FilePaths> filePathsSet = fileSourceLanguage.getFilePaths();
			    		for(FilePaths filePath : filePathsSet){
			    			if(path != null && path.trim().length() >0)
			    				break;
			    				path = filePath.getFilePath();
			    		}
			    		if(path != null && path.trim().length() >0)
		    				break;
			    	}
			    	if(path != null && path.trim().length() >0)
	    				break;
					fileVO.setTitle(file.getFileTitle() != null ? WordUtils.capitalize(file.getFileTitle()) : "");

				}
				fileVO.setPath(path);
				fileVO.setGallaryId((Long) gallary[0]);
				fileVO.setSizeOfGallary((long) (fileGallaryDAO.getAllRecordInGallary((Long) gallary[0]).size()));
				fileVO.setGallaryName(gallary[1] != null ? WordUtils.capitalize(gallary[1].toString()) : "");
				fileVO.setGallaryDescription(gallary[2] != null ? WordUtils.capitalize(gallary[2].toString()) : "");
				fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() : "");
				fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() : "");
				retValue.add(fileVO);
			}
			return retValue;
		} catch (Exception e) {
			log.error("Exception Occured, Please Check For Log Details - "+e);
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
			 List<Object> PartyUpdatesEmails= partyUpdatesEmailDAO.getPartyUpdatesEmail(emailId,partyId);
			 if(!(PartyUpdatesEmails.size() >0))
			 {
				 DateUtilService dateservice=new DateUtilService();
				 partyUpdatesEmail.setEmail(emailId);
				 partyUpdatesEmail.setParty(partyDAO.get(partyId));
				 partyUpdatesEmail.setUnsubscribed("false");
				 partyUpdatesEmail.setSubscribedDate(dateservice.getCurrentDateAndTime());
				 partyUpdatesEmailDAO.save(partyUpdatesEmail);
			 }
			 else
			 {
				 statusCode.setExceptionMsg("You have already subscribed for this party"); 
			 }
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
			if (createOrUpdate.trim().equalsIgnoreCase("Create")) {
			partyGallery.setIsDelete(IConstants.FALSE);
			partyGallery.setIsPrivate(gallaryVO.getVisibility());
			partyGallery.setParty(partyDAO.get(gallaryVO.getCandidateId()));
			partyGallery.setGallery(gallary);
			partyGallery
					.setCreatedDate(dateUtilService.getCurrentDateAndTime());
			partyGallery
					.setUpdatedDate(dateUtilService.getCurrentDateAndTime());
			partyGallery = partyGalleryDAO.save(partyGallery);
			}
			if (createOrUpdate.trim().equalsIgnoreCase("Create")) {
				userGallary = new UserGallary();
				userGallary.setGallary(gallary);
				userGallary.setUserId(gallaryVO.getUserId());
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
	 	     fileVO.setProblem(objects[6].toString());
	 	     
	 	    List<Object> filePath = filePathsDAO.getFilePaths(fileVO.getIds());
	 	     if(filePath != null && filePath.size() > 0)
	 	     {
	 	    	fileVO.setPath(filePath.get(0).toString());
	 	    	fileVO.setPathOfFile(filePath.get(0).toString());
	 	     }
	 	    List<Object> fileLanguage = fileSourceLanguageDAO.getFileLanguage(fileVO.getIds());
	 	    if(fileLanguage != null && fileLanguage.size() > 0)
	 	    	fileVO.setLanguage(fileLanguage.get(0).toString());
	 	   	 
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
	   	 fileVO.setProblem(objects[7].toString());
	   	 List<Object> filePath = filePathsDAO.getFilePaths(fileVO.getIds());
	   	 if(filePath != null && filePath.size() > 0)
	   	 {
	   		 fileVO.setPath(filePath.get(0).toString());
	   		 fileVO.setPathOfFile(filePath.get(0).toString());
	   	 }
	   	 List<Object> fileLanguage = fileSourceLanguageDAO.getFileLanguage(fileVO.getIds());
	   	 if(fileLanguage != null && fileLanguage.size() > 0)
	   		 fileVO.setLanguage(fileLanguage.get(0).toString());
	   	 
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
		FileSourceLanguage fileSourceLanguage = new FileSourceLanguage();
		FilePaths filePaths = new FilePaths();
		Long orderNo=0l;;
		file.setFileName(fileVO.getName());
		file.setFileDate(dateUtilService.getCurrentDateAndTime());
		file.setFileDescription(fileVO.getDescription());
		
		file = fileDAO.save(file);
		
		fileSourceLanguage.setFile(file);
		fileSourceLanguage.setSource(null);
		fileSourceLanguage.setLanguage(sourceLanguageDAO.get(fileVO.getLanguegeId()));
		fileSourceLanguage = fileSourceLanguageDAO.save(fileSourceLanguage);
		
		filePaths.setFilePath(fileVO.getPath());
		filePaths.setFileType(fileTypeDAO.getFileType(fileVO.getContentType()).get(0));
		List<Object> maxOrderNo = filePathsDAO.getMaxOrderNo();
		if(maxOrderNo.size()==0 && maxOrderNo.get(0)==null)
			orderNo = orderNo + 1L;
		else
		 orderNo = new Long (filePathsDAO.getMaxOrderNo().get(0).toString());
		
		filePaths.setOrderNo(orderNo);
		filePaths.setFileSourceLanguage(fileSourceLanguage);
		filePathsDAO.save(filePaths);
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
			gallaryVO.setDescription(params[2].toString().replaceAll("\\s+", " "));
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
	
	
	public ResultStatus savePartyMessageFromPeople(GallaryVO gallaryVO)
	{
		log.debug("Entered Into savePartyMessageFromPeople() Method");
		ResultStatus resultStatus = new ResultStatus();
		
		try
		{
			if(gallaryVO != null)
			{
				MessageToParty messageToParty = new MessageToParty();
				
				messageToParty.setParty(partyDAO.get(gallaryVO.getPartyId()));
				messageToParty.setConstituency(constituencyDAO.get(gallaryVO.getUserId()));
				messageToParty.setName(gallaryVO.getGallaryName());
				messageToParty.setIsApproved(IConstants.FALSE);
				messageToParty.setIsDelete(IConstants.FALSE);
				messageToParty.setMessage(gallaryVO.getDescription());
				messageToParty.setSentTime(dateUtilService.getCurrentDateAndTime());
				messageToParty.setIsPrivate(gallaryVO.getIsPrivate());
				
				messageToPartyDAO.save(messageToParty);
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}
		}catch (Exception e) {
			log.error("Exception occured in savePartyMessageFromPeople() Method - "+e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
		
		return null;
	}
	public List<CandidateCommentsVO> getMessageToParty(Long partyId,int startIndex,int resultsCount)
	{
		List<CandidateCommentsVO> userlist = new ArrayList<CandidateCommentsVO>(0);

		try
		{
			if(log.isDebugEnabled())
				   log.debug("entered into getMessageToParty()in CandidateDetailsService");
			List<Long> messCount = messageToPartyDAO.getPartyMessageCount(partyId);
			List<Object[]> list= messageToPartyDAO.getMessageToParty(partyId,startIndex,resultsCount);
			if(list!=null && list.size()>0)
			{
				CandidateCommentsVO candidateCommentsVO = null;
				 for(Object[] params:list)
                 {
					 candidateCommentsVO = new CandidateCommentsVO();
					 
					 candidateCommentsVO.setUserName(params[0].toString());
                	 candidateCommentsVO.setMessage(params[1].toString());
                	 candidateCommentsVO.setConstituency(params[2].toString());
                	 candidateCommentsVO.setTime(params[3].toString().substring(0,19));
                	 candidateCommentsVO.setConsituencyId((Long)params[4]);
                	 candidateCommentsVO.setTotalResultsCount(messCount.get(0));
                	 userlist.add(candidateCommentsVO);
                 }
			}
			
		}
		catch(Exception e)
		{
			log.error("Exception in getMessageToParty()of CandidateDetailsService",e);
		}
		return userlist;
	}
	
	
	public String getPartyVisibility(Long gallaryId)
	{
		log.debug("Entered into getPartyVisibility() method");
		List<Object> result = partyGalleryDAO.getPartyVisibility(gallaryId);
		try
		{
			return result.get(0).toString();
		}
		catch(Exception e)
		{
			log.error("Exception occured in getPartyVisibility() method of PartyDetailsService");
			return result.get(0).toString();
		}
	}
	
	public List<CustomPageVO> getCustomPagesOfAPartyPage(Long partyId)
    {
    	try{
			List<CustomPageVO> customPages = null;
			List<Object[]> list = partyPageCustomPagesDAO.getCustomPagesOfAPartyPage(partyId);
			
			if(list != null && list.size() > 0)
			{
				customPages = new ArrayList<CustomPageVO>(0);
				for(Object[] params : list)
					customPages.add(new CustomPageVO(IConstants.CUSTOM_JSP_PAGES_PATH+"/"+params[0].toString(),params[1].toString()));
			}
			return customPages;
		}catch (Exception e) {
			log.error("Exception Occured in getCustomPagesOfAPartyPage(), Exception is - "+e);
			return null;
		}
    }
	
	public Map<String,List<PartyInfoVO>> getPartyElectionResults(Long partyId,boolean includeAlliances,boolean includeBielections)
	{
	 try
	  {
		Map<String,List<PartyInfoVO>> resultMap = new HashMap<String, List<PartyInfoVO>>();
		List<PartyInfoVO> partyInfoList = new ArrayList<PartyInfoVO>();
		List<PartyElectionResult> electionResultList = partyElectionResultDAO.getPartyElectionResultsBasedOnPartyId(partyId,IConstants.PARLIAMENT_ELECTION_TYPE,includeBielections);
		partyInfoList = getPartyElectionProfile(electionResultList,includeAlliances);
		resultMap.put("Parliament", partyInfoList);
		electionResultList = partyElectionResultDAO.getPartyElectionResultsBasedOnPartyId(partyId,IConstants.ASSEMBLY_ELECTION_TYPE,includeBielections);
		partyInfoList = getPartyElectionProfile(electionResultList,includeAlliances);
		resultMap.put("Assembly", partyInfoList);
		return resultMap;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public List<PartyInfoVO> getPartyElectionProfile(List<PartyElectionResult> electionResultList,boolean includeAlliances)
	{
		List<PartyInfoVO> partyInfoList = new ArrayList<PartyInfoVO>();
		PartyInfoVO partyInfoVO = new PartyInfoVO();
		DecimalFormat dformat = new DecimalFormat("##.00");
	 try
		{
		 if(log.isDebugEnabled())
			 log.debug("Entered Into getPartyElectionProfile().........");
		  if(electionResultList !=null && electionResultList.size()>0)
		  {
		   for(PartyElectionResult partyElectionResult :electionResultList)
			 {
				partyInfoVO = new PartyInfoVO();
				partyInfoVO.setPartyId(partyElectionResult.getParty().getPartyId());
				partyInfoVO.setPartyShortName(partyElectionResult.getParty().getShortName());
				partyInfoVO.setElectionTypeId(partyElectionResult.getElection().getElectionScope().getElectionType().getElectionTypeId());
				if(partyElectionResult.getElection().getElectionScope().getState()!=null)
				{
				partyInfoVO.setStateId(partyElectionResult.getElection().getElectionScope().getState().getStateId());
				partyInfoVO.setStateName(partyElectionResult.getElection().getElectionScope().getState().getStateName());
			    }
				partyInfoVO.setElectionId(partyElectionResult.getElection().getElectionId());
				partyInfoVO.setElectionYear(partyElectionResult.getElection().getElectionYear());
				partyInfoVO.setElectionType(partyElectionResult.getElection().getElectionScope().getElectionType().getElectionType());
				partyInfoVO.setElectionScopeId(partyElectionResult.getElection().getElectionScope().getElectionScopeId());
				partyInfoVO.setPartyTotalVotes(partyElectionResult.getTotalValidVotes().longValue());
				partyInfoVO.setSeatsParticipated(new Long(partyElectionResult.getTotalConstiParticipated()));
				partyInfoVO.setSeatsWin(new Long(partyElectionResult.getTotalSeatsWon()));
				partyInfoVO.setPercentageOfVotes(new BigDecimal(partyElectionResult.getCompleteVotesPercent()));
				partyInfoVO.setParticipatedPercentage(new BigDecimal(partyElectionResult.getVotesPercentage()));				
				
			if(includeAlliances)
			{	
				List allianceList = allianceGroupDAO.findAlliancePartiesByElectionAndPartyExcludeParty(partyElectionResult.getElection().getElectionId(), partyElectionResult.getParty().getPartyId());
				Double totalVotesGained = partyElectionResult.getTotalVotesGained();
				Double totalValidVotes = partyElectionResult.getTotalValidVotes();
				 for(int i=0;i<allianceList.size();i++)
			      {
				   Object[] allianceParty = (Object[])allianceList.get(i);
				   
				    List<PartyElectionResult> electionResult = partyElectionResultDAO.getPartyElectionResultsBasedOnPartyIdAndElecId((Long)allianceParty[1],IConstants.ASSEMBLY_ELECTION_TYPE,partyElectionResult.getElection().getElectionId());
				   if(electionResult.size() > 0)
				   {
					  Long constiValue =  ((partyInfoVO.getSeatsParticipated()).longValue() + (new Long(electionResult.get(0).getTotalConstiParticipated())).longValue());
					  partyInfoVO.setSeatsParticipated(constiValue);
				     Long seatsValue = (partyInfoVO.getSeatsWin()).longValue() + (new Long(electionResult.get(0).getTotalSeatsWon())).longValue();
				     partyInfoVO.setSeatsWin(seatsValue);
				     totalVotesGained = totalVotesGained+electionResult.get(0).getTotalVotesGained();
				     totalValidVotes = totalValidVotes+electionResult.get(0).getTotalValidVotes();
				   }
			     }
				 //partyElectionResult.setTotalVotesGained(totalVotesGained);
				 partyInfoVO.setPartyTotalVotes(totalValidVotes.longValue());
				 Double completeVotesperc = ((totalVotesGained/partyElectionResult.getCompleteConstiValidVotes())*100);
				 Double votesPerc =  ((totalVotesGained/totalValidVotes)*100);
				 partyInfoVO.setPercentageOfVotes(new BigDecimal(dformat.format(completeVotesperc)));
				 partyInfoVO.setParticipatedPercentage(new BigDecimal(dformat.format(votesPerc)));
			}
				
								
				partyInfoList.add(partyInfoVO);
			}
		  }
	 }catch (Exception e) {
		 log.error("Exception Occured  In getPartyElectionProfile()........."+e);
		e.printStackTrace();
	}
		return partyInfoList;
		
	}
	
	
	public CandidateElectionResultVO getCandidateDetailsForAsses(Long candidateId,Long electionId)
	{
		try{
			CandidateElectionResultVO candidateElectionResultVO = new CandidateElectionResultVO();
			Election election = electionDAO.get(electionId);
			
			candidateElectionResultVO.setCandidateId(candidateId);
			List<Object[]> list = nominationDAO.getCandidatePertcipatedConstituenciesInAElection(candidateId,electionId);
			
			if(list != null && list.size() > 0)
			{
				candidateElectionResultVO.setConstituencyId((Long)list.get(0)[0]);
				candidateElectionResultVO.setConstituencyName(list.get(0)[1].toString());
				candidateElectionResultVO.setState((Long)list.get(0)[2]);
				
			}
			
			if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE))
			{
				List<Object[]> parList = delimitationConstituencyAssemblyDetailsDAO.findLatestParliamentForAssembly(candidateElectionResultVO.getConstituencyId());
				if(parList != null && parList.size() > 0)
				{
					candidateElectionResultVO.setPartyId((Long)parList.get(0)[0]);
					candidateElectionResultVO.setPartyName(parList.get(0)[1].toString());
				}
			}
			else if(election.getElectionScope().getElectionType().getElectionType().equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE))
			{
				candidateElectionResultVO.setPartyId((Long)list.get(0)[0]);
				candidateElectionResultVO.setPartyName(list.get(0)[1].toString());
			}
			
			return candidateElectionResultVO;
		}catch (Exception e) {
			log.error("Exception occured in getCandidateDetailsForAsses() Method, Exception is - "+e);
			return null;
		}
	}
	
	public List<SelectOptionVO> getStateDetails(Long partyId)
	{
		List<SelectOptionVO> returnValue = new ArrayList<SelectOptionVO>();
		if(log.isDebugEnabled())
			 log.debug("Entered Into getStateDetails() of PartyDetailsService ");
		try
		{
		  List<Object[]> stateDetailsList = nominationDAO.getStateDetails(partyId);
		  returnValue = convertToSelectOptionVO(returnValue,stateDetailsList);
		}
		catch(Exception e)
		{
			log.error("Exception occured in getStateDetails() Method  of PartyDetailsService , Exception is - "+e);
		}
		
		return returnValue;
	}
	
	public List<SelectOptionVO> convertToSelectOptionVO(List<SelectOptionVO> returnValue,List<Object[]> data)
	{
		SelectOptionVO selectOptionVO = null;
		for(Object[] values: data)
		{
			selectOptionVO = new SelectOptionVO();
			selectOptionVO.setId((Long)values[0]);
			selectOptionVO.setName(values[1] != null?values[1].toString():"");
			returnValue.add(selectOptionVO);
		}
		
		return returnValue;
	}
	
	public List<SelectOptionVO> getElecYears(Long partyId,Long electionType,Long stateId)
	{
		List<SelectOptionVO> returnValue = new ArrayList<SelectOptionVO>();
		if(log.isDebugEnabled())
			 log.debug("Entered Into getElecYears() of PartyDetailsService ");
		try
		{
		  List<Object[]> stateDetailsList = nominationDAO.getElecYears(partyId,electionType,stateId);
		  returnValue = convertToSelectOptionVO(returnValue,stateDetailsList);
		}
		catch(Exception e)
		{
			log.error("Exception occured in getElecYears() Method  of PartyDetailsService , Exception is - "+e);
		}
		
		return returnValue;
	}
	
	public List<SelectOptionVO> getCandidateDetailsForAParty(Long partyId,Long electionId)
	{
		List<SelectOptionVO> returnValue = new ArrayList<SelectOptionVO>();
		if(log.isDebugEnabled())
			 log.debug("Entered Into getCandidateDetailsForAParty() of PartyDetailsService ");
		try
		{
		  List<Object[]> stateDetailsList = nominationDAO.getCandidateDetailsForAParty(partyId,electionId);
		  returnValue = convertToSelectOptionVO(returnValue,stateDetailsList);
		}
		catch(Exception e)
		{
			log.error("Exception occured in getCandidateDetailsForAParty() Method  of PartyDetailsService , Exception is - "+e);
		}
		
		return returnValue;
	}
	public List<SelectOptionVO> getAllPartysNamesByUser(Long userId) {
		try {
			log.debug("Entered into getAllPartysNamesByUser() Method");

			List<SelectOptionVO> partySelectList = new ArrayList<SelectOptionVO>(0);
			//List<Object[]> list = partyDAO.findAllPartyNames();
			List<Object[]> list = userPartyRelationDAO.getPartiesByUser(userId);

			if (list != null && list.size() > 0) {
				
				SelectOptionVO selectOptionVO = null;
				for (Object[] params : list) {
					selectOptionVO = new SelectOptionVO();
					selectOptionVO.setId((Long) params[0]);
					selectOptionVO.setName(params[1].toString());
					partySelectList.add(selectOptionVO);
				}
			}
			return partySelectList;
		} catch (Exception e) {
			log
					.error("Exception Occured in  getAllPartysNamesByUser() method - "
							+ e);
			return null;
		}
	}
	public ResultStatus saveDataToUserPartyRelation(Long userId,Long partyId)
	{
	  ResultStatus resultStatus = new ResultStatus();
	  try{
		  if(log.isDebugEnabled())
				 log.debug("Entered Into saveDataToUserPartyRelation() of PartyDetailsSer");
		  
		  UserPartyRelation userPartyRelation = new UserPartyRelation();
		 Long object = userPartyRelationDAO.checkPartyForUser(userId,partyId);
		  if(object == null || (object != null && object.longValue()==0l))
		  {
		  userPartyRelation.setUser(userDAO.get(userId));
		  userPartyRelation.setParty(partyDAO.get(partyId));
		  
		  userPartyRelationDAO.save(userPartyRelation);
		  resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
		  }
		  else {
			  resultStatus.setResultCode(ResultCodeMapper.DATA_NOT_FOUND);
		  }
		 
		  
	  }catch(Exception e)
	  {
		  log.error("Exception occured in saveDataToUserPartyRelation() Method in partyDetailsService - "+e);
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
		  
	  }
	  return resultStatus;
	}
}
	

