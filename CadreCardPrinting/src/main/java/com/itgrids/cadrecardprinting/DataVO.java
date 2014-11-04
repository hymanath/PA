package com.itgrids.cadrecardprinting;

import java.util.ArrayList;
import java.util.List;



public class DataVO {

private Long id;
private String name;
private List<DataVO> subList;
private List<DataVO> localbodyList;
private List<DataVO> boothList;

public Long getId() {
return id;
}
public void setId(Long id) {
this.id = id;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public List<DataVO> getSubList() {
return subList;
}
public void setSubList(List<DataVO> subList) {
this.subList = subList;
}
public List<DataVO> getLocalbodyList() {
	return localbodyList;
}
public void setLocalbodyList(List<DataVO> localbodyList) {
	this.localbodyList = localbodyList;
}
public List<DataVO> getBoothList() {
	return boothList;
}
public void setBoothList(List<DataVO> boothList) {
	this.boothList = boothList;
}


}
