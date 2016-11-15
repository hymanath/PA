package com.itgrids.partyanalyst.dto;

import java.io.File;
import java.io.Serializable;

public class ImageCadreVO implements Serializable{
	
	private static final long serialVersionUID = 2389557435175002120L;
	
	private String 	memberShipNo;
	private Long    constituencyId;
	private String	photoType;
	private String  dataSourceType;
	private String  imageBase64String;
	private File    uploadImage;
	private Long    voterId;
	private Long    tdpCadreId;
	private boolean isUpdate;
	
	private String  imagePath;
	private String  imageSaveStatus;
	private String  imageIssue;
	
	
	public String getMemberShipNo() {
		return memberShipNo;
	}
	public void setMemberShipNo(String memberShipNo) {
		this.memberShipNo = memberShipNo;
	}
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public String getDataSourceType() {
		return dataSourceType;
	}
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
	}
	public String getImageBase64String() {
		return imageBase64String;
	}
	public void setImageBase64String(String imageBase64String) {
		this.imageBase64String = imageBase64String;
	}
	public File getUploadImage() {
		return uploadImage;
	}
	public void setUploadImage(File uploadImage) {
		this.uploadImage = uploadImage;
	}
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public boolean isUpdate() {
		return isUpdate;
	}
	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public String getImageSaveStatus() {
		return imageSaveStatus;
	}
	public void setImageSaveStatus(String imageSaveStatus) {
		this.imageSaveStatus = imageSaveStatus;
	}
	public String getImageIssue() {
		return imageIssue;
	}
	public void setImageIssue(String imageIssue) {
		this.imageIssue = imageIssue;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
