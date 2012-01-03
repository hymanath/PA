package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IAssemblyLocalElectionBodyDAO;
import com.itgrids.partyanalyst.dao.IContentTypeDAO;
import com.itgrids.partyanalyst.dao.IFileDAO;
import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IFileTypeDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IRegionScopesDAO;
import com.itgrids.partyanalyst.dao.IRegistrationDAO;
import com.itgrids.partyanalyst.dao.ISourceDAO;
import com.itgrids.partyanalyst.dao.ISourceLanguageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageDescriptionDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageUpdatesEmailDAO;
import com.itgrids.partyanalyst.dao.IUserGallaryDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.dto.ResultStatus;
import com.itgrids.partyanalyst.dto.ResultCodeMapper;
import com.itgrids.partyanalyst.dto.SelectOptionVO;
import com.itgrids.partyanalyst.dto.SpecialPageVO;

import com.itgrids.partyanalyst.model.ContentType;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.model.Gallary;
import com.itgrids.partyanalyst.model.PartyGallery;
import com.itgrids.partyanalyst.model.Source;
import com.itgrids.partyanalyst.model.SourceLanguage;
import com.itgrids.partyanalyst.model.SpecialPage;
import com.itgrids.partyanalyst.model.SpecialPageDescription;
import com.itgrids.partyanalyst.model.SpecialPageGallery;
import com.itgrids.partyanalyst.model.SpecialPageUpdatesEmail;
import com.itgrids.partyanalyst.model.UserGallary;
import com.itgrids.partyanalyst.service.ISpecialPageService;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;


public class SpecialPageService implements ISpecialPageService {
	//log object creation
	public static final Logger log = Logger.getLogger(SpecialPageService.class);
	private ISpecialPageDAO specialPageDAO;
	//declare DAO class reference variable as property variable
	private ISpecialPageDescriptionDAO specialPageDescriptionDAO;
	private ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO;
	private SpecialPageVO specialPageVO;
	private SpecialPageDescription specialPageDescription;
	private IGallaryDAO gallaryDAO;
	private SpecialPageGallery specialPageGallery;
	private IContentTypeDAO contentTypeDAO;
	private DateUtilService dateUtilService = new DateUtilService();
	private IRegistrationDAO registrationDAO;
	private IUserGallaryDAO userGallaryDAO;
	private ISpecialPageGalleryDAO specialPageGalleryDAO;
	private ISourceDAO sourceDAO;
	private Source source;
	private SelectOptionVO selectOptionVO;
	private ISourceLanguageDAO sourceLanguageDAO;
	private SourceLanguage sourceLanguage;
	private FileVO fileVO;
	private IFileDAO fileDAO;
	private IFileTypeDAO fileTypeDAO;
	private IRegionScopesDAO regionScopesDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO;
	private List<SelectOptionVO> gallarySelectList;

	public List<SelectOptionVO> getGallarySelectList() {
		return gallarySelectList;
	}

	public void setGallarySelectList(List<SelectOptionVO> gallarySelectList) {
		this.gallarySelectList = gallarySelectList;
	}

	public IAssemblyLocalElectionBodyDAO getAssemblyLocalElectionBodyDAO() {
		return assemblyLocalElectionBodyDAO;
	}

	public void setAssemblyLocalElectionBodyDAO(
			IAssemblyLocalElectionBodyDAO assemblyLocalElectionBodyDAO) {
		this.assemblyLocalElectionBodyDAO = assemblyLocalElectionBodyDAO;
	}

	public IFileDAO getFileDAO() {
		return fileDAO;
	}

	public void setFileDAO(IFileDAO fileDAO) {
		this.fileDAO = fileDAO;
	}

	public IFileTypeDAO getFileTypeDAO() {
		return fileTypeDAO;
	}

	public void setFileTypeDAO(IFileTypeDAO fileTypeDAO) {
		this.fileTypeDAO = fileTypeDAO;
	}

	public IRegionScopesDAO getRegionScopesDAO() {
		return regionScopesDAO;
	}

	public void setRegionScopesDAO(IRegionScopesDAO regionScopesDAO) {
		this.regionScopesDAO = regionScopesDAO;
	}

	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}

	public FileVO getFileVO() {
		return fileVO;
	}

	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}

	public SourceLanguage getSourceLanguage() {
		return sourceLanguage;
	}

	public void setSourceLanguage(SourceLanguage sourceLanguage) {
		this.sourceLanguage = sourceLanguage;
	}

	public ISourceLanguageDAO getSourceLanguageDAO() {
		return sourceLanguageDAO;
	}

	public void setSourceLanguageDAO(ISourceLanguageDAO sourceLanguageDAO) {
		this.sourceLanguageDAO = sourceLanguageDAO;
	}

	public SelectOptionVO getSelectOptionVO() {
		return selectOptionVO;
	}

	public void setSelectOptionVO(SelectOptionVO selectOptionVO) {
		this.selectOptionVO = selectOptionVO;
	}

	public void setSource(Source source) {
		this.source = source;
	}

	public ISourceDAO getSourceDAO() {
		return sourceDAO;
	}

	public void setSourceDAO(ISourceDAO sourceDAO) {
		this.sourceDAO = sourceDAO;
	}

	public ISpecialPageGalleryDAO getSpecialPageGalleryDAO() {
		return specialPageGalleryDAO;
	}

	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
	}

	public IUserGallaryDAO getUserGallaryDAO() {
		return userGallaryDAO;
	}

	public void setUserGallaryDAO(IUserGallaryDAO userGallaryDAO) {
		this.userGallaryDAO = userGallaryDAO;
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

	public SpecialPageGallery getSpecialPageGallery() {
		return specialPageGallery;
	}

	public void setSpecialPageGallery(SpecialPageGallery specialPageGallery) {
		this.specialPageGallery = specialPageGallery;
	}

	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}

	public SpecialPageDescription getSpecialPageDescription() {
		return specialPageDescription;
	}

	public void setSpecialPageDescription(
			SpecialPageDescription specialPageDescription) {
		this.specialPageDescription = specialPageDescription;
	}

	public SpecialPageVO getSpecialPageVO() {
		return specialPageVO;
	}

	public void setSpecialPageVO(SpecialPageVO specialPageVO) {
		this.specialPageVO = specialPageVO;
	}

	public ISpecialPageDAO getSpecialPageDAO() {
		return specialPageDAO;
	}

	public void setSpecialPageDAO(ISpecialPageDAO specialPageDAO) {
		this.specialPageDAO = specialPageDAO;
	}

	public ISpecialPageUpdatesEmailDAO getSpecialPageUpdatesEmailDAO() {
		return specialPageUpdatesEmailDAO;
	}

	public void setSpecialPageUpdatesEmailDAO(
			ISpecialPageUpdatesEmailDAO specialPageUpdatesEmailDAO) {
		this.specialPageUpdatesEmailDAO = specialPageUpdatesEmailDAO;
	}
	//getter&setter methods for reference variables

	public ISpecialPageDescriptionDAO getSpecialPageDescriptionDAO() {
		return specialPageDescriptionDAO;
	}

	public void setSpecialPageDescriptionDAO(
			ISpecialPageDescriptionDAO specialPageDescriptionDAO) {
		this.specialPageDescriptionDAO = specialPageDescriptionDAO;
	}

	//implementations of declaration reference variable
	public List<String> getSpecialPageDescription(Long specialPageId)
	{
	 	List<String> descList = new ArrayList<String>(0); 
	   try{
			log.debug("Entered into getSpecialPageDescription() Method");
		     List<Object> results = specialPageDescriptionDAO.getSpecialPageDescription(specialPageId);
		 
		   if(results != null && results.size() >0)
		   {
			  
			   for(Object desc :results)
				 descList.add(desc.toString());
			   
		   }
		  return descList;
	   }catch(Exception e){
		 log.error("Exception Occured in getCandidateProfileDescriptionByCandidateID() method - "+e);
		 return descList;
		 }
	}
	public ResultStatus subScribeEmailAlertForAEvent(String emailId,
			Long specialPageId) {

		log
				.debug("Entered into subScribeEmailAlertForAEvent() method in SpecialPageService");

		ResultStatus statusCode = new ResultStatus();
		try {
			SpecialPageUpdatesEmail specialPageUpdatesEmail = new SpecialPageUpdatesEmail();
			specialPageUpdatesEmail.setEmail(emailId);

			specialPageUpdatesEmail.setSpecialPage(specialPageDAO
					.get(specialPageId));
			specialPageUpdatesEmail.setUnsubscribed("false");

			specialPageUpdatesEmailDAO.save(specialPageUpdatesEmail);
			statusCode.setResultCode(ResultCodeMapper.SUCCESS);
			return statusCode;

		} catch (Exception e) {
			log
					.error("Exception encountered in subScribeEmailAlertForAEvent() method in SpecialPageService, Check log for Details - "
							+ e);
			e.printStackTrace();
			statusCode.setExceptionEncountered(e);
			statusCode.setResultCode(ResultCodeMapper.FAILURE);
			return statusCode;
		}
	}

	public List<SpecialPageVO> getEventProfileInfo(Long specialPageId) {
		log
				.debug("Entered into getEventProfileInfo() method in  SpecialPageService");
		List<SpecialPageVO> specialPageList = new ArrayList<SpecialPageVO>(0);
		specialPageVO = new SpecialPageVO();

		List<Object[]> list = specialPageDescriptionDAO
				.getEventDescription(specialPageId);
		try {
			for (Object[] params : list) {
				specialPageVO = new SpecialPageVO();
				specialPageVO.setOrderNo((Long) params[0]);
				specialPageVO.setDescription(params[1].toString());
				specialPageVO.setSpecialPageDescriptionId((Long) params[2]);
				specialPageList.add(specialPageVO);

			}
			return specialPageList;
		} catch (Exception e) {
			log.error("Exception Occured in getEventProfileInfo() method - "
					+ e);
			return specialPageList;
		}
	}

	public ResultStatus deleteEventProfileDescById(Long specialPageDescriptionId) {
		log
				.debug("Entered into deleteEventProfileDescById() method in SpecialPageService");
		ResultStatus resultStatus = new ResultStatus();
		int flag = specialPageDescriptionDAO
				.deleteEventProfileDescriptionById(specialPageDescriptionId);
		if (flag != 0) {
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} else {
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}

	}

	public ResultStatus saveDescription(SpecialPageVO specialPageVO) {
		ResultStatus resultStatus = new ResultStatus();

		specialPageDescription = new SpecialPageDescription();
		try {
			Long orderNo;

			List<Object> results = specialPageDescriptionDAO
					.getMaxOrderNo(specialPageVO.getSpecialPageId());
			orderNo = results.get(0) == null ? 0l : (Long) results.get(0);
			orderNo = orderNo + 1;
			specialPageDescription.setOrderNo(orderNo);
			specialPageDescription.setDescription(specialPageVO
					.getDescription());
			specialPageDescription.setSpecialPage(specialPageDAO
					.get(specialPageVO.getSpecialPageId()));
			specialPageDescriptionDAO.save(specialPageDescription);
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;
		} catch (Exception e) {
			resultStatus.setExceptionEncountered(e);
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			log.error("Exception Occured in saveDescription() method - " + e);
			return resultStatus;
		}
	}

	public ResultStatus updateEventProfileDescripton(
			List<SpecialPageVO> specialPageVO, Long specialPageId) {
		log
				.debug("Entered into updateEventProfileDescripton() in specialPageService");
		ResultStatus resultStatus = new ResultStatus();
		SpecialPage specialPage = specialPageDAO.get(specialPageId);
		try {
			for (SpecialPageVO params : specialPageVO) {
				specialPageDescription = specialPageDescriptionDAO.get(params
						.getSpecialPageDescriptionId());
				specialPageDescription.setSpecialPage(specialPage);
				specialPageDescription.setOrderNo(params.getOrderNo());
				specialPageDescription.setDescription(params.getDescription());
				specialPageDescriptionDAO.save(specialPageDescription);

			}
			resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
			return resultStatus;

		} catch (Exception e) {
			e.printStackTrace();
			resultStatus.setResultCode(ResultCodeMapper.FAILURE);
			return resultStatus;
		}
	}

	public ResultStatus createNewGallaryOrUpdateGallary(GallaryVO gallaryVO,
			String createOrUpdate) {
		Gallary gallary = null;

		specialPageGallery = new SpecialPageGallery();
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
			specialPageGallery.setIsDelect(IConstants.FALSE);
			// specialPageGallery.setIsPrivate(gallaryVO.getVisibility());
			specialPageGallery.setSpecialPage(specialPageDAO.get(gallaryVO
					.getCandidateId()));
			specialPageGallery.setGallary(gallary);
			specialPageGallery.setCreatedDate(dateUtilService
					.getCurrentDateAndTime());
			specialPageGallery.setUpdatedDate(dateUtilService
					.getCurrentDateAndTime());
			specialPageGallery = specialPageGalleryDAO.save(specialPageGallery);
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

	public List<SelectOptionVO> getSource() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>(0);
		try {
			List<Source> source = sourceDAO.getAll();

			
			for (Source params : source) {
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(params.getSourceId());
				selectOptionVO.setName(params.getSource());
				selectOptionList.add(selectOptionVO);

			}
			return selectOptionList;
		} catch (Exception e) {
			e.printStackTrace();
			return selectOptionList;
		}
	}

	public List<SelectOptionVO> getLanguage() {
		List<SelectOptionVO> selectOptionList = new ArrayList<SelectOptionVO>();
		try {
			
			List<SourceLanguage> sourceLanguage = sourceLanguageDAO.getAll();
			for (SourceLanguage params : sourceLanguage) {
				selectOptionVO = new SelectOptionVO();
				selectOptionVO.setId(params.getLanguageId());
				selectOptionVO.setName(params.getLanguage());
				selectOptionList.add(selectOptionVO);
			}
			return selectOptionList;

		} catch (Exception e) {
			e.printStackTrace();
			return selectOptionList;
		}
	}
	
	public ResultStatus uploadAFile(FileVO fileVO){
		
			ResultStatus resultStatus = new ResultStatus();
			try{
				log.debug("Entered into uploadAFile() method in Candidate Details Service()");
				
				File file = new File();
				FileGallary fileGallary = new FileGallary();
				SimpleDateFormat sdf = new SimpleDateFormat(IConstants.DATE_PATTERN);
				file.setFileName(fileVO.getName());
				file.setFilePath(fileVO.getPath());
				file.setFileType(fileTypeDAO.getFileType(fileVO.getContentType()).get(0));
				file.setFileTitle(fileVO.getTitle());
				file.setFileDescription(fileVO.getDescription());
				file.setKeywords(fileVO.getKeywords());
				
				if(fileVO.getLanguegeId() != null && fileVO.getLanguegeId() > 0)
					file.setLanguage(sourceLanguageDAO.get(fileVO.getLanguegeId()));
				if(fileVO.getSourceId() != null && fileVO.getSourceId() > 0)
					file.setSourceObj(sourceDAO.get(fileVO.getSourceId()));
				
				if(fileVO.getLocationScope() != null && fileVO.getLocationScope().longValue() > 0 &&
						fileVO.getLocationValue() != null && Integer.parseInt(fileVO.getLocationValue()) > 0)
				{
					file.setRegionScopes(regionScopesDAO.get(fileVO.getLocationScope()));
					file.setLocationValue(getLocationScopeValue(fileVO.getLocationScope(),fileVO.getLocationValue()));
				}
				
				if(fileVO.getFileDate() != null && fileVO.getFileDate().length() > 0)
					file.setFileDate(sdf.parse(fileVO.getFileDate()));
				
				file = fileDAO.save(file);
				
				fileGallary.setGallary(gallaryDAO.get(fileVO.getGallaryId()));
				fileGallary.setFile(file);
				fileGallary.setCreatedDate(dateUtilService.getCurrentDateAndTime());
				fileGallary.setUpdateddate(dateUtilService.getCurrentDateAndTime());
				fileGallary.setIsDelete(IConstants.FALSE);
				
				if(fileVO.getVisibility().equalsIgnoreCase("public"))
					fileGallary.setIsPrivate(IConstants.FALSE);
				else
					fileGallary.setIsPrivate(IConstants.TRUE);
				
				fileGallaryDAO.save(fileGallary);
				
				resultStatus.setResultCode(ResultCodeMapper.SUCCESS);
				return resultStatus;
			}catch (Exception e) {
				log.error("Exception encountered, Check log for Details - "+e);
				resultStatus.setExceptionEncountered(e);
				resultStatus.setResultCode(ResultCodeMapper.FAILURE);
				return resultStatus;
			}
		}
		
	public Long getLocationScopeValue(Long scope,String locationValue)
	{
		try{
			int scopeValue = scope.intValue();
			
			if(scopeValue == 1 || scopeValue == 2 || scopeValue == 3 || scopeValue == 4 || scopeValue == 9)
				return Long.parseLong(locationValue);
			else if(scopeValue == 5 || scopeValue == 6 || scopeValue == 8)
				return Long.parseLong(locationValue.substring(1));
			else if(scopeValue == 7)
			{
				List<Object> list = assemblyLocalElectionBodyDAO.getLocalElectionBodyId(Long.parseLong(locationValue.substring(1)));
				return (Long) list.get(0);
			}
			
			return null;
		}catch (Exception e) {
			log.error("Error Occured in getLocationScopeVlaue() method - "+e); 
			return null;
		}
	}
	
		
	public List<SelectOptionVO> getEventGallarySelectList(Long specialPageId,
			String contentType) {
			
		gallarySelectList = new ArrayList<SelectOptionVO>(0);
		try {
			log.debug("Entered into getCandidateGallarySelectList() Method");
	
			List<Object[]> list = specialPageGalleryDAO.getGallariesBySpecialPageId(specialPageId, contentType);
			
			if (list != null && list.size() > 0) {
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
			log.error("Exception Occured in getCandidateGallarySelectList() method - "+ e);
			e.printStackTrace();
			return gallarySelectList;
		}
	}
	public List<SelectOptionVO> getPartyGallarySelectList(Long specialPageId,
			String contentType) {
		try {
			log.debug("Entered into getCandidateGallarySelectList() Method");

			List<SelectOptionVO> gallarySelectList = null;
			List<Object[]> list = specialPageGalleryDAO.getGallariesBySpecialPageId(
					specialPageId, contentType);

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

}
