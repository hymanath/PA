/**
 * @author SRAVANTH
 * Jun 15, 2016
 * MobileDetailsVO.java
 * PA - SERV - com.itgrids.partyanalyst.dto
 */
package com.itgrids.partyanalyst.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SRAVANTH
 * @date Jun 15, 2016
 */
public class MobileDetailsVO {

	private Long id;
	private String name;
	
	private String mobileNo;
	private Long tdpCadreId;
	private Long connectionTypeId;
	private String connectionType;
	private Long networkProviderId;
	private String networkProvider;
	private Long brandId;
	private String brand;
	private Long brandModelId;
	private String brandModel;
	private Long featureId;
	private String feature;
	private Long networkTypeId;
	private String networkType;
	private List<MobileDetailsVO> subList = new ArrayList<MobileDetailsVO>(0);
	
	
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Long getTdpCadreId() {
		return tdpCadreId;
	}
	public void setTdpCadreId(Long tdpCadreId) {
		this.tdpCadreId = tdpCadreId;
	}
	public Long getConnectionTypeId() {
		return connectionTypeId;
	}
	public void setConnectionTypeId(Long connectionTypeId) {
		this.connectionTypeId = connectionTypeId;
	}
	public String getConnectionType() {
		return connectionType;
	}
	public void setConnectionType(String connectionType) {
		this.connectionType = connectionType;
	}
	public Long getNetworkProviderId() {
		return networkProviderId;
	}
	public void setNetworkProviderId(Long networkProviderId) {
		this.networkProviderId = networkProviderId;
	}
	public String getNetworkProvider() {
		return networkProvider;
	}
	public void setNetworkProvider(String networkProvider) {
		this.networkProvider = networkProvider;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public Long getFeatureId() {
		return featureId;
	}
	public void setFeatureId(Long featureId) {
		this.featureId = featureId;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public Long getNetworkTypeId() {
		return networkTypeId;
	}
	public void setNetworkTypeId(Long networkTypeId) {
		this.networkTypeId = networkTypeId;
	}
	public String getNetworkType() {
		return networkType;
	}
	public void setNetworkType(String networkType) {
		this.networkType = networkType;
	}
	public Long getBrandModelId() {
		return brandModelId;
	}
	public void setBrandModelId(Long brandModelId) {
		this.brandModelId = brandModelId;
	}
	public String getBrandModel() {
		return brandModel;
	}
	public void setBrandModel(String brandModel) {
		this.brandModel = brandModel;
	}
	public List<MobileDetailsVO> getSubList() {
		return subList;
	}
	public void setSubList(List<MobileDetailsVO> subList) {
		this.subList = subList;
	}
}
