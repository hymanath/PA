package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IGallaryDAO;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.dao.ISpecialPageGalleryDAO;
import com.itgrids.partyanalyst.dto.ContentDetailsVO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.GallaryVO;
import com.itgrids.partyanalyst.model.FileGallary;
import com.itgrids.partyanalyst.service.IContentManagementService;
import com.itgrids.partyanalyst.utils.IConstants;

public class ContentManagementService implements IContentManagementService{
	
	private static final Logger log = Logger.getLogger(ContentManagementService.class);
	private IFileGallaryDAO fileGallaryDAO;
	private ISpecialPageGalleryDAO specialPageGalleryDAO;
	private IGallaryDAO gallaryDAO;
	private IPartyGalleryDAO partyGalleryDAO;
	
	public IGallaryDAO getGallaryDAO() {
		return gallaryDAO;
	}

	public void setGallaryDAO(IGallaryDAO gallaryDAO) {
		this.gallaryDAO = gallaryDAO;
	}

	public IPartyGalleryDAO getPartyGalleryDAO() {
		return partyGalleryDAO;
	}

	public void setPartyGalleryDAO(IPartyGalleryDAO partyGalleryDAO) {
		this.partyGalleryDAO = partyGalleryDAO;
	}

	public ISpecialPageGalleryDAO getSpecialPageGalleryDAO() {
		return specialPageGalleryDAO;
	}

	public void setSpecialPageGalleryDAO(
			ISpecialPageGalleryDAO specialPageGalleryDAO) {
		this.specialPageGalleryDAO = specialPageGalleryDAO;
	}

	public IFileGallaryDAO getFileGallaryDAO() {
		return fileGallaryDAO;
	}

	public void setFileGallaryDAO(IFileGallaryDAO fileGallaryDAO) {
		this.fileGallaryDAO = fileGallaryDAO;
	}



	/**
	 * This Method will give the Selected File Details, their related files and Other Galleries Details
	 * @param contentId Long
	 * @param requestFrom String
	 * @param requestPageId Long
	 * @return {@link ContentDetailsVO}
	 * @author Kamalakar Dandu
	 */
	public ContentDetailsVO getSelectedContentAndRelatedGalleries(Long contentId, String requestFrom, Long requestPageId)
	{
		log.debug("Entered into getSelectedContentAndRelatedGalleries() Method");
		try{
			ContentDetailsVO contentDetailsVO = new ContentDetailsVO();
			Long fileId = null;
			List<Long> gallaryIds = null;
			String contentType = null;
			
			fileId = (Long)fileGallaryDAO.getFileIdByFileGallaryId(contentId);
			
			List<Object> list = fileGallaryDAO.getGalleryIdsOfAFile(fileId);
			
			if(list != null && list.size() > 0)
			{
				gallaryIds = new ArrayList<Long>(0);
				for(Object obj : list)
					gallaryIds.add((Long)obj);
				
				List<FileGallary> files = fileGallaryDAO.getFilesOfInGallaries(gallaryIds);
				
				List<GallaryVO> relatedGalleries = new ArrayList<GallaryVO>(0);
				List<GallaryVO> otherGalleries = new ArrayList<GallaryVO>(0);
				GallaryVO relatedGallary = new GallaryVO();
				FileVO fileVO = null;
				List<FileVO> filesList = new ArrayList<FileVO>(0);
				for(FileGallary fileGallary : files)
				{
					fileVO = new FileVO();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					
					if(fileGallary.getFile() == null)
						continue;
					if(contentId.longValue() == fileGallary.getFileGallaryId().longValue())
					{
						fileVO.setIsSelectedContent(true);
						contentType = fileGallary.getGallary().getContentType().getContentType();
						contentDetailsVO.setContentType(contentType);
					}
					
					fileVO.setTitle(fileGallary.getFile().getFileTitle());
					fileVO.setDescription(fileGallary.getFile().getFileDescription());
					fileVO.setContentType(fileGallary.getGallary().getContentType().getContentType());
					fileVO.setContentId(fileGallary.getFileGallaryId());
					fileVO.setPath(fileGallary.getFile().getFilePath());
					fileVO.setFileDate(fileGallary.getFile().getFileDate() == null ? null :
						sdf.format(fileGallary.getFile().getFileDate()));
					fileVO.setSource(fileGallary.getFile().getSourceObj() == null ? null :
						fileGallary.getFile().getSourceObj().getSource());
					filesList.add(fileVO);
				}
				relatedGallary.setFilesList(filesList);
				relatedGalleries.add(relatedGallary);
				contentDetailsVO.setRelatedGalleries(relatedGalleries);
				
				List<Object> otherGalIdsResult = null;
				GallaryVO otherGallary = null;
				List<FileVO> otherFiles = null;
				
				if(requestFrom.equalsIgnoreCase(IConstants.SPECIAL_PAGE))
				{
					otherGalIdsResult = specialPageGalleryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
				}
				else if(requestFrom.equalsIgnoreCase(IConstants.CANDIDATE_PAGE))
				{
					otherGalIdsResult = gallaryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
				}
				else if(requestFrom.equalsIgnoreCase(IConstants.PARTY_PAGE))
				{
					otherGalIdsResult = partyGalleryDAO.getOtherGalleries(requestPageId,gallaryIds,contentType);
				}
				
				for(Object galId : otherGalIdsResult)
				{
					List<Object[]> galInfoList = fileGallaryDAO.getFirstFileAndGallaryInfo((Long)galId);
					if(galInfoList != null && galInfoList.size() > 0)
					{
						Object[] galAndFileInfo = galInfoList.get(0);
						otherGallary = new GallaryVO();
						otherFiles = new ArrayList<FileVO>();
						FileVO otherFileVO = new FileVO();
						
						otherGallary.setGallaryName(galAndFileInfo[0].toString());
						otherGallary.setDescription(galAndFileInfo[1].toString());
						otherGallary.setOrderNo((Long)galAndFileInfo[2]);
						
						otherFileVO.setFileId((Long)galAndFileInfo[3]);
						otherFileVO.setPath(galAndFileInfo[4].toString());
						otherFiles.add(otherFileVO);
						otherGallary.setFilesList(otherFiles);
						otherGalleries.add(otherGallary);
					}
				}
				contentDetailsVO.setOtherGalleries(otherGalleries);
			}
			
			return contentDetailsVO;
		}catch (Exception e) {
			log.debug("Exception occured in getSelectedContentAndRelatedGalleries() Method, Exception is - "+e);
			return null;
		}
	}

}
