/**
 * 
 */
package com.itgrids.partyanalyst.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 *
 */
public class CadreDetailsInfoVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CadreInfo> cadreInfo;
	private List<CadreRegionInfoVO> cadreRegionInfo;
	private String cadreType;
	private String region;
	private String regionId;
	private List<SelectOptionVO> zeroCadresRegion;
	private List<StateToHamletVO> zeroCadresRegion1;
	
	
	public List<CadreInfo> getCadreInfo() {
		return cadreInfo;
	}
	public void setCadreInfo(List<CadreInfo> cadreInfo) {
		this.cadreInfo = cadreInfo;
	}
	public List<CadreRegionInfoVO> getCadreRegionInfo() {
		return cadreRegionInfo;
	}
	public void setCadreRegionInfo(List<CadreRegionInfoVO> cadreRegionInfo) {
		this.cadreRegionInfo = cadreRegionInfo;
	}
	public String getCadreType() {
		return cadreType;
	}
	public void setCadreType(String cadreType) {
		this.cadreType = cadreType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	public List<SelectOptionVO> getZeroCadresRegion() {
		return zeroCadresRegion;
	}
	public void setZeroCadresRegion(List<SelectOptionVO> zeroCadresRegion) {
		this.zeroCadresRegion = zeroCadresRegion;
	}
	public List<StateToHamletVO> getZeroCadresRegion1() {
		return zeroCadresRegion1;
	}
	public void setZeroCadresRegion1(List<StateToHamletVO> zeroCadresRegion1) {
		this.zeroCadresRegion1 = zeroCadresRegion1;
	}
	

}
