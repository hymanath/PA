package com.itgrids.partyanalyst.model;

import java.io.Serializable;

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
@Table(name = "mobile_numbers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MobileNumbers extends BaseModel implements Serializable{
	

	private static final long serialVersionUID = -7139152982408460524L;
	
	private Long mobileNumberId;
	private String mobileNumber;
	private Long mobileSourceTypeId;
	private Long voterId;
	private Long constituencyId;
	private Long boothId;
	
	private MobileSourceType mobileSourceType;
	private Voter voter;
	private Constituency constituency;
	private Booth booth;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mobile_numbers_id", unique = true, nullable = false)
	public Long getMobileNumberId() {
		return mobileNumberId;
	}
	public void setMobileNumberId(Long mobileNumberId) {
		this.mobileNumberId = mobileNumberId;
	}
	
	@Column(name = "mobile_number")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name = "mobile_source_type_id")
	public Long getMobileSourceTypeId() {
		return mobileSourceTypeId;
	}
	public void setMobileSourceTypeId(Long mobileSourceTypeId) {
		this.mobileSourceTypeId = mobileSourceTypeId;
	}
	
	@Column(name = "voter_id")
	public Long getVoterId() {
		return voterId;
	}
	public void setVoterId(Long voterId) {
		this.voterId = voterId;
	}
	
	@Column(name = "constituency_id")
	public Long getConstituencyId() {
		return constituencyId;
	}
	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "voter_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Voter getVoter() {
		return voter;
	}
	public void setVoter(Voter voter) {
		this.voter = voter;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "constituency_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Constituency getConstituency() {
		return constituency;
	}
	public void setConstituency(Constituency constituency) {
		this.constituency = constituency;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mobile_source_type_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public MobileSourceType getMobileSourceType() {
		return mobileSourceType;
	}
	public void setMobileSourceType(MobileSourceType mobileSourceType) {
		this.mobileSourceType = mobileSourceType;
	}
	
	@Column(name="booth_id")
	public Long getBoothId() {
		return boothId;
	}
	public void setBoothId(Long boothId) {
		this.boothId = boothId;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "booth_id" ,insertable = false , updatable = false)
	@LazyToOne(LazyToOneOption.NO_PROXY)
	@org.hibernate.annotations.NotFound(action=NotFoundAction.IGNORE)
	public Booth getBooth() {
		return booth;
	}
	public void setBooth(Booth booth) {
		this.booth = booth;
	}
	
	
	
	
	/*private Long mobile;
	private Long slnoinpart;
	private String idcardNO;
	private Long acId;
	private Long partNo;
	private Long distNo;
	
	
	public MobileNumbers()
	{
		
	}
	public MobileNumbers(Long mobile,Long slnoinpart,String idcardNO,Long acId,Long partNo,Long distNo)
	{
	this.mobile = mobile;
	this.slnoinpart =slnoinpart;
	this.idcardNO =idcardNO;
	this.acId =acId;
	this.partNo = partNo;
	this.distNo = distNo;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDCARD_NO", unique = true, nullable = false)
	public String getIdcardNO() {
		return idcardNO;
	}
	public void setIdcardNO(String idcardNO) {
		this.idcardNO = idcardNO;
	}
	@Column(name = "mobile")
	public Long getMobile() {
		return mobile;
	}
	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
	@Column(name = "slnoinpart")
	public Long getSlnoinpart() {
		return slnoinpart;
	}
	public void setSlnoinpart(Long slnoinpart) {
		this.slnoinpart = slnoinpart;
	}
	
	@Column(name = "AC_ID")
	public Long getAcId() {
		return acId;
	}
	public void setAcId(Long acId) {
		this.acId = acId;
	}
	@Column(name = "PART_NO")
	public Long getPartNo() {
		return partNo;
	}
	
	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}
	@Column(name = "DIST_NO")
	public Long getDistNo() {
		return distNo;
	}
	public void setDistNo(Long distNo) {
		this.distNo = distNo;
	}
	*/
	

}
