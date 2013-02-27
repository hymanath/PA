package com.itgrids.partyanalyst.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "voter_temp")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VoterTemp extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 2150007490509414092L;
	
	private Long id;
	private String voterId;
	private String name;
	private String sex;
	private String age;
	private String houseNo;
	private String guardianName;
	private String relationShip;
	private Long constituencyId;
	private String constituencyName;
	private Long partNo;
	private String boothName;
	private Long serialNo;
	
	public VoterTemp()
	{}
	
	public VoterTemp(String voterId,String name,String sex,String age,
			String houseNo,String guardianName,String relationShip,
			Long constituencyId,String constituencyName,Long partNo,String boothName)
	{
		this.voterId = voterId;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.houseNo = houseNo;
		this.guardianName = guardianName;
		this.relationShip = relationShip;
		this.constituencyId = constituencyId;
		this.constituencyName = constituencyName;
		this.partNo = partNo;
		this.boothName = boothName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "voter_id", length = 45)
	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	@Column(name = "name", length = 45)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex", length = 45)
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "age", length = 45)
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	@Column(name = "house_no", length = 45)
	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	@Column(name = "guardian_name", length = 45)
	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	@Column(name = "relation", length = 45)
	public String getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(String relationShip) {
		this.relationShip = relationShip;
	}

	@Column(name = "constituency_id", length = 45)
	public Long getConstituencyId() {
		return constituencyId;
	}

	public void setConstituencyId(Long constituencyId) {
		this.constituencyId = constituencyId;
	}

	@Column(name = "constituency_name", length = 45)
	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	@Column(name = "booth_id", length = 45)
	public Long getPartNo() {
		return partNo;
	}

	public void setPartNo(Long partNo) {
		this.partNo = partNo;
	}

	@Column(name = "booth_name", length = 45)
	public String getBoothName() {
		return boothName;
	}

	public void setBoothName(String boothName) {
		this.boothName = boothName;
	}

	@Column(name = "sno", length = 4)
	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	

}
