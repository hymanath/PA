package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "mobile_number_details")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileNumberDetails extends BaseModel implements Serializable{

	private Long mobileNumberDetailsId;
	private String mobileNo;
	private Long mobileConnectionTypeId;
	private Long mobileNetworkProviderId;
	private Long mobileBrandId;
	private Long mobileBrandModelId;
	private Long mobileDeviceFeatureId;
	private Long mobileNetworkTypeId;
	
	private MobileConnectionType mobileConnectionType;
	private MobileNetworkProvider mobileNetworkProvider;
	private MobileBrand mobileBrand;
	private MobileBrandModel mobileBrandModel;
	private MobileDeviceFeature mobileDeviceFeature;
	private MobileNetworkType mobileNetworkType;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_number_details_id", unique = true, nullable = false)
	public Long getMobileNumberDetailsId() {
		return mobileNumberDetailsId;
	}
	public void setMobileNumberDetailsId(Long mobileNumberDetailsId) {
		this.mobileNumberDetailsId = mobileNumberDetailsId;
	}
	
	@Column(name="mobile_no")
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	
	@Column(name="mobile_connection_type_id")
	public Long getMobileConnectionTypeId() {
		return mobileConnectionTypeId;
	}
	public void setMobileConnectionTypeId(Long mobileConnectionTypeId) {
		this.mobileConnectionTypeId = mobileConnectionTypeId;
	}
	
	@Column(name="mobile_network_provider_id")
	public Long getMobileNetworkProviderId() {
		return mobileNetworkProviderId;
	}
	public void setMobileNetworkProviderId(Long mobileNetworkProviderId) {
		this.mobileNetworkProviderId = mobileNetworkProviderId;
	}
	
	@Column(name="mobile_brand_id")
	public Long getMobileBrandId() {
		return mobileBrandId;
	}
	public void setMobileBrandId(Long mobileBrandId) {
		this.mobileBrandId = mobileBrandId;
	}
	
	@Column(name="mobile_brand_model_id")
	public Long getMobileBrandModelId() {
		return mobileBrandModelId;
	}
	public void setMobileBrandModelId(Long mobileBrandModelId) {
		this.mobileBrandModelId = mobileBrandModelId;
	}
	
	@Column(name="mobile_device_feature_id")
	public Long getMobileDeviceFeatureId() {
		return mobileDeviceFeatureId;
	}
	public void setMobileDeviceFeatureId(Long mobileDeviceFeatureId) {
		this.mobileDeviceFeatureId = mobileDeviceFeatureId;
	}
	
	@Column(name="mobile_network_type_id")
	public Long getMobileNetworkTypeId() {
		return mobileNetworkTypeId;
	}
	public void setMobileNetworkTypeId(Long mobileNetworkTypeId) {
		this.mobileNetworkTypeId = mobileNetworkTypeId;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_connection_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileConnectionType getMobileConnectionType() {
		return mobileConnectionType;
	}
	public void setMobileConnectionType(MobileConnectionType mobileConnectionType) {
		this.mobileConnectionType = mobileConnectionType;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_network_provider_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileNetworkProvider getMobileNetworkProvider() {
		return mobileNetworkProvider;
	}
	public void setMobileNetworkProvider(MobileNetworkProvider mobileNetworkProvider) {
		this.mobileNetworkProvider = mobileNetworkProvider;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_brand_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileBrand getMobileBrand() {
		return mobileBrand;
	}
	public void setMobileBrand(MobileBrand mobileBrand) {
		this.mobileBrand = mobileBrand;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_brand_model_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileBrandModel getMobileBrandModel() {
		return mobileBrandModel;
	}
	public void setMobileBrandModel(MobileBrandModel mobileBrandModel) {
		this.mobileBrandModel = mobileBrandModel;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_device_feature_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileDeviceFeature getMobileDeviceFeature() {
		return mobileDeviceFeature;
	}
	public void setMobileDeviceFeature(MobileDeviceFeature mobileDeviceFeature) {
		this.mobileDeviceFeature = mobileDeviceFeature;
	}
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="mobile_network_type_id", insertable=false, updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileNetworkType getMobileNetworkType() {
		return mobileNetworkType;
	}
	public void setMobileNetworkType(MobileNetworkType mobileNetworkType) {
		this.mobileNetworkType = mobileNetworkType;
	}
}
