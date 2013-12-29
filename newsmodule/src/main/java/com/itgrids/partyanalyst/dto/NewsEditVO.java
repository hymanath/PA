package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class NewsEditVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6090715857764998229L;
    
	private String fileTitle;
	private String fileDate;
	private Long newsimportance;
	private String titleCheckBox;
	private String fileDescription;
	private String fileDescriptionCheckBox;
	private String defaultImg;
	private File imageForDisplay;
	private String imgToDisplayDeleted;
	private Integer day;
	private Integer month;
	private Integer year;
	private Long fileId;
	List<CandidatePartyDestinationVO> sourceVOList;
	List<CandidatePartyDestinationVO> destinationVOList;
	private List<FileSourceVO> fileSourceVOList;
	private List<SelectOptionVO> selectOptionVOList;
	private List<SelectOptionVO> partyList;
	private List<FileVO> fileVOLIst;
	private List<SelectOptionVO> benefitsOptionList;
	private FileVO fileVO;
	private List<SelectOptionVO> keywordsList;
	private List<SelectOptionVO> newsEdition; 
	
	private String synopsysDescription;
	private String synopsysCheckBox;
	
	
	
	public String getSynopsysDescription() {
		return synopsysDescription;
	}

	public void setSynopsysDescription(String synopsysDescription) {
		this.synopsysDescription = synopsysDescription;
	}

	public String getSynopsysCheckBox() {
		return synopsysCheckBox;
	}

	public void setSynopsysCheckBox(String synopsysCheckBox) {
		this.synopsysCheckBox = synopsysCheckBox;
	}

	public List<SelectOptionVO> getKeywordsList() {
		return keywordsList;
	}

	public void setKeywordsList(List<SelectOptionVO> keywordsList) {
		this.keywordsList = keywordsList;
	}

	public String getFileDescriptionCheckBox() {
		return fileDescriptionCheckBox;
	}

	public void setFileDescriptionCheckBox(String fileDescriptionCheckBox) {
		this.fileDescriptionCheckBox = fileDescriptionCheckBox;
	}

	public FileVO getFileVO() {
		return fileVO;
	}

	public void setFileVO(FileVO fileVO) {
		this.fileVO = fileVO;
	}

	public List<SelectOptionVO> getBenefitsOptionList() {
		return benefitsOptionList;
	}

	public void setBenefitsOptionList(List<SelectOptionVO> benefitsOptionList) {
		this.benefitsOptionList = benefitsOptionList;
	}

	public List<FileVO> getFileVOLIst() {
		return fileVOLIst;
	}

	public void setFileVOLIst(List<FileVO> fileVOLIst) {
		this.fileVOLIst = fileVOLIst;
	}

	public List<SelectOptionVO> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<SelectOptionVO> partyList) {
		this.partyList = partyList;
	}

	public List<FileSourceVO> getFileSourceVOList() {
		return fileSourceVOList;
	}

	public void setFileSourceVOList(List<FileSourceVO> fileSourceVOList) {
		this.fileSourceVOList = fileSourceVOList;
	}

	public List<SelectOptionVO> getSelectOptionVOList() {
		return selectOptionVOList;
	}

	public void setSelectOptionVOList(List<SelectOptionVO> selectOptionVOList) {
		this.selectOptionVOList = selectOptionVOList;
	}

	public List<CandidatePartyDestinationVO> getDestinationVOList() {
		return destinationVOList;
	}

	public void setDestinationVOList(
			List<CandidatePartyDestinationVO> destinationVOList) {
		this.destinationVOList = destinationVOList;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public Long getNewsimportance() {
		return newsimportance;
	}

	public void setNewsimportance(Long newsimportance) {
		this.newsimportance = newsimportance;
	}

	public String getTitleCheckBox() {
		return titleCheckBox;
	}

	public void setTitleCheckBox(String titleCheckBox) {
		this.titleCheckBox = titleCheckBox;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getDefaultImg() {
		return defaultImg;
	}

	public void setDefaultImg(String defaultImg) {
		this.defaultImg = defaultImg;
	}

	public File getImageForDisplay() {
		return imageForDisplay;
	}

	public void setImageForDisplay(File imageForDisplay) {
		this.imageForDisplay = imageForDisplay;
	}

	public String getImgToDisplayDeleted() {
		return imgToDisplayDeleted;
	}

	public void setImgToDisplayDeleted(String imgToDisplayDeleted) {
		this.imgToDisplayDeleted = imgToDisplayDeleted;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getFileId() {
		return fileId;
	}

	public void setFileId(Long fileId) {
		this.fileId = fileId;
	}

	public List<CandidatePartyDestinationVO> getSourceVOList() {
		return sourceVOList;
	}

	public void setSourceVOList(List<CandidatePartyDestinationVO> sourceVOList) {
		this.sourceVOList = sourceVOList;
	}

	public List<SelectOptionVO> getNewsEdition() {
		return newsEdition;
	}

	public void setNewsEdition(List<SelectOptionVO> newsEdition) {
		this.newsEdition = newsEdition;
	}

	
	
}
