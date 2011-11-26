package com.itgrids.partyanalyst.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.WordUtils;
import org.apache.log4j.Logger;

import com.itgrids.partyanalyst.dao.IFileGallaryDAO;
import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.IPartyGalleryDAO;
import com.itgrids.partyanalyst.dao.IPartyProfileDescriptionDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.dto.PartyVO;
import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.Party;
import com.itgrids.partyanalyst.service.IPartyDetailsService;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyDetailsService implements IPartyDetailsService {
	private static final Logger log = Logger
			.getLogger(PartyDetailsService.class);
	private IPartyDAO partyDAO;
	private IPartyProfileDescriptionDAO partyProfileDescriptionDAO;
	private IFileGallaryDAO fileGallaryDAO;
	private IPartyGalleryDAO partyGalleryDAO;

	
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

	public List<FileVO> getNewsToDisplay(Long partyId, int firstResult,
			int maxResult, String queryType) {
		List<FileVO> retValue = new ArrayList<FileVO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			List<File> file = fileGallaryDAO.getFirstFourNewsForParty(partyId,firstResult,maxResult,queryType);
			for (File file2 : file) {
				FileVO fileVO = new FileVO();
				fileVO.setFileId((Long) file2.getFileId());
				fileVO.setFileName1(file2.getFileName() != null ? file2
						.getFileName() : "");
				fileVO.setFilePath1(IConstants.UPLOADED_FILES + "/"
						+ file2.getFilePath());
				fileVO.setFileTitle1(file2.getFileTitle() != null ? file2
						.getFileTitle() : "");
				fileVO
						.setFileDescription1(file2.getFileDescription() != null ? file2
								.getFileDescription()
								: "");
				fileVO.setSource(file2.getSourceObj() != null ? file2
						.getSourceObj().getSource() : "");
				fileVO.setLanguage(file2.getLanguage() != null ? file2
						.getLanguage().getLanguage() : "");
				fileVO.setFileDate(file2.getFileDate() != null ? file2
						.getFileDate().toString() : "");
				retValue.add(fileVO);
			}

			return retValue;
		} catch (Exception e) {
			e.printStackTrace();
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

	public List<FileVO> getPartyLatestVideos(Long partyId,
			Integer startIndex, Integer maxRecords) {
		try {
			log.debug("Entered into getCandidateLatestVideos() Method");

			List<FileVO> fileList = null;
			List<File> list = fileGallaryDAO.getPartyLatestVideos(
					partyId, startIndex, maxRecords);

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

	public List<FileVO> getFirstThreePhotoGallaryDetail(Long partyId){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = partyGalleryDAO.getPartyGallaryDetail(partyId,0,20,IConstants.PHOTO_GALLARY);
		
		for(Object[] gallary: results){
			FileVO fileVO = new FileVO();
		    List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(Object[] startingRecord: record){
		    	fileVO.setFileId((Long)startingRecord[0]);
		    	fileVO.setName(startingRecord[1] != null ? startingRecord[1].toString() :"");		    			    	
		    	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+startingRecord[1].toString());
		    	String title =""; 
		   	    if(startingRecord[3] != null && startingRecord[3].toString().length()>=18)
		   	    {
		   	    	title = startingRecord[3].toString().substring(0, 17);
		   	    	title = title+"...";
		   	    }
		   	    else
		   	    {
		   	    if(startingRecord[3] != null)
		   	    {	
		   	    	title = startingRecord[3].toString();
		   	    }
		   	    }
		    	fileVO.setTitle(title);
		    	
		    }
		    fileVO.setGallaryId((Long)gallary[0]);
		    fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
		    fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() :"");
		    fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString() :"");
		    fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() :"");
		    fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() :"");
		    retValue.add(fileVO);	  
		}
		return retValue;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 return retValue;
	 }
	}
	
	public List<FileVO> getPartyPhotoGallaryDetailWithOutGallerySizeZero(Long partyId,int firstRecord,int maxRecord,String type){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = partyGalleryDAO.getPartyGallaryDetail(partyId,firstRecord,maxRecord,type);
		
		for(Object[] gallary: results){
			FileVO fileVO = new FileVO();
		    List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(Object[] startingRecord: record){
		    if(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()>0L)
		    {
		    	fileVO.setFileId((Long)startingRecord[0]);
		    	fileVO.setName(startingRecord[1] != null ? startingRecord[1].toString() :"");		    			    	
		    	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+startingRecord[1].toString());
		    	fileVO.setTitle(startingRecord[3] != null ? startingRecord[3].toString() :"");
		    	fileVO.setGallaryId((Long)gallary[0]);
			    fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
			    fileVO.setGallaryName(gallary[1] != null ? gallary[1].toString() :"");
			    fileVO.setGallaryDescription(gallary[2] != null ? gallary[2].toString() :"");
			    fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() :"");
			    fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() :"");
			    retValue.add(fileVO);
		    }
			    
	      }
		    	  
		}
		return retValue;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 return retValue;
	 }
	}
	
	public List<FileVO> getPartyPhotoGallaryDetail(Long partyId,int firstRecord,int maxRecord,String type){
		 List<FileVO> retValue = new ArrayList<FileVO>();
	 try{
		List<Object[]> results = partyGalleryDAO.getPartyGallaryDetail(partyId,firstRecord,maxRecord,type);
		
		for(Object[] gallary: results){
			FileVO fileVO = new FileVO();
		    List<Object[]> record = fileGallaryDAO.getStartingRecordInGallary((Long)gallary[0]);
		    for(Object[] startingRecord: record){
		    	fileVO.setFileId((Long)startingRecord[0]);
		    	fileVO.setName(startingRecord[1] != null ? WordUtils.capitalize(startingRecord[1].toString()) :"");
		    	if(type.equalsIgnoreCase(IConstants.VIDEO_GALLARY))
		    		fileVO.setPath(startingRecord[2].toString());
		    	else
		    	fileVO.setPath(IConstants.UPLOADED_FILES+"/"+startingRecord[1].toString());
		    	
		    	fileVO.setTitle(startingRecord[3] != null ? WordUtils.capitalize(startingRecord[3].toString()) :"");
		    	
		    }
		    fileVO.setGallaryId((Long)gallary[0]);
		    fileVO.setSizeOfGallary((long)(fileGallaryDAO.getAllRecordInGallary((Long)gallary[0]).size()));
		    fileVO.setGallaryName(gallary[1] != null ? WordUtils.capitalize(gallary[1].toString()) :"");
		    fileVO.setGallaryDescription(gallary[2] != null ? WordUtils.capitalize(gallary[2].toString()) :"");
		    fileVO.setGallaryCreatedDate(gallary[3] != null ? gallary[3].toString() :"");
		    fileVO.setGallaryUpdatedDate(gallary[4] != null ? gallary[4].toString() :"");
		    retValue.add(fileVO);	  
		}
		return retValue;
	 }
	 catch(Exception e)
	 {
		 e.printStackTrace();
		 return retValue;
	 }
	}
}
