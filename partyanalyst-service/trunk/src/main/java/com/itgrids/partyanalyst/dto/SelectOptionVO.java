package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SelectOptionVO implements Serializable, Comparable<SelectOptionVO> {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Long id;
	private String url;
	private String type;
	private String value;
	private String location;
	private String villageCovered;
	private List<SelectOptionVO> selectOptionsList = new ArrayList<SelectOptionVO>(0);
	private List<SelectOptionVO> selectOptionsList1 = new ArrayList<SelectOptionVO>(0);
	private boolean hampletPresent;
	private String partno;
	private List<Long> locationValuesList;
	private Long populateId;
	private Long orderId;
	private Long parentUserId;
	private Long mainAccountId;
	private boolean flag;
	

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Long getPopulateId() {
		return populateId;
	}

	public void setPopulateId(Long populateId) {
		this.populateId = populateId;
	}

	public String getPartno() {
		return partno;
	}

	public void setPartno(String partno) {
		this.partno = partno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SelectOptionVO(){}
	
	public SelectOptionVO(Long id, String name)
	{
		this.id = id;
		this.name = name;	
	}
	
	public SelectOptionVO(Long id, String name,String location,String villageCovered)
	{
		this.id = id;
		this.name = name;
		this.location = location;	
		this.villageCovered = villageCovered;	
	}
	
	public SelectOptionVO(Long id, String name,String url){
		this.id = id;
		this.name = name;
		this.url = url;
	}
	
	public SelectOptionVO(List<Long> locationValuesList, String type)
	{
		this.locationValuesList = locationValuesList;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public List<SelectOptionVO> getSelectOptionsList1() {
		return selectOptionsList1;
	}

	public void setSelectOptionsList1(List<SelectOptionVO> selectOptionsList1) {
		this.selectOptionsList1 = selectOptionsList1;
	}

	@Override
	public boolean equals(Object obj){
		if(id==null)
			id = -1L;
		if(obj instanceof SelectOptionVO){
			SelectOptionVO vo = (SelectOptionVO) obj;
			return this.id.equals(vo.getId());
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		if(id==null)
			id = -1L;
		return this.id.intValue();
	}

	public int compareTo(SelectOptionVO obj) {
		if(obj instanceof SelectOptionVO){
			SelectOptionVO vo = (SelectOptionVO) obj;
			return name.compareToIgnoreCase(vo.getName());
		}
		else
			return 0;
	}

	public List<SelectOptionVO> getSelectOptionsList() {
		return selectOptionsList;
	}

	public void setSelectOptionsList(List<SelectOptionVO> selectOptionsList) {
		this.selectOptionsList = selectOptionsList;
	}
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getVillageCovered() {
		return villageCovered;
	}

	public void setVillageCovered(String villageCovered) {
		this.villageCovered = villageCovered;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public boolean isHampletPresent() {
		return hampletPresent;
	}

	public void setHampletPresent(boolean hampletPresent) {
		this.hampletPresent = hampletPresent;
	}

	public List<Long> getLocationValuesList() {
		return locationValuesList;
	}

	public void setLocationValuesList(List<Long> locationValuesList) {
		this.locationValuesList = locationValuesList;
	}

	public Long getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(Long parentUserId) {
		this.parentUserId = parentUserId;
	}

	public Long getMainAccountId() {
		return mainAccountId;
	}

	public void setMainAccountId(Long mainAccountId) {
		this.mainAccountId = mainAccountId;
	}
	
	
}
