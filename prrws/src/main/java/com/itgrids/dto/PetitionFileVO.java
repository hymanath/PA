package com.itgrids.dto;

import java.util.ArrayList;
import java.util.List;

public class PetitionFileVO implements java.io.Serializable{

	private Long id;
	private String key;
	private String path;
	private String value;
	private List<PetitionFileVO> filesList = new ArrayList<PetitionFileVO>(0);
	
	public PetitionFileVO(){}
	public PetitionFileVO(Long id,String value){
		this.id= id;
		this.value = value;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public PetitionFileVO(String key,String value){
		this.key= key;
		this.value = value;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public List<PetitionFileVO> getFilesList() {
		return filesList;
	}
	public void setFilesList(List<PetitionFileVO> filesList) {
		this.filesList = filesList;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
}
